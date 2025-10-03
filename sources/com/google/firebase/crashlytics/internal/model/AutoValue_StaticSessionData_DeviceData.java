package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* loaded from: classes2.dex */
final class AutoValue_StaticSessionData_DeviceData extends StaticSessionData.DeviceData {
    private final int arch;
    private final int availableProcessors;
    private final long diskSpace;
    private final boolean isEmulator;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final int state;
    private final long totalRam;

    public AutoValue_StaticSessionData_DeviceData(int i9, String str, int i10, long j9, long j10, boolean z8, int i11, String str2, String str3) {
        this.arch = i9;
        if (str == null) {
            throw new NullPointerException("Null model");
        }
        this.model = str;
        this.availableProcessors = i10;
        this.totalRam = j9;
        this.diskSpace = j10;
        this.isEmulator = z8;
        this.state = i11;
        if (str2 == null) {
            throw new NullPointerException("Null manufacturer");
        }
        this.manufacturer = str2;
        if (str3 == null) {
            throw new NullPointerException("Null modelClass");
        }
        this.modelClass = str3;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int arch() {
        return this.arch;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int availableProcessors() {
        return this.availableProcessors;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public long diskSpace() {
        return this.diskSpace;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StaticSessionData.DeviceData)) {
            return false;
        }
        StaticSessionData.DeviceData deviceData = (StaticSessionData.DeviceData) obj;
        return this.arch == deviceData.arch() && this.model.equals(deviceData.model()) && this.availableProcessors == deviceData.availableProcessors() && this.totalRam == deviceData.totalRam() && this.diskSpace == deviceData.diskSpace() && this.isEmulator == deviceData.isEmulator() && this.state == deviceData.state() && this.manufacturer.equals(deviceData.manufacturer()) && this.modelClass.equals(deviceData.modelClass());
    }

    public int hashCode() {
        int iHashCode = (((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.availableProcessors) * 1000003;
        long j9 = this.totalRam;
        int i9 = (iHashCode ^ ((int) (j9 ^ (j9 >>> 32)))) * 1000003;
        long j10 = this.diskSpace;
        return ((((((((i9 ^ ((int) (j10 ^ (j10 >>> 32)))) * 1000003) ^ (this.isEmulator ? 1231 : 1237)) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003) ^ this.modelClass.hashCode();
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public boolean isEmulator() {
        return this.isEmulator;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String manufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String model() {
        return this.model;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String modelClass() {
        return this.modelClass;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int state() {
        return this.state;
    }

    public String toString() {
        return "DeviceData{arch=" + this.arch + ", model=" + this.model + ", availableProcessors=" + this.availableProcessors + ", totalRam=" + this.totalRam + ", diskSpace=" + this.diskSpace + ", isEmulator=" + this.isEmulator + ", state=" + this.state + ", manufacturer=" + this.manufacturer + ", modelClass=" + this.modelClass + "}";
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public long totalRam() {
        return this.totalRam;
    }
}
