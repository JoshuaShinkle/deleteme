package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
class SingleGeneratedAdapterObserver implements InterfaceC0383d {

    /* renamed from: a */
    public final InterfaceC0382c f2209a;

    public SingleGeneratedAdapterObserver(InterfaceC0382c interfaceC0382c) {
        this.f2209a = interfaceC0382c;
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
        this.f2209a.m2080a(interfaceC0385f, event, false, null);
        this.f2209a.m2080a(interfaceC0385f, event, true, null);
    }
}
