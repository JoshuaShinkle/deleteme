package com.google.android.material.button;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import com.google.android.material.C3476R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import p042d0.C4647u;
import p224w.C6494a;

/* loaded from: classes2.dex */
class MaterialButtonHelper {
    private static final float CORNER_RADIUS_ADJUSTMENT = 1.0E-5f;
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final boolean IS_LOLLIPOP = true;
    private GradientDrawable backgroundDrawableLollipop;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private GradientDrawable colorableBackgroundDrawableCompat;
    private int cornerRadius;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private GradientDrawable maskDrawableLollipop;
    private final MaterialButton materialButton;
    private ColorStateList rippleColor;
    private GradientDrawable rippleDrawableCompat;
    private ColorStateList strokeColor;
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;
    private Drawable tintableBackgroundDrawableCompat;
    private Drawable tintableRippleDrawableCompat;
    private final Paint buttonStrokePaint = new Paint(1);
    private final Rect bounds = new Rect();
    private final RectF rectF = new RectF();
    private boolean backgroundOverwritten = false;

    public MaterialButtonHelper(MaterialButton materialButton) {
        this.materialButton = materialButton;
    }

    private Drawable createBackgroundCompat() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.colorableBackgroundDrawableCompat = gradientDrawable;
        gradientDrawable.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.colorableBackgroundDrawableCompat.setColor(-1);
        Drawable drawableM24849l = C6494a.m24849l(this.colorableBackgroundDrawableCompat);
        this.tintableBackgroundDrawableCompat = drawableM24849l;
        C6494a.m24846i(drawableM24849l, this.backgroundTint);
        PorterDuff.Mode mode = this.backgroundTintMode;
        if (mode != null) {
            C6494a.m24847j(this.tintableBackgroundDrawableCompat, mode);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.rippleDrawableCompat = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.rippleDrawableCompat.setColor(-1);
        Drawable drawableM24849l2 = C6494a.m24849l(this.rippleDrawableCompat);
        this.tintableRippleDrawableCompat = drawableM24849l2;
        C6494a.m24846i(drawableM24849l2, this.rippleColor);
        return wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.tintableBackgroundDrawableCompat, this.tintableRippleDrawableCompat}));
    }

    @TargetApi(21)
    private Drawable createBackgroundLollipop() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.backgroundDrawableLollipop = gradientDrawable;
        gradientDrawable.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.backgroundDrawableLollipop.setColor(-1);
        updateTintAndTintModeLollipop();
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.strokeDrawableLollipop = gradientDrawable2;
        gradientDrawable2.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.strokeDrawableLollipop.setColor(0);
        this.strokeDrawableLollipop.setStroke(this.strokeWidth, this.strokeColor);
        InsetDrawable insetDrawableWrapDrawableWithInset = wrapDrawableWithInset(new LayerDrawable(new Drawable[]{this.backgroundDrawableLollipop, this.strokeDrawableLollipop}));
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        this.maskDrawableLollipop = gradientDrawable3;
        gradientDrawable3.setCornerRadius(this.cornerRadius + CORNER_RADIUS_ADJUSTMENT);
        this.maskDrawableLollipop.setColor(-1);
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(this.rippleColor), insetDrawableWrapDrawableWithInset, this.maskDrawableLollipop);
    }

    private GradientDrawable unwrapBackgroundDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }

    private GradientDrawable unwrapStrokeDrawable() {
        if (!IS_LOLLIPOP || this.materialButton.getBackground() == null) {
            return null;
        }
        return (GradientDrawable) ((LayerDrawable) ((InsetDrawable) ((RippleDrawable) this.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }

    private void updateStroke() {
        boolean z8 = IS_LOLLIPOP;
        if (z8 && this.strokeDrawableLollipop != null) {
            this.materialButton.setInternalBackground(createBackgroundLollipop());
        } else {
            if (z8) {
                return;
            }
            this.materialButton.invalidate();
        }
    }

    private void updateTintAndTintModeLollipop() {
        GradientDrawable gradientDrawable = this.backgroundDrawableLollipop;
        if (gradientDrawable != null) {
            C6494a.m24846i(gradientDrawable, this.backgroundTint);
            PorterDuff.Mode mode = this.backgroundTintMode;
            if (mode != null) {
                C6494a.m24847j(this.backgroundDrawableLollipop, mode);
            }
        }
    }

    private InsetDrawable wrapDrawableWithInset(Drawable drawable) {
        return new InsetDrawable(drawable, this.insetLeft, this.insetTop, this.insetRight, this.insetBottom);
    }

    public void drawStroke(Canvas canvas) {
        if (canvas == null || this.strokeColor == null || this.strokeWidth <= 0) {
            return;
        }
        this.bounds.set(this.materialButton.getBackground().getBounds());
        RectF rectF = this.rectF;
        float f9 = this.bounds.left;
        int i9 = this.strokeWidth;
        rectF.set(f9 + (i9 / 2.0f) + this.insetLeft, r1.top + (i9 / 2.0f) + this.insetTop, (r1.right - (i9 / 2.0f)) - this.insetRight, (r1.bottom - (i9 / 2.0f)) - this.insetBottom);
        float f10 = this.cornerRadius - (this.strokeWidth / 2.0f);
        canvas.drawRoundRect(this.rectF, f10, f10, this.buttonStrokePaint);
    }

    public int getCornerRadius() {
        return this.cornerRadius;
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    public boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        this.insetLeft = typedArray.getDimensionPixelOffset(C3476R.styleable.MaterialButton_android_insetLeft, 0);
        this.insetRight = typedArray.getDimensionPixelOffset(C3476R.styleable.MaterialButton_android_insetRight, 0);
        this.insetTop = typedArray.getDimensionPixelOffset(C3476R.styleable.MaterialButton_android_insetTop, 0);
        this.insetBottom = typedArray.getDimensionPixelOffset(C3476R.styleable.MaterialButton_android_insetBottom, 0);
        this.cornerRadius = typedArray.getDimensionPixelSize(C3476R.styleable.MaterialButton_cornerRadius, 0);
        this.strokeWidth = typedArray.getDimensionPixelSize(C3476R.styleable.MaterialButton_strokeWidth, 0);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArray.getInt(C3476R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.backgroundTint = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C3476R.styleable.MaterialButton_backgroundTint);
        this.strokeColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C3476R.styleable.MaterialButton_strokeColor);
        this.rippleColor = MaterialResources.getColorStateList(this.materialButton.getContext(), typedArray, C3476R.styleable.MaterialButton_rippleColor);
        this.buttonStrokePaint.setStyle(Paint.Style.STROKE);
        this.buttonStrokePaint.setStrokeWidth(this.strokeWidth);
        Paint paint = this.buttonStrokePaint;
        ColorStateList colorStateList = this.strokeColor;
        paint.setColor(colorStateList != null ? colorStateList.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
        int iM18571w = C4647u.m18571w(this.materialButton);
        int paddingTop = this.materialButton.getPaddingTop();
        int iM18570v = C4647u.m18570v(this.materialButton);
        int paddingBottom = this.materialButton.getPaddingBottom();
        this.materialButton.setInternalBackground(IS_LOLLIPOP ? createBackgroundLollipop() : createBackgroundCompat());
        C4647u.m18556m0(this.materialButton, iM18571w + this.insetLeft, paddingTop + this.insetTop, iM18570v + this.insetRight, paddingBottom + this.insetBottom);
    }

    public void setBackgroundColor(int i9) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        boolean z8 = IS_LOLLIPOP;
        if (z8 && (gradientDrawable2 = this.backgroundDrawableLollipop) != null) {
            gradientDrawable2.setColor(i9);
        } else {
            if (z8 || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null) {
                return;
            }
            gradientDrawable.setColor(i9);
        }
    }

    public void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    public void setCornerRadius(int i9) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (this.cornerRadius != i9) {
            this.cornerRadius = i9;
            boolean z8 = IS_LOLLIPOP;
            if (z8 && (gradientDrawable2 = this.backgroundDrawableLollipop) != null && this.strokeDrawableLollipop != null && this.maskDrawableLollipop != null) {
                float f9 = i9 + CORNER_RADIUS_ADJUSTMENT;
                gradientDrawable2.setCornerRadius(f9);
                this.strokeDrawableLollipop.setCornerRadius(f9);
                this.maskDrawableLollipop.setCornerRadius(f9);
                return;
            }
            if (z8 || (gradientDrawable = this.colorableBackgroundDrawableCompat) == null || this.rippleDrawableCompat == null) {
                return;
            }
            float f10 = i9 + CORNER_RADIUS_ADJUSTMENT;
            gradientDrawable.setCornerRadius(f10);
            this.rippleDrawableCompat.setCornerRadius(f10);
            this.materialButton.invalidate();
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            boolean z8 = IS_LOLLIPOP;
            if (z8 && (this.materialButton.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.materialButton.getBackground()).setColor(colorStateList);
            } else {
                if (z8 || (drawable = this.tintableRippleDrawableCompat) == null) {
                    return;
                }
                C6494a.m24846i(drawable, colorStateList);
            }
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (this.strokeColor != colorStateList) {
            this.strokeColor = colorStateList;
            this.buttonStrokePaint.setColor(colorStateList != null ? colorStateList.getColorForState(this.materialButton.getDrawableState(), 0) : 0);
            updateStroke();
        }
    }

    public void setStrokeWidth(int i9) {
        if (this.strokeWidth != i9) {
            this.strokeWidth = i9;
            this.buttonStrokePaint.setStrokeWidth(i9);
            updateStroke();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable != null) {
                C6494a.m24846i(drawable, colorStateList);
            }
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable == null || mode == null) {
                return;
            }
            C6494a.m24847j(drawable, mode);
        }
    }

    public void updateMaskBounds(int i9, int i10) {
        GradientDrawable gradientDrawable = this.maskDrawableLollipop;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(this.insetLeft, this.insetTop, i10 - this.insetRight, i9 - this.insetBottom);
        }
    }
}
