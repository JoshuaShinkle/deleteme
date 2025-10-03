package p124l1;

import android.graphics.drawable.Drawable;
import p012b1.InterfaceC0595j;

/* renamed from: l1.c */
/* loaded from: classes.dex */
public final class C5274c extends AbstractC5273b<Drawable> {
    public C5274c(Drawable drawable) {
        super(drawable);
    }

    /* renamed from: f */
    public static InterfaceC0595j<Drawable> m20539f(Drawable drawable) {
        if (drawable != null) {
            return new C5274c(drawable);
        }
        return null;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return Math.max(1, this.f17876b.getIntrinsicWidth() * this.f17876b.getIntrinsicHeight() * 4);
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<Drawable> mo3283d() {
        return this.f17876b.getClass();
    }
}
