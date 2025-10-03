package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.view.menu.b */
/* loaded from: classes.dex */
public abstract class AbstractC0132b implements InterfaceC0143m {

    /* renamed from: b */
    public Context f494b;

    /* renamed from: c */
    public Context f495c;

    /* renamed from: d */
    public C0137g f496d;

    /* renamed from: e */
    public LayoutInflater f497e;

    /* renamed from: f */
    public LayoutInflater f498f;

    /* renamed from: g */
    public InterfaceC0143m.a f499g;

    /* renamed from: h */
    public int f500h;

    /* renamed from: i */
    public int f501i;

    /* renamed from: j */
    public InterfaceC0144n f502j;

    /* renamed from: k */
    public int f503k;

    public AbstractC0132b(Context context, int i9, int i10) {
        this.f494b = context;
        this.f497e = LayoutInflater.from(context);
        this.f500h = i9;
        this.f501i = i10;
    }

    /* renamed from: b */
    public void m474b(View view, int i9) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f502j).addView(view, i9);
    }

    /* renamed from: c */
    public abstract void mo475c(C0139i c0139i, InterfaceC0144n.a aVar);

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    /* renamed from: d */
    public InterfaceC0144n.a m476d(ViewGroup viewGroup) {
        return (InterfaceC0144n.a) this.f497e.inflate(this.f501i, viewGroup, false);
    }

    /* renamed from: e */
    public boolean mo477e(ViewGroup viewGroup, int i9) {
        viewGroup.removeViewAt(i9);
        return true;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    /* renamed from: f */
    public InterfaceC0143m.a m478f() {
        return this.f499g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: g */
    public View mo479g(C0139i c0139i, View view, ViewGroup viewGroup) {
        InterfaceC0144n.a aVarM476d = view instanceof InterfaceC0144n.a ? (InterfaceC0144n.a) view : m476d(viewGroup);
        mo475c(c0139i, aVarM476d);
        return (View) aVarM476d;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public int getId() {
        return this.f503k;
    }

    /* renamed from: h */
    public InterfaceC0144n mo480h(ViewGroup viewGroup) {
        if (this.f502j == null) {
            InterfaceC0144n interfaceC0144n = (InterfaceC0144n) this.f497e.inflate(this.f500h, viewGroup, false);
            this.f502j = interfaceC0144n;
            interfaceC0144n.initialize(this.f496d);
            updateMenuView(true);
        }
        return this.f502j;
    }

    /* renamed from: i */
    public void m481i(int i9) {
        this.f503k = i9;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
        this.f495c = context;
        this.f498f = LayoutInflater.from(context);
        this.f496d = c0137g;
    }

    /* renamed from: j */
    public abstract boolean mo482j(int i9, C0139i c0139i);

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        InterfaceC0143m.a aVar = this.f499g;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, z8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.appcompat.view.menu.g] */
    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        InterfaceC0143m.a aVar = this.f499g;
        SubMenuC0148r subMenuC0148r2 = subMenuC0148r;
        if (aVar == null) {
            return false;
        }
        if (subMenuC0148r == null) {
            subMenuC0148r2 = this.f496d;
        }
        return aVar.mo352a(subMenuC0148r2);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
        this.f499g = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        ViewGroup viewGroup = (ViewGroup) this.f502j;
        if (viewGroup == null) {
            return;
        }
        C0137g c0137g = this.f496d;
        int i9 = 0;
        if (c0137g != null) {
            c0137g.flagActionItems();
            ArrayList<C0139i> visibleItems = this.f496d.getVisibleItems();
            int size = visibleItems.size();
            int i10 = 0;
            for (int i11 = 0; i11 < size; i11++) {
                C0139i c0139i = visibleItems.get(i11);
                if (mo482j(i10, c0139i)) {
                    View childAt = viewGroup.getChildAt(i10);
                    C0139i itemData = childAt instanceof InterfaceC0144n.a ? ((InterfaceC0144n.a) childAt).getItemData() : null;
                    View viewMo479g = mo479g(c0139i, childAt, viewGroup);
                    if (c0139i != itemData) {
                        viewMo479g.setPressed(false);
                        viewMo479g.jumpDrawablesToCurrentState();
                    }
                    if (viewMo479g != childAt) {
                        m474b(viewMo479g, i10);
                    }
                    i10++;
                }
            }
            i9 = i10;
        }
        while (i9 < viewGroup.getChildCount()) {
            if (!mo477e(viewGroup, i9)) {
                i9++;
            }
        }
    }
}
