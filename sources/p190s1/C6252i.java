package p190s1;

/* renamed from: s1.i */
/* loaded from: classes.dex */
public class C6252i implements InterfaceC6247d, InterfaceC6246c {

    /* renamed from: b */
    public final InterfaceC6247d f21091b;

    /* renamed from: c */
    public InterfaceC6246c f21092c;

    /* renamed from: d */
    public InterfaceC6246c f21093d;

    /* renamed from: e */
    public boolean f21094e;

    public C6252i() {
        this(null);
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: a */
    public boolean mo23886a(InterfaceC6246c interfaceC6246c) {
        return m23951n() && interfaceC6246c.equals(this.f21092c) && !mo23887c();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: b */
    public void mo4009b() {
        this.f21092c.mo4009b();
        this.f21093d.mo4009b();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: c */
    public boolean mo23887c() {
        return m23953p() || mo4011d();
    }

    @Override // p190s1.InterfaceC6246c
    public void clear() {
        this.f21094e = false;
        this.f21093d.clear();
        this.f21092c.clear();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: d */
    public boolean mo4011d() {
        return this.f21092c.mo4011d() || this.f21093d.mo4011d();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: e */
    public void mo23888e(InterfaceC6246c interfaceC6246c) {
        InterfaceC6247d interfaceC6247d;
        if (interfaceC6246c.equals(this.f21092c) && (interfaceC6247d = this.f21091b) != null) {
            interfaceC6247d.mo23888e(this);
        }
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: f */
    public boolean mo4013f(InterfaceC6246c interfaceC6246c) {
        if (!(interfaceC6246c instanceof C6252i)) {
            return false;
        }
        C6252i c6252i = (C6252i) interfaceC6246c;
        InterfaceC6246c interfaceC6246c2 = this.f21092c;
        if (interfaceC6246c2 == null) {
            if (c6252i.f21092c != null) {
                return false;
            }
        } else if (!interfaceC6246c2.mo4013f(c6252i.f21092c)) {
            return false;
        }
        InterfaceC6246c interfaceC6246c3 = this.f21093d;
        InterfaceC6246c interfaceC6246c4 = c6252i.f21093d;
        if (interfaceC6246c3 == null) {
            if (interfaceC6246c4 != null) {
                return false;
            }
        } else if (!interfaceC6246c3.mo4013f(interfaceC6246c4)) {
            return false;
        }
        return true;
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: g */
    public boolean mo4014g() {
        return this.f21092c.mo4014g();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: h */
    public boolean mo4015h() {
        return this.f21092c.mo4015h();
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: i */
    public void mo4016i() {
        this.f21094e = true;
        if (!this.f21092c.isComplete() && !this.f21093d.isRunning()) {
            this.f21093d.mo4016i();
        }
        if (!this.f21094e || this.f21092c.isRunning()) {
            return;
        }
        this.f21092c.mo4016i();
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isComplete() {
        return this.f21092c.isComplete() || this.f21093d.isComplete();
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isRunning() {
        return this.f21092c.isRunning();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: j */
    public void mo23889j(InterfaceC6246c interfaceC6246c) {
        if (interfaceC6246c.equals(this.f21093d)) {
            return;
        }
        InterfaceC6247d interfaceC6247d = this.f21091b;
        if (interfaceC6247d != null) {
            interfaceC6247d.mo23889j(this);
        }
        if (this.f21093d.isComplete()) {
            return;
        }
        this.f21093d.clear();
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: k */
    public boolean mo23890k(InterfaceC6246c interfaceC6246c) {
        return m23952o() && (interfaceC6246c.equals(this.f21092c) || !this.f21092c.mo4011d());
    }

    @Override // p190s1.InterfaceC6247d
    /* renamed from: l */
    public boolean mo23891l(InterfaceC6246c interfaceC6246c) {
        return m23950m() && interfaceC6246c.equals(this.f21092c);
    }

    /* renamed from: m */
    public final boolean m23950m() {
        InterfaceC6247d interfaceC6247d = this.f21091b;
        return interfaceC6247d == null || interfaceC6247d.mo23891l(this);
    }

    /* renamed from: n */
    public final boolean m23951n() {
        InterfaceC6247d interfaceC6247d = this.f21091b;
        return interfaceC6247d == null || interfaceC6247d.mo23886a(this);
    }

    /* renamed from: o */
    public final boolean m23952o() {
        InterfaceC6247d interfaceC6247d = this.f21091b;
        return interfaceC6247d == null || interfaceC6247d.mo23890k(this);
    }

    /* renamed from: p */
    public final boolean m23953p() {
        InterfaceC6247d interfaceC6247d = this.f21091b;
        return interfaceC6247d != null && interfaceC6247d.mo23887c();
    }

    /* renamed from: q */
    public void m23954q(InterfaceC6246c interfaceC6246c, InterfaceC6246c interfaceC6246c2) {
        this.f21092c = interfaceC6246c;
        this.f21093d = interfaceC6246c2;
    }

    public C6252i(InterfaceC6247d interfaceC6247d) {
        this.f21091b = interfaceC6247d;
    }
}
