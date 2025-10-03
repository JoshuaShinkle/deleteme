package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* renamed from: androidx.transition.v */
/* loaded from: classes.dex */
public class C0541v implements InterfaceC0542w {

    /* renamed from: a */
    public final ViewGroupOverlay f2972a;

    public C0541v(ViewGroup viewGroup) {
        this.f2972a = viewGroup.getOverlay();
    }

    @Override // androidx.transition.InterfaceC0512a0
    /* renamed from: a */
    public void mo3019a(Drawable drawable) {
        this.f2972a.add(drawable);
    }

    @Override // androidx.transition.InterfaceC0512a0
    /* renamed from: b */
    public void mo3020b(Drawable drawable) {
        this.f2972a.remove(drawable);
    }

    @Override // androidx.transition.InterfaceC0542w
    /* renamed from: c */
    public void mo3119c(View view) {
        this.f2972a.add(view);
    }

    @Override // androidx.transition.InterfaceC0542w
    /* renamed from: d */
    public void mo3120d(View view) {
        this.f2972a.remove(view);
    }
}
