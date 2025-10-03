package org.apache.commons.lang3;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class StringUtils {

    /* renamed from: CR */
    public static final String f19107CR = "\r";
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    /* renamed from: LF */
    public static final String f19108LF = "\n";
    private static final int PAD_LIMIT = 8192;
    public static final String SPACE = " ";
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("(?: |\\u00A0|\\s|[\\s&&[^ ]])\\s*");

    public static String abbreviate(String str, int i9) {
        return abbreviate(str, 0, i9);
    }

    public static String abbreviateMiddle(String str, String str2, int i9) {
        if (isEmpty(str) || isEmpty(str2) || i9 >= str.length() || i9 < str2.length() + 2) {
            return str;
        }
        int length = i9 - str2.length();
        int i10 = length / 2;
        int i11 = (length % 2) + i10;
        int length2 = str.length() - i10;
        StringBuilder sb = new StringBuilder(i9);
        sb.append(str.substring(0, i11));
        sb.append(str2);
        sb.append(str.substring(length2));
        return sb.toString();
    }

    private static String appendIfMissing(String str, CharSequence charSequence, boolean z8, CharSequence... charSequenceArr) {
        if (str == null || isEmpty(charSequence) || endsWith(str, charSequence, z8)) {
            return str;
        }
        if (charSequenceArr != null && charSequenceArr.length > 0) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (endsWith(str, charSequence2, z8)) {
                    return str;
                }
            }
        }
        return str + charSequence.toString();
    }

    public static String appendIfMissingIgnoreCase(String str, CharSequence charSequence, CharSequence... charSequenceArr) {
        return appendIfMissing(str, charSequence, true, charSequenceArr);
    }

    public static String capitalize(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        char cCharAt = str.charAt(0);
        if (Character.isTitleCase(cCharAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(Character.toTitleCase(cCharAt));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String center(String str, int i9) {
        return center(str, i9, ' ');
    }

    public static String chomp(String str) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() == 1) {
            char cCharAt = str.charAt(0);
            return (cCharAt == '\r' || cCharAt == '\n') ? "" : str;
        }
        int length = str.length() - 1;
        char cCharAt2 = str.charAt(length);
        if (cCharAt2 == '\n') {
            if (str.charAt(length - 1) == '\r') {
                length--;
            }
        } else if (cCharAt2 != '\r') {
            length++;
        }
        return str.substring(0, length);
    }

    public static String chop(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length < 2) {
            return "";
        }
        int i9 = length - 1;
        String strSubstring = str.substring(0, i9);
        if (str.charAt(i9) == '\n') {
            int i10 = i9 - 1;
            if (strSubstring.charAt(i10) == '\r') {
                return strSubstring.substring(0, i10);
            }
        }
        return strSubstring;
    }

    private static int commonPrefixLength(CharSequence charSequence, CharSequence charSequence2) {
        int length = getCommonPrefix(charSequence.toString(), charSequence2.toString()).length();
        if (length > 4) {
            return 4;
        }
        return length;
    }

    public static boolean contains(CharSequence charSequence, int i9) {
        return !isEmpty(charSequence) && CharSequenceUtils.indexOf(charSequence, i9, 0) >= 0;
    }

    public static boolean containsAny(CharSequence charSequence, char... cArr) {
        if (!isEmpty(charSequence) && !ArrayUtils.isEmpty(cArr)) {
            int length = charSequence.length();
            int length2 = cArr.length;
            int i9 = length - 1;
            int i10 = length2 - 1;
            for (int i11 = 0; i11 < length; i11++) {
                char cCharAt = charSequence.charAt(i11);
                for (int i12 = 0; i12 < length2; i12++) {
                    if (cArr[i12] == cCharAt) {
                        if (!Character.isHighSurrogate(cCharAt) || i12 == i10) {
                            return true;
                        }
                        if (i11 < i9 && cArr[i12 + 1] == charSequence.charAt(i11 + 1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean containsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null && charSequence2 != null) {
            int length = charSequence2.length();
            int length2 = charSequence.length() - length;
            for (int i9 = 0; i9 <= length2; i9++) {
                if (CharSequenceUtils.regionMatches(charSequence, true, i9, charSequence2, 0, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNone(CharSequence charSequence, char... cArr) {
        if (charSequence != null && cArr != null) {
            int length = charSequence.length();
            int i9 = length - 1;
            int length2 = cArr.length;
            int i10 = length2 - 1;
            for (int i11 = 0; i11 < length; i11++) {
                char cCharAt = charSequence.charAt(i11);
                for (int i12 = 0; i12 < length2; i12++) {
                    if (cArr[i12] == cCharAt) {
                        if (!Character.isHighSurrogate(cCharAt) || i12 == i10) {
                            return false;
                        }
                        if (i11 < i9 && cArr[i12 + 1] == charSequence.charAt(i11 + 1)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean containsOnly(CharSequence charSequence, char... cArr) {
        if (cArr == null || charSequence == null) {
            return false;
        }
        if (charSequence.length() == 0) {
            return true;
        }
        return cArr.length != 0 && indexOfAnyBut(charSequence, cArr) == -1;
    }

    public static boolean containsWhitespace(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (Character.isWhitespace(charSequence.charAt(i9))) {
                return true;
            }
        }
        return false;
    }

    public static int countMatches(CharSequence charSequence, CharSequence charSequence2) {
        int length = 0;
        if (isEmpty(charSequence) || isEmpty(charSequence2)) {
            return 0;
        }
        int i9 = 0;
        while (true) {
            int iIndexOf = CharSequenceUtils.indexOf(charSequence, charSequence2, length);
            if (iIndexOf == -1) {
                return i9;
            }
            i9++;
            length = iIndexOf + charSequence2.length();
        }
    }

    public static <T extends CharSequence> T defaultIfBlank(T t8, T t9) {
        return isBlank(t8) ? t9 : t8;
    }

    public static <T extends CharSequence> T defaultIfEmpty(T t8, T t9) {
        return isEmpty(t8) ? t9 : t8;
    }

    public static String defaultString(String str) {
        return str == null ? "" : str;
    }

    public static String defaultString(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i9 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            if (!Character.isWhitespace(str.charAt(i10))) {
                cArr[i9] = str.charAt(i10);
                i9++;
            }
        }
        return i9 == length ? str : new String(cArr, 0, i9);
    }

    public static String difference(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        int iIndexOfDifference = indexOfDifference(str, str2);
        return iIndexOfDifference == -1 ? "" : str2.substring(iIndexOfDifference);
    }

    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        return endsWith(charSequence, charSequence2, false);
    }

    public static boolean endsWithAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (!isEmpty(charSequence) && !ArrayUtils.isEmpty(charSequenceArr)) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (endsWith(charSequence, charSequence2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean endsWithIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return endsWith(charSequence, charSequence2, true);
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        return ((charSequence instanceof String) && (charSequence2 instanceof String)) ? charSequence.equals(charSequence2) : CharSequenceUtils.regionMatches(charSequence, false, 0, charSequence2, 0, Math.max(charSequence.length(), charSequence2.length()));
    }

    public static boolean equalsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return charSequence == charSequence2;
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence.length() != charSequence2.length()) {
            return false;
        }
        return CharSequenceUtils.regionMatches(charSequence, true, 0, charSequence2, 0, charSequence.length());
    }

    public static String getCommonPrefix(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        int iIndexOfDifference = indexOfDifference(strArr);
        if (iIndexOfDifference != -1) {
            return iIndexOfDifference == 0 ? "" : strArr[0].substring(0, iIndexOfDifference);
        }
        String str = strArr[0];
        return str == null ? "" : str;
    }

    public static double getJaroWinklerDistance(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        double dScore = score(charSequence, charSequence2);
        return Math.round((dScore + ((commonPrefixLength(charSequence, charSequence2) * 0.1d) * (1.0d - dScore))) * 100.0d) / 100.0d;
    }

    public static int getLevenshteinDistance(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        if (length > length2) {
            length2 = charSequence.length();
            length = length2;
        } else {
            charSequence2 = charSequence;
            charSequence = charSequence2;
        }
        int i9 = length + 1;
        int[] iArr = new int[i9];
        int[] iArr2 = new int[i9];
        for (int i10 = 0; i10 <= length; i10++) {
            iArr[i10] = i10;
        }
        int i11 = 1;
        while (i11 <= length2) {
            char cCharAt = charSequence.charAt(i11 - 1);
            iArr2[0] = i11;
            for (int i12 = 1; i12 <= length; i12++) {
                int i13 = i12 - 1;
                iArr2[i12] = Math.min(Math.min(iArr2[i13] + 1, iArr[i12] + 1), iArr[i13] + (charSequence2.charAt(i13) == cCharAt ? 0 : 1));
            }
            i11++;
            int[] iArr3 = iArr;
            iArr = iArr2;
            iArr2 = iArr3;
        }
        return iArr[length];
    }

    private static String getSetOfMatchingCharacterWithin(CharSequence charSequence, CharSequence charSequence2, int i9) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder(charSequence2);
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            char cCharAt = charSequence.charAt(i10);
            boolean z8 = false;
            for (int iMax = Math.max(0, i10 - i9); !z8 && iMax < Math.min(i10 + i9, charSequence2.length()); iMax++) {
                if (sb2.charAt(iMax) == cCharAt) {
                    sb.append(cCharAt);
                    sb2.setCharAt(iMax, '*');
                    z8 = true;
                }
            }
        }
        return sb.toString();
    }

    public static int indexOf(CharSequence charSequence, int i9) {
        if (isEmpty(charSequence)) {
            return -1;
        }
        return CharSequenceUtils.indexOf(charSequence, i9, 0);
    }

    public static int indexOfAny(CharSequence charSequence, char... cArr) {
        if (!isEmpty(charSequence) && !ArrayUtils.isEmpty(cArr)) {
            int length = charSequence.length();
            int i9 = length - 1;
            int length2 = cArr.length;
            int i10 = length2 - 1;
            for (int i11 = 0; i11 < length; i11++) {
                char cCharAt = charSequence.charAt(i11);
                for (int i12 = 0; i12 < length2; i12++) {
                    if (cArr[i12] == cCharAt && (i11 >= i9 || i12 >= i10 || !Character.isHighSurrogate(cCharAt) || cArr[i12 + 1] == charSequence.charAt(i11 + 1))) {
                        return i11;
                    }
                }
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
    
        r6 = r6 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOfAnyBut(CharSequence charSequence, char... cArr) {
        int i9;
        if (!isEmpty(charSequence) && !ArrayUtils.isEmpty(cArr)) {
            int length = charSequence.length();
            int i10 = length - 1;
            int length2 = cArr.length;
            int i11 = length2 - 1;
            int i12 = 0;
            while (i12 < length) {
                char cCharAt = charSequence.charAt(i12);
                for (0; i9 < length2; i9 + 1) {
                    i9 = (cArr[i9] != cCharAt || (i12 < i10 && i9 < i11 && Character.isHighSurrogate(cCharAt) && cArr[i9 + 1] != charSequence.charAt(i12 + 1))) ? i9 + 1 : 0;
                }
                return i12;
            }
        }
        return -1;
    }

    public static int indexOfDifference(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return -1;
        }
        int i9 = 0;
        if (charSequence != null && charSequence2 != null) {
            while (i9 < charSequence.length() && i9 < charSequence2.length() && charSequence.charAt(i9) == charSequence2.charAt(i9)) {
                i9++;
            }
            if (i9 >= charSequence2.length() && i9 >= charSequence.length()) {
                return -1;
            }
        }
        return i9;
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return indexOfIgnoreCase(charSequence, charSequence2, 0);
    }

    public static boolean isAllLowerCase(CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isLowerCase(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllUpperCase(CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isUpperCase(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlpha(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isLetter(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isLetter(charSequence.charAt(i9)) && charSequence.charAt(i9) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumeric(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isLetterOrDigit(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumericSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isLetterOrDigit(charSequence.charAt(i9)) && charSequence.charAt(i9) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyBlank(CharSequence... charSequenceArr) {
        if (ArrayUtils.isEmpty(charSequenceArr)) {
            return true;
        }
        for (CharSequence charSequence : charSequenceArr) {
            if (isBlank(charSequence)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyEmpty(CharSequence... charSequenceArr) {
        if (ArrayUtils.isEmpty(charSequenceArr)) {
            return true;
        }
        for (CharSequence charSequence : charSequenceArr) {
            if (isEmpty(charSequence)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAsciiPrintable(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!CharUtils.isAsciiPrintable(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(CharSequence charSequence) {
        int length;
        if (charSequence != null && (length = charSequence.length()) != 0) {
            for (int i9 = 0; i9 < length; i9++) {
                if (!Character.isWhitespace(charSequence.charAt(i9))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNoneBlank(CharSequence... charSequenceArr) {
        return !isAnyBlank(charSequenceArr);
    }

    public static boolean isNoneEmpty(CharSequence... charSequenceArr) {
        return !isAnyEmpty(charSequenceArr);
    }

    public static boolean isNotBlank(CharSequence charSequence) {
        return !isBlank(charSequence);
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isNumeric(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isDigit(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isDigit(charSequence.charAt(i9)) && charSequence.charAt(i9) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isWhitespace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isWhitespace(charSequence.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    public static <T> String join(T... tArr) {
        return join(tArr, (String) null);
    }

    public static int lastIndexOf(CharSequence charSequence, int i9) {
        if (isEmpty(charSequence)) {
            return -1;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, i9, charSequence.length());
    }

    public static int lastIndexOfAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        int iLastIndexOf;
        int i9 = -1;
        if (charSequence != null && charSequenceArr != null) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (charSequence2 != null && (iLastIndexOf = CharSequenceUtils.lastIndexOf(charSequence, charSequence2, charSequence.length())) > i9) {
                    i9 = iLastIndexOf;
                }
            }
        }
        return i9;
    }

    public static int lastIndexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        return lastIndexOfIgnoreCase(charSequence, charSequence2, charSequence.length());
    }

    public static int lastOrdinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        return ordinalIndexOf(charSequence, charSequence2, i9, true);
    }

    public static String left(String str, int i9) {
        if (str == null) {
            return null;
        }
        return i9 < 0 ? "" : str.length() <= i9 ? str : str.substring(0, i9);
    }

    public static String leftPad(String str, int i9) {
        return leftPad(str, i9, ' ');
    }

    public static int length(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    public static String mid(String str, int i9, int i10) {
        if (str == null) {
            return null;
        }
        if (i10 < 0 || i9 > str.length()) {
            return "";
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int i11 = i10 + i9;
        return str.length() <= i11 ? str.substring(i9) : str.substring(i9, i11);
    }

    public static String normalizeSpace(String str) {
        if (str == null) {
            return null;
        }
        return WHITESPACE_PATTERN.matcher(trim(str)).replaceAll(SPACE);
    }

    public static int ordinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        return ordinalIndexOf(charSequence, charSequence2, i9, false);
    }

    public static String overlay(String str, String str2, int i9, int i10) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        int length = str.length();
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 > length) {
            i9 = length;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        if (i10 > length) {
            i10 = length;
        }
        if (i9 > i10) {
            int i11 = i10;
            i10 = i9;
            i9 = i11;
        }
        StringBuilder sb = new StringBuilder(((length + i9) - i10) + str2.length() + 1);
        sb.append(str.substring(0, i9));
        sb.append(str2);
        sb.append(str.substring(i10));
        return sb.toString();
    }

    private static String prependIfMissing(String str, CharSequence charSequence, boolean z8, CharSequence... charSequenceArr) {
        if (str == null || isEmpty(charSequence) || startsWith(str, charSequence, z8)) {
            return str;
        }
        if (charSequenceArr != null && charSequenceArr.length > 0) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (startsWith(str, charSequence2, z8)) {
                    return str;
                }
            }
        }
        return charSequence.toString() + str;
    }

    public static String prependIfMissingIgnoreCase(String str, CharSequence charSequence, CharSequence... charSequenceArr) {
        return prependIfMissing(str, charSequence, true, charSequenceArr);
    }

    public static String remove(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2)) ? str : replace(str, str2, "", -1);
    }

    public static String removeEnd(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2) || !str.endsWith(str2)) ? str : str.substring(0, str.length() - str2.length());
    }

    public static String removeEndIgnoreCase(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2) || !endsWithIgnoreCase(str, str2)) ? str : str.substring(0, str.length() - str2.length());
    }

    public static String removePattern(String str, String str2) {
        return replacePattern(str, str2, "");
    }

    public static String removeStart(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2) || !str.startsWith(str2)) ? str : str.substring(str2.length());
    }

    public static String removeStartIgnoreCase(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2) || !startsWithIgnoreCase(str, str2)) ? str : str.substring(str2.length());
    }

    public static String repeat(String str, int i9) {
        if (str == null) {
            return null;
        }
        if (i9 <= 0) {
            return "";
        }
        int length = str.length();
        if (i9 == 1 || length == 0) {
            return str;
        }
        if (length == 1 && i9 <= 8192) {
            return repeat(str.charAt(0), i9);
        }
        int i10 = length * i9;
        if (length == 1) {
            return repeat(str.charAt(0), i9);
        }
        if (length != 2) {
            StringBuilder sb = new StringBuilder(i10);
            for (int i11 = 0; i11 < i9; i11++) {
                sb.append(str);
            }
            return sb.toString();
        }
        char cCharAt = str.charAt(0);
        char cCharAt2 = str.charAt(1);
        char[] cArr = new char[i10];
        for (int i12 = (i9 * 2) - 2; i12 >= 0; i12 = (i12 - 1) - 1) {
            cArr[i12] = cCharAt;
            cArr[i12 + 1] = cCharAt2;
        }
        return new String(cArr);
    }

    public static String replace(String str, String str2, String str3) {
        return replace(str, str2, str3, -1);
    }

    public static String replaceChars(String str, char c9, char c10) {
        if (str == null) {
            return null;
        }
        return str.replace(c9, c10);
    }

    public static String replaceEach(String str, String[] strArr, String[] strArr2) {
        return replaceEach(str, strArr, strArr2, false, 0);
    }

    public static String replaceEachRepeatedly(String str, String[] strArr, String[] strArr2) {
        return replaceEach(str, strArr, strArr2, true, strArr == null ? 0 : strArr.length);
    }

    public static String replaceOnce(String str, String str2, String str3) {
        return replace(str, str2, str3, 1);
    }

    public static String replacePattern(String str, String str2, String str3) {
        return Pattern.compile(str2, 32).matcher(str).replaceAll(str3);
    }

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverseDelimited(String str, char c9) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = split(str, c9);
        ArrayUtils.reverse(strArrSplit);
        return join(strArrSplit, c9);
    }

    public static String right(String str, int i9) {
        if (str == null) {
            return null;
        }
        return i9 < 0 ? "" : str.length() <= i9 ? str : str.substring(str.length() - i9);
    }

    public static String rightPad(String str, int i9) {
        return rightPad(str, i9, ' ');
    }

    private static double score(CharSequence charSequence, CharSequence charSequence2) {
        String lowerCase;
        String lowerCase2;
        if (charSequence.length() > charSequence2.length()) {
            lowerCase2 = charSequence.toString().toLowerCase();
            lowerCase = charSequence2.toString().toLowerCase();
        } else {
            String lowerCase3 = charSequence2.toString().toLowerCase();
            lowerCase = charSequence.toString().toLowerCase();
            lowerCase2 = lowerCase3;
        }
        int length = (lowerCase.length() / 2) + 1;
        String setOfMatchingCharacterWithin = getSetOfMatchingCharacterWithin(lowerCase, lowerCase2, length);
        String setOfMatchingCharacterWithin2 = getSetOfMatchingCharacterWithin(lowerCase2, lowerCase, length);
        if (setOfMatchingCharacterWithin.length() == 0 || setOfMatchingCharacterWithin2.length() == 0 || setOfMatchingCharacterWithin.length() != setOfMatchingCharacterWithin2.length()) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return (((setOfMatchingCharacterWithin.length() / lowerCase.length()) + (setOfMatchingCharacterWithin2.length() / lowerCase2.length())) + ((setOfMatchingCharacterWithin.length() - transpositions(setOfMatchingCharacterWithin, setOfMatchingCharacterWithin2)) / setOfMatchingCharacterWithin.length())) / 3.0d;
    }

    public static String[] split(String str) {
        return split(str, null, -1);
    }

    public static String[] splitByCharacterType(String str) {
        return splitByCharacterType(str, false);
    }

    public static String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

    public static String[] splitByWholeSeparator(String str, String str2) {
        return splitByWholeSeparatorWorker(str, str2, -1, false);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String str2) {
        return splitByWholeSeparatorWorker(str, str2, -1, true);
    }

    private static String[] splitByWholeSeparatorWorker(String str, String str2, int i9, boolean z8) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (str2 == null || "".equals(str2)) {
            return splitWorker(str, null, i9, z8);
        }
        int length2 = str2.length();
        ArrayList arrayList = new ArrayList();
        int iIndexOf = 0;
        int i10 = 0;
        int i11 = 0;
        while (iIndexOf < length) {
            iIndexOf = str.indexOf(str2, i10);
            if (iIndexOf > -1) {
                if (iIndexOf > i10) {
                    i11++;
                    if (i11 == i9) {
                        arrayList.add(str.substring(i10));
                    } else {
                        arrayList.add(str.substring(i10, iIndexOf));
                    }
                } else if (z8) {
                    i11++;
                    if (i11 == i9) {
                        arrayList.add(str.substring(i10));
                        iIndexOf = length;
                    } else {
                        arrayList.add("");
                    }
                }
                i10 = iIndexOf + length2;
            } else {
                arrayList.add(str.substring(i10));
            }
            iIndexOf = length;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] splitPreserveAllTokens(String str) {
        return splitWorker(str, null, -1, true);
    }

    private static String[] splitWorker(String str, char c9, boolean z8) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        boolean z9 = false;
        boolean z10 = false;
        int i10 = 0;
        while (i9 < length) {
            if (str.charAt(i9) == c9) {
                if (z9 || z8) {
                    arrayList.add(str.substring(i10, i9));
                    z9 = false;
                    z10 = true;
                }
                i10 = i9 + 1;
                i9 = i10;
            } else {
                i9++;
                z10 = false;
                z9 = true;
            }
        }
        if (z9 || (z8 && z10)) {
            arrayList.add(str.substring(i10, i9));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        return startsWith(charSequence, charSequence2, false);
    }

    public static boolean startsWithAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (!isEmpty(charSequence) && !ArrayUtils.isEmpty(charSequenceArr)) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (startsWith(charSequence, charSequence2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startsWithIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return startsWith(charSequence, charSequence2, true);
    }

    public static String strip(String str) {
        return strip(str, null);
    }

    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(Normalizer.normalize(str, Normalizer.Form.NFD)).replaceAll("");
    }

    public static String[] stripAll(String... strArr) {
        return stripAll(strArr, null);
    }

    public static String stripEnd(String str, String str2) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        if (str2 == null) {
            while (length != 0 && Character.isWhitespace(str.charAt(length - 1))) {
                length--;
            }
        } else {
            if (str2.isEmpty()) {
                return str;
            }
            while (length != 0 && str2.indexOf(str.charAt(length - 1)) != -1) {
                length--;
            }
        }
        return str.substring(0, length);
    }

    public static String stripStart(String str, String str2) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        int i9 = 0;
        if (str2 == null) {
            while (i9 != length && Character.isWhitespace(str.charAt(i9))) {
                i9++;
            }
        } else {
            if (str2.isEmpty()) {
                return str;
            }
            while (i9 != length && str2.indexOf(str.charAt(i9)) != -1) {
                i9++;
            }
        }
        return str.substring(i9);
    }

    public static String stripToEmpty(String str) {
        return str == null ? "" : strip(str, null);
    }

    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        String strStrip = strip(str, null);
        if (strStrip.isEmpty()) {
            return null;
        }
        return strStrip;
    }

    public static String substring(String str, int i9) {
        if (str == null) {
            return null;
        }
        if (i9 < 0) {
            i9 += str.length();
        }
        if (i9 < 0) {
            i9 = 0;
        }
        return i9 > str.length() ? "" : str.substring(i9);
    }

    public static String substringAfter(String str, String str2) {
        int iIndexOf;
        return isEmpty(str) ? str : (str2 == null || (iIndexOf = str.indexOf(str2)) == -1) ? "" : str.substring(iIndexOf + str2.length());
    }

    public static String substringAfterLast(String str, String str2) {
        int iLastIndexOf;
        return isEmpty(str) ? str : (isEmpty(str2) || (iLastIndexOf = str.lastIndexOf(str2)) == -1 || iLastIndexOf == str.length() - str2.length()) ? "" : str.substring(iLastIndexOf + str2.length());
    }

    public static String substringBefore(String str, String str2) {
        if (isEmpty(str) || str2 == null) {
            return str;
        }
        if (str2.isEmpty()) {
            return "";
        }
        int iIndexOf = str.indexOf(str2);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }

    public static String substringBeforeLast(String str, String str2) {
        int iLastIndexOf;
        return (isEmpty(str) || isEmpty(str2) || (iLastIndexOf = str.lastIndexOf(str2)) == -1) ? str : str.substring(0, iLastIndexOf);
    }

    public static String substringBetween(String str, String str2) {
        return substringBetween(str, str2, str2);
    }

    public static String[] substringsBetween(String str, String str2, String str3) {
        int iIndexOf;
        int i9;
        int iIndexOf2;
        if (str == null || isEmpty(str2) || isEmpty(str3)) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        int length2 = str3.length();
        int length3 = str2.length();
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (i10 < length - length2 && (iIndexOf = str.indexOf(str2, i10)) >= 0 && (iIndexOf2 = str.indexOf(str3, (i9 = iIndexOf + length3))) >= 0) {
            arrayList.add(str.substring(i9, iIndexOf2));
            i10 = iIndexOf2 + length2;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String swapCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i9 = 0; i9 < charArray.length; i9++) {
            char c9 = charArray[i9];
            if (Character.isUpperCase(c9)) {
                charArray[i9] = Character.toLowerCase(c9);
            } else if (Character.isTitleCase(c9)) {
                charArray[i9] = Character.toLowerCase(c9);
            } else if (Character.isLowerCase(c9)) {
                charArray[i9] = Character.toUpperCase(c9);
            }
        }
        return new String(charArray);
    }

    public static String toEncodedString(byte[] bArr, Charset charset) {
        if (charset == null) {
            charset = Charset.defaultCharset();
        }
        return new String(bArr, charset);
    }

    @Deprecated
    public static String toString(byte[] bArr, String str) {
        return str != null ? new String(bArr, str) : new String(bArr, Charset.defaultCharset());
    }

    private static int transpositions(CharSequence charSequence, CharSequence charSequence2) {
        int i9 = 0;
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            if (charSequence.charAt(i10) != charSequence2.charAt(i10)) {
                i9++;
            }
        }
        return i9 / 2;
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String trimToNull(String str) {
        String strTrim = trim(str);
        if (isEmpty(strTrim)) {
            return null;
        }
        return strTrim;
    }

    public static String uncapitalize(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        char cCharAt = str.charAt(0);
        if (Character.isLowerCase(cCharAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(Character.toLowerCase(cCharAt));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static String abbreviate(String str, int i9, int i10) {
        if (str == null) {
            return null;
        }
        if (i10 < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (str.length() <= i10) {
            return str;
        }
        if (i9 > str.length()) {
            i9 = str.length();
        }
        int i11 = i10 - 3;
        if (str.length() - i9 < i11) {
            i9 = str.length() - i11;
        }
        if (i9 <= 4) {
            return str.substring(0, i11) + "...";
        }
        if (i10 < 7) {
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        }
        if ((i10 + i9) - 3 < str.length()) {
            return "..." + abbreviate(str.substring(i9), i11);
        }
        return "..." + str.substring(str.length() - i11);
    }

    public static String center(String str, int i9, char c9) {
        int length;
        int length2;
        return (str == null || i9 <= 0 || (length2 = i9 - (length = str.length())) <= 0) ? str : rightPad(leftPad(str, length + (length2 / 2), c9), i9, c9);
    }

    private static boolean endsWith(CharSequence charSequence, CharSequence charSequence2, boolean z8) {
        if (charSequence == null || charSequence2 == null) {
            return charSequence == null && charSequence2 == null;
        }
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        return CharSequenceUtils.regionMatches(charSequence, z8, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length());
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i9) {
        if (charSequence != null && charSequence2 != null) {
            if (i9 < 0) {
                i9 = 0;
            }
            int length = (charSequence.length() - charSequence2.length()) + 1;
            if (i9 > length) {
                return -1;
            }
            if (charSequence2.length() == 0) {
                return i9;
            }
            while (i9 < length) {
                if (CharSequenceUtils.regionMatches(charSequence, true, i9, charSequence2, 0, charSequence2.length())) {
                    return i9;
                }
                i9++;
            }
        }
        return -1;
    }

    public static String join(Object[] objArr, char c9) {
        if (objArr == null) {
            return null;
        }
        return join(objArr, c9, 0, objArr.length);
    }

    public static int lastIndexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i9) {
        if (charSequence != null && charSequence2 != null) {
            if (i9 > charSequence.length() - charSequence2.length()) {
                i9 = charSequence.length() - charSequence2.length();
            }
            if (i9 < 0) {
                return -1;
            }
            if (charSequence2.length() == 0) {
                return i9;
            }
            while (i9 >= 0) {
                if (CharSequenceUtils.regionMatches(charSequence, true, i9, charSequence2, 0, charSequence2.length())) {
                    return i9;
                }
                i9--;
            }
        }
        return -1;
    }

    public static String leftPad(String str, int i9, char c9) {
        if (str == null) {
            return null;
        }
        int length = i9 - str.length();
        return length <= 0 ? str : length > 8192 ? leftPad(str, i9, String.valueOf(c9)) : repeat(c9, length).concat(str);
    }

    public static String lowerCase(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(locale);
    }

    private static int ordinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i9, boolean z8) {
        if (charSequence != null && charSequence2 != null && i9 > 0) {
            int i10 = 0;
            if (charSequence2.length() == 0) {
                if (z8) {
                    return charSequence.length();
                }
                return 0;
            }
            length = z8 ? charSequence.length() : -1;
            do {
                length = z8 ? CharSequenceUtils.lastIndexOf(charSequence, charSequence2, length - 1) : CharSequenceUtils.indexOf(charSequence, charSequence2, length + 1);
                if (length < 0) {
                    return length;
                }
                i10++;
            } while (i10 < i9);
        }
        return length;
    }

    public static String replace(String str, String str2, String str3, int i9) {
        int i10;
        if (isEmpty(str) || isEmpty(str2) || str3 == null || i9 == 0) {
            return str;
        }
        int i11 = 0;
        int iIndexOf = str.indexOf(str2, 0);
        if (iIndexOf == -1) {
            return str;
        }
        int length = str2.length();
        int length2 = str3.length() - length;
        if (length2 < 0) {
            length2 = 0;
        }
        if (i9 < 0) {
            i10 = 16;
        } else {
            i10 = 64;
            if (i9 <= 64) {
                i10 = i9;
            }
        }
        StringBuilder sb = new StringBuilder(str.length() + (length2 * i10));
        while (iIndexOf != -1) {
            sb.append(str.substring(i11, iIndexOf));
            sb.append(str3);
            i11 = iIndexOf + length;
            i9--;
            if (i9 == 0) {
                break;
            }
            iIndexOf = str.indexOf(str2, i11);
        }
        sb.append(str.substring(i11));
        return sb.toString();
    }

    public static String replaceChars(String str, String str2, String str3) {
        if (isEmpty(str) || isEmpty(str2)) {
            return str;
        }
        if (str3 == null) {
            str3 = "";
        }
        int length = str3.length();
        int length2 = str.length();
        StringBuilder sb = new StringBuilder(length2);
        boolean z8 = false;
        for (int i9 = 0; i9 < length2; i9++) {
            char cCharAt = str.charAt(i9);
            int iIndexOf = str2.indexOf(cCharAt);
            if (iIndexOf >= 0) {
                if (iIndexOf < length) {
                    sb.append(str3.charAt(iIndexOf));
                }
                z8 = true;
            } else {
                sb.append(cCharAt);
            }
        }
        return z8 ? sb.toString() : str;
    }

    private static String replaceEach(String str, String[] strArr, String[] strArr2, boolean z8, int i9) {
        String str2;
        String str3;
        int length;
        String str4;
        if (str == null || str.isEmpty() || strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0) {
            return str;
        }
        if (i9 < 0) {
            throw new IllegalStateException("Aborting to protect against StackOverflowError - output of one loop is the input of another");
        }
        int length2 = strArr.length;
        int length3 = strArr2.length;
        if (length2 != length3) {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: " + length2 + " vs " + length3);
        }
        boolean[] zArr = new boolean[length2];
        int i10 = -1;
        int i11 = -1;
        for (int i12 = 0; i12 < length2; i12++) {
            if (!zArr[i12] && (str4 = strArr[i12]) != null && !str4.isEmpty() && strArr2[i12] != null) {
                int iIndexOf = str.indexOf(strArr[i12]);
                if (iIndexOf == -1) {
                    zArr[i12] = true;
                } else if (i10 == -1 || iIndexOf < i10) {
                    i11 = i12;
                    i10 = iIndexOf;
                }
            }
        }
        if (i10 == -1) {
            return str;
        }
        int i13 = 0;
        for (int i14 = 0; i14 < strArr.length; i14++) {
            if (strArr[i14] != null && (str3 = strArr2[i14]) != null && (length = str3.length() - strArr[i14].length()) > 0) {
                i13 += length * 3;
            }
        }
        StringBuilder sb = new StringBuilder(str.length() + Math.min(i13, str.length() / 5));
        int length4 = 0;
        while (i10 != -1) {
            while (length4 < i10) {
                sb.append(str.charAt(length4));
                length4++;
            }
            sb.append(strArr2[i11]);
            length4 = strArr[i11].length() + i10;
            i10 = -1;
            i11 = -1;
            for (int i15 = 0; i15 < length2; i15++) {
                if (!zArr[i15] && (str2 = strArr[i15]) != null && !str2.isEmpty() && strArr2[i15] != null) {
                    int iIndexOf2 = str.indexOf(strArr[i15], length4);
                    if (iIndexOf2 == -1) {
                        zArr[i15] = true;
                    } else if (i10 == -1 || iIndexOf2 < i10) {
                        i11 = i15;
                        i10 = iIndexOf2;
                    }
                }
            }
        }
        int length5 = str.length();
        while (length4 < length5) {
            sb.append(str.charAt(length4));
            length4++;
        }
        String string = sb.toString();
        return !z8 ? string : replaceEach(string, strArr, strArr2, z8, i9 - 1);
    }

    public static String rightPad(String str, int i9, char c9) {
        if (str == null) {
            return null;
        }
        int length = i9 - str.length();
        return length <= 0 ? str : length > 8192 ? rightPad(str, i9, String.valueOf(c9)) : str.concat(repeat(c9, length));
    }

    public static String[] split(String str, char c9) {
        return splitWorker(str, c9, false);
    }

    private static String[] splitByCharacterType(String str, boolean z8) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        int type = Character.getType(charArray[0]);
        for (int i10 = 1; i10 < charArray.length; i10++) {
            int type2 = Character.getType(charArray[i10]);
            if (type2 != type) {
                if (z8 && type2 == 2 && type == 1) {
                    int i11 = i10 - 1;
                    if (i11 != i9) {
                        arrayList.add(new String(charArray, i9, i11 - i9));
                        i9 = i11;
                    }
                } else {
                    arrayList.add(new String(charArray, i9, i10 - i9));
                    i9 = i10;
                }
                type = type2;
            }
        }
        arrayList.add(new String(charArray, i9, charArray.length - i9));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] splitByWholeSeparator(String str, String str2, int i9) {
        return splitByWholeSeparatorWorker(str, str2, i9, false);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String str2, int i9) {
        return splitByWholeSeparatorWorker(str, str2, i9, true);
    }

    public static String[] splitPreserveAllTokens(String str, char c9) {
        return splitWorker(str, c9, true);
    }

    private static boolean startsWith(CharSequence charSequence, CharSequence charSequence2, boolean z8) {
        if (charSequence == null || charSequence2 == null) {
            return charSequence == null && charSequence2 == null;
        }
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        return CharSequenceUtils.regionMatches(charSequence, z8, 0, charSequence2, 0, charSequence2.length());
    }

    public static String strip(String str, String str2) {
        return isEmpty(str) ? str : stripEnd(stripStart(str, str2), str2);
    }

    public static String[] stripAll(String[] strArr, String str) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return strArr;
        }
        String[] strArr2 = new String[length];
        for (int i9 = 0; i9 < length; i9++) {
            strArr2[i9] = strip(strArr[i9], str);
        }
        return strArr2;
    }

    public static String substringBetween(String str, String str2, String str3) {
        int iIndexOf;
        int iIndexOf2;
        if (str == null || str2 == null || str3 == null || (iIndexOf = str.indexOf(str2)) == -1 || (iIndexOf2 = str.indexOf(str3, str2.length() + iIndexOf)) == -1) {
            return null;
        }
        return str.substring(iIndexOf + str2.length(), iIndexOf2);
    }

    public static String upperCase(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(locale);
    }

    public static boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        return (charSequence == null || charSequence2 == null || CharSequenceUtils.indexOf(charSequence, charSequence2, 0) < 0) ? false : true;
    }

    public static int indexOf(CharSequence charSequence, int i9, int i10) {
        if (isEmpty(charSequence)) {
            return -1;
        }
        return CharSequenceUtils.indexOf(charSequence, i9, i10);
    }

    public static String join(long[] jArr, char c9) {
        if (jArr == null) {
            return null;
        }
        return join(jArr, c9, 0, jArr.length);
    }

    public static int lastIndexOf(CharSequence charSequence, int i9, int i10) {
        if (isEmpty(charSequence)) {
            return -1;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, i9, i10);
    }

    public static String remove(String str, char c9) {
        if (isEmpty(str) || str.indexOf(c9) == -1) {
            return str;
        }
        char[] charArray = str.toCharArray();
        int i9 = 0;
        for (char c10 : charArray) {
            if (c10 != c9) {
                charArray[i9] = c10;
                i9++;
            }
        }
        return new String(charArray, 0, i9);
    }

    public static String[] split(String str, String str2) {
        return splitWorker(str, str2, -1, false);
    }

    public static String[] splitPreserveAllTokens(String str, String str2) {
        return splitWorker(str, str2, -1, true);
    }

    public static boolean containsOnly(CharSequence charSequence, String str) {
        if (charSequence == null || str == null) {
            return false;
        }
        return containsOnly(charSequence, str.toCharArray());
    }

    public static int indexOfDifference(CharSequence... charSequenceArr) {
        if (charSequenceArr != null && charSequenceArr.length > 1) {
            int length = charSequenceArr.length;
            int iMin = Integer.MAX_VALUE;
            boolean z8 = true;
            int iMax = 0;
            boolean z9 = false;
            for (int i9 = 0; i9 < length; i9++) {
                CharSequence charSequence = charSequenceArr[i9];
                if (charSequence == null) {
                    z9 = true;
                    iMin = 0;
                } else {
                    iMin = Math.min(charSequence.length(), iMin);
                    iMax = Math.max(charSequenceArr[i9].length(), iMax);
                    z8 = false;
                }
            }
            if (!z8 && (iMax != 0 || z9)) {
                if (iMin == 0) {
                    return 0;
                }
                int i10 = -1;
                for (int i11 = 0; i11 < iMin; i11++) {
                    char cCharAt = charSequenceArr[0].charAt(i11);
                    int i12 = 1;
                    while (true) {
                        if (i12 >= length) {
                            break;
                        }
                        if (charSequenceArr[i12].charAt(i11) != cCharAt) {
                            i10 = i11;
                            break;
                        }
                        i12++;
                    }
                    if (i10 != -1) {
                        break;
                    }
                }
                return (i10 != -1 || iMin == iMax) ? i10 : iMin;
            }
        }
        return -1;
    }

    public static String join(int[] iArr, char c9) {
        if (iArr == null) {
            return null;
        }
        return join(iArr, c9, 0, iArr.length);
    }

    public static String[] split(String str, String str2, int i9) {
        return splitWorker(str, str2, i9, false);
    }

    public static String[] splitPreserveAllTokens(String str, String str2, int i9) {
        return splitWorker(str, str2, i9, true);
    }

    public static String substring(String str, int i9, int i10) {
        if (str == null) {
            return null;
        }
        if (i10 < 0) {
            i10 += str.length();
        }
        if (i9 < 0) {
            i9 += str.length();
        }
        if (i10 > str.length()) {
            i10 = str.length();
        }
        if (i9 > i10) {
            return "";
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        return str.substring(i9, i10);
    }

    public static String center(String str, int i9, String str2) {
        if (str == null || i9 <= 0) {
            return str;
        }
        if (isEmpty(str2)) {
            str2 = SPACE;
        }
        int length = str.length();
        int i10 = i9 - length;
        return i10 <= 0 ? str : rightPad(leftPad(str, length + (i10 / 2), str2), i9, str2);
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        return CharSequenceUtils.indexOf(charSequence, charSequence2, 0);
    }

    public static String join(short[] sArr, char c9) {
        if (sArr == null) {
            return null;
        }
        return join(sArr, c9, 0, sArr.length);
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, charSequence2, charSequence.length());
    }

    public static String leftPad(String str, int i9, String str2) {
        if (str == null) {
            return null;
        }
        if (isEmpty(str2)) {
            str2 = SPACE;
        }
        int length = str2.length();
        int length2 = i9 - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= 8192) {
            return leftPad(str, i9, str2.charAt(0));
        }
        if (length2 == length) {
            return str2.concat(str);
        }
        if (length2 < length) {
            return str2.substring(0, length2).concat(str);
        }
        char[] cArr = new char[length2];
        char[] charArray = str2.toCharArray();
        for (int i10 = 0; i10 < length2; i10++) {
            cArr[i10] = charArray[i10 % length];
        }
        return new String(cArr).concat(str);
    }

    public static String rightPad(String str, int i9, String str2) {
        if (str == null) {
            return null;
        }
        if (isEmpty(str2)) {
            str2 = SPACE;
        }
        int length = str2.length();
        int length2 = i9 - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= 8192) {
            return rightPad(str, i9, str2.charAt(0));
        }
        if (length2 == length) {
            return str.concat(str2);
        }
        if (length2 < length) {
            return str.concat(str2.substring(0, length2));
        }
        char[] cArr = new char[length2];
        char[] charArray = str2.toCharArray();
        for (int i10 = 0; i10 < length2; i10++) {
            cArr[i10] = charArray[i10 % length];
        }
        return str.concat(new String(cArr));
    }

    public static String appendIfMissing(String str, CharSequence charSequence, CharSequence... charSequenceArr) {
        return appendIfMissing(str, charSequence, false, charSequenceArr);
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        return CharSequenceUtils.indexOf(charSequence, charSequence2, i9);
    }

    public static String join(byte[] bArr, char c9) {
        if (bArr == null) {
            return null;
        }
        return join(bArr, c9, 0, bArr.length);
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i9) {
        if (charSequence == null || charSequence2 == null) {
            return -1;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, charSequence2, i9);
    }

    public static String prependIfMissing(String str, CharSequence charSequence, CharSequence... charSequenceArr) {
        return prependIfMissing(str, charSequence, false, charSequenceArr);
    }

    public static boolean containsNone(CharSequence charSequence, String str) {
        if (charSequence == null || str == null) {
            return true;
        }
        return containsNone(charSequence, str.toCharArray());
    }

    public static String join(char[] cArr, char c9) {
        if (cArr == null) {
            return null;
        }
        return join(cArr, c9, 0, cArr.length);
    }

    @Deprecated
    public static String chomp(String str, String str2) {
        return removeEnd(str, str2);
    }

    public static boolean containsAny(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2 == null) {
            return false;
        }
        return containsAny(charSequence, CharSequenceUtils.toCharArray(charSequence2));
    }

    public static int indexOfAny(CharSequence charSequence, String str) {
        if (isEmpty(charSequence) || isEmpty(str)) {
            return -1;
        }
        return indexOfAny(charSequence, str.toCharArray());
    }

    public static int indexOfAnyBut(CharSequence charSequence, CharSequence charSequence2) {
        if (!isEmpty(charSequence) && !isEmpty(charSequence2)) {
            int length = charSequence.length();
            int i9 = 0;
            while (i9 < length) {
                char cCharAt = charSequence.charAt(i9);
                boolean z8 = CharSequenceUtils.indexOf(charSequence2, cCharAt, 0) >= 0;
                int i10 = i9 + 1;
                if (i10 < length && Character.isHighSurrogate(cCharAt)) {
                    char cCharAt2 = charSequence.charAt(i10);
                    if (z8 && CharSequenceUtils.indexOf(charSequence2, cCharAt2, 0) < 0) {
                        return i9;
                    }
                } else if (!z8) {
                    return i9;
                }
                i9 = i10;
            }
        }
        return -1;
    }

    public static String join(float[] fArr, char c9) {
        if (fArr == null) {
            return null;
        }
        return join(fArr, c9, 0, fArr.length);
    }

    private static String[] splitWorker(String str, String str2, int i9, boolean z8) {
        int i10;
        boolean z9;
        boolean z10;
        int i11;
        int i12;
        boolean z11;
        boolean z12;
        int i13;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        ArrayList arrayList = new ArrayList();
        if (str2 == null) {
            i12 = 0;
            z11 = false;
            z12 = false;
            i13 = 0;
            int i14 = 1;
            while (i12 < length) {
                if (Character.isWhitespace(str.charAt(i12))) {
                    if (z11 || z8) {
                        int i15 = i14 + 1;
                        if (i14 == i9) {
                            i12 = length;
                            z12 = false;
                        } else {
                            z12 = true;
                        }
                        arrayList.add(str.substring(i13, i12));
                        i14 = i15;
                        z11 = false;
                    }
                    i13 = i12 + 1;
                    i12 = i13;
                } else {
                    i12++;
                    z12 = false;
                    z11 = true;
                }
            }
        } else {
            if (str2.length() == 1) {
                char cCharAt = str2.charAt(0);
                i10 = 0;
                z9 = false;
                z10 = false;
                i11 = 0;
                int i16 = 1;
                while (i10 < length) {
                    if (str.charAt(i10) == cCharAt) {
                        if (z9 || z8) {
                            int i17 = i16 + 1;
                            if (i16 == i9) {
                                i10 = length;
                                z10 = false;
                            } else {
                                z10 = true;
                            }
                            arrayList.add(str.substring(i11, i10));
                            i16 = i17;
                            z9 = false;
                        }
                        i11 = i10 + 1;
                        i10 = i11;
                    } else {
                        i10++;
                        z10 = false;
                        z9 = true;
                    }
                }
            } else {
                i10 = 0;
                z9 = false;
                z10 = false;
                i11 = 0;
                int i18 = 1;
                while (i10 < length) {
                    if (str2.indexOf(str.charAt(i10)) >= 0) {
                        if (z9 || z8) {
                            int i19 = i18 + 1;
                            if (i18 == i9) {
                                i10 = length;
                                z10 = false;
                            } else {
                                z10 = true;
                            }
                            arrayList.add(str.substring(i11, i10));
                            i18 = i19;
                            z9 = false;
                        }
                        i11 = i10 + 1;
                        i10 = i11;
                    } else {
                        i10++;
                        z10 = false;
                        z9 = true;
                    }
                }
            }
            i12 = i10;
            z11 = z9;
            z12 = z10;
            i13 = i11;
        }
        if (z11 || (z8 && z12)) {
            arrayList.add(str.substring(i13, i12));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String join(double[] dArr, char c9) {
        if (dArr == null) {
            return null;
        }
        return join(dArr, c9, 0, dArr.length);
    }

    public static int indexOfAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        int iIndexOf;
        if (charSequence == null || charSequenceArr == null) {
            return -1;
        }
        int i9 = Integer.MAX_VALUE;
        for (CharSequence charSequence2 : charSequenceArr) {
            if (charSequence2 != null && (iIndexOf = CharSequenceUtils.indexOf(charSequence, charSequence2, 0)) != -1 && iIndexOf < i9) {
                i9 = iIndexOf;
            }
        }
        if (i9 == Integer.MAX_VALUE) {
            return -1;
        }
        return i9;
    }

    public static String join(Object[] objArr, char c9, int i9, int i10) {
        if (objArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            Object obj = objArr[i12];
            if (obj != null) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    public static int getLevenshteinDistance(CharSequence charSequence, CharSequence charSequence2, int i9) {
        int i10;
        int length;
        CharSequence charSequence3;
        CharSequence charSequence4;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        if (i9 >= 0) {
            int length2 = charSequence.length();
            int length3 = charSequence2.length();
            if (length2 == 0) {
                if (length3 <= i9) {
                    return length3;
                }
                return -1;
            }
            if (length3 == 0) {
                if (length2 <= i9) {
                    return length2;
                }
                return -1;
            }
            if (length2 > length3) {
                length = charSequence.length();
                i10 = length3;
                charSequence4 = charSequence;
                charSequence3 = charSequence2;
            } else {
                i10 = length2;
                length = length3;
                charSequence3 = charSequence;
                charSequence4 = charSequence2;
            }
            int i11 = i10 + 1;
            int[] iArr = new int[i11];
            int[] iArr2 = new int[i11];
            int iMin = Math.min(i10, i9) + 1;
            char c9 = 0;
            for (int i12 = 0; i12 < iMin; i12++) {
                iArr[i12] = i12;
            }
            int i13 = Integer.MAX_VALUE;
            Arrays.fill(iArr, iMin, i11, Integer.MAX_VALUE);
            Arrays.fill(iArr2, Integer.MAX_VALUE);
            int i14 = 1;
            while (i14 <= length) {
                char cCharAt = charSequence4.charAt(i14 - 1);
                iArr2[c9] = i14;
                int iMax = Math.max(1, i14 - i9);
                int iMin2 = i14 > i13 - i9 ? i10 : Math.min(i10, i14 + i9);
                if (iMax > iMin2) {
                    return -1;
                }
                if (iMax > 1) {
                    iArr2[iMax - 1] = i13;
                }
                while (iMax <= iMin2) {
                    int i15 = iMax - 1;
                    if (charSequence3.charAt(i15) == cCharAt) {
                        iArr2[iMax] = iArr[i15];
                    } else {
                        iArr2[iMax] = Math.min(Math.min(iArr2[i15], iArr[iMax]), iArr[i15]) + 1;
                    }
                    iMax++;
                }
                i14++;
                c9 = 0;
                i13 = Integer.MAX_VALUE;
                int[] iArr3 = iArr2;
                iArr2 = iArr;
                iArr = iArr3;
            }
            int i16 = iArr[i10];
            if (i16 <= i9) {
                return i16;
            }
            return -1;
        }
        throw new IllegalArgumentException("Threshold must not be negative");
    }

    public static String repeat(String str, String str2, int i9) {
        if (str != null && str2 != null) {
            return removeEnd(repeat(str + str2, i9), str2);
        }
        return repeat(str, i9);
    }

    public static String join(long[] jArr, char c9, int i9, int i10) {
        if (jArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append(jArr[i12]);
        }
        return sb.toString();
    }

    public static String repeat(char c9, int i9) {
        char[] cArr = new char[i9];
        for (int i10 = i9 - 1; i10 >= 0; i10--) {
            cArr[i10] = c9;
        }
        return new String(cArr);
    }

    public static String join(int[] iArr, char c9, int i9, int i10) {
        if (iArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append(iArr[i12]);
        }
        return sb.toString();
    }

    public static String join(byte[] bArr, char c9, int i9, int i10) {
        if (bArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append((int) bArr[i12]);
        }
        return sb.toString();
    }

    public static String join(short[] sArr, char c9, int i9, int i10) {
        if (sArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append((int) sArr[i12]);
        }
        return sb.toString();
    }

    public static String join(char[] cArr, char c9, int i9, int i10) {
        if (cArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append(cArr[i12]);
        }
        return sb.toString();
    }

    public static String join(double[] dArr, char c9, int i9, int i10) {
        if (dArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append(dArr[i12]);
        }
        return sb.toString();
    }

    public static String join(float[] fArr, char c9, int i9, int i10) {
        if (fArr == null) {
            return null;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(c9);
            }
            sb.append(fArr[i12]);
        }
        return sb.toString();
    }

    public static String join(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return join(objArr, str, 0, objArr.length);
    }

    public static String join(Object[] objArr, String str, int i9, int i10) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i11 * 16);
        for (int i12 = i9; i12 < i10; i12++) {
            if (i12 > i9) {
                sb.append(str);
            }
            Object obj = objArr[i12];
            if (obj != null) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    public static String join(Iterator<?> it, char c9) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.toString(next);
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            sb.append(c9);
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static String join(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.toString(next);
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                sb.append(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static String join(Iterable<?> iterable, char c9) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), c9);
    }

    public static String join(Iterable<?> iterable, String str) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), str);
    }
}
