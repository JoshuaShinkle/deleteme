package com.cyberlink.you.groupalbum;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        int defaultSize = View.getDefaultSize(getSuggestedMinimumWidth(), i9);
        setMeasuredDimension(defaultSize, defaultSize);
    }

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
