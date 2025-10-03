package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class zzgb extends WeakReference<Throwable> {
    private final int zza;

    public zzgb(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zza = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zzgb.class) {
            if (this == obj) {
                return true;
            }
            zzgb zzgbVar = (zzgb) obj;
            if (this.zza == zzgbVar.zza && get() == zzgbVar.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }
}
