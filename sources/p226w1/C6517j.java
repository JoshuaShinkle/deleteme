package p226w1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Looper;
import com.google.common.primitives.UnsignedBytes;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import p073g1.InterfaceC4818l;

/* renamed from: w1.j */
/* loaded from: classes.dex */
public final class C6517j {

    /* renamed from: a */
    public static final char[] f21926a = "0123456789abcdef".toCharArray();

    /* renamed from: b */
    public static final char[] f21927b = new char[64];

    /* renamed from: w1.j$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f21928a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f21928a = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f21928a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f21928a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f21928a[Bitmap.Config.RGBA_F16.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f21928a[Bitmap.Config.ARGB_8888.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: a */
    public static void m24940a() {
        if (!m24956q()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }

    /* renamed from: b */
    public static void m24941b() {
        if (!m24957r()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /* renamed from: c */
    public static boolean m24942c(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj instanceof InterfaceC4818l ? ((InterfaceC4818l) obj).m19119a(obj2) : obj.equals(obj2);
    }

    /* renamed from: d */
    public static boolean m24943d(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* renamed from: e */
    public static String m24944e(byte[] bArr, char[] cArr) {
        for (int i9 = 0; i9 < bArr.length; i9++) {
            int i10 = bArr[i9] & UnsignedBytes.MAX_VALUE;
            int i11 = i9 * 2;
            char[] cArr2 = f21926a;
            cArr[i11] = cArr2[i10 >>> 4];
            cArr[i11 + 1] = cArr2[i10 & 15];
        }
        return new String(cArr);
    }

    /* renamed from: f */
    public static <T> Queue<T> m24945f(int i9) {
        return new ArrayDeque(i9);
    }

    /* renamed from: g */
    public static int m24946g(int i9, int i10, Bitmap.Config config) {
        return i9 * i10 * m24948i(config);
    }

    @TargetApi(19)
    /* renamed from: h */
    public static int m24947h(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException unused) {
                return bitmap.getHeight() * bitmap.getRowBytes();
            }
        }
        throw new IllegalStateException("Cannot obtain size for recycled Bitmap: " + bitmap + "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig());
    }

    /* renamed from: i */
    public static int m24948i(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i9 = a.f21928a[config.ordinal()];
        if (i9 == 1) {
            return 1;
        }
        if (i9 == 2 || i9 == 3) {
            return 2;
        }
        return i9 != 4 ? 4 : 8;
    }

    /* renamed from: j */
    public static <T> List<T> m24949j(Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (T t8 : collection) {
            if (t8 != null) {
                arrayList.add(t8);
            }
        }
        return arrayList;
    }

    /* renamed from: k */
    public static int m24950k(float f9) {
        return m24951l(f9, 17);
    }

    /* renamed from: l */
    public static int m24951l(float f9, int i9) {
        return m24953n(Float.floatToIntBits(f9), i9);
    }

    /* renamed from: m */
    public static int m24952m(int i9) {
        return m24953n(i9, 17);
    }

    /* renamed from: n */
    public static int m24953n(int i9, int i10) {
        return (i10 * 31) + i9;
    }

    /* renamed from: o */
    public static int m24954o(Object obj, int i9) {
        return m24953n(obj == null ? 0 : obj.hashCode(), i9);
    }

    /* renamed from: p */
    public static int m24955p(boolean z8, int i9) {
        return m24953n(z8 ? 1 : 0, i9);
    }

    /* renamed from: q */
    public static boolean m24956q() {
        return !m24957r();
    }

    /* renamed from: r */
    public static boolean m24957r() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: s */
    public static boolean m24958s(int i9) {
        return i9 > 0 || i9 == Integer.MIN_VALUE;
    }

    /* renamed from: t */
    public static boolean m24959t(int i9, int i10) {
        return m24958s(i9) && m24958s(i10);
    }

    /* renamed from: u */
    public static String m24960u(byte[] bArr) {
        String strM24944e;
        char[] cArr = f21927b;
        synchronized (cArr) {
            strM24944e = m24944e(bArr, cArr);
        }
        return strM24944e;
    }
}
