package p021c0;

import java.io.PrintWriter;

/* renamed from: c0.i */
/* loaded from: classes.dex */
public final class C0703i {

    /* renamed from: a */
    public static final Object f3358a = new Object();

    /* renamed from: b */
    public static char[] f3359b = new char[24];

    /* renamed from: a */
    public static int m3470a(int i9, int i10, boolean z8, int i11) {
        if (i9 > 99 || (z8 && i11 >= 3)) {
            return i10 + 3;
        }
        if (i9 > 9 || (z8 && i11 >= 2)) {
            return i10 + 2;
        }
        if (z8 || i9 > 0) {
            return i10 + 1;
        }
        return 0;
    }

    /* renamed from: b */
    public static void m3471b(long j9, long j10, PrintWriter printWriter) {
        if (j9 == 0) {
            printWriter.print("--");
        } else {
            m3473d(j9 - j10, printWriter, 0);
        }
    }

    /* renamed from: c */
    public static void m3472c(long j9, PrintWriter printWriter) {
        m3473d(j9, printWriter, 0);
    }

    /* renamed from: d */
    public static void m3473d(long j9, PrintWriter printWriter, int i9) {
        synchronized (f3358a) {
            printWriter.print(new String(f3359b, 0, m3474e(j9, i9)));
        }
    }

    /* renamed from: e */
    public static int m3474e(long j9, int i9) {
        char c9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        long j10 = j9;
        if (f3359b.length < i9) {
            f3359b = new char[i9];
        }
        char[] cArr = f3359b;
        if (j10 == 0) {
            int i15 = i9 - 1;
            while (i15 > 0) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (j10 > 0) {
            c9 = '+';
        } else {
            j10 = -j10;
            c9 = '-';
        }
        int i16 = (int) (j10 % 1000);
        int iFloor = (int) Math.floor(j10 / 1000);
        if (iFloor > 86400) {
            i10 = iFloor / 86400;
            iFloor -= 86400 * i10;
        } else {
            i10 = 0;
        }
        if (iFloor > 3600) {
            i11 = iFloor / 3600;
            iFloor -= i11 * 3600;
        } else {
            i11 = 0;
        }
        if (iFloor > 60) {
            int i17 = iFloor / 60;
            i12 = iFloor - (i17 * 60);
            i13 = i17;
        } else {
            i12 = iFloor;
            i13 = 0;
        }
        if (i9 != 0) {
            int iM3470a = m3470a(i10, 1, false, 0);
            int iM3470a2 = iM3470a + m3470a(i11, 1, iM3470a > 0, 2);
            int iM3470a3 = iM3470a2 + m3470a(i13, 1, iM3470a2 > 0, 2);
            int iM3470a4 = iM3470a3 + m3470a(i12, 1, iM3470a3 > 0, 2);
            i14 = 0;
            for (int iM3470a5 = iM3470a4 + m3470a(i16, 2, true, iM3470a4 > 0 ? 3 : 0) + 1; iM3470a5 < i9; iM3470a5++) {
                cArr[i14] = ' ';
                i14++;
            }
        } else {
            i14 = 0;
        }
        cArr[i14] = c9;
        int i18 = i14 + 1;
        boolean z8 = i9 != 0;
        int iM3475f = m3475f(cArr, i10, 'd', i18, false, 0);
        int iM3475f2 = m3475f(cArr, i11, 'h', iM3475f, iM3475f != i18, z8 ? 2 : 0);
        int iM3475f3 = m3475f(cArr, i13, 'm', iM3475f2, iM3475f2 != i18, z8 ? 2 : 0);
        int iM3475f4 = m3475f(cArr, i12, 's', iM3475f3, iM3475f3 != i18, z8 ? 2 : 0);
        int iM3475f5 = m3475f(cArr, i16, 'm', iM3475f4, true, (!z8 || iM3475f4 == i18) ? 0 : 3);
        cArr[iM3475f5] = 's';
        return iM3475f5 + 1;
    }

    /* renamed from: f */
    public static int m3475f(char[] cArr, int i9, char c9, int i10, boolean z8, int i11) {
        int i12;
        if (!z8 && i9 <= 0) {
            return i10;
        }
        if ((!z8 || i11 < 3) && i9 <= 99) {
            i12 = i10;
        } else {
            int i13 = i9 / 100;
            cArr[i10] = (char) (i13 + 48);
            i12 = i10 + 1;
            i9 -= i13 * 100;
        }
        if ((z8 && i11 >= 2) || i9 > 9 || i10 != i12) {
            int i14 = i9 / 10;
            cArr[i12] = (char) (i14 + 48);
            i12++;
            i9 -= i14 * 10;
        }
        cArr[i12] = (char) (i9 + 48);
        int i15 = i12 + 1;
        cArr[i15] = c9;
        return i15 + 1;
    }
}
