package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
abstract class zzkm extends zzkn {
    private boolean zzb;

    public zzkm(zzkp zzkpVar) {
        super(zzkpVar);
        this.zza.zza(this);
    }

    public final boolean zzai() {
        return this.zzb;
    }

    public final void zzaj() {
        if (!zzai()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzak() {
        if (this.zzb) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzd();
        this.zza.zzs();
        this.zzb = true;
    }

    public abstract boolean zzd();
}
