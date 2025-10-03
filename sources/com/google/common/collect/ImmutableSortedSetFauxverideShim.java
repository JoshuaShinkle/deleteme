package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableSortedSet;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class ImmutableSortedSetFauxverideShim<E> extends ImmutableSet<E> {
    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedSet<E> copyOf(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17660of(E e9) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17661of(E e9, E e10) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17662of(E e9, E e10, E e11) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17663of(E e9, E e10, E e11, E e12) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17664of(E e9, E e10, E e11, E e12, E e13) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* renamed from: of */
    public static <E> ImmutableSortedSet<E> m17665of(E e9, E e10, E e11, E e12, E e13, E e14, E... eArr) {
        throw new UnsupportedOperationException();
    }
}
