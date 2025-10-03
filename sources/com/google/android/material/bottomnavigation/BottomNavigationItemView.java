package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.C0254s0;
import androidx.core.widget.C0337l;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.material.C3476R;
import p042d0.C4645s;
import p042d0.C4647u;
import p197t.C6273a;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class BottomNavigationItemView extends FrameLayout implements InterfaceC0144n.a {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    public static final int INVALID_ITEM_POSITION = -1;
    private final int defaultMargin;
    private ImageView icon;
    private ColorStateList iconTint;
    private boolean isShifting;
    private C0139i itemData;
    private int itemPosition;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    private void calculateTextScaleFactors(float f9, float f10) {
        this.shiftAmount = f9 - f10;
        this.scaleUpFactor = (f10 * 1.0f) / f9;
        this.scaleDownFactor = (f9 * 1.0f) / f10;
    }

    private void setViewLayoutParams(View view, int i9, int i10) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i9;
        layoutParams.gravity = i10;
        view.setLayoutParams(layoutParams);
    }

    private void setViewValues(View view, float f9, float f10, int i9) {
        view.setScaleX(f9);
        view.setScaleY(f10);
        view.setVisibility(i9);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public C0139i getItemData() {
        return this.itemData;
    }

    public int getItemPosition() {
        return this.itemPosition;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public void initialize(C0139i c0139i, int i9) {
        this.itemData = c0139i;
        setCheckable(c0139i.isCheckable());
        setChecked(c0139i.isChecked());
        setEnabled(c0139i.isEnabled());
        setIcon(c0139i.getIcon());
        setTitle(c0139i.getTitle());
        setId(c0139i.getItemId());
        if (!TextUtils.isEmpty(c0139i.getContentDescription())) {
            setContentDescription(c0139i.getContentDescription());
        }
        C0254s0.m1061a(this, c0139i.getTooltipText());
        setVisibility(c0139i.isVisible() ? 0 : 8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i9) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i9 + 1);
        C0139i c0139i = this.itemData;
        if (c0139i != null && c0139i.isCheckable() && this.itemData.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z8) {
        refreshDrawableState();
    }

    public void setChecked(boolean z8) {
        this.largeLabel.setPivotX(r0.getWidth() / 2);
        this.largeLabel.setPivotY(r0.getBaseline());
        this.smallLabel.setPivotX(r0.getWidth() / 2);
        this.smallLabel.setPivotY(r0.getBaseline());
        int i9 = this.labelVisibilityMode;
        if (i9 != -1) {
            if (i9 == 0) {
                if (z8) {
                    setViewLayoutParams(this.icon, this.defaultMargin, 49);
                    setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                } else {
                    setViewLayoutParams(this.icon, this.defaultMargin, 17);
                    setViewValues(this.largeLabel, 0.5f, 0.5f, 4);
                }
                this.smallLabel.setVisibility(4);
            } else if (i9 != 1) {
                if (i9 == 2) {
                    setViewLayoutParams(this.icon, this.defaultMargin, 17);
                    this.largeLabel.setVisibility(8);
                    this.smallLabel.setVisibility(8);
                }
            } else if (z8) {
                setViewLayoutParams(this.icon, (int) (this.defaultMargin + this.shiftAmount), 49);
                setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                TextView textView = this.smallLabel;
                float f9 = this.scaleUpFactor;
                setViewValues(textView, f9, f9, 4);
            } else {
                setViewLayoutParams(this.icon, this.defaultMargin, 49);
                TextView textView2 = this.largeLabel;
                float f10 = this.scaleDownFactor;
                setViewValues(textView2, f10, f10, 4);
                setViewValues(this.smallLabel, 1.0f, 1.0f, 0);
            }
        } else if (this.isShifting) {
            if (z8) {
                setViewLayoutParams(this.icon, this.defaultMargin, 49);
                setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
            } else {
                setViewLayoutParams(this.icon, this.defaultMargin, 17);
                setViewValues(this.largeLabel, 0.5f, 0.5f, 4);
            }
            this.smallLabel.setVisibility(4);
        } else if (z8) {
            setViewLayoutParams(this.icon, (int) (this.defaultMargin + this.shiftAmount), 49);
            setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
            TextView textView3 = this.smallLabel;
            float f11 = this.scaleUpFactor;
            setViewValues(textView3, f11, f11, 4);
        } else {
            setViewLayoutParams(this.icon, this.defaultMargin, 49);
            TextView textView4 = this.largeLabel;
            float f12 = this.scaleDownFactor;
            setViewValues(textView4, f12, f12, 4);
            setViewValues(this.smallLabel, 1.0f, 1.0f, 0);
        }
        refreshDrawableState();
        setSelected(z8);
    }

    @Override // android.view.View
    public void setEnabled(boolean z8) {
        super.setEnabled(z8);
        this.smallLabel.setEnabled(z8);
        this.largeLabel.setEnabled(z8);
        this.icon.setEnabled(z8);
        if (z8) {
            C4647u.m18558n0(this, C4645s.m18502b(getContext(), CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE));
        } else {
            C4647u.m18558n0(this, null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = C6494a.m24849l(drawable).mutate();
            C6494a.m24846i(drawable, this.iconTint);
        }
        this.icon.setImageDrawable(drawable);
    }

    public void setIconSize(int i9) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.icon.getLayoutParams();
        layoutParams.width = i9;
        layoutParams.height = i9;
        this.icon.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.iconTint = colorStateList;
        C0139i c0139i = this.itemData;
        if (c0139i != null) {
            setIcon(c0139i.getIcon());
        }
    }

    public void setItemBackground(int i9) {
        setItemBackground(i9 == 0 ? null : C6273a.m24025d(getContext(), i9));
    }

    public void setItemPosition(int i9) {
        this.itemPosition = i9;
    }

    public void setLabelVisibilityMode(int i9) {
        if (this.labelVisibilityMode != i9) {
            this.labelVisibilityMode = i9;
            C0139i c0139i = this.itemData;
            if (c0139i != null) {
                setChecked(c0139i.isChecked());
            }
        }
    }

    public void setShifting(boolean z8) {
        if (this.isShifting != z8) {
            this.isShifting = z8;
            C0139i c0139i = this.itemData;
            if (c0139i != null) {
                setChecked(c0139i.isChecked());
            }
        }
    }

    public void setShortcut(boolean z8, char c9) {
    }

    public void setTextAppearanceActive(int i9) {
        C0337l.m1620o(this.largeLabel, i9);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceInactive(int i9) {
        C0337l.m1620o(this.smallLabel, i9);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.smallLabel.setTextColor(colorStateList);
            this.largeLabel.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.smallLabel.setText(charSequence);
        this.largeLabel.setText(charSequence);
        C0139i c0139i = this.itemData;
        if (c0139i == null || TextUtils.isEmpty(c0139i.getContentDescription())) {
            setContentDescription(charSequence);
        }
    }

    public boolean showsIcon() {
        return true;
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.itemPosition = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(C3476R.layout.design_bottom_navigation_item, (ViewGroup) this, true);
        setBackgroundResource(C3476R.drawable.design_bottom_navigation_item_background);
        this.defaultMargin = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_margin);
        this.icon = (ImageView) findViewById(C3476R.id.icon);
        TextView textView = (TextView) findViewById(C3476R.id.smallLabel);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(C3476R.id.largeLabel);
        this.largeLabel = textView2;
        C4647u.m18548i0(textView, 2);
        C4647u.m18548i0(textView2, 2);
        setFocusable(true);
        calculateTextScaleFactors(textView.getTextSize(), textView2.getTextSize());
    }

    public void setItemBackground(Drawable drawable) {
        C4647u.m18534b0(this, drawable);
    }
}
