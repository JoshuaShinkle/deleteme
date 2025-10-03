package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.widget.C0228f0;
import p010b.C0563d;
import p010b.C0566g;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.view.menu.q */
/* loaded from: classes.dex */
public final class ViewOnKeyListenerC0147q extends AbstractC0141k implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* renamed from: w */
    public static final int f624w = C0566g.abc_popup_menu_item_layout;

    /* renamed from: c */
    public final Context f625c;

    /* renamed from: d */
    public final C0137g f626d;

    /* renamed from: e */
    public final C0136f f627e;

    /* renamed from: f */
    public final boolean f628f;

    /* renamed from: g */
    public final int f629g;

    /* renamed from: h */
    public final int f630h;

    /* renamed from: i */
    public final int f631i;

    /* renamed from: j */
    public final C0228f0 f632j;

    /* renamed from: m */
    public PopupWindow.OnDismissListener f635m;

    /* renamed from: n */
    public View f636n;

    /* renamed from: o */
    public View f637o;

    /* renamed from: p */
    public InterfaceC0143m.a f638p;

    /* renamed from: q */
    public ViewTreeObserver f639q;

    /* renamed from: r */
    public boolean f640r;

    /* renamed from: s */
    public boolean f641s;

    /* renamed from: t */
    public int f642t;

    /* renamed from: v */
    public boolean f644v;

    /* renamed from: k */
    public final ViewTreeObserver.OnGlobalLayoutListener f633k = new a();

    /* renamed from: l */
    public final View.OnAttachStateChangeListener f634l = new b();

    /* renamed from: u */
    public int f643u = 0;

    /* renamed from: androidx.appcompat.view.menu.q$a */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!ViewOnKeyListenerC0147q.this.mo488a() || ViewOnKeyListenerC0147q.this.f632j.m710v()) {
                return;
            }
            View view = ViewOnKeyListenerC0147q.this.f637o;
            if (view == null || !view.isShown()) {
                ViewOnKeyListenerC0147q.this.dismiss();
            } else {
                ViewOnKeyListenerC0147q.this.f632j.show();
            }
        }
    }

    /* renamed from: androidx.appcompat.view.menu.q$b */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = ViewOnKeyListenerC0147q.this.f639q;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    ViewOnKeyListenerC0147q.this.f639q = view.getViewTreeObserver();
                }
                ViewOnKeyListenerC0147q viewOnKeyListenerC0147q = ViewOnKeyListenerC0147q.this;
                viewOnKeyListenerC0147q.f639q.removeGlobalOnLayoutListener(viewOnKeyListenerC0147q.f633k);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public ViewOnKeyListenerC0147q(Context context, C0137g c0137g, View view, int i9, int i10, boolean z8) {
        this.f625c = context;
        this.f626d = c0137g;
        this.f628f = z8;
        this.f627e = new C0136f(c0137g, LayoutInflater.from(context), z8, f624w);
        this.f630h = i9;
        this.f631i = i10;
        Resources resources = context.getResources();
        this.f629g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0563d.abc_config_prefDialogWidth));
        this.f636n = view;
        this.f632j = new C0228f0(context, null, i9, i10);
        c0137g.addMenuPresenter(this, context);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: a */
    public boolean mo488a() {
        return !this.f640r && this.f632j.mo488a();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: b */
    public void mo489b(C0137g c0137g) {
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void dismiss() {
        if (mo488a()) {
            this.f632j.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: f */
    public void mo491f(View view) {
        this.f636n = view;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: h */
    public ListView mo492h() {
        return this.f632j.mo492h();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: i */
    public void mo493i(boolean z8) {
        this.f627e.m518d(z8);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: j */
    public void mo494j(int i9) {
        this.f643u = i9;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: k */
    public void mo495k(int i9) {
        this.f632j.m699d(i9);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: l */
    public void mo496l(PopupWindow.OnDismissListener onDismissListener) {
        this.f635m = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: m */
    public void mo497m(boolean z8) {
        this.f644v = z8;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: n */
    public void mo498n(int i9) {
        this.f632j.m701j(i9);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        if (c0137g != this.f626d) {
            return;
        }
        dismiss();
        InterfaceC0143m.a aVar = this.f638p;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, z8);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f640r = true;
        this.f626d.close();
        ViewTreeObserver viewTreeObserver = this.f639q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f639q = this.f637o.getViewTreeObserver();
            }
            this.f639q.removeGlobalOnLayoutListener(this.f633k);
            this.f639q = null;
        }
        this.f637o.removeOnAttachStateChangeListener(this.f634l);
        PopupWindow.OnDismissListener onDismissListener = this.f635m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i9, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i9 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        if (subMenuC0148r.hasVisibleItems()) {
            C0142l c0142l = new C0142l(this.f625c, subMenuC0148r, this.f637o, this.f628f, this.f630h, this.f631i);
            c0142l.m571j(this.f638p);
            c0142l.m568g(AbstractC0141k.m558o(subMenuC0148r));
            c0142l.m570i(this.f635m);
            this.f635m = null;
            this.f626d.close(false);
            int iM698b = this.f632j.m698b();
            int iM702m = this.f632j.m702m();
            if ((Gravity.getAbsoluteGravity(this.f643u, C4647u.m18567s(this.f636n)) & 7) == 5) {
                iM698b += this.f636n.getWidth();
            }
            if (c0142l.m575n(iM698b, iM702m)) {
                InterfaceC0143m.a aVar = this.f638p;
                if (aVar == null) {
                    return true;
                }
                aVar.mo352a(subMenuC0148r);
                return true;
            }
        }
        return false;
    }

    /* renamed from: q */
    public final boolean m576q() {
        View view;
        if (mo488a()) {
            return true;
        }
        if (this.f640r || (view = this.f636n) == null) {
            return false;
        }
        this.f637o = view;
        this.f632j.m691E(this);
        this.f632j.m692F(this);
        this.f632j.m690D(true);
        View view2 = this.f637o;
        boolean z8 = this.f639q == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f639q = viewTreeObserver;
        if (z8) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f633k);
        }
        view2.addOnAttachStateChangeListener(this.f634l);
        this.f632j.m712x(view2);
        this.f632j.m687A(this.f643u);
        if (!this.f641s) {
            this.f642t = AbstractC0141k.m557e(this.f627e, null, this.f625c, this.f629g);
            this.f641s = true;
        }
        this.f632j.m714z(this.f642t);
        this.f632j.m689C(2);
        this.f632j.m688B(m560d());
        this.f632j.show();
        ListView listViewMo492h = this.f632j.mo492h();
        listViewMo492h.setOnKeyListener(this);
        if (this.f644v && this.f626d.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f625c).inflate(C0566g.abc_popup_menu_header_item_layout, (ViewGroup) listViewMo492h, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f626d.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            listViewMo492h.addHeaderView(frameLayout, null, false);
        }
        this.f632j.mo678n(this.f627e);
        this.f632j.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
        this.f638p = aVar;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void show() {
        if (!m576q()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        this.f641s = false;
        C0136f c0136f = this.f627e;
        if (c0136f != null) {
            c0136f.notifyDataSetChanged();
        }
    }
}
