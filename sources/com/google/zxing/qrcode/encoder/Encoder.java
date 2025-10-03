package com.google.zxing.qrcode.encoder;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.primitives.UnsignedBytes;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    /* renamed from: com.google.zxing.qrcode.encoder.Encoder$1 */
    public static /* synthetic */ class C44711 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode = iArr;
            try {
                iArr[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private Encoder() {
    }

    public static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException, UnsupportedEncodingException {
        try {
            for (byte b9 : str.getBytes(str2)) {
                bitArray.appendBits(b9, 8);
            }
        } catch (UnsupportedEncodingException e9) {
            throw new WriterException(e9);
        }
    }

    public static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i9));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            int i10 = i9 + 1;
            if (i10 < length) {
                int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(i10));
                if (alphanumericCode2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                i9 += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                i9 = i10;
            }
        }
    }

    public static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException, UnsupportedEncodingException {
        int i9 = C44711.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()];
        if (i9 == 1) {
            appendNumericBytes(str, bitArray);
            return;
        }
        if (i9 == 2) {
            appendAlphanumericBytes(str, bitArray);
            return;
        }
        if (i9 == 3) {
            append8BitBytes(str, bitArray, str2);
        } else {
            if (i9 == 4) {
                appendKanjiBytes(str, bitArray);
                return;
            }
            throw new WriterException("Invalid mode: " + mode);
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }

    public static void appendKanjiBytes(String str, BitArray bitArray) throws WriterException, UnsupportedEncodingException {
        int i9;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i10 = 0; i10 < length; i10 += 2) {
                int i11 = ((bytes[i10] & UnsignedBytes.MAX_VALUE) << 8) | (bytes[i10 + 1] & UnsignedBytes.MAX_VALUE);
                int i12 = 33088;
                if (i11 >= 33088 && i11 <= 40956) {
                    i9 = i11 - i12;
                } else if (i11 < 57408 || i11 > 60351) {
                    i9 = -1;
                } else {
                    i12 = 49472;
                    i9 = i11 - i12;
                }
                if (i9 == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bitArray.appendBits(((i9 >> 8) * PsExtractor.AUDIO_STREAM) + (i9 & 255), 13);
            }
        } catch (UnsupportedEncodingException e9) {
            throw new WriterException(e9);
        }
    }

    public static void appendLengthInfo(int i9, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int i10 = 1 << characterCountBits;
        if (i9 < i10) {
            bitArray.appendBits(i9, characterCountBits);
            return;
        }
        throw new WriterException(i9 + " is bigger than " + (i10 - 1));
    }

    public static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    public static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length) {
            int iCharAt = charSequence.charAt(i9) - '0';
            int i10 = i9 + 2;
            if (i10 < length) {
                bitArray.appendBits((iCharAt * 100) + ((charSequence.charAt(i9 + 1) - '0') * 10) + (charSequence.charAt(i10) - '0'), 10);
                i9 += 3;
            } else {
                i9++;
                if (i9 < length) {
                    bitArray.appendBits((iCharAt * 10) + (charSequence.charAt(i9) - '0'), 7);
                    i9 = i10;
                } else {
                    bitArray.appendBits(iCharAt, 4);
                }
            }
        }
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i9 = Integer.MAX_VALUE;
        int i10 = -1;
        for (int i11 = 0; i11 < 8; i11++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i11, byteMatrix);
            int iCalculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (iCalculateMaskPenalty < i9) {
                i10 = i11;
                i9 = iCalculateMaskPenalty;
            }
        }
        return i10;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    private static Version chooseVersion(int i9, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i10 = 1; i10 <= 40; i10++) {
            Version versionForNumber = Version.getVersionForNumber(i10);
            if (versionForNumber.getTotalCodewords() - versionForNumber.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i9 + 7) / 8) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) {
        return encode(str, errorCorrectionLevel, null);
    }

    public static byte[] generateECBytes(byte[] bArr, int i9) {
        int length = bArr.length;
        int[] iArr = new int[length + i9];
        for (int i10 = 0; i10 < length; i10++) {
            iArr[i10] = bArr[i10] & UnsignedBytes.MAX_VALUE;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i9);
        byte[] bArr2 = new byte[i9];
        for (int i11 = 0; i11 < i9; i11++) {
            bArr2[i11] = (byte) iArr[length + i11];
        }
        return bArr2;
    }

    public static int getAlphanumericCode(int i9) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (i9 < iArr.length) {
            return iArr[i9];
        }
        return -1;
    }

    public static void getNumDataBytesAndNumECBytesForBlockID(int i9, int i10, int i11, int i12, int[] iArr, int[] iArr2) throws WriterException {
        if (i12 >= i11) {
            throw new WriterException("Block ID too large");
        }
        int i13 = i9 % i11;
        int i14 = i11 - i13;
        int i15 = i9 / i11;
        int i16 = i15 + 1;
        int i17 = i10 / i11;
        int i18 = i17 + 1;
        int i19 = i15 - i17;
        int i20 = i16 - i18;
        if (i19 != i20) {
            throw new WriterException("EC bytes mismatch");
        }
        if (i11 != i14 + i13) {
            throw new WriterException("RS blocks mismatch");
        }
        if (i9 != ((i17 + i19) * i14) + ((i18 + i20) * i13)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (i12 < i14) {
            iArr[0] = i17;
            iArr2[0] = i19;
        } else {
            iArr[0] = i18;
            iArr2[0] = i20;
        }
    }

    public static BitArray interleaveWithECBytes(BitArray bitArray, int i9, int i10, int i11) throws WriterException {
        if (bitArray.getSizeInBytes() != i10) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(i11);
        int i12 = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i13 = 0; i13 < i11; i13++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(i9, i10, i11, i13, iArr, iArr2);
            int i14 = iArr[0];
            byte[] bArr = new byte[i14];
            bitArray.toBytes(i12 * 8, bArr, 0, i14);
            byte[] bArrGenerateECBytes = generateECBytes(bArr, iArr2[0]);
            arrayList.add(new BlockPair(bArr, bArrGenerateECBytes));
            iMax = Math.max(iMax, i14);
            iMax2 = Math.max(iMax2, bArrGenerateECBytes.length);
            i12 += iArr[0];
        }
        if (i10 != i12) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = new BitArray();
        for (int i15 = 0; i15 < iMax; i15++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] dataBytes = ((BlockPair) it.next()).getDataBytes();
                if (i15 < dataBytes.length) {
                    bitArray2.appendBits(dataBytes[i15], 8);
                }
            }
        }
        for (int i16 = 0; i16 < iMax2; i16++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] errorCorrectionBytes = ((BlockPair) it2.next()).getErrorCorrectionBytes();
                if (i16 < errorCorrectionBytes.length) {
                    bitArray2.appendBits(errorCorrectionBytes[i16], 8);
                }
            }
        }
        if (i9 == bitArray2.getSizeInBytes()) {
            return bitArray2;
        }
        throw new WriterException("Interleaving error: " + i9 + " and " + bitArray2.getSizeInBytes() + " differ.");
    }

    private static boolean isOnlyDoubleByteKanji(String str) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i9 = 0; i9 < length; i9 += 2) {
                int i10 = bytes[i9] & UnsignedBytes.MAX_VALUE;
                if ((i10 < 129 || i10 > 159) && (i10 < 224 || i10 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    public static void terminateBits(int i9, BitArray bitArray) throws WriterException {
        int i10 = i9 * 8;
        if (bitArray.getSize() > i10) {
            throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + i10);
        }
        for (int i11 = 0; i11 < 4 && bitArray.getSize() < i10; i11++) {
            bitArray.appendBit(false);
        }
        int size = bitArray.getSize() & 7;
        if (size > 0) {
            while (size < 8) {
                bitArray.appendBit(false);
                size++;
            }
        }
        int sizeInBytes = i9 - bitArray.getSizeInBytes();
        for (int i12 = 0; i12 < sizeInBytes; i12++) {
            bitArray.appendBits((i12 & 1) == 0 ? 236 : 17, 8);
        }
        if (bitArray.getSize() != i10) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z8 = false;
        boolean z9 = false;
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z9 = true;
            } else {
                if (getAlphanumericCode(cCharAt) == -1) {
                    return Mode.BYTE;
                }
                z8 = true;
            }
        }
        return z8 ? Mode.ALPHANUMERIC : z9 ? Mode.NUMERIC : Mode.BYTE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException, UnsupportedEncodingException {
        String string;
        CharacterSetECI characterSetECIByName;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.CHARACTER_SET;
            string = map.containsKey(encodeHintType) ? map.get(encodeHintType).toString() : "ISO-8859-1";
        }
        Mode modeChooseMode = chooseMode(str, string);
        BitArray bitArray = new BitArray();
        Mode mode = Mode.BYTE;
        if (modeChooseMode == mode && !"ISO-8859-1".equals(string) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(string)) != null) {
            appendECI(characterSetECIByName, bitArray);
        }
        appendModeInfo(modeChooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, modeChooseMode, bitArray2, string);
        Version versionChooseVersion = chooseVersion(bitArray.getSize() + modeChooseMode.getCharacterCountBits(chooseVersion(bitArray.getSize() + modeChooseMode.getCharacterCountBits(Version.getVersionForNumber(1)) + bitArray2.getSize(), errorCorrectionLevel)) + bitArray2.getSize(), errorCorrectionLevel);
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(modeChooseMode == mode ? bitArray2.getSizeInBytes() : str.length(), versionChooseVersion, modeChooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        Version.ECBlocks eCBlocksForLevel = versionChooseVersion.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = versionChooseVersion.getTotalCodewords() - eCBlocksForLevel.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        BitArray bitArrayInterleaveWithECBytes = interleaveWithECBytes(bitArray3, versionChooseVersion.getTotalCodewords(), totalCodewords, eCBlocksForLevel.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(modeChooseMode);
        qRCode.setVersion(versionChooseVersion);
        int dimensionForVersion = versionChooseVersion.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        int iChooseMaskPattern = chooseMaskPattern(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionChooseVersion, byteMatrix);
        qRCode.setMaskPattern(iChooseMaskPattern);
        MatrixUtil.buildMatrix(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionChooseVersion, iChooseMaskPattern, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }
}
