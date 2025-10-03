package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: androidx.constraintlayout.solver.widgets.h */
/* loaded from: classes.dex */
public class C0295h {

    /* renamed from: a */
    public static boolean[] f1506a = new boolean[3];

    /* renamed from: a */
    public static void m1367a(int i9, ConstraintWidget constraintWidget) {
        constraintWidget.m1245H0();
        C0296i c0296iM1221f = constraintWidget.f1430u.m1221f();
        C0296i c0296iM1221f2 = constraintWidget.f1431v.m1221f();
        C0296i c0296iM1221f3 = constraintWidget.f1432w.m1221f();
        C0296i c0296iM1221f4 = constraintWidget.f1433x.m1221f();
        boolean z8 = (i9 & 8) == 8;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.f1368E[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z9 = dimensionBehaviour == dimensionBehaviour2 && m1370d(constraintWidget, 0);
        if (c0296iM1221f.f1513i != 4 && c0296iM1221f3.f1513i != 4) {
            if (constraintWidget.f1368E[0] == ConstraintWidget.DimensionBehaviour.FIXED || (z9 && constraintWidget.m1234C() == 8)) {
                ConstraintAnchor constraintAnchor = constraintWidget.f1430u.f1338d;
                if (constraintAnchor == null && constraintWidget.f1432w.f1338d == null) {
                    c0296iM1221f.m1383p(1);
                    c0296iM1221f3.m1383p(1);
                    if (z8) {
                        c0296iM1221f3.m1377j(c0296iM1221f, 1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f3.m1376i(c0296iM1221f, constraintWidget.m1236D());
                    }
                } else if (constraintAnchor != null && constraintWidget.f1432w.f1338d == null) {
                    c0296iM1221f.m1383p(1);
                    c0296iM1221f3.m1383p(1);
                    if (z8) {
                        c0296iM1221f3.m1377j(c0296iM1221f, 1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f3.m1376i(c0296iM1221f, constraintWidget.m1236D());
                    }
                } else if (constraintAnchor == null && constraintWidget.f1432w.f1338d != null) {
                    c0296iM1221f.m1383p(1);
                    c0296iM1221f3.m1383p(1);
                    c0296iM1221f.m1376i(c0296iM1221f3, -constraintWidget.m1236D());
                    if (z8) {
                        c0296iM1221f.m1377j(c0296iM1221f3, -1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f.m1376i(c0296iM1221f3, -constraintWidget.m1236D());
                    }
                } else if (constraintAnchor != null && constraintWidget.f1432w.f1338d != null) {
                    c0296iM1221f.m1383p(2);
                    c0296iM1221f3.m1383p(2);
                    if (z8) {
                        constraintWidget.m1310x().m21091a(c0296iM1221f);
                        constraintWidget.m1310x().m21091a(c0296iM1221f3);
                        c0296iM1221f.m1382o(c0296iM1221f3, -1, constraintWidget.m1310x());
                        c0296iM1221f3.m1382o(c0296iM1221f, 1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f.m1381n(c0296iM1221f3, -constraintWidget.m1236D());
                        c0296iM1221f3.m1381n(c0296iM1221f, constraintWidget.m1236D());
                    }
                }
            } else if (z9) {
                int iM1236D = constraintWidget.m1236D();
                c0296iM1221f.m1383p(1);
                c0296iM1221f3.m1383p(1);
                ConstraintAnchor constraintAnchor2 = constraintWidget.f1430u.f1338d;
                if (constraintAnchor2 == null && constraintWidget.f1432w.f1338d == null) {
                    if (z8) {
                        c0296iM1221f3.m1377j(c0296iM1221f, 1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f3.m1376i(c0296iM1221f, iM1236D);
                    }
                } else if (constraintAnchor2 == null || constraintWidget.f1432w.f1338d != null) {
                    if (constraintAnchor2 != null || constraintWidget.f1432w.f1338d == null) {
                        if (constraintAnchor2 != null && constraintWidget.f1432w.f1338d != null) {
                            if (z8) {
                                constraintWidget.m1310x().m21091a(c0296iM1221f);
                                constraintWidget.m1310x().m21091a(c0296iM1221f3);
                            }
                            if (constraintWidget.f1372I == BitmapDescriptorFactory.HUE_RED) {
                                c0296iM1221f.m1383p(3);
                                c0296iM1221f3.m1383p(3);
                                c0296iM1221f.m1381n(c0296iM1221f3, BitmapDescriptorFactory.HUE_RED);
                                c0296iM1221f3.m1381n(c0296iM1221f, BitmapDescriptorFactory.HUE_RED);
                            } else {
                                c0296iM1221f.m1383p(2);
                                c0296iM1221f3.m1383p(2);
                                c0296iM1221f.m1381n(c0296iM1221f3, -iM1236D);
                                c0296iM1221f3.m1381n(c0296iM1221f, iM1236D);
                                constraintWidget.m1313y0(iM1236D);
                            }
                        }
                    } else if (z8) {
                        c0296iM1221f.m1377j(c0296iM1221f3, -1, constraintWidget.m1310x());
                    } else {
                        c0296iM1221f.m1376i(c0296iM1221f3, -iM1236D);
                    }
                } else if (z8) {
                    c0296iM1221f3.m1377j(c0296iM1221f, 1, constraintWidget.m1310x());
                } else {
                    c0296iM1221f3.m1376i(c0296iM1221f, iM1236D);
                }
            }
        }
        boolean z10 = constraintWidget.f1368E[1] == dimensionBehaviour2 && m1370d(constraintWidget, 1);
        if (c0296iM1221f2.f1513i == 4 || c0296iM1221f4.f1513i == 4) {
            return;
        }
        if (constraintWidget.f1368E[1] != ConstraintWidget.DimensionBehaviour.FIXED && (!z10 || constraintWidget.m1234C() != 8)) {
            if (z10) {
                int iM1298r = constraintWidget.m1298r();
                c0296iM1221f2.m1383p(1);
                c0296iM1221f4.m1383p(1);
                ConstraintAnchor constraintAnchor3 = constraintWidget.f1431v.f1338d;
                if (constraintAnchor3 == null && constraintWidget.f1433x.f1338d == null) {
                    if (z8) {
                        c0296iM1221f4.m1377j(c0296iM1221f2, 1, constraintWidget.m1308w());
                        return;
                    } else {
                        c0296iM1221f4.m1376i(c0296iM1221f2, iM1298r);
                        return;
                    }
                }
                if (constraintAnchor3 != null && constraintWidget.f1433x.f1338d == null) {
                    if (z8) {
                        c0296iM1221f4.m1377j(c0296iM1221f2, 1, constraintWidget.m1308w());
                        return;
                    } else {
                        c0296iM1221f4.m1376i(c0296iM1221f2, iM1298r);
                        return;
                    }
                }
                if (constraintAnchor3 == null && constraintWidget.f1433x.f1338d != null) {
                    if (z8) {
                        c0296iM1221f2.m1377j(c0296iM1221f4, -1, constraintWidget.m1308w());
                        return;
                    } else {
                        c0296iM1221f2.m1376i(c0296iM1221f4, -iM1298r);
                        return;
                    }
                }
                if (constraintAnchor3 == null || constraintWidget.f1433x.f1338d == null) {
                    return;
                }
                if (z8) {
                    constraintWidget.m1308w().m21091a(c0296iM1221f2);
                    constraintWidget.m1310x().m21091a(c0296iM1221f4);
                }
                if (constraintWidget.f1372I == BitmapDescriptorFactory.HUE_RED) {
                    c0296iM1221f2.m1383p(3);
                    c0296iM1221f4.m1383p(3);
                    c0296iM1221f2.m1381n(c0296iM1221f4, BitmapDescriptorFactory.HUE_RED);
                    c0296iM1221f4.m1381n(c0296iM1221f2, BitmapDescriptorFactory.HUE_RED);
                    return;
                }
                c0296iM1221f2.m1383p(2);
                c0296iM1221f4.m1383p(2);
                c0296iM1221f2.m1381n(c0296iM1221f4, -iM1298r);
                c0296iM1221f4.m1381n(c0296iM1221f2, iM1298r);
                constraintWidget.m1267b0(iM1298r);
                if (constraintWidget.f1384U > 0) {
                    constraintWidget.f1434y.m1221f().m1375h(1, c0296iM1221f2, constraintWidget.f1384U);
                    return;
                }
                return;
            }
            return;
        }
        ConstraintAnchor constraintAnchor4 = constraintWidget.f1431v.f1338d;
        if (constraintAnchor4 == null && constraintWidget.f1433x.f1338d == null) {
            c0296iM1221f2.m1383p(1);
            c0296iM1221f4.m1383p(1);
            if (z8) {
                c0296iM1221f4.m1377j(c0296iM1221f2, 1, constraintWidget.m1308w());
            } else {
                c0296iM1221f4.m1376i(c0296iM1221f2, constraintWidget.m1298r());
            }
            ConstraintAnchor constraintAnchor5 = constraintWidget.f1434y;
            if (constraintAnchor5.f1338d != null) {
                constraintAnchor5.m1221f().m1383p(1);
                c0296iM1221f2.m1375h(1, constraintWidget.f1434y.m1221f(), -constraintWidget.f1384U);
                return;
            }
            return;
        }
        if (constraintAnchor4 != null && constraintWidget.f1433x.f1338d == null) {
            c0296iM1221f2.m1383p(1);
            c0296iM1221f4.m1383p(1);
            if (z8) {
                c0296iM1221f4.m1377j(c0296iM1221f2, 1, constraintWidget.m1308w());
            } else {
                c0296iM1221f4.m1376i(c0296iM1221f2, constraintWidget.m1298r());
            }
            if (constraintWidget.f1384U > 0) {
                constraintWidget.f1434y.m1221f().m1375h(1, c0296iM1221f2, constraintWidget.f1384U);
                return;
            }
            return;
        }
        if (constraintAnchor4 == null && constraintWidget.f1433x.f1338d != null) {
            c0296iM1221f2.m1383p(1);
            c0296iM1221f4.m1383p(1);
            if (z8) {
                c0296iM1221f2.m1377j(c0296iM1221f4, -1, constraintWidget.m1308w());
            } else {
                c0296iM1221f2.m1376i(c0296iM1221f4, -constraintWidget.m1298r());
            }
            if (constraintWidget.f1384U > 0) {
                constraintWidget.f1434y.m1221f().m1375h(1, c0296iM1221f2, constraintWidget.f1384U);
                return;
            }
            return;
        }
        if (constraintAnchor4 == null || constraintWidget.f1433x.f1338d == null) {
            return;
        }
        c0296iM1221f2.m1383p(2);
        c0296iM1221f4.m1383p(2);
        if (z8) {
            c0296iM1221f2.m1382o(c0296iM1221f4, -1, constraintWidget.m1308w());
            c0296iM1221f4.m1382o(c0296iM1221f2, 1, constraintWidget.m1308w());
            constraintWidget.m1308w().m21091a(c0296iM1221f2);
            constraintWidget.m1310x().m21091a(c0296iM1221f4);
        } else {
            c0296iM1221f2.m1381n(c0296iM1221f4, -constraintWidget.m1298r());
            c0296iM1221f4.m1381n(c0296iM1221f2, constraintWidget.m1298r());
        }
        if (constraintWidget.f1384U > 0) {
            constraintWidget.f1434y.m1221f().m1375h(1, c0296iM1221f2, constraintWidget.f1384U);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[PHI: r11 r12
      0x002c: PHI (r11v10 boolean) = (r11v1 boolean), (r11v12 boolean) binds: [B:21:0x003c, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]
      0x002c: PHI (r12v8 boolean) = (r12v1 boolean), (r12v10 boolean) binds: [B:21:0x003c, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002e A[PHI: r11 r12
      0x002e: PHI (r11v3 boolean) = (r11v1 boolean), (r11v12 boolean) binds: [B:21:0x003c, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]
      0x002e: PHI (r12v3 boolean) = (r12v1 boolean), (r12v10 boolean) binds: [B:21:0x003c, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ee  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m1368b(C0292e c0292e, C0285c c0285c, int i9, int i10, C0291d c0291d) {
        boolean z8;
        boolean z9;
        boolean z10;
        C0296i c0296i;
        float fM1219d;
        float f9;
        ConstraintWidget constraintWidget;
        boolean z11;
        ConstraintWidget constraintWidget2 = c0291d.f1446a;
        ConstraintWidget constraintWidget3 = c0291d.f1448c;
        ConstraintWidget constraintWidget4 = c0291d.f1447b;
        ConstraintWidget constraintWidget5 = c0291d.f1449d;
        ConstraintWidget constraintWidget6 = c0291d.f1450e;
        float f10 = c0291d.f1456k;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = c0292e.f1368E[i9];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (i9 == 0) {
            int i11 = constraintWidget6.f1413l0;
            z8 = i11 == 0;
            z9 = i11 == 1;
            z10 = i11 == 2;
        } else {
            int i12 = constraintWidget6.f1415m0;
            z8 = i12 == 0;
            z9 = i12 == 1;
            if (i12 == 2) {
            }
        }
        ConstraintWidget constraintWidget7 = constraintWidget2;
        int i13 = 0;
        boolean z12 = false;
        int i14 = 0;
        float fM1236D = BitmapDescriptorFactory.HUE_RED;
        float fM1219d2 = BitmapDescriptorFactory.HUE_RED;
        while (!z12) {
            if (constraintWidget7.m1234C() != 8) {
                i14++;
                fM1236D += i9 == 0 ? constraintWidget7.m1236D() : constraintWidget7.m1298r();
                if (constraintWidget7 != constraintWidget4) {
                    fM1236D += constraintWidget7.f1366C[i10].m1219d();
                }
                if (constraintWidget7 != constraintWidget5) {
                    fM1236D += constraintWidget7.f1366C[i10 + 1].m1219d();
                }
                fM1219d2 = fM1219d2 + constraintWidget7.f1366C[i10].m1219d() + constraintWidget7.f1366C[i10 + 1].m1219d();
            }
            ConstraintAnchor constraintAnchor = constraintWidget7.f1366C[i10];
            if (constraintWidget7.m1234C() != 8 && constraintWidget7.f1368E[i9] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                i13++;
                if (i9 != 0) {
                    z11 = false;
                    if (constraintWidget7.f1400f != 0) {
                        return false;
                    }
                    if (constraintWidget7.f1410k != 0 || constraintWidget7.f1412l != 0) {
                    }
                    return z11;
                }
                if (constraintWidget7.f1398e != 0) {
                    return false;
                }
                z11 = false;
                if (constraintWidget7.f1404h != 0 || constraintWidget7.f1406i != 0) {
                    return false;
                }
                if (constraintWidget7.f1372I != BitmapDescriptorFactory.HUE_RED) {
                    return z11;
                }
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget7.f1366C[i10 + 1].f1338d;
            if (constraintAnchor2 != null) {
                ConstraintWidget constraintWidget8 = constraintAnchor2.f1336b;
                ConstraintAnchor constraintAnchor3 = constraintWidget8.f1366C[i10].f1338d;
                constraintWidget = (constraintAnchor3 == null || constraintAnchor3.f1336b != constraintWidget7) ? null : constraintWidget8;
            }
            if (constraintWidget != null) {
                constraintWidget7 = constraintWidget;
            } else {
                z12 = true;
            }
        }
        C0296i c0296iM1221f = constraintWidget2.f1366C[i10].m1221f();
        int i15 = i10 + 1;
        C0296i c0296iM1221f2 = constraintWidget3.f1366C[i15].m1221f();
        C0296i c0296i2 = c0296iM1221f.f1509e;
        if (c0296i2 == null || (c0296i = c0296iM1221f2.f1509e) == null || c0296i2.f18287b != 1 || c0296i.f18287b != 1) {
            return false;
        }
        if (i13 > 0 && i13 != i14) {
            return false;
        }
        if (z10 || z8 || z9) {
            fM1219d = constraintWidget4 != null ? constraintWidget4.f1366C[i10].m1219d() : BitmapDescriptorFactory.HUE_RED;
            if (constraintWidget5 != null) {
                fM1219d += constraintWidget5.f1366C[i15].m1219d();
            }
        } else {
            fM1219d = BitmapDescriptorFactory.HUE_RED;
        }
        float f11 = c0296iM1221f.f1509e.f1512h;
        float f12 = c0296iM1221f2.f1509e.f1512h;
        float f13 = (f11 < f12 ? f12 - f11 : f11 - f12) - fM1236D;
        if (i13 > 0 && i13 == i14) {
            if (constraintWidget7.m1304u() != null && constraintWidget7.m1304u().f1368E[i9] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                return false;
            }
            float f14 = (f13 + fM1236D) - fM1219d2;
            float fM1219d3 = f11;
            ConstraintWidget constraintWidget9 = constraintWidget2;
            while (constraintWidget9 != null) {
                int i16 = C0285c.f1318q;
                ConstraintWidget constraintWidget10 = constraintWidget9.f1425r0[i9];
                if (constraintWidget10 != null || constraintWidget9 == constraintWidget3) {
                    float f15 = f14 / i13;
                    if (f10 > BitmapDescriptorFactory.HUE_RED) {
                        float f16 = constraintWidget9.f1421p0[i9];
                        if (f16 == -1.0f) {
                            f9 = BitmapDescriptorFactory.HUE_RED;
                            if (constraintWidget9.m1234C() == 8) {
                                f9 = BitmapDescriptorFactory.HUE_RED;
                            }
                            float fM1219d4 = fM1219d3 + constraintWidget9.f1366C[i10].m1219d();
                            constraintWidget9.f1366C[i10].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d4);
                            float f17 = fM1219d4 + f9;
                            constraintWidget9.f1366C[i15].m1221f().m1379l(c0296iM1221f.f1511g, f17);
                            constraintWidget9.f1366C[i10].m1221f().m1374g(c0285c);
                            constraintWidget9.f1366C[i15].m1221f().m1374g(c0285c);
                            fM1219d3 = f17 + constraintWidget9.f1366C[i15].m1219d();
                        } else {
                            f15 = (f16 * f14) / f10;
                            f9 = f15;
                            if (constraintWidget9.m1234C() == 8) {
                            }
                            float fM1219d42 = fM1219d3 + constraintWidget9.f1366C[i10].m1219d();
                            constraintWidget9.f1366C[i10].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d42);
                            float f172 = fM1219d42 + f9;
                            constraintWidget9.f1366C[i15].m1221f().m1379l(c0296iM1221f.f1511g, f172);
                            constraintWidget9.f1366C[i10].m1221f().m1374g(c0285c);
                            constraintWidget9.f1366C[i15].m1221f().m1374g(c0285c);
                            fM1219d3 = f172 + constraintWidget9.f1366C[i15].m1219d();
                        }
                    } else {
                        f9 = f15;
                        if (constraintWidget9.m1234C() == 8) {
                        }
                        float fM1219d422 = fM1219d3 + constraintWidget9.f1366C[i10].m1219d();
                        constraintWidget9.f1366C[i10].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d422);
                        float f1722 = fM1219d422 + f9;
                        constraintWidget9.f1366C[i15].m1221f().m1379l(c0296iM1221f.f1511g, f1722);
                        constraintWidget9.f1366C[i10].m1221f().m1374g(c0285c);
                        constraintWidget9.f1366C[i15].m1221f().m1374g(c0285c);
                        fM1219d3 = f1722 + constraintWidget9.f1366C[i15].m1219d();
                    }
                }
                constraintWidget9 = constraintWidget10;
            }
            return true;
        }
        if (f13 < BitmapDescriptorFactory.HUE_RED) {
            z10 = true;
            z8 = false;
            z9 = false;
        }
        if (z10) {
            ConstraintWidget constraintWidget11 = constraintWidget2;
            float fM1284k = f11 + ((f13 - fM1219d) * constraintWidget11.m1284k(i9));
            while (true) {
                ConstraintWidget constraintWidget12 = constraintWidget11;
                if (constraintWidget12 == null) {
                    return true;
                }
                int i17 = C0285c.f1318q;
                constraintWidget11 = constraintWidget12.f1425r0[i9];
                if (constraintWidget11 != null || constraintWidget12 == constraintWidget3) {
                    int iM1236D = i9 == 0 ? constraintWidget12.m1236D() : constraintWidget12.m1298r();
                    float fM1219d5 = fM1284k + constraintWidget12.f1366C[i10].m1219d();
                    constraintWidget12.f1366C[i10].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d5);
                    float f18 = fM1219d5 + iM1236D;
                    constraintWidget12.f1366C[i15].m1221f().m1379l(c0296iM1221f.f1511g, f18);
                    constraintWidget12.f1366C[i10].m1221f().m1374g(c0285c);
                    constraintWidget12.f1366C[i15].m1221f().m1374g(c0285c);
                    fM1284k = f18 + constraintWidget12.f1366C[i15].m1219d();
                }
            }
        } else {
            ConstraintWidget constraintWidget13 = constraintWidget2;
            if (!z8 && !z9) {
                return true;
            }
            if (z8 || z9) {
                f13 -= fM1219d;
            }
            float f19 = f13 / (i14 + 1);
            if (z9) {
                f19 = f13 / (i14 > 1 ? i14 - 1 : 2.0f);
            }
            float fM1219d6 = constraintWidget13.m1234C() != 8 ? f11 + f19 : f11;
            if (z9 && i14 > 1) {
                fM1219d6 = constraintWidget4.f1366C[i10].m1219d() + f11;
            }
            if (z8 && constraintWidget4 != null) {
                fM1219d6 += constraintWidget4.f1366C[i10].m1219d();
            }
            while (true) {
                ConstraintWidget constraintWidget14 = constraintWidget13;
                if (constraintWidget14 == null) {
                    return true;
                }
                int i18 = C0285c.f1318q;
                constraintWidget13 = constraintWidget14.f1425r0[i9];
                if (constraintWidget13 != null || constraintWidget14 == constraintWidget3) {
                    float fM1236D2 = i9 == 0 ? constraintWidget14.m1236D() : constraintWidget14.m1298r();
                    if (constraintWidget14 != constraintWidget4) {
                        fM1219d6 += constraintWidget14.f1366C[i10].m1219d();
                    }
                    constraintWidget14.f1366C[i10].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d6);
                    constraintWidget14.f1366C[i15].m1221f().m1379l(c0296iM1221f.f1511g, fM1219d6 + fM1236D2);
                    constraintWidget14.f1366C[i10].m1221f().m1374g(c0285c);
                    constraintWidget14.f1366C[i15].m1221f().m1374g(c0285c);
                    fM1219d6 += fM1236D2 + constraintWidget14.f1366C[i15].m1219d();
                    if (constraintWidget13 != null) {
                        if (constraintWidget13.m1234C() != 8) {
                            fM1219d6 += f19;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public static void m1369c(C0292e c0292e, C0285c c0285c, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = c0292e.f1368E[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.f1368E[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i9 = constraintWidget.f1430u.f1339e;
            int iM1236D = c0292e.m1236D() - constraintWidget.f1432w.f1339e;
            ConstraintAnchor constraintAnchor = constraintWidget.f1430u;
            constraintAnchor.f1344j = c0285c.m1209r(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.f1432w;
            constraintAnchor2.f1344j = c0285c.m1209r(constraintAnchor2);
            c0285c.m1197f(constraintWidget.f1430u.f1344j, i9);
            c0285c.m1197f(constraintWidget.f1432w.f1344j, iM1236D);
            constraintWidget.f1390a = 2;
            constraintWidget.m1275f0(i9, iM1236D);
        }
        if (c0292e.f1368E[1] == dimensionBehaviour2 || constraintWidget.f1368E[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i10 = constraintWidget.f1431v.f1339e;
        int iM1298r = c0292e.m1298r() - constraintWidget.f1433x.f1339e;
        ConstraintAnchor constraintAnchor3 = constraintWidget.f1431v;
        constraintAnchor3.f1344j = c0285c.m1209r(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.f1433x;
        constraintAnchor4.f1344j = c0285c.m1209r(constraintAnchor4);
        c0285c.m1197f(constraintWidget.f1431v.f1344j, i10);
        c0285c.m1197f(constraintWidget.f1433x.f1344j, iM1298r);
        if (constraintWidget.f1384U > 0 || constraintWidget.m1234C() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.f1434y;
            constraintAnchor5.f1344j = c0285c.m1209r(constraintAnchor5);
            c0285c.m1197f(constraintWidget.f1434y.f1344j, constraintWidget.f1384U + i10);
        }
        constraintWidget.f1392b = 2;
        constraintWidget.m1303t0(i10, iM1298r);
    }

    /* renamed from: d */
    public static boolean m1370d(ConstraintWidget constraintWidget, int i9) {
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.f1368E;
        if (dimensionBehaviourArr[i9] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            return false;
        }
        if (constraintWidget.f1372I != BitmapDescriptorFactory.HUE_RED) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[i9 != 0 ? (char) 0 : (char) 1];
            return false;
        }
        if (i9 == 0) {
            if (constraintWidget.f1398e != 0 || constraintWidget.f1404h != 0 || constraintWidget.f1406i != 0) {
                return false;
            }
        } else if (constraintWidget.f1400f != 0 || constraintWidget.f1410k != 0 || constraintWidget.f1412l != 0) {
            return false;
        }
        return true;
    }

    /* renamed from: e */
    public static void m1371e(ConstraintWidget constraintWidget, int i9, int i10) {
        int i11 = i9 * 2;
        int i12 = i11 + 1;
        constraintWidget.f1366C[i11].m1221f().f1511g = constraintWidget.m1304u().f1430u.m1221f();
        constraintWidget.f1366C[i11].m1221f().f1512h = i10;
        constraintWidget.f1366C[i11].m1221f().f18287b = 1;
        constraintWidget.f1366C[i12].m1221f().f1511g = constraintWidget.f1366C[i11].m1221f();
        constraintWidget.f1366C[i12].m1221f().f1512h = constraintWidget.m1302t(i9);
        constraintWidget.f1366C[i12].m1221f().f18287b = 1;
    }
}
