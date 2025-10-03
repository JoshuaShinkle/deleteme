package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.internal.gtm.zzcq;
import com.google.android.gms.internal.gtm.zzcu;

@TargetApi(24)
/* loaded from: classes2.dex */
public final class AnalyticsJobService extends JobService implements zzcu {
    private zzcq<AnalyticsJobService> zzrd;

    private final zzcq<AnalyticsJobService> zzad() {
        if (this.zzrd == null) {
            this.zzrd = new zzcq<>(this);
        }
        return this.zzrd;
    }

    @Override // com.google.android.gms.internal.gtm.zzcu
    public final boolean callServiceStopSelfResult(int i9) {
        return stopSelfResult(i9);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        zzad().onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        zzad().onDestroy();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i9, int i10) {
        return zzad().onStartCommand(intent, i9, i10);
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        return zzad().onStartJob(jobParameters);
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // com.google.android.gms.internal.gtm.zzcu
    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z8) {
        jobFinished(jobParameters, false);
    }
}
