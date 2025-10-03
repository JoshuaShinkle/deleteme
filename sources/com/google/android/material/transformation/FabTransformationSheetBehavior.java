package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.Positioning;
import com.google.android.material.transformation.FabTransformationBehavior;
import java.util.HashMap;
import java.util.Map;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map<View, Integer> importantForAccessibilityMap;

    public FabTransformationSheetBehavior() {
    }

    private void updateImportantForAccessibility(View view, boolean z8) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z8) {
                this.importantForAccessibilityMap = new HashMap(childCount);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = coordinatorLayout.getChildAt(i9);
                boolean z9 = (childAt.getLayoutParams() instanceof CoordinatorLayout.C0307f) && (((CoordinatorLayout.C0307f) childAt.getLayoutParams()).m1439f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z9) {
                    if (z8) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        C4647u.m18548i0(childAt, 4);
                    } else {
                        Map<View, Integer> map = this.importantForAccessibilityMap;
                        if (map != null && map.containsKey(childAt)) {
                            C4647u.m18548i0(childAt, this.importantForAccessibilityMap.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z8) {
                return;
            }
            this.importantForAccessibilityMap = null;
        }
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public FabTransformationBehavior.FabTransformationSpec onCreateMotionSpec(Context context, boolean z8) {
        int i9 = z8 ? C3476R.animator.mtrl_fab_transformation_sheet_expand_spec : C3476R.animator.mtrl_fab_transformation_sheet_collapse_spec;
        FabTransformationBehavior.FabTransformationSpec fabTransformationSpec = new FabTransformationBehavior.FabTransformationSpec();
        fabTransformationSpec.timings = MotionSpec.createFromResource(context, i9);
        fabTransformationSpec.positioning = new Positioning(17, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        return fabTransformationSpec;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public boolean onExpandedStateChange(View view, View view2, boolean z8, boolean z9) {
        updateImportantForAccessibility(view2, z8);
        return super.onExpandedStateChange(view, view2, z8, z9);
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
