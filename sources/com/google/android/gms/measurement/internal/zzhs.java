package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes2.dex */
final class zzhs implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzhe zzb;

    public zzhs(zzhe zzheVar, Bundle bundle) {
        this.zzb = zzheVar;
        this.zza = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzd(this.zza);
    }
}
