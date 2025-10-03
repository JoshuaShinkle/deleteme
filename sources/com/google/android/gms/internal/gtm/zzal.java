package com.google.android.gms.internal.gtm;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzal implements Callable<Void> {
    private final /* synthetic */ zzae zzvw;

    public zzal(zzae zzaeVar) {
        this.zzvw = zzaeVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() {
        this.zzvw.zzvu.zzeb();
        return null;
    }
}
