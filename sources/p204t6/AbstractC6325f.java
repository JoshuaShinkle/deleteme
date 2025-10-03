package p204t6;

import p007a6.C0042f;

/* renamed from: t6.f */
/* loaded from: classes.dex */
public abstract class AbstractC6325f implements InterfaceC6340u {

    /* renamed from: b */
    public final InterfaceC6340u f21339b;

    public AbstractC6325f(InterfaceC6340u interfaceC6340u) {
        C0042f.m158e(interfaceC6340u, "delegate");
        this.f21339b = interfaceC6340u;
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: a */
    public C6343x mo21081a() {
        return this.f21339b.mo21081a();
    }

    @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f21339b.close();
    }

    @Override // p204t6.InterfaceC6340u, java.io.Flushable
    public void flush() {
        this.f21339b.flush();
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: q */
    public void mo21082q(C6322c c6322c, long j9) {
        C0042f.m158e(c6322c, "source");
        this.f21339b.mo21082q(c6322c, j9);
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f21339b + ')';
    }
}
