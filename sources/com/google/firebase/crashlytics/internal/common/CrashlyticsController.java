package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler;
import com.google.firebase.crashlytics.internal.metadata.LogFileManager;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.Settings;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
class CrashlyticsController {
    static final FilenameFilter APP_EXCEPTION_MARKER_FILTER = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.common.b
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return CrashlyticsController.lambda$static$0(file, str);
        }
    };
    static final String APP_EXCEPTION_MARKER_PREFIX = ".ae";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String META_INF_FOLDER = "META-INF/";
    static final String NATIVE_SESSION_DIR = "native-sessions";
    private static final String VERSION_CONTROL_INFO_FILE = "version-control-info.textproto";
    private static final String VERSION_CONTROL_INFO_KEY = "com.crashlytics.version-control-info";
    private final AnalyticsEventLogger analyticsEventLogger;
    private final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private final Context context;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsFileMarker crashMarker;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final FileStore fileStore;
    private final IdManager idManager;
    private final LogFileManager logFileManager;
    private final CrashlyticsNativeComponent nativeComponent;
    private final SessionReportingCoordinator reportingCoordinator;
    private final UserMetadata userMetadata;
    private SettingsProvider settingsProvider = null;
    final TaskCompletionSource<Boolean> unsentReportsAvailable = new TaskCompletionSource<>();
    final TaskCompletionSource<Boolean> reportActionProvided = new TaskCompletionSource<>();
    final TaskCompletionSource<Void> unsentReportsHandled = new TaskCompletionSource<>();
    final AtomicBoolean checkForUnsentReportsCalled = new AtomicBoolean(false);

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$4 */
    public class C40764 implements SuccessContinuation<Boolean, Void> {
        final /* synthetic */ Task val$settingsDataTask;

        public C40764(Task task) {
            this.val$settingsDataTask = task;
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        public Task<Void> then(final Boolean bool) {
            return CrashlyticsController.this.backgroundWorker.submitTask(new Callable<Task<Void>>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.4.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Task<Void> call() {
                    if (bool.booleanValue()) {
                        Logger.getLogger().m17767d("Sending cached crash reports...");
                        CrashlyticsController.this.dataCollectionArbiter.grantDataCollectionPermission(bool.booleanValue());
                        final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                        return C40764.this.val$settingsDataTask.onSuccessTask(executor, new SuccessContinuation<Settings, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.4.1.1
                            @Override // com.google.android.gms.tasks.SuccessContinuation
                            public Task<Void> then(Settings settings) {
                                if (settings == null) {
                                    Logger.getLogger().m17775w("Received null app settings at app startup. Cannot send cached reports");
                                    return Tasks.forResult(null);
                                }
                                CrashlyticsController.this.logAnalyticsAppExceptionEvents();
                                CrashlyticsController.this.reportingCoordinator.sendReports(executor);
                                CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                                return Tasks.forResult(null);
                            }
                        });
                    }
                    Logger.getLogger().m17773v("Deleting cached crash reports...");
                    CrashlyticsController.deleteFiles(CrashlyticsController.this.listAppExceptionMarkerFiles());
                    CrashlyticsController.this.reportingCoordinator.removeAllReports();
                    CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                    return Tasks.forResult(null);
                }
            });
        }
    }

    public CrashlyticsController(Context context, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, IdManager idManager, DataCollectionArbiter dataCollectionArbiter, FileStore fileStore, CrashlyticsFileMarker crashlyticsFileMarker, AppData appData, UserMetadata userMetadata, LogFileManager logFileManager, SessionReportingCoordinator sessionReportingCoordinator, CrashlyticsNativeComponent crashlyticsNativeComponent, AnalyticsEventLogger analyticsEventLogger) {
        this.context = context;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.idManager = idManager;
        this.dataCollectionArbiter = dataCollectionArbiter;
        this.fileStore = fileStore;
        this.crashMarker = crashlyticsFileMarker;
        this.appData = appData;
        this.userMetadata = userMetadata;
        this.logFileManager = logFileManager;
        this.nativeComponent = crashlyticsNativeComponent;
        this.analyticsEventLogger = analyticsEventLogger;
        this.reportingCoordinator = sessionReportingCoordinator;
    }

    private static StaticSessionData.AppData createAppData(IdManager idManager, AppData appData) {
        return StaticSessionData.AppData.create(idManager.getAppIdentifier(), appData.versionCode, appData.versionName, idManager.getCrashlyticsInstallId(), DeliveryMechanism.determineFrom(appData.installerPackageName).getId(), appData.developmentPlatformProvider);
    }

    private static StaticSessionData.DeviceData createDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return StaticSessionData.DeviceData.create(CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.getTotalRamInBytes(), statFs.getBlockCount() * statFs.getBlockSize(), CommonUtils.isEmulator(), CommonUtils.getDeviceState(), Build.MANUFACTURER, Build.PRODUCT);
    }

    private static StaticSessionData.OsData createOsData() {
        return StaticSessionData.OsData.create(Build.VERSION.RELEASE, Build.VERSION.CODENAME, CommonUtils.isRooted());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteFiles(List<File> list) {
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOpenSession(String str) {
        long currentTimestampSeconds = getCurrentTimestampSeconds();
        Logger.getLogger().m17767d("Opening a new session with ID " + str);
        this.nativeComponent.prepareNativeSession(str, String.format(Locale.US, GENERATOR_FORMAT, CrashlyticsCore.getVersion()), currentTimestampSeconds, StaticSessionData.create(createAppData(this.idManager, this.appData), createOsData(), createDeviceData()));
        this.logFileManager.setCurrentSession(str);
        this.reportingCoordinator.onBeginSession(str, currentTimestampSeconds);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWriteAppExceptionMarker(long j9) throws IOException {
        try {
            if (this.fileStore.getCommonFile(APP_EXCEPTION_MARKER_PREFIX + j9).createNewFile()) {
            } else {
                throw new IOException("Create new file failed.");
            }
        } catch (IOException e9) {
            Logger.getLogger().m17776w("Could not create app exception marker file.", e9);
        }
    }

    private void finalizePreviousNativeSession(String str) throws IOException {
        Logger.getLogger().m17773v("Finalizing native report for session " + str);
        NativeSessionFileProvider sessionFileProvider = this.nativeComponent.getSessionFileProvider(str);
        File minidumpFile = sessionFileProvider.getMinidumpFile();
        CrashlyticsReport.ApplicationExitInfo applicationExitInto = sessionFileProvider.getApplicationExitInto();
        if (nativeCoreAbsent(str, minidumpFile, applicationExitInto)) {
            Logger.getLogger().m17775w("No native core present");
            return;
        }
        long jLastModified = minidumpFile.lastModified();
        LogFileManager logFileManager = new LogFileManager(this.fileStore, str);
        File nativeSessionDir = this.fileStore.getNativeSessionDir(str);
        if (!nativeSessionDir.isDirectory()) {
            Logger.getLogger().m17775w("Couldn't create directory to store native session files, aborting.");
            return;
        }
        doWriteAppExceptionMarker(jLastModified);
        List<NativeSessionFile> nativeSessionFiles = getNativeSessionFiles(sessionFileProvider, str, this.fileStore, logFileManager.getBytesForLog());
        NativeSessionFileGzipper.processNativeSessions(nativeSessionDir, nativeSessionFiles);
        Logger.getLogger().m17767d("CrashlyticsController#finalizePreviousNativeSession");
        this.reportingCoordinator.finalizeSessionWithNativeEvent(str, nativeSessionFiles, applicationExitInto);
        logFileManager.clearLog();
    }

    private static boolean firebaseCrashExists() throws ClassNotFoundException {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentSessionId() {
        SortedSet<String> sortedSetListSortedOpenSessionIds = this.reportingCoordinator.listSortedOpenSessionIds();
        if (sortedSetListSortedOpenSessionIds.isEmpty()) {
            return null;
        }
        return sortedSetListSortedOpenSessionIds.first();
    }

    private static long getCurrentTimestampSeconds() {
        return getTimestampSeconds(System.currentTimeMillis());
    }

    public static List<NativeSessionFile> getNativeSessionFiles(NativeSessionFileProvider nativeSessionFileProvider, String str, FileStore fileStore, byte[] bArr) {
        File sessionFile = fileStore.getSessionFile(str, UserMetadata.USERDATA_FILENAME);
        File sessionFile2 = fileStore.getSessionFile(str, UserMetadata.KEYDATA_FILENAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BytesBackedNativeSessionFile("logs_file", "logs", bArr));
        arrayList.add(new FileBackedNativeSessionFile("crash_meta_file", TtmlNode.TAG_METADATA, nativeSessionFileProvider.getMetadataFile()));
        arrayList.add(new FileBackedNativeSessionFile("session_meta_file", "session", nativeSessionFileProvider.getSessionFile()));
        arrayList.add(new FileBackedNativeSessionFile("app_meta_file", "app", nativeSessionFileProvider.getAppFile()));
        arrayList.add(new FileBackedNativeSessionFile("device_meta_file", "device", nativeSessionFileProvider.getDeviceFile()));
        arrayList.add(new FileBackedNativeSessionFile("os_meta_file", "os", nativeSessionFileProvider.getOsFile()));
        arrayList.add(nativeCoreFile(nativeSessionFileProvider));
        arrayList.add(new FileBackedNativeSessionFile("user_meta_file", "user", sessionFile));
        arrayList.add(new FileBackedNativeSessionFile("keys_file", UserMetadata.KEYDATA_FILENAME, sessionFile2));
        return arrayList;
    }

    private InputStream getResourceAsStream(String str) {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            Logger.getLogger().m17775w("Couldn't get Class Loader");
            return null;
        }
        InputStream resourceAsStream = classLoader.getResourceAsStream(str);
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        Logger.getLogger().m17771i("No version control information found");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getTimestampSeconds(long j9) {
        return j9 / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(File file, String str) {
        return str.startsWith(APP_EXCEPTION_MARKER_PREFIX);
    }

    private Task<Void> logAnalyticsAppExceptionEvent(final long j9) {
        if (firebaseCrashExists()) {
            Logger.getLogger().m17775w("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
            return Tasks.forResult(null);
        }
        Logger.getLogger().m17767d("Logging app exception event to Firebase Analytics");
        return Tasks.call(new ScheduledThreadPoolExecutor(1), new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.8
            @Override // java.util.concurrent.Callable
            public Void call() {
                Bundle bundle = new Bundle();
                bundle.putInt("fatal", 1);
                bundle.putLong("timestamp", j9);
                CrashlyticsController.this.analyticsEventLogger.logEvent("_ae", bundle);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> logAnalyticsAppExceptionEvents() {
        ArrayList arrayList = new ArrayList();
        for (File file : listAppExceptionMarkerFiles()) {
            try {
                arrayList.add(logAnalyticsAppExceptionEvent(Long.parseLong(file.getName().substring(3))));
            } catch (NumberFormatException unused) {
                Logger.getLogger().m17775w("Could not parse app exception timestamp from file " + file.getName());
            }
            file.delete();
        }
        return Tasks.whenAll(arrayList);
    }

    private static boolean nativeCoreAbsent(String str, File file, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        if (file == null || !file.exists()) {
            Logger.getLogger().m17775w("No minidump data found for session " + str);
        }
        if (applicationExitInfo == null) {
            Logger.getLogger().m17771i("No Tombstones data found for session " + str);
        }
        return (file == null || !file.exists()) && applicationExitInfo == null;
    }

    private static NativeSessionFile nativeCoreFile(NativeSessionFileProvider nativeSessionFileProvider) {
        File minidumpFile = nativeSessionFileProvider.getMinidumpFile();
        return (minidumpFile == null || !minidumpFile.exists()) ? new BytesBackedNativeSessionFile("minidump_file", "minidump", new byte[]{0}) : new FileBackedNativeSessionFile("minidump_file", "minidump", minidumpFile);
    }

    private static byte[] readResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i9);
        }
    }

    private Task<Boolean> waitForReportAction() {
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
            Logger.getLogger().m17767d("Automatic data collection is enabled. Allowing upload.");
            this.unsentReportsAvailable.trySetResult(Boolean.FALSE);
            return Tasks.forResult(Boolean.TRUE);
        }
        Logger.getLogger().m17767d("Automatic data collection is disabled.");
        Logger.getLogger().m17773v("Notifying that unsent reports are available.");
        this.unsentReportsAvailable.trySetResult(Boolean.TRUE);
        Task<TContinuationResult> taskOnSuccessTask = this.dataCollectionArbiter.waitForAutomaticDataCollectionEnabled().onSuccessTask(new SuccessContinuation<Void, Boolean>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.3
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public Task<Boolean> then(Void r12) {
                return Tasks.forResult(Boolean.TRUE);
            }
        });
        Logger.getLogger().m17767d("Waiting for send/deleteUnsentReports to be called.");
        return Utils.race(taskOnSuccessTask, this.reportActionProvided.getTask());
    }

    private void writeApplicationExitInfoEventIfRelevant(String str) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 < 30) {
            Logger.getLogger().m17773v("ANR feature enabled, but device is API " + i9);
            return;
        }
        List<ApplicationExitInfo> historicalProcessExitReasons = ((ActivityManager) this.context.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);
        if (historicalProcessExitReasons.size() != 0) {
            this.reportingCoordinator.persistRelevantAppExitInfoEvent(str, historicalProcessExitReasons, new LogFileManager(this.fileStore, str), UserMetadata.loadFromExistingSession(str, this.fileStore, this.backgroundWorker));
        } else {
            Logger.getLogger().m17773v("No ApplicationExitInfo available. Session: " + str);
        }
    }

    public Task<Boolean> checkForUnsentReports() {
        if (this.checkForUnsentReportsCalled.compareAndSet(false, true)) {
            return this.unsentReportsAvailable.getTask();
        }
        Logger.getLogger().m17775w("checkForUnsentReports should only be called once per execution.");
        return Tasks.forResult(Boolean.FALSE);
    }

    public Task<Void> deleteUnsentReports() {
        this.reportActionProvided.trySetResult(Boolean.FALSE);
        return this.unsentReportsHandled.getTask();
    }

    public boolean didCrashOnPreviousExecution() {
        if (!this.crashMarker.isPresent()) {
            String currentSessionId = getCurrentSessionId();
            return currentSessionId != null && this.nativeComponent.hasCrashDataForSession(currentSessionId);
        }
        Logger.getLogger().m17773v("Found previous crash marker.");
        this.crashMarker.remove();
        return true;
    }

    public void doCloseSessions(SettingsProvider settingsProvider) throws IOException {
        doCloseSessions(false, settingsProvider);
    }

    public void enableExceptionHandling(String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
        openSession(str);
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.1
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler.CrashListener
            public void onUncaughtException(SettingsProvider settingsProvider2, Thread thread, Throwable th) {
                CrashlyticsController.this.handleUncaughtException(settingsProvider2, thread, th);
            }
        }, settingsProvider, uncaughtExceptionHandler, this.nativeComponent);
        this.crashHandler = crashlyticsUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(crashlyticsUncaughtExceptionHandler);
    }

    public boolean finalizeSessions(SettingsProvider settingsProvider) {
        this.backgroundWorker.checkRunningOnThread();
        if (isHandlingException()) {
            Logger.getLogger().m17775w("Skipping session finalization because a crash has already occurred.");
            return false;
        }
        Logger.getLogger().m17773v("Finalizing previously open sessions.");
        try {
            doCloseSessions(true, settingsProvider);
            Logger.getLogger().m17773v("Closed all previously open sessions.");
            return true;
        } catch (Exception e9) {
            Logger.getLogger().m17770e("Unable to finalize previously open sessions.", e9);
            return false;
        }
    }

    public UserMetadata getUserMetadata() {
        return this.userMetadata;
    }

    public String getVersionControlInfo() {
        InputStream resourceAsStream = getResourceAsStream("META-INF/version-control-info.textproto");
        if (resourceAsStream == null) {
            return null;
        }
        Logger.getLogger().m17767d("Read version control info");
        return Base64.encodeToString(readResource(resourceAsStream), 0);
    }

    public void handleUncaughtException(SettingsProvider settingsProvider, Thread thread, Throwable th) {
        handleUncaughtException(settingsProvider, thread, th, false);
    }

    public boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = this.crashHandler;
        return crashlyticsUncaughtExceptionHandler != null && crashlyticsUncaughtExceptionHandler.isHandlingException();
    }

    public List<File> listAppExceptionMarkerFiles() {
        return this.fileStore.getCommonFiles(APP_EXCEPTION_MARKER_FILTER);
    }

    public void logFatalException(Thread thread, Throwable th) {
        SettingsProvider settingsProvider = this.settingsProvider;
        if (settingsProvider == null) {
            Logger.getLogger().m17775w("settingsProvider not set");
        } else {
            handleUncaughtException(settingsProvider, thread, th, true);
        }
    }

    public void openSession(final String str) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.7
            @Override // java.util.concurrent.Callable
            public Void call() {
                CrashlyticsController.this.doOpenSession(str);
                return null;
            }
        });
    }

    public void saveVersionControlInfo() {
        try {
            String versionControlInfo = getVersionControlInfo();
            if (versionControlInfo != null) {
                setInternalKey(VERSION_CONTROL_INFO_KEY, versionControlInfo);
                Logger.getLogger().m17771i("Saved version control info");
            }
        } catch (IOException e9) {
            Logger.getLogger().m17776w("Unable to save version control info", e9);
        }
    }

    public Task<Void> sendUnsentReports() {
        this.reportActionProvided.trySetResult(Boolean.TRUE);
        return this.unsentReportsHandled.getTask();
    }

    public void setCustomKey(String str, String str2) {
        try {
            this.userMetadata.setCustomKey(str, str2);
        } catch (IllegalArgumentException e9) {
            Context context = this.context;
            if (context != null && CommonUtils.isAppDebuggable(context)) {
                throw e9;
            }
            Logger.getLogger().m17769e("Attempting to set custom attribute with null key, ignoring.");
        }
    }

    public void setCustomKeys(Map<String, String> map) {
        this.userMetadata.setCustomKeys(map);
    }

    public void setInternalKey(String str, String str2) {
        try {
            this.userMetadata.setInternalKey(str, str2);
        } catch (IllegalArgumentException e9) {
            Context context = this.context;
            if (context != null && CommonUtils.isAppDebuggable(context)) {
                throw e9;
            }
            Logger.getLogger().m17769e("Attempting to set custom attribute with null key, ignoring.");
        }
    }

    public void setUserId(String str) {
        this.userMetadata.setUserId(str);
    }

    @SuppressLint({"TaskMainThread"})
    public Task<Void> submitAllReports(Task<Settings> task) {
        if (this.reportingCoordinator.hasReportsToSend()) {
            Logger.getLogger().m17773v("Crash reports are available to be sent.");
            return waitForReportAction().onSuccessTask(new C40764(task));
        }
        Logger.getLogger().m17773v("No crash reports are available to be sent.");
        this.unsentReportsAvailable.trySetResult(Boolean.FALSE);
        return Tasks.forResult(null);
    }

    public void writeNonFatalException(final Thread thread, final Throwable th) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.backgroundWorker.submit(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.6
            @Override // java.lang.Runnable
            public void run() {
                if (CrashlyticsController.this.isHandlingException()) {
                    return;
                }
                long timestampSeconds = CrashlyticsController.getTimestampSeconds(jCurrentTimeMillis);
                String currentSessionId = CrashlyticsController.this.getCurrentSessionId();
                if (currentSessionId == null) {
                    Logger.getLogger().m17775w("Tried to write a non-fatal exception while no session was open.");
                } else {
                    CrashlyticsController.this.reportingCoordinator.persistNonFatalEvent(th, thread, currentSessionId, timestampSeconds);
                }
            }
        });
    }

    public void writeToLog(final long j9, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.logFileManager.writeToLog(j9, str);
                return null;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void doCloseSessions(boolean z8, SettingsProvider settingsProvider) throws IOException {
        ArrayList arrayList = new ArrayList(this.reportingCoordinator.listSortedOpenSessionIds());
        if (arrayList.size() <= z8) {
            Logger.getLogger().m17773v("No open sessions to be closed.");
            return;
        }
        String str = (String) arrayList.get(z8 ? 1 : 0);
        if (settingsProvider.getSettingsSync().featureFlagData.collectAnrs) {
            writeApplicationExitInfoEventIfRelevant(str);
        } else {
            Logger.getLogger().m17773v("ANR feature disabled.");
        }
        if (this.nativeComponent.hasCrashDataForSession(str)) {
            finalizePreviousNativeSession(str);
        }
        this.reportingCoordinator.finalizeSessions(getCurrentTimestampSeconds(), z8 != 0 ? (String) arrayList.get(0) : null);
    }

    public synchronized void handleUncaughtException(final SettingsProvider settingsProvider, final Thread thread, final Throwable th, final boolean z8) {
        Logger.getLogger().m17767d("Handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            Utils.awaitEvenIfOnMainThread(this.backgroundWorker.submitTask(new Callable<Task<Void>>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Task<Void> call() throws IOException {
                    long timestampSeconds = CrashlyticsController.getTimestampSeconds(jCurrentTimeMillis);
                    final String currentSessionId = CrashlyticsController.this.getCurrentSessionId();
                    if (currentSessionId == null) {
                        Logger.getLogger().m17769e("Tried to write a fatal exception while no session was open.");
                        return Tasks.forResult(null);
                    }
                    CrashlyticsController.this.crashMarker.create();
                    CrashlyticsController.this.reportingCoordinator.persistFatalEvent(th, thread, currentSessionId, timestampSeconds);
                    CrashlyticsController.this.doWriteAppExceptionMarker(jCurrentTimeMillis);
                    CrashlyticsController.this.doCloseSessions(settingsProvider);
                    CrashlyticsController.this.doOpenSession(new CLSUUID(CrashlyticsController.this.idManager).toString());
                    if (!CrashlyticsController.this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
                        return Tasks.forResult(null);
                    }
                    final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                    return settingsProvider.getSettingsAsync().onSuccessTask(executor, new SuccessContinuation<Settings, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.2.1
                        @Override // com.google.android.gms.tasks.SuccessContinuation
                        public Task<Void> then(Settings settings) {
                            if (settings == null) {
                                Logger.getLogger().m17775w("Received null app settings, cannot send reports at crash time.");
                                return Tasks.forResult(null);
                            }
                            Task[] taskArr = new Task[2];
                            taskArr[0] = CrashlyticsController.this.logAnalyticsAppExceptionEvents();
                            taskArr[1] = CrashlyticsController.this.reportingCoordinator.sendReports(executor, z8 ? currentSessionId : null);
                            return Tasks.whenAll((Task<?>[]) taskArr);
                        }
                    });
                }
            }));
        } catch (TimeoutException unused) {
            Logger.getLogger().m17769e("Cannot send reports. Timed out while fetching settings.");
        } catch (Exception e9) {
            Logger.getLogger().m17770e("Error handling uncaught exception", e9);
        }
    }
}
