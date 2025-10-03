package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.C0254s0;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.C0337l;
import com.google.android.material.C3476R;
import p010b.C0560a;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;
import p206u.C6348b;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements InterfaceC0144n.a {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private final C4613a accessibilityDelegate;
    private FrameLayout actionArea;
    boolean checkable;
    private Drawable emptyDrawable;
    private boolean hasIconTintList;
    private final int iconSize;
    private ColorStateList iconTintList;
    private C0139i itemData;
    private boolean needsEmptyIcon;
    private final CheckedTextView textView;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void adjustAppearance() {
        if (shouldExpandActionArea()) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout = this.actionArea;
            if (frameLayout != null) {
                LinearLayoutCompat.C0183a c0183a = (LinearLayoutCompat.C0183a) frameLayout.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) c0183a).width = -1;
                this.actionArea.setLayoutParams(c0183a);
                return;
            }
            return;
        }
        this.textView.setVisibility(0);
        FrameLayout frameLayout2 = this.actionArea;
        if (frameLayout2 != null) {
            LinearLayoutCompat.C0183a c0183a2 = (LinearLayoutCompat.C0183a) frameLayout2.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) c0183a2).width = -2;
            this.actionArea.setLayoutParams(c0183a2);
        }
    }

    private StateListDrawable createDefaultBackground() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(C0560a.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.actionArea == null) {
                this.actionArea = (FrameLayout) ((ViewStub) findViewById(C3476R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(view);
        }
    }

    private boolean shouldExpandActionArea() {
        return this.itemData.getTitle() == null && this.itemData.getIcon() == null && this.itemData.getActionView() != null;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public C0139i getItemData() {
        return this.itemData;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public void initialize(C0139i c0139i, int i9) {
        this.itemData = c0139i;
        setVisibility(c0139i.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            C4647u.m18534b0(this, createDefaultBackground());
        }
        setCheckable(c0139i.isCheckable());
        setChecked(c0139i.isChecked());
        setEnabled(c0139i.isEnabled());
        setTitle(c0139i.getTitle());
        setIcon(c0139i.getIcon());
        setActionView(c0139i.getActionView());
        setContentDescription(c0139i.getContentDescription());
        C0254s0.m1061a(this, c0139i.getTooltipText());
        adjustAppearance();
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

    public void recycle() {
        FrameLayout frameLayout = this.actionArea;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.textView.setCompoundDrawables(null, null, null, null);
    }

    public void setCheckable(boolean z8) {
        refreshDrawableState();
        if (this.checkable != z8) {
            this.checkable = z8;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        }
    }

    public void setChecked(boolean z8) {
        refreshDrawableState();
        this.textView.setChecked(z8);
    }

    public void setHorizontalPadding(int i9) {
        setPadding(i9, 0, i9, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.hasIconTintList) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = C6494a.m24849l(drawable).mutate();
                C6494a.m24846i(drawable, this.iconTintList);
            }
            int i9 = this.iconSize;
            drawable.setBounds(0, 0, i9, i9);
        } else if (this.needsEmptyIcon) {
            if (this.emptyDrawable == null) {
                Drawable drawableM24363a = C6348b.m24363a(getResources(), C3476R.drawable.navigation_empty_icon, getContext().getTheme());
                this.emptyDrawable = drawableM24363a;
                if (drawableM24363a != null) {
                    int i10 = this.iconSize;
                    drawableM24363a.setBounds(0, 0, i10, i10);
                }
            }
            drawable = this.emptyDrawable;
        }
        C0337l.m1615j(this.textView, drawable, null, null, null);
    }

    public void setIconPadding(int i9) {
        this.textView.setCompoundDrawablePadding(i9);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        this.hasIconTintList = colorStateList != null;
        C0139i c0139i = this.itemData;
        if (c0139i != null) {
            setIcon(c0139i.getIcon());
        }
    }

    public void setNeedsEmptyIcon(boolean z8) {
        this.needsEmptyIcon = z8;
    }

    public void setShortcut(boolean z8, char c9) {
    }

    public void setTextAppearance(int i9) {
        C0337l.m1620o(this.textView, i9);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.textView.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }

    public boolean showsIcon() {
        return true;
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        C4613a c4613a = new C4613a() { // from class: com.google.android.material.internal.NavigationMenuItemView.1
            @Override // p042d0.C4613a
            public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
                super.onInitializeAccessibilityNodeInfo(view, c4704m);
                c4704m.m18779S(NavigationMenuItemView.this.checkable);
            }
        };
        this.accessibilityDelegate = c4613a;
        setOrientation(0);
        LayoutInflater.from(context).inflate(C3476R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        this.iconSize = context.getResources().getDimensionPixelSize(C3476R.dimen.design_navigation_icon_size);
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(C3476R.id.design_menu_item_text);
        this.textView = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        C4647u.m18530Z(checkedTextView, c4613a);
    }
}
