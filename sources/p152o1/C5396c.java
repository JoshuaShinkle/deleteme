package p152o1;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p103j1.C5071d;
import p143n1.C5350c;
import p243y0.C6592e;

/* renamed from: o1.c */
/* loaded from: classes.dex */
public final class C5396c implements InterfaceC5398e<Drawable, byte[]> {

    /* renamed from: a */
    public final InterfaceC0707d f18292a;

    /* renamed from: b */
    public final InterfaceC5398e<Bitmap, byte[]> f18293b;

    /* renamed from: c */
    public final InterfaceC5398e<C5350c, byte[]> f18294c;

    public C5396c(InterfaceC0707d interfaceC0707d, InterfaceC5398e<Bitmap, byte[]> interfaceC5398e, InterfaceC5398e<C5350c, byte[]> interfaceC5398e2) {
        this.f18292a = interfaceC0707d;
        this.f18293b = interfaceC5398e;
        this.f18294c = interfaceC5398e2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static InterfaceC0595j<C5350c> m21103b(InterfaceC0595j<Drawable> interfaceC0595j) {
        return interfaceC0595j;
    }

    @Override // p152o1.InterfaceC5398e
    /* renamed from: a */
    public InterfaceC0595j<byte[]> mo21102a(InterfaceC0595j<Drawable> interfaceC0595j, C6592e c6592e) {
        Drawable drawable = interfaceC0595j.get();
        if (drawable instanceof BitmapDrawable) {
            return this.f18293b.mo21102a(C5071d.m19858f(((BitmapDrawable) drawable).getBitmap(), this.f18292a), c6592e);
        }
        if (drawable instanceof C5350c) {
            return this.f18294c.mo21102a(m21103b(interfaceC0595j), c6592e);
        }
        return null;
    }
}
