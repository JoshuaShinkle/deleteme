package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.h */
/* loaded from: classes.dex */
public class C0479h {

    /* renamed from: b */
    public int f2702b;

    /* renamed from: c */
    public int f2703c;

    /* renamed from: d */
    public int f2704d;

    /* renamed from: e */
    public int f2705e;

    /* renamed from: h */
    public boolean f2708h;

    /* renamed from: i */
    public boolean f2709i;

    /* renamed from: a */
    public boolean f2701a = true;

    /* renamed from: f */
    public int f2706f = 0;

    /* renamed from: g */
    public int f2707g = 0;

    /* renamed from: a */
    public boolean m2847a(RecyclerView.C0465z c0465z) {
        int i9 = this.f2703c;
        return i9 >= 0 && i9 < c0465z.m2620b();
    }

    /* renamed from: b */
    public View m2848b(RecyclerView.C0461v c0461v) {
        View viewM2583o = c0461v.m2583o(this.f2703c);
        this.f2703c += this.f2704d;
        return viewM2583o;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f2702b + ", mCurrentPosition=" + this.f2703c + ", mItemDirection=" + this.f2704d + ", mLayoutDirection=" + this.f2705e + ", mStartLine=" + this.f2706f + ", mEndLine=" + this.f2707g + '}';
    }
}
