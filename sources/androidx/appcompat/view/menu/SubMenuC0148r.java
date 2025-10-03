package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.C0137g;

/* renamed from: androidx.appcompat.view.menu.r */
/* loaded from: classes.dex */
public class SubMenuC0148r extends C0137g implements SubMenu {
    private C0139i mItem;
    private C0137g mParentMenu;

    public SubMenuC0148r(Context context, C0137g c0137g, C0139i c0139i) {
        super(context);
        this.mParentMenu = c0137g;
        this.mItem = c0139i;
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean collapseItemActionView(C0139i c0139i) {
        return this.mParentMenu.collapseItemActionView(c0139i);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean dispatchMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
        return super.dispatchMenuItemSelected(c0137g, menuItem) || this.mParentMenu.dispatchMenuItemSelected(c0137g, menuItem);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean expandItemActionView(C0139i c0139i) {
        return this.mParentMenu.expandItemActionView(c0139i);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public String getActionViewStatesKey() {
        C0139i c0139i = this.mItem;
        int itemId = c0139i != null ? c0139i.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.getActionViewStatesKey() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public C0137g getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean isGroupDividerEnabled() {
        return this.mParentMenu.isGroupDividerEnabled();
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public void setCallback(C0137g.a aVar) {
        this.mParentMenu.setCallback(aVar);
    }

    @Override // androidx.appcompat.view.menu.C0137g, android.view.Menu
    public void setGroupDividerEnabled(boolean z8) {
        this.mParentMenu.setGroupDividerEnabled(z8);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.setHeaderIconInt(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.setHeaderTitleInt(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.setHeaderViewInt(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.mItem.setIcon(drawable);
        return this;
    }

    @Override // androidx.appcompat.view.menu.C0137g, android.view.Menu
    public void setQwertyMode(boolean z8) {
        this.mParentMenu.setQwertyMode(z8);
    }

    @Override // androidx.appcompat.view.menu.C0137g
    public void setShortcutsVisible(boolean z8) {
        this.mParentMenu.setShortcutsVisible(z8);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i9) {
        return (SubMenu) super.setHeaderIconInt(i9);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i9) {
        return (SubMenu) super.setHeaderTitleInt(i9);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i9) {
        this.mItem.setIcon(i9);
        return this;
    }
}
