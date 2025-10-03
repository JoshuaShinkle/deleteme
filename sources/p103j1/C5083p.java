package p103j1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import p012b1.InterfaceC0592g;
import p012b1.InterfaceC0595j;
import p226w1.C6516i;

/* renamed from: j1.p */
/* loaded from: classes.dex */
public final class C5083p implements InterfaceC0595j<BitmapDrawable>, InterfaceC0592g {

    /* renamed from: b */
    public final Resources f17519b;

    /* renamed from: c */
    public final InterfaceC0595j<Bitmap> f17520c;

    public C5083p(Resources resources, InterfaceC0595j<Bitmap> interfaceC0595j) {
        this.f17519b = (Resources) C6516i.m24938d(resources);
        this.f17520c = (InterfaceC0595j) C6516i.m24938d(interfaceC0595j);
    }

    /* renamed from: f */
    public static InterfaceC0595j<BitmapDrawable> m19887f(Resources resources, InterfaceC0595j<Bitmap> interfaceC0595j) {
        if (interfaceC0595j == null) {
            return null;
        }
        return new C5083p(resources, interfaceC0595j);
    }

    @Override // p012b1.InterfaceC0592g
    /* renamed from: a */
    public void mo3274a() {
        InterfaceC0595j<Bitmap> interfaceC0595j = this.f17520c;
        if (interfaceC0595j instanceof InterfaceC0592g) {
            ((InterfaceC0592g) interfaceC0595j).mo3274a();
        }
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
        this.f17520c.mo3281b();
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return this.f17520c.mo3282c();
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<BitmapDrawable> mo3283d() {
        return BitmapDrawable.class;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public BitmapDrawable get() {
        return new BitmapDrawable(this.f17519b, this.f17520c.get());
    }
}
