package com.google.android.exoplayer2.util;

import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int i9, int i10) {
        reset(bArr, i9, i10);
    }

    private void assertValidOffset() {
        int i9;
        int i10 = this.byteOffset;
        Assertions.checkState(i10 >= 0 && (i10 < (i9 = this.byteLimit) || (i10 == i9 && this.bitOffset == 0)));
    }

    private int readExpGolombCodeNum() {
        int i9 = 0;
        while (!readBit()) {
            i9++;
        }
        return ((1 << i9) - 1) + (i9 > 0 ? readBits(i9) : 0);
    }

    private boolean shouldSkipByte(int i9) {
        if (2 <= i9 && i9 < this.byteLimit) {
            byte[] bArr = this.data;
            if (bArr[i9] == 3 && bArr[i9 - 2] == 0 && bArr[i9 - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean canReadBits(int i9) {
        int i10 = this.byteOffset;
        int i11 = i9 / 8;
        int i12 = i10 + i11;
        int i13 = (this.bitOffset + i9) - (i11 * 8);
        if (i13 > 7) {
            i12++;
            i13 -= 8;
        }
        while (true) {
            i10++;
            if (i10 > i12 || i12 >= this.byteLimit) {
                break;
            }
            if (shouldSkipByte(i10)) {
                i12++;
                i10 += 2;
            }
        }
        int i14 = this.byteLimit;
        if (i12 >= i14) {
            return i12 == i14 && i13 == 0;
        }
        return true;
    }

    public boolean canReadExpGolombCodedNum() {
        int i9 = this.byteOffset;
        int i10 = this.bitOffset;
        int i11 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i11++;
        }
        boolean z8 = this.byteOffset == this.byteLimit;
        this.byteOffset = i9;
        this.bitOffset = i10;
        return !z8 && canReadBits((i11 * 2) + 1);
    }

    public boolean readBit() {
        boolean z8 = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z8;
    }

    public int readBits(int i9) {
        int i10;
        this.bitOffset += i9;
        int i11 = 0;
        while (true) {
            i10 = this.bitOffset;
            if (i10 <= 8) {
                break;
            }
            int i12 = i10 - 8;
            this.bitOffset = i12;
            byte[] bArr = this.data;
            int i13 = this.byteOffset;
            i11 |= (bArr[i13] & UnsignedBytes.MAX_VALUE) << i12;
            if (!shouldSkipByte(i13 + 1)) {
                i = 1;
            }
            this.byteOffset = i13 + i;
        }
        byte[] bArr2 = this.data;
        int i14 = this.byteOffset;
        int i15 = ((-1) >>> (32 - i9)) & (i11 | ((bArr2[i14] & UnsignedBytes.MAX_VALUE) >> (8 - i10)));
        if (i10 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i14 + (shouldSkipByte(i14 + 1) ? 2 : 1);
        }
        assertValidOffset();
        return i15;
    }

    public int readSignedExpGolombCodedInt() {
        int expGolombCodeNum = readExpGolombCodeNum();
        return (expGolombCodeNum % 2 == 0 ? -1 : 1) * ((expGolombCodeNum + 1) / 2);
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public void reset(byte[] bArr, int i9, int i10) {
        this.data = bArr;
        this.byteOffset = i9;
        this.byteLimit = i10;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBit() {
        int i9 = this.bitOffset + 1;
        this.bitOffset = i9;
        if (i9 == 8) {
            this.bitOffset = 0;
            int i10 = this.byteOffset;
            this.byteOffset = i10 + (shouldSkipByte(i10 + 1) ? 2 : 1);
        }
        assertValidOffset();
    }

    public void skipBits(int i9) {
        int i10 = this.byteOffset;
        int i11 = i9 / 8;
        int i12 = i10 + i11;
        this.byteOffset = i12;
        int i13 = this.bitOffset + (i9 - (i11 * 8));
        this.bitOffset = i13;
        if (i13 > 7) {
            this.byteOffset = i12 + 1;
            this.bitOffset = i13 - 8;
        }
        while (true) {
            i10++;
            if (i10 > this.byteOffset) {
                assertValidOffset();
                return;
            } else if (shouldSkipByte(i10)) {
                this.byteOffset++;
                i10 += 2;
            }
        }
    }
}
