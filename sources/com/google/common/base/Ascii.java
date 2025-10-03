package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Ascii {
    public static final byte ACK = 6;
    public static final byte BEL = 7;

    /* renamed from: BS */
    public static final byte f15379BS = 8;
    public static final byte CAN = 24;

    /* renamed from: CR */
    public static final byte f15380CR = 13;
    public static final byte DC1 = 17;
    public static final byte DC2 = 18;
    public static final byte DC3 = 19;
    public static final byte DC4 = 20;
    public static final byte DEL = 127;
    public static final byte DLE = 16;

    /* renamed from: EM */
    public static final byte f15381EM = 25;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;
    public static final byte ETB = 23;
    public static final byte ETX = 3;

    /* renamed from: FF */
    public static final byte f15382FF = 12;

    /* renamed from: FS */
    public static final byte f15383FS = 28;

    /* renamed from: GS */
    public static final byte f15384GS = 29;

    /* renamed from: HT */
    public static final byte f15385HT = 9;

    /* renamed from: LF */
    public static final byte f15386LF = 10;
    public static final char MAX = 127;
    public static final char MIN = 0;
    public static final byte NAK = 21;

    /* renamed from: NL */
    public static final byte f15387NL = 10;
    public static final byte NUL = 0;

    /* renamed from: RS */
    public static final byte f15388RS = 30;

    /* renamed from: SI */
    public static final byte f15389SI = 15;

    /* renamed from: SO */
    public static final byte f15390SO = 14;
    public static final byte SOH = 1;

    /* renamed from: SP */
    public static final byte f15391SP = 32;
    public static final byte SPACE = 32;
    public static final byte STX = 2;
    public static final byte SUB = 26;
    public static final byte SYN = 22;

    /* renamed from: US */
    public static final byte f15392US = 31;

    /* renamed from: VT */
    public static final byte f15393VT = 11;
    public static final byte XOFF = 19;
    public static final byte XON = 17;

    private Ascii() {
    }

    public static boolean equalsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        int alphaIndex;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = charSequence.charAt(i9);
            char cCharAt2 = charSequence2.charAt(i9);
            if (cCharAt != cCharAt2 && ((alphaIndex = getAlphaIndex(cCharAt)) >= 26 || alphaIndex != getAlphaIndex(cCharAt2))) {
                return false;
            }
        }
        return true;
    }

    private static int getAlphaIndex(char c9) {
        return (char) ((c9 | ' ') - 97);
    }

    public static boolean isLowerCase(char c9) {
        return c9 >= 'a' && c9 <= 'z';
    }

    public static boolean isUpperCase(char c9) {
        return c9 >= 'A' && c9 <= 'Z';
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        int i9 = 0;
        while (i9 < length) {
            if (isUpperCase(str.charAt(i9))) {
                char[] charArray = str.toCharArray();
                while (i9 < length) {
                    char c9 = charArray[i9];
                    if (isUpperCase(c9)) {
                        charArray[i9] = (char) (c9 ^ ' ');
                    }
                    i9++;
                }
                return String.valueOf(charArray);
            }
            i9++;
        }
        return str;
    }

    public static String toUpperCase(String str) {
        int length = str.length();
        int i9 = 0;
        while (i9 < length) {
            if (isLowerCase(str.charAt(i9))) {
                char[] charArray = str.toCharArray();
                while (i9 < length) {
                    char c9 = charArray[i9];
                    if (isLowerCase(c9)) {
                        charArray[i9] = (char) (c9 & '_');
                    }
                    i9++;
                }
                return String.valueOf(charArray);
            }
            i9++;
        }
        return str;
    }

    public static String truncate(CharSequence charSequence, int i9, String str) {
        Preconditions.checkNotNull(charSequence);
        int length = i9 - str.length();
        Preconditions.checkArgument(length >= 0, "maxLength (%s) must be >= length of the truncation indicator (%s)", i9, str.length());
        int length2 = charSequence.length();
        String str2 = charSequence;
        if (length2 <= i9) {
            String string = charSequence.toString();
            int length3 = string.length();
            str2 = string;
            if (length3 <= i9) {
                return string;
            }
        }
        StringBuilder sb = new StringBuilder(i9);
        sb.append((CharSequence) str2, 0, length);
        sb.append(str);
        return sb.toString();
    }

    public static String toLowerCase(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return toLowerCase((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        for (int i9 = 0; i9 < length; i9++) {
            cArr[i9] = toLowerCase(charSequence.charAt(i9));
        }
        return String.valueOf(cArr);
    }

    public static String toUpperCase(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return toUpperCase((String) charSequence);
        }
        int length = charSequence.length();
        char[] cArr = new char[length];
        for (int i9 = 0; i9 < length; i9++) {
            cArr[i9] = toUpperCase(charSequence.charAt(i9));
        }
        return String.valueOf(cArr);
    }

    public static char toLowerCase(char c9) {
        return isUpperCase(c9) ? (char) (c9 ^ ' ') : c9;
    }

    public static char toUpperCase(char c9) {
        return isLowerCase(c9) ? (char) (c9 & '_') : c9;
    }
}
