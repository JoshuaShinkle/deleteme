package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.widget.C0228f0;
import androidx.appcompat.widget.InterfaceC0224d0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p010b.C0563d;
import p010b.C0566g;
import p042d0.C4621e;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.view.menu.d */
/* loaded from: classes.dex */
public final class ViewOnKeyListenerC0134d extends AbstractC0141k implements View.OnKeyListener, PopupWindow.OnDismissListener {

    /* renamed from: C */
    public static final int f507C = C0566g.abc_cascading_menu_item_layout;

    /* renamed from: A */
    public PopupWindow.OnDismissListener f508A;

    /* renamed from: B */
    public boolean f509B;

    /* renamed from: c */
    public final Context f510c;

    /* renamed from: d */
    public final int f511d;

    /* renamed from: e */
    public final int f512e;

    /* renamed from: f */
    public final int f513f;

    /* renamed from: g */
    public final boolean f514g;

    /* renamed from: h */
    public final Handler f515h;

    /* renamed from: p */
    public View f523p;

    /* renamed from: q */
    public View f524q;

    /* renamed from: s */
    public boolean f526s;

    /* renamed from: t */
    public boolean f527t;

    /* renamed from: u */
    public int f528u;

    /* renamed from: v */
    public int f529v;

    /* renamed from: x */
    public boolean f531x;

    /* renamed from: y */
    public InterfaceC0143m.a f532y;

    /* renamed from: z */
    public ViewTreeObserver f533z;

    /* renamed from: i */
    public final List<C0137g> f516i = new ArrayList();

    /* renamed from: j */
    public final List<d> f517j = new ArrayList();

    /* renamed from: k */
    public final ViewTreeObserver.OnGlobalLayoutListener f518k = new a();

    /* renamed from: l */
    public final View.OnAttachStateChangeListener f519l = new b();

    /* renamed from: m */
    public final InterfaceC0224d0 f520m = new c();

    /* renamed from: n */
    public int f521n = 0;

    /* renamed from: o */
    public int f522o = 0;

    /* renamed from: w */
    public boolean f530w = false;

    /* renamed from: r */
    public int f525r = m503u();

    /* renamed from: androidx.appcompat.view.menu.d$a */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!ViewOnKeyListenerC0134d.this.mo488a() || ViewOnKeyListenerC0134d.this.f517j.size() <= 0 || ViewOnKeyListenerC0134d.this.f517j.get(0).f541a.m710v()) {
                return;
            }
            View view = ViewOnKeyListenerC0134d.this.f524q;
            if (view == null || !view.isShown()) {
                ViewOnKeyListenerC0134d.this.dismiss();
                return;
            }
            Iterator<d> it = ViewOnKeyListenerC0134d.this.f517j.iterator();
            while (it.hasNext()) {
                it.next().f541a.show();
            }
        }
    }

    /* renamed from: androidx.appcompat.view.menu.d$b */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = ViewOnKeyListenerC0134d.this.f533z;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    ViewOnKeyListenerC0134d.this.f533z = view.getViewTreeObserver();
                }
                ViewOnKeyListenerC0134d viewOnKeyListenerC0134d = ViewOnKeyListenerC0134d.this;
                viewOnKeyListenerC0134d.f533z.removeGlobalOnLayoutListener(viewOnKeyListenerC0134d.f518k);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* renamed from: androidx.appcompat.view.menu.d$c */
    public class c implements InterfaceC0224d0 {

        /* renamed from: androidx.appcompat.view.menu.d$c$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ d f537b;

            /* renamed from: c */
            public final /* synthetic */ MenuItem f538c;

            /* renamed from: d */
            public final /* synthetic */ C0137g f539d;

            public a(d dVar, MenuItem menuItem, C0137g c0137g) {
                this.f537b = dVar;
                this.f538c = menuItem;
                this.f539d = c0137g;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.f537b;
                if (dVar != null) {
                    ViewOnKeyListenerC0134d.this.f509B = true;
                    dVar.f542b.close(false);
                    ViewOnKeyListenerC0134d.this.f509B = false;
                }
                if (this.f538c.isEnabled() && this.f538c.hasSubMenu()) {
                    this.f539d.performItemAction(this.f538c, 4);
                }
            }
        }

        public c() {
        }

        @Override // androidx.appcompat.widget.InterfaceC0224d0
        /* renamed from: c */
        public void mo506c(C0137g c0137g, MenuItem menuItem) {
            ViewOnKeyListenerC0134d.this.f515h.removeCallbacksAndMessages(null);
            int size = ViewOnKeyListenerC0134d.this.f517j.size();
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    i9 = -1;
                    break;
                } else if (c0137g == ViewOnKeyListenerC0134d.this.f517j.get(i9).f542b) {
                    break;
                } else {
                    i9++;
                }
            }
            if (i9 == -1) {
                return;
            }
            int i10 = i9 + 1;
            ViewOnKeyListenerC0134d.this.f515h.postAtTime(new a(i10 < ViewOnKeyListenerC0134d.this.f517j.size() ? ViewOnKeyListenerC0134d.this.f517j.get(i10) : null, menuItem, c0137g), c0137g, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.InterfaceC0224d0
        /* renamed from: f */
        public void mo507f(C0137g c0137g, MenuItem menuItem) {
            ViewOnKeyListenerC0134d.this.f515h.removeCallbacksAndMessages(c0137g);
        }
    }

    /* renamed from: androidx.appcompat.view.menu.d$d */
    public static class d {

        /* renamed from: a */
        public final C0228f0 f541a;

        /* renamed from: b */
        public final C0137g f542b;

        /* renamed from: c */
        public final int f543c;

        public d(C0228f0 c0228f0, C0137g c0137g, int i9) {
            this.f541a = c0228f0;
            this.f542b = c0137g;
            this.f543c = i9;
        }

        /* renamed from: a */
        public ListView m508a() {
            return this.f541a.mo492h();
        }
    }

    public ViewOnKeyListenerC0134d(Context context, View view, int i9, int i10, boolean z8) {
        this.f510c = context;
        this.f523p = view;
        this.f512e = i9;
        this.f513f = i10;
        this.f514g = z8;
        Resources resources = context.getResources();
        this.f511d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0563d.abc_config_prefDialogWidth));
        this.f515h = new Handler();
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: a */
    public boolean mo488a() {
        return this.f517j.size() > 0 && this.f517j.get(0).f541a.mo488a();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: b */
    public void mo489b(C0137g c0137g) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        c0137g.addMenuPresenter(this, this.f510c);
        if (mo488a()) {
            m505w(c0137g);
        } else {
            this.f516i.add(c0137g);
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: c */
    public boolean mo490c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void dismiss() {
        int size = this.f517j.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.f517j.toArray(new d[size]);
            for (int i9 = size - 1; i9 >= 0; i9--) {
                d dVar = dVarArr[i9];
                if (dVar.f541a.mo488a()) {
                    dVar.f541a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: f */
    public void mo491f(View view) {
        if (this.f523p != view) {
            this.f523p = view;
            this.f522o = C4621e.m18420b(this.f521n, C4647u.m18567s(view));
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: h */
    public ListView mo492h() {
        if (this.f517j.isEmpty()) {
            return null;
        }
        return this.f517j.get(r0.size() - 1).m508a();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: i */
    public void mo493i(boolean z8) {
        this.f530w = z8;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: j */
    public void mo494j(int i9) {
        if (this.f521n != i9) {
            this.f521n = i9;
            this.f522o = C4621e.m18420b(i9, C4647u.m18567s(this.f523p));
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: k */
    public void mo495k(int i9) {
        this.f526s = true;
        this.f528u = i9;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: l */
    public void mo496l(PopupWindow.OnDismissListener onDismissListener) {
        this.f508A = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: m */
    public void mo497m(boolean z8) {
        this.f531x = z8;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0141k
    /* renamed from: n */
    public void mo498n(int i9) {
        this.f527t = true;
        this.f529v = i9;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        int iM500r = m500r(c0137g);
        if (iM500r < 0) {
            return;
        }
        int i9 = iM500r + 1;
        if (i9 < this.f517j.size()) {
            this.f517j.get(i9).f542b.close(false);
        }
        d dVarRemove = this.f517j.remove(iM500r);
        dVarRemove.f542b.removeMenuPresenter(this);
        if (this.f509B) {
            dVarRemove.f541a.m840M(null);
            dVarRemove.f541a.m713y(0);
        }
        dVarRemove.f541a.dismiss();
        int size = this.f517j.size();
        if (size > 0) {
            this.f525r = this.f517j.get(size - 1).f543c;
        } else {
            this.f525r = m503u();
        }
        if (size != 0) {
            if (z8) {
                this.f517j.get(0).f542b.close(false);
                return;
            }
            return;
        }
        dismiss();
        InterfaceC0143m.a aVar = this.f532y;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, true);
        }
        ViewTreeObserver viewTreeObserver = this.f533z;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f533z.removeGlobalOnLayoutListener(this.f518k);
            }
            this.f533z = null;
        }
        this.f524q.removeOnAttachStateChangeListener(this.f519l);
        this.f508A.onDismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.f517j.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size) {
                dVar = null;
                break;
            }
            dVar = this.f517j.get(i9);
            if (!dVar.f541a.mo488a()) {
                break;
            } else {
                i9++;
            }
        }
        if (dVar != null) {
            dVar.f542b.close(false);
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
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (d dVar : this.f517j) {
            if (subMenuC0148r == dVar.f542b) {
                dVar.m508a().requestFocus();
                return true;
            }
        }
        if (!subMenuC0148r.hasVisibleItems()) {
            return false;
        }
        mo489b(subMenuC0148r);
        InterfaceC0143m.a aVar = this.f532y;
        if (aVar != null) {
            aVar.mo352a(subMenuC0148r);
        }
        return true;
    }

    /* renamed from: q */
    public final C0228f0 m499q() {
        C0228f0 c0228f0 = new C0228f0(this.f510c, null, this.f512e, this.f513f);
        c0228f0.m841N(this.f520m);
        c0228f0.m692F(this);
        c0228f0.m691E(this);
        c0228f0.m712x(this.f523p);
        c0228f0.m687A(this.f522o);
        c0228f0.m690D(true);
        c0228f0.m689C(2);
        return c0228f0;
    }

    /* renamed from: r */
    public final int m500r(C0137g c0137g) {
        int size = this.f517j.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (c0137g == this.f517j.get(i9).f542b) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: s */
    public final MenuItem m501s(C0137g c0137g, C0137g c0137g2) {
        int size = c0137g.size();
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = c0137g.getItem(i9);
            if (item.hasSubMenu() && c0137g2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
        this.f532y = aVar;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void show() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (mo488a()) {
            return;
        }
        Iterator<C0137g> it = this.f516i.iterator();
        while (it.hasNext()) {
            m505w(it.next());
        }
        this.f516i.clear();
        View view = this.f523p;
        this.f524q = view;
        if (view != null) {
            boolean z8 = this.f533z == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f533z = viewTreeObserver;
            if (z8) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f518k);
            }
            this.f524q.addOnAttachStateChangeListener(this.f519l);
        }
    }

    /* renamed from: t */
    public final View m502t(d dVar, C0137g c0137g) {
        C0136f c0136f;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemM501s = m501s(dVar.f542b, c0137g);
        if (menuItemM501s == null) {
            return null;
        }
        ListView listViewM508a = dVar.m508a();
        ListAdapter adapter = listViewM508a.getAdapter();
        int i9 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            c0136f = (C0136f) headerViewListAdapter.getWrappedAdapter();
        } else {
            c0136f = (C0136f) adapter;
            headersCount = 0;
        }
        int count = c0136f.getCount();
        while (true) {
            if (i9 >= count) {
                i9 = -1;
                break;
            }
            if (menuItemM501s == c0136f.getItem(i9)) {
                break;
            }
            i9++;
        }
        if (i9 != -1 && (firstVisiblePosition = (i9 + headersCount) - listViewM508a.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewM508a.getChildCount()) {
            return listViewM508a.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    /* renamed from: u */
    public final int m503u() {
        return C4647u.m18567s(this.f523p) == 1 ? 0 : 1;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        Iterator<d> it = this.f517j.iterator();
        while (it.hasNext()) {
            AbstractC0141k.m559p(it.next().m508a().getAdapter()).notifyDataSetChanged();
        }
    }

    /* renamed from: v */
    public final int m504v(int i9) {
        List<d> list = this.f517j;
        ListView listViewM508a = list.get(list.size() - 1).m508a();
        int[] iArr = new int[2];
        listViewM508a.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f524q.getWindowVisibleDisplayFrame(rect);
        return this.f525r == 1 ? (iArr[0] + listViewM508a.getWidth()) + i9 > rect.right ? 0 : 1 : iArr[0] - i9 < 0 ? 1 : 0;
    }

    /* renamed from: w */
    public final void m505w(C0137g c0137g) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        d dVar;
        View viewM502t;
        int i9;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f510c);
        C0136f c0136f = new C0136f(c0137g, layoutInflaterFrom, this.f514g, f507C);
        if (!mo488a() && this.f530w) {
            c0136f.m518d(true);
        } else if (mo488a()) {
            c0136f.m518d(AbstractC0141k.m558o(c0137g));
        }
        int iM557e = AbstractC0141k.m557e(c0136f, null, this.f510c, this.f511d);
        C0228f0 c0228f0M499q = m499q();
        c0228f0M499q.mo678n(c0136f);
        c0228f0M499q.m714z(iM557e);
        c0228f0M499q.m687A(this.f522o);
        if (this.f517j.size() > 0) {
            List<d> list = this.f517j;
            dVar = list.get(list.size() - 1);
            viewM502t = m502t(dVar, c0137g);
        } else {
            dVar = null;
            viewM502t = null;
        }
        if (viewM502t != null) {
            c0228f0M499q.m842O(false);
            c0228f0M499q.m839L(null);
            int iM504v = m504v(iM557e);
            boolean z8 = iM504v == 1;
            this.f525r = iM504v;
            c0228f0M499q.m712x(viewM502t);
            if ((this.f522o & 5) == 5) {
                if (!z8) {
                    iM557e = viewM502t.getWidth();
                    i9 = 0 - iM557e;
                }
                i9 = iM557e + 0;
            } else {
                if (z8) {
                    iM557e = viewM502t.getWidth();
                    i9 = iM557e + 0;
                }
                i9 = 0 - iM557e;
            }
            c0228f0M499q.m699d(i9);
            c0228f0M499q.m693G(true);
            c0228f0M499q.m701j(0);
        } else {
            if (this.f526s) {
                c0228f0M499q.m699d(this.f528u);
            }
            if (this.f527t) {
                c0228f0M499q.m701j(this.f529v);
            }
            c0228f0M499q.m688B(m560d());
        }
        this.f517j.add(new d(c0228f0M499q, c0137g, this.f525r));
        c0228f0M499q.show();
        ListView listViewMo492h = c0228f0M499q.mo492h();
        listViewMo492h.setOnKeyListener(this);
        if (dVar == null && this.f531x && c0137g.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(C0566g.abc_popup_menu_header_item_layout, (ViewGroup) listViewMo492h, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(c0137g.getHeaderTitle());
            listViewMo492h.addHeaderView(frameLayout, null, false);
            c0228f0M499q.show();
        }
    }
}
