package com.google.android.material.internal;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p215v.C6427a;

/* loaded from: classes2.dex */
public class CircularBorderDrawable extends Drawable {
    private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333f;
    private ColorStateList borderTint;
    float borderWidth;
    private int bottomInnerStrokeColor;
    private int bottomOuterStrokeColor;
    private int currentBorderTintColor;
    final Paint paint;
    private float rotation;
    private int topInnerStrokeColor;
    private int topOuterStrokeColor;
    final Rect rect = new Rect();
    final RectF rectF = new RectF();
    final CircularBorderState state = new CircularBorderState();
    private boolean invalidateShader = true;

    public class CircularBorderState extends Drawable.ConstantState {
        private CircularBorderState() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return CircularBorderDrawable.this;
        }
    }

    public CircularBorderDrawable() {
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    private Shader createGradientShader() {
        copyBounds(this.rect);
        float fHeight = this.borderWidth / r0.height();
        return new LinearGradient(BitmapDescriptorFactory.HUE_RED, r0.top, BitmapDescriptorFactory.HUE_RED, r0.bottom, new int[]{C6427a.m24588b(this.topOuterStrokeColor, this.currentBorderTintColor), C6427a.m24588b(this.topInnerStrokeColor, this.currentBorderTintColor), C6427a.m24588b(C6427a.m24590d(this.topInnerStrokeColor, 0), this.currentBorderTintColor), C6427a.m24588b(C6427a.m24590d(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor), C6427a.m24588b(this.bottomInnerStrokeColor, this.currentBorderTintColor), C6427a.m24588b(this.bottomOuterStrokeColor, this.currentBorderTintColor)}, new float[]{BitmapDescriptorFactory.HUE_RED, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.invalidateShader) {
            this.paint.setShader(createGradientShader());
            this.invalidateShader = false;
        }
        float strokeWidth = this.paint.getStrokeWidth() / 2.0f;
        RectF rectF = this.rectF;
        copyBounds(this.rect);
        rectF.set(this.rect);
        rectF.left += strokeWidth;
        rectF.top += strokeWidth;
        rectF.right -= strokeWidth;
        rectF.bottom -= strokeWidth;
        canvas.save();
        canvas.rotate(this.rotation, rectF.centerX(), rectF.centerY());
        canvas.drawOval(rectF, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.borderWidth > BitmapDescriptorFactory.HUE_RED ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iRound = Math.round(this.borderWidth);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.borderTint;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.invalidateShader = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.borderTint;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.currentBorderTintColor)) != this.currentBorderTintColor) {
            this.invalidateShader = true;
            this.currentBorderTintColor = colorForState;
        }
        if (this.invalidateShader) {
            invalidateSelf();
        }
        return this.invalidateShader;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.paint.setAlpha(i9);
        invalidateSelf();
    }

    public void setBorderTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.currentBorderTintColor = colorStateList.getColorForState(getState(), this.currentBorderTintColor);
        }
        this.borderTint = colorStateList;
        this.invalidateShader = true;
        invalidateSelf();
    }

    public void setBorderWidth(float f9) {
        if (this.borderWidth != f9) {
            this.borderWidth = f9;
            this.paint.setStrokeWidth(f9 * DRAW_STROKE_WIDTH_MULTIPLE);
            this.invalidateShader = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setGradientColors(int i9, int i10, int i11, int i12) {
        this.topOuterStrokeColor = i9;
        this.topInnerStrokeColor = i10;
        this.bottomOuterStrokeColor = i11;
        this.bottomInnerStrokeColor = i12;
    }

    public final void setRotation(float f9) {
        if (f9 != this.rotation) {
            this.rotation = f9;
            invalidateSelf();
        }
    }
}
