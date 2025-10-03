package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.RequestDeduplicator;
import com.google.firebase.iid.Store;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class FirebaseInstanceId {
    private static Store store;

    @VisibleForTesting
    static ScheduledExecutorService syncExecutor;
    private final FirebaseApp app;
    private final AutoInit autoInit;

    @VisibleForTesting
    final Executor fileIoExecutor;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;
    private final GmsRpc rpc;
    private boolean syncScheduledOrRunning;
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static final Pattern API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");

    public class AutoInit {
        private EventHandler<DataCollectionDefaultChange> dataCollectionDefaultChangeEventHandler;
        private Boolean fcmAutoInitEnabled;
        private boolean initialized;
        private boolean isFcmLibraryPresent;
        private final Subscriber subscriber;

        public AutoInit(Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        private boolean isFcmPresent() {
            return true;
        }

        private Boolean readEnabled() {
            ApplicationInfo applicationInfo;
            Bundle bundle;
            Context applicationContext = FirebaseInstanceId.this.app.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        public synchronized void initialize() {
            if (this.initialized) {
                return;
            }
            this.isFcmLibraryPresent = isFcmPresent();
            Boolean enabled = readEnabled();
            this.fcmAutoInitEnabled = enabled;
            if (enabled == null && this.isFcmLibraryPresent) {
                EventHandler<DataCollectionDefaultChange> eventHandler = new EventHandler(this) { // from class: com.google.firebase.iid.FirebaseInstanceId$AutoInit$$Lambda$0
                    private final FirebaseInstanceId.AutoInit arg$1;

                    {
                        this.arg$1 = this;
                    }

                    @Override // com.google.firebase.events.EventHandler
                    public final void handle(Event event) {
                        this.arg$1.lambda$initialize$0$FirebaseInstanceId$AutoInit(event);
                    }
                };
                this.dataCollectionDefaultChangeEventHandler = eventHandler;
                this.subscriber.subscribe(DataCollectionDefaultChange.class, eventHandler);
            }
            this.initialized = true;
        }

        public synchronized boolean isEnabled() {
            initialize();
            Boolean bool = this.fcmAutoInitEnabled;
            if (bool != null) {
                return bool.booleanValue();
            }
            return this.isFcmLibraryPresent && FirebaseInstanceId.this.app.isDataCollectionDefaultEnabled();
        }

        public final /* synthetic */ void lambda$initialize$0$FirebaseInstanceId$AutoInit(Event event) {
            synchronized (this) {
                if (isEnabled()) {
                    FirebaseInstanceId.this.startSyncIfNecessary();
                }
            }
        }

        public synchronized void setEnabled(boolean z8) {
            initialize();
            EventHandler<DataCollectionDefaultChange> eventHandler = this.dataCollectionDefaultChangeEventHandler;
            if (eventHandler != null) {
                this.subscriber.unsubscribe(DataCollectionDefaultChange.class, eventHandler);
                this.dataCollectionDefaultChangeEventHandler = null;
            }
            SharedPreferences.Editor editorEdit = FirebaseInstanceId.this.app.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            editorEdit.putBoolean("auto_init", z8);
            editorEdit.apply();
            if (z8) {
                FirebaseInstanceId.this.startSyncIfNecessary();
            }
            this.fcmAutoInitEnabled = Boolean.valueOf(z8);
        }
    }

    public FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, new Metadata(firebaseApp.getApplicationContext()), FirebaseIidExecutors.newCachedSingleThreadExecutor(), FirebaseIidExecutors.newCachedSingleThreadExecutor(), subscriber, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
    }

    private <T> T awaitTask(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (ExecutionException e9) {
            Throwable cause = e9.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    resetStorageAndScheduleSync();
                }
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e9);
        }
    }

    private static <T> T awaitTaskAllowOnMainThread(Task<T> task) throws InterruptedException {
        Preconditions.checkNotNull(task, "Task must not be null");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        task.addOnCompleteListener(FirebaseInstanceId$$Lambda$2.$instance, new OnCompleteListener(countDownLatch) { // from class: com.google.firebase.iid.FirebaseInstanceId$$Lambda$3
            private final CountDownLatch arg$1;

            {
                this.arg$1 = countDownLatch;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                this.arg$1.countDown();
            }
        });
        countDownLatch.await(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, TimeUnit.MILLISECONDS);
        return (T) getResultOrThrowException(task);
    }

    private static void checkRequiredFirebaseOptions(FirebaseApp firebaseApp) {
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getProjectId(), "Please set your project ID. A valid Firebase project ID is required to communicate with Firebase server APIs: It identifies your project with Google.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApplicationId(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.");
        Preconditions.checkArgument(isValidAppIdFormat(firebaseApp.getOptions().getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(isValidApiKeyFormat(firebaseApp.getOptions().getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    @VisibleForTesting
    @KeepForSdk
    public static synchronized void clearInstancesForTest() {
        ScheduledExecutorService scheduledExecutorService = syncExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        syncExecutor = null;
        store = null;
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    private static <T> T getResultOrThrowException(Task<T> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        if (task.isComplete()) {
            throw new IllegalStateException(task.getException());
        }
        throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
    }

    private String getSubtype() {
        return FirebaseApp.DEFAULT_APP_NAME.equals(this.app.getName()) ? "" : this.app.getPersistenceKey();
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseInstanceId", 3);
    }

    public static boolean isValidApiKeyFormat(String str) {
        return API_KEY_FORMAT.matcher(str).matches();
    }

    public static boolean isValidAppIdFormat(String str) {
        return str.contains(":");
    }

    private static String rationaliseScope(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSyncIfNecessary() {
        if (tokenNeedsRefresh(getTokenWithoutTriggeringSync())) {
            startSync();
        }
    }

    public String blockingGetMasterToken() {
        return getToken(Metadata.getDefaultSenderId(this.app), "*");
    }

    public void deleteInstanceId() throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        awaitTask(this.firebaseInstallations.delete());
        resetStorageAndScheduleSync();
    }

    public void deleteToken(String str, String str2) throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String strRationaliseScope = rationaliseScope(str2);
        awaitTask(this.rpc.deleteToken(getIdWithoutTriggeringSync(), str, strRationaliseScope));
        store.deleteToken(getSubtype(), str, strRationaliseScope);
    }

    public void enqueueTaskWithDelaySeconds(Runnable runnable, long j9) {
        synchronized (FirebaseInstanceId.class) {
            if (syncExecutor == null) {
                syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            syncExecutor.schedule(runnable, j9, TimeUnit.SECONDS);
        }
    }

    public void forceTokenRefresh() {
        store.deleteTokens(getSubtype());
        startSync();
    }

    public FirebaseApp getApp() {
        return this.app;
    }

    public long getCreationTime() {
        return store.getCreationTime(this.app.getPersistenceKey());
    }

    public String getId() {
        checkRequiredFirebaseOptions(this.app);
        startSyncIfNecessary();
        return getIdWithoutTriggeringSync();
    }

    public String getIdWithoutTriggeringSync() {
        try {
            store.setCreationTime(this.app.getPersistenceKey());
            return (String) awaitTaskAllowOnMainThread(this.firebaseInstallations.getId());
        } catch (InterruptedException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public Task<InstanceIdResult> getInstanceId() {
        checkRequiredFirebaseOptions(this.app);
        return getInstanceId(Metadata.getDefaultSenderId(this.app), "*");
    }

    @Deprecated
    public String getToken() {
        checkRequiredFirebaseOptions(this.app);
        Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync();
        if (tokenNeedsRefresh(tokenWithoutTriggeringSync)) {
            startSync();
        }
        return Store.Token.getTokenOrNull(tokenWithoutTriggeringSync);
    }

    public Store.Token getTokenWithoutTriggeringSync() {
        return getTokenWithoutTriggeringSync(Metadata.getDefaultSenderId(this.app), "*");
    }

    @VisibleForTesting
    @KeepForSdk
    public boolean isFcmAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    @VisibleForTesting
    public boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    public final /* synthetic */ Task lambda$getInstanceId$1$FirebaseInstanceId(String str, String str2, String str3, String str4) {
        store.saveToken(getSubtype(), str, str2, str4, this.metadata.getAppVersionCode());
        return Tasks.forResult(new InstanceIdResultImpl(str3, str4));
    }

    public final /* synthetic */ Task lambda$getInstanceId$2$FirebaseInstanceId(final String str, final String str2, final String str3) {
        return this.rpc.getToken(str, str2, str3).onSuccessTask(this.fileIoExecutor, new SuccessContinuation(this, str2, str3, str) { // from class: com.google.firebase.iid.FirebaseInstanceId$$Lambda$5
            private final FirebaseInstanceId arg$1;
            private final String arg$2;
            private final String arg$3;
            private final String arg$4;

            {
                this.arg$1 = this;
                this.arg$2 = str2;
                this.arg$3 = str3;
                this.arg$4 = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.arg$1.lambda$getInstanceId$1$FirebaseInstanceId(this.arg$2, this.arg$3, this.arg$4, (String) obj);
            }
        });
    }

    public final /* synthetic */ Task lambda$getInstanceId$3$FirebaseInstanceId(final String str, final String str2, Task task) {
        final String idWithoutTriggeringSync = getIdWithoutTriggeringSync();
        Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync(str, str2);
        return !tokenNeedsRefresh(tokenWithoutTriggeringSync) ? Tasks.forResult(new InstanceIdResultImpl(idWithoutTriggeringSync, tokenWithoutTriggeringSync.token)) : this.requestDeduplicator.getOrStartGetTokenRequest(str, str2, new RequestDeduplicator.GetTokenRequest(this, idWithoutTriggeringSync, str, str2) { // from class: com.google.firebase.iid.FirebaseInstanceId$$Lambda$4
            private final FirebaseInstanceId arg$1;
            private final String arg$2;
            private final String arg$3;
            private final String arg$4;

            {
                this.arg$1 = this;
                this.arg$2 = idWithoutTriggeringSync;
                this.arg$3 = str;
                this.arg$4 = str2;
            }

            @Override // com.google.firebase.iid.RequestDeduplicator.GetTokenRequest
            public final Task start() {
                return this.arg$1.lambda$getInstanceId$2$FirebaseInstanceId(this.arg$2, this.arg$3, this.arg$4);
            }
        });
    }

    public final /* synthetic */ void lambda$new$0$FirebaseInstanceId() {
        if (isFcmAutoInitEnabled()) {
            startSyncIfNecessary();
        }
    }

    public synchronized void resetStorageAndScheduleSync() {
        store.deleteAll();
        if (isFcmAutoInitEnabled()) {
            startSync();
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public void setFcmAutoInitEnabled(boolean z8) {
        this.autoInit.setEnabled(z8);
    }

    public synchronized void setSyncScheduledOrRunning(boolean z8) {
        this.syncScheduledOrRunning = z8;
    }

    public synchronized void startSync() {
        if (!this.syncScheduledOrRunning) {
            syncWithDelaySecondsInternal(0L);
        }
    }

    public synchronized void syncWithDelaySecondsInternal(long j9) {
        enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30L, j9 << 1), MAX_DELAY_SEC)), j9);
        this.syncScheduledOrRunning = true;
    }

    public boolean tokenNeedsRefresh(Store.Token token) {
        return token == null || token.needsRefresh(this.metadata.getAppVersionCode());
    }

    @Keep
    public static FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        checkRequiredFirebaseOptions(firebaseApp);
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    @VisibleForTesting
    public Store.Token getTokenWithoutTriggeringSync(String str, String str2) {
        return store.getToken(getSubtype(), str, str2);
    }

    private Task<InstanceIdResult> getInstanceId(final String str, String str2) {
        final String strRationaliseScope = rationaliseScope(str2);
        return Tasks.forResult(null).continueWithTask(this.fileIoExecutor, new Continuation(this, str, strRationaliseScope) { // from class: com.google.firebase.iid.FirebaseInstanceId$$Lambda$1
            private final FirebaseInstanceId arg$1;
            private final String arg$2;
            private final String arg$3;

            {
                this.arg$1 = this;
                this.arg$2 = str;
                this.arg$3 = strRationaliseScope;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.arg$1.lambda$getInstanceId$3$FirebaseInstanceId(this.arg$2, this.arg$3, task);
            }
        });
    }

    public FirebaseInstanceId(FirebaseApp firebaseApp, Metadata metadata, Executor executor, Executor executor2, Subscriber subscriber, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.syncScheduledOrRunning = false;
        if (Metadata.getDefaultSenderId(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (store == null) {
                    store = new Store(firebaseApp.getApplicationContext());
                }
            }
            this.app = firebaseApp;
            this.metadata = metadata;
            this.rpc = new GmsRpc(firebaseApp, metadata, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
            this.fileIoExecutor = executor2;
            this.autoInit = new AutoInit(subscriber);
            this.requestDeduplicator = new RequestDeduplicator(executor);
            this.firebaseInstallations = firebaseInstallationsApi;
            executor2.execute(new Runnable(this) { // from class: com.google.firebase.iid.FirebaseInstanceId$$Lambda$0
                private final FirebaseInstanceId arg$1;

                {
                    this.arg$1 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.arg$1.lambda$new$0$FirebaseInstanceId();
                }
            });
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    public String getToken(String str, String str2) throws IOException {
        checkRequiredFirebaseOptions(this.app);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) awaitTask(getInstanceId(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }
}
