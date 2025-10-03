package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.C5231n;
import okhttp3.AbstractC5516q;
import okhttp3.C5482a;
import okhttp3.C5514o;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.CertificatePinner;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5489f;
import p007a6.C0042f;
import p098i6.C5057d;
import p118k6.C5200a;
import p139m6.C5328a;
import p139m6.C5329b;
import p139m6.C5334g;
import p139m6.C5337j;
import p168p6.C6113j;
import p203t5.C6313a;
import p203t5.C6319g;
import p204t6.C6321b;

/* renamed from: okhttp3.internal.connection.e */
/* loaded from: classes.dex */
public final class C5499e implements InterfaceC5488e {

    /* renamed from: b */
    public final C5522w f18708b;

    /* renamed from: c */
    public final C5523x f18709c;

    /* renamed from: d */
    public final boolean f18710d;

    /* renamed from: e */
    public final C5500f f18711e;

    /* renamed from: f */
    public final AbstractC5516q f18712f;

    /* renamed from: g */
    public final c f18713g;

    /* renamed from: h */
    public final AtomicBoolean f18714h;

    /* renamed from: i */
    public Object f18715i;

    /* renamed from: j */
    public C5498d f18716j;

    /* renamed from: k */
    public RealConnection f18717k;

    /* renamed from: l */
    public boolean f18718l;

    /* renamed from: m */
    public C5497c f18719m;

    /* renamed from: n */
    public boolean f18720n;

    /* renamed from: o */
    public boolean f18721o;

    /* renamed from: p */
    public boolean f18722p;

    /* renamed from: q */
    public volatile boolean f18723q;

    /* renamed from: r */
    public volatile C5497c f18724r;

    /* renamed from: s */
    public volatile RealConnection f18725s;

    /* renamed from: okhttp3.internal.connection.e$a */
    public final class a implements Runnable {

        /* renamed from: b */
        public final InterfaceC5489f f18726b;

        /* renamed from: c */
        public volatile AtomicInteger f18727c;

        /* renamed from: d */
        public final /* synthetic */ C5499e f18728d;

        public a(C5499e c5499e, InterfaceC5489f interfaceC5489f) {
            C0042f.m158e(interfaceC5489f, "responseCallback");
            this.f18728d = c5499e;
            this.f18726b = interfaceC5489f;
            this.f18727c = new AtomicInteger(0);
        }

        /* renamed from: a */
        public final void m21368a(ExecutorService executorService) {
            C0042f.m158e(executorService, "executorService");
            C5514o c5514oM21754o = this.f18728d.m21352k().m21754o();
            if (C5057d.f17450h && Thread.holdsLock(c5514oM21754o)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5514oM21754o);
            }
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e9) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e9);
                    this.f18728d.m21362u(interruptedIOException);
                    this.f18726b.mo7306d(this.f18728d, interruptedIOException);
                    this.f18728d.m21352k().m21754o().m21593f(this);
                }
            } catch (Throwable th) {
                this.f18728d.m21352k().m21754o().m21593f(this);
                throw th;
            }
        }

        /* renamed from: b */
        public final C5499e m21369b() {
            return this.f18728d;
        }

        /* renamed from: c */
        public final AtomicInteger m21370c() {
            return this.f18727c;
        }

        /* renamed from: d */
        public final String m21371d() {
            return this.f18728d.m21357p().m21811i().m21653h();
        }

        /* renamed from: e */
        public final void m21372e(a aVar) {
            C0042f.m158e(aVar, "other");
            this.f18727c = aVar.f18727c;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z8;
            Throwable th;
            IOException e9;
            C5514o c5514oM21754o;
            String str = "OkHttp " + this.f18728d.m21363v();
            C5499e c5499e = this.f18728d;
            Thread threadCurrentThread = Thread.currentThread();
            String name = threadCurrentThread.getName();
            threadCurrentThread.setName(str);
            try {
                c5499e.f18713g.m24175v();
                try {
                    try {
                        z8 = true;
                        try {
                            this.f18726b.mo7305c(c5499e, c5499e.m21358q());
                            c5514oM21754o = c5499e.m21352k().m21754o();
                        } catch (IOException e10) {
                            e9 = e10;
                            if (z8) {
                                C6113j.f20745a.m23447g().m23440j("Callback failure for " + c5499e.m21344B(), 4, e9);
                            } else {
                                this.f18726b.mo7306d(c5499e, e9);
                            }
                            c5514oM21754o = c5499e.m21352k().m21754o();
                            c5514oM21754o.m21593f(this);
                        } catch (Throwable th2) {
                            th = th2;
                            c5499e.cancel();
                            if (!z8) {
                                IOException iOException = new IOException("canceled due to " + th);
                                C6313a.m24147a(iOException, th);
                                this.f18726b.mo7306d(c5499e, iOException);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        c5499e.m21352k().m21754o().m21593f(this);
                        throw th3;
                    }
                } catch (IOException e11) {
                    z8 = false;
                    e9 = e11;
                } catch (Throwable th4) {
                    z8 = false;
                    th = th4;
                }
                c5514oM21754o.m21593f(this);
            } finally {
                threadCurrentThread.setName(name);
            }
        }
    }

    /* renamed from: okhttp3.internal.connection.e$b */
    public static final class b extends WeakReference<C5499e> {

        /* renamed from: a */
        public final Object f18729a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(C5499e c5499e, Object obj) {
            super(c5499e);
            C0042f.m158e(c5499e, "referent");
            this.f18729a = obj;
        }

        /* renamed from: a */
        public final Object m21373a() {
            return this.f18729a;
        }
    }

    /* renamed from: okhttp3.internal.connection.e$c */
    public static final class c extends C6321b {
        public c() {
        }

        @Override // p204t6.C6321b
        /* renamed from: B */
        public void mo21163B() throws IOException {
            C5499e.this.cancel();
        }
    }

    public C5499e(C5522w c5522w, C5523x c5523x, boolean z8) {
        C0042f.m158e(c5522w, "client");
        C0042f.m158e(c5523x, "originalRequest");
        this.f18708b = c5522w;
        this.f18709c = c5523x;
        this.f18710d = z8;
        this.f18711e = c5522w.m21751l().m21554a();
        this.f18712f = c5522w.m21756q().mo19760a(this);
        c cVar = new c();
        cVar.mo24245g(c5522w.m21747h(), TimeUnit.MILLISECONDS);
        this.f18713g = cVar;
        this.f18714h = new AtomicBoolean();
        this.f18722p = true;
    }

    /* renamed from: A */
    public final <E extends IOException> E m21343A(E e9) {
        if (this.f18718l || !this.f18713g.m24176w()) {
            return e9;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (e9 != null) {
            interruptedIOException.initCause(e9);
        }
        return interruptedIOException;
    }

    /* renamed from: B */
    public final String m21344B() {
        StringBuilder sb = new StringBuilder();
        sb.append(m21360s() ? "canceled " : "");
        sb.append(this.f18710d ? "web socket" : "call");
        sb.append(" to ");
        sb.append(m21363v());
        return sb.toString();
    }

    /* renamed from: c */
    public final void m21345c(RealConnection realConnection) {
        C0042f.m158e(realConnection, "connection");
        if (!C5057d.f17450h || Thread.holdsLock(realConnection)) {
            if (!(this.f18717k == null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.f18717k = realConnection;
            realConnection.m21289n().add(new b(this, this.f18715i));
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
    }

    @Override // okhttp3.InterfaceC5488e
    public void cancel() throws IOException {
        if (this.f18723q) {
            return;
        }
        this.f18723q = true;
        C5497c c5497c = this.f18724r;
        if (c5497c != null) {
            c5497c.m21311b();
        }
        RealConnection realConnection = this.f18725s;
        if (realConnection != null) {
            realConnection.m21279d();
        }
        this.f18712f.m21605f(this);
    }

    /* renamed from: d */
    public final <E extends IOException> E m21346d(E e9) throws IOException {
        Socket socketM21364w;
        boolean z8 = C5057d.f17450h;
        if (z8 && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        RealConnection realConnection = this.f18717k;
        if (realConnection != null) {
            if (z8 && Thread.holdsLock(realConnection)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + realConnection);
            }
            synchronized (realConnection) {
                socketM21364w = m21364w();
            }
            if (this.f18717k == null) {
                if (socketM21364w != null) {
                    C5057d.m19800n(socketM21364w);
                }
                this.f18712f.m21610k(this, realConnection);
            } else {
                if (!(socketM21364w == null)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
        E e10 = (E) m21343A(e9);
        if (e9 != null) {
            AbstractC5516q abstractC5516q = this.f18712f;
            C0042f.m155b(e10);
            abstractC5516q.m21603d(this, e10);
        } else {
            this.f18712f.m21602c(this);
        }
        return e10;
    }

    /* renamed from: e */
    public final void m21347e() {
        this.f18715i = C6113j.f20745a.m23447g().mo23411h("response.body().close()");
        this.f18712f.m21604e(this);
    }

    @Override // okhttp3.InterfaceC5488e
    public C5525z execute() {
        if (!this.f18714h.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        this.f18713g.m24175v();
        m21347e();
        try {
            this.f18708b.m21754o().m21589b(this);
            return m21358q();
        } finally {
            this.f18708b.m21754o().m21594g(this);
        }
    }

    @Override // okhttp3.InterfaceC5488e
    /* renamed from: f */
    public void mo21255f(InterfaceC5489f interfaceC5489f) {
        C0042f.m158e(interfaceC5489f, "responseCallback");
        if (!this.f18714h.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed".toString());
        }
        m21347e();
        this.f18708b.m21754o().m21588a(new a(this, interfaceC5489f));
    }

    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public C5499e clone() {
        return new C5499e(this.f18708b, this.f18709c, this.f18710d);
    }

    /* renamed from: h */
    public final C5482a m21349h(C5518s c5518s) {
        SSLSocketFactory sSLSocketFactoryM21741H;
        HostnameVerifier hostnameVerifierM21760u;
        CertificatePinner certificatePinnerM21749j;
        if (c5518s.m21654i()) {
            sSLSocketFactoryM21741H = this.f18708b.m21741H();
            hostnameVerifierM21760u = this.f18708b.m21760u();
            certificatePinnerM21749j = this.f18708b.m21749j();
        } else {
            sSLSocketFactoryM21741H = null;
            hostnameVerifierM21760u = null;
            certificatePinnerM21749j = null;
        }
        return new C5482a(c5518s.m21653h(), c5518s.m21657l(), this.f18708b.m21755p(), this.f18708b.m21740G(), sSLSocketFactoryM21741H, hostnameVerifierM21760u, certificatePinnerM21749j, this.f18708b.m21736C(), this.f18708b.m21735B(), this.f18708b.m21734A(), this.f18708b.m21752m(), this.f18708b.m21737D());
    }

    /* renamed from: i */
    public final void m21350i(C5523x c5523x, boolean z8) {
        C0042f.m158e(c5523x, "request");
        if (!(this.f18719m == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        synchronized (this) {
            if (!(!this.f18721o)) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
            }
            if (!(!this.f18720n)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            C6319g c6319g = C6319g.f21314a;
        }
        if (z8) {
            this.f18716j = new C5498d(this.f18711e, m21349h(c5523x.m21811i()), this, this.f18712f);
        }
    }

    /* renamed from: j */
    public final void m21351j(boolean z8) {
        C5497c c5497c;
        synchronized (this) {
            if (!this.f18722p) {
                throw new IllegalStateException("released".toString());
            }
            C6319g c6319g = C6319g.f21314a;
        }
        if (z8 && (c5497c = this.f18724r) != null) {
            c5497c.m21313d();
        }
        this.f18719m = null;
    }

    /* renamed from: k */
    public final C5522w m21352k() {
        return this.f18708b;
    }

    /* renamed from: l */
    public final RealConnection m21353l() {
        return this.f18717k;
    }

    /* renamed from: m */
    public final AbstractC5516q m21354m() {
        return this.f18712f;
    }

    /* renamed from: n */
    public final boolean m21355n() {
        return this.f18710d;
    }

    /* renamed from: o */
    public final C5497c m21356o() {
        return this.f18719m;
    }

    /* renamed from: p */
    public final C5523x m21357p() {
        return this.f18709c;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a1  */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C5525z m21358q() throws Throwable {
        ArrayList arrayList = new ArrayList();
        C5231n.m20410p(arrayList, this.f18708b.m21761v());
        arrayList.add(new C5337j(this.f18708b));
        arrayList.add(new C5328a(this.f18708b.m21753n()));
        this.f18708b.m21746g();
        arrayList.add(new C5200a(null));
        arrayList.add(C5495a.f18675a);
        if (!this.f18710d) {
            C5231n.m20410p(arrayList, this.f18708b.m21763x());
        }
        arrayList.add(new C5329b(this.f18710d));
        boolean z8 = false;
        try {
            C5525z c5525zMo20958a = new C5334g(this, arrayList, 0, null, this.f18709c, this.f18708b.m21750k(), this.f18708b.m21738E(), this.f18708b.m21743J()).mo20958a(this.f18709c);
            if (m21360s()) {
                C5057d.m19799m(c5525zMo20958a);
                throw new IOException("Canceled");
            }
            m21362u(null);
            return c5525zMo20958a;
        } catch (IOException e9) {
            try {
                IOException iOExceptionM21362u = m21362u(e9);
                C0042f.m156c(iOExceptionM21362u, "null cannot be cast to non-null type kotlin.Throwable");
                throw iOExceptionM21362u;
            } catch (Throwable th) {
                th = th;
                z8 = true;
                if (!z8) {
                    m21362u(null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            if (!z8) {
            }
            throw th;
        }
    }

    /* renamed from: r */
    public final C5497c m21359r(C5334g c5334g) throws IOException {
        C0042f.m158e(c5334g, "chain");
        synchronized (this) {
            if (!this.f18722p) {
                throw new IllegalStateException("released".toString());
            }
            if (!(!this.f18721o)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(!this.f18720n)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            C6319g c6319g = C6319g.f21314a;
        }
        C5498d c5498d = this.f18716j;
        C0042f.m155b(c5498d);
        C5497c c5497c = new C5497c(this, this.f18712f, c5498d, c5498d.m21333a(this.f18708b, c5334g));
        this.f18719m = c5497c;
        this.f18724r = c5497c;
        synchronized (this) {
            this.f18720n = true;
            this.f18721o = true;
        }
        if (this.f18723q) {
            throw new IOException("Canceled");
        }
        return c5497c;
    }

    /* renamed from: s */
    public boolean m21360s() {
        return this.f18723q;
    }

    /* renamed from: t */
    public final <E extends IOException> E m21361t(C5497c c5497c, boolean z8, boolean z9, E e9) {
        boolean z10;
        boolean z11;
        C0042f.m158e(c5497c, "exchange");
        if (!C0042f.m154a(c5497c, this.f18724r)) {
            return e9;
        }
        synchronized (this) {
            z10 = false;
            if (z8) {
                try {
                    if (!this.f18720n) {
                        if (z9 || !this.f18721o) {
                            z11 = false;
                        }
                    }
                    if (z8) {
                        this.f18720n = false;
                    }
                    if (z9) {
                        this.f18721o = false;
                    }
                    boolean z12 = this.f18720n;
                    boolean z13 = (z12 || this.f18721o) ? false : true;
                    if (!z12 && !this.f18721o && !this.f18722p) {
                        z10 = true;
                    }
                    z11 = z10;
                    z10 = z13;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z9) {
                }
                z11 = false;
            }
            C6319g c6319g = C6319g.f21314a;
        }
        if (z10) {
            this.f18724r = null;
            RealConnection realConnection = this.f18717k;
            if (realConnection != null) {
                realConnection.m21294s();
            }
        }
        return z11 ? (E) m21346d(e9) : e9;
    }

    /* renamed from: u */
    public final IOException m21362u(IOException iOException) {
        boolean z8;
        synchronized (this) {
            z8 = false;
            if (this.f18722p) {
                this.f18722p = false;
                if (!this.f18720n && !this.f18721o) {
                    z8 = true;
                }
            }
            C6319g c6319g = C6319g.f21314a;
        }
        return z8 ? m21346d(iOException) : iOException;
    }

    /* renamed from: v */
    public final String m21363v() {
        return this.f18709c.m21811i().m21659n();
    }

    /* renamed from: w */
    public final Socket m21364w() {
        RealConnection realConnection = this.f18717k;
        C0042f.m155b(realConnection);
        if (C5057d.f17450h && !Thread.holdsLock(realConnection)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
        }
        List<Reference<C5499e>> listM21289n = realConnection.m21289n();
        Iterator<Reference<C5499e>> it = listM21289n.iterator();
        int i9 = 0;
        while (true) {
            if (!it.hasNext()) {
                i9 = -1;
                break;
            }
            if (C0042f.m154a(it.next().get(), this)) {
                break;
            }
            i9++;
        }
        if (!(i9 != -1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        listM21289n.remove(i9);
        this.f18717k = null;
        if (listM21289n.isEmpty()) {
            realConnection.m21271B(System.nanoTime());
            if (this.f18711e.m21376c(realConnection)) {
                return realConnection.m21273D();
            }
        }
        return null;
    }

    /* renamed from: x */
    public final boolean m21365x() {
        C5498d c5498d = this.f18716j;
        C0042f.m155b(c5498d);
        return c5498d.m21337e();
    }

    /* renamed from: y */
    public final void m21366y(RealConnection realConnection) {
        this.f18725s = realConnection;
    }

    /* renamed from: z */
    public final void m21367z() {
        if (!(!this.f18718l)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f18718l = true;
        this.f18713g.m24176w();
    }
}
