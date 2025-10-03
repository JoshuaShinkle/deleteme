package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes2.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.AbstractC0304c<V> {
    private int tempLeftRightOffset;
    private int tempTopBottomOffset;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public int getLeftAndRightOffset() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.getLeftAndRightOffset();
        }
        return 0;
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.getTopAndBottomOffset();
        }
        return 0;
    }

    public void layoutChild(CoordinatorLayout coordinatorLayout, V v8, int i9) {
        coordinatorLayout.onLayoutChild(v8, i9);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v8, int i9) {
        layoutChild(coordinatorLayout, v8, i9);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(v8);
        }
        this.viewOffsetHelper.onViewLayout();
        int i10 = this.tempTopBottomOffset;
        if (i10 != 0) {
            this.viewOffsetHelper.setTopAndBottomOffset(i10);
            this.tempTopBottomOffset = 0;
        }
        int i11 = this.tempLeftRightOffset;
        if (i11 == 0) {
            return true;
        }
        this.viewOffsetHelper.setLeftAndRightOffset(i11);
        this.tempLeftRightOffset = 0;
        return true;
    }

    public boolean setLeftAndRightOffset(int i9) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.setLeftAndRightOffset(i9);
        }
        this.tempLeftRightOffset = i9;
        return false;
    }

    public boolean setTopAndBottomOffset(int i9) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.setTopAndBottomOffset(i9);
        }
        this.tempTopBottomOffset = i9;
        return false;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }
}
