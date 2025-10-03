package p103j1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p124l1.C5275d;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: j1.q */
/* loaded from: classes.dex */
public class C5084q implements InterfaceC6593f<Uri, Bitmap> {

    /* renamed from: a */
    public final C5275d f17521a;

    /* renamed from: b */
    public final InterfaceC0707d f17522b;

    public C5084q(C5275d c5275d, InterfaceC0707d interfaceC0707d) {
        this.f17521a = c5275d;
        this.f17522b = interfaceC0707d;
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Bitmap> mo3998b(Uri uri, int i9, int i10, C6592e c6592e) throws NumberFormatException {
        InterfaceC0595j<Drawable> interfaceC0595jMo3998b = this.f17521a.mo3998b(uri, i9, i10, c6592e);
        if (interfaceC0595jMo3998b == null) {
            return null;
        }
        return C5078k.m19880a(this.f17522b, interfaceC0595jMo3998b.get(), i9, i10);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(Uri uri, C6592e c6592e) {
        return "android.resource".equals(uri.getScheme());
    }
}
