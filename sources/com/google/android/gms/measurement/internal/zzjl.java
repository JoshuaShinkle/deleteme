package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* loaded from: classes2.dex */
final class zzjl implements Runnable {
    private final /* synthetic */ boolean zza = true;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzw zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ zzw zze;
    private final /* synthetic */ zziv zzf;

    public zzjl(zziv zzivVar, boolean z8, boolean z9, zzw zzwVar, zzn zznVar, zzw zzwVar2) {
        this.zzf = zzivVar;
        this.zzb = z9;
        this.zzc = zzwVar;
        this.zzd = zznVar;
        this.zze = zzwVar2;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzep zzepVar = this.zzf.zzb;
        if (zzepVar == null) {
            this.zzf.zzq().zze().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zza) {
            this.zzf.zza(zzepVar, this.zzb ? null : this.zzc, this.zzd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze.zza)) {
                    zzepVar.zza(this.zzc, this.zzd);
                } else {
                    zzepVar.zza(this.zzc);
                }
            } catch (RemoteException e9) {
                this.zzf.zzq().zze().zza("Failed to send conditional user property to the service", e9);
            }
        }
        this.zzf.zzaj();
    }
}
