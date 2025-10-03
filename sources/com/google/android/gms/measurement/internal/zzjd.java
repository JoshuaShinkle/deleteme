package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: classes2.dex */
final class zzjd implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zziv zzb;

    public zzjd(zziv zzivVar, zzn zznVar) {
        this.zzb = zzivVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzep zzepVar = this.zzb.zzb;
        if (zzepVar == null) {
            this.zzb.zzq().zze().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzepVar.zza(this.zza);
            this.zzb.zzi().zzac();
            this.zzb.zza(zzepVar, (AbstractSafeParcelable) null, this.zza);
            this.zzb.zzaj();
        } catch (RemoteException e9) {
            this.zzb.zzq().zze().zza("Failed to send app launch to the service", e9);
        }
    }
}
