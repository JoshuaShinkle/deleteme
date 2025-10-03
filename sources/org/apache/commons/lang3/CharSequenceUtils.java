package org.apache.commons.lang3;

/* loaded from: classes.dex */
public class CharSequenceUtils {
    public static int indexOf(CharSequence charSequence, int i9, int i10) {
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(i9, i10);
        }
        int length = charSequence.length();
        if (i10 < 0) {
            i10 = 0;
        }
        while (i10 < length) {
            if (charSequence.charAt(i10) == i9) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    public static int lastIndexOf(CharSequence charSequence, int i9, int i10) {
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(i9, i10);
        }
        int length = charSequence.length();
        if (i10 < 0) {
            return -1;
        }
        if (i10 >= length) {
            i10 = length - 1;
        }
        while (i10 >= 0) {
            if (charSequence.charAt(i10) == i9) {
                return i10;
            }
            i10--;
        }
        return -1;
    }

    public static boolean regionMatches(CharSequence charSequence, boolean z8, int i9, CharSequence charSequence2, int i10, int i11) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(z8, i9, (String) charSequence2, i10, i11);
        }
        while (true) {
            int i12 = i11 - 1;
            if (i11 <= 0) {
                return true;
            }
            int i13 = i9 + 1;
            char cCharAt = charSequence.charAt(i9);
            int i14 = i10 + 1;
            char cCharAt2 = charSequence2.charAt(i10);
            if (cCharAt != cCharAt2) {
                if (!z8) {
                    return false;
                }
                if (Character.toUpperCase(cCharAt) != Character.toUpperCase(cCharAt2) && Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2)) {
                    return false;
                }
            }
            i9 = i13;
            i11 = i12;
            i10 = i14;
        }
    }

    public static CharSequence subSequence(CharSequence charSequence, int i9) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.subSequence(i9, charSequence.length());
    }

    public static char[] toCharArray(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String) charSequence).toCharArray();
        }
        int length = charSequence.length();
        char[] cArr = new char[charSequence.length()];
        for (int i9 = 0; i9 < length; i9++) {
            cArr[i9] = charSequence.charAt(i9);
        }
        return cArr;
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        return charSequence.toString().indexOf(charSequence2.toString(), i9);
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        return charSequence.toString().lastIndexOf(charSequence2.toString(), i9);
    }
}
