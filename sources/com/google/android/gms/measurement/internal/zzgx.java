package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
abstract class zzgx extends zzgu {
    private boolean zza;

    public zzgx(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzy.zza(this);
    }

    /* renamed from: g_ */
    public void mo17541g_() {
    }

    public final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzd()) {
            return;
        }
        this.zzy.zzae();
        this.zza = true;
    }

    public final void zzac() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        mo17541g_();
        this.zzy.zzae();
        this.zza = true;
    }

    public abstract boolean zzd();

    public final boolean zzz() {
        return this.zza;
    }
}
