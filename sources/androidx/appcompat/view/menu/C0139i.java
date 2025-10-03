package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.InterfaceC0144n;
import com.google.android.exoplayer2.C3322C;
import p010b.C0567h;
import p020c.C0694a;
import p042d0.AbstractC4615b;
import p224w.C6494a;
import p233x.InterfaceMenuItemC6560b;

/* renamed from: androidx.appcompat.view.menu.i */
/* loaded from: classes.dex */
public final class C0139i implements InterfaceMenuItemC6560b {

    /* renamed from: A */
    public View f566A;

    /* renamed from: B */
    public AbstractC4615b f567B;

    /* renamed from: C */
    public MenuItem.OnActionExpandListener f568C;

    /* renamed from: E */
    public ContextMenu.ContextMenuInfo f570E;

    /* renamed from: a */
    public final int f571a;

    /* renamed from: b */
    public final int f572b;

    /* renamed from: c */
    public final int f573c;

    /* renamed from: d */
    public final int f574d;

    /* renamed from: e */
    public CharSequence f575e;

    /* renamed from: f */
    public CharSequence f576f;

    /* renamed from: g */
    public Intent f577g;

    /* renamed from: h */
    public char f578h;

    /* renamed from: j */
    public char f580j;

    /* renamed from: l */
    public Drawable f582l;

    /* renamed from: n */
    public C0137g f584n;

    /* renamed from: o */
    public SubMenuC0148r f585o;

    /* renamed from: p */
    public Runnable f586p;

    /* renamed from: q */
    public MenuItem.OnMenuItemClickListener f587q;

    /* renamed from: r */
    public CharSequence f588r;

    /* renamed from: s */
    public CharSequence f589s;

    /* renamed from: z */
    public int f596z;

    /* renamed from: i */
    public int f579i = 4096;

    /* renamed from: k */
    public int f581k = 4096;

    /* renamed from: m */
    public int f583m = 0;

    /* renamed from: t */
    public ColorStateList f590t = null;

    /* renamed from: u */
    public PorterDuff.Mode f591u = null;

    /* renamed from: v */
    public boolean f592v = false;

    /* renamed from: w */
    public boolean f593w = false;

    /* renamed from: x */
    public boolean f594x = false;

    /* renamed from: y */
    public int f595y = 16;

    /* renamed from: D */
    public boolean f569D = false;

    /* renamed from: androidx.appcompat.view.menu.i$a */
    public class a implements AbstractC4615b.b {
        public a() {
        }

        @Override // p042d0.AbstractC4615b.b
        public void onActionProviderVisibilityChanged(boolean z8) {
            C0139i c0139i = C0139i.this;
            c0139i.f584n.onItemVisibleChanged(c0139i);
        }
    }

    public C0139i(C0137g c0137g, int i9, int i10, int i11, int i12, CharSequence charSequence, int i13) {
        this.f584n = c0137g;
        this.f571a = i10;
        this.f572b = i9;
        this.f573c = i11;
        this.f574d = i12;
        this.f575e = charSequence;
        this.f596z = i13;
    }

    /* renamed from: d */
    public static void m521d(StringBuilder sb, int i9, int i10, String str) {
        if ((i9 & i10) == i10) {
            sb.append(str);
        }
    }

    /* renamed from: A */
    public boolean m522A() {
        return this.f584n.isShortcutsVisible() && m527g() != 0;
    }

    /* renamed from: B */
    public boolean m523B() {
        return (this.f596z & 4) == 4;
    }

    @Override // p233x.InterfaceMenuItemC6560b
    /* renamed from: a */
    public InterfaceMenuItemC6560b mo468a(AbstractC4615b abstractC4615b) {
        AbstractC4615b abstractC4615b2 = this.f567B;
        if (abstractC4615b2 != null) {
            abstractC4615b2.m18393h();
        }
        this.f566A = null;
        this.f567B = abstractC4615b;
        this.f584n.onItemsChanged(true);
        AbstractC4615b abstractC4615b3 = this.f567B;
        if (abstractC4615b3 != null) {
            abstractC4615b3.mo555j(new a());
        }
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b
    /* renamed from: b */
    public AbstractC4615b mo469b() {
        return this.f567B;
    }

    /* renamed from: c */
    public void m524c() {
        this.f584n.onItemActionRequestChanged(this);
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f596z & 8) == 0) {
            return false;
        }
        if (this.f566A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f568C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f584n.collapseItemActionView(this);
        }
        return false;
    }

    /* renamed from: e */
    public final Drawable m525e(Drawable drawable) {
        if (drawable != null && this.f594x && (this.f592v || this.f593w)) {
            drawable = C6494a.m24849l(drawable).mutate();
            if (this.f592v) {
                C6494a.m24846i(drawable, this.f590t);
            }
            if (this.f593w) {
                C6494a.m24847j(drawable, this.f591u);
            }
            this.f594x = false;
        }
        return drawable;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean expandActionView() {
        if (!m530j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f568C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f584n.expandItemActionView(this);
        }
        return false;
    }

    /* renamed from: f */
    public int m526f() {
        return this.f574d;
    }

    /* renamed from: g */
    public char m527g() {
        return this.f584n.isQwertyMode() ? this.f580j : this.f578h;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public View getActionView() {
        View view = this.f566A;
        if (view != null) {
            return view;
        }
        AbstractC4615b abstractC4615b = this.f567B;
        if (abstractC4615b == null) {
            return null;
        }
        View viewMo553d = abstractC4615b.mo553d(this);
        this.f566A = viewMo553d;
        return viewMo553d;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f581k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f580j;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f588r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f572b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f582l;
        if (drawable != null) {
            return m525e(drawable);
        }
        if (this.f583m == 0) {
            return null;
        }
        Drawable drawableM3458b = C0694a.m3458b(this.f584n.getContext(), this.f583m);
        this.f583m = 0;
        this.f582l = drawableM3458b;
        return m525e(drawableM3458b);
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f590t;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f591u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f577g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f571a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f570E;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.f579i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f578h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f573c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f585o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f575e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f576f;
        return charSequence != null ? charSequence : this.f575e;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f589s;
    }

    /* renamed from: h */
    public String m528h() {
        char cM527g = m527g();
        if (cM527g == 0) {
            return "";
        }
        Resources resources = this.f584n.getContext().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f584n.getContext()).hasPermanentMenuKey()) {
            sb.append(resources.getString(C0567h.abc_prepend_shortcut_label));
        }
        int i9 = this.f584n.isQwertyMode() ? this.f581k : this.f579i;
        m521d(sb, i9, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, resources.getString(C0567h.abc_menu_meta_shortcut_label));
        m521d(sb, i9, 4096, resources.getString(C0567h.abc_menu_ctrl_shortcut_label));
        m521d(sb, i9, 2, resources.getString(C0567h.abc_menu_alt_shortcut_label));
        m521d(sb, i9, 1, resources.getString(C0567h.abc_menu_shift_shortcut_label));
        m521d(sb, i9, 4, resources.getString(C0567h.abc_menu_sym_shortcut_label));
        m521d(sb, i9, 8, resources.getString(C0567h.abc_menu_function_shortcut_label));
        if (cM527g == '\b') {
            sb.append(resources.getString(C0567h.abc_menu_delete_shortcut_label));
        } else if (cM527g == '\n') {
            sb.append(resources.getString(C0567h.abc_menu_enter_shortcut_label));
        } else if (cM527g != ' ') {
            sb.append(cM527g);
        } else {
            sb.append(resources.getString(C0567h.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f585o != null;
    }

    /* renamed from: i */
    public CharSequence m529i(InterfaceC0144n.a aVar) {
        return (aVar == null || !aVar.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f569D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f595y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f595y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f595y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        AbstractC4615b abstractC4615b = this.f567B;
        return (abstractC4615b == null || !abstractC4615b.mo554g()) ? (this.f595y & 8) == 0 : (this.f595y & 8) == 0 && this.f567B.mo552b();
    }

    /* renamed from: j */
    public boolean m530j() {
        AbstractC4615b abstractC4615b;
        if ((this.f596z & 8) == 0) {
            return false;
        }
        if (this.f566A == null && (abstractC4615b = this.f567B) != null) {
            this.f566A = abstractC4615b.mo553d(this);
        }
        return this.f566A != null;
    }

    /* renamed from: k */
    public boolean m531k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f587q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        C0137g c0137g = this.f584n;
        if (c0137g.dispatchMenuItemSelected(c0137g, this)) {
            return true;
        }
        Runnable runnable = this.f586p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f577g != null) {
            try {
                this.f584n.getContext().startActivity(this.f577g);
                return true;
            } catch (ActivityNotFoundException e9) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e9);
            }
        }
        AbstractC4615b abstractC4615b = this.f567B;
        return abstractC4615b != null && abstractC4615b.mo550e();
    }

    /* renamed from: l */
    public boolean m532l() {
        return (this.f595y & 32) == 32;
    }

    /* renamed from: m */
    public boolean m533m() {
        return (this.f595y & 4) != 0;
    }

    /* renamed from: n */
    public boolean m534n() {
        return (this.f596z & 1) == 1;
    }

    /* renamed from: o */
    public boolean m535o() {
        return (this.f596z & 2) == 2;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setActionView(int i9) {
        Context context = this.f584n.getContext();
        setActionView(LayoutInflater.from(context).inflate(i9, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setActionView(View view) {
        int i9;
        this.f566A = view;
        this.f567B = null;
        if (view != null && view.getId() == -1 && (i9 = this.f571a) > 0) {
            view.setId(i9);
        }
        this.f584n.onItemActionRequestChanged(this);
        return this;
    }

    /* renamed from: r */
    public void m538r(boolean z8) {
        this.f569D = z8;
        this.f584n.onItemsChanged(false);
    }

    /* renamed from: s */
    public void m539s(boolean z8) {
        int i9 = this.f595y;
        int i10 = (z8 ? 2 : 0) | (i9 & (-3));
        this.f595y = i10;
        if (i9 != i10) {
            this.f584n.onItemsChanged(false);
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9) {
        if (this.f580j == c9) {
            return this;
        }
        this.f580j = Character.toLowerCase(c9);
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z8) {
        int i9 = this.f595y;
        int i10 = (z8 ? 1 : 0) | (i9 & (-2));
        this.f595y = i10;
        if (i9 != i10) {
            this.f584n.onItemsChanged(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z8) {
        if ((this.f595y & 4) != 0) {
            this.f584n.setExclusiveItemChecked(this);
        } else {
            m539s(z8);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z8) {
        if (z8) {
            this.f595y |= 16;
        } else {
            this.f595y &= -17;
        }
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f583m = 0;
        this.f582l = drawable;
        this.f594x = true;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f590t = colorStateList;
        this.f592v = true;
        this.f594x = true;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f591u = mode;
        this.f593w = true;
        this.f594x = true;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f577g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c9) {
        if (this.f578h == c9) {
            return this;
        }
        this.f578h = c9;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f568C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f587q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10) {
        this.f578h = c9;
        this.f580j = Character.toLowerCase(c10);
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public void setShowAsAction(int i9) {
        int i10 = i9 & 3;
        if (i10 != 0 && i10 != 1 && i10 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f596z = i9;
        this.f584n.onItemActionRequestChanged(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f575e = charSequence;
        this.f584n.onItemsChanged(false);
        SubMenuC0148r subMenuC0148r = this.f585o;
        if (subMenuC0148r != null) {
            subMenuC0148r.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f576f = charSequence;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z8) {
        if (m545y(z8)) {
            this.f584n.onItemVisibleChanged(this);
        }
        return this;
    }

    /* renamed from: t */
    public void m540t(boolean z8) {
        this.f595y = (z8 ? 4 : 0) | (this.f595y & (-5));
    }

    public String toString() {
        CharSequence charSequence = this.f575e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    /* renamed from: u */
    public void m541u(boolean z8) {
        if (z8) {
            this.f595y |= 32;
        } else {
            this.f595y &= -33;
        }
    }

    /* renamed from: v */
    public void m542v(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f570E = contextMenuInfo;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC6560b setShowAsActionFlags(int i9) {
        setShowAsAction(i9);
        return this;
    }

    /* renamed from: x */
    public void m544x(SubMenuC0148r subMenuC0148r) {
        this.f585o = subMenuC0148r;
        subMenuC0148r.setHeaderTitle(getTitle());
    }

    /* renamed from: y */
    public boolean m545y(boolean z8) {
        int i9 = this.f595y;
        int i10 = (z8 ? 0 : 8) | (i9 & (-9));
        this.f595y = i10;
        return i9 != i10;
    }

    /* renamed from: z */
    public boolean m546z() {
        return this.f584n.getOptionalIconsVisible();
    }

    @Override // android.view.MenuItem
    public InterfaceMenuItemC6560b setContentDescription(CharSequence charSequence) {
        this.f588r = charSequence;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public InterfaceMenuItemC6560b setTooltipText(CharSequence charSequence) {
        this.f589s = charSequence;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c9, int i9) {
        if (this.f580j == c9 && this.f581k == i9) {
            return this;
        }
        this.f580j = Character.toLowerCase(c9);
        this.f581k = KeyEvent.normalizeMetaState(i9);
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c9, int i9) {
        if (this.f578h == c9 && this.f579i == i9) {
            return this;
        }
        this.f578h = c9;
        this.f579i = KeyEvent.normalizeMetaState(i9);
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // p233x.InterfaceMenuItemC6560b, android.view.MenuItem
    public MenuItem setShortcut(char c9, char c10, int i9, int i10) {
        this.f578h = c9;
        this.f579i = KeyEvent.normalizeMetaState(i9);
        this.f580j = Character.toLowerCase(c10);
        this.f581k = KeyEvent.normalizeMetaState(i10);
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i9) {
        this.f582l = null;
        this.f583m = i9;
        this.f594x = true;
        this.f584n.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i9) {
        return setTitle(this.f584n.getContext().getString(i9));
    }
}
