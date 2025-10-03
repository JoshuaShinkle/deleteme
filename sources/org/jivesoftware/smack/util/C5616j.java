package org.jivesoftware.smack.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.logging.Logger;
import org.apache.commons.lang3.CharEncoding;

/* renamed from: org.jivesoftware.smack.util.j */
/* loaded from: classes.dex */
public class C5616j {

    /* renamed from: a */
    public static final Logger f19524a = Logger.getLogger(C5616j.class.getName());

    /* renamed from: b */
    public static MessageDigest f19525b = null;

    /* renamed from: c */
    public static Random f19526c = new Random();

    /* renamed from: d */
    public static char[] f19527d = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: a */
    public static byte[] m22336a(String str) throws UnsupportedEncodingException {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        return C5608b.m22299d(bytes, 0, bytes.length, 0);
    }

    /* renamed from: b */
    public static String m22337b(String str) {
        try {
            return m22338c(str.getBytes(CharEncoding.ISO_8859_1));
        } catch (UnsupportedEncodingException e9) {
            throw new IllegalStateException(e9);
        }
    }

    /* renamed from: c */
    public static String m22338c(byte[] bArr) {
        return m22340e(bArr, false);
    }

    /* renamed from: d */
    public static String m22339d(byte[] bArr, int i9, int i10, boolean z8) {
        return C5608b.m22303h(bArr, i9, i10, z8 ? 0 : 8);
    }

    /* renamed from: e */
    public static String m22340e(byte[] bArr, boolean z8) {
        return m22339d(bArr, 0, bArr.length, z8);
    }

    /* renamed from: f */
    public static CharSequence m22341f(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        StringBuilder sb = new StringBuilder((int) (length * 1.3d));
        int i9 = 0;
        int i10 = 0;
        while (i9 < length) {
            char c9 = charArray[i9];
            String str2 = c9 != '\"' ? c9 != '<' ? c9 != '>' ? c9 != '&' ? c9 != '\'' ? null : "&apos;" : "&amp;" : "&gt;" : "&lt;" : "&quot;";
            if (str2 != null) {
                if (i9 > i10) {
                    sb.append(charArray, i10, i9 - i10);
                }
                sb.append((CharSequence) str2);
                i10 = i9 + 1;
                i9 = i10;
            } else {
                i9++;
            }
        }
        if (i10 == 0) {
            return str;
        }
        if (i9 > i10) {
            sb.append(charArray, i10, i9 - i10);
        }
        return sb;
    }

    /* renamed from: g */
    public static boolean m22342g(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    /* renamed from: h */
    public static boolean m22343h(CharSequence charSequence) {
        return !m22344i(charSequence);
    }

    /* renamed from: i */
    public static boolean m22344i(CharSequence charSequence) {
        return charSequence == null || m22342g(charSequence);
    }

    /* renamed from: j */
    public static String m22345j(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf("/");
        return iIndexOf < 0 ? str : iIndexOf == 0 ? "" : str.substring(0, iIndexOf);
    }

    /* renamed from: k */
    public static String m22346k(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("@");
        return iLastIndexOf <= 0 ? "" : str.substring(0, iLastIndexOf);
    }

    /* renamed from: l */
    public static String m22347l(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf("/");
        int i9 = iIndexOf + 1;
        return (i9 > str.length() || iIndexOf < 0) ? "" : str.substring(i9);
    }

    /* renamed from: m */
    public static String m22348m(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("@");
        int i9 = iLastIndexOf + 1;
        if (i9 > str.length()) {
            return "";
        }
        int iIndexOf = str.indexOf("/");
        return (iIndexOf <= 0 || iIndexOf <= iLastIndexOf) ? str.substring(i9) : str.substring(i9, iIndexOf);
    }

    /* renamed from: n */
    public static String m22349n(int i9) {
        if (i9 < 1) {
            return null;
        }
        char[] cArr = new char[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            cArr[i10] = f19527d[f19526c.nextInt(71)];
        }
        return new String(cArr);
    }
}
