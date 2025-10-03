package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes2.dex */
public class Lazy<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance;
    private volatile Provider<T> provider;

    public Lazy(T t8) {
        this.instance = UNINITIALIZED;
        this.instance = t8;
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        T t8 = (T) this.instance;
        Object obj = UNINITIALIZED;
        if (t8 == obj) {
            synchronized (this) {
                t8 = (T) this.instance;
                if (t8 == obj) {
                    t8 = this.provider.get();
                    this.instance = t8;
                    this.provider = null;
                }
            }
        }
        return t8;
    }

    public boolean isInitialized() {
        return this.instance != UNINITIALIZED;
    }

    public Lazy(Provider<T> provider) {
        this.instance = UNINITIALIZED;
        this.provider = provider;
    }
}
