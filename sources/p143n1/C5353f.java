package p143n1;

import android.content.Context;
import android.graphics.Bitmap;
import java.security.MessageDigest;
import p012b1.InterfaceC0595j;
import p103j1.C5071d;
import p207u0.ComponentCallbacks2C6355e;
import p226w1.C6516i;
import p243y0.InterfaceC6595h;

/* renamed from: n1.f */
/* loaded from: classes.dex */
public class C5353f implements InterfaceC6595h<C5350c> {

    /* renamed from: b */
    public final InterfaceC6595h<Bitmap> f18218b;

    public C5353f(InterfaceC6595h<Bitmap> interfaceC6595h) {
        this.f18218b = (InterfaceC6595h) C6516i.m24938d(interfaceC6595h);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        this.f18218b.mo3265a(messageDigest);
    }

    @Override // p243y0.InterfaceC6595h
    /* renamed from: b */
    public InterfaceC0595j<C5350c> mo19696b(Context context, InterfaceC0595j<C5350c> interfaceC0595j, int i9, int i10) {
        C5350c c5350c = interfaceC0595j.get();
        InterfaceC0595j<Bitmap> c5071d = new C5071d(c5350c.m21013e(), ComponentCallbacks2C6355e.m24381d(context).m24391g());
        InterfaceC0595j<Bitmap> interfaceC0595jMo19696b = this.f18218b.mo19696b(context, c5071d, i9, i10);
        if (!c5071d.equals(interfaceC0595jMo19696b)) {
            c5071d.mo3281b();
        }
        c5350c.m21020l(this.f18218b, interfaceC0595jMo19696b.get());
        return interfaceC0595j;
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (obj instanceof C5353f) {
            return this.f18218b.equals(((C5353f) obj).f18218b);
        }
        return false;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return this.f18218b.hashCode();
    }
}
