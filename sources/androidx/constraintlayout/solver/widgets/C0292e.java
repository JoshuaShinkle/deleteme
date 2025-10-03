package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p150o.C5386c;
import p150o.C5388e;

/* renamed from: androidx.constraintlayout.solver.widgets.e */
/* loaded from: classes.dex */
public class C0292e extends C5388e {

    /* renamed from: A0 */
    public int f1463A0;

    /* renamed from: B0 */
    public int f1464B0;

    /* renamed from: C0 */
    public int f1465C0;

    /* renamed from: y0 */
    public C0297j f1483y0;

    /* renamed from: z0 */
    public int f1484z0;

    /* renamed from: w0 */
    public boolean f1481w0 = false;

    /* renamed from: x0 */
    public C0285c f1482x0 = new C0285c();

    /* renamed from: D0 */
    public int f1466D0 = 0;

    /* renamed from: E0 */
    public int f1467E0 = 0;

    /* renamed from: F0 */
    public C0291d[] f1468F0 = new C0291d[4];

    /* renamed from: G0 */
    public C0291d[] f1469G0 = new C0291d[4];

    /* renamed from: H0 */
    public List<C0293f> f1470H0 = new ArrayList();

    /* renamed from: I0 */
    public boolean f1471I0 = false;

    /* renamed from: J0 */
    public boolean f1472J0 = false;

    /* renamed from: K0 */
    public boolean f1473K0 = false;

    /* renamed from: L0 */
    public int f1474L0 = 0;

    /* renamed from: M0 */
    public int f1475M0 = 0;

    /* renamed from: N0 */
    public int f1476N0 = 7;

    /* renamed from: O0 */
    public boolean f1477O0 = false;

    /* renamed from: P0 */
    public boolean f1478P0 = false;

    /* renamed from: Q0 */
    public boolean f1479Q0 = false;

    /* renamed from: R0 */
    public int f1480R0 = 0;

    /* JADX WARN: Removed duplicated region for block: B:105:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02c2 A[PHI: r0 r9
      0x02c2: PHI (r0v31 boolean) = (r0v30 boolean), (r0v33 boolean), (r0v33 boolean), (r0v33 boolean) binds: [B:113:0x0285, B:121:0x02aa, B:122:0x02ac, B:124:0x02b2] A[DONT_GENERATE, DONT_INLINE]
      0x02c2: PHI (r9v7 boolean) = (r9v6 boolean), (r9v9 boolean), (r9v9 boolean), (r9v9 boolean) binds: [B:113:0x0285, B:121:0x02aa, B:122:0x02ac, B:124:0x02b2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01dd  */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v25 */
    @Override // p150o.C5388e
    /* renamed from: K0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo1335K0() {
        int i9;
        int i10;
        int i11;
        int i12;
        char c9;
        boolean z8;
        int iMax;
        int iMax2;
        ?? r8;
        boolean z9;
        int i13 = this.f1374K;
        int i14 = this.f1375L;
        int i15 = 0;
        int iMax3 = Math.max(0, m1236D());
        int iMax4 = Math.max(0, m1298r());
        this.f1478P0 = false;
        this.f1479Q0 = false;
        if (this.f1369F != null) {
            if (this.f1483y0 == null) {
                this.f1483y0 = new C0297j(this);
            }
            this.f1483y0.m1386b(this);
            m1235C0(this.f1484z0);
            m1237D0(this.f1463A0);
            m1255R();
            mo1257T(this.f1482x0.m1213w());
        } else {
            this.f1374K = 0;
            this.f1375L = 0;
        }
        int i16 = 32;
        if (this.f1476N0 != 0) {
            if (!m1346X0(8)) {
                m1348Z0();
            }
            if (!m1346X0(32)) {
                m1345W0();
            }
            this.f1482x0.f1325g = true;
        } else {
            this.f1482x0.f1325g = false;
        }
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = this.f1368E;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[1];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        m1350b1();
        if (this.f1470H0.size() == 0) {
            this.f1470H0.clear();
            this.f1470H0.add(0, new C0293f(this.f18288v0));
        }
        int size = this.f1470H0.size();
        ArrayList<ConstraintWidget> arrayList = this.f18288v0;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = m1300s();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z10 = dimensionBehaviourM1300s == dimensionBehaviour3 || m1232B() == dimensionBehaviour3;
        boolean z11 = false;
        int i17 = 0;
        while (i17 < size && !this.f1477O0) {
            if (this.f1470H0.get(i17).f1488d) {
                i9 = i14;
                i10 = size;
            } else {
                if (m1346X0(i16)) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s2 = m1300s();
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (dimensionBehaviourM1300s2 == dimensionBehaviour4 && m1232B() == dimensionBehaviour4) {
                        this.f18288v0 = (ArrayList) this.f1470H0.get(i17).m1358d();
                    } else {
                        this.f18288v0 = (ArrayList) this.f1470H0.get(i17).f1485a;
                    }
                }
                m1350b1();
                int size2 = this.f18288v0.size();
                for (int i18 = i15; i18 < size2; i18++) {
                    ConstraintWidget constraintWidget = this.f18288v0.get(i18);
                    if (constraintWidget instanceof C5388e) {
                        ((C5388e) constraintWidget).mo1335K0();
                    }
                }
                boolean z12 = z11;
                int i19 = 0;
                boolean zM1337O0 = true;
                while (zM1337O0) {
                    boolean z13 = z12;
                    int i20 = i19 + 1;
                    try {
                        this.f1482x0.m1190E();
                        m1350b1();
                        m1276g(this.f1482x0);
                        int i21 = 0;
                        while (i21 < size2) {
                            boolean z14 = zM1337O0;
                            try {
                                this.f18288v0.get(i21).m1276g(this.f1482x0);
                                i21++;
                                zM1337O0 = z14;
                            } catch (Exception e9) {
                                e = e9;
                                zM1337O0 = z14;
                                e.printStackTrace();
                                PrintStream printStream = System.out;
                                boolean z15 = zM1337O0;
                                StringBuilder sb = new StringBuilder();
                                i11 = size;
                                sb.append("EXCEPTION : ");
                                sb.append(e);
                                printStream.println(sb.toString());
                                zM1337O0 = z15;
                                if (!zM1337O0) {
                                }
                                i12 = i14;
                                c9 = 2;
                                if (z10) {
                                    z8 = false;
                                }
                                iMax = Math.max(this.f1385V, m1236D());
                                if (iMax > m1236D()) {
                                }
                                iMax2 = Math.max(this.f1386W, m1298r());
                                if (iMax2 > m1298r()) {
                                }
                                if (!z9) {
                                }
                                i19 = i20;
                                z12 = z9;
                                size = i11;
                                i14 = i12;
                            }
                        }
                        zM1337O0 = m1337O0(this.f1482x0);
                        if (zM1337O0) {
                            try {
                                this.f1482x0.m1186A();
                            } catch (Exception e10) {
                                e = e10;
                                e.printStackTrace();
                                PrintStream printStream2 = System.out;
                                boolean z152 = zM1337O0;
                                StringBuilder sb2 = new StringBuilder();
                                i11 = size;
                                sb2.append("EXCEPTION : ");
                                sb2.append(e);
                                printStream2.println(sb2.toString());
                                zM1337O0 = z152;
                                if (!zM1337O0) {
                                }
                                i12 = i14;
                                c9 = 2;
                                if (z10) {
                                }
                                iMax = Math.max(this.f1385V, m1236D());
                                if (iMax > m1236D()) {
                                }
                                iMax2 = Math.max(this.f1386W, m1298r());
                                if (iMax2 > m1298r()) {
                                }
                                if (!z9) {
                                }
                                i19 = i20;
                                z12 = z9;
                                size = i11;
                                i14 = i12;
                            }
                        }
                        i11 = size;
                    } catch (Exception e11) {
                        e = e11;
                    }
                    if (!zM1337O0) {
                        mo1243G0(this.f1482x0);
                        int i22 = 0;
                        while (i22 < size2) {
                            ConstraintWidget constraintWidget2 = this.f18288v0.get(i22);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = constraintWidget2.f1368E[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                            if (dimensionBehaviour5 == dimensionBehaviour6) {
                                i12 = i14;
                                if (constraintWidget2.m1236D() < constraintWidget2.m1240F()) {
                                    c9 = 2;
                                    C0295h.f1506a[2] = true;
                                    break;
                                }
                            } else {
                                i12 = i14;
                            }
                            if (constraintWidget2.f1368E[1] == dimensionBehaviour6 && constraintWidget2.m1298r() < constraintWidget2.m1238E()) {
                                c9 = 2;
                                C0295h.f1506a[2] = true;
                                break;
                            } else {
                                i22++;
                                i14 = i12;
                            }
                        }
                    } else {
                        m1354f1(this.f1482x0, C0295h.f1506a);
                    }
                    i12 = i14;
                    c9 = 2;
                    if (z10 && i20 < 8 && C0295h.f1506a[c9]) {
                        int iMax5 = 0;
                        int iMax6 = 0;
                        for (int i23 = 0; i23 < size2; i23++) {
                            ConstraintWidget constraintWidget3 = this.f18288v0.get(i23);
                            iMax5 = Math.max(iMax5, constraintWidget3.f1374K + constraintWidget3.m1236D());
                            iMax6 = Math.max(iMax6, constraintWidget3.f1375L + constraintWidget3.m1298r());
                        }
                        int iMax7 = Math.max(this.f1385V, iMax5);
                        int iMax8 = Math.max(this.f1386W, iMax6);
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                        if (dimensionBehaviour2 != dimensionBehaviour7 || m1236D() >= iMax7) {
                            z8 = false;
                        } else {
                            m1313y0(iMax7);
                            this.f1368E[0] = dimensionBehaviour7;
                            z8 = true;
                            z13 = true;
                        }
                        if (dimensionBehaviour == dimensionBehaviour7 && m1298r() < iMax8) {
                            m1267b0(iMax8);
                            this.f1368E[1] = dimensionBehaviour7;
                            z8 = true;
                            z13 = true;
                        }
                    } else {
                        z8 = false;
                    }
                    iMax = Math.max(this.f1385V, m1236D());
                    if (iMax > m1236D()) {
                        m1313y0(iMax);
                        this.f1368E[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                        z8 = true;
                        z13 = true;
                    }
                    iMax2 = Math.max(this.f1386W, m1298r());
                    if (iMax2 > m1298r()) {
                        m1267b0(iMax2);
                        r8 = 1;
                        this.f1368E[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                        z8 = true;
                        z9 = true;
                    } else {
                        r8 = 1;
                        z9 = z13;
                    }
                    if (!z9) {
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = this.f1368E[0];
                        ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                        if (dimensionBehaviour8 == dimensionBehaviour9 && iMax3 > 0 && m1236D() > iMax3) {
                            this.f1478P0 = r8;
                            this.f1368E[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                            m1313y0(iMax3);
                            z8 = r8;
                            z9 = z8;
                        }
                        if (this.f1368E[r8] != dimensionBehaviour9 || iMax4 <= 0 || m1298r() <= iMax4) {
                            zM1337O0 = z8;
                        } else {
                            this.f1479Q0 = r8;
                            this.f1368E[r8] = ConstraintWidget.DimensionBehaviour.FIXED;
                            m1267b0(iMax4);
                            zM1337O0 = true;
                            z9 = true;
                        }
                    }
                    i19 = i20;
                    z12 = z9;
                    size = i11;
                    i14 = i12;
                }
                i9 = i14;
                i10 = size;
                this.f1470H0.get(i17).m1361g();
                z11 = z12;
            }
            i17++;
            size = i10;
            i14 = i9;
            i15 = 0;
            i16 = 32;
        }
        int i24 = i14;
        this.f18288v0 = arrayList;
        if (this.f1369F != null) {
            int iMax9 = Math.max(this.f1385V, m1236D());
            int iMax10 = Math.max(this.f1386W, m1298r());
            this.f1483y0.m1385a(this);
            m1313y0(iMax9 + this.f1484z0 + this.f1464B0);
            m1267b0(iMax10 + this.f1463A0 + this.f1465C0);
        } else {
            this.f1374K = i13;
            this.f1375L = i24;
        }
        if (z11) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = this.f1368E;
            dimensionBehaviourArr2[0] = dimensionBehaviour2;
            dimensionBehaviourArr2[1] = dimensionBehaviour;
        }
        mo1257T(this.f1482x0.m1213w());
        if (this == m21096J0()) {
            mo1241F0();
        }
    }

    /* renamed from: N0 */
    public void m1336N0(ConstraintWidget constraintWidget, int i9) {
        if (i9 == 0) {
            m1338P0(constraintWidget);
        } else if (i9 == 1) {
            m1339Q0(constraintWidget);
        }
    }

    /* renamed from: O0 */
    public boolean m1337O0(C0285c c0285c) {
        mo1266b(c0285c);
        int size = this.f18288v0.size();
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget = this.f18288v0.get(i9);
            if (constraintWidget instanceof C0292e) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.f1368E;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour == dimensionBehaviour3) {
                    constraintWidget.m1277g0(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == dimensionBehaviour3) {
                    constraintWidget.m1305u0(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.mo1266b(c0285c);
                if (dimensionBehaviour == dimensionBehaviour3) {
                    constraintWidget.m1277g0(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == dimensionBehaviour3) {
                    constraintWidget.m1305u0(dimensionBehaviour2);
                }
            } else {
                C0295h.m1369c(this, c0285c, constraintWidget);
                constraintWidget.mo1266b(c0285c);
            }
        }
        if (this.f1466D0 > 0) {
            C0290c.m1330a(this, c0285c, 0);
        }
        if (this.f1467E0 > 0) {
            C0290c.m1330a(this, c0285c, 1);
        }
        return true;
    }

    /* renamed from: P0 */
    public final void m1338P0(ConstraintWidget constraintWidget) {
        int i9 = this.f1466D0 + 1;
        C0291d[] c0291dArr = this.f1469G0;
        if (i9 >= c0291dArr.length) {
            this.f1469G0 = (C0291d[]) Arrays.copyOf(c0291dArr, c0291dArr.length * 2);
        }
        this.f1469G0[this.f1466D0] = new C0291d(constraintWidget, 0, m1343U0());
        this.f1466D0++;
    }

    @Override // p150o.C5388e, androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: Q */
    public void mo1254Q() {
        this.f1482x0.m1190E();
        this.f1484z0 = 0;
        this.f1464B0 = 0;
        this.f1463A0 = 0;
        this.f1465C0 = 0;
        this.f1470H0.clear();
        this.f1477O0 = false;
        super.mo1254Q();
    }

    /* renamed from: Q0 */
    public final void m1339Q0(ConstraintWidget constraintWidget) {
        int i9 = this.f1467E0 + 1;
        C0291d[] c0291dArr = this.f1468F0;
        if (i9 >= c0291dArr.length) {
            this.f1468F0 = (C0291d[]) Arrays.copyOf(c0291dArr, c0291dArr.length * 2);
        }
        this.f1468F0[this.f1467E0] = new C0291d(constraintWidget, 1, m1343U0());
        this.f1467E0++;
    }

    /* renamed from: R0 */
    public int m1340R0() {
        return this.f1476N0;
    }

    /* renamed from: S0 */
    public boolean m1341S0() {
        return false;
    }

    /* renamed from: T0 */
    public boolean m1342T0() {
        return this.f1479Q0;
    }

    /* renamed from: U0 */
    public boolean m1343U0() {
        return this.f1481w0;
    }

    /* renamed from: V0 */
    public boolean m1344V0() {
        return this.f1478P0;
    }

    /* renamed from: W0 */
    public void m1345W0() {
        if (!m1346X0(8)) {
            mo1270d(this.f1476N0);
        }
        m1353e1();
    }

    /* renamed from: X0 */
    public boolean m1346X0(int i9) {
        return (this.f1476N0 & i9) == i9;
    }

    /* renamed from: Y0 */
    public void m1347Y0(int i9, int i10) {
        C5386c c5386c;
        C5386c c5386c2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f1368E[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && (c5386c2 = this.f1394c) != null) {
            c5386c2.m21090h(i9);
        }
        if (this.f1368E[1] == dimensionBehaviour2 || (c5386c = this.f1396d) == null) {
            return;
        }
        c5386c.m21090h(i10);
    }

    /* renamed from: Z0 */
    public void m1348Z0() {
        int size = this.f18288v0.size();
        mo1256S();
        for (int i9 = 0; i9 < size; i9++) {
            this.f18288v0.get(i9).mo1256S();
        }
    }

    /* renamed from: a1 */
    public void m1349a1() {
        m1348Z0();
        mo1270d(this.f1476N0);
    }

    /* renamed from: b1 */
    public final void m1350b1() {
        this.f1466D0 = 0;
        this.f1467E0 = 0;
    }

    /* renamed from: c1 */
    public void m1351c1(int i9) {
        this.f1476N0 = i9;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: d */
    public void mo1270d(int i9) {
        super.mo1270d(i9);
        int size = this.f18288v0.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.f18288v0.get(i10).mo1270d(i9);
        }
    }

    /* renamed from: d1 */
    public void m1352d1(boolean z8) {
        this.f1481w0 = z8;
    }

    /* renamed from: e1 */
    public void m1353e1() {
        C0296i c0296iM1221f = mo1278h(ConstraintAnchor.Type.LEFT).m1221f();
        C0296i c0296iM1221f2 = mo1278h(ConstraintAnchor.Type.TOP).m1221f();
        c0296iM1221f.m1379l(null, BitmapDescriptorFactory.HUE_RED);
        c0296iM1221f2.m1379l(null, BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: f1 */
    public void m1354f1(C0285c c0285c, boolean[] zArr) {
        zArr[2] = false;
        mo1243G0(c0285c);
        int size = this.f18288v0.size();
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget = this.f18288v0.get(i9);
            constraintWidget.mo1243G0(c0285c);
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.f1368E[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviour == dimensionBehaviour2 && constraintWidget.m1236D() < constraintWidget.m1240F()) {
                zArr[2] = true;
            }
            if (constraintWidget.f1368E[1] == dimensionBehaviour2 && constraintWidget.m1298r() < constraintWidget.m1238E()) {
                zArr[2] = true;
            }
        }
    }
}
