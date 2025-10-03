package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzju;
import com.google.android.gms.measurement.internal.zzjy;

@TargetApi(24)
/* loaded from: classes2.dex */
public final class AppMeasurementJobService extends JobService implements zzjy {
    private zzju<AppMeasurementJobService> zza;

    private final zzju<AppMeasurementJobService> zza() {
        if (this.zza == null) {
            this.zza = new zzju<>(this);
        }
        return this.zza;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        zza().zza();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        zza().zzb();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onRebind(Intent intent) {
        zza().zzc(intent);
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        return zza().zza(jobParameters);
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return zza().zzb(intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    public final void zza(Intent intent) {
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    public final boolean zza(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z8) {
        jobFinished(jobParameters, false);
    }
}
