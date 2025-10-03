package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* loaded from: classes2.dex */
class zzgu implements zzgw {
    protected final zzgb zzy;

    public zzgu(zzgb zzgbVar) {
        Preconditions.checkNotNull(zzgbVar);
        this.zzy = zzgbVar;
    }

    public void zza() {
        this.zzy.zzad();
    }

    public void zzb() {
        this.zzy.zzp().zzb();
    }

    public void zzc() {
        this.zzy.zzp().zzc();
    }

    public zzal zzk() {
        return this.zzy.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public Clock zzl() {
        return this.zzy.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public Context zzm() {
        return this.zzy.zzm();
    }

    public zzev zzn() {
        return this.zzy.zzi();
    }

    public zzkx zzo() {
        return this.zzy.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public zzfu zzp() {
        return this.zzy.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public zzex zzq() {
        return this.zzy.zzq();
    }

    public zzfj zzr() {
        return this.zzy.zzb();
    }

    public zzy zzs() {
        return this.zzy.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public zzx zzt() {
        return this.zzy.zzt();
    }
}
