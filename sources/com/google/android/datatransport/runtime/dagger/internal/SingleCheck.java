package com.google.android.datatransport.runtime.dagger.internal;

import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SingleCheck<T> implements InterfaceC6266a<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile InterfaceC6266a<T> provider;

    private SingleCheck(InterfaceC6266a<T> interfaceC6266a) {
        this.provider = interfaceC6266a;
    }

    public static <P extends InterfaceC6266a<T>, T> InterfaceC6266a<T> provider(P p8) {
        return ((p8 instanceof SingleCheck) || (p8 instanceof DoubleCheck)) ? p8 : new SingleCheck((InterfaceC6266a) Preconditions.checkNotNull(p8));
    }

    @Override // p194s5.InterfaceC6266a
    public T get() {
        T t8 = (T) this.instance;
        if (t8 != UNINITIALIZED) {
            return t8;
        }
        InterfaceC6266a<T> interfaceC6266a = this.provider;
        if (interfaceC6266a == null) {
            return (T) this.instance;
        }
        T t9 = interfaceC6266a.get();
        this.instance = t9;
        this.provider = null;
        return t9;
    }
}
