package com.google.firebase.remoteconfig.internal;

import android.text.format.DateUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ConfigFetchHandler {
    static final int HTTP_TOO_MANY_REQUESTS = 429;
    private final AnalyticsConnector analyticsConnector;
    private final Clock clock;
    private final Map<String, String> customHttpHeaders;
    private final Executor executor;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final ConfigFetchHttpClient frcBackendApiClient;
    private final ConfigMetadataClient frcMetadata;
    private final Random randomGenerator;
    public static final long DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS = TimeUnit.HOURS.toSeconds(12);
    static final int[] BACKOFF_TIME_DURATIONS_IN_MINUTES = {2, 4, 8, 16, 32, 64, 128, 256};

    public static class FetchResponse {
        private final Date fetchTime;
        private final ConfigContainer fetchedConfigs;
        private final String lastFetchETag;
        private final int status;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Status {
            public static final int BACKEND_HAS_NO_UPDATES = 1;
            public static final int BACKEND_UPDATES_FETCHED = 0;
            public static final int LOCAL_STORAGE_USED = 2;
        }

        private FetchResponse(Date date, int i9, ConfigContainer configContainer, String str) {
            this.fetchTime = date;
            this.status = i9;
            this.fetchedConfigs = configContainer;
            this.lastFetchETag = str;
        }

        public static FetchResponse forBackendHasNoUpdates(Date date) {
            return new FetchResponse(date, 1, null, null);
        }

        public static FetchResponse forBackendUpdatesFetched(ConfigContainer configContainer, String str) {
            return new FetchResponse(configContainer.getFetchTime(), 0, configContainer, str);
        }

        public static FetchResponse forLocalStorageUsed(Date date) {
            return new FetchResponse(date, 2, null, null);
        }

        public Date getFetchTime() {
            return this.fetchTime;
        }

        public ConfigContainer getFetchedConfigs() {
            return this.fetchedConfigs;
        }

        public String getLastFetchETag() {
            return this.lastFetchETag;
        }

        public int getStatus() {
            return this.status;
        }
    }

    public ConfigFetchHandler(FirebaseInstallationsApi firebaseInstallationsApi, AnalyticsConnector analyticsConnector, Executor executor, Clock clock, Random random, ConfigCacheClient configCacheClient, ConfigFetchHttpClient configFetchHttpClient, ConfigMetadataClient configMetadataClient, Map<String, String> map) {
        this.firebaseInstallations = firebaseInstallationsApi;
        this.analyticsConnector = analyticsConnector;
        this.executor = executor;
        this.clock = clock;
        this.randomGenerator = random;
        this.fetchedConfigsCache = configCacheClient;
        this.frcBackendApiClient = configFetchHttpClient;
        this.frcMetadata = configMetadataClient;
        this.customHttpHeaders = map;
    }

    private boolean areCachedFetchConfigsValid(long j9, Date date) {
        Date lastSuccessfulFetchTime = this.frcMetadata.getLastSuccessfulFetchTime();
        if (lastSuccessfulFetchTime.equals(ConfigMetadataClient.LAST_FETCH_TIME_NO_FETCH_YET)) {
            return false;
        }
        return date.before(new Date(lastSuccessfulFetchTime.getTime() + TimeUnit.SECONDS.toMillis(j9)));
    }

    private FirebaseRemoteConfigServerException createExceptionWithGenericMessage(FirebaseRemoteConfigServerException firebaseRemoteConfigServerException) throws FirebaseRemoteConfigClientException {
        String str;
        int httpStatusCode = firebaseRemoteConfigServerException.getHttpStatusCode();
        if (httpStatusCode == 401) {
            str = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
        } else if (httpStatusCode == 403) {
            str = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
        } else {
            if (httpStatusCode == HTTP_TOO_MANY_REQUESTS) {
                throw new FirebaseRemoteConfigClientException("The throttled response from the server was not handled correctly by the FRC SDK.");
            }
            if (httpStatusCode != 500) {
                switch (httpStatusCode) {
                    case 502:
                    case 503:
                    case 504:
                        str = "The server is unavailable. Please try again later.";
                        break;
                    default:
                        str = "The server returned an unexpected error.";
                        break;
                }
            } else {
                str = "There was an internal server error.";
            }
        }
        return new FirebaseRemoteConfigServerException(firebaseRemoteConfigServerException.getHttpStatusCode(), "Fetch failed: " + str, firebaseRemoteConfigServerException);
    }

    private String createThrottledMessage(long j9) {
        return String.format("Fetch is throttled. Please wait before calling fetch again: %s", DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(j9)));
    }

    private FetchResponse fetchFromBackend(String str, String str2, Date date) throws FirebaseRemoteConfigFetchThrottledException, FirebaseRemoteConfigServerException {
        try {
            FetchResponse fetchResponseFetch = this.frcBackendApiClient.fetch(this.frcBackendApiClient.createHttpURLConnection(), str, str2, getUserProperties(), this.frcMetadata.getLastFetchETag(), this.customHttpHeaders, date);
            if (fetchResponseFetch.getLastFetchETag() != null) {
                this.frcMetadata.setLastFetchETag(fetchResponseFetch.getLastFetchETag());
            }
            this.frcMetadata.resetBackoff();
            return fetchResponseFetch;
        } catch (FirebaseRemoteConfigServerException e9) {
            ConfigMetadataClient.BackoffMetadata backoffMetadataUpdateAndReturnBackoffMetadata = updateAndReturnBackoffMetadata(e9.getHttpStatusCode(), date);
            if (shouldThrottle(backoffMetadataUpdateAndReturnBackoffMetadata, e9.getHttpStatusCode())) {
                throw new FirebaseRemoteConfigFetchThrottledException(backoffMetadataUpdateAndReturnBackoffMetadata.getBackoffEndTime().getTime());
            }
            throw createExceptionWithGenericMessage(e9);
        }
    }

    private Task<FetchResponse> fetchFromBackendAndCacheResponse(String str, String str2, Date date) {
        try {
            FetchResponse fetchResponseFetchFromBackend = fetchFromBackend(str, str2, date);
            return fetchResponseFetchFromBackend.getStatus() != 0 ? Tasks.forResult(fetchResponseFetchFromBackend) : this.fetchedConfigsCache.put(fetchResponseFetchFromBackend.getFetchedConfigs()).onSuccessTask(this.executor, ConfigFetchHandler$$Lambda$4.lambdaFactory$(fetchResponseFetchFromBackend));
        } catch (FirebaseRemoteConfigException e9) {
            return Tasks.forException(e9);
        }
    }

    private Task<FetchResponse> fetchIfCacheExpiredAndNotThrottled(Task<ConfigContainer> task, long j9) {
        Task taskContinueWithTask;
        Date date = new Date(this.clock.currentTimeMillis());
        if (task.isSuccessful() && areCachedFetchConfigsValid(j9, date)) {
            return Tasks.forResult(FetchResponse.forLocalStorageUsed(date));
        }
        Date backoffEndTimeInMillis = getBackoffEndTimeInMillis(date);
        if (backoffEndTimeInMillis != null) {
            taskContinueWithTask = Tasks.forException(new FirebaseRemoteConfigFetchThrottledException(createThrottledMessage(backoffEndTimeInMillis.getTime() - date.getTime()), backoffEndTimeInMillis.getTime()));
        } else {
            Task<String> id = this.firebaseInstallations.getId();
            Task<InstallationTokenResult> token = this.firebaseInstallations.getToken(false);
            taskContinueWithTask = Tasks.whenAllComplete((Task<?>[]) new Task[]{id, token}).continueWithTask(this.executor, ConfigFetchHandler$$Lambda$2.lambdaFactory$(this, id, token, date));
        }
        return taskContinueWithTask.continueWithTask(this.executor, ConfigFetchHandler$$Lambda$3.lambdaFactory$(this, date));
    }

    private Date getBackoffEndTimeInMillis(Date date) {
        Date backoffEndTime = this.frcMetadata.getBackoffMetadata().getBackoffEndTime();
        if (date.before(backoffEndTime)) {
            return backoffEndTime;
        }
        return null;
    }

    private long getRandomizedBackoffDurationInMillis(int i9) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        int[] iArr = BACKOFF_TIME_DURATIONS_IN_MINUTES;
        return (timeUnit.toMillis(iArr[Math.min(i9, iArr.length) - 1]) / 2) + this.randomGenerator.nextInt((int) r0);
    }

    private Map<String, String> getUserProperties() {
        HashMap map = new HashMap();
        AnalyticsConnector analyticsConnector = this.analyticsConnector;
        if (analyticsConnector == null) {
            return map;
        }
        for (Map.Entry<String, Object> entry : analyticsConnector.getUserProperties(false).entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }

    private boolean isThrottleableServerError(int i9) {
        return i9 == HTTP_TOO_MANY_REQUESTS || i9 == 502 || i9 == 503 || i9 == 504;
    }

    public static /* synthetic */ Task lambda$fetch$0(ConfigFetchHandler configFetchHandler, long j9, Task task) {
        return configFetchHandler.fetchIfCacheExpiredAndNotThrottled(task, j9);
    }

    public static /* synthetic */ Task lambda$fetchFromBackendAndCacheResponse$3(FetchResponse fetchResponse, ConfigContainer configContainer) {
        return Tasks.forResult(fetchResponse);
    }

    public static /* synthetic */ Task lambda$fetchIfCacheExpiredAndNotThrottled$1(ConfigFetchHandler configFetchHandler, Task task, Task task2, Date date, Task task3) {
        return !task.isSuccessful() ? Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for fetch.", task.getException())) : !task2.isSuccessful() ? Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for fetch.", task2.getException())) : configFetchHandler.fetchFromBackendAndCacheResponse((String) task.getResult(), ((InstallationTokenResult) task2.getResult()).getToken(), date);
    }

    public static /* synthetic */ Task lambda$fetchIfCacheExpiredAndNotThrottled$2(ConfigFetchHandler configFetchHandler, Date date, Task task) {
        configFetchHandler.updateLastFetchStatusAndTime(task, date);
        return task;
    }

    private boolean shouldThrottle(ConfigMetadataClient.BackoffMetadata backoffMetadata, int i9) {
        return backoffMetadata.getNumFailedFetches() > 1 || i9 == HTTP_TOO_MANY_REQUESTS;
    }

    private ConfigMetadataClient.BackoffMetadata updateAndReturnBackoffMetadata(int i9, Date date) {
        if (isThrottleableServerError(i9)) {
            updateBackoffMetadataWithLastFailedFetchTime(date);
        }
        return this.frcMetadata.getBackoffMetadata();
    }

    private void updateBackoffMetadataWithLastFailedFetchTime(Date date) {
        int numFailedFetches = this.frcMetadata.getBackoffMetadata().getNumFailedFetches() + 1;
        this.frcMetadata.setBackoffMetadata(numFailedFetches, new Date(date.getTime() + getRandomizedBackoffDurationInMillis(numFailedFetches)));
    }

    private void updateLastFetchStatusAndTime(Task<FetchResponse> task, Date date) {
        if (task.isSuccessful()) {
            this.frcMetadata.updateLastFetchAsSuccessfulAt(date);
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            return;
        }
        if (exception instanceof FirebaseRemoteConfigFetchThrottledException) {
            this.frcMetadata.updateLastFetchAsThrottled();
        } else {
            this.frcMetadata.updateLastFetchAsFailed();
        }
    }

    public Task<FetchResponse> fetch() {
        return fetch(this.frcMetadata.getMinimumFetchIntervalInSeconds());
    }

    public AnalyticsConnector getAnalyticsConnector() {
        return this.analyticsConnector;
    }

    public Task<FetchResponse> fetch(long j9) {
        if (this.frcMetadata.isDeveloperModeEnabled()) {
            j9 = 0;
        }
        return this.fetchedConfigsCache.get().continueWithTask(this.executor, ConfigFetchHandler$$Lambda$1.lambdaFactory$(this, j9));
    }
}
