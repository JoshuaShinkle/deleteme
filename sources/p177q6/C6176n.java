package p177q6;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6113j;

/* renamed from: q6.n */
/* loaded from: classes.dex */
public final class C6176n extends C6170h {

    /* renamed from: j */
    public static final a f20824j = new a(null);

    /* renamed from: h */
    public final Class<? super SSLSocketFactory> f20825h;

    /* renamed from: i */
    public final Class<?> f20826i;

    /* renamed from: q6.n$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public static /* synthetic */ InterfaceC6175m m23646b(a aVar, String str, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                str = "com.android.org.conscrypt";
            }
            return aVar.m23647a(str);
        }

        /* renamed from: a */
        public final InterfaceC6175m m23647a(String str) throws ClassNotFoundException {
            C0042f.m158e(str, "packageName");
            try {
                Class<?> cls = Class.forName(str + ".OpenSSLSocketImpl");
                C0042f.m156c(cls, "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocket>");
                Class<?> cls2 = Class.forName(str + ".OpenSSLSocketFactoryImpl");
                C0042f.m156c(cls2, "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocketFactory>");
                Class<?> cls3 = Class.forName(str + ".SSLParametersImpl");
                C0042f.m157d(cls3, "paramsClass");
                return new C6176n(cls, cls2, cls3);
            } catch (Exception e9) {
                C6113j.f20745a.m23447g().m23440j("unable to load android socket classes", 5, e9);
                return null;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C6176n(Class<? super SSLSocket> cls, Class<? super SSLSocketFactory> cls2, Class<?> cls3) {
        super(cls);
        C0042f.m158e(cls, "sslSocketClass");
        C0042f.m158e(cls2, "sslSocketFactoryClass");
        C0042f.m158e(cls3, "paramClass");
        this.f20825h = cls2;
        this.f20826i = cls3;
    }
}
