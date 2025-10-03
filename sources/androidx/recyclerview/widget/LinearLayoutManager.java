package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;

/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.AbstractC0454o implements RecyclerView.AbstractC0464y.b {

    /* renamed from: A */
    public int f2379A;

    /* renamed from: B */
    public int f2380B;

    /* renamed from: C */
    public boolean f2381C;

    /* renamed from: D */
    public SavedState f2382D;

    /* renamed from: E */
    public final C0433a f2383E;

    /* renamed from: F */
    public final C0434b f2384F;

    /* renamed from: G */
    public int f2385G;

    /* renamed from: s */
    public int f2386s;

    /* renamed from: t */
    public C0435c f2387t;

    /* renamed from: u */
    public AbstractC0483l f2388u;

    /* renamed from: v */
    public boolean f2389v;

    /* renamed from: w */
    public boolean f2390w;

    /* renamed from: x */
    public boolean f2391x;

    /* renamed from: y */
    public boolean f2392y;

    /* renamed from: z */
    public boolean f2393z;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0432a();

        /* renamed from: b */
        public int f2394b;

        /* renamed from: c */
        public int f2395c;

        /* renamed from: d */
        public boolean f2396d;

        /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$SavedState$a */
        public static class C0432a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState() {
        }

        /* renamed from: a */
        public boolean m2324a() {
            return this.f2394b >= 0;
        }

        /* renamed from: b */
        public void m2325b() {
            this.f2394b = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            parcel.writeInt(this.f2394b);
            parcel.writeInt(this.f2395c);
            parcel.writeInt(this.f2396d ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f2394b = parcel.readInt();
            this.f2395c = parcel.readInt();
            this.f2396d = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f2394b = savedState.f2394b;
            this.f2395c = savedState.f2395c;
            this.f2396d = savedState.f2396d;
        }
    }

    /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$a */
    public static class C0433a {

        /* renamed from: a */
        public AbstractC0483l f2397a;

        /* renamed from: b */
        public int f2398b;

        /* renamed from: c */
        public int f2399c;

        /* renamed from: d */
        public boolean f2400d;

        /* renamed from: e */
        public boolean f2401e;

        public C0433a() {
            m2332e();
        }

        /* renamed from: a */
        public void m2328a() {
            this.f2399c = this.f2400d ? this.f2397a.mo2873i() : this.f2397a.mo2877m();
        }

        /* renamed from: b */
        public void m2329b(View view, int i9) {
            if (this.f2400d) {
                this.f2399c = this.f2397a.mo2868d(view) + this.f2397a.m2879o();
            } else {
                this.f2399c = this.f2397a.mo2871g(view);
            }
            this.f2398b = i9;
        }

        /* renamed from: c */
        public void m2330c(View view, int i9) {
            int iM2879o = this.f2397a.m2879o();
            if (iM2879o >= 0) {
                m2329b(view, i9);
                return;
            }
            this.f2398b = i9;
            if (this.f2400d) {
                int iMo2873i = (this.f2397a.mo2873i() - iM2879o) - this.f2397a.mo2868d(view);
                this.f2399c = this.f2397a.mo2873i() - iMo2873i;
                if (iMo2873i > 0) {
                    int iMo2869e = this.f2399c - this.f2397a.mo2869e(view);
                    int iMo2877m = this.f2397a.mo2877m();
                    int iMin = iMo2869e - (iMo2877m + Math.min(this.f2397a.mo2871g(view) - iMo2877m, 0));
                    if (iMin < 0) {
                        this.f2399c += Math.min(iMo2873i, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iMo2871g = this.f2397a.mo2871g(view);
            int iMo2877m2 = iMo2871g - this.f2397a.mo2877m();
            this.f2399c = iMo2871g;
            if (iMo2877m2 > 0) {
                int iMo2873i2 = (this.f2397a.mo2873i() - Math.min(0, (this.f2397a.mo2873i() - iM2879o) - this.f2397a.mo2868d(view))) - (iMo2871g + this.f2397a.mo2869e(view));
                if (iMo2873i2 < 0) {
                    this.f2399c -= Math.min(iMo2877m2, -iMo2873i2);
                }
            }
        }

        /* renamed from: d */
        public boolean m2331d(View view, RecyclerView.C0465z c0465z) {
            RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
            return !c0455p.m2535c() && c0455p.m2533a() >= 0 && c0455p.m2533a() < c0465z.m2620b();
        }

        /* renamed from: e */
        public void m2332e() {
            this.f2398b = -1;
            this.f2399c = Integer.MIN_VALUE;
            this.f2400d = false;
            this.f2401e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f2398b + ", mCoordinate=" + this.f2399c + ", mLayoutFromEnd=" + this.f2400d + ", mValid=" + this.f2401e + '}';
        }
    }

    /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$b */
    public static class C0434b {

        /* renamed from: a */
        public int f2402a;

        /* renamed from: b */
        public boolean f2403b;

        /* renamed from: c */
        public boolean f2404c;

        /* renamed from: d */
        public boolean f2405d;

        /* renamed from: a */
        public void m2333a() {
            this.f2402a = 0;
            this.f2403b = false;
            this.f2404c = false;
            this.f2405d = false;
        }
    }

    /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$c */
    public static class C0435c {

        /* renamed from: b */
        public int f2407b;

        /* renamed from: c */
        public int f2408c;

        /* renamed from: d */
        public int f2409d;

        /* renamed from: e */
        public int f2410e;

        /* renamed from: f */
        public int f2411f;

        /* renamed from: g */
        public int f2412g;

        /* renamed from: j */
        public int f2415j;

        /* renamed from: l */
        public boolean f2417l;

        /* renamed from: a */
        public boolean f2406a = true;

        /* renamed from: h */
        public int f2413h = 0;

        /* renamed from: i */
        public boolean f2414i = false;

        /* renamed from: k */
        public List<RecyclerView.AbstractC0442c0> f2416k = null;

        /* renamed from: a */
        public void m2334a() {
            m2335b(null);
        }

        /* renamed from: b */
        public void m2335b(View view) {
            View viewM2339f = m2339f(view);
            if (viewM2339f == null) {
                this.f2409d = -1;
            } else {
                this.f2409d = ((RecyclerView.C0455p) viewM2339f.getLayoutParams()).m2533a();
            }
        }

        /* renamed from: c */
        public boolean m2336c(RecyclerView.C0465z c0465z) {
            int i9 = this.f2409d;
            return i9 >= 0 && i9 < c0465z.m2620b();
        }

        /* renamed from: d */
        public View m2337d(RecyclerView.C0461v c0461v) {
            if (this.f2416k != null) {
                return m2338e();
            }
            View viewM2583o = c0461v.m2583o(this.f2409d);
            this.f2409d += this.f2410e;
            return viewM2583o;
        }

        /* renamed from: e */
        public final View m2338e() {
            int size = this.f2416k.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = this.f2416k.get(i9).itemView;
                RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
                if (!c0455p.m2535c() && this.f2409d == c0455p.m2533a()) {
                    m2335b(view);
                    return view;
                }
            }
            return null;
        }

        /* renamed from: f */
        public View m2339f(View view) {
            int iM2533a;
            int size = this.f2416k.size();
            View view2 = null;
            int i9 = Integer.MAX_VALUE;
            for (int i10 = 0; i10 < size; i10++) {
                View view3 = this.f2416k.get(i10).itemView;
                RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view3.getLayoutParams();
                if (view3 != view && !c0455p.m2535c() && (iM2533a = (c0455p.m2533a() - this.f2409d) * this.f2410e) >= 0 && iM2533a < i9) {
                    view2 = view3;
                    if (iM2533a == 0) {
                        break;
                    }
                    i9 = iM2533a;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    /* renamed from: A2 */
    public void m2256A2(int i9) {
        if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i9);
        }
        mo2293g(null);
        if (i9 != this.f2386s || this.f2388u == null) {
            AbstractC0483l abstractC0483lM2866b = AbstractC0483l.m2866b(this, i9);
            this.f2388u = abstractC0483lM2866b;
            this.f2383E.f2397a = abstractC0483lM2866b;
            this.f2386s = i9;
            m2512s1();
        }
    }

    /* renamed from: B2 */
    public void m2257B2(boolean z8) {
        mo2293g(null);
        if (z8 == this.f2390w) {
            return;
        }
        this.f2390w = z8;
        m2512s1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: C */
    public View mo2258C(int i9) {
        int iM2448J = m2448J();
        if (iM2448J == 0) {
            return null;
        }
        int iM2487g0 = i9 - m2487g0(m2446I(0));
        if (iM2487g0 >= 0 && iM2487g0 < iM2448J) {
            View viewM2446I = m2446I(iM2487g0);
            if (m2487g0(viewM2446I) == i9) {
                return viewM2446I;
            }
        }
        return super.mo2258C(i9);
    }

    /* renamed from: C2 */
    public void mo2211C2(boolean z8) {
        mo2293g(null);
        if (this.f2392y == z8) {
            return;
        }
        this.f2392y = z8;
        m2512s1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: D */
    public RecyclerView.C0455p mo2212D() {
        return new RecyclerView.C0455p(-2, -2);
    }

    /* renamed from: D2 */
    public final boolean m2259D2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, C0433a c0433a) {
        if (m2448J() == 0) {
            return false;
        }
        View viewM2465V = m2465V();
        if (viewM2465V != null && c0433a.m2331d(viewM2465V, c0465z)) {
            c0433a.m2330c(viewM2465V, m2487g0(viewM2465V));
            return true;
        }
        if (this.f2389v != this.f2392y) {
            return false;
        }
        View viewM2294g2 = c0433a.f2400d ? m2294g2(c0461v, c0465z) : m2295h2(c0461v, c0465z);
        if (viewM2294g2 == null) {
            return false;
        }
        c0433a.m2329b(viewM2294g2, m2487g0(viewM2294g2));
        if (!c0465z.m2623e() && mo2216K1()) {
            if (this.f2388u.mo2871g(viewM2294g2) >= this.f2388u.mo2873i() || this.f2388u.mo2868d(viewM2294g2) < this.f2388u.mo2877m()) {
                c0433a.f2399c = c0433a.f2400d ? this.f2388u.mo2873i() : this.f2388u.mo2877m();
            }
        }
        return true;
    }

    /* renamed from: E2 */
    public final boolean m2260E2(RecyclerView.C0465z c0465z, C0433a c0433a) {
        int i9;
        if (!c0465z.m2623e() && (i9 = this.f2379A) != -1) {
            if (i9 >= 0 && i9 < c0465z.m2620b()) {
                c0433a.f2398b = this.f2379A;
                SavedState savedState = this.f2382D;
                if (savedState != null && savedState.m2324a()) {
                    boolean z8 = this.f2382D.f2396d;
                    c0433a.f2400d = z8;
                    if (z8) {
                        c0433a.f2399c = this.f2388u.mo2873i() - this.f2382D.f2395c;
                    } else {
                        c0433a.f2399c = this.f2388u.mo2877m() + this.f2382D.f2395c;
                    }
                    return true;
                }
                if (this.f2380B != Integer.MIN_VALUE) {
                    boolean z9 = this.f2391x;
                    c0433a.f2400d = z9;
                    if (z9) {
                        c0433a.f2399c = this.f2388u.mo2873i() - this.f2380B;
                    } else {
                        c0433a.f2399c = this.f2388u.mo2877m() + this.f2380B;
                    }
                    return true;
                }
                View viewMo2258C = mo2258C(this.f2379A);
                if (viewMo2258C == null) {
                    if (m2448J() > 0) {
                        c0433a.f2400d = (this.f2379A < m2487g0(m2446I(0))) == this.f2391x;
                    }
                    c0433a.m2328a();
                } else {
                    if (this.f2388u.mo2869e(viewMo2258C) > this.f2388u.mo2878n()) {
                        c0433a.m2328a();
                        return true;
                    }
                    if (this.f2388u.mo2871g(viewMo2258C) - this.f2388u.mo2877m() < 0) {
                        c0433a.f2399c = this.f2388u.mo2877m();
                        c0433a.f2400d = false;
                        return true;
                    }
                    if (this.f2388u.mo2873i() - this.f2388u.mo2868d(viewMo2258C) < 0) {
                        c0433a.f2399c = this.f2388u.mo2873i();
                        c0433a.f2400d = true;
                        return true;
                    }
                    c0433a.f2399c = c0433a.f2400d ? this.f2388u.mo2868d(viewMo2258C) + this.f2388u.m2879o() : this.f2388u.mo2871g(viewMo2258C);
                }
                return true;
            }
            this.f2379A = -1;
            this.f2380B = Integer.MIN_VALUE;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: F1 */
    public boolean mo2261F1() {
        return (m2467X() == 1073741824 || m2504o0() == 1073741824 || !m2506p0()) ? false : true;
    }

    /* renamed from: F2 */
    public final void m2262F2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, C0433a c0433a) {
        if (m2260E2(c0465z, c0433a) || m2259D2(c0461v, c0465z, c0433a)) {
            return;
        }
        c0433a.m2328a();
        c0433a.f2398b = this.f2392y ? c0465z.m2620b() - 1 : 0;
    }

    /* renamed from: G2 */
    public final void m2263G2(int i9, int i10, boolean z8, RecyclerView.C0465z c0465z) {
        int iMo2877m;
        this.f2387t.f2417l = m2320w2();
        this.f2387t.f2413h = m2302m2(c0465z);
        C0435c c0435c = this.f2387t;
        c0435c.f2411f = i9;
        if (i9 == 1) {
            c0435c.f2413h += this.f2388u.mo2874j();
            View viewM2299k2 = m2299k2();
            C0435c c0435c2 = this.f2387t;
            c0435c2.f2410e = this.f2391x ? -1 : 1;
            int iM2487g0 = m2487g0(viewM2299k2);
            C0435c c0435c3 = this.f2387t;
            c0435c2.f2409d = iM2487g0 + c0435c3.f2410e;
            c0435c3.f2407b = this.f2388u.mo2868d(viewM2299k2);
            iMo2877m = this.f2388u.mo2868d(viewM2299k2) - this.f2388u.mo2873i();
        } else {
            View viewM2301l2 = m2301l2();
            this.f2387t.f2413h += this.f2388u.mo2877m();
            C0435c c0435c4 = this.f2387t;
            c0435c4.f2410e = this.f2391x ? 1 : -1;
            int iM2487g02 = m2487g0(viewM2301l2);
            C0435c c0435c5 = this.f2387t;
            c0435c4.f2409d = iM2487g02 + c0435c5.f2410e;
            c0435c5.f2407b = this.f2388u.mo2871g(viewM2301l2);
            iMo2877m = (-this.f2388u.mo2871g(viewM2301l2)) + this.f2388u.mo2877m();
        }
        C0435c c0435c6 = this.f2387t;
        c0435c6.f2408c = i10;
        if (z8) {
            c0435c6.f2408c = i10 - iMo2877m;
        }
        c0435c6.f2412g = iMo2877m;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: H0 */
    public void mo2264H0(RecyclerView recyclerView, RecyclerView.C0461v c0461v) {
        super.mo2264H0(recyclerView, c0461v);
        if (this.f2381C) {
            m2495j1(c0461v);
            c0461v.m2571c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: H1 */
    public void mo2265H1(RecyclerView recyclerView, RecyclerView.C0465z c0465z, int i9) {
        C0480i c0480i = new C0480i(recyclerView.getContext());
        c0480i.m2611p(i9);
        m2447I1(c0480i);
    }

    /* renamed from: H2 */
    public final void m2266H2(int i9, int i10) {
        this.f2387t.f2408c = this.f2388u.mo2873i() - i10;
        C0435c c0435c = this.f2387t;
        c0435c.f2410e = this.f2391x ? -1 : 1;
        c0435c.f2409d = i9;
        c0435c.f2411f = 1;
        c0435c.f2407b = i10;
        c0435c.f2412g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: I0 */
    public View mo2215I0(View view, int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        int iM2274P1;
        m2321x2();
        if (m2448J() == 0 || (iM2274P1 = m2274P1(i9)) == Integer.MIN_VALUE) {
            return null;
        }
        m2276R1();
        m2276R1();
        m2263G2(iM2274P1, (int) (this.f2388u.mo2878n() * 0.33333334f), false, c0465z);
        C0435c c0435c = this.f2387t;
        c0435c.f2412g = Integer.MIN_VALUE;
        c0435c.f2406a = false;
        m2277S1(c0461v, c0435c, c0465z, true);
        View viewM2292e2 = iM2274P1 == -1 ? m2292e2(c0461v, c0465z) : m2291d2(c0461v, c0465z);
        View viewM2301l2 = iM2274P1 == -1 ? m2301l2() : m2299k2();
        if (!viewM2301l2.hasFocusable()) {
            return viewM2292e2;
        }
        if (viewM2292e2 == null) {
            return null;
        }
        return viewM2301l2;
    }

    /* renamed from: I2 */
    public final void m2267I2(C0433a c0433a) {
        m2266H2(c0433a.f2398b, c0433a.f2399c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: J0 */
    public void mo2268J0(AccessibilityEvent accessibilityEvent) {
        super.mo2268J0(accessibilityEvent);
        if (m2448J() > 0) {
            accessibilityEvent.setFromIndex(m2282X1());
            accessibilityEvent.setToIndex(m2286a2());
        }
    }

    /* renamed from: J2 */
    public final void m2269J2(int i9, int i10) {
        this.f2387t.f2408c = i10 - this.f2388u.mo2877m();
        C0435c c0435c = this.f2387t;
        c0435c.f2409d = i9;
        c0435c.f2410e = this.f2391x ? 1 : -1;
        c0435c.f2411f = -1;
        c0435c.f2407b = i10;
        c0435c.f2412g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: K1 */
    public boolean mo2216K1() {
        return this.f2382D == null && this.f2389v == this.f2392y;
    }

    /* renamed from: K2 */
    public final void m2270K2(C0433a c0433a) {
        m2269J2(c0433a.f2398b, c0433a.f2399c);
    }

    /* renamed from: L1 */
    public void mo2217L1(RecyclerView.C0465z c0465z, C0435c c0435c, RecyclerView.AbstractC0454o.c cVar) {
        int i9 = c0435c.f2409d;
        if (i9 < 0 || i9 >= c0465z.m2620b()) {
            return;
        }
        cVar.mo2532a(i9, Math.max(0, c0435c.f2412g));
    }

    /* renamed from: M1 */
    public final int m2271M1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        m2276R1();
        return C0485n.m2886a(c0465z, this.f2388u, m2281W1(!this.f2393z, true), m2280V1(!this.f2393z, true), this, this.f2393z);
    }

    /* renamed from: N1 */
    public final int m2272N1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        m2276R1();
        return C0485n.m2887b(c0465z, this.f2388u, m2281W1(!this.f2393z, true), m2280V1(!this.f2393z, true), this, this.f2393z, this.f2391x);
    }

    /* renamed from: O1 */
    public final int m2273O1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        m2276R1();
        return C0485n.m2888c(c0465z, this.f2388u, m2281W1(!this.f2393z, true), m2280V1(!this.f2393z, true), this, this.f2393z);
    }

    /* renamed from: P1 */
    public int m2274P1(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 17 ? i9 != 33 ? i9 != 66 ? (i9 == 130 && this.f2386s == 1) ? 1 : Integer.MIN_VALUE : this.f2386s == 0 ? 1 : Integer.MIN_VALUE : this.f2386s == 1 ? -1 : Integer.MIN_VALUE : this.f2386s == 0 ? -1 : Integer.MIN_VALUE : (this.f2386s != 1 && m2305o2()) ? -1 : 1 : (this.f2386s != 1 && m2305o2()) ? 1 : -1;
    }

    /* renamed from: Q1 */
    public C0435c m2275Q1() {
        return new C0435c();
    }

    /* renamed from: R1 */
    public void m2276R1() {
        if (this.f2387t == null) {
            this.f2387t = m2275Q1();
        }
    }

    /* renamed from: S1 */
    public int m2277S1(RecyclerView.C0461v c0461v, C0435c c0435c, RecyclerView.C0465z c0465z, boolean z8) {
        int i9 = c0435c.f2408c;
        int i10 = c0435c.f2412g;
        if (i10 != Integer.MIN_VALUE) {
            if (i9 < 0) {
                c0435c.f2412g = i10 + i9;
            }
            m2312s2(c0461v, c0435c);
        }
        int i11 = c0435c.f2408c + c0435c.f2413h;
        C0434b c0434b = this.f2384F;
        while (true) {
            if ((!c0435c.f2417l && i11 <= 0) || !c0435c.m2336c(c0465z)) {
                break;
            }
            c0434b.m2333a();
            mo2245p2(c0461v, c0465z, c0435c, c0434b);
            if (!c0434b.f2403b) {
                c0435c.f2407b += c0434b.f2402a * c0435c.f2411f;
                if (!c0434b.f2404c || this.f2387t.f2416k != null || !c0465z.m2623e()) {
                    int i12 = c0435c.f2408c;
                    int i13 = c0434b.f2402a;
                    c0435c.f2408c = i12 - i13;
                    i11 -= i13;
                }
                int i14 = c0435c.f2412g;
                if (i14 != Integer.MIN_VALUE) {
                    int i15 = i14 + c0434b.f2402a;
                    c0435c.f2412g = i15;
                    int i16 = c0435c.f2408c;
                    if (i16 < 0) {
                        c0435c.f2412g = i15 + i16;
                    }
                    m2312s2(c0461v, c0435c);
                }
                if (z8 && c0434b.f2405d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i9 - c0435c.f2408c;
    }

    /* renamed from: T1 */
    public final View m2278T1(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return m2288b2(0, m2448J());
    }

    /* renamed from: U1 */
    public final View m2279U1(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return mo2242f2(c0461v, c0465z, 0, m2448J(), c0465z.m2620b());
    }

    /* renamed from: V1 */
    public final View m2280V1(boolean z8, boolean z9) {
        return this.f2391x ? m2290c2(0, m2448J(), z8, z9) : m2290c2(m2448J() - 1, -1, z8, z9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: W0 */
    public void mo2235W0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iM2296i2;
        int i14;
        View viewMo2258C;
        int iMo2871g;
        int iMo2873i;
        int i15 = -1;
        if (!(this.f2382D == null && this.f2379A == -1) && c0465z.m2620b() == 0) {
            m2495j1(c0461v);
            return;
        }
        SavedState savedState = this.f2382D;
        if (savedState != null && savedState.m2324a()) {
            this.f2379A = this.f2382D.f2394b;
        }
        m2276R1();
        this.f2387t.f2406a = false;
        m2321x2();
        View viewM2465V = m2465V();
        C0433a c0433a = this.f2383E;
        if (!c0433a.f2401e || this.f2379A != -1 || this.f2382D != null) {
            c0433a.m2332e();
            C0433a c0433a2 = this.f2383E;
            c0433a2.f2400d = this.f2391x ^ this.f2392y;
            m2262F2(c0461v, c0465z, c0433a2);
            this.f2383E.f2401e = true;
        } else if (viewM2465V != null && (this.f2388u.mo2871g(viewM2465V) >= this.f2388u.mo2873i() || this.f2388u.mo2868d(viewM2465V) <= this.f2388u.mo2877m())) {
            this.f2383E.m2330c(viewM2465V, m2487g0(viewM2465V));
        }
        int iM2302m2 = m2302m2(c0465z);
        if (this.f2387t.f2415j >= 0) {
            i9 = iM2302m2;
            iM2302m2 = 0;
        } else {
            i9 = 0;
        }
        int iMo2877m = iM2302m2 + this.f2388u.mo2877m();
        int iMo2874j = i9 + this.f2388u.mo2874j();
        if (c0465z.m2623e() && (i14 = this.f2379A) != -1 && this.f2380B != Integer.MIN_VALUE && (viewMo2258C = mo2258C(i14)) != null) {
            if (this.f2391x) {
                iMo2873i = this.f2388u.mo2873i() - this.f2388u.mo2868d(viewMo2258C);
                iMo2871g = this.f2380B;
            } else {
                iMo2871g = this.f2388u.mo2871g(viewMo2258C) - this.f2388u.mo2877m();
                iMo2873i = this.f2380B;
            }
            int i16 = iMo2873i - iMo2871g;
            if (i16 > 0) {
                iMo2877m += i16;
            } else {
                iMo2874j -= i16;
            }
        }
        C0433a c0433a3 = this.f2383E;
        if (!c0433a3.f2400d ? !this.f2391x : this.f2391x) {
            i15 = 1;
        }
        mo2246r2(c0461v, c0465z, c0433a3, i15);
        m2517w(c0461v);
        this.f2387t.f2417l = m2320w2();
        this.f2387t.f2414i = c0465z.m2623e();
        C0433a c0433a4 = this.f2383E;
        if (c0433a4.f2400d) {
            m2270K2(c0433a4);
            C0435c c0435c = this.f2387t;
            c0435c.f2413h = iMo2877m;
            m2277S1(c0461v, c0435c, c0465z, false);
            C0435c c0435c2 = this.f2387t;
            i11 = c0435c2.f2407b;
            int i17 = c0435c2.f2409d;
            int i18 = c0435c2.f2408c;
            if (i18 > 0) {
                iMo2874j += i18;
            }
            m2267I2(this.f2383E);
            C0435c c0435c3 = this.f2387t;
            c0435c3.f2413h = iMo2874j;
            c0435c3.f2409d += c0435c3.f2410e;
            m2277S1(c0461v, c0435c3, c0465z, false);
            C0435c c0435c4 = this.f2387t;
            i10 = c0435c4.f2407b;
            int i19 = c0435c4.f2408c;
            if (i19 > 0) {
                m2269J2(i17, i11);
                C0435c c0435c5 = this.f2387t;
                c0435c5.f2413h = i19;
                m2277S1(c0461v, c0435c5, c0465z, false);
                i11 = this.f2387t.f2407b;
            }
        } else {
            m2267I2(c0433a4);
            C0435c c0435c6 = this.f2387t;
            c0435c6.f2413h = iMo2874j;
            m2277S1(c0461v, c0435c6, c0465z, false);
            C0435c c0435c7 = this.f2387t;
            i10 = c0435c7.f2407b;
            int i20 = c0435c7.f2409d;
            int i21 = c0435c7.f2408c;
            if (i21 > 0) {
                iMo2877m += i21;
            }
            m2270K2(this.f2383E);
            C0435c c0435c8 = this.f2387t;
            c0435c8.f2413h = iMo2877m;
            c0435c8.f2409d += c0435c8.f2410e;
            m2277S1(c0461v, c0435c8, c0465z, false);
            C0435c c0435c9 = this.f2387t;
            i11 = c0435c9.f2407b;
            int i22 = c0435c9.f2408c;
            if (i22 > 0) {
                m2266H2(i20, i10);
                C0435c c0435c10 = this.f2387t;
                c0435c10.f2413h = i22;
                m2277S1(c0461v, c0435c10, c0465z, false);
                i10 = this.f2387t.f2407b;
            }
        }
        if (m2448J() > 0) {
            if (this.f2391x ^ this.f2392y) {
                int iM2296i22 = m2296i2(i10, c0461v, c0465z, true);
                i12 = i11 + iM2296i22;
                i13 = i10 + iM2296i22;
                iM2296i2 = m2297j2(i12, c0461v, c0465z, false);
            } else {
                int iM2297j2 = m2297j2(i11, c0461v, c0465z, true);
                i12 = i11 + iM2297j2;
                i13 = i10 + iM2297j2;
                iM2296i2 = m2296i2(i13, c0461v, c0465z, false);
            }
            i11 = i12 + iM2296i2;
            i10 = i13 + iM2296i2;
        }
        m2308q2(c0461v, c0465z, i11, i10);
        if (c0465z.m2623e()) {
            this.f2383E.m2332e();
        } else {
            this.f2388u.m2883s();
        }
        this.f2389v = this.f2392y;
    }

    /* renamed from: W1 */
    public final View m2281W1(boolean z8, boolean z9) {
        return this.f2391x ? m2290c2(m2448J() - 1, -1, z8, z9) : m2290c2(0, m2448J(), z8, z9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: X0 */
    public void mo2237X0(RecyclerView.C0465z c0465z) {
        super.mo2237X0(c0465z);
        this.f2382D = null;
        this.f2379A = -1;
        this.f2380B = Integer.MIN_VALUE;
        this.f2383E.m2332e();
    }

    /* renamed from: X1 */
    public int m2282X1() {
        View viewM2290c2 = m2290c2(0, m2448J(), false, true);
        if (viewM2290c2 == null) {
            return -1;
        }
        return m2487g0(viewM2290c2);
    }

    /* renamed from: Y1 */
    public final View m2283Y1(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return m2288b2(m2448J() - 1, -1);
    }

    /* renamed from: Z1 */
    public final View m2284Z1(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return mo2242f2(c0461v, c0465z, m2448J() - 1, -1, c0465z.m2620b());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y.b
    /* renamed from: a */
    public PointF mo2285a(int i9) {
        if (m2448J() == 0) {
            return null;
        }
        int i10 = (i9 < m2487g0(m2446I(0))) != this.f2391x ? -1 : 1;
        return this.f2386s == 0 ? new PointF(i10, BitmapDescriptorFactory.HUE_RED) : new PointF(BitmapDescriptorFactory.HUE_RED, i10);
    }

    /* renamed from: a2 */
    public int m2286a2() {
        View viewM2290c2 = m2290c2(m2448J() - 1, -1, false, true);
        if (viewM2290c2 == null) {
            return -1;
        }
        return m2487g0(viewM2290c2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: b1 */
    public void mo2287b1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f2382D = (SavedState) parcelable;
            m2512s1();
        }
    }

    /* renamed from: b2 */
    public View m2288b2(int i9, int i10) {
        int i11;
        int i12;
        m2276R1();
        if ((i10 > i9 ? (char) 1 : i10 < i9 ? (char) 65535 : (char) 0) == 0) {
            return m2446I(i9);
        }
        if (this.f2388u.mo2871g(m2446I(i9)) < this.f2388u.mo2877m()) {
            i11 = 16644;
            i12 = 16388;
        } else {
            i11 = 4161;
            i12 = 4097;
        }
        return this.f2386s == 0 ? this.f2446e.m2935a(i9, i10, i11, i12) : this.f2447f.m2935a(i9, i10, i11, i12);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: c1 */
    public Parcelable mo2289c1() {
        if (this.f2382D != null) {
            return new SavedState(this.f2382D);
        }
        SavedState savedState = new SavedState();
        if (m2448J() > 0) {
            m2276R1();
            boolean z8 = this.f2389v ^ this.f2391x;
            savedState.f2396d = z8;
            if (z8) {
                View viewM2299k2 = m2299k2();
                savedState.f2395c = this.f2388u.mo2873i() - this.f2388u.mo2868d(viewM2299k2);
                savedState.f2394b = m2487g0(viewM2299k2);
            } else {
                View viewM2301l2 = m2301l2();
                savedState.f2394b = m2487g0(viewM2301l2);
                savedState.f2395c = this.f2388u.mo2871g(viewM2301l2) - this.f2388u.mo2877m();
            }
        } else {
            savedState.m2325b();
        }
        return savedState;
    }

    /* renamed from: c2 */
    public View m2290c2(int i9, int i10, boolean z8, boolean z9) {
        m2276R1();
        int i11 = z8 ? 24579 : 320;
        int i12 = z9 ? 320 : 0;
        return this.f2386s == 0 ? this.f2446e.m2935a(i9, i10, i11, i12) : this.f2447f.m2935a(i9, i10, i11, i12);
    }

    /* renamed from: d2 */
    public final View m2291d2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2391x ? m2278T1(c0461v, c0465z) : m2283Y1(c0461v, c0465z);
    }

    /* renamed from: e2 */
    public final View m2292e2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2391x ? m2283Y1(c0461v, c0465z) : m2278T1(c0461v, c0465z);
    }

    /* renamed from: f2 */
    public View mo2242f2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9, int i10, int i11) {
        m2276R1();
        int iMo2877m = this.f2388u.mo2877m();
        int iMo2873i = this.f2388u.mo2873i();
        int i12 = i10 > i9 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i9 != i10) {
            View viewM2446I = m2446I(i9);
            int iM2487g0 = m2487g0(viewM2446I);
            if (iM2487g0 >= 0 && iM2487g0 < i11) {
                if (((RecyclerView.C0455p) viewM2446I.getLayoutParams()).m2535c()) {
                    if (view2 == null) {
                        view2 = viewM2446I;
                    }
                } else {
                    if (this.f2388u.mo2871g(viewM2446I) < iMo2873i && this.f2388u.mo2868d(viewM2446I) >= iMo2877m) {
                        return viewM2446I;
                    }
                    if (view == null) {
                        view = viewM2446I;
                    }
                }
            }
            i9 += i12;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: g */
    public void mo2293g(String str) {
        if (this.f2382D == null) {
            super.mo2293g(str);
        }
    }

    /* renamed from: g2 */
    public final View m2294g2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2391x ? m2279U1(c0461v, c0465z) : m2284Z1(c0461v, c0465z);
    }

    /* renamed from: h2 */
    public final View m2295h2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2391x ? m2284Z1(c0461v, c0465z) : m2279U1(c0461v, c0465z);
    }

    /* renamed from: i2 */
    public final int m2296i2(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, boolean z8) {
        int iMo2873i;
        int iMo2873i2 = this.f2388u.mo2873i() - i9;
        if (iMo2873i2 <= 0) {
            return 0;
        }
        int i10 = -m2322y2(-iMo2873i2, c0461v, c0465z);
        int i11 = i9 + i10;
        if (!z8 || (iMo2873i = this.f2388u.mo2873i() - i11) <= 0) {
            return i10;
        }
        this.f2388u.mo2882r(iMo2873i);
        return iMo2873i + i10;
    }

    /* renamed from: j2 */
    public final int m2297j2(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, boolean z8) {
        int iMo2877m;
        int iMo2877m2 = i9 - this.f2388u.mo2877m();
        if (iMo2877m2 <= 0) {
            return 0;
        }
        int i10 = -m2322y2(iMo2877m2, c0461v, c0465z);
        int i11 = i9 + i10;
        if (!z8 || (iMo2877m = i11 - this.f2388u.mo2877m()) <= 0) {
            return i10;
        }
        this.f2388u.mo2882r(-iMo2877m);
        return i10 - iMo2877m;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: k */
    public boolean mo2298k() {
        return this.f2386s == 0;
    }

    /* renamed from: k2 */
    public final View m2299k2() {
        return m2446I(this.f2391x ? 0 : m2448J() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: l */
    public boolean mo2300l() {
        return this.f2386s == 1;
    }

    /* renamed from: l2 */
    public final View m2301l2() {
        return m2446I(this.f2391x ? m2448J() - 1 : 0);
    }

    /* renamed from: m2 */
    public int m2302m2(RecyclerView.C0465z c0465z) {
        if (c0465z.m2622d()) {
            return this.f2388u.mo2878n();
        }
        return 0;
    }

    /* renamed from: n2 */
    public int m2303n2() {
        return this.f2386s;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: o */
    public void mo2304o(int i9, int i10, RecyclerView.C0465z c0465z, RecyclerView.AbstractC0454o.c cVar) {
        if (this.f2386s != 0) {
            i9 = i10;
        }
        if (m2448J() == 0 || i9 == 0) {
            return;
        }
        m2276R1();
        m2263G2(i9 > 0 ? 1 : -1, Math.abs(i9), true, c0465z);
        mo2217L1(c0465z, this.f2387t, cVar);
    }

    /* renamed from: o2 */
    public boolean m2305o2() {
        return m2468Y() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: p */
    public void mo2306p(int i9, RecyclerView.AbstractC0454o.c cVar) {
        boolean z8;
        int i10;
        SavedState savedState = this.f2382D;
        if (savedState == null || !savedState.m2324a()) {
            m2321x2();
            z8 = this.f2391x;
            i10 = this.f2379A;
            if (i10 == -1) {
                i10 = z8 ? i9 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.f2382D;
            z8 = savedState2.f2396d;
            i10 = savedState2.f2394b;
        }
        int i11 = z8 ? -1 : 1;
        for (int i12 = 0; i12 < this.f2385G && i10 >= 0 && i10 < i9; i12++) {
            cVar.mo2532a(i10, 0);
            i10 += i11;
        }
    }

    /* renamed from: p2 */
    public void mo2245p2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, C0435c c0435c, C0434b c0434b) {
        int i9;
        int i10;
        int i11;
        int iM2479d0;
        int iMo2870f;
        View viewM2337d = c0435c.m2337d(c0461v);
        if (viewM2337d == null) {
            c0434b.f2403b = true;
            return;
        }
        RecyclerView.C0455p c0455p = (RecyclerView.C0455p) viewM2337d.getLayoutParams();
        if (c0435c.f2416k == null) {
            if (this.f2391x == (c0435c.f2411f == -1)) {
                m2478d(viewM2337d);
            } else {
                m2481e(viewM2337d, 0);
            }
        } else {
            if (this.f2391x == (c0435c.f2411f == -1)) {
                m2474b(viewM2337d);
            } else {
                m2476c(viewM2337d, 0);
            }
        }
        m2525z0(viewM2337d, 0, 0);
        c0434b.f2402a = this.f2388u.mo2869e(viewM2337d);
        if (this.f2386s == 1) {
            if (m2305o2()) {
                iMo2870f = m2502n0() - m2482e0();
                iM2479d0 = iMo2870f - this.f2388u.mo2870f(viewM2337d);
            } else {
                iM2479d0 = m2479d0();
                iMo2870f = this.f2388u.mo2870f(viewM2337d) + iM2479d0;
            }
            if (c0435c.f2411f == -1) {
                int i12 = c0435c.f2407b;
                i11 = i12;
                i10 = iMo2870f;
                i9 = i12 - c0434b.f2402a;
            } else {
                int i13 = c0435c.f2407b;
                i9 = i13;
                i10 = iMo2870f;
                i11 = c0434b.f2402a + i13;
            }
        } else {
            int iM2485f0 = m2485f0();
            int iMo2870f2 = this.f2388u.mo2870f(viewM2337d) + iM2485f0;
            if (c0435c.f2411f == -1) {
                int i14 = c0435c.f2407b;
                i10 = i14;
                i9 = iM2485f0;
                i11 = iMo2870f2;
                iM2479d0 = i14 - c0434b.f2402a;
            } else {
                int i15 = c0435c.f2407b;
                i9 = iM2485f0;
                i10 = c0434b.f2402a + i15;
                i11 = iMo2870f2;
                iM2479d0 = i15;
            }
        }
        m2522y0(viewM2337d, iM2479d0, i9, i10, i11);
        if (c0455p.m2535c() || c0455p.m2534b()) {
            c0434b.f2404c = true;
        }
        c0434b.f2405d = viewM2337d.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: q */
    public int mo2307q(RecyclerView.C0465z c0465z) {
        return m2271M1(c0465z);
    }

    /* renamed from: q2 */
    public final void m2308q2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9, int i10) {
        if (!c0465z.m2625g() || m2448J() == 0 || c0465z.m2623e() || !mo2216K1()) {
            return;
        }
        List<RecyclerView.AbstractC0442c0> listM2579k = c0461v.m2579k();
        int size = listM2579k.size();
        int iM2487g0 = m2487g0(m2446I(0));
        int iMo2869e = 0;
        int iMo2869e2 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            RecyclerView.AbstractC0442c0 abstractC0442c0 = listM2579k.get(i11);
            if (!abstractC0442c0.isRemoved()) {
                if (((abstractC0442c0.getLayoutPosition() < iM2487g0) != this.f2391x ? (char) 65535 : (char) 1) == 65535) {
                    iMo2869e += this.f2388u.mo2869e(abstractC0442c0.itemView);
                } else {
                    iMo2869e2 += this.f2388u.mo2869e(abstractC0442c0.itemView);
                }
            }
        }
        this.f2387t.f2416k = listM2579k;
        if (iMo2869e > 0) {
            m2269J2(m2487g0(m2301l2()), i9);
            C0435c c0435c = this.f2387t;
            c0435c.f2413h = iMo2869e;
            c0435c.f2408c = 0;
            c0435c.m2334a();
            m2277S1(c0461v, this.f2387t, c0465z, false);
        }
        if (iMo2869e2 > 0) {
            m2266H2(m2487g0(m2299k2()), i10);
            C0435c c0435c2 = this.f2387t;
            c0435c2.f2413h = iMo2869e2;
            c0435c2.f2408c = 0;
            c0435c2.m2334a();
            m2277S1(c0461v, this.f2387t, c0465z, false);
        }
        this.f2387t.f2416k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: r */
    public int mo2309r(RecyclerView.C0465z c0465z) {
        return m2272N1(c0465z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: r0 */
    public boolean mo2310r0() {
        return true;
    }

    /* renamed from: r2 */
    public void mo2246r2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, C0433a c0433a, int i9) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: s */
    public int mo2311s(RecyclerView.C0465z c0465z) {
        return m2273O1(c0465z);
    }

    /* renamed from: s2 */
    public final void m2312s2(RecyclerView.C0461v c0461v, C0435c c0435c) {
        if (!c0435c.f2406a || c0435c.f2417l) {
            return;
        }
        if (c0435c.f2411f == -1) {
            m2316u2(c0461v, c0435c.f2412g);
        } else {
            m2318v2(c0461v, c0435c.f2412g);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: t */
    public int mo2313t(RecyclerView.C0465z c0465z) {
        return m2271M1(c0465z);
    }

    /* renamed from: t2 */
    public final void m2314t2(RecyclerView.C0461v c0461v, int i9, int i10) {
        if (i9 == i10) {
            return;
        }
        if (i10 <= i9) {
            while (i9 > i10) {
                m2501m1(i9, c0461v);
                i9--;
            }
        } else {
            for (int i11 = i10 - 1; i11 >= i9; i11--) {
                m2501m1(i11, c0461v);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: u */
    public int mo2315u(RecyclerView.C0465z c0465z) {
        return m2272N1(c0465z);
    }

    /* renamed from: u2 */
    public final void m2316u2(RecyclerView.C0461v c0461v, int i9) {
        int iM2448J = m2448J();
        if (i9 < 0) {
            return;
        }
        int iMo2872h = this.f2388u.mo2872h() - i9;
        if (this.f2391x) {
            for (int i10 = 0; i10 < iM2448J; i10++) {
                View viewM2446I = m2446I(i10);
                if (this.f2388u.mo2871g(viewM2446I) < iMo2872h || this.f2388u.mo2881q(viewM2446I) < iMo2872h) {
                    m2314t2(c0461v, 0, i10);
                    return;
                }
            }
            return;
        }
        int i11 = iM2448J - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            View viewM2446I2 = m2446I(i12);
            if (this.f2388u.mo2871g(viewM2446I2) < iMo2872h || this.f2388u.mo2881q(viewM2446I2) < iMo2872h) {
                m2314t2(c0461v, i11, i12);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: v */
    public int mo2317v(RecyclerView.C0465z c0465z) {
        return m2273O1(c0465z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: v1 */
    public int mo2247v1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (this.f2386s == 1) {
            return 0;
        }
        return m2322y2(i9, c0461v, c0465z);
    }

    /* renamed from: v2 */
    public final void m2318v2(RecyclerView.C0461v c0461v, int i9) {
        if (i9 < 0) {
            return;
        }
        int iM2448J = m2448J();
        if (!this.f2391x) {
            for (int i10 = 0; i10 < iM2448J; i10++) {
                View viewM2446I = m2446I(i10);
                if (this.f2388u.mo2868d(viewM2446I) > i9 || this.f2388u.mo2880p(viewM2446I) > i9) {
                    m2314t2(c0461v, 0, i10);
                    return;
                }
            }
            return;
        }
        int i11 = iM2448J - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            View viewM2446I2 = m2446I(i12);
            if (this.f2388u.mo2868d(viewM2446I2) > i9 || this.f2388u.mo2880p(viewM2446I2) > i9) {
                m2314t2(c0461v, i11, i12);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: w1 */
    public void mo2319w1(int i9) {
        this.f2379A = i9;
        this.f2380B = Integer.MIN_VALUE;
        SavedState savedState = this.f2382D;
        if (savedState != null) {
            savedState.m2325b();
        }
        m2512s1();
    }

    /* renamed from: w2 */
    public boolean m2320w2() {
        return this.f2388u.mo2875k() == 0 && this.f2388u.mo2872h() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: x1 */
    public int mo2248x1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (this.f2386s == 0) {
            return 0;
        }
        return m2322y2(i9, c0461v, c0465z);
    }

    /* renamed from: x2 */
    public final void m2321x2() {
        if (this.f2386s == 1 || !m2305o2()) {
            this.f2391x = this.f2390w;
        } else {
            this.f2391x = !this.f2390w;
        }
    }

    /* renamed from: y2 */
    public int m2322y2(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (m2448J() == 0 || i9 == 0) {
            return 0;
        }
        this.f2387t.f2406a = true;
        m2276R1();
        int i10 = i9 > 0 ? 1 : -1;
        int iAbs = Math.abs(i9);
        m2263G2(i10, iAbs, true, c0465z);
        C0435c c0435c = this.f2387t;
        int iM2277S1 = c0435c.f2412g + m2277S1(c0461v, c0435c, c0465z, false);
        if (iM2277S1 < 0) {
            return 0;
        }
        if (iAbs > iM2277S1) {
            i9 = i10 * iM2277S1;
        }
        this.f2388u.mo2882r(-i9);
        this.f2387t.f2415j = i9;
        return i9;
    }

    /* renamed from: z2 */
    public void m2323z2(int i9, int i10) {
        this.f2379A = i9;
        this.f2380B = i10;
        SavedState savedState = this.f2382D;
        if (savedState != null) {
            savedState.m2325b();
        }
        m2512s1();
    }

    public LinearLayoutManager(Context context, int i9, boolean z8) {
        this.f2386s = 1;
        this.f2390w = false;
        this.f2391x = false;
        this.f2392y = false;
        this.f2393z = true;
        this.f2379A = -1;
        this.f2380B = Integer.MIN_VALUE;
        this.f2382D = null;
        this.f2383E = new C0433a();
        this.f2384F = new C0434b();
        this.f2385G = 2;
        m2256A2(i9);
        m2257B2(z8);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i9, int i10) {
        this.f2386s = 1;
        this.f2390w = false;
        this.f2391x = false;
        this.f2392y = false;
        this.f2393z = true;
        this.f2379A = -1;
        this.f2380B = Integer.MIN_VALUE;
        this.f2382D = null;
        this.f2383E = new C0433a();
        this.f2384F = new C0434b();
        this.f2385G = 2;
        RecyclerView.AbstractC0454o.d dVarM2427h0 = RecyclerView.AbstractC0454o.m2427h0(context, attributeSet, i9, i10);
        m2256A2(dVarM2427h0.f2462a);
        m2257B2(dVarM2427h0.f2464c);
        mo2211C2(dVarM2427h0.f2465d);
    }
}
