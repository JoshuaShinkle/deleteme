package p071g;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
import p071g.AbstractC4796b;

/* renamed from: g.e */
/* loaded from: classes.dex */
public class C4799e extends AbstractC4796b implements C0137g.a {

    /* renamed from: d */
    public Context f16659d;

    /* renamed from: e */
    public ActionBarContextView f16660e;

    /* renamed from: f */
    public AbstractC4796b.a f16661f;

    /* renamed from: g */
    public WeakReference<View> f16662g;

    /* renamed from: h */
    public boolean f16663h;

    /* renamed from: i */
    public boolean f16664i;

    /* renamed from: j */
    public C0137g f16665j;

    public C4799e(Context context, ActionBarContextView actionBarContextView, AbstractC4796b.a aVar, boolean z8) {
        this.f16659d = context;
        this.f16660e = actionBarContextView;
        this.f16661f = aVar;
        C0137g defaultShowAsAction = new C0137g(actionBarContextView.getContext()).setDefaultShowAsAction(1);
        this.f16665j = defaultShowAsAction;
        defaultShowAsAction.setCallback(this);
        this.f16664i = z8;
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: a */
    public void mo438a() {
        if (this.f16663h) {
            return;
        }
        this.f16663h = true;
        this.f16660e.sendAccessibilityEvent(32);
        this.f16661f.mo354b(this);
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: b */
    public View mo439b() {
        WeakReference<View> weakReference = this.f16662g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: c */
    public Menu mo440c() {
        return this.f16665j;
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: d */
    public MenuInflater mo441d() {
        return new C4801g(this.f16660e.getContext());
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: e */
    public CharSequence mo442e() {
        return this.f16660e.getSubtitle();
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: g */
    public CharSequence mo443g() {
        return this.f16660e.getTitle();
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: i */
    public void mo444i() {
        this.f16661f.mo355c(this, this.f16665j);
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: j */
    public boolean mo445j() {
        return this.f16660e.m583j();
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: k */
    public void mo446k(View view) {
        this.f16660e.setCustomView(view);
        this.f16662g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: l */
    public void mo447l(int i9) {
        mo448m(this.f16659d.getString(i9));
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: m */
    public void mo448m(CharSequence charSequence) {
        this.f16660e.setSubtitle(charSequence);
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: o */
    public void mo449o(int i9) {
        mo450p(this.f16659d.getString(i9));
    }

    @Override // androidx.appcompat.view.menu.C0137g.a
    public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
        return this.f16661f.mo353a(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.C0137g.a
    public void onMenuModeChange(C0137g c0137g) {
        mo444i();
        this.f16660e.m585l();
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: p */
    public void mo450p(CharSequence charSequence) {
        this.f16660e.setTitle(charSequence);
    }

    @Override // p071g.AbstractC4796b
    /* renamed from: q */
    public void mo451q(boolean z8) {
        super.mo451q(z8);
        this.f16660e.setTitleOptional(z8);
    }
}
