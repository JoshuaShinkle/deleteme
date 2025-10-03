package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import p010b.C0565f;
import p010b.C0569j;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* renamed from: b */
    public boolean f836b;

    /* renamed from: c */
    public int f837c;

    /* renamed from: d */
    public int f838d;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f837c = -1;
        this.f838d = 0;
        int[] iArr = C0569j.ButtonBarLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        C4647u.m18528X(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        this.f836b = typedArrayObtainStyledAttributes.getBoolean(C0569j.ButtonBarLayout_allowStacking, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setStacked(boolean z8) {
        setOrientation(z8 ? 1 : 0);
        setGravity(z8 ? 5 : 80);
        View viewFindViewById = findViewById(C0565f.spacer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z8 ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    /* renamed from: a */
    public final int m684a(int i9) {
        int childCount = getChildCount();
        while (i9 < childCount) {
            if (getChildAt(i9).getVisibility() == 0) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* renamed from: b */
    public final boolean m685b() {
        return getOrientation() == 1;
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(this.f838d, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        int iMakeMeasureSpec;
        boolean z8;
        int size = View.MeasureSpec.getSize(i9);
        int paddingBottom = 0;
        if (this.f836b) {
            if (size > this.f837c && m685b()) {
                setStacked(false);
            }
            this.f837c = size;
        }
        if (m685b() || View.MeasureSpec.getMode(i9) != 1073741824) {
            iMakeMeasureSpec = i9;
            z8 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z8 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i10);
        if (this.f836b && !m685b()) {
            if ((getMeasuredWidthAndState() & (-16777216)) == 16777216) {
                setStacked(true);
                z8 = true;
            }
        }
        if (z8) {
            super.onMeasure(i9, i10);
        }
        int iM684a = m684a(0);
        if (iM684a >= 0) {
            View childAt = getChildAt(iM684a);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int paddingTop = getPaddingTop() + childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (m685b()) {
                int iM684a2 = m684a(iM684a + 1);
                if (iM684a2 >= 0) {
                    paddingTop += getChildAt(iM684a2).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
                paddingBottom = paddingTop;
            } else {
                paddingBottom = paddingTop + getPaddingBottom();
            }
        }
        if (C4647u.m18568t(this) != paddingBottom) {
            setMinimumHeight(paddingBottom);
        }
    }

    public void setAllowStacking(boolean z8) {
        if (this.f836b != z8) {
            this.f836b = z8;
            if (!z8 && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
