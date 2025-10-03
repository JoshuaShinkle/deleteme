package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class ProviderOfLazy<T> implements InterfaceC6266a<Lazy<T>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final InterfaceC6266a<T> provider;

    private ProviderOfLazy(InterfaceC6266a<T> interfaceC6266a) {
        this.provider = interfaceC6266a;
    }

    public static <T> InterfaceC6266a<Lazy<T>> create(InterfaceC6266a<T> interfaceC6266a) {
        return new ProviderOfLazy((InterfaceC6266a) Preconditions.checkNotNull(interfaceC6266a));
    }

    @Override // p194s5.InterfaceC6266a
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }
}
