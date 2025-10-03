package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class zzjm implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzn zzf;
    private final /* synthetic */ zziv zzg;

    public zzjm(zziv zzivVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z8, zzn zznVar) {
        this.zzg = zzivVar;
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z8;
        this.zzf = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzep zzepVar;
        synchronized (this.zza) {
            try {
                try {
                    zzepVar = this.zzg.zzb;
                } catch (RemoteException e9) {
                    this.zzg.zzq().zze().zza("(legacy) Failed to get user properties; remote exception", zzex.zza(this.zzb), this.zzc, e9);
                    this.zza.set(Collections.emptyList());
                }
                if (zzepVar == null) {
                    this.zzg.zzq().zze().zza("(legacy) Failed to get user properties; not connected to service", zzex.zza(this.zzb), this.zzc, this.zzd);
                    this.zza.set(Collections.emptyList());
                    return;
                }
                if (TextUtils.isEmpty(this.zzb)) {
                    this.zza.set(zzepVar.zza(this.zzc, this.zzd, this.zze, this.zzf));
                } else {
                    this.zza.set(zzepVar.zza(this.zzb, this.zzc, this.zzd, this.zze));
                }
                this.zzg.zzaj();
                this.zza.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}
