package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import p251z.C6800f;

/* renamed from: androidx.recyclerview.widget.g */
/* loaded from: classes.dex */
public final class RunnableC0478g implements Runnable {

    /* renamed from: f */
    public static final ThreadLocal<RunnableC0478g> f2686f = new ThreadLocal<>();

    /* renamed from: g */
    public static Comparator<c> f2687g = new a();

    /* renamed from: c */
    public long f2689c;

    /* renamed from: d */
    public long f2690d;

    /* renamed from: b */
    public ArrayList<RecyclerView> f2688b = new ArrayList<>();

    /* renamed from: e */
    public ArrayList<c> f2691e = new ArrayList<>();

    /* renamed from: androidx.recyclerview.widget.g$a */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f2699d;
            if ((recyclerView == null) != (cVar2.f2699d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z8 = cVar.f2696a;
            if (z8 != cVar2.f2696a) {
                return z8 ? -1 : 1;
            }
            int i9 = cVar2.f2697b - cVar.f2697b;
            if (i9 != 0) {
                return i9;
            }
            int i10 = cVar.f2698c - cVar2.f2698c;
            if (i10 != 0) {
                return i10;
            }
            return 0;
        }
    }

    /* renamed from: androidx.recyclerview.widget.g$b */
    public static class b implements RecyclerView.AbstractC0454o.c {

        /* renamed from: a */
        public int f2692a;

        /* renamed from: b */
        public int f2693b;

        /* renamed from: c */
        public int[] f2694c;

        /* renamed from: d */
        public int f2695d;

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0454o.c
        /* renamed from: a */
        public void mo2532a(int i9, int i10) {
            if (i9 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i10 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i11 = this.f2695d * 2;
            int[] iArr = this.f2694c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f2694c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i11 >= iArr.length) {
                int[] iArr3 = new int[i11 * 2];
                this.f2694c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f2694c;
            iArr4[i11] = i9;
            iArr4[i11 + 1] = i10;
            this.f2695d++;
        }

        /* renamed from: b */
        public void m2842b() {
            int[] iArr = this.f2694c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f2695d = 0;
        }

        /* renamed from: c */
        public void m2843c(RecyclerView recyclerView, boolean z8) {
            this.f2695d = 0;
            int[] iArr = this.f2694c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.AbstractC0454o abstractC0454o = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || abstractC0454o == null || !abstractC0454o.m2513t0()) {
                return;
            }
            if (z8) {
                if (!recyclerView.mAdapterHelper.m2749p()) {
                    abstractC0454o.mo2306p(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                abstractC0454o.mo2304o(this.f2692a, this.f2693b, recyclerView.mState, this);
            }
            int i9 = this.f2695d;
            if (i9 > abstractC0454o.f2454m) {
                abstractC0454o.f2454m = i9;
                abstractC0454o.f2455n = z8;
                recyclerView.mRecycler.m2566K();
            }
        }

        /* renamed from: d */
        public boolean m2844d(int i9) {
            if (this.f2694c != null) {
                int i10 = this.f2695d * 2;
                for (int i11 = 0; i11 < i10; i11 += 2) {
                    if (this.f2694c[i11] == i9) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* renamed from: e */
        public void m2845e(int i9, int i10) {
            this.f2692a = i9;
            this.f2693b = i10;
        }
    }

    /* renamed from: androidx.recyclerview.widget.g$c */
    public static class c {

        /* renamed from: a */
        public boolean f2696a;

        /* renamed from: b */
        public int f2697b;

        /* renamed from: c */
        public int f2698c;

        /* renamed from: d */
        public RecyclerView f2699d;

        /* renamed from: e */
        public int f2700e;

        /* renamed from: a */
        public void m2846a() {
            this.f2696a = false;
            this.f2697b = 0;
            this.f2698c = 0;
            this.f2699d = null;
            this.f2700e = 0;
        }
    }

    /* renamed from: e */
    public static boolean m2831e(RecyclerView recyclerView, int i9) {
        int iM2775j = recyclerView.mChildHelper.m2775j();
        for (int i10 = 0; i10 < iM2775j; i10++) {
            RecyclerView.AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.m2774i(i10));
            if (childViewHolderInt.mPosition == i9 && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void m2832a(RecyclerView recyclerView) {
        this.f2688b.add(recyclerView);
    }

    /* renamed from: b */
    public final void m2833b() {
        c cVar;
        int size = this.f2688b.size();
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView recyclerView = this.f2688b.get(i10);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.m2843c(recyclerView, false);
                i9 += recyclerView.mPrefetchRegistry.f2695d;
            }
        }
        this.f2691e.ensureCapacity(i9);
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            RecyclerView recyclerView2 = this.f2688b.get(i12);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int iAbs = Math.abs(bVar.f2692a) + Math.abs(bVar.f2693b);
                for (int i13 = 0; i13 < bVar.f2695d * 2; i13 += 2) {
                    if (i11 >= this.f2691e.size()) {
                        cVar = new c();
                        this.f2691e.add(cVar);
                    } else {
                        cVar = this.f2691e.get(i11);
                    }
                    int[] iArr = bVar.f2694c;
                    int i14 = iArr[i13 + 1];
                    cVar.f2696a = i14 <= iAbs;
                    cVar.f2697b = iAbs;
                    cVar.f2698c = i14;
                    cVar.f2699d = recyclerView2;
                    cVar.f2700e = iArr[i13];
                    i11++;
                }
            }
        }
        Collections.sort(this.f2691e, f2687g);
    }

    /* renamed from: c */
    public final void m2834c(c cVar, long j9) {
        RecyclerView.AbstractC0442c0 abstractC0442c0M2839i = m2839i(cVar.f2699d, cVar.f2700e, cVar.f2696a ? Long.MAX_VALUE : j9);
        if (abstractC0442c0M2839i == null || abstractC0442c0M2839i.mNestedRecyclerView == null || !abstractC0442c0M2839i.isBound() || abstractC0442c0M2839i.isInvalid()) {
            return;
        }
        m2838h(abstractC0442c0M2839i.mNestedRecyclerView.get(), j9);
    }

    /* renamed from: d */
    public final void m2835d(long j9) {
        for (int i9 = 0; i9 < this.f2691e.size(); i9++) {
            c cVar = this.f2691e.get(i9);
            if (cVar.f2699d == null) {
                return;
            }
            m2834c(cVar, j9);
            cVar.m2846a();
        }
    }

    /* renamed from: f */
    public void m2836f(RecyclerView recyclerView, int i9, int i10) {
        if (recyclerView.isAttachedToWindow() && this.f2689c == 0) {
            this.f2689c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.m2845e(i9, i10);
    }

    /* renamed from: g */
    public void m2837g(long j9) {
        m2833b();
        m2835d(j9);
    }

    /* renamed from: h */
    public final void m2838h(RecyclerView recyclerView, long j9) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.m2775j() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.m2843c(recyclerView, true);
        if (bVar.f2695d != 0) {
            try {
                C6800f.m25355a("RV Nested Prefetch");
                recyclerView.mState.m2624f(recyclerView.mAdapter);
                for (int i9 = 0; i9 < bVar.f2695d * 2; i9 += 2) {
                    m2839i(recyclerView, bVar.f2694c[i9], j9);
                }
            } finally {
                C6800f.m25356b();
            }
        }
    }

    /* renamed from: i */
    public final RecyclerView.AbstractC0442c0 m2839i(RecyclerView recyclerView, int i9, long j9) {
        if (m2831e(recyclerView, i9)) {
            return null;
        }
        RecyclerView.C0461v c0461v = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.AbstractC0442c0 abstractC0442c0M2564I = c0461v.m2564I(i9, false, j9);
            if (abstractC0442c0M2564I != null) {
                if (!abstractC0442c0M2564I.isBound() || abstractC0442c0M2564I.isInvalid()) {
                    c0461v.m2569a(abstractC0442c0M2564I, false);
                } else {
                    c0461v.m2557B(abstractC0442c0M2564I.itemView);
                }
            }
            return abstractC0442c0M2564I;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    /* renamed from: j */
    public void m2840j(RecyclerView recyclerView) {
        this.f2688b.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C6800f.m25355a("RV Prefetch");
            if (!this.f2688b.isEmpty()) {
                int size = this.f2688b.size();
                long jMax = 0;
                for (int i9 = 0; i9 < size; i9++) {
                    RecyclerView recyclerView = this.f2688b.get(i9);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    m2837g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f2690d);
                }
            }
        } finally {
            this.f2689c = 0L;
            C6800f.m25356b();
        }
    }
}
