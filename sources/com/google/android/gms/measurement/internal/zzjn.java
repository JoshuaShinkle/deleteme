package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;

/* loaded from: classes2.dex */
final class zzjn implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzw zzd;
    private final /* synthetic */ zziv zze;

    public zzjn(zziv zzivVar, String str, String str2, zzn zznVar, com.google.android.gms.internal.measurement.zzw zzwVar) {
        this.zze = zzivVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zznVar;
        this.zzd = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzep zzepVar = this.zze.zzb;
            if (zzepVar == null) {
                this.zze.zzq().zze().zza("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                return;
            }
            ArrayList<Bundle> arrayListZzb = zzkx.zzb(zzepVar.zza(this.zza, this.zzb, this.zzc));
            this.zze.zzaj();
            this.zze.zzo().zza(this.zzd, arrayListZzb);
        } catch (RemoteException e9) {
            this.zze.zzq().zze().zza("Failed to get conditional properties; remote exception", this.zza, this.zzb, e9);
        } finally {
            this.zze.zzo().zza(this.zzd, arrayList);
        }
    }
}
