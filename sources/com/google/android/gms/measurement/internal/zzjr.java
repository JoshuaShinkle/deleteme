package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* loaded from: classes2.dex */
final class zzjr implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzjp zzb;

    public zzjr(zzjp zzjpVar, ComponentName componentName) {
        this.zzb = zzjpVar;
        this.zza = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}
