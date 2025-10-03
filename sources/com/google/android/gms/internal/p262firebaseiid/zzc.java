package com.google.android.gms.internal.p262firebaseiid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes2.dex */
final class zzc implements zzb {
    private zzc() {
    }

    @Override // com.google.android.gms.internal.p262firebaseiid.zzb
    public final ScheduledExecutorService zza(int i9, ThreadFactory threadFactory, int i10) {
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, threadFactory));
    }
}
