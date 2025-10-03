package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;

/* loaded from: classes2.dex */
final class zzix implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzw zze;
    private final /* synthetic */ zziv zzf;

    public zzix(zziv zzivVar, String str, String str2, boolean z8, zzn zznVar, com.google.android.gms.internal.measurement.zzw zzwVar) {
        this.zzf = zzivVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z8;
        this.zzd = zznVar;
        this.zze = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle = new Bundle();
        try {
            zzep zzepVar = this.zzf.zzb;
            if (zzepVar == null) {
                this.zzf.zzq().zze().zza("Failed to get user properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Bundle bundleZza = zzkx.zza(zzepVar.zza(this.zza, this.zzb, this.zzc, this.zzd));
            this.zzf.zzaj();
            this.zzf.zzo().zza(this.zze, bundleZza);
        } catch (RemoteException e9) {
            this.zzf.zzq().zze().zza("Failed to get user properties; remote exception", this.zza, e9);
        } finally {
            this.zzf.zzo().zza(this.zze, bundle);
        }
    }
}
