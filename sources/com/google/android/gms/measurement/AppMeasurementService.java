package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzju;
import com.google.android.gms.measurement.internal.zzjy;

/* loaded from: classes2.dex */
public final class AppMeasurementService extends Service implements zzjy {
    private zzju<AppMeasurementService> zza;

    private final zzju<AppMeasurementService> zza() {
        if (this.zza == null) {
            this.zza = new zzju<>(this);
        }
        return this.zza;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return zza().zza(intent);
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

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i9, int i10) {
        return zza().zza(intent, i9, i10);
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return zza().zzb(intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    public final boolean zza(int i9) {
        return stopSelfResult(i9);
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    public final void zza(JobParameters jobParameters, boolean z8) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.measurement.internal.zzjy
    public final void zza(Intent intent) {
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
