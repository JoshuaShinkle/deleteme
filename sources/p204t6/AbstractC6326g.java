package p204t6;

import p007a6.C0042f;

/* renamed from: t6.g */
/* loaded from: classes.dex */
public abstract class AbstractC6326g implements InterfaceC6342w {

    /* renamed from: b */
    public final InterfaceC6342w f21340b;

    public AbstractC6326g(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "delegate");
        this.f21340b = interfaceC6342w;
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return this.f21340b.mo21076a();
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f21340b.close();
    }

    /* renamed from: f */
    public final InterfaceC6342w m24238f() {
        return this.f21340b;
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f21340b + ')';
    }
}
