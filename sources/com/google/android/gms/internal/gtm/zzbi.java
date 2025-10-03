package com.google.android.gms.internal.gtm;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzbi implements Callable<String> {
    private final /* synthetic */ zzbh zzyi;

    public zzbi(zzbh zzbhVar) {
        this.zzyi = zzbhVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() {
        return this.zzyi.zzej();
    }
}
