package p143n1;

import android.graphics.Bitmap;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p103j1.C5071d;
import p225w0.InterfaceC6503a;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: n1.h */
/* loaded from: classes.dex */
public final class C5355h implements InterfaceC6593f<InterfaceC6503a, Bitmap> {

    /* renamed from: a */
    public final InterfaceC0707d f18239a;

    public C5355h(InterfaceC0707d interfaceC0707d) {
        this.f18239a = interfaceC0707d;
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Bitmap> mo3998b(InterfaceC6503a interfaceC6503a, int i9, int i10, C6592e c6592e) {
        return C5071d.m19858f(interfaceC6503a.getNextFrame(), this.f18239a);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(InterfaceC6503a interfaceC6503a, C6592e c6592e) {
        return true;
    }
}
