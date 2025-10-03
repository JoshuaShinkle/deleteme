package p182r2;

import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.Group;
import java.util.List;
import p209u2.AbstractC6381r;

/* renamed from: r2.d0 */
/* loaded from: classes.dex */
public final class C6196d0 {

    /* renamed from: a */
    public final C6203k f20870a;

    /* renamed from: b */
    public final C6214v f20871b;

    /* renamed from: c */
    public final XMPPManager.InterfaceC2851b0 f20872c;

    /* renamed from: r2.d0$b */
    public static class b {

        /* renamed from: a */
        public static final C6196d0 f20873a = new C6196d0();
    }

    /* renamed from: d */
    public static C6196d0 m23692d() {
        return b.f20873a;
    }

    /* renamed from: b */
    public void m23693b(InterfaceC6190a0 interfaceC6190a0) {
        this.f20870a.m23672k(interfaceC6190a0);
    }

    /* renamed from: c */
    public void m23694c() {
        C6218z.m23766e("XSender.X.-", "clear", new Object[0]);
        this.f20870a.m23724q();
        this.f20871b.m23744m();
    }

    /* renamed from: e */
    public final void m23695e(boolean z8) {
        Object[] objArr = new Object[1];
        objArr[0] = z8 ? "connected" : "unavailable";
        C6218z.m23763b("XSender.X.-", "Connection: %s", objArr);
        if (z8 != XMPPManager.m14184g0().m14204A0()) {
            C6218z.m23770i("XSender.X.-", "> status not sync", new Object[0]);
        }
        m23696f((z8 || XMPPManager.m14184g0().m14204A0()) ? false : true);
    }

    /* renamed from: f */
    public final void m23696f(boolean z8) {
        Object[] objArr = new Object[1];
        objArr[0] = z8 ? "pause" : "resume";
        C6218z.m23766e("XSender.X.-", "inform: %s", objArr);
        this.f20870a.m23726s(z8);
        this.f20871b.m23745n(z8);
    }

    /* renamed from: g */
    public final void m23697g() {
        this.f20870a.m23727t();
        this.f20871b.m23746o();
    }

    /* renamed from: h */
    public void m23698h(InterfaceC6190a0 interfaceC6190a0) {
        this.f20870a.m23677p(interfaceC6190a0);
    }

    /* renamed from: i */
    public void m23699i(List<C6201i> list) {
        m23700j((C6201i[]) list.toArray(new C6201i[0]));
    }

    /* renamed from: j */
    public void m23700j(C6201i... c6201iArr) {
        for (C6201i c6201i : c6201iArr) {
            if (c6201i != null) {
                this.f20870a.m23728u(c6201i);
            }
        }
    }

    /* renamed from: k */
    public void m23701k(Group group, AbstractC6381r<Void, Void> abstractC6381r) {
        this.f20871b.m23748s(group, abstractC6381r);
    }

    /* renamed from: l */
    public void m23702l(String str, String str2) {
        this.f20871b.m23749t(str, str2);
    }

    /* renamed from: m */
    public void m23703m(Group group, String str, AbstractC6381r<Void, Void> abstractC6381r) {
        this.f20871b.m23753x(group, str, abstractC6381r);
    }

    public C6196d0() {
        XMPPManager.InterfaceC2851b0 interfaceC2851b0 = new XMPPManager.InterfaceC2851b0() { // from class: r2.c0
            @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
            /* renamed from: h0 */
            public final void mo13974h0(boolean z8) {
                this.f20861b.m23695e(z8);
            }
        };
        this.f20872c = interfaceC2851b0;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f20870a = new C6203k();
        this.f20871b = new C6214v();
        XMPPManager.m14184g0().m14211K(interfaceC2851b0);
        m23696f(!XMPPManager.m14184g0().m14204A0());
        m23697g();
        C6218z.m23766e("XSender.X.-", "instantiation took %dms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }
}
