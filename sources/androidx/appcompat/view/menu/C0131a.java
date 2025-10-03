package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import p042d0.AbstractC4615b;
import p197t.C6273a;
import p224w.C6494a;
import p233x.InterfaceMenuItemC6560b;

/* renamed from: androidx.appcompat.view.menu.a */
/* loaded from: classes.dex */
public class C0131a implements InterfaceMenuItemC6560b {

    /* renamed from: a */
    public final int f474a;

    /* renamed from: b */
    public final int f475b;

    /* renamed from: c */
    public final int f476c;

    /* renamed from: d */
    public CharSequence f477d;

    /* renamed from: e */
    public CharSequence f478e;

    /* renamed from: f */
    public Intent f479f;

    /* renamed from: g */
    public char f480g;

    /* renamed from: i */
    public char f482i;

    /* renamed from: k */
    public Drawable f484k;

    /* renamed from: l */
    public Context f485l;

    /* renamed from: m */
    public MenuItem.OnMenuItemClickListener f486m;

    /* renamed from: n */
    public CharSequence f487n;

    /* renamed from: o */
    public CharSequence f488o;

    /* renamed from: h */
    public int f481h = 4096;

    /* renamed from: j */
    public int f483j = 4096;

    /* renamed from: p */
    public ColorStateList f489p = null;

    /* renamed from: q */
    public PorterDuff.Mode f490q = null;

    /* renamed from: r */
    public boolean f491r = false;

    /* renamed from: s */
    public boolean f492s = false;

    /* renamed from: t */
    public int f493t = 16;

    public C0131a(Context context, int i9, int i10, int i11, int i12, CharSequence charSequence) {
        this.f485l = context;
        this.f474a = i10;
        this.f475b = i9;
        this.f476c = i12;
        this.f477d = charSequence;
    }

    @Override // p233x.InterfaceMenuItemC6560b
    /* renamed from: a */
    public InterfaceMenuItemC6560b mo468a(AbstractC4615b abstractC4615b) {
        throw new UnsupportedOperationException();
    }

    @Override // p233x.InterfaceMenuItemC6560b
    /* renamed from: b */
    public AbstractC4615b mo469b() {
        return null;
    }

    /* renamed from: c */
    public final void m470c() {
        Drawable drawable = this.f484k;
        if (drawable != null) {
            if (this.f491r || this.f492s) {
                Drawable drawableM24849l = C6494a.m24849l(drawable);
                this.f484k = drawableM24849l;
                Drawable drawableMutate = drawableM24849l.mutate();
                this.f484k = drawableMutate;
                if (this.f491r) {
                    C6494a.m24846i(drawableMutate, this.f489p);
                }
                if (this.f492s) {
                    C6494a.m24847j(this.f484k, this.f490q);
                }
            }
        }
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setActionView(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setShowAsActionFlags(int i9) {
        setShowAsAction(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f483j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f482i;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f487n;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f475b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f484k;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f489p;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f490q;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f479f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f474a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f481h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f480g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f476c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f477d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f478e;
        return charSequence != null ? charSequence : this.f477d;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f488o;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f493t & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f493t & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f493t & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f493t & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9) {
        this.f482i = Character.toLowerCase(c9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z8) {
        this.f493t = (z8 ? 1 : 0) | (this.f493t & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z8) {
        this.f493t = (z8 ? 2 : 0) | (this.f493t & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z8) {
        this.f493t = (z8 ? 16 : 0) | (this.f493t & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f484k = drawable;
        m470c();
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f489p = colorStateList;
        this.f491r = true;
        m470c();
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f490q = mode;
        this.f492s = true;
        m470c();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f479f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c9) {
        this.f480g = c9;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f486m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10) {
        this.f480g = c9;
        this.f482i = Character.toLowerCase(c10);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public void setShowAsAction(int i9) {
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f477d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f478e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z8) {
        this.f493t = (this.f493t & 8) | (z8 ? 0 : 8);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9, int i9) {
        this.f482i = Character.toLowerCase(c9);
        this.f483j = KeyEvent.normalizeMetaState(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public InterfaceMenuItemC6560b setContentDescription(CharSequence charSequence) {
        this.f487n = charSequence;
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c9, int i9) {
        this.f480g = c9;
        this.f481h = KeyEvent.normalizeMetaState(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i9) {
        this.f477d = this.f485l.getResources().getString(i9);
        return this;
    }

    @Override // android.view.MenuItem
    public InterfaceMenuItemC6560b setTooltipText(CharSequence charSequence) {
        this.f488o = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i9) {
        this.f484k = C6273a.m24025d(this.f485l, i9);
        m470c();
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10, int i9, int i10) {
        this.f480g = c9;
        this.f481h = KeyEvent.normalizeMetaState(i9);
        this.f482i = Character.toLowerCase(c10);
        this.f483j = KeyEvent.normalizeMetaState(i10);
        return this;
    }
}
