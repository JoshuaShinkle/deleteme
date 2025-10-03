package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class NavigationMenuView extends RecyclerView implements InterfaceC0144n {
    public NavigationMenuView(Context context) {
        this(context, null);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n
    public void initialize(C0137g c0137g) {
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        setLayoutManager(new LinearLayoutManager(context, 1, false));
    }
}
