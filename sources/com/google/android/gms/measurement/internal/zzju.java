package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzjy;

/* loaded from: classes2.dex */
public final class zzju<T extends Context & zzjy> {
    private final T zza;

    public zzju(T t8) {
        Preconditions.checkNotNull(t8);
        this.zza = t8;
    }

    public final void zza() {
        zzgb.zza(this.zza, null, null).zzq().zzw().zza("Local AppMeasurementService is starting up");
    }

    public final void zzb() {
        zzgb.zza(this.zza, null, null).zzq().zzw().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzc(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onRebind called with null intent");
        } else {
            zzc().zzw().zza("onRebind called. action", intent.getAction());
        }
    }

    private final zzex zzc() {
        return zzgb.zza(this.zza, null, null).zzq();
    }

    public final int zza(final Intent intent, int i9, final int i10) {
        final zzex zzexVarZzq = zzgb.zza(this.zza, null, null).zzq();
        if (intent == null) {
            zzexVarZzq.zzh().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzexVarZzq.zzw().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i10), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza(new Runnable(this, i10, zzexVarZzq, intent) { // from class: com.google.android.gms.measurement.internal.zzjx
                private final zzju zza;
                private final int zzb;
                private final zzex zzc;
                private final Intent zzd;

                {
                    this.zza = this;
                    this.zzb = i10;
                    this.zzc = zzexVarZzq;
                    this.zzd = intent;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zza(this.zzb, this.zzc, this.zzd);
                }
            });
        }
        return 2;
    }

    public final boolean zzb(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzw().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    private final void zza(Runnable runnable) {
        zzkp zzkpVarZza = zzkp.zza(this.zza);
        zzkpVarZza.zzp().zza(new zzjz(this, zzkpVarZza, runnable));
    }

    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgc(zzkp.zza(this.zza));
        }
        zzc().zzh().zza("onBind received unknown action", action);
        return null;
    }

    @TargetApi(24)
    public final boolean zza(final JobParameters jobParameters) {
        final zzex zzexVarZzq = zzgb.zza(this.zza, null, null).zzq();
        String string = jobParameters.getExtras().getString("action");
        zzexVarZzq.zzw().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza(new Runnable(this, zzexVarZzq, jobParameters) { // from class: com.google.android.gms.measurement.internal.zzjw
            private final zzju zza;
            private final zzex zzb;
            private final JobParameters zzc;

            {
                this.zza = this;
                this.zzb = zzexVarZzq;
                this.zzc = jobParameters;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc);
            }
        });
        return true;
    }

    public final /* synthetic */ void zza(zzex zzexVar, JobParameters jobParameters) {
        zzexVar.zzw().zza("AppMeasurementJobService processed last upload request.");
        this.zza.zza(jobParameters, false);
    }

    public final /* synthetic */ void zza(int i9, zzex zzexVar, Intent intent) {
        if (this.zza.zza(i9)) {
            zzexVar.zzw().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i9));
            zzc().zzw().zza("Completed wakeful intent.");
            this.zza.zza(intent);
        }
    }
}
