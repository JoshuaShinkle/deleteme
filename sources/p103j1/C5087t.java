package p103j1;

import android.graphics.Bitmap;
import p012b1.InterfaceC0595j;
import p226w1.C6517j;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: j1.t */
/* loaded from: classes.dex */
public final class C5087t implements InterfaceC6593f<Bitmap, Bitmap> {

    /* renamed from: j1.t$a */
    public static final class a implements InterfaceC0595j<Bitmap> {

        /* renamed from: b */
        public final Bitmap f17530b;

        public a(Bitmap bitmap) {
            this.f17530b = bitmap;
        }

        @Override // p012b1.InterfaceC0595j
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap get() {
            return this.f17530b;
        }

        @Override // p012b1.InterfaceC0595j
        /* renamed from: b */
        public void mo3281b() {
        }

        @Override // p012b1.InterfaceC0595j
        /* renamed from: c */
        public int mo3282c() {
            return C6517j.m24947h(this.f17530b);
        }

        @Override // p012b1.InterfaceC0595j
        /* renamed from: d */
        public Class<Bitmap> mo3283d() {
            return Bitmap.class;
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Bitmap> mo3998b(Bitmap bitmap, int i9, int i10, C6592e c6592e) {
        return new a(bitmap);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(Bitmap bitmap, C6592e c6592e) {
        return true;
    }
}
