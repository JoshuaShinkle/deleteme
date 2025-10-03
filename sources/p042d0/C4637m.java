package p042d0;

import android.view.View;
import android.view.ViewParent;

/* renamed from: d0.m */
/* loaded from: classes.dex */
public class C4637m {

    /* renamed from: a */
    public ViewParent f16265a;

    /* renamed from: b */
    public ViewParent f16266b;

    /* renamed from: c */
    public final View f16267c;

    /* renamed from: d */
    public boolean f16268d;

    /* renamed from: e */
    public int[] f16269e;

    public C4637m(View view) {
        this.f16267c = view;
    }

    /* renamed from: a */
    public boolean m18474a(float f9, float f10, boolean z8) {
        ViewParent viewParentM18482i;
        if (!m18486m() || (viewParentM18482i = m18482i(0)) == null) {
            return false;
        }
        return C4618c0.m18398a(viewParentM18482i, this.f16267c, f9, f10, z8);
    }

    /* renamed from: b */
    public boolean m18475b(float f9, float f10) {
        ViewParent viewParentM18482i;
        if (!m18486m() || (viewParentM18482i = m18482i(0)) == null) {
            return false;
        }
        return C4618c0.m18399b(viewParentM18482i, this.f16267c, f9, f10);
    }

    /* renamed from: c */
    public boolean m18476c(int i9, int i10, int[] iArr, int[] iArr2) {
        return m18477d(i9, i10, iArr, iArr2, 0);
    }

    /* renamed from: d */
    public boolean m18477d(int i9, int i10, int[] iArr, int[] iArr2, int i11) {
        ViewParent viewParentM18482i;
        int i12;
        int i13;
        if (!m18486m() || (viewParentM18482i = m18482i(i11)) == null) {
            return false;
        }
        if (i9 == 0 && i10 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f16267c.getLocationInWindow(iArr2);
            i12 = iArr2[0];
            i13 = iArr2[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr == null) {
            iArr = m18483j();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        C4618c0.m18400c(viewParentM18482i, this.f16267c, i9, i10, iArr, i11);
        if (iArr2 != null) {
            this.f16267c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i12;
            iArr2[1] = iArr2[1] - i13;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    /* renamed from: e */
    public void m18478e(int i9, int i10, int i11, int i12, int[] iArr, int i13, int[] iArr2) {
        m18481h(i9, i10, i11, i12, iArr, i13, iArr2);
    }

    /* renamed from: f */
    public boolean m18479f(int i9, int i10, int i11, int i12, int[] iArr) {
        return m18481h(i9, i10, i11, i12, iArr, 0, null);
    }

    /* renamed from: g */
    public boolean m18480g(int i9, int i10, int i11, int i12, int[] iArr, int i13) {
        return m18481h(i9, i10, i11, i12, iArr, i13, null);
    }

    /* renamed from: h */
    public final boolean m18481h(int i9, int i10, int i11, int i12, int[] iArr, int i13, int[] iArr2) {
        ViewParent viewParentM18482i;
        int i14;
        int i15;
        int[] iArr3;
        if (!m18486m() || (viewParentM18482i = m18482i(i13)) == null) {
            return false;
        }
        if (i9 == 0 && i10 == 0 && i11 == 0 && i12 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f16267c.getLocationInWindow(iArr);
            i14 = iArr[0];
            i15 = iArr[1];
        } else {
            i14 = 0;
            i15 = 0;
        }
        if (iArr2 == null) {
            int[] iArrM18483j = m18483j();
            iArrM18483j[0] = 0;
            iArrM18483j[1] = 0;
            iArr3 = iArrM18483j;
        } else {
            iArr3 = iArr2;
        }
        C4618c0.m18401d(viewParentM18482i, this.f16267c, i9, i10, i11, i12, i13, iArr3);
        if (iArr != null) {
            this.f16267c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i14;
            iArr[1] = iArr[1] - i15;
        }
        return true;
    }

    /* renamed from: i */
    public final ViewParent m18482i(int i9) {
        if (i9 == 0) {
            return this.f16265a;
        }
        if (i9 != 1) {
            return null;
        }
        return this.f16266b;
    }

    /* renamed from: j */
    public final int[] m18483j() {
        if (this.f16269e == null) {
            this.f16269e = new int[2];
        }
        return this.f16269e;
    }

    /* renamed from: k */
    public boolean m18484k() {
        return m18485l(0);
    }

    /* renamed from: l */
    public boolean m18485l(int i9) {
        return m18482i(i9) != null;
    }

    /* renamed from: m */
    public boolean m18486m() {
        return this.f16268d;
    }

    /* renamed from: n */
    public void m18487n(boolean z8) {
        if (this.f16268d) {
            C4647u.m18564q0(this.f16267c);
        }
        this.f16268d = z8;
    }

    /* renamed from: o */
    public final void m18488o(int i9, ViewParent viewParent) {
        if (i9 == 0) {
            this.f16265a = viewParent;
        } else {
            if (i9 != 1) {
                return;
            }
            this.f16266b = viewParent;
        }
    }

    /* renamed from: p */
    public boolean m18489p(int i9) {
        return m18490q(i9, 0);
    }

    /* renamed from: q */
    public boolean m18490q(int i9, int i10) {
        if (m18485l(i10)) {
            return true;
        }
        if (!m18486m()) {
            return false;
        }
        View view = this.f16267c;
        for (ViewParent parent = this.f16267c.getParent(); parent != null; parent = parent.getParent()) {
            if (C4618c0.m18403f(parent, view, this.f16267c, i9, i10)) {
                m18488o(i10, parent);
                C4618c0.m18402e(parent, view, this.f16267c, i9, i10);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    /* renamed from: r */
    public void m18491r() {
        m18492s(0);
    }

    /* renamed from: s */
    public void m18492s(int i9) {
        ViewParent viewParentM18482i = m18482i(i9);
        if (viewParentM18482i != null) {
            C4618c0.m18404g(viewParentM18482i, this.f16267c, i9);
            m18488o(i9, null);
        }
    }
}
