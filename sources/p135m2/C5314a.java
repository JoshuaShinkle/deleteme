package p135m2;

import com.cyberlink.clrtc.C1121t;
import com.cyberlink.util.DeviceCapability;
import com.google.firebase.iid.MessengerIpcClient;
import p044d2.C4664a;
import p044d2.C4665b;

/* renamed from: m2.a */
/* loaded from: classes.dex */
public final class C5314a {

    /* renamed from: a */
    public static volatile transient boolean f18053a = false;

    /* renamed from: a */
    public static String m20791a() {
        DeviceCapability.C1399a c1399aM7311e = DeviceCapability.m7311e();
        StringBuilder sb = new StringBuilder();
        sb.append("Hardware ");
        sb.append(C4664a.m18638j() ? "supported" : MessengerIpcClient.KEY_UNSUPPORTED);
        sb.append("; ");
        sb.append(c1399aM7311e.m7326g() ? "In blacklist" : "Not in blacklist");
        sb.append("; ");
        sb.append(C4664a.m18637i() ? "AOSP" : "Not AOSP");
        sb.append(" implementor");
        return sb.toString();
    }

    /* renamed from: b */
    public static String m20792b() {
        boolean zM18632b = C4664a.m18632b();
        StringBuilder sb = new StringBuilder();
        sb.append("Built-in AEC is ");
        sb.append(zM18632b ? "available" : "unavailable");
        return sb.toString();
    }

    /* renamed from: c */
    public static void m20793c() {
        if (f18053a) {
            return;
        }
        f18053a = true;
        DeviceCapability.C1399a c1399aM7311e = DeviceCapability.m7311e();
        C1121t.m5159h(c1399aM7311e.m7324e());
        if (C4664a.m18637i()) {
            C4665b.m18663o(true);
        } else if (c1399aM7311e.m7326g()) {
            C4665b.m18665q(true);
        }
    }

    /* renamed from: d */
    public static boolean m20794d() {
        return C1121t.m5155d();
    }

    /* renamed from: e */
    public static void m20795e() {
        f18053a = false;
    }
}
