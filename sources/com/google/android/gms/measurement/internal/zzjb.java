package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzmb;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzjb implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zziv zzc;

    public zzjb(zziv zzivVar, AtomicReference atomicReference, zzn zznVar) {
        this.zzc = zzivVar;
        this.zza = atomicReference;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                try {
                } catch (RemoteException e9) {
                    this.zzc.zzq().zze().zza("Failed to get app instance id", e9);
                }
                if (zzmb.zzb() && this.zzc.zzs().zza(zzat.zzco) && !this.zzc.zzr().zzw().zze()) {
                    this.zzc.zzq().zzj().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zze().zza((String) null);
                    this.zzc.zzr().zzj.zza(null);
                    this.zza.set(null);
                    return;
                }
                zzep zzepVar = this.zzc.zzb;
                if (zzepVar == null) {
                    this.zzc.zzq().zze().zza("Failed to get app instance id");
                    return;
                }
                this.zza.set(zzepVar.zzc(this.zzb));
                String str = (String) this.zza.get();
                if (str != null) {
                    this.zzc.zze().zza(str);
                    this.zzc.zzr().zzj.zza(str);
                }
                this.zzc.zzaj();
                this.zza.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}
