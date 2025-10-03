package com.google.android.material.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.C3476R;
import p042d0.C4627h;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private boolean singleLine;

    public FlowLayout(Context context) {
        this(context, null);
    }

    private static int getMeasuredDimension(int i9, int i10, int i11) {
        return i10 != Integer.MIN_VALUE ? i10 != 1073741824 ? i11 : i9 : Math.min(i11, i9);
    }

    private void loadFromAttributes(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C3476R.styleable.FlowLayout, 0, 0);
        this.lineSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.FlowLayout_itemSpacing, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getItemSpacing() {
        return this.itemSpacing;
    }

    public int getLineSpacing() {
        return this.lineSpacing;
    }

    public boolean isSingleLine() {
        return this.singleLine;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int iM18428a;
        int iM18429b;
        if (getChildCount() == 0) {
            return;
        }
        boolean z9 = C4647u.m18567s(this) == 1;
        int paddingRight = z9 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z9 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i13 = (i11 - i9) - paddingLeft;
        int measuredWidth = paddingRight;
        int i14 = paddingTop;
        for (int i15 = 0; i15 < getChildCount(); i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    iM18429b = C4627h.m18429b(marginLayoutParams);
                    iM18428a = C4627h.m18428a(marginLayoutParams);
                } else {
                    iM18428a = 0;
                    iM18429b = 0;
                }
                int measuredWidth2 = measuredWidth + iM18429b + childAt.getMeasuredWidth();
                if (!this.singleLine && measuredWidth2 > i13) {
                    i14 = this.lineSpacing + paddingTop;
                    measuredWidth = paddingRight;
                }
                int i16 = measuredWidth + iM18429b;
                int measuredWidth3 = childAt.getMeasuredWidth() + i16;
                int measuredHeight = childAt.getMeasuredHeight() + i14;
                if (z9) {
                    childAt.layout(i13 - measuredWidth3, i14, (i13 - measuredWidth) - iM18429b, measuredHeight);
                } else {
                    childAt.layout(i16, i14, measuredWidth3, measuredHeight);
                }
                measuredWidth += iM18429b + iM18428a + childAt.getMeasuredWidth() + this.itemSpacing;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int i11;
        int i12;
        int paddingLeft;
        int size = View.MeasureSpec.getSize(i9);
        int mode = View.MeasureSpec.getMode(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        int mode2 = View.MeasureSpec.getMode(i10);
        int i13 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i13 - getPaddingRight();
        int i14 = paddingTop;
        int i15 = 0;
        for (int i16 = 0; i16 < getChildCount(); i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i9, i10);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i11 = marginLayoutParams.leftMargin + 0;
                    i12 = marginLayoutParams.rightMargin + 0;
                } else {
                    i11 = 0;
                    i12 = 0;
                }
                int i17 = paddingLeft2;
                if (paddingLeft2 + i11 + childAt.getMeasuredWidth() <= paddingRight || isSingleLine()) {
                    paddingLeft = i17;
                } else {
                    paddingLeft = getPaddingLeft();
                    i14 = this.lineSpacing + paddingTop;
                }
                int measuredWidth = paddingLeft + i11 + childAt.getMeasuredWidth();
                int measuredHeight = i14 + childAt.getMeasuredHeight();
                if (measuredWidth > i15) {
                    i15 = measuredWidth;
                }
                paddingLeft2 = paddingLeft + i11 + i12 + childAt.getMeasuredWidth() + this.itemSpacing;
                paddingTop = measuredHeight;
            }
        }
        setMeasuredDimension(getMeasuredDimension(size, mode, i15), getMeasuredDimension(size2, mode2, paddingTop));
    }

    public void setItemSpacing(int i9) {
        this.itemSpacing = i9;
    }

    public void setLineSpacing(int i9) {
        this.lineSpacing = i9;
    }

    public void setSingleLine(boolean z8) {
        this.singleLine = z8;
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }

    @TargetApi(21)
    public FlowLayout(Context context, AttributeSet attributeSet, int i9, int i10) {
        super(context, attributeSet, i9, i10);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }
}
