package p177q6;

import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import p007a6.C0042f;

/* renamed from: q6.l */
/* loaded from: classes.dex */
public final class C6174l implements InterfaceC6175m {

    /* renamed from: a */
    public final a f20822a;

    /* renamed from: b */
    public InterfaceC6175m f20823b;

    /* renamed from: q6.l$a */
    public interface a {
        /* renamed from: a */
        boolean mo23636a(SSLSocket sSLSocket);

        /* renamed from: b */
        InterfaceC6175m mo23637b(SSLSocket sSLSocket);
    }

    public C6174l(a aVar) {
        C0042f.m158e(aVar, "socketAdapterFactory");
        this.f20822a = aVar;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: a */
    public boolean mo23617a(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return this.f20822a.mo23636a(sSLSocket);
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: b */
    public boolean mo23618b() {
        return true;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: c */
    public String mo23619c(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        InterfaceC6175m interfaceC6175mM23645e = m23645e(sSLSocket);
        if (interfaceC6175mM23645e != null) {
            return interfaceC6175mM23645e.mo23619c(sSLSocket);
        }
        return null;
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: d */
    public void mo23620d(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        InterfaceC6175m interfaceC6175mM23645e = m23645e(sSLSocket);
        if (interfaceC6175mM23645e != null) {
            interfaceC6175mM23645e.mo23620d(sSLSocket, str, list);
        }
    }

    /* renamed from: e */
    public final synchronized InterfaceC6175m m23645e(SSLSocket sSLSocket) {
        if (this.f20823b == null && this.f20822a.mo23636a(sSLSocket)) {
            this.f20823b = this.f20822a.mo23637b(sSLSocket);
        }
        return this.f20823b;
    }
}
