package com.google.zxing.common;

import com.google.common.primitives.UnsignedBytes;
import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes2.dex */
public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING;
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static {
        String strName = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = strName;
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(strName) || EUC_JP.equalsIgnoreCase(strName);
    }

    private StringUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0058 A[PHI: r10
      0x0058: PHI (r10v6 int) = (r10v1 int), (r10v5 int), (r10v1 int) binds: [B:33:0x0063, B:42:0x007c, B:27:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String guessEncoding(byte[] bArr, Map<DecodeHintType, ?> map) {
        byte[] bArr2 = bArr;
        if (map != null) {
            DecodeHintType decodeHintType = DecodeHintType.CHARACTER_SET;
            if (map.containsKey(decodeHintType)) {
                return map.get(decodeHintType).toString();
            }
        }
        int length = bArr2.length;
        boolean z8 = true;
        int i9 = 0;
        boolean z9 = bArr2.length > 3 && bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65;
        boolean z10 = true;
        boolean z11 = true;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (i11 < length && (z8 || z10 || z11)) {
            int i21 = bArr2[i11] & UnsignedBytes.MAX_VALUE;
            if (z11) {
                if (i12 > 0) {
                    if ((i21 & 128) == 0) {
                        z11 = false;
                    } else {
                        i12--;
                    }
                } else if ((i21 & 128) != 0) {
                    if ((i21 & 64) != 0) {
                        i12++;
                        if ((i21 & 32) == 0) {
                            i14++;
                        } else {
                            i12++;
                            if ((i21 & 16) == 0) {
                                i15++;
                            } else {
                                i12++;
                                if ((i21 & 8) == 0) {
                                    i16++;
                                }
                            }
                        }
                    }
                }
            }
            if (z8) {
                if (i21 > 127 && i21 < 160) {
                    z8 = false;
                } else if (i21 > 159 && (i21 < 192 || i21 == 215 || i21 == 247)) {
                    i18++;
                }
            }
            if (z10) {
                if (i13 > 0) {
                    if (i21 < 64 || i21 == 127 || i21 > 252) {
                        z10 = false;
                    } else {
                        i13--;
                    }
                } else if (i21 != 128 && i21 != 160 && i21 <= 239) {
                    if (i21 <= 160 || i21 >= 224) {
                        if (i21 > 127) {
                            i13++;
                            int i22 = i19 + 1;
                            if (i22 > i9) {
                                i9 = i22;
                                i19 = i9;
                            } else {
                                i19 = i22;
                            }
                        } else {
                            i19 = 0;
                        }
                        i20 = 0;
                    } else {
                        i10++;
                        int i23 = i20 + 1;
                        if (i23 > i17) {
                            i17 = i23;
                            i20 = i17;
                        } else {
                            i20 = i23;
                        }
                        i19 = 0;
                    }
                }
            }
            i11++;
            bArr2 = bArr;
        }
        if (z11 && i12 > 0) {
            z11 = false;
        }
        if (z10 && i13 > 0) {
            z10 = false;
        }
        return (!z11 || (!z9 && (i14 + i15) + i16 <= 0)) ? (!z10 || (!ASSUME_SHIFT_JIS && i17 < 3 && i9 < 3)) ? (z8 && z10) ? (!(i17 == 2 && i10 == 2) && i18 * 10 < length) ? ISO88591 : SHIFT_JIS : z8 ? ISO88591 : z10 ? SHIFT_JIS : z11 ? UTF8 : PLATFORM_DEFAULT_ENCODING : SHIFT_JIS : UTF8;
    }
}
