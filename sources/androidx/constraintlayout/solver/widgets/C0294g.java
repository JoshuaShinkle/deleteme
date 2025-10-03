package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import p150o.C5385b;

/* renamed from: androidx.constraintlayout.solver.widgets.g */
/* loaded from: classes.dex */
public class C0294g extends ConstraintWidget {

    /* renamed from: v0 */
    public float f1500v0 = -1.0f;

    /* renamed from: w0 */
    public int f1501w0 = -1;

    /* renamed from: x0 */
    public int f1502x0 = -1;

    /* renamed from: y0 */
    public ConstraintAnchor f1503y0 = this.f1431v;

    /* renamed from: z0 */
    public int f1504z0 = 0;

    /* renamed from: A0 */
    public boolean f1496A0 = false;

    /* renamed from: B0 */
    public int f1497B0 = 0;

    /* renamed from: C0 */
    public C5385b f1498C0 = new C5385b();

    /* renamed from: D0 */
    public int f1499D0 = 8;

    /* renamed from: androidx.constraintlayout.solver.widgets.g$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f1505a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f1505a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1505a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1505a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1505a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1505a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1505a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1505a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1505a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1505a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public C0294g() {
        this.f1367D.clear();
        this.f1367D.add(this.f1503y0);
        int length = this.f1366C.length;
        for (int i9 = 0; i9 < length; i9++) {
            this.f1366C[i9] = this.f1503y0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: G0 */
    public void mo1243G0(C0285c c0285c) {
        if (m1304u() == null) {
            return;
        }
        int iM1214y = c0285c.m1214y(this.f1503y0);
        if (this.f1504z0 == 1) {
            m1235C0(iM1214y);
            m1237D0(0);
            m1267b0(m1304u().m1298r());
            m1313y0(0);
            return;
        }
        m1235C0(0);
        m1237D0(iM1214y);
        m1313y0(m1304u().m1236D());
        m1267b0(0);
    }

    /* renamed from: I0 */
    public int m1362I0() {
        return this.f1504z0;
    }

    /* renamed from: J0 */
    public void m1363J0(int i9) {
        if (i9 > -1) {
            this.f1500v0 = -1.0f;
            this.f1501w0 = i9;
            this.f1502x0 = -1;
        }
    }

    /* renamed from: K0 */
    public void m1364K0(int i9) {
        if (i9 > -1) {
            this.f1500v0 = -1.0f;
            this.f1501w0 = -1;
            this.f1502x0 = i9;
        }
    }

    /* renamed from: L0 */
    public void m1365L0(float f9) {
        if (f9 > -1.0f) {
            this.f1500v0 = f9;
            this.f1501w0 = -1;
            this.f1502x0 = -1;
        }
    }

    /* renamed from: M0 */
    public void m1366M0(int i9) {
        if (this.f1504z0 == i9) {
            return;
        }
        this.f1504z0 = i9;
        this.f1367D.clear();
        if (this.f1504z0 == 1) {
            this.f1503y0 = this.f1430u;
        } else {
            this.f1503y0 = this.f1431v;
        }
        this.f1367D.add(this.f1503y0);
        int length = this.f1366C.length;
        for (int i10 = 0; i10 < length; i10++) {
            this.f1366C[i10] = this.f1503y0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: b */
    public void mo1266b(C0285c c0285c) {
        C0292e c0292e = (C0292e) m1304u();
        if (c0292e == null) {
            return;
        }
        ConstraintAnchor constraintAnchorMo1278h = c0292e.mo1278h(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorMo1278h2 = c0292e.mo1278h(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.f1369F;
        boolean z8 = constraintWidget != null && constraintWidget.f1368E[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.f1504z0 == 0) {
            constraintAnchorMo1278h = c0292e.mo1278h(ConstraintAnchor.Type.TOP);
            constraintAnchorMo1278h2 = c0292e.mo1278h(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.f1369F;
            z8 = constraintWidget2 != null && constraintWidget2.f1368E[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.f1501w0 != -1) {
            SolverVariable solverVariableM1209r = c0285c.m1209r(this.f1503y0);
            c0285c.m1196e(solverVariableM1209r, c0285c.m1209r(constraintAnchorMo1278h), this.f1501w0, 6);
            if (z8) {
                c0285c.m1200i(c0285c.m1209r(constraintAnchorMo1278h2), solverVariableM1209r, 0, 5);
                return;
            }
            return;
        }
        if (this.f1502x0 == -1) {
            if (this.f1500v0 != -1.0f) {
                c0285c.m1195d(C0285c.m1184t(c0285c, c0285c.m1209r(this.f1503y0), c0285c.m1209r(constraintAnchorMo1278h), c0285c.m1209r(constraintAnchorMo1278h2), this.f1500v0, this.f1496A0));
                return;
            }
            return;
        }
        SolverVariable solverVariableM1209r2 = c0285c.m1209r(this.f1503y0);
        SolverVariable solverVariableM1209r3 = c0285c.m1209r(constraintAnchorMo1278h2);
        c0285c.m1196e(solverVariableM1209r2, solverVariableM1209r3, -this.f1502x0, 6);
        if (z8) {
            c0285c.m1200i(solverVariableM1209r2, c0285c.m1209r(constraintAnchorMo1278h), 0, 5);
            c0285c.m1200i(solverVariableM1209r3, solverVariableM1209r2, 0, 5);
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
        ConstraintWidget constraintWidgetM1304u = m1304u();
        if (constraintWidgetM1304u == null) {
            return;
        }
        if (m1362I0() == 1) {
            this.f1431v.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), 0);
            this.f1433x.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), 0);
            if (this.f1501w0 != -1) {
                this.f1430u.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), this.f1501w0);
                this.f1432w.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), this.f1501w0);
                return;
            } else if (this.f1502x0 != -1) {
                this.f1430u.m1221f().m1375h(1, constraintWidgetM1304u.f1432w.m1221f(), -this.f1502x0);
                this.f1432w.m1221f().m1375h(1, constraintWidgetM1304u.f1432w.m1221f(), -this.f1502x0);
                return;
            } else {
                if (this.f1500v0 == -1.0f || constraintWidgetM1304u.m1300s() != ConstraintWidget.DimensionBehaviour.FIXED) {
                    return;
                }
                int i10 = (int) (constraintWidgetM1304u.f1370G * this.f1500v0);
                this.f1430u.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), i10);
                this.f1432w.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), i10);
                return;
            }
        }
        this.f1430u.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), 0);
        this.f1432w.m1221f().m1375h(1, constraintWidgetM1304u.f1430u.m1221f(), 0);
        if (this.f1501w0 != -1) {
            this.f1431v.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), this.f1501w0);
            this.f1433x.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), this.f1501w0);
        } else if (this.f1502x0 != -1) {
            this.f1431v.m1221f().m1375h(1, constraintWidgetM1304u.f1433x.m1221f(), -this.f1502x0);
            this.f1433x.m1221f().m1375h(1, constraintWidgetM1304u.f1433x.m1221f(), -this.f1502x0);
        } else {
            if (this.f1500v0 == -1.0f || constraintWidgetM1304u.m1232B() != ConstraintWidget.DimensionBehaviour.FIXED) {
                return;
            }
            int i11 = (int) (constraintWidgetM1304u.f1371H * this.f1500v0);
            this.f1431v.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), i11);
            this.f1433x.m1221f().m1375h(1, constraintWidgetM1304u.f1431v.m1221f(), i11);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: h */
    public ConstraintAnchor mo1278h(ConstraintAnchor.Type type) {
        switch (a.f1505a[type.ordinal()]) {
            case 1:
            case 2:
                if (this.f1504z0 == 1) {
                    return this.f1503y0;
                }
                break;
            case 3:
            case 4:
                if (this.f1504z0 == 0) {
                    return this.f1503y0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(type.name());
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: i */
    public ArrayList<ConstraintAnchor> mo1280i() {
        return this.f1367D;
    }
}
