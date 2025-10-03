package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzhm implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzhe zze;

    public zzhm(zzhe zzheVar, String str, String str2, Object obj, long j9) {
        this.zze = zzheVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = obj;
        this.zzd = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
