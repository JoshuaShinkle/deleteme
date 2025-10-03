package p182r2;

import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Group;

/* renamed from: r2.f */
/* loaded from: classes.dex */
public class C6198f extends C6204l {
    public C6198f(AbstractC6191b abstractC6191b, C6201i c6201i) {
        super(abstractC6191b, c6201i);
    }

    @Override // p182r2.C6204l, p182r2.AbstractC6197e
    /* renamed from: d */
    public String mo23706d() {
        return "XSender.E.T";
    }

    @Override // p182r2.C6204l, p182r2.AbstractC6197e
    /* renamed from: f */
    public void mo23708f() {
        super.mo23708f();
        m23711n();
    }

    @Override // p182r2.C6204l, p182r2.AbstractC6197e
    /* renamed from: g */
    public void mo23709g() {
        super.mo23709g();
        m23711n();
    }

    /* renamed from: n */
    public final void m23711n() {
        String strM14239d0;
        Group groupM15077n = C2950b0.m14912k().m15077n(((C6201i) this.f20875f).f20884c.m14772j());
        if (groupM15077n == null) {
            return;
        }
        C2904l c2904l = new C2904l(C2925v.m14593f(groupM15077n.f13716c, groupM15077n.f13723j, ((C6201i) this.f20875f).f20884c), XMPPManager.HandleType.UNKNOWN);
        if (groupM15077n.m15750g()) {
            strM14239d0 = XMPPManager.m14184g0().m14239d0();
        } else {
            strM14239d0 = groupM15077n.f13723j + "/" + Globals.m7388i0().m7568k1();
        }
        c2904l.m14447v0(strM14239d0);
        XMPPManager.m14184g0().m14228W0(c2904l, XMPPManager.HandleType.XMPP, false);
    }
}
