package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.view.menu.InterfaceC0143m;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p010b.C0560a;
import p010b.C0565f;
import p042d0.C4628h0;
import p042d0.C4642p;
import p042d0.C4647u;
import p042d0.InterfaceC4639n;
import p042d0.InterfaceC4641o;
import p215v.C6429c;

@SuppressLint({"UnknownNullness"})
/* loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements InterfaceC0249q, InterfaceC4639n, InterfaceC4641o {

    /* renamed from: G */
    public static final int[] f671G = {C0560a.actionBarSize, R.attr.windowContentOverlay};

    /* renamed from: A */
    public OverScroller f672A;

    /* renamed from: B */
    public ViewPropertyAnimator f673B;

    /* renamed from: C */
    public final AnimatorListenerAdapter f674C;

    /* renamed from: D */
    public final Runnable f675D;

    /* renamed from: E */
    public final Runnable f676E;

    /* renamed from: F */
    public final C4642p f677F;

    /* renamed from: b */
    public int f678b;

    /* renamed from: c */
    public int f679c;

    /* renamed from: d */
    public ContentFrameLayout f680d;

    /* renamed from: e */
    public ActionBarContainer f681e;

    /* renamed from: f */
    public InterfaceC0251r f682f;

    /* renamed from: g */
    public Drawable f683g;

    /* renamed from: h */
    public boolean f684h;

    /* renamed from: i */
    public boolean f685i;

    /* renamed from: j */
    public boolean f686j;

    /* renamed from: k */
    public boolean f687k;

    /* renamed from: l */
    public boolean f688l;

    /* renamed from: m */
    public int f689m;

    /* renamed from: n */
    public int f690n;

    /* renamed from: o */
    public final Rect f691o;

    /* renamed from: p */
    public final Rect f692p;

    /* renamed from: q */
    public final Rect f693q;

    /* renamed from: r */
    public final Rect f694r;

    /* renamed from: s */
    public final Rect f695s;

    /* renamed from: t */
    public final Rect f696t;

    /* renamed from: u */
    public final Rect f697u;

    /* renamed from: v */
    public C4628h0 f698v;

    /* renamed from: w */
    public C4628h0 f699w;

    /* renamed from: x */
    public C4628h0 f700x;

    /* renamed from: y */
    public C4628h0 f701y;

    /* renamed from: z */
    public InterfaceC0154d f702z;

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$a */
    public class C0151a extends AnimatorListenerAdapter {
        public C0151a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f673B = null;
            actionBarOverlayLayout.f688l = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f673B = null;
            actionBarOverlayLayout.f688l = false;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$b */
    public class RunnableC0152b implements Runnable {
        public RunnableC0152b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.m601p();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f673B = actionBarOverlayLayout.f681e.animate().translationY(BitmapDescriptorFactory.HUE_RED).setListener(ActionBarOverlayLayout.this.f674C);
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$c */
    public class RunnableC0153c implements Runnable {
        public RunnableC0153c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.m601p();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f673B = actionBarOverlayLayout.f681e.animate().translationY(-ActionBarOverlayLayout.this.f681e.getHeight()).setListener(ActionBarOverlayLayout.this.f674C);
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$d */
    public interface InterfaceC0154d {
        /* renamed from: a */
        void mo427a();

        /* renamed from: b */
        void mo428b();

        /* renamed from: c */
        void mo429c(boolean z8);

        /* renamed from: d */
        void mo430d();

        /* renamed from: e */
        void mo431e();

        void onWindowVisibilityChanged(int i9);
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$e */
    public static class C0155e extends ViewGroup.MarginLayoutParams {
        public C0155e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C0155e(int i9, int i10) {
            super(i9, i10);
        }

        public C0155e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f679c = 0;
        this.f691o = new Rect();
        this.f692p = new Rect();
        this.f693q = new Rect();
        this.f694r = new Rect();
        this.f695s = new Rect();
        this.f696t = new Rect();
        this.f697u = new Rect();
        C4628h0 c4628h0 = C4628h0.f16248b;
        this.f698v = c4628h0;
        this.f699w = c4628h0;
        this.f700x = c4628h0;
        this.f701y = c4628h0;
        this.f674C = new C0151a();
        this.f675D = new RunnableC0152b();
        this.f676E = new RunnableC0153c();
        m602q(context);
        this.f677F = new C4642p(this);
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: a */
    public void mo586a(Menu menu, InterfaceC0143m.a aVar) {
        m606u();
        this.f682f.mo1025a(menu, aVar);
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: b */
    public boolean mo587b() {
        m606u();
        return this.f682f.mo1026b();
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: c */
    public void mo588c() {
        m606u();
        this.f682f.mo1027c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0155e;
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: d */
    public boolean mo589d() {
        m606u();
        return this.f682f.mo1028d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f683g == null || this.f684h) {
            return;
        }
        int bottom = this.f681e.getVisibility() == 0 ? (int) (this.f681e.getBottom() + this.f681e.getTranslationY() + 0.5f) : 0;
        this.f683g.setBounds(0, bottom, getWidth(), this.f683g.getIntrinsicHeight() + bottom);
        this.f683g.draw(canvas);
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: e */
    public boolean mo590e() {
        m606u();
        return this.f682f.mo1029e();
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: f */
    public boolean mo591f() {
        m606u();
        return this.f682f.mo1030f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: g */
    public boolean mo592g() {
        m606u();
        return this.f682f.mo1031g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f681e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f677F.m18495a();
    }

    public CharSequence getTitle() {
        m606u();
        return this.f682f.getTitle();
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: h */
    public void mo593h(int i9) {
        m606u();
        if (i9 == 2) {
            this.f682f.mo1044t();
        } else if (i9 == 5) {
            this.f682f.mo1045u();
        } else {
            if (i9 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    /* renamed from: i */
    public void mo594i() {
        m606u();
        this.f682f.mo1032h();
    }

    @Override // p042d0.InterfaceC4641o
    /* renamed from: j */
    public void mo595j(View view, int i9, int i10, int i11, int i12, int i13, int[] iArr) {
        onNestedScroll(view, i9, i10, i11, i12, i13);
    }

    /* renamed from: k */
    public final void m596k() {
        m601p();
        this.f676E.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m597l(View view, Rect rect, boolean z8, boolean z9, boolean z10, boolean z11) {
        boolean z12;
        C0155e c0155e = (C0155e) view.getLayoutParams();
        if (z8) {
            int i9 = ((ViewGroup.MarginLayoutParams) c0155e).leftMargin;
            int i10 = rect.left;
            if (i9 != i10) {
                ((ViewGroup.MarginLayoutParams) c0155e).leftMargin = i10;
                z12 = true;
            } else {
                z12 = false;
            }
        }
        if (z9) {
            int i11 = ((ViewGroup.MarginLayoutParams) c0155e).topMargin;
            int i12 = rect.top;
            if (i11 != i12) {
                ((ViewGroup.MarginLayoutParams) c0155e).topMargin = i12;
                z12 = true;
            }
        }
        if (z11) {
            int i13 = ((ViewGroup.MarginLayoutParams) c0155e).rightMargin;
            int i14 = rect.right;
            if (i13 != i14) {
                ((ViewGroup.MarginLayoutParams) c0155e).rightMargin = i14;
                z12 = true;
            }
        }
        if (z10) {
            int i15 = ((ViewGroup.MarginLayoutParams) c0155e).bottomMargin;
            int i16 = rect.bottom;
            if (i15 != i16) {
                ((ViewGroup.MarginLayoutParams) c0155e).bottomMargin = i16;
                return true;
            }
        }
        return z12;
    }

    @Override // android.view.ViewGroup
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public C0155e generateDefaultLayoutParams() {
        return new C0155e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public C0155e generateLayoutParams(AttributeSet attributeSet) {
        return new C0155e(getContext(), attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: o */
    public final InterfaceC0251r m600o(View view) {
        if (view instanceof InterfaceC0251r) {
            return (InterfaceC0251r) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        m606u();
        C4628h0 c4628h0M18432o = C4628h0.m18432o(windowInsets);
        boolean zM597l = m597l(this.f681e, new Rect(c4628h0M18432o.m18437e(), c4628h0M18432o.m18439g(), c4628h0M18432o.m18438f(), c4628h0M18432o.m18436d()), true, true, false, true);
        C4647u.m18535c(this, c4628h0M18432o, this.f691o);
        Rect rect = this.f691o;
        C4628h0 c4628h0M18442j = c4628h0M18432o.m18442j(rect.left, rect.top, rect.right, rect.bottom);
        this.f698v = c4628h0M18442j;
        boolean z8 = true;
        if (!this.f699w.equals(c4628h0M18442j)) {
            this.f699w = this.f698v;
            zM597l = true;
        }
        if (this.f692p.equals(this.f691o)) {
            z8 = zM597l;
        } else {
            this.f692p.set(this.f691o);
        }
        if (z8) {
            requestLayout();
        }
        return c4628h0M18432o.m18433a().m18435c().m18434b().m18445n();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m602q(getContext());
        C4647u.m18527W(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m601p();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                C0155e c0155e = (C0155e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i14 = ((ViewGroup.MarginLayoutParams) c0155e).leftMargin + paddingLeft;
                int i15 = ((ViewGroup.MarginLayoutParams) c0155e).topMargin + paddingTop;
                childAt.layout(i14, i15, measuredWidth + i14, measuredHeight + i15);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int measuredHeight;
        m606u();
        measureChildWithMargins(this.f681e, i9, 0, i10, 0);
        C0155e c0155e = (C0155e) this.f681e.getLayoutParams();
        int iMax = Math.max(0, this.f681e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0155e).leftMargin + ((ViewGroup.MarginLayoutParams) c0155e).rightMargin);
        int iMax2 = Math.max(0, this.f681e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0155e).topMargin + ((ViewGroup.MarginLayoutParams) c0155e).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f681e.getMeasuredState());
        boolean z8 = (C4647u.m18505A(this) & 256) != 0;
        if (z8) {
            measuredHeight = this.f678b;
            if (this.f686j && this.f681e.getTabContainer() != null) {
                measuredHeight += this.f678b;
            }
        } else {
            measuredHeight = this.f681e.getVisibility() != 8 ? this.f681e.getMeasuredHeight() : 0;
        }
        this.f693q.set(this.f691o);
        C4628h0 c4628h0 = this.f698v;
        this.f700x = c4628h0;
        if (this.f685i || z8) {
            this.f700x = new C4628h0.a(this.f700x).m18448c(C6429c.m24592a(c4628h0.m18437e(), this.f700x.m18439g() + measuredHeight, this.f700x.m18438f(), this.f700x.m18436d() + 0)).m18446a();
        } else {
            Rect rect = this.f693q;
            rect.top += measuredHeight;
            rect.bottom += 0;
            this.f700x = c4628h0.m18442j(0, measuredHeight, 0, 0);
        }
        m597l(this.f680d, this.f693q, true, true, true, true);
        if (!this.f701y.equals(this.f700x)) {
            C4628h0 c4628h02 = this.f700x;
            this.f701y = c4628h02;
            C4647u.m18537d(this.f680d, c4628h02);
        }
        measureChildWithMargins(this.f680d, i9, 0, i10, 0);
        C0155e c0155e2 = (C0155e) this.f680d.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f680d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0155e2).leftMargin + ((ViewGroup.MarginLayoutParams) c0155e2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f680d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0155e2).topMargin + ((ViewGroup.MarginLayoutParams) c0155e2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f680d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i9, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i10, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f9, float f10, boolean z8) {
        if (!this.f687k || !z8) {
            return false;
        }
        if (m608w(f10)) {
            m596k();
        } else {
            m607v();
        }
        this.f688l = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f9, float f10) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr) {
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr, int i11) {
        if (i11 == 0) {
            onNestedPreScroll(view, i9, i10, iArr);
        }
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12, int i13) {
        if (i13 == 0) {
            onNestedScroll(view, i9, i10, i11, i12);
        }
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScrollAccepted(View view, View view2, int i9, int i10) {
        if (i10 == 0) {
            onNestedScrollAccepted(view, view2, i9);
        }
    }

    @Override // p042d0.InterfaceC4639n
    public boolean onStartNestedScroll(View view, View view2, int i9, int i10) {
        return i10 == 0 && onStartNestedScroll(view, view2, i9);
    }

    @Override // p042d0.InterfaceC4639n
    public void onStopNestedScroll(View view, int i9) {
        if (i9 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i9) {
        super.onWindowSystemUiVisibilityChanged(i9);
        m606u();
        int i10 = this.f690n ^ i9;
        this.f690n = i9;
        boolean z8 = (i9 & 4) == 0;
        boolean z9 = (i9 & 256) != 0;
        InterfaceC0154d interfaceC0154d = this.f702z;
        if (interfaceC0154d != null) {
            interfaceC0154d.mo429c(!z9);
            if (z8 || !z9) {
                this.f702z.mo427a();
            } else {
                this.f702z.mo430d();
            }
        }
        if ((i10 & 256) == 0 || this.f702z == null) {
            return;
        }
        C4647u.m18527W(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i9) {
        super.onWindowVisibilityChanged(i9);
        this.f679c = i9;
        InterfaceC0154d interfaceC0154d = this.f702z;
        if (interfaceC0154d != null) {
            interfaceC0154d.onWindowVisibilityChanged(i9);
        }
    }

    /* renamed from: p */
    public void m601p() {
        removeCallbacks(this.f675D);
        removeCallbacks(this.f676E);
        ViewPropertyAnimator viewPropertyAnimator = this.f673B;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    /* renamed from: q */
    public final void m602q(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f671G);
        this.f678b = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f683g = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f684h = context.getApplicationInfo().targetSdkVersion < 19;
        this.f672A = new OverScroller(context);
    }

    /* renamed from: r */
    public boolean m603r() {
        return this.f685i;
    }

    /* renamed from: s */
    public final void m604s() {
        m601p();
        postDelayed(this.f676E, 600L);
    }

    public void setActionBarHideOffset(int i9) {
        m601p();
        this.f681e.setTranslationY(-Math.max(0, Math.min(i9, this.f681e.getHeight())));
    }

    public void setActionBarVisibilityCallback(InterfaceC0154d interfaceC0154d) {
        this.f702z = interfaceC0154d;
        if (getWindowToken() != null) {
            this.f702z.onWindowVisibilityChanged(this.f679c);
            int i9 = this.f690n;
            if (i9 != 0) {
                onWindowSystemUiVisibilityChanged(i9);
                C4647u.m18527W(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z8) {
        this.f686j = z8;
    }

    public void setHideOnContentScrollEnabled(boolean z8) {
        if (z8 != this.f687k) {
            this.f687k = z8;
            if (z8) {
                return;
            }
            m601p();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i9) {
        m606u();
        this.f682f.setIcon(i9);
    }

    public void setLogo(int i9) {
        m606u();
        this.f682f.mo1036l(i9);
    }

    public void setOverlayMode(boolean z8) {
        this.f685i = z8;
        this.f684h = z8 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z8) {
    }

    public void setUiOptions(int i9) {
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    public void setWindowCallback(Window.Callback callback) {
        m606u();
        this.f682f.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.InterfaceC0249q
    public void setWindowTitle(CharSequence charSequence) {
        m606u();
        this.f682f.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* renamed from: t */
    public final void m605t() {
        m601p();
        postDelayed(this.f675D, 600L);
    }

    /* renamed from: u */
    public void m606u() {
        if (this.f680d == null) {
            this.f680d = (ContentFrameLayout) findViewById(C0565f.action_bar_activity_content);
            this.f681e = (ActionBarContainer) findViewById(C0565f.action_bar_container);
            this.f682f = m600o(findViewById(C0565f.action_bar));
        }
    }

    /* renamed from: v */
    public final void m607v() {
        m601p();
        this.f675D.run();
    }

    /* renamed from: w */
    public final boolean m608w(float f9) {
        this.f672A.fling(0, 0, 0, (int) f9, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.f672A.getFinalY() > this.f681e.getHeight();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0155e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12) {
        int i13 = this.f689m + i10;
        this.f689m = i13;
        setActionBarHideOffset(i13);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i9) {
        this.f677F.m18496b(view, view2, i9);
        this.f689m = getActionBarHideOffset();
        m601p();
        InterfaceC0154d interfaceC0154d = this.f702z;
        if (interfaceC0154d != null) {
            interfaceC0154d.mo431e();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i9) {
        if ((i9 & 2) == 0 || this.f681e.getVisibility() != 0) {
            return false;
        }
        return this.f687k;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.f687k && !this.f688l) {
            if (this.f689m <= this.f681e.getHeight()) {
                m605t();
            } else {
                m604s();
            }
        }
        InterfaceC0154d interfaceC0154d = this.f702z;
        if (interfaceC0154d != null) {
            interfaceC0154d.mo428b();
        }
    }

    public void setIcon(Drawable drawable) {
        m606u();
        this.f682f.setIcon(drawable);
    }
}
