package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import p132m.C5308g;
import p233x.InterfaceMenuItemC6560b;
import p233x.InterfaceSubMenuC6561c;

/* renamed from: androidx.appcompat.view.menu.c */
/* loaded from: classes.dex */
public abstract class AbstractC0133c {

    /* renamed from: a */
    public final Context f504a;

    /* renamed from: b */
    public C5308g<InterfaceMenuItemC6560b, MenuItem> f505b;

    /* renamed from: c */
    public C5308g<InterfaceSubMenuC6561c, SubMenu> f506c;

    public AbstractC0133c(Context context) {
        this.f504a = context;
    }

    /* renamed from: c */
    public final MenuItem m483c(MenuItem menuItem) {
        if (!(menuItem instanceof InterfaceMenuItemC6560b)) {
            return menuItem;
        }
        InterfaceMenuItemC6560b interfaceMenuItemC6560b = (InterfaceMenuItemC6560b) menuItem;
        if (this.f505b == null) {
            this.f505b = new C5308g<>();
        }
        MenuItem menuItem2 = this.f505b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemC0140j menuItemC0140j = new MenuItemC0140j(this.f504a, interfaceMenuItemC6560b);
        this.f505b.put(interfaceMenuItemC6560b, menuItemC0140j);
        return menuItemC0140j;
    }

    /* renamed from: d */
    public final SubMenu m484d(SubMenu subMenu) {
        if (!(subMenu instanceof InterfaceSubMenuC6561c)) {
            return subMenu;
        }
        InterfaceSubMenuC6561c interfaceSubMenuC6561c = (InterfaceSubMenuC6561c) subMenu;
        if (this.f506c == null) {
            this.f506c = new C5308g<>();
        }
        SubMenu subMenu2 = this.f506c.get(interfaceSubMenuC6561c);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenuC0149s subMenuC0149s = new SubMenuC0149s(this.f504a, interfaceSubMenuC6561c);
        this.f506c.put(interfaceSubMenuC6561c, subMenuC0149s);
        return subMenuC0149s;
    }

    /* renamed from: e */
    public final void m485e() {
        C5308g<InterfaceMenuItemC6560b, MenuItem> c5308g = this.f505b;
        if (c5308g != null) {
            c5308g.clear();
        }
        C5308g<InterfaceSubMenuC6561c, SubMenu> c5308g2 = this.f506c;
        if (c5308g2 != null) {
            c5308g2.clear();
        }
    }

    /* renamed from: f */
    public final void m486f(int i9) {
        if (this.f505b == null) {
            return;
        }
        int i10 = 0;
        while (i10 < this.f505b.size()) {
            if (this.f505b.m20751i(i10).getGroupId() == i9) {
                this.f505b.mo20753k(i10);
                i10--;
            }
            i10++;
        }
    }

    /* renamed from: g */
    public final void m487g(int i9) {
        if (this.f505b == null) {
            return;
        }
        for (int i10 = 0; i10 < this.f505b.size(); i10++) {
            if (this.f505b.m20751i(i10).getItemId() == i9) {
                this.f505b.mo20753k(i10);
                return;
            }
        }
    }
}
