package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME;
    static final String GENERATOR;
    static final int GENERATOR_TYPE = 3;
    static final int REPORT_ANDROID_PLATFORM = 4;
    static final int SESSION_ANDROID_PLATFORM = 3;
    static final String SIGNAL_DEFAULT = "0";
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final SettingsProvider settingsProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    static {
        HashMap map = new HashMap();
        ARCHITECTURES_BY_NAME = map;
        map.put("armeabi", 5);
        map.put("armeabi-v7a", 6);
        map.put("arm64-v8a", 9);
        map.put("x86", 0);
        map.put("x86_64", 1);
        GENERATOR = String.format(Locale.US, "Crashlytics Android SDK/%s", "18.3.7");
    }

    public CrashlyticsReportDataCapture(Context context, IdManager idManager, AppData appData, StackTraceTrimmingStrategy stackTraceTrimmingStrategy, SettingsProvider settingsProvider) {
        this.context = context;
        this.idManager = idManager;
        this.appData = appData;
        this.stackTraceTrimmingStrategy = stackTraceTrimmingStrategy;
        this.settingsProvider = settingsProvider;
    }

    private CrashlyticsReport.ApplicationExitInfo addBuildIdInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        ImmutableList<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> immutableListFrom;
        if (!this.settingsProvider.getSettingsSync().featureFlagData.collectBuildIds || this.appData.buildIdInfoList.size() <= 0) {
            immutableListFrom = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (BuildIdInfo buildIdInfo : this.appData.buildIdInfoList) {
                arrayList.add(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.builder().setLibraryName(buildIdInfo.getLibraryName()).setArch(buildIdInfo.getArch()).setBuildId(buildIdInfo.getBuildId()).build());
            }
            immutableListFrom = ImmutableList.from(arrayList);
        }
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(applicationExitInfo.getImportance()).setProcessName(applicationExitInfo.getProcessName()).setReasonCode(applicationExitInfo.getReasonCode()).setTimestamp(applicationExitInfo.getTimestamp()).setPid(applicationExitInfo.getPid()).setPss(applicationExitInfo.getPss()).setRss(applicationExitInfo.getRss()).setTraceFile(applicationExitInfo.getTraceFile()).setBuildIdMappingForArch(immutableListFrom).build();
    }

    private CrashlyticsReport.Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion("18.3.7").setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private static int getDeviceArchitecture() {
        Integer num;
        String str = Build.CPU_ABI;
        if (TextUtils.isEmpty(str) || (num = ARCHITECTURES_BY_NAME.get(str.toLowerCase(Locale.US))) == null) {
            return 7;
        }
        return num.intValue();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.BinaryImage populateBinaryImageData() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0L).setSize(0L).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> populateBinaryImagesList() {
        return ImmutableList.from(populateBinaryImageData());
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i9, TrimmedThrowableData trimmedThrowableData, Thread thread, int i10, int i11, boolean z8) {
        Boolean boolValueOf;
        ActivityManager.RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(this.appData.packageName, this.context);
        if (appProcessInfo != null) {
            boolValueOf = Boolean.valueOf(appProcessInfo.importance != 100);
        } else {
            boolValueOf = null;
        }
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(boolValueOf).setUiOrientation(i9).setExecution(populateExecutionData(trimmedThrowableData, thread, i10, i11, z8)).build();
    }

    private CrashlyticsReport.Session.Event.Device populateEventDeviceData(int i9) {
        BatteryState batteryState = BatteryState.get(this.context);
        Float batteryLevel = batteryState.getBatteryLevel();
        Double dValueOf = batteryLevel != null ? Double.valueOf(batteryLevel.doubleValue()) : null;
        int batteryVelocity = batteryState.getBatteryVelocity();
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(this.context);
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(dValueOf).setBatteryVelocity(batteryVelocity).setProximityOn(proximitySensorEnabled).setOrientation(i9).setRamUsed(CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(this.context)).setDiskUsed(CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath())).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i9, int i10) {
        return populateExceptionData(trimmedThrowableData, i9, i10, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(TrimmedThrowableData trimmedThrowableData, Thread thread, int i9, int i10, boolean z8) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(populateThreadsList(trimmedThrowableData, thread, i9, z8)).setException(populateExceptionData(trimmedThrowableData, i9, i10)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame populateFrameData(StackTraceElement stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder builder) {
        long lineNumber = 0;
        long jMax = stackTraceElement.isNativeMethod() ? Math.max(stackTraceElement.getLineNumber(), 0L) : 0L;
        String str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            lineNumber = stackTraceElement.getLineNumber();
        }
        return builder.setPc(jMax).setSymbol(str).setFile(fileName).setOffset(lineNumber).build();
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> populateFramesList(StackTraceElement[] stackTraceElementArr, int i9) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            arrayList.add(populateFrameData(stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(i9)));
        }
        return ImmutableList.from(arrayList);
    }

    private CrashlyticsReport.Session.Application populateSessionApplicationData() {
        return CrashlyticsReport.Session.Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).setDevelopmentPlatform(this.appData.developmentPlatformProvider.getDevelopmentPlatform()).setDevelopmentPlatformVersion(this.appData.developmentPlatformProvider.getDevelopmentPlatformVersion()).build();
    }

    private CrashlyticsReport.Session populateSessionData(String str, long j9) {
        return CrashlyticsReport.Session.builder().setStartedAt(j9).setIdentifier(str).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private CrashlyticsReport.Session.Device populateSessionDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int deviceArchitecture = getDeviceArchitecture();
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRamInBytes = CommonUtils.getTotalRamInBytes();
        long blockCount = statFs.getBlockCount() * statFs.getBlockSize();
        boolean zIsEmulator = CommonUtils.isEmulator();
        int deviceState = CommonUtils.getDeviceState();
        return CrashlyticsReport.Session.Device.builder().setArch(deviceArchitecture).setModel(Build.MODEL).setCores(iAvailableProcessors).setRam(totalRamInBytes).setDiskSpace(blockCount).setSimulator(zIsEmulator).setState(deviceState).setManufacturer(Build.MANUFACTURER).setModelClass(Build.PRODUCT).build();
    }

    private CrashlyticsReport.Session.OperatingSystem populateSessionOperatingSystemData() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Signal populateSignalData() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName(SIGNAL_DEFAULT).setCode(SIGNAL_DEFAULT).setAddress(0L).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr) {
        return populateThreadData(thread, stackTraceElementArr, 0);
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread> populateThreadsList(TrimmedThrowableData trimmedThrowableData, Thread thread, int i9, boolean z8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(populateThreadData(thread, trimmedThrowableData.stacktrace, i9));
        if (z8) {
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if (!key.equals(thread)) {
                    arrayList.add(populateThreadData(key, this.stackTraceTrimmingStrategy.getTrimmedStackTrace(entry.getValue())));
                }
            }
        }
        return ImmutableList.from(arrayList);
    }

    public CrashlyticsReport.Session.Event captureAnrEventData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        int i9 = this.context.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType("anr").setTimestamp(applicationExitInfo.getTimestamp()).setApp(populateEventApplicationData(i9, addBuildIdInfo(applicationExitInfo))).setDevice(populateEventDeviceData(i9)).build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable th, Thread thread, String str, long j9, int i9, int i10, boolean z8) {
        int i11 = this.context.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType(str).setTimestamp(j9).setApp(populateEventApplicationData(i11, new TrimmedThrowableData(th, this.stackTraceTrimmingStrategy), thread, i9, i10, z8)).setDevice(populateEventDeviceData(i11)).build();
    }

    public CrashlyticsReport captureReportData(String str, long j9) {
        return buildReportData().setSession(populateSessionData(str, j9)).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i9, int i10, int i11) {
        String str = trimmedThrowableData.className;
        String str2 = trimmedThrowableData.localizedMessage;
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        int i12 = 0;
        if (stackTraceElementArr == null) {
            stackTraceElementArr = new StackTraceElement[0];
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (i11 >= i10) {
            TrimmedThrowableData trimmedThrowableData3 = trimmedThrowableData2;
            while (trimmedThrowableData3 != null) {
                trimmedThrowableData3 = trimmedThrowableData3.cause;
                i12++;
            }
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder overflowCount = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(str).setReason(str2).setFrames(ImmutableList.from(populateFramesList(stackTraceElementArr, i9))).setOverflowCount(i12);
        if (trimmedThrowableData2 != null && i12 == 0) {
            overflowCount.setCausedBy(populateExceptionData(trimmedThrowableData2, i9, i10, i11 + 1));
        }
        return overflowCount.build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr, int i9) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(thread.getName()).setImportance(i9).setFrames(ImmutableList.from(populateFramesList(stackTraceElementArr, i9))).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setAppExitInfo(applicationExitInfo).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i9, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(Boolean.valueOf(applicationExitInfo.getImportance() != 100)).setUiOrientation(i9).setExecution(populateExecutionData(applicationExitInfo)).build();
    }
}
