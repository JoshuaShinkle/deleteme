package com.google.android.gms.measurement.internal;

/* loaded from: classes2.dex */
abstract class zzg extends zzd {
    private boolean zza;

    public zzg(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzy.zza(this);
    }

    public final boolean zzu() {
        return this.zza;
    }

    public final void zzv() {
        if (!zzu()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzy()) {
            return;
        }
        this.zzy.zzae();
        this.zza = true;
    }

    public final void zzx() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzz();
        this.zzy.zzae();
        this.zza = true;
    }

    public abstract boolean zzy();

    public void zzz() {
    }
}
