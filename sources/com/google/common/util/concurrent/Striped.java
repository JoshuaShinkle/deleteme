package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() { // from class: com.google.common.util.concurrent.Striped.5
        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };

    public static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i9) {
            return (L) this.array[i9];
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.array.length;
        }

        private CompactStriped(int i9, Supplier<L> supplier) {
            super(i9);
            int i10 = 0;
            Preconditions.checkArgument(i9 <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[this.mask + 1];
            while (true) {
                Object[] objArr = this.array;
                if (i10 >= objArr.length) {
                    return;
                }
                objArr[i10] = supplier.get();
                i10++;
            }
        }
    }

    @VisibleForTesting
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        public LargeLazyStriped(int i9, Supplier<L> supplier) {
            super(i9);
            int i10 = this.mask;
            this.size = i10 == -1 ? Integer.MAX_VALUE : i10 + 1;
            this.supplier = supplier;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i9) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i9, size());
            }
            L l9 = this.locks.get(Integer.valueOf(i9));
            if (l9 != null) {
                return l9;
            }
            L l10 = this.supplier.get();
            return (L) MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(i9), l10), l10);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }
    }

    public static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        public PaddedLock() {
            super(false);
        }
    }

    public static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        public PaddedSemaphore(int i9) {
            super(i9, false);
        }
    }

    public static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        public PowerOfTwoStriped(int i9) {
            super();
            Preconditions.checkArgument(i9 > 0, "Stripes must be positive");
            this.mask = i9 > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(i9) - 1;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object obj) {
            return getAt(indexFor(obj));
        }

        @Override // com.google.common.util.concurrent.Striped
        public final int indexFor(Object obj) {
            return Striped.smear(obj.hashCode()) & this.mask;
        }
    }

    @VisibleForTesting
    public static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue;
        final int size;
        final Supplier<L> supplier;

        public static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            public ArrayReference(L l9, int i9, ReferenceQueue<L> referenceQueue) {
                super(l9, referenceQueue);
                this.index = i9;
            }
        }

        public SmallLazyStriped(int i9, Supplier<L> supplier) {
            super(i9);
            this.queue = new ReferenceQueue<>();
            int i10 = this.mask;
            int i11 = i10 == -1 ? Integer.MAX_VALUE : i10 + 1;
            this.size = i11;
            this.locks = new AtomicReferenceArray<>(i11);
            this.supplier = supplier;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> referencePoll = this.queue.poll();
                if (referencePoll == null) {
                    return;
                }
                ArrayReference arrayReference = (ArrayReference) referencePoll;
                C4004b.m17714a(this.locks, arrayReference.index, arrayReference, null);
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i9) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i9, size());
            }
            ArrayReference<? extends L> arrayReference = this.locks.get(i9);
            L l9 = arrayReference == null ? null : arrayReference.get();
            if (l9 != null) {
                return l9;
            }
            L l10 = this.supplier.get();
            ArrayReference arrayReference2 = new ArrayReference(l10, i9, this.queue);
            while (!C4004b.m17714a(this.locks, i9, arrayReference, arrayReference2)) {
                arrayReference = this.locks.get(i9);
                L l11 = arrayReference == null ? null : arrayReference.get();
                if (l11 != null) {
                    return l11;
                }
            }
            drainQueue();
            return l10;
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }
    }

    private Striped() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int i9) {
        return 1 << IntMath.log2(i9, RoundingMode.CEILING);
    }

    private static <L> Striped<L> lazy(int i9, Supplier<L> supplier) {
        return i9 < 1024 ? new SmallLazyStriped(i9, supplier) : new LargeLazyStriped(i9, supplier);
    }

    public static Striped<Lock> lazyWeakLock(int i9) {
        return lazy(i9, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.2
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i9) {
        return lazy(i9, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i9, final int i10) {
        return lazy(i9, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.4
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new Semaphore(i10, false);
            }
        });
    }

    public static Striped<Lock> lock(int i9) {
        return new CompactStriped(i9, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.1
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int i9) {
        return new CompactStriped(i9, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<Semaphore> semaphore(int i9, final int i10) {
        return new CompactStriped(i9, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.3
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new PaddedSemaphore(i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int smear(int i9) {
        int i10 = i9 ^ ((i9 >>> 20) ^ (i9 >>> 12));
        return (i10 >>> 4) ^ ((i10 >>> 7) ^ i10);
    }

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] array = Iterables.toArray(iterable, Object.class);
        if (array.length == 0) {
            return ImmutableList.m17573of();
        }
        int[] iArr = new int[array.length];
        for (int i9 = 0; i9 < array.length; i9++) {
            iArr[i9] = indexFor(array[i9]);
        }
        Arrays.sort(iArr);
        int i10 = iArr[0];
        array[0] = getAt(i10);
        for (int i11 = 1; i11 < array.length; i11++) {
            int i12 = iArr[i11];
            if (i12 == i10) {
                array[i11] = array[i11 - 1];
            } else {
                array[i11] = getAt(i12);
                i10 = i12;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i9);

    public abstract int indexFor(Object obj);

    public abstract int size();
}
