package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

/* renamed from: androidx.constraintlayout.solver.widgets.j */
/* loaded from: classes.dex */
public class C0297j {

    /* renamed from: a */
    public int f1520a;

    /* renamed from: b */
    public int f1521b;

    /* renamed from: c */
    public int f1522c;

    /* renamed from: d */
    public int f1523d;

    /* renamed from: e */
    public ArrayList<a> f1524e = new ArrayList<>();

    /* renamed from: androidx.constraintlayout.solver.widgets.j$a */
    public static class a {

        /* renamed from: a */
        public ConstraintAnchor f1525a;

        /* renamed from: b */
        public ConstraintAnchor f1526b;

        /* renamed from: c */
        public int f1527c;

        /* renamed from: d */
        public ConstraintAnchor.Strength f1528d;

        /* renamed from: e */
        public int f1529e;

        public a(ConstraintAnchor constraintAnchor) {
            this.f1525a = constraintAnchor;
            this.f1526b = constraintAnchor.m1224i();
            this.f1527c = constraintAnchor.m1219d();
            this.f1528d = constraintAnchor.m1223h();
            this.f1529e = constraintAnchor.m1218c();
        }

        /* renamed from: a */
        public void m1387a(ConstraintWidget constraintWidget) {
            constraintWidget.mo1278h(this.f1525a.m1225j()).m1217b(this.f1526b, this.f1527c, this.f1528d, this.f1529e);
        }

        /* renamed from: b */
        public void m1388b(ConstraintWidget constraintWidget) {
            ConstraintAnchor constraintAnchorMo1278h = constraintWidget.mo1278h(this.f1525a.m1225j());
            this.f1525a = constraintAnchorMo1278h;
            if (constraintAnchorMo1278h != null) {
                this.f1526b = constraintAnchorMo1278h.m1224i();
                this.f1527c = this.f1525a.m1219d();
                this.f1528d = this.f1525a.m1223h();
                this.f1529e = this.f1525a.m1218c();
                return;
            }
            this.f1526b = null;
            this.f1527c = 0;
            this.f1528d = ConstraintAnchor.Strength.STRONG;
            this.f1529e = 0;
        }
    }

    public C0297j(ConstraintWidget constraintWidget) {
        this.f1520a = constraintWidget.m1242G();
        this.f1521b = constraintWidget.m1244H();
        this.f1522c = constraintWidget.m1236D();
        this.f1523d = constraintWidget.m1298r();
        ArrayList<ConstraintAnchor> arrayListMo1280i = constraintWidget.mo1280i();
        int size = arrayListMo1280i.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f1524e.add(new a(arrayListMo1280i.get(i9)));
        }
    }

    /* renamed from: a */
    public void m1385a(ConstraintWidget constraintWidget) {
        constraintWidget.m1235C0(this.f1520a);
        constraintWidget.m1237D0(this.f1521b);
        constraintWidget.m1313y0(this.f1522c);
        constraintWidget.m1267b0(this.f1523d);
        int size = this.f1524e.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f1524e.get(i9).m1387a(constraintWidget);
        }
    }

    /* renamed from: b */
    public void m1386b(ConstraintWidget constraintWidget) {
        this.f1520a = constraintWidget.m1242G();
        this.f1521b = constraintWidget.m1244H();
        this.f1522c = constraintWidget.m1236D();
        this.f1523d = constraintWidget.m1298r();
        int size = this.f1524e.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f1524e.get(i9).m1388b(constraintWidget);
        }
    }
}
