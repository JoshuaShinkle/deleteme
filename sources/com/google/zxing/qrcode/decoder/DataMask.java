package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

/* loaded from: classes2.dex */
abstract class DataMask {
    private static final DataMask[] DATA_MASKS;

    public static final class DataMask000 extends DataMask {
        private DataMask000() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return ((i9 + i10) & 1) == 0;
        }
    }

    public static final class DataMask001 extends DataMask {
        private DataMask001() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return (i9 & 1) == 0;
        }
    }

    public static final class DataMask010 extends DataMask {
        private DataMask010() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return i10 % 3 == 0;
        }
    }

    public static final class DataMask011 extends DataMask {
        private DataMask011() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return (i9 + i10) % 3 == 0;
        }
    }

    public static final class DataMask100 extends DataMask {
        private DataMask100() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return (((i9 / 2) + (i10 / 3)) & 1) == 0;
        }
    }

    public static final class DataMask101 extends DataMask {
        private DataMask101() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            int i11 = i9 * i10;
            return (i11 & 1) + (i11 % 3) == 0;
        }
    }

    public static final class DataMask110 extends DataMask {
        private DataMask110() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            int i11 = i9 * i10;
            return (((i11 & 1) + (i11 % 3)) & 1) == 0;
        }
    }

    public static final class DataMask111 extends DataMask {
        private DataMask111() {
            super();
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int i9, int i10) {
            return ((((i9 + i10) & 1) + ((i9 * i10) % 3)) & 1) == 0;
        }
    }

    static {
        DATA_MASKS = new DataMask[]{new DataMask000(), new DataMask001(), new DataMask010(), new DataMask011(), new DataMask100(), new DataMask101(), new DataMask110(), new DataMask111()};
    }

    public static DataMask forReference(int i9) {
        if (i9 < 0 || i9 > 7) {
            throw new IllegalArgumentException();
        }
        return DATA_MASKS[i9];
    }

    public abstract boolean isMasked(int i9, int i10);

    public final void unmaskBitMatrix(BitMatrix bitMatrix, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            for (int i11 = 0; i11 < i9; i11++) {
                if (isMasked(i10, i11)) {
                    bitMatrix.flip(i11, i10);
                }
            }
        }
    }

    private DataMask() {
    }
}
