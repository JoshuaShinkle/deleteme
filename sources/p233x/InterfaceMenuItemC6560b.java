package p233x;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.MenuItem;
import android.view.View;
import p042d0.AbstractC4615b;

/* renamed from: x.b */
/* loaded from: classes.dex */
public interface InterfaceMenuItemC6560b extends MenuItem {
    /* renamed from: a */
    InterfaceMenuItemC6560b mo468a(AbstractC4615b abstractC4615b);

    /* renamed from: b */
    AbstractC4615b mo469b();

    @Override // android.view.MenuItem
    boolean collapseActionView();

    @Override // android.view.MenuItem
    boolean expandActionView();

    @Override // android.view.MenuItem
    View getActionView();

    @Override // android.view.MenuItem
    int getAlphabeticModifiers();

    @Override // android.view.MenuItem
    CharSequence getContentDescription();

    @Override // android.view.MenuItem
    ColorStateList getIconTintList();

    @Override // android.view.MenuItem
    PorterDuff.Mode getIconTintMode();

    @Override // android.view.MenuItem
    int getNumericModifiers();

    @Override // android.view.MenuItem
    CharSequence getTooltipText();

    @Override // android.view.MenuItem
    boolean isActionViewExpanded();

    @Override // android.view.MenuItem
    MenuItem setActionView(int i9);

    @Override // android.view.MenuItem
    MenuItem setActionView(View view);

    @Override // android.view.MenuItem
    MenuItem setAlphabeticShortcut(char c9, int i9);

    @Override // android.view.MenuItem
    InterfaceMenuItemC6560b setContentDescription(CharSequence charSequence);

    @Override // android.view.MenuItem
    MenuItem setIconTintList(ColorStateList colorStateList);

    @Override // android.view.MenuItem
    MenuItem setIconTintMode(PorterDuff.Mode mode);

    @Override // android.view.MenuItem
    MenuItem setNumericShortcut(char c9, int i9);

    @Override // android.view.MenuItem
    MenuItem setShortcut(char c9, char c10, int i9, int i10);

    @Override // android.view.MenuItem
    void setShowAsAction(int i9);

    @Override // android.view.MenuItem
    MenuItem setShowAsActionFlags(int i9);

    @Override // android.view.MenuItem
    InterfaceMenuItemC6560b setTooltipText(CharSequence charSequence);
}
