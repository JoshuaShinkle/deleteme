package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.c */
/* loaded from: classes.dex */
public class C0474c {

    /* renamed from: a */
    public final b f2590a;

    /* renamed from: b */
    public final a f2591b = new a();

    /* renamed from: c */
    public final List<View> f2592c = new ArrayList();

    /* renamed from: androidx.recyclerview.widget.c$a */
    public static class a {

        /* renamed from: a */
        public long f2593a = 0;

        /* renamed from: b */
        public a f2594b;

        /* renamed from: a */
        public void m2786a(int i9) {
            if (i9 < 64) {
                this.f2593a &= ~(1 << i9);
                return;
            }
            a aVar = this.f2594b;
            if (aVar != null) {
                aVar.m2786a(i9 - 64);
            }
        }

        /* renamed from: b */
        public int m2787b(int i9) {
            a aVar = this.f2594b;
            return aVar == null ? i9 >= 64 ? Long.bitCount(this.f2593a) : Long.bitCount(this.f2593a & ((1 << i9) - 1)) : i9 < 64 ? Long.bitCount(this.f2593a & ((1 << i9) - 1)) : aVar.m2787b(i9 - 64) + Long.bitCount(this.f2593a);
        }

        /* renamed from: c */
        public final void m2788c() {
            if (this.f2594b == null) {
                this.f2594b = new a();
            }
        }

        /* renamed from: d */
        public boolean m2789d(int i9) {
            if (i9 < 64) {
                return (this.f2593a & (1 << i9)) != 0;
            }
            m2788c();
            return this.f2594b.m2789d(i9 - 64);
        }

        /* renamed from: e */
        public void m2790e(int i9, boolean z8) {
            if (i9 >= 64) {
                m2788c();
                this.f2594b.m2790e(i9 - 64, z8);
                return;
            }
            long j9 = this.f2593a;
            boolean z9 = (Long.MIN_VALUE & j9) != 0;
            long j10 = (1 << i9) - 1;
            this.f2593a = ((j9 & (~j10)) << 1) | (j9 & j10);
            if (z8) {
                m2793h(i9);
            } else {
                m2786a(i9);
            }
            if (z9 || this.f2594b != null) {
                m2788c();
                this.f2594b.m2790e(0, z9);
            }
        }

        /* renamed from: f */
        public boolean m2791f(int i9) {
            if (i9 >= 64) {
                m2788c();
                return this.f2594b.m2791f(i9 - 64);
            }
            long j9 = 1 << i9;
            long j10 = this.f2593a;
            boolean z8 = (j10 & j9) != 0;
            long j11 = j10 & (~j9);
            this.f2593a = j11;
            long j12 = j9 - 1;
            this.f2593a = (j11 & j12) | Long.rotateRight((~j12) & j11, 1);
            a aVar = this.f2594b;
            if (aVar != null) {
                if (aVar.m2789d(0)) {
                    m2793h(63);
                }
                this.f2594b.m2791f(0);
            }
            return z8;
        }

        /* renamed from: g */
        public void m2792g() {
            this.f2593a = 0L;
            a aVar = this.f2594b;
            if (aVar != null) {
                aVar.m2792g();
            }
        }

        /* renamed from: h */
        public void m2793h(int i9) {
            if (i9 < 64) {
                this.f2593a |= 1 << i9;
            } else {
                m2788c();
                this.f2594b.m2793h(i9 - 64);
            }
        }

        public String toString() {
            if (this.f2594b == null) {
                return Long.toBinaryString(this.f2593a);
            }
            return this.f2594b.toString() + "xx" + Long.toBinaryString(this.f2593a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.c$b */
    public interface b {
        /* renamed from: a */
        View mo2360a(int i9);

        /* renamed from: b */
        void mo2361b(View view);

        /* renamed from: c */
        int mo2362c();

        /* renamed from: d */
        void mo2363d();

        /* renamed from: e */
        int mo2364e(View view);

        /* renamed from: f */
        RecyclerView.AbstractC0442c0 mo2365f(View view);

        /* renamed from: g */
        void mo2366g(int i9);

        /* renamed from: h */
        void mo2367h(View view);

        /* renamed from: i */
        void mo2368i(View view, int i9);

        /* renamed from: j */
        void mo2369j(int i9);

        /* renamed from: k */
        void mo2370k(View view, int i9, ViewGroup.LayoutParams layoutParams);
    }

    public C0474c(b bVar) {
        this.f2590a = bVar;
    }

    /* renamed from: a */
    public void m2766a(View view, int i9, boolean z8) {
        int iMo2362c = i9 < 0 ? this.f2590a.mo2362c() : m2773h(i9);
        this.f2591b.m2790e(iMo2362c, z8);
        if (z8) {
            m2777l(view);
        }
        this.f2590a.mo2368i(view, iMo2362c);
    }

    /* renamed from: b */
    public void m2767b(View view, boolean z8) {
        m2766a(view, -1, z8);
    }

    /* renamed from: c */
    public void m2768c(View view, int i9, ViewGroup.LayoutParams layoutParams, boolean z8) {
        int iMo2362c = i9 < 0 ? this.f2590a.mo2362c() : m2773h(i9);
        this.f2591b.m2790e(iMo2362c, z8);
        if (z8) {
            m2777l(view);
        }
        this.f2590a.mo2370k(view, iMo2362c, layoutParams);
    }

    /* renamed from: d */
    public void m2769d(int i9) {
        int iM2773h = m2773h(i9);
        this.f2591b.m2791f(iM2773h);
        this.f2590a.mo2366g(iM2773h);
    }

    /* renamed from: e */
    public View m2770e(int i9) {
        int size = this.f2592c.size();
        for (int i10 = 0; i10 < size; i10++) {
            View view = this.f2592c.get(i10);
            RecyclerView.AbstractC0442c0 abstractC0442c0Mo2365f = this.f2590a.mo2365f(view);
            if (abstractC0442c0Mo2365f.getLayoutPosition() == i9 && !abstractC0442c0Mo2365f.isInvalid() && !abstractC0442c0Mo2365f.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* renamed from: f */
    public View m2771f(int i9) {
        return this.f2590a.mo2360a(m2773h(i9));
    }

    /* renamed from: g */
    public int m2772g() {
        return this.f2590a.mo2362c() - this.f2592c.size();
    }

    /* renamed from: h */
    public final int m2773h(int i9) {
        if (i9 < 0) {
            return -1;
        }
        int iMo2362c = this.f2590a.mo2362c();
        int i10 = i9;
        while (i10 < iMo2362c) {
            int iM2787b = i9 - (i10 - this.f2591b.m2787b(i10));
            if (iM2787b == 0) {
                while (this.f2591b.m2789d(i10)) {
                    i10++;
                }
                return i10;
            }
            i10 += iM2787b;
        }
        return -1;
    }

    /* renamed from: i */
    public View m2774i(int i9) {
        return this.f2590a.mo2360a(i9);
    }

    /* renamed from: j */
    public int m2775j() {
        return this.f2590a.mo2362c();
    }

    /* renamed from: k */
    public void m2776k(View view) {
        int iMo2364e = this.f2590a.mo2364e(view);
        if (iMo2364e >= 0) {
            this.f2591b.m2793h(iMo2364e);
            m2777l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    /* renamed from: l */
    public final void m2777l(View view) {
        this.f2592c.add(view);
        this.f2590a.mo2361b(view);
    }

    /* renamed from: m */
    public int m2778m(View view) {
        int iMo2364e = this.f2590a.mo2364e(view);
        if (iMo2364e == -1 || this.f2591b.m2789d(iMo2364e)) {
            return -1;
        }
        return iMo2364e - this.f2591b.m2787b(iMo2364e);
    }

    /* renamed from: n */
    public boolean m2779n(View view) {
        return this.f2592c.contains(view);
    }

    /* renamed from: o */
    public void m2780o() {
        this.f2591b.m2792g();
        for (int size = this.f2592c.size() - 1; size >= 0; size--) {
            this.f2590a.mo2367h(this.f2592c.get(size));
            this.f2592c.remove(size);
        }
        this.f2590a.mo2363d();
    }

    /* renamed from: p */
    public void m2781p(View view) {
        int iMo2364e = this.f2590a.mo2364e(view);
        if (iMo2364e < 0) {
            return;
        }
        if (this.f2591b.m2791f(iMo2364e)) {
            m2785t(view);
        }
        this.f2590a.mo2369j(iMo2364e);
    }

    /* renamed from: q */
    public void m2782q(int i9) {
        int iM2773h = m2773h(i9);
        View viewMo2360a = this.f2590a.mo2360a(iM2773h);
        if (viewMo2360a == null) {
            return;
        }
        if (this.f2591b.m2791f(iM2773h)) {
            m2785t(viewMo2360a);
        }
        this.f2590a.mo2369j(iM2773h);
    }

    /* renamed from: r */
    public boolean m2783r(View view) {
        int iMo2364e = this.f2590a.mo2364e(view);
        if (iMo2364e == -1) {
            m2785t(view);
            return true;
        }
        if (!this.f2591b.m2789d(iMo2364e)) {
            return false;
        }
        this.f2591b.m2791f(iMo2364e);
        m2785t(view);
        this.f2590a.mo2369j(iMo2364e);
        return true;
    }

    /* renamed from: s */
    public void m2784s(View view) {
        int iMo2364e = this.f2590a.mo2364e(view);
        if (iMo2364e < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f2591b.m2789d(iMo2364e)) {
            this.f2591b.m2786a(iMo2364e);
            m2785t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    /* renamed from: t */
    public final boolean m2785t(View view) {
        if (!this.f2592c.remove(view)) {
            return false;
        }
        this.f2590a.mo2367h(view);
        return true;
    }

    public String toString() {
        return this.f2591b.toString() + ", hidden list:" + this.f2592c.size();
    }
}
