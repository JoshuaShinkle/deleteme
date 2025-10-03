package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements InterfaceC0383d {

    /* renamed from: a */
    public final InterfaceC0382c[] f2178a;

    public CompositeGeneratedAdaptersObserver(InterfaceC0382c[] interfaceC0382cArr) {
        this.f2178a = interfaceC0382cArr;
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
        C0389j c0389j = new C0389j();
        for (InterfaceC0382c interfaceC0382c : this.f2178a) {
            interfaceC0382c.m2080a(interfaceC0385f, event, false, c0389j);
        }
        for (InterfaceC0382c interfaceC0382c2 : this.f2178a) {
            interfaceC0382c2.m2080a(interfaceC0385f, event, true, c0389j);
        }
    }
}
