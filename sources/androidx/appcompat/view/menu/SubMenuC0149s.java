package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import p233x.InterfaceSubMenuC6561c;

/* renamed from: androidx.appcompat.view.menu.s */
/* loaded from: classes.dex */
public class SubMenuC0149s extends MenuC0145o implements SubMenu {

    /* renamed from: e */
    public final InterfaceSubMenuC6561c f647e;

    public SubMenuC0149s(Context context, InterfaceSubMenuC6561c interfaceSubMenuC6561c) {
        super(context, interfaceSubMenuC6561c);
        this.f647e = interfaceSubMenuC6561c;
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        this.f647e.clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return m483c(this.f647e.getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i9) {
        this.f647e.setHeaderIcon(i9);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i9) {
        this.f647e.setHeaderTitle(i9);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        this.f647e.setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i9) {
        this.f647e.setIcon(i9);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        this.f647e.setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f647e.setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.f647e.setIcon(drawable);
        return this;
    }
}
