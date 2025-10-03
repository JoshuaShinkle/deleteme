package com.google.zxing.client.android.encode;

import java.util.HashSet;
import java.util.List;

/* loaded from: classes2.dex */
abstract class ContactEncoder {
    public static void append(StringBuilder sb, StringBuilder sb2, String str, String str2, Formatter formatter, char c9) {
        String strTrim = trim(str2);
        if (strTrim != null) {
            sb.append(str);
            sb.append(formatter.format(strTrim, 0));
            sb.append(c9);
            sb2.append(strTrim);
            sb2.append('\n');
        }
    }

    public static void appendUpToUnique(StringBuilder sb, StringBuilder sb2, String str, List<String> list, int i9, Formatter formatter, Formatter formatter2, char c9) {
        if (list == null) {
            return;
        }
        HashSet hashSet = new HashSet(2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            String strTrim = trim(list.get(i11));
            if (strTrim != null && !strTrim.isEmpty() && !hashSet.contains(strTrim)) {
                sb.append(str);
                sb.append(formatter2.format(strTrim, i11));
                sb.append(c9);
                sb2.append(formatter == null ? strTrim : formatter.format(strTrim, i11));
                sb2.append('\n');
                i10++;
                if (i10 == i9) {
                    return;
                } else {
                    hashSet.add(strTrim);
                }
            }
        }
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return strTrim;
    }

    public abstract String[] encode(List<String> list, String str, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, String str2);
}
