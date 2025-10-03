package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;

/* loaded from: classes2.dex */
public final class BottomNavigationMenu extends C0137g {
    public static final int MAX_ITEM_COUNT = 5;

    public BottomNavigationMenu(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public MenuItem addInternal(int i9, int i10, int i11, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        stopDispatchingItemsChanged();
        MenuItem menuItemAddInternal = super.addInternal(i9, i10, i11, charSequence);
        if (menuItemAddInternal instanceof C0139i) {
            ((C0139i) menuItemAddInternal).m540t(true);
        }
        startDispatchingItemsChanged();
        return menuItemAddInternal;
    }

    @Override // androidx.appcompat.view.menu.C0137g, android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
