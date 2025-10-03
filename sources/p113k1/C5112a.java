package p113k1;

import java.nio.ByteBuffer;
import p252z0.InterfaceC6806e;

/* renamed from: k1.a */
/* loaded from: classes.dex */
public class C5112a implements InterfaceC6806e<ByteBuffer> {

    /* renamed from: a */
    public final ByteBuffer f17590a;

    /* renamed from: k1.a$a */
    public static class a implements InterfaceC6806e.a<ByteBuffer> {
        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: a */
        public Class<ByteBuffer> mo19966a() {
            return ByteBuffer.class;
        }

        @Override // p252z0.InterfaceC6806e.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public InterfaceC6806e<ByteBuffer> mo19967b(ByteBuffer byteBuffer) {
            return new C5112a(byteBuffer);
        }
    }

    public C5112a(ByteBuffer byteBuffer) {
        this.f17590a = byteBuffer;
    }

    @Override // p252z0.InterfaceC6806e
    /* renamed from: b */
    public void mo19964b() {
    }

    @Override // p252z0.InterfaceC6806e
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ByteBuffer mo19963a() {
        this.f17590a.position(0);
        return this.f17590a;
    }
}
