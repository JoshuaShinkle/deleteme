package com.google.android.exoplayer2.util;

import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    private void assertValidOffset() {
        int i9;
        int i10 = this.byteOffset;
        Assertions.checkState(i10 >= 0 && (i10 < (i9 = this.byteLimit) || (i10 == i9 && this.bitOffset == 0)));
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public void byteAlign() {
        if (this.bitOffset == 0) {
            return;
        }
        this.bitOffset = 0;
        this.byteOffset++;
        assertValidOffset();
    }

    public int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void putInt(int i9, int i10) {
        if (i10 < 32) {
            i9 &= (1 << i10) - 1;
        }
        int iMin = Math.min(8 - this.bitOffset, i10);
        int i11 = this.bitOffset;
        int i12 = (8 - i11) - iMin;
        byte[] bArr = this.data;
        int i13 = this.byteOffset;
        byte b9 = (byte) (((65280 >> i11) | ((1 << i12) - 1)) & bArr[i13]);
        bArr[i13] = b9;
        int i14 = i10 - iMin;
        bArr[i13] = (byte) (b9 | ((i9 >>> i14) << i12));
        int i15 = i13 + 1;
        while (i14 > 8) {
            this.data[i15] = (byte) (i9 >>> (i14 - 8));
            i14 -= 8;
            i15++;
        }
        int i16 = 8 - i14;
        byte[] bArr2 = this.data;
        byte b10 = (byte) (bArr2[i15] & ((1 << i16) - 1));
        bArr2[i15] = b10;
        bArr2[i15] = (byte) (((i9 & ((1 << i14) - 1)) << i16) | b10);
        skipBits(i10);
        assertValidOffset();
    }

    public boolean readBit() {
        boolean z8 = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z8;
    }

    public int readBits(int i9) {
        int i10;
        if (i9 == 0) {
            return 0;
        }
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
            this.byteOffset = i13 + 1;
            i11 |= (bArr[i13] & UnsignedBytes.MAX_VALUE) << i12;
        }
        byte[] bArr2 = this.data;
        int i14 = this.byteOffset;
        int i15 = ((-1) >>> (32 - i9)) & (i11 | ((bArr2[i14] & UnsignedBytes.MAX_VALUE) >> (8 - i10)));
        if (i10 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i14 + 1;
        }
        assertValidOffset();
        return i15;
    }

    public void readBytes(byte[] bArr, int i9, int i10) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, i9, i10);
        this.byteOffset += i10;
        assertValidOffset();
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void setPosition(int i9) {
        int i10 = i9 / 8;
        this.byteOffset = i10;
        this.bitOffset = i9 - (i10 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int i9 = this.bitOffset + 1;
        this.bitOffset = i9;
        if (i9 == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
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

    public void skipBytes(int i9) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += i9;
        assertValidOffset();
    }

    public ParsableBitArray(byte[] bArr, int i9) {
        this.data = bArr;
        this.byteLimit = i9;
    }

    public void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.data, parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public void reset(byte[] bArr, int i9) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i9;
    }

    public void readBits(byte[] bArr, int i9, int i10) {
        int i11 = (i10 >> 3) + i9;
        while (i9 < i11) {
            byte[] bArr2 = this.data;
            int i12 = this.byteOffset;
            int i13 = i12 + 1;
            this.byteOffset = i13;
            byte b9 = bArr2[i12];
            int i14 = this.bitOffset;
            byte b10 = (byte) (b9 << i14);
            bArr[i9] = b10;
            bArr[i9] = (byte) (((255 & bArr2[i13]) >> (8 - i14)) | b10);
            i9++;
        }
        int i15 = i10 & 7;
        if (i15 == 0) {
            return;
        }
        byte b11 = (byte) (bArr[i11] & (255 >> i15));
        bArr[i11] = b11;
        int i16 = this.bitOffset;
        if (i16 + i15 > 8) {
            byte[] bArr3 = this.data;
            int i17 = this.byteOffset;
            this.byteOffset = i17 + 1;
            bArr[i11] = (byte) (b11 | ((byte) ((bArr3[i17] & UnsignedBytes.MAX_VALUE) << i16)));
            this.bitOffset = i16 - 8;
        }
        int i18 = this.bitOffset + i15;
        this.bitOffset = i18;
        byte[] bArr4 = this.data;
        int i19 = this.byteOffset;
        bArr[i11] = (byte) (((byte) (((255 & bArr4[i19]) >> (8 - i18)) << (8 - i15))) | bArr[i11]);
        if (i18 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i19 + 1;
        }
        assertValidOffset();
    }
}
