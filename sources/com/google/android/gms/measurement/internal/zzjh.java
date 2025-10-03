package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* loaded from: classes2.dex */
final class zzjh implements Runnable {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzw zzc;
    private final /* synthetic */ zziv zzd;

    public zzjh(zziv zzivVar, zzar zzarVar, String str, com.google.android.gms.internal.measurement.zzw zzwVar) {
        this.zzd = zzivVar;
        this.zza = zzarVar;
        this.zzb = str;
        this.zzc = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zzep zzepVar = this.zzd.zzb;
            if (zzepVar == null) {
                this.zzd.zzq().zze().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            byte[] bArrZza = zzepVar.zza(this.zza, this.zzb);
            this.zzd.zzaj();
            this.zzd.zzo().zza(this.zzc, bArrZza);
        } catch (RemoteException e9) {
            this.zzd.zzq().zze().zza("Failed to send event to the service to bundle", e9);
        } finally {
            this.zzd.zzo().zza(this.zzc, (byte[]) null);
        }
    }
}
