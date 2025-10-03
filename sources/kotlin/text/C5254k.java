package kotlin.text;

import p007a6.C0042f;

/* renamed from: kotlin.text.k */
/* loaded from: classes2.dex */
public class C5254k extends C5253j {
    /* renamed from: f */
    public static final Integer m20507f(String str) {
        C0042f.m158e(str, "<this>");
        return m20508g(str, 10);
    }

    /* renamed from: g */
    public static final Integer m20508g(String str, int i9) {
        boolean z8;
        int i10;
        int i11;
        C0042f.m158e(str, "<this>");
        C5244a.m20494a(i9);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i12 = 0;
        char cCharAt = str.charAt(0);
        int i13 = -2147483647;
        if (C0042f.m159f(cCharAt, 48) < 0) {
            i10 = 1;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                i13 = Integer.MIN_VALUE;
                z8 = true;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z8 = false;
            }
        } else {
            z8 = false;
            i10 = 0;
        }
        int i14 = -59652323;
        while (i10 < length) {
            int iM20495b = C5244a.m20495b(str.charAt(i10), i9);
            if (iM20495b < 0) {
                return null;
            }
            if ((i12 < i14 && (i14 != -59652323 || i12 < (i14 = i13 / i9))) || (i11 = i12 * i9) < i13 + iM20495b) {
                return null;
            }
            i12 = i11 - iM20495b;
            i10++;
        }
        return z8 ? Integer.valueOf(i12) : Integer.valueOf(-i12);
    }
}
