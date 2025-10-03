package androidx.slidingpanelayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.C0342c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;
import p197t.C6273a;

/* loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* renamed from: b */
    public int f2756b;

    /* renamed from: c */
    public int f2757c;

    /* renamed from: d */
    public Drawable f2758d;

    /* renamed from: e */
    public Drawable f2759e;

    /* renamed from: f */
    public final int f2760f;

    /* renamed from: g */
    public boolean f2761g;

    /* renamed from: h */
    public View f2762h;

    /* renamed from: i */
    public float f2763i;

    /* renamed from: j */
    public float f2764j;

    /* renamed from: k */
    public int f2765k;

    /* renamed from: l */
    public boolean f2766l;

    /* renamed from: m */
    public int f2767m;

    /* renamed from: n */
    public float f2768n;

    /* renamed from: o */
    public float f2769o;

    /* renamed from: p */
    public final C0342c f2770p;

    /* renamed from: q */
    public boolean f2771q;

    /* renamed from: r */
    public boolean f2772r;

    /* renamed from: s */
    public final Rect f2773s;

    /* renamed from: t */
    public final ArrayList<RunnableC0497b> f2774t;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0495a();

        /* renamed from: b */
        public boolean f2775b;

        /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$SavedState$a */
        public static class C0495a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.f2775b ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2775b = parcel.readInt() != 0;
        }
    }

    /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$a */
    public class C0496a extends C4613a {

        /* renamed from: a */
        public final Rect f2776a = new Rect();

        public C0496a() {
        }

        /* renamed from: a */
        public final void m2994a(C4704m c4704m, C4704m c4704m2) {
            Rect rect = this.f2776a;
            c4704m2.m18804j(rect);
            c4704m.m18776P(rect);
            c4704m2.m18806k(rect);
            c4704m.m18777Q(rect);
            c4704m.m18826u0(c4704m2.m18770G());
            c4704m.m18805j0(c4704m2.m18819r());
            c4704m.m18781U(c4704m2.m18810m());
            c4704m.m18785Y(c4704m2.m18813o());
            c4704m.m18790b0(c4704m2.m18832z());
            c4704m.m18782V(c4704m2.m18831y());
            c4704m.m18794d0(c4704m2.m18764A());
            c4704m.m18796e0(c4704m2.m18765B());
            c4704m.m18774N(c4704m2.m18827v());
            c4704m.m18816p0(c4704m2.m18769F());
            c4704m.m18801h0(c4704m2.m18766C());
            c4704m.m18787a(c4704m2.m18802i());
            c4704m.m18803i0(c4704m2.m18817q());
        }

        /* renamed from: b */
        public boolean m2995b(View view) {
            return SlidingPaneLayout.this.m2980h(view);
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            C4704m c4704mM18760J = C4704m.m18760J(c4704m);
            super.onInitializeAccessibilityNodeInfo(view, c4704mM18760J);
            m2994a(c4704m, c4704mM18760J);
            c4704mM18760J.m18772L();
            c4704m.m18781U(SlidingPaneLayout.class.getName());
            c4704m.m18820r0(view);
            Object objM18572x = C4647u.m18572x(view);
            if (objM18572x instanceof View) {
                c4704m.m18809l0((View) objM18572x);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i9);
                if (!m2995b(childAt) && childAt.getVisibility() == 0) {
                    C4647u.m18548i0(childAt, 1);
                    c4704m.m18791c(childAt);
                }
            }
        }

        @Override // p042d0.C4613a
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (m2995b(view)) {
                return false;
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$b */
    public class RunnableC0497b implements Runnable {

        /* renamed from: b */
        public final View f2778b;

        public RunnableC0497b(View view) {
            this.f2778b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2778b.getParent() == SlidingPaneLayout.this) {
                this.f2778b.setLayerType(0, null);
                SlidingPaneLayout.this.m2979g(this.f2778b);
            }
            SlidingPaneLayout.this.f2774t.remove(this);
        }
    }

    /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$c */
    public class C0498c extends C0342c.c {
        public C0498c() {
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionHorizontal(View view, int i9, int i10) {
            C0499d c0499d = (C0499d) SlidingPaneLayout.this.f2762h.getLayoutParams();
            if (SlidingPaneLayout.this.m2981i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) c0499d).rightMargin) + SlidingPaneLayout.this.f2762h.getWidth());
                return Math.max(Math.min(i9, width), width - SlidingPaneLayout.this.f2765k);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0499d).leftMargin;
            return Math.min(Math.max(i9, paddingLeft), SlidingPaneLayout.this.f2765k + paddingLeft);
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionVertical(View view, int i9, int i10) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.C0342c.c
        public int getViewHorizontalDragRange(View view) {
            return SlidingPaneLayout.this.f2765k;
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onEdgeDragStarted(int i9, int i10) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.f2770p.m1677c(slidingPaneLayout.f2762h, i10);
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewCaptured(View view, int i9) {
            SlidingPaneLayout.this.m2988p();
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewDragStateChanged(int i9) {
            if (SlidingPaneLayout.this.f2770p.m1658A() == 0) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                if (slidingPaneLayout.f2763i != BitmapDescriptorFactory.HUE_RED) {
                    slidingPaneLayout.m2977e(slidingPaneLayout.f2762h);
                    SlidingPaneLayout.this.f2771q = true;
                } else {
                    slidingPaneLayout.m2990r(slidingPaneLayout.f2762h);
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.m2976d(slidingPaneLayout2.f2762h);
                    SlidingPaneLayout.this.f2771q = false;
                }
            }
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewPositionChanged(View view, int i9, int i10, int i11, int i12) {
            SlidingPaneLayout.this.m2984l(i9);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewReleased(View view, float f9, float f10) {
            int paddingLeft;
            C0499d c0499d = (C0499d) view.getLayoutParams();
            if (SlidingPaneLayout.this.m2981i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) c0499d).rightMargin;
                if (f9 < BitmapDescriptorFactory.HUE_RED || (f9 == BitmapDescriptorFactory.HUE_RED && SlidingPaneLayout.this.f2763i > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.f2765k;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.f2762h.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) c0499d).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f9 > BitmapDescriptorFactory.HUE_RED || (f9 == BitmapDescriptorFactory.HUE_RED && SlidingPaneLayout.this.f2763i > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.f2765k;
                }
            }
            SlidingPaneLayout.this.f2770p.m1671N(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.C0342c.c
        public boolean tryCaptureView(View view, int i9) {
            if (SlidingPaneLayout.this.f2766l) {
                return false;
            }
            return ((C0499d) view.getLayoutParams()).f2783b;
        }
    }

    /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$e */
    public interface InterfaceC0500e {
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f2756b = -858993460;
        this.f2772r = true;
        this.f2773s = new Rect();
        this.f2774t = new ArrayList<>();
        float f9 = context.getResources().getDisplayMetrics().density;
        this.f2760f = (int) ((32.0f * f9) + 0.5f);
        setWillNotDraw(false);
        C4647u.m18530Z(this, new C0496a());
        C4647u.m18548i0(this, 1);
        C0342c c0342cM1656o = C0342c.m1656o(this, 0.5f, new C0498c());
        this.f2770p = c0342cM1656o;
        c0342cM1656o.m1670M(f9 * 400.0f);
    }

    /* renamed from: s */
    public static boolean m2972s(View view) {
        return view.isOpaque();
    }

    /* renamed from: a */
    public boolean m2973a() {
        return m2974b(this.f2762h, 0);
    }

    /* renamed from: b */
    public final boolean m2974b(View view, int i9) {
        if (!this.f2772r && !m2989q(BitmapDescriptorFactory.HUE_RED, i9)) {
            return false;
        }
        this.f2771q = false;
        return true;
    }

    /* renamed from: c */
    public final void m2975c(View view, float f9, int i9) {
        C0499d c0499d = (C0499d) view.getLayoutParams();
        if (f9 > BitmapDescriptorFactory.HUE_RED && i9 != 0) {
            int i10 = (((int) ((((-16777216) & i9) >>> 24) * f9)) << 24) | (i9 & 16777215);
            if (c0499d.f2785d == null) {
                c0499d.f2785d = new Paint();
            }
            c0499d.f2785d.setColorFilter(new PorterDuffColorFilter(i10, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, c0499d.f2785d);
            }
            m2979g(view);
            return;
        }
        if (view.getLayerType() != 0) {
            Paint paint = c0499d.f2785d;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            RunnableC0497b runnableC0497b = new RunnableC0497b(view);
            this.f2774t.add(runnableC0497b);
            C4647u.m18525U(this, runnableC0497b);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0499d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f2770p.m1688n(true)) {
            if (this.f2761g) {
                C4647u.m18524T(this);
            } else {
                this.f2770p.m1675a();
            }
        }
    }

    /* renamed from: d */
    public void m2976d(View view) {
        sendAccessibilityEvent(32);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i9;
        int right;
        super.draw(canvas);
        Drawable drawable = m2981i() ? this.f2759e : this.f2758d;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (m2981i()) {
            right = childAt.getRight();
            i9 = intrinsicWidth + right;
        } else {
            int left = childAt.getLeft();
            int i10 = left - intrinsicWidth;
            i9 = left;
            right = i10;
        }
        drawable.setBounds(right, top, i9, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j9) {
        C0499d c0499d = (C0499d) view.getLayoutParams();
        int iSave = canvas.save();
        if (this.f2761g && !c0499d.f2783b && this.f2762h != null) {
            canvas.getClipBounds(this.f2773s);
            if (m2981i()) {
                Rect rect = this.f2773s;
                rect.left = Math.max(rect.left, this.f2762h.getRight());
            } else {
                Rect rect2 = this.f2773s;
                rect2.right = Math.min(rect2.right, this.f2762h.getLeft());
            }
            canvas.clipRect(this.f2773s);
        }
        boolean zDrawChild = super.drawChild(canvas, view, j9);
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    /* renamed from: e */
    public void m2977e(View view) {
        sendAccessibilityEvent(32);
    }

    /* renamed from: f */
    public void m2978f(View view) {
    }

    /* renamed from: g */
    public void m2979g(View view) {
        C4647u.m18552k0(view, ((C0499d) view.getLayoutParams()).f2785d);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0499d();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0499d((ViewGroup.MarginLayoutParams) layoutParams) : new C0499d(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f2757c;
    }

    public int getParallaxDistance() {
        return this.f2767m;
    }

    public int getSliderFadeColor() {
        return this.f2756b;
    }

    /* renamed from: h */
    public boolean m2980h(View view) {
        if (view == null) {
            return false;
        }
        return this.f2761g && ((C0499d) view.getLayoutParams()).f2784c && this.f2763i > BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: i */
    public boolean m2981i() {
        return C4647u.m18567s(this) == 1;
    }

    /* renamed from: j */
    public boolean m2982j() {
        return !this.f2761g || this.f2763i == 1.0f;
    }

    /* renamed from: k */
    public boolean m2983k() {
        return this.f2761g;
    }

    /* renamed from: l */
    public void m2984l(int i9) {
        if (this.f2762h == null) {
            this.f2763i = BitmapDescriptorFactory.HUE_RED;
            return;
        }
        boolean zM2981i = m2981i();
        C0499d c0499d = (C0499d) this.f2762h.getLayoutParams();
        int width = this.f2762h.getWidth();
        if (zM2981i) {
            i9 = (getWidth() - i9) - width;
        }
        float paddingRight = (i9 - ((zM2981i ? getPaddingRight() : getPaddingLeft()) + (zM2981i ? ((ViewGroup.MarginLayoutParams) c0499d).rightMargin : ((ViewGroup.MarginLayoutParams) c0499d).leftMargin))) / this.f2765k;
        this.f2763i = paddingRight;
        if (this.f2767m != 0) {
            m2987o(paddingRight);
        }
        if (c0499d.f2784c) {
            m2975c(this.f2762h, this.f2763i, this.f2756b);
        }
        m2978f(this.f2762h);
    }

    /* renamed from: m */
    public boolean m2985m() {
        return m2986n(this.f2762h, 0);
    }

    /* renamed from: n */
    public final boolean m2986n(View view, int i9) {
        if (!this.f2772r && !m2989q(1.0f, i9)) {
            return false;
        }
        this.f2771q = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2987o(float f9) {
        boolean z8;
        boolean zM2981i = m2981i();
        C0499d c0499d = (C0499d) this.f2762h.getLayoutParams();
        if (!c0499d.f2784c) {
            z8 = false;
        } else if ((zM2981i ? ((ViewGroup.MarginLayoutParams) c0499d).rightMargin : ((ViewGroup.MarginLayoutParams) c0499d).leftMargin) <= 0) {
            z8 = true;
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt != this.f2762h) {
                float f10 = 1.0f - this.f2764j;
                int i10 = this.f2767m;
                this.f2764j = f9;
                int i11 = ((int) (f10 * i10)) - ((int) ((1.0f - f9) * i10));
                if (zM2981i) {
                    i11 = -i11;
                }
                childAt.offsetLeftAndRight(i11);
                if (z8) {
                    float f11 = this.f2764j;
                    m2975c(childAt, zM2981i ? f11 - 1.0f : 1.0f - f11, this.f2757c);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2772r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2772r = true;
        int size = this.f2774t.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2774t.get(i9).run();
        }
        this.f2774t.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.f2761g && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.f2771q = !this.f2770p.m1662E(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.f2761g || (this.f2766l && actionMasked != 0)) {
            this.f2770p.m1676b();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1) {
            this.f2770p.m1676b();
            return false;
        }
        if (actionMasked == 0) {
            this.f2766l = false;
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            this.f2768n = x8;
            this.f2769o = y8;
            if (this.f2770p.m1662E(this.f2762h, (int) x8, (int) y8) && m2980h(this.f2762h)) {
                z8 = true;
            }
            return this.f2770p.m1672O(motionEvent) || z8;
        }
        if (actionMasked == 2) {
            float x9 = motionEvent.getX();
            float y9 = motionEvent.getY();
            float fAbs = Math.abs(x9 - this.f2768n);
            float fAbs2 = Math.abs(y9 - this.f2769o);
            if (fAbs > this.f2770p.m1698z() && fAbs2 > fAbs) {
                this.f2770p.m1676b();
                this.f2766l = true;
                return false;
            }
        }
        z8 = false;
        if (this.f2770p.m1672O(motionEvent)) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        boolean zM2981i = m2981i();
        if (zM2981i) {
            this.f2770p.m1669L(2);
        } else {
            this.f2770p.m1669L(1);
        }
        int i17 = i11 - i9;
        int paddingRight = zM2981i ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = zM2981i ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f2772r) {
            this.f2763i = (this.f2761g && this.f2771q) ? 1.0f : BitmapDescriptorFactory.HUE_RED;
        }
        int i18 = paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt = getChildAt(i19);
            if (childAt.getVisibility() != 8) {
                C0499d c0499d = (C0499d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (c0499d.f2783b) {
                    int i20 = i17 - paddingLeft;
                    int iMin = (Math.min(paddingRight, i20 - this.f2760f) - i18) - (((ViewGroup.MarginLayoutParams) c0499d).leftMargin + ((ViewGroup.MarginLayoutParams) c0499d).rightMargin);
                    this.f2765k = iMin;
                    int i21 = zM2981i ? ((ViewGroup.MarginLayoutParams) c0499d).rightMargin : ((ViewGroup.MarginLayoutParams) c0499d).leftMargin;
                    c0499d.f2784c = ((i18 + i21) + iMin) + (measuredWidth / 2) > i20;
                    int i22 = (int) (iMin * this.f2763i);
                    i18 += i21 + i22;
                    this.f2763i = i22 / iMin;
                    i13 = 0;
                } else if (!this.f2761g || (i14 = this.f2767m) == 0) {
                    i18 = paddingRight;
                    i13 = 0;
                } else {
                    i13 = (int) ((1.0f - this.f2763i) * i14);
                    i18 = paddingRight;
                }
                if (zM2981i) {
                    i16 = (i17 - i18) + i13;
                    i15 = i16 - measuredWidth;
                } else {
                    i15 = i18 - i13;
                    i16 = i15 + measuredWidth;
                }
                childAt.layout(i15, paddingTop, i16, childAt.getMeasuredHeight() + paddingTop);
                paddingRight += childAt.getWidth();
            }
        }
        if (this.f2772r) {
            if (this.f2761g) {
                if (this.f2767m != 0) {
                    m2987o(this.f2763i);
                }
                if (((C0499d) this.f2762h.getLayoutParams()).f2784c) {
                    m2975c(this.f2762h, this.f2763i, this.f2756b);
                }
            } else {
                for (int i23 = 0; i23 < childCount; i23++) {
                    m2975c(getChildAt(i23), BitmapDescriptorFactory.HUE_RED, this.f2756b);
                }
            }
            m2990r(this.f2762h);
        }
        this.f2772r = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ad A[PHI: r13
      0x00ad: PHI (r13v2 float) = (r13v1 float), (r13v8 float) binds: [B:36:0x00a4, B:38:0x00a9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x013a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) {
        int paddingTop;
        int iMin;
        int i11;
        int iMakeMeasureSpec;
        int i12;
        int i13;
        int iMakeMeasureSpec2;
        float f9;
        int i14;
        int iMakeMeasureSpec3;
        int iMakeMeasureSpec4;
        int i15;
        int iMakeMeasureSpec5;
        int measuredHeight;
        boolean z8;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
        } else if (mode2 == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            }
            if (mode2 == 0) {
                size2 = 300;
                mode2 = Integer.MIN_VALUE;
            }
        }
        boolean z9 = false;
        if (mode2 != Integer.MIN_VALUE) {
            iMin = mode2 != 1073741824 ? 0 : (size2 - getPaddingTop()) - getPaddingBottom();
            paddingTop = iMin;
        } else {
            paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            iMin = 0;
        }
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.f2762h = null;
        int i16 = 0;
        boolean z10 = false;
        int i17 = paddingLeft;
        float f10 = BitmapDescriptorFactory.HUE_RED;
        while (true) {
            i11 = 8;
            if (i16 >= childCount) {
                break;
            }
            View childAt = getChildAt(i16);
            C0499d c0499d = (C0499d) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                c0499d.f2784c = z9;
            } else {
                float f11 = c0499d.f2782a;
                if (f11 > BitmapDescriptorFactory.HUE_RED) {
                    f10 += f11;
                    if (((ViewGroup.MarginLayoutParams) c0499d).width != 0) {
                        int i18 = ((ViewGroup.MarginLayoutParams) c0499d).leftMargin + ((ViewGroup.MarginLayoutParams) c0499d).rightMargin;
                        int i19 = ((ViewGroup.MarginLayoutParams) c0499d).width;
                        if (i19 == -2) {
                            iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingLeft - i18, Integer.MIN_VALUE);
                            f9 = f10;
                            i14 = Integer.MIN_VALUE;
                        } else {
                            f9 = f10;
                            i14 = Integer.MIN_VALUE;
                            if (i19 == -1) {
                                iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(paddingLeft - i18, 1073741824);
                            } else {
                                iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i19, 1073741824);
                                i15 = ((ViewGroup.MarginLayoutParams) c0499d).height;
                                if (i15 != -2) {
                                    iMakeMeasureSpec5 = View.MeasureSpec.makeMeasureSpec(paddingTop, i14);
                                } else {
                                    iMakeMeasureSpec5 = i15 == -1 ? View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824) : View.MeasureSpec.makeMeasureSpec(i15, 1073741824);
                                }
                                childAt.measure(iMakeMeasureSpec3, iMakeMeasureSpec5);
                                int measuredWidth = childAt.getMeasuredWidth();
                                measuredHeight = childAt.getMeasuredHeight();
                                if (mode2 == i14 && measuredHeight > iMin) {
                                    iMin = Math.min(measuredHeight, paddingTop);
                                }
                                i17 -= measuredWidth;
                                z8 = i17 >= 0;
                                c0499d.f2783b = z8;
                                z10 |= z8;
                                if (z8) {
                                    this.f2762h = childAt;
                                }
                                f10 = f9;
                            }
                        }
                        iMakeMeasureSpec3 = iMakeMeasureSpec4;
                        i15 = ((ViewGroup.MarginLayoutParams) c0499d).height;
                        if (i15 != -2) {
                        }
                        childAt.measure(iMakeMeasureSpec3, iMakeMeasureSpec5);
                        int measuredWidth2 = childAt.getMeasuredWidth();
                        measuredHeight = childAt.getMeasuredHeight();
                        if (mode2 == i14) {
                            iMin = Math.min(measuredHeight, paddingTop);
                        }
                        i17 -= measuredWidth2;
                        if (i17 >= 0) {
                        }
                        c0499d.f2783b = z8;
                        z10 |= z8;
                        if (z8) {
                        }
                        f10 = f9;
                    }
                }
            }
            i16++;
            z9 = false;
        }
        if (z10 || f10 > BitmapDescriptorFactory.HUE_RED) {
            int i20 = paddingLeft - this.f2760f;
            int i21 = 0;
            while (i21 < childCount) {
                View childAt2 = getChildAt(i21);
                if (childAt2.getVisibility() == i11) {
                    i12 = i20;
                } else {
                    C0499d c0499d2 = (C0499d) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != i11) {
                        boolean z11 = ((ViewGroup.MarginLayoutParams) c0499d2).width == 0 && c0499d2.f2782a > BitmapDescriptorFactory.HUE_RED;
                        int measuredWidth3 = z11 ? 0 : childAt2.getMeasuredWidth();
                        if (!z10 || childAt2 == this.f2762h) {
                            if (c0499d2.f2782a > BitmapDescriptorFactory.HUE_RED) {
                                if (((ViewGroup.MarginLayoutParams) c0499d2).width == 0) {
                                    int i22 = ((ViewGroup.MarginLayoutParams) c0499d2).height;
                                    iMakeMeasureSpec = i22 == -2 ? View.MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE) : i22 == -1 ? View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824) : View.MeasureSpec.makeMeasureSpec(i22, 1073741824);
                                } else {
                                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                }
                                if (z10) {
                                    int i23 = paddingLeft - (((ViewGroup.MarginLayoutParams) c0499d2).leftMargin + ((ViewGroup.MarginLayoutParams) c0499d2).rightMargin);
                                    i12 = i20;
                                    int iMakeMeasureSpec6 = View.MeasureSpec.makeMeasureSpec(i23, 1073741824);
                                    if (measuredWidth3 != i23) {
                                        childAt2.measure(iMakeMeasureSpec6, iMakeMeasureSpec);
                                    }
                                } else {
                                    i12 = i20;
                                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth3 + ((int) ((c0499d2.f2782a * Math.max(0, i17)) / f10)), 1073741824), iMakeMeasureSpec);
                                }
                            }
                        } else if (((ViewGroup.MarginLayoutParams) c0499d2).width < 0 && (measuredWidth3 > i20 || c0499d2.f2782a > BitmapDescriptorFactory.HUE_RED)) {
                            if (z11) {
                                int i24 = ((ViewGroup.MarginLayoutParams) c0499d2).height;
                                if (i24 == -2) {
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                    i13 = 1073741824;
                                } else if (i24 == -1) {
                                    i13 = 1073741824;
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                                } else {
                                    i13 = 1073741824;
                                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i24, 1073741824);
                                }
                            } else {
                                i13 = 1073741824;
                                iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            }
                            childAt2.measure(View.MeasureSpec.makeMeasureSpec(i20, i13), iMakeMeasureSpec2);
                        }
                        i12 = i20;
                    }
                }
                i21++;
                i20 = i12;
                i11 = 8;
            }
        }
        setMeasuredDimension(size, iMin + getPaddingTop() + getPaddingBottom());
        this.f2761g = z10;
        if (this.f2770p.m1658A() == 0 || z10) {
            return;
        }
        this.f2770p.m1675a();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f2775b) {
            m2985m();
        } else {
            m2973a();
        }
        this.f2771q = savedState.f2775b;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2775b = m2983k() ? m2982j() : this.f2771q;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        if (i9 != i11) {
            this.f2772r = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f2761g) {
            return super.onTouchEvent(motionEvent);
        }
        this.f2770p.m1663F(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            this.f2768n = x8;
            this.f2769o = y8;
        } else if (actionMasked == 1 && m2980h(this.f2762h)) {
            float x9 = motionEvent.getX();
            float y9 = motionEvent.getY();
            float f9 = x9 - this.f2768n;
            float f10 = y9 - this.f2769o;
            int iM1698z = this.f2770p.m1698z();
            if ((f9 * f9) + (f10 * f10) < iM1698z * iM1698z && this.f2770p.m1662E(this.f2762h, (int) x9, (int) y9)) {
                m2974b(this.f2762h, 0);
            }
        }
        return true;
    }

    /* renamed from: p */
    public void m2988p() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    /* renamed from: q */
    public boolean m2989q(float f9, int i9) {
        int paddingLeft;
        if (!this.f2761g) {
            return false;
        }
        boolean zM2981i = m2981i();
        C0499d c0499d = (C0499d) this.f2762h.getLayoutParams();
        if (zM2981i) {
            paddingLeft = (int) (getWidth() - (((getPaddingRight() + ((ViewGroup.MarginLayoutParams) c0499d).rightMargin) + (f9 * this.f2765k)) + this.f2762h.getWidth()));
        } else {
            paddingLeft = (int) (getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0499d).leftMargin + (f9 * this.f2765k));
        }
        C0342c c0342c = this.f2770p;
        View view = this.f2762h;
        if (!c0342c.m1673P(view, paddingLeft, view.getTop())) {
            return false;
        }
        m2988p();
        C4647u.m18524T(this);
        return true;
    }

    /* renamed from: r */
    public void m2990r(View view) {
        int left;
        int right;
        int top;
        int bottom;
        View childAt;
        boolean z8;
        View view2 = view;
        boolean zM2981i = m2981i();
        int width = zM2981i ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = zM2981i ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !m2972s(view)) {
            left = 0;
            right = 0;
            top = 0;
            bottom = 0;
        } else {
            left = view.getLeft();
            right = view.getRight();
            top = view.getTop();
            bottom = view.getBottom();
        }
        int childCount = getChildCount();
        int i9 = 0;
        while (i9 < childCount && (childAt = getChildAt(i9)) != view2) {
            if (childAt.getVisibility() == 8) {
                z8 = zM2981i;
            } else {
                z8 = zM2981i;
                childAt.setVisibility((Math.max(zM2981i ? paddingLeft : width, childAt.getLeft()) < left || Math.max(paddingTop, childAt.getTop()) < top || Math.min(zM2981i ? width : paddingLeft, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4);
            }
            i9++;
            view2 = view;
            zM2981i = z8;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.f2761g) {
            return;
        }
        this.f2771q = view == this.f2762h;
    }

    public void setCoveredFadeColor(int i9) {
        this.f2757c = i9;
    }

    public void setPanelSlideListener(InterfaceC0500e interfaceC0500e) {
    }

    public void setParallaxDistance(int i9) {
        this.f2767m = i9;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f2758d = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f2759e = drawable;
    }

    @Deprecated
    public void setShadowResource(int i9) {
        setShadowDrawable(getResources().getDrawable(i9));
    }

    public void setShadowResourceLeft(int i9) {
        setShadowDrawableLeft(C6273a.m24025d(getContext(), i9));
    }

    public void setShadowResourceRight(int i9) {
        setShadowDrawableRight(C6273a.m24025d(getContext(), i9));
    }

    public void setSliderFadeColor(int i9) {
        this.f2756b = i9;
    }

    /* renamed from: androidx.slidingpanelayout.widget.SlidingPaneLayout$d */
    public static class C0499d extends ViewGroup.MarginLayoutParams {

        /* renamed from: e */
        public static final int[] f2781e = {R.attr.layout_weight};

        /* renamed from: a */
        public float f2782a;

        /* renamed from: b */
        public boolean f2783b;

        /* renamed from: c */
        public boolean f2784c;

        /* renamed from: d */
        public Paint f2785d;

        public C0499d() {
            super(-1, -1);
            this.f2782a = BitmapDescriptorFactory.HUE_RED;
        }

        public C0499d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2782a = BitmapDescriptorFactory.HUE_RED;
        }

        public C0499d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2782a = BitmapDescriptorFactory.HUE_RED;
        }

        public C0499d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2782a = BitmapDescriptorFactory.HUE_RED;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2781e);
            this.f2782a = typedArrayObtainStyledAttributes.getFloat(0, BitmapDescriptorFactory.HUE_RED);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0499d(getContext(), attributeSet);
    }
}
