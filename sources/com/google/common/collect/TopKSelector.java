package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@GwtCompatible
/* loaded from: classes2.dex */
final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;

    /* renamed from: k */
    private final int f15422k;
    private T threshold;

    private TopKSelector(Comparator<? super T> comparator, int i9) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator, "comparator");
        this.f15422k = i9;
        Preconditions.checkArgument(i9 >= 0, "k must be nonnegative, was %s", i9);
        this.buffer = (T[]) new Object[i9 * 2];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int i9) {
        return greatest(i9, Ordering.natural());
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int i9) {
        return least(i9, Ordering.natural());
    }

    private int partition(int i9, int i10, int i11) {
        T[] tArr = this.buffer;
        T t8 = tArr[i11];
        tArr[i11] = tArr[i10];
        int i12 = i9;
        while (i9 < i10) {
            if (this.comparator.compare(this.buffer[i9], t8) < 0) {
                swap(i12, i9);
                i12++;
            }
            i9++;
        }
        T[] tArr2 = this.buffer;
        tArr2[i10] = tArr2[i12];
        tArr2[i12] = t8;
        return i12;
    }

    private void swap(int i9, int i10) {
        T[] tArr = this.buffer;
        T t8 = tArr[i9];
        tArr[i9] = tArr[i10];
        tArr[i10] = t8;
    }

    private void trim() {
        int i9 = (this.f15422k * 2) - 1;
        int iLog2 = IntMath.log2(i9 + 0, RoundingMode.CEILING) * 3;
        int iMax = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (iMax >= i9) {
                break;
            }
            int iPartition = partition(iMax, i9, ((iMax + i9) + 1) >>> 1);
            int i12 = this.f15422k;
            if (iPartition <= i12) {
                if (iPartition >= i12) {
                    break;
                }
                iMax = Math.max(iPartition, iMax + 1);
                i11 = iPartition;
            } else {
                i9 = iPartition - 1;
            }
            i10++;
            if (i10 >= iLog2) {
                Arrays.sort(this.buffer, iMax, i9, this.comparator);
                break;
            }
        }
        this.bufferSize = this.f15422k;
        this.threshold = this.buffer[i11];
        while (true) {
            i11++;
            if (i11 >= this.f15422k) {
                return;
            }
            if (this.comparator.compare(this.buffer[i11], this.threshold) > 0) {
                this.threshold = this.buffer[i11];
            }
        }
    }

    public void offer(T t8) {
        int i9 = this.f15422k;
        if (i9 == 0) {
            return;
        }
        int i10 = this.bufferSize;
        if (i10 == 0) {
            this.buffer[0] = t8;
            this.threshold = t8;
            this.bufferSize = 1;
            return;
        }
        if (i10 < i9) {
            T[] tArr = this.buffer;
            this.bufferSize = i10 + 1;
            tArr[i10] = t8;
            if (this.comparator.compare(t8, this.threshold) > 0) {
                this.threshold = t8;
                return;
            }
            return;
        }
        if (this.comparator.compare(t8, this.threshold) < 0) {
            T[] tArr2 = this.buffer;
            int i11 = this.bufferSize;
            int i12 = i11 + 1;
            this.bufferSize = i12;
            tArr2[i11] = t8;
            if (i12 == this.f15422k * 2) {
                trim();
            }
        }
    }

    public void offerAll(Iterable<? extends T> iterable) {
        offerAll(iterable.iterator());
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        int i9 = this.bufferSize;
        int i10 = this.f15422k;
        if (i9 > i10) {
            T[] tArr = this.buffer;
            Arrays.fill(tArr, i10, tArr.length, (Object) null);
            int i11 = this.f15422k;
            this.bufferSize = i11;
            this.threshold = this.buffer[i11 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }

    public static <T> TopKSelector<T> greatest(int i9, Comparator<? super T> comparator) {
        return new TopKSelector<>(Ordering.from(comparator).reverse(), i9);
    }

    public static <T> TopKSelector<T> least(int i9, Comparator<? super T> comparator) {
        return new TopKSelector<>(comparator, i9);
    }

    public void offerAll(Iterator<? extends T> it) {
        while (it.hasNext()) {
            offer(it.next());
        }
    }
}
