package com.google.android.gms.analytics;

import android.util.Log;
import com.google.android.gms.analytics.zzk;
import java.lang.Thread;
import java.util.concurrent.FutureTask;

/* loaded from: classes2.dex */
final class zzm extends FutureTask {
    private final /* synthetic */ zzk.zza zzsy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzm(zzk.zza zzaVar, Runnable runnable, Object obj) {
        super(runnable, obj);
        this.zzsy = zzaVar;
    }

    @Override // java.util.concurrent.FutureTask
    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = zzk.this.zzsv;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        } else if (Log.isLoggable("GAv4", 6)) {
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 37);
            sb.append("MeasurementExecutor: job failed with ");
            sb.append(strValueOf);
            Log.e("GAv4", sb.toString());
        }
        super.setException(th);
    }
}
