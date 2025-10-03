package org.apache.commons.lang3.text;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/* loaded from: classes.dex */
public class WordUtils {
    public static String capitalize(String str) {
        return capitalize(str, null);
    }

    public static String capitalizeFully(String str) {
        return capitalizeFully(str, null);
    }

    public static String initials(String str) {
        return initials(str, null);
    }

    private static boolean isDelimiter(char c9, char[] cArr) {
        if (cArr == null) {
            return Character.isWhitespace(c9);
        }
        for (char c10 : cArr) {
            if (c9 == c10) {
                return true;
            }
        }
        return false;
    }

    public static String swapCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean zIsWhitespace = true;
        int i9 = 0;
        while (i9 < charArray.length) {
            char c9 = charArray[i9];
            if (Character.isUpperCase(c9) || Character.isTitleCase(c9)) {
                charArray[i9] = Character.toLowerCase(c9);
            } else {
                if (!Character.isLowerCase(c9)) {
                    zIsWhitespace = Character.isWhitespace(c9);
                } else if (zIsWhitespace) {
                    charArray[i9] = Character.toTitleCase(c9);
                } else {
                    charArray[i9] = Character.toUpperCase(c9);
                }
                i9++;
            }
            zIsWhitespace = false;
            i9++;
        }
        return new String(charArray);
    }

    public static String uncapitalize(String str) {
        return uncapitalize(str, null);
    }

    public static String wrap(String str, int i9) {
        return wrap(str, i9, null, false);
    }

    public static String capitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (StringUtils.isEmpty(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z8 = true;
        for (int i9 = 0; i9 < charArray.length; i9++) {
            char c9 = charArray[i9];
            if (isDelimiter(c9, cArr)) {
                z8 = true;
            } else if (z8) {
                charArray[i9] = Character.toTitleCase(c9);
                z8 = false;
            }
        }
        return new String(charArray);
    }

    public static String capitalizeFully(String str, char... cArr) {
        return (StringUtils.isEmpty(str) || (cArr == null ? -1 : cArr.length) == 0) ? str : capitalize(str.toLowerCase(), cArr);
    }

    public static String initials(String str, char... cArr) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (cArr != null && cArr.length == 0) {
            return "";
        }
        int length = str.length();
        char[] cArr2 = new char[(length / 2) + 1];
        boolean z8 = true;
        int i9 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (isDelimiter(cCharAt, cArr)) {
                z8 = true;
            } else if (z8) {
                cArr2[i9] = cCharAt;
                i9++;
                z8 = false;
            }
        }
        return new String(cArr2, 0, i9);
    }

    public static String uncapitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (StringUtils.isEmpty(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z8 = true;
        for (int i9 = 0; i9 < charArray.length; i9++) {
            char c9 = charArray[i9];
            if (isDelimiter(c9, cArr)) {
                z8 = true;
            } else if (z8) {
                charArray[i9] = Character.toLowerCase(c9);
                z8 = false;
            }
        }
        return new String(charArray);
    }

    public static String wrap(String str, int i9, String str2, boolean z8) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = SystemUtils.LINE_SEPARATOR;
        }
        if (i9 < 1) {
            i9 = 1;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 32);
        int i10 = 0;
        while (length - i10 > i9) {
            if (str.charAt(i10) == ' ') {
                i10++;
            } else {
                int i11 = i9 + i10;
                int iLastIndexOf = str.lastIndexOf(32, i11);
                if (iLastIndexOf >= i10) {
                    sb.append(str.substring(i10, iLastIndexOf));
                    sb.append(str2);
                    i10 = iLastIndexOf + 1;
                } else {
                    if (z8) {
                        sb.append(str.substring(i10, i11));
                        sb.append(str2);
                    } else {
                        int iIndexOf = str.indexOf(32, i11);
                        if (iIndexOf >= 0) {
                            sb.append(str.substring(i10, iIndexOf));
                            sb.append(str2);
                            i11 = iIndexOf + 1;
                        } else {
                            sb.append(str.substring(i10));
                            i10 = length;
                        }
                    }
                    i10 = i11;
                }
            }
        }
        sb.append(str.substring(i10));
        return sb.toString();
    }
}
