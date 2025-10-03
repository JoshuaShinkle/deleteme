package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.widget.C0250q0;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C3476R;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.ThemeEnforcement;
import p010b.C0560a;
import p020c.C0694a;
import p042d0.C4628h0;
import p042d0.C4647u;
import p071g.C4801g;
import p197t.C6273a;

/* loaded from: classes2.dex */
public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private final NavigationMenuPresenter presenter;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private ColorStateList createDefaultColorStateList(int i9) {
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
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListM3457a.getColorForState(iArr, defaultColor), i10, defaultColor});
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new C4801g(getContext());
        }
        return this.menuInflater;
    }

    public void addHeaderView(View view) {
        this.presenter.addHeaderView(view);
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i9) {
        return this.presenter.getHeaderView(i9);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public View inflateHeaderView(int i9) {
        return this.presenter.inflateHeaderView(i9);
    }

    public void inflateMenu(int i9) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i9, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void onInsetsChanged(C4628h0 c4628h0) {
        this.presenter.dispatchApplyWindowInsets(c4628h0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i9);
        if (mode == Integer.MIN_VALUE) {
            i9 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i9), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i9 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i9, i10);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuState = bundle;
        this.menu.savePresenterStates(bundle);
        return savedState;
    }

    public void removeHeaderView(View view) {
        this.presenter.removeHeaderView(view);
    }

    public void setCheckedItem(int i9) {
        MenuItem menuItemFindItem = this.menu.findItem(i9);
        if (menuItemFindItem != null) {
            this.presenter.setCheckedItem((C0139i) menuItemFindItem);
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i9) {
        setItemBackground(C6273a.m24025d(getContext(), i9));
    }

    public void setItemHorizontalPadding(int i9) {
        this.presenter.setItemHorizontalPadding(i9);
    }

    public void setItemHorizontalPaddingResource(int i9) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i9));
    }

    public void setItemIconPadding(int i9) {
        this.presenter.setItemIconPadding(i9);
    }

    public void setItemIconPaddingResource(int i9) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i9));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    public void setItemTextAppearance(int i9) {
        this.presenter.setItemTextAppearance(i9);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.navigation.NavigationView.SavedState.1
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
        public Bundle menuState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeBundle(this.menuState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.navigationViewStyle);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i9) {
        ColorStateList colorStateListCreateDefaultColorStateList;
        int iM1018n;
        boolean z8;
        super(context, attributeSet, i9);
        NavigationMenuPresenter navigationMenuPresenter = new NavigationMenuPresenter();
        this.presenter = navigationMenuPresenter;
        NavigationMenu navigationMenu = new NavigationMenu(context);
        this.menu = navigationMenu;
        C0250q0 c0250q0ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, C3476R.styleable.NavigationView, i9, C3476R.style.Widget_Design_NavigationView, new int[0]);
        C4647u.m18534b0(this, c0250q0ObtainTintedStyledAttributes.m1011g(C3476R.styleable.NavigationView_android_background));
        if (c0250q0ObtainTintedStyledAttributes.m1023s(C3476R.styleable.NavigationView_elevation)) {
            C4647u.m18542f0(this, c0250q0ObtainTintedStyledAttributes.m1010f(r13, 0));
        }
        C4647u.m18544g0(this, c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.maxWidth = c0250q0ObtainTintedStyledAttributes.m1010f(C3476R.styleable.NavigationView_android_maxWidth, 0);
        int i10 = C3476R.styleable.NavigationView_itemIconTint;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i10)) {
            colorStateListCreateDefaultColorStateList = c0250q0ObtainTintedStyledAttributes.m1007c(i10);
        } else {
            colorStateListCreateDefaultColorStateList = createDefaultColorStateList(R.attr.textColorSecondary);
        }
        int i11 = C3476R.styleable.NavigationView_itemTextAppearance;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i11)) {
            iM1018n = c0250q0ObtainTintedStyledAttributes.m1018n(i11, 0);
            z8 = true;
        } else {
            iM1018n = 0;
            z8 = false;
        }
        int i12 = C3476R.styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListM1007c = c0250q0ObtainTintedStyledAttributes.m1023s(i12) ? c0250q0ObtainTintedStyledAttributes.m1007c(i12) : null;
        if (!z8 && colorStateListM1007c == null) {
            colorStateListM1007c = createDefaultColorStateList(R.attr.textColorPrimary);
        }
        Drawable drawableM1011g = c0250q0ObtainTintedStyledAttributes.m1011g(C3476R.styleable.NavigationView_itemBackground);
        int i13 = C3476R.styleable.NavigationView_itemHorizontalPadding;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i13)) {
            navigationMenuPresenter.setItemHorizontalPadding(c0250q0ObtainTintedStyledAttributes.m1010f(i13, 0));
        }
        int iM1010f = c0250q0ObtainTintedStyledAttributes.m1010f(C3476R.styleable.NavigationView_itemIconPadding, 0);
        navigationMenu.setCallback(new C0137g.a() { // from class: com.google.android.material.navigation.NavigationView.1
            @Override // androidx.appcompat.view.menu.C0137g.a
            public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
                OnNavigationItemSelectedListener onNavigationItemSelectedListener = NavigationView.this.listener;
                return onNavigationItemSelectedListener != null && onNavigationItemSelectedListener.onNavigationItemSelected(menuItem);
            }

            @Override // androidx.appcompat.view.menu.C0137g.a
            public void onMenuModeChange(C0137g c0137g) {
            }
        });
        navigationMenuPresenter.setId(1);
        navigationMenuPresenter.initForMenu(context, navigationMenu);
        navigationMenuPresenter.setItemIconTintList(colorStateListCreateDefaultColorStateList);
        if (z8) {
            navigationMenuPresenter.setItemTextAppearance(iM1018n);
        }
        navigationMenuPresenter.setItemTextColor(colorStateListM1007c);
        navigationMenuPresenter.setItemBackground(drawableM1011g);
        navigationMenuPresenter.setItemIconPadding(iM1010f);
        navigationMenu.addMenuPresenter(navigationMenuPresenter);
        addView((View) navigationMenuPresenter.getMenuView(this));
        int i14 = C3476R.styleable.NavigationView_menu;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i14)) {
            inflateMenu(c0250q0ObtainTintedStyledAttributes.m1018n(i14, 0));
        }
        int i15 = C3476R.styleable.NavigationView_headerLayout;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i15)) {
            inflateHeaderView(c0250q0ObtainTintedStyledAttributes.m1018n(i15, 0));
        }
        c0250q0ObtainTintedStyledAttributes.m1024w();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.menu.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.presenter.setCheckedItem((C0139i) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
