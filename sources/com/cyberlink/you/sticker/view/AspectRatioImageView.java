package com.cyberlink.you.sticker.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class AspectRatioImageView extends ImageView {
    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(i9, i10);
            return;
        }
        int size = View.MeasureSpec.getSize(i9);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth > 0) {
            setMeasuredDimension(size, (drawable.getIntrinsicHeight() * size) / intrinsicWidth);
        } else {
            super.onMeasure(i9, i10);
        }
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
