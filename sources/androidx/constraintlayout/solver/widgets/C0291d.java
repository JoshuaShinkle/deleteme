package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* renamed from: androidx.constraintlayout.solver.widgets.d */
/* loaded from: classes.dex */
public class C0291d {

    /* renamed from: a */
    public ConstraintWidget f1446a;

    /* renamed from: b */
    public ConstraintWidget f1447b;

    /* renamed from: c */
    public ConstraintWidget f1448c;

    /* renamed from: d */
    public ConstraintWidget f1449d;

    /* renamed from: e */
    public ConstraintWidget f1450e;

    /* renamed from: f */
    public ConstraintWidget f1451f;

    /* renamed from: g */
    public ConstraintWidget f1452g;

    /* renamed from: h */
    public ArrayList<ConstraintWidget> f1453h;

    /* renamed from: i */
    public int f1454i;

    /* renamed from: j */
    public int f1455j;

    /* renamed from: k */
    public float f1456k = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: l */
    public int f1457l;

    /* renamed from: m */
    public boolean f1458m;

    /* renamed from: n */
    public boolean f1459n;

    /* renamed from: o */
    public boolean f1460o;

    /* renamed from: p */
    public boolean f1461p;

    /* renamed from: q */
    public boolean f1462q;

    public C0291d(ConstraintWidget constraintWidget, int i9, boolean z8) {
        this.f1446a = constraintWidget;
        this.f1457l = i9;
        this.f1458m = z8;
    }

    /* renamed from: c */
    public static boolean m1332c(ConstraintWidget constraintWidget, int i9) {
        int i10;
        return constraintWidget.m1234C() != 8 && constraintWidget.f1368E[i9] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && ((i10 = constraintWidget.f1402g[i9]) == 0 || i10 == 3);
    }

    /* renamed from: a */
    public void m1333a() {
        if (!this.f1462q) {
            m1334b();
        }
        this.f1462q = true;
    }

    /* renamed from: b */
    public final void m1334b() {
        int i9;
        int i10 = this.f1457l * 2;
        ConstraintWidget constraintWidget = this.f1446a;
        boolean z8 = false;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z9 = false;
        while (!z9) {
            this.f1454i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.f1425r0;
            int i11 = this.f1457l;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i11] = null;
            constraintWidget.f1423q0[i11] = null;
            if (constraintWidget.m1234C() != 8) {
                if (this.f1447b == null) {
                    this.f1447b = constraintWidget;
                }
                this.f1449d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.f1368E;
                int i12 = this.f1457l;
                if (dimensionBehaviourArr[i12] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && ((i9 = constraintWidget.f1402g[i12]) == 0 || i9 == 3 || i9 == 2)) {
                    this.f1455j++;
                    float f9 = constraintWidget.f1421p0[i12];
                    if (f9 > BitmapDescriptorFactory.HUE_RED) {
                        this.f1456k += f9;
                    }
                    if (m1332c(constraintWidget, i12)) {
                        if (f9 < BitmapDescriptorFactory.HUE_RED) {
                            this.f1459n = true;
                        } else {
                            this.f1460o = true;
                        }
                        if (this.f1453h == null) {
                            this.f1453h = new ArrayList<>();
                        }
                        this.f1453h.add(constraintWidget);
                    }
                    if (this.f1451f == null) {
                        this.f1451f = constraintWidget;
                    }
                    ConstraintWidget constraintWidget4 = this.f1452g;
                    if (constraintWidget4 != null) {
                        constraintWidget4.f1423q0[this.f1457l] = constraintWidget;
                    }
                    this.f1452g = constraintWidget;
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.f1425r0[this.f1457l] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.f1366C[i10 + 1].f1338d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f1336b;
                ConstraintAnchor constraintAnchor2 = constraintWidget5.f1366C[i10].f1338d;
                if (constraintAnchor2 != null && constraintAnchor2.f1336b == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z9 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        this.f1448c = constraintWidget;
        if (this.f1457l == 0 && this.f1458m) {
            this.f1450e = constraintWidget;
        } else {
            this.f1450e = this.f1446a;
        }
        if (this.f1460o && this.f1459n) {
            z8 = true;
        }
        this.f1461p = z8;
    }
}
