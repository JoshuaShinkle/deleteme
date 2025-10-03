package com.google.android.material.appbar;

import android.view.View;
import p042d0.C4647u;

/* loaded from: classes2.dex */
class ViewOffsetHelper {
    private int layoutLeft;
    private int layoutTop;
    private int offsetLeft;
    private int offsetTop;
    private final View view;

    public ViewOffsetHelper(View view) {
        this.view = view;
    }

    private void updateOffsets() {
        View view = this.view;
        C4647u.m18519O(view, this.offsetTop - (view.getTop() - this.layoutTop));
        View view2 = this.view;
        C4647u.m18518N(view2, this.offsetLeft - (view2.getLeft() - this.layoutLeft));
    }

    public int getLayoutLeft() {
        return this.layoutLeft;
    }

    public int getLayoutTop() {
        return this.layoutTop;
    }

    public int getLeftAndRightOffset() {
        return this.offsetLeft;
    }

    public int getTopAndBottomOffset() {
        return this.offsetTop;
    }

    public void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
        updateOffsets();
    }

    public boolean setLeftAndRightOffset(int i9) {
        if (this.offsetLeft == i9) {
            return false;
        }
        this.offsetLeft = i9;
        updateOffsets();
        return true;
    }

    public boolean setTopAndBottomOffset(int i9) {
        if (this.offsetTop == i9) {
            return false;
        }
        this.offsetTop = i9;
        updateOffsets();
        return true;
    }
}
