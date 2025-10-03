package com.google.zxing.common;

import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int i9) {
        if (i9 < 1 || i9 > 32 || i9 > available()) {
            throw new IllegalArgumentException(String.valueOf(i9));
        }
        int i10 = this.bitOffset;
        int i11 = 0;
        if (i10 > 0) {
            int i12 = 8 - i10;
            int i13 = i9 < i12 ? i9 : i12;
            int i14 = i12 - i13;
            byte[] bArr = this.bytes;
            int i15 = this.byteOffset;
            int i16 = (((255 >> (8 - i13)) << i14) & bArr[i15]) >> i14;
            i9 -= i13;
            int i17 = i10 + i13;
            this.bitOffset = i17;
            if (i17 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i15 + 1;
            }
            i11 = i16;
        }
        if (i9 <= 0) {
            return i11;
        }
        while (i9 >= 8) {
            int i18 = i11 << 8;
            byte[] bArr2 = this.bytes;
            int i19 = this.byteOffset;
            i11 = (bArr2[i19] & UnsignedBytes.MAX_VALUE) | i18;
            this.byteOffset = i19 + 1;
            i9 -= 8;
        }
        if (i9 <= 0) {
            return i11;
        }
        int i20 = 8 - i9;
        int i21 = (i11 << i9) | ((((255 >> i20) << i20) & this.bytes[this.byteOffset]) >> i20);
        this.bitOffset += i9;
        return i21;
    }
}
