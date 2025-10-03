package com.cyberlink.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes.dex */
public class ExpandableHeightGridView extends GridView {
    public ExpandableHeightGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public ExpandableHeightGridView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
