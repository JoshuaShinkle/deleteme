package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import okhttp3.C5482a;
import okhttp3.C5485b0;
import okhttp3.internal.connection.C5499e;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p129l6.AbstractC5294a;
import p129l6.C5297d;
import p129l6.C5298e;
import p168p6.C6113j;
import p203t5.C6319g;

/* renamed from: okhttp3.internal.connection.f */
/* loaded from: classes.dex */
public final class C5500f {

    /* renamed from: f */
    public static final a f18731f = new a(null);

    /* renamed from: a */
    public final int f18732a;

    /* renamed from: b */
    public final long f18733b;

    /* renamed from: c */
    public final C5297d f18734c;

    /* renamed from: d */
    public final b f18735d;

    /* renamed from: e */
    public final ConcurrentLinkedQueue<RealConnection> f18736e;

    /* renamed from: okhttp3.internal.connection.f$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: okhttp3.internal.connection.f$b */
    public static final class b extends AbstractC5294a {
        public b(String str) {
            super(str, false, 2, null);
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() {
            return C5500f.this.m21375b(System.nanoTime());
        }
    }

    public C5500f(C5298e c5298e, int i9, long j9, TimeUnit timeUnit) {
        C0042f.m158e(c5298e, "taskRunner");
        C0042f.m158e(timeUnit, "timeUnit");
        this.f18732a = i9;
        this.f18733b = timeUnit.toNanos(j9);
        this.f18734c = c5298e.m20679i();
        this.f18735d = new b(C5057d.f17451i + " ConnectionPool");
        this.f18736e = new ConcurrentLinkedQueue<>();
        if (j9 > 0) {
            return;
        }
        throw new IllegalArgumentException(("keepAliveDuration <= 0: " + j9).toString());
    }

    /* renamed from: a */
    public final boolean m21374a(C5482a c5482a, C5499e c5499e, List<C5485b0> list, boolean z8) {
        C0042f.m158e(c5482a, "address");
        C0042f.m158e(c5499e, "call");
        Iterator<RealConnection> it = this.f18736e.iterator();
        while (it.hasNext()) {
            RealConnection next = it.next();
            C0042f.m157d(next, "connection");
            synchronized (next) {
                if (z8) {
                    if (next.m21297v()) {
                    }
                    C6319g c6319g = C6319g.f21314a;
                }
                if (next.m21295t(c5482a, list)) {
                    c5499e.m21345c(next);
                    return true;
                }
                C6319g c6319g2 = C6319g.f21314a;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final long m21375b(long j9) throws IOException {
        Iterator<RealConnection> it = this.f18736e.iterator();
        int i9 = 0;
        long j10 = Long.MIN_VALUE;
        RealConnection realConnection = null;
        int i10 = 0;
        while (it.hasNext()) {
            RealConnection next = it.next();
            C0042f.m157d(next, "connection");
            synchronized (next) {
                if (m21377d(next, j9) > 0) {
                    i10++;
                } else {
                    i9++;
                    long jM21290o = j9 - next.m21290o();
                    if (jM21290o > j10) {
                        realConnection = next;
                        j10 = jM21290o;
                    }
                    C6319g c6319g = C6319g.f21314a;
                }
            }
        }
        long j11 = this.f18733b;
        if (j10 < j11 && i9 <= this.f18732a) {
            if (i9 > 0) {
                return j11 - j10;
            }
            if (i10 > 0) {
                return j11;
            }
            return -1L;
        }
        C0042f.m155b(realConnection);
        synchronized (realConnection) {
            if (!realConnection.m21289n().isEmpty()) {
                return 0L;
            }
            if (realConnection.m21290o() + j10 != j9) {
                return 0L;
            }
            realConnection.m21272C(true);
            this.f18736e.remove(realConnection);
            C5057d.m19800n(realConnection.m21273D());
            if (this.f18736e.isEmpty()) {
                this.f18734c.m20658a();
            }
            return 0L;
        }
    }

    /* renamed from: c */
    public final boolean m21376c(RealConnection realConnection) {
        C0042f.m158e(realConnection, "connection");
        if (C5057d.f17450h && !Thread.holdsLock(realConnection)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
        }
        if (!realConnection.m21291p() && this.f18732a != 0) {
            C5297d.m20657j(this.f18734c, this.f18735d, 0L, 2, null);
            return false;
        }
        realConnection.m21272C(true);
        this.f18736e.remove(realConnection);
        if (this.f18736e.isEmpty()) {
            this.f18734c.m20658a();
        }
        return true;
    }

    /* renamed from: d */
    public final int m21377d(RealConnection realConnection, long j9) {
        if (C5057d.f17450h && !Thread.holdsLock(realConnection)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
        }
        List<Reference<C5499e>> listM21289n = realConnection.m21289n();
        int i9 = 0;
        while (i9 < listM21289n.size()) {
            Reference<C5499e> reference = listM21289n.get(i9);
            if (reference.get() != null) {
                i9++;
            } else {
                C0042f.m156c(reference, "null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference");
                C6113j.f20745a.m23447g().mo23412l("A connection to " + realConnection.m21301z().m21235a().m21228l() + " was leaked. Did you forget to close a response body?", ((C5499e.b) reference).m21373a());
                listM21289n.remove(i9);
                realConnection.m21272C(true);
                if (listM21289n.isEmpty()) {
                    realConnection.m21271B(j9 - this.f18733b);
                    return 0;
                }
            }
        }
        return listM21289n.size();
    }

    /* renamed from: e */
    public final void m21378e(RealConnection realConnection) {
        C0042f.m158e(realConnection, "connection");
        if (!C5057d.f17450h || Thread.holdsLock(realConnection)) {
            this.f18736e.add(realConnection);
            C5297d.m20657j(this.f18734c, this.f18735d, 0L, 2, null);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
    }
}
