package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Date;

/* loaded from: classes2.dex */
public class ConfigMetadataClient {
    private static final String BACKOFF_END_TIME_IN_MILLIS_KEY = "backoff_end_time_in_millis";
    private static final String DEVELOPER_MODE_KEY = "is_developer_mode_enabled";
    private static final String FETCH_TIMEOUT_IN_SECONDS_KEY = "fetch_timeout_in_seconds";
    private static final String LAST_FETCH_ETAG_KEY = "last_fetch_etag";
    private static final String LAST_FETCH_STATUS_KEY = "last_fetch_status";
    public static final long LAST_FETCH_TIME_IN_MILLIS_NO_FETCH_YET = -1;
    private static final String LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY = "last_fetch_time_in_millis";
    private static final String MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY = "minimum_fetch_interval_in_seconds";
    private static final long NO_BACKOFF_TIME_IN_MILLIS = -1;
    static final int NO_FAILED_FETCHES = 0;
    private static final String NUM_FAILED_FETCHES_KEY = "num_failed_fetches";
    private final SharedPreferences frcMetadata;
    static final Date LAST_FETCH_TIME_NO_FETCH_YET = new Date(-1);
    static final Date NO_BACKOFF_TIME = new Date(-1);
    private final Object frcInfoLock = new Object();
    private final Object backoffMetadataLock = new Object();

    public static class BackoffMetadata {
        private Date backoffEndTime;
        private int numFailedFetches;

        public BackoffMetadata(int i9, Date date) {
            this.numFailedFetches = i9;
            this.backoffEndTime = date;
        }

        public Date getBackoffEndTime() {
            return this.backoffEndTime;
        }

        public int getNumFailedFetches() {
            return this.numFailedFetches;
        }
    }

    public ConfigMetadataClient(SharedPreferences sharedPreferences) {
        this.frcMetadata = sharedPreferences;
    }

    public void clear() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().clear().commit();
        }
    }

    public BackoffMetadata getBackoffMetadata() {
        BackoffMetadata backoffMetadata;
        synchronized (this.backoffMetadataLock) {
            backoffMetadata = new BackoffMetadata(this.frcMetadata.getInt(NUM_FAILED_FETCHES_KEY, 0), new Date(this.frcMetadata.getLong(BACKOFF_END_TIME_IN_MILLIS_KEY, -1L)));
        }
        return backoffMetadata;
    }

    public long getFetchTimeoutInSeconds() {
        return this.frcMetadata.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60L);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfoImpl firebaseRemoteConfigInfoImplBuild;
        synchronized (this.frcInfoLock) {
            long j9 = this.frcMetadata.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1L);
            int i9 = this.frcMetadata.getInt(LAST_FETCH_STATUS_KEY, 0);
            firebaseRemoteConfigInfoImplBuild = FirebaseRemoteConfigInfoImpl.newBuilder().withLastFetchStatus(i9).withLastSuccessfulFetchTimeInMillis(j9).withConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(this.frcMetadata.getBoolean(DEVELOPER_MODE_KEY, false)).setFetchTimeoutInSeconds(this.frcMetadata.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60L)).setMinimumFetchIntervalInSeconds(this.frcMetadata.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS)).build()).build();
        }
        return firebaseRemoteConfigInfoImplBuild;
    }

    public String getLastFetchETag() {
        return this.frcMetadata.getString(LAST_FETCH_ETAG_KEY, null);
    }

    public int getLastFetchStatus() {
        return this.frcMetadata.getInt(LAST_FETCH_STATUS_KEY, 0);
    }

    public Date getLastSuccessfulFetchTime() {
        return new Date(this.frcMetadata.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1L));
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.frcMetadata.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS);
    }

    public boolean isDeveloperModeEnabled() {
        return this.frcMetadata.getBoolean(DEVELOPER_MODE_KEY, false);
    }

    public void resetBackoff() {
        setBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    public void setBackoffMetadata(int i9, Date date) {
        synchronized (this.backoffMetadataLock) {
            this.frcMetadata.edit().putInt(NUM_FAILED_FETCHES_KEY, i9).putLong(BACKOFF_END_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putBoolean(DEVELOPER_MODE_KEY, firebaseRemoteConfigSettings.isDeveloperModeEnabled()).putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).commit();
        }
    }

    public void setConfigSettingsWithoutWaitingOnDiskWrite(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putBoolean(DEVELOPER_MODE_KEY, firebaseRemoteConfigSettings.isDeveloperModeEnabled()).putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).apply();
        }
    }

    public void setLastFetchETag(String str) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putString(LAST_FETCH_ETAG_KEY, str).apply();
        }
    }

    public void updateLastFetchAsFailed() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, 1).apply();
        }
    }

    public void updateLastFetchAsSuccessfulAt(Date date) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, -1).putLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    public void updateLastFetchAsThrottled() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, 2).apply();
        }
    }
}
