package p204t6;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import p007a6.C0042f;

/* renamed from: t6.v */
/* loaded from: classes.dex */
public final class C6341v extends C6321b {

    /* renamed from: o */
    public final Socket f21379o;

    public C6341v(Socket socket) {
        C0042f.m158e(socket, "socket");
        this.f21379o = socket;
    }

    @Override // p204t6.C6321b
    /* renamed from: B */
    public void mo21163B() throws IOException {
        try {
            this.f21379o.close();
        } catch (AssertionError e9) {
            if (!C6331l.m24257c(e9)) {
                throw e9;
            }
            C6332m.f21353a.log(Level.WARNING, "Failed to close timed out socket " + this.f21379o, (Throwable) e9);
        } catch (Exception e10) {
            C6332m.f21353a.log(Level.WARNING, "Failed to close timed out socket " + this.f21379o, (Throwable) e10);
        }
    }

    @Override // p204t6.C6321b
    /* renamed from: x */
    public IOException mo21165x(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }
}
