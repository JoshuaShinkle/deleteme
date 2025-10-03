package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;
import org.apache.commons.lang3.StringUtils;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class PercentEscaper extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = {'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z8) {
        Preconditions.checkNotNull(str);
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String str2 = str + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        if (z8 && str2.contains(StringUtils.SPACE)) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = z8;
        this.safeOctets = createSafeOctets(str2);
    }

    private static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int iMax = -1;
        for (char c9 : charArray) {
            iMax = Math.max((int) c9, iMax);
        }
        boolean[] zArr = new boolean[iMax + 1];
        for (char c10 : charArray) {
            zArr[c10] = true;
        }
        return zArr;
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                return escapeSlow(str, i9);
            }
        }
        return str;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public int nextEscapeIndex(CharSequence charSequence, int i9, int i10) {
        Preconditions.checkNotNull(charSequence);
        while (i9 < i10) {
            char cCharAt = charSequence.charAt(i9);
            boolean[] zArr = this.safeOctets;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                break;
            }
            i9++;
        }
        return i9;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public char[] escape(int i9) {
        boolean[] zArr = this.safeOctets;
        if (i9 < zArr.length && zArr[i9]) {
            return null;
        }
        if (i9 == 32 && this.plusForSpace) {
            return PLUS_SIGN;
        }
        if (i9 <= 127) {
            char[] cArr = UPPER_HEX_DIGITS;
            return new char[]{'%', cArr[i9 >>> 4], cArr[i9 & 15]};
        }
        if (i9 <= 2047) {
            char[] cArr2 = UPPER_HEX_DIGITS;
            char[] cArr3 = {'%', cArr2[(i >>> 4) | 12], cArr2[i & 15], '%', cArr2[(i & 3) | 8], cArr2[i9 & 15]};
            int i10 = i9 >>> 4;
            int i11 = i10 >>> 2;
            return cArr3;
        }
        if (i9 <= 65535) {
            char[] cArr4 = UPPER_HEX_DIGITS;
            char[] cArr5 = {'%', 'E', cArr4[i >>> 2], '%', cArr4[(i & 3) | 8], cArr4[i & 15], '%', cArr4[(i & 3) | 8], cArr4[i9 & 15]};
            int i12 = i9 >>> 4;
            int i13 = i12 >>> 2;
            int i14 = i13 >>> 4;
            return cArr5;
        }
        if (i9 <= 1114111) {
            char[] cArr6 = UPPER_HEX_DIGITS;
            char[] cArr7 = {'%', 'F', cArr6[(i >>> 2) & 7], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i & 15], '%', cArr6[(i & 3) | 8], cArr6[i9 & 15]};
            int i15 = i9 >>> 4;
            int i16 = i15 >>> 2;
            int i17 = i16 >>> 4;
            int i18 = i17 >>> 2;
            int i19 = i18 >>> 4;
            return cArr7;
        }
        throw new IllegalArgumentException("Invalid unicode character value " + i9);
    }
}
