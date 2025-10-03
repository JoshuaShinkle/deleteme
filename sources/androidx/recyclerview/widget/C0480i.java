package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: androidx.recyclerview.widget.i */
/* loaded from: classes.dex */
public class C0480i extends RecyclerView.AbstractC0464y {

    /* renamed from: k */
    public PointF f2712k;

    /* renamed from: l */
    public final float f2713l;

    /* renamed from: i */
    public final LinearInterpolator f2710i = new LinearInterpolator();

    /* renamed from: j */
    public final DecelerateInterpolator f2711j = new DecelerateInterpolator();

    /* renamed from: m */
    public int f2714m = 0;

    /* renamed from: n */
    public int f2715n = 0;

    public C0480i(Context context) {
        this.f2713l = m2854v(context.getResources().getDisplayMetrics());
    }

    /* renamed from: A */
    public int m2849A() {
        PointF pointF = this.f2712k;
        if (pointF != null) {
            float f9 = pointF.y;
            if (f9 != BitmapDescriptorFactory.HUE_RED) {
                return f9 > BitmapDescriptorFactory.HUE_RED ? 1 : -1;
            }
        }
        return 0;
    }

    /* renamed from: B */
    public void m2850B(RecyclerView.AbstractC0464y.a aVar) {
        PointF pointFM2596a = m2596a(m2601f());
        if (pointFM2596a == null || (pointFM2596a.x == BitmapDescriptorFactory.HUE_RED && pointFM2596a.y == BitmapDescriptorFactory.HUE_RED)) {
            aVar.m2615b(m2601f());
            m2613r();
            return;
        }
        m2604i(pointFM2596a);
        this.f2712k = pointFM2596a;
        this.f2714m = (int) (pointFM2596a.x * 10000.0f);
        this.f2715n = (int) (pointFM2596a.y * 10000.0f);
        aVar.m2617d((int) (this.f2714m * 1.2f), (int) (this.f2715n * 1.2f), (int) (m2856x(10000) * 1.2f), this.f2710i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y
    /* renamed from: l */
    public void mo2607l(int i9, int i10, RecyclerView.C0465z c0465z, RecyclerView.AbstractC0464y.a aVar) {
        if (m2598c() == 0) {
            m2613r();
            return;
        }
        this.f2714m = m2857y(this.f2714m, i9);
        int iM2857y = m2857y(this.f2715n, i10);
        this.f2715n = iM2857y;
        if (this.f2714m == 0 && iM2857y == 0) {
            m2850B(aVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y
    /* renamed from: m */
    public void mo2608m() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y
    /* renamed from: n */
    public void mo2609n() {
        this.f2715n = 0;
        this.f2714m = 0;
        this.f2712k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0464y
    /* renamed from: o */
    public void mo2610o(View view, RecyclerView.C0465z c0465z, RecyclerView.AbstractC0464y.a aVar) {
        int iM2852t = m2852t(view, m2858z());
        int iM2853u = m2853u(view, m2849A());
        int iM2855w = m2855w((int) Math.sqrt((iM2852t * iM2852t) + (iM2853u * iM2853u)));
        if (iM2855w > 0) {
            aVar.m2617d(-iM2852t, -iM2853u, iM2855w, this.f2711j);
        }
    }

    /* renamed from: s */
    public int m2851s(int i9, int i10, int i11, int i12, int i13) {
        if (i13 == -1) {
            return i11 - i9;
        }
        if (i13 != 0) {
            if (i13 == 1) {
                return i12 - i10;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i14 = i11 - i9;
        if (i14 > 0) {
            return i14;
        }
        int i15 = i12 - i10;
        if (i15 < 0) {
            return i15;
        }
        return 0;
    }

    /* renamed from: t */
    public int m2852t(View view, int i9) {
        RecyclerView.AbstractC0454o abstractC0454oM2600e = m2600e();
        if (abstractC0454oM2600e == null || !abstractC0454oM2600e.mo2298k()) {
            return 0;
        }
        RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
        return m2851s(abstractC0454oM2600e.m2459Q(view) - ((ViewGroup.MarginLayoutParams) c0455p).leftMargin, abstractC0454oM2600e.m2462T(view) + ((ViewGroup.MarginLayoutParams) c0455p).rightMargin, abstractC0454oM2600e.m2479d0(), abstractC0454oM2600e.m2502n0() - abstractC0454oM2600e.m2482e0(), i9);
    }

    /* renamed from: u */
    public int m2853u(View view, int i9) {
        RecyclerView.AbstractC0454o abstractC0454oM2600e = m2600e();
        if (abstractC0454oM2600e == null || !abstractC0454oM2600e.mo2300l()) {
            return 0;
        }
        RecyclerView.C0455p c0455p = (RecyclerView.C0455p) view.getLayoutParams();
        return m2851s(abstractC0454oM2600e.m2463U(view) - ((ViewGroup.MarginLayoutParams) c0455p).topMargin, abstractC0454oM2600e.m2456O(view) + ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin, abstractC0454oM2600e.m2485f0(), abstractC0454oM2600e.m2466W() - abstractC0454oM2600e.m2477c0(), i9);
    }

    /* renamed from: v */
    public float m2854v(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    /* renamed from: w */
    public int m2855w(int i9) {
        return (int) Math.ceil(m2856x(i9) / 0.3356d);
    }

    /* renamed from: x */
    public int m2856x(int i9) {
        return (int) Math.ceil(Math.abs(i9) * this.f2713l);
    }

    /* renamed from: y */
    public final int m2857y(int i9, int i10) {
        int i11 = i9 - i10;
        if (i9 * i11 <= 0) {
            return 0;
        }
        return i11;
    }

    /* renamed from: z */
    public int m2858z() {
        PointF pointF = this.f2712k;
        if (pointF != null) {
            float f9 = pointF.x;
            if (f9 != BitmapDescriptorFactory.HUE_RED) {
                return f9 > BitmapDescriptorFactory.HUE_RED ? 1 : -1;
            }
        }
        return 0;
    }
}
