package com.google.zxing.common.reedsolomon;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

/* loaded from: classes2.dex */
public final class GenericGF {
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM;
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256;
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, UserMetadata.MAX_ATTRIBUTE_SIZE, 1);

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        AZTEC_PARAM = new GenericGF(19, 16, 1);
        QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int i9, int i10, int i11) {
        this.primitive = i9;
        this.size = i10;
        this.generatorBase = i11;
        this.expTable = new int[i10];
        this.logTable = new int[i10];
        int i12 = 1;
        for (int i13 = 0; i13 < i10; i13++) {
            this.expTable[i13] = i12;
            i12 *= 2;
            if (i12 >= i10) {
                i12 = (i12 ^ i9) & (i10 - 1);
            }
        }
        for (int i14 = 0; i14 < i10 - 1; i14++) {
            this.logTable[this.expTable[i14]] = i14;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    public static int addOrSubtract(int i9, int i10) {
        return i9 ^ i10;
    }

    public GenericGFPoly buildMonomial(int i9, int i10) {
        if (i9 < 0) {
            throw new IllegalArgumentException();
        }
        if (i10 == 0) {
            return this.zero;
        }
        int[] iArr = new int[i9 + 1];
        iArr[0] = i10;
        return new GenericGFPoly(this, iArr);
    }

    public int exp(int i9) {
        return this.expTable[i9];
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public GenericGFPoly getOne() {
        return this.one;
    }

    public int getSize() {
        return this.size;
    }

    public GenericGFPoly getZero() {
        return this.zero;
    }

    public int inverse(int i9) {
        if (i9 != 0) {
            return this.expTable[(this.size - this.logTable[i9]) - 1];
        }
        throw new ArithmeticException();
    }

    public int log(int i9) {
        if (i9 != 0) {
            return this.logTable[i9];
        }
        throw new IllegalArgumentException();
    }

    public int multiply(int i9, int i10) {
        if (i9 == 0 || i10 == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[i9] + iArr2[i10]) % (this.size - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.primitive) + ',' + this.size + ')';
    }
}
