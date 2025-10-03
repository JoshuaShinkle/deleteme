package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;

/* renamed from: androidx.appcompat.view.menu.m */
/* loaded from: classes.dex */
public interface InterfaceC0143m {

    /* renamed from: androidx.appcompat.view.menu.m$a */
    public interface a {
        /* renamed from: a */
        boolean mo352a(C0137g c0137g);

        void onCloseMenu(C0137g c0137g, boolean z8);
    }

    boolean collapseItemActionView(C0137g c0137g, C0139i c0139i);

    boolean expandItemActionView(C0137g c0137g, C0139i c0139i);

    boolean flagActionItems();

    int getId();

    void initForMenu(Context context, C0137g c0137g);

    void onCloseMenu(C0137g c0137g, boolean z8);

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r);

    void setCallback(a aVar);

    void updateMenuView(boolean z8);
}
