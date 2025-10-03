package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import p052e0.C4704m;

/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.AbstractC0454o implements RecyclerView.AbstractC0464y.b {

    /* renamed from: B */
    public BitSet f2518B;

    /* renamed from: G */
    public boolean f2523G;

    /* renamed from: H */
    public boolean f2524H;

    /* renamed from: I */
    public SavedState f2525I;

    /* renamed from: J */
    public int f2526J;

    /* renamed from: O */
    public int[] f2531O;

    /* renamed from: t */
    public C0471d[] f2534t;

    /* renamed from: u */
    public AbstractC0483l f2535u;

    /* renamed from: v */
    public AbstractC0483l f2536v;

    /* renamed from: w */
    public int f2537w;

    /* renamed from: x */
    public int f2538x;

    /* renamed from: y */
    public final C0479h f2539y;

    /* renamed from: s */
    public int f2533s = -1;

    /* renamed from: z */
    public boolean f2540z = false;

    /* renamed from: A */
    public boolean f2517A = false;

    /* renamed from: C */
    public int f2519C = -1;

    /* renamed from: D */
    public int f2520D = Integer.MIN_VALUE;

    /* renamed from: E */
    public LazySpanLookup f2521E = new LazySpanLookup();

    /* renamed from: F */
    public int f2522F = 2;

    /* renamed from: K */
    public final Rect f2527K = new Rect();

    /* renamed from: L */
    public final C0469b f2528L = new C0469b();

    /* renamed from: M */
    public boolean f2529M = false;

    /* renamed from: N */
    public boolean f2530N = true;

    /* renamed from: P */
    public final Runnable f2532P = new RunnableC0468a();

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0467a();

        /* renamed from: b */
        public int f2547b;

        /* renamed from: c */
        public int f2548c;

        /* renamed from: d */
        public int f2549d;

        /* renamed from: e */
        public int[] f2550e;

        /* renamed from: f */
        public int f2551f;

        /* renamed from: g */
        public int[] f2552g;

        /* renamed from: h */
        public List<LazySpanLookup.FullSpanItem> f2553h;

        /* renamed from: i */
        public boolean f2554i;

        /* renamed from: j */
        public boolean f2555j;

        /* renamed from: k */
        public boolean f2556k;

        /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState$a */
        public static class C0467a implements Parcelable.Creator<SavedState> {
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
        public void m2702a() {
            this.f2550e = null;
            this.f2549d = 0;
            this.f2547b = -1;
            this.f2548c = -1;
        }

        /* renamed from: b */
        public void m2703b() {
            this.f2550e = null;
            this.f2549d = 0;
            this.f2551f = 0;
            this.f2552g = null;
            this.f2553h = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            parcel.writeInt(this.f2547b);
            parcel.writeInt(this.f2548c);
            parcel.writeInt(this.f2549d);
            if (this.f2549d > 0) {
                parcel.writeIntArray(this.f2550e);
            }
            parcel.writeInt(this.f2551f);
            if (this.f2551f > 0) {
                parcel.writeIntArray(this.f2552g);
            }
            parcel.writeInt(this.f2554i ? 1 : 0);
            parcel.writeInt(this.f2555j ? 1 : 0);
            parcel.writeInt(this.f2556k ? 1 : 0);
            parcel.writeList(this.f2553h);
        }

        public SavedState(Parcel parcel) {
            this.f2547b = parcel.readInt();
            this.f2548c = parcel.readInt();
            int i9 = parcel.readInt();
            this.f2549d = i9;
            if (i9 > 0) {
                int[] iArr = new int[i9];
                this.f2550e = iArr;
                parcel.readIntArray(iArr);
            }
            int i10 = parcel.readInt();
            this.f2551f = i10;
            if (i10 > 0) {
                int[] iArr2 = new int[i10];
                this.f2552g = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.f2554i = parcel.readInt() == 1;
            this.f2555j = parcel.readInt() == 1;
            this.f2556k = parcel.readInt() == 1;
            this.f2553h = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f2549d = savedState.f2549d;
            this.f2547b = savedState.f2547b;
            this.f2548c = savedState.f2548c;
            this.f2550e = savedState.f2550e;
            this.f2551f = savedState.f2551f;
            this.f2552g = savedState.f2552g;
            this.f2554i = savedState.f2554i;
            this.f2555j = savedState.f2555j;
            this.f2556k = savedState.f2556k;
            this.f2553h = savedState.f2553h;
        }
    }

    /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$a */
    public class RunnableC0468a implements Runnable {
        public RunnableC0468a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.m2649R1();
        }
    }

    /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$b */
    public class C0469b {

        /* renamed from: a */
        public int f2558a;

        /* renamed from: b */
        public int f2559b;

        /* renamed from: c */
        public boolean f2560c;

        /* renamed from: d */
        public boolean f2561d;

        /* renamed from: e */
        public boolean f2562e;

        /* renamed from: f */
        public int[] f2563f;

        public C0469b() {
            m2708c();
        }

        /* renamed from: a */
        public void m2706a() {
            this.f2559b = this.f2560c ? StaggeredGridLayoutManager.this.f2535u.mo2873i() : StaggeredGridLayoutManager.this.f2535u.mo2877m();
        }

        /* renamed from: b */
        public void m2707b(int i9) {
            if (this.f2560c) {
                this.f2559b = StaggeredGridLayoutManager.this.f2535u.mo2873i() - i9;
            } else {
                this.f2559b = StaggeredGridLayoutManager.this.f2535u.mo2877m() + i9;
            }
        }

        /* renamed from: c */
        public void m2708c() {
            this.f2558a = -1;
            this.f2559b = Integer.MIN_VALUE;
            this.f2560c = false;
            this.f2561d = false;
            this.f2562e = false;
            int[] iArr = this.f2563f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        /* renamed from: d */
        public void m2709d(C0471d[] c0471dArr) {
            int length = c0471dArr.length;
            int[] iArr = this.f2563f;
            if (iArr == null || iArr.length < length) {
                this.f2563f = new int[StaggeredGridLayoutManager.this.f2534t.length];
            }
            for (int i9 = 0; i9 < length; i9++) {
                this.f2563f[i9] = c0471dArr[i9].m2727p(Integer.MIN_VALUE);
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$c */
    public static class C0470c extends RecyclerView.C0455p {

        /* renamed from: e */
        public C0471d f2565e;

        /* renamed from: f */
        public boolean f2566f;

        public C0470c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* renamed from: e */
        public final int m2710e() {
            C0471d c0471d = this.f2565e;
            if (c0471d == null) {
                return -1;
            }
            return c0471d.f2571e;
        }

        /* renamed from: f */
        public boolean m2711f() {
            return this.f2566f;
        }

        public C0470c(int i9, int i10) {
            super(i9, i10);
        }

        public C0470c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public C0470c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$d */
    public class C0471d {

        /* renamed from: a */
        public ArrayList<View> f2567a = new ArrayList<>();

        /* renamed from: b */
        public int f2568b = Integer.MIN_VALUE;

        /* renamed from: c */
        public int f2569c = Integer.MIN_VALUE;

        /* renamed from: d */
        public int f2570d = 0;

        /* renamed from: e */
        public final int f2571e;

        public C0471d(int i9) {
            this.f2571e = i9;
        }

        /* renamed from: a */
        public void m2712a(View view) {
            C0470c c0470cM2725n = m2725n(view);
            c0470cM2725n.f2565e = this;
            this.f2567a.add(view);
            this.f2569c = Integer.MIN_VALUE;
            if (this.f2567a.size() == 1) {
                this.f2568b = Integer.MIN_VALUE;
            }
            if (c0470cM2725n.m2535c() || c0470cM2725n.m2534b()) {
                this.f2570d += StaggeredGridLayoutManager.this.f2535u.mo2869e(view);
            }
        }

        /* renamed from: b */
        public void m2713b(boolean z8, int i9) {
            int iM2723l = z8 ? m2723l(Integer.MIN_VALUE) : m2727p(Integer.MIN_VALUE);
            m2716e();
            if (iM2723l == Integer.MIN_VALUE) {
                return;
            }
            if (!z8 || iM2723l >= StaggeredGridLayoutManager.this.f2535u.mo2873i()) {
                if (z8 || iM2723l <= StaggeredGridLayoutManager.this.f2535u.mo2877m()) {
                    if (i9 != Integer.MIN_VALUE) {
                        iM2723l += i9;
                    }
                    this.f2569c = iM2723l;
                    this.f2568b = iM2723l;
                }
            }
        }

        /* renamed from: c */
        public void m2714c() {
            LazySpanLookup.FullSpanItem fullSpanItemM2689f;
            ArrayList<View> arrayList = this.f2567a;
            View view = arrayList.get(arrayList.size() - 1);
            C0470c c0470cM2725n = m2725n(view);
            this.f2569c = StaggeredGridLayoutManager.this.f2535u.mo2868d(view);
            if (c0470cM2725n.f2566f && (fullSpanItemM2689f = StaggeredGridLayoutManager.this.f2521E.m2689f(c0470cM2725n.m2533a())) != null && fullSpanItemM2689f.f2544c == 1) {
                this.f2569c += fullSpanItemM2689f.m2699a(this.f2571e);
            }
        }

        /* renamed from: d */
        public void m2715d() {
            LazySpanLookup.FullSpanItem fullSpanItemM2689f;
            View view = this.f2567a.get(0);
            C0470c c0470cM2725n = m2725n(view);
            this.f2568b = StaggeredGridLayoutManager.this.f2535u.mo2871g(view);
            if (c0470cM2725n.f2566f && (fullSpanItemM2689f = StaggeredGridLayoutManager.this.f2521E.m2689f(c0470cM2725n.m2533a())) != null && fullSpanItemM2689f.f2544c == -1) {
                this.f2568b -= fullSpanItemM2689f.m2699a(this.f2571e);
            }
        }

        /* renamed from: e */
        public void m2716e() {
            this.f2567a.clear();
            m2728q();
            this.f2570d = 0;
        }

        /* renamed from: f */
        public int m2717f() {
            return StaggeredGridLayoutManager.this.f2540z ? m2720i(this.f2567a.size() - 1, -1, true) : m2720i(0, this.f2567a.size(), true);
        }

        /* renamed from: g */
        public int m2718g() {
            return StaggeredGridLayoutManager.this.f2540z ? m2720i(0, this.f2567a.size(), true) : m2720i(this.f2567a.size() - 1, -1, true);
        }

        /* renamed from: h */
        public int m2719h(int i9, int i10, boolean z8, boolean z9, boolean z10) {
            int iMo2877m = StaggeredGridLayoutManager.this.f2535u.mo2877m();
            int iMo2873i = StaggeredGridLayoutManager.this.f2535u.mo2873i();
            int i11 = i10 > i9 ? 1 : -1;
            while (i9 != i10) {
                View view = this.f2567a.get(i9);
                int iMo2871g = StaggeredGridLayoutManager.this.f2535u.mo2871g(view);
                int iMo2868d = StaggeredGridLayoutManager.this.f2535u.mo2868d(view);
                boolean z11 = false;
                boolean z12 = !z10 ? iMo2871g >= iMo2873i : iMo2871g > iMo2873i;
                if (!z10 ? iMo2868d > iMo2877m : iMo2868d >= iMo2877m) {
                    z11 = true;
                }
                if (z12 && z11) {
                    if (z8 && z9) {
                        if (iMo2871g >= iMo2877m && iMo2868d <= iMo2873i) {
                            return StaggeredGridLayoutManager.this.m2487g0(view);
                        }
                    } else {
                        if (z9) {
                            return StaggeredGridLayoutManager.this.m2487g0(view);
                        }
                        if (iMo2871g < iMo2877m || iMo2868d > iMo2873i) {
                            return StaggeredGridLayoutManager.this.m2487g0(view);
                        }
                    }
                }
                i9 += i11;
            }
            return -1;
        }

        /* renamed from: i */
        public int m2720i(int i9, int i10, boolean z8) {
            return m2719h(i9, i10, false, false, z8);
        }

        /* renamed from: j */
        public int m2721j() {
            return this.f2570d;
        }

        /* renamed from: k */
        public int m2722k() {
            int i9 = this.f2569c;
            if (i9 != Integer.MIN_VALUE) {
                return i9;
            }
            m2714c();
            return this.f2569c;
        }

        /* renamed from: l */
        public int m2723l(int i9) {
            int i10 = this.f2569c;
            if (i10 != Integer.MIN_VALUE) {
                return i10;
            }
            if (this.f2567a.size() == 0) {
                return i9;
            }
            m2714c();
            return this.f2569c;
        }

        /* renamed from: m */
        public View m2724m(int i9, int i10) {
            View view = null;
            if (i10 != -1) {
                int size = this.f2567a.size() - 1;
                while (size >= 0) {
                    View view2 = this.f2567a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.f2540z && staggeredGridLayoutManager.m2487g0(view2) >= i9) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.f2540z && staggeredGridLayoutManager2.m2487g0(view2) <= i9) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.f2567a.size();
                int i11 = 0;
                while (i11 < size2) {
                    View view3 = this.f2567a.get(i11);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.f2540z && staggeredGridLayoutManager3.m2487g0(view3) <= i9) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.f2540z && staggeredGridLayoutManager4.m2487g0(view3) >= i9) || !view3.hasFocusable()) {
                        break;
                    }
                    i11++;
                    view = view3;
                }
            }
            return view;
        }

        /* renamed from: n */
        public C0470c m2725n(View view) {
            return (C0470c) view.getLayoutParams();
        }

        /* renamed from: o */
        public int m2726o() {
            int i9 = this.f2568b;
            if (i9 != Integer.MIN_VALUE) {
                return i9;
            }
            m2715d();
            return this.f2568b;
        }

        /* renamed from: p */
        public int m2727p(int i9) {
            int i10 = this.f2568b;
            if (i10 != Integer.MIN_VALUE) {
                return i10;
            }
            if (this.f2567a.size() == 0) {
                return i9;
            }
            m2715d();
            return this.f2568b;
        }

        /* renamed from: q */
        public void m2728q() {
            this.f2568b = Integer.MIN_VALUE;
            this.f2569c = Integer.MIN_VALUE;
        }

        /* renamed from: r */
        public void m2729r(int i9) {
            int i10 = this.f2568b;
            if (i10 != Integer.MIN_VALUE) {
                this.f2568b = i10 + i9;
            }
            int i11 = this.f2569c;
            if (i11 != Integer.MIN_VALUE) {
                this.f2569c = i11 + i9;
            }
        }

        /* renamed from: s */
        public void m2730s() {
            int size = this.f2567a.size();
            View viewRemove = this.f2567a.remove(size - 1);
            C0470c c0470cM2725n = m2725n(viewRemove);
            c0470cM2725n.f2565e = null;
            if (c0470cM2725n.m2535c() || c0470cM2725n.m2534b()) {
                this.f2570d -= StaggeredGridLayoutManager.this.f2535u.mo2869e(viewRemove);
            }
            if (size == 1) {
                this.f2568b = Integer.MIN_VALUE;
            }
            this.f2569c = Integer.MIN_VALUE;
        }

        /* renamed from: t */
        public void m2731t() {
            View viewRemove = this.f2567a.remove(0);
            C0470c c0470cM2725n = m2725n(viewRemove);
            c0470cM2725n.f2565e = null;
            if (this.f2567a.size() == 0) {
                this.f2569c = Integer.MIN_VALUE;
            }
            if (c0470cM2725n.m2535c() || c0470cM2725n.m2534b()) {
                this.f2570d -= StaggeredGridLayoutManager.this.f2535u.mo2869e(viewRemove);
            }
            this.f2568b = Integer.MIN_VALUE;
        }

        /* renamed from: u */
        public void m2732u(View view) {
            C0470c c0470cM2725n = m2725n(view);
            c0470cM2725n.f2565e = this;
            this.f2567a.add(0, view);
            this.f2568b = Integer.MIN_VALUE;
            if (this.f2567a.size() == 1) {
                this.f2569c = Integer.MIN_VALUE;
            }
            if (c0470cM2725n.m2535c() || c0470cM2725n.m2534b()) {
                this.f2570d += StaggeredGridLayoutManager.this.f2535u.mo2869e(view);
            }
        }

        /* renamed from: v */
        public void m2733v(int i9) {
            this.f2568b = i9;
            this.f2569c = i9;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i9, int i10) {
        RecyclerView.AbstractC0454o.d dVarM2427h0 = RecyclerView.AbstractC0454o.m2427h0(context, attributeSet, i9, i10);
        m2632G2(dVarM2427h0.f2462a);
        m2634I2(dVarM2427h0.f2463b);
        m2633H2(dVarM2427h0.f2464c);
        this.f2539y = new C0479h();
        m2657Z1();
    }

    /* renamed from: A2 */
    public final void m2626A2(RecyclerView.C0461v c0461v, int i9) {
        for (int iM2448J = m2448J() - 1; iM2448J >= 0; iM2448J--) {
            View viewM2446I = m2446I(iM2448J);
            if (this.f2535u.mo2871g(viewM2446I) < i9 || this.f2535u.mo2881q(viewM2446I) < i9) {
                return;
            }
            C0470c c0470c = (C0470c) viewM2446I.getLayoutParams();
            if (c0470c.f2566f) {
                for (int i10 = 0; i10 < this.f2533s; i10++) {
                    if (this.f2534t[i10].f2567a.size() == 1) {
                        return;
                    }
                }
                for (int i11 = 0; i11 < this.f2533s; i11++) {
                    this.f2534t[i11].m2730s();
                }
            } else if (c0470c.f2565e.f2567a.size() == 1) {
                return;
            } else {
                c0470c.f2565e.m2730s();
            }
            m2499l1(viewM2446I, c0461v);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: B0 */
    public void mo2434B0(int i9) {
        super.mo2434B0(i9);
        for (int i10 = 0; i10 < this.f2533s; i10++) {
            this.f2534t[i10].m2729r(i9);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: B1 */
    public void mo2210B1(Rect rect, int i9, int i10) {
        int iM2428n;
        int iM2428n2;
        int iM2479d0 = m2479d0() + m2482e0();
        int iM2485f0 = m2485f0() + m2477c0();
        if (this.f2537w == 1) {
            iM2428n2 = RecyclerView.AbstractC0454o.m2428n(i10, rect.height() + iM2485f0, m2472a0());
            iM2428n = RecyclerView.AbstractC0454o.m2428n(i9, (this.f2538x * this.f2533s) + iM2479d0, m2475b0());
        } else {
            iM2428n = RecyclerView.AbstractC0454o.m2428n(i9, rect.width() + iM2479d0, m2475b0());
            iM2428n2 = RecyclerView.AbstractC0454o.m2428n(i10, (this.f2538x * this.f2533s) + iM2485f0, m2472a0());
        }
        m2432A1(iM2428n, iM2428n2);
    }

    /* renamed from: B2 */
    public final void m2627B2(RecyclerView.C0461v c0461v, int i9) {
        while (m2448J() > 0) {
            View viewM2446I = m2446I(0);
            if (this.f2535u.mo2868d(viewM2446I) > i9 || this.f2535u.mo2880p(viewM2446I) > i9) {
                return;
            }
            C0470c c0470c = (C0470c) viewM2446I.getLayoutParams();
            if (c0470c.f2566f) {
                for (int i10 = 0; i10 < this.f2533s; i10++) {
                    if (this.f2534t[i10].f2567a.size() == 1) {
                        return;
                    }
                }
                for (int i11 = 0; i11 < this.f2533s; i11++) {
                    this.f2534t[i11].m2731t();
                }
            } else if (c0470c.f2565e.f2567a.size() == 1) {
                return;
            } else {
                c0470c.f2565e.m2731t();
            }
            m2499l1(viewM2446I, c0461v);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: C0 */
    public void mo2435C0(int i9) {
        super.mo2435C0(i9);
        for (int i10 = 0; i10 < this.f2533s; i10++) {
            this.f2534t[i10].m2729r(i9);
        }
    }

    /* renamed from: C2 */
    public final void m2628C2() {
        if (this.f2536v.mo2875k() == 1073741824) {
            return;
        }
        int iM2448J = m2448J();
        float fMax = BitmapDescriptorFactory.HUE_RED;
        for (int i9 = 0; i9 < iM2448J; i9++) {
            View viewM2446I = m2446I(i9);
            float fMo2869e = this.f2536v.mo2869e(viewM2446I);
            if (fMo2869e >= fMax) {
                if (((C0470c) viewM2446I.getLayoutParams()).m2711f()) {
                    fMo2869e = (fMo2869e * 1.0f) / this.f2533s;
                }
                fMax = Math.max(fMax, fMo2869e);
            }
        }
        int i10 = this.f2538x;
        int iRound = Math.round(fMax * this.f2533s);
        if (this.f2536v.mo2875k() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.f2536v.mo2878n());
        }
        m2644O2(iRound);
        if (this.f2538x == i10) {
            return;
        }
        for (int i11 = 0; i11 < iM2448J; i11++) {
            View viewM2446I2 = m2446I(i11);
            C0470c c0470c = (C0470c) viewM2446I2.getLayoutParams();
            if (!c0470c.f2566f) {
                if (m2676s2() && this.f2537w == 1) {
                    int i12 = this.f2533s;
                    int i13 = c0470c.f2565e.f2571e;
                    viewM2446I2.offsetLeftAndRight(((-((i12 - 1) - i13)) * this.f2538x) - ((-((i12 - 1) - i13)) * i10));
                } else {
                    int i14 = c0470c.f2565e.f2571e;
                    int i15 = this.f2538x * i14;
                    int i16 = i14 * i10;
                    if (this.f2537w == 1) {
                        viewM2446I2.offsetLeftAndRight(i15 - i16);
                    } else {
                        viewM2446I2.offsetTopAndBottom(i15 - i16);
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: D */
    public RecyclerView.C0455p mo2212D() {
        return this.f2537w == 0 ? new C0470c(-2, -1) : new C0470c(-1, -2);
    }

    /* renamed from: D2 */
    public final void m2629D2() {
        if (this.f2537w == 1 || !m2676s2()) {
            this.f2517A = this.f2540z;
        } else {
            this.f2517A = !this.f2540z;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: E */
    public RecyclerView.C0455p mo2213E(Context context, AttributeSet attributeSet) {
        return new C0470c(context, attributeSet);
    }

    /* renamed from: E2 */
    public int m2630E2(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (m2448J() == 0 || i9 == 0) {
            return 0;
        }
        m2681x2(i9, c0465z);
        int iM2658a2 = m2658a2(c0461v, this.f2539y, c0465z);
        if (this.f2539y.f2702b >= iM2658a2) {
            i9 = i9 < 0 ? -iM2658a2 : iM2658a2;
        }
        this.f2535u.mo2882r(-i9);
        this.f2523G = this.f2517A;
        C0479h c0479h = this.f2539y;
        c0479h.f2702b = 0;
        m2683z2(c0461v, c0479h);
        return i9;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: F */
    public RecyclerView.C0455p mo2214F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0470c((ViewGroup.MarginLayoutParams) layoutParams) : new C0470c(layoutParams);
    }

    /* renamed from: F2 */
    public final void m2631F2(int i9) {
        C0479h c0479h = this.f2539y;
        c0479h.f2705e = i9;
        c0479h.f2704d = this.f2517A != (i9 == -1) ? -1 : 1;
    }

    /* renamed from: G2 */
    public void m2632G2(int i9) {
        if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        mo2293g(null);
        if (i9 == this.f2537w) {
            return;
        }
        this.f2537w = i9;
        AbstractC0483l abstractC0483l = this.f2535u;
        this.f2535u = this.f2536v;
        this.f2536v = abstractC0483l;
        m2512s1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: H0 */
    public void mo2264H0(RecyclerView recyclerView, RecyclerView.C0461v c0461v) {
        super.mo2264H0(recyclerView, c0461v);
        m2503n1(this.f2532P);
        for (int i9 = 0; i9 < this.f2533s; i9++) {
            this.f2534t[i9].m2716e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: H1 */
    public void mo2265H1(RecyclerView recyclerView, RecyclerView.C0465z c0465z, int i9) {
        C0480i c0480i = new C0480i(recyclerView.getContext());
        c0480i.m2611p(i9);
        m2447I1(c0480i);
    }

    /* renamed from: H2 */
    public void m2633H2(boolean z8) {
        mo2293g(null);
        SavedState savedState = this.f2525I;
        if (savedState != null && savedState.f2554i != z8) {
            savedState.f2554i = z8;
        }
        this.f2540z = z8;
        m2512s1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: I0 */
    public View mo2215I0(View view, int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        View viewM2433B;
        View viewM2724m;
        if (m2448J() == 0 || (viewM2433B = m2433B(view)) == null) {
            return null;
        }
        m2629D2();
        int iM2654W1 = m2654W1(i9);
        if (iM2654W1 == Integer.MIN_VALUE) {
            return null;
        }
        C0470c c0470c = (C0470c) viewM2433B.getLayoutParams();
        boolean z8 = c0470c.f2566f;
        C0471d c0471d = c0470c.f2565e;
        int iM2667j2 = iM2654W1 == 1 ? m2667j2() : m2666i2();
        m2642N2(iM2667j2, c0465z);
        m2631F2(iM2654W1);
        C0479h c0479h = this.f2539y;
        c0479h.f2703c = c0479h.f2704d + iM2667j2;
        c0479h.f2702b = (int) (this.f2535u.mo2878n() * 0.33333334f);
        C0479h c0479h2 = this.f2539y;
        c0479h2.f2708h = true;
        c0479h2.f2701a = false;
        m2658a2(c0461v, c0479h2, c0465z);
        this.f2523G = this.f2517A;
        if (!z8 && (viewM2724m = c0471d.m2724m(iM2667j2, iM2654W1)) != null && viewM2724m != viewM2433B) {
            return viewM2724m;
        }
        if (m2680w2(iM2654W1)) {
            for (int i10 = this.f2533s - 1; i10 >= 0; i10--) {
                View viewM2724m2 = this.f2534t[i10].m2724m(iM2667j2, iM2654W1);
                if (viewM2724m2 != null && viewM2724m2 != viewM2433B) {
                    return viewM2724m2;
                }
            }
        } else {
            for (int i11 = 0; i11 < this.f2533s; i11++) {
                View viewM2724m3 = this.f2534t[i11].m2724m(iM2667j2, iM2654W1);
                if (viewM2724m3 != null && viewM2724m3 != viewM2433B) {
                    return viewM2724m3;
                }
            }
        }
        boolean z9 = (this.f2540z ^ true) == (iM2654W1 == -1);
        if (!z8) {
            View viewMo2258C = mo2258C(z9 ? c0471d.m2717f() : c0471d.m2718g());
            if (viewMo2258C != null && viewMo2258C != viewM2433B) {
                return viewMo2258C;
            }
        }
        if (m2680w2(iM2654W1)) {
            for (int i12 = this.f2533s - 1; i12 >= 0; i12--) {
                if (i12 != c0471d.f2571e) {
                    View viewMo2258C2 = mo2258C(z9 ? this.f2534t[i12].m2717f() : this.f2534t[i12].m2718g());
                    if (viewMo2258C2 != null && viewMo2258C2 != viewM2433B) {
                        return viewMo2258C2;
                    }
                }
            }
        } else {
            for (int i13 = 0; i13 < this.f2533s; i13++) {
                View viewMo2258C3 = mo2258C(z9 ? this.f2534t[i13].m2717f() : this.f2534t[i13].m2718g());
                if (viewMo2258C3 != null && viewMo2258C3 != viewM2433B) {
                    return viewMo2258C3;
                }
            }
        }
        return null;
    }

    /* renamed from: I2 */
    public void m2634I2(int i9) {
        mo2293g(null);
        if (i9 != this.f2533s) {
            m2675r2();
            this.f2533s = i9;
            this.f2518B = new BitSet(this.f2533s);
            this.f2534t = new C0471d[this.f2533s];
            for (int i10 = 0; i10 < this.f2533s; i10++) {
                this.f2534t[i10] = new C0471d(i10);
            }
            m2512s1();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: J0 */
    public void mo2268J0(AccessibilityEvent accessibilityEvent) {
        super.mo2268J0(accessibilityEvent);
        if (m2448J() > 0) {
            View viewM2661d2 = m2661d2(false);
            View viewM2660c2 = m2660c2(false);
            if (viewM2661d2 == null || viewM2660c2 == null) {
                return;
            }
            int iM2487g0 = m2487g0(viewM2661d2);
            int iM2487g02 = m2487g0(viewM2660c2);
            if (iM2487g0 < iM2487g02) {
                accessibilityEvent.setFromIndex(iM2487g0);
                accessibilityEvent.setToIndex(iM2487g02);
            } else {
                accessibilityEvent.setFromIndex(iM2487g02);
                accessibilityEvent.setToIndex(iM2487g0);
            }
        }
    }

    /* renamed from: J2 */
    public final void m2635J2(int i9, int i10) {
        for (int i11 = 0; i11 < this.f2533s; i11++) {
            if (!this.f2534t[i11].f2567a.isEmpty()) {
                m2646P2(this.f2534t[i11], i9, i10);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: K1 */
    public boolean mo2216K1() {
        return this.f2525I == null;
    }

    /* renamed from: K2 */
    public final boolean m2636K2(RecyclerView.C0465z c0465z, C0469b c0469b) {
        c0469b.f2558a = this.f2523G ? m2663f2(c0465z.m2620b()) : m2659b2(c0465z.m2620b());
        c0469b.f2559b = Integer.MIN_VALUE;
        return true;
    }

    /* renamed from: L1 */
    public final void m2637L1(View view) {
        for (int i9 = this.f2533s - 1; i9 >= 0; i9--) {
            this.f2534t[i9].m2712a(view);
        }
    }

    /* renamed from: L2 */
    public boolean m2638L2(RecyclerView.C0465z c0465z, C0469b c0469b) {
        int i9;
        if (!c0465z.m2623e() && (i9 = this.f2519C) != -1) {
            if (i9 >= 0 && i9 < c0465z.m2620b()) {
                SavedState savedState = this.f2525I;
                if (savedState == null || savedState.f2547b == -1 || savedState.f2549d < 1) {
                    View viewMo2258C = mo2258C(this.f2519C);
                    if (viewMo2258C != null) {
                        c0469b.f2558a = this.f2517A ? m2667j2() : m2666i2();
                        if (this.f2520D != Integer.MIN_VALUE) {
                            if (c0469b.f2560c) {
                                c0469b.f2559b = (this.f2535u.mo2873i() - this.f2520D) - this.f2535u.mo2868d(viewMo2258C);
                            } else {
                                c0469b.f2559b = (this.f2535u.mo2877m() + this.f2520D) - this.f2535u.mo2871g(viewMo2258C);
                            }
                            return true;
                        }
                        if (this.f2535u.mo2869e(viewMo2258C) > this.f2535u.mo2878n()) {
                            c0469b.f2559b = c0469b.f2560c ? this.f2535u.mo2873i() : this.f2535u.mo2877m();
                            return true;
                        }
                        int iMo2871g = this.f2535u.mo2871g(viewMo2258C) - this.f2535u.mo2877m();
                        if (iMo2871g < 0) {
                            c0469b.f2559b = -iMo2871g;
                            return true;
                        }
                        int iMo2873i = this.f2535u.mo2873i() - this.f2535u.mo2868d(viewMo2258C);
                        if (iMo2873i < 0) {
                            c0469b.f2559b = iMo2873i;
                            return true;
                        }
                        c0469b.f2559b = Integer.MIN_VALUE;
                    } else {
                        int i10 = this.f2519C;
                        c0469b.f2558a = i10;
                        int i11 = this.f2520D;
                        if (i11 == Integer.MIN_VALUE) {
                            c0469b.f2560c = m2647Q1(i10) == 1;
                            c0469b.m2706a();
                        } else {
                            c0469b.m2707b(i11);
                        }
                        c0469b.f2561d = true;
                    }
                } else {
                    c0469b.f2559b = Integer.MIN_VALUE;
                    c0469b.f2558a = this.f2519C;
                }
                return true;
            }
            this.f2519C = -1;
            this.f2520D = Integer.MIN_VALUE;
        }
        return false;
    }

    /* renamed from: M1 */
    public final void m2639M1(C0469b c0469b) {
        SavedState savedState = this.f2525I;
        int i9 = savedState.f2549d;
        if (i9 > 0) {
            if (i9 == this.f2533s) {
                for (int i10 = 0; i10 < this.f2533s; i10++) {
                    this.f2534t[i10].m2716e();
                    SavedState savedState2 = this.f2525I;
                    int iMo2873i = savedState2.f2550e[i10];
                    if (iMo2873i != Integer.MIN_VALUE) {
                        iMo2873i += savedState2.f2555j ? this.f2535u.mo2873i() : this.f2535u.mo2877m();
                    }
                    this.f2534t[i10].m2733v(iMo2873i);
                }
            } else {
                savedState.m2703b();
                SavedState savedState3 = this.f2525I;
                savedState3.f2547b = savedState3.f2548c;
            }
        }
        SavedState savedState4 = this.f2525I;
        this.f2524H = savedState4.f2556k;
        m2633H2(savedState4.f2554i);
        m2629D2();
        SavedState savedState5 = this.f2525I;
        int i11 = savedState5.f2547b;
        if (i11 != -1) {
            this.f2519C = i11;
            c0469b.f2560c = savedState5.f2555j;
        } else {
            c0469b.f2560c = this.f2517A;
        }
        if (savedState5.f2551f > 1) {
            LazySpanLookup lazySpanLookup = this.f2521E;
            lazySpanLookup.f2541a = savedState5.f2552g;
            lazySpanLookup.f2542b = savedState5.f2553h;
        }
    }

    /* renamed from: M2 */
    public void m2640M2(RecyclerView.C0465z c0465z, C0469b c0469b) {
        if (m2638L2(c0465z, c0469b) || m2636K2(c0465z, c0469b)) {
            return;
        }
        c0469b.m2706a();
        c0469b.f2558a = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: N */
    public int mo2220N(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2537w == 1 ? this.f2533s : super.mo2220N(c0461v, c0465z);
    }

    /* renamed from: N1 */
    public boolean m2641N1() {
        int iM2723l = this.f2534t[0].m2723l(Integer.MIN_VALUE);
        for (int i9 = 1; i9 < this.f2533s; i9++) {
            if (this.f2534t[i9].m2723l(Integer.MIN_VALUE) != iM2723l) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: N2 */
    public final void m2642N2(int i9, RecyclerView.C0465z c0465z) {
        int iMo2878n;
        int iMo2878n2;
        int iM2621c;
        C0479h c0479h = this.f2539y;
        boolean z8 = false;
        c0479h.f2702b = 0;
        c0479h.f2703c = i9;
        if (!m2518w0() || (iM2621c = c0465z.m2621c()) == -1) {
            iMo2878n = 0;
            iMo2878n2 = 0;
        } else {
            if (this.f2517A == (iM2621c < i9)) {
                iMo2878n = this.f2535u.mo2878n();
                iMo2878n2 = 0;
            } else {
                iMo2878n2 = this.f2535u.mo2878n();
                iMo2878n = 0;
            }
        }
        if (m2453M()) {
            this.f2539y.f2706f = this.f2535u.mo2877m() - iMo2878n2;
            this.f2539y.f2707g = this.f2535u.mo2873i() + iMo2878n;
        } else {
            this.f2539y.f2707g = this.f2535u.mo2872h() + iMo2878n;
            this.f2539y.f2706f = -iMo2878n2;
        }
        C0479h c0479h2 = this.f2539y;
        c0479h2.f2708h = false;
        c0479h2.f2701a = true;
        if (this.f2535u.mo2875k() == 0 && this.f2535u.mo2872h() == 0) {
            z8 = true;
        }
        c0479h2.f2709i = z8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: O0 */
    public void mo2222O0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, View view, C4704m c4704m) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C0470c)) {
            super.m2455N0(view, c4704m);
            return;
        }
        C0470c c0470c = (C0470c) layoutParams;
        if (this.f2537w == 0) {
            int iM2710e = c0470c.m2710e();
            boolean z8 = c0470c.f2566f;
            c4704m.m18784X(C4704m.c.m18837a(iM2710e, z8 ? this.f2533s : 1, -1, -1, z8, false));
        } else {
            int iM2710e2 = c0470c.m2710e();
            boolean z9 = c0470c.f2566f;
            c4704m.m18784X(C4704m.c.m18837a(-1, -1, iM2710e2, z9 ? this.f2533s : 1, z9, false));
        }
    }

    /* renamed from: O1 */
    public boolean m2643O1() {
        int iM2727p = this.f2534t[0].m2727p(Integer.MIN_VALUE);
        for (int i9 = 1; i9 < this.f2533s; i9++) {
            if (this.f2534t[i9].m2727p(Integer.MIN_VALUE) != iM2727p) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: O2 */
    public void m2644O2(int i9) {
        this.f2538x = i9 / this.f2533s;
        this.f2526J = View.MeasureSpec.makeMeasureSpec(i9, this.f2536v.mo2875k());
    }

    /* renamed from: P1 */
    public final void m2645P1(View view, C0470c c0470c, C0479h c0479h) {
        if (c0479h.f2705e == 1) {
            if (c0470c.f2566f) {
                m2637L1(view);
                return;
            } else {
                c0470c.f2565e.m2712a(view);
                return;
            }
        }
        if (c0470c.f2566f) {
            m2682y2(view);
        } else {
            c0470c.f2565e.m2732u(view);
        }
    }

    /* renamed from: P2 */
    public final void m2646P2(C0471d c0471d, int i9, int i10) {
        int iM2721j = c0471d.m2721j();
        if (i9 == -1) {
            if (c0471d.m2726o() + iM2721j <= i10) {
                this.f2518B.set(c0471d.f2571e, false);
            }
        } else if (c0471d.m2722k() - iM2721j >= i10) {
            this.f2518B.set(c0471d.f2571e, false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: Q0 */
    public void mo2224Q0(RecyclerView recyclerView, int i9, int i10) {
        m2673p2(i9, i10, 1);
    }

    /* renamed from: Q1 */
    public final int m2647Q1(int i9) {
        if (m2448J() == 0) {
            return this.f2517A ? 1 : -1;
        }
        return (i9 < m2666i2()) != this.f2517A ? -1 : 1;
    }

    /* renamed from: Q2 */
    public final int m2648Q2(int i9, int i10, int i11) {
        if (i10 == 0 && i11 == 0) {
            return i9;
        }
        int mode = View.MeasureSpec.getMode(i9);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i9) - i10) - i11), mode) : i9;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: R0 */
    public void mo2226R0(RecyclerView recyclerView) {
        this.f2521E.m2685b();
        m2512s1();
    }

    /* renamed from: R1 */
    public boolean m2649R1() {
        int iM2666i2;
        int iM2667j2;
        if (m2448J() == 0 || this.f2522F == 0 || !m2508q0()) {
            return false;
        }
        if (this.f2517A) {
            iM2666i2 = m2667j2();
            iM2667j2 = m2666i2();
        } else {
            iM2666i2 = m2666i2();
            iM2667j2 = m2667j2();
        }
        if (iM2666i2 == 0 && m2674q2() != null) {
            this.f2521E.m2685b();
            m2514t1();
            m2512s1();
            return true;
        }
        if (!this.f2529M) {
            return false;
        }
        int i9 = this.f2517A ? -1 : 1;
        int i10 = iM2667j2 + 1;
        LazySpanLookup.FullSpanItem fullSpanItemM2688e = this.f2521E.m2688e(iM2666i2, i10, i9, true);
        if (fullSpanItemM2688e == null) {
            this.f2529M = false;
            this.f2521E.m2687d(i10);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemM2688e2 = this.f2521E.m2688e(iM2666i2, fullSpanItemM2688e.f2543b, i9 * (-1), true);
        if (fullSpanItemM2688e2 == null) {
            this.f2521E.m2687d(fullSpanItemM2688e.f2543b);
        } else {
            this.f2521E.m2687d(fullSpanItemM2688e2.f2543b + 1);
        }
        m2514t1();
        m2512s1();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: S0 */
    public void mo2228S0(RecyclerView recyclerView, int i9, int i10, int i11) {
        m2673p2(i9, i10, 8);
    }

    /* renamed from: S1 */
    public final boolean m2650S1(C0471d c0471d) {
        if (this.f2517A) {
            if (c0471d.m2722k() < this.f2535u.mo2873i()) {
                ArrayList<View> arrayList = c0471d.f2567a;
                return !c0471d.m2725n(arrayList.get(arrayList.size() - 1)).f2566f;
            }
        } else if (c0471d.m2726o() > this.f2535u.mo2877m()) {
            return !c0471d.m2725n(c0471d.f2567a.get(0)).f2566f;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: T0 */
    public void mo2230T0(RecyclerView recyclerView, int i9, int i10) {
        m2673p2(i9, i10, 2);
    }

    /* renamed from: T1 */
    public final int m2651T1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        return C0485n.m2886a(c0465z, this.f2535u, m2661d2(!this.f2530N), m2660c2(!this.f2530N), this, this.f2530N);
    }

    /* renamed from: U1 */
    public final int m2652U1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        return C0485n.m2887b(c0465z, this.f2535u, m2661d2(!this.f2530N), m2660c2(!this.f2530N), this, this.f2530N, this.f2517A);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: V0 */
    public void mo2233V0(RecyclerView recyclerView, int i9, int i10, Object obj) {
        m2673p2(i9, i10, 4);
    }

    /* renamed from: V1 */
    public final int m2653V1(RecyclerView.C0465z c0465z) {
        if (m2448J() == 0) {
            return 0;
        }
        return C0485n.m2888c(c0465z, this.f2535u, m2661d2(!this.f2530N), m2660c2(!this.f2530N), this, this.f2530N);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: W0 */
    public void mo2235W0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        m2679v2(c0461v, c0465z, true);
    }

    /* renamed from: W1 */
    public final int m2654W1(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 17 ? i9 != 33 ? i9 != 66 ? (i9 == 130 && this.f2537w == 1) ? 1 : Integer.MIN_VALUE : this.f2537w == 0 ? 1 : Integer.MIN_VALUE : this.f2537w == 1 ? -1 : Integer.MIN_VALUE : this.f2537w == 0 ? -1 : Integer.MIN_VALUE : (this.f2537w != 1 && m2676s2()) ? -1 : 1 : (this.f2537w != 1 && m2676s2()) ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: X0 */
    public void mo2237X0(RecyclerView.C0465z c0465z) {
        super.mo2237X0(c0465z);
        this.f2519C = -1;
        this.f2520D = Integer.MIN_VALUE;
        this.f2525I = null;
        this.f2528L.m2708c();
    }

    /* renamed from: X1 */
    public final LazySpanLookup.FullSpanItem m2655X1(int i9) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f2545d = new int[this.f2533s];
        for (int i10 = 0; i10 < this.f2533s; i10++) {
            fullSpanItem.f2545d[i10] = i9 - this.f2534t[i10].m2723l(i9);
        }
        return fullSpanItem;
    }

    /* renamed from: Y1 */
    public final LazySpanLookup.FullSpanItem m2656Y1(int i9) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f2545d = new int[this.f2533s];
        for (int i10 = 0; i10 < this.f2533s; i10++) {
            fullSpanItem.f2545d[i10] = this.f2534t[i10].m2727p(i9) - i9;
        }
        return fullSpanItem;
    }

    /* renamed from: Z1 */
    public final void m2657Z1() {
        this.f2535u = AbstractC0483l.m2866b(this, this.f2537w);
        this.f2536v = AbstractC0483l.m2866b(this, 1 - this.f2537w);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y.b
    /* renamed from: a */
    public PointF mo2285a(int i9) {
        int iM2647Q1 = m2647Q1(i9);
        PointF pointF = new PointF();
        if (iM2647Q1 == 0) {
            return null;
        }
        if (this.f2537w == 0) {
            pointF.x = iM2647Q1;
            pointF.y = BitmapDescriptorFactory.HUE_RED;
        } else {
            pointF.x = BitmapDescriptorFactory.HUE_RED;
            pointF.y = iM2647Q1;
        }
        return pointF;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* renamed from: a2 */
    public final int m2658a2(RecyclerView.C0461v c0461v, C0479h c0479h, RecyclerView.C0465z c0465z) {
        C0471d c0471dM2672o2;
        int iMo2869e;
        int i9;
        int iMo2869e2;
        int iMo2869e3;
        boolean z8;
        ?? r9 = 0;
        this.f2518B.set(0, this.f2533s, true);
        int i10 = this.f2539y.f2709i ? c0479h.f2705e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : c0479h.f2705e == 1 ? c0479h.f2707g + c0479h.f2702b : c0479h.f2706f - c0479h.f2702b;
        m2635J2(c0479h.f2705e, i10);
        int iMo2873i = this.f2517A ? this.f2535u.mo2873i() : this.f2535u.mo2877m();
        boolean z9 = false;
        while (c0479h.m2847a(c0465z) && (this.f2539y.f2709i || !this.f2518B.isEmpty())) {
            View viewM2848b = c0479h.m2848b(c0461v);
            C0470c c0470c = (C0470c) viewM2848b.getLayoutParams();
            int iM2533a = c0470c.m2533a();
            int iM2690g = this.f2521E.m2690g(iM2533a);
            boolean z10 = iM2690g == -1 ? true : r9;
            if (z10) {
                c0471dM2672o2 = c0470c.f2566f ? this.f2534t[r9] : m2672o2(c0479h);
                this.f2521E.m2697n(iM2533a, c0471dM2672o2);
            } else {
                c0471dM2672o2 = this.f2534t[iM2690g];
            }
            C0471d c0471d = c0471dM2672o2;
            c0470c.f2565e = c0471d;
            if (c0479h.f2705e == 1) {
                m2478d(viewM2848b);
            } else {
                m2481e(viewM2848b, r9);
            }
            m2678u2(viewM2848b, c0470c, r9);
            if (c0479h.f2705e == 1) {
                int iM2668k2 = c0470c.f2566f ? m2668k2(iMo2873i) : c0471d.m2723l(iMo2873i);
                int iMo2869e4 = this.f2535u.mo2869e(viewM2848b) + iM2668k2;
                if (z10 && c0470c.f2566f) {
                    LazySpanLookup.FullSpanItem fullSpanItemM2655X1 = m2655X1(iM2668k2);
                    fullSpanItemM2655X1.f2544c = -1;
                    fullSpanItemM2655X1.f2543b = iM2533a;
                    this.f2521E.m2684a(fullSpanItemM2655X1);
                }
                i9 = iMo2869e4;
                iMo2869e = iM2668k2;
            } else {
                int iM2671n2 = c0470c.f2566f ? m2671n2(iMo2873i) : c0471d.m2727p(iMo2873i);
                iMo2869e = iM2671n2 - this.f2535u.mo2869e(viewM2848b);
                if (z10 && c0470c.f2566f) {
                    LazySpanLookup.FullSpanItem fullSpanItemM2656Y1 = m2656Y1(iM2671n2);
                    fullSpanItemM2656Y1.f2544c = 1;
                    fullSpanItemM2656Y1.f2543b = iM2533a;
                    this.f2521E.m2684a(fullSpanItemM2656Y1);
                }
                i9 = iM2671n2;
            }
            if (c0470c.f2566f && c0479h.f2704d == -1) {
                if (z10) {
                    this.f2529M = true;
                } else {
                    if (!(c0479h.f2705e == 1 ? m2641N1() : m2643O1())) {
                        LazySpanLookup.FullSpanItem fullSpanItemM2689f = this.f2521E.m2689f(iM2533a);
                        if (fullSpanItemM2689f != null) {
                            fullSpanItemM2689f.f2546e = true;
                        }
                        this.f2529M = true;
                    }
                }
            }
            m2645P1(viewM2848b, c0470c, c0479h);
            if (m2676s2() && this.f2537w == 1) {
                int iMo2873i2 = c0470c.f2566f ? this.f2536v.mo2873i() : this.f2536v.mo2873i() - (((this.f2533s - 1) - c0471d.f2571e) * this.f2538x);
                iMo2869e3 = iMo2873i2;
                iMo2869e2 = iMo2873i2 - this.f2536v.mo2869e(viewM2848b);
            } else {
                int iMo2877m = c0470c.f2566f ? this.f2536v.mo2877m() : (c0471d.f2571e * this.f2538x) + this.f2536v.mo2877m();
                iMo2869e2 = iMo2877m;
                iMo2869e3 = this.f2536v.mo2869e(viewM2848b) + iMo2877m;
            }
            if (this.f2537w == 1) {
                m2522y0(viewM2848b, iMo2869e2, iMo2869e, iMo2869e3, i9);
            } else {
                m2522y0(viewM2848b, iMo2869e, iMo2869e2, i9, iMo2869e3);
            }
            if (c0470c.f2566f) {
                m2635J2(this.f2539y.f2705e, i10);
            } else {
                m2646P2(c0471d, this.f2539y.f2705e, i10);
            }
            m2683z2(c0461v, this.f2539y);
            if (!this.f2539y.f2708h || !viewM2848b.hasFocusable()) {
                z8 = false;
            } else if (c0470c.f2566f) {
                this.f2518B.clear();
                z8 = false;
            } else {
                z8 = false;
                this.f2518B.set(c0471d.f2571e, false);
            }
            r9 = z8;
            z9 = true;
        }
        int i11 = r9;
        if (!z9) {
            m2683z2(c0461v, this.f2539y);
        }
        int iMo2877m2 = this.f2539y.f2705e == -1 ? this.f2535u.mo2877m() - m2671n2(this.f2535u.mo2877m()) : m2668k2(this.f2535u.mo2873i()) - this.f2535u.mo2873i();
        return iMo2877m2 > 0 ? Math.min(c0479h.f2702b, iMo2877m2) : i11;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: b1 */
    public void mo2287b1(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f2525I = (SavedState) parcelable;
            m2512s1();
        }
    }

    /* renamed from: b2 */
    public final int m2659b2(int i9) {
        int iM2448J = m2448J();
        for (int i10 = 0; i10 < iM2448J; i10++) {
            int iM2487g0 = m2487g0(m2446I(i10));
            if (iM2487g0 >= 0 && iM2487g0 < i9) {
                return iM2487g0;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: c1 */
    public Parcelable mo2289c1() {
        int iM2727p;
        int iMo2877m;
        int[] iArr;
        if (this.f2525I != null) {
            return new SavedState(this.f2525I);
        }
        SavedState savedState = new SavedState();
        savedState.f2554i = this.f2540z;
        savedState.f2555j = this.f2523G;
        savedState.f2556k = this.f2524H;
        LazySpanLookup lazySpanLookup = this.f2521E;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f2541a) == null) {
            savedState.f2551f = 0;
        } else {
            savedState.f2552g = iArr;
            savedState.f2551f = iArr.length;
            savedState.f2553h = lazySpanLookup.f2542b;
        }
        if (m2448J() > 0) {
            savedState.f2547b = this.f2523G ? m2667j2() : m2666i2();
            savedState.f2548c = m2662e2();
            int i9 = this.f2533s;
            savedState.f2549d = i9;
            savedState.f2550e = new int[i9];
            for (int i10 = 0; i10 < this.f2533s; i10++) {
                if (this.f2523G) {
                    iM2727p = this.f2534t[i10].m2723l(Integer.MIN_VALUE);
                    if (iM2727p != Integer.MIN_VALUE) {
                        iMo2877m = this.f2535u.mo2873i();
                        iM2727p -= iMo2877m;
                    }
                } else {
                    iM2727p = this.f2534t[i10].m2727p(Integer.MIN_VALUE);
                    if (iM2727p != Integer.MIN_VALUE) {
                        iMo2877m = this.f2535u.mo2877m();
                        iM2727p -= iMo2877m;
                    }
                }
                savedState.f2550e[i10] = iM2727p;
            }
        } else {
            savedState.f2547b = -1;
            savedState.f2548c = -1;
            savedState.f2549d = 0;
        }
        return savedState;
    }

    /* renamed from: c2 */
    public View m2660c2(boolean z8) {
        int iMo2877m = this.f2535u.mo2877m();
        int iMo2873i = this.f2535u.mo2873i();
        View view = null;
        for (int iM2448J = m2448J() - 1; iM2448J >= 0; iM2448J--) {
            View viewM2446I = m2446I(iM2448J);
            int iMo2871g = this.f2535u.mo2871g(viewM2446I);
            int iMo2868d = this.f2535u.mo2868d(viewM2446I);
            if (iMo2868d > iMo2877m && iMo2871g < iMo2873i) {
                if (iMo2868d <= iMo2873i || !z8) {
                    return viewM2446I;
                }
                if (view == null) {
                    view = viewM2446I;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: d1 */
    public void mo2480d1(int i9) {
        if (i9 == 0) {
            m2649R1();
        }
    }

    /* renamed from: d2 */
    public View m2661d2(boolean z8) {
        int iMo2877m = this.f2535u.mo2877m();
        int iMo2873i = this.f2535u.mo2873i();
        int iM2448J = m2448J();
        View view = null;
        for (int i9 = 0; i9 < iM2448J; i9++) {
            View viewM2446I = m2446I(i9);
            int iMo2871g = this.f2535u.mo2871g(viewM2446I);
            if (this.f2535u.mo2868d(viewM2446I) > iMo2877m && iMo2871g < iMo2873i) {
                if (iMo2871g >= iMo2877m || !z8) {
                    return viewM2446I;
                }
                if (view == null) {
                    view = viewM2446I;
                }
            }
        }
        return view;
    }

    /* renamed from: e2 */
    public int m2662e2() {
        View viewM2660c2 = this.f2517A ? m2660c2(true) : m2661d2(true);
        if (viewM2660c2 == null) {
            return -1;
        }
        return m2487g0(viewM2660c2);
    }

    /* renamed from: f2 */
    public final int m2663f2(int i9) {
        for (int iM2448J = m2448J() - 1; iM2448J >= 0; iM2448J--) {
            int iM2487g0 = m2487g0(m2446I(iM2448J));
            if (iM2487g0 >= 0 && iM2487g0 < i9) {
                return iM2487g0;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: g */
    public void mo2293g(String str) {
        if (this.f2525I == null) {
            super.mo2293g(str);
        }
    }

    /* renamed from: g2 */
    public final void m2664g2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, boolean z8) {
        int iMo2873i;
        int iM2668k2 = m2668k2(Integer.MIN_VALUE);
        if (iM2668k2 != Integer.MIN_VALUE && (iMo2873i = this.f2535u.mo2873i() - iM2668k2) > 0) {
            int i9 = iMo2873i - (-m2630E2(-iMo2873i, c0461v, c0465z));
            if (!z8 || i9 <= 0) {
                return;
            }
            this.f2535u.mo2882r(i9);
        }
    }

    /* renamed from: h2 */
    public final void m2665h2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, boolean z8) {
        int iMo2877m;
        int iM2671n2 = m2671n2(Integer.MAX_VALUE);
        if (iM2671n2 != Integer.MAX_VALUE && (iMo2877m = iM2671n2 - this.f2535u.mo2877m()) > 0) {
            int iM2630E2 = iMo2877m - m2630E2(iMo2877m, c0461v, c0465z);
            if (!z8 || iM2630E2 <= 0) {
                return;
            }
            this.f2535u.mo2882r(-iM2630E2);
        }
    }

    /* renamed from: i2 */
    public int m2666i2() {
        if (m2448J() == 0) {
            return 0;
        }
        return m2487g0(m2446I(0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: j0 */
    public int mo2243j0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return this.f2537w == 0 ? this.f2533s : super.mo2243j0(c0461v, c0465z);
    }

    /* renamed from: j2 */
    public int m2667j2() {
        int iM2448J = m2448J();
        if (iM2448J == 0) {
            return 0;
        }
        return m2487g0(m2446I(iM2448J - 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: k */
    public boolean mo2298k() {
        return this.f2537w == 0;
    }

    /* renamed from: k2 */
    public final int m2668k2(int i9) {
        int iM2723l = this.f2534t[0].m2723l(i9);
        for (int i10 = 1; i10 < this.f2533s; i10++) {
            int iM2723l2 = this.f2534t[i10].m2723l(i9);
            if (iM2723l2 > iM2723l) {
                iM2723l = iM2723l2;
            }
        }
        return iM2723l;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: l */
    public boolean mo2300l() {
        return this.f2537w == 1;
    }

    /* renamed from: l2 */
    public final int m2669l2(int i9) {
        int iM2727p = this.f2534t[0].m2727p(i9);
        for (int i10 = 1; i10 < this.f2533s; i10++) {
            int iM2727p2 = this.f2534t[i10].m2727p(i9);
            if (iM2727p2 > iM2727p) {
                iM2727p = iM2727p2;
            }
        }
        return iM2727p;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: m */
    public boolean mo2244m(RecyclerView.C0455p c0455p) {
        return c0455p instanceof C0470c;
    }

    /* renamed from: m2 */
    public final int m2670m2(int i9) {
        int iM2723l = this.f2534t[0].m2723l(i9);
        for (int i10 = 1; i10 < this.f2533s; i10++) {
            int iM2723l2 = this.f2534t[i10].m2723l(i9);
            if (iM2723l2 < iM2723l) {
                iM2723l = iM2723l2;
            }
        }
        return iM2723l;
    }

    /* renamed from: n2 */
    public final int m2671n2(int i9) {
        int iM2727p = this.f2534t[0].m2727p(i9);
        for (int i10 = 1; i10 < this.f2533s; i10++) {
            int iM2727p2 = this.f2534t[i10].m2727p(i9);
            if (iM2727p2 < iM2727p) {
                iM2727p = iM2727p2;
            }
        }
        return iM2727p;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: o */
    public void mo2304o(int i9, int i10, RecyclerView.C0465z c0465z, RecyclerView.AbstractC0454o.c cVar) {
        int iM2723l;
        int iM2727p;
        if (this.f2537w != 0) {
            i9 = i10;
        }
        if (m2448J() == 0 || i9 == 0) {
            return;
        }
        m2681x2(i9, c0465z);
        int[] iArr = this.f2531O;
        if (iArr == null || iArr.length < this.f2533s) {
            this.f2531O = new int[this.f2533s];
        }
        int i11 = 0;
        for (int i12 = 0; i12 < this.f2533s; i12++) {
            C0479h c0479h = this.f2539y;
            if (c0479h.f2704d == -1) {
                iM2723l = c0479h.f2706f;
                iM2727p = this.f2534t[i12].m2727p(iM2723l);
            } else {
                iM2723l = this.f2534t[i12].m2723l(c0479h.f2707g);
                iM2727p = this.f2539y.f2707g;
            }
            int i13 = iM2723l - iM2727p;
            if (i13 >= 0) {
                this.f2531O[i11] = i13;
                i11++;
            }
        }
        Arrays.sort(this.f2531O, 0, i11);
        for (int i14 = 0; i14 < i11 && this.f2539y.m2847a(c0465z); i14++) {
            cVar.mo2532a(this.f2539y.f2703c, this.f2531O[i14]);
            C0479h c0479h2 = this.f2539y;
            c0479h2.f2703c += c0479h2.f2704d;
        }
    }

    /* renamed from: o2 */
    public final C0471d m2672o2(C0479h c0479h) {
        int i9;
        int i10;
        int i11;
        if (m2680w2(c0479h.f2705e)) {
            i10 = this.f2533s - 1;
            i9 = -1;
            i11 = -1;
        } else {
            i9 = this.f2533s;
            i10 = 0;
            i11 = 1;
        }
        C0471d c0471d = null;
        if (c0479h.f2705e == 1) {
            int iMo2877m = this.f2535u.mo2877m();
            int i12 = Integer.MAX_VALUE;
            while (i10 != i9) {
                C0471d c0471d2 = this.f2534t[i10];
                int iM2723l = c0471d2.m2723l(iMo2877m);
                if (iM2723l < i12) {
                    c0471d = c0471d2;
                    i12 = iM2723l;
                }
                i10 += i11;
            }
            return c0471d;
        }
        int iMo2873i = this.f2535u.mo2873i();
        int i13 = Integer.MIN_VALUE;
        while (i10 != i9) {
            C0471d c0471d3 = this.f2534t[i10];
            int iM2727p = c0471d3.m2727p(iMo2873i);
            if (iM2727p > i13) {
                c0471d = c0471d3;
                i13 = iM2727p;
            }
            i10 += i11;
        }
        return c0471d;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /* renamed from: p2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2673p2(int i9, int i10, int i11) {
        int i12;
        int i13;
        int iM2667j2 = this.f2517A ? m2667j2() : m2666i2();
        if (i11 != 8) {
            i12 = i9 + i10;
        } else {
            if (i9 >= i10) {
                i12 = i9 + 1;
                i13 = i10;
                this.f2521E.m2691h(i13);
                if (i11 != 1) {
                    this.f2521E.m2693j(i9, i10);
                } else if (i11 == 2) {
                    this.f2521E.m2694k(i9, i10);
                } else if (i11 == 8) {
                    this.f2521E.m2694k(i9, 1);
                    this.f2521E.m2693j(i10, 1);
                }
                if (i12 > iM2667j2) {
                    return;
                }
                if (i13 <= (this.f2517A ? m2666i2() : m2667j2())) {
                    m2512s1();
                    return;
                }
                return;
            }
            i12 = i10 + 1;
        }
        i13 = i9;
        this.f2521E.m2691h(i13);
        if (i11 != 1) {
        }
        if (i12 > iM2667j2) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: q */
    public int mo2307q(RecyclerView.C0465z c0465z) {
        return m2651T1(c0465z);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /* renamed from: q2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View m2674q2() {
        int i9;
        int i10;
        boolean z8;
        int iM2448J = m2448J() - 1;
        BitSet bitSet = new BitSet(this.f2533s);
        bitSet.set(0, this.f2533s, true);
        char c9 = (this.f2537w == 1 && m2676s2()) ? (char) 1 : (char) 65535;
        if (this.f2517A) {
            i9 = -1;
        } else {
            i9 = iM2448J + 1;
            iM2448J = 0;
        }
        int i11 = iM2448J < i9 ? 1 : -1;
        while (iM2448J != i9) {
            View viewM2446I = m2446I(iM2448J);
            C0470c c0470c = (C0470c) viewM2446I.getLayoutParams();
            if (bitSet.get(c0470c.f2565e.f2571e)) {
                if (m2650S1(c0470c.f2565e)) {
                    return viewM2446I;
                }
                bitSet.clear(c0470c.f2565e.f2571e);
            }
            if (!c0470c.f2566f && (i10 = iM2448J + i11) != i9) {
                View viewM2446I2 = m2446I(i10);
                if (this.f2517A) {
                    int iMo2868d = this.f2535u.mo2868d(viewM2446I);
                    int iMo2868d2 = this.f2535u.mo2868d(viewM2446I2);
                    if (iMo2868d < iMo2868d2) {
                        return viewM2446I;
                    }
                    z8 = iMo2868d == iMo2868d2;
                } else {
                    int iMo2871g = this.f2535u.mo2871g(viewM2446I);
                    int iMo2871g2 = this.f2535u.mo2871g(viewM2446I2);
                    if (iMo2871g > iMo2871g2) {
                        return viewM2446I;
                    }
                    if (iMo2871g == iMo2871g2) {
                    }
                }
                if (z8) {
                    if ((c0470c.f2565e.f2571e - ((C0470c) viewM2446I2.getLayoutParams()).f2565e.f2571e < 0) != (c9 < 0)) {
                        return viewM2446I;
                    }
                } else {
                    continue;
                }
            }
            iM2448J += i11;
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: r */
    public int mo2309r(RecyclerView.C0465z c0465z) {
        return m2652U1(c0465z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: r0 */
    public boolean mo2310r0() {
        return this.f2522F != 0;
    }

    /* renamed from: r2 */
    public void m2675r2() {
        this.f2521E.m2685b();
        m2512s1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: s */
    public int mo2311s(RecyclerView.C0465z c0465z) {
        return m2653V1(c0465z);
    }

    /* renamed from: s2 */
    public boolean m2676s2() {
        return m2468Y() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: t */
    public int mo2313t(RecyclerView.C0465z c0465z) {
        return m2651T1(c0465z);
    }

    /* renamed from: t2 */
    public final void m2677t2(View view, int i9, int i10, boolean z8) {
        m2494j(view, this.f2527K);
        C0470c c0470c = (C0470c) view.getLayoutParams();
        int i11 = ((ViewGroup.MarginLayoutParams) c0470c).leftMargin;
        Rect rect = this.f2527K;
        int iM2648Q2 = m2648Q2(i9, i11 + rect.left, ((ViewGroup.MarginLayoutParams) c0470c).rightMargin + rect.right);
        int i12 = ((ViewGroup.MarginLayoutParams) c0470c).topMargin;
        Rect rect2 = this.f2527K;
        int iM2648Q22 = m2648Q2(i10, i12 + rect2.top, ((ViewGroup.MarginLayoutParams) c0470c).bottomMargin + rect2.bottom);
        if (z8 ? m2444G1(view, iM2648Q2, iM2648Q22, c0470c) : m2440E1(view, iM2648Q2, iM2648Q22, c0470c)) {
            view.measure(iM2648Q2, iM2648Q22);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: u */
    public int mo2315u(RecyclerView.C0465z c0465z) {
        return m2652U1(c0465z);
    }

    /* renamed from: u2 */
    public final void m2678u2(View view, C0470c c0470c, boolean z8) {
        if (c0470c.f2566f) {
            if (this.f2537w == 1) {
                m2677t2(view, this.f2526J, RecyclerView.AbstractC0454o.m2426K(m2466W(), m2467X(), m2485f0() + m2477c0(), ((ViewGroup.MarginLayoutParams) c0470c).height, true), z8);
                return;
            } else {
                m2677t2(view, RecyclerView.AbstractC0454o.m2426K(m2502n0(), m2504o0(), m2479d0() + m2482e0(), ((ViewGroup.MarginLayoutParams) c0470c).width, true), this.f2526J, z8);
                return;
            }
        }
        if (this.f2537w == 1) {
            m2677t2(view, RecyclerView.AbstractC0454o.m2426K(this.f2538x, m2504o0(), 0, ((ViewGroup.MarginLayoutParams) c0470c).width, false), RecyclerView.AbstractC0454o.m2426K(m2466W(), m2467X(), m2485f0() + m2477c0(), ((ViewGroup.MarginLayoutParams) c0470c).height, true), z8);
        } else {
            m2677t2(view, RecyclerView.AbstractC0454o.m2426K(m2502n0(), m2504o0(), m2479d0() + m2482e0(), ((ViewGroup.MarginLayoutParams) c0470c).width, true), RecyclerView.AbstractC0454o.m2426K(this.f2538x, m2467X(), 0, ((ViewGroup.MarginLayoutParams) c0470c).height, false), z8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: v */
    public int mo2317v(RecyclerView.C0465z c0465z) {
        return m2653V1(c0465z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: v1 */
    public int mo2247v1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return m2630E2(i9, c0461v, c0465z);
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /* renamed from: v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2679v2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, boolean z8) {
        SavedState savedState;
        C0469b c0469b = this.f2528L;
        if (!(this.f2525I == null && this.f2519C == -1) && c0465z.m2620b() == 0) {
            m2495j1(c0461v);
            c0469b.m2708c();
            return;
        }
        boolean z9 = true;
        boolean z10 = (c0469b.f2562e && this.f2519C == -1 && this.f2525I == null) ? false : true;
        if (z10) {
            c0469b.m2708c();
            if (this.f2525I != null) {
                m2639M1(c0469b);
            } else {
                m2629D2();
                c0469b.f2560c = this.f2517A;
            }
            m2640M2(c0465z, c0469b);
            c0469b.f2562e = true;
        }
        if (this.f2525I == null && this.f2519C == -1 && (c0469b.f2560c != this.f2523G || m2676s2() != this.f2524H)) {
            this.f2521E.m2685b();
            c0469b.f2561d = true;
        }
        if (m2448J() > 0 && ((savedState = this.f2525I) == null || savedState.f2549d < 1)) {
            if (c0469b.f2561d) {
                for (int i9 = 0; i9 < this.f2533s; i9++) {
                    this.f2534t[i9].m2716e();
                    int i10 = c0469b.f2559b;
                    if (i10 != Integer.MIN_VALUE) {
                        this.f2534t[i9].m2733v(i10);
                    }
                }
            } else if (z10 || this.f2528L.f2563f == null) {
                for (int i11 = 0; i11 < this.f2533s; i11++) {
                    this.f2534t[i11].m2713b(this.f2517A, c0469b.f2559b);
                }
                this.f2528L.m2709d(this.f2534t);
            } else {
                for (int i12 = 0; i12 < this.f2533s; i12++) {
                    C0471d c0471d = this.f2534t[i12];
                    c0471d.m2716e();
                    c0471d.m2733v(this.f2528L.f2563f[i12]);
                }
            }
        }
        m2517w(c0461v);
        this.f2539y.f2701a = false;
        this.f2529M = false;
        m2644O2(this.f2536v.mo2878n());
        m2642N2(c0469b.f2558a, c0465z);
        if (c0469b.f2560c) {
            m2631F2(-1);
            m2658a2(c0461v, this.f2539y, c0465z);
            m2631F2(1);
            C0479h c0479h = this.f2539y;
            c0479h.f2703c = c0469b.f2558a + c0479h.f2704d;
            m2658a2(c0461v, c0479h, c0465z);
        } else {
            m2631F2(1);
            m2658a2(c0461v, this.f2539y, c0465z);
            m2631F2(-1);
            C0479h c0479h2 = this.f2539y;
            c0479h2.f2703c = c0469b.f2558a + c0479h2.f2704d;
            m2658a2(c0461v, c0479h2, c0465z);
        }
        m2628C2();
        if (m2448J() > 0) {
            if (this.f2517A) {
                m2664g2(c0461v, c0465z, true);
                m2665h2(c0461v, c0465z, false);
            } else {
                m2665h2(c0461v, c0465z, true);
                m2664g2(c0461v, c0465z, false);
            }
        }
        if (!z8 || c0465z.m2623e()) {
            z9 = false;
        } else if (this.f2522F != 0 && m2448J() > 0 && (this.f2529M || m2674q2() != null)) {
            m2503n1(this.f2532P);
            if (!m2649R1()) {
            }
        }
        if (c0465z.m2623e()) {
            this.f2528L.m2708c();
        }
        this.f2523G = c0469b.f2560c;
        this.f2524H = m2676s2();
        if (z9) {
            this.f2528L.m2708c();
            m2679v2(c0461v, c0465z, false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: w1 */
    public void mo2319w1(int i9) {
        SavedState savedState = this.f2525I;
        if (savedState != null && savedState.f2547b != i9) {
            savedState.m2702a();
        }
        this.f2519C = i9;
        this.f2520D = Integer.MIN_VALUE;
        m2512s1();
    }

    /* renamed from: w2 */
    public final boolean m2680w2(int i9) {
        if (this.f2537w == 0) {
            return (i9 == -1) != this.f2517A;
        }
        return ((i9 == -1) == this.f2517A) == m2676s2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: x1 */
    public int mo2248x1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        return m2630E2(i9, c0461v, c0465z);
    }

    /* renamed from: x2 */
    public void m2681x2(int i9, RecyclerView.C0465z c0465z) {
        int iM2666i2;
        int i10;
        if (i9 > 0) {
            iM2666i2 = m2667j2();
            i10 = 1;
        } else {
            iM2666i2 = m2666i2();
            i10 = -1;
        }
        this.f2539y.f2701a = true;
        m2642N2(iM2666i2, c0465z);
        m2631F2(i10);
        C0479h c0479h = this.f2539y;
        c0479h.f2703c = iM2666i2 + c0479h.f2704d;
        c0479h.f2702b = Math.abs(i9);
    }

    /* renamed from: y2 */
    public final void m2682y2(View view) {
        for (int i9 = this.f2533s - 1; i9 >= 0; i9--) {
            this.f2534t[i9].m2732u(view);
        }
    }

    /* renamed from: z2 */
    public final void m2683z2(RecyclerView.C0461v c0461v, C0479h c0479h) {
        if (!c0479h.f2701a || c0479h.f2709i) {
            return;
        }
        if (c0479h.f2702b == 0) {
            if (c0479h.f2705e == -1) {
                m2626A2(c0461v, c0479h.f2707g);
                return;
            } else {
                m2627B2(c0461v, c0479h.f2706f);
                return;
            }
        }
        if (c0479h.f2705e != -1) {
            int iM2670m2 = m2670m2(c0479h.f2707g) - c0479h.f2707g;
            m2627B2(c0461v, iM2670m2 < 0 ? c0479h.f2706f : Math.min(iM2670m2, c0479h.f2702b) + c0479h.f2706f);
        } else {
            int i9 = c0479h.f2706f;
            int iM2669l2 = i9 - m2669l2(i9);
            m2626A2(c0461v, iM2669l2 < 0 ? c0479h.f2707g : c0479h.f2707g - Math.min(iM2669l2, c0479h.f2702b));
        }
    }

    public static class LazySpanLookup {

        /* renamed from: a */
        public int[] f2541a;

        /* renamed from: b */
        public List<FullSpanItem> f2542b;

        /* renamed from: a */
        public void m2684a(FullSpanItem fullSpanItem) {
            if (this.f2542b == null) {
                this.f2542b = new ArrayList();
            }
            int size = this.f2542b.size();
            for (int i9 = 0; i9 < size; i9++) {
                FullSpanItem fullSpanItem2 = this.f2542b.get(i9);
                if (fullSpanItem2.f2543b == fullSpanItem.f2543b) {
                    this.f2542b.remove(i9);
                }
                if (fullSpanItem2.f2543b >= fullSpanItem.f2543b) {
                    this.f2542b.add(i9, fullSpanItem);
                    return;
                }
            }
            this.f2542b.add(fullSpanItem);
        }

        /* renamed from: b */
        public void m2685b() {
            int[] iArr = this.f2541a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f2542b = null;
        }

        /* renamed from: c */
        public void m2686c(int i9) {
            int[] iArr = this.f2541a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i9, 10) + 1];
                this.f2541a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i9 >= iArr.length) {
                int[] iArr3 = new int[m2698o(i9)];
                this.f2541a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f2541a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        /* renamed from: d */
        public int m2687d(int i9) {
            List<FullSpanItem> list = this.f2542b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.f2542b.get(size).f2543b >= i9) {
                        this.f2542b.remove(size);
                    }
                }
            }
            return m2691h(i9);
        }

        /* renamed from: e */
        public FullSpanItem m2688e(int i9, int i10, int i11, boolean z8) {
            List<FullSpanItem> list = this.f2542b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i12 = 0; i12 < size; i12++) {
                FullSpanItem fullSpanItem = this.f2542b.get(i12);
                int i13 = fullSpanItem.f2543b;
                if (i13 >= i10) {
                    return null;
                }
                if (i13 >= i9 && (i11 == 0 || fullSpanItem.f2544c == i11 || (z8 && fullSpanItem.f2546e))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        /* renamed from: f */
        public FullSpanItem m2689f(int i9) {
            List<FullSpanItem> list = this.f2542b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2542b.get(size);
                if (fullSpanItem.f2543b == i9) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        /* renamed from: g */
        public int m2690g(int i9) {
            int[] iArr = this.f2541a;
            if (iArr == null || i9 >= iArr.length) {
                return -1;
            }
            return iArr[i9];
        }

        /* renamed from: h */
        public int m2691h(int i9) {
            int[] iArr = this.f2541a;
            if (iArr == null || i9 >= iArr.length) {
                return -1;
            }
            int iM2692i = m2692i(i9);
            if (iM2692i == -1) {
                int[] iArr2 = this.f2541a;
                Arrays.fill(iArr2, i9, iArr2.length, -1);
                return this.f2541a.length;
            }
            int i10 = iM2692i + 1;
            Arrays.fill(this.f2541a, i9, i10, -1);
            return i10;
        }

        /* renamed from: i */
        public final int m2692i(int i9) {
            if (this.f2542b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemM2689f = m2689f(i9);
            if (fullSpanItemM2689f != null) {
                this.f2542b.remove(fullSpanItemM2689f);
            }
            int size = this.f2542b.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    i10 = -1;
                    break;
                }
                if (this.f2542b.get(i10).f2543b >= i9) {
                    break;
                }
                i10++;
            }
            if (i10 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.f2542b.get(i10);
            this.f2542b.remove(i10);
            return fullSpanItem.f2543b;
        }

        /* renamed from: j */
        public void m2693j(int i9, int i10) {
            int[] iArr = this.f2541a;
            if (iArr == null || i9 >= iArr.length) {
                return;
            }
            int i11 = i9 + i10;
            m2686c(i11);
            int[] iArr2 = this.f2541a;
            System.arraycopy(iArr2, i9, iArr2, i11, (iArr2.length - i9) - i10);
            Arrays.fill(this.f2541a, i9, i11, -1);
            m2695l(i9, i10);
        }

        /* renamed from: k */
        public void m2694k(int i9, int i10) {
            int[] iArr = this.f2541a;
            if (iArr == null || i9 >= iArr.length) {
                return;
            }
            int i11 = i9 + i10;
            m2686c(i11);
            int[] iArr2 = this.f2541a;
            System.arraycopy(iArr2, i11, iArr2, i9, (iArr2.length - i9) - i10);
            int[] iArr3 = this.f2541a;
            Arrays.fill(iArr3, iArr3.length - i10, iArr3.length, -1);
            m2696m(i9, i10);
        }

        /* renamed from: l */
        public final void m2695l(int i9, int i10) {
            List<FullSpanItem> list = this.f2542b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2542b.get(size);
                int i11 = fullSpanItem.f2543b;
                if (i11 >= i9) {
                    fullSpanItem.f2543b = i11 + i10;
                }
            }
        }

        /* renamed from: m */
        public final void m2696m(int i9, int i10) {
            List<FullSpanItem> list = this.f2542b;
            if (list == null) {
                return;
            }
            int i11 = i9 + i10;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.f2542b.get(size);
                int i12 = fullSpanItem.f2543b;
                if (i12 >= i9) {
                    if (i12 < i11) {
                        this.f2542b.remove(size);
                    } else {
                        fullSpanItem.f2543b = i12 - i10;
                    }
                }
            }
        }

        /* renamed from: n */
        public void m2697n(int i9, C0471d c0471d) {
            m2686c(i9);
            this.f2541a[i9] = c0471d.f2571e;
        }

        /* renamed from: o */
        public int m2698o(int i9) {
            int length = this.f2541a.length;
            while (length <= i9) {
                length *= 2;
            }
            return length;
        }

        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new C0466a();

            /* renamed from: b */
            public int f2543b;

            /* renamed from: c */
            public int f2544c;

            /* renamed from: d */
            public int[] f2545d;

            /* renamed from: e */
            public boolean f2546e;

            /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem$a */
            public static class C0466a implements Parcelable.Creator<FullSpanItem> {
                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i9) {
                    return new FullSpanItem[i9];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.f2543b = parcel.readInt();
                this.f2544c = parcel.readInt();
                this.f2546e = parcel.readInt() == 1;
                int i9 = parcel.readInt();
                if (i9 > 0) {
                    int[] iArr = new int[i9];
                    this.f2545d = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            /* renamed from: a */
            public int m2699a(int i9) {
                int[] iArr = this.f2545d;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i9];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f2543b + ", mGapDir=" + this.f2544c + ", mHasUnwantedGapAfter=" + this.f2546e + ", mGapPerSpan=" + Arrays.toString(this.f2545d) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i9) {
                parcel.writeInt(this.f2543b);
                parcel.writeInt(this.f2544c);
                parcel.writeInt(this.f2546e ? 1 : 0);
                int[] iArr = this.f2545d;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f2545d);
                }
            }

            public FullSpanItem() {
            }
        }
    }
}
