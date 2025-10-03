package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.expandable.ExpandableWidget;
import java.util.List;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public abstract class ExpandableBehavior extends CoordinatorLayout.AbstractC0304c<View> {
    private static final int STATE_COLLAPSED = 2;
    private static final int STATE_EXPANDED = 1;
    private static final int STATE_UNINITIALIZED = 0;
    private int currentState;

    public ExpandableBehavior() {
        this.currentState = 0;
    }

    private boolean didStateChange(boolean z8) {
        if (!z8) {
            return this.currentState == 1;
        }
        int i9 = this.currentState;
        return i9 == 0 || i9 == 2;
    }

    public static <T extends ExpandableBehavior> T from(View view, Class<T> cls) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.C0307f)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.AbstractC0304c abstractC0304cM1439f = ((CoordinatorLayout.C0307f) layoutParams).m1439f();
        if (abstractC0304cM1439f instanceof ExpandableBehavior) {
            return cls.cast(abstractC0304cM1439f);
        }
        throw new IllegalArgumentException("The view is not associated with ExpandableBehavior");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableWidget findExpandableWidget(CoordinatorLayout coordinatorLayout, View view) {
        List<View> dependencies = coordinatorLayout.getDependencies(view);
        int size = dependencies.size();
        for (int i9 = 0; i9 < size; i9++) {
            View view2 = dependencies.get(i9);
            if (layoutDependsOn(coordinatorLayout, view, view2)) {
                return (ExpandableWidget) view2;
            }
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public abstract boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        ExpandableWidget expandableWidget = (ExpandableWidget) view2;
        if (!didStateChange(expandableWidget.isExpanded())) {
            return false;
        }
        this.currentState = expandableWidget.isExpanded() ? 1 : 2;
        return onExpandedStateChange((View) expandableWidget, view, expandableWidget.isExpanded(), true);
    }

    public abstract boolean onExpandedStateChange(View view, View view2, boolean z8, boolean z9);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, final View view, int i9) {
        final ExpandableWidget expandableWidgetFindExpandableWidget;
        if (C4647u.m18513I(view) || (expandableWidgetFindExpandableWidget = findExpandableWidget(coordinatorLayout, view)) == null || !didStateChange(expandableWidgetFindExpandableWidget.isExpanded())) {
            return false;
        }
        final int i10 = expandableWidgetFindExpandableWidget.isExpanded() ? 1 : 2;
        this.currentState = i10;
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.transformation.ExpandableBehavior.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ExpandableBehavior.this.currentState == i10) {
                    ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                    ExpandableWidget expandableWidget = expandableWidgetFindExpandableWidget;
                    expandableBehavior.onExpandedStateChange((View) expandableWidget, view, expandableWidget.isExpanded(), false);
                }
                return false;
            }
        });
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.currentState = 0;
    }
}
