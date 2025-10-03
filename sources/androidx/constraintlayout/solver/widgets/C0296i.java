package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p150o.C5386c;
import p150o.C5387d;

/* renamed from: androidx.constraintlayout.solver.widgets.i */
/* loaded from: classes.dex */
public class C0296i extends C5387d {

    /* renamed from: c */
    public ConstraintAnchor f1507c;

    /* renamed from: d */
    public float f1508d;

    /* renamed from: e */
    public C0296i f1509e;

    /* renamed from: f */
    public float f1510f;

    /* renamed from: g */
    public C0296i f1511g;

    /* renamed from: h */
    public float f1512h;

    /* renamed from: j */
    public C0296i f1514j;

    /* renamed from: k */
    public float f1515k;

    /* renamed from: i */
    public int f1513i = 0;

    /* renamed from: l */
    public C5386c f1516l = null;

    /* renamed from: m */
    public int f1517m = 1;

    /* renamed from: n */
    public C5386c f1518n = null;

    /* renamed from: o */
    public int f1519o = 1;

    public C0296i(ConstraintAnchor constraintAnchor) {
        this.f1507c = constraintAnchor;
    }

    @Override // p150o.C5387d
    /* renamed from: e */
    public void mo1372e() {
        super.mo1372e();
        this.f1509e = null;
        this.f1510f = BitmapDescriptorFactory.HUE_RED;
        this.f1516l = null;
        this.f1517m = 1;
        this.f1518n = null;
        this.f1519o = 1;
        this.f1511g = null;
        this.f1512h = BitmapDescriptorFactory.HUE_RED;
        this.f1508d = BitmapDescriptorFactory.HUE_RED;
        this.f1514j = null;
        this.f1515k = BitmapDescriptorFactory.HUE_RED;
        this.f1513i = 0;
    }

    @Override // p150o.C5387d
    /* renamed from: f */
    public void mo1373f() {
        int i9;
        C0296i c0296i;
        C0296i c0296i2;
        C0296i c0296i3;
        C0296i c0296i4;
        C0296i c0296i5;
        C0296i c0296i6;
        float fM1236D;
        float f9;
        C0296i c0296i7;
        boolean z8 = true;
        if (this.f18287b == 1 || (i9 = this.f1513i) == 4) {
            return;
        }
        C5386c c5386c = this.f1516l;
        if (c5386c != null) {
            if (c5386c.f18287b != 1) {
                return;
            } else {
                this.f1510f = this.f1517m * c5386c.f18285c;
            }
        }
        C5386c c5386c2 = this.f1518n;
        if (c5386c2 != null) {
            if (c5386c2.f18287b != 1) {
                return;
            } else {
                this.f1515k = this.f1519o * c5386c2.f18285c;
            }
        }
        if (i9 == 1 && ((c0296i7 = this.f1509e) == null || c0296i7.f18287b == 1)) {
            if (c0296i7 == null) {
                this.f1511g = this;
                this.f1512h = this.f1510f;
            } else {
                this.f1511g = c0296i7.f1511g;
                this.f1512h = c0296i7.f1512h + this.f1510f;
            }
            m21092b();
            return;
        }
        if (i9 != 2 || (c0296i4 = this.f1509e) == null || c0296i4.f18287b != 1 || (c0296i5 = this.f1514j) == null || (c0296i6 = c0296i5.f1509e) == null || c0296i6.f18287b != 1) {
            if (i9 != 3 || (c0296i = this.f1509e) == null || c0296i.f18287b != 1 || (c0296i2 = this.f1514j) == null || (c0296i3 = c0296i2.f1509e) == null || c0296i3.f18287b != 1) {
                if (i9 == 5) {
                    this.f1507c.f1336b.mo1258U();
                    return;
                }
                return;
            }
            C0285c.m1185x();
            C0296i c0296i8 = this.f1509e;
            this.f1511g = c0296i8.f1511g;
            C0296i c0296i9 = this.f1514j;
            C0296i c0296i10 = c0296i9.f1509e;
            c0296i9.f1511g = c0296i10.f1511g;
            this.f1512h = c0296i8.f1512h + this.f1510f;
            c0296i9.f1512h = c0296i10.f1512h + c0296i9.f1510f;
            m21092b();
            this.f1514j.m21092b();
            return;
        }
        C0285c.m1185x();
        C0296i c0296i11 = this.f1509e;
        this.f1511g = c0296i11.f1511g;
        C0296i c0296i12 = this.f1514j;
        C0296i c0296i13 = c0296i12.f1509e;
        c0296i12.f1511g = c0296i13.f1511g;
        ConstraintAnchor.Type type = this.f1507c.f1337c;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
        int i10 = 0;
        if (type != type2 && type != ConstraintAnchor.Type.BOTTOM) {
            z8 = false;
        }
        float f10 = z8 ? c0296i11.f1512h - c0296i13.f1512h : c0296i13.f1512h - c0296i11.f1512h;
        if (type == ConstraintAnchor.Type.LEFT || type == type2) {
            fM1236D = f10 - r2.f1336b.m1236D();
            f9 = this.f1507c.f1336b.f1389Z;
        } else {
            fM1236D = f10 - r2.f1336b.m1298r();
            f9 = this.f1507c.f1336b.f1391a0;
        }
        int iM1219d = this.f1507c.m1219d();
        int iM1219d2 = this.f1514j.f1507c.m1219d();
        if (this.f1507c.m1224i() == this.f1514j.f1507c.m1224i()) {
            f9 = 0.5f;
            iM1219d2 = 0;
        } else {
            i10 = iM1219d;
        }
        float f11 = i10;
        float f12 = iM1219d2;
        float f13 = (fM1236D - f11) - f12;
        if (z8) {
            C0296i c0296i14 = this.f1514j;
            c0296i14.f1512h = c0296i14.f1509e.f1512h + f12 + (f13 * f9);
            this.f1512h = (this.f1509e.f1512h - f11) - (f13 * (1.0f - f9));
        } else {
            this.f1512h = this.f1509e.f1512h + f11 + (f13 * f9);
            C0296i c0296i15 = this.f1514j;
            c0296i15.f1512h = (c0296i15.f1509e.f1512h - f12) - (f13 * (1.0f - f9));
        }
        m21092b();
        this.f1514j.m21092b();
    }

    /* renamed from: g */
    public void m1374g(C0285c c0285c) {
        SolverVariable solverVariableM1222g = this.f1507c.m1222g();
        C0296i c0296i = this.f1511g;
        if (c0296i == null) {
            c0285c.m1197f(solverVariableM1222g, (int) (this.f1512h + 0.5f));
        } else {
            c0285c.m1196e(solverVariableM1222g, c0285c.m1209r(c0296i.f1507c), (int) (this.f1512h + 0.5f), 6);
        }
    }

    /* renamed from: h */
    public void m1375h(int i9, C0296i c0296i, int i10) {
        this.f1513i = i9;
        this.f1509e = c0296i;
        this.f1510f = i10;
        c0296i.m21091a(this);
    }

    /* renamed from: i */
    public void m1376i(C0296i c0296i, int i9) {
        this.f1509e = c0296i;
        this.f1510f = i9;
        c0296i.m21091a(this);
    }

    /* renamed from: j */
    public void m1377j(C0296i c0296i, int i9, C5386c c5386c) {
        this.f1509e = c0296i;
        c0296i.m21091a(this);
        this.f1516l = c5386c;
        this.f1517m = i9;
        c5386c.m21091a(this);
    }

    /* renamed from: k */
    public float m1378k() {
        return this.f1512h;
    }

    /* renamed from: l */
    public void m1379l(C0296i c0296i, float f9) {
        int i9 = this.f18287b;
        if (i9 == 0 || !(this.f1511g == c0296i || this.f1512h == f9)) {
            this.f1511g = c0296i;
            this.f1512h = f9;
            if (i9 == 1) {
                m21093c();
            }
            m21092b();
        }
    }

    /* renamed from: m */
    public String m1380m(int i9) {
        return i9 == 1 ? "DIRECT" : i9 == 2 ? "CENTER" : i9 == 3 ? "MATCH" : i9 == 4 ? "CHAIN" : i9 == 5 ? "BARRIER" : "UNCONNECTED";
    }

    /* renamed from: n */
    public void m1381n(C0296i c0296i, float f9) {
        this.f1514j = c0296i;
        this.f1515k = f9;
    }

    /* renamed from: o */
    public void m1382o(C0296i c0296i, int i9, C5386c c5386c) {
        this.f1514j = c0296i;
        this.f1518n = c5386c;
        this.f1519o = i9;
    }

    /* renamed from: p */
    public void m1383p(int i9) {
        this.f1513i = i9;
    }

    /* renamed from: q */
    public void m1384q() {
        ConstraintAnchor constraintAnchorM1224i = this.f1507c.m1224i();
        if (constraintAnchorM1224i == null) {
            return;
        }
        if (constraintAnchorM1224i.m1224i() == this.f1507c) {
            this.f1513i = 4;
            constraintAnchorM1224i.m1221f().f1513i = 4;
        }
        int iM1219d = this.f1507c.m1219d();
        ConstraintAnchor.Type type = this.f1507c.f1337c;
        if (type == ConstraintAnchor.Type.RIGHT || type == ConstraintAnchor.Type.BOTTOM) {
            iM1219d = -iM1219d;
        }
        m1376i(constraintAnchorM1224i.m1221f(), iM1219d);
    }

    public String toString() {
        if (this.f18287b != 1) {
            return "{ " + this.f1507c + " UNRESOLVED} type: " + m1380m(this.f1513i);
        }
        if (this.f1511g == this) {
            return "[" + this.f1507c + ", RESOLVED: " + this.f1512h + "]  type: " + m1380m(this.f1513i);
        }
        return "[" + this.f1507c + ", RESOLVED: " + this.f1511g + ":" + this.f1512h + "] type: " + m1380m(this.f1513i);
    }
}
