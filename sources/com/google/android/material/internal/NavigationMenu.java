package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;

/* loaded from: classes2.dex */
public class NavigationMenu extends C0137g {
    public NavigationMenu(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.view.menu.C0137g, android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, CharSequence charSequence) {
        C0139i c0139i = (C0139i) addInternal(i9, i10, i11, charSequence);
        NavigationSubMenu navigationSubMenu = new NavigationSubMenu(getContext(), this, c0139i);
        c0139i.m544x(navigationSubMenu);
        return navigationSubMenu;
    }
}
