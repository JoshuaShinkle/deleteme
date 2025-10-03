package p132m;

/* renamed from: m.c */
/* loaded from: classes.dex */
public class C5304c {

    /* renamed from: a */
    public static final int[] f18014a = new int[0];

    /* renamed from: b */
    public static final long[] f18015b = new long[0];

    /* renamed from: c */
    public static final Object[] f18016c = new Object[0];

    /* renamed from: a */
    public static int m20712a(int[] iArr, int i9, int i10) {
        int i11 = i9 - 1;
        int i12 = 0;
        while (i12 <= i11) {
            int i13 = (i12 + i11) >>> 1;
            int i14 = iArr[i13];
            if (i14 < i10) {
                i12 = i13 + 1;
            } else {
                if (i14 <= i10) {
                    return i13;
                }
                i11 = i13 - 1;
            }
        }
        return ~i12;
    }

    /* renamed from: b */
    public static int m20713b(long[] jArr, int i9, long j9) {
        int i10 = i9 - 1;
        int i11 = 0;
        while (i11 <= i10) {
            int i12 = (i11 + i10) >>> 1;
            long j10 = jArr[i12];
            if (j10 < j9) {
                i11 = i12 + 1;
            } else {
                if (j10 <= j9) {
                    return i12;
                }
                i10 = i12 - 1;
            }
        }
        return ~i11;
    }

    /* renamed from: c */
    public static boolean m20714c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: d */
    public static int m20715d(int i9) {
        for (int i10 = 4; i10 < 32; i10++) {
            int i11 = (1 << i10) - 12;
            if (i9 <= i11) {
                return i11;
            }
        }
        return i9;
    }

    /* renamed from: e */
    public static int m20716e(int i9) {
        return m20715d(i9 * 4) / 4;
    }

    /* renamed from: f */
    public static int m20717f(int i9) {
        return m20715d(i9 * 8) / 8;
    }
}
