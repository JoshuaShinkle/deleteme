package com.google.zxing.common;

import java.util.Arrays;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    private void ensureCapacity(int i9) {
        if (i9 > this.bits.length * 32) {
            int[] iArrMakeArray = makeArray(i9);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, iArrMakeArray, 0, iArr.length);
            this.bits = iArrMakeArray;
        }
    }

    private static int[] makeArray(int i9) {
        return new int[(i9 + 31) / 32];
    }

    public void appendBit(boolean z8) {
        ensureCapacity(this.size + 1);
        if (z8) {
            int[] iArr = this.bits;
            int i9 = this.size;
            int i10 = i9 / 32;
            iArr[i10] = (1 << (i9 & 31)) | iArr[i10];
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i9 = bitArray.size;
        ensureCapacity(this.size + i9);
        for (int i10 = 0; i10 < i9; i10++) {
            appendBit(bitArray.get(i10));
        }
    }

    public void appendBits(int i9, int i10) {
        if (i10 < 0 || i10 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i10);
        while (i10 > 0) {
            boolean z8 = true;
            if (((i9 >> (i10 - 1)) & 1) != 1) {
                z8 = false;
            }
            appendBit(z8);
            i10--;
        }
    }

    public void clear() {
        int length = this.bits.length;
        for (int i9 = 0; i9 < length; i9++) {
            this.bits[i9] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        return this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits);
    }

    public void flip(int i9) {
        int[] iArr = this.bits;
        int i10 = i9 / 32;
        iArr[i10] = (1 << (i9 & 31)) ^ iArr[i10];
    }

    public boolean get(int i9) {
        return ((1 << (i9 & 31)) & this.bits[i9 / 32]) != 0;
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public int getNextSet(int i9) {
        int i10 = this.size;
        if (i9 >= i10) {
            return i10;
        }
        int i11 = i9 / 32;
        int i12 = (~((1 << (i9 & 31)) - 1)) & this.bits[i11];
        while (i12 == 0) {
            i11++;
            int[] iArr = this.bits;
            if (i11 == iArr.length) {
                return this.size;
            }
            i12 = iArr[i11];
        }
        int iNumberOfTrailingZeros = (i11 * 32) + Integer.numberOfTrailingZeros(i12);
        int i13 = this.size;
        return iNumberOfTrailingZeros > i13 ? i13 : iNumberOfTrailingZeros;
    }

    public int getNextUnset(int i9) {
        int i10 = this.size;
        if (i9 >= i10) {
            return i10;
        }
        int i11 = i9 / 32;
        int i12 = (~((1 << (i9 & 31)) - 1)) & (~this.bits[i11]);
        while (i12 == 0) {
            i11++;
            int[] iArr = this.bits;
            if (i11 == iArr.length) {
                return this.size;
            }
            i12 = ~iArr[i11];
        }
        int iNumberOfTrailingZeros = (i11 * 32) + Integer.numberOfTrailingZeros(i12);
        int i13 = this.size;
        return iNumberOfTrailingZeros > i13 ? i13 : iNumberOfTrailingZeros;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public boolean isRange(int i9, int i10, boolean z8) {
        int i11;
        if (i10 < i9) {
            throw new IllegalArgumentException();
        }
        if (i10 == i9) {
            return true;
        }
        int i12 = i10 - 1;
        int i13 = i9 / 32;
        int i14 = i12 / 32;
        int i15 = i13;
        while (i15 <= i14) {
            int i16 = i15 > i13 ? 0 : i9 & 31;
            int i17 = i15 < i14 ? 31 : i12 & 31;
            if (i16 == 0 && i17 == 31) {
                i11 = -1;
            } else {
                i11 = 0;
                while (i16 <= i17) {
                    i11 |= 1 << i16;
                    i16++;
                }
            }
            int i18 = this.bits[i15] & i11;
            if (!z8) {
                i11 = 0;
            }
            if (i18 != i11) {
                return false;
            }
            i15++;
        }
        return true;
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i9 = (this.size - 1) / 32;
        int i10 = i9 + 1;
        for (int i11 = 0; i11 < i10; i11++) {
            long j9 = this.bits[i11];
            long j10 = ((j9 & 1431655765) << 1) | ((j9 >> 1) & 1431655765);
            long j11 = ((j10 & 858993459) << 2) | ((j10 >> 2) & 858993459);
            long j12 = ((j11 & 252645135) << 4) | ((j11 >> 4) & 252645135);
            long j13 = ((j12 & 16711935) << 8) | ((j12 >> 8) & 16711935);
            iArr[i9 - i11] = (int) (((j13 & 65535) << 16) | ((j13 >> 16) & 65535));
        }
        int i12 = this.size;
        int i13 = i10 * 32;
        if (i12 != i13) {
            int i14 = i13 - i12;
            int i15 = 1;
            for (int i16 = 0; i16 < 31 - i14; i16++) {
                i15 = (i15 << 1) | 1;
            }
            int i17 = (iArr[0] >> i14) & i15;
            for (int i18 = 1; i18 < i10; i18++) {
                int i19 = iArr[i18];
                iArr[i18 - 1] = i17 | (i19 << (32 - i14));
                i17 = (i19 >> i14) & i15;
            }
            iArr[i10 - 1] = i17;
        }
        this.bits = iArr;
    }

    public void set(int i9) {
        int[] iArr = this.bits;
        int i10 = i9 / 32;
        iArr[i10] = (1 << (i9 & 31)) | iArr[i10];
    }

    public void setBulk(int i9, int i10) {
        this.bits[i9 / 32] = i10;
    }

    public void setRange(int i9, int i10) {
        if (i10 < i9) {
            throw new IllegalArgumentException();
        }
        if (i10 == i9) {
            return;
        }
        int i11 = i10 - 1;
        int i12 = i9 / 32;
        int i13 = i11 / 32;
        int i14 = i12;
        while (i14 <= i13) {
            int i15 = 0;
            int i16 = i14 > i12 ? 0 : i9 & 31;
            int i17 = i14 < i13 ? 31 : i11 & 31;
            if (i16 == 0 && i17 == 31) {
                i15 = -1;
            } else {
                while (i16 <= i17) {
                    i15 |= 1 << i16;
                    i16++;
                }
            }
            int[] iArr = this.bits;
            iArr[i14] = i15 | iArr[i14];
            i14++;
        }
    }

    public void toBytes(int i9, byte[] bArr, int i10, int i11) {
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = 0;
            for (int i14 = 0; i14 < 8; i14++) {
                if (get(i9)) {
                    i13 |= 1 << (7 - i14);
                }
                i9++;
            }
            bArr[i10 + i12] = (byte) i13;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        for (int i9 = 0; i9 < this.size; i9++) {
            if ((i9 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i9) ? 'X' : ClassUtils.PACKAGE_SEPARATOR_CHAR);
        }
        return sb.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.bits.length != bitArray.bits.length) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i9 = 0;
        while (true) {
            int[] iArr = this.bits;
            if (i9 >= iArr.length) {
                return;
            }
            iArr[i9] = iArr[i9] ^ bitArray.bits[i9];
            i9++;
        }
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitArray m25575clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public BitArray(int i9) {
        this.size = i9;
        this.bits = makeArray(i9);
    }

    public BitArray(int[] iArr, int i9) {
        this.bits = iArr;
        this.size = i9;
    }
}
