package org.apache.commons.lang3;

/* loaded from: classes.dex */
public class CharUtils {
    private static final String[] CHAR_STRING_ARRAY = new String[128];

    /* renamed from: CR */
    public static final char f19105CR = '\r';

    /* renamed from: LF */
    public static final char f19106LF = '\n';

    static {
        char c9 = 0;
        while (true) {
            String[] strArr = CHAR_STRING_ARRAY;
            if (c9 >= strArr.length) {
                return;
            }
            strArr[c9] = String.valueOf(c9);
            c9 = (char) (c9 + 1);
        }
    }

    public static boolean isAscii(char c9) {
        return c9 < 128;
    }

    public static boolean isAsciiAlpha(char c9) {
        return isAsciiAlphaUpper(c9) || isAsciiAlphaLower(c9);
    }

    public static boolean isAsciiAlphaLower(char c9) {
        return c9 >= 'a' && c9 <= 'z';
    }

    public static boolean isAsciiAlphaUpper(char c9) {
        return c9 >= 'A' && c9 <= 'Z';
    }

    public static boolean isAsciiAlphanumeric(char c9) {
        return isAsciiAlpha(c9) || isAsciiNumeric(c9);
    }

    public static boolean isAsciiControl(char c9) {
        return c9 < ' ' || c9 == 127;
    }

    public static boolean isAsciiNumeric(char c9) {
        return c9 >= '0' && c9 <= '9';
    }

    public static boolean isAsciiPrintable(char c9) {
        return c9 >= ' ' && c9 < 127;
    }

    public static char toChar(Character ch) {
        if (ch != null) {
            return ch.charValue();
        }
        throw new IllegalArgumentException("The Character must not be null");
    }

    @Deprecated
    public static Character toCharacterObject(char c9) {
        return Character.valueOf(c9);
    }

    public static int toIntValue(char c9) {
        if (isAsciiNumeric(c9)) {
            return c9 - '0';
        }
        throw new IllegalArgumentException("The character " + c9 + " is not in the range '0' - '9'");
    }

    public static String toString(char c9) {
        return c9 < 128 ? CHAR_STRING_ARRAY[c9] : new String(new char[]{c9});
    }

    public static String unicodeEscaped(char c9) {
        if (c9 < 16) {
            return "\\u000" + Integer.toHexString(c9);
        }
        if (c9 < 256) {
            return "\\u00" + Integer.toHexString(c9);
        }
        if (c9 < 4096) {
            return "\\u0" + Integer.toHexString(c9);
        }
        return "\\u" + Integer.toHexString(c9);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch, char c9) {
        return ch == null ? c9 : ch.charValue();
    }

    public static int toIntValue(char c9, int i9) {
        return !isAsciiNumeric(c9) ? i9 : c9 - '0';
    }

    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return toString(ch.charValue());
    }

    public static char toChar(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.charAt(0);
        }
        throw new IllegalArgumentException("The String must not be empty");
    }

    public static int toIntValue(Character ch) {
        if (ch != null) {
            return toIntValue(ch.charValue());
        }
        throw new IllegalArgumentException("The character must not be null");
    }

    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    public static int toIntValue(Character ch, int i9) {
        return ch == null ? i9 : toIntValue(ch.charValue(), i9);
    }

    public static char toChar(String str, char c9) {
        return StringUtils.isEmpty(str) ? c9 : str.charAt(0);
    }
}
