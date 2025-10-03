package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* loaded from: classes2.dex */
final class zzjg implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zziv zzb;

    public zzjg(zziv zzivVar, zzn zznVar) {
        this.zzb = zzivVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzep zzepVar = this.zzb.zzb;
        if (zzepVar == null) {
            this.zzb.zzq().zze().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzepVar.zzb(this.zza);
            this.zzb.zzaj();
        } catch (RemoteException e9) {
            this.zzb.zzq().zze().zza("Failed to send measurementEnabled to the service", e9);
        }
    }
}
