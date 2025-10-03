package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: androidx.appcompat.view.menu.k */
/* loaded from: classes.dex */
public abstract class AbstractC0141k implements InterfaceC0146p, InterfaceC0143m, AdapterView.OnItemClickListener {

    /* renamed from: b */
    public Rect f609b;

    /* renamed from: e */
    public static int m557e(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i9) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i10 = 0;
        int i11 = 0;
        View view = null;
        for (int i12 = 0; i12 < count; i12++) {
            int itemViewType = listAdapter.getItemViewType(i12);
            if (itemViewType != i11) {
                view = null;
                i11 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i12, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i9) {
                return i9;
            }
            if (measuredWidth > i10) {
                i10 = measuredWidth;
            }
        }
        return i10;
    }

    /* renamed from: o */
    public static boolean m558o(C0137g c0137g) {
        int size = c0137g.size();
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = c0137g.getItem(i9);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: p */
    public static C0136f m559p(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (C0136f) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (C0136f) listAdapter;
    }

    /* renamed from: b */
    public abstract void mo489b(C0137g c0137g);

    /* renamed from: c */
    public boolean mo490c() {
        return true;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    /* renamed from: d */
    public Rect m560d() {
        return this.f609b;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    /* renamed from: f */
    public abstract void mo491f(View view);

    /* renamed from: g */
    public void m561g(Rect rect) {
        this.f609b = rect;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public int getId() {
        return 0;
    }

    /* renamed from: i */
    public abstract void mo493i(boolean z8);

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
    }

    /* renamed from: j */
    public abstract void mo494j(int i9);

    /* renamed from: k */
    public abstract void mo495k(int i9);

    /* renamed from: l */
    public abstract void mo496l(PopupWindow.OnDismissListener onDismissListener);

    /* renamed from: m */
    public abstract void mo497m(boolean z8);

    /* renamed from: n */
    public abstract void mo498n(int i9);

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        m559p(listAdapter).f556b.performItemAction((MenuItem) listAdapter.getItem(i9), this, mo490c() ? 0 : 4);
    }
}
