package p073g1;

import android.util.Base64;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.e */
/* loaded from: classes.dex */
public final class C4811e<Model, Data> implements InterfaceC4820n<Model, Data> {

    /* renamed from: a */
    public final a<Data> f16737a;

    /* renamed from: g1.e$a */
    public interface a<Data> {
        /* renamed from: a */
        Class<Data> mo19090a();

        /* renamed from: b */
        void mo19091b(Data data);

        /* renamed from: c */
        Data mo19092c(String str);
    }

    /* renamed from: g1.e$b */
    public static final class b<Data> implements InterfaceC6805d<Data> {

        /* renamed from: b */
        public final String f16738b;

        /* renamed from: c */
        public final a<Data> f16739c;

        /* renamed from: d */
        public Data f16740d;

        public b(String str, a<Data> aVar) {
            this.f16738b = str;
            this.f16739c = aVar;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<Data> mo56a() {
            return this.f16739c.mo19090a();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
            try {
                this.f16739c.mo19091b(this.f16740d);
            } catch (IOException unused) {
            }
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return DataSource.LOCAL;
        }

        /* JADX WARN: Type inference failed for: r2v3, types: [Data, java.lang.Object] */
        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super Data> aVar) {
            try {
                Data dataMo19092c = this.f16739c.mo19092c(this.f16738b);
                this.f16740d = dataMo19092c;
                aVar.mo3903f(dataMo19092c);
            } catch (IllegalArgumentException e9) {
                aVar.mo3902c(e9);
            }
        }
    }

    /* renamed from: g1.e$c */
    public static final class c<Model> implements InterfaceC4821o<Model, InputStream> {

        /* renamed from: a */
        public final a<InputStream> f16741a = new a();

        /* renamed from: g1.e$c$a */
        public class a implements a<InputStream> {
            public a() {
            }

            @Override // p073g1.C4811e.a
            /* renamed from: a */
            public Class<InputStream> mo19090a() {
                return InputStream.class;
            }

            @Override // p073g1.C4811e.a
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void mo19091b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // p073g1.C4811e.a
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public InputStream mo19092c(String str) {
                if (!str.startsWith("data:image")) {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
                int iIndexOf = str.indexOf(44);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                }
                if (str.substring(0, iIndexOf).endsWith(";base64")) {
                    return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
                }
                throw new IllegalArgumentException("Not a base64 image data URL.");
            }
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Model, InputStream> mo3831a(C4824r c4824r) {
            return new C4811e(this.f16741a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4811e(a<Data> aVar) {
        this.f16737a = aVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: a */
    public boolean mo3826a(Model model) {
        return model.toString().startsWith("data:image");
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: b */
    public InterfaceC4820n.a<Data> mo3827b(Model model, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(model), new b(model.toString(), this.f16737a));
    }
}
