package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* loaded from: classes2.dex */
final class zzjc implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ zziv zzb;

    public zzjc(zziv zzivVar, zzin zzinVar) {
        this.zzb = zzivVar;
        this.zza = zzinVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzep zzepVar = this.zzb.zzb;
        if (zzepVar == null) {
            this.zzb.zzq().zze().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzin zzinVar = this.zza;
            if (zzinVar == null) {
                zzepVar.zza(0L, (String) null, (String) null, this.zzb.zzm().getPackageName());
            } else {
                zzepVar.zza(zzinVar.zzc, zzinVar.zza, zzinVar.zzb, this.zzb.zzm().getPackageName());
            }
            this.zzb.zzaj();
        } catch (RemoteException e9) {
            this.zzb.zzq().zze().zza("Failed to send current screen to the service", e9);
        }
    }
}
