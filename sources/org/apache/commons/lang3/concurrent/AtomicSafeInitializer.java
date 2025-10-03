package org.apache.commons.lang3.concurrent;

import com.google.android.gms.common.api.internal.C3457a;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class AtomicSafeInitializer<T> implements ConcurrentInitializer<T> {
    private final AtomicReference<AtomicSafeInitializer<T>> factory = new AtomicReference<>();
    private final AtomicReference<T> reference = new AtomicReference<>();

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public final T get() {
        while (true) {
            T t8 = this.reference.get();
            if (t8 != null) {
                return t8;
            }
            if (C3457a.m17508a(this.factory, null, this)) {
                this.reference.set(initialize());
            }
        }
    }

    public abstract T initialize();
}
