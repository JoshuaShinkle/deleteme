package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@KeepForSdk
@SafeParcelable.Class(creator = "WakeLockEventCreator")
@Deprecated
/* loaded from: classes2.dex */
public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();

    @SafeParcelable.VersionField(m17523id = 1)
    final int zza;

    @SafeParcelable.Field(getter = "getTimeMillis", m17520id = 2)
    private final long zzb;

    @SafeParcelable.Field(getter = "getEventType", m17520id = 11)
    private int zzc;

    @SafeParcelable.Field(getter = "getWakeLockName", m17520id = 4)
    private final String zzd;

    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", m17520id = 10)
    private final String zze;

    @SafeParcelable.Field(getter = "getCodePackage", m17520id = 17)
    private final String zzf;

    @SafeParcelable.Field(getter = "getWakeLockType", m17520id = 5)
    private final int zzg;

    @SafeParcelable.Field(getter = "getCallingPackages", m17520id = 6)
    private final List zzh;

    @SafeParcelable.Field(getter = "getEventKey", m17520id = 12)
    private final String zzi;

    @SafeParcelable.Field(getter = "getElapsedRealtime", m17520id = 8)
    private final long zzj;

    @SafeParcelable.Field(getter = "getDeviceState", m17520id = 14)
    private int zzk;

    @SafeParcelable.Field(getter = "getHostPackage", m17520id = 13)
    private final String zzl;

    @SafeParcelable.Field(getter = "getBeginPowerPercentage", m17520id = 15)
    private final float zzm;

    @SafeParcelable.Field(getter = "getTimeout", m17520id = 16)
    private final long zzn;

    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", m17520id = 18)
    private final boolean zzo;
    private long zzp = -1;

    @SafeParcelable.Constructor
    public WakeLockEvent(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) long j9, @SafeParcelable.Param(m17521id = 11) int i10, @SafeParcelable.Param(m17521id = 4) String str, @SafeParcelable.Param(m17521id = 5) int i11, @SafeParcelable.Param(m17521id = 6) List list, @SafeParcelable.Param(m17521id = 12) String str2, @SafeParcelable.Param(m17521id = 8) long j10, @SafeParcelable.Param(m17521id = 14) int i12, @SafeParcelable.Param(m17521id = 10) String str3, @SafeParcelable.Param(m17521id = 13) String str4, @SafeParcelable.Param(m17521id = 15) float f9, @SafeParcelable.Param(m17521id = 16) long j11, @SafeParcelable.Param(m17521id = 17) String str5, @SafeParcelable.Param(m17521id = 18) boolean z8) {
        this.zza = i9;
        this.zzb = j9;
        this.zzc = i10;
        this.zzd = str;
        this.zze = str3;
        this.zzf = str5;
        this.zzg = i11;
        this.zzh = list;
        this.zzi = str2;
        this.zzj = j10;
        this.zzk = i12;
        this.zzl = str4;
        this.zzm = f9;
        this.zzn = j11;
        this.zzo = z8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzg);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzj);
        SafeParcelWriter.writeString(parcel, 10, this.zze, false);
        SafeParcelWriter.writeInt(parcel, 11, this.zzc);
        SafeParcelWriter.writeString(parcel, 12, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeInt(parcel, 14, this.zzk);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzm);
        SafeParcelWriter.writeLong(parcel, 16, this.zzn);
        SafeParcelWriter.writeString(parcel, 17, this.zzf, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzo);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzb() {
        return this.zzp;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final String zzd() {
        List list = this.zzh;
        String str = this.zzd;
        int i9 = this.zzg;
        String strJoin = list == null ? "" : TextUtils.join(",", list);
        int i10 = this.zzk;
        String str2 = this.zze;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = this.zzl;
        if (str3 == null) {
            str3 = "";
        }
        float f9 = this.zzm;
        String str4 = this.zzf;
        return "\t" + str + "\t" + i9 + "\t" + strJoin + "\t" + i10 + "\t" + str2 + "\t" + str3 + "\t" + f9 + "\t" + (str4 != null ? str4 : "") + "\t" + this.zzo;
    }
}
