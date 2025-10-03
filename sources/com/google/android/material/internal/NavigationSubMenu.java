package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.SubMenuC0148r;

/* loaded from: classes2.dex */
public class NavigationSubMenu extends SubMenuC0148r {
    public NavigationSubMenu(Context context, NavigationMenu navigationMenu, C0139i c0139i) {
        super(context, navigationMenu, c0139i);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public void onItemsChanged(boolean z8) {
        super.onItemsChanged(z8);
        ((C0137g) getParentMenu()).onItemsChanged(z8);
    }
}
