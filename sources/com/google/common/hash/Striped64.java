package com.google.common.hash;

import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class Striped64 extends Number {
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    static final Random rng = new Random();
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    public static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;

        /* renamed from: p0 */
        volatile long f15444p0;

        /* renamed from: p1 */
        volatile long f15445p1;

        /* renamed from: p2 */
        volatile long f15446p2;

        /* renamed from: p3 */
        volatile long f15447p3;

        /* renamed from: p4 */
        volatile long f15448p4;

        /* renamed from: p5 */
        volatile long f15449p5;

        /* renamed from: p6 */
        volatile long f15450p6;

        /* renamed from: q0 */
        volatile long f15451q0;

        /* renamed from: q1 */
        volatile long f15452q1;

        /* renamed from: q2 */
        volatile long f15453q2;

        /* renamed from: q3 */
        volatile long f15454q3;

        /* renamed from: q4 */
        volatile long f15455q4;

        /* renamed from: q5 */
        volatile long f15456q5;

        /* renamed from: q6 */
        volatile long f15457q6;
        volatile long value;

        static {
            try {
                Unsafe unsafe = Striped64.getUnsafe();
                UNSAFE = unsafe;
                valueOffset = unsafe.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e9) {
                throw new Error(e9);
            }
        }

        public Cell(long j9) {
            this.value = j9;
        }

        public final boolean cas(long j9, long j10) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j9, j10);
        }
    }

    static {
        try {
            Unsafe unsafe = getUnsafe();
            UNSAFE = unsafe;
            baseOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            busyOffset = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e9) {
            throw new Error(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (PrivilegedActionException e9) {
                throw new RuntimeException("Could not initialize intrinsics", e9.getCause());
            }
        } catch (SecurityException unused) {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.hash.Striped64.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws IllegalAccessException, SecurityException, IllegalArgumentException {
                    for (Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }
            });
        }
    }

    final boolean casBase(long j9, long j10) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j9, j10);
    }

    final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* renamed from: fn */
    abstract long mo17677fn(long j9, long j10);

    final void internalReset(long j9) {
        Cell[] cellArr = this.cells;
        this.base = j9;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j9;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ee A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void retryUpdate(long j9, int[] iArr, boolean z8) {
        int iNextInt;
        int[] iArr2;
        boolean z9;
        int length;
        boolean z10;
        int length2;
        if (iArr == null) {
            iArr2 = new int[1];
            threadHashCode.set(iArr2);
            iNextInt = rng.nextInt();
            if (iNextInt == 0) {
                iNextInt = 1;
            }
            iArr2[0] = iNextInt;
        } else {
            iNextInt = iArr[0];
            iArr2 = iArr;
        }
        boolean z11 = false;
        int i9 = iNextInt;
        boolean z12 = z8;
        while (true) {
            Cell[] cellArr = this.cells;
            if (cellArr != null && (length = cellArr.length) > 0) {
                Cell cell = cellArr[(length - 1) & i9];
                if (cell == null) {
                    if (this.busy == 0) {
                        Cell cell2 = new Cell(j9);
                        if (this.busy == 0 && casBusy()) {
                            try {
                                Cell[] cellArr2 = this.cells;
                                if (cellArr2 == null || (length2 = cellArr2.length) <= 0) {
                                    z10 = false;
                                    if (!z10) {
                                        return;
                                    }
                                } else {
                                    int i10 = (length2 - 1) & i9;
                                    if (cellArr2[i10] == null) {
                                        cellArr2[i10] = cell2;
                                        z10 = true;
                                    }
                                    if (!z10) {
                                    }
                                }
                            } finally {
                            }
                        }
                    }
                    z11 = false;
                } else if (z12) {
                    long j10 = cell.value;
                    if (cell.cas(j10, mo17677fn(j10, j9))) {
                        return;
                    }
                    if (length >= NCPU || this.cells != cellArr) {
                        z11 = false;
                    } else if (!z11) {
                        z11 = true;
                    } else if (this.busy == 0 && casBusy()) {
                        try {
                            if (this.cells == cellArr) {
                                Cell[] cellArr3 = new Cell[length << 1];
                                for (int i11 = 0; i11 < length; i11++) {
                                    cellArr3[i11] = cellArr[i11];
                                }
                                this.cells = cellArr3;
                            }
                            this.busy = 0;
                            z11 = false;
                        } finally {
                        }
                    }
                } else {
                    z12 = true;
                }
                int i12 = i9 ^ (i9 << 13);
                int i13 = i12 ^ (i12 >>> 17);
                i9 = i13 ^ (i13 << 5);
                iArr2[0] = i9;
            } else if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                try {
                    if (this.cells == cellArr) {
                        Cell[] cellArr4 = new Cell[2];
                        cellArr4[i9 & 1] = new Cell(j9);
                        this.cells = cellArr4;
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    if (z9) {
                        return;
                    }
                } finally {
                }
            } else {
                long j11 = this.base;
                if (casBase(j11, mo17677fn(j11, j9))) {
                    return;
                }
            }
        }
    }
}
