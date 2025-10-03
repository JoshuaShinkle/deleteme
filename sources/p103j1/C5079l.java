package p103j1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p207u0.ComponentCallbacks2C6355e;
import p243y0.InterfaceC6595h;

/* renamed from: j1.l */
/* loaded from: classes.dex */
public class C5079l implements InterfaceC6595h<Drawable> {

    /* renamed from: b */
    public final InterfaceC6595h<Bitmap> f17512b;

    /* renamed from: c */
    public final boolean f17513c;

    public C5079l(InterfaceC6595h<Bitmap> interfaceC6595h, boolean z8) {
        this.f17512b = interfaceC6595h;
        this.f17513c = z8;
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        this.f17512b.mo3265a(messageDigest);
    }

    @Override // p243y0.InterfaceC6595h
    /* renamed from: b */
    public InterfaceC0595j<Drawable> mo19696b(Context context, InterfaceC0595j<Drawable> interfaceC0595j, int i9, int i10) {
        InterfaceC0707d interfaceC0707dM24391g = ComponentCallbacks2C6355e.m24381d(context).m24391g();
        Drawable drawable = interfaceC0595j.get();
        InterfaceC0595j<Bitmap> interfaceC0595jM19880a = C5078k.m19880a(interfaceC0707dM24391g, drawable, i9, i10);
        if (interfaceC0595jM19880a != null) {
            InterfaceC0595j<Bitmap> interfaceC0595jMo19696b = this.f17512b.mo19696b(context, interfaceC0595jM19880a, i9, i10);
            if (!interfaceC0595jMo19696b.equals(interfaceC0595jM19880a)) {
                return m19883d(context, interfaceC0595jMo19696b);
            }
            interfaceC0595jMo19696b.mo3281b();
            return interfaceC0595j;
        }
        if (!this.f17513c) {
            return interfaceC0595j;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    /* renamed from: c */
    public InterfaceC6595h<BitmapDrawable> m19882c() {
        return this;
    }

    /* renamed from: d */
    public final InterfaceC0595j<Drawable> m19883d(Context context, InterfaceC0595j<Bitmap> interfaceC0595j) {
        return C5083p.m19887f(context.getResources(), interfaceC0595j);
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (obj instanceof C5079l) {
            return this.f17512b.equals(((C5079l) obj).f17512b);
        }
        return false;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return this.f17512b.hashCode();
    }
}
