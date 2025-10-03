package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.resource.bitmap.C0849a;
import java.io.IOException;
import java.io.InputStream;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p226w1.C6511d;
import p226w1.C6514g;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: com.bumptech.glide.load.resource.bitmap.b */
/* loaded from: classes.dex */
public class C0850b implements InterfaceC6593f<InputStream, Bitmap> {

    /* renamed from: a */
    public final C0849a f3908a;

    /* renamed from: b */
    public final InterfaceC0705b f3909b;

    /* renamed from: com.bumptech.glide.load.resource.bitmap.b$a */
    public static class a implements C0849a.b {

        /* renamed from: a */
        public final RecyclableBufferedInputStream f3910a;

        /* renamed from: b */
        public final C6511d f3911b;

        public a(RecyclableBufferedInputStream recyclableBufferedInputStream, C6511d c6511d) {
            this.f3910a = recyclableBufferedInputStream;
            this.f3911b = c6511d;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.C0849a.b
        /* renamed from: a */
        public void mo3995a(InterfaceC0707d interfaceC0707d, Bitmap bitmap) throws IOException {
            IOException iOExceptionM24920f = this.f3911b.m24920f();
            if (iOExceptionM24920f != null) {
                if (bitmap == null) {
                    throw iOExceptionM24920f;
                }
                interfaceC0707d.mo3487c(bitmap);
                throw iOExceptionM24920f;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.C0849a.b
        /* renamed from: b */
        public void mo3996b() {
            this.f3910a.m3972u();
        }
    }

    public C0850b(C0849a c0849a, InterfaceC0705b interfaceC0705b) {
        this.f3908a = c0849a;
        this.f3909b = interfaceC0705b;
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Bitmap> mo3998b(InputStream inputStream, int i9, int i10, C6592e c6592e) {
        boolean z8;
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z8 = false;
        } else {
            z8 = true;
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.f3909b);
        }
        C6511d c6511dM24919u = C6511d.m24919u(recyclableBufferedInputStream);
        try {
            return this.f3908a.m3990e(new C6514g(c6511dM24919u), i9, i10, c6592e, new a(recyclableBufferedInputStream, c6511dM24919u));
        } finally {
            c6511dM24919u.release();
            if (z8) {
                recyclableBufferedInputStream.release();
            }
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(InputStream inputStream, C6592e c6592e) {
        return this.f3908a.m3992m(inputStream);
    }
}
