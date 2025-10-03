package com.google.firebase.analytics;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzc implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    public zzc(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() {
        String strZzb = this.zza.zzb();
        if (strZzb != null) {
            return strZzb;
        }
        String strZzh = this.zza.zzb.zzh();
        this.zza.zza(strZzh);
        return strZzh;
    }
}
