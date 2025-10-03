package p182r2;

import com.cyberlink.you.chat.C2925v;
import p209u2.C6389z;

/* renamed from: r2.b */
/* loaded from: classes.dex */
public abstract class AbstractC6191b extends AbstractC6195d<C6201i> {

    /* renamed from: f */
    public final C6389z<InterfaceC6190a0> f20853f = new C6389z<>(mo23688d());

    /* renamed from: r2.b$a */
    public class a extends C6389z.a<InterfaceC6190a0> {

        /* renamed from: a */
        public final /* synthetic */ C6201i f20854a;

        public a(C6201i c6201i) {
            this.f20854a = c6201i;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC6190a0 interfaceC6190a0) {
            interfaceC6190a0.mo11938B0(this.f20854a.f20884c);
        }
    }

    /* renamed from: r2.b$b */
    public class b extends C6389z.a<InterfaceC6190a0> {

        /* renamed from: a */
        public final /* synthetic */ C6201i f20856a;

        public b(C6201i c6201i) {
            this.f20856a = c6201i;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC6190a0 interfaceC6190a0) {
            interfaceC6190a0.mo11940h(this.f20856a.f20884c);
        }
    }

    /* renamed from: r2.b$c */
    public class c extends C6389z.a<InterfaceC6190a0> {

        /* renamed from: a */
        public final /* synthetic */ C6201i f20858a;

        public c(C6201i c6201i) {
            this.f20858a = c6201i;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC6190a0 interfaceC6190a0) {
            interfaceC6190a0.mo11941v0(this.f20858a.f20884c);
        }
    }

    /* renamed from: k */
    public void m23672k(InterfaceC6190a0 interfaceC6190a0) {
        this.f20853f.m24539c(interfaceC6190a0);
    }

    @Override // p182r2.AbstractC6195d
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public void mo23669g(C6201i c6201i) {
        super.mo23669g(c6201i);
        this.f20853f.m24540f(new c(c6201i));
    }

    /* renamed from: m */
    public void m23674m(C6201i c6201i) {
        this.f20853f.m24540f(new a(c6201i));
    }

    @Override // p182r2.AbstractC6195d
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public void mo23670h(C6201i c6201i) {
        super.mo23670h(c6201i);
        if (C2925v.m14594f0(c6201i.f20884c)) {
            return;
        }
        C6202j.m23722a(c6201i.f20852a);
    }

    @Override // p182r2.AbstractC6195d
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public boolean mo23671i(C6201i c6201i) {
        this.f20853f.m24540f(new b(c6201i));
        return super.mo23671i(c6201i);
    }

    /* renamed from: p */
    public void m23677p(InterfaceC6190a0 interfaceC6190a0) {
        this.f20853f.m24541g(interfaceC6190a0);
    }
}
