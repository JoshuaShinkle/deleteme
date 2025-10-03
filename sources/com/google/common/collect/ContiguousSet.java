package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedSet;
import java.lang.Comparable;
import java.util.NoSuchElementException;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> domain;

    public ContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(Ordering.natural());
        this.domain = discreteDomain;
    }

    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @Beta
    public static ContiguousSet<Integer> closed(int i9, int i10) {
        return create(Range.closed(Integer.valueOf(i9), Integer.valueOf(i10)), DiscreteDomain.integers());
    }

    @Beta
    public static ContiguousSet<Integer> closedOpen(int i9, int i10) {
        return create(Range.closedOpen(Integer.valueOf(i9), Integer.valueOf(i10)), DiscreteDomain.integers());
    }

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(discreteDomain);
        try {
            Range<C> rangeIntersection = !range.hasLowerBound() ? range.intersection(Range.atLeast(discreteDomain.minValue())) : range;
            if (!range.hasUpperBound()) {
                rangeIntersection = rangeIntersection.intersection(Range.atMost(discreteDomain.maxValue()));
            }
            return rangeIntersection.isEmpty() || Range.compareOrThrow(range.lowerBound.leastValueAbove(discreteDomain), range.upperBound.greatestValueBelow(discreteDomain)) > 0 ? new EmptyContiguousSet(discreteDomain) : new RegularContiguousSet(rangeIntersection, discreteDomain);
        } catch (NoSuchElementException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> headSetImpl(C c9, boolean z8);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType boundType, BoundType boundType2);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> subSetImpl(C c9, boolean z8, C c10, boolean z9);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> tailSetImpl(C c9, boolean z8);

    @Override // java.util.AbstractCollection
    public String toString() {
        return range().toString();
    }

    @Beta
    public static ContiguousSet<Long> closed(long j9, long j10) {
        return create(Range.closed(Long.valueOf(j9), Long.valueOf(j10)), DiscreteDomain.longs());
    }

    @Beta
    public static ContiguousSet<Long> closedOpen(long j9, long j10) {
        return create(Range.closedOpen(Long.valueOf(j9), Long.valueOf(j10)), DiscreteDomain.longs());
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> headSet(C c9) {
        return headSetImpl((ContiguousSet<C>) Preconditions.checkNotNull(c9), false);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> subSet(C c9, C c10) {
        Preconditions.checkNotNull(c9);
        Preconditions.checkNotNull(c10);
        Preconditions.checkArgument(comparator().compare(c9, c10) <= 0);
        return subSetImpl((boolean) c9, true, (boolean) c10, false);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> tailSet(C c9) {
        return tailSetImpl((ContiguousSet<C>) Preconditions.checkNotNull(c9), true);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> headSet(C c9, boolean z8) {
        return headSetImpl((ContiguousSet<C>) Preconditions.checkNotNull(c9), z8);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> tailSet(C c9, boolean z8) {
        return tailSetImpl((ContiguousSet<C>) Preconditions.checkNotNull(c9), z8);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> subSet(C c9, boolean z8, C c10, boolean z9) {
        Preconditions.checkNotNull(c9);
        Preconditions.checkNotNull(c10);
        Preconditions.checkArgument(comparator().compare(c9, c10) <= 0);
        return subSetImpl((boolean) c9, z8, (boolean) c10, z9);
    }
}
