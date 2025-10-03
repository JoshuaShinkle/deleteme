package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public abstract class zzan extends zzam {
    private boolean zzwd;

    public zzan(zzap zzapVar) {
        super(zzapVar);
    }

    public final boolean isInitialized() {
        return this.zzwd;
    }

    public final void zzag() {
        zzaw();
        this.zzwd = true;
    }

    public abstract void zzaw();

    public final void zzdb() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
