package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Comparator;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ComparisonChain {
    private static final ComparisonChain ACTIVE = new ComparisonChain() { // from class: com.google.common.collect.ComparisonChain.1
        public ComparisonChain classify(int i9) {
            return i9 < 0 ? ComparisonChain.LESS : i9 > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean z8, boolean z9) {
            return classify(Booleans.compare(z8, z9));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean z8, boolean z9) {
            return classify(Booleans.compare(z9, z8));
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return 0;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(T t8, T t9, Comparator<T> comparator) {
            return classify(comparator.compare(t8, t9));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int i9, int i10) {
            return classify(Ints.compare(i9, i10));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long j9, long j10) {
            return classify(Longs.compare(j9, j10));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float f9, float f10) {
            return classify(Float.compare(f9, f10));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double d9, double d10) {
            return classify(Double.compare(d9, d10));
        }
    };
    private static final ComparisonChain LESS = new InactiveComparisonChain(-1);
    private static final ComparisonChain GREATER = new InactiveComparisonChain(1);

    public static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        public InactiveComparisonChain(int i9) {
            super();
            this.result = i9;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double d9, double d10) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float f9, float f10) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int i9, int i10) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long j9, long j10) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(T t8, T t9, Comparator<T> comparator) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean z8, boolean z9) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean z8, boolean z9) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return this.result;
        }
    }

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    public abstract ComparisonChain compare(double d9, double d10);

    public abstract ComparisonChain compare(float f9, float f10);

    public abstract ComparisonChain compare(int i9, int i10);

    public abstract ComparisonChain compare(long j9, long j10);

    @Deprecated
    public final ComparisonChain compare(Boolean bool, Boolean bool2) {
        return compareFalseFirst(bool.booleanValue(), bool2.booleanValue());
    }

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(T t8, T t9, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z8, boolean z9);

    public abstract ComparisonChain compareTrueFirst(boolean z8, boolean z9);

    public abstract int result();
}
