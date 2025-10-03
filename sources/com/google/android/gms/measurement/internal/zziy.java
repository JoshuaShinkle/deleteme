package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* loaded from: classes2.dex */
final class zziy implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zziv zzb;

    public zziy(zziv zzivVar, zzn zznVar) {
        this.zzb = zzivVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzep zzepVar = this.zzb.zzb;
        if (zzepVar == null) {
            this.zzb.zzq().zze().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            zzepVar.zzd(this.zza);
        } catch (RemoteException e9) {
            this.zzb.zzq().zze().zza("Failed to reset data on the service: remote exception", e9);
        }
        this.zzb.zzaj();
    }
}
