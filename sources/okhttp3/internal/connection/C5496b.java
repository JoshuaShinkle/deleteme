package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.C5510k;
import p007a6.C0042f;

/* renamed from: okhttp3.internal.connection.b */
/* loaded from: classes.dex */
public final class C5496b {

    /* renamed from: a */
    public final List<C5510k> f18676a;

    /* renamed from: b */
    public int f18677b;

    /* renamed from: c */
    public boolean f18678c;

    /* renamed from: d */
    public boolean f18679d;

    public C5496b(List<C5510k> list) {
        C0042f.m158e(list, "connectionSpecs");
        this.f18676a = list;
    }

    /* renamed from: a */
    public final C5510k m21307a(SSLSocket sSLSocket) throws UnknownServiceException {
        C5510k c5510k;
        C0042f.m158e(sSLSocket, "sslSocket");
        int i9 = this.f18677b;
        int size = this.f18676a.size();
        while (true) {
            if (i9 >= size) {
                c5510k = null;
                break;
            }
            c5510k = this.f18676a.get(i9);
            if (c5510k.m21559e(sSLSocket)) {
                this.f18677b = i9 + 1;
                break;
            }
            i9++;
        }
        if (c5510k != null) {
            this.f18678c = m21309c(sSLSocket);
            c5510k.m21557c(sSLSocket, this.f18679d);
            return c5510k;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find acceptable protocols. isFallback=");
        sb.append(this.f18679d);
        sb.append(", modes=");
        sb.append(this.f18676a);
        sb.append(", supported protocols=");
        String[] enabledProtocols = sSLSocket.getEnabledProtocols();
        C0042f.m155b(enabledProtocols);
        String string = Arrays.toString(enabledProtocols);
        C0042f.m157d(string, "toString(this)");
        sb.append(string);
        throw new UnknownServiceException(sb.toString());
    }

    /* renamed from: b */
    public final boolean m21308b(IOException iOException) {
        C0042f.m158e(iOException, "e");
        this.f18679d = true;
        return (!this.f18678c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException) || ((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException) || !(iOException instanceof SSLException)) ? false : true;
    }

    /* renamed from: c */
    public final boolean m21309c(SSLSocket sSLSocket) {
        int size = this.f18676a.size();
        for (int i9 = this.f18677b; i9 < size; i9++) {
            if (this.f18676a.get(i9).m21559e(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
