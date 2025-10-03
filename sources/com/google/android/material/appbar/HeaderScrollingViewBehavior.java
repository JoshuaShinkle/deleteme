package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;
import p042d0.C4621e;
import p042d0.C4628h0;
import p042d0.C4647u;
import p242y.C6587a;

/* loaded from: classes2.dex */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private int overlayTop;
    final Rect tempRect1;
    final Rect tempRect2;
    private int verticalLayoutGap;

    public HeaderScrollingViewBehavior() {
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }

    private static int resolveGravity(int i9) {
        if (i9 == 0) {
            return 8388659;
        }
        return i9;
    }

    public abstract View findFirstDependency(List<View> list);

    public final int getOverlapPixelsForOffset(View view) {
        if (this.overlayTop == 0) {
            return 0;
        }
        float overlapRatioForOffset = getOverlapRatioForOffset(view);
        int i9 = this.overlayTop;
        return C6587a.m25200b((int) (overlapRatioForOffset * i9), 0, i9);
    }

    public float getOverlapRatioForOffset(View view) {
        return 1.0f;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }

    public int getScrollRange(View view) {
        return view.getMeasuredHeight();
    }

    public final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i9) {
        View viewFindFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
        if (viewFindFirstDependency == null) {
            super.layoutChild(coordinatorLayout, view, i9);
            this.verticalLayoutGap = 0;
            return;
        }
        CoordinatorLayout.C0307f c0307f = (CoordinatorLayout.C0307f) view.getLayoutParams();
        Rect rect = this.tempRect1;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0307f).leftMargin, viewFindFirstDependency.getBottom() + ((ViewGroup.MarginLayoutParams) c0307f).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin, ((coordinatorLayout.getHeight() + viewFindFirstDependency.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin);
        C4628h0 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && C4647u.m18561p(coordinatorLayout) && !C4647u.m18561p(view)) {
            rect.left += lastWindowInsets.m18437e();
            rect.right -= lastWindowInsets.m18438f();
        }
        Rect rect2 = this.tempRect2;
        C4621e.m18419a(resolveGravity(c0307f.f1723c), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i9);
        int overlapPixelsForOffset = getOverlapPixelsForOffset(viewFindFirstDependency);
        view.layout(rect2.left, rect2.top - overlapPixelsForOffset, rect2.right, rect2.bottom - overlapPixelsForOffset);
        this.verticalLayoutGap = rect2.top - viewFindFirstDependency.getBottom();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i9, int i10, int i11, int i12) {
        View viewFindFirstDependency;
        int i13 = view.getLayoutParams().height;
        if ((i13 != -1 && i13 != -2) || (viewFindFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view))) == null) {
            return false;
        }
        if (C4647u.m18561p(viewFindFirstDependency) && !C4647u.m18561p(view)) {
            C4647u.m18544g0(view, true);
            if (C4647u.m18561p(view)) {
                view.requestLayout();
                return true;
            }
        }
        int size = View.MeasureSpec.getSize(i11);
        if (size == 0) {
            size = coordinatorLayout.getHeight();
        }
        coordinatorLayout.onMeasureChild(view, i9, i10, View.MeasureSpec.makeMeasureSpec((size - viewFindFirstDependency.getMeasuredHeight()) + getScrollRange(viewFindFirstDependency), i13 == -1 ? 1073741824 : Integer.MIN_VALUE), i12);
        return true;
    }

    public final void setOverlayTop(int i9) {
        this.overlayTop = i9;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }
}
