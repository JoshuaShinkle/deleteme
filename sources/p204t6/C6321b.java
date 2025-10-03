package p204t6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import p007a6.C0040d;
import p007a6.C0042f;
import p203t5.C6319g;

/* renamed from: t6.b */
/* loaded from: classes.dex */
public class C6321b extends C6343x {

    /* renamed from: i */
    public static final a f21317i = new a(null);

    /* renamed from: j */
    public static final ReentrantLock f21318j;

    /* renamed from: k */
    public static final Condition f21319k;

    /* renamed from: l */
    public static final long f21320l;

    /* renamed from: m */
    public static final long f21321m;

    /* renamed from: n */
    public static C6321b f21322n;

    /* renamed from: f */
    public boolean f21323f;

    /* renamed from: g */
    public C6321b f21324g;

    /* renamed from: h */
    public long f21325h;

    /* renamed from: t6.b$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: c */
        public final C6321b m24181c() throws InterruptedException {
            C6321b c6321b = C6321b.f21322n;
            C0042f.m155b(c6321b);
            C6321b c6321b2 = c6321b.f21324g;
            if (c6321b2 == null) {
                long jNanoTime = System.nanoTime();
                m24183e().await(C6321b.f21320l, TimeUnit.MILLISECONDS);
                C6321b c6321b3 = C6321b.f21322n;
                C0042f.m155b(c6321b3);
                if (c6321b3.f21324g != null || System.nanoTime() - jNanoTime < C6321b.f21321m) {
                    return null;
                }
                return C6321b.f21322n;
            }
            long jM24177y = c6321b2.m24177y(System.nanoTime());
            if (jM24177y > 0) {
                m24183e().await(jM24177y, TimeUnit.NANOSECONDS);
                return null;
            }
            C6321b c6321b4 = C6321b.f21322n;
            C0042f.m155b(c6321b4);
            c6321b4.f21324g = c6321b2.f21324g;
            c6321b2.f21324g = null;
            return c6321b2;
        }

        /* renamed from: d */
        public final boolean m24182d(C6321b c6321b) {
            ReentrantLock reentrantLockM24184f = C6321b.f21317i.m24184f();
            reentrantLockM24184f.lock();
            try {
                if (!c6321b.f21323f) {
                    return false;
                }
                c6321b.f21323f = false;
                for (C6321b c6321b2 = C6321b.f21322n; c6321b2 != null; c6321b2 = c6321b2.f21324g) {
                    if (c6321b2.f21324g == c6321b) {
                        c6321b2.f21324g = c6321b.f21324g;
                        c6321b.f21324g = null;
                        return false;
                    }
                }
                reentrantLockM24184f.unlock();
                return true;
            } finally {
                reentrantLockM24184f.unlock();
            }
        }

        /* renamed from: e */
        public final Condition m24183e() {
            return C6321b.f21319k;
        }

        /* renamed from: f */
        public final ReentrantLock m24184f() {
            return C6321b.f21318j;
        }

        /* renamed from: g */
        public final void m24185g(C6321b c6321b, long j9, boolean z8) {
            ReentrantLock reentrantLockM24184f = C6321b.f21317i.m24184f();
            reentrantLockM24184f.lock();
            try {
                if (!(!c6321b.f21323f)) {
                    throw new IllegalStateException("Unbalanced enter/exit".toString());
                }
                c6321b.f21323f = true;
                if (C6321b.f21322n == null) {
                    C6321b.f21322n = new C6321b();
                    new b().start();
                }
                long jNanoTime = System.nanoTime();
                if (j9 != 0 && z8) {
                    c6321b.f21325h = Math.min(j9, c6321b.mo24241c() - jNanoTime) + jNanoTime;
                } else if (j9 != 0) {
                    c6321b.f21325h = j9 + jNanoTime;
                } else {
                    if (!z8) {
                        throw new AssertionError();
                    }
                    c6321b.f21325h = c6321b.mo24241c();
                }
                long jM24177y = c6321b.m24177y(jNanoTime);
                C6321b c6321b2 = C6321b.f21322n;
                C0042f.m155b(c6321b2);
                while (c6321b2.f21324g != null) {
                    C6321b c6321b3 = c6321b2.f21324g;
                    C0042f.m155b(c6321b3);
                    if (jM24177y < c6321b3.m24177y(jNanoTime)) {
                        break;
                    }
                    c6321b2 = c6321b2.f21324g;
                    C0042f.m155b(c6321b2);
                }
                c6321b.f21324g = c6321b2.f21324g;
                c6321b2.f21324g = c6321b;
                if (c6321b2 == C6321b.f21322n) {
                    C6321b.f21317i.m24183e().signal();
                }
                C6319g c6319g = C6319g.f21314a;
            } finally {
                reentrantLockM24184f.unlock();
            }
        }
    }

    /* renamed from: t6.b$b */
    public static final class b extends Thread {
        public b() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ReentrantLock reentrantLockM24184f;
            C6321b c6321bM24181c;
            while (true) {
                try {
                    a aVar = C6321b.f21317i;
                    reentrantLockM24184f = aVar.m24184f();
                    reentrantLockM24184f.lock();
                    try {
                        c6321bM24181c = aVar.m24181c();
                    } finally {
                        reentrantLockM24184f.unlock();
                    }
                } catch (InterruptedException unused) {
                    continue;
                }
                if (c6321bM24181c == C6321b.f21322n) {
                    C6321b.f21322n = null;
                    return;
                }
                C6319g c6319g = C6319g.f21314a;
                reentrantLockM24184f.unlock();
                if (c6321bM24181c != null) {
                    c6321bM24181c.mo21163B();
                }
            }
        }
    }

    /* renamed from: t6.b$c */
    public static final class c implements InterfaceC6340u {

        /* renamed from: c */
        public final /* synthetic */ InterfaceC6340u f21327c;

        public c(InterfaceC6340u interfaceC6340u) {
            this.f21327c = interfaceC6340u;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            C6321b c6321b = C6321b.this;
            InterfaceC6340u interfaceC6340u = this.f21327c;
            c6321b.m24175v();
            try {
                interfaceC6340u.close();
                C6319g c6319g = C6319g.f21314a;
                if (c6321b.m24176w()) {
                    throw c6321b.m24174p(null);
                }
            } catch (IOException e9) {
                if (!c6321b.m24176w()) {
                    throw e9;
                }
                throw c6321b.m24174p(e9);
            } finally {
                c6321b.m24176w();
            }
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public C6321b mo21081a() {
            return C6321b.this;
        }

        @Override // p204t6.InterfaceC6340u, java.io.Flushable
        public void flush() throws IOException {
            C6321b c6321b = C6321b.this;
            InterfaceC6340u interfaceC6340u = this.f21327c;
            c6321b.m24175v();
            try {
                interfaceC6340u.flush();
                C6319g c6319g = C6319g.f21314a;
                if (c6321b.m24176w()) {
                    throw c6321b.m24174p(null);
                }
            } catch (IOException e9) {
                if (!c6321b.m24176w()) {
                    throw e9;
                }
                throw c6321b.m24174p(e9);
            } finally {
                c6321b.m24176w();
            }
        }

        @Override // p204t6.InterfaceC6340u
        /* renamed from: q */
        public void mo21082q(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "source");
            C6320a.m24153b(c6322c.size(), 0L, j9);
            while (true) {
                long j10 = 0;
                if (j9 <= 0) {
                    return;
                }
                C6338s c6338s = c6322c.f21330b;
                C0042f.m155b(c6338s);
                while (true) {
                    if (j10 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        break;
                    }
                    j10 += c6338s.f21369c - c6338s.f21368b;
                    if (j10 >= j9) {
                        j10 = j9;
                        break;
                    } else {
                        c6338s = c6338s.f21372f;
                        C0042f.m155b(c6338s);
                    }
                }
                C6321b c6321b = C6321b.this;
                InterfaceC6340u interfaceC6340u = this.f21327c;
                c6321b.m24175v();
                try {
                    interfaceC6340u.mo21082q(c6322c, j10);
                    C6319g c6319g = C6319g.f21314a;
                    if (c6321b.m24176w()) {
                        throw c6321b.m24174p(null);
                    }
                    j9 -= j10;
                } catch (IOException e9) {
                    if (!c6321b.m24176w()) {
                        throw e9;
                    }
                    throw c6321b.m24174p(e9);
                } finally {
                    c6321b.m24176w();
                }
            }
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f21327c + ')';
        }
    }

    /* renamed from: t6.b$d */
    public static final class d implements InterfaceC6342w {

        /* renamed from: c */
        public final /* synthetic */ InterfaceC6342w f21329c;

        public d(InterfaceC6342w interfaceC6342w) {
            this.f21329c = interfaceC6342w;
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            C6321b c6321b = C6321b.this;
            InterfaceC6342w interfaceC6342w = this.f21329c;
            c6321b.m24175v();
            try {
                interfaceC6342w.close();
                C6319g c6319g = C6319g.f21314a;
                if (c6321b.m24176w()) {
                    throw c6321b.m24174p(null);
                }
            } catch (IOException e9) {
                if (!c6321b.m24176w()) {
                    throw e9;
                }
                throw c6321b.m24174p(e9);
            } finally {
                c6321b.m24176w();
            }
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            C6321b c6321b = C6321b.this;
            InterfaceC6342w interfaceC6342w = this.f21329c;
            c6321b.m24175v();
            try {
                long jMo21077d = interfaceC6342w.mo21077d(c6322c, j9);
                if (c6321b.m24176w()) {
                    throw c6321b.m24174p(null);
                }
                return jMo21077d;
            } catch (IOException e9) {
                if (c6321b.m24176w()) {
                    throw c6321b.m24174p(e9);
                }
                throw e9;
            } finally {
                c6321b.m24176w();
            }
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public C6321b mo21076a() {
            return C6321b.this;
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f21329c + ')';
        }
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f21318j = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        C0042f.m157d(conditionNewCondition, "newCondition(...)");
        f21319k = conditionNewCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f21320l = millis;
        f21321m = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* renamed from: A */
    public final InterfaceC6342w m24173A(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "source");
        return new d(interfaceC6342w);
    }

    /* renamed from: B */
    public void mo21163B() {
    }

    /* renamed from: p */
    public final IOException m24174p(IOException iOException) {
        return mo21165x(iOException);
    }

    /* renamed from: v */
    public final void m24175v() {
        long jM24295h = m24295h();
        boolean zMo24243e = mo24243e();
        if (jM24295h != 0 || zMo24243e) {
            f21317i.m24185g(this, jM24295h, zMo24243e);
        }
    }

    /* renamed from: w */
    public final boolean m24176w() {
        return f21317i.m24182d(this);
    }

    /* renamed from: x */
    public IOException mo21165x(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* renamed from: y */
    public final long m24177y(long j9) {
        return this.f21325h - j9;
    }

    /* renamed from: z */
    public final InterfaceC6340u m24178z(InterfaceC6340u interfaceC6340u) {
        C0042f.m158e(interfaceC6340u, "sink");
        return new c(interfaceC6340u);
    }
}
