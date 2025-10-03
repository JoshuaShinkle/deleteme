package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
final class zzi implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzw zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ AppMeasurementDynamiteService zze;

    public zzi(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzw zzwVar, String str, String str2, boolean z8) {
        this.zze = appMeasurementDynamiteService;
        this.zza = zzwVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza.zzv().zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
