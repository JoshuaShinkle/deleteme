package com.google.zxing.qrcode.decoder;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, IOUtils.DIR_SEPARATOR_UNIX, ':'};
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    public static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode mode;
        Mode mode2;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i9 = 1;
        ArrayList arrayList = new ArrayList(1);
        int i10 = -1;
        int bits = -1;
        boolean z8 = false;
        CharacterSetECI characterSetECIByValue = null;
        while (true) {
            try {
                Mode modeForBits = bitSource.available() < 4 ? Mode.TERMINATOR : Mode.forBits(bitSource.readBits(4));
                Mode mode3 = Mode.TERMINATOR;
                if (modeForBits == mode3) {
                    mode = mode3;
                    mode2 = modeForBits;
                } else if (modeForBits == Mode.FNC1_FIRST_POSITION || modeForBits == Mode.FNC1_SECOND_POSITION) {
                    mode = mode3;
                    mode2 = modeForBits;
                    z8 = true;
                } else {
                    if (modeForBits == Mode.STRUCTURED_APPEND) {
                        if (bitSource.available() < 16) {
                            throw FormatException.getFormatInstance();
                        }
                        int bits2 = bitSource.readBits(8);
                        bits = bitSource.readBits(8);
                        i10 = bits2;
                    } else if (modeForBits == Mode.ECI) {
                        characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                        if (characterSetECIByValue == null) {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (modeForBits == Mode.HANZI) {
                        int bits3 = bitSource.readBits(4);
                        int bits4 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                        if (bits3 == i9) {
                            decodeHanziSegment(bitSource, sb, bits4);
                        }
                    } else {
                        int bits5 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                        if (modeForBits == Mode.NUMERIC) {
                            decodeNumericSegment(bitSource, sb, bits5);
                        } else if (modeForBits == Mode.ALPHANUMERIC) {
                            decodeAlphanumericSegment(bitSource, sb, bits5, z8);
                        } else if (modeForBits == Mode.BYTE) {
                            mode = mode3;
                            mode2 = modeForBits;
                            decodeByteSegment(bitSource, sb, bits5, characterSetECIByValue, arrayList, map);
                        } else {
                            mode = mode3;
                            mode2 = modeForBits;
                            if (mode2 != Mode.KANJI) {
                                throw FormatException.getFormatInstance();
                            }
                            decodeKanjiSegment(bitSource, sb, bits5);
                        }
                    }
                    mode = mode3;
                    mode2 = modeForBits;
                }
                if (mode2 == mode) {
                    return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i10, bits);
                }
                i9 = 1;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i9, boolean z8) throws FormatException {
        while (i9 > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(bits / 45));
            sb.append(toAlphaNumericChar(bits % 45));
            i9 -= 2;
        }
        if (i9 == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z8) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i10 = length + 1;
                        if (sb.charAt(i10) == '%') {
                            sb.deleteCharAt(i10);
                        } else {
                            sb.setCharAt(length, (char) 29);
                        }
                    }
                }
            }
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i9, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        if (i9 * 8 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            bArr[i10] = (byte) bitSource.readBits(8);
        }
        try {
            sb.append(new String(bArr, characterSetECI == null ? StringUtils.guessEncoding(bArr, map) : characterSetECI.name()));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i9) throws FormatException {
        if (i9 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i9 * 2];
        int i10 = 0;
        while (i9 > 0) {
            int bits = bitSource.readBits(13);
            int i11 = (bits % 96) | ((bits / 96) << 8);
            int i12 = i11 + (i11 < 959 ? 41377 : 42657);
            bArr[i10] = (byte) ((i12 >> 8) & 255);
            bArr[i10 + 1] = (byte) (i12 & 255);
            i10 += 2;
            i9--;
        }
        try {
            sb.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i9) throws FormatException {
        if (i9 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i9 * 2];
        int i10 = 0;
        while (i9 > 0) {
            int bits = bitSource.readBits(13);
            int i11 = (bits % PsExtractor.AUDIO_STREAM) | ((bits / PsExtractor.AUDIO_STREAM) << 8);
            int i12 = i11 + (i11 < 7936 ? 33088 : 49472);
            bArr[i10] = (byte) (i12 >> 8);
            bArr[i10 + 1] = (byte) i12;
            i10 += 2;
            i9--;
        }
        try {
            sb.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i9) throws FormatException {
        while (i9 >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(10);
            if (bits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits / 100));
            sb.append(toAlphaNumericChar((bits / 10) % 10));
            sb.append(toAlphaNumericChar(bits % 10));
            i9 -= 3;
        }
        if (i9 == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int bits2 = bitSource.readBits(7);
            if (bits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits2 / 10));
            sb.append(toAlphaNumericChar(bits2 % 10));
            return;
        }
        if (i9 == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int bits3 = bitSource.readBits(4);
            if (bits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int bits = bitSource.readBits(8);
        if ((bits & 128) == 0) {
            return bits & 127;
        }
        if ((bits & PsExtractor.AUDIO_STREAM) == 128) {
            return bitSource.readBits(8) | ((bits & 63) << 8);
        }
        if ((bits & 224) == 192) {
            return bitSource.readBits(16) | ((bits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int i9) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i9 < cArr.length) {
            return cArr[i9];
        }
        throw FormatException.getFormatInstance();
    }
}
