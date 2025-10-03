package com.google.android.datatransport.runtime.dagger.internal;

import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private InterfaceC6266a<T> delegate;

    public static <T> void setDelegate(InterfaceC6266a<T> interfaceC6266a, InterfaceC6266a<T> interfaceC6266a2) {
        Preconditions.checkNotNull(interfaceC6266a2);
        DelegateFactory delegateFactory = (DelegateFactory) interfaceC6266a;
        if (delegateFactory.delegate != null) {
            throw new IllegalStateException();
        }
        delegateFactory.delegate = interfaceC6266a2;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public T get() {
        InterfaceC6266a<T> interfaceC6266a = this.delegate;
        if (interfaceC6266a != null) {
            return interfaceC6266a.get();
        }
        throw new IllegalStateException();
    }

    public InterfaceC6266a<T> getDelegate() {
        return (InterfaceC6266a) Preconditions.checkNotNull(this.delegate);
    }

    @Deprecated
    public void setDelegatedProvider(InterfaceC6266a<T> interfaceC6266a) {
        setDelegate(this, interfaceC6266a);
    }
}
