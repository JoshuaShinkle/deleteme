package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes2.dex */
public final class zzae extends zzan {
    private final zzbb zzvu;

    public zzae(zzap zzapVar, zzar zzarVar) {
        super(zzapVar);
        Preconditions.checkNotNull(zzarVar);
        this.zzvu = new zzbb(zzapVar, zzarVar);
    }

    public final void onServiceConnected() {
        com.google.android.gms.analytics.zzk.zzav();
        this.zzvu.onServiceConnected();
    }

    public final void setLocalDispatchPeriod(int i9) {
        zzdb();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i9));
        zzcq().zza(new zzaf(this, i9));
    }

    public final void start() {
        this.zzvu.start();
    }

    public final long zza(zzas zzasVar) {
        zzdb();
        Preconditions.checkNotNull(zzasVar);
        com.google.android.gms.analytics.zzk.zzav();
        long jZza = this.zzvu.zza(zzasVar, true);
        if (jZza == 0) {
            this.zzvu.zzb(zzasVar);
        }
        return jZza;
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
        this.zzvu.zzag();
    }

    public final void zzch() {
        zzdb();
        zzcq().zza(new zzaj(this));
    }

    public final void zzci() {
        zzdb();
        Context context = getContext();
        if (!zzcp.zza(context) || !zzcq.zze(context)) {
            zza((zzbw) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public final boolean zzcj() throws ExecutionException, InterruptedException, TimeoutException {
        zzdb();
        try {
            zzcq().zza(new zzal(this)).get(4L, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e9) {
            zzd("syncDispatchLocalHits interrupted", e9);
            return false;
        } catch (ExecutionException e10) {
            zze("syncDispatchLocalHits failed", e10);
            return false;
        } catch (TimeoutException e11) {
            zzd("syncDispatchLocalHits timed out", e11);
            return false;
        }
    }

    public final void zzck() {
        zzdb();
        com.google.android.gms.analytics.zzk.zzav();
        zzbb zzbbVar = this.zzvu;
        com.google.android.gms.analytics.zzk.zzav();
        zzbbVar.zzdb();
        zzbbVar.zzq("Service disconnected");
    }

    public final void zzcl() {
        com.google.android.gms.analytics.zzk.zzav();
        this.zzvu.zzcl();
    }

    public final void zza(zzcd zzcdVar) {
        Preconditions.checkNotNull(zzcdVar);
        zzdb();
        zzb("Hit delivery requested", zzcdVar);
        zzcq().zza(new zzai(this, zzcdVar));
    }

    public final void zza(zzbw zzbwVar) {
        zzdb();
        zzcq().zza(new zzak(this, zzbwVar));
    }

    public final void zza(String str, Runnable runnable) {
        Preconditions.checkNotEmpty(str, "campaign param can't be empty");
        zzcq().zza(new zzah(this, str, runnable));
    }
}
