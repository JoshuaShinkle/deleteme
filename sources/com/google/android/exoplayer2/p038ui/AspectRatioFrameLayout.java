package com.google.android.exoplayer2.p038ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class AspectRatioFrameLayout extends FrameLayout {
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    public static final int RESIZE_MODE_FILL = 3;
    public static final int RESIZE_MODE_FIT = 0;
    public static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    public static final int RESIZE_MODE_FIXED_WIDTH = 1;
    public static final int RESIZE_MODE_ZOOM = 4;
    private int resizeMode;
    private float videoAspectRatio;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResizeMode {
    }

    public AspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        float f9;
        float f10;
        super.onMeasure(i9, i10);
        if (this.resizeMode == 3 || this.videoAspectRatio <= BitmapDescriptorFactory.HUE_RED) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f11 = measuredWidth;
        float f12 = measuredHeight;
        float f13 = (this.videoAspectRatio / (f11 / f12)) - 1.0f;
        if (Math.abs(f13) <= MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
            return;
        }
        int i11 = this.resizeMode;
        if (i11 != 1) {
            if (i11 != 2) {
                if (i11 != 4) {
                    if (f13 > BitmapDescriptorFactory.HUE_RED) {
                        f9 = this.videoAspectRatio;
                    } else {
                        f10 = this.videoAspectRatio;
                    }
                } else if (f13 > BitmapDescriptorFactory.HUE_RED) {
                    f10 = this.videoAspectRatio;
                } else {
                    f9 = this.videoAspectRatio;
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
            f10 = this.videoAspectRatio;
            measuredWidth = (int) (f12 * f10);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
        f9 = this.videoAspectRatio;
        measuredHeight = (int) (f11 / f9);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
    }

    public void setAspectRatio(float f9) {
        if (this.videoAspectRatio != f9) {
            this.videoAspectRatio = f9;
            requestLayout();
        }
    }

    public void setResizeMode(int i9) {
        if (this.resizeMode != i9) {
            this.resizeMode = i9;
            requestLayout();
        }
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.resizeMode = 0;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C3429R.styleable.AspectRatioFrameLayout, 0, 0);
            try {
                this.resizeMode = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.AspectRatioFrameLayout_resize_mode, 0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }
}
