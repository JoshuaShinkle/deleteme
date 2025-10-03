package com.google.android.exoplayer2.util;

import android.net.Uri;
import android.text.TextUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
public final class UriUtil {
    private static final int FRAGMENT = 3;
    private static final int INDEX_COUNT = 4;
    private static final int PATH = 1;
    private static final int QUERY = 2;
    private static final int SCHEME_COLON = 0;

    private UriUtil() {
    }

    private static int[] getUriIndices(String str) {
        int iIndexOf;
        int[] iArr = new int[4];
        if (TextUtils.isEmpty(str)) {
            iArr[0] = -1;
            return iArr;
        }
        int length = str.length();
        int iIndexOf2 = str.indexOf(35);
        if (iIndexOf2 != -1) {
            length = iIndexOf2;
        }
        int iIndexOf3 = str.indexOf(63);
        if (iIndexOf3 == -1 || iIndexOf3 > length) {
            iIndexOf3 = length;
        }
        int iIndexOf4 = str.indexOf(47);
        if (iIndexOf4 == -1 || iIndexOf4 > iIndexOf3) {
            iIndexOf4 = iIndexOf3;
        }
        int iIndexOf5 = str.indexOf(58);
        if (iIndexOf5 > iIndexOf4) {
            iIndexOf5 = -1;
        }
        int i9 = iIndexOf5 + 2;
        if (i9 < iIndexOf3 && str.charAt(iIndexOf5 + 1) == '/' && str.charAt(i9) == '/') {
            iIndexOf = str.indexOf(47, iIndexOf5 + 3);
            if (iIndexOf == -1 || iIndexOf > iIndexOf3) {
                iIndexOf = iIndexOf3;
            }
        } else {
            iIndexOf = iIndexOf5 + 1;
        }
        iArr[0] = iIndexOf5;
        iArr[1] = iIndexOf;
        iArr[2] = iIndexOf3;
        iArr[3] = length;
        return iArr;
    }

    private static String removeDotSegments(StringBuilder sb, int i9, int i10) {
        int i11;
        int iLastIndexOf;
        if (i9 >= i10) {
            return sb.toString();
        }
        if (sb.charAt(i9) == '/') {
            i9++;
        }
        int i12 = i9;
        int i13 = i12;
        while (i12 <= i10) {
            if (i12 == i10) {
                i11 = i12;
            } else if (sb.charAt(i12) == '/') {
                i11 = i12 + 1;
            } else {
                i12++;
            }
            int i14 = i13 + 1;
            if (i12 == i14 && sb.charAt(i13) == '.') {
                sb.delete(i13, i11);
                i10 -= i11 - i13;
            } else {
                if (i12 == i13 + 2 && sb.charAt(i13) == '.' && sb.charAt(i14) == '.') {
                    iLastIndexOf = sb.lastIndexOf("/", i13 - 2) + 1;
                    int i15 = iLastIndexOf > i9 ? iLastIndexOf : i9;
                    sb.delete(i15, i11);
                    i10 -= i11 - i15;
                } else {
                    iLastIndexOf = i12 + 1;
                }
                i13 = iLastIndexOf;
            }
            i12 = i13;
        }
        return sb.toString();
    }

    public static String resolve(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] uriIndices = getUriIndices(str2);
        if (uriIndices[0] != -1) {
            sb.append(str2);
            removeDotSegments(sb, uriIndices[1], uriIndices[2]);
            return sb.toString();
        }
        int[] uriIndices2 = getUriIndices(str);
        if (uriIndices[3] == 0) {
            sb.append((CharSequence) str, 0, uriIndices2[3]);
            sb.append(str2);
            return sb.toString();
        }
        if (uriIndices[2] == 0) {
            sb.append((CharSequence) str, 0, uriIndices2[2]);
            sb.append(str2);
            return sb.toString();
        }
        int i9 = uriIndices[1];
        if (i9 != 0) {
            int i10 = uriIndices2[0] + 1;
            sb.append((CharSequence) str, 0, i10);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices[1] + i10, i10 + uriIndices[2]);
        }
        if (str2.charAt(i9) == '/') {
            sb.append((CharSequence) str, 0, uriIndices2[1]);
            sb.append(str2);
            int i11 = uriIndices2[1];
            return removeDotSegments(sb, i11, uriIndices[2] + i11);
        }
        int i12 = uriIndices2[0] + 2;
        int i13 = uriIndices2[1];
        if (i12 >= i13 || i13 != uriIndices2[2]) {
            int iLastIndexOf = str.lastIndexOf(47, uriIndices2[2] - 1);
            int i14 = iLastIndexOf == -1 ? uriIndices2[1] : iLastIndexOf + 1;
            sb.append((CharSequence) str, 0, i14);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices2[1], i14 + uriIndices[2]);
        }
        sb.append((CharSequence) str, 0, i13);
        sb.append(IOUtils.DIR_SEPARATOR_UNIX);
        sb.append(str2);
        int i15 = uriIndices2[1];
        return removeDotSegments(sb, i15, uriIndices[2] + i15 + 1);
    }

    public static Uri resolveToUri(String str, String str2) {
        return Uri.parse(resolve(str, str2));
    }
}
