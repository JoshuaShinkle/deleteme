package org.apache.commons.lang3;

/* loaded from: classes.dex */
public class CharSetUtils {
    public static boolean containsAny(String str, String... strArr) {
        if (!StringUtils.isEmpty(str) && !deepEmpty(strArr)) {
            CharSet charSet = CharSet.getInstance(strArr);
            for (char c9 : str.toCharArray()) {
                if (charSet.contains(c9)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int count(String str, String... strArr) {
        if (StringUtils.isEmpty(str) || deepEmpty(strArr)) {
            return 0;
        }
        CharSet charSet = CharSet.getInstance(strArr);
        int i9 = 0;
        for (char c9 : str.toCharArray()) {
            if (charSet.contains(c9)) {
                i9++;
            }
        }
        return i9;
    }

    private static boolean deepEmpty(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (StringUtils.isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static String delete(String str, String... strArr) {
        return (StringUtils.isEmpty(str) || deepEmpty(strArr)) ? str : modify(str, strArr, false);
    }

    public static String keep(String str, String... strArr) {
        if (str == null) {
            return null;
        }
        return (str.isEmpty() || deepEmpty(strArr)) ? "" : modify(str, strArr, true);
    }

    private static String modify(String str, String[] strArr, boolean z8) {
        CharSet charSet = CharSet.getInstance(strArr);
        StringBuilder sb = new StringBuilder(str.length());
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (charSet.contains(charArray[i9]) == z8) {
                sb.append(charArray[i9]);
            }
        }
        return sb.toString();
    }

    public static String squeeze(String str, String... strArr) {
        if (StringUtils.isEmpty(str) || deepEmpty(strArr)) {
            return str;
        }
        CharSet charSet = CharSet.getInstance(strArr);
        StringBuilder sb = new StringBuilder(str.length());
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        char c9 = ' ';
        for (int i9 = 0; i9 < length; i9++) {
            char c10 = charArray[i9];
            if (c10 != c9 || i9 == 0 || !charSet.contains(c10)) {
                sb.append(c10);
                c9 = c10;
            }
        }
        return sb.toString();
    }
}
