package p114k2;

/* renamed from: k2.e */
/* loaded from: classes.dex */
public abstract class AbstractC5118e implements InterfaceC5119f {

    /* renamed from: b */
    public final int f17598b;

    /* renamed from: c */
    public final int f17599c;

    /* renamed from: d */
    public int f17600d;

    public AbstractC5118e(int i9, int i10, int i11) {
        this.f17598b = i10;
        this.f17599c = i11;
        m19980u();
        mo19979v(i9);
    }

    @Override // p114k2.InterfaceC5119f, java.lang.AutoCloseable
    public final void close() {
        mo19979v(this.f17600d);
    }

    /* renamed from: f */
    public abstract int mo19978f();

    /* renamed from: u */
    public void m19980u() {
        this.f17600d = mo19978f();
    }

    /* renamed from: v */
    public abstract void mo19979v(int i9);
}
