package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import p052e0.C4704m;

/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {

    /* renamed from: H */
    public boolean f2367H;

    /* renamed from: I */
    public int f2368I;

    /* renamed from: J */
    public int[] f2369J;

    /* renamed from: K */
    public View[] f2370K;

    /* renamed from: L */
    public final SparseIntArray f2371L;

    /* renamed from: M */
    public final SparseIntArray f2372M;

    /* renamed from: N */
    public AbstractC0431c f2373N;

    /* renamed from: O */
    public final Rect f2374O;

    /* renamed from: androidx.recyclerview.widget.GridLayoutManager$a */
    public static final class C0429a extends AbstractC0431c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.AbstractC0431c
        /* renamed from: c */
        public int mo2249c(int i9, int i10) {
            return i9 % i10;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.AbstractC0431c
        /* renamed from: d */
        public int mo2250d(int i9) {
            return 1;
        }
    }

    /* renamed from: androidx.recyclerview.widget.GridLayoutManager$c */
    public static abstract class AbstractC0431c {

        /* renamed from: a */
        public final SparseIntArray f2377a = new SparseIntArray();

        /* renamed from: b */
        public boolean f2378b = false;

        /* renamed from: a */
        public int m2253a(int i9, int i10) {
            if (!this.f2378b) {
                return mo2249c(i9, i10);
            }
            int i11 = this.f2377a.get(i9, -1);
            if (i11 != -1) {
                return i11;
            }
            int iMo2249c = mo2249c(i9, i10);
            this.f2377a.put(i9, iMo2249c);
            return iMo2249c;
        }

        /* renamed from: b */
        public int m2254b(int i9, int i10) {
            int iMo2250d = mo2250d(i9);
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 0; i13 < i9; i13++) {
                int iMo2250d2 = mo2250d(i13);
                i11 += iMo2250d2;
                if (i11 == i10) {
                    i12++;
                    i11 = 0;
                } else if (i11 > i10) {
                    i12++;
                    i11 = iMo2250d2;
                }
            }
            return i11 + iMo2250d > i10 ? i12 + 1 : i12;
        }

        /* renamed from: c */
        public abstract int mo2249c(int i9, int i10);

        /* renamed from: d */
        public abstract int mo2250d(int i9);

        /* renamed from: e */
        public void m2255e() {
            this.f2377a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i9, int i10) {
        super(context, attributeSet, i9, i10);
        this.f2367H = false;
        this.f2368I = -1;
        this.f2371L = new SparseIntArray();
        this.f2372M = new SparseIntArray();
        this.f2373N = new C0429a();
        this.f2374O = new Rect();
        m2240Z2(RecyclerView.AbstractC0454o.m2427h0(context, attributeSet, i9, i10).f2463b);
    }

    /* renamed from: O2 */
    public static int[] m2209O2(int[] iArr, int i9, int i10) {
        int i11;
        if (iArr == null || iArr.length != i9 + 1 || iArr[iArr.length - 1] != i10) {
            iArr = new int[i9 + 1];
        }
        int i12 = 0;
        iArr[0] = 0;
        int i13 = i10 / i9;
        int i14 = i10 % i9;
        int i15 = 0;
        for (int i16 = 1; i16 <= i9; i16++) {
            i12 += i14;
            if (i12 <= 0 || i9 - i12 >= i14) {
                i11 = i13;
            } else {
                i11 = i13 + 1;
                i12 -= i9;
            }
            i15 += i11;
            iArr[i16] = i15;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: B1 */
    public void mo2210B1(Rect rect, int i9, int i10) {
        int iM2428n;
        int iM2428n2;
        if (this.f2369J == null) {
            super.mo2210B1(rect, i9, i10);
        }
        int iM2479d0 = m2479d0() + m2482e0();
        int iM2485f0 = m2485f0() + m2477c0();
        if (this.f2386s == 1) {
            iM2428n2 = RecyclerView.AbstractC0454o.m2428n(i10, rect.height() + iM2485f0, m2472a0());
            int[] iArr = this.f2369J;
            iM2428n = RecyclerView.AbstractC0454o.m2428n(i9, iArr[iArr.length - 1] + iM2479d0, m2475b0());
        } else {
            iM2428n = RecyclerView.AbstractC0454o.m2428n(i9, rect.width() + iM2479d0, m2475b0());
            int[] iArr2 = this.f2369J;
            iM2428n2 = RecyclerView.AbstractC0454o.m2428n(i10, iArr2[iArr2.length - 1] + iM2485f0, m2472a0());
        }
        m2432A1(iM2428n, iM2428n2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /* renamed from: C2 */
    public void mo2211C2(boolean z8) {
        if (z8) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.mo2211C2(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: D */
    public RecyclerView.C0455p mo2212D() {
        return this.f2386s == 0 ? new C0430b(-2, -1) : new C0430b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: E */
    public RecyclerView.C0455p mo2213E(Context context, AttributeSet attributeSet) {
        return new C0430b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: F */
    public RecyclerView.C0455p mo2214F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0430b((ViewGroup.MarginLayoutParams) layoutParams) : new C0430b(layoutParams);
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        if (r13 == (r2 > r15)) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0107  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: I0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View mo2215I0(View view, int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        int iM2448J;
        int i10;
        int iM2448J2;
        View view2;
        View view3;
        int i11;
        int i12;
        boolean z8;
        int i13;
        int i14;
        RecyclerView.C0461v c0461v2 = c0461v;
        RecyclerView.C0465z c0465z2 = c0465z;
        View viewM2433B = m2433B(view);
        View view4 = null;
        if (viewM2433B == null) {
            return null;
        }
        C0430b c0430b = (C0430b) viewM2433B.getLayoutParams();
        int i15 = c0430b.f2375e;
        int i16 = c0430b.f2376f + i15;
        if (super.mo2215I0(view, i9, c0461v, c0465z) == null) {
            return null;
        }
        if ((m2274P1(i9) == 1) != this.f2391x) {
            iM2448J2 = m2448J() - 1;
            iM2448J = -1;
            i10 = -1;
        } else {
            iM2448J = m2448J();
            i10 = 1;
            iM2448J2 = 0;
        }
        boolean z9 = this.f2386s == 1 && m2305o2();
        int iM2231T2 = m2231T2(c0461v2, c0465z2, iM2448J2);
        int i17 = -1;
        int i18 = -1;
        int iMin = 0;
        int iMin2 = 0;
        int i19 = iM2448J2;
        View view5 = null;
        while (i19 != iM2448J) {
            int iM2231T22 = m2231T2(c0461v2, c0465z2, i19);
            View viewM2446I = m2446I(i19);
            if (viewM2446I == viewM2433B) {
                break;
            }
            if (!viewM2446I.hasFocusable() || iM2231T22 == iM2231T2) {
                C0430b c0430b2 = (C0430b) viewM2446I.getLayoutParams();
                int i20 = c0430b2.f2375e;
                view2 = viewM2433B;
                int i21 = c0430b2.f2376f + i20;
                if (viewM2446I.hasFocusable() && i20 == i15 && i21 == i16) {
                    return viewM2446I;
                }
                if (!(viewM2446I.hasFocusable() && view4 == null) && (viewM2446I.hasFocusable() || view5 != null)) {
                    view3 = view5;
                    int iMin3 = Math.min(i21, i16) - Math.max(i20, i15);
                    if (!viewM2446I.hasFocusable()) {
                        if (view4 == null) {
                            i11 = iMin;
                            i12 = iM2448J;
                            if (m2520x0(viewM2446I, false, true)) {
                                i13 = iMin2;
                                if (iMin3 > i13) {
                                    i14 = i18;
                                    if (!z8) {
                                        if (viewM2446I.hasFocusable()) {
                                            i17 = c0430b2.f2375e;
                                            i18 = i14;
                                            iMin2 = i13;
                                            view5 = view3;
                                            view4 = viewM2446I;
                                            iMin = Math.min(i21, i16) - Math.max(i20, i15);
                                        } else {
                                            int i22 = c0430b2.f2375e;
                                            iMin2 = Math.min(i21, i16) - Math.max(i20, i15);
                                            i18 = i22;
                                            iMin = i11;
                                            view5 = viewM2446I;
                                        }
                                    }
                                    i19 += i10;
                                    c0461v2 = c0461v;
                                    c0465z2 = c0465z;
                                    viewM2433B = view2;
                                    iM2448J = i12;
                                } else if (iMin3 == i13) {
                                    i14 = i18;
                                    if (z9 == (i20 > i14)) {
                                        z8 = true;
                                    }
                                    if (!z8) {
                                    }
                                    i19 += i10;
                                    c0461v2 = c0461v;
                                    c0465z2 = c0465z;
                                    viewM2433B = view2;
                                    iM2448J = i12;
                                } else {
                                    i14 = i18;
                                }
                            }
                            z8 = false;
                            if (!z8) {
                            }
                            i19 += i10;
                            c0461v2 = c0461v;
                            c0465z2 = c0465z;
                            viewM2433B = view2;
                            iM2448J = i12;
                        }
                        i14 = i18;
                        i13 = iMin2;
                        z8 = false;
                        if (!z8) {
                        }
                        i19 += i10;
                        c0461v2 = c0461v;
                        c0465z2 = c0465z;
                        viewM2433B = view2;
                        iM2448J = i12;
                    } else if (iMin3 <= iMin) {
                        if (iMin3 == iMin) {
                        }
                    }
                    i11 = iMin;
                    i12 = iM2448J;
                    i14 = i18;
                    i13 = iMin2;
                    z8 = false;
                    if (!z8) {
                    }
                    i19 += i10;
                    c0461v2 = c0461v;
                    c0465z2 = c0465z;
                    viewM2433B = view2;
                    iM2448J = i12;
                } else {
                    view3 = view5;
                }
                i11 = iMin;
                i12 = iM2448J;
                i14 = i18;
                i13 = iMin2;
                z8 = true;
                if (!z8) {
                }
                i19 += i10;
                c0461v2 = c0461v;
                c0465z2 = c0465z;
                viewM2433B = view2;
                iM2448J = i12;
            } else {
                if (view4 != null) {
                    break;
                }
                view2 = viewM2433B;
                view3 = view5;
                i11 = iMin;
                i12 = iM2448J;
                i14 = i18;
                i13 = iMin2;
            }
            i18 = i14;
            iMin2 = i13;
            iMin = i11;
            view5 = view3;
            i19 += i10;
            c0461v2 = c0461v;
            c0465z2 = c0465z;
            viewM2433B = view2;
            iM2448J = i12;
        }
        return view4 != null ? view4 : view5;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: K1 */
    public boolean mo2216K1() {
        return this.f2382D == null && !this.f2367H;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /* renamed from: L1 */
    public void mo2217L1(RecyclerView.C0465z c0465z, LinearLayoutManager.C0435c c0435c, RecyclerView.AbstractC0454o.c cVar) {
        int iMo2250d = this.f2368I;
        for (int i9 = 0; i9 < this.f2368I && c0435c.m2336c(c0465z) && iMo2250d > 0; i9++) {
            int i10 = c0435c.f2409d;
            cVar.mo2532a(i10, Math.max(0, c0435c.f2412g));
            iMo2250d -= this.f2373N.mo2250d(i10);
            c0435c.f2409d += c0435c.f2410e;
        }
    }

    /* renamed from: L2 */
    public final void m2218L2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9, int i10, boolean z8) {
        int i11;
        int i12;
        int i13;
        int i14 = 0;
        if (z8) {
            i13 = 1;
            i12 = i9;
            i11 = 0;
        } else {
            i11 = i9 - 1;
            i12 = -1;
            i13 = -1;
        }
        while (i11 != i12) {
            View view = this.f2370K[i11];
            C0430b c0430b = (C0430b) view.getLayoutParams();
            int iM2234V2 = m2234V2(c0461v, c0465z, m2487g0(view));
            c0430b.f2376f = iM2234V2;
            c0430b.f2375e = i14;
            i14 += iM2234V2;
            i11 += i13;
        }
    }

    /* renamed from: M2 */
    public final void m2219M2() {
        int iM2448J = m2448J();
        for (int i9 = 0; i9 < iM2448J; i9++) {
            C0430b c0430b = (C0430b) m2446I(i9).getLayoutParams();
            int iM2533a = c0430b.m2533a();
            this.f2371L.put(iM2533a, c0430b.m2252f());
            this.f2372M.put(iM2533a, c0430b.m2251e());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: N */
    public int mo2220N(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (this.f2386s == 1) {
            return this.f2368I;
        }
        if (c0465z.m2620b() < 1) {
            return 0;
        }
        return m2231T2(c0461v, c0465z, c0465z.m2620b() - 1) + 1;
    }

    /* renamed from: N2 */
    public final void m2221N2(int i9) {
        this.f2369J = m2209O2(this.f2369J, this.f2368I, i9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: O0 */
    public void mo2222O0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, View view, C4704m c4704m) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C0430b)) {
            super.m2455N0(view, c4704m);
            return;
        }
        C0430b c0430b = (C0430b) layoutParams;
        int iM2231T2 = m2231T2(c0461v, c0465z, c0430b.m2533a());
        if (this.f2386s == 0) {
            c4704m.m18784X(C4704m.c.m18837a(c0430b.m2251e(), c0430b.m2252f(), iM2231T2, 1, this.f2368I > 1 && c0430b.m2252f() == this.f2368I, false));
        } else {
            c4704m.m18784X(C4704m.c.m18837a(iM2231T2, 1, c0430b.m2251e(), c0430b.m2252f(), this.f2368I > 1 && c0430b.m2252f() == this.f2368I, false));
        }
    }

    /* renamed from: P2 */
    public final void m2223P2() {
        this.f2371L.clear();
        this.f2372M.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: Q0 */
    public void mo2224Q0(RecyclerView recyclerView, int i9, int i10) {
        this.f2373N.m2255e();
    }

    /* renamed from: Q2 */
    public final void m2225Q2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, LinearLayoutManager.C0433a c0433a, int i9) {
        boolean z8 = i9 == 1;
        int iM2232U2 = m2232U2(c0461v, c0465z, c0433a.f2398b);
        if (z8) {
            while (iM2232U2 > 0) {
                int i10 = c0433a.f2398b;
                if (i10 <= 0) {
                    return;
                }
                int i11 = i10 - 1;
                c0433a.f2398b = i11;
                iM2232U2 = m2232U2(c0461v, c0465z, i11);
            }
            return;
        }
        int iM2620b = c0465z.m2620b() - 1;
        int i12 = c0433a.f2398b;
        while (i12 < iM2620b) {
            int i13 = i12 + 1;
            int iM2232U22 = m2232U2(c0461v, c0465z, i13);
            if (iM2232U22 <= iM2232U2) {
                break;
            }
            i12 = i13;
            iM2232U2 = iM2232U22;
        }
        c0433a.f2398b = i12;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: R0 */
    public void mo2226R0(RecyclerView recyclerView) {
        this.f2373N.m2255e();
    }

    /* renamed from: R2 */
    public final void m2227R2() {
        View[] viewArr = this.f2370K;
        if (viewArr == null || viewArr.length != this.f2368I) {
            this.f2370K = new View[this.f2368I];
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: S0 */
    public void mo2228S0(RecyclerView recyclerView, int i9, int i10, int i11) {
        this.f2373N.m2255e();
    }

    /* renamed from: S2 */
    public int m2229S2(int i9, int i10) {
        if (this.f2386s != 1 || !m2305o2()) {
            int[] iArr = this.f2369J;
            return iArr[i10 + i9] - iArr[i9];
        }
        int[] iArr2 = this.f2369J;
        int i11 = this.f2368I;
        return iArr2[i11 - i9] - iArr2[(i11 - i9) - i10];
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: T0 */
    public void mo2230T0(RecyclerView recyclerView, int i9, int i10) {
        this.f2373N.m2255e();
    }

    /* renamed from: T2 */
    public final int m2231T2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9) {
        if (!c0465z.m2623e()) {
            return this.f2373N.m2254b(i9, this.f2368I);
        }
        int iM2574f = c0461v.m2574f(i9);
        if (iM2574f != -1) {
            return this.f2373N.m2254b(iM2574f, this.f2368I);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i9);
        return 0;
    }

    /* renamed from: U2 */
    public final int m2232U2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9) {
        if (!c0465z.m2623e()) {
            return this.f2373N.m2253a(i9, this.f2368I);
        }
        int i10 = this.f2372M.get(i9, -1);
        if (i10 != -1) {
            return i10;
        }
        int iM2574f = c0461v.m2574f(i9);
        if (iM2574f != -1) {
            return this.f2373N.m2253a(iM2574f, this.f2368I);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i9);
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: V0 */
    public void mo2233V0(RecyclerView recyclerView, int i9, int i10, Object obj) {
        this.f2373N.m2255e();
    }

    /* renamed from: V2 */
    public final int m2234V2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, int i9) {
        if (!c0465z.m2623e()) {
            return this.f2373N.mo2250d(i9);
        }
        int i10 = this.f2371L.get(i9, -1);
        if (i10 != -1) {
            return i10;
        }
        int iM2574f = c0461v.m2574f(i9);
        if (iM2574f != -1) {
            return this.f2373N.mo2250d(iM2574f);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i9);
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: W0 */
    public void mo2235W0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (c0465z.m2623e()) {
            m2219M2();
        }
        super.mo2235W0(c0461v, c0465z);
        m2223P2();
    }

    /* renamed from: W2 */
    public final void m2236W2(float f9, int i9) {
        m2221N2(Math.max(Math.round(f9 * this.f2368I), i9));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: X0 */
    public void mo2237X0(RecyclerView.C0465z c0465z) {
        super.mo2237X0(c0465z);
        this.f2367H = false;
    }

    /* renamed from: X2 */
    public final void m2238X2(View view, int i9, boolean z8) {
        int iM2426K;
        int iM2426K2;
        C0430b c0430b = (C0430b) view.getLayoutParams();
        Rect rect = c0430b.f2467b;
        int i10 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) c0430b).topMargin + ((ViewGroup.MarginLayoutParams) c0430b).bottomMargin;
        int i11 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) c0430b).leftMargin + ((ViewGroup.MarginLayoutParams) c0430b).rightMargin;
        int iM2229S2 = m2229S2(c0430b.f2375e, c0430b.f2376f);
        if (this.f2386s == 1) {
            iM2426K2 = RecyclerView.AbstractC0454o.m2426K(iM2229S2, i9, i11, ((ViewGroup.MarginLayoutParams) c0430b).width, false);
            iM2426K = RecyclerView.AbstractC0454o.m2426K(this.f2388u.mo2878n(), m2467X(), i10, ((ViewGroup.MarginLayoutParams) c0430b).height, true);
        } else {
            int iM2426K3 = RecyclerView.AbstractC0454o.m2426K(iM2229S2, i9, i10, ((ViewGroup.MarginLayoutParams) c0430b).height, false);
            int iM2426K4 = RecyclerView.AbstractC0454o.m2426K(this.f2388u.mo2878n(), m2504o0(), i11, ((ViewGroup.MarginLayoutParams) c0430b).width, true);
            iM2426K = iM2426K3;
            iM2426K2 = iM2426K4;
        }
        m2239Y2(view, iM2426K2, iM2426K, z8);
    }

    /* renamed from: Y2 */
    public final void m2239Y2(View view, int i9, int i10, boolean z8) {
        RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
        if (z8 ? m2444G1(view, i9, i10, c0455p) : m2440E1(view, i9, i10, c0455p)) {
            view.measure(i9, i10);
        }
    }

    /* renamed from: Z2 */
    public void m2240Z2(int i9) {
        if (i9 == this.f2368I) {
            return;
        }
        this.f2367H = true;
        if (i9 >= 1) {
            this.f2368I = i9;
            this.f2373N.m2255e();
            m2512s1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i9);
        }
    }

    /* renamed from: a3 */
    public final void m2241a3() {
        int iM2466W;
        int iM2485f0;
        if (m2303n2() == 1) {
            iM2466W = m2502n0() - m2482e0();
            iM2485f0 = m2479d0();
        } else {
            iM2466W = m2466W() - m2477c0();
            iM2485f0 = m2485f0();
        }
        m2221N2(iM2466W - iM2485f0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
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
            if (iM2487g0 >= 0 && iM2487g0 < i11 && m2232U2(c0461v, c0465z, iM2487g0) == 0) {
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
    /* renamed from: j0 */
    public int mo2243j0(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        if (this.f2386s == 0) {
            return this.f2368I;
        }
        if (c0465z.m2620b() < 1) {
            return 0;
        }
        return m2231T2(c0461v, c0465z, c0465z.m2620b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: m */
    public boolean mo2244m(RecyclerView.C0455p c0455p) {
        return c0455p instanceof C0430b;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /* renamed from: p2 */
    public void mo2245p2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, LinearLayoutManager.C0435c c0435c, LinearLayoutManager.C0434b c0434b) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iMo2870f;
        int iMo2870f2;
        int iMo2870f3;
        int iM2426K;
        int iM2426K2;
        boolean z8;
        View viewM2337d;
        int iMo2876l = this.f2388u.mo2876l();
        boolean z9 = iMo2876l != 1073741824;
        int i14 = m2448J() > 0 ? this.f2369J[this.f2368I] : 0;
        if (z9) {
            m2241a3();
        }
        boolean z10 = c0435c.f2410e == 1;
        int iM2232U2 = this.f2368I;
        if (!z10) {
            iM2232U2 = m2232U2(c0461v, c0465z, c0435c.f2409d) + m2234V2(c0461v, c0465z, c0435c.f2409d);
        }
        int i15 = 0;
        int i16 = 0;
        while (i16 < this.f2368I && c0435c.m2336c(c0465z) && iM2232U2 > 0) {
            int i17 = c0435c.f2409d;
            int iM2234V2 = m2234V2(c0461v, c0465z, i17);
            if (iM2234V2 > this.f2368I) {
                throw new IllegalArgumentException("Item at position " + i17 + " requires " + iM2234V2 + " spans but GridLayoutManager has only " + this.f2368I + " spans.");
            }
            iM2232U2 -= iM2234V2;
            if (iM2232U2 < 0 || (viewM2337d = c0435c.m2337d(c0461v)) == null) {
                break;
            }
            i15 += iM2234V2;
            this.f2370K[i16] = viewM2337d;
            i16++;
        }
        if (i16 == 0) {
            c0434b.f2403b = true;
            return;
        }
        int i18 = i16;
        m2218L2(c0461v, c0465z, i16, i15, z10);
        float f9 = BitmapDescriptorFactory.HUE_RED;
        int i19 = 0;
        for (int i20 = 0; i20 < i18; i20++) {
            View view = this.f2370K[i20];
            if (c0435c.f2416k != null) {
                z8 = false;
                if (z10) {
                    m2474b(view);
                } else {
                    m2476c(view, 0);
                }
            } else if (z10) {
                m2478d(view);
                z8 = false;
            } else {
                z8 = false;
                m2481e(view, 0);
            }
            m2494j(view, this.f2374O);
            m2238X2(view, iMo2876l, z8);
            int iMo2869e = this.f2388u.mo2869e(view);
            if (iMo2869e > i19) {
                i19 = iMo2869e;
            }
            float fMo2870f = (this.f2388u.mo2870f(view) * 1.0f) / ((C0430b) view.getLayoutParams()).f2376f;
            if (fMo2870f > f9) {
                f9 = fMo2870f;
            }
        }
        if (z9) {
            m2236W2(f9, i14);
            i19 = 0;
            for (int i21 = 0; i21 < i18; i21++) {
                View view2 = this.f2370K[i21];
                m2238X2(view2, 1073741824, true);
                int iMo2869e2 = this.f2388u.mo2869e(view2);
                if (iMo2869e2 > i19) {
                    i19 = iMo2869e2;
                }
            }
        }
        for (int i22 = 0; i22 < i18; i22++) {
            View view3 = this.f2370K[i22];
            if (this.f2388u.mo2869e(view3) != i19) {
                C0430b c0430b = (C0430b) view3.getLayoutParams();
                Rect rect = c0430b.f2467b;
                int i23 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) c0430b).topMargin + ((ViewGroup.MarginLayoutParams) c0430b).bottomMargin;
                int i24 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) c0430b).leftMargin + ((ViewGroup.MarginLayoutParams) c0430b).rightMargin;
                int iM2229S2 = m2229S2(c0430b.f2375e, c0430b.f2376f);
                if (this.f2386s == 1) {
                    iM2426K2 = RecyclerView.AbstractC0454o.m2426K(iM2229S2, 1073741824, i24, ((ViewGroup.MarginLayoutParams) c0430b).width, false);
                    iM2426K = View.MeasureSpec.makeMeasureSpec(i19 - i23, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i19 - i24, 1073741824);
                    iM2426K = RecyclerView.AbstractC0454o.m2426K(iM2229S2, 1073741824, i23, ((ViewGroup.MarginLayoutParams) c0430b).height, false);
                    iM2426K2 = iMakeMeasureSpec;
                }
                m2239Y2(view3, iM2426K2, iM2426K, true);
            }
        }
        int i25 = 0;
        c0434b.f2402a = i19;
        if (this.f2386s != 1) {
            if (c0435c.f2411f == -1) {
                int i26 = c0435c.f2407b;
                i10 = i26 - i19;
                i9 = i26;
            } else {
                int i27 = c0435c.f2407b;
                i9 = i27 + i19;
                i10 = i27;
            }
            i11 = 0;
            i12 = 0;
        } else if (c0435c.f2411f == -1) {
            int i28 = c0435c.f2407b;
            int i29 = i28 - i19;
            i10 = 0;
            i9 = 0;
            i12 = i29;
            i11 = i28;
        } else {
            i12 = c0435c.f2407b;
            i11 = i12 + i19;
            i10 = 0;
            i9 = 0;
        }
        while (i25 < i18) {
            View view4 = this.f2370K[i25];
            C0430b c0430b2 = (C0430b) view4.getLayoutParams();
            if (this.f2386s == 1) {
                if (m2305o2()) {
                    int iM2479d0 = m2479d0() + this.f2369J[this.f2368I - c0430b2.f2375e];
                    iMo2870f3 = i11;
                    iMo2870f2 = iM2479d0;
                    iMo2870f = iM2479d0 - this.f2388u.mo2870f(view4);
                } else {
                    int iM2479d02 = m2479d0() + this.f2369J[c0430b2.f2375e];
                    iMo2870f3 = i11;
                    iMo2870f = iM2479d02;
                    iMo2870f2 = this.f2388u.mo2870f(view4) + iM2479d02;
                }
                i13 = i12;
            } else {
                int iM2485f0 = m2485f0() + this.f2369J[c0430b2.f2375e];
                i13 = iM2485f0;
                iMo2870f = i10;
                iMo2870f2 = i9;
                iMo2870f3 = this.f2388u.mo2870f(view4) + iM2485f0;
            }
            m2522y0(view4, iMo2870f, i13, iMo2870f2, iMo2870f3);
            if (c0430b2.m2535c() || c0430b2.m2534b()) {
                c0434b.f2404c = true;
            }
            c0434b.f2405d |= view4.hasFocusable();
            i25++;
            i11 = iMo2870f3;
            i10 = iMo2870f;
            i9 = iMo2870f2;
            i12 = i13;
        }
        Arrays.fill(this.f2370K, (Object) null);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /* renamed from: r2 */
    public void mo2246r2(RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z, LinearLayoutManager.C0433a c0433a, int i9) {
        super.mo2246r2(c0461v, c0465z, c0433a, i9);
        m2241a3();
        if (c0465z.m2620b() > 0 && !c0465z.m2623e()) {
            m2225Q2(c0461v, c0465z, c0433a, i9);
        }
        m2227R2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: v1 */
    public int mo2247v1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        m2241a3();
        m2227R2();
        return super.mo2247v1(i9, c0461v, c0465z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.AbstractC0454o
    /* renamed from: x1 */
    public int mo2248x1(int i9, RecyclerView.C0461v c0461v, RecyclerView.C0465z c0465z) {
        m2241a3();
        m2227R2();
        return super.mo2248x1(i9, c0461v, c0465z);
    }

    /* renamed from: androidx.recyclerview.widget.GridLayoutManager$b */
    public static class C0430b extends RecyclerView.C0455p {

        /* renamed from: e */
        public int f2375e;

        /* renamed from: f */
        public int f2376f;

        public C0430b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2375e = -1;
            this.f2376f = 0;
        }

        /* renamed from: e */
        public int m2251e() {
            return this.f2375e;
        }

        /* renamed from: f */
        public int m2252f() {
            return this.f2376f;
        }

        public C0430b(int i9, int i10) {
            super(i9, i10);
            this.f2375e = -1;
            this.f2376f = 0;
        }

        public C0430b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2375e = -1;
            this.f2376f = 0;
        }

        public C0430b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2375e = -1;
            this.f2376f = 0;
        }
    }
}
