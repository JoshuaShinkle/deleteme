package p042d0;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: d0.p */
/* loaded from: classes.dex */
public class C4642p {

    /* renamed from: a */
    public int f16270a;

    /* renamed from: b */
    public int f16271b;

    public C4642p(ViewGroup viewGroup) {
    }

    /* renamed from: a */
    public int m18495a() {
        return this.f16270a | this.f16271b;
    }

    /* renamed from: b */
    public void m18496b(View view, View view2, int i9) {
        m18497c(view, view2, i9, 0);
    }

    /* renamed from: c */
    public void m18497c(View view, View view2, int i9, int i10) {
        if (i10 == 1) {
            this.f16271b = i9;
        } else {
            this.f16270a = i9;
        }
    }

    /* renamed from: d */
    public void m18498d(View view) {
        m18499e(view, 0);
    }

    /* renamed from: e */
    public void m18499e(View view, int i9) {
        if (i9 == 1) {
            this.f16271b = 0;
        } else {
            this.f16270a = 0;
        }
    }
}
