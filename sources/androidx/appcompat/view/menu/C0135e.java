package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import java.util.ArrayList;
import p010b.C0566g;

/* renamed from: androidx.appcompat.view.menu.e */
/* loaded from: classes.dex */
public class C0135e implements InterfaceC0143m, AdapterView.OnItemClickListener {

    /* renamed from: b */
    public Context f544b;

    /* renamed from: c */
    public LayoutInflater f545c;

    /* renamed from: d */
    public C0137g f546d;

    /* renamed from: e */
    public ExpandedMenuView f547e;

    /* renamed from: f */
    public int f548f;

    /* renamed from: g */
    public int f549g;

    /* renamed from: h */
    public int f550h;

    /* renamed from: i */
    public InterfaceC0143m.a f551i;

    /* renamed from: j */
    public a f552j;

    /* renamed from: k */
    public int f553k;

    /* renamed from: androidx.appcompat.view.menu.e$a */
    public class a extends BaseAdapter {

        /* renamed from: b */
        public int f554b = -1;

        public a() {
            m513a();
        }

        /* renamed from: a */
        public void m513a() {
            C0139i expandedItem = C0135e.this.f546d.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<C0139i> nonActionItems = C0135e.this.f546d.getNonActionItems();
                int size = nonActionItems.size();
                for (int i9 = 0; i9 < size; i9++) {
                    if (nonActionItems.get(i9) == expandedItem) {
                        this.f554b = i9;
                        return;
                    }
                }
            }
            this.f554b = -1;
        }

        @Override // android.widget.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0139i getItem(int i9) {
            ArrayList<C0139i> nonActionItems = C0135e.this.f546d.getNonActionItems();
            int i10 = i9 + C0135e.this.f548f;
            int i11 = this.f554b;
            if (i11 >= 0 && i10 >= i11) {
                i10++;
            }
            return nonActionItems.get(i10);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = C0135e.this.f546d.getNonActionItems().size() - C0135e.this.f548f;
            return this.f554b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            if (view == null) {
                C0135e c0135e = C0135e.this;
                view = c0135e.f545c.inflate(c0135e.f550h, viewGroup, false);
            }
            ((InterfaceC0144n.a) view).initialize(getItem(i9), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            m513a();
            super.notifyDataSetChanged();
        }
    }

    public C0135e(Context context, int i9) {
        this(i9, 0);
        this.f544b = context;
        this.f545c = LayoutInflater.from(context);
    }

    /* renamed from: a */
    public ListAdapter m509a() {
        if (this.f552j == null) {
            this.f552j = new a();
        }
        return this.f552j;
    }

    /* renamed from: b */
    public InterfaceC0144n m510b(ViewGroup viewGroup) {
        if (this.f547e == null) {
            this.f547e = (ExpandedMenuView) this.f545c.inflate(C0566g.abc_expanded_menu_layout, viewGroup, false);
            if (this.f552j == null) {
                this.f552j = new a();
            }
            this.f547e.setAdapter((ListAdapter) this.f552j);
            this.f547e.setOnItemClickListener(this);
        }
        return this.f547e;
    }

    /* renamed from: c */
    public void m511c(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f547e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    /* renamed from: d */
    public void m512d(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f547e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public int getId() {
        return this.f553k;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
        if (this.f549g != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.f549g);
            this.f544b = contextThemeWrapper;
            this.f545c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f544b != null) {
            this.f544b = context;
            if (this.f545c == null) {
                this.f545c = LayoutInflater.from(context);
            }
        }
        this.f546d = c0137g;
        a aVar = this.f552j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        InterfaceC0143m.a aVar = this.f551i;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, z8);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
        this.f546d.performItemAction(this.f552j.getItem(i9), this, 0);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onRestoreInstanceState(Parcelable parcelable) {
        m511c((Bundle) parcelable);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public Parcelable onSaveInstanceState() {
        if (this.f547e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        m512d(bundle);
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        if (!subMenuC0148r.hasVisibleItems()) {
            return false;
        }
        new DialogInterfaceOnKeyListenerC0138h(subMenuC0148r).m520c(null);
        InterfaceC0143m.a aVar = this.f551i;
        if (aVar == null) {
            return true;
        }
        aVar.mo352a(subMenuC0148r);
        return true;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
        this.f551i = aVar;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        a aVar = this.f552j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public C0135e(int i9, int i10) {
        this.f550h = i9;
        this.f549g = i10;
    }
}
