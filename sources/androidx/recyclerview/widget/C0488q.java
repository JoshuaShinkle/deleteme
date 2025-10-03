package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.q */
/* loaded from: classes.dex */
public class C0488q {

    /* renamed from: a */
    public final b f2735a;

    /* renamed from: b */
    public a f2736b = new a();

    /* renamed from: androidx.recyclerview.widget.q$a */
    public static class a {

        /* renamed from: a */
        public int f2737a = 0;

        /* renamed from: b */
        public int f2738b;

        /* renamed from: c */
        public int f2739c;

        /* renamed from: d */
        public int f2740d;

        /* renamed from: e */
        public int f2741e;

        /* renamed from: a */
        public void m2937a(int i9) {
            this.f2737a = i9 | this.f2737a;
        }

        /* renamed from: b */
        public boolean m2938b() {
            int i9 = this.f2737a;
            if ((i9 & 7) != 0 && (i9 & (m2939c(this.f2740d, this.f2738b) << 0)) == 0) {
                return false;
            }
            int i10 = this.f2737a;
            if ((i10 & 112) != 0 && (i10 & (m2939c(this.f2740d, this.f2739c) << 4)) == 0) {
                return false;
            }
            int i11 = this.f2737a;
            if ((i11 & 1792) != 0 && (i11 & (m2939c(this.f2741e, this.f2738b) << 8)) == 0) {
                return false;
            }
            int i12 = this.f2737a;
            return (i12 & 28672) == 0 || (i12 & (m2939c(this.f2741e, this.f2739c) << 12)) != 0;
        }

        /* renamed from: c */
        public int m2939c(int i9, int i10) {
            if (i9 > i10) {
                return 1;
            }
            return i9 == i10 ? 2 : 4;
        }

        /* renamed from: d */
        public void m2940d() {
            this.f2737a = 0;
        }

        /* renamed from: e */
        public void m2941e(int i9, int i10, int i11, int i12) {
            this.f2738b = i9;
            this.f2739c = i10;
            this.f2740d = i11;
            this.f2741e = i12;
        }
    }

    /* renamed from: androidx.recyclerview.widget.q$b */
    public interface b {
        /* renamed from: a */
        View mo2527a(int i9);

        /* renamed from: b */
        int mo2528b(View view);

        /* renamed from: c */
        int mo2529c();

        /* renamed from: d */
        int mo2530d();

        /* renamed from: e */
        int mo2531e(View view);
    }

    public C0488q(b bVar) {
        this.f2735a = bVar;
    }

    /* renamed from: a */
    public View m2935a(int i9, int i10, int i11, int i12) {
        int iMo2529c = this.f2735a.mo2529c();
        int iMo2530d = this.f2735a.mo2530d();
        int i13 = i10 > i9 ? 1 : -1;
        View view = null;
        while (i9 != i10) {
            View viewMo2527a = this.f2735a.mo2527a(i9);
            this.f2736b.m2941e(iMo2529c, iMo2530d, this.f2735a.mo2528b(viewMo2527a), this.f2735a.mo2531e(viewMo2527a));
            if (i11 != 0) {
                this.f2736b.m2940d();
                this.f2736b.m2937a(i11);
                if (this.f2736b.m2938b()) {
                    return viewMo2527a;
                }
            }
            if (i12 != 0) {
                this.f2736b.m2940d();
                this.f2736b.m2937a(i12);
                if (this.f2736b.m2938b()) {
                    view = viewMo2527a;
                }
            }
            i9 += i13;
        }
        return view;
    }

    /* renamed from: b */
    public boolean m2936b(View view, int i9) {
        this.f2736b.m2941e(this.f2735a.mo2529c(), this.f2735a.mo2530d(), this.f2735a.mo2528b(view), this.f2735a.mo2531e(view));
        if (i9 == 0) {
            return false;
        }
        this.f2736b.m2940d();
        this.f2736b.m2937a(i9);
        return this.f2736b.m2938b();
    }
}
