package p103j1;

import android.content.Context;
import android.graphics.Bitmap;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p207u0.ComponentCallbacks2C6355e;
import p226w1.C6517j;
import p243y0.InterfaceC6595h;

/* renamed from: j1.e */
/* loaded from: classes.dex */
public abstract class AbstractC5072e implements InterfaceC6595h<Bitmap> {
    @Override // p243y0.InterfaceC6595h
    /* renamed from: b */
    public final InterfaceC0595j<Bitmap> mo19696b(Context context, InterfaceC0595j<Bitmap> interfaceC0595j, int i9, int i10) {
        if (!C6517j.m24959t(i9, i10)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i9 + " or height: " + i10 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        InterfaceC0707d interfaceC0707dM24391g = ComponentCallbacks2C6355e.m24381d(context).m24391g();
        Bitmap bitmap = interfaceC0595j.get();
        if (i9 == Integer.MIN_VALUE) {
            i9 = bitmap.getWidth();
        }
        if (i10 == Integer.MIN_VALUE) {
            i10 = bitmap.getHeight();
        }
        Bitmap bitmapMo19860c = mo19860c(interfaceC0707dM24391g, bitmap, i9, i10);
        return bitmap.equals(bitmapMo19860c) ? interfaceC0595j : C5071d.m19858f(bitmapMo19860c, interfaceC0707dM24391g);
    }

    /* renamed from: c */
    public abstract Bitmap mo19860c(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10);
}
