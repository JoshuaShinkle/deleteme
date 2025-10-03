package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class zzp extends WeakReference<Throwable> {
    private final int zza;

    public zzp(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zza = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zzp.class) {
            if (this == obj) {
                return true;
            }
            zzp zzpVar = (zzp) obj;
            if (this.zza == zzpVar.zza && get() == zzpVar.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }
}
