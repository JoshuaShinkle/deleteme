package androidx.constraintlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public class Group extends ConstraintHelper {
    public Group(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    /* renamed from: b */
    public void mo1389b(AttributeSet attributeSet) throws IllegalAccessException, IllegalArgumentException {
        super.mo1389b(attributeSet);
        this.f1537f = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    /* renamed from: c */
    public void mo1391c(ConstraintLayout constraintLayout) {
        ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) getLayoutParams();
        c0298a.f1607l0.m1313y0(0);
        c0298a.f1607l0.m1267b0(0);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    /* renamed from: e */
    public void mo1393e(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i9 = 0; i9 < this.f1534c; i9++) {
            View viewM1399e = constraintLayout.m1399e(this.f1533b[i9]);
            if (viewM1399e != null) {
                viewM1399e.setVisibility(visibility);
                if (elevation > BitmapDescriptorFactory.HUE_RED) {
                    viewM1399e.setElevation(elevation);
                }
            }
        }
    }
}
