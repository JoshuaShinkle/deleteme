package p103j1;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import p022c1.InterfaceC0707d;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.InterfaceC6589b;

/* renamed from: j1.r */
/* loaded from: classes.dex */
public final class C5085r extends AbstractC5072e {

    /* renamed from: c */
    public static final byte[] f17523c = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(InterfaceC6589b.f22139a);

    /* renamed from: b */
    public final int f17524b;

    public C5085r(int i9) {
        C6516i.m24935a(i9 > 0, "roundingRadius must be greater than 0.");
        this.f17524b = i9;
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        messageDigest.update(f17523c);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f17524b).array());
    }

    @Override // p103j1.AbstractC5072e
    /* renamed from: c */
    public Bitmap mo19860c(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        return C5086s.m19905o(interfaceC0707d, bitmap, this.f17524b);
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        return (obj instanceof C5085r) && this.f17524b == ((C5085r) obj).f17524b;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return C6517j.m24953n(-569625254, C6517j.m24952m(this.f17524b));
    }
}
