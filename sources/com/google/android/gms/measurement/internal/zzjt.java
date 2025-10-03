package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* loaded from: classes2.dex */
final class zzjt implements Runnable {
    private final /* synthetic */ zzjp zza;

    public zzjt(zzjp zzjpVar) {
        this.zza = zzjpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza.zza(new ComponentName(this.zza.zza.zzm(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
