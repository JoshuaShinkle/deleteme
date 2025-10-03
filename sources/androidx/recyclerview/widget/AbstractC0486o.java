package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.o */
/* loaded from: classes.dex */
public abstract class AbstractC0486o extends RecyclerView.AbstractC0451l {

    /* renamed from: g */
    public boolean f2723g = true;

    /* renamed from: A */
    public final void m2889A(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2897I(abstractC0442c0);
        m2401h(abstractC0442c0);
    }

    /* renamed from: B */
    public final void m2890B(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2898J(abstractC0442c0);
    }

    /* renamed from: C */
    public final void m2891C(RecyclerView.AbstractC0442c0 abstractC0442c0, boolean z8) {
        m2899K(abstractC0442c0, z8);
        m2401h(abstractC0442c0);
    }

    /* renamed from: D */
    public final void m2892D(RecyclerView.AbstractC0442c0 abstractC0442c0, boolean z8) {
        m2900L(abstractC0442c0, z8);
    }

    /* renamed from: E */
    public final void m2893E(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2901M(abstractC0442c0);
        m2401h(abstractC0442c0);
    }

    /* renamed from: F */
    public final void m2894F(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2902N(abstractC0442c0);
    }

    /* renamed from: G */
    public final void m2895G(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2903O(abstractC0442c0);
        m2401h(abstractC0442c0);
    }

    /* renamed from: H */
    public final void m2896H(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2904P(abstractC0442c0);
    }

    /* renamed from: I */
    public void m2897I(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: J */
    public void m2898J(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: K */
    public void m2899K(RecyclerView.AbstractC0442c0 abstractC0442c0, boolean z8) {
    }

    /* renamed from: L */
    public void m2900L(RecyclerView.AbstractC0442c0 abstractC0442c0, boolean z8) {
    }

    /* renamed from: M */
    public void m2901M(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: N */
    public void m2902N(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: O */
    public void m2903O(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: P */
    public void m2904P(RecyclerView.AbstractC0442c0 abstractC0442c0) {
    }

    /* renamed from: Q */
    public void m2905Q(boolean z8) {
        this.f2723g = z8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: a */
    public boolean mo2395a(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2) {
        int i9;
        int i10;
        return (cVar == null || ((i9 = cVar.f2437a) == (i10 = cVar2.f2437a) && cVar.f2438b == cVar2.f2438b)) ? mo2804w(abstractC0442c0) : mo2806y(abstractC0442c0, i9, cVar.f2438b, i10, cVar2.f2438b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: b */
    public boolean mo2396b(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0442c0 abstractC0442c02, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2) {
        int i9;
        int i10;
        int i11 = cVar.f2437a;
        int i12 = cVar.f2438b;
        if (abstractC0442c02.shouldIgnore()) {
            int i13 = cVar.f2437a;
            i10 = cVar.f2438b;
            i9 = i13;
        } else {
            i9 = cVar2.f2437a;
            i10 = cVar2.f2438b;
        }
        return mo2805x(abstractC0442c0, abstractC0442c02, i11, i12, i9, i10);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: c */
    public boolean mo2397c(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2) {
        int i9 = cVar.f2437a;
        int i10 = cVar.f2438b;
        View view = abstractC0442c0.itemView;
        int left = cVar2 == null ? view.getLeft() : cVar2.f2437a;
        int top = cVar2 == null ? view.getTop() : cVar2.f2438b;
        if (abstractC0442c0.isRemoved() || (i9 == left && i10 == top)) {
            return mo2807z(abstractC0442c0);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return mo2806y(abstractC0442c0, i9, i10, left, top);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: d */
    public boolean mo2398d(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2) {
        int i9 = cVar.f2437a;
        int i10 = cVar2.f2437a;
        if (i9 != i10 || cVar.f2438b != cVar2.f2438b) {
            return mo2806y(abstractC0442c0, i9, cVar.f2438b, i10, cVar2.f2438b);
        }
        m2893E(abstractC0442c0);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: f */
    public boolean mo2399f(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        return !this.f2723g || abstractC0442c0.isInvalid();
    }

    /* renamed from: w */
    public abstract boolean mo2804w(RecyclerView.AbstractC0442c0 abstractC0442c0);

    /* renamed from: x */
    public abstract boolean mo2805x(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0442c0 abstractC0442c02, int i9, int i10, int i11, int i12);

    /* renamed from: y */
    public abstract boolean mo2806y(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9, int i10, int i11, int i12);

    /* renamed from: z */
    public abstract boolean mo2807z(RecyclerView.AbstractC0442c0 abstractC0442c0);
}
