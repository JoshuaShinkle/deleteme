package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.connection.C5499e;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6319g;

/* renamed from: okhttp3.o */
/* loaded from: classes2.dex */
public final class C5514o {

    /* renamed from: c */
    public Runnable f18928c;

    /* renamed from: d */
    public ExecutorService f18929d;

    /* renamed from: a */
    public int f18926a = 64;

    /* renamed from: b */
    public int f18927b = 5;

    /* renamed from: e */
    public final ArrayDeque<C5499e.a> f18930e = new ArrayDeque<>();

    /* renamed from: f */
    public final ArrayDeque<C5499e.a> f18931f = new ArrayDeque<>();

    /* renamed from: g */
    public final ArrayDeque<C5499e> f18932g = new ArrayDeque<>();

    /* renamed from: a */
    public final void m21588a(C5499e.a aVar) {
        C5499e.a aVarM21591d;
        C0042f.m158e(aVar, "call");
        synchronized (this) {
            this.f18930e.add(aVar);
            if (!aVar.m21369b().m21355n() && (aVarM21591d = m21591d(aVar.m21371d())) != null) {
                aVar.m21372e(aVarM21591d);
            }
            C6319g c6319g = C6319g.f21314a;
        }
        m21595h();
    }

    /* renamed from: b */
    public final synchronized void m21589b(C5499e c5499e) {
        C0042f.m158e(c5499e, "call");
        this.f18932g.add(c5499e);
    }

    /* renamed from: c */
    public final synchronized ExecutorService m21590c() {
        ExecutorService executorService;
        if (this.f18929d == null) {
            this.f18929d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), C5057d.m19773M(C5057d.f17451i + " Dispatcher", false));
        }
        executorService = this.f18929d;
        C0042f.m155b(executorService);
        return executorService;
    }

    /* renamed from: d */
    public final C5499e.a m21591d(String str) {
        Iterator<C5499e.a> it = this.f18931f.iterator();
        while (it.hasNext()) {
            C5499e.a next = it.next();
            if (C0042f.m154a(next.m21371d(), str)) {
                return next;
            }
        }
        Iterator<C5499e.a> it2 = this.f18930e.iterator();
        while (it2.hasNext()) {
            C5499e.a next2 = it2.next();
            if (C0042f.m154a(next2.m21371d(), str)) {
                return next2;
            }
        }
        return null;
    }

    /* renamed from: e */
    public final <T> void m21592e(Deque<T> deque, T t8) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t8)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.f18928c;
            C6319g c6319g = C6319g.f21314a;
        }
        if (m21595h() || runnable == null) {
            return;
        }
        runnable.run();
    }

    /* renamed from: f */
    public final void m21593f(C5499e.a aVar) {
        C0042f.m158e(aVar, "call");
        aVar.m21370c().decrementAndGet();
        m21592e(this.f18931f, aVar);
    }

    /* renamed from: g */
    public final void m21594g(C5499e c5499e) {
        C0042f.m158e(c5499e, "call");
        m21592e(this.f18932g, c5499e);
    }

    /* renamed from: h */
    public final boolean m21595h() {
        int i9;
        boolean z8;
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<C5499e.a> it = this.f18930e.iterator();
            C0042f.m157d(it, "readyAsyncCalls.iterator()");
            while (it.hasNext()) {
                C5499e.a next = it.next();
                if (this.f18931f.size() >= this.f18926a) {
                    break;
                }
                if (next.m21370c().get() < this.f18927b) {
                    it.remove();
                    next.m21370c().incrementAndGet();
                    C0042f.m157d(next, "asyncCall");
                    arrayList.add(next);
                    this.f18931f.add(next);
                }
            }
            z8 = m21596i() > 0;
            C6319g c6319g = C6319g.f21314a;
        }
        int size = arrayList.size();
        for (i9 = 0; i9 < size; i9++) {
            ((C5499e.a) arrayList.get(i9)).m21368a(m21590c());
        }
        return z8;
    }

    /* renamed from: i */
    public final synchronized int m21596i() {
        return this.f18931f.size() + this.f18932g.size();
    }
}
