package okhttp3.internal.connection;

import com.google.android.gms.appinvite.PreviewActivity;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.C5227j;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__IndentKt;
import okhttp3.AbstractC5516q;
import okhttp3.C5482a;
import okhttp3.C5485b0;
import okhttp3.C5510k;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.CertificatePinner;
import okhttp3.Handshake;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5492i;
import okhttp3.Protocol;
import okhttp3.internal.http2.C5505b;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p129l6.C5298e;
import p139m6.C5334g;
import p139m6.InterfaceC5331d;
import p148n6.C5374b;
import p157o6.C5471c;
import p157o6.C5472d;
import p157o6.C5475g;
import p168p6.C6113j;
import p195s6.AbstractC6269c;
import p195s6.C6270d;
import p204t6.C6331l;
import p204t6.C6343x;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6324e;
import p257z5.InterfaceC6831a;

/* loaded from: classes.dex */
public final class RealConnection extends C5505b.c implements InterfaceC5492i {

    /* renamed from: t */
    public static final C5493a f18656t = new C5493a(null);

    /* renamed from: c */
    public final C5500f f18657c;

    /* renamed from: d */
    public final C5485b0 f18658d;

    /* renamed from: e */
    public Socket f18659e;

    /* renamed from: f */
    public Socket f18660f;

    /* renamed from: g */
    public Handshake f18661g;

    /* renamed from: h */
    public Protocol f18662h;

    /* renamed from: i */
    public C5505b f18663i;

    /* renamed from: j */
    public InterfaceC6324e f18664j;

    /* renamed from: k */
    public InterfaceC6323d f18665k;

    /* renamed from: l */
    public boolean f18666l;

    /* renamed from: m */
    public boolean f18667m;

    /* renamed from: n */
    public int f18668n;

    /* renamed from: o */
    public int f18669o;

    /* renamed from: p */
    public int f18670p;

    /* renamed from: q */
    public int f18671q;

    /* renamed from: r */
    public final List<Reference<C5499e>> f18672r;

    /* renamed from: s */
    public long f18673s;

    /* renamed from: okhttp3.internal.connection.RealConnection$a */
    public static final class C5493a {
        public C5493a() {
        }

        public /* synthetic */ C5493a(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: okhttp3.internal.connection.RealConnection$b */
    public /* synthetic */ class C5494b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f18674a;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Proxy.Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f18674a = iArr;
        }
    }

    public RealConnection(C5500f c5500f, C5485b0 c5485b0) {
        C0042f.m158e(c5500f, "connectionPool");
        C0042f.m158e(c5485b0, "route");
        this.f18657c = c5500f;
        this.f18658d = c5485b0;
        this.f18671q = 1;
        this.f18672r = new ArrayList();
        this.f18673s = Long.MAX_VALUE;
    }

    /* renamed from: A */
    public final boolean m21270A(List<C5485b0> list) {
        List<C5485b0> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        for (C5485b0 c5485b0 : list2) {
            if (c5485b0.m21236b().type() == Proxy.Type.DIRECT && this.f18658d.m21236b().type() == Proxy.Type.DIRECT && C0042f.m154a(this.f18658d.m21238d(), c5485b0.m21238d())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: B */
    public final void m21271B(long j9) {
        this.f18673s = j9;
    }

    /* renamed from: C */
    public final void m21272C(boolean z8) {
        this.f18666l = z8;
    }

    /* renamed from: D */
    public Socket m21273D() {
        Socket socket = this.f18660f;
        C0042f.m155b(socket);
        return socket;
    }

    /* renamed from: E */
    public final void m21274E(int i9) throws SocketException {
        Socket socket = this.f18660f;
        C0042f.m155b(socket);
        InterfaceC6324e interfaceC6324e = this.f18664j;
        C0042f.m155b(interfaceC6324e);
        InterfaceC6323d interfaceC6323d = this.f18665k;
        C0042f.m155b(interfaceC6323d);
        socket.setSoTimeout(0);
        C5505b c5505bM21478a = new C5505b.a(true, C5298e.f17983i).m21494q(socket, this.f18658d.m21235a().m21228l().m21653h(), interfaceC6324e, interfaceC6323d).m21488k(this).m21489l(i9).m21478a();
        this.f18663i = c5505bM21478a;
        this.f18671q = C5505b.f18787D.m21495a().m21180d();
        C5505b.m21437m0(c5505bM21478a, false, null, 3, null);
    }

    /* renamed from: F */
    public final boolean m21275F(C5518s c5518s) {
        Handshake handshake;
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        C5518s c5518sM21228l = this.f18658d.m21235a().m21228l();
        if (c5518s.m21657l() != c5518sM21228l.m21657l()) {
            return false;
        }
        if (C0042f.m154a(c5518s.m21653h(), c5518sM21228l.m21653h())) {
            return true;
        }
        if (this.f18667m || (handshake = this.f18661g) == null) {
            return false;
        }
        C0042f.m155b(handshake);
        return m21280e(c5518s, handshake);
    }

    /* renamed from: G */
    public final synchronized void m21276G(C5499e c5499e, IOException iOException) {
        C0042f.m158e(c5499e, "call");
        if (iOException instanceof StreamResetException) {
            if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                int i9 = this.f18670p + 1;
                this.f18670p = i9;
                if (i9 > 1) {
                    this.f18666l = true;
                    this.f18668n++;
                }
            } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !c5499e.m21360s()) {
                this.f18666l = true;
                this.f18668n++;
            }
        } else if (!m21297v() || (iOException instanceof ConnectionShutdownException)) {
            this.f18666l = true;
            if (this.f18669o == 0) {
                if (iOException != null) {
                    m21282g(c5499e.m21352k(), this.f18658d, iOException);
                }
                this.f18668n++;
            }
        }
    }

    @Override // okhttp3.internal.http2.C5505b.c
    /* renamed from: a */
    public synchronized void mo21277a(C5505b c5505b, C5475g c5475g) {
        C0042f.m158e(c5505b, "connection");
        C0042f.m158e(c5475g, "settings");
        this.f18671q = c5475g.m21180d();
    }

    @Override // okhttp3.internal.http2.C5505b.c
    /* renamed from: b */
    public void mo21278b(C5472d c5472d) {
        C0042f.m158e(c5472d, "stream");
        c5472d.m21131d(ErrorCode.REFUSED_STREAM, null);
    }

    /* renamed from: d */
    public final void m21279d() throws IOException {
        Socket socket = this.f18659e;
        if (socket != null) {
            C5057d.m19800n(socket);
        }
    }

    /* renamed from: e */
    public final boolean m21280e(C5518s c5518s, Handshake handshake) {
        List<Certificate> listM21205d = handshake.m21205d();
        if (!listM21205d.isEmpty()) {
            C6270d c6270d = C6270d.f21152a;
            String strM21653h = c5518s.m21653h();
            Certificate certificate = listM21205d.get(0);
            C0042f.m156c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            if (c6270d.m24016e(strM21653h, (X509Certificate) certificate)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014c A[SYNTHETIC] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m21281f(int i9, int i10, int i11, int i12, boolean z8, InterfaceC5488e interfaceC5488e, AbstractC5516q abstractC5516q) throws Throwable {
        Socket socket;
        Socket socket2;
        C0042f.m158e(interfaceC5488e, "call");
        C0042f.m158e(abstractC5516q, "eventListener");
        if (!(this.f18662h == null)) {
            throw new IllegalStateException("already connected".toString());
        }
        List<C5510k> listM21218b = this.f18658d.m21235a().m21218b();
        C5496b c5496b = new C5496b(listM21218b);
        if (this.f18658d.m21235a().m21227k() == null) {
            if (!listM21218b.contains(C5510k.f18899k)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strM21653h = this.f18658d.m21235a().m21228l().m21653h();
            if (!C6113j.f20745a.m23447g().mo23405i(strM21653h)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + strM21653h + " not permitted by network security policy"));
            }
        } else if (this.f18658d.m21235a().m21222f().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        do {
            try {
                try {
                    if (this.f18658d.m21237c()) {
                        m21285j(i9, i10, i11, interfaceC5488e, abstractC5516q);
                        if (this.f18659e == null) {
                            if (!this.f18658d.m21237c() && this.f18659e == null) {
                                throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                            }
                            this.f18673s = System.nanoTime();
                            return;
                        }
                    } else {
                        try {
                            m21283h(i9, i10, interfaceC5488e, abstractC5516q);
                        } catch (IOException e9) {
                            e = e9;
                            socket = this.f18660f;
                            if (socket != null) {
                            }
                            socket2 = this.f18659e;
                            if (socket2 != null) {
                            }
                            this.f18660f = null;
                            this.f18659e = null;
                            this.f18664j = null;
                            this.f18665k = null;
                            this.f18661g = null;
                            this.f18662h = null;
                            this.f18663i = null;
                            this.f18671q = 1;
                            abstractC5516q.m21607h(interfaceC5488e, this.f18658d.m21238d(), this.f18658d.m21236b(), null, e);
                            if (routeException != null) {
                            }
                            if (z8) {
                            }
                        }
                    }
                    m21288m(c5496b, i12, interfaceC5488e, abstractC5516q);
                    abstractC5516q.m21606g(interfaceC5488e, this.f18658d.m21238d(), this.f18658d.m21236b(), this.f18662h);
                    if (!this.f18658d.m21237c()) {
                    }
                    this.f18673s = System.nanoTime();
                    return;
                } catch (IOException e10) {
                    e = e10;
                    socket = this.f18660f;
                    if (socket != null) {
                        C5057d.m19800n(socket);
                    }
                    socket2 = this.f18659e;
                    if (socket2 != null) {
                        C5057d.m19800n(socket2);
                    }
                    this.f18660f = null;
                    this.f18659e = null;
                    this.f18664j = null;
                    this.f18665k = null;
                    this.f18661g = null;
                    this.f18662h = null;
                    this.f18663i = null;
                    this.f18671q = 1;
                    abstractC5516q.m21607h(interfaceC5488e, this.f18658d.m21238d(), this.f18658d.m21236b(), null, e);
                    if (routeException != null) {
                        routeException = new RouteException(e);
                    } else {
                        routeException.m21304a(e);
                    }
                    if (z8) {
                        throw routeException;
                    }
                }
            } catch (IOException e11) {
                e = e11;
            }
        } while (c5496b.m21308b(e));
        throw routeException;
    }

    /* renamed from: g */
    public final void m21282g(C5522w c5522w, C5485b0 c5485b0, IOException iOException) {
        C0042f.m158e(c5522w, "client");
        C0042f.m158e(c5485b0, "failedRoute");
        C0042f.m158e(iOException, "failure");
        if (c5485b0.m21236b().type() != Proxy.Type.DIRECT) {
            C5482a c5482aM21235a = c5485b0.m21235a();
            c5482aM21235a.m21225i().connectFailed(c5482aM21235a.m21228l().m21662q(), c5485b0.m21236b().address(), iOException);
        }
        c5522w.m21759t().m21380b(c5485b0);
    }

    /* renamed from: h */
    public final void m21283h(int i9, int i10, InterfaceC5488e interfaceC5488e, AbstractC5516q abstractC5516q) throws IOException {
        Socket socketCreateSocket;
        Proxy proxyM21236b = this.f18658d.m21236b();
        C5482a c5482aM21235a = this.f18658d.m21235a();
        Proxy.Type type = proxyM21236b.type();
        int i11 = type == null ? -1 : C5494b.f18674a[type.ordinal()];
        if (i11 == 1 || i11 == 2) {
            socketCreateSocket = c5482aM21235a.m21226j().createSocket();
            C0042f.m155b(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(proxyM21236b);
        }
        this.f18659e = socketCreateSocket;
        abstractC5516q.m21608i(interfaceC5488e, this.f18658d.m21238d(), proxyM21236b);
        socketCreateSocket.setSoTimeout(i10);
        try {
            C6113j.f20745a.m23447g().mo23410f(socketCreateSocket, this.f18658d.m21238d(), i9);
            try {
                this.f18664j = C6331l.m24256b(C6331l.m24261g(socketCreateSocket));
                this.f18665k = C6331l.m24255a(C6331l.m24258d(socketCreateSocket));
            } catch (NullPointerException e9) {
                if (C0042f.m154a(e9.getMessage(), "throw with null exception")) {
                    throw new IOException(e9);
                }
            }
        } catch (ConnectException e10) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f18658d.m21238d());
            connectException.initCause(e10);
            throw connectException;
        }
    }

    /* renamed from: i */
    public final void m21284i(C5496b c5496b) throws Throwable {
        final C5482a c5482aM21235a = this.f18658d.m21235a();
        SSLSocketFactory sSLSocketFactoryM21227k = c5482aM21235a.m21227k();
        SSLSocket sSLSocket = null;
        try {
            C0042f.m155b(sSLSocketFactoryM21227k);
            Socket socketCreateSocket = sSLSocketFactoryM21227k.createSocket(this.f18659e, c5482aM21235a.m21228l().m21653h(), c5482aM21235a.m21228l().m21657l(), true);
            C0042f.m156c(socketCreateSocket, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                C5510k c5510kM21307a = c5496b.m21307a(sSLSocket2);
                if (c5510kM21307a.m21562h()) {
                    C6113j.f20745a.m23447g().mo23403e(sSLSocket2, c5482aM21235a.m21228l().m21653h(), c5482aM21235a.m21222f());
                }
                sSLSocket2.startHandshake();
                SSLSession session = sSLSocket2.getSession();
                Handshake.Companion companion = Handshake.f18465e;
                C0042f.m157d(session, "sslSocketSession");
                final Handshake handshakeM21207a = companion.m21207a(session);
                HostnameVerifier hostnameVerifierM21221e = c5482aM21235a.m21221e();
                C0042f.m155b(hostnameVerifierM21221e);
                if (hostnameVerifierM21221e.verify(c5482aM21235a.m21228l().m21653h(), session)) {
                    final CertificatePinner certificatePinnerM21217a = c5482aM21235a.m21217a();
                    C0042f.m155b(certificatePinnerM21217a);
                    this.f18661g = new Handshake(handshakeM21207a.m21206e(), handshakeM21207a.m21202a(), handshakeM21207a.m21204c(), new InterfaceC6831a<List<? extends Certificate>>() { // from class: okhttp3.internal.connection.RealConnection$connectTls$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // p257z5.InterfaceC6831a
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public final List<Certificate> mo21200a() {
                            AbstractC6269c abstractC6269cM21190d = certificatePinnerM21217a.m21190d();
                            C0042f.m155b(abstractC6269cM21190d);
                            return abstractC6269cM21190d.mo23623a(handshakeM21207a.m21205d(), c5482aM21235a.m21228l().m21653h());
                        }
                    });
                    certificatePinnerM21217a.m21188b(c5482aM21235a.m21228l().m21653h(), new InterfaceC6831a<List<? extends X509Certificate>>() { // from class: okhttp3.internal.connection.RealConnection$connectTls$2
                        {
                            super(0);
                        }

                        @Override // p257z5.InterfaceC6831a
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public final List<X509Certificate> mo21200a() {
                            Handshake handshake = this.this$0.f18661g;
                            C0042f.m155b(handshake);
                            List<Certificate> listM21205d = handshake.m21205d();
                            ArrayList arrayList = new ArrayList(C5227j.m20408n(listM21205d, 10));
                            for (Certificate certificate : listM21205d) {
                                C0042f.m156c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                                arrayList.add((X509Certificate) certificate);
                            }
                            return arrayList;
                        }
                    });
                    String strMo23404g = c5510kM21307a.m21562h() ? C6113j.f20745a.m23447g().mo23404g(sSLSocket2) : null;
                    this.f18660f = sSLSocket2;
                    this.f18664j = C6331l.m24256b(C6331l.m24261g(sSLSocket2));
                    this.f18665k = C6331l.m24255a(C6331l.m24258d(sSLSocket2));
                    this.f18662h = strMo23404g != null ? Protocol.f18470b.m21213a(strMo23404g) : Protocol.HTTP_1_1;
                    C6113j.f20745a.m23447g().mo23426b(sSLSocket2);
                    return;
                }
                List<Certificate> listM21205d = handshakeM21207a.m21205d();
                if (!(!listM21205d.isEmpty())) {
                    throw new SSLPeerUnverifiedException("Hostname " + c5482aM21235a.m21228l().m21653h() + " not verified (no certificates)");
                }
                Certificate certificate = listM21205d.get(0);
                C0042f.m156c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                X509Certificate x509Certificate = (X509Certificate) certificate;
                throw new SSLPeerUnverifiedException(StringsKt__IndentKt.m20446e("\n              |Hostname " + c5482aM21235a.m21228l().m21653h() + " not verified:\n              |    certificate: " + CertificatePinner.f18457c.m21194a(x509Certificate) + "\n              |    DN: " + x509Certificate.getSubjectDN().getName() + "\n              |    subjectAltNames: " + C6270d.f21152a.m24012a(x509Certificate) + "\n              ", null, 1, null));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    C6113j.f20745a.m23447g().mo23426b(sSLSocket);
                }
                if (sSLSocket != null) {
                    C5057d.m19800n(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: j */
    public final void m21285j(int i9, int i10, int i11, InterfaceC5488e interfaceC5488e, AbstractC5516q abstractC5516q) throws IOException {
        C5523x c5523xM21287l = m21287l();
        C5518s c5518sM21811i = c5523xM21287l.m21811i();
        for (int i12 = 0; i12 < 21; i12++) {
            m21283h(i9, i10, interfaceC5488e, abstractC5516q);
            c5523xM21287l = m21286k(i10, i11, c5523xM21287l, c5518sM21811i);
            if (c5523xM21287l == null) {
                return;
            }
            Socket socket = this.f18659e;
            if (socket != null) {
                C5057d.m19800n(socket);
            }
            this.f18659e = null;
            this.f18665k = null;
            this.f18664j = null;
            abstractC5516q.m21606g(interfaceC5488e, this.f18658d.m21238d(), this.f18658d.m21236b(), null);
        }
    }

    /* renamed from: k */
    public final C5523x m21286k(int i9, int i10, C5523x c5523x, C5518s c5518s) throws IOException {
        String str = "CONNECT " + C5057d.m19777Q(c5518s, true) + " HTTP/1.1";
        while (true) {
            InterfaceC6324e interfaceC6324e = this.f18664j;
            C0042f.m155b(interfaceC6324e);
            InterfaceC6323d interfaceC6323d = this.f18665k;
            C0042f.m155b(interfaceC6323d);
            C5374b c5374b = new C5374b(null, this, interfaceC6324e, interfaceC6323d);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            interfaceC6324e.mo21076a().mo24245g(i9, timeUnit);
            interfaceC6323d.mo21081a().mo24245g(i10, timeUnit);
            c5374b.m21066A(c5523x.m21807e(), str);
            c5374b.mo20937a();
            C5525z.a aVarMo20940d = c5374b.mo20940d(false);
            C0042f.m155b(aVarMo20940d);
            C5525z c5525zM21858c = aVarMo20940d.m21873r(c5523x).m21858c();
            c5374b.m21075z(c5525zM21858c);
            int iM21853x = c5525zM21858c.m21853x();
            if (iM21853x == 200) {
                if (interfaceC6324e.mo24215b().mo24218g() && interfaceC6323d.mo24215b().mo24218g()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iM21853x != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + c5525zM21858c.m21853x());
            }
            C5523x c5523xMo19952a = this.f18658d.m21235a().m21224h().mo19952a(this.f18658d, c5525zM21858c);
            if (c5523xMo19952a == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if (C5255l.m20513l(PreviewActivity.ON_CLICK_LISTENER_CLOSE, C5525z.m21837B(c5525zM21858c, HttpHeaders.CONNECTION, null, 2, null), true)) {
                return c5523xMo19952a;
            }
            c5523x = c5523xMo19952a;
        }
    }

    /* renamed from: l */
    public final C5523x m21287l() {
        C5523x c5523xM21813b = new C5523x.a().m21821j(this.f18658d.m21235a().m21228l()).m21817f("CONNECT", null).m21815d(HttpHeaders.HOST, C5057d.m19777Q(this.f18658d.m21235a().m21228l(), true)).m21815d("Proxy-Connection", "Keep-Alive").m21815d(HttpHeaders.USER_AGENT, "okhttp/4.12.0").m21813b();
        C5523x c5523xMo19952a = this.f18658d.m21235a().m21224h().mo19952a(this.f18658d, new C5525z.a().m21873r(c5523xM21813b).m21871p(Protocol.HTTP_1_1).m21862g(407).m21868m("Preemptive Authenticate").m21857b(C5057d.f17445c).m21874s(-1L).m21872q(-1L).m21865j(HttpHeaders.PROXY_AUTHENTICATE, "OkHttp-Preemptive").m21858c());
        return c5523xMo19952a == null ? c5523xM21813b : c5523xMo19952a;
    }

    /* renamed from: m */
    public final void m21288m(C5496b c5496b, int i9, InterfaceC5488e interfaceC5488e, AbstractC5516q abstractC5516q) throws Throwable {
        if (this.f18658d.m21235a().m21227k() != null) {
            abstractC5516q.m21599B(interfaceC5488e);
            m21284i(c5496b);
            abstractC5516q.m21598A(interfaceC5488e, this.f18661g);
            if (this.f18662h == Protocol.HTTP_2) {
                m21274E(i9);
                return;
            }
            return;
        }
        List<Protocol> listM21222f = this.f18658d.m21235a().m21222f();
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        if (!listM21222f.contains(protocol)) {
            this.f18660f = this.f18659e;
            this.f18662h = Protocol.HTTP_1_1;
        } else {
            this.f18660f = this.f18659e;
            this.f18662h = protocol;
            m21274E(i9);
        }
    }

    /* renamed from: n */
    public final List<Reference<C5499e>> m21289n() {
        return this.f18672r;
    }

    /* renamed from: o */
    public final long m21290o() {
        return this.f18673s;
    }

    /* renamed from: p */
    public final boolean m21291p() {
        return this.f18666l;
    }

    /* renamed from: q */
    public final int m21292q() {
        return this.f18668n;
    }

    /* renamed from: r */
    public Handshake m21293r() {
        return this.f18661g;
    }

    /* renamed from: s */
    public final synchronized void m21294s() {
        this.f18669o++;
    }

    /* renamed from: t */
    public final boolean m21295t(C5482a c5482a, List<C5485b0> list) {
        C0042f.m158e(c5482a, "address");
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        if (this.f18672r.size() >= this.f18671q || this.f18666l || !this.f18658d.m21235a().m21220d(c5482a)) {
            return false;
        }
        if (C0042f.m154a(c5482a.m21228l().m21653h(), m21301z().m21235a().m21228l().m21653h())) {
            return true;
        }
        if (this.f18663i == null || list == null || !m21270A(list) || c5482a.m21221e() != C6270d.f21152a || !m21275F(c5482a.m21228l())) {
            return false;
        }
        try {
            CertificatePinner certificatePinnerM21217a = c5482a.m21217a();
            C0042f.m155b(certificatePinnerM21217a);
            String strM21653h = c5482a.m21228l().m21653h();
            Handshake handshakeM21293r = m21293r();
            C0042f.m155b(handshakeM21293r);
            certificatePinnerM21217a.m21187a(strM21653h, handshakeM21293r.m21205d());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public String toString() {
        Object objM21202a;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f18658d.m21235a().m21228l().m21653h());
        sb.append(':');
        sb.append(this.f18658d.m21235a().m21228l().m21657l());
        sb.append(", proxy=");
        sb.append(this.f18658d.m21236b());
        sb.append(" hostAddress=");
        sb.append(this.f18658d.m21238d());
        sb.append(" cipherSuite=");
        Handshake handshake = this.f18661g;
        if (handshake == null || (objM21202a = handshake.m21202a()) == null) {
            objM21202a = "none";
        }
        sb.append(objM21202a);
        sb.append(" protocol=");
        sb.append(this.f18662h);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: u */
    public final boolean m21296u(boolean z8) {
        long j9;
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        long jNanoTime = System.nanoTime();
        Socket socket = this.f18659e;
        C0042f.m155b(socket);
        Socket socket2 = this.f18660f;
        C0042f.m155b(socket2);
        InterfaceC6324e interfaceC6324e = this.f18664j;
        C0042f.m155b(interfaceC6324e);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        C5505b c5505b = this.f18663i;
        if (c5505b != null) {
            return c5505b.m21457Y(jNanoTime);
        }
        synchronized (this) {
            j9 = jNanoTime - this.f18673s;
        }
        if (j9 < 10000000000L || !z8) {
            return true;
        }
        return C5057d.m19766F(socket2, interfaceC6324e);
    }

    /* renamed from: v */
    public final boolean m21297v() {
        return this.f18663i != null;
    }

    /* renamed from: w */
    public final InterfaceC5331d m21298w(C5522w c5522w, C5334g c5334g) throws SocketException {
        C0042f.m158e(c5522w, "client");
        C0042f.m158e(c5334g, "chain");
        Socket socket = this.f18660f;
        C0042f.m155b(socket);
        InterfaceC6324e interfaceC6324e = this.f18664j;
        C0042f.m155b(interfaceC6324e);
        InterfaceC6323d interfaceC6323d = this.f18665k;
        C0042f.m155b(interfaceC6323d);
        C5505b c5505b = this.f18663i;
        if (c5505b != null) {
            return new C5471c(c5522w, this, c5334g, c5505b);
        }
        socket.setSoTimeout(c5334g.m20967k());
        C6343x c6343xMo21076a = interfaceC6324e.mo21076a();
        long jM20964h = c5334g.m20964h();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        c6343xMo21076a.mo24245g(jM20964h, timeUnit);
        interfaceC6323d.mo21081a().mo24245g(c5334g.m20966j(), timeUnit);
        return new C5374b(c5522w, this, interfaceC6324e, interfaceC6323d);
    }

    /* renamed from: x */
    public final synchronized void m21299x() {
        this.f18667m = true;
    }

    /* renamed from: y */
    public final synchronized void m21300y() {
        this.f18666l = true;
    }

    /* renamed from: z */
    public C5485b0 m21301z() {
        return this.f18658d;
    }
}
