package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "RootTelemetryConfigurationCreator")
/* loaded from: classes2.dex */
public class RootTelemetryConfiguration extends AbstractSafeParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = new zzai();

    @SafeParcelable.Field(getter = "getVersion", m17520id = 1)
    private final int zza;

    @SafeParcelable.Field(getter = "getMethodInvocationTelemetryEnabled", m17520id = 2)
    private final boolean zzb;

    @SafeParcelable.Field(getter = "getMethodTimingTelemetryEnabled", m17520id = 3)
    private final boolean zzc;

    @SafeParcelable.Field(getter = "getBatchPeriodMillis", m17520id = 4)
    private final int zzd;

    @SafeParcelable.Field(getter = "getMaxMethodInvocationsInBatch", m17520id = 5)
    private final int zze;

    @SafeParcelable.Constructor
    public RootTelemetryConfiguration(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) boolean z8, @SafeParcelable.Param(m17521id = 3) boolean z9, @SafeParcelable.Param(m17521id = 4) int i10, @SafeParcelable.Param(m17521id = 5) int i11) {
        this.zza = i9;
        this.zzb = z8;
        this.zzc = z9;
        this.zzd = i10;
        this.zze = i11;
    }

    @KeepForSdk
    public int getBatchPeriodMillis() {
        return this.zzd;
    }

    @KeepForSdk
    public int getMaxMethodInvocationsInBatch() {
        return this.zze;
    }

    @KeepForSdk
    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    @KeepForSdk
    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @KeepForSdk
    public int getVersion() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersion());
        SafeParcelWriter.writeBoolean(parcel, 2, getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(parcel, 3, getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeInt(parcel, 4, getBatchPeriodMillis());
        SafeParcelWriter.writeInt(parcel, 5, getMaxMethodInvocationsInBatch());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
