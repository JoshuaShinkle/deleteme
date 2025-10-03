package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Strings {
    private Strings() {
    }

    public static String commonPrefix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int iMin = Math.min(charSequence.length(), charSequence2.length());
        int i9 = 0;
        while (i9 < iMin && charSequence.charAt(i9) == charSequence2.charAt(i9)) {
            i9++;
        }
        int i10 = i9 - 1;
        if (validSurrogatePairAt(charSequence, i10) || validSurrogatePairAt(charSequence2, i10)) {
            i9--;
        }
        return charSequence.subSequence(0, i9).toString();
    }

    public static String commonSuffix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int iMin = Math.min(charSequence.length(), charSequence2.length());
        int i9 = 0;
        while (i9 < iMin && charSequence.charAt((charSequence.length() - i9) - 1) == charSequence2.charAt((charSequence2.length() - i9) - 1)) {
            i9++;
        }
        if (validSurrogatePairAt(charSequence, (charSequence.length() - i9) - 1) || validSurrogatePairAt(charSequence2, (charSequence2.length() - i9) - 1)) {
            i9--;
        }
        return charSequence.subSequence(charSequence.length() - i9, charSequence.length()).toString();
    }

    public static String emptyToNull(String str) {
        if (isNullOrEmpty(str)) {
            return null;
        }
        return str;
    }

    public static boolean isNullOrEmpty(String str) {
        return Platform.stringIsNullOrEmpty(str);
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String padEnd(String str, int i9, char c9) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i9) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i9);
        sb.append(str);
        for (int length = str.length(); length < i9; length++) {
            sb.append(c9);
        }
        return sb.toString();
    }

    public static String padStart(String str, int i9, char c9) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i9) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i9);
        for (int length = str.length(); length < i9; length++) {
            sb.append(c9);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String repeat(String str, int i9) {
        Preconditions.checkNotNull(str);
        if (i9 <= 1) {
            Preconditions.checkArgument(i9 >= 0, "invalid count: %s", i9);
            return i9 == 0 ? "" : str;
        }
        int length = str.length();
        long j9 = length * i9;
        int i10 = (int) j9;
        if (i10 != j9) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + j9);
        }
        char[] cArr = new char[i10];
        str.getChars(0, length, cArr, 0);
        while (true) {
            int i11 = i10 - length;
            if (length >= i11) {
                System.arraycopy(cArr, 0, cArr, length, i11);
                return new String(cArr);
            }
            System.arraycopy(cArr, 0, cArr, length, length);
            length <<= 1;
        }
    }

    @VisibleForTesting
    public static boolean validSurrogatePairAt(CharSequence charSequence, int i9) {
        return i9 >= 0 && i9 <= charSequence.length() + (-2) && Character.isHighSurrogate(charSequence.charAt(i9)) && Character.isLowSurrogate(charSequence.charAt(i9 + 1));
    }
}
