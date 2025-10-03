package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* loaded from: classes2.dex */
final class zzji implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzar zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zziv zzf;

    public zzji(zziv zzivVar, boolean z8, boolean z9, zzar zzarVar, zzn zznVar, String str) {
        this.zzf = zzivVar;
        this.zza = z8;
        this.zzb = z9;
        this.zzc = zzarVar;
        this.zzd = zznVar;
        this.zze = str;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzep zzepVar = this.zzf.zzb;
        if (zzepVar == null) {
            this.zzf.zzq().zze().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            this.zzf.zza(zzepVar, this.zzb ? null : this.zzc, this.zzd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    zzepVar.zza(this.zzc, this.zzd);
                } else {
                    zzepVar.zza(this.zzc, this.zze, this.zzf.zzq().zzx());
                }
            } catch (RemoteException e9) {
                this.zzf.zzq().zze().zza("Failed to send event to the service", e9);
            }
        }
        this.zzf.zzaj();
    }
}
