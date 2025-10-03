package androidx.recyclerview.widget;

import androidx.recyclerview.widget.C0482k;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import java.util.List;
import p021c0.C0700f;
import p021c0.InterfaceC0699e;

/* renamed from: androidx.recyclerview.widget.a */
/* loaded from: classes.dex */
public class C0472a implements C0482k.a {

    /* renamed from: a */
    public InterfaceC0699e<b> f2573a;

    /* renamed from: b */
    public final ArrayList<b> f2574b;

    /* renamed from: c */
    public final ArrayList<b> f2575c;

    /* renamed from: d */
    public final a f2576d;

    /* renamed from: e */
    public Runnable f2577e;

    /* renamed from: f */
    public final boolean f2578f;

    /* renamed from: g */
    public final C0482k f2579g;

    /* renamed from: h */
    public int f2580h;

    /* renamed from: androidx.recyclerview.widget.a$a */
    public interface a {
        /* renamed from: a */
        void mo2371a(int i9, int i10);

        /* renamed from: b */
        void mo2372b(b bVar);

        /* renamed from: c */
        void mo2373c(int i9, int i10, Object obj);

        /* renamed from: d */
        void mo2374d(b bVar);

        /* renamed from: e */
        RecyclerView.AbstractC0442c0 mo2375e(int i9);

        /* renamed from: f */
        void mo2376f(int i9, int i10);

        /* renamed from: g */
        void mo2377g(int i9, int i10);

        /* renamed from: h */
        void mo2378h(int i9, int i10);
    }

    /* renamed from: androidx.recyclerview.widget.a$b */
    public static class b {

        /* renamed from: a */
        public int f2581a;

        /* renamed from: b */
        public int f2582b;

        /* renamed from: c */
        public Object f2583c;

        /* renamed from: d */
        public int f2584d;

        public b(int i9, int i10, int i11, Object obj) {
            this.f2581a = i9;
            this.f2582b = i10;
            this.f2584d = i11;
            this.f2583c = obj;
        }

        /* renamed from: a */
        public String m2760a() {
            int i9 = this.f2581a;
            return i9 != 1 ? i9 != 2 ? i9 != 4 ? i9 != 8 ? "??" : "mv" : "up" : "rm" : ProductAction.ACTION_ADD;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i9 = this.f2581a;
            if (i9 != bVar.f2581a) {
                return false;
            }
            if (i9 == 8 && Math.abs(this.f2584d - this.f2582b) == 1 && this.f2584d == bVar.f2582b && this.f2582b == bVar.f2584d) {
                return true;
            }
            if (this.f2584d != bVar.f2584d || this.f2582b != bVar.f2582b) {
                return false;
            }
            Object obj2 = this.f2583c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f2583c)) {
                    return false;
                }
            } else if (bVar.f2583c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f2581a * 31) + this.f2582b) * 31) + this.f2584d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + m2760a() + ",s:" + this.f2582b + "c:" + this.f2584d + ",p:" + this.f2583c + "]";
        }
    }

    public C0472a(a aVar) {
        this(aVar, false);
    }

    @Override // androidx.recyclerview.widget.C0482k.a
    /* renamed from: a */
    public void mo2734a(b bVar) {
        if (this.f2578f) {
            return;
        }
        bVar.f2583c = null;
        this.f2573a.mo3464a(bVar);
    }

    @Override // androidx.recyclerview.widget.C0482k.a
    /* renamed from: b */
    public b mo2735b(int i9, int i10, int i11, Object obj) {
        b bVarMo3465b = this.f2573a.mo3465b();
        if (bVarMo3465b == null) {
            return new b(i9, i10, i11, obj);
        }
        bVarMo3465b.f2581a = i9;
        bVarMo3465b.f2582b = i10;
        bVarMo3465b.f2584d = i11;
        bVarMo3465b.f2583c = obj;
        return bVarMo3465b;
    }

    /* renamed from: c */
    public final void m2736c(b bVar) {
        m2755v(bVar);
    }

    /* renamed from: d */
    public final void m2737d(b bVar) {
        m2755v(bVar);
    }

    /* renamed from: e */
    public int m2738e(int i9) {
        int size = this.f2574b.size();
        for (int i10 = 0; i10 < size; i10++) {
            b bVar = this.f2574b.get(i10);
            int i11 = bVar.f2581a;
            if (i11 != 1) {
                if (i11 == 2) {
                    int i12 = bVar.f2582b;
                    if (i12 <= i9) {
                        int i13 = bVar.f2584d;
                        if (i12 + i13 > i9) {
                            return -1;
                        }
                        i9 -= i13;
                    } else {
                        continue;
                    }
                } else if (i11 == 8) {
                    int i14 = bVar.f2582b;
                    if (i14 == i9) {
                        i9 = bVar.f2584d;
                    } else {
                        if (i14 < i9) {
                            i9--;
                        }
                        if (bVar.f2584d <= i9) {
                            i9++;
                        }
                    }
                }
            } else if (bVar.f2582b <= i9) {
                i9 += bVar.f2584d;
            }
        }
        return i9;
    }

    /* renamed from: f */
    public final void m2739f(b bVar) {
        boolean z8;
        char c9;
        int i9 = bVar.f2582b;
        int i10 = bVar.f2584d + i9;
        char c10 = 65535;
        int i11 = i9;
        int i12 = 0;
        while (i11 < i10) {
            if (this.f2576d.mo2375e(i11) != null || m2741h(i11)) {
                if (c10 == 0) {
                    m2744k(mo2735b(2, i9, i12, null));
                    z8 = true;
                } else {
                    z8 = false;
                }
                c9 = 1;
            } else {
                if (c10 == 1) {
                    m2755v(mo2735b(2, i9, i12, null));
                    z8 = true;
                } else {
                    z8 = false;
                }
                c9 = 0;
            }
            if (z8) {
                i11 -= i12;
                i10 -= i12;
                i12 = 1;
            } else {
                i12++;
            }
            i11++;
            c10 = c9;
        }
        if (i12 != bVar.f2584d) {
            mo2734a(bVar);
            bVar = mo2735b(2, i9, i12, null);
        }
        if (c10 == 0) {
            m2744k(bVar);
        } else {
            m2755v(bVar);
        }
    }

    /* renamed from: g */
    public final void m2740g(b bVar) {
        int i9 = bVar.f2582b;
        int i10 = bVar.f2584d + i9;
        int i11 = 0;
        boolean z8 = -1;
        int i12 = i9;
        while (i9 < i10) {
            if (this.f2576d.mo2375e(i9) != null || m2741h(i9)) {
                if (!z8) {
                    m2744k(mo2735b(4, i12, i11, bVar.f2583c));
                    i12 = i9;
                    i11 = 0;
                }
                z8 = true;
            } else {
                if (z8) {
                    m2755v(mo2735b(4, i12, i11, bVar.f2583c));
                    i12 = i9;
                    i11 = 0;
                }
                z8 = false;
            }
            i11++;
            i9++;
        }
        if (i11 != bVar.f2584d) {
            Object obj = bVar.f2583c;
            mo2734a(bVar);
            bVar = mo2735b(4, i12, i11, obj);
        }
        if (z8) {
            m2755v(bVar);
        } else {
            m2744k(bVar);
        }
    }

    /* renamed from: h */
    public final boolean m2741h(int i9) {
        int size = this.f2575c.size();
        for (int i10 = 0; i10 < size; i10++) {
            b bVar = this.f2575c.get(i10);
            int i11 = bVar.f2581a;
            if (i11 == 8) {
                if (m2747n(bVar.f2584d, i10 + 1) == i9) {
                    return true;
                }
            } else if (i11 == 1) {
                int i12 = bVar.f2582b;
                int i13 = bVar.f2584d + i12;
                while (i12 < i13) {
                    if (m2747n(i12, i10 + 1) == i9) {
                        return true;
                    }
                    i12++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    /* renamed from: i */
    public void m2742i() {
        int size = this.f2575c.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2576d.mo2374d(this.f2575c.get(i9));
        }
        m2757x(this.f2575c);
        this.f2580h = 0;
    }

    /* renamed from: j */
    public void m2743j() {
        m2742i();
        int size = this.f2574b.size();
        for (int i9 = 0; i9 < size; i9++) {
            b bVar = this.f2574b.get(i9);
            int i10 = bVar.f2581a;
            if (i10 == 1) {
                this.f2576d.mo2374d(bVar);
                this.f2576d.mo2377g(bVar.f2582b, bVar.f2584d);
            } else if (i10 == 2) {
                this.f2576d.mo2374d(bVar);
                this.f2576d.mo2378h(bVar.f2582b, bVar.f2584d);
            } else if (i10 == 4) {
                this.f2576d.mo2374d(bVar);
                this.f2576d.mo2373c(bVar.f2582b, bVar.f2584d, bVar.f2583c);
            } else if (i10 == 8) {
                this.f2576d.mo2374d(bVar);
                this.f2576d.mo2371a(bVar.f2582b, bVar.f2584d);
            }
            Runnable runnable = this.f2577e;
            if (runnable != null) {
                runnable.run();
            }
        }
        m2757x(this.f2574b);
        this.f2580h = 0;
    }

    /* renamed from: k */
    public final void m2744k(b bVar) {
        int i9;
        int i10 = bVar.f2581a;
        if (i10 == 1 || i10 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM2759z = m2759z(bVar.f2582b, i10);
        int i11 = bVar.f2582b;
        int i12 = bVar.f2581a;
        if (i12 == 2) {
            i9 = 0;
        } else {
            if (i12 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i9 = 1;
        }
        int i13 = 1;
        for (int i14 = 1; i14 < bVar.f2584d; i14++) {
            int iM2759z2 = m2759z(bVar.f2582b + (i9 * i14), bVar.f2581a);
            int i15 = bVar.f2581a;
            if (i15 == 2 ? iM2759z2 == iM2759z : i15 == 4 && iM2759z2 == iM2759z + 1) {
                i13++;
            } else {
                b bVarMo2735b = mo2735b(i15, iM2759z, i13, bVar.f2583c);
                m2745l(bVarMo2735b, i11);
                mo2734a(bVarMo2735b);
                if (bVar.f2581a == 4) {
                    i11 += i13;
                }
                i13 = 1;
                iM2759z = iM2759z2;
            }
        }
        Object obj = bVar.f2583c;
        mo2734a(bVar);
        if (i13 > 0) {
            b bVarMo2735b2 = mo2735b(bVar.f2581a, iM2759z, i13, obj);
            m2745l(bVarMo2735b2, i11);
            mo2734a(bVarMo2735b2);
        }
    }

    /* renamed from: l */
    public void m2745l(b bVar, int i9) {
        this.f2576d.mo2372b(bVar);
        int i10 = bVar.f2581a;
        if (i10 == 2) {
            this.f2576d.mo2378h(i9, bVar.f2584d);
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f2576d.mo2373c(i9, bVar.f2584d, bVar.f2583c);
        }
    }

    /* renamed from: m */
    public int m2746m(int i9) {
        return m2747n(i9, 0);
    }

    /* renamed from: n */
    public int m2747n(int i9, int i10) {
        int size = this.f2575c.size();
        while (i10 < size) {
            b bVar = this.f2575c.get(i10);
            int i11 = bVar.f2581a;
            if (i11 == 8) {
                int i12 = bVar.f2582b;
                if (i12 == i9) {
                    i9 = bVar.f2584d;
                } else {
                    if (i12 < i9) {
                        i9--;
                    }
                    if (bVar.f2584d <= i9) {
                        i9++;
                    }
                }
            } else {
                int i13 = bVar.f2582b;
                if (i13 > i9) {
                    continue;
                } else if (i11 == 2) {
                    int i14 = bVar.f2584d;
                    if (i9 < i13 + i14) {
                        return -1;
                    }
                    i9 -= i14;
                } else if (i11 == 1) {
                    i9 += bVar.f2584d;
                }
            }
            i10++;
        }
        return i9;
    }

    /* renamed from: o */
    public boolean m2748o(int i9) {
        return (i9 & this.f2580h) != 0;
    }

    /* renamed from: p */
    public boolean m2749p() {
        return this.f2574b.size() > 0;
    }

    /* renamed from: q */
    public boolean m2750q() {
        return (this.f2575c.isEmpty() || this.f2574b.isEmpty()) ? false : true;
    }

    /* renamed from: r */
    public boolean m2751r(int i9, int i10, Object obj) {
        if (i10 < 1) {
            return false;
        }
        this.f2574b.add(mo2735b(4, i9, i10, obj));
        this.f2580h |= 4;
        return this.f2574b.size() == 1;
    }

    /* renamed from: s */
    public boolean m2752s(int i9, int i10) {
        if (i10 < 1) {
            return false;
        }
        this.f2574b.add(mo2735b(1, i9, i10, null));
        this.f2580h |= 1;
        return this.f2574b.size() == 1;
    }

    /* renamed from: t */
    public boolean m2753t(int i9, int i10, int i11) {
        if (i9 == i10) {
            return false;
        }
        if (i11 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.f2574b.add(mo2735b(8, i9, i10, null));
        this.f2580h |= 8;
        return this.f2574b.size() == 1;
    }

    /* renamed from: u */
    public boolean m2754u(int i9, int i10) {
        if (i10 < 1) {
            return false;
        }
        this.f2574b.add(mo2735b(2, i9, i10, null));
        this.f2580h |= 2;
        return this.f2574b.size() == 1;
    }

    /* renamed from: v */
    public final void m2755v(b bVar) {
        this.f2575c.add(bVar);
        int i9 = bVar.f2581a;
        if (i9 == 1) {
            this.f2576d.mo2377g(bVar.f2582b, bVar.f2584d);
            return;
        }
        if (i9 == 2) {
            this.f2576d.mo2376f(bVar.f2582b, bVar.f2584d);
            return;
        }
        if (i9 == 4) {
            this.f2576d.mo2373c(bVar.f2582b, bVar.f2584d, bVar.f2583c);
        } else {
            if (i9 == 8) {
                this.f2576d.mo2371a(bVar.f2582b, bVar.f2584d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* renamed from: w */
    public void m2756w() {
        this.f2579g.m2860b(this.f2574b);
        int size = this.f2574b.size();
        for (int i9 = 0; i9 < size; i9++) {
            b bVar = this.f2574b.get(i9);
            int i10 = bVar.f2581a;
            if (i10 == 1) {
                m2736c(bVar);
            } else if (i10 == 2) {
                m2739f(bVar);
            } else if (i10 == 4) {
                m2740g(bVar);
            } else if (i10 == 8) {
                m2737d(bVar);
            }
            Runnable runnable = this.f2577e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f2574b.clear();
    }

    /* renamed from: x */
    public void m2757x(List<b> list) {
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            mo2734a(list.get(i9));
        }
        list.clear();
    }

    /* renamed from: y */
    public void m2758y() {
        m2757x(this.f2574b);
        m2757x(this.f2575c);
        this.f2580h = 0;
    }

    /* renamed from: z */
    public final int m2759z(int i9, int i10) {
        int i11;
        int i12;
        for (int size = this.f2575c.size() - 1; size >= 0; size--) {
            b bVar = this.f2575c.get(size);
            int i13 = bVar.f2581a;
            if (i13 == 8) {
                int i14 = bVar.f2582b;
                int i15 = bVar.f2584d;
                if (i14 < i15) {
                    i12 = i14;
                    i11 = i15;
                } else {
                    i11 = i14;
                    i12 = i15;
                }
                if (i9 < i12 || i9 > i11) {
                    if (i9 < i14) {
                        if (i10 == 1) {
                            bVar.f2582b = i14 + 1;
                            bVar.f2584d = i15 + 1;
                        } else if (i10 == 2) {
                            bVar.f2582b = i14 - 1;
                            bVar.f2584d = i15 - 1;
                        }
                    }
                } else if (i12 == i14) {
                    if (i10 == 1) {
                        bVar.f2584d = i15 + 1;
                    } else if (i10 == 2) {
                        bVar.f2584d = i15 - 1;
                    }
                    i9++;
                } else {
                    if (i10 == 1) {
                        bVar.f2582b = i14 + 1;
                    } else if (i10 == 2) {
                        bVar.f2582b = i14 - 1;
                    }
                    i9--;
                }
            } else {
                int i16 = bVar.f2582b;
                if (i16 <= i9) {
                    if (i13 == 1) {
                        i9 -= bVar.f2584d;
                    } else if (i13 == 2) {
                        i9 += bVar.f2584d;
                    }
                } else if (i10 == 1) {
                    bVar.f2582b = i16 + 1;
                } else if (i10 == 2) {
                    bVar.f2582b = i16 - 1;
                }
            }
        }
        for (int size2 = this.f2575c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f2575c.get(size2);
            if (bVar2.f2581a == 8) {
                int i17 = bVar2.f2584d;
                if (i17 == bVar2.f2582b || i17 < 0) {
                    this.f2575c.remove(size2);
                    mo2734a(bVar2);
                }
            } else if (bVar2.f2584d <= 0) {
                this.f2575c.remove(size2);
                mo2734a(bVar2);
            }
        }
        return i9;
    }

    public C0472a(a aVar, boolean z8) {
        this.f2573a = new C0700f(30);
        this.f2574b = new ArrayList<>();
        this.f2575c = new ArrayList<>();
        this.f2580h = 0;
        this.f2576d = aVar;
        this.f2578f = z8;
        this.f2579g = new C0482k(this);
    }
}
