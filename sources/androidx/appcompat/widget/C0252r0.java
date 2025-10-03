package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.view.menu.C0131a;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p010b.C0560a;
import p010b.C0564e;
import p010b.C0565f;
import p010b.C0567h;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4620d0;
import p042d0.C4624f0;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.widget.r0 */
/* loaded from: classes.dex */
public class C0252r0 implements InterfaceC0251r {

    /* renamed from: a */
    public Toolbar f1195a;

    /* renamed from: b */
    public int f1196b;

    /* renamed from: c */
    public View f1197c;

    /* renamed from: d */
    public View f1198d;

    /* renamed from: e */
    public Drawable f1199e;

    /* renamed from: f */
    public Drawable f1200f;

    /* renamed from: g */
    public Drawable f1201g;

    /* renamed from: h */
    public boolean f1202h;

    /* renamed from: i */
    public CharSequence f1203i;

    /* renamed from: j */
    public CharSequence f1204j;

    /* renamed from: k */
    public CharSequence f1205k;

    /* renamed from: l */
    public Window.Callback f1206l;

    /* renamed from: m */
    public boolean f1207m;

    /* renamed from: n */
    public ActionMenuPresenter f1208n;

    /* renamed from: o */
    public int f1209o;

    /* renamed from: p */
    public int f1210p;

    /* renamed from: q */
    public Drawable f1211q;

    /* renamed from: androidx.appcompat.widget.r0$a */
    public class a implements View.OnClickListener {

        /* renamed from: b */
        public final C0131a f1212b;

        public a() {
            this.f1212b = new C0131a(C0252r0.this.f1195a.getContext(), 0, R.id.home, 0, 0, C0252r0.this.f1203i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0252r0 c0252r0 = C0252r0.this;
            Window.Callback callback = c0252r0.f1206l;
            if (callback == null || !c0252r0.f1207m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1212b);
        }
    }

    /* renamed from: androidx.appcompat.widget.r0$b */
    public class b extends C4624f0 {

        /* renamed from: a */
        public boolean f1214a = false;

        /* renamed from: b */
        public final /* synthetic */ int f1215b;

        public b(int i9) {
            this.f1215b = i9;
        }

        @Override // p042d0.C4624f0, p042d0.InterfaceC4622e0
        /* renamed from: a */
        public void mo787a(View view) {
            this.f1214a = true;
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            if (this.f1214a) {
                return;
            }
            C0252r0.this.f1195a.setVisibility(this.f1215b);
        }

        @Override // p042d0.C4624f0, p042d0.InterfaceC4622e0
        /* renamed from: c */
        public void mo351c(View view) {
            C0252r0.this.f1195a.setVisibility(0);
        }
    }

    public C0252r0(Toolbar toolbar, boolean z8) {
        this(toolbar, z8, C0567h.abc_action_bar_up_description, C0564e.abc_ic_ab_back_material);
    }

    /* renamed from: A */
    public void m1047A(int i9) {
        m1048B(i9 == 0 ? null : mo1042r().getString(i9));
    }

    /* renamed from: B */
    public void m1048B(CharSequence charSequence) {
        this.f1205k = charSequence;
        m1053G();
    }

    /* renamed from: C */
    public void m1049C(Drawable drawable) {
        this.f1201g = drawable;
        m1054H();
    }

    /* renamed from: D */
    public void m1050D(CharSequence charSequence) {
        this.f1204j = charSequence;
        if ((this.f1196b & 8) != 0) {
            this.f1195a.setSubtitle(charSequence);
        }
    }

    /* renamed from: E */
    public void m1051E(CharSequence charSequence) {
        this.f1202h = true;
        m1052F(charSequence);
    }

    /* renamed from: F */
    public final void m1052F(CharSequence charSequence) {
        this.f1203i = charSequence;
        if ((this.f1196b & 8) != 0) {
            this.f1195a.setTitle(charSequence);
        }
    }

    /* renamed from: G */
    public final void m1053G() {
        if ((this.f1196b & 4) != 0) {
            if (TextUtils.isEmpty(this.f1205k)) {
                this.f1195a.setNavigationContentDescription(this.f1210p);
            } else {
                this.f1195a.setNavigationContentDescription(this.f1205k);
            }
        }
    }

    /* renamed from: H */
    public final void m1054H() {
        if ((this.f1196b & 4) == 0) {
            this.f1195a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1195a;
        Drawable drawable = this.f1201g;
        if (drawable == null) {
            drawable = this.f1211q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    /* renamed from: I */
    public final void m1055I() {
        Drawable drawable;
        int i9 = this.f1196b;
        if ((i9 & 2) == 0) {
            drawable = null;
        } else if ((i9 & 1) == 0 || (drawable = this.f1200f) == null) {
            drawable = this.f1199e;
        }
        this.f1195a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: a */
    public void mo1025a(Menu menu, InterfaceC0143m.a aVar) {
        if (this.f1208n == null) {
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(this.f1195a.getContext());
            this.f1208n = actionMenuPresenter;
            actionMenuPresenter.m481i(C0565f.action_menu_presenter);
        }
        this.f1208n.setCallback(aVar);
        this.f1195a.setMenu((C0137g) menu, this.f1208n);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: b */
    public boolean mo1026b() {
        return this.f1195a.isOverflowMenuShowing();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: c */
    public void mo1027c() {
        this.f1207m = true;
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public void collapseActionView() {
        this.f1195a.collapseActionView();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: d */
    public boolean mo1028d() {
        return this.f1195a.canShowOverflowMenu();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: e */
    public boolean mo1029e() {
        return this.f1195a.isOverflowMenuShowPending();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: f */
    public boolean mo1030f() {
        return this.f1195a.hideOverflowMenu();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: g */
    public boolean mo1031g() {
        return this.f1195a.showOverflowMenu();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public CharSequence getTitle() {
        return this.f1195a.getTitle();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: h */
    public void mo1032h() {
        this.f1195a.dismissPopupMenus();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: i */
    public void mo1033i(C0236j0 c0236j0) {
        View view = this.f1197c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1195a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1197c);
            }
        }
        this.f1197c = c0236j0;
        if (c0236j0 == null || this.f1209o != 2) {
            return;
        }
        this.f1195a.addView(c0236j0, 0);
        Toolbar.C0214e c0214e = (Toolbar.C0214e) this.f1197c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) c0214e).width = -2;
        ((ViewGroup.MarginLayoutParams) c0214e).height = -2;
        c0214e.f376a = 8388691;
        c0236j0.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: j */
    public boolean mo1034j() {
        return this.f1195a.hasExpandedActionView();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: k */
    public void mo1035k(int i9) {
        View view;
        int i10 = this.f1196b ^ i9;
        this.f1196b = i9;
        if (i10 != 0) {
            if ((i10 & 4) != 0) {
                if ((i9 & 4) != 0) {
                    m1053G();
                }
                m1054H();
            }
            if ((i10 & 3) != 0) {
                m1055I();
            }
            if ((i10 & 8) != 0) {
                if ((i9 & 8) != 0) {
                    this.f1195a.setTitle(this.f1203i);
                    this.f1195a.setSubtitle(this.f1204j);
                } else {
                    this.f1195a.setTitle((CharSequence) null);
                    this.f1195a.setSubtitle((CharSequence) null);
                }
            }
            if ((i10 & 16) == 0 || (view = this.f1198d) == null) {
                return;
            }
            if ((i9 & 16) != 0) {
                this.f1195a.addView(view);
            } else {
                this.f1195a.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: l */
    public void mo1036l(int i9) {
        m1059z(i9 != 0 ? C0694a.m3458b(mo1042r(), i9) : null);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: m */
    public int mo1037m() {
        return this.f1209o;
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: n */
    public C4620d0 mo1038n(int i9, long j9) {
        return C4647u.m18533b(this.f1195a).m18408a(i9 == 0 ? 1.0f : BitmapDescriptorFactory.HUE_RED).m18411d(j9).m18413f(new b(i9));
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: o */
    public void mo1039o(int i9) {
        this.f1195a.setVisibility(i9);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: p */
    public ViewGroup mo1040p() {
        return this.f1195a;
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: q */
    public void mo1041q(boolean z8) {
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: r */
    public Context mo1042r() {
        return this.f1195a.getContext();
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: s */
    public int mo1043s() {
        return this.f1196b;
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public void setIcon(int i9) {
        setIcon(i9 != 0 ? C0694a.m3458b(mo1042r(), i9) : null);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public void setWindowCallback(Window.Callback callback) {
        this.f1206l = callback;
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1202h) {
            return;
        }
        m1052F(charSequence);
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: t */
    public void mo1044t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: u */
    public void mo1045u() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    /* renamed from: v */
    public void mo1046v(boolean z8) {
        this.f1195a.setCollapsible(z8);
    }

    /* renamed from: w */
    public final int m1056w() {
        if (this.f1195a.getNavigationIcon() == null) {
            return 11;
        }
        this.f1211q = this.f1195a.getNavigationIcon();
        return 15;
    }

    /* renamed from: x */
    public void m1057x(View view) {
        View view2 = this.f1198d;
        if (view2 != null && (this.f1196b & 16) != 0) {
            this.f1195a.removeView(view2);
        }
        this.f1198d = view;
        if (view == null || (this.f1196b & 16) == 0) {
            return;
        }
        this.f1195a.addView(view);
    }

    /* renamed from: y */
    public void m1058y(int i9) {
        if (i9 == this.f1210p) {
            return;
        }
        this.f1210p = i9;
        if (TextUtils.isEmpty(this.f1195a.getNavigationContentDescription())) {
            m1047A(this.f1210p);
        }
    }

    /* renamed from: z */
    public void m1059z(Drawable drawable) {
        this.f1200f = drawable;
        m1055I();
    }

    public C0252r0(Toolbar toolbar, boolean z8, int i9, int i10) {
        Drawable drawable;
        this.f1209o = 0;
        this.f1210p = 0;
        this.f1195a = toolbar;
        this.f1203i = toolbar.getTitle();
        this.f1204j = toolbar.getSubtitle();
        this.f1202h = this.f1203i != null;
        this.f1201g = toolbar.getNavigationIcon();
        C0250q0 c0250q0M1004v = C0250q0.m1004v(toolbar.getContext(), null, C0569j.ActionBar, C0560a.actionBarStyle, 0);
        this.f1211q = c0250q0M1004v.m1011g(C0569j.ActionBar_homeAsUpIndicator);
        if (z8) {
            CharSequence charSequenceM1020p = c0250q0M1004v.m1020p(C0569j.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceM1020p)) {
                m1051E(charSequenceM1020p);
            }
            CharSequence charSequenceM1020p2 = c0250q0M1004v.m1020p(C0569j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceM1020p2)) {
                m1050D(charSequenceM1020p2);
            }
            Drawable drawableM1011g = c0250q0M1004v.m1011g(C0569j.ActionBar_logo);
            if (drawableM1011g != null) {
                m1059z(drawableM1011g);
            }
            Drawable drawableM1011g2 = c0250q0M1004v.m1011g(C0569j.ActionBar_icon);
            if (drawableM1011g2 != null) {
                setIcon(drawableM1011g2);
            }
            if (this.f1201g == null && (drawable = this.f1211q) != null) {
                m1049C(drawable);
            }
            mo1035k(c0250q0M1004v.m1015k(C0569j.ActionBar_displayOptions, 0));
            int iM1018n = c0250q0M1004v.m1018n(C0569j.ActionBar_customNavigationLayout, 0);
            if (iM1018n != 0) {
                m1057x(LayoutInflater.from(this.f1195a.getContext()).inflate(iM1018n, (ViewGroup) this.f1195a, false));
                mo1035k(this.f1196b | 16);
            }
            int iM1017m = c0250q0M1004v.m1017m(C0569j.ActionBar_height, 0);
            if (iM1017m > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1195a.getLayoutParams();
                layoutParams.height = iM1017m;
                this.f1195a.setLayoutParams(layoutParams);
            }
            int iM1009e = c0250q0M1004v.m1009e(C0569j.ActionBar_contentInsetStart, -1);
            int iM1009e2 = c0250q0M1004v.m1009e(C0569j.ActionBar_contentInsetEnd, -1);
            if (iM1009e >= 0 || iM1009e2 >= 0) {
                this.f1195a.setContentInsetsRelative(Math.max(iM1009e, 0), Math.max(iM1009e2, 0));
            }
            int iM1018n2 = c0250q0M1004v.m1018n(C0569j.ActionBar_titleTextStyle, 0);
            if (iM1018n2 != 0) {
                Toolbar toolbar2 = this.f1195a;
                toolbar2.setTitleTextAppearance(toolbar2.getContext(), iM1018n2);
            }
            int iM1018n3 = c0250q0M1004v.m1018n(C0569j.ActionBar_subtitleTextStyle, 0);
            if (iM1018n3 != 0) {
                Toolbar toolbar3 = this.f1195a;
                toolbar3.setSubtitleTextAppearance(toolbar3.getContext(), iM1018n3);
            }
            int iM1018n4 = c0250q0M1004v.m1018n(C0569j.ActionBar_popupTheme, 0);
            if (iM1018n4 != 0) {
                this.f1195a.setPopupTheme(iM1018n4);
            }
        } else {
            this.f1196b = m1056w();
        }
        c0250q0M1004v.m1024w();
        m1058y(i9);
        this.f1205k = this.f1195a.getNavigationContentDescription();
        this.f1195a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.InterfaceC0251r
    public void setIcon(Drawable drawable) {
        this.f1199e = drawable;
        m1055I();
    }
}
