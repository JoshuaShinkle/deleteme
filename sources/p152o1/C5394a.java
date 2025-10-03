package p152o1;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import p012b1.InterfaceC0595j;
import p113k1.C5113b;
import p243y0.C6592e;

/* renamed from: o1.a */
/* loaded from: classes.dex */
public class C5394a implements InterfaceC5398e<Bitmap, byte[]> {

    /* renamed from: a */
    public final Bitmap.CompressFormat f18289a;

    /* renamed from: b */
    public final int f18290b;

    public C5394a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    @Override // p152o1.InterfaceC5398e
    /* renamed from: a */
    public InterfaceC0595j<byte[]> mo21102a(InterfaceC0595j<Bitmap> interfaceC0595j, C6592e c6592e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        interfaceC0595j.get().compress(this.f18289a, this.f18290b, byteArrayOutputStream);
        interfaceC0595j.mo3281b();
        return new C5113b(byteArrayOutputStream.toByteArray());
    }

    public C5394a(Bitmap.CompressFormat compressFormat, int i9) {
        this.f18289a = compressFormat;
        this.f18290b = i9;
    }
}
