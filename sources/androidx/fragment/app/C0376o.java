package androidx.fragment.app;

import androidx.lifecycle.C0386g;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;

/* renamed from: androidx.fragment.app.o */
/* loaded from: classes.dex */
public class C0376o implements InterfaceC0385f {

    /* renamed from: b */
    public C0386g f2177b = null;

    /* renamed from: a */
    public void m2043a(Lifecycle.Event event) {
        this.f2177b.m2088i(event);
    }

    /* renamed from: b */
    public void m2044b() {
        if (this.f2177b == null) {
            this.f2177b = new C0386g(this);
        }
    }

    /* renamed from: c */
    public boolean m2045c() {
        return this.f2177b != null;
    }

    @Override // androidx.lifecycle.InterfaceC0385f
    public Lifecycle getLifecycle() {
        m2044b();
        return this.f2177b;
    }
}
