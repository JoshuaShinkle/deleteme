package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version;

/* loaded from: classes2.dex */
final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i9, byte[] bArr) {
        this.numDataCodewords = i9;
        this.codewords = bArr;
    }

    public static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
        Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
        int count = 0;
        for (Version.ECB ecb : eCBlocks) {
            count += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[count];
        int i9 = 0;
        for (Version.ECB ecb2 : eCBlocks) {
            int i10 = 0;
            while (i10 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[i9] = new DataBlock(dataCodewords, new byte[eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords]);
                i10++;
                i9++;
            }
        }
        int length = dataBlockArr[0].codewords.length;
        int i11 = count - 1;
        while (i11 >= 0 && dataBlockArr[i11].codewords.length != length) {
            i11--;
        }
        int i12 = i11 + 1;
        int eCCodewordsPerBlock = length - eCBlocksForLevel.getECCodewordsPerBlock();
        int i13 = 0;
        for (int i14 = 0; i14 < eCCodewordsPerBlock; i14++) {
            int i15 = 0;
            while (i15 < i9) {
                dataBlockArr[i15].codewords[i14] = bArr[i13];
                i15++;
                i13++;
            }
        }
        int i16 = i12;
        while (i16 < i9) {
            dataBlockArr[i16].codewords[eCCodewordsPerBlock] = bArr[i13];
            i16++;
            i13++;
        }
        int length2 = dataBlockArr[0].codewords.length;
        while (eCCodewordsPerBlock < length2) {
            int i17 = 0;
            while (i17 < i9) {
                dataBlockArr[i17].codewords[i17 < i12 ? eCCodewordsPerBlock : eCCodewordsPerBlock + 1] = bArr[i13];
                i17++;
                i13++;
            }
            eCCodewordsPerBlock++;
        }
        return dataBlockArr;
    }

    public byte[] getCodewords() {
        return this.codewords;
    }

    public int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
