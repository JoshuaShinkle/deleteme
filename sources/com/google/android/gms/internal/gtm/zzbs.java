package com.google.android.gms.internal.gtm;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
abstract class zzbs {
    private static volatile Handler handler;
    private final zzap zzwc;
    private final Runnable zzys;
    private volatile long zzyt;

    public zzbs(zzap zzapVar) {
        Preconditions.checkNotNull(zzapVar);
        this.zzwc = zzapVar;
        this.zzys = new zzbt(this);
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzbs.class) {
            if (handler == null) {
                handler = new zzdj(this.zzwc.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    public final void cancel() {
        this.zzyt = 0L;
        getHandler().removeCallbacks(this.zzys);
    }

    public abstract void run();

    public final long zzey() {
        if (this.zzyt == 0) {
            return 0L;
        }
        return Math.abs(this.zzwc.zzcn().currentTimeMillis() - this.zzyt);
    }

    public final boolean zzez() {
        return this.zzyt != 0;
    }

    public final void zzh(long j9) {
        cancel();
        if (j9 >= 0) {
            this.zzyt = this.zzwc.zzcn().currentTimeMillis();
            if (getHandler().postDelayed(this.zzys, j9)) {
                return;
            }
            this.zzwc.zzco().zze("Failed to schedule delayed post. time", Long.valueOf(j9));
        }
    }

    public final void zzi(long j9) {
        if (zzez()) {
            if (j9 < 0) {
                cancel();
                return;
            }
            long jAbs = j9 - Math.abs(this.zzwc.zzcn().currentTimeMillis() - this.zzyt);
            long j10 = jAbs >= 0 ? jAbs : 0L;
            getHandler().removeCallbacks(this.zzys);
            if (getHandler().postDelayed(this.zzys, j10)) {
                return;
            }
            this.zzwc.zzco().zze("Failed to adjust delayed post. time", Long.valueOf(j10));
        }
    }

    public static /* synthetic */ long zza(zzbs zzbsVar, long j9) {
        zzbsVar.zzyt = 0L;
        return 0L;
    }
}
