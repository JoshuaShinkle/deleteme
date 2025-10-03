package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzgv implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzgc zze;

    public zzgv(zzgc zzgcVar, String str, String str2, String str3, long j9) {
        this.zze = zzgcVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zzu().zzu().zza(this.zzb, (zzin) null);
        } else {
            this.zze.zza.zzu().zzu().zza(this.zzb, new zzin(this.zzc, str, this.zzd));
        }
    }
}
