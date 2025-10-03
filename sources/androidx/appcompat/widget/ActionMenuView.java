package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements C0137g.b, InterfaceC0144n {

    /* renamed from: b */
    public C0137g f736b;

    /* renamed from: c */
    public Context f737c;

    /* renamed from: d */
    public int f738d;

    /* renamed from: e */
    public boolean f739e;

    /* renamed from: f */
    public ActionMenuPresenter f740f;

    /* renamed from: g */
    public InterfaceC0143m.a f741g;

    /* renamed from: h */
    public C0137g.a f742h;

    /* renamed from: i */
    public boolean f743i;

    /* renamed from: j */
    public int f744j;

    /* renamed from: k */
    public int f745k;

    /* renamed from: l */
    public int f746l;

    /* renamed from: m */
    public InterfaceC0167e f747m;

    /* renamed from: androidx.appcompat.widget.ActionMenuView$a */
    public interface InterfaceC0163a {
        /* renamed from: a */
        boolean mo453a();

        /* renamed from: b */
        boolean mo454b();
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuView$b */
    public static class C0164b implements InterfaceC0143m.a {
        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        /* renamed from: a */
        public boolean mo352a(C0137g c0137g) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        public void onCloseMenu(C0137g c0137g, boolean z8) {
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuView$c */
    public static class C0165c extends LinearLayoutCompat.C0183a {

        /* renamed from: c */
        @ViewDebug.ExportedProperty
        public boolean f748c;

        /* renamed from: d */
        @ViewDebug.ExportedProperty
        public int f749d;

        /* renamed from: e */
        @ViewDebug.ExportedProperty
        public int f750e;

        /* renamed from: f */
        @ViewDebug.ExportedProperty
        public boolean f751f;

        /* renamed from: g */
        @ViewDebug.ExportedProperty
        public boolean f752g;

        /* renamed from: h */
        public boolean f753h;

        public C0165c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C0165c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public C0165c(C0165c c0165c) {
            super(c0165c);
            this.f748c = c0165c.f748c;
        }

        public C0165c(int i9, int i10) {
            super(i9, i10);
            this.f748c = false;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuView$d */
    public class C0166d implements C0137g.a {
        public C0166d() {
        }

        @Override // androidx.appcompat.view.menu.C0137g.a
        public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
            InterfaceC0167e interfaceC0167e = ActionMenuView.this.f747m;
            return interfaceC0167e != null && interfaceC0167e.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.C0137g.a
        public void onMenuModeChange(C0137g c0137g) {
            C0137g.a aVar = ActionMenuView.this.f742h;
            if (aVar != null) {
                aVar.onMenuModeChange(c0137g);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuView$e */
    public interface InterfaceC0167e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int m633l(View view, int i9, int i10, int i11, int i12) {
        int i13;
        C0165c c0165c = (C0165c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i11) - i12, View.MeasureSpec.getMode(i11));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z8 = actionMenuItemView != null && actionMenuItemView.m455d();
        if (i10 > 0) {
            i13 = 2;
            if (!z8 || i10 >= 2) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i10 * i9, Integer.MIN_VALUE), iMakeMeasureSpec);
                int measuredWidth = view.getMeasuredWidth();
                int i14 = measuredWidth / i9;
                if (measuredWidth % i9 != 0) {
                    i14++;
                }
                if (!z8 || i14 >= 2) {
                    i13 = i14;
                }
            } else {
                i13 = 0;
            }
        }
        c0165c.f751f = !c0165c.f748c && z8;
        c0165c.f749d = i13;
        view.measure(View.MeasureSpec.makeMeasureSpec(i9 * i13, 1073741824), iMakeMeasureSpec);
        return i13;
    }

    @Override // androidx.appcompat.view.menu.C0137g.b
    /* renamed from: a */
    public boolean mo461a(C0139i c0139i) {
        return this.f736b.performItemAction(c0139i, 0);
    }

    /* renamed from: b */
    public void m634b() {
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.m621r();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C0165c generateDefaultLayoutParams() {
        C0165c c0165c = new C0165c(-2, -2);
        c0165c.f850b = 16;
        return c0165c;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0165c;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public C0165c generateLayoutParams(AttributeSet attributeSet) {
        return new C0165c(getContext(), attributeSet);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public C0165c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        C0165c c0165c = layoutParams instanceof C0165c ? new C0165c((C0165c) layoutParams) : new C0165c(layoutParams);
        if (c0165c.f850b <= 0) {
            c0165c.f850b = 16;
        }
        return c0165c;
    }

    /* renamed from: f */
    public C0165c m638f() {
        C0165c c0165cGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        c0165cGenerateDefaultLayoutParams.f748c = true;
        return c0165cGenerateDefaultLayoutParams;
    }

    /* renamed from: g */
    public boolean m639g(int i9) {
        boolean zMo453a = false;
        if (i9 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i9 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i9);
        if (i9 < getChildCount() && (childAt instanceof InterfaceC0163a)) {
            zMo453a = false | ((InterfaceC0163a) childAt).mo453a();
        }
        return (i9 <= 0 || !(childAt2 instanceof InterfaceC0163a)) ? zMo453a : zMo453a | ((InterfaceC0163a) childAt2).mo454b();
    }

    public Menu getMenu() {
        if (this.f736b == null) {
            Context context = getContext();
            C0137g c0137g = new C0137g(context);
            this.f736b = c0137g;
            c0137g.setCallback(new C0166d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.f740f = actionMenuPresenter;
            actionMenuPresenter.m618C(true);
            ActionMenuPresenter actionMenuPresenter2 = this.f740f;
            InterfaceC0143m.a c0164b = this.f741g;
            if (c0164b == null) {
                c0164b = new C0164b();
            }
            actionMenuPresenter2.setCallback(c0164b);
            this.f736b.addMenuPresenter(this.f740f, this.f737c);
            this.f740f.m616A(this);
        }
        return this.f736b;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f740f.m623t();
    }

    public int getPopupTheme() {
        return this.f738d;
    }

    public int getWindowAnimations() {
        return 0;
    }

    /* renamed from: h */
    public boolean m640h() {
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        return actionMenuPresenter != null && actionMenuPresenter.m624u();
    }

    /* renamed from: i */
    public boolean m641i() {
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        return actionMenuPresenter != null && actionMenuPresenter.m626w();
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n
    public void initialize(C0137g c0137g) {
        this.f736b = c0137g;
    }

    /* renamed from: j */
    public boolean m642j() {
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        return actionMenuPresenter != null && actionMenuPresenter.m627x();
    }

    /* renamed from: k */
    public boolean m643k() {
        return this.f739e;
    }

    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* renamed from: m */
    public final void m644m(int i9, int i10) {
        int i11;
        int i12;
        boolean z8;
        int i13;
        int i14;
        boolean z9;
        boolean z10;
        int i15;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingTop, -2);
        int i16 = size - paddingLeft;
        int i17 = this.f745k;
        int i18 = i16 / i17;
        int i19 = i16 % i17;
        if (i18 == 0) {
            setMeasuredDimension(i16, 0);
            return;
        }
        int i20 = i17 + (i19 / i18);
        int childCount = getChildCount();
        int iMax = 0;
        int i21 = 0;
        boolean z11 = false;
        int i22 = 0;
        int iMax2 = 0;
        int i23 = 0;
        long j9 = 0;
        while (i21 < childCount) {
            View childAt = getChildAt(i21);
            int i24 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z12 = childAt instanceof ActionMenuItemView;
                int i25 = i22 + 1;
                if (z12) {
                    int i26 = this.f746l;
                    i15 = i25;
                    r14 = 0;
                    childAt.setPadding(i26, 0, i26, 0);
                } else {
                    i15 = i25;
                    r14 = 0;
                }
                C0165c c0165c = (C0165c) childAt.getLayoutParams();
                c0165c.f753h = r14;
                c0165c.f750e = r14;
                c0165c.f749d = r14;
                c0165c.f751f = r14;
                ((ViewGroup.MarginLayoutParams) c0165c).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) c0165c).rightMargin = r14;
                c0165c.f752g = z12 && ((ActionMenuItemView) childAt).m455d();
                int iM633l = m633l(childAt, i20, c0165c.f748c ? 1 : i18, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iM633l);
                if (c0165c.f751f) {
                    i23++;
                }
                if (c0165c.f748c) {
                    z11 = true;
                }
                i18 -= iM633l;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iM633l == 1) {
                    j9 |= 1 << i21;
                    iMax = iMax;
                }
                i22 = i15;
            }
            i21++;
            size2 = i24;
        }
        int i27 = size2;
        boolean z13 = z11 && i22 == 2;
        boolean z14 = false;
        while (i23 > 0 && i18 > 0) {
            int i28 = Integer.MAX_VALUE;
            int i29 = 0;
            int i30 = 0;
            long j10 = 0;
            while (i30 < childCount) {
                boolean z15 = z14;
                C0165c c0165c2 = (C0165c) getChildAt(i30).getLayoutParams();
                int i31 = iMax;
                if (c0165c2.f751f) {
                    int i32 = c0165c2.f749d;
                    if (i32 < i28) {
                        j10 = 1 << i30;
                        i28 = i32;
                        i29 = 1;
                    } else if (i32 == i28) {
                        i29++;
                        j10 |= 1 << i30;
                    }
                }
                i30++;
                iMax = i31;
                z14 = z15;
            }
            z8 = z14;
            i13 = iMax;
            j9 |= j10;
            if (i29 > i18) {
                i11 = mode;
                i12 = i16;
                break;
            }
            int i33 = i28 + 1;
            int i34 = 0;
            while (i34 < childCount) {
                View childAt2 = getChildAt(i34);
                C0165c c0165c3 = (C0165c) childAt2.getLayoutParams();
                int i35 = i16;
                int i36 = mode;
                long j11 = 1 << i34;
                if ((j10 & j11) == 0) {
                    if (c0165c3.f749d == i33) {
                        j9 |= j11;
                    }
                    z10 = z13;
                } else {
                    if (z13 && c0165c3.f752g && i18 == 1) {
                        int i37 = this.f746l;
                        z10 = z13;
                        childAt2.setPadding(i37 + i20, 0, i37, 0);
                    } else {
                        z10 = z13;
                    }
                    c0165c3.f749d++;
                    c0165c3.f753h = true;
                    i18--;
                }
                i34++;
                mode = i36;
                i16 = i35;
                z13 = z10;
            }
            iMax = i13;
            z14 = true;
        }
        i11 = mode;
        i12 = i16;
        z8 = z14;
        i13 = iMax;
        boolean z16 = !z11 && i22 == 1;
        if (i18 <= 0 || j9 == 0 || (i18 >= i22 - 1 && !z16 && iMax2 <= 1)) {
            i14 = 0;
            z9 = z8;
        } else {
            float fBitCount = Long.bitCount(j9);
            if (z16) {
                i14 = 0;
            } else {
                i14 = 0;
                if ((j9 & 1) != 0 && !((C0165c) getChildAt(0).getLayoutParams()).f752g) {
                    fBitCount -= 0.5f;
                }
                int i38 = childCount - 1;
                if ((j9 & (1 << i38)) != 0 && !((C0165c) getChildAt(i38).getLayoutParams()).f752g) {
                    fBitCount -= 0.5f;
                }
            }
            int i39 = fBitCount > BitmapDescriptorFactory.HUE_RED ? (int) ((i18 * i20) / fBitCount) : i14;
            z9 = z8;
            for (int i40 = i14; i40 < childCount; i40++) {
                if ((j9 & (1 << i40)) != 0) {
                    View childAt3 = getChildAt(i40);
                    C0165c c0165c4 = (C0165c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        c0165c4.f750e = i39;
                        c0165c4.f753h = true;
                        if (i40 == 0 && !c0165c4.f752g) {
                            ((ViewGroup.MarginLayoutParams) c0165c4).leftMargin = (-i39) / 2;
                        }
                        z9 = true;
                    } else if (c0165c4.f748c) {
                        c0165c4.f750e = i39;
                        c0165c4.f753h = true;
                        ((ViewGroup.MarginLayoutParams) c0165c4).rightMargin = (-i39) / 2;
                        z9 = true;
                    } else {
                        if (i40 != 0) {
                            ((ViewGroup.MarginLayoutParams) c0165c4).leftMargin = i39 / 2;
                        }
                        if (i40 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) c0165c4).rightMargin = i39 / 2;
                        }
                    }
                }
            }
        }
        if (z9) {
            for (int i41 = i14; i41 < childCount; i41++) {
                View childAt4 = getChildAt(i41);
                C0165c c0165c5 = (C0165c) childAt4.getLayoutParams();
                if (c0165c5.f753h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((c0165c5.f749d * i20) + c0165c5.f750e, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i12, i11 != 1073741824 ? i13 : i27);
    }

    /* renamed from: n */
    public C0137g m645n() {
        return this.f736b;
    }

    /* renamed from: o */
    public void m646o(InterfaceC0143m.a aVar, C0137g.a aVar2) {
        this.f741g = aVar;
        this.f742h = aVar2;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
            if (this.f740f.m627x()) {
                this.f740f.m624u();
                this.f740f.m619D();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m634b();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int width;
        int paddingLeft;
        if (!this.f743i) {
            super.onLayout(z8, i9, i10, i11, i12);
            return;
        }
        int childCount = getChildCount();
        int i13 = (i12 - i10) / 2;
        int dividerWidth = getDividerWidth();
        int i14 = i11 - i9;
        int paddingRight = (i14 - getPaddingRight()) - getPaddingLeft();
        boolean zM1068b = C0258u0.m1068b(this);
        int i15 = 0;
        int i16 = 0;
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() != 8) {
                C0165c c0165c = (C0165c) childAt.getLayoutParams();
                if (c0165c.f748c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (m639g(i17)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zM1068b) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0165c).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) c0165c).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i18 = i13 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i18, width, measuredHeight + i18);
                    paddingRight -= measuredWidth;
                    i15 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0165c).leftMargin) + ((ViewGroup.MarginLayoutParams) c0165c).rightMargin;
                    m639g(i17);
                    i16++;
                }
            }
        }
        if (childCount == 1 && i15 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i19 = (i14 / 2) - (measuredWidth2 / 2);
            int i20 = i13 - (measuredHeight2 / 2);
            childAt2.layout(i19, i20, measuredWidth2 + i19, measuredHeight2 + i20);
            return;
        }
        int i21 = i16 - (i15 ^ 1);
        int iMax = Math.max(0, i21 > 0 ? paddingRight / i21 : 0);
        if (zM1068b) {
            int width2 = getWidth() - getPaddingRight();
            for (int i22 = 0; i22 < childCount; i22++) {
                View childAt3 = getChildAt(i22);
                C0165c c0165c2 = (C0165c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !c0165c2.f748c) {
                    int i23 = width2 - ((ViewGroup.MarginLayoutParams) c0165c2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i24 = i13 - (measuredHeight3 / 2);
                    childAt3.layout(i23 - measuredWidth3, i24, i23, measuredHeight3 + i24);
                    width2 = i23 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) c0165c2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i25 = 0; i25 < childCount; i25++) {
            View childAt4 = getChildAt(i25);
            C0165c c0165c3 = (C0165c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !c0165c3.f748c) {
                int i26 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) c0165c3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i27 = i13 - (measuredHeight4 / 2);
                childAt4.layout(i26, i27, i26 + measuredWidth4, measuredHeight4 + i27);
                paddingLeft2 = i26 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) c0165c3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i9, int i10) {
        C0137g c0137g;
        boolean z8 = this.f743i;
        boolean z9 = View.MeasureSpec.getMode(i9) == 1073741824;
        this.f743i = z9;
        if (z8 != z9) {
            this.f744j = 0;
        }
        int size = View.MeasureSpec.getSize(i9);
        if (this.f743i && (c0137g = this.f736b) != null && size != this.f744j) {
            this.f744j = size;
            c0137g.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.f743i && childCount > 0) {
            m644m(i9, i10);
            return;
        }
        for (int i11 = 0; i11 < childCount; i11++) {
            C0165c c0165c = (C0165c) getChildAt(i11).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) c0165c).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) c0165c).leftMargin = 0;
        }
        super.onMeasure(i9, i10);
    }

    /* renamed from: p */
    public boolean m647p() {
        ActionMenuPresenter actionMenuPresenter = this.f740f;
        return actionMenuPresenter != null && actionMenuPresenter.m619D();
    }

    public void setExpandedActionViewsExclusive(boolean z8) {
        this.f740f.m629z(z8);
    }

    public void setOnMenuItemClickListener(InterfaceC0167e interfaceC0167e) {
        this.f747m = interfaceC0167e;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f740f.m617B(drawable);
    }

    public void setOverflowReserved(boolean z8) {
        this.f739e = z8;
    }

    public void setPopupTheme(int i9) {
        if (this.f738d != i9) {
            this.f738d = i9;
            if (i9 == 0) {
                this.f737c = getContext();
            } else {
                this.f737c = new ContextThemeWrapper(getContext(), i9);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f740f = actionMenuPresenter;
        actionMenuPresenter.m616A(this);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f9 = context.getResources().getDisplayMetrics().density;
        this.f745k = (int) (56.0f * f9);
        this.f746l = (int) (f9 * 4.0f);
        this.f737c = context;
        this.f738d = 0;
    }
}
