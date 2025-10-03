package com.google.zxing.common;

import java.util.Arrays;

/* loaded from: classes2.dex */
public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(int i9) {
        this(i9, i9);
    }

    public static BitMatrix parse(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        boolean[] zArr = new boolean[str.length()];
        int i9 = -1;
        int length = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (length < str.length()) {
            if (str.charAt(length) == '\n' || str.charAt(length) == '\r') {
                if (i10 > i11) {
                    if (i9 == -1) {
                        i9 = i10 - i11;
                    } else if (i10 - i11 != i9) {
                        throw new IllegalArgumentException("row lengths do not match");
                    }
                    i12++;
                    i11 = i10;
                }
                length++;
            } else {
                if (str.substring(length, str2.length() + length).equals(str2)) {
                    length += str2.length();
                    zArr[i10] = true;
                } else {
                    if (!str.substring(length, str3.length() + length).equals(str3)) {
                        throw new IllegalArgumentException("illegal character encountered: " + str.substring(length));
                    }
                    length += str3.length();
                    zArr[i10] = false;
                }
                i10++;
            }
        }
        if (i10 > i11) {
            if (i9 == -1) {
                i9 = i10 - i11;
            } else if (i10 - i11 != i9) {
                throw new IllegalArgumentException("row lengths do not match");
            }
            i12++;
        }
        BitMatrix bitMatrix = new BitMatrix(i9, i12);
        for (int i13 = 0; i13 < i10; i13++) {
            if (zArr[i13]) {
                bitMatrix.set(i13 % i9, i13 / i9);
            }
        }
        return bitMatrix;
    }

    public void clear() {
        int length = this.bits.length;
        for (int i9 = 0; i9 < length; i9++) {
            this.bits[i9] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        return this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits);
    }

    public void flip(int i9, int i10) {
        int i11 = (i10 * this.rowSize) + (i9 / 32);
        int[] iArr = this.bits;
        iArr[i11] = (1 << (i9 & 31)) ^ iArr[i11];
    }

    public boolean get(int i9, int i10) {
        return ((this.bits[(i10 * this.rowSize) + (i9 / 32)] >>> (i9 & 31)) & 1) != 0;
    }

    public int[] getBottomRightOnBit() {
        int length = this.bits.length - 1;
        while (length >= 0 && this.bits[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i9 = this.rowSize;
        int i10 = length / i9;
        int i11 = (length % i9) * 32;
        int i12 = 31;
        while ((this.bits[length] >>> i12) == 0) {
            i12--;
        }
        return new int[]{i11 + i12, i10};
    }

    public int[] getEnclosingRectangle() {
        int i9 = this.width;
        int i10 = this.height;
        int i11 = -1;
        int i12 = -1;
        for (int i13 = 0; i13 < this.height; i13++) {
            int i14 = 0;
            while (true) {
                int i15 = this.rowSize;
                if (i14 < i15) {
                    int i16 = this.bits[(i15 * i13) + i14];
                    if (i16 != 0) {
                        if (i13 < i10) {
                            i10 = i13;
                        }
                        if (i13 > i12) {
                            i12 = i13;
                        }
                        int i17 = i14 * 32;
                        if (i17 < i9) {
                            int i18 = 0;
                            while ((i16 << (31 - i18)) == 0) {
                                i18++;
                            }
                            int i19 = i18 + i17;
                            if (i19 < i9) {
                                i9 = i19;
                            }
                        }
                        if (i17 + 31 > i11) {
                            int i20 = 31;
                            while ((i16 >>> i20) == 0) {
                                i20--;
                            }
                            int i21 = i17 + i20;
                            if (i21 > i11) {
                                i11 = i21;
                            }
                        }
                    }
                    i14++;
                }
            }
        }
        int i22 = i11 - i9;
        int i23 = i12 - i10;
        if (i22 < 0 || i23 < 0) {
            return null;
        }
        return new int[]{i9, i10, i22, i23};
    }

    public int getHeight() {
        return this.height;
    }

    public BitArray getRow(int i9, BitArray bitArray) {
        if (bitArray == null || bitArray.getSize() < this.width) {
            bitArray = new BitArray(this.width);
        } else {
            bitArray.clear();
        }
        int i10 = i9 * this.rowSize;
        for (int i11 = 0; i11 < this.rowSize; i11++) {
            bitArray.setBulk(i11 * 32, this.bits[i10 + i11]);
        }
        return bitArray;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int i9 = 0;
        while (true) {
            iArr = this.bits;
            if (i9 >= iArr.length || iArr[i9] != 0) {
                break;
            }
            i9++;
        }
        if (i9 == iArr.length) {
            return null;
        }
        int i10 = this.rowSize;
        int i11 = i9 / i10;
        int i12 = (i9 % i10) * 32;
        int i13 = iArr[i9];
        int i14 = 0;
        while ((i13 << (31 - i14)) == 0) {
            i14++;
        }
        return new int[]{i12 + i14, i11};
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i9 = this.width;
        return (((((((i9 * 31) + i9) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public void rotate180() {
        int width = getWidth();
        int height = getHeight();
        BitArray bitArray = new BitArray(width);
        BitArray bitArray2 = new BitArray(width);
        for (int i9 = 0; i9 < (height + 1) / 2; i9++) {
            bitArray = getRow(i9, bitArray);
            int i10 = (height - 1) - i9;
            bitArray2 = getRow(i10, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(i9, bitArray2);
            setRow(i10, bitArray);
        }
    }

    public void set(int i9, int i10) {
        int i11 = (i10 * this.rowSize) + (i9 / 32);
        int[] iArr = this.bits;
        iArr[i11] = (1 << (i9 & 31)) | iArr[i11];
    }

    public void setRegion(int i9, int i10, int i11, int i12) {
        if (i10 < 0 || i9 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i12 < 1 || i11 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i13 = i11 + i9;
        int i14 = i12 + i10;
        if (i14 > this.height || i13 > this.width) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i10 < i14) {
            int i15 = this.rowSize * i10;
            for (int i16 = i9; i16 < i13; i16++) {
                int[] iArr = this.bits;
                int i17 = (i16 / 32) + i15;
                iArr[i17] = iArr[i17] | (1 << (i16 & 31));
            }
            i10++;
        }
    }

    public void setRow(int i9, BitArray bitArray) {
        int[] bitArray2 = bitArray.getBitArray();
        int[] iArr = this.bits;
        int i10 = this.rowSize;
        System.arraycopy(bitArray2, 0, iArr, i9 * i10, i10);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public void unset(int i9, int i10) {
        int i11 = (i10 * this.rowSize) + (i9 / 32);
        int[] iArr = this.bits;
        iArr[i11] = (~(1 << (i9 & 31))) & iArr[i11];
    }

    public void xor(BitMatrix bitMatrix) {
        if (this.width != bitMatrix.getWidth() || this.height != bitMatrix.getHeight() || this.rowSize != bitMatrix.getRowSize()) {
            throw new IllegalArgumentException("input matrix dimensions do not match");
        }
        BitArray bitArray = new BitArray((this.width / 32) + 1);
        for (int i9 = 0; i9 < this.height; i9++) {
            int i10 = this.rowSize * i9;
            int[] bitArray2 = bitMatrix.getRow(i9, bitArray).getBitArray();
            for (int i11 = 0; i11 < this.rowSize; i11++) {
                int[] iArr = this.bits;
                int i12 = i10 + i11;
                iArr[i12] = iArr[i12] ^ bitArray2[i11];
            }
        }
    }

    public BitMatrix(int i9, int i10) {
        if (i9 < 1 || i10 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i9;
        this.height = i10;
        int i11 = (i9 + 31) / 32;
        this.rowSize = i11;
        this.bits = new int[i11 * i10];
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitMatrix m25576clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }

    public String toString(String str, String str2) {
        return toString(str, str2, "\n");
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.height * (this.width + 1));
        for (int i9 = 0; i9 < this.height; i9++) {
            for (int i10 = 0; i10 < this.width; i10++) {
                sb.append(get(i10, i9) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    private BitMatrix(int i9, int i10, int i11, int[] iArr) {
        this.width = i9;
        this.height = i10;
        this.rowSize = i11;
        this.bits = iArr;
    }
}
