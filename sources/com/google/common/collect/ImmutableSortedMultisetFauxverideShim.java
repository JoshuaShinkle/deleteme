package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableSortedMultiset;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class ImmutableSortedMultisetFauxverideShim<E> extends ImmutableMultiset<E> {
    @Deprecated
    public static <E> ImmutableSortedMultiset.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> copyOf(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17647of(E e9) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17648of(E e9, E e10) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17649of(E e9, E e10, E e11) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17650of(E e9, E e10, E e11, E e12) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17651of(E e9, E e10, E e11, E e12, E e13) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedMultiset<E> m17652of(E e9, E e10, E e11, E e12, E e13, E e14, E... eArr) {
        throw new UnsupportedOperationException();
    }
}
