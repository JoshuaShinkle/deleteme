package com.google.firebase.crashlytics.internal.model;

import com.google.auto.value.AutoValue;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;

@AutoValue
/* loaded from: classes2.dex */
public abstract class StaticSessionData {

    @AutoValue
    public static abstract class AppData {
        public static AppData create(String str, String str2, String str3, String str4, int i9, DevelopmentPlatformProvider developmentPlatformProvider) {
            return new AutoValue_StaticSessionData_AppData(str, str2, str3, str4, i9, developmentPlatformProvider);
        }

        public abstract String appIdentifier();

        public abstract int deliveryMechanism();

        public abstract DevelopmentPlatformProvider developmentPlatformProvider();

        public abstract String installUuid();

        public abstract String versionCode();

        public abstract String versionName();
    }

    @AutoValue
    public static abstract class DeviceData {
        public static DeviceData create(int i9, String str, int i10, long j9, long j10, boolean z8, int i11, String str2, String str3) {
            return new AutoValue_StaticSessionData_DeviceData(i9, str, i10, j9, j10, z8, i11, str2, str3);
        }

        public abstract int arch();

        public abstract int availableProcessors();

        public abstract long diskSpace();

        public abstract boolean isEmulator();

        public abstract String manufacturer();

        public abstract String model();

        public abstract String modelClass();

        public abstract int state();

        public abstract long totalRam();
    }

    @AutoValue
    public static abstract class OsData {
        public static OsData create(String str, String str2, boolean z8) {
            return new AutoValue_StaticSessionData_OsData(str, str2, z8);
        }

        public abstract boolean isRooted();

        public abstract String osCodeName();

        public abstract String osRelease();
    }

    public static StaticSessionData create(AppData appData, OsData osData, DeviceData deviceData) {
        return new AutoValue_StaticSessionData(appData, osData, deviceData);
    }

    public abstract AppData appData();

    public abstract DeviceData deviceData();

    public abstract OsData osData();
}
