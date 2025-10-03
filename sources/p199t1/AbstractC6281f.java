package p199t1;

import p226w1.C6517j;

@Deprecated
/* renamed from: t1.f */
/* loaded from: classes.dex */
public abstract class AbstractC6281f<Z> extends AbstractC6276a<Z> {

    /* renamed from: c */
    public final int f21180c;

    /* renamed from: d */
    public final int f21181d;

    public AbstractC6281f() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: b */
    public void mo23899b(InterfaceC6282g interfaceC6282g) {
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: j */
    public final void mo23903j(InterfaceC6282g interfaceC6282g) {
        if (C6517j.m24959t(this.f21180c, this.f21181d)) {
            interfaceC6282g.mo4012e(this.f21180c, this.f21181d);
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.f21180c + " and height: " + this.f21181d + ", either provide dimensions in the constructor or call override()");
    }

    public AbstractC6281f(int i9, int i10) {
        this.f21180c = i9;
        this.f21181d = i10;
    }
}
