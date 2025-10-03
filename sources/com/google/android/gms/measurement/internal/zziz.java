package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zziz implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zziv zzd;

    public zziz(zziv zzivVar, AtomicReference atomicReference, zzn zznVar, boolean z8) {
        this.zzd = zzivVar;
        this.zza = atomicReference;
        this.zzb = zznVar;
        this.zzc = z8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzep zzepVar;
        synchronized (this.zza) {
            try {
                try {
                    zzepVar = this.zzd.zzb;
                } catch (RemoteException e9) {
                    this.zzd.zzq().zze().zza("Failed to get all user properties; remote exception", e9);
                }
                if (zzepVar == null) {
                    this.zzd.zzq().zze().zza("Failed to get all user properties; not connected to service");
                    return;
                }
                this.zza.set(zzepVar.zza(this.zzb, this.zzc));
                this.zzd.zzaj();
                this.zza.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}
