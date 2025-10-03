package p173q2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import p163p1.InterfaceC5877h;
import p163p1.InterfaceC5882m;
import p190s1.C6250g;
import p207u0.C6359i;
import p207u0.ComponentCallbacks2C6355e;

/* renamed from: q2.i */
/* loaded from: classes.dex */
public class C6135i extends C6359i {
    public C6135i(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC5877h interfaceC5877h, InterfaceC5882m interfaceC5882m, Context context) {
        super(componentCallbacks2C6355e, interfaceC5877h, interfaceC5882m, context);
    }

    /* renamed from: A */
    public C6134h<Drawable> m23572A(Integer num) {
        return (C6134h) super.m24435p(num);
    }

    /* renamed from: B */
    public C6134h<Drawable> m23573B(Object obj) {
        return (C6134h) super.m24436q(obj);
    }

    @Override // p207u0.C6359i
    /* renamed from: t */
    public void mo23577t(C6250g c6250g) {
        if (c6250g instanceof C6133g) {
            super.mo23577t(c6250g);
        } else {
            super.mo23577t(new C6133g().mo23515a(c6250g));
        }
    }

    @Override // p207u0.C6359i
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public <ResourceType> C6134h<ResourceType> mo23574c(Class<ResourceType> cls) {
        return new C6134h<>(this.f21485b, this, cls, this.f21486c);
    }

    @Override // p207u0.C6359i
    /* renamed from: y, reason: merged with bridge method [inline-methods] */
    public C6134h<Bitmap> mo23575d() {
        return (C6134h) super.mo23575d();
    }

    @Override // p207u0.C6359i
    /* renamed from: z, reason: merged with bridge method [inline-methods] */
    public C6134h<Drawable> mo23576k() {
        return (C6134h) super.mo23576k();
    }
}
