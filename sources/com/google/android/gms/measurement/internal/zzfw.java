package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* loaded from: classes2.dex */
final class zzfw implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzfu zzb;

    public zzfw(zzfu zzfuVar, String str) {
        this.zzb = zzfuVar;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzq().zze().zza(this.zza, th);
    }
}
