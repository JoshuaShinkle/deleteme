package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.C0284b;
import androidx.constraintlayout.solver.C0285c;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* renamed from: androidx.constraintlayout.solver.widgets.c */
/* loaded from: classes.dex */
public class C0290c {
    /* renamed from: a */
    public static void m1330a(C0292e c0292e, C0285c c0285c, int i9) {
        int i10;
        C0291d[] c0291dArr;
        int i11;
        if (i9 == 0) {
            i10 = c0292e.f1466D0;
            c0291dArr = c0292e.f1469G0;
            i11 = 0;
        } else {
            i10 = c0292e.f1467E0;
            c0291dArr = c0292e.f1468F0;
            i11 = 2;
        }
        for (int i12 = 0; i12 < i10; i12++) {
            C0291d c0291d = c0291dArr[i12];
            c0291d.m1333a();
            if (!c0292e.m1346X0(4)) {
                m1331b(c0292e, c0285c, i9, i11, c0291d);
            } else if (!C0295h.m1368b(c0292e, c0285c, i9, i11, c0291d)) {
                m1331b(c0292e, c0285c, i9, i11, c0291d);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e A[PHI: r8 r15
      0x003e: PHI (r8v3 boolean) = (r8v1 boolean), (r8v32 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
      0x003e: PHI (r15v3 boolean) = (r15v1 boolean), (r15v35 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0040 A[PHI: r8 r15
      0x0040: PHI (r8v30 boolean) = (r8v1 boolean), (r8v32 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
      0x0040: PHI (r15v33 boolean) = (r15v1 boolean), (r15v35 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0137  */
    /* JADX WARN: Type inference failed for: r2v58, types: [androidx.constraintlayout.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.solver.widgets.ConstraintWidget] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m1331b(C0292e c0292e, C0285c c0285c, int i9, int i10, C0291d c0291d) {
        boolean z8;
        boolean z9;
        boolean z10;
        int i11;
        SolverVariable solverVariable;
        ConstraintAnchor constraintAnchor;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        ConstraintWidget constraintWidget2;
        int size;
        ArrayList<ConstraintWidget> arrayList;
        int i12;
        float f9;
        int i13;
        boolean z11;
        ConstraintWidget constraintWidget3;
        boolean z12;
        int i14;
        ConstraintWidget constraintWidget4 = c0291d.f1446a;
        ConstraintWidget constraintWidget5 = c0291d.f1448c;
        ConstraintWidget constraintWidget6 = c0291d.f1447b;
        ConstraintWidget constraintWidget7 = c0291d.f1449d;
        ConstraintWidget constraintWidget8 = c0291d.f1450e;
        float f10 = c0291d.f1456k;
        boolean z13 = c0292e.f1368E[i9] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i9 == 0) {
            int i15 = constraintWidget8.f1413l0;
            z8 = i15 == 0;
            z9 = i15 == 1;
            z10 = i15 == 2;
        } else {
            int i16 = constraintWidget8.f1415m0;
            z8 = i16 == 0;
            z9 = i16 == 1;
            if (i16 == 2) {
            }
        }
        boolean z14 = z9;
        boolean z15 = false;
        boolean z16 = z8;
        ?? r8 = constraintWidget4;
        while (true) {
            if (z15) {
                break;
            }
            ConstraintAnchor constraintAnchor3 = r8.f1366C[i10];
            int i17 = (z13 || z10) ? 1 : 4;
            int iM1219d = constraintAnchor3.m1219d();
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.f1338d;
            if (constraintAnchor4 != null && r8 != constraintWidget4) {
                iM1219d += constraintAnchor4.m1219d();
            }
            int i18 = iM1219d;
            if (z10 && r8 != constraintWidget4 && r8 != constraintWidget6) {
                f9 = f10;
                z11 = z15;
                i13 = 6;
            } else if (z16 && z13) {
                f9 = f10;
                z11 = z15;
                i13 = 4;
            } else {
                f9 = f10;
                i13 = i17;
                z11 = z15;
            }
            ConstraintAnchor constraintAnchor5 = constraintAnchor3.f1338d;
            if (constraintAnchor5 != null) {
                if (r8 == constraintWidget6) {
                    z12 = z16;
                    constraintWidget3 = constraintWidget8;
                    c0285c.m1200i(constraintAnchor3.f1344j, constraintAnchor5.f1344j, i18, 5);
                } else {
                    constraintWidget3 = constraintWidget8;
                    z12 = z16;
                    c0285c.m1200i(constraintAnchor3.f1344j, constraintAnchor5.f1344j, i18, 6);
                }
                c0285c.m1196e(constraintAnchor3.f1344j, constraintAnchor3.f1338d.f1344j, i18, i13);
            } else {
                constraintWidget3 = constraintWidget8;
                z12 = z16;
            }
            if (z13) {
                if (r8.m1234C() == 8 || r8.f1368E[i9] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i14 = 0;
                } else {
                    ConstraintAnchor[] constraintAnchorArr = r8.f1366C;
                    i14 = 0;
                    c0285c.m1200i(constraintAnchorArr[i10 + 1].f1344j, constraintAnchorArr[i10].f1344j, 0, 5);
                }
                c0285c.m1200i(r8.f1366C[i10].f1344j, c0292e.f1366C[i10].f1344j, i14, 6);
            }
            ConstraintAnchor constraintAnchor6 = r8.f1366C[i10 + 1].f1338d;
            if (constraintAnchor6 != null) {
                ?? r22 = constraintAnchor6.f1336b;
                ConstraintAnchor constraintAnchor7 = r22.f1366C[i10].f1338d;
                if (constraintAnchor7 != null && constraintAnchor7.f1336b == r8) {
                    solverVariable = r22;
                }
            }
            if (solverVariable != null) {
                r8 = solverVariable;
                z15 = z11;
            } else {
                z15 = true;
            }
            f10 = f9;
            z16 = z12;
            constraintWidget8 = constraintWidget3;
            r8 = r8;
        }
        ConstraintWidget constraintWidget9 = constraintWidget8;
        float f11 = f10;
        boolean z17 = z16;
        if (constraintWidget7 != null) {
            int i19 = i10 + 1;
            ConstraintAnchor constraintAnchor8 = constraintWidget5.f1366C[i19].f1338d;
            if (constraintAnchor8 != null) {
                ConstraintAnchor constraintAnchor9 = constraintWidget7.f1366C[i19];
                c0285c.m1202k(constraintAnchor9.f1344j, constraintAnchor8.f1344j, -constraintAnchor9.m1219d(), 5);
            }
        }
        if (z13) {
            int i20 = i10 + 1;
            SolverVariable solverVariable5 = c0292e.f1366C[i20].f1344j;
            ConstraintAnchor constraintAnchor10 = constraintWidget5.f1366C[i20];
            c0285c.m1200i(solverVariable5, constraintAnchor10.f1344j, constraintAnchor10.m1219d(), 6);
        }
        ArrayList<ConstraintWidget> arrayList2 = c0291d.f1453h;
        if (arrayList2 != null && (size = arrayList2.size()) > 1) {
            float f12 = (!c0291d.f1459n || c0291d.f1461p) ? f11 : c0291d.f1455j;
            float f13 = BitmapDescriptorFactory.HUE_RED;
            float f14 = 0.0f;
            ConstraintWidget constraintWidget10 = null;
            int i21 = 0;
            while (i21 < size) {
                ConstraintWidget constraintWidget11 = arrayList2.get(i21);
                float f15 = constraintWidget11.f1421p0[i9];
                if (f15 < f13) {
                    if (c0291d.f1461p) {
                        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget11.f1366C;
                        c0285c.m1196e(constraintAnchorArr2[i10 + 1].f1344j, constraintAnchorArr2[i10].f1344j, 0, 4);
                        arrayList = arrayList2;
                        i12 = size;
                        i21++;
                        size = i12;
                        arrayList2 = arrayList;
                        f13 = BitmapDescriptorFactory.HUE_RED;
                    } else {
                        f15 = 1.0f;
                    }
                }
                if (f15 == BitmapDescriptorFactory.HUE_RED) {
                    ConstraintAnchor[] constraintAnchorArr3 = constraintWidget11.f1366C;
                    c0285c.m1196e(constraintAnchorArr3[i10 + 1].f1344j, constraintAnchorArr3[i10].f1344j, 0, 6);
                    arrayList = arrayList2;
                    i12 = size;
                    i21++;
                    size = i12;
                    arrayList2 = arrayList;
                    f13 = BitmapDescriptorFactory.HUE_RED;
                } else {
                    if (constraintWidget10 != null) {
                        ConstraintAnchor[] constraintAnchorArr4 = constraintWidget10.f1366C;
                        SolverVariable solverVariable6 = constraintAnchorArr4[i10].f1344j;
                        int i22 = i10 + 1;
                        SolverVariable solverVariable7 = constraintAnchorArr4[i22].f1344j;
                        ConstraintAnchor[] constraintAnchorArr5 = constraintWidget11.f1366C;
                        arrayList = arrayList2;
                        SolverVariable solverVariable8 = constraintAnchorArr5[i10].f1344j;
                        SolverVariable solverVariable9 = constraintAnchorArr5[i22].f1344j;
                        i12 = size;
                        C0284b c0284bM1210s = c0285c.m1210s();
                        c0284bM1210s.m1170k(f14, f12, f15, solverVariable6, solverVariable7, solverVariable8, solverVariable9);
                        c0285c.m1195d(c0284bM1210s);
                    } else {
                        arrayList = arrayList2;
                        i12 = size;
                    }
                    constraintWidget10 = constraintWidget11;
                    f14 = f15;
                    i21++;
                    size = i12;
                    arrayList2 = arrayList;
                    f13 = BitmapDescriptorFactory.HUE_RED;
                }
            }
        }
        if (constraintWidget6 != null && (constraintWidget6 == constraintWidget7 || z10)) {
            ConstraintAnchor constraintAnchor11 = constraintWidget4.f1366C[i10];
            int i23 = i10 + 1;
            ConstraintAnchor constraintAnchor12 = constraintWidget5.f1366C[i23];
            ConstraintAnchor constraintAnchor13 = constraintAnchor11.f1338d;
            SolverVariable solverVariable10 = constraintAnchor13 != null ? constraintAnchor13.f1344j : null;
            ConstraintAnchor constraintAnchor14 = constraintAnchor12.f1338d;
            SolverVariable solverVariable11 = constraintAnchor14 != null ? constraintAnchor14.f1344j : null;
            if (constraintWidget6 == constraintWidget7) {
                ConstraintAnchor[] constraintAnchorArr6 = constraintWidget6.f1366C;
                ConstraintAnchor constraintAnchor15 = constraintAnchorArr6[i10];
                constraintAnchor12 = constraintAnchorArr6[i23];
                constraintAnchor11 = constraintAnchor15;
            }
            if (solverVariable10 != null && solverVariable11 != null) {
                c0285c.m1194c(constraintAnchor11.f1344j, solverVariable10, constraintAnchor11.m1219d(), i9 == 0 ? constraintWidget9.f1389Z : constraintWidget9.f1391a0, solverVariable11, constraintAnchor12.f1344j, constraintAnchor12.m1219d(), 5);
            }
        } else if (z17 && constraintWidget6 != null) {
            int i24 = c0291d.f1455j;
            boolean z18 = i24 > 0 && c0291d.f1454i == i24;
            ConstraintWidget constraintWidget12 = constraintWidget6;
            ConstraintWidget constraintWidget13 = constraintWidget12;
            while (constraintWidget12 != null) {
                ConstraintWidget constraintWidget14 = constraintWidget12.f1425r0[i9];
                while (constraintWidget14 != null && constraintWidget14.m1234C() == 8) {
                    constraintWidget14 = constraintWidget14.f1425r0[i9];
                }
                if (constraintWidget14 != null || constraintWidget12 == constraintWidget7) {
                    ConstraintAnchor constraintAnchor16 = constraintWidget12.f1366C[i10];
                    SolverVariable solverVariable12 = constraintAnchor16.f1344j;
                    ConstraintAnchor constraintAnchor17 = constraintAnchor16.f1338d;
                    SolverVariable solverVariable13 = constraintAnchor17 != null ? constraintAnchor17.f1344j : null;
                    if (constraintWidget13 != constraintWidget12) {
                        solverVariable13 = constraintWidget13.f1366C[i10 + 1].f1344j;
                    } else if (constraintWidget12 == constraintWidget6 && constraintWidget13 == constraintWidget12) {
                        ConstraintAnchor constraintAnchor18 = constraintWidget4.f1366C[i10].f1338d;
                        solverVariable13 = constraintAnchor18 != null ? constraintAnchor18.f1344j : null;
                    }
                    int iM1219d2 = constraintAnchor16.m1219d();
                    int i25 = i10 + 1;
                    int iM1219d3 = constraintWidget12.f1366C[i25].m1219d();
                    if (constraintWidget14 != null) {
                        constraintAnchor2 = constraintWidget14.f1366C[i10];
                        solverVariable3 = constraintAnchor2.f1344j;
                        solverVariable4 = constraintWidget12.f1366C[i25].f1344j;
                    } else {
                        constraintAnchor2 = constraintWidget5.f1366C[i25].f1338d;
                        solverVariable3 = constraintAnchor2 != null ? constraintAnchor2.f1344j : null;
                        solverVariable4 = constraintWidget12.f1366C[i25].f1344j;
                    }
                    if (constraintAnchor2 != null) {
                        iM1219d3 += constraintAnchor2.m1219d();
                    }
                    if (constraintWidget13 != null) {
                        iM1219d2 += constraintWidget13.f1366C[i25].m1219d();
                    }
                    if (solverVariable12 == null || solverVariable13 == null || solverVariable3 == null || solverVariable4 == null) {
                        constraintWidget2 = constraintWidget14;
                    } else {
                        if (constraintWidget12 == constraintWidget6) {
                            iM1219d2 = constraintWidget6.f1366C[i10].m1219d();
                        }
                        int i26 = iM1219d2;
                        constraintWidget2 = constraintWidget14;
                        c0285c.m1194c(solverVariable12, solverVariable13, i26, 0.5f, solverVariable3, solverVariable4, constraintWidget12 == constraintWidget7 ? constraintWidget7.f1366C[i25].m1219d() : iM1219d3, z18 ? 6 : 4);
                    }
                }
                if (constraintWidget12.m1234C() != 8) {
                    constraintWidget13 = constraintWidget12;
                }
                constraintWidget12 = constraintWidget2;
            }
        } else if (z14 && constraintWidget6 != null) {
            int i27 = c0291d.f1455j;
            boolean z19 = i27 > 0 && c0291d.f1454i == i27;
            ConstraintWidget constraintWidget15 = constraintWidget6;
            ConstraintWidget constraintWidget16 = constraintWidget15;
            while (constraintWidget15 != null) {
                ConstraintWidget constraintWidget17 = constraintWidget15.f1425r0[i9];
                while (constraintWidget17 != null && constraintWidget17.m1234C() == 8) {
                    constraintWidget17 = constraintWidget17.f1425r0[i9];
                }
                if (constraintWidget15 != constraintWidget6 && constraintWidget15 != constraintWidget7 && constraintWidget17 != null) {
                    ConstraintWidget constraintWidget18 = constraintWidget17 == constraintWidget7 ? null : constraintWidget17;
                    ConstraintAnchor constraintAnchor19 = constraintWidget15.f1366C[i10];
                    SolverVariable solverVariable14 = constraintAnchor19.f1344j;
                    ConstraintAnchor constraintAnchor20 = constraintAnchor19.f1338d;
                    if (constraintAnchor20 != null) {
                        SolverVariable solverVariable15 = constraintAnchor20.f1344j;
                    }
                    int i28 = i10 + 1;
                    SolverVariable solverVariable16 = constraintWidget16.f1366C[i28].f1344j;
                    int iM1219d4 = constraintAnchor19.m1219d();
                    int iM1219d5 = constraintWidget15.f1366C[i28].m1219d();
                    if (constraintWidget18 != null) {
                        constraintAnchor = constraintWidget18.f1366C[i10];
                        solverVariable2 = constraintAnchor.f1344j;
                        ConstraintAnchor constraintAnchor21 = constraintAnchor.f1338d;
                        solverVariable = constraintAnchor21 != null ? constraintAnchor21.f1344j : null;
                    } else {
                        ConstraintAnchor constraintAnchor22 = constraintWidget15.f1366C[i28];
                        ConstraintAnchor constraintAnchor23 = constraintAnchor22.f1338d;
                        SolverVariable solverVariable17 = constraintAnchor23 != null ? constraintAnchor23.f1344j : null;
                        solverVariable = constraintAnchor22.f1344j;
                        constraintAnchor = constraintAnchor23;
                        solverVariable2 = solverVariable17;
                    }
                    if (constraintAnchor != null) {
                        iM1219d5 += constraintAnchor.m1219d();
                    }
                    int i29 = iM1219d5;
                    int iM1219d6 = constraintWidget16.f1366C[i28].m1219d() + iM1219d4;
                    int i30 = z19 ? 6 : 4;
                    if (solverVariable14 == null || solverVariable16 == null || solverVariable2 == null || solverVariable == null) {
                        constraintWidget = constraintWidget18;
                    } else {
                        constraintWidget = constraintWidget18;
                        c0285c.m1194c(solverVariable14, solverVariable16, iM1219d6, 0.5f, solverVariable2, solverVariable, i29, i30);
                    }
                    constraintWidget17 = constraintWidget;
                }
                if (constraintWidget15.m1234C() != 8) {
                    constraintWidget16 = constraintWidget15;
                }
                constraintWidget15 = constraintWidget17;
            }
            ConstraintAnchor constraintAnchor24 = constraintWidget6.f1366C[i10];
            ConstraintAnchor constraintAnchor25 = constraintWidget4.f1366C[i10].f1338d;
            int i31 = i10 + 1;
            ConstraintAnchor constraintAnchor26 = constraintWidget7.f1366C[i31];
            ConstraintAnchor constraintAnchor27 = constraintWidget5.f1366C[i31].f1338d;
            if (constraintAnchor25 == null) {
                i11 = 5;
            } else if (constraintWidget6 != constraintWidget7) {
                i11 = 5;
                c0285c.m1196e(constraintAnchor24.f1344j, constraintAnchor25.f1344j, constraintAnchor24.m1219d(), 5);
            } else {
                i11 = 5;
                if (constraintAnchor27 != null) {
                    c0285c.m1194c(constraintAnchor24.f1344j, constraintAnchor25.f1344j, constraintAnchor24.m1219d(), 0.5f, constraintAnchor26.f1344j, constraintAnchor27.f1344j, constraintAnchor26.m1219d(), 5);
                }
            }
            if (constraintAnchor27 != null && constraintWidget6 != constraintWidget7) {
                c0285c.m1196e(constraintAnchor26.f1344j, constraintAnchor27.f1344j, -constraintAnchor26.m1219d(), i11);
            }
        }
        if ((z17 || z14) && constraintWidget6 != null) {
            ConstraintAnchor[] constraintAnchorArr7 = constraintWidget6.f1366C;
            ConstraintAnchor constraintAnchor28 = constraintAnchorArr7[i10];
            int i32 = i10 + 1;
            ConstraintAnchor constraintAnchor29 = constraintWidget7.f1366C[i32];
            ConstraintAnchor constraintAnchor30 = constraintAnchor28.f1338d;
            SolverVariable solverVariable18 = constraintAnchor30 != null ? constraintAnchor30.f1344j : null;
            ConstraintAnchor constraintAnchor31 = constraintAnchor29.f1338d;
            SolverVariable solverVariable19 = constraintAnchor31 != null ? constraintAnchor31.f1344j : null;
            if (constraintWidget5 != constraintWidget7) {
                ConstraintAnchor constraintAnchor32 = constraintWidget5.f1366C[i32].f1338d;
                solverVariable19 = constraintAnchor32 != null ? constraintAnchor32.f1344j : null;
            }
            if (constraintWidget6 == constraintWidget7) {
                constraintAnchor29 = constraintAnchorArr7[i32];
            }
            if (solverVariable18 == null || solverVariable19 == null) {
                return;
            }
            c0285c.m1194c(constraintAnchor28.f1344j, solverVariable18, constraintAnchor28.m1219d(), 0.5f, solverVariable19, constraintAnchor29.f1344j, constraintWidget7.f1366C[i32].m1219d(), 5);
        }
    }
}
