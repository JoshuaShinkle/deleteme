package p103j1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import p012b1.InterfaceC0595j;
import p226w1.C6516i;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: j1.a */
/* loaded from: classes.dex */
public class C5068a<DataType> implements InterfaceC6593f<DataType, BitmapDrawable> {

    /* renamed from: a */
    public final InterfaceC6593f<DataType, Bitmap> f17493a;

    /* renamed from: b */
    public final Resources f17494b;

    public C5068a(Resources resources, InterfaceC6593f<DataType, Bitmap> interfaceC6593f) {
        this.f17494b = (Resources) C6516i.m24938d(resources);
        this.f17493a = (InterfaceC6593f) C6516i.m24938d(interfaceC6593f);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: a */
    public boolean mo3997a(DataType datatype, C6592e c6592e) {
        return this.f17493a.mo3997a(datatype, c6592e);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: b */
    public InterfaceC0595j<BitmapDrawable> mo3998b(DataType datatype, int i9, int i10, C6592e c6592e) {
        return C5083p.m19887f(this.f17494b, this.f17493a.mo3998b(datatype, i9, i10, c6592e));
    }
}
