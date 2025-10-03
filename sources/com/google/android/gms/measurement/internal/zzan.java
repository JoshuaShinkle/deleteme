package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zzan {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    public zzan(String str, String str2, long j9, long j10, long j11, long j12, long j13, Long l9, Long l10, Long l11, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkArgument(j9 >= 0);
        Preconditions.checkArgument(j10 >= 0);
        Preconditions.checkArgument(j11 >= 0);
        Preconditions.checkArgument(j13 >= 0);
        this.zza = str;
        this.zzb = str2;
        this.zzc = j9;
        this.zzd = j10;
        this.zze = j11;
        this.zzf = j12;
        this.zzg = j13;
        this.zzh = l9;
        this.zzi = l10;
        this.zzj = l11;
        this.zzk = bool;
    }

    public final zzan zza(long j9) {
        return new zzan(this.zza, this.zzb, this.zzc, this.zzd, this.zze, j9, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }

    public final zzan zza(long j9, long j10) {
        return new zzan(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, j9, Long.valueOf(j10), this.zzi, this.zzj, this.zzk);
    }

    public final zzan zza(Long l9, Long l10, Boolean bool) {
        return new zzan(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l9, l10, (bool == null || bool.booleanValue()) ? bool : null);
    }

    public zzan(String str, String str2, long j9, long j10, long j11, long j12, Long l9, Long l10, Long l11, Boolean bool) {
        this(str, str2, 0L, 0L, 0L, j11, 0L, null, null, null, null);
    }
}
