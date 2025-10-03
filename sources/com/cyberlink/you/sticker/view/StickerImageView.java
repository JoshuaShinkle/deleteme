package com.cyberlink.you.sticker.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import com.cyberlink.you.Globals;

/* loaded from: classes.dex */
public class StickerImageView extends ImageView {

    /* renamed from: b */
    public static final int f14356b = Math.round(TypedValue.applyDimension(1, 110.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    public StickerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(i9, i10);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            super.onMeasure(i9, i10);
            return;
        }
        if (intrinsicWidth < intrinsicHeight) {
            int i11 = f14356b;
            setMeasuredDimension(i11, Math.round(intrinsicHeight * (i11 / intrinsicWidth)));
        } else {
            int i12 = f14356b;
            setMeasuredDimension(Math.round(intrinsicWidth * (i12 / intrinsicHeight)), i12);
        }
    }

    public StickerImageView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
