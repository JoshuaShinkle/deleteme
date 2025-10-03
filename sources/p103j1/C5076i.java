package p103j1;

import android.graphics.Bitmap;
import java.security.MessageDigest;
import p022c1.InterfaceC0707d;
import p243y0.InterfaceC6589b;

/* renamed from: j1.i */
/* loaded from: classes.dex */
public class C5076i extends AbstractC5072e {

    /* renamed from: b */
    public static final byte[] f17505b = "com.bumptech.glide.load.resource.bitmap.CircleCrop.1".getBytes(InterfaceC6589b.f22139a);

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        messageDigest.update(f17505b);
    }

    @Override // p103j1.AbstractC5072e
    /* renamed from: c */
    public Bitmap mo19860c(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        return C5086s.m19894d(interfaceC0707d, bitmap, i9, i10);
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        return obj instanceof C5076i;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return 1101716364;
    }
}
