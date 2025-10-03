package com.google.android.gms.internal.gtm;

import android.content.ComponentName;

/* loaded from: classes2.dex */
final class zzax implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzav zzxi;

    public zzax(zzav zzavVar, ComponentName componentName) {
        this.zzxi = zzavVar;
        this.val$name = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzxi.zzxe.onServiceDisconnected(this.val$name);
    }
}
