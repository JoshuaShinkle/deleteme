package p129l6;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6319g;

/* renamed from: l6.d */
/* loaded from: classes.dex */
public final class C5297d {

    /* renamed from: a */
    public final C5298e f17976a;

    /* renamed from: b */
    public final String f17977b;

    /* renamed from: c */
    public boolean f17978c;

    /* renamed from: d */
    public AbstractC5294a f17979d;

    /* renamed from: e */
    public final List<AbstractC5294a> f17980e;

    /* renamed from: f */
    public boolean f17981f;

    public C5297d(C5298e c5298e, String str) {
        C0042f.m158e(c5298e, "taskRunner");
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.f17976a = c5298e;
        this.f17977b = str;
        this.f17980e = new ArrayList();
    }

    /* renamed from: j */
    public static /* synthetic */ void m20657j(C5297d c5297d, AbstractC5294a abstractC5294a, long j9, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            j9 = 0;
        }
        c5297d.m20666i(abstractC5294a, j9);
    }

    /* renamed from: a */
    public final void m20658a() {
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f17976a) {
            if (m20659b()) {
                this.f17976a.m20678h(this);
            }
            C6319g c6319g = C6319g.f21314a;
        }
    }

    /* renamed from: b */
    public final boolean m20659b() {
        AbstractC5294a abstractC5294a = this.f17979d;
        if (abstractC5294a != null) {
            C0042f.m155b(abstractC5294a);
            if (abstractC5294a.m20647a()) {
                this.f17981f = true;
            }
        }
        boolean z8 = false;
        for (int size = this.f17980e.size() - 1; -1 < size; size--) {
            if (this.f17980e.get(size).m20647a()) {
                AbstractC5294a abstractC5294a2 = this.f17980e.get(size);
                if (C5298e.f17982h.m20683a().isLoggable(Level.FINE)) {
                    C5295b.m20656c(abstractC5294a2, this, "canceled");
                }
                this.f17980e.remove(size);
                z8 = true;
            }
        }
        return z8;
    }

    /* renamed from: c */
    public final AbstractC5294a m20660c() {
        return this.f17979d;
    }

    /* renamed from: d */
    public final boolean m20661d() {
        return this.f17981f;
    }

    /* renamed from: e */
    public final List<AbstractC5294a> m20662e() {
        return this.f17980e;
    }

    /* renamed from: f */
    public final String m20663f() {
        return this.f17977b;
    }

    /* renamed from: g */
    public final boolean m20664g() {
        return this.f17978c;
    }

    /* renamed from: h */
    public final C5298e m20665h() {
        return this.f17976a;
    }

    /* renamed from: i */
    public final void m20666i(AbstractC5294a abstractC5294a, long j9) {
        C0042f.m158e(abstractC5294a, "task");
        synchronized (this.f17976a) {
            if (!this.f17978c) {
                if (m20667k(abstractC5294a, j9, false)) {
                    this.f17976a.m20678h(this);
                }
                C6319g c6319g = C6319g.f21314a;
            } else if (abstractC5294a.m20647a()) {
                if (C5298e.f17982h.m20683a().isLoggable(Level.FINE)) {
                    C5295b.m20656c(abstractC5294a, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                if (C5298e.f17982h.m20683a().isLoggable(Level.FINE)) {
                    C5295b.m20656c(abstractC5294a, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    /* renamed from: k */
    public final boolean m20667k(AbstractC5294a abstractC5294a, long j9, boolean z8) {
        String str;
        C0042f.m158e(abstractC5294a, "task");
        abstractC5294a.m20651e(this);
        long jNanoTime = this.f17976a.m20677g().nanoTime();
        long j10 = jNanoTime + j9;
        int iIndexOf = this.f17980e.indexOf(abstractC5294a);
        if (iIndexOf != -1) {
            if (abstractC5294a.m20649c() <= j10) {
                if (C5298e.f17982h.m20683a().isLoggable(Level.FINE)) {
                    C5295b.m20656c(abstractC5294a, this, "already scheduled");
                }
                return false;
            }
            this.f17980e.remove(iIndexOf);
        }
        abstractC5294a.m20653g(j10);
        if (C5298e.f17982h.m20683a().isLoggable(Level.FINE)) {
            if (z8) {
                str = "run again after " + C5295b.m20655b(j10 - jNanoTime);
            } else {
                str = "scheduled after " + C5295b.m20655b(j10 - jNanoTime);
            }
            C5295b.m20656c(abstractC5294a, this, str);
        }
        Iterator<AbstractC5294a> it = this.f17980e.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (it.next().m20649c() - jNanoTime > j9) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = this.f17980e.size();
        }
        this.f17980e.add(size, abstractC5294a);
        return size == 0;
    }

    /* renamed from: l */
    public final void m20668l(AbstractC5294a abstractC5294a) {
        this.f17979d = abstractC5294a;
    }

    /* renamed from: m */
    public final void m20669m(boolean z8) {
        this.f17981f = z8;
    }

    /* renamed from: n */
    public final void m20670n() {
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f17976a) {
            this.f17978c = true;
            if (m20659b()) {
                this.f17976a.m20678h(this);
            }
            C6319g c6319g = C6319g.f21314a;
        }
    }

    public String toString() {
        return this.f17977b;
    }
}
