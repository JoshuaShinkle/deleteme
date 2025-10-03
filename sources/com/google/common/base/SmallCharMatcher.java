package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;

@GwtIncompatible
/* loaded from: classes2.dex */
final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {

    /* renamed from: C1 */
    private static final int f15398C1 = -862048943;

    /* renamed from: C2 */
    private static final int f15399C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5d;
    static final int MAX_SIZE = 1023;
    private final boolean containsZero;
    private final long filter;
    private final char[] table;

    private SmallCharMatcher(char[] cArr, long j9, boolean z8, String str) {
        super(str);
        this.table = cArr;
        this.filter = j9;
        this.containsZero = z8;
    }

    private boolean checkFilter(int i9) {
        return 1 == ((this.filter >> i9) & 1);
    }

    @VisibleForTesting
    public static int chooseTableSize(int i9) {
        if (i9 == 1) {
            return 2;
        }
        int iHighestOneBit = Integer.highestOneBit(i9 - 1) << 1;
        while (iHighestOneBit * DESIRED_LOAD_FACTOR < i9) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static CharMatcher from(BitSet bitSet, String str) {
        int i9;
        int iCardinality = bitSet.cardinality();
        boolean z8 = bitSet.get(0);
        int iChooseTableSize = chooseTableSize(iCardinality);
        char[] cArr = new char[iChooseTableSize];
        int i10 = iChooseTableSize - 1;
        int iNextSetBit = bitSet.nextSetBit(0);
        long j9 = 0;
        while (iNextSetBit != -1) {
            long j10 = (1 << iNextSetBit) | j9;
            int iSmear = smear(iNextSetBit);
            while (true) {
                i9 = iSmear & i10;
                if (cArr[i9] == 0) {
                    break;
                }
                iSmear = i9 + 1;
            }
            cArr[i9] = (char) iNextSetBit;
            iNextSetBit = bitSet.nextSetBit(iNextSetBit + 1);
            j9 = j10;
        }
        return new SmallCharMatcher(cArr, j9, z8, str);
    }

    public static int smear(int i9) {
        return Integer.rotateLeft(i9 * f15398C1, 15) * f15399C2;
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c9) {
        if (c9 == 0) {
            return this.containsZero;
        }
        if (!checkFilter(c9)) {
            return false;
        }
        int length = this.table.length - 1;
        int iSmear = smear(c9) & length;
        int i9 = iSmear;
        do {
            char c10 = this.table[i9];
            if (c10 == 0) {
                return false;
            }
            if (c10 == c9) {
                return true;
            }
            i9 = (i9 + 1) & length;
        } while (i9 != iSmear);
        return false;
    }

    @Override // com.google.common.base.CharMatcher
    public void setBits(BitSet bitSet) {
        if (this.containsZero) {
            bitSet.set(0);
        }
        for (char c9 : this.table) {
            if (c9 != 0) {
                bitSet.set(c9);
            }
        }
    }
}
