package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.C0337l;
import com.google.android.material.C3476R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p020c.C0694a;
import p042d0.C4647u;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class MaterialButton extends AppCompatButton {
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    private static final String LOG_TAG = "MaterialButton";
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private final MaterialButtonHelper materialButtonHelper;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private boolean isLayoutRTL() {
        return C4647u.m18567s(this) == 1;
    }

    private boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return (materialButtonHelper == null || materialButtonHelper.isBackgroundOverwritten()) ? false : true;
    }

    private void updateIcon() {
        Drawable drawable = this.icon;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.icon = drawableMutate;
            C6494a.m24846i(drawableMutate, this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                C6494a.m24847j(this.icon, mode);
            }
            int intrinsicWidth = this.iconSize;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.icon.getIntrinsicWidth();
            }
            int intrinsicHeight = this.iconSize;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i9 = this.iconLeft;
            drawable2.setBounds(i9, 0, intrinsicWidth + i9, intrinsicHeight);
        }
        C0337l.m1615j(this, this.icon, null, null, null);
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getCornerRadius();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getRippleColor();
        }
        return null;
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeColor();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeWidth();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintList() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return isUsingOriginalBackground() ? this.materialButtonHelper.getSupportBackgroundTintMode() : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.icon == null || this.iconGravity != 2) {
            return;
        }
        int iMeasureText = (int) getPaint().measureText(getText().toString());
        int intrinsicWidth = this.iconSize;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.icon.getIntrinsicWidth();
        }
        int measuredWidth = (((((getMeasuredWidth() - iMeasureText) - C4647u.m18570v(this)) - intrinsicWidth) - this.iconPadding) - C4647u.m18571w(this)) / 2;
        if (isLayoutRTL()) {
            measuredWidth = -measuredWidth;
        }
        if (this.iconLeft != measuredWidth) {
            this.iconLeft = measuredWidth;
            updateIcon();
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i9) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(i9);
        } else {
            super.setBackgroundColor(i9);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.i(LOG_TAG, "Setting a custom background is not supported.");
            this.materialButtonHelper.setBackgroundOverwritten();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i9) {
        setBackgroundDrawable(i9 != 0 ? C0694a.m3458b(getContext(), i9) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCornerRadius(int i9) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(i9);
        }
    }

    public void setCornerRadiusResource(int i9) {
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(i9));
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.icon != drawable) {
            this.icon = drawable;
            updateIcon();
        }
    }

    public void setIconGravity(int i9) {
        this.iconGravity = i9;
    }

    public void setIconPadding(int i9) {
        if (this.iconPadding != i9) {
            this.iconPadding = i9;
            setCompoundDrawablePadding(i9);
        }
    }

    public void setIconResource(int i9) {
        setIcon(i9 != 0 ? C0694a.m3458b(getContext(), i9) : null);
    }

    public void setIconSize(int i9) {
        if (i9 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.iconSize != i9) {
            this.iconSize = i9;
            updateIcon();
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.iconTint != colorStateList) {
            this.iconTint = colorStateList;
            updateIcon();
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.iconTintMode != mode) {
            this.iconTintMode = mode;
            updateIcon();
        }
    }

    public void setIconTintResource(int i9) {
        setIconTint(C0694a.m3457a(getContext(), i9));
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(colorStateList);
        }
    }

    public void setRippleColorResource(int i9) {
        if (isUsingOriginalBackground()) {
            setRippleColor(C0694a.m3457a(getContext(), i9));
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(colorStateList);
        }
    }

    public void setStrokeColorResource(int i9) {
        if (isUsingOriginalBackground()) {
            setStrokeColor(C0694a.m3457a(getContext(), i9));
        }
    }

    public void setStrokeWidth(int i9) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(i9);
        }
    }

    public void setStrokeWidthResource(int i9) {
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i9));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(colorStateList);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(mode);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.materialButtonStyle);
    }

    public MaterialButton(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.MaterialButton, i9, C3476R.style.Widget_MaterialComponents_Button, new int[0]);
        this.iconPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.MaterialButton_iconPadding, 0);
        this.iconTintMode = ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = MaterialResources.getColorStateList(getContext(), typedArrayObtainStyledAttributes, C3476R.styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(getContext(), typedArrayObtainStyledAttributes, C3476R.styleable.MaterialButton_icon);
        this.iconGravity = typedArrayObtainStyledAttributes.getInteger(C3476R.styleable.MaterialButton_iconGravity, 1);
        this.iconSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.MaterialButton_iconSize, 0);
        MaterialButtonHelper materialButtonHelper = new MaterialButtonHelper(this);
        this.materialButtonHelper = materialButtonHelper;
        materialButtonHelper.loadFromAttributes(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon();
    }
}
