package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes2.dex */
final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    public BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix;
    }

    private int copyBit(int i9, int i10, int i11) {
        return this.mirror ? this.bitMatrix.get(i10, i9) : this.bitMatrix.get(i9, i10) ? (i11 << 1) | 1 : i11 << 1;
    }

    public void mirror() {
        int i9 = 0;
        while (i9 < this.bitMatrix.getWidth()) {
            int i10 = i9 + 1;
            for (int i11 = i10; i11 < this.bitMatrix.getHeight(); i11++) {
                if (this.bitMatrix.get(i9, i11) != this.bitMatrix.get(i11, i9)) {
                    this.bitMatrix.flip(i11, i9);
                    this.bitMatrix.flip(i9, i11);
                }
            }
            i9 = i10;
        }
    }

    public byte[] readCodewords() throws FormatException {
        FormatInformation formatInformation = readFormatInformation();
        Version version = readVersion();
        DataMask dataMaskForReference = DataMask.forReference(formatInformation.getDataMask());
        int height = this.bitMatrix.getHeight();
        dataMaskForReference.unmaskBitMatrix(this.bitMatrix, height);
        BitMatrix bitMatrixBuildFunctionPattern = version.buildFunctionPattern();
        byte[] bArr = new byte[version.getTotalCodewords()];
        int i9 = height - 1;
        boolean z8 = true;
        int i10 = i9;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i10 > 0) {
            if (i10 == 6) {
                i10--;
            }
            for (int i14 = 0; i14 < height; i14++) {
                int i15 = z8 ? i9 - i14 : i14;
                for (int i16 = 0; i16 < 2; i16++) {
                    int i17 = i10 - i16;
                    if (!bitMatrixBuildFunctionPattern.get(i17, i15)) {
                        i12++;
                        i13 <<= 1;
                        if (this.bitMatrix.get(i17, i15)) {
                            i13 |= 1;
                        }
                        if (i12 == 8) {
                            bArr[i11] = (byte) i13;
                            i11++;
                            i12 = 0;
                            i13 = 0;
                        }
                    }
                }
            }
            z8 = !z8;
            i10 -= 2;
        }
        if (i11 == version.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    public FormatInformation readFormatInformation() throws FormatException {
        FormatInformation formatInformation = this.parsedFormatInfo;
        if (formatInformation != null) {
            return formatInformation;
        }
        int iCopyBit = 0;
        int iCopyBit2 = 0;
        for (int i9 = 0; i9 < 6; i9++) {
            iCopyBit2 = copyBit(i9, 8, iCopyBit2);
        }
        int iCopyBit3 = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, iCopyBit2)));
        for (int i10 = 5; i10 >= 0; i10--) {
            iCopyBit3 = copyBit(8, i10, iCopyBit3);
        }
        int height = this.bitMatrix.getHeight();
        int i11 = height - 7;
        for (int i12 = height - 1; i12 >= i11; i12--) {
            iCopyBit = copyBit(8, i12, iCopyBit);
        }
        for (int i13 = height - 8; i13 < height; i13++) {
            iCopyBit = copyBit(i13, 8, iCopyBit);
        }
        FormatInformation formatInformationDecodeFormatInformation = FormatInformation.decodeFormatInformation(iCopyBit3, iCopyBit);
        this.parsedFormatInfo = formatInformationDecodeFormatInformation;
        if (formatInformationDecodeFormatInformation != null) {
            return formatInformationDecodeFormatInformation;
        }
        throw FormatException.getFormatInstance();
    }

    public Version readVersion() throws FormatException {
        Version version = this.parsedVersion;
        if (version != null) {
            return version;
        }
        int height = this.bitMatrix.getHeight();
        int i9 = (height - 17) / 4;
        if (i9 <= 6) {
            return Version.getVersionForNumber(i9);
        }
        int i10 = height - 11;
        int iCopyBit = 0;
        int iCopyBit2 = 0;
        for (int i11 = 5; i11 >= 0; i11--) {
            for (int i12 = height - 9; i12 >= i10; i12--) {
                iCopyBit2 = copyBit(i12, i11, iCopyBit2);
            }
        }
        Version versionDecodeVersionInformation = Version.decodeVersionInformation(iCopyBit2);
        if (versionDecodeVersionInformation != null && versionDecodeVersionInformation.getDimensionForVersion() == height) {
            this.parsedVersion = versionDecodeVersionInformation;
            return versionDecodeVersionInformation;
        }
        for (int i13 = 5; i13 >= 0; i13--) {
            for (int i14 = height - 9; i14 >= i10; i14--) {
                iCopyBit = copyBit(i13, i14, iCopyBit);
            }
        }
        Version versionDecodeVersionInformation2 = Version.decodeVersionInformation(iCopyBit);
        if (versionDecodeVersionInformation2 == null || versionDecodeVersionInformation2.getDimensionForVersion() != height) {
            throw FormatException.getFormatInstance();
        }
        this.parsedVersion = versionDecodeVersionInformation2;
        return versionDecodeVersionInformation2;
    }

    public void remask() {
        FormatInformation formatInformation = this.parsedFormatInfo;
        if (formatInformation == null) {
            return;
        }
        DataMask.forReference(formatInformation.getDataMask()).unmaskBitMatrix(this.bitMatrix, this.bitMatrix.getHeight());
    }

    public void setMirror(boolean z8) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = z8;
    }
}
