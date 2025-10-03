package p103j1;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.EncodeStrategy;
import java.io.File;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p243y0.C6592e;
import p243y0.InterfaceC6594g;

/* renamed from: j1.b */
/* loaded from: classes.dex */
public class C5069b implements InterfaceC6594g<BitmapDrawable> {

    /* renamed from: a */
    public final InterfaceC0707d f17495a;

    /* renamed from: b */
    public final InterfaceC6594g<Bitmap> f17496b;

    public C5069b(InterfaceC0707d interfaceC0707d, InterfaceC6594g<Bitmap> interfaceC6594g) {
        this.f17495a = interfaceC0707d;
        this.f17496b = interfaceC6594g;
    }

    @Override // p243y0.InterfaceC6594g
    /* renamed from: a */
    public EncodeStrategy mo19854a(C6592e c6592e) {
        return this.f17496b.mo19854a(c6592e);
    }

    @Override // p243y0.InterfaceC6588a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean mo19086b(InterfaceC0595j<BitmapDrawable> interfaceC0595j, File file, C6592e c6592e) {
        return this.f17496b.mo19086b(new C5071d(interfaceC0595j.get().getBitmap(), this.f17495a), file, c6592e);
    }
}
