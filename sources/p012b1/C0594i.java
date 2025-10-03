package p012b1;

import p021c0.InterfaceC0699e;
import p226w1.C6516i;
import p235x1.AbstractC6565c;
import p235x1.C6563a;

/* renamed from: b1.i */
/* loaded from: classes.dex */
public final class C0594i<Z> implements InterfaceC0595j<Z>, C6563a.f {

    /* renamed from: f */
    public static final InterfaceC0699e<C0594i<?>> f3123f = C6563a.m25128e(20, new a());

    /* renamed from: b */
    public final AbstractC6565c f3124b = AbstractC6565c.m25138a();

    /* renamed from: c */
    public InterfaceC0595j<Z> f3125c;

    /* renamed from: d */
    public boolean f3126d;

    /* renamed from: e */
    public boolean f3127e;

    /* renamed from: b1.i$a */
    public class a implements C6563a.d<C0594i<?>> {
        @Override // p235x1.C6563a.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0594i<?> mo3287a() {
            return new C0594i<>();
        }
    }

    /* renamed from: e */
    public static <Z> C0594i<Z> m3279e(InterfaceC0595j<Z> interfaceC0595j) {
        C0594i<Z> c0594i = (C0594i) C6516i.m24938d(f3123f.mo3465b());
        c0594i.m3280a(interfaceC0595j);
        return c0594i;
    }

    /* renamed from: a */
    public final void m3280a(InterfaceC0595j<Z> interfaceC0595j) {
        this.f3127e = false;
        this.f3126d = true;
        this.f3125c = interfaceC0595j;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public synchronized void mo3281b() {
        this.f3124b.mo25140c();
        this.f3127e = true;
        if (!this.f3126d) {
            this.f3125c.mo3281b();
            m3284f();
        }
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return this.f3125c.mo3282c();
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<Z> mo3283d() {
        return this.f3125c.mo3283d();
    }

    /* renamed from: f */
    public final void m3284f() {
        this.f3125c = null;
        f3123f.mo3464a(this);
    }

    /* renamed from: g */
    public synchronized void m3285g() {
        this.f3124b.mo25140c();
        if (!this.f3126d) {
            throw new IllegalStateException("Already unlocked");
        }
        this.f3126d = false;
        if (this.f3127e) {
            mo3281b();
        }
    }

    @Override // p012b1.InterfaceC0595j
    public Z get() {
        return this.f3125c.get();
    }

    @Override // p235x1.C6563a.f
    /* renamed from: j */
    public AbstractC6565c mo3286j() {
        return this.f3124b;
    }
}
