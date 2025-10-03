package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* renamed from: androidx.transition.z */
/* loaded from: classes.dex */
public class C0545z implements InterfaceC0512a0 {

    /* renamed from: a */
    public final ViewOverlay f2975a;

    public C0545z(View view) {
        this.f2975a = view.getOverlay();
    }

    @Override // androidx.transition.InterfaceC0512a0
    /* renamed from: a */
    public void mo3019a(Drawable drawable) {
        this.f2975a.add(drawable);
    }

    @Override // androidx.transition.InterfaceC0512a0
    /* renamed from: b */
    public void mo3020b(Drawable drawable) {
        this.f2975a.remove(drawable);
    }
}
