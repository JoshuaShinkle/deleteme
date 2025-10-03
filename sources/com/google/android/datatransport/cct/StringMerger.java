package com.google.android.datatransport.cct;

/* loaded from: classes.dex */
public final class StringMerger {
    public static String mergeStrings(String str, String str2) {
        int length = str.length() - str2.length();
        if (length < 0 || length > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length());
        for (int i9 = 0; i9 < str.length(); i9++) {
            sb.append(str.charAt(i9));
            if (str2.length() > i9) {
                sb.append(str2.charAt(i9));
            }
        }
        return sb.toString();
    }
}
