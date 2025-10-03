package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzmb;

/* loaded from: classes2.dex */
final class zzja implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzw zzb;
    private final /* synthetic */ zziv zzc;

    public zzja(zziv zzivVar, zzn zznVar, com.google.android.gms.internal.measurement.zzw zzwVar) {
        this.zzc = zzivVar;
        this.zza = zznVar;
        this.zzb = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (zzmb.zzb() && this.zzc.zzs().zza(zzat.zzco) && !this.zzc.zzr().zzw().zze()) {
                this.zzc.zzq().zzj().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zze().zza((String) null);
                this.zzc.zzr().zzj.zza(null);
                return;
            }
            zzep zzepVar = this.zzc.zzb;
            if (zzepVar == null) {
                this.zzc.zzq().zze().zza("Failed to get app instance id");
                return;
            }
            String strZzc = zzepVar.zzc(this.zza);
            if (strZzc != null) {
                this.zzc.zze().zza(strZzc);
                this.zzc.zzr().zzj.zza(strZzc);
            }
            this.zzc.zzaj();
            this.zzc.zzo().zza(this.zzb, strZzc);
        } catch (RemoteException e9) {
            this.zzc.zzq().zze().zza("Failed to get app instance id", e9);
        } finally {
            this.zzc.zzo().zza(this.zzb, (String) null);
        }
    }
}
