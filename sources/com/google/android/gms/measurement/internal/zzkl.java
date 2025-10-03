package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.google.android.gms.common.util.Clock;

/* loaded from: classes2.dex */
public final class zzkl extends zzkm {
    private final AlarmManager zzb;
    private final zzaj zzc;
    private Integer zzd;

    public zzkl(zzkp zzkpVar) {
        super(zzkpVar);
        this.zzb = (AlarmManager) zzm().getSystemService("alarm");
        this.zzc = new zzkk(this, zzkpVar.zzu(), zzkpVar);
    }

    @TargetApi(24)
    private final void zzu() {
        ((JobScheduler) zzm().getSystemService("jobscheduler")).cancel(zzv());
    }

    private final int zzv() {
        if (this.zzd == null) {
            String strValueOf = String.valueOf(zzm().getPackageName());
            this.zzd = Integer.valueOf((strValueOf.length() != 0 ? "measurement".concat(strValueOf) : new String("measurement")).hashCode());
        }
        return this.zzd.intValue();
    }

    private final PendingIntent zzw() {
        Context contextZzm = zzm();
        return PendingIntent.getBroadcast(contextZzm, 0, new Intent().setClassName(contextZzm, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), 0);
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    /* renamed from: f_ */
    public final /* bridge */ /* synthetic */ zzkt mo17540f_() {
        return super.mo17540f_();
    }

    public final void zza(long j9) {
        zzaj();
        Context contextZzm = zzm();
        if (!zzft.zza(contextZzm)) {
            zzq().zzv().zza("Receiver not registered/enabled");
        }
        if (!zzkx.zza(contextZzm, false)) {
            zzq().zzv().zza("Service not registered/enabled");
        }
        zze();
        zzq().zzw().zza("Scheduling upload, millis", Long.valueOf(j9));
        zzl().elapsedRealtime();
        if (j9 < Math.max(0L, zzat.zzw.zza(null).longValue()) && !this.zzc.zzb()) {
            this.zzc.zza(j9);
        }
        Context contextZzm2 = zzm();
        ComponentName componentName = new ComponentName(contextZzm2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int iZzv = zzv();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzh.zza(contextZzm2, new JobInfo.Builder(iZzv, componentName).setMinimumLatency(j9).setOverrideDeadline(j9 << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        this.zzb.cancel(zzw());
        zzu();
        return false;
    }

    public final void zze() {
        zzaj();
        zzq().zzw().zza("Unscheduling upload");
        this.zzb.cancel(zzw());
        this.zzc.zzc();
        zzu();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzjv zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzfv zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
