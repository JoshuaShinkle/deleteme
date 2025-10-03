package p103j1;

import android.graphics.Bitmap;
import p012b1.InterfaceC0592g;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p226w1.C6516i;
import p226w1.C6517j;

/* renamed from: j1.d */
/* loaded from: classes.dex */
public class C5071d implements InterfaceC0595j<Bitmap>, InterfaceC0592g {

    /* renamed from: b */
    public final Bitmap f17500b;

    /* renamed from: c */
    public final InterfaceC0707d f17501c;

    public C5071d(Bitmap bitmap, InterfaceC0707d interfaceC0707d) {
        this.f17500b = (Bitmap) C6516i.m24939e(bitmap, "Bitmap must not be null");
        this.f17501c = (InterfaceC0707d) C6516i.m24939e(interfaceC0707d, "BitmapPool must not be null");
    }

    /* renamed from: f */
    public static C5071d m19858f(Bitmap bitmap, InterfaceC0707d interfaceC0707d) {
        if (bitmap == null) {
            return null;
        }
        return new C5071d(bitmap, interfaceC0707d);
    }

    @Override // p012b1.InterfaceC0592g
    /* renamed from: a */
    public void mo3274a() {
        this.f17500b.prepareToDraw();
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
        this.f17501c.mo3487c(this.f17500b);
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return C6517j.m24947h(this.f17500b);
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<Bitmap> mo3283d() {
        return Bitmap.class;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Bitmap get() {
        return this.f17500b;
    }
}
