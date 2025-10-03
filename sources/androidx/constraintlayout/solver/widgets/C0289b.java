package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import p150o.C5384a;

/* renamed from: androidx.constraintlayout.solver.widgets.b */
/* loaded from: classes.dex */
public class C0289b extends C5384a {

    /* renamed from: x0 */
    public int f1443x0 = 0;

    /* renamed from: y0 */
    public ArrayList<C0296i> f1444y0 = new ArrayList<>(4);

    /* renamed from: z0 */
    public boolean f1445z0 = true;

    /* renamed from: K0 */
    public void m1328K0(boolean z8) {
        this.f1445z0 = z8;
    }

    /* renamed from: L0 */
    public void m1329L0(int i9) {
        this.f1443x0 = i9;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: S */
    public void mo1256S() {
        super.mo1256S();
        this.f1444y0.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: U */
    public void mo1258U() {
        C0296i c0296iM1221f;
        float f9;
        C0296i c0296i;
        int i9 = this.f1443x0;
        float f10 = Float.MAX_VALUE;
        if (i9 != 0) {
            if (i9 == 1) {
                c0296iM1221f = this.f1432w.m1221f();
            } else if (i9 == 2) {
                c0296iM1221f = this.f1431v.m1221f();
            } else if (i9 != 3) {
                return;
            } else {
                c0296iM1221f = this.f1433x.m1221f();
            }
            f10 = 0.0f;
        } else {
            c0296iM1221f = this.f1430u.m1221f();
        }
        int size = this.f1444y0.size();
        C0296i c0296i2 = null;
        for (int i10 = 0; i10 < size; i10++) {
            C0296i c0296i3 = this.f1444y0.get(i10);
            if (c0296i3.f18287b != 1) {
                return;
            }
            int i11 = this.f1443x0;
            if (i11 == 0 || i11 == 2) {
                f9 = c0296i3.f1512h;
                if (f9 < f10) {
                    c0296i = c0296i3.f1511g;
                    c0296i2 = c0296i;
                    f10 = f9;
                }
            } else {
                f9 = c0296i3.f1512h;
                if (f9 > f10) {
                    c0296i = c0296i3.f1511g;
                    c0296i2 = c0296i;
                    f10 = f9;
                }
            }
        }
        C0285c.m1185x();
        c0296iM1221f.f1511g = c0296i2;
        c0296iM1221f.f1512h = f10;
        c0296iM1221f.m21092b();
        int i12 = this.f1443x0;
        if (i12 == 0) {
            this.f1432w.m1221f().m1379l(c0296i2, f10);
            return;
        }
        if (i12 == 1) {
            this.f1430u.m1221f().m1379l(c0296i2, f10);
        } else if (i12 == 2) {
            this.f1433x.m1221f().m1379l(c0296i2, f10);
        } else {
            if (i12 != 3) {
                return;
            }
            this.f1431v.m1221f().m1379l(c0296i2, f10);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: b */
    public void mo1266b(C0285c c0285c) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z8;
        int i9;
        int i10;
        ConstraintAnchor[] constraintAnchorArr2 = this.f1366C;
        constraintAnchorArr2[0] = this.f1430u;
        constraintAnchorArr2[2] = this.f1431v;
        constraintAnchorArr2[1] = this.f1432w;
        constraintAnchorArr2[3] = this.f1433x;
        int i11 = 0;
        while (true) {
            constraintAnchorArr = this.f1366C;
            if (i11 >= constraintAnchorArr.length) {
                break;
            }
            ConstraintAnchor constraintAnchor = constraintAnchorArr[i11];
            constraintAnchor.f1344j = c0285c.m1209r(constraintAnchor);
            i11++;
        }
        int i12 = this.f1443x0;
        if (i12 < 0 || i12 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i12];
        for (int i13 = 0; i13 < this.f18284w0; i13++) {
            ConstraintWidget constraintWidget = this.f18283v0[i13];
            if ((this.f1445z0 || constraintWidget.mo1268c()) && ((((i9 = this.f1443x0) == 0 || i9 == 1) && constraintWidget.m1300s() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) || (((i10 = this.f1443x0) == 2 || i10 == 3) && constraintWidget.m1232B() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
                z8 = true;
                break;
            }
        }
        z8 = false;
        int i14 = this.f1443x0;
        if (i14 == 0 || i14 == 1 ? m1304u().m1300s() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT : m1304u().m1232B() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            z8 = false;
        }
        for (int i15 = 0; i15 < this.f18284w0; i15++) {
            ConstraintWidget constraintWidget2 = this.f18283v0[i15];
            if (this.f1445z0 || constraintWidget2.mo1268c()) {
                SolverVariable solverVariableM1209r = c0285c.m1209r(constraintWidget2.f1366C[this.f1443x0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.f1366C;
                int i16 = this.f1443x0;
                constraintAnchorArr3[i16].f1344j = solverVariableM1209r;
                if (i16 == 0 || i16 == 2) {
                    c0285c.m1201j(constraintAnchor2.f1344j, solverVariableM1209r, z8);
                } else {
                    c0285c.m1199h(constraintAnchor2.f1344j, solverVariableM1209r, z8);
                }
            }
        }
        int i17 = this.f1443x0;
        if (i17 == 0) {
            c0285c.m1196e(this.f1432w.f1344j, this.f1430u.f1344j, 0, 6);
            if (z8) {
                return;
            }
            c0285c.m1196e(this.f1430u.f1344j, this.f1369F.f1432w.f1344j, 0, 5);
            return;
        }
        if (i17 == 1) {
            c0285c.m1196e(this.f1430u.f1344j, this.f1432w.f1344j, 0, 6);
            if (z8) {
                return;
            }
            c0285c.m1196e(this.f1430u.f1344j, this.f1369F.f1430u.f1344j, 0, 5);
            return;
        }
        if (i17 == 2) {
            c0285c.m1196e(this.f1433x.f1344j, this.f1431v.f1344j, 0, 6);
            if (z8) {
                return;
            }
            c0285c.m1196e(this.f1431v.f1344j, this.f1369F.f1433x.f1344j, 0, 5);
            return;
        }
        if (i17 == 3) {
            c0285c.m1196e(this.f1431v.f1344j, this.f1433x.f1344j, 0, 6);
            if (z8) {
                return;
            }
            c0285c.m1196e(this.f1431v.f1344j, this.f1369F.f1431v.f1344j, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: c */
    public boolean mo1268c() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: d */
    public void mo1270d(int i9) {
        C0296i c0296iM1221f;
        ConstraintWidget constraintWidget = this.f1369F;
        if (constraintWidget != null && ((C0292e) constraintWidget).m1346X0(2)) {
            int i10 = this.f1443x0;
            if (i10 == 0) {
                c0296iM1221f = this.f1430u.m1221f();
            } else if (i10 == 1) {
                c0296iM1221f = this.f1432w.m1221f();
            } else if (i10 == 2) {
                c0296iM1221f = this.f1431v.m1221f();
            } else if (i10 != 3) {
                return;
            } else {
                c0296iM1221f = this.f1433x.m1221f();
            }
            c0296iM1221f.m1383p(5);
            int i11 = this.f1443x0;
            if (i11 == 0 || i11 == 1) {
                this.f1431v.m1221f().m1379l(null, BitmapDescriptorFactory.HUE_RED);
                this.f1433x.m1221f().m1379l(null, BitmapDescriptorFactory.HUE_RED);
            } else {
                this.f1430u.m1221f().m1379l(null, BitmapDescriptorFactory.HUE_RED);
                this.f1432w.m1221f().m1379l(null, BitmapDescriptorFactory.HUE_RED);
            }
            this.f1444y0.clear();
            for (int i12 = 0; i12 < this.f18284w0; i12++) {
                ConstraintWidget constraintWidget2 = this.f18283v0[i12];
                if (this.f1445z0 || constraintWidget2.mo1268c()) {
                    int i13 = this.f1443x0;
                    C0296i c0296iM1221f2 = i13 != 0 ? i13 != 1 ? i13 != 2 ? i13 != 3 ? null : constraintWidget2.f1433x.m1221f() : constraintWidget2.f1431v.m1221f() : constraintWidget2.f1432w.m1221f() : constraintWidget2.f1430u.m1221f();
                    if (c0296iM1221f2 != null) {
                        this.f1444y0.add(c0296iM1221f2);
                        c0296iM1221f2.m21091a(c0296iM1221f);
                    }
                }
            }
        }
    }
}
