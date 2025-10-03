package com.google.android.exoplayer2.util;

import android.util.Pair;
import com.google.android.exoplayer2.ParserException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class CodecSpecificDataUtil {
    private static final int AUDIO_OBJECT_TYPE_AAC_LC = 2;
    private static final int AUDIO_OBJECT_TYPE_ER_BSAC = 22;
    private static final int AUDIO_OBJECT_TYPE_ESCAPE = 31;
    private static final int AUDIO_OBJECT_TYPE_PS = 29;
    private static final int AUDIO_OBJECT_TYPE_SBR = 5;
    private static final int AUDIO_SPECIFIC_CONFIG_CHANNEL_CONFIGURATION_INVALID = -1;
    private static final int AUDIO_SPECIFIC_CONFIG_FREQUENCY_INDEX_ARBITRARY = 15;
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    private CodecSpecificDataUtil() {
    }

    public static byte[] buildAacAudioSpecificConfig(int i9, int i10, int i11) {
        return new byte[]{(byte) (((i9 << 3) & 248) | ((i10 >> 1) & 7)), (byte) (((i10 << 7) & 128) | ((i11 << 3) & 120))};
    }

    public static byte[] buildAacLcAudioSpecificConfig(int i9, int i10) {
        int i11 = 0;
        int i12 = -1;
        int i13 = 0;
        while (true) {
            int[] iArr = AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
            if (i13 >= iArr.length) {
                break;
            }
            if (i9 == iArr[i13]) {
                i12 = i13;
            }
            i13++;
        }
        int i14 = -1;
        while (true) {
            int[] iArr2 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE;
            if (i11 >= iArr2.length) {
                break;
            }
            if (i10 == iArr2[i11]) {
                i14 = i11;
            }
            i11++;
        }
        if (i9 != -1 && i14 != -1) {
            return buildAacAudioSpecificConfig(2, i12, i14);
        }
        throw new IllegalArgumentException("Invalid sample rate or number of channels: " + i9 + ", " + i10);
    }

    public static byte[] buildNalUnit(byte[] bArr, int i9, int i10) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[bArr2.length + i10];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i9, bArr3, bArr2.length, i10);
        return bArr3;
    }

    private static int findNalStartCode(byte[] bArr, int i9) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i9 <= length) {
            if (isNalStartCode(bArr, i9)) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    private static int getAacAudioObjectType(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(5);
        return bits == 31 ? parsableBitArray.readBits(6) + 32 : bits;
    }

    private static int getAacSamplingFrequency(ParsableBitArray parsableBitArray) {
        int bits = parsableBitArray.readBits(4);
        if (bits == 15) {
            return parsableBitArray.readBits(24);
        }
        Assertions.checkArgument(bits < 13);
        return AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[bits];
    }

    private static boolean isNalStartCode(byte[] bArr, int i9) {
        if (bArr.length - i9 <= NAL_START_CODE.length) {
            return false;
        }
        int i10 = 0;
        while (true) {
            byte[] bArr2 = NAL_START_CODE;
            if (i10 >= bArr2.length) {
                return true;
            }
            if (bArr[i9 + i10] != bArr2[i10]) {
                return false;
            }
            i10++;
        }
    }

    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(byte[] bArr) {
        return parseAacAudioSpecificConfig(new ParsableBitArray(bArr), false);
    }

    private static void parseGaSpecificConfig(ParsableBitArray parsableBitArray, int i9, int i10) {
        parsableBitArray.skipBits(1);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(14);
        }
        boolean bit = parsableBitArray.readBit();
        if (i10 == 0) {
            throw new UnsupportedOperationException();
        }
        if (i9 == 6 || i9 == 20) {
            parsableBitArray.skipBits(3);
        }
        if (bit) {
            if (i9 == 22) {
                parsableBitArray.skipBits(16);
            }
            if (i9 == 17 || i9 == 19 || i9 == 20 || i9 == 23) {
                parsableBitArray.skipBits(3);
            }
            parsableBitArray.skipBits(1);
        }
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iFindNalStartCode = 0;
        do {
            arrayList.add(Integer.valueOf(iFindNalStartCode));
            iFindNalStartCode = findNalStartCode(bArr, iFindNalStartCode + NAL_START_CODE.length);
        } while (iFindNalStartCode != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i9 = 0;
        while (i9 < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i9)).intValue();
            int iIntValue2 = (i9 < arrayList.size() + (-1) ? ((Integer) arrayList.get(i9 + 1)).intValue() : bArr.length) - iIntValue;
            byte[] bArr3 = new byte[iIntValue2];
            System.arraycopy(bArr, iIntValue, bArr3, 0, iIntValue2);
            bArr2[i9] = bArr3;
            i9++;
        }
        return bArr2;
    }

    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(ParsableBitArray parsableBitArray, boolean z8) throws ParserException {
        int aacAudioObjectType = getAacAudioObjectType(parsableBitArray);
        int aacSamplingFrequency = getAacSamplingFrequency(parsableBitArray);
        int bits = parsableBitArray.readBits(4);
        if (aacAudioObjectType == 5 || aacAudioObjectType == 29) {
            aacSamplingFrequency = getAacSamplingFrequency(parsableBitArray);
            aacAudioObjectType = getAacAudioObjectType(parsableBitArray);
            if (aacAudioObjectType == 22) {
                bits = parsableBitArray.readBits(4);
            }
        }
        if (z8) {
            if (aacAudioObjectType != 1 && aacAudioObjectType != 2 && aacAudioObjectType != 3 && aacAudioObjectType != 4 && aacAudioObjectType != 6 && aacAudioObjectType != 7 && aacAudioObjectType != 17) {
                switch (aacAudioObjectType) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        break;
                    default:
                        throw new ParserException("Unsupported audio object type: " + aacAudioObjectType);
                }
            }
            parseGaSpecificConfig(parsableBitArray, aacAudioObjectType, bits);
            switch (aacAudioObjectType) {
                case 17:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int bits2 = parsableBitArray.readBits(2);
                    if (bits2 == 2 || bits2 == 3) {
                        throw new ParserException("Unsupported epConfig: " + bits2);
                    }
            }
        }
        int i9 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[bits];
        Assertions.checkArgument(i9 != -1);
        return Pair.create(Integer.valueOf(aacSamplingFrequency), Integer.valueOf(i9));
    }
}
