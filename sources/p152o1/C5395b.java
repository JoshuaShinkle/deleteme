package p152o1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import p012b1.InterfaceC0595j;
import p103j1.C5083p;
import p226w1.C6516i;
import p243y0.C6592e;

/* renamed from: o1.b */
/* loaded from: classes.dex */
public class C5395b implements InterfaceC5398e<Bitmap, BitmapDrawable> {

    /* renamed from: a */
    public final Resources f18291a;

    public C5395b(Resources resources) {
        this.f18291a = (Resources) C6516i.m24938d(resources);
    }

    @Override // p152o1.InterfaceC5398e
    /* renamed from: a */
    public InterfaceC0595j<BitmapDrawable> mo21102a(InterfaceC0595j<Bitmap> interfaceC0595j, C6592e c6592e) {
        return C5083p.m19887f(this.f18291a, interfaceC0595j);
    }
}
