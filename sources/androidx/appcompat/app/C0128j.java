package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.app.AbstractC0119a;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.C0236j0;
import androidx.appcompat.widget.InterfaceC0251r;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import p010b.C0560a;
import p010b.C0565f;
import p010b.C0569j;
import p042d0.C4620d0;
import p042d0.C4624f0;
import p042d0.C4647u;
import p042d0.InterfaceC4622e0;
import p042d0.InterfaceC4626g0;
import p071g.AbstractC4796b;
import p071g.C4795a;
import p071g.C4801g;
import p071g.C4802h;

/* renamed from: androidx.appcompat.app.j */
/* loaded from: classes.dex */
public class C0128j extends AbstractC0119a implements ActionBarOverlayLayout.InterfaceC0154d {

    /* renamed from: E */
    public static final Interpolator f402E = new AccelerateInterpolator();

    /* renamed from: F */
    public static final Interpolator f403F = new DecelerateInterpolator();

    /* renamed from: A */
    public boolean f404A;

    /* renamed from: a */
    public Context f408a;

    /* renamed from: b */
    public Context f409b;

    /* renamed from: c */
    public Activity f410c;

    /* renamed from: d */
    public ActionBarOverlayLayout f411d;

    /* renamed from: e */
    public ActionBarContainer f412e;

    /* renamed from: f */
    public InterfaceC0251r f413f;

    /* renamed from: g */
    public ActionBarContextView f414g;

    /* renamed from: h */
    public View f415h;

    /* renamed from: i */
    public C0236j0 f416i;

    /* renamed from: l */
    public boolean f419l;

    /* renamed from: m */
    public d f420m;

    /* renamed from: n */
    public AbstractC4796b f421n;

    /* renamed from: o */
    public AbstractC4796b.a f422o;

    /* renamed from: p */
    public boolean f423p;

    /* renamed from: r */
    public boolean f425r;

    /* renamed from: u */
    public boolean f428u;

    /* renamed from: v */
    public boolean f429v;

    /* renamed from: w */
    public boolean f430w;

    /* renamed from: y */
    public C4802h f432y;

    /* renamed from: z */
    public boolean f433z;

    /* renamed from: j */
    public ArrayList<Object> f417j = new ArrayList<>();

    /* renamed from: k */
    public int f418k = -1;

    /* renamed from: q */
    public ArrayList<AbstractC0119a.b> f424q = new ArrayList<>();

    /* renamed from: s */
    public int f426s = 0;

    /* renamed from: t */
    public boolean f427t = true;

    /* renamed from: x */
    public boolean f431x = true;

    /* renamed from: B */
    public final InterfaceC4622e0 f405B = new a();

    /* renamed from: C */
    public final InterfaceC4622e0 f406C = new b();

    /* renamed from: D */
    public final InterfaceC4626g0 f407D = new c();

    /* renamed from: androidx.appcompat.app.j$a */
    public class a extends C4624f0 {
        public a() {
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            View view2;
            C0128j c0128j = C0128j.this;
            if (c0128j.f427t && (view2 = c0128j.f415h) != null) {
                view2.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                C0128j.this.f412e.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            }
            C0128j.this.f412e.setVisibility(8);
            C0128j.this.f412e.setTransitioning(false);
            C0128j c0128j2 = C0128j.this;
            c0128j2.f432y = null;
            c0128j2.m433w();
            ActionBarOverlayLayout actionBarOverlayLayout = C0128j.this.f411d;
            if (actionBarOverlayLayout != null) {
                C4647u.m18527W(actionBarOverlayLayout);
            }
        }
    }

    /* renamed from: androidx.appcompat.app.j$b */
    public class b extends C4624f0 {
        public b() {
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            C0128j c0128j = C0128j.this;
            c0128j.f432y = null;
            c0128j.f412e.requestLayout();
        }
    }

    /* renamed from: androidx.appcompat.app.j$c */
    public class c implements InterfaceC4626g0 {
        public c() {
        }

        @Override // p042d0.InterfaceC4626g0
        /* renamed from: a */
        public void mo437a(View view) {
            ((View) C0128j.this.f412e.getParent()).invalidate();
        }
    }

    /* renamed from: androidx.appcompat.app.j$d */
    public class d extends AbstractC4796b implements C0137g.a {

        /* renamed from: d */
        public final Context f437d;

        /* renamed from: e */
        public final C0137g f438e;

        /* renamed from: f */
        public AbstractC4796b.a f439f;

        /* renamed from: g */
        public WeakReference<View> f440g;

        public d(Context context, AbstractC4796b.a aVar) {
            this.f437d = context;
            this.f439f = aVar;
            C0137g defaultShowAsAction = new C0137g(context).setDefaultShowAsAction(1);
            this.f438e = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: a */
        public void mo438a() {
            C0128j c0128j = C0128j.this;
            if (c0128j.f420m != this) {
                return;
            }
            if (C0128j.m414v(c0128j.f428u, c0128j.f429v, false)) {
                this.f439f.mo354b(this);
            } else {
                C0128j c0128j2 = C0128j.this;
                c0128j2.f421n = this;
                c0128j2.f422o = this.f439f;
            }
            this.f439f = null;
            C0128j.this.m432u(false);
            C0128j.this.f414g.m580g();
            C0128j.this.f413f.mo1040p().sendAccessibilityEvent(32);
            C0128j c0128j3 = C0128j.this;
            c0128j3.f411d.setHideOnContentScrollEnabled(c0128j3.f404A);
            C0128j.this.f420m = null;
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: b */
        public View mo439b() {
            WeakReference<View> weakReference = this.f440g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: c */
        public Menu mo440c() {
            return this.f438e;
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: d */
        public MenuInflater mo441d() {
            return new C4801g(this.f437d);
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: e */
        public CharSequence mo442e() {
            return C0128j.this.f414g.getSubtitle();
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: g */
        public CharSequence mo443g() {
            return C0128j.this.f414g.getTitle();
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: i */
        public void mo444i() {
            if (C0128j.this.f420m != this) {
                return;
            }
            this.f438e.stopDispatchingItemsChanged();
            try {
                this.f439f.mo355c(this, this.f438e);
            } finally {
                this.f438e.startDispatchingItemsChanged();
            }
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: j */
        public boolean mo445j() {
            return C0128j.this.f414g.m583j();
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: k */
        public void mo446k(View view) {
            C0128j.this.f414g.setCustomView(view);
            this.f440g = new WeakReference<>(view);
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: l */
        public void mo447l(int i9) {
            mo448m(C0128j.this.f408a.getResources().getString(i9));
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: m */
        public void mo448m(CharSequence charSequence) {
            C0128j.this.f414g.setSubtitle(charSequence);
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: o */
        public void mo449o(int i9) {
            mo450p(C0128j.this.f408a.getResources().getString(i9));
        }

        @Override // androidx.appcompat.view.menu.C0137g.a
        public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
            AbstractC4796b.a aVar = this.f439f;
            if (aVar != null) {
                return aVar.mo353a(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.C0137g.a
        public void onMenuModeChange(C0137g c0137g) {
            if (this.f439f == null) {
                return;
            }
            mo444i();
            C0128j.this.f414g.m585l();
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: p */
        public void mo450p(CharSequence charSequence) {
            C0128j.this.f414g.setTitle(charSequence);
        }

        @Override // p071g.AbstractC4796b
        /* renamed from: q */
        public void mo451q(boolean z8) {
            super.mo451q(z8);
            C0128j.this.f414g.setTitleOptional(z8);
        }

        /* renamed from: r */
        public boolean m452r() {
            this.f438e.stopDispatchingItemsChanged();
            try {
                return this.f439f.mo356d(this, this.f438e);
            } finally {
                this.f438e.startDispatchingItemsChanged();
            }
        }
    }

    public C0128j(Activity activity, boolean z8) {
        this.f410c = activity;
        View decorView = activity.getWindow().getDecorView();
        m417C(decorView);
        if (z8) {
            return;
        }
        this.f415h = decorView.findViewById(R.id.content);
    }

    /* renamed from: v */
    public static boolean m414v(boolean z8, boolean z9, boolean z10) {
        if (z10) {
            return true;
        }
        return (z8 || z9) ? false : true;
    }

    /* renamed from: A */
    public int m415A() {
        return this.f413f.mo1037m();
    }

    /* renamed from: B */
    public final void m416B() {
        if (this.f430w) {
            this.f430w = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f411d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            m426L(false);
        }
    }

    /* renamed from: C */
    public final void m417C(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(C0565f.decor_content_parent);
        this.f411d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f413f = m436z(view.findViewById(C0565f.action_bar));
        this.f414g = (ActionBarContextView) view.findViewById(C0565f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(C0565f.action_bar_container);
        this.f412e = actionBarContainer;
        InterfaceC0251r interfaceC0251r = this.f413f;
        if (interfaceC0251r == null || this.f414g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f408a = interfaceC0251r.mo1042r();
        boolean z8 = (this.f413f.mo1043s() & 4) != 0;
        if (z8) {
            this.f419l = true;
        }
        C4795a c4795aM19032b = C4795a.m19032b(this.f408a);
        m423I(c4795aM19032b.m19033a() || z8);
        m421G(c4795aM19032b.m19038g());
        TypedArray typedArrayObtainStyledAttributes = this.f408a.obtainStyledAttributes(null, C0569j.ActionBar, C0560a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(C0569j.ActionBar_hideOnContentScroll, false)) {
            m422H(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0569j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            m420F(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* renamed from: D */
    public void m418D(boolean z8) {
        m419E(z8 ? 4 : 0, 4);
    }

    /* renamed from: E */
    public void m419E(int i9, int i10) {
        int iMo1043s = this.f413f.mo1043s();
        if ((i10 & 4) != 0) {
            this.f419l = true;
        }
        this.f413f.mo1035k((i9 & i10) | ((~i10) & iMo1043s));
    }

    /* renamed from: F */
    public void m420F(float f9) {
        C4647u.m18542f0(this.f412e, f9);
    }

    /* renamed from: G */
    public final void m421G(boolean z8) {
        this.f425r = z8;
        if (z8) {
            this.f412e.setTabContainer(null);
            this.f413f.mo1033i(this.f416i);
        } else {
            this.f413f.mo1033i(null);
            this.f412e.setTabContainer(this.f416i);
        }
        boolean z9 = m415A() == 2;
        C0236j0 c0236j0 = this.f416i;
        if (c0236j0 != null) {
            if (z9) {
                c0236j0.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f411d;
                if (actionBarOverlayLayout != null) {
                    C4647u.m18527W(actionBarOverlayLayout);
                }
            } else {
                c0236j0.setVisibility(8);
            }
        }
        this.f413f.mo1046v(!this.f425r && z9);
        this.f411d.setHasNonEmbeddedTabs(!this.f425r && z9);
    }

    /* renamed from: H */
    public void m422H(boolean z8) {
        if (z8 && !this.f411d.m603r()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.f404A = z8;
        this.f411d.setHideOnContentScrollEnabled(z8);
    }

    /* renamed from: I */
    public void m423I(boolean z8) {
        this.f413f.mo1041q(z8);
    }

    /* renamed from: J */
    public final boolean m424J() {
        return C4647u.m18513I(this.f412e);
    }

    /* renamed from: K */
    public final void m425K() {
        if (this.f430w) {
            return;
        }
        this.f430w = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f411d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        m426L(false);
    }

    /* renamed from: L */
    public final void m426L(boolean z8) {
        if (m414v(this.f428u, this.f429v, this.f430w)) {
            if (this.f431x) {
                return;
            }
            this.f431x = true;
            m435y(z8);
            return;
        }
        if (this.f431x) {
            this.f431x = false;
            m434x(z8);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    /* renamed from: a */
    public void mo427a() {
        if (this.f429v) {
            this.f429v = false;
            m426L(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    /* renamed from: b */
    public void mo428b() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    /* renamed from: c */
    public void mo429c(boolean z8) {
        this.f427t = z8;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    /* renamed from: d */
    public void mo430d() {
        if (this.f429v) {
            return;
        }
        this.f429v = true;
        m426L(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    /* renamed from: e */
    public void mo431e() {
        C4802h c4802h = this.f432y;
        if (c4802h != null) {
            c4802h.m19062a();
            this.f432y = null;
        }
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: g */
    public boolean mo369g() {
        InterfaceC0251r interfaceC0251r = this.f413f;
        if (interfaceC0251r == null || !interfaceC0251r.mo1034j()) {
            return false;
        }
        this.f413f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: h */
    public void mo370h(boolean z8) {
        if (z8 == this.f423p) {
            return;
        }
        this.f423p = z8;
        int size = this.f424q.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f424q.get(i9).onMenuVisibilityChanged(z8);
        }
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: i */
    public int mo371i() {
        return this.f413f.mo1043s();
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: j */
    public Context mo372j() {
        if (this.f409b == null) {
            TypedValue typedValue = new TypedValue();
            this.f408a.getTheme().resolveAttribute(C0560a.actionBarWidgetTheme, typedValue, true);
            int i9 = typedValue.resourceId;
            if (i9 != 0) {
                this.f409b = new ContextThemeWrapper(this.f408a, i9);
            } else {
                this.f409b = this.f408a;
            }
        }
        return this.f409b;
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: l */
    public void mo374l(Configuration configuration) {
        m421G(C4795a.m19032b(this.f408a).m19038g());
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: n */
    public boolean mo376n(int i9, KeyEvent keyEvent) {
        Menu menuMo440c;
        d dVar = this.f420m;
        if (dVar == null || (menuMo440c = dVar.mo440c()) == null) {
            return false;
        }
        menuMo440c.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuMo440c.performShortcut(i9, keyEvent, 0);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.InterfaceC0154d
    public void onWindowVisibilityChanged(int i9) {
        this.f426s = i9;
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: q */
    public void mo379q(boolean z8) {
        if (this.f419l) {
            return;
        }
        m418D(z8);
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: r */
    public void mo380r(boolean z8) {
        C4802h c4802h;
        this.f433z = z8;
        if (z8 || (c4802h = this.f432y) == null) {
            return;
        }
        c4802h.m19062a();
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: s */
    public void mo381s(CharSequence charSequence) {
        this.f413f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.AbstractC0119a
    /* renamed from: t */
    public AbstractC4796b mo382t(AbstractC4796b.a aVar) {
        d dVar = this.f420m;
        if (dVar != null) {
            dVar.mo438a();
        }
        this.f411d.setHideOnContentScrollEnabled(false);
        this.f414g.m584k();
        d dVar2 = new d(this.f414g.getContext(), aVar);
        if (!dVar2.m452r()) {
            return null;
        }
        this.f420m = dVar2;
        dVar2.mo444i();
        this.f414g.m581h(dVar2);
        m432u(true);
        this.f414g.sendAccessibilityEvent(32);
        return dVar2;
    }

    /* renamed from: u */
    public void m432u(boolean z8) {
        C4620d0 c4620d0Mo1038n;
        C4620d0 c4620d0Mo579f;
        if (z8) {
            m425K();
        } else {
            m416B();
        }
        if (!m424J()) {
            if (z8) {
                this.f413f.mo1039o(4);
                this.f414g.setVisibility(0);
                return;
            } else {
                this.f413f.mo1039o(0);
                this.f414g.setVisibility(8);
                return;
            }
        }
        if (z8) {
            c4620d0Mo579f = this.f413f.mo1038n(4, 100L);
            c4620d0Mo1038n = this.f414g.mo579f(0, 200L);
        } else {
            c4620d0Mo1038n = this.f413f.mo1038n(0, 200L);
            c4620d0Mo579f = this.f414g.mo579f(8, 100L);
        }
        C4802h c4802h = new C4802h();
        c4802h.m19065d(c4620d0Mo579f, c4620d0Mo1038n);
        c4802h.m19069h();
    }

    /* renamed from: w */
    public void m433w() {
        AbstractC4796b.a aVar = this.f422o;
        if (aVar != null) {
            aVar.mo354b(this.f421n);
            this.f421n = null;
            this.f422o = null;
        }
    }

    /* renamed from: x */
    public void m434x(boolean z8) {
        View view;
        C4802h c4802h = this.f432y;
        if (c4802h != null) {
            c4802h.m19062a();
        }
        if (this.f426s != 0 || (!this.f433z && !z8)) {
            this.f405B.mo350b(null);
            return;
        }
        this.f412e.setAlpha(1.0f);
        this.f412e.setTransitioning(true);
        C4802h c4802h2 = new C4802h();
        float f9 = -this.f412e.getHeight();
        if (z8) {
            this.f412e.getLocationInWindow(new int[]{0, 0});
            f9 -= r5[1];
        }
        C4620d0 c4620d0M18418k = C4647u.m18533b(this.f412e).m18418k(f9);
        c4620d0M18418k.m18416i(this.f407D);
        c4802h2.m19064c(c4620d0M18418k);
        if (this.f427t && (view = this.f415h) != null) {
            c4802h2.m19064c(C4647u.m18533b(view).m18418k(f9));
        }
        c4802h2.m19067f(f402E);
        c4802h2.m19066e(250L);
        c4802h2.m19068g(this.f405B);
        this.f432y = c4802h2;
        c4802h2.m19069h();
    }

    /* renamed from: y */
    public void m435y(boolean z8) {
        View view;
        View view2;
        C4802h c4802h = this.f432y;
        if (c4802h != null) {
            c4802h.m19062a();
        }
        this.f412e.setVisibility(0);
        if (this.f426s == 0 && (this.f433z || z8)) {
            this.f412e.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            float f9 = -this.f412e.getHeight();
            if (z8) {
                this.f412e.getLocationInWindow(new int[]{0, 0});
                f9 -= r5[1];
            }
            this.f412e.setTranslationY(f9);
            C4802h c4802h2 = new C4802h();
            C4620d0 c4620d0M18418k = C4647u.m18533b(this.f412e).m18418k(BitmapDescriptorFactory.HUE_RED);
            c4620d0M18418k.m18416i(this.f407D);
            c4802h2.m19064c(c4620d0M18418k);
            if (this.f427t && (view2 = this.f415h) != null) {
                view2.setTranslationY(f9);
                c4802h2.m19064c(C4647u.m18533b(this.f415h).m18418k(BitmapDescriptorFactory.HUE_RED));
            }
            c4802h2.m19067f(f403F);
            c4802h2.m19066e(250L);
            c4802h2.m19068g(this.f406C);
            this.f432y = c4802h2;
            c4802h2.m19069h();
        } else {
            this.f412e.setAlpha(1.0f);
            this.f412e.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            if (this.f427t && (view = this.f415h) != null) {
                view.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            }
            this.f406C.mo350b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f411d;
        if (actionBarOverlayLayout != null) {
            C4647u.m18527W(actionBarOverlayLayout);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: z */
    public final InterfaceC0251r m436z(View view) {
        if (view instanceof InterfaceC0251r) {
            return (InterfaceC0251r) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    public C0128j(Dialog dialog) {
        m417C(dialog.getWindow().getDecorView());
    }
}
