package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.solver.widgets.C0289b;
import p161p.C5867b;

/* loaded from: classes.dex */
public class Barrier extends ConstraintHelper {

    /* renamed from: h */
    public int f1530h;

    /* renamed from: i */
    public int f1531i;

    /* renamed from: j */
    public C0289b f1532j;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    /* renamed from: b */
    public void mo1389b(AttributeSet attributeSet) throws IllegalAccessException, IllegalArgumentException {
        super.mo1389b(attributeSet);
        this.f1532j = new C0289b();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C5867b.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == C5867b.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C5867b.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.f1532j.m1328K0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                }
            }
        }
        this.f1536e = this.f1532j;
        m1394f();
    }

    public int getType() {
        return this.f1530h;
    }

    public void setAllowsGoneWidget(boolean z8) {
        this.f1532j.m1328K0(z8);
    }

    public void setType(int i9) {
        this.f1530h = i9;
        this.f1531i = i9;
        if (1 == getResources().getConfiguration().getLayoutDirection()) {
            int i10 = this.f1530h;
            if (i10 == 5) {
                this.f1531i = 1;
            } else if (i10 == 6) {
                this.f1531i = 0;
            }
        } else {
            int i11 = this.f1530h;
            if (i11 == 5) {
                this.f1531i = 0;
            } else if (i11 == 6) {
                this.f1531i = 1;
            }
        }
        this.f1532j.m1329L0(this.f1531i);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        super.setVisibility(8);
    }
}
