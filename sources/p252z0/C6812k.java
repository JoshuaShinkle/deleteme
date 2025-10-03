package p252z0;

import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.InputStream;
import p022c1.InterfaceC0705b;
import p252z0.InterfaceC6806e;

/* renamed from: z0.k */
/* loaded from: classes.dex */
public final class C6812k implements InterfaceC6806e<InputStream> {

    /* renamed from: a */
    public final RecyclableBufferedInputStream f22564a;

    /* renamed from: z0.k$a */
    public static final class a implements InterfaceC6806e.a<InputStream> {

        /* renamed from: a */
        public final InterfaceC0705b f22565a;

        public a(InterfaceC0705b interfaceC0705b) {
            this.f22565a = interfaceC0705b;
        }

        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: a */
        public Class<InputStream> mo19966a() {
            return InputStream.class;
        }

        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public InterfaceC6806e<InputStream> mo19967b(InputStream inputStream) {
            return new C6812k(inputStream, this.f22565a);
        }
    }

    public C6812k(InputStream inputStream, InterfaceC0705b interfaceC0705b) {
        RecyclableBufferedInputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, interfaceC0705b);
        this.f22564a = recyclableBufferedInputStream;
        recyclableBufferedInputStream.mark(5242880);
    }

    @Override // p252z0.InterfaceC6806e
    /* renamed from: b */
    public void mo19964b() {
        this.f22564a.release();
    }

    @Override // p252z0.InterfaceC6806e
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InputStream mo19963a() {
        this.f22564a.reset();
        return this.f22564a;
    }
}
