package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;

@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableList<Comparator<? super T>> comparators;

    public CompoundOrdering(Comparator<? super T> comparator, Comparator<? super T> comparator2) {
        this.comparators = ImmutableList.m17575of(comparator, comparator2);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t8, T t9) {
        int size = this.comparators.size();
        for (int i9 = 0; i9 < size; i9++) {
            int iCompare = this.comparators.get(i9).compare(t8, t9);
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompoundOrdering) {
            return this.comparators.equals(((CompoundOrdering) obj).comparators);
        }
        return false;
    }

    public int hashCode() {
        return this.comparators.hashCode();
    }

    public String toString() {
        return "Ordering.compound(" + this.comparators + ")";
    }

    public CompoundOrdering(Iterable<? extends Comparator<? super T>> iterable) {
        this.comparators = ImmutableList.copyOf(iterable);
    }
}
