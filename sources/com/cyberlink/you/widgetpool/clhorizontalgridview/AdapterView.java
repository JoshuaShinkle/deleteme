package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Adapter;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;

/* loaded from: classes.dex */
public abstract class AdapterView<T extends Adapter> extends ViewGroup {

    /* renamed from: b */
    @ViewDebug.ExportedProperty(category = "scrolling")
    public int f14960b;

    /* renamed from: c */
    public int f14961c;

    /* renamed from: d */
    public int f14962d;

    /* renamed from: e */
    public long f14963e;

    /* renamed from: f */
    public long f14964f;

    /* renamed from: g */
    public boolean f14965g;

    /* renamed from: h */
    public int f14966h;

    /* renamed from: i */
    public int f14967i;

    /* renamed from: j */
    public boolean f14968j;

    /* renamed from: k */
    public InterfaceC3229e f14969k;

    /* renamed from: l */
    public boolean f14970l;

    /* renamed from: m */
    @ViewDebug.ExportedProperty(category = "list")
    public int f14971m;

    /* renamed from: n */
    public long f14972n;

    /* renamed from: o */
    @ViewDebug.ExportedProperty(category = "list")
    public int f14973o;

    /* renamed from: p */
    public long f14974p;

    /* renamed from: q */
    public View f14975q;

    /* renamed from: r */
    @ViewDebug.ExportedProperty(category = "list")
    public int f14976r;

    /* renamed from: s */
    public int f14977s;

    /* renamed from: t */
    public int f14978t;

    /* renamed from: u */
    public long f14979u;

    /* renamed from: v */
    public boolean f14980v;

    /* renamed from: w */
    public boolean f14981w;

    /* renamed from: x */
    public AdapterView<T>.RunnableC3232h f14982x;

    /* renamed from: y */
    public boolean f14983y;

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$b */
    public static class ContextMenuContextMenuInfoC3226b implements ContextMenu.ContextMenuInfo {

        /* renamed from: a */
        public View f14984a;

        /* renamed from: b */
        public int f14985b;

        /* renamed from: c */
        public long f14986c;

        public ContextMenuContextMenuInfoC3226b(View view, int i9, long j9) {
            this.f14984a = view;
            this.f14985b = i9;
            this.f14986c = j9;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$c */
    public class C3227c extends DataSetObserver {

        /* renamed from: a */
        public Parcelable f14987a = null;

        public C3227c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
        @Override // android.database.DataSetObserver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onChanged() {
            Parcelable parcelable;
            AdapterView adapterView = AdapterView.this;
            adapterView.f14970l = true;
            adapterView.f14977s = adapterView.f14976r;
            adapterView.f14976r = adapterView.getAdapter().getCount();
            if (!AdapterView.this.getAdapter().hasStableIds() || (parcelable = this.f14987a) == null) {
                AdapterView.this.m17207q();
            } else {
                AdapterView adapterView2 = AdapterView.this;
                if (adapterView2.f14977s == 0 && adapterView2.f14976r > 0) {
                    adapterView2.onRestoreInstanceState(parcelable);
                    this.f14987a = null;
                }
            }
            AdapterView.this.m17197f();
            AdapterView.this.requestLayout();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            AdapterView adapterView = AdapterView.this;
            adapterView.f14970l = true;
            if (adapterView.getAdapter().hasStableIds()) {
                this.f14987a = AdapterView.this.onSaveInstanceState();
            }
            AdapterView adapterView2 = AdapterView.this;
            adapterView2.f14977s = adapterView2.f14976r;
            adapterView2.f14976r = 0;
            adapterView2.f14973o = -1;
            adapterView2.f14974p = Long.MIN_VALUE;
            adapterView2.f14971m = -1;
            adapterView2.f14972n = Long.MIN_VALUE;
            adapterView2.f14965g = false;
            adapterView2.m17197f();
            AdapterView.this.requestLayout();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$d */
    public class C3228d extends C4613a {
        public C3228d() {
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(AdapterView.class.getName());
            accessibilityEvent.setScrollable(AdapterView.this.m17204m());
            View selectedView = AdapterView.this.getSelectedView();
            if (selectedView != null) {
                accessibilityEvent.setEnabled(selectedView.isEnabled());
            }
            accessibilityEvent.setCurrentItemIndex(AdapterView.this.getSelectedItemPosition());
            accessibilityEvent.setFromIndex(AdapterView.this.getFirstVisiblePosition());
            accessibilityEvent.setToIndex(AdapterView.this.getLastVisiblePosition());
            accessibilityEvent.setItemCount(AdapterView.this.getCount());
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            c4704m.m18781U(AdapterView.class.getName());
            c4704m.m18814o0(AdapterView.this.m17204m());
            View selectedView = AdapterView.this.getSelectedView();
            if (selectedView != null) {
                c4704m.m18790b0(selectedView.isEnabled());
            }
        }

        @Override // p042d0.C4613a
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (!super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent)) {
                return false;
            }
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
            onInitializeAccessibilityEvent(viewGroup, accessibilityEventObtain);
            view.dispatchPopulateAccessibilityEvent(accessibilityEventObtain);
            accessibilityEvent.appendRecord(accessibilityEventObtain);
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$e */
    public interface InterfaceC3229e {
        /* renamed from: a */
        void mo17210a(AdapterView<?> adapterView, View view, int i9, long j9);
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$f */
    public interface InterfaceC3230f {
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$g */
    public interface InterfaceC3231g {
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView$h */
    public class RunnableC3232h implements Runnable {
        public RunnableC3232h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AdapterView adapterView = AdapterView.this;
            if (!adapterView.f14970l) {
                adapterView.m17200i();
                AdapterView.this.m17206o();
            } else if (adapterView.getAdapter() != null) {
                AdapterView.this.post(this);
            }
        }
    }

    public AdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14960b = 0;
        this.f14963e = Long.MIN_VALUE;
        this.f14965g = false;
        this.f14968j = false;
        this.f14971m = -1;
        this.f14972n = Long.MIN_VALUE;
        this.f14973o = -1;
        this.f14974p = Long.MIN_VALUE;
        this.f14978t = -1;
        this.f14979u = Long.MIN_VALUE;
        this.f14983y = false;
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    @Override // android.view.ViewGroup
    public boolean canAnimate() {
        return super.canAnimate() && this.f14976r > 0;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        View selectedView = getSelectedView();
        return selectedView != null && selectedView.getVisibility() == 0 && selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    /* renamed from: f */
    public void m17197f() {
        Adapter adapter = getAdapter();
        boolean z8 = !(adapter == null || adapter.getCount() == 0) || m17203l();
        super.setFocusableInTouchMode(z8 && this.f14981w);
        super.setFocusable(z8 && this.f14980v);
        if (this.f14975q != null) {
            m17209s(adapter == null || adapter.isEmpty());
        }
    }

    /* renamed from: g */
    public void m17198g() {
        if (this.f14973o == this.f14978t && this.f14974p == this.f14979u) {
            return;
        }
        m17208r();
        this.f14978t = this.f14973o;
        this.f14979u = this.f14974p;
    }

    public abstract T getAdapter();

    @ViewDebug.CapturedViewProperty
    public int getCount() {
        return this.f14976r;
    }

    public View getEmptyView() {
        return this.f14975q;
    }

    public int getFirstVisiblePosition() {
        return this.f14960b;
    }

    public int getLastVisiblePosition() {
        return (this.f14960b + getChildCount()) - 1;
    }

    public final InterfaceC3229e getOnItemClickListener() {
        return this.f14969k;
    }

    public final InterfaceC3230f getOnItemLongClickListener() {
        return null;
    }

    public final InterfaceC3231g getOnItemSelectedListener() {
        return null;
    }

    public Object getSelectedItem() {
        Adapter adapter = getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        if (adapter == null || adapter.getCount() <= 0 || selectedItemPosition < 0) {
            return null;
        }
        return adapter.getItem(selectedItemPosition);
    }

    @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
        return this.f14972n;
    }

    @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.f14971m;
    }

    public abstract View getSelectedView();

    /* renamed from: h */
    public int m17199h() {
        int i9 = this.f14976r;
        if (i9 == 0) {
            return -1;
        }
        long j9 = this.f14963e;
        int i10 = this.f14962d;
        if (j9 == Long.MIN_VALUE) {
            return -1;
        }
        int i11 = i9 - 1;
        int iMin = Math.min(i11, Math.max(0, i10));
        long jUptimeMillis = SystemClock.uptimeMillis() + 100;
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return -1;
        }
        int i12 = iMin;
        int i13 = i12;
        boolean z8 = false;
        while (SystemClock.uptimeMillis() <= jUptimeMillis) {
            if (adapter.getItemId(iMin) != j9) {
                boolean z9 = i12 == i11;
                boolean z10 = i13 == 0;
                if (z9 && z10) {
                    break;
                }
                if (z10 || (z8 && !z9)) {
                    i12++;
                    z8 = false;
                    iMin = i12;
                } else if (z9 || (!z8 && !z10)) {
                    i13--;
                    z8 = true;
                    iMin = i13;
                }
            } else {
                return iMin;
            }
        }
        return -1;
    }

    /* renamed from: i */
    public final void m17200i() {
    }

    /* renamed from: j */
    public long m17201j(int i9) {
        Adapter adapter = getAdapter();
        if (adapter == null || i9 < 0) {
            return Long.MIN_VALUE;
        }
        return adapter.getItemId(i9);
    }

    /* renamed from: k */
    public int m17202k(View view) {
        while (true) {
            try {
                View view2 = (View) view.getParent();
                if (view2.equals(this)) {
                    break;
                }
                view = view2;
            } catch (ClassCastException unused) {
            }
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            if (getChildAt(i9).equals(view)) {
                return this.f14960b + i9;
            }
        }
        return -1;
    }

    /* renamed from: l */
    boolean m17203l() {
        return false;
    }

    /* renamed from: m */
    public final boolean m17204m() {
        int count;
        Adapter adapter = getAdapter();
        if (adapter == null || (count = adapter.getCount()) <= 0) {
            return false;
        }
        return getFirstVisiblePosition() > 0 || getLastVisiblePosition() < count - 1;
    }

    /* renamed from: n */
    public int mo17205n(int i9, boolean z8) {
        return i9;
    }

    /* renamed from: o */
    public final void m17206o() {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled() && getSelectedItemPosition() >= 0) {
            sendAccessibilityEvent(4);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f14982x);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        this.f14967i = getHeight();
    }

    /* renamed from: p */
    public boolean mo17149p(View view, int i9, long j9) {
        if (this.f14969k == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.f14969k.mo17210a(this, view, i9, j9);
        return true;
    }

    /* renamed from: q */
    public void m17207q() {
        if (getChildCount() > 0) {
            this.f14965g = true;
            this.f14964f = this.f14967i;
            int i9 = this.f14973o;
            if (i9 >= 0) {
                View childAt = getChildAt(i9 - this.f14960b);
                this.f14963e = this.f14972n;
                this.f14962d = this.f14971m;
                if (childAt != null) {
                    this.f14961c = childAt.getLeft();
                }
                this.f14966h = 0;
                return;
            }
            View childAt2 = getChildAt(0);
            Adapter adapter = getAdapter();
            int i10 = this.f14960b;
            if (i10 < 0 || i10 >= adapter.getCount()) {
                this.f14963e = -1L;
            } else {
                this.f14963e = adapter.getItemId(this.f14960b);
            }
            this.f14962d = this.f14960b;
            if (childAt2 != null) {
                this.f14961c = childAt2.getLeft();
            }
            this.f14966h = 1;
        }
    }

    /* renamed from: r */
    public void m17208r() {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            if (!this.f14968j && !this.f14983y) {
                m17200i();
                m17206o();
            } else {
                if (this.f14982x == null) {
                    this.f14982x = new RunnableC3232h();
                }
                post(this.f14982x);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i9) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: s */
    public final void m17209s(boolean z8) {
        if (m17203l()) {
            z8 = false;
        }
        if (!z8) {
            View view = this.f14975q;
            if (view != null) {
                view.setVisibility(8);
            }
            setVisibility(0);
            return;
        }
        View view2 = this.f14975q;
        if (view2 != null) {
            view2.setVisibility(0);
            setVisibility(8);
        } else {
            setVisibility(0);
        }
        if (this.f14970l) {
            onLayout(false, getLeft(), getTop(), getRight(), getBottom());
        }
    }

    public abstract void setAdapter(T t8);

    @Override // android.view.View
    public void setFocusable(boolean z8) {
        Adapter adapter = getAdapter();
        boolean z9 = true;
        boolean z10 = adapter == null || adapter.getCount() == 0;
        this.f14980v = z8;
        if (!z8) {
            this.f14981w = false;
        }
        if (!z8 || (z10 && !m17203l())) {
            z9 = false;
        }
        super.setFocusable(z9);
    }

    @Override // android.view.View
    public void setFocusableInTouchMode(boolean z8) {
        Adapter adapter = getAdapter();
        boolean z9 = false;
        boolean z10 = adapter == null || adapter.getCount() == 0;
        this.f14981w = z8;
        if (z8) {
            this.f14980v = true;
        }
        if (z8 && (!z10 || m17203l())) {
            z9 = true;
        }
        super.setFocusableInTouchMode(z9);
    }

    public void setNextSelectedPositionInt(int i9) {
        this.f14971m = i9;
        long jM17201j = m17201j(i9);
        this.f14972n = jM17201j;
        if (this.f14965g && this.f14966h == 0 && i9 >= 0) {
            this.f14962d = i9;
            this.f14963e = jM17201j;
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(InterfaceC3229e interfaceC3229e) {
        this.f14969k = interfaceC3229e;
    }

    public void setOnItemLongClickListener(InterfaceC3230f interfaceC3230f) {
        if (isLongClickable()) {
            return;
        }
        setLongClickable(true);
    }

    public void setOnItemSelectedListener(InterfaceC3231g interfaceC3231g) {
    }

    public void setSelectedPositionInt(int i9) {
        this.f14973o = i9;
        this.f14974p = m17201j(i9);
    }

    public abstract void setSelection(int i9);

    @Override // android.view.ViewGroup
    public void addView(View view, int i9) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public AdapterView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f14960b = 0;
        this.f14963e = Long.MIN_VALUE;
        this.f14965g = false;
        this.f14968j = false;
        this.f14971m = -1;
        this.f14972n = Long.MIN_VALUE;
        this.f14973o = -1;
        this.f14974p = Long.MIN_VALUE;
        this.f14978t = -1;
        this.f14979u = Long.MIN_VALUE;
        this.f14983y = false;
        C4647u.m18530Z(this, new C3228d());
        if (C4647u.m18563q(this) == 0) {
            C4647u.m18548i0(this, 1);
        }
    }
}
