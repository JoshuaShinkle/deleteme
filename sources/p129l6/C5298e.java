package p129l6;

import com.google.android.exoplayer2.C3322C;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6319g;

/* renamed from: l6.e */
/* loaded from: classes.dex */
public final class C5298e {

    /* renamed from: h */
    public static final b f17982h = new b(null);

    /* renamed from: i */
    public static final C5298e f17983i = new C5298e(new c(C5057d.m19773M(C5057d.f17451i + " TaskRunner", true)));

    /* renamed from: j */
    public static final Logger f17984j;

    /* renamed from: a */
    public final a f17985a;

    /* renamed from: b */
    public int f17986b;

    /* renamed from: c */
    public boolean f17987c;

    /* renamed from: d */
    public long f17988d;

    /* renamed from: e */
    public final List<C5297d> f17989e;

    /* renamed from: f */
    public final List<C5297d> f17990f;

    /* renamed from: g */
    public final Runnable f17991g;

    /* renamed from: l6.e$a */
    public interface a {
        /* renamed from: a */
        void mo20681a(C5298e c5298e, long j9);

        /* renamed from: b */
        void mo20682b(C5298e c5298e);

        void execute(Runnable runnable);

        long nanoTime();
    }

    /* renamed from: l6.e$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final Logger m20683a() {
            return C5298e.f17984j;
        }
    }

    /* renamed from: l6.e$c */
    public static final class c implements a {

        /* renamed from: a */
        public final ThreadPoolExecutor f17992a;

        public c(ThreadFactory threadFactory) {
            C0042f.m158e(threadFactory, "threadFactory");
            this.f17992a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
        }

        @Override // p129l6.C5298e.a
        /* renamed from: a */
        public void mo20681a(C5298e c5298e, long j9) throws InterruptedException {
            C0042f.m158e(c5298e, "taskRunner");
            long j10 = j9 / C3322C.MICROS_PER_SECOND;
            long j11 = j9 - (C3322C.MICROS_PER_SECOND * j10);
            if (j10 > 0 || j9 > 0) {
                c5298e.wait(j10, (int) j11);
            }
        }

        @Override // p129l6.C5298e.a
        /* renamed from: b */
        public void mo20682b(C5298e c5298e) {
            C0042f.m158e(c5298e, "taskRunner");
            c5298e.notify();
        }

        @Override // p129l6.C5298e.a
        public void execute(Runnable runnable) {
            C0042f.m158e(runnable, "runnable");
            this.f17992a.execute(runnable);
        }

        @Override // p129l6.C5298e.a
        public long nanoTime() {
            return System.nanoTime();
        }
    }

    /* renamed from: l6.e$d */
    public static final class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC5294a abstractC5294aM20674d;
            long jNanoTime;
            while (true) {
                C5298e c5298e = C5298e.this;
                synchronized (c5298e) {
                    abstractC5294aM20674d = c5298e.m20674d();
                }
                if (abstractC5294aM20674d == null) {
                    return;
                }
                C5297d c5297dM20650d = abstractC5294aM20674d.m20650d();
                C0042f.m155b(c5297dM20650d);
                C5298e c5298e2 = C5298e.this;
                boolean zIsLoggable = C5298e.f17982h.m20683a().isLoggable(Level.FINE);
                if (zIsLoggable) {
                    jNanoTime = c5297dM20650d.m20665h().m20677g().nanoTime();
                    C5295b.m20656c(abstractC5294aM20674d, c5297dM20650d, "starting");
                } else {
                    jNanoTime = -1;
                }
                try {
                    try {
                        c5298e2.m20680j(abstractC5294aM20674d);
                        C6319g c6319g = C6319g.f21314a;
                        if (zIsLoggable) {
                            C5295b.m20656c(abstractC5294aM20674d, c5297dM20650d, "finished run in " + C5295b.m20655b(c5297dM20650d.m20665h().m20677g().nanoTime() - jNanoTime));
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    if (zIsLoggable) {
                        C5295b.m20656c(abstractC5294aM20674d, c5297dM20650d, "failed a run in " + C5295b.m20655b(c5297dM20650d.m20665h().m20677g().nanoTime() - jNanoTime));
                    }
                    throw th;
                }
            }
        }
    }

    static {
        Logger logger = Logger.getLogger(C5298e.class.getName());
        C0042f.m157d(logger, "getLogger(TaskRunner::class.java.name)");
        f17984j = logger;
    }

    public C5298e(a aVar) {
        C0042f.m158e(aVar, "backend");
        this.f17985a = aVar;
        this.f17986b = 10000;
        this.f17989e = new ArrayList();
        this.f17990f = new ArrayList();
        this.f17991g = new d();
    }

    /* renamed from: c */
    public final void m20673c(AbstractC5294a abstractC5294a, long j9) {
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        C5297d c5297dM20650d = abstractC5294a.m20650d();
        C0042f.m155b(c5297dM20650d);
        if (!(c5297dM20650d.m20660c() == abstractC5294a)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean zM20661d = c5297dM20650d.m20661d();
        c5297dM20650d.m20669m(false);
        c5297dM20650d.m20668l(null);
        this.f17989e.remove(c5297dM20650d);
        if (j9 != -1 && !zM20661d && !c5297dM20650d.m20664g()) {
            c5297dM20650d.m20667k(abstractC5294a, j9, true);
        }
        if (!c5297dM20650d.m20662e().isEmpty()) {
            this.f17990f.add(c5297dM20650d);
        }
    }

    /* renamed from: d */
    public final AbstractC5294a m20674d() {
        boolean z8;
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        while (!this.f17990f.isEmpty()) {
            long jNanoTime = this.f17985a.nanoTime();
            Iterator<C5297d> it = this.f17990f.iterator();
            long jMin = Long.MAX_VALUE;
            AbstractC5294a abstractC5294a = null;
            while (true) {
                if (!it.hasNext()) {
                    z8 = false;
                    break;
                }
                AbstractC5294a abstractC5294a2 = it.next().m20662e().get(0);
                long jMax = Math.max(0L, abstractC5294a2.m20649c() - jNanoTime);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (abstractC5294a != null) {
                        z8 = true;
                        break;
                    }
                    abstractC5294a = abstractC5294a2;
                }
            }
            if (abstractC5294a != null) {
                m20675e(abstractC5294a);
                if (z8 || (!this.f17987c && (!this.f17990f.isEmpty()))) {
                    this.f17985a.execute(this.f17991g);
                }
                return abstractC5294a;
            }
            if (this.f17987c) {
                if (jMin < this.f17988d - jNanoTime) {
                    this.f17985a.mo20682b(this);
                }
                return null;
            }
            this.f17987c = true;
            this.f17988d = jNanoTime + jMin;
            try {
                try {
                    this.f17985a.mo20681a(this, jMin);
                } catch (InterruptedException unused) {
                    m20676f();
                }
            } finally {
                this.f17987c = false;
            }
        }
        return null;
    }

    /* renamed from: e */
    public final void m20675e(AbstractC5294a abstractC5294a) {
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        abstractC5294a.m20653g(-1L);
        C5297d c5297dM20650d = abstractC5294a.m20650d();
        C0042f.m155b(c5297dM20650d);
        c5297dM20650d.m20662e().remove(abstractC5294a);
        this.f17990f.remove(c5297dM20650d);
        c5297dM20650d.m20668l(abstractC5294a);
        this.f17989e.add(c5297dM20650d);
    }

    /* renamed from: f */
    public final void m20676f() {
        int size = this.f17989e.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            } else {
                this.f17989e.get(size).m20659b();
            }
        }
        for (int size2 = this.f17990f.size() - 1; -1 < size2; size2--) {
            C5297d c5297d = this.f17990f.get(size2);
            c5297d.m20659b();
            if (c5297d.m20662e().isEmpty()) {
                this.f17990f.remove(size2);
            }
        }
    }

    /* renamed from: g */
    public final a m20677g() {
        return this.f17985a;
    }

    /* renamed from: h */
    public final void m20678h(C5297d c5297d) {
        C0042f.m158e(c5297d, "taskQueue");
        if (C5057d.f17450h && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        if (c5297d.m20660c() == null) {
            if (!c5297d.m20662e().isEmpty()) {
                C5057d.m19789c(this.f17990f, c5297d);
            } else {
                this.f17990f.remove(c5297d);
            }
        }
        if (this.f17987c) {
            this.f17985a.mo20682b(this);
        } else {
            this.f17985a.execute(this.f17991g);
        }
    }

    /* renamed from: i */
    public final C5297d m20679i() {
        int i9;
        synchronized (this) {
            i9 = this.f17986b;
            this.f17986b = i9 + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('Q');
        sb.append(i9);
        return new C5297d(this, sb.toString());
    }

    /* renamed from: j */
    public final void m20680j(AbstractC5294a abstractC5294a) {
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(abstractC5294a.m20648b());
        try {
            long jMo20652f = abstractC5294a.mo20652f();
            synchronized (this) {
                m20673c(abstractC5294a, jMo20652f);
                C6319g c6319g = C6319g.f21314a;
            }
            threadCurrentThread.setName(name);
        } catch (Throwable th) {
            synchronized (this) {
                m20673c(abstractC5294a, -1L);
                C6319g c6319g2 = C6319g.f21314a;
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }
}
