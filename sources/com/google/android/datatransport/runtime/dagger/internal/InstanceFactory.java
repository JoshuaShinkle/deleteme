package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;

/* loaded from: classes.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {
    private static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = new InstanceFactory<>(null);
    private final T instance;

    private InstanceFactory(T t8) {
        this.instance = t8;
    }

    public static <T> Factory<T> create(T t8) {
        return new InstanceFactory(Preconditions.checkNotNull(t8, "instance cannot be null"));
    }

    public static <T> Factory<T> createNullable(T t8) {
        return t8 == null ? nullInstanceFactory() : new InstanceFactory(t8);
    }

    private static <T> InstanceFactory<T> nullInstanceFactory() {
        return (InstanceFactory<T>) NULL_INSTANCE_FACTORY;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public T get() {
        return this.instance;
    }
}
