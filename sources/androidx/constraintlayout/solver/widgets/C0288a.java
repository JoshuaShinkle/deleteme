package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p150o.C5384a;
import p150o.C5387d;

/* renamed from: androidx.constraintlayout.solver.widgets.a */
/* loaded from: classes.dex */
public class C0288a {
    /* renamed from: a */
    public static void m1316a(C0292e c0292e) {
        if ((c0292e.m1340R0() & 32) != 32) {
            m1325j(c0292e);
            return;
        }
        c0292e.f1477O0 = true;
        c0292e.f1471I0 = false;
        c0292e.f1472J0 = false;
        c0292e.f1473K0 = false;
        ArrayList<ConstraintWidget> arrayList = c0292e.f18288v0;
        List<C0293f> list = c0292e.f1470H0;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = c0292e.m1300s();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z8 = dimensionBehaviourM1300s == dimensionBehaviour;
        boolean z9 = c0292e.m1232B() == dimensionBehaviour;
        boolean z10 = z8 || z9;
        list.clear();
        for (ConstraintWidget constraintWidget : arrayList) {
            constraintWidget.f1424r = null;
            constraintWidget.f1411k0 = false;
            constraintWidget.mo1256S();
        }
        for (ConstraintWidget constraintWidget2 : arrayList) {
            if (constraintWidget2.f1424r == null && !m1317b(constraintWidget2, list, z10)) {
                m1325j(c0292e);
                c0292e.f1477O0 = false;
                return;
            }
        }
        int iMax = 0;
        int iMax2 = 0;
        for (C0293f c0293f : list) {
            iMax = Math.max(iMax, m1318c(c0293f, 0));
            iMax2 = Math.max(iMax2, m1318c(c0293f, 1));
        }
        if (z8) {
            c0292e.m1277g0(ConstraintWidget.DimensionBehaviour.FIXED);
            c0292e.m1313y0(iMax);
            c0292e.f1471I0 = true;
            c0292e.f1472J0 = true;
            c0292e.f1474L0 = iMax;
        }
        if (z9) {
            c0292e.m1305u0(ConstraintWidget.DimensionBehaviour.FIXED);
            c0292e.m1267b0(iMax2);
            c0292e.f1471I0 = true;
            c0292e.f1473K0 = true;
            c0292e.f1475M0 = iMax2;
        }
        m1324i(list, 0, c0292e.m1236D());
        m1324i(list, 1, c0292e.m1298r());
    }

    /* renamed from: b */
    public static boolean m1317b(ConstraintWidget constraintWidget, List<C0293f> list, boolean z8) {
        C0293f c0293f = new C0293f(new ArrayList(), true);
        list.add(c0293f);
        return m1326k(constraintWidget, c0293f, list, z8);
    }

    /* renamed from: c */
    public static int m1318c(C0293f c0293f, int i9) {
        int i10 = i9 * 2;
        List<ConstraintWidget> listM1356b = c0293f.m1356b(i9);
        int size = listM1356b.size();
        int iMax = 0;
        for (int i11 = 0; i11 < size; i11++) {
            ConstraintWidget constraintWidget = listM1356b.get(i11);
            ConstraintAnchor[] constraintAnchorArr = constraintWidget.f1366C;
            ConstraintAnchor constraintAnchor = constraintAnchorArr[i10 + 1].f1338d;
            iMax = Math.max(iMax, m1319d(constraintWidget, i9, constraintAnchor == null || !(constraintAnchorArr[i10].f1338d == null || constraintAnchor == null), 0));
        }
        c0293f.f1489e[i9] = iMax;
        return iMax;
    }

    /* renamed from: d */
    public static int m1319d(ConstraintWidget constraintWidget, int i9, boolean z8, int i10) {
        int iM1298r;
        int iM1282j;
        int i11;
        int i12;
        int i13;
        int iM1236D;
        int i14;
        int i15;
        int i16;
        int iMax = 0;
        if (!constraintWidget.f1407i0) {
            return 0;
        }
        boolean z9 = constraintWidget.f1434y.f1338d != null && i9 == 1;
        if (z8) {
            iM1298r = constraintWidget.m1282j();
            iM1282j = constraintWidget.m1298r() - constraintWidget.m1282j();
            i12 = i9 * 2;
            i11 = i12 + 1;
        } else {
            iM1298r = constraintWidget.m1298r() - constraintWidget.m1282j();
            iM1282j = constraintWidget.m1282j();
            i11 = i9 * 2;
            i12 = i11 + 1;
        }
        ConstraintAnchor[] constraintAnchorArr = constraintWidget.f1366C;
        if (constraintAnchorArr[i11].f1338d == null || constraintAnchorArr[i12].f1338d != null) {
            i13 = 1;
        } else {
            i13 = -1;
            int i17 = i11;
            i11 = i12;
            i12 = i17;
        }
        int i18 = z9 ? i10 - iM1298r : i10;
        int iM1219d = (constraintAnchorArr[i12].m1219d() * i13) + m1320e(constraintWidget, i9);
        int i19 = i18 + iM1219d;
        int iM1236D2 = (i9 == 0 ? constraintWidget.m1236D() : constraintWidget.m1298r()) * i13;
        Iterator<C5387d> it = constraintWidget.f1366C[i12].m1221f().f18286a.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, m1319d(((C0296i) it.next()).f1507c.f1336b, i9, z8, i19));
        }
        int iMax2 = 0;
        for (Iterator<C5387d> it2 = constraintWidget.f1366C[i11].m1221f().f18286a.iterator(); it2.hasNext(); it2 = it2) {
            iMax2 = Math.max(iMax2, m1319d(((C0296i) it2.next()).f1507c.f1336b, i9, z8, iM1236D2 + i19));
        }
        if (z9) {
            iMax -= iM1298r;
            iM1236D = iMax2 + iM1282j;
        } else {
            iM1236D = iMax2 + ((i9 == 0 ? constraintWidget.m1236D() : constraintWidget.m1298r()) * i13);
        }
        int i20 = 1;
        if (i9 == 1) {
            Iterator<C5387d> it3 = constraintWidget.f1434y.m1221f().f18286a.iterator();
            int iMax3 = 0;
            while (it3.hasNext()) {
                Iterator<C5387d> it4 = it3;
                C0296i c0296i = (C0296i) it3.next();
                if (i13 == i20) {
                    iMax3 = Math.max(iMax3, m1319d(c0296i.f1507c.f1336b, i9, z8, iM1298r + i19));
                    i16 = i11;
                } else {
                    i16 = i11;
                    iMax3 = Math.max(iMax3, m1319d(c0296i.f1507c.f1336b, i9, z8, (iM1282j * i13) + i19));
                }
                it3 = it4;
                i11 = i16;
                i20 = 1;
            }
            i14 = i11;
            int i21 = iMax3;
            i15 = (constraintWidget.f1434y.m1221f().f18286a.size() <= 0 || z9) ? i21 : i13 == 1 ? i21 + iM1298r : i21 - iM1282j;
        } else {
            i14 = i11;
            i15 = 0;
        }
        int iMax4 = iM1219d + Math.max(iMax, Math.max(iM1236D, i15));
        int i22 = iM1236D2 + i19;
        if (i13 == -1) {
            i22 = i19;
            i19 = i22;
        }
        if (z8) {
            C0295h.m1371e(constraintWidget, i9, i19);
            constraintWidget.m1263Z(i19, i22, i9);
        } else {
            constraintWidget.f1424r.m1355a(constraintWidget, i9);
            constraintWidget.m1297q0(i19, i9);
        }
        if (constraintWidget.m1292o(i9) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.f1372I != BitmapDescriptorFactory.HUE_RED) {
            constraintWidget.f1424r.m1355a(constraintWidget, i9);
        }
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.f1366C;
        if (constraintAnchorArr2[i12].f1338d != null && constraintAnchorArr2[i14].f1338d != null) {
            ConstraintWidget constraintWidgetM1304u = constraintWidget.m1304u();
            ConstraintAnchor[] constraintAnchorArr3 = constraintWidget.f1366C;
            if (constraintAnchorArr3[i12].f1338d.f1336b == constraintWidgetM1304u && constraintAnchorArr3[i14].f1338d.f1336b == constraintWidgetM1304u) {
                constraintWidget.f1424r.m1355a(constraintWidget, i9);
            }
        }
        return iMax4;
    }

    /* renamed from: e */
    public static int m1320e(ConstraintWidget constraintWidget, int i9) {
        ConstraintAnchor constraintAnchor;
        int i10 = i9 * 2;
        ConstraintAnchor[] constraintAnchorArr = constraintWidget.f1366C;
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i10];
        ConstraintAnchor constraintAnchor3 = constraintAnchorArr[i10 + 1];
        ConstraintAnchor constraintAnchor4 = constraintAnchor2.f1338d;
        if (constraintAnchor4 == null) {
            return 0;
        }
        ConstraintWidget constraintWidget2 = constraintAnchor4.f1336b;
        ConstraintWidget constraintWidget3 = constraintWidget.f1369F;
        if (constraintWidget2 != constraintWidget3 || (constraintAnchor = constraintAnchor3.f1338d) == null || constraintAnchor.f1336b != constraintWidget3) {
            return 0;
        }
        return (int) ((((constraintWidget3.m1302t(i9) - constraintAnchor2.m1219d()) - constraintAnchor3.m1219d()) - constraintWidget.m1302t(i9)) * (i9 == 0 ? constraintWidget.f1389Z : constraintWidget.f1391a0));
    }

    /* renamed from: f */
    public static void m1321f(C0292e c0292e, ConstraintWidget constraintWidget, C0293f c0293f) {
        c0293f.f1488d = false;
        c0292e.f1477O0 = false;
        constraintWidget.f1407i0 = false;
    }

    /* renamed from: g */
    public static int m1322g(ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = constraintWidget.m1300s();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviourM1300s == dimensionBehaviour) {
            int iM1298r = (int) (constraintWidget.f1373J == 0 ? constraintWidget.m1298r() * constraintWidget.f1372I : constraintWidget.m1298r() / constraintWidget.f1372I);
            constraintWidget.m1313y0(iM1298r);
            return iM1298r;
        }
        if (constraintWidget.m1232B() != dimensionBehaviour) {
            return -1;
        }
        int iM1236D = (int) (constraintWidget.f1373J == 1 ? constraintWidget.m1236D() * constraintWidget.f1372I : constraintWidget.m1236D() / constraintWidget.f1372I);
        constraintWidget.m1267b0(iM1236D);
        return iM1236D;
    }

    /* renamed from: h */
    public static void m1323h(ConstraintAnchor constraintAnchor) {
        C0296i c0296iM1221f = constraintAnchor.m1221f();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.f1338d;
        if (constraintAnchor2 == null || constraintAnchor2.f1338d == constraintAnchor) {
            return;
        }
        constraintAnchor2.m1221f().m21091a(c0296iM1221f);
    }

    /* renamed from: i */
    public static void m1324i(List<C0293f> list, int i9, int i10) {
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            for (ConstraintWidget constraintWidget : list.get(i11).m1357c(i9)) {
                if (constraintWidget.f1407i0) {
                    m1327l(constraintWidget, i9, i10);
                }
            }
        }
    }

    /* renamed from: j */
    public static void m1325j(C0292e c0292e) {
        c0292e.f1470H0.clear();
        c0292e.f1470H0.add(0, new C0293f(c0292e.f18288v0));
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0112  */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m1326k(ConstraintWidget constraintWidget, C0293f c0293f, List<C0293f> list, boolean z8) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (constraintWidget == null) {
            return true;
        }
        constraintWidget.f1409j0 = false;
        C0292e c0292e = (C0292e) constraintWidget.m1304u();
        C0293f c0293f2 = constraintWidget.f1424r;
        if (c0293f2 != null) {
            if (c0293f2 != c0293f) {
                c0293f.f1485a.addAll(c0293f2.f1485a);
                c0293f.f1490f.addAll(constraintWidget.f1424r.f1490f);
                c0293f.f1491g.addAll(constraintWidget.f1424r.f1491g);
                C0293f c0293f3 = constraintWidget.f1424r;
                if (!c0293f3.f1488d) {
                    c0293f.f1488d = false;
                }
                list.remove(c0293f3);
                Iterator<ConstraintWidget> it = constraintWidget.f1424r.f1485a.iterator();
                while (it.hasNext()) {
                    it.next().f1424r = c0293f;
                }
            }
            return true;
        }
        constraintWidget.f1407i0 = true;
        c0293f.f1485a.add(constraintWidget);
        constraintWidget.f1424r = c0293f;
        if (constraintWidget.f1430u.f1338d == null && constraintWidget.f1432w.f1338d == null && constraintWidget.f1431v.f1338d == null && constraintWidget.f1433x.f1338d == null && constraintWidget.f1434y.f1338d == null && constraintWidget.f1365B.f1338d == null) {
            m1321f(c0292e, constraintWidget, c0293f);
            if (z8) {
                return false;
            }
        }
        if (constraintWidget.f1431v.f1338d != null && constraintWidget.f1433x.f1338d != null) {
            c0292e.m1232B();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
            if (z8) {
                m1321f(c0292e, constraintWidget, c0293f);
                return false;
            }
            if (constraintWidget.f1431v.f1338d.f1336b != constraintWidget.m1304u() || constraintWidget.f1433x.f1338d.f1336b != constraintWidget.m1304u()) {
                m1321f(c0292e, constraintWidget, c0293f);
            }
        }
        if (constraintWidget.f1430u.f1338d != null && constraintWidget.f1432w.f1338d != null) {
            c0292e.m1300s();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
            if (z8) {
                m1321f(c0292e, constraintWidget, c0293f);
                return false;
            }
            if (constraintWidget.f1430u.f1338d.f1336b != constraintWidget.m1304u() || constraintWidget.f1432w.f1338d.f1336b != constraintWidget.m1304u()) {
                m1321f(c0292e, constraintWidget, c0293f);
            }
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = constraintWidget.m1300s();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (((dimensionBehaviourM1300s == dimensionBehaviour3) ^ (constraintWidget.m1232B() == dimensionBehaviour3)) && constraintWidget.f1372I != BitmapDescriptorFactory.HUE_RED) {
            m1322g(constraintWidget);
        } else if (constraintWidget.m1300s() == dimensionBehaviour3 || constraintWidget.m1232B() == dimensionBehaviour3) {
            m1321f(c0292e, constraintWidget, c0293f);
            if (z8) {
                return false;
            }
        }
        ConstraintAnchor constraintAnchor3 = constraintWidget.f1430u.f1338d;
        if ((constraintAnchor3 != null || constraintWidget.f1432w.f1338d != null) && ((constraintAnchor3 == null || constraintAnchor3.f1336b != constraintWidget.f1369F || constraintWidget.f1432w.f1338d != null) && ((constraintAnchor = constraintWidget.f1432w.f1338d) == null || constraintAnchor.f1336b != constraintWidget.f1369F || constraintAnchor3 != null))) {
            if (constraintAnchor3 != null) {
                ConstraintWidget constraintWidget2 = constraintAnchor3.f1336b;
                ConstraintWidget constraintWidget3 = constraintWidget.f1369F;
                if (constraintWidget2 == constraintWidget3 && constraintAnchor != null && constraintAnchor.f1336b == constraintWidget3) {
                    if (constraintWidget.f1365B.f1338d == null && !(constraintWidget instanceof C0294g) && !(constraintWidget instanceof C5384a)) {
                        c0293f.f1490f.add(constraintWidget);
                    }
                }
            }
        }
        ConstraintAnchor constraintAnchor4 = constraintWidget.f1431v.f1338d;
        if ((constraintAnchor4 != null || constraintWidget.f1433x.f1338d != null) && ((constraintAnchor4 == null || constraintAnchor4.f1336b != constraintWidget.f1369F || constraintWidget.f1433x.f1338d != null) && ((constraintAnchor2 = constraintWidget.f1433x.f1338d) == null || constraintAnchor2.f1336b != constraintWidget.f1369F || constraintAnchor4 != null))) {
            if (constraintAnchor4 != null) {
                ConstraintWidget constraintWidget4 = constraintAnchor4.f1336b;
                ConstraintWidget constraintWidget5 = constraintWidget.f1369F;
                if (constraintWidget4 == constraintWidget5 && constraintAnchor2 != null && constraintAnchor2.f1336b == constraintWidget5) {
                    if (constraintWidget.f1365B.f1338d == null && constraintWidget.f1434y.f1338d == null && !(constraintWidget instanceof C0294g) && !(constraintWidget instanceof C5384a)) {
                        c0293f.f1491g.add(constraintWidget);
                    }
                }
            }
        }
        if (constraintWidget instanceof C5384a) {
            m1321f(c0292e, constraintWidget, c0293f);
            if (z8) {
                return false;
            }
            C5384a c5384a = (C5384a) constraintWidget;
            for (int i9 = 0; i9 < c5384a.f18284w0; i9++) {
                if (!m1326k(c5384a.f18283v0[i9], c0293f, list, z8)) {
                    return false;
                }
            }
        }
        int length = constraintWidget.f1366C.length;
        for (int i10 = 0; i10 < length; i10++) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.f1366C[i10];
            ConstraintAnchor constraintAnchor6 = constraintAnchor5.f1338d;
            if (constraintAnchor6 != null && constraintAnchor6.f1336b != constraintWidget.m1304u()) {
                if (constraintAnchor5.f1337c == ConstraintAnchor.Type.CENTER) {
                    m1321f(c0292e, constraintWidget, c0293f);
                    if (z8) {
                        return false;
                    }
                } else {
                    m1323h(constraintAnchor5);
                }
                if (!m1326k(constraintAnchor5.f1338d.f1336b, c0293f, list, z8)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: l */
    public static void m1327l(ConstraintWidget constraintWidget, int i9, int i10) {
        int i11 = i9 * 2;
        ConstraintAnchor[] constraintAnchorArr = constraintWidget.f1366C;
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i11];
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i11 + 1];
        if ((constraintAnchor.f1338d == null || constraintAnchor2.f1338d == null) ? false : true) {
            C0295h.m1371e(constraintWidget, i9, m1320e(constraintWidget, i9) + constraintAnchor.m1219d());
            return;
        }
        if (constraintWidget.f1372I == BitmapDescriptorFactory.HUE_RED || constraintWidget.m1292o(i9) != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int iM1306v = i10 - constraintWidget.m1306v(i9);
            int iM1302t = iM1306v - constraintWidget.m1302t(i9);
            constraintWidget.m1263Z(iM1302t, iM1306v, i9);
            C0295h.m1371e(constraintWidget, i9, iM1302t);
            return;
        }
        int iM1322g = m1322g(constraintWidget);
        int i12 = (int) constraintWidget.f1366C[i11].m1221f().f1512h;
        constraintAnchor2.m1221f().f1511g = constraintAnchor.m1221f();
        constraintAnchor2.m1221f().f1512h = iM1322g;
        constraintAnchor2.m1221f().f18287b = 1;
        constraintWidget.m1263Z(i12, i12 + iM1322g, i9);
    }
}
