package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.n */
/* loaded from: classes.dex */
public class C0485n {
    /* renamed from: a */
    public static int m2886a(RecyclerView.C0465z c0465z, AbstractC0483l abstractC0483l, View view, View view2, RecyclerView.AbstractC0454o abstractC0454o, boolean z8) {
        if (abstractC0454o.m2448J() == 0 || c0465z.m2620b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z8) {
            return Math.abs(abstractC0454o.m2487g0(view) - abstractC0454o.m2487g0(view2)) + 1;
        }
        return Math.min(abstractC0483l.mo2878n(), abstractC0483l.mo2868d(view2) - abstractC0483l.mo2871g(view));
    }

    /* renamed from: b */
    public static int m2887b(RecyclerView.C0465z c0465z, AbstractC0483l abstractC0483l, View view, View view2, RecyclerView.AbstractC0454o abstractC0454o, boolean z8, boolean z9) {
        if (abstractC0454o.m2448J() == 0 || c0465z.m2620b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z9 ? Math.max(0, (c0465z.m2620b() - Math.max(abstractC0454o.m2487g0(view), abstractC0454o.m2487g0(view2))) - 1) : Math.max(0, Math.min(abstractC0454o.m2487g0(view), abstractC0454o.m2487g0(view2)));
        if (z8) {
            return Math.round((iMax * (Math.abs(abstractC0483l.mo2868d(view2) - abstractC0483l.mo2871g(view)) / (Math.abs(abstractC0454o.m2487g0(view) - abstractC0454o.m2487g0(view2)) + 1))) + (abstractC0483l.mo2877m() - abstractC0483l.mo2871g(view)));
        }
        return iMax;
    }

    /* renamed from: c */
    public static int m2888c(RecyclerView.C0465z c0465z, AbstractC0483l abstractC0483l, View view, View view2, RecyclerView.AbstractC0454o abstractC0454o, boolean z8) {
        if (abstractC0454o.m2448J() == 0 || c0465z.m2620b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z8) {
            return c0465z.m2620b();
        }
        return (int) (((abstractC0483l.mo2868d(view2) - abstractC0483l.mo2871g(view)) / (Math.abs(abstractC0454o.m2487g0(view) - abstractC0454o.m2487g0(view2)) + 1)) * c0465z.m2620b());
    }
}
