package p157o6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import okhttp3.C5517r;
import okhttp3.internal.http2.C5505b;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6319g;
import p204t6.C6321b;
import p204t6.C6322c;
import p204t6.C6343x;
import p204t6.InterfaceC6324e;
import p204t6.InterfaceC6340u;
import p204t6.InterfaceC6342w;

/* renamed from: o6.d */
/* loaded from: classes.dex */
public final class C5472d {

    /* renamed from: o */
    public static final a f18413o = new a(null);

    /* renamed from: a */
    public final int f18414a;

    /* renamed from: b */
    public final C5505b f18415b;

    /* renamed from: c */
    public long f18416c;

    /* renamed from: d */
    public long f18417d;

    /* renamed from: e */
    public long f18418e;

    /* renamed from: f */
    public long f18419f;

    /* renamed from: g */
    public final ArrayDeque<C5517r> f18420g;

    /* renamed from: h */
    public boolean f18421h;

    /* renamed from: i */
    public final c f18422i;

    /* renamed from: j */
    public final b f18423j;

    /* renamed from: k */
    public final d f18424k;

    /* renamed from: l */
    public final d f18425l;

    /* renamed from: m */
    public ErrorCode f18426m;

    /* renamed from: n */
    public IOException f18427n;

    /* renamed from: o6.d$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: o6.d$b */
    public final class b implements InterfaceC6340u {

        /* renamed from: b */
        public boolean f18428b;

        /* renamed from: c */
        public final C6322c f18429c = new C6322c();

        /* renamed from: d */
        public C5517r f18430d;

        /* renamed from: e */
        public boolean f18431e;

        public b(boolean z8) {
            this.f18428b = z8;
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: a */
        public C6343x mo21081a() {
            return C5472d.this.m21146s();
        }

        @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            C5472d c5472d = C5472d.this;
            if (C5057d.f17450h && Thread.holdsLock(c5472d)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5472d);
            }
            C5472d c5472d2 = C5472d.this;
            synchronized (c5472d2) {
                if (this.f18431e) {
                    return;
                }
                boolean z8 = c5472d2.m21135h() == null;
                C6319g c6319g = C6319g.f21314a;
                if (!C5472d.this.m21142o().f18428b) {
                    boolean z9 = this.f18429c.size() > 0;
                    if (this.f18430d != null) {
                        while (this.f18429c.size() > 0) {
                            m21154f(false);
                        }
                        C5505b c5505bM21134g = C5472d.this.m21134g();
                        int iM21137j = C5472d.this.m21137j();
                        C5517r c5517r = this.f18430d;
                        C0042f.m155b(c5517r);
                        c5505bM21134g.m21473p0(iM21137j, z8, C5057d.m19775O(c5517r));
                    } else if (z9) {
                        while (this.f18429c.size() > 0) {
                            m21154f(true);
                        }
                    } else if (z8) {
                        C5472d.this.m21134g().m21472o0(C5472d.this.m21137j(), true, null, 0L);
                    }
                }
                synchronized (C5472d.this) {
                    this.f18431e = true;
                    C6319g c6319g2 = C6319g.f21314a;
                }
                C5472d.this.m21134g().flush();
                C5472d.this.m21129b();
            }
        }

        /* renamed from: f */
        public final void m21154f(boolean z8) throws IOException {
            long jMin;
            boolean z9;
            C5472d c5472d = C5472d.this;
            synchronized (c5472d) {
                c5472d.m21146s().m24175v();
                while (c5472d.m21145r() >= c5472d.m21144q() && !this.f18428b && !this.f18431e && c5472d.m21135h() == null) {
                    try {
                        c5472d.m21126D();
                    } finally {
                        c5472d.m21146s().m21164C();
                    }
                }
                c5472d.m21146s().m21164C();
                c5472d.m21130c();
                jMin = Math.min(c5472d.m21144q() - c5472d.m21145r(), this.f18429c.size());
                c5472d.m21124B(c5472d.m21145r() + jMin);
                z9 = z8 && jMin == this.f18429c.size();
                C6319g c6319g = C6319g.f21314a;
            }
            C5472d.this.m21146s().m24175v();
            try {
                C5472d.this.m21134g().m21472o0(C5472d.this.m21137j(), z9, this.f18429c, jMin);
            } finally {
                c5472d = C5472d.this;
            }
        }

        @Override // p204t6.InterfaceC6340u, java.io.Flushable
        public void flush() throws IOException {
            C5472d c5472d = C5472d.this;
            if (C5057d.f17450h && Thread.holdsLock(c5472d)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5472d);
            }
            C5472d c5472d2 = C5472d.this;
            synchronized (c5472d2) {
                c5472d2.m21130c();
                C6319g c6319g = C6319g.f21314a;
            }
            while (this.f18429c.size() > 0) {
                m21154f(false);
                C5472d.this.m21134g().flush();
            }
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: q */
        public void mo21082q(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "source");
            C5472d c5472d = C5472d.this;
            if (!C5057d.f17450h || !Thread.holdsLock(c5472d)) {
                this.f18429c.mo21082q(c6322c, j9);
                while (this.f18429c.size() >= PlaybackStateCompat.ACTION_PREPARE) {
                    m21154f(false);
                }
            } else {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5472d);
            }
        }

        /* renamed from: u */
        public final boolean m21155u() {
            return this.f18431e;
        }

        /* renamed from: v */
        public final boolean m21156v() {
            return this.f18428b;
        }
    }

    /* renamed from: o6.d$c */
    public final class c implements InterfaceC6342w {

        /* renamed from: b */
        public final long f18433b;

        /* renamed from: c */
        public boolean f18434c;

        /* renamed from: d */
        public final C6322c f18435d = new C6322c();

        /* renamed from: e */
        public final C6322c f18436e = new C6322c();

        /* renamed from: f */
        public C5517r f18437f;

        /* renamed from: g */
        public boolean f18438g;

        public c(long j9, boolean z8) {
            this.f18433b = j9;
            this.f18434c = z8;
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: a */
        public C6343x mo21076a() {
            return C5472d.this.m21140m();
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long size;
            C5472d c5472d = C5472d.this;
            synchronized (c5472d) {
                this.f18438g = true;
                size = this.f18436e.size();
                this.f18436e.m24231u();
                C0042f.m156c(c5472d, "null cannot be cast to non-null type java.lang.Object");
                c5472d.notifyAll();
                C6319g c6319g = C6319g.f21314a;
            }
            if (size > 0) {
                m21162y(size);
            }
            C5472d.this.m21129b();
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            IOException iOExceptionM21136i;
            long jMo21077d;
            boolean z8;
            C0042f.m158e(c6322c, "sink");
            long j10 = 0;
            if (!(j9 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
            }
            while (true) {
                C5472d c5472d = C5472d.this;
                synchronized (c5472d) {
                    c5472d.m21140m().m24175v();
                    try {
                        if (c5472d.m21135h() == null || this.f18434c) {
                            iOExceptionM21136i = null;
                        } else {
                            iOExceptionM21136i = c5472d.m21136i();
                            if (iOExceptionM21136i == null) {
                                ErrorCode errorCodeM21135h = c5472d.m21135h();
                                C0042f.m155b(errorCodeM21135h);
                                iOExceptionM21136i = new StreamResetException(errorCodeM21135h);
                            }
                        }
                        if (this.f18438g) {
                            throw new IOException("stream closed");
                        }
                        if (this.f18436e.size() > j10) {
                            C6322c c6322c2 = this.f18436e;
                            jMo21077d = c6322c2.mo21077d(c6322c, Math.min(j9, c6322c2.size()));
                            c5472d.m21123A(c5472d.m21139l() + jMo21077d);
                            long jM21139l = c5472d.m21139l() - c5472d.m21138k();
                            if (iOExceptionM21136i == null && jM21139l >= c5472d.m21134g().m21451S().m21179c() / 2) {
                                c5472d.m21134g().m21477t0(c5472d.m21137j(), jM21139l);
                                c5472d.m21153z(c5472d.m21139l());
                            }
                        } else if (this.f18434c || iOExceptionM21136i != null) {
                            jMo21077d = -1;
                        } else {
                            c5472d.m21126D();
                            jMo21077d = -1;
                            z8 = true;
                            c5472d.m21140m().m21164C();
                            C6319g c6319g = C6319g.f21314a;
                        }
                        z8 = false;
                        c5472d.m21140m().m21164C();
                        C6319g c6319g2 = C6319g.f21314a;
                    } finally {
                    }
                }
                if (!z8) {
                    if (jMo21077d != -1) {
                        return jMo21077d;
                    }
                    if (iOExceptionM21136i == null) {
                        return -1L;
                    }
                    throw iOExceptionM21136i;
                }
                j10 = 0;
            }
        }

        /* renamed from: f */
        public final boolean m21157f() {
            return this.f18438g;
        }

        /* renamed from: u */
        public final boolean m21158u() {
            return this.f18434c;
        }

        /* renamed from: v */
        public final void m21159v(InterfaceC6324e interfaceC6324e, long j9) throws EOFException {
            boolean z8;
            boolean z9;
            boolean z10;
            C0042f.m158e(interfaceC6324e, "source");
            C5472d c5472d = C5472d.this;
            if (C5057d.f17450h && Thread.holdsLock(c5472d)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5472d);
            }
            long j10 = j9;
            while (j10 > 0) {
                synchronized (C5472d.this) {
                    z8 = this.f18434c;
                    z9 = true;
                    z10 = this.f18436e.size() + j10 > this.f18433b;
                    C6319g c6319g = C6319g.f21314a;
                }
                if (z10) {
                    interfaceC6324e.skip(j10);
                    C5472d.this.m21133f(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z8) {
                    interfaceC6324e.skip(j10);
                    return;
                }
                long jMo21077d = interfaceC6324e.mo21077d(this.f18435d, j10);
                if (jMo21077d == -1) {
                    throw new EOFException();
                }
                j10 -= jMo21077d;
                C5472d c5472d2 = C5472d.this;
                synchronized (c5472d2) {
                    if (this.f18438g) {
                        this.f18435d.m24231u();
                    } else {
                        if (this.f18436e.size() != 0) {
                            z9 = false;
                        }
                        this.f18436e.mo24219h(this.f18435d);
                        if (z9) {
                            C0042f.m156c(c5472d2, "null cannot be cast to non-null type java.lang.Object");
                            c5472d2.notifyAll();
                        }
                    }
                }
            }
            m21162y(j9);
        }

        /* renamed from: w */
        public final void m21160w(boolean z8) {
            this.f18434c = z8;
        }

        /* renamed from: x */
        public final void m21161x(C5517r c5517r) {
            this.f18437f = c5517r;
        }

        /* renamed from: y */
        public final void m21162y(long j9) {
            C5472d c5472d = C5472d.this;
            if (!C5057d.f17450h || !Thread.holdsLock(c5472d)) {
                C5472d.this.m21134g().m21471n0(j9);
                return;
            }
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + c5472d);
        }
    }

    /* renamed from: o6.d$d */
    public final class d extends C6321b {
        public d() {
        }

        @Override // p204t6.C6321b
        /* renamed from: B */
        public void mo21163B() {
            C5472d.this.m21133f(ErrorCode.CANCEL);
            C5472d.this.m21134g().m21466h0();
        }

        /* renamed from: C */
        public final void m21164C() throws IOException {
            if (m24176w()) {
                throw mo21165x(null);
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

    public C5472d(int i9, C5505b c5505b, boolean z8, boolean z9, C5517r c5517r) {
        C0042f.m158e(c5505b, "connection");
        this.f18414a = i9;
        this.f18415b = c5505b;
        this.f18419f = c5505b.m21452T().m21179c();
        ArrayDeque<C5517r> arrayDeque = new ArrayDeque<>();
        this.f18420g = arrayDeque;
        this.f18422i = new c(c5505b.m21451S().m21179c(), z9);
        this.f18423j = new b(z8);
        this.f18424k = new d();
        this.f18425l = new d();
        if (c5517r == null) {
            if (!m21147t()) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!m21147t())) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(c5517r);
        }
    }

    /* renamed from: A */
    public final void m21123A(long j9) {
        this.f18416c = j9;
    }

    /* renamed from: B */
    public final void m21124B(long j9) {
        this.f18418e = j9;
    }

    /* renamed from: C */
    public final synchronized C5517r m21125C() {
        C5517r c5517rRemoveFirst;
        this.f18424k.m24175v();
        while (this.f18420g.isEmpty() && this.f18426m == null) {
            try {
                m21126D();
            } catch (Throwable th) {
                this.f18424k.m21164C();
                throw th;
            }
        }
        this.f18424k.m21164C();
        if (!(!this.f18420g.isEmpty())) {
            IOException iOException = this.f18427n;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.f18426m;
            C0042f.m155b(errorCode);
            throw new StreamResetException(errorCode);
        }
        c5517rRemoveFirst = this.f18420g.removeFirst();
        C0042f.m157d(c5517rRemoveFirst, "headersQueue.removeFirst()");
        return c5517rRemoveFirst;
    }

    /* renamed from: D */
    public final void m21126D() throws InterruptedException, InterruptedIOException {
        try {
            C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    /* renamed from: E */
    public final C6343x m21127E() {
        return this.f18425l;
    }

    /* renamed from: a */
    public final void m21128a(long j9) {
        this.f18419f += j9;
        if (j9 > 0) {
            C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
        }
    }

    /* renamed from: b */
    public final void m21129b() {
        boolean z8;
        boolean zM21148u;
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            z8 = !this.f18422i.m21158u() && this.f18422i.m21157f() && (this.f18423j.m21156v() || this.f18423j.m21155u());
            zM21148u = m21148u();
            C6319g c6319g = C6319g.f21314a;
        }
        if (z8) {
            m21131d(ErrorCode.CANCEL, null);
        } else {
            if (zM21148u) {
                return;
            }
            this.f18415b.m21465g0(this.f18414a);
        }
    }

    /* renamed from: c */
    public final void m21130c() throws IOException {
        if (this.f18423j.m21155u()) {
            throw new IOException("stream closed");
        }
        if (this.f18423j.m21156v()) {
            throw new IOException("stream finished");
        }
        if (this.f18426m != null) {
            IOException iOException = this.f18427n;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.f18426m;
            C0042f.m155b(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    /* renamed from: d */
    public final void m21131d(ErrorCode errorCode, IOException iOException) {
        C0042f.m158e(errorCode, "rstStatusCode");
        if (m21132e(errorCode, iOException)) {
            this.f18415b.m21475r0(this.f18414a, errorCode);
        }
    }

    /* renamed from: e */
    public final boolean m21132e(ErrorCode errorCode, IOException iOException) {
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            if (this.f18426m != null) {
                return false;
            }
            this.f18426m = errorCode;
            this.f18427n = iOException;
            C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
            if (this.f18422i.m21158u() && this.f18423j.m21156v()) {
                return false;
            }
            C6319g c6319g = C6319g.f21314a;
            this.f18415b.m21465g0(this.f18414a);
            return true;
        }
    }

    /* renamed from: f */
    public final void m21133f(ErrorCode errorCode) {
        C0042f.m158e(errorCode, "errorCode");
        if (m21132e(errorCode, null)) {
            this.f18415b.m21476s0(this.f18414a, errorCode);
        }
    }

    /* renamed from: g */
    public final C5505b m21134g() {
        return this.f18415b;
    }

    /* renamed from: h */
    public final synchronized ErrorCode m21135h() {
        return this.f18426m;
    }

    /* renamed from: i */
    public final IOException m21136i() {
        return this.f18427n;
    }

    /* renamed from: j */
    public final int m21137j() {
        return this.f18414a;
    }

    /* renamed from: k */
    public final long m21138k() {
        return this.f18417d;
    }

    /* renamed from: l */
    public final long m21139l() {
        return this.f18416c;
    }

    /* renamed from: m */
    public final d m21140m() {
        return this.f18424k;
    }

    /* renamed from: n */
    public final InterfaceC6340u m21141n() {
        synchronized (this) {
            if (!(this.f18421h || m21147t())) {
                throw new IllegalStateException("reply before requesting the sink".toString());
            }
            C6319g c6319g = C6319g.f21314a;
        }
        return this.f18423j;
    }

    /* renamed from: o */
    public final b m21142o() {
        return this.f18423j;
    }

    /* renamed from: p */
    public final c m21143p() {
        return this.f18422i;
    }

    /* renamed from: q */
    public final long m21144q() {
        return this.f18419f;
    }

    /* renamed from: r */
    public final long m21145r() {
        return this.f18418e;
    }

    /* renamed from: s */
    public final d m21146s() {
        return this.f18425l;
    }

    /* renamed from: t */
    public final boolean m21147t() {
        return this.f18415b.m21446N() == ((this.f18414a & 1) == 1);
    }

    /* renamed from: u */
    public final synchronized boolean m21148u() {
        if (this.f18426m != null) {
            return false;
        }
        if ((this.f18422i.m21158u() || this.f18422i.m21157f()) && (this.f18423j.m21156v() || this.f18423j.m21155u())) {
            if (this.f18421h) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: v */
    public final C6343x m21149v() {
        return this.f18424k;
    }

    /* renamed from: w */
    public final void m21150w(InterfaceC6324e interfaceC6324e, int i9) {
        C0042f.m158e(interfaceC6324e, "source");
        if (!C5057d.f17450h || !Thread.holdsLock(this)) {
            this.f18422i.m21159v(interfaceC6324e, i9);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    /* renamed from: x */
    public final void m21151x(C5517r c5517r, boolean z8) {
        boolean zM21148u;
        C0042f.m158e(c5517r, "headers");
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            if (this.f18421h && z8) {
                this.f18422i.m21161x(c5517r);
            } else {
                this.f18421h = true;
                this.f18420g.add(c5517r);
            }
            if (z8) {
                this.f18422i.m21160w(true);
            }
            zM21148u = m21148u();
            C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
            C6319g c6319g = C6319g.f21314a;
        }
        if (zM21148u) {
            return;
        }
        this.f18415b.m21465g0(this.f18414a);
    }

    /* renamed from: y */
    public final synchronized void m21152y(ErrorCode errorCode) {
        C0042f.m158e(errorCode, "errorCode");
        if (this.f18426m == null) {
            this.f18426m = errorCode;
            C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
        }
    }

    /* renamed from: z */
    public final void m21153z(long j9) {
        this.f18417d = j9;
    }
}
