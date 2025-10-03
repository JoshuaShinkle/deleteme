package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.InterfaceC0144n;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.view.menu.f */
/* loaded from: classes.dex */
public class C0136f extends BaseAdapter {

    /* renamed from: b */
    public C0137g f556b;

    /* renamed from: c */
    public int f557c = -1;

    /* renamed from: d */
    public boolean f558d;

    /* renamed from: e */
    public final boolean f559e;

    /* renamed from: f */
    public final LayoutInflater f560f;

    /* renamed from: g */
    public final int f561g;

    public C0136f(C0137g c0137g, LayoutInflater layoutInflater, boolean z8, int i9) {
        this.f559e = z8;
        this.f560f = layoutInflater;
        this.f556b = c0137g;
        this.f561g = i9;
        m515a();
    }

    /* renamed from: a */
    public void m515a() {
        C0139i expandedItem = this.f556b.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<C0139i> nonActionItems = this.f556b.getNonActionItems();
            int size = nonActionItems.size();
            for (int i9 = 0; i9 < size; i9++) {
                if (nonActionItems.get(i9) == expandedItem) {
                    this.f557c = i9;
                    return;
                }
            }
        }
        this.f557c = -1;
    }

    /* renamed from: b */
    public C0137g m516b() {
        return this.f556b;
    }

    @Override // android.widget.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C0139i getItem(int i9) {
        ArrayList<C0139i> nonActionItems = this.f559e ? this.f556b.getNonActionItems() : this.f556b.getVisibleItems();
        int i10 = this.f557c;
        if (i10 >= 0 && i9 >= i10) {
            i9++;
        }
        return nonActionItems.get(i9);
    }

    /* renamed from: d */
    public void m518d(boolean z8) {
        this.f558d = z8;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f557c < 0 ? (this.f559e ? this.f556b.getNonActionItems() : this.f556b.getVisibleItems()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        return i9;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f560f.inflate(this.f561g, viewGroup, false);
        }
        int groupId = getItem(i9).getGroupId();
        int i10 = i9 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f556b.isGroupDividerEnabled() && groupId != (i10 >= 0 ? getItem(i10).getGroupId() : groupId));
        InterfaceC0144n.a aVar = (InterfaceC0144n.a) view;
        if (this.f558d) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.initialize(getItem(i9), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        m515a();
        super.notifyDataSetChanged();
    }
}
