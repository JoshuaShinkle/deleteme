package com.cyberlink.you.widgetpool.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class FixedAspectRatioLinearLayout extends LinearLayout {

    /* renamed from: b */
    public int f15091b;

    /* renamed from: c */
    public int f15092c;

    public FixedAspectRatioLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15091b = -1;
        this.f15092c = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15091b = typedArrayObtainStyledAttributes.getInteger(1, 1);
        this.f15092c = typedArrayObtainStyledAttributes.getInteger(0, 1);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.f15091b == 0 || this.f15092c == 0) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight == 0 && measuredWidth != 0) {
            int i11 = (int) (measuredWidth * (this.f15092c / this.f15091b));
            setMeasuredDimension(measuredWidth, i11);
            measureChildren(i9, View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
            return;
        }
        if (measuredWidth != 0 || measuredHeight == 0) {
            return;
        }
        int i12 = (int) (measuredHeight * (this.f15091b / this.f15092c));
        setMeasuredDimension(i12, measuredHeight);
        measureChildren(View.MeasureSpec.makeMeasureSpec(i12, 1073741824), i10);
    }

    public void setGuidelineHeight(int i9) {
        this.f15092c = i9;
    }

    public FixedAspectRatioLinearLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15091b = -1;
        this.f15092c = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15091b = typedArrayObtainStyledAttributes.getInteger(1, 1);
        this.f15092c = typedArrayObtainStyledAttributes.getInteger(0, 1);
        typedArrayObtainStyledAttributes.recycle();
    }
}
