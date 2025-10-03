package p204t6;

import p007a6.C0042f;

/* renamed from: t6.y */
/* loaded from: classes.dex */
public final class C6344y {
    /* renamed from: a */
    public static final long m24296a(String str, int i9, int i10) {
        int i11;
        C0042f.m158e(str, "<this>");
        if (!(i9 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i9).toString());
        }
        if (!(i10 >= i9)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i10 + " < " + i9).toString());
        }
        if (!(i10 <= str.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i10 + " > " + str.length()).toString());
        }
        long j9 = 0;
        while (i9 < i10) {
            char cCharAt = str.charAt(i9);
            if (cCharAt < 128) {
                j9++;
            } else {
                if (cCharAt < 2048) {
                    i11 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i11 = 3;
                } else {
                    int i12 = i9 + 1;
                    char cCharAt2 = i12 < i10 ? str.charAt(i12) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j9++;
                        i9 = i12;
                    } else {
                        j9 += 4;
                        i9 += 2;
                    }
                }
                j9 += i11;
            }
            i9++;
        }
        return j9;
    }

    /* renamed from: b */
    public static /* synthetic */ long m24297b(String str, int i9, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i9 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = str.length();
        }
        return m24296a(str, i9, i10);
    }
}
