package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private int maxInlineActionWidth;
    private int maxWidth;
    private TextView messageView;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    private static void updateTopBottomPadding(View view, int i9, int i10) {
        if (C4647u.m18515K(view)) {
            C4647u.m18556m0(view, C4647u.m18571w(view), i9, C4647u.m18570v(view), i10);
        } else {
            view.setPadding(view.getPaddingLeft(), i9, view.getPaddingRight(), i10);
        }
    }

    private boolean updateViewsWithinLayout(int i9, int i10, int i11) {
        boolean z8;
        if (i9 != getOrientation()) {
            setOrientation(i9);
            z8 = true;
        } else {
            z8 = false;
        }
        if (this.messageView.getPaddingTop() == i10 && this.messageView.getPaddingBottom() == i11) {
            return z8;
        }
        updateTopBottomPadding(this.messageView, i10, i11);
        return true;
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentIn(int i9, int i10) {
        this.messageView.setAlpha(BitmapDescriptorFactory.HUE_RED);
        long j9 = i10;
        long j10 = i9;
        this.messageView.animate().alpha(1.0f).setDuration(j9).setStartDelay(j10).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(BitmapDescriptorFactory.HUE_RED);
            this.actionView.animate().alpha(1.0f).setDuration(j9).setStartDelay(j10).start();
        }
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentOut(int i9, int i10) {
        this.messageView.setAlpha(1.0f);
        long j9 = i10;
        long j10 = i9;
        this.messageView.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setStartDelay(j10).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(1.0f);
            this.actionView.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setStartDelay(j10).start();
        }
    }

    public Button getActionView() {
        return this.actionView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(C3476R.id.snackbar_text);
        this.actionView = (Button) findViewById(C3476R.id.snackbar_action);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) throws Resources.NotFoundException {
        super.onMeasure(i9, i10);
        if (this.maxWidth > 0) {
            int measuredWidth = getMeasuredWidth();
            int i11 = this.maxWidth;
            if (measuredWidth > i11) {
                i9 = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
                super.onMeasure(i9, i10);
            }
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(C3476R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C3476R.dimen.design_snackbar_padding_vertical);
        boolean z8 = false;
        boolean z9 = this.messageView.getLayout().getLineCount() > 1;
        if (!z9 || this.maxInlineActionWidth <= 0 || this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) {
            if (!z9) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (updateViewsWithinLayout(0, dimensionPixelSize, dimensionPixelSize)) {
                z8 = true;
            }
        } else if (updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
        }
        if (z8) {
            super.onMeasure(i9, i10);
        }
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.SnackbarLayout);
        this.maxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.SnackbarLayout_android_maxWidth, -1);
        this.maxInlineActionWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        typedArrayObtainStyledAttributes.recycle();
    }
}
