package p150o;

import androidx.constraintlayout.solver.widgets.C0292e;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import p141n.C5341a;

/* renamed from: o.e */
/* loaded from: classes.dex */
public class C5388e extends ConstraintWidget {

    /* renamed from: v0 */
    public ArrayList<ConstraintWidget> f18288v0 = new ArrayList<>();

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: F0 */
    public void mo1241F0() {
        super.mo1241F0();
        ArrayList<ConstraintWidget> arrayList = this.f18288v0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget = this.f18288v0.get(i9);
            constraintWidget.mo1291n0(m1294p(), m1296q());
            if (!(constraintWidget instanceof C0292e)) {
                constraintWidget.mo1241F0();
            }
        }
    }

    /* renamed from: I0 */
    public void m21095I0(ConstraintWidget constraintWidget) {
        this.f18288v0.add(constraintWidget);
        if (constraintWidget.m1304u() != null) {
            ((C5388e) constraintWidget.m1304u()).m21097L0(constraintWidget);
        }
        constraintWidget.m1295p0(this);
    }

    /* renamed from: J0 */
    public C0292e m21096J0() {
        ConstraintWidget constraintWidgetM1304u = m1304u();
        C0292e c0292e = this instanceof C0292e ? (C0292e) this : null;
        while (constraintWidgetM1304u != null) {
            ConstraintWidget constraintWidgetM1304u2 = constraintWidgetM1304u.m1304u();
            if (constraintWidgetM1304u instanceof C0292e) {
                c0292e = (C0292e) constraintWidgetM1304u;
            }
            constraintWidgetM1304u = constraintWidgetM1304u2;
        }
        return c0292e;
    }

    /* renamed from: K0 */
    public void mo1335K0() {
        mo1241F0();
        ArrayList<ConstraintWidget> arrayList = this.f18288v0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget = this.f18288v0.get(i9);
            if (constraintWidget instanceof C5388e) {
                ((C5388e) constraintWidget).mo1335K0();
            }
        }
    }

    /* renamed from: L0 */
    public void m21097L0(ConstraintWidget constraintWidget) {
        this.f18288v0.remove(constraintWidget);
        constraintWidget.m1295p0(null);
    }

    /* renamed from: M0 */
    public void m21098M0() {
        this.f18288v0.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: Q */
    public void mo1254Q() {
        this.f18288v0.clear();
        super.mo1254Q();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: T */
    public void mo1257T(C5341a c5341a) {
        super.mo1257T(c5341a);
        int size = this.f18288v0.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f18288v0.get(i9).mo1257T(c5341a);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    /* renamed from: n0 */
    public void mo1291n0(int i9, int i10) {
        super.mo1291n0(i9, i10);
        int size = this.f18288v0.size();
        for (int i11 = 0; i11 < size; i11++) {
            this.f18288v0.get(i11).mo1291n0(m1314z(), m1230A());
        }
    }
}
