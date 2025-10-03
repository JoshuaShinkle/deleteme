package p215v;

import android.graphics.Color;

/* renamed from: v.a */
/* loaded from: classes.dex */
public final class C6427a {

    /* renamed from: a */
    public static final ThreadLocal<double[]> f21654a = new ThreadLocal<>();

    /* renamed from: a */
    public static int m24587a(int i9, int i10) {
        return 255 - (((255 - i10) * (255 - i9)) / 255);
    }

    /* renamed from: b */
    public static int m24588b(int i9, int i10) {
        int iAlpha = Color.alpha(i10);
        int iAlpha2 = Color.alpha(i9);
        int iM24587a = m24587a(iAlpha2, iAlpha);
        return Color.argb(iM24587a, m24589c(Color.red(i9), iAlpha2, Color.red(i10), iAlpha, iM24587a), m24589c(Color.green(i9), iAlpha2, Color.green(i10), iAlpha, iM24587a), m24589c(Color.blue(i9), iAlpha2, Color.blue(i10), iAlpha, iM24587a));
    }

    /* renamed from: c */
    public static int m24589c(int i9, int i10, int i11, int i12, int i13) {
        if (i13 == 0) {
            return 0;
        }
        return (((i9 * 255) * i10) + ((i11 * i12) * (255 - i10))) / (i13 * 255);
    }

    /* renamed from: d */
    public static int m24590d(int i9, int i10) {
        if (i10 < 0 || i10 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i9 & 16777215) | (i10 << 24);
    }
}
