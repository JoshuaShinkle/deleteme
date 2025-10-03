package p168p6;

import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import kotlin.text.C5254k;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: p6.h */
/* loaded from: classes.dex */
public class C6111h extends C6113j {

    /* renamed from: d */
    public static final a f20740d = new a(0 == true ? 1 : 0);

    /* renamed from: e */
    public static final boolean f20741e;

    /* renamed from: p6.h$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6111h m23433a() {
            if (m23434b()) {
                return new C6111h();
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23434b() {
            return C6111h.f20741e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        String property = System.getProperty("java.specification.version");
        Integer numM20507f = property != null ? C5254k.m20507f(property) : null;
        boolean z8 = true;
        if (numM20507f == null) {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
            } catch (NoSuchMethodException unused) {
            }
        } else if (numM20507f.intValue() < 9) {
            z8 = false;
        }
        f20741e = z8;
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        sSLParameters.setApplicationProtocols((String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
        sSLSocket.setSSLParameters(sSLParameters);
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        try {
            String applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null ? true : C0042f.m154a(applicationProtocol, "")) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
