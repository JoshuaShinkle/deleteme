package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.util.Assertions;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] bArr) {
        this.data = bArr;
        this.byteLimit = bArr.length;
    }

    private void assertValidOffset() {
        int i9;
        int i10 = this.byteOffset;
        Assertions.checkState(i10 >= 0 && (i10 < (i9 = this.byteLimit) || (i10 == i9 && this.bitOffset == 0)));
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public boolean readBit() {
        boolean z8 = (((this.data[this.byteOffset] & UnsignedBytes.MAX_VALUE) >> this.bitOffset) & 1) == 1;
        skipBits(1);
        return z8;
    }

    public int readBits(int i9) {
        int i10 = this.byteOffset;
        int iMin = Math.min(i9, 8 - this.bitOffset);
        int i11 = i10 + 1;
        int i12 = ((this.data[i10] & UnsignedBytes.MAX_VALUE) >> this.bitOffset) & (255 >> (8 - iMin));
        while (iMin < i9) {
            i12 |= (this.data[i11] & UnsignedBytes.MAX_VALUE) << iMin;
            iMin += 8;
            i11++;
        }
        int i13 = i12 & ((-1) >>> (32 - i9));
        skipBits(i9);
        return i13;
    }

    public void reset() {
        this.byteOffset = 0;
        this.bitOffset = 0;
    }

    public void setPosition(int i9) {
        int i10 = i9 / 8;
        this.byteOffset = i10;
        this.bitOffset = i9 - (i10 * 8);
        assertValidOffset();
    }

    public void skipBits(int i9) {
        int i10 = i9 / 8;
        int i11 = this.byteOffset + i10;
        this.byteOffset = i11;
        int i12 = this.bitOffset + (i9 - (i10 * 8));
        this.bitOffset = i12;
        if (i12 > 7) {
            this.byteOffset = i11 + 1;
            this.bitOffset = i12 - 8;
        }
        assertValidOffset();
    }
}
