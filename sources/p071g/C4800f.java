package p071g;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuC0145o;
import androidx.appcompat.view.menu.MenuItemC0140j;
import java.util.ArrayList;
import p071g.AbstractC4796b;
import p132m.C5308g;
import p233x.InterfaceMenuC6559a;
import p233x.InterfaceMenuItemC6560b;

/* renamed from: g.f */
/* loaded from: classes.dex */
public class C4800f extends ActionMode {

    /* renamed from: a */
    public final Context f16666a;

    /* renamed from: b */
    public final AbstractC4796b f16667b;

    /* renamed from: g.f$a */
    public static class a implements AbstractC4796b.a {

        /* renamed from: a */
        public final ActionMode.Callback f16668a;

        /* renamed from: b */
        public final Context f16669b;

        /* renamed from: c */
        public final ArrayList<C4800f> f16670c = new ArrayList<>();

        /* renamed from: d */
        public final C5308g<Menu, Menu> f16671d = new C5308g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f16669b = context;
            this.f16668a = callback;
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: a */
        public boolean mo353a(AbstractC4796b abstractC4796b, MenuItem menuItem) {
            return this.f16668a.onActionItemClicked(m19048e(abstractC4796b), new MenuItemC0140j(this.f16669b, (InterfaceMenuItemC6560b) menuItem));
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: b */
        public void mo354b(AbstractC4796b abstractC4796b) {
            this.f16668a.onDestroyActionMode(m19048e(abstractC4796b));
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: c */
        public boolean mo355c(AbstractC4796b abstractC4796b, Menu menu) {
            return this.f16668a.onPrepareActionMode(m19048e(abstractC4796b), m19049f(menu));
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: d */
        public boolean mo356d(AbstractC4796b abstractC4796b, Menu menu) {
            return this.f16668a.onCreateActionMode(m19048e(abstractC4796b), m19049f(menu));
        }

        /* renamed from: e */
        public ActionMode m19048e(AbstractC4796b abstractC4796b) {
            int size = this.f16670c.size();
            for (int i9 = 0; i9 < size; i9++) {
                C4800f c4800f = this.f16670c.get(i9);
                if (c4800f != null && c4800f.f16667b == abstractC4796b) {
                    return c4800f;
                }
            }
            C4800f c4800f2 = new C4800f(this.f16669b, abstractC4796b);
            this.f16670c.add(c4800f2);
            return c4800f2;
        }

        /* renamed from: f */
        public final Menu m19049f(Menu menu) {
            Menu menu2 = this.f16671d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            MenuC0145o menuC0145o = new MenuC0145o(this.f16669b, (InterfaceMenuC6559a) menu);
            this.f16671d.put(menu, menuC0145o);
            return menuC0145o;
        }
    }

    public C4800f(Context context, AbstractC4796b abstractC4796b) {
        this.f16666a = context;
        this.f16667b = abstractC4796b;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f16667b.mo438a();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f16667b.mo439b();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new MenuC0145o(this.f16666a, (InterfaceMenuC6559a) this.f16667b.mo440c());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f16667b.mo441d();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f16667b.mo442e();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f16667b.m19040f();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f16667b.mo443g();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f16667b.m19041h();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f16667b.mo444i();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f16667b.mo445j();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f16667b.mo446k(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f16667b.mo448m(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f16667b.m19042n(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f16667b.mo450p(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z8) {
        this.f16667b.mo451q(z8);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i9) {
        this.f16667b.mo447l(i9);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i9) {
        this.f16667b.mo449o(i9);
    }
}
