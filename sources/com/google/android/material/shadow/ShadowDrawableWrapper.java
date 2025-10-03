package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import p041d.C4612a;
import p197t.C6273a;

/* loaded from: classes2.dex */
public class ShadowDrawableWrapper extends C4612a {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f9, float f10, float f11) {
        super(drawable);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = C6273a.m24024c(context, C3476R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = C6273a.m24024c(context, C3476R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = C6273a.m24024c(context, C3476R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.cornerShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.cornerRadius = Math.round(f9);
        this.contentBounds = new RectF();
        Paint paint2 = new Paint(paint);
        this.edgeShadowPaint = paint2;
        paint2.setAntiAlias(false);
        setShadowSize(f10, f11);
    }

    private void buildComponents(Rect rect) {
        float f9 = this.rawMaxShadowSize;
        float f10 = SHADOW_MULTIPLIER * f9;
        this.contentBounds.set(rect.left + f9, rect.top + f10, rect.right - f9, rect.bottom - f10);
        Drawable wrappedDrawable = getWrappedDrawable();
        RectF rectF = this.contentBounds;
        wrappedDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        buildShadowCorners();
    }

    private void buildShadowCorners() {
        float f9 = this.cornerRadius;
        RectF rectF = new RectF(-f9, -f9, f9, f9);
        RectF rectF2 = new RectF(rectF);
        float f10 = this.shadowSize;
        rectF2.inset(-f10, -f10);
        Path path = this.cornerShadowPath;
        if (path == null) {
            this.cornerShadowPath = new Path();
        } else {
            path.reset();
        }
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, BitmapDescriptorFactory.HUE_RED);
        this.cornerShadowPath.rLineTo(-this.shadowSize, BitmapDescriptorFactory.HUE_RED);
        this.cornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float f11 = -rectF2.top;
        if (f11 > BitmapDescriptorFactory.HUE_RED) {
            float f12 = this.cornerRadius / f11;
            this.cornerShadowPaint.setShader(new RadialGradient(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f11, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{BitmapDescriptorFactory.HUE_RED, f12, ((SHADOW_BOTTOM_SCALE - f12) / 2.0f) + f12, SHADOW_BOTTOM_SCALE}, Shader.TileMode.CLAMP));
        }
        this.edgeShadowPaint.setShader(new LinearGradient(BitmapDescriptorFactory.HUE_RED, rectF.top, BitmapDescriptorFactory.HUE_RED, rectF2.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{BitmapDescriptorFactory.HUE_RED, SHADOW_HORIZ_SCALE, SHADOW_BOTTOM_SCALE}, Shader.TileMode.CLAMP));
        this.edgeShadowPaint.setAntiAlias(false);
    }

    public static float calculateHorizontalPadding(float f9, float f10, boolean z8) {
        return z8 ? (float) (f9 + ((1.0d - COS_45) * f10)) : f9;
    }

    public static float calculateVerticalPadding(float f9, float f10, boolean z8) {
        return z8 ? (float) ((f9 * SHADOW_MULTIPLIER) + ((1.0d - COS_45) * f10)) : f9 * SHADOW_MULTIPLIER;
    }

    private void drawShadow(Canvas canvas) {
        int i9;
        float f9;
        int i10;
        float f10;
        float f11;
        float f12;
        int iSave = canvas.save();
        canvas.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float f13 = this.cornerRadius;
        float f14 = (-f13) - this.shadowSize;
        float f15 = f13 * 2.0f;
        boolean z8 = this.contentBounds.width() - f15 > BitmapDescriptorFactory.HUE_RED;
        boolean z9 = this.contentBounds.height() - f15 > BitmapDescriptorFactory.HUE_RED;
        float f16 = this.rawShadowSize;
        float f17 = f16 - (SHADOW_TOP_SCALE * f16);
        float f18 = f13 / ((f16 - (SHADOW_HORIZ_SCALE * f16)) + f13);
        float f19 = f13 / (f17 + f13);
        float f20 = f13 / ((f16 - (f16 * SHADOW_BOTTOM_SCALE)) + f13);
        int iSave2 = canvas.save();
        RectF rectF = this.contentBounds;
        canvas.translate(rectF.left + f13, rectF.top + f13);
        canvas.scale(f18, f19);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z8) {
            canvas.scale(SHADOW_BOTTOM_SCALE / f18, SHADOW_BOTTOM_SCALE);
            i9 = iSave2;
            f9 = f20;
            i10 = iSave;
            f10 = f19;
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f14, this.contentBounds.width() - f15, -this.cornerRadius, this.edgeShadowPaint);
        } else {
            i9 = iSave2;
            f9 = f20;
            i10 = iSave;
            f10 = f19;
        }
        canvas.restoreToCount(i9);
        int iSave3 = canvas.save();
        RectF rectF2 = this.contentBounds;
        canvas.translate(rectF2.right - f13, rectF2.bottom - f13);
        float f21 = f9;
        canvas.scale(f18, f21);
        canvas.rotate(180.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z8) {
            canvas.scale(SHADOW_BOTTOM_SCALE / f18, SHADOW_BOTTOM_SCALE);
            f11 = f10;
            f12 = f21;
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f14, this.contentBounds.width() - f15, (-this.cornerRadius) + this.shadowSize, this.edgeShadowPaint);
        } else {
            f11 = f10;
            f12 = f21;
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF3 = this.contentBounds;
        canvas.translate(rectF3.left + f13, rectF3.bottom - f13);
        canvas.scale(f18, f12);
        canvas.rotate(270.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z9) {
            canvas.scale(SHADOW_BOTTOM_SCALE / f12, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f14, this.contentBounds.height() - f15, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave4);
        int iSave5 = canvas.save();
        RectF rectF4 = this.contentBounds;
        canvas.translate(rectF4.right - f13, rectF4.top + f13);
        float f22 = f11;
        canvas.scale(f18, f22);
        canvas.rotate(90.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z9) {
            canvas.scale(SHADOW_BOTTOM_SCALE / f22, SHADOW_BOTTOM_SCALE);
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f14, this.contentBounds.height() - f15, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave5);
        canvas.restoreToCount(i10);
    }

    private static int toEven(float f9) {
        int iRound = Math.round(f9);
        return iRound % 2 == 1 ? iRound - 1 : iRound;
    }

    @Override // p041d.C4612a, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinHeight() {
        float f9 = this.rawMaxShadowSize;
        return (Math.max(f9, this.cornerRadius + ((f9 * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * SHADOW_MULTIPLIER * 2.0f);
    }

    public float getMinWidth() {
        float f9 = this.rawMaxShadowSize;
        return (Math.max(f9, this.cornerRadius + (f9 / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * 2.0f);
    }

    @Override // p041d.C4612a, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // p041d.C4612a, android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int iCeil2 = (int) Math.ceil(calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    @Override // p041d.C4612a, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.dirty = true;
    }

    public void setAddPaddingForCorners(boolean z8) {
        this.addPaddingForCorners = z8;
        invalidateSelf();
    }

    @Override // p041d.C4612a, android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        super.setAlpha(i9);
        this.cornerShadowPaint.setAlpha(i9);
        this.edgeShadowPaint.setAlpha(i9);
    }

    public void setCornerRadius(float f9) {
        float fRound = Math.round(f9);
        if (this.cornerRadius == fRound) {
            return;
        }
        this.cornerRadius = fRound;
        this.dirty = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float f9) {
        setShadowSize(this.rawShadowSize, f9);
    }

    public final void setRotation(float f9) {
        if (this.rotation != f9) {
            this.rotation = f9;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f9, float f10) {
        if (f9 < BitmapDescriptorFactory.HUE_RED || f10 < BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float even = toEven(f9);
        float even2 = toEven(f10);
        if (even > even2) {
            if (!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.rawShadowSize == even && this.rawMaxShadowSize == even2) {
            return;
        }
        this.rawShadowSize = even;
        this.rawMaxShadowSize = even2;
        this.shadowSize = Math.round(even * SHADOW_MULTIPLIER);
        this.maxShadowSize = even2;
        this.dirty = true;
        invalidateSelf();
    }

    public void setShadowSize(float f9) {
        setShadowSize(f9, this.rawMaxShadowSize);
    }
}
