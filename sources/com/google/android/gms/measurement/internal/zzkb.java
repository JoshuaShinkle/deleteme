package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;

/* loaded from: classes2.dex */
public final class zzkb extends zzg {
    protected final zzkj zza;
    protected final zzkh zzb;
    private Handler zzc;
    private final zzkc zzd;

    public zzkb(zzgb zzgbVar) {
        super(zzgbVar);
        this.zza = new zzkj(this);
        this.zzb = new zzkh(this);
        this.zzd = new zzkc(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzaa() {
        zzc();
        if (this.zzc == null) {
            this.zzc = new com.google.android.gms.internal.measurement.zzq(Looper.getMainLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(long j9) {
        zzc();
        zzaa();
        zzq().zzw().zza("Activity resumed, time", Long.valueOf(j9));
        if (zzs().zza(zzat.zzbw)) {
            if (zzs().zzh().booleanValue() || zzr().zzr.zza()) {
                this.zzb.zza(j9);
            }
            this.zzd.zza();
        } else {
            this.zzd.zza();
            if (zzs().zzh().booleanValue()) {
                this.zzb.zza(j9);
            }
        }
        zzkj zzkjVar = this.zza;
        zzkjVar.zza.zzc();
        if (zzkjVar.zza.zzy.zzaa()) {
            if (!zzkjVar.zza.zzs().zza(zzat.zzbw)) {
                zzkjVar.zza.zzr().zzr.zza(false);
            }
            zzkjVar.zza(zzkjVar.zza.zzl().currentTimeMillis(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(long j9) {
        zzc();
        zzaa();
        zzq().zzw().zza("Activity paused, time", Long.valueOf(j9));
        this.zzd.zza(j9);
        if (zzs().zzh().booleanValue()) {
            this.zzb.zzb(j9);
        }
        zzkj zzkjVar = this.zza;
        if (zzkjVar.zza.zzs().zza(zzat.zzbw)) {
            return;
        }
        zzkjVar.zza.zzr().zzr.zza(true);
    }

    public final boolean zza(boolean z8, boolean z9, long j9) {
        return this.zzb.zza(z8, z9, j9);
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhe zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziv zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzim zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzet zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzkb zzj() {
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

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzy() {
        return false;
    }

    public final long zza(long j9) {
        return this.zzb.zzc(j9);
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }
}
