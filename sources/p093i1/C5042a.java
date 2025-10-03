package p093i1;

import p012b1.InterfaceC0595j;
import p226w1.C6516i;

/* renamed from: i1.a */
/* loaded from: classes.dex */
public class C5042a<T> implements InterfaceC0595j<T> {

    /* renamed from: b */
    public final T f17409b;

    public C5042a(T t8) {
        this.f17409b = (T) C6516i.m24938d(t8);
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public final int mo3282c() {
        return 1;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<T> mo3283d() {
        return (Class<T>) this.f17409b.getClass();
    }

    @Override // p012b1.InterfaceC0595j
    public final T get() {
        return this.f17409b;
    }
}
