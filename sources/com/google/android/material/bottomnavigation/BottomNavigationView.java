package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.widget.C0250q0;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C3476R;
import com.google.android.material.internal.ThemeEnforcement;
import p042d0.C4647u;
import p071g.C4801g;
import p197t.C6273a;

/* loaded from: classes2.dex */
public class BottomNavigationView extends FrameLayout {
    private static final int MENU_PRESENTER_ID = 1;
    private final C0137g menu;
    private MenuInflater menuInflater;
    private final BottomNavigationMenuView menuView;
    private final BottomNavigationPresenter presenter;
    private OnNavigationItemReselectedListener reselectedListener;
    private OnNavigationItemSelectedListener selectedListener;

    public interface OnNavigationItemReselectedListener {
        void onNavigationItemReselected(MenuItem menuItem);
    }

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        Bundle menuPresenterState;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void readFromParcel(Parcel parcel, ClassLoader classLoader) {
            this.menuPresenterState = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeBundle(this.menuPresenterState);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readFromParcel(parcel, classLoader);
        }
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    private void addCompatibilityTopDivider(Context context) {
        View view = new View(context);
        view.setBackgroundColor(C6273a.m24024c(context, C3476R.color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new C4801g(getContext());
        }
        return this.menuInflater;
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void inflateMenu(int i9) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i9, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.menuView.isItemHorizontalTranslationEnabled();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuPresenterState = bundle;
        this.menu.savePresenterStates(bundle);
        return savedState;
    }

    public void setItemBackground(Drawable drawable) {
        this.menuView.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i9) {
        this.menuView.setItemBackgroundRes(i9);
    }

    public void setItemHorizontalTranslationEnabled(boolean z8) {
        if (this.menuView.isItemHorizontalTranslationEnabled() != z8) {
            this.menuView.setItemHorizontalTranslationEnabled(z8);
            this.presenter.updateMenuView(false);
        }
    }

    public void setItemIconSize(int i9) {
        this.menuView.setItemIconSize(i9);
    }

    public void setItemIconSizeRes(int i9) {
        setItemIconSize(getResources().getDimensionPixelSize(i9));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.menuView.setIconTintList(colorStateList);
    }

    public void setItemTextAppearanceActive(int i9) {
        this.menuView.setItemTextAppearanceActive(i9);
    }

    public void setItemTextAppearanceInactive(int i9) {
        this.menuView.setItemTextAppearanceInactive(i9);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.menuView.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i9) {
        if (this.menuView.getLabelVisibilityMode() != i9) {
            this.menuView.setLabelVisibilityMode(i9);
            this.presenter.updateMenuView(false);
        }
    }

    public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener onNavigationItemReselectedListener) {
        this.reselectedListener = onNavigationItemReselectedListener;
    }

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.selectedListener = onNavigationItemSelectedListener;
    }

    public void setSelectedItemId(int i9) {
        MenuItem menuItemFindItem = this.menu.findItem(i9);
        if (menuItemFindItem == null || this.menu.performItemAction(menuItemFindItem, this.presenter, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.presenter = bottomNavigationPresenter;
        C0137g bottomNavigationMenu = new BottomNavigationMenu(context);
        this.menu = bottomNavigationMenu;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context);
        this.menuView = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.setBottomNavigationMenuView(bottomNavigationMenuView);
        bottomNavigationPresenter.setId(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        bottomNavigationMenu.addMenuPresenter(bottomNavigationPresenter);
        bottomNavigationPresenter.initForMenu(getContext(), bottomNavigationMenu);
        int[] iArr = C3476R.styleable.BottomNavigationView;
        int i10 = C3476R.style.Widget_Design_BottomNavigationView;
        int i11 = C3476R.styleable.BottomNavigationView_itemTextAppearanceInactive;
        int i12 = C3476R.styleable.BottomNavigationView_itemTextAppearanceActive;
        C0250q0 c0250q0ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, iArr, i9, i10, i11, i12);
        int i13 = C3476R.styleable.BottomNavigationView_itemIconTint;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i13)) {
            bottomNavigationMenuView.setIconTintList(c0250q0ObtainTintedStyledAttributes.m1007c(i13));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.createDefaultColorStateList(R.attr.textColorSecondary));
        }
        setItemIconSize(c0250q0ObtainTintedStyledAttributes.m1010f(C3476R.styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(C3476R.dimen.design_bottom_navigation_icon_size)));
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i11)) {
            setItemTextAppearanceInactive(c0250q0ObtainTintedStyledAttributes.m1018n(i11, 0));
        }
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i12)) {
            setItemTextAppearanceActive(c0250q0ObtainTintedStyledAttributes.m1018n(i12, 0));
        }
        int i14 = C3476R.styleable.BottomNavigationView_itemTextColor;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i14)) {
            setItemTextColor(c0250q0ObtainTintedStyledAttributes.m1007c(i14));
        }
        if (c0250q0ObtainTintedStyledAttributes.m1023s(C3476R.styleable.BottomNavigationView_elevation)) {
            C4647u.m18542f0(this, c0250q0ObtainTintedStyledAttributes.m1010f(r2, 0));
        }
        setLabelVisibilityMode(c0250q0ObtainTintedStyledAttributes.m1016l(C3476R.styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        bottomNavigationMenuView.setItemBackgroundRes(c0250q0ObtainTintedStyledAttributes.m1018n(C3476R.styleable.BottomNavigationView_itemBackground, 0));
        int i15 = C3476R.styleable.BottomNavigationView_menu;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i15)) {
            inflateMenu(c0250q0ObtainTintedStyledAttributes.m1018n(i15, 0));
        }
        c0250q0ObtainTintedStyledAttributes.m1024w();
        addView(bottomNavigationMenuView, layoutParams);
        bottomNavigationMenu.setCallback(new C0137g.a() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.1
            @Override // androidx.appcompat.view.menu.C0137g.a
            public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
                if (BottomNavigationView.this.reselectedListener == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                    return (BottomNavigationView.this.selectedListener == null || BottomNavigationView.this.selectedListener.onNavigationItemSelected(menuItem)) ? false : true;
                }
                BottomNavigationView.this.reselectedListener.onNavigationItemReselected(menuItem);
                return true;
            }

            @Override // androidx.appcompat.view.menu.C0137g.a
            public void onMenuModeChange(C0137g c0137g) {
            }
        });
    }
}
