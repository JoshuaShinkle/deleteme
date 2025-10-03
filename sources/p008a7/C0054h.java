package p008a7;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: a7.h */
/* loaded from: classes.dex */
public final class C0054h {

    /* renamed from: a */
    public static final Map<String, Object> f146a = new ConcurrentHashMap();

    /* renamed from: b */
    public static final Map<String, Object> f147b = new ConcurrentHashMap();

    /* renamed from: a */
    public static void m185a(String str, String str2, Object obj) {
        if (!(obj instanceof InterfaceC0051e) && !(obj instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        f146a.put(m189e(str, str2), obj);
    }

    /* renamed from: b */
    public static void m186b(InterfaceC0053g interfaceC0053g) {
        if (interfaceC0053g.mo183b() != null) {
            for (C0050d c0050d : interfaceC0053g.mo183b()) {
                f147b.put(m189e(c0050d.mo177a(), c0050d.mo178b()), c0050d.m179c());
            }
        }
        if (interfaceC0053g.mo182a() != null) {
            for (C0048b c0048b : interfaceC0053g.mo182a()) {
                f146a.put(m189e(c0048b.mo177a(), c0048b.mo178b()), c0048b.m179c());
            }
        }
    }

    /* renamed from: c */
    public static Object m187c(String str, String str2) {
        return f146a.get(m189e(str, str2));
    }

    /* renamed from: d */
    public static Object m188d(String str, String str2) {
        return f147b.get(m189e(str, str2));
    }

    /* renamed from: e */
    public static String m189e(String str, String str2) {
        return str + '#' + str2;
    }
}
