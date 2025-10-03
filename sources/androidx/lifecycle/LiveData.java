package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import p081h.C4975a;
import p091i.C5037b;

/* loaded from: classes.dex */
public abstract class LiveData<T> {

    /* renamed from: j */
    public static final Object f2190j = new Object();

    /* renamed from: a */
    public final Object f2191a = new Object();

    /* renamed from: b */
    public C5037b<InterfaceC0391l<? super T>, LiveData<T>.AbstractC0379b> f2192b = new C5037b<>();

    /* renamed from: c */
    public int f2193c = 0;

    /* renamed from: d */
    public volatile Object f2194d;

    /* renamed from: e */
    public volatile Object f2195e;

    /* renamed from: f */
    public int f2196f;

    /* renamed from: g */
    public boolean f2197g;

    /* renamed from: h */
    public boolean f2198h;

    /* renamed from: i */
    public final Runnable f2199i;

    public class LifecycleBoundObserver extends LiveData<T>.AbstractC0379b implements InterfaceC0383d {

        /* renamed from: e */
        public final InterfaceC0385f f2200e;

        public LifecycleBoundObserver(InterfaceC0385f interfaceC0385f, InterfaceC0391l<? super T> interfaceC0391l) {
            super(interfaceC0391l);
            this.f2200e = interfaceC0385f;
        }

        @Override // androidx.lifecycle.InterfaceC0383d
        /* renamed from: c */
        public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
            if (this.f2200e.getLifecycle().mo2048b() == Lifecycle.State.DESTROYED) {
                LiveData.this.mo2060k(this.f2203a);
            } else {
                m2065h(mo2064k());
            }
        }

        @Override // androidx.lifecycle.LiveData.AbstractC0379b
        /* renamed from: i */
        public void mo2062i() {
            this.f2200e.getLifecycle().mo2049c(this);
        }

        @Override // androidx.lifecycle.LiveData.AbstractC0379b
        /* renamed from: j */
        public boolean mo2063j(InterfaceC0385f interfaceC0385f) {
            return this.f2200e == interfaceC0385f;
        }

        @Override // androidx.lifecycle.LiveData.AbstractC0379b
        /* renamed from: k */
        public boolean mo2064k() {
            return this.f2200e.getLifecycle().mo2048b().m2050a(Lifecycle.State.STARTED);
        }
    }

    /* renamed from: androidx.lifecycle.LiveData$a */
    public class RunnableC0378a implements Runnable {
        public RunnableC0378a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f2191a) {
                obj = LiveData.this.f2195e;
                LiveData.this.f2195e = LiveData.f2190j;
            }
            LiveData.this.mo2061l(obj);
        }
    }

    /* renamed from: androidx.lifecycle.LiveData$b */
    public abstract class AbstractC0379b {

        /* renamed from: a */
        public final InterfaceC0391l<? super T> f2203a;

        /* renamed from: b */
        public boolean f2204b;

        /* renamed from: c */
        public int f2205c = -1;

        public AbstractC0379b(InterfaceC0391l<? super T> interfaceC0391l) {
            this.f2203a = interfaceC0391l;
        }

        /* renamed from: h */
        public void m2065h(boolean z8) {
            if (z8 == this.f2204b) {
                return;
            }
            this.f2204b = z8;
            LiveData liveData = LiveData.this;
            int i9 = liveData.f2193c;
            boolean z9 = i9 == 0;
            liveData.f2193c = i9 + (z8 ? 1 : -1);
            if (z9 && z8) {
                liveData.mo2057h();
            }
            LiveData liveData2 = LiveData.this;
            if (liveData2.f2193c == 0 && !this.f2204b) {
                liveData2.mo2058i();
            }
            if (this.f2204b) {
                LiveData.this.m2053d(this);
            }
        }

        /* renamed from: i */
        public void mo2062i() {
        }

        /* renamed from: j */
        public boolean mo2063j(InterfaceC0385f interfaceC0385f) {
            return false;
        }

        /* renamed from: k */
        public abstract boolean mo2064k();
    }

    public LiveData() {
        Object obj = f2190j;
        this.f2194d = obj;
        this.f2195e = obj;
        this.f2196f = -1;
        this.f2199i = new RunnableC0378a();
    }

    /* renamed from: b */
    public static void m2051b(String str) {
        if (C4975a.m19264d().mo19266b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    /* renamed from: c */
    public final void m2052c(LiveData<T>.AbstractC0379b abstractC0379b) {
        if (abstractC0379b.f2204b) {
            if (!abstractC0379b.mo2064k()) {
                abstractC0379b.m2065h(false);
                return;
            }
            int i9 = abstractC0379b.f2205c;
            int i10 = this.f2196f;
            if (i9 >= i10) {
                return;
            }
            abstractC0379b.f2205c = i10;
            abstractC0379b.f2203a.mo2104a((Object) this.f2194d);
        }
    }

    /* renamed from: d */
    public void m2053d(LiveData<T>.AbstractC0379b abstractC0379b) {
        if (this.f2197g) {
            this.f2198h = true;
            return;
        }
        this.f2197g = true;
        do {
            this.f2198h = false;
            if (abstractC0379b != null) {
                m2052c(abstractC0379b);
                abstractC0379b = null;
            } else {
                C5037b<InterfaceC0391l<? super T>, LiveData<T>.AbstractC0379b>.d dVarM19686c = this.f2192b.m19686c();
                while (dVarM19686c.hasNext()) {
                    m2052c((AbstractC0379b) dVarM19686c.next().getValue());
                    if (this.f2198h) {
                        break;
                    }
                }
            }
        } while (this.f2198h);
        this.f2197g = false;
    }

    /* renamed from: e */
    public T m2054e() {
        T t8 = (T) this.f2194d;
        if (t8 != f2190j) {
            return t8;
        }
        return null;
    }

    /* renamed from: f */
    public boolean m2055f() {
        return this.f2193c > 0;
    }

    /* renamed from: g */
    public void m2056g(InterfaceC0385f interfaceC0385f, InterfaceC0391l<? super T> interfaceC0391l) {
        m2051b("observe");
        if (interfaceC0385f.getLifecycle().mo2048b() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(interfaceC0385f, interfaceC0391l);
        LiveData<T>.AbstractC0379b abstractC0379bMo19682f = this.f2192b.mo19682f(interfaceC0391l, lifecycleBoundObserver);
        if (abstractC0379bMo19682f != null && !abstractC0379bMo19682f.mo2063j(interfaceC0385f)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (abstractC0379bMo19682f != null) {
            return;
        }
        interfaceC0385f.getLifecycle().mo2047a(lifecycleBoundObserver);
    }

    /* renamed from: h */
    public void mo2057h() {
    }

    /* renamed from: i */
    public void mo2058i() {
    }

    /* renamed from: j */
    public void mo2059j(T t8) {
        boolean z8;
        synchronized (this.f2191a) {
            z8 = this.f2195e == f2190j;
            this.f2195e = t8;
        }
        if (z8) {
            C4975a.m19264d().mo19267c(this.f2199i);
        }
    }

    /* renamed from: k */
    public void mo2060k(InterfaceC0391l<? super T> interfaceC0391l) {
        m2051b("removeObserver");
        LiveData<T>.AbstractC0379b abstractC0379bMo19683g = this.f2192b.mo19683g(interfaceC0391l);
        if (abstractC0379bMo19683g == null) {
            return;
        }
        abstractC0379bMo19683g.mo2062i();
        abstractC0379bMo19683g.m2065h(false);
    }

    /* renamed from: l */
    public void mo2061l(T t8) {
        m2051b("setValue");
        this.f2196f++;
        this.f2194d = t8;
        m2053d(null);
    }
}
