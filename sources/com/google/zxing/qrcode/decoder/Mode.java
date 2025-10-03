package com.google.zxing.qrcode.decoder;

/* loaded from: classes2.dex */
public enum Mode {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);

    private final int bits;
    private final int[] characterCountBitsForVersions;

    Mode(int[] iArr, int i9) {
        this.characterCountBitsForVersions = iArr;
        this.bits = i9;
    }

    public static Mode forBits(int i9) {
        if (i9 == 0) {
            return TERMINATOR;
        }
        if (i9 == 1) {
            return NUMERIC;
        }
        if (i9 == 2) {
            return ALPHANUMERIC;
        }
        if (i9 == 3) {
            return STRUCTURED_APPEND;
        }
        if (i9 == 4) {
            return BYTE;
        }
        if (i9 == 5) {
            return FNC1_FIRST_POSITION;
        }
        if (i9 == 7) {
            return ECI;
        }
        if (i9 == 8) {
            return KANJI;
        }
        if (i9 == 9) {
            return FNC1_SECOND_POSITION;
        }
        if (i9 == 13) {
            return HANZI;
        }
        throw new IllegalArgumentException();
    }

    public int getBits() {
        return this.bits;
    }

    public int getCharacterCountBits(Version version) {
        int versionNumber = version.getVersionNumber();
        return this.characterCountBitsForVersions[versionNumber <= 9 ? (char) 0 : versionNumber <= 26 ? (char) 1 : (char) 2];
    }
}
