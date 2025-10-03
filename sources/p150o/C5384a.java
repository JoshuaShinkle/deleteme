package p150o;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;

/* renamed from: o.a */
/* loaded from: classes.dex */
public class C5384a extends ConstraintWidget {

    /* renamed from: v0 */
    public ConstraintWidget[] f18283v0 = new ConstraintWidget[4];

    /* renamed from: w0 */
    public int f18284w0 = 0;

    /* renamed from: I0 */
    public void m21087I0(ConstraintWidget constraintWidget) {
        int i9 = this.f18284w0 + 1;
        ConstraintWidget[] constraintWidgetArr = this.f18283v0;
        if (i9 > constraintWidgetArr.length) {
            this.f18283v0 = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.f18283v0;
        int i10 = this.f18284w0;
        constraintWidgetArr2[i10] = constraintWidget;
        this.f18284w0 = i10 + 1;
    }

    /* renamed from: J0 */
    public void m21088J0() {
        this.f18284w0 = 0;
    }
}
