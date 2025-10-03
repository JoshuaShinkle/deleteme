package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import p091i.C5036a;
import p091i.C5037b;

/* renamed from: androidx.lifecycle.g */
/* loaded from: classes.dex */
public class C0386g extends Lifecycle {

    /* renamed from: d */
    public final WeakReference<InterfaceC0385f> f2219d;

    /* renamed from: b */
    public C5036a<InterfaceC0384e, b> f2217b = new C5036a<>();

    /* renamed from: e */
    public int f2220e = 0;

    /* renamed from: f */
    public boolean f2221f = false;

    /* renamed from: g */
    public boolean f2222g = false;

    /* renamed from: h */
    public ArrayList<Lifecycle.State> f2223h = new ArrayList<>();

    /* renamed from: c */
    public Lifecycle.State f2218c = Lifecycle.State.INITIALIZED;

    /* renamed from: androidx.lifecycle.g$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f2224a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f2225b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f2225b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2225b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2225b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2225b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2225b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f2224a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2224a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2224a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2224a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2224a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2224a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2224a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* renamed from: androidx.lifecycle.g$b */
    public static class b {

        /* renamed from: a */
        public Lifecycle.State f2226a;

        /* renamed from: b */
        public InterfaceC0383d f2227b;

        public b(InterfaceC0384e interfaceC0384e, Lifecycle.State state) {
            this.f2227b = C0388i.m2102f(interfaceC0384e);
            this.f2226a = state;
        }

        /* renamed from: a */
        public void m2096a(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
            Lifecycle.State stateM2082h = C0386g.m2082h(event);
            this.f2226a = C0386g.m2083l(this.f2226a, stateM2082h);
            this.f2227b.mo209c(interfaceC0385f, event);
            this.f2226a = stateM2082h;
        }
    }

    public C0386g(InterfaceC0385f interfaceC0385f) {
        this.f2219d = new WeakReference<>(interfaceC0385f);
    }

    /* renamed from: f */
    public static Lifecycle.Event m2081f(Lifecycle.State state) {
        int i9 = a.f2225b[state.ordinal()];
        if (i9 == 1) {
            throw new IllegalArgumentException();
        }
        if (i9 == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i9 == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i9 == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i9 == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    /* renamed from: h */
    public static Lifecycle.State m2082h(Lifecycle.Event event) {
        switch (a.f2224a[event.ordinal()]) {
            case 1:
            case 2:
                return Lifecycle.State.CREATED;
            case 3:
            case 4:
                return Lifecycle.State.STARTED;
            case 5:
                return Lifecycle.State.RESUMED;
            case 6:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    /* renamed from: l */
    public static Lifecycle.State m2083l(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    /* renamed from: r */
    public static Lifecycle.Event m2084r(Lifecycle.State state) {
        int i9 = a.f2225b[state.ordinal()];
        if (i9 != 1) {
            if (i9 == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i9 == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i9 == 4) {
                throw new IllegalArgumentException();
            }
            if (i9 != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Override // androidx.lifecycle.Lifecycle
    /* renamed from: a */
    public void mo2047a(InterfaceC0384e interfaceC0384e) {
        InterfaceC0385f interfaceC0385f;
        Lifecycle.State state = this.f2218c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(interfaceC0384e, state2);
        if (this.f2217b.mo19682f(interfaceC0384e, bVar) == null && (interfaceC0385f = this.f2219d.get()) != null) {
            boolean z8 = this.f2220e != 0 || this.f2221f;
            Lifecycle.State stateM2086e = m2086e(interfaceC0384e);
            this.f2220e++;
            while (bVar.f2226a.compareTo(stateM2086e) < 0 && this.f2217b.contains(interfaceC0384e)) {
                m2093o(bVar.f2226a);
                bVar.m2096a(interfaceC0385f, m2084r(bVar.f2226a));
                m2092n();
                stateM2086e = m2086e(interfaceC0384e);
            }
            if (!z8) {
                m2095q();
            }
            this.f2220e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    /* renamed from: b */
    public Lifecycle.State mo2048b() {
        return this.f2218c;
    }

    @Override // androidx.lifecycle.Lifecycle
    /* renamed from: c */
    public void mo2049c(InterfaceC0384e interfaceC0384e) {
        this.f2217b.mo19683g(interfaceC0384e);
    }

    /* renamed from: d */
    public final void m2085d(InterfaceC0385f interfaceC0385f) {
        Iterator<Map.Entry<InterfaceC0384e, b>> itDescendingIterator = this.f2217b.descendingIterator();
        while (itDescendingIterator.hasNext() && !this.f2222g) {
            Map.Entry<InterfaceC0384e, b> next = itDescendingIterator.next();
            b value = next.getValue();
            while (value.f2226a.compareTo(this.f2218c) > 0 && !this.f2222g && this.f2217b.contains(next.getKey())) {
                Lifecycle.Event eventM2081f = m2081f(value.f2226a);
                m2093o(m2082h(eventM2081f));
                value.m2096a(interfaceC0385f, eventM2081f);
                m2092n();
            }
        }
    }

    /* renamed from: e */
    public final Lifecycle.State m2086e(InterfaceC0384e interfaceC0384e) {
        Map.Entry<InterfaceC0384e, b> entryM19684h = this.f2217b.m19684h(interfaceC0384e);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryM19684h != null ? entryM19684h.getValue().f2226a : null;
        if (!this.f2223h.isEmpty()) {
            state = this.f2223h.get(r0.size() - 1);
        }
        return m2083l(m2083l(this.f2218c, state2), state);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: g */
    public final void m2087g(InterfaceC0385f interfaceC0385f) {
        C5037b<InterfaceC0384e, b>.d dVarM19686c = this.f2217b.m19686c();
        while (dVarM19686c.hasNext() && !this.f2222g) {
            Map.Entry next = dVarM19686c.next();
            b bVar = (b) next.getValue();
            while (bVar.f2226a.compareTo(this.f2218c) < 0 && !this.f2222g && this.f2217b.contains(next.getKey())) {
                m2093o(bVar.f2226a);
                bVar.m2096a(interfaceC0385f, m2084r(bVar.f2226a));
                m2092n();
            }
        }
    }

    /* renamed from: i */
    public void m2088i(Lifecycle.Event event) {
        m2091m(m2082h(event));
    }

    /* renamed from: j */
    public final boolean m2089j() {
        if (this.f2217b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f2217b.m19685a().getValue().f2226a;
        Lifecycle.State state2 = this.f2217b.m19687d().getValue().f2226a;
        return state == state2 && this.f2218c == state2;
    }

    @Deprecated
    /* renamed from: k */
    public void m2090k(Lifecycle.State state) {
        m2094p(state);
    }

    /* renamed from: m */
    public final void m2091m(Lifecycle.State state) {
        if (this.f2218c == state) {
            return;
        }
        this.f2218c = state;
        if (this.f2221f || this.f2220e != 0) {
            this.f2222g = true;
            return;
        }
        this.f2221f = true;
        m2095q();
        this.f2221f = false;
    }

    /* renamed from: n */
    public final void m2092n() {
        this.f2223h.remove(r0.size() - 1);
    }

    /* renamed from: o */
    public final void m2093o(Lifecycle.State state) {
        this.f2223h.add(state);
    }

    /* renamed from: p */
    public void m2094p(Lifecycle.State state) {
        m2091m(state);
    }

    /* renamed from: q */
    public final void m2095q() {
        InterfaceC0385f interfaceC0385f = this.f2219d.get();
        if (interfaceC0385f == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!m2089j()) {
            this.f2222g = false;
            if (this.f2218c.compareTo(this.f2217b.m19685a().getValue().f2226a) < 0) {
                m2085d(interfaceC0385f);
            }
            Map.Entry<InterfaceC0384e, b> entryM19687d = this.f2217b.m19687d();
            if (!this.f2222g && entryM19687d != null && this.f2218c.compareTo(entryM19687d.getValue().f2226a) > 0) {
                m2087g(interfaceC0385f);
            }
        }
        this.f2222g = false;
    }
}
