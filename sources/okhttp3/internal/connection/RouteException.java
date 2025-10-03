package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import p007a6.C0042f;
import p203t5.C6313a;

/* loaded from: classes.dex */
public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteException(IOException iOException) {
        super(iOException);
        C0042f.m158e(iOException, "firstConnectException");
        this.firstConnectException = iOException;
        this.lastConnectException = iOException;
    }

    /* renamed from: a */
    public final void m21304a(IOException iOException) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(iOException, "e");
        C6313a.m24147a(this.firstConnectException, iOException);
        this.lastConnectException = iOException;
    }

    /* renamed from: b */
    public final IOException m21305b() {
        return this.firstConnectException;
    }

    /* renamed from: c */
    public final IOException m21306c() {
        return this.lastConnectException;
    }
}
