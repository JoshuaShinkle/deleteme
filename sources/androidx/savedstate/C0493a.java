package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* renamed from: androidx.savedstate.a */
/* loaded from: classes.dex */
public final class C0493a {

    /* renamed from: a */
    public final InterfaceC0494b f2754a;

    /* renamed from: b */
    public final SavedStateRegistry f2755b = new SavedStateRegistry();

    public C0493a(InterfaceC0494b interfaceC0494b) {
        this.f2754a = interfaceC0494b;
    }

    /* renamed from: a */
    public static C0493a m2968a(InterfaceC0494b interfaceC0494b) {
        return new C0493a(interfaceC0494b);
    }

    /* renamed from: b */
    public SavedStateRegistry m2969b() {
        return this.f2755b;
    }

    /* renamed from: c */
    public void m2970c(Bundle bundle) {
        Lifecycle lifecycle = this.f2754a.getLifecycle();
        if (lifecycle.mo2048b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.mo2047a(new Recreator(this.f2754a));
        this.f2755b.m2964b(lifecycle, bundle);
    }

    /* renamed from: d */
    public void m2971d(Bundle bundle) {
        this.f2755b.m2965c(bundle);
    }
}
