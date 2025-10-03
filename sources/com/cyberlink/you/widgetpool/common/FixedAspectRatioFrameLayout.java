package com.cyberlink.you.widgetpool.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class FixedAspectRatioFrameLayout extends FrameLayout {

    /* renamed from: b */
    public int f15089b;

    /* renamed from: c */
    public int f15090c;

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15089b = -1;
        this.f15090c = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15089b = typedArrayObtainStyledAttributes.getInteger(1, 1);
        this.f15090c = typedArrayObtainStyledAttributes.getInteger(0, 1);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public void m17311a(int i9, int i10) {
        this.f15089b = i9;
        this.f15090c = i10;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.f15089b == 0 || this.f15090c == 0) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight == 0 && measuredWidth != 0) {
            int i11 = (int) (measuredWidth * (this.f15090c / this.f15089b));
            setMeasuredDimension(measuredWidth, i11);
            measureChildren(i9, View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
            return;
        }
        if (measuredWidth != 0 || measuredHeight == 0) {
            return;
        }
        int i12 = (int) (measuredHeight * (this.f15089b / this.f15090c));
        setMeasuredDimension(i12, measuredHeight);
        measureChildren(View.MeasureSpec.makeMeasureSpec(i12, 1073741824), i10);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15089b = -1;
        this.f15090c = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15089b = typedArrayObtainStyledAttributes.getInteger(1, 1);
        this.f15090c = typedArrayObtainStyledAttributes.getInteger(0, 1);
        typedArrayObtainStyledAttributes.recycle();
    }
}
