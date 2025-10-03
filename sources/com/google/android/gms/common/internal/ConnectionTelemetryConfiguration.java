package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "ConnectionTelemetryConfigurationCreator")
/* loaded from: classes2.dex */
public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<ConnectionTelemetryConfiguration> CREATOR = new zzl();

    @SafeParcelable.Field(getter = "getRootTelemetryConfiguration", m17520id = 1)
    private final RootTelemetryConfiguration zza;

    @SafeParcelable.Field(getter = "getMethodInvocationTelemetryEnabled", m17520id = 2)
    private final boolean zzb;

    @SafeParcelable.Field(getter = "getMethodTimingTelemetryEnabled", m17520id = 3)
    private final boolean zzc;

    @SafeParcelable.Field(getter = "getMethodInvocationMethodKeyAllowlist", m17520id = 4)
    private final int[] zzd;

    @SafeParcelable.Field(getter = "getMaxMethodInvocationsLogged", m17520id = 5)
    private final int zze;

    @SafeParcelable.Field(getter = "getMethodInvocationMethodKeyDisallowlist", m17520id = 6)
    private final int[] zzf;

    @SafeParcelable.Constructor
    public ConnectionTelemetryConfiguration(@SafeParcelable.Param(m17521id = 1) RootTelemetryConfiguration rootTelemetryConfiguration, @SafeParcelable.Param(m17521id = 2) boolean z8, @SafeParcelable.Param(m17521id = 3) boolean z9, @SafeParcelable.Param(m17521id = 4) int[] iArr, @SafeParcelable.Param(m17521id = 5) int i9, @SafeParcelable.Param(m17521id = 6) int[] iArr2) {
        this.zza = rootTelemetryConfiguration;
        this.zzb = z8;
        this.zzc = z9;
        this.zzd = iArr;
        this.zze = i9;
        this.zzf = iArr2;
    }

    @KeepForSdk
    public int getMaxMethodInvocationsLogged() {
        return this.zze;
    }

    @KeepForSdk
    public int[] getMethodInvocationMethodKeyAllowlist() {
        return this.zzd;
    }

    @KeepForSdk
    public int[] getMethodInvocationMethodKeyDisallowlist() {
        return this.zzf;
    }

    @KeepForSdk
    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    @KeepForSdk
    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i9, false);
        SafeParcelWriter.writeBoolean(parcel, 2, getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(parcel, 3, getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeIntArray(parcel, 4, getMethodInvocationMethodKeyAllowlist(), false);
        SafeParcelWriter.writeInt(parcel, 5, getMaxMethodInvocationsLogged());
        SafeParcelWriter.writeIntArray(parcel, 6, getMethodInvocationMethodKeyDisallowlist(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final RootTelemetryConfiguration zza() {
        return this.zza;
    }
}
