package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p042d0.AbstractC4615b;
import p071g.InterfaceC4797c;
import p233x.InterfaceMenuItemC6560b;

/* renamed from: androidx.appcompat.view.menu.j */
/* loaded from: classes.dex */
public class MenuItemC0140j extends AbstractC0133c implements MenuItem {

    /* renamed from: d */
    public final InterfaceMenuItemC6560b f598d;

    /* renamed from: e */
    public Method f599e;

    /* renamed from: androidx.appcompat.view.menu.j$a */
    public class a extends AbstractC4615b {

        /* renamed from: d */
        public final ActionProvider f600d;

        public a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f600d = actionProvider;
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: a */
        public boolean mo548a() {
            return this.f600d.hasSubMenu();
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: c */
        public View mo549c() {
            return this.f600d.onCreateActionView();
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: e */
        public boolean mo550e() {
            return this.f600d.onPerformDefaultAction();
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: f */
        public void mo551f(SubMenu subMenu) {
            this.f600d.onPrepareSubMenu(MenuItemC0140j.this.m484d(subMenu));
        }
    }

    /* renamed from: androidx.appcompat.view.menu.j$b */
    public class b extends a implements ActionProvider.VisibilityListener {

        /* renamed from: f */
        public AbstractC4615b.b f602f;

        public b(Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: b */
        public boolean mo552b() {
            return this.f600d.isVisible();
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: d */
        public View mo553d(MenuItem menuItem) {
            return this.f600d.onCreateActionView(menuItem);
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: g */
        public boolean mo554g() {
            return this.f600d.overridesItemVisibility();
        }

        @Override // p042d0.AbstractC4615b
        /* renamed from: j */
        public void mo555j(AbstractC4615b.b bVar) {
            this.f602f = bVar;
            this.f600d.setVisibilityListener(bVar != null ? this : null);
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z8) {
            AbstractC4615b.b bVar = this.f602f;
            if (bVar != null) {
                bVar.onActionProviderVisibilityChanged(z8);
            }
        }
    }

    /* renamed from: androidx.appcompat.view.menu.j$c */
    public static class c extends FrameLayout implements InterfaceC4797c {

        /* renamed from: b */
        public final CollapsibleActionView f604b;

        /* JADX WARN: Multi-variable type inference failed */
        public c(View view) {
            super(view.getContext());
            this.f604b = (CollapsibleActionView) view;
            addView(view);
        }

        /* renamed from: a */
        public View m556a() {
            return (View) this.f604b;
        }

        @Override // p071g.InterfaceC4797c
        public void onActionViewCollapsed() {
            this.f604b.onActionViewCollapsed();
        }

        @Override // p071g.InterfaceC4797c
        public void onActionViewExpanded() {
            this.f604b.onActionViewExpanded();
        }
    }

    /* renamed from: androidx.appcompat.view.menu.j$d */
    public class d implements MenuItem.OnActionExpandListener {

        /* renamed from: a */
        public final MenuItem.OnActionExpandListener f605a;

        public d(MenuItem.OnActionExpandListener onActionExpandListener) {
            this.f605a = onActionExpandListener;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f605a.onMenuItemActionCollapse(MenuItemC0140j.this.m483c(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f605a.onMenuItemActionExpand(MenuItemC0140j.this.m483c(menuItem));
        }
    }

    /* renamed from: androidx.appcompat.view.menu.j$e */
    public class e implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a */
        public final MenuItem.OnMenuItemClickListener f607a;

        public e(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.f607a = onMenuItemClickListener;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f607a.onMenuItemClick(MenuItemC0140j.this.m483c(menuItem));
        }
    }

    public MenuItemC0140j(Context context, InterfaceMenuItemC6560b interfaceMenuItemC6560b) {
        super(context);
        if (interfaceMenuItemC6560b == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f598d = interfaceMenuItemC6560b;
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.f598d.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.f598d.expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        AbstractC4615b abstractC4615bMo469b = this.f598d.mo469b();
        if (abstractC4615bMo469b instanceof a) {
            return ((a) abstractC4615bMo469b).f600d;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = this.f598d.getActionView();
        return actionView instanceof c ? ((c) actionView).m556a() : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f598d.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f598d.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f598d.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f598d.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f598d.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f598d.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f598d.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f598d.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f598d.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f598d.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.f598d.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f598d.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f598d.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return m484d(this.f598d.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f598d.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.f598d.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f598d.getTooltipText();
    }

    /* renamed from: h */
    public void m547h(boolean z8) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            if (this.f599e == null) {
                this.f599e = this.f598d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f599e.invoke(this.f598d, Boolean.valueOf(z8));
        } catch (Exception e9) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e9);
        }
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f598d.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f598d.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.f598d.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.f598d.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.f598d.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.f598d.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        b bVar = new b(this.f504a, actionProvider);
        InterfaceMenuItemC6560b interfaceMenuItemC6560b = this.f598d;
        if (actionProvider == null) {
            bVar = null;
        }
        interfaceMenuItemC6560b.mo468a(bVar);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new c(view);
        }
        this.f598d.setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9) {
        this.f598d.setAlphabeticShortcut(c9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z8) {
        this.f598d.setCheckable(z8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z8) {
        this.f598d.setChecked(z8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.f598d.setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z8) {
        this.f598d.setEnabled(z8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f598d.setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f598d.setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f598d.setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f598d.setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c9) {
        this.f598d.setNumericShortcut(c9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f598d.setOnActionExpandListener(onActionExpandListener != null ? new d(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f598d.setOnMenuItemClickListener(onMenuItemClickListener != null ? new e(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10) {
        this.f598d.setShortcut(c9, c10);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i9) {
        this.f598d.setShowAsAction(i9);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i9) {
        this.f598d.setShowAsActionFlags(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f598d.setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f598d.setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        this.f598d.setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z8) {
        return this.f598d.setVisible(z8);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9, int i9) {
        this.f598d.setAlphabeticShortcut(c9, i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i9) {
        this.f598d.setIcon(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c9, int i9) {
        this.f598d.setNumericShortcut(c9, i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10, int i9, int i10) {
        this.f598d.setShortcut(c9, c10, i9, i10);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i9) {
        this.f598d.setTitle(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i9) {
        this.f598d.setActionView(i9);
        View actionView = this.f598d.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.f598d.setActionView(new c(actionView));
        }
        return this;
    }
}
