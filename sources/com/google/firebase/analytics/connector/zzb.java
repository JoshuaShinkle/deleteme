package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
final /* synthetic */ class zzb implements Executor {
    static final Executor zza = new zzb();

    private zzb() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
