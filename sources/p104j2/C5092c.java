package p104j2;

import android.util.Log;

/* renamed from: j2.c */
/* loaded from: classes.dex */
public final class C5092c {

    /* renamed from: a */
    public final boolean f17539a;

    /* renamed from: b */
    public final boolean f17540b;

    /* renamed from: c */
    public final boolean f17541c;

    /* renamed from: j2.c$b */
    public static class b {

        /* renamed from: a */
        public static final C5092c f17542a = new C5092c();
    }

    /* renamed from: a */
    public static void m19924a() {
        if (!m19925b()) {
            throw new UnsatisfiedLinkError("Could not load the native engine libraries.");
        }
    }

    /* renamed from: b */
    public static boolean m19925b() {
        return b.f17542a.f17541c;
    }

    /* renamed from: c */
    public static boolean m19926c() {
        return b.f17542a.f17540b && m19925b();
    }

    /* renamed from: d */
    public static boolean m19927d(String str) {
        return m19928e(str, 5);
    }

    /* renamed from: e */
    public static boolean m19928e(String str, int i9) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            Log.println(i9, "JNILoader", "Could not load lib" + str);
            return false;
        }
    }

    public C5092c() {
        m19927d("NativeDTCPLocker");
        m19927d("fribidi");
        this.f17539a = m19927d("claud");
        this.f17540b = m19927d("av");
        this.f17541c = m19928e("clmf_jni", 6);
    }
}
