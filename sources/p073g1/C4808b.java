package p073g1;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.b */
/* loaded from: classes.dex */
public class C4808b<Data> implements InterfaceC4820n<byte[], Data> {

    /* renamed from: a */
    public final b<Data> f16731a;

    /* renamed from: g1.b$a */
    public static class a implements InterfaceC4821o<byte[], ByteBuffer> {

        /* renamed from: g1.b$a$a, reason: collision with other inner class name */
        public class C6869a implements b<ByteBuffer> {
            public C6869a() {
            }

            @Override // p073g1.C4808b.b
            /* renamed from: a */
            public Class<ByteBuffer> mo19082a() {
                return ByteBuffer.class;
            }

            @Override // p073g1.C4808b.b
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public ByteBuffer mo19083b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<byte[], ByteBuffer> mo3831a(C4824r c4824r) {
            return new C4808b(new C6869a());
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.b$b */
    public interface b<Data> {
        /* renamed from: a */
        Class<Data> mo19082a();

        /* renamed from: b */
        Data mo19083b(byte[] bArr);
    }

    /* renamed from: g1.b$c */
    public static class c<Data> implements InterfaceC6805d<Data> {

        /* renamed from: b */
        public final byte[] f16733b;

        /* renamed from: c */
        public final b<Data> f16734c;

        public c(byte[] bArr, b<Data> bVar) {
            this.f16733b = bArr;
            this.f16734c = bVar;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<Data> mo56a() {
            return this.f16734c.mo19082a();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return DataSource.LOCAL;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super Data> aVar) {
            aVar.mo3903f(this.f16734c.mo19083b(this.f16733b));
        }
    }

    /* renamed from: g1.b$d */
    public static class d implements InterfaceC4821o<byte[], InputStream> {

        /* renamed from: g1.b$d$a */
        public class a implements b<InputStream> {
            public a() {
            }

            @Override // p073g1.C4808b.b
            /* renamed from: a */
            public Class<InputStream> mo19082a() {
                return InputStream.class;
            }

            @Override // p073g1.C4808b.b
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public InputStream mo19083b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<byte[], InputStream> mo3831a(C4824r c4824r) {
            return new C4808b(new a());
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4808b(b<Data> bVar) {
        this.f16731a = bVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(byte[] bArr, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(bArr), new c(bArr, this.f16731a));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(byte[] bArr) {
        return true;
    }
}
