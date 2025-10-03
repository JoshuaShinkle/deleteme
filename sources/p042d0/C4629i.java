package p042d0;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MenuItem;
import p233x.InterfaceMenuItemC6560b;

/* renamed from: d0.i */
/* loaded from: classes.dex */
public final class C4629i {
    /* renamed from: a */
    public static MenuItem m18462a(MenuItem menuItem, AbstractC4615b abstractC4615b) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            return ((InterfaceMenuItemC6560b) menuItem).mo468a(abstractC4615b);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    /* renamed from: b */
    public static void m18463b(MenuItem menuItem, char c9, int i9) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setAlphabeticShortcut(c9, i9);
        } else {
            menuItem.setAlphabeticShortcut(c9, i9);
        }
    }

    /* renamed from: c */
    public static void m18464c(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setContentDescription(charSequence);
        } else {
            menuItem.setContentDescription(charSequence);
        }
    }

    /* renamed from: d */
    public static void m18465d(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setIconTintList(colorStateList);
        } else {
            menuItem.setIconTintList(colorStateList);
        }
    }

    /* renamed from: e */
    public static void m18466e(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setIconTintMode(mode);
        } else {
            menuItem.setIconTintMode(mode);
        }
    }

    /* renamed from: f */
    public static void m18467f(MenuItem menuItem, char c9, int i9) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setNumericShortcut(c9, i9);
        } else {
            menuItem.setNumericShortcut(c9, i9);
        }
    }

    /* renamed from: g */
    public static void m18468g(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof InterfaceMenuItemC6560b) {
            ((InterfaceMenuItemC6560b) menuItem).setTooltipText(charSequence);
        } else {
            menuItem.setTooltipText(charSequence);
        }
    }
}
