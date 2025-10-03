package p177q6;

import android.annotation.SuppressLint;
import android.net.ssl.SSLSockets;
import android.os.Build;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6113j;

@SuppressLint({"NewApi"})
/* renamed from: q6.c */
/* loaded from: classes.dex */
public final class C6165c implements InterfaceC6175m {

    /* renamed from: a */
    public static final a f20798a = new a(null);

    /* renamed from: q6.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final InterfaceC6175m m23621a() {
            if (m23622b()) {
                return new C6165c();
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m23622b() {
            return C6113j.f20745a.m23448h() && Build.VERSION.SDK_INT >= 29;
        }
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: a */
    public boolean mo23617a(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return SSLSockets.isSupportedSocket(sSLSocket);
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: b */
    public boolean mo23618b() {
        return f20798a.m23622b();
    }

    @Override // p177q6.InterfaceC6175m
    @SuppressLint({"NewApi"})
    /* renamed from: c */
    public String mo23619c(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        String applicationProtocol = sSLSocket.getApplicationProtocol();
        if (applicationProtocol == null ? true : C0042f.m154a(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // p177q6.InterfaceC6175m
    @SuppressLint({"NewApi"})
    /* renamed from: d */
    public void mo23620d(SSLSocket sSLSocket, String str, List<? extends Protocol> list) throws IOException {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        try {
            SSLSockets.setUseSessionTickets(sSLSocket, true);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            sSLParameters.setApplicationProtocols((String[]) C6113j.f20745a.m23442b(list).toArray(new String[0]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalArgumentException e9) {
            throw new IOException("Android internal error", e9);
        }
    }
}
