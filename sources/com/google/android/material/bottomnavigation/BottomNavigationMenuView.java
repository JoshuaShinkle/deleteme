package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.transition.C0513b;
import androidx.transition.C0534o;
import androidx.transition.C0536q;
import com.google.android.material.C3476R;
import com.google.android.material.internal.TextScale;
import p010b.C0560a;
import p020c.C0694a;
import p021c0.C0701g;
import p021c0.InterfaceC0699e;
import p042d0.C4647u;
import p092i0.C5039b;

/* loaded from: classes2.dex */
public class BottomNavigationMenuView extends ViewGroup implements InterfaceC0144n {
    private static final long ACTIVE_ANIMATION_DURATION_MS = 115;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private BottomNavigationItemView[] buttons;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private final int itemHeight;
    private boolean itemHorizontalTranslationEnabled;
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private final InterfaceC0699e<BottomNavigationItemView> itemPool;
    private int itemTextAppearanceActive;
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    private C0137g menu;
    private final View.OnClickListener onClickListener;
    private BottomNavigationPresenter presenter;
    private int selectedItemId;
    private int selectedItemPosition;
    private final C0536q set;
    private int[] tempChildWidths;

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemViewMo3465b = this.itemPool.mo3465b();
        return bottomNavigationItemViewMo3465b == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewMo3465b;
    }

    private boolean isShifting(int i9, int i10) {
        if (i9 == -1) {
            if (i10 > 3) {
                return true;
            }
        } else if (i9 == 0) {
            return true;
        }
        return false;
    }

    public void buildMenuView() {
        removeAllViews();
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                if (bottomNavigationItemView != null) {
                    this.itemPool.mo3464a(bottomNavigationItemView);
                }
            }
        }
        if (this.menu.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        this.buttons = new BottomNavigationItemView[this.menu.size()];
        boolean zIsShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i9 = 0; i9 < this.menu.size(); i9++) {
            this.presenter.setUpdateSuspended(true);
            this.menu.getItem(i9).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            BottomNavigationItemView newItem = getNewItem();
            this.buttons[i9] = newItem;
            newItem.setIconTintList(this.itemIconTint);
            newItem.setIconSize(this.itemIconSize);
            newItem.setTextColor(this.itemTextColorDefault);
            newItem.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            newItem.setTextAppearanceActive(this.itemTextAppearanceActive);
            newItem.setTextColor(this.itemTextColorFromUser);
            Drawable drawable = this.itemBackground;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.itemBackgroundRes);
            }
            newItem.setShifting(zIsShifting);
            newItem.setLabelVisibilityMode(this.labelVisibilityMode);
            newItem.initialize((C0139i) this.menu.getItem(i9), 0);
            newItem.setItemPosition(i9);
            newItem.setOnClickListener(this.onClickListener);
            addView(newItem);
        }
        int iMin = Math.min(this.menu.size() - 1, this.selectedItemPosition);
        this.selectedItemPosition = iMin;
        this.menu.getItem(iMin).setChecked(true);
    }

    public ColorStateList createDefaultColorStateList(int i9) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i9, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListM3457a = C0694a.m3457a(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0560a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i10 = typedValue.data;
        int defaultColor = colorStateListM3457a.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListM3457a.getColorForState(iArr, defaultColor), i10, defaultColor});
    }

    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public Drawable getItemBackground() {
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        return (bottomNavigationItemViewArr == null || bottomNavigationItemViewArr.length <= 0) ? this.itemBackground : bottomNavigationItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n
    public void initialize(C0137g c0137g) {
        this.menu = c0137g;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int childCount = getChildCount();
        int i13 = i11 - i9;
        int i14 = i12 - i10;
        int measuredWidth = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                if (C4647u.m18567s(this) == 1) {
                    int i16 = i13 - measuredWidth;
                    childAt.layout(i16 - childAt.getMeasuredWidth(), 0, i16, i14);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i14);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int size = View.MeasureSpec.getSize(i9);
        int size2 = this.menu.getVisibleItems().size();
        int childCount = getChildCount();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.itemHeight, 1073741824);
        if (isShifting(this.labelVisibilityMode, size2) && this.itemHorizontalTranslationEnabled) {
            View childAt = getChildAt(this.selectedItemPosition);
            int iMax = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i11 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.inactiveItemMinWidth * i11), Math.min(iMax, this.activeItemMaxWidth));
            int i12 = size - iMin;
            int iMin2 = Math.min(i12 / (i11 != 0 ? i11 : 1), this.inactiveItemMaxWidth);
            int i13 = i12 - (i11 * iMin2);
            int i14 = 0;
            while (i14 < childCount) {
                if (getChildAt(i14).getVisibility() != 8) {
                    int[] iArr = this.tempChildWidths;
                    int i15 = i14 == this.selectedItemPosition ? iMin : iMin2;
                    iArr[i14] = i15;
                    if (i13 > 0) {
                        iArr[i14] = i15 + 1;
                        i13--;
                    }
                } else {
                    this.tempChildWidths[i14] = 0;
                }
                i14++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 != 0 ? size2 : 1), this.activeItemMaxWidth);
            int i16 = size - (size2 * iMin3);
            for (int i17 = 0; i17 < childCount; i17++) {
                if (getChildAt(i17).getVisibility() != 8) {
                    int[] iArr2 = this.tempChildWidths;
                    iArr2[i17] = iMin3;
                    if (i16 > 0) {
                        iArr2[i17] = iMin3 + 1;
                        i16--;
                    }
                } else {
                    this.tempChildWidths[i17] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths[i18], 1073741824), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), 0), View.resolveSizeAndState(this.itemHeight, iMakeMeasureSpec, 0));
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.itemIconTint = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.itemBackground = drawable;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i9) {
        this.itemBackgroundRes = i9;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(i9);
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z8) {
        this.itemHorizontalTranslationEnabled = z8;
    }

    public void setItemIconSize(int i9) {
        this.itemIconSize = i9;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconSize(i9);
            }
        }
    }

    public void setItemTextAppearanceActive(int i9) {
        this.itemTextAppearanceActive = i9;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceActive(i9);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i9) {
        this.itemTextAppearanceInactive = i9;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceInactive(i9);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.itemTextColorFromUser = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i9) {
        this.labelVisibilityMode = i9;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.presenter = bottomNavigationPresenter;
    }

    public void tryRestoreSelectedItemId(int i9) {
        int size = this.menu.size();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItem item = this.menu.getItem(i10);
            if (i9 == item.getItemId()) {
                this.selectedItemId = i9;
                this.selectedItemPosition = i10;
                item.setChecked(true);
                return;
            }
        }
    }

    public void updateMenuView() {
        C0137g c0137g = this.menu;
        if (c0137g == null || this.buttons == null) {
            return;
        }
        int size = c0137g.size();
        if (size != this.buttons.length) {
            buildMenuView();
            return;
        }
        int i9 = this.selectedItemId;
        for (int i10 = 0; i10 < size; i10++) {
            MenuItem item = this.menu.getItem(i10);
            if (item.isChecked()) {
                this.selectedItemId = item.getItemId();
                this.selectedItemPosition = i10;
            }
        }
        if (i9 != this.selectedItemId) {
            C0534o.m3091a(this, this.set);
        }
        boolean zIsShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i11 = 0; i11 < size; i11++) {
            this.presenter.setUpdateSuspended(true);
            this.buttons[i11].setLabelVisibilityMode(this.labelVisibilityMode);
            this.buttons[i11].setShifting(zIsShifting);
            this.buttons[i11].initialize((C0139i) this.menu.getItem(i11), 0);
            this.presenter.setUpdateSuspended(false);
        }
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.itemPool = new C0701g(5);
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_active_item_min_width);
        this.itemHeight = resources.getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_height);
        this.itemTextColorDefault = createDefaultColorStateList(R.attr.textColorSecondary);
        C0513b c0513b = new C0513b();
        this.set = c0513b;
        c0513b.m3111q(0);
        c0513b.setDuration(ACTIVE_ANIMATION_DURATION_MS);
        c0513b.setInterpolator(new C5039b());
        c0513b.m3101g(new TextScale());
        this.onClickListener = new View.OnClickListener() { // from class: com.google.android.material.bottomnavigation.BottomNavigationMenuView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                C0139i itemData = ((BottomNavigationItemView) view).getItemData();
                if (BottomNavigationMenuView.this.menu.performItemAction(itemData, BottomNavigationMenuView.this.presenter, 0)) {
                    return;
                }
                itemData.setChecked(true);
            }
        };
        this.tempChildWidths = new int[5];
    }
}
