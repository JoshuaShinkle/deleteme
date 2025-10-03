package p044d2;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import java.util.Arrays;
import java.util.List;
import org.webrtc.Logging;

/* renamed from: d2.b */
/* loaded from: classes.dex */
public final class C4665b {

    /* renamed from: a */
    public static final String[] f16334a = new String[0];

    /* renamed from: b */
    public static final String[] f16335b = {"Nexus 10", "Nexus 9", "ONE A2005"};

    /* renamed from: c */
    public static int f16336c = 16000;

    /* renamed from: d */
    public static boolean f16337d = false;

    /* renamed from: e */
    public static boolean f16338e = false;

    /* renamed from: f */
    public static boolean f16339f = false;

    /* renamed from: g */
    public static boolean f16340g = false;

    /* renamed from: a */
    public static boolean m18649a() {
        return Arrays.asList(f16334a).contains(Build.MODEL);
    }

    /* renamed from: b */
    public static List<String> m18650b() {
        return Arrays.asList(f16335b);
    }

    /* renamed from: c */
    public static synchronized int m18651c() {
        return f16336c;
    }

    /* renamed from: d */
    public static String m18652d() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    /* renamed from: e */
    public static boolean m18653e(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: f */
    public static synchronized boolean m18654f() {
        return f16337d;
    }

    /* renamed from: g */
    public static boolean m18655g() {
        return Build.HARDWARE.equals("goldfish") && Build.BRAND.startsWith("generic_");
    }

    /* renamed from: h */
    public static boolean m18656h() {
        return true;
    }

    /* renamed from: i */
    public static boolean m18657i() {
        return true;
    }

    /* renamed from: j */
    public static boolean m18658j() {
        return true;
    }

    /* renamed from: k */
    public static boolean m18659k() {
        return true;
    }

    /* renamed from: l */
    public static boolean m18660l() {
        return true;
    }

    /* renamed from: m */
    public static boolean m18661m() {
        return true;
    }

    /* renamed from: n */
    public static boolean m18662n() {
        return true;
    }

    /* renamed from: o */
    public static synchronized void m18663o(boolean z8) {
        if (C4664a.m18637i()) {
            f16339f = z8;
        }
    }

    /* renamed from: p */
    public static synchronized void m18664p(int i9) {
        f16337d = true;
        f16336c = i9;
    }

    /* renamed from: q */
    public static synchronized void m18665q(boolean z8) {
        f16338e = z8;
    }

    /* renamed from: r */
    public static synchronized boolean m18666r() {
        if (f16339f) {
            Logging.m23189w("AudioUtils", "Overriding default behavior; now using AOSP AEC!");
        }
        return f16339f;
    }

    /* renamed from: s */
    public static synchronized boolean m18667s() {
        if (f16338e) {
            Logging.m23189w("AudioUtils", "Overriding default behavior; now using WebRTC AEC!");
        }
        return f16338e;
    }

    /* renamed from: t */
    public static synchronized boolean m18668t() {
        if (f16340g) {
            Logging.m23189w("AudioUtils", "Overriding default behavior; now using WebRTC NS!");
        }
        return f16340g;
    }
}
