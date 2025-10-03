package com.google.android.gms.internal.gtm;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.gtm.zzcu;
import com.google.android.gms.stats.WakeLock;

/* loaded from: classes2.dex */
public final class zzcq<T extends Context & zzcu> {
    private static Boolean zzacd;
    private final Handler handler;
    private final T zzacc;

    public zzcq(T t8) {
        Preconditions.checkNotNull(t8);
        this.zzacc = t8;
        this.handler = new zzdj();
    }

    private final void zzb(Runnable runnable) {
        zzap.zzc(this.zzacc).zzcs().zza(new zzct(this, runnable));
    }

    public static boolean zze(Context context) {
        Preconditions.checkNotNull(context);
        Boolean bool = zzacd;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zZzc = zzcz.zzc(context, "com.google.android.gms.analytics.AnalyticsService");
        zzacd = Boolean.valueOf(zZzc);
        return zZzc;
    }

    public final void onCreate() {
        zzap.zzc(this.zzacc).zzco().zzq("Local AnalyticsService is starting up");
    }

    public final void onDestroy() {
        zzap.zzc(this.zzacc).zzco().zzq("Local AnalyticsService is shutting down");
    }

    public final int onStartCommand(Intent intent, int i9, final int i10) {
        try {
            synchronized (zzcp.lock) {
                WakeLock wakeLock = zzcp.zzacb;
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        } catch (SecurityException unused) {
        }
        final zzci zzciVarZzco = zzap.zzc(this.zzacc).zzco();
        if (intent == null) {
            zzciVarZzco.zzt("AnalyticsService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzciVarZzco.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i10), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzb(new Runnable(this, i10, zzciVarZzco) { // from class: com.google.android.gms.internal.gtm.zzcr
                private final zzcq zzace;
                private final int zzacf;
                private final zzci zzacg;

                {
                    this.zzace = this;
                    this.zzacf = i10;
                    this.zzacg = zzciVarZzco;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzace.zza(this.zzacf, this.zzacg);
                }
            });
        }
        return 2;
    }

    @TargetApi(24)
    public final boolean onStartJob(final JobParameters jobParameters) {
        final zzci zzciVarZzco = zzap.zzc(this.zzacc).zzco();
        String string = jobParameters.getExtras().getString("action");
        zzciVarZzco.zza("Local AnalyticsJobService called. action", string);
        if (!"com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            return true;
        }
        zzb(new Runnable(this, zzciVarZzco, jobParameters) { // from class: com.google.android.gms.internal.gtm.zzcs
            private final zzcq zzace;
            private final zzci zzach;
            private final JobParameters zzaci;

            {
                this.zzace = this;
                this.zzach = zzciVarZzco;
                this.zzaci = jobParameters;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzace.zza(this.zzach, this.zzaci);
            }
        });
        return true;
    }

    public final /* synthetic */ void zza(zzci zzciVar, JobParameters jobParameters) {
        zzciVar.zzq("AnalyticsJobService processed last dispatch request");
        this.zzacc.zza(jobParameters, false);
    }

    public final /* synthetic */ void zza(int i9, zzci zzciVar) {
        if (this.zzacc.callServiceStopSelfResult(i9)) {
            zzciVar.zzq("Local AnalyticsService processed last dispatch request");
        }
    }
}
