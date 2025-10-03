package p190s1;

/* renamed from: s1.a */
/* loaded from: classes.dex */
public final class C6244a implements InterfaceC6247d, InterfaceC6246c {

    /* renamed from: b */
    public final InterfaceC6247d f21050b;

    /* renamed from: c */
    public InterfaceC6246c f21051c;

    /* renamed from: d */
    public InterfaceC6246c f21052d;

    public C6244a(InterfaceC6247d interfaceC6247d) {
        this.f21050b = interfaceC6247d;
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: a */
    public boolean mo23886a(InterfaceC6246c interfaceC6246c) {
        return m23894o() && m23892m(interfaceC6246c);
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: b */
    public void mo4009b() {
        this.f21051c.mo4009b();
        this.f21052d.mo4009b();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: c */
    public boolean mo23887c() {
        return m23896q() || mo4011d();
    }

    @Override // p190s1.InterfaceC6246c
    public void clear() {
        this.f21051c.clear();
        if (this.f21052d.isRunning()) {
            this.f21052d.clear();
        }
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: d */
    public boolean mo4011d() {
        return (this.f21051c.mo4014g() ? this.f21052d : this.f21051c).mo4011d();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: e */
    public void mo23888e(InterfaceC6246c interfaceC6246c) {
        if (!interfaceC6246c.equals(this.f21052d)) {
            if (this.f21052d.isRunning()) {
                return;
            }
            this.f21052d.mo4016i();
        } else {
            InterfaceC6247d interfaceC6247d = this.f21050b;
            if (interfaceC6247d != null) {
                interfaceC6247d.mo23888e(this);
            }
        }
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: f */
    public boolean mo4013f(InterfaceC6246c interfaceC6246c) {
        if (!(interfaceC6246c instanceof C6244a)) {
            return false;
        }
        C6244a c6244a = (C6244a) interfaceC6246c;
        return this.f21051c.mo4013f(c6244a.f21051c) && this.f21052d.mo4013f(c6244a.f21052d);
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: g */
    public boolean mo4014g() {
        return this.f21051c.mo4014g() && this.f21052d.mo4014g();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: h */
    public boolean mo4015h() {
        return (this.f21051c.mo4014g() ? this.f21052d : this.f21051c).mo4015h();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: i */
    public void mo4016i() {
        if (this.f21051c.isRunning()) {
            return;
        }
        this.f21051c.mo4016i();
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isComplete() {
        return (this.f21051c.mo4014g() ? this.f21052d : this.f21051c).isComplete();
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isRunning() {
        return (this.f21051c.mo4014g() ? this.f21052d : this.f21051c).isRunning();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: j */
    public void mo23889j(InterfaceC6246c interfaceC6246c) {
        InterfaceC6247d interfaceC6247d = this.f21050b;
        if (interfaceC6247d != null) {
            interfaceC6247d.mo23889j(this);
        }
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: k */
    public boolean mo23890k(InterfaceC6246c interfaceC6246c) {
        return m23895p() && m23892m(interfaceC6246c);
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: l */
    public boolean mo23891l(InterfaceC6246c interfaceC6246c) {
        return m23893n() && m23892m(interfaceC6246c);
    }

    /* renamed from: m */
    public final boolean m23892m(InterfaceC6246c interfaceC6246c) {
        return interfaceC6246c.equals(this.f21051c) || (this.f21051c.mo4014g() && interfaceC6246c.equals(this.f21052d));
    }

    /* renamed from: n */
    public final boolean m23893n() {
        InterfaceC6247d interfaceC6247d = this.f21050b;
        return interfaceC6247d == null || interfaceC6247d.mo23891l(this);
    }

    /* renamed from: o */
    public final boolean m23894o() {
        InterfaceC6247d interfaceC6247d = this.f21050b;
        return interfaceC6247d == null || interfaceC6247d.mo23886a(this);
    }

    /* renamed from: p */
    public final boolean m23895p() {
        InterfaceC6247d interfaceC6247d = this.f21050b;
        return interfaceC6247d == null || interfaceC6247d.mo23890k(this);
    }

    /* renamed from: q */
    public final boolean m23896q() {
        InterfaceC6247d interfaceC6247d = this.f21050b;
        return interfaceC6247d != null && interfaceC6247d.mo23887c();
    }

    /* renamed from: r */
    public void m23897r(InterfaceC6246c interfaceC6246c, InterfaceC6246c interfaceC6246c2) {
        this.f21051c = interfaceC6246c;
        this.f21052d = interfaceC6246c2;
    }
}
