package p195s6;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6113j;

/* renamed from: s6.c */
/* loaded from: classes.dex */
public abstract class AbstractC6269c {

    /* renamed from: a */
    public static final a f21151a = new a(null);

    /* renamed from: s6.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final AbstractC6269c m24011a(X509TrustManager x509TrustManager) {
            C0042f.m158e(x509TrustManager, "trustManager");
            return C6113j.f20745a.m23447g().mo23402c(x509TrustManager);
        }
    }

    /* renamed from: a */
    public abstract List<Certificate> mo23623a(List<? extends Certificate> list, String str);
}
