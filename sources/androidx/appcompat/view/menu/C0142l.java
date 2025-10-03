package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.InterfaceC0143m;
import p010b.C0563d;
import p042d0.C4621e;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.view.menu.l */
/* loaded from: classes.dex */
public class C0142l {

    /* renamed from: a */
    public final Context f610a;

    /* renamed from: b */
    public final C0137g f611b;

    /* renamed from: c */
    public final boolean f612c;

    /* renamed from: d */
    public final int f613d;

    /* renamed from: e */
    public final int f614e;

    /* renamed from: f */
    public View f615f;

    /* renamed from: g */
    public int f616g;

    /* renamed from: h */
    public boolean f617h;

    /* renamed from: i */
    public InterfaceC0143m.a f618i;

    /* renamed from: j */
    public AbstractC0141k f619j;

    /* renamed from: k */
    public PopupWindow.OnDismissListener f620k;

    /* renamed from: l */
    public final PopupWindow.OnDismissListener f621l;

    /* renamed from: androidx.appcompat.view.menu.l$a */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            C0142l.this.mo566e();
        }
    }

    public C0142l(Context context, C0137g c0137g, View view, boolean z8, int i9) {
        this(context, c0137g, view, z8, i9, 0);
    }

    /* renamed from: a */
    public final AbstractC0141k m562a() {
        Display defaultDisplay = ((WindowManager) this.f610a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        AbstractC0141k viewOnKeyListenerC0134d = Math.min(point.x, point.y) >= this.f610a.getResources().getDimensionPixelSize(C0563d.abc_cascading_menus_min_smallest_width) ? new ViewOnKeyListenerC0134d(this.f610a, this.f615f, this.f613d, this.f614e, this.f612c) : new ViewOnKeyListenerC0147q(this.f610a, this.f611b, this.f615f, this.f613d, this.f614e, this.f612c);
        viewOnKeyListenerC0134d.mo489b(this.f611b);
        viewOnKeyListenerC0134d.mo496l(this.f621l);
        viewOnKeyListenerC0134d.mo491f(this.f615f);
        viewOnKeyListenerC0134d.setCallback(this.f618i);
        viewOnKeyListenerC0134d.mo493i(this.f617h);
        viewOnKeyListenerC0134d.mo494j(this.f616g);
        return viewOnKeyListenerC0134d;
    }

    /* renamed from: b */
    public void m563b() {
        if (m565d()) {
            this.f619j.dismiss();
        }
    }

    /* renamed from: c */
    public AbstractC0141k m564c() {
        if (this.f619j == null) {
            this.f619j = m562a();
        }
        return this.f619j;
    }

    /* renamed from: d */
    public boolean m565d() {
        AbstractC0141k abstractC0141k = this.f619j;
        return abstractC0141k != null && abstractC0141k.mo488a();
    }

    /* renamed from: e */
    public void mo566e() {
        this.f619j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f620k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    /* renamed from: f */
    public void m567f(View view) {
        this.f615f = view;
    }

    /* renamed from: g */
    public void m568g(boolean z8) {
        this.f617h = z8;
        AbstractC0141k abstractC0141k = this.f619j;
        if (abstractC0141k != null) {
            abstractC0141k.mo493i(z8);
        }
    }

    /* renamed from: h */
    public void m569h(int i9) {
        this.f616g = i9;
    }

    /* renamed from: i */
    public void m570i(PopupWindow.OnDismissListener onDismissListener) {
        this.f620k = onDismissListener;
    }

    /* renamed from: j */
    public void m571j(InterfaceC0143m.a aVar) {
        this.f618i = aVar;
        AbstractC0141k abstractC0141k = this.f619j;
        if (abstractC0141k != null) {
            abstractC0141k.setCallback(aVar);
        }
    }

    /* renamed from: k */
    public void m572k() {
        if (!m574m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    /* renamed from: l */
    public final void m573l(int i9, int i10, boolean z8, boolean z9) {
        AbstractC0141k abstractC0141kM564c = m564c();
        abstractC0141kM564c.mo497m(z9);
        if (z8) {
            if ((C4621e.m18420b(this.f616g, C4647u.m18567s(this.f615f)) & 7) == 5) {
                i9 -= this.f615f.getWidth();
            }
            abstractC0141kM564c.mo495k(i9);
            abstractC0141kM564c.mo498n(i10);
            int i11 = (int) ((this.f610a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            abstractC0141kM564c.m561g(new Rect(i9 - i11, i10 - i11, i9 + i11, i10 + i11));
        }
        abstractC0141kM564c.show();
    }

    /* renamed from: m */
    public boolean m574m() {
        if (m565d()) {
            return true;
        }
        if (this.f615f == null) {
            return false;
        }
        m573l(0, 0, false, false);
        return true;
    }

    /* renamed from: n */
    public boolean m575n(int i9, int i10) {
        if (m565d()) {
            return true;
        }
        if (this.f615f == null) {
            return false;
        }
        m573l(i9, i10, true, true);
        return true;
    }

    public C0142l(Context context, C0137g c0137g, View view, boolean z8, int i9, int i10) {
        this.f616g = 8388611;
        this.f621l = new a();
        this.f610a = context;
        this.f611b = c0137g;
        this.f615f = view;
        this.f612c = z8;
        this.f613d = i9;
        this.f614e = i10;
    }
}
