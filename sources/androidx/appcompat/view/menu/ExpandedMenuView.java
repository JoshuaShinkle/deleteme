package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.widget.C0250q0;

/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements C0137g.b, InterfaceC0144n, AdapterView.OnItemClickListener {

    /* renamed from: d */
    public static final int[] f454d = {R.attr.background, R.attr.divider};

    /* renamed from: b */
    public C0137g f455b;

    /* renamed from: c */
    public int f456c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // androidx.appcompat.view.menu.C0137g.b
    /* renamed from: a */
    public boolean mo461a(C0139i c0139i) {
        return this.f455b.performItemAction(c0139i, 0);
    }

    public int getWindowAnimations() {
        return this.f456c;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n
    public void initialize(C0137g c0137g) {
        this.f455b = c0137g;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i9, long j9) {
        mo461a((C0139i) getAdapter().getItem(i9));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, f454d, i9, 0);
        if (c0250q0M1004v.m1023s(0)) {
            setBackgroundDrawable(c0250q0M1004v.m1011g(0));
        }
        if (c0250q0M1004v.m1023s(1)) {
            setDivider(c0250q0M1004v.m1011g(1));
        }
        c0250q0M1004v.m1024w();
    }
}
