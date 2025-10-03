package p103j1;

import android.graphics.Bitmap;
import com.bumptech.glide.load.resource.bitmap.C0849a;
import java.nio.ByteBuffer;
import p012b1.InterfaceC0595j;
import p226w1.C6508a;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: j1.f */
/* loaded from: classes.dex */
public class C5073f implements InterfaceC6593f<ByteBuffer, Bitmap> {

    /* renamed from: a */
    public final C0849a f17502a;

    public C5073f(C0849a c0849a) {
        this.f17502a = c0849a;
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Bitmap> mo3998b(ByteBuffer byteBuffer, int i9, int i10, C6592e c6592e) {
        return this.f17502a.m3989d(C6508a.m24916e(byteBuffer), i9, i10, c6592e);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(ByteBuffer byteBuffer, C6592e c6592e) {
        return this.f17502a.m3993n(byteBuffer);
    }
}
