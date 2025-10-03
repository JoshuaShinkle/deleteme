package com.google.zxing.qrcode.decoder;

/* loaded from: classes2.dex */
final class FormatInformation {
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private static final int FORMAT_INFO_MASK_QR = 21522;
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = {new int[]{FORMAT_INFO_MASK_QR, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    private static final int[] BITS_SET_IN_HALF_BYTE = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    private FormatInformation(int i9) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((i9 >> 3) & 3);
        this.dataMask = (byte) (i9 & 7);
    }

    public static FormatInformation decodeFormatInformation(int i9, int i10) {
        FormatInformation formatInformationDoDecodeFormatInformation = doDecodeFormatInformation(i9, i10);
        return formatInformationDoDecodeFormatInformation != null ? formatInformationDoDecodeFormatInformation : doDecodeFormatInformation(i9 ^ FORMAT_INFO_MASK_QR, i10 ^ FORMAT_INFO_MASK_QR);
    }

    private static FormatInformation doDecodeFormatInformation(int i9, int i10) {
        int iNumBitsDiffering;
        int i11 = Integer.MAX_VALUE;
        int i12 = 0;
        for (int[] iArr : FORMAT_INFO_DECODE_LOOKUP) {
            int i13 = iArr[0];
            if (i13 == i9 || i13 == i10) {
                return new FormatInformation(iArr[1]);
            }
            int iNumBitsDiffering2 = numBitsDiffering(i9, i13);
            if (iNumBitsDiffering2 < i11) {
                i12 = iArr[1];
                i11 = iNumBitsDiffering2;
            }
            if (i9 != i10 && (iNumBitsDiffering = numBitsDiffering(i10, i13)) < i11) {
                i12 = iArr[1];
                i11 = iNumBitsDiffering;
            }
        }
        if (i11 <= 3) {
            return new FormatInformation(i12);
        }
        return null;
    }

    public static int numBitsDiffering(int i9, int i10) {
        int i11 = i9 ^ i10;
        int[] iArr = BITS_SET_IN_HALF_BYTE;
        return iArr[i11 & 15] + iArr[(i11 >>> 4) & 15] + iArr[(i11 >>> 8) & 15] + iArr[(i11 >>> 12) & 15] + iArr[(i11 >>> 16) & 15] + iArr[(i11 >>> 20) & 15] + iArr[(i11 >>> 24) & 15] + iArr[(i11 >>> 28) & 15];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FormatInformation)) {
            return false;
        }
        FormatInformation formatInformation = (FormatInformation) obj;
        return this.errorCorrectionLevel == formatInformation.errorCorrectionLevel && this.dataMask == formatInformation.dataMask;
    }

    public byte getDataMask() {
        return this.dataMask;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public int hashCode() {
        return (this.errorCorrectionLevel.ordinal() << 3) | this.dataMask;
    }
}
