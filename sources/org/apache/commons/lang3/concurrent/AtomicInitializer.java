package org.apache.commons.lang3.concurrent;

import com.google.android.gms.common.api.internal.C3457a;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class AtomicInitializer<T> implements ConcurrentInitializer<T> {
    private final AtomicReference<T> reference = new AtomicReference<>();

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() {
        T t8 = this.reference.get();
        if (t8 != null) {
            return t8;
        }
        T tInitialize = initialize();
        return !C3457a.m17508a(this.reference, null, tInitialize) ? this.reference.get() : tInitialize;
    }

    public abstract T initialize();
}
