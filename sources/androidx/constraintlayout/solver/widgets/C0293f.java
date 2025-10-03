package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p150o.C5384a;

/* renamed from: androidx.constraintlayout.solver.widgets.f */
/* loaded from: classes.dex */
public class C0293f {

    /* renamed from: a */
    public List<ConstraintWidget> f1485a;

    /* renamed from: b */
    public int f1486b;

    /* renamed from: c */
    public int f1487c;

    /* renamed from: d */
    public boolean f1488d;

    /* renamed from: e */
    public final int[] f1489e;

    /* renamed from: f */
    public List<ConstraintWidget> f1490f;

    /* renamed from: g */
    public List<ConstraintWidget> f1491g;

    /* renamed from: h */
    public HashSet<ConstraintWidget> f1492h;

    /* renamed from: i */
    public HashSet<ConstraintWidget> f1493i;

    /* renamed from: j */
    public List<ConstraintWidget> f1494j;

    /* renamed from: k */
    public List<ConstraintWidget> f1495k;

    public C0293f(List<ConstraintWidget> list) {
        this.f1486b = -1;
        this.f1487c = -1;
        this.f1488d = false;
        this.f1489e = new int[]{-1, -1};
        this.f1490f = new ArrayList();
        this.f1491g = new ArrayList();
        this.f1492h = new HashSet<>();
        this.f1493i = new HashSet<>();
        this.f1494j = new ArrayList();
        this.f1495k = new ArrayList();
        this.f1485a = list;
    }

    /* renamed from: a */
    public void m1355a(ConstraintWidget constraintWidget, int i9) {
        if (i9 == 0) {
            this.f1492h.add(constraintWidget);
        } else if (i9 == 1) {
            this.f1493i.add(constraintWidget);
        }
    }

    /* renamed from: b */
    public List<ConstraintWidget> m1356b(int i9) {
        if (i9 == 0) {
            return this.f1490f;
        }
        if (i9 == 1) {
            return this.f1491g;
        }
        return null;
    }

    /* renamed from: c */
    public Set<ConstraintWidget> m1357c(int i9) {
        if (i9 == 0) {
            return this.f1492h;
        }
        if (i9 == 1) {
            return this.f1493i;
        }
        return null;
    }

    /* renamed from: d */
    public List<ConstraintWidget> m1358d() {
        if (!this.f1494j.isEmpty()) {
            return this.f1494j;
        }
        int size = this.f1485a.size();
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget = this.f1485a.get(i9);
            if (!constraintWidget.f1407i0) {
                m1359e((ArrayList) this.f1494j, constraintWidget);
            }
        }
        this.f1495k.clear();
        this.f1495k.addAll(this.f1485a);
        this.f1495k.removeAll(this.f1494j);
        return this.f1494j;
    }

    /* renamed from: e */
    public final void m1359e(ArrayList<ConstraintWidget> arrayList, ConstraintWidget constraintWidget) {
        ConstraintWidget constraintWidget2;
        if (constraintWidget.f1411k0) {
            return;
        }
        arrayList.add(constraintWidget);
        constraintWidget.f1411k0 = true;
        if (constraintWidget.m1249L()) {
            return;
        }
        if (constraintWidget instanceof C5384a) {
            C5384a c5384a = (C5384a) constraintWidget;
            int i9 = c5384a.f18284w0;
            for (int i10 = 0; i10 < i9; i10++) {
                m1359e(arrayList, c5384a.f18283v0[i10]);
            }
        }
        int length = constraintWidget.f1366C.length;
        for (int i11 = 0; i11 < length; i11++) {
            ConstraintAnchor constraintAnchor = constraintWidget.f1366C[i11].f1338d;
            if (constraintAnchor != null && (constraintWidget2 = constraintAnchor.f1336b) != constraintWidget.m1304u()) {
                m1359e(arrayList, constraintWidget2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m1360f(ConstraintWidget constraintWidget) {
        int iM1236D;
        if (!constraintWidget.f1407i0 || constraintWidget.m1249L()) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintWidget.f1432w.f1338d;
        boolean z8 = constraintAnchor != null;
        if (!z8) {
            constraintAnchor = constraintWidget.f1430u.f1338d;
        }
        if (constraintAnchor != null) {
            ConstraintWidget constraintWidget2 = constraintAnchor.f1336b;
            if (!constraintWidget2.f1409j0) {
                m1360f(constraintWidget2);
            }
            ConstraintAnchor.Type type = constraintAnchor.f1337c;
            if (type == ConstraintAnchor.Type.RIGHT) {
                ConstraintWidget constraintWidget3 = constraintAnchor.f1336b;
                iM1236D = constraintWidget3.f1374K + constraintWidget3.m1236D();
            } else {
                iM1236D = type == ConstraintAnchor.Type.LEFT ? constraintAnchor.f1336b.f1374K : 0;
            }
        }
        int iM1219d = z8 ? iM1236D - constraintWidget.f1432w.m1219d() : iM1236D + constraintWidget.f1430u.m1219d() + constraintWidget.m1236D();
        constraintWidget.m1275f0(iM1219d - constraintWidget.m1236D(), iM1219d);
        ConstraintAnchor constraintAnchor2 = constraintWidget.f1434y.f1338d;
        if (constraintAnchor2 != null) {
            ConstraintWidget constraintWidget4 = constraintAnchor2.f1336b;
            if (!constraintWidget4.f1409j0) {
                m1360f(constraintWidget4);
            }
            ConstraintWidget constraintWidget5 = constraintAnchor2.f1336b;
            int i9 = (constraintWidget5.f1375L + constraintWidget5.f1384U) - constraintWidget.f1384U;
            constraintWidget.m1303t0(i9, constraintWidget.f1371H + i9);
            constraintWidget.f1409j0 = true;
            return;
        }
        ConstraintAnchor constraintAnchor3 = constraintWidget.f1433x.f1338d;
        boolean z9 = constraintAnchor3 != null;
        if (!z9) {
            constraintAnchor3 = constraintWidget.f1431v.f1338d;
        }
        if (constraintAnchor3 != null) {
            ConstraintWidget constraintWidget6 = constraintAnchor3.f1336b;
            if (!constraintWidget6.f1409j0) {
                m1360f(constraintWidget6);
            }
            ConstraintAnchor.Type type2 = constraintAnchor3.f1337c;
            if (type2 == ConstraintAnchor.Type.BOTTOM) {
                ConstraintWidget constraintWidget7 = constraintAnchor3.f1336b;
                iM1219d = constraintWidget7.f1375L + constraintWidget7.m1298r();
            } else if (type2 == ConstraintAnchor.Type.TOP) {
                iM1219d = constraintAnchor3.f1336b.f1375L;
            }
        }
        int iM1219d2 = z9 ? iM1219d - constraintWidget.f1433x.m1219d() : iM1219d + constraintWidget.f1431v.m1219d() + constraintWidget.m1298r();
        constraintWidget.m1303t0(iM1219d2 - constraintWidget.m1298r(), iM1219d2);
        constraintWidget.f1409j0 = true;
    }

    /* renamed from: g */
    public void m1361g() {
        int size = this.f1495k.size();
        for (int i9 = 0; i9 < size; i9++) {
            m1360f(this.f1495k.get(i9));
        }
    }

    public C0293f(List<ConstraintWidget> list, boolean z8) {
        this.f1486b = -1;
        this.f1487c = -1;
        this.f1488d = false;
        this.f1489e = new int[]{-1, -1};
        this.f1490f = new ArrayList();
        this.f1491g = new ArrayList();
        this.f1492h = new HashSet<>();
        this.f1493i = new HashSet<>();
        this.f1494j = new ArrayList();
        this.f1495k = new ArrayList();
        this.f1485a = list;
        this.f1488d = z8;
    }
}
