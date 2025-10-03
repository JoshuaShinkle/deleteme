package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class DoubleCheck<T> implements InterfaceC6266a<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile InterfaceC6266a<T> provider;

    private DoubleCheck(InterfaceC6266a<T> interfaceC6266a) {
        this.provider = interfaceC6266a;
    }

    public static <P extends InterfaceC6266a<T>, T> Lazy<T> lazy(P p8) {
        return p8 instanceof Lazy ? (Lazy) p8 : new DoubleCheck((InterfaceC6266a) Preconditions.checkNotNull(p8));
    }

    public static <P extends InterfaceC6266a<T>, T> InterfaceC6266a<T> provider(P p8) {
        Preconditions.checkNotNull(p8);
        return p8 instanceof DoubleCheck ? p8 : new DoubleCheck(p8);
    }

    public static Object reentrantCheck(Object obj, Object obj2) {
        if (!((obj == UNINITIALIZED || (obj instanceof MemoizedSentinel)) ? false : true) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // p194s5.InterfaceC6266a
    public T get() {
        T t8 = (T) this.instance;
        Object obj = UNINITIALIZED;
        if (t8 == obj) {
            synchronized (this) {
                t8 = (T) this.instance;
                if (t8 == obj) {
                    t8 = this.provider.get();
                    this.instance = reentrantCheck(this.instance, t8);
                    this.provider = null;
                }
            }
        }
        return t8;
    }
}
