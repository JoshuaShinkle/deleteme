package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import p233x.InterfaceMenuC6559a;

/* renamed from: androidx.appcompat.view.menu.o */
/* loaded from: classes.dex */
public class MenuC0145o extends AbstractC0133c implements Menu {

    /* renamed from: d */
    public final InterfaceMenuC6559a f623d;

    public MenuC0145o(Context context, InterfaceMenuC6559a interfaceMenuC6559a) {
        super(context);
        if (interfaceMenuC6559a == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f623d = interfaceMenuC6559a;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return m483c(this.f623d.add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i9, int i10, int i11, ComponentName componentName, Intent[] intentArr, Intent intent, int i12, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = this.f623d.addIntentOptions(i9, i10, i11, componentName, intentArr, intent, i12, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i13 = 0; i13 < length; i13++) {
                menuItemArr[i13] = m483c(menuItemArr2[i13]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return m484d(this.f623d.addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        m485e();
        this.f623d.clear();
    }

    @Override // android.view.Menu
    public void close() {
        this.f623d.close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i9) {
        return m483c(this.f623d.findItem(i9));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i9) {
        return m483c(this.f623d.getItem(i9));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.f623d.hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i9, KeyEvent keyEvent) {
        return this.f623d.isShortcutKey(i9, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i9, int i10) {
        return this.f623d.performIdentifierAction(i9, i10);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i9, KeyEvent keyEvent, int i10) {
        return this.f623d.performShortcut(i9, keyEvent, i10);
    }

    @Override // android.view.Menu
    public void removeGroup(int i9) {
        m486f(i9);
        this.f623d.removeGroup(i9);
    }

    @Override // android.view.Menu
    public void removeItem(int i9) {
        m487g(i9);
        this.f623d.removeItem(i9);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i9, boolean z8, boolean z9) {
        this.f623d.setGroupCheckable(i9, z8, z9);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i9, boolean z8) {
        this.f623d.setGroupEnabled(i9, z8);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i9, boolean z8) {
        this.f623d.setGroupVisible(i9, z8);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z8) {
        this.f623d.setQwertyMode(z8);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f623d.size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i9) {
        return m483c(this.f623d.add(i9));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9) {
        return m484d(this.f623d.addSubMenu(i9));
    }

    @Override // android.view.Menu
    public MenuItem add(int i9, int i10, int i11, CharSequence charSequence) {
        return m483c(this.f623d.add(i9, i10, i11, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, CharSequence charSequence) {
        return m484d(this.f623d.addSubMenu(i9, i10, i11, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i9, int i10, int i11, int i12) {
        return m483c(this.f623d.add(i9, i10, i11, i12));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, int i12) {
        return m484d(this.f623d.addSubMenu(i9, i10, i11, i12));
    }
}
