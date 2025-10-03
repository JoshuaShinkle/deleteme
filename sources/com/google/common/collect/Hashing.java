package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
final class Hashing {

    /* renamed from: C1 */
    private static final long f15418C1 = -862048943;

    /* renamed from: C2 */
    private static final long f15419C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    private Hashing() {
    }

    public static int closedTableSize(int i9, double d9) {
        int iMax = Math.max(i9, 2);
        int iHighestOneBit = Integer.highestOneBit(iMax);
        if (iMax <= ((int) (d9 * iHighestOneBit))) {
            return iHighestOneBit;
        }
        int i10 = iHighestOneBit << 1;
        if (i10 > 0) {
            return i10;
        }
        return 1073741824;
    }

    public static boolean needsResizing(int i9, int i10, double d9) {
        return ((double) i9) > d9 * ((double) i10) && i10 < 1073741824;
    }

    public static int smear(int i9) {
        return (int) (Integer.rotateLeft((int) (i9 * f15418C1), 15) * f15419C2);
    }

    public static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }
}
