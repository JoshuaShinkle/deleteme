package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.l */
/* loaded from: classes.dex */
public abstract class AbstractC0483l {

    /* renamed from: a */
    public final RecyclerView.AbstractC0454o f2717a;

    /* renamed from: b */
    public int f2718b;

    /* renamed from: c */
    public final Rect f2719c;

    /* renamed from: androidx.recyclerview.widget.l$a */
    public static class a extends AbstractC0483l {
        public a(RecyclerView.AbstractC0454o abstractC0454o) {
            super(abstractC0454o, null);
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: d */
        public int mo2868d(View view) {
            return this.f2717a.m2462T(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.C0455p) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: e */
        public int mo2869e(View view) {
            RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
            return this.f2717a.m2461S(view) + ((ViewGroup.MarginLayoutParams) c0455p).leftMargin + ((ViewGroup.MarginLayoutParams) c0455p).rightMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: f */
        public int mo2870f(View view) {
            RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
            return this.f2717a.m2460R(view) + ((ViewGroup.MarginLayoutParams) c0455p).topMargin + ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: g */
        public int mo2871g(View view) {
            return this.f2717a.m2459Q(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.C0455p) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: h */
        public int mo2872h() {
            return this.f2717a.m2502n0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: i */
        public int mo2873i() {
            return this.f2717a.m2502n0() - this.f2717a.m2482e0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: j */
        public int mo2874j() {
            return this.f2717a.m2482e0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: k */
        public int mo2875k() {
            return this.f2717a.m2504o0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: l */
        public int mo2876l() {
            return this.f2717a.m2467X();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: m */
        public int mo2877m() {
            return this.f2717a.m2479d0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: n */
        public int mo2878n() {
            return (this.f2717a.m2502n0() - this.f2717a.m2479d0()) - this.f2717a.m2482e0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: p */
        public int mo2880p(View view) {
            this.f2717a.m2500m0(view, true, this.f2719c);
            return this.f2719c.right;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: q */
        public int mo2881q(View view) {
            this.f2717a.m2500m0(view, true, this.f2719c);
            return this.f2719c.left;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: r */
        public void mo2882r(int i9) {
            this.f2717a.mo2434B0(i9);
        }
    }

    /* renamed from: androidx.recyclerview.widget.l$b */
    public static class b extends AbstractC0483l {
        public b(RecyclerView.AbstractC0454o abstractC0454o) {
            super(abstractC0454o, null);
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: d */
        public int mo2868d(View view) {
            return this.f2717a.m2456O(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.C0455p) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: e */
        public int mo2869e(View view) {
            RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
            return this.f2717a.m2460R(view) + ((ViewGroup.MarginLayoutParams) c0455p).topMargin + ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: f */
        public int mo2870f(View view) {
            RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
            return this.f2717a.m2461S(view) + ((ViewGroup.MarginLayoutParams) c0455p).leftMargin + ((ViewGroup.MarginLayoutParams) c0455p).rightMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: g */
        public int mo2871g(View view) {
            return this.f2717a.m2463U(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.C0455p) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: h */
        public int mo2872h() {
            return this.f2717a.m2466W();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: i */
        public int mo2873i() {
            return this.f2717a.m2466W() - this.f2717a.m2477c0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: j */
        public int mo2874j() {
            return this.f2717a.m2477c0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: k */
        public int mo2875k() {
            return this.f2717a.m2467X();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: l */
        public int mo2876l() {
            return this.f2717a.m2504o0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: m */
        public int mo2877m() {
            return this.f2717a.m2485f0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: n */
        public int mo2878n() {
            return (this.f2717a.m2466W() - this.f2717a.m2485f0()) - this.f2717a.m2477c0();
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: p */
        public int mo2880p(View view) {
            this.f2717a.m2500m0(view, true, this.f2719c);
            return this.f2719c.bottom;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: q */
        public int mo2881q(View view) {
            this.f2717a.m2500m0(view, true, this.f2719c);
            return this.f2719c.top;
        }

        @Override // androidx.recyclerview.widget.AbstractC0483l
        /* renamed from: r */
        public void mo2882r(int i9) {
            this.f2717a.mo2435C0(i9);
        }
    }

    public /* synthetic */ AbstractC0483l(RecyclerView.AbstractC0454o abstractC0454o, a aVar) {
        this(abstractC0454o);
    }

    /* renamed from: a */
    public static AbstractC0483l m2865a(RecyclerView.AbstractC0454o abstractC0454o) {
        return new a(abstractC0454o);
    }

    /* renamed from: b */
    public static AbstractC0483l m2866b(RecyclerView.AbstractC0454o abstractC0454o, int i9) {
        if (i9 == 0) {
            return m2865a(abstractC0454o);
        }
        if (i9 == 1) {
            return m2867c(abstractC0454o);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    /* renamed from: c */
    public static AbstractC0483l m2867c(RecyclerView.AbstractC0454o abstractC0454o) {
        return new b(abstractC0454o);
    }

    /* renamed from: d */
    public abstract int mo2868d(View view);

    /* renamed from: e */
    public abstract int mo2869e(View view);

    /* renamed from: f */
    public abstract int mo2870f(View view);

    /* renamed from: g */
    public abstract int mo2871g(View view);

    /* renamed from: h */
    public abstract int mo2872h();

    /* renamed from: i */
    public abstract int mo2873i();

    /* renamed from: j */
    public abstract int mo2874j();

    /* renamed from: k */
    public abstract int mo2875k();

    /* renamed from: l */
    public abstract int mo2876l();

    /* renamed from: m */
    public abstract int mo2877m();

    /* renamed from: n */
    public abstract int mo2878n();

    /* renamed from: o */
    public int m2879o() {
        if (Integer.MIN_VALUE == this.f2718b) {
            return 0;
        }
        return mo2878n() - this.f2718b;
    }

    /* renamed from: p */
    public abstract int mo2880p(View view);

    /* renamed from: q */
    public abstract int mo2881q(View view);

    /* renamed from: r */
    public abstract void mo2882r(int i9);

    /* renamed from: s */
    public void m2883s() {
        this.f2718b = mo2878n();
    }

    public AbstractC0483l(RecyclerView.AbstractC0454o abstractC0454o) {
        this.f2718b = Integer.MIN_VALUE;
        this.f2719c = new Rect();
        this.f2717a = abstractC0454o;
    }
}
