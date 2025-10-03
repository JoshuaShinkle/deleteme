package p177q6;

import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6107d;
import p168p6.C6113j;
import p177q6.C6174l;

/* renamed from: q6.k */
/* loaded from: classes.dex */
public final class C6173k implements InterfaceC6175m {

    /* renamed from: a */
    public static final b f20820a = new b(null);

    /* renamed from: b */
    public static final C6174l.a f20821b = new a();

    /* renamed from: q6.k$a */
    public static final class a implements C6174l.a {
        @Override // p177q6.C6174l.a
        /* renamed from: a */
        public boolean mo23636a(SSLSocket sSLSocket) {
            C0042f.m158e(sSLSocket, "sslSocket");
            return C6107d.f20727e.m23425c() && Conscrypt.isConscrypt(sSLSocket);
        }

        @Override // p177q6.C6174l.a
        /* renamed from: b */
        public InterfaceC6175m mo23637b(SSLSocket sSLSocket) {
            C0042f.m158e(sSLSocket, "sslSocket");
            return new C6173k();
        }
    }

    /* renamed from: q6.k$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6174l.a m23644a() {
            return C6173k.f20821b;
        }
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: a */
    public boolean mo23617a(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: b */
    public boolean mo23618b() {
        return C6107d.f20727e.m23425c();
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: c */
    public String mo23619c(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        if (mo23617a(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: d */
    public void mo23620d(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (mo23617a(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
        }
    }
}
