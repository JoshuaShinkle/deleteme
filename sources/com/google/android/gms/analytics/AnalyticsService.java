package com.google.android.gms.analytics;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.internal.gtm.zzcq;
import com.google.android.gms.internal.gtm.zzcu;

/* loaded from: classes2.dex */
public final class AnalyticsService extends Service implements zzcu {
    private zzcq<AnalyticsService> zzrd;

    private final zzcq<AnalyticsService> zzad() {
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
    public final IBinder onBind(Intent intent) {
        zzad();
        return null;
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

    @Override // com.google.android.gms.internal.gtm.zzcu
    public final void zza(JobParameters jobParameters, boolean z8) {
        throw new UnsupportedOperationException();
    }
}
