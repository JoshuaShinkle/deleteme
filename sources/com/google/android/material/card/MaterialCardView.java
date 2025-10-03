package com.google.android.material.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import com.google.android.material.C3476R;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes2.dex */
public class MaterialCardView extends CardView {
    private final MaterialCardViewHelper cardViewHelper;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f9) {
        super.setRadius(f9);
        this.cardViewHelper.updateForeground();
    }

    public void setStrokeColor(int i9) {
        this.cardViewHelper.setStrokeColor(i9);
    }

    public void setStrokeWidth(int i9) {
        this.cardViewHelper.setStrokeWidth(i9);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.MaterialCardView, i9, C3476R.style.Widget_MaterialComponents_CardView, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this);
        this.cardViewHelper = materialCardViewHelper;
        materialCardViewHelper.loadFromAttributes(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }
}
