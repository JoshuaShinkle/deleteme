package org.apache.commons.p159io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
public class FilenameUtils {
    private static final char EXTENSION_SEPARATOR = '.';
    private static final char OTHER_SEPARATOR;
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    public static String concat(String str, String str2) {
        int prefixLength = getPrefixLength(str2);
        if (prefixLength < 0) {
            return null;
        }
        if (prefixLength > 0) {
            return normalize(str2);
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return normalize(str2);
        }
        if (isSeparator(str.charAt(length - 1))) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(str2);
            return normalize(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(str);
        stringBuffer2.append('/');
        stringBuffer2.append(str2);
        return normalize(stringBuffer2.toString());
    }

    private static String doGetFullPath(String str, boolean z8) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength >= str.length()) {
            return z8 ? getPrefix(str) : str;
        }
        int iIndexOfLastSeparator = indexOfLastSeparator(str);
        return iIndexOfLastSeparator < 0 ? str.substring(0, prefixLength) : str.substring(0, iIndexOfLastSeparator + (z8 ? 1 : 0));
    }

    private static String doGetPath(String str, int i9) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        int iIndexOfLastSeparator = indexOfLastSeparator(str);
        return (prefixLength >= str.length() || iIndexOfLastSeparator < 0) ? "" : str.substring(prefixLength, iIndexOfLastSeparator + i9);
    }

    private static String doNormalize(String str, boolean z8) {
        boolean z9;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int prefixLength = getPrefixLength(str);
        if (prefixLength < 0) {
            return null;
        }
        int i9 = length + 2;
        char[] cArr = new char[i9];
        str.getChars(0, str.length(), cArr, 0);
        for (int i10 = 0; i10 < i9; i10++) {
            if (cArr[i10] == OTHER_SEPARATOR) {
                cArr[i10] = SYSTEM_SEPARATOR;
            }
        }
        char c9 = cArr[length - 1];
        char c10 = SYSTEM_SEPARATOR;
        if (c9 != c10) {
            cArr[length] = c10;
            length++;
            z9 = false;
        } else {
            z9 = true;
        }
        int i11 = prefixLength + 1;
        int i12 = i11;
        while (i12 < length) {
            char c11 = cArr[i12];
            char c12 = SYSTEM_SEPARATOR;
            if (c11 == c12) {
                int i13 = i12 - 1;
                if (cArr[i13] == c12) {
                    System.arraycopy(cArr, i12, cArr, i13, length - i12);
                    length--;
                    i12--;
                }
            }
            i12++;
        }
        int i14 = i11;
        while (i14 < length) {
            char c13 = cArr[i14];
            char c14 = SYSTEM_SEPARATOR;
            if (c13 == c14) {
                int i15 = i14 - 1;
                if (cArr[i15] == '.' && (i14 == i11 || cArr[i14 - 2] == c14)) {
                    if (i14 == length - 1) {
                        z9 = true;
                    }
                    System.arraycopy(cArr, i14 + 1, cArr, i15, length - i14);
                    length -= 2;
                    i14--;
                }
            }
            i14++;
        }
        int i16 = prefixLength + 2;
        int i17 = i16;
        while (i17 < length) {
            char c15 = cArr[i17];
            char c16 = SYSTEM_SEPARATOR;
            if (c15 == c16 && cArr[i17 - 1] == '.' && cArr[i17 - 2] == '.' && (i17 == i16 || cArr[i17 - 3] == c16)) {
                if (i17 == i16) {
                    return null;
                }
                if (i17 == length - 1) {
                    z9 = true;
                }
                int i18 = i17 - 4;
                while (true) {
                    if (i18 < prefixLength) {
                        int i19 = i17 + 1;
                        System.arraycopy(cArr, i19, cArr, prefixLength, length - i17);
                        length -= i19 - prefixLength;
                        i17 = i11;
                        break;
                    }
                    if (cArr[i18] == SYSTEM_SEPARATOR) {
                        int i20 = i18 + 1;
                        System.arraycopy(cArr, i17 + 1, cArr, i20, length - i17);
                        length -= i17 - i18;
                        i17 = i20;
                        break;
                    }
                    i18--;
                }
            }
            i17++;
        }
        return length <= 0 ? "" : length <= prefixLength ? new String(cArr, 0, length) : (z9 && z8) ? new String(cArr, 0, length) : new String(cArr, 0, length - 1);
    }

    public static boolean equals(String str, String str2) {
        return equals(str, str2, false, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalized(String str, String str2) {
        return equals(str, str2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String str, String str2) {
        return equals(str, str2, true, IOCase.SYSTEM);
    }

    public static boolean equalsOnSystem(String str, String str2) {
        return equals(str, str2, false, IOCase.SYSTEM);
    }

    public static String getBaseName(String str) {
        return removeExtension(getName(str));
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOfExtension = indexOfExtension(str);
        return iIndexOfExtension == -1 ? "" : str.substring(iIndexOfExtension + 1);
    }

    public static String getFullPath(String str) {
        return doGetFullPath(str, true);
    }

    public static String getFullPathNoEndSeparator(String str) {
        return doGetFullPath(str, false);
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    public static String getPath(String str) {
        return doGetPath(str, 1);
    }

    public static String getPathNoEndSeparator(String str) {
        return doGetPath(str, 0);
    }

    public static String getPrefix(String str) {
        int prefixLength;
        if (str == null || (prefixLength = getPrefixLength(str)) < 0) {
            return null;
        }
        if (prefixLength <= str.length()) {
            return str.substring(0, prefixLength);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append('/');
        return stringBuffer.toString();
    }

    public static int getPrefixLength(String str) {
        int iMin;
        if (str == null) {
            return -1;
        }
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == ':') {
            return -1;
        }
        if (length == 1) {
            if (cCharAt == '~') {
                return 2;
            }
            return isSeparator(cCharAt) ? 1 : 0;
        }
        if (cCharAt == '~') {
            int iIndexOf = str.indexOf(47, 1);
            int iIndexOf2 = str.indexOf(92, 1);
            if (iIndexOf == -1 && iIndexOf2 == -1) {
                return length + 1;
            }
            if (iIndexOf == -1) {
                iIndexOf = iIndexOf2;
            }
            if (iIndexOf2 == -1) {
                iIndexOf2 = iIndexOf;
            }
            iMin = Math.min(iIndexOf, iIndexOf2);
        } else {
            char cCharAt2 = str.charAt(1);
            if (cCharAt2 == ':') {
                char upperCase = Character.toUpperCase(cCharAt);
                if (upperCase < 'A' || upperCase > 'Z') {
                    return -1;
                }
                return (length == 2 || !isSeparator(str.charAt(2))) ? 2 : 3;
            }
            if (!isSeparator(cCharAt) || !isSeparator(cCharAt2)) {
                return isSeparator(cCharAt) ? 1 : 0;
            }
            int iIndexOf3 = str.indexOf(47, 2);
            int iIndexOf4 = str.indexOf(92, 2);
            if ((iIndexOf3 == -1 && iIndexOf4 == -1) || iIndexOf3 == 2 || iIndexOf4 == 2) {
                return -1;
            }
            if (iIndexOf3 == -1) {
                iIndexOf3 = iIndexOf4;
            }
            if (iIndexOf4 == -1) {
                iIndexOf4 = iIndexOf3;
            }
            iMin = Math.min(iIndexOf3, iIndexOf4);
        }
        return iMin + 1;
    }

    public static int indexOfExtension(String str) {
        int iLastIndexOf;
        if (str != null && indexOfLastSeparator(str) <= (iLastIndexOf = str.lastIndexOf(46))) {
            return iLastIndexOf;
        }
        return -1;
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }

    public static boolean isExtension(String str, String str2) {
        if (str == null) {
            return false;
        }
        return (str2 == null || str2.length() == 0) ? indexOfExtension(str) == -1 : getExtension(str).equals(str2);
    }

    private static boolean isSeparator(char c9) {
        return c9 == '/' || c9 == '\\';
    }

    public static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    public static String normalize(String str) {
        return doNormalize(str, true);
    }

    public static String normalizeNoEndSeparator(String str) {
        return doNormalize(str, false);
    }

    public static String removeExtension(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOfExtension = indexOfExtension(str);
        return iIndexOfExtension == -1 ? str : str.substring(0, iIndexOfExtension);
    }

    public static String separatorsToSystem(String str) {
        if (str == null) {
            return null;
        }
        return isSystemWindows() ? separatorsToWindows(str) : separatorsToUnix(str);
    }

    public static String separatorsToUnix(String str) {
        return (str == null || str.indexOf(92) == -1) ? str : str.replace('\\', '/');
    }

    public static String separatorsToWindows(String str) {
        return (str == null || str.indexOf(47) == -1) ? str : str.replace('/', '\\');
    }

    public static String[] splitOnTokens(String str) {
        if (str.indexOf("?") == -1 && str.indexOf("*") == -1) {
            return new String[]{str};
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i9 = 0; i9 < charArray.length; i9++) {
            char c9 = charArray[i9];
            if (c9 == '?' || c9 == '*') {
                if (stringBuffer.length() != 0) {
                    arrayList.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                }
                if (charArray[i9] == '?') {
                    arrayList.add("?");
                } else if (arrayList.size() == 0 || (i9 > 0 && !arrayList.get(arrayList.size() - 1).equals("*"))) {
                    arrayList.add("*");
                }
            } else {
                stringBuffer.append(c9);
            }
        }
        if (stringBuffer.length() != 0) {
            arrayList.add(stringBuffer.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean wildcardMatch(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatchOnSystem(String str, String str2) {
        return wildcardMatch(str, str2, IOCase.SYSTEM);
    }

    public static boolean equals(String str, String str2, boolean z8, IOCase iOCase) {
        if (str == null || str2 == null) {
            return str == str2;
        }
        if (z8) {
            str = normalize(str);
            str2 = normalize(str2);
        }
        if (iOCase == null) {
            iOCase = IOCase.SENSITIVE;
        }
        return iOCase.checkEquals(str, str2);
    }

    public static boolean wildcardMatch(String str, String str2, IOCase iOCase) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str != null && str2 != null) {
            if (iOCase == null) {
                iOCase = IOCase.SENSITIVE;
            }
            String strConvertCase = iOCase.convertCase(str);
            String[] strArrSplitOnTokens = splitOnTokens(iOCase.convertCase(str2));
            Stack stack = new Stack();
            boolean z8 = false;
            int length = 0;
            int i9 = 0;
            do {
                if (stack.size() > 0) {
                    int[] iArr = (int[]) stack.pop();
                    i9 = iArr[0];
                    length = iArr[1];
                    z8 = true;
                }
                while (i9 < strArrSplitOnTokens.length) {
                    if (strArrSplitOnTokens[i9].equals("?")) {
                        length++;
                    } else if (strArrSplitOnTokens[i9].equals("*")) {
                        if (i9 == strArrSplitOnTokens.length - 1) {
                            length = strConvertCase.length();
                        }
                        z8 = true;
                        i9++;
                    } else {
                        if (z8) {
                            length = strConvertCase.indexOf(strArrSplitOnTokens[i9], length);
                            if (length == -1) {
                                break;
                            }
                            int iIndexOf = strConvertCase.indexOf(strArrSplitOnTokens[i9], length + 1);
                            if (iIndexOf >= 0) {
                                stack.push(new int[]{i9, iIndexOf});
                            }
                            length += strArrSplitOnTokens[i9].length();
                        } else {
                            if (!strConvertCase.startsWith(strArrSplitOnTokens[i9], length)) {
                                break;
                            }
                            length += strArrSplitOnTokens[i9].length();
                        }
                        i9++;
                    }
                    z8 = false;
                    i9++;
                }
                if (i9 == strArrSplitOnTokens.length && length == strConvertCase.length()) {
                    return true;
                }
            } while (stack.size() > 0);
        }
        return false;
    }

    public static boolean isExtension(String str, String[] strArr) {
        if (str == null) {
            return false;
        }
        if (strArr == null || strArr.length == 0) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        for (String str2 : strArr) {
            if (extension.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtension(String str, Collection collection) {
        if (str == null) {
            return false;
        }
        if (collection == null || collection.isEmpty()) {
            return indexOfExtension(str) == -1;
        }
        String extension = getExtension(str);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (extension.equals(it.next())) {
                return true;
            }
        }
        return false;
    }
}
