package com.google.firebase.crashlytics.internal.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;

/* loaded from: classes2.dex */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    public static final class CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> {
        static final CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder();
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.m17815of("arch");
        private static final FieldDescriptor LIBRARYNAME_DESCRIPTOR = FieldDescriptor.m17815of("libraryName");
        private static final FieldDescriptor BUILDID_DESCRIPTOR = FieldDescriptor.m17815of("buildId");

        private CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch buildIdMappingForArch, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(ARCH_DESCRIPTOR, buildIdMappingForArch.getArch());
            objectEncoderContext.add(LIBRARYNAME_DESCRIPTOR, buildIdMappingForArch.getLibraryName());
            objectEncoderContext.add(BUILDID_DESCRIPTOR, buildIdMappingForArch.getBuildId());
        }
    }

    public static final class CrashlyticsReportApplicationExitInfoEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo> {
        static final CrashlyticsReportApplicationExitInfoEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoEncoder();
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.m17815of("pid");
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.m17815of("processName");
        private static final FieldDescriptor REASONCODE_DESCRIPTOR = FieldDescriptor.m17815of("reasonCode");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m17815of("importance");
        private static final FieldDescriptor PSS_DESCRIPTOR = FieldDescriptor.m17815of("pss");
        private static final FieldDescriptor RSS_DESCRIPTOR = FieldDescriptor.m17815of("rss");
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.m17815of("timestamp");
        private static final FieldDescriptor TRACEFILE_DESCRIPTOR = FieldDescriptor.m17815of("traceFile");
        private static final FieldDescriptor BUILDIDMAPPINGFORARCH_DESCRIPTOR = FieldDescriptor.m17815of("buildIdMappingForArch");

        private CrashlyticsReportApplicationExitInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.ApplicationExitInfo applicationExitInfo, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(PID_DESCRIPTOR, applicationExitInfo.getPid());
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, applicationExitInfo.getProcessName());
            objectEncoderContext.add(REASONCODE_DESCRIPTOR, applicationExitInfo.getReasonCode());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, applicationExitInfo.getImportance());
            objectEncoderContext.add(PSS_DESCRIPTOR, applicationExitInfo.getPss());
            objectEncoderContext.add(RSS_DESCRIPTOR, applicationExitInfo.getRss());
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, applicationExitInfo.getTimestamp());
            objectEncoderContext.add(TRACEFILE_DESCRIPTOR, applicationExitInfo.getTraceFile());
            objectEncoderContext.add(BUILDIDMAPPINGFORARCH_DESCRIPTOR, applicationExitInfo.getBuildIdMappingForArch());
        }
    }

    public static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();
        private static final FieldDescriptor KEY_DESCRIPTOR = FieldDescriptor.m17815of("key");
        private static final FieldDescriptor VALUE_DESCRIPTOR = FieldDescriptor.m17815of("value");

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.CustomAttribute customAttribute, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(KEY_DESCRIPTOR, customAttribute.getKey());
            objectEncoderContext.add(VALUE_DESCRIPTOR, customAttribute.getValue());
        }
    }

    public static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.m17815of(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        private static final FieldDescriptor GMPAPPID_DESCRIPTOR = FieldDescriptor.m17815of("gmpAppId");
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.m17815of("platform");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.m17815of("installationUuid");
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.m17815of("buildVersion");
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.m17815of("displayVersion");
        private static final FieldDescriptor SESSION_DESCRIPTOR = FieldDescriptor.m17815of("session");
        private static final FieldDescriptor NDKPAYLOAD_DESCRIPTOR = FieldDescriptor.m17815of("ndkPayload");
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.m17815of("appExitInfo");

        private CrashlyticsReportEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport crashlyticsReport, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(SDKVERSION_DESCRIPTOR, crashlyticsReport.getSdkVersion());
            objectEncoderContext.add(GMPAPPID_DESCRIPTOR, crashlyticsReport.getGmpAppId());
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, crashlyticsReport.getPlatform());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, crashlyticsReport.getInstallationUuid());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, crashlyticsReport.getBuildVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, crashlyticsReport.getDisplayVersion());
            objectEncoderContext.add(SESSION_DESCRIPTOR, crashlyticsReport.getSession());
            objectEncoderContext.add(NDKPAYLOAD_DESCRIPTOR, crashlyticsReport.getNdkPayload());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, crashlyticsReport.getAppExitInfo());
        }
    }

    public static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();
        private static final FieldDescriptor FILES_DESCRIPTOR = FieldDescriptor.m17815of("files");
        private static final FieldDescriptor ORGID_DESCRIPTOR = FieldDescriptor.m17815of("orgId");

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.FilesPayload filesPayload, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(FILES_DESCRIPTOR, filesPayload.getFiles());
            objectEncoderContext.add(ORGID_DESCRIPTOR, filesPayload.getOrgId());
        }
    }

    public static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();
        private static final FieldDescriptor FILENAME_DESCRIPTOR = FieldDescriptor.m17815of("filename");
        private static final FieldDescriptor CONTENTS_DESCRIPTOR = FieldDescriptor.m17815of("contents");

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.FilesPayload.File file, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(FILENAME_DESCRIPTOR, file.getFilename());
            objectEncoderContext.add(CONTENTS_DESCRIPTOR, file.getContents());
        }
    }

    public static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m17815of("identifier");
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.m17815of("version");
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.m17815of("displayVersion");
        private static final FieldDescriptor ORGANIZATION_DESCRIPTOR = FieldDescriptor.m17815of("organization");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.m17815of("installationUuid");
        private static final FieldDescriptor DEVELOPMENTPLATFORM_DESCRIPTOR = FieldDescriptor.m17815of("developmentPlatform");
        private static final FieldDescriptor DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = FieldDescriptor.m17815of("developmentPlatformVersion");

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Application application, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, application.getIdentifier());
            objectEncoderContext.add(VERSION_DESCRIPTOR, application.getVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, application.getDisplayVersion());
            objectEncoderContext.add(ORGANIZATION_DESCRIPTOR, application.getOrganization());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, application.getInstallationUuid());
            objectEncoderContext.add(DEVELOPMENTPLATFORM_DESCRIPTOR, application.getDevelopmentPlatform());
            objectEncoderContext.add(DEVELOPMENTPLATFORMVERSION_DESCRIPTOR, application.getDevelopmentPlatformVersion());
        }
    }

    public static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();
        private static final FieldDescriptor CLSID_DESCRIPTOR = FieldDescriptor.m17815of("clsId");

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Application.Organization organization, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(CLSID_DESCRIPTOR, organization.getClsId());
        }
    }

    public static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.m17815of("arch");
        private static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.m17815of("model");
        private static final FieldDescriptor CORES_DESCRIPTOR = FieldDescriptor.m17815of("cores");
        private static final FieldDescriptor RAM_DESCRIPTOR = FieldDescriptor.m17815of("ram");
        private static final FieldDescriptor DISKSPACE_DESCRIPTOR = FieldDescriptor.m17815of("diskSpace");
        private static final FieldDescriptor SIMULATOR_DESCRIPTOR = FieldDescriptor.m17815of("simulator");
        private static final FieldDescriptor STATE_DESCRIPTOR = FieldDescriptor.m17815of(RemoteConfigConstants.ResponseFieldKey.STATE);
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.m17815of("manufacturer");
        private static final FieldDescriptor MODELCLASS_DESCRIPTOR = FieldDescriptor.m17815of("modelClass");

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Device device, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(ARCH_DESCRIPTOR, device.getArch());
            objectEncoderContext.add(MODEL_DESCRIPTOR, device.getModel());
            objectEncoderContext.add(CORES_DESCRIPTOR, device.getCores());
            objectEncoderContext.add(RAM_DESCRIPTOR, device.getRam());
            objectEncoderContext.add(DISKSPACE_DESCRIPTOR, device.getDiskSpace());
            objectEncoderContext.add(SIMULATOR_DESCRIPTOR, device.isSimulator());
            objectEncoderContext.add(STATE_DESCRIPTOR, device.getState());
            objectEncoderContext.add(MANUFACTURER_DESCRIPTOR, device.getManufacturer());
            objectEncoderContext.add(MODELCLASS_DESCRIPTOR, device.getModelClass());
        }
    }

    public static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();
        private static final FieldDescriptor GENERATOR_DESCRIPTOR = FieldDescriptor.m17815of("generator");
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m17815of("identifier");
        private static final FieldDescriptor STARTEDAT_DESCRIPTOR = FieldDescriptor.m17815of("startedAt");
        private static final FieldDescriptor ENDEDAT_DESCRIPTOR = FieldDescriptor.m17815of("endedAt");
        private static final FieldDescriptor CRASHED_DESCRIPTOR = FieldDescriptor.m17815of("crashed");
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.m17815of("app");
        private static final FieldDescriptor USER_DESCRIPTOR = FieldDescriptor.m17815of("user");
        private static final FieldDescriptor OS_DESCRIPTOR = FieldDescriptor.m17815of("os");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m17815of("device");
        private static final FieldDescriptor EVENTS_DESCRIPTOR = FieldDescriptor.m17815of("events");
        private static final FieldDescriptor GENERATORTYPE_DESCRIPTOR = FieldDescriptor.m17815of("generatorType");

        private CrashlyticsReportSessionEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session session, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(GENERATOR_DESCRIPTOR, session.getGenerator());
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, session.getIdentifierUtf8Bytes());
            objectEncoderContext.add(STARTEDAT_DESCRIPTOR, session.getStartedAt());
            objectEncoderContext.add(ENDEDAT_DESCRIPTOR, session.getEndedAt());
            objectEncoderContext.add(CRASHED_DESCRIPTOR, session.isCrashed());
            objectEncoderContext.add(APP_DESCRIPTOR, session.getApp());
            objectEncoderContext.add(USER_DESCRIPTOR, session.getUser());
            objectEncoderContext.add(OS_DESCRIPTOR, session.getOs());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, session.getDevice());
            objectEncoderContext.add(EVENTS_DESCRIPTOR, session.getEvents());
            objectEncoderContext.add(GENERATORTYPE_DESCRIPTOR, session.getGeneratorType());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();
        private static final FieldDescriptor EXECUTION_DESCRIPTOR = FieldDescriptor.m17815of("execution");
        private static final FieldDescriptor CUSTOMATTRIBUTES_DESCRIPTOR = FieldDescriptor.m17815of("customAttributes");
        private static final FieldDescriptor INTERNALKEYS_DESCRIPTOR = FieldDescriptor.m17815of("internalKeys");
        private static final FieldDescriptor BACKGROUND_DESCRIPTOR = FieldDescriptor.m17815of("background");
        private static final FieldDescriptor UIORIENTATION_DESCRIPTOR = FieldDescriptor.m17815of("uiOrientation");

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application application, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(EXECUTION_DESCRIPTOR, application.getExecution());
            objectEncoderContext.add(CUSTOMATTRIBUTES_DESCRIPTOR, application.getCustomAttributes());
            objectEncoderContext.add(INTERNALKEYS_DESCRIPTOR, application.getInternalKeys());
            objectEncoderContext.add(BACKGROUND_DESCRIPTOR, application.getBackground());
            objectEncoderContext.add(UIORIENTATION_DESCRIPTOR, application.getUiOrientation());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder */
    public static final class C4112x99c932db implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        static final C4112x99c932db INSTANCE = new C4112x99c932db();
        private static final FieldDescriptor BASEADDRESS_DESCRIPTOR = FieldDescriptor.m17815of("baseAddress");
        private static final FieldDescriptor SIZE_DESCRIPTOR = FieldDescriptor.m17815of("size");
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m17815of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        private static final FieldDescriptor UUID_DESCRIPTOR = FieldDescriptor.m17815of("uuid");

        private C4112x99c932db() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(BASEADDRESS_DESCRIPTOR, binaryImage.getBaseAddress());
            objectEncoderContext.add(SIZE_DESCRIPTOR, binaryImage.getSize());
            objectEncoderContext.add(NAME_DESCRIPTOR, binaryImage.getName());
            objectEncoderContext.add(UUID_DESCRIPTOR, binaryImage.getUuidUtf8Bytes());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();
        private static final FieldDescriptor THREADS_DESCRIPTOR = FieldDescriptor.m17815of("threads");
        private static final FieldDescriptor EXCEPTION_DESCRIPTOR = FieldDescriptor.m17815of("exception");
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.m17815of("appExitInfo");
        private static final FieldDescriptor SIGNAL_DESCRIPTOR = FieldDescriptor.m17815of("signal");
        private static final FieldDescriptor BINARIES_DESCRIPTOR = FieldDescriptor.m17815of("binaries");

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(THREADS_DESCRIPTOR, execution.getThreads());
            objectEncoderContext.add(EXCEPTION_DESCRIPTOR, execution.getException());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, execution.getAppExitInfo());
            objectEncoderContext.add(SIGNAL_DESCRIPTOR, execution.getSignal());
            objectEncoderContext.add(BINARIES_DESCRIPTOR, execution.getBinaries());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder */
    public static final class C4113x55689506 implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        static final C4113x55689506 INSTANCE = new C4113x55689506();
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.m17815of("type");
        private static final FieldDescriptor REASON_DESCRIPTOR = FieldDescriptor.m17815of("reason");
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.m17815of("frames");
        private static final FieldDescriptor CAUSEDBY_DESCRIPTOR = FieldDescriptor.m17815of("causedBy");
        private static final FieldDescriptor OVERFLOWCOUNT_DESCRIPTOR = FieldDescriptor.m17815of("overflowCount");

        private C4113x55689506() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(TYPE_DESCRIPTOR, exception.getType());
            objectEncoderContext.add(REASON_DESCRIPTOR, exception.getReason());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, exception.getFrames());
            objectEncoderContext.add(CAUSEDBY_DESCRIPTOR, exception.getCausedBy());
            objectEncoderContext.add(OVERFLOWCOUNT_DESCRIPTOR, exception.getOverflowCount());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m17815of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        private static final FieldDescriptor CODE_DESCRIPTOR = FieldDescriptor.m17815of("code");
        private static final FieldDescriptor ADDRESS_DESCRIPTOR = FieldDescriptor.m17815of("address");

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(NAME_DESCRIPTOR, signal.getName());
            objectEncoderContext.add(CODE_DESCRIPTOR, signal.getCode());
            objectEncoderContext.add(ADDRESS_DESCRIPTOR, signal.getAddress());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m17815of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m17815of("importance");
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.m17815of("frames");

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(NAME_DESCRIPTOR, thread.getName());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, thread.getImportance());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, thread.getFrames());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder */
    public static final class C4114xc3999712 implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        static final C4114xc3999712 INSTANCE = new C4114xc3999712();
        private static final FieldDescriptor PC_DESCRIPTOR = FieldDescriptor.m17815of("pc");
        private static final FieldDescriptor SYMBOL_DESCRIPTOR = FieldDescriptor.m17815of("symbol");
        private static final FieldDescriptor FILE_DESCRIPTOR = FieldDescriptor.m17815of("file");
        private static final FieldDescriptor OFFSET_DESCRIPTOR = FieldDescriptor.m17815of("offset");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m17815of("importance");

        private C4114xc3999712() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(PC_DESCRIPTOR, frame.getPc());
            objectEncoderContext.add(SYMBOL_DESCRIPTOR, frame.getSymbol());
            objectEncoderContext.add(FILE_DESCRIPTOR, frame.getFile());
            objectEncoderContext.add(OFFSET_DESCRIPTOR, frame.getOffset());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, frame.getImportance());
        }
    }

    public static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();
        private static final FieldDescriptor BATTERYLEVEL_DESCRIPTOR = FieldDescriptor.m17815of("batteryLevel");
        private static final FieldDescriptor BATTERYVELOCITY_DESCRIPTOR = FieldDescriptor.m17815of("batteryVelocity");
        private static final FieldDescriptor PROXIMITYON_DESCRIPTOR = FieldDescriptor.m17815of("proximityOn");
        private static final FieldDescriptor ORIENTATION_DESCRIPTOR = FieldDescriptor.m17815of("orientation");
        private static final FieldDescriptor RAMUSED_DESCRIPTOR = FieldDescriptor.m17815of("ramUsed");
        private static final FieldDescriptor DISKUSED_DESCRIPTOR = FieldDescriptor.m17815of("diskUsed");

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Device device, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(BATTERYLEVEL_DESCRIPTOR, device.getBatteryLevel());
            objectEncoderContext.add(BATTERYVELOCITY_DESCRIPTOR, device.getBatteryVelocity());
            objectEncoderContext.add(PROXIMITYON_DESCRIPTOR, device.isProximityOn());
            objectEncoderContext.add(ORIENTATION_DESCRIPTOR, device.getOrientation());
            objectEncoderContext.add(RAMUSED_DESCRIPTOR, device.getRamUsed());
            objectEncoderContext.add(DISKUSED_DESCRIPTOR, device.getDiskUsed());
        }
    }

    public static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.m17815of("timestamp");
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.m17815of("type");
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.m17815of("app");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m17815of("device");
        private static final FieldDescriptor LOG_DESCRIPTOR = FieldDescriptor.m17815of("log");

        private CrashlyticsReportSessionEventEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event event, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, event.getTimestamp());
            objectEncoderContext.add(TYPE_DESCRIPTOR, event.getType());
            objectEncoderContext.add(APP_DESCRIPTOR, event.getApp());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, event.getDevice());
            objectEncoderContext.add(LOG_DESCRIPTOR, event.getLog());
        }
    }

    public static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();
        private static final FieldDescriptor CONTENT_DESCRIPTOR = FieldDescriptor.m17815of(FirebaseAnalytics.Param.CONTENT);

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Log log, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(CONTENT_DESCRIPTOR, log.getContent());
        }
    }

    public static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.m17815of("platform");
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.m17815of("version");
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.m17815of("buildVersion");
        private static final FieldDescriptor JAILBROKEN_DESCRIPTOR = FieldDescriptor.m17815of("jailbroken");

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, operatingSystem.getPlatform());
            objectEncoderContext.add(VERSION_DESCRIPTOR, operatingSystem.getVersion());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, operatingSystem.getBuildVersion());
            objectEncoderContext.add(JAILBROKEN_DESCRIPTOR, operatingSystem.isJailbroken());
        }
    }

    public static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m17815of("identifier");

        private CrashlyticsReportSessionUserEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.User user, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, user.getIdentifier());
        }
    }

    private AutoCrashlyticsReportEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        CrashlyticsReportEncoder crashlyticsReportEncoder = CrashlyticsReportEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.class, crashlyticsReportEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport.class, crashlyticsReportEncoder);
        CrashlyticsReportSessionEncoder crashlyticsReportSessionEncoder = CrashlyticsReportSessionEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.class, crashlyticsReportSessionEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session.class, crashlyticsReportSessionEncoder);
        CrashlyticsReportSessionApplicationEncoder crashlyticsReportSessionApplicationEncoder = CrashlyticsReportSessionApplicationEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.class, crashlyticsReportSessionApplicationEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Application.class, crashlyticsReportSessionApplicationEncoder);
        CrashlyticsReportSessionApplicationOrganizationEncoder crashlyticsReportSessionApplicationOrganizationEncoder = CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.Organization.class, crashlyticsReportSessionApplicationOrganizationEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Application_Organization.class, crashlyticsReportSessionApplicationOrganizationEncoder);
        CrashlyticsReportSessionUserEncoder crashlyticsReportSessionUserEncoder = CrashlyticsReportSessionUserEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.User.class, crashlyticsReportSessionUserEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_User.class, crashlyticsReportSessionUserEncoder);
        CrashlyticsReportSessionOperatingSystemEncoder crashlyticsReportSessionOperatingSystemEncoder = CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.OperatingSystem.class, crashlyticsReportSessionOperatingSystemEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, crashlyticsReportSessionOperatingSystemEncoder);
        CrashlyticsReportSessionDeviceEncoder crashlyticsReportSessionDeviceEncoder = CrashlyticsReportSessionDeviceEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Device.class, crashlyticsReportSessionDeviceEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Device.class, crashlyticsReportSessionDeviceEncoder);
        CrashlyticsReportSessionEventEncoder crashlyticsReportSessionEventEncoder = CrashlyticsReportSessionEventEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.class, crashlyticsReportSessionEventEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event.class, crashlyticsReportSessionEventEncoder);
        CrashlyticsReportSessionEventApplicationEncoder crashlyticsReportSessionEventApplicationEncoder = CrashlyticsReportSessionEventApplicationEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.class, crashlyticsReportSessionEventApplicationEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application.class, crashlyticsReportSessionEventApplicationEncoder);
        CrashlyticsReportSessionEventApplicationExecutionEncoder crashlyticsReportSessionEventApplicationExecutionEncoder = CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.class, crashlyticsReportSessionEventApplicationExecutionEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, crashlyticsReportSessionEventApplicationExecutionEncoder);
        CrashlyticsReportSessionEventApplicationExecutionThreadEncoder crashlyticsReportSessionEventApplicationExecutionThreadEncoder = CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        encoderConfig.registerEncoder(C4131x7e3e3ebd.class, crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        C4114xc3999712 c4114xc3999712 = C4114xc3999712.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, c4114xc3999712);
        encoderConfig.registerEncoder(C4132xce3d994b.class, c4114xc3999712);
        C4113x55689506 c4113x55689506 = C4113x55689506.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, c4113x55689506);
        encoderConfig.registerEncoder(C4129xc2f5febc.class, c4113x55689506);
        CrashlyticsReportApplicationExitInfoEncoder crashlyticsReportApplicationExitInfoEncoder = CrashlyticsReportApplicationExitInfoEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.ApplicationExitInfo.class, crashlyticsReportApplicationExitInfoEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_ApplicationExitInfo.class, crashlyticsReportApplicationExitInfoEncoder);
        CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder = CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.class, crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        encoderConfig.registerEncoder(C4117xb26d2aa8.class, crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        CrashlyticsReportSessionEventApplicationExecutionSignalEncoder crashlyticsReportSessionEventApplicationExecutionSignalEncoder = CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        encoderConfig.registerEncoder(C4130x7c929f5b.class, crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        C4112x99c932db c4112x99c932db = C4112x99c932db.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, c4112x99c932db);
        encoderConfig.registerEncoder(C4128xfe724d07.class, c4112x99c932db);
        CrashlyticsReportCustomAttributeEncoder crashlyticsReportCustomAttributeEncoder = CrashlyticsReportCustomAttributeEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.CustomAttribute.class, crashlyticsReportCustomAttributeEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_CustomAttribute.class, crashlyticsReportCustomAttributeEncoder);
        CrashlyticsReportSessionEventDeviceEncoder crashlyticsReportSessionEventDeviceEncoder = CrashlyticsReportSessionEventDeviceEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Device.class, crashlyticsReportSessionEventDeviceEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Device.class, crashlyticsReportSessionEventDeviceEncoder);
        CrashlyticsReportSessionEventLogEncoder crashlyticsReportSessionEventLogEncoder = CrashlyticsReportSessionEventLogEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Log.class, crashlyticsReportSessionEventLogEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Log.class, crashlyticsReportSessionEventLogEncoder);
        CrashlyticsReportFilesPayloadEncoder crashlyticsReportFilesPayloadEncoder = CrashlyticsReportFilesPayloadEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.class, crashlyticsReportFilesPayloadEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload.class, crashlyticsReportFilesPayloadEncoder);
        CrashlyticsReportFilesPayloadFileEncoder crashlyticsReportFilesPayloadFileEncoder = CrashlyticsReportFilesPayloadFileEncoder.INSTANCE;
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.File.class, crashlyticsReportFilesPayloadFileEncoder);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload_File.class, crashlyticsReportFilesPayloadFileEncoder);
    }
}
