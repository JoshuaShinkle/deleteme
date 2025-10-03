package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ListView;
import androidx.core.widget.C0332g;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import p042d0.C4637m;
import p042d0.C4642p;
import p042d0.C4647u;
import p042d0.InterfaceC4635l;
import p162p0.C5868a;
import p162p0.C5869b;
import p197t.C6273a;

/* loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements InterfaceC4635l {

    /* renamed from: O */
    public static final String f2786O = "SwipeRefreshLayout";

    /* renamed from: P */
    public static final int[] f2787P = {R.attr.enabled};

    /* renamed from: A */
    public int f2788A;

    /* renamed from: B */
    public int f2789B;

    /* renamed from: C */
    public C5869b f2790C;

    /* renamed from: D */
    public Animation f2791D;

    /* renamed from: E */
    public Animation f2792E;

    /* renamed from: F */
    public Animation f2793F;

    /* renamed from: G */
    public Animation f2794G;

    /* renamed from: H */
    public Animation f2795H;

    /* renamed from: I */
    public boolean f2796I;

    /* renamed from: J */
    public int f2797J;

    /* renamed from: K */
    public boolean f2798K;

    /* renamed from: L */
    public Animation.AnimationListener f2799L;

    /* renamed from: M */
    public final Animation f2800M;

    /* renamed from: N */
    public final Animation f2801N;

    /* renamed from: b */
    public View f2802b;

    /* renamed from: c */
    public InterfaceC0510j f2803c;

    /* renamed from: d */
    public boolean f2804d;

    /* renamed from: e */
    public int f2805e;

    /* renamed from: f */
    public float f2806f;

    /* renamed from: g */
    public float f2807g;

    /* renamed from: h */
    public final C4642p f2808h;

    /* renamed from: i */
    public final C4637m f2809i;

    /* renamed from: j */
    public final int[] f2810j;

    /* renamed from: k */
    public final int[] f2811k;

    /* renamed from: l */
    public boolean f2812l;

    /* renamed from: m */
    public int f2813m;

    /* renamed from: n */
    public int f2814n;

    /* renamed from: o */
    public float f2815o;

    /* renamed from: p */
    public float f2816p;

    /* renamed from: q */
    public boolean f2817q;

    /* renamed from: r */
    public int f2818r;

    /* renamed from: s */
    public boolean f2819s;

    /* renamed from: t */
    public boolean f2820t;

    /* renamed from: u */
    public final DecelerateInterpolator f2821u;

    /* renamed from: v */
    public C5868a f2822v;

    /* renamed from: w */
    public int f2823w;

    /* renamed from: x */
    public int f2824x;

    /* renamed from: y */
    public float f2825y;

    /* renamed from: z */
    public int f2826z;

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$a */
    public class AnimationAnimationListenerC0501a implements Animation.AnimationListener {
        public AnimationAnimationListenerC0501a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            InterfaceC0510j interfaceC0510j;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.f2804d) {
                swipeRefreshLayout.m3006k();
                return;
            }
            swipeRefreshLayout.f2790C.setAlpha(255);
            SwipeRefreshLayout.this.f2790C.start();
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            if (swipeRefreshLayout2.f2796I && (interfaceC0510j = swipeRefreshLayout2.f2803c) != null) {
                interfaceC0510j.mo3015a();
            }
            SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
            swipeRefreshLayout3.f2814n = swipeRefreshLayout3.f2822v.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$b */
    public class C0502b extends Animation {
        public C0502b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f9);
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$c */
    public class C0503c extends Animation {
        public C0503c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f9);
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$d */
    public class C0504d extends Animation {

        /* renamed from: b */
        public final /* synthetic */ int f2830b;

        /* renamed from: c */
        public final /* synthetic */ int f2831c;

        public C0504d(int i9, int i10) {
            this.f2830b = i9;
            this.f2831c = i10;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout.this.f2790C.setAlpha((int) (this.f2830b + ((this.f2831c - r0) * f9)));
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$e */
    public class AnimationAnimationListenerC0505e implements Animation.AnimationListener {
        public AnimationAnimationListenerC0505e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.f2819s) {
                return;
            }
            swipeRefreshLayout.m3012q(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$f */
    public class C0506f extends Animation {
        public C0506f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            int iAbs = !swipeRefreshLayout.f2798K ? swipeRefreshLayout.f2788A - Math.abs(swipeRefreshLayout.f2826z) : swipeRefreshLayout.f2788A;
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((swipeRefreshLayout2.f2824x + ((int) ((iAbs - r1) * f9))) - swipeRefreshLayout2.f2822v.getTop());
            SwipeRefreshLayout.this.f2790C.m23276e(1.0f - f9);
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$g */
    public class C0507g extends Animation {
        public C0507g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout.this.m3004i(f9);
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$h */
    public class C0508h extends Animation {
        public C0508h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f9, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f10 = swipeRefreshLayout.f2825y;
            swipeRefreshLayout.setAnimationProgress(f10 + ((-f10) * f9));
            SwipeRefreshLayout.this.m3004i(f9);
        }
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$i */
    public interface InterfaceC0509i {
    }

    /* renamed from: androidx.swiperefreshlayout.widget.SwipeRefreshLayout$j */
    public interface InterfaceC0510j {
        /* renamed from: a */
        void mo3015a();
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2804d = false;
        this.f2806f = -1.0f;
        this.f2810j = new int[2];
        this.f2811k = new int[2];
        this.f2818r = -1;
        this.f2823w = -1;
        this.f2799L = new AnimationAnimationListenerC0501a();
        this.f2800M = new C0506f();
        this.f2801N = new C0507g();
        this.f2805e = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f2813m = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.f2821u = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f2797J = (int) (displayMetrics.density * 40.0f);
        m2999d();
        setChildrenDrawingOrderEnabled(true);
        int i9 = (int) (displayMetrics.density * 64.0f);
        this.f2788A = i9;
        this.f2806f = i9;
        this.f2808h = new C4642p(this);
        this.f2809i = new C4637m(this);
        setNestedScrollingEnabled(true);
        int i10 = -this.f2797J;
        this.f2814n = i10;
        this.f2826z = i10;
        m3004i(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2787P);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setColorViewAlpha(int i9) {
        this.f2822v.getBackground().setAlpha(i9);
        this.f2790C.setAlpha(i9);
    }

    /* renamed from: a */
    public final void m2996a(int i9, Animation.AnimationListener animationListener) {
        this.f2824x = i9;
        this.f2800M.reset();
        this.f2800M.setDuration(200L);
        this.f2800M.setInterpolator(this.f2821u);
        if (animationListener != null) {
            this.f2822v.m23270b(animationListener);
        }
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(this.f2800M);
    }

    /* renamed from: b */
    public final void m2997b(int i9, Animation.AnimationListener animationListener) {
        if (this.f2819s) {
            m3013r(i9, animationListener);
            return;
        }
        this.f2824x = i9;
        this.f2801N.reset();
        this.f2801N.setDuration(200L);
        this.f2801N.setInterpolator(this.f2821u);
        if (animationListener != null) {
            this.f2822v.m23270b(animationListener);
        }
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(this.f2801N);
    }

    /* renamed from: c */
    public boolean m2998c() {
        View view = this.f2802b;
        return view instanceof ListView ? C0332g.m1598a((ListView) view, -1) : view.canScrollVertically(-1);
    }

    /* renamed from: d */
    public final void m2999d() {
        this.f2822v = new C5868a(getContext(), -328966);
        C5869b c5869b = new C5869b(getContext());
        this.f2790C = c5869b;
        c5869b.m23283l(1);
        this.f2822v.setImageDrawable(this.f2790C);
        this.f2822v.setVisibility(8);
        addView(this.f2822v);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f9, float f10, boolean z8) {
        return this.f2809i.m18474a(f9, f10, z8);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f9, float f10) {
        return this.f2809i.m18475b(f9, f10);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i9, int i10, int[] iArr, int[] iArr2) {
        return this.f2809i.m18476c(i9, i10, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i9, int i10, int i11, int i12, int[] iArr) {
        return this.f2809i.m18479f(i9, i10, i11, i12, iArr);
    }

    /* renamed from: e */
    public final void m3000e() {
        if (this.f2802b == null) {
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                View childAt = getChildAt(i9);
                if (!childAt.equals(this.f2822v)) {
                    this.f2802b = childAt;
                    return;
                }
            }
        }
    }

    /* renamed from: f */
    public final void m3001f(float f9) {
        if (f9 > this.f2806f) {
            m3007l(true, true);
            return;
        }
        this.f2804d = false;
        this.f2790C.m23281j(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        m2997b(this.f2814n, !this.f2819s ? new AnimationAnimationListenerC0505e() : null);
        this.f2790C.m23275d(false);
    }

    /* renamed from: g */
    public final boolean m3002g(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i9, int i10) {
        int i11 = this.f2823w;
        return i11 < 0 ? i10 : i10 == i9 + (-1) ? i11 : i10 >= i11 ? i10 + 1 : i10;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f2808h.m18495a();
    }

    public int getProgressCircleDiameter() {
        return this.f2797J;
    }

    public int getProgressViewEndOffset() {
        return this.f2788A;
    }

    public int getProgressViewStartOffset() {
        return this.f2826z;
    }

    /* renamed from: h */
    public final void m3003h(float f9) {
        this.f2790C.m23275d(true);
        float fMin = Math.min(1.0f, Math.abs(f9 / this.f2806f));
        float fMax = (((float) Math.max(fMin - 0.4d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f9) - this.f2806f;
        int i9 = this.f2789B;
        if (i9 <= 0) {
            i9 = this.f2798K ? this.f2788A - this.f2826z : this.f2788A;
        }
        float f10 = i9;
        double dMax = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(fAbs, f10 * 2.0f) / f10) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i10 = this.f2826z + ((int) ((f10 * fMin) + (f10 * fPow * 2.0f)));
        if (this.f2822v.getVisibility() != 0) {
            this.f2822v.setVisibility(0);
        }
        if (!this.f2819s) {
            this.f2822v.setScaleX(1.0f);
            this.f2822v.setScaleY(1.0f);
        }
        if (this.f2819s) {
            setAnimationProgress(Math.min(1.0f, f9 / this.f2806f));
        }
        if (f9 < this.f2806f) {
            if (this.f2790C.getAlpha() > 76 && !m3002g(this.f2793F)) {
                m3011p();
            }
        } else if (this.f2790C.getAlpha() < 255 && !m3002g(this.f2794G)) {
            m3010o();
        }
        this.f2790C.m23281j(BitmapDescriptorFactory.HUE_RED, Math.min(0.8f, fMax * 0.8f));
        this.f2790C.m23276e(Math.min(1.0f, fMax));
        this.f2790C.m23278g((((fMax * 0.4f) - 0.25f) + (fPow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i10 - this.f2814n);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.f2809i.m18484k();
    }

    /* renamed from: i */
    public void m3004i(float f9) {
        setTargetOffsetTopAndBottom((this.f2824x + ((int) ((this.f2826z - r0) * f9))) - this.f2822v.getTop());
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.f2809i.m18486m();
    }

    /* renamed from: j */
    public final void m3005j(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f2818r) {
            this.f2818r = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    /* renamed from: k */
    public void m3006k() {
        this.f2822v.clearAnimation();
        this.f2790C.stop();
        this.f2822v.setVisibility(8);
        setColorViewAlpha(255);
        if (this.f2819s) {
            setAnimationProgress(BitmapDescriptorFactory.HUE_RED);
        } else {
            setTargetOffsetTopAndBottom(this.f2826z - this.f2814n);
        }
        this.f2814n = this.f2822v.getTop();
    }

    /* renamed from: l */
    public final void m3007l(boolean z8, boolean z9) {
        if (this.f2804d != z8) {
            this.f2796I = z9;
            m3000e();
            this.f2804d = z8;
            if (z8) {
                m2996a(this.f2814n, this.f2799L);
            } else {
                m3012q(this.f2799L);
            }
        }
    }

    /* renamed from: m */
    public final Animation m3008m(int i9, int i10) {
        C0504d c0504d = new C0504d(i9, i10);
        c0504d.setDuration(300L);
        this.f2822v.m23270b(null);
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(c0504d);
        return c0504d;
    }

    /* renamed from: n */
    public final void m3009n(float f9) {
        float f10 = this.f2816p;
        float f11 = f9 - f10;
        int i9 = this.f2805e;
        if (f11 <= i9 || this.f2817q) {
            return;
        }
        this.f2815o = f10 + i9;
        this.f2817q = true;
        this.f2790C.setAlpha(76);
    }

    /* renamed from: o */
    public final void m3010o() {
        this.f2794G = m3008m(this.f2790C.getAlpha(), 255);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m3006k();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0058  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        m3000e();
        int actionMasked = motionEvent.getActionMasked();
        if (this.f2820t && actionMasked == 0) {
            this.f2820t = false;
        }
        if (!isEnabled() || this.f2820t || m2998c() || this.f2804d || this.f2812l) {
            return false;
        }
        if (actionMasked == 0) {
            setTargetOffsetTopAndBottom(this.f2826z - this.f2822v.getTop());
            int pointerId = motionEvent.getPointerId(0);
            this.f2818r = pointerId;
            this.f2817q = false;
            int iFindPointerIndex = motionEvent.findPointerIndex(pointerId);
            if (iFindPointerIndex < 0) {
                return false;
            }
            this.f2816p = motionEvent.getY(iFindPointerIndex);
        } else if (actionMasked == 1) {
            this.f2817q = false;
            this.f2818r = -1;
        } else if (actionMasked == 2) {
            int i9 = this.f2818r;
            if (i9 == -1) {
                Log.e(f2786O, "Got ACTION_MOVE event but don't have an active pointer id.");
                return false;
            }
            int iFindPointerIndex2 = motionEvent.findPointerIndex(i9);
            if (iFindPointerIndex2 < 0) {
                return false;
            }
            m3009n(motionEvent.getY(iFindPointerIndex2));
        } else if (actionMasked != 3) {
            if (actionMasked == 6) {
                m3005j(motionEvent);
            }
        }
        return this.f2817q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f2802b == null) {
            m3000e();
        }
        View view = this.f2802b;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.f2822v.getMeasuredWidth();
        int measuredHeight2 = this.f2822v.getMeasuredHeight();
        int i13 = measuredWidth / 2;
        int i14 = measuredWidth2 / 2;
        int i15 = this.f2814n;
        this.f2822v.layout(i13 - i14, i15, i13 + i14, measuredHeight2 + i15);
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.f2802b == null) {
            m3000e();
        }
        View view = this.f2802b;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.f2822v.measure(View.MeasureSpec.makeMeasureSpec(this.f2797J, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f2797J, 1073741824));
        this.f2823w = -1;
        for (int i11 = 0; i11 < getChildCount(); i11++) {
            if (getChildAt(i11) == this.f2822v) {
                this.f2823w = i11;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f9, float f10, boolean z8) {
        return dispatchNestedFling(f9, f10, z8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f9, float f10) {
        return dispatchNestedPreFling(f9, f10);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr) {
        if (i10 > 0) {
            float f9 = this.f2807g;
            if (f9 > BitmapDescriptorFactory.HUE_RED) {
                float f10 = i10;
                if (f10 > f9) {
                    iArr[1] = i10 - ((int) f9);
                    this.f2807g = BitmapDescriptorFactory.HUE_RED;
                } else {
                    this.f2807g = f9 - f10;
                    iArr[1] = i10;
                }
                m3003h(this.f2807g);
            }
        }
        if (this.f2798K && i10 > 0 && this.f2807g == BitmapDescriptorFactory.HUE_RED && Math.abs(i10 - iArr[1]) > 0) {
            this.f2822v.setVisibility(8);
        }
        int[] iArr2 = this.f2810j;
        if (dispatchNestedPreScroll(i9 - iArr[0], i10 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12) {
        dispatchNestedScroll(i9, i10, i11, i12, this.f2811k);
        if (i12 + this.f2811k[1] >= 0 || m2998c()) {
            return;
        }
        float fAbs = this.f2807g + Math.abs(r11);
        this.f2807g = fAbs;
        m3003h(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i9) {
        this.f2808h.m18496b(view, view2, i9);
        startNestedScroll(i9 & 2);
        this.f2807g = BitmapDescriptorFactory.HUE_RED;
        this.f2812l = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i9) {
        return (!isEnabled() || this.f2820t || this.f2804d || (i9 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.f2808h.m18498d(view);
        this.f2812l = false;
        float f9 = this.f2807g;
        if (f9 > BitmapDescriptorFactory.HUE_RED) {
            m3001f(f9);
            this.f2807g = BitmapDescriptorFactory.HUE_RED;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.f2820t && actionMasked == 0) {
            this.f2820t = false;
        }
        if (!isEnabled() || this.f2820t || m2998c() || this.f2804d || this.f2812l) {
            return false;
        }
        if (actionMasked == 0) {
            this.f2818r = motionEvent.getPointerId(0);
            this.f2817q = false;
        } else {
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f2818r);
                if (iFindPointerIndex < 0) {
                    Log.e(f2786O, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.f2817q) {
                    float y8 = (motionEvent.getY(iFindPointerIndex) - this.f2815o) * 0.5f;
                    this.f2817q = false;
                    m3001f(y8);
                }
                this.f2818r = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.f2818r);
                if (iFindPointerIndex2 < 0) {
                    Log.e(f2786O, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y9 = motionEvent.getY(iFindPointerIndex2);
                m3009n(y9);
                if (this.f2817q) {
                    float f9 = (y9 - this.f2815o) * 0.5f;
                    if (f9 <= BitmapDescriptorFactory.HUE_RED) {
                        return false;
                    }
                    m3003h(f9);
                }
            } else {
                if (actionMasked == 3) {
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex < 0) {
                        Log.e(f2786O, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.f2818r = motionEvent.getPointerId(actionIndex);
                } else if (actionMasked == 6) {
                    m3005j(motionEvent);
                }
            }
        }
        return true;
    }

    /* renamed from: p */
    public final void m3011p() {
        this.f2793F = m3008m(this.f2790C.getAlpha(), 76);
    }

    /* renamed from: q */
    public void m3012q(Animation.AnimationListener animationListener) {
        C0503c c0503c = new C0503c();
        this.f2792E = c0503c;
        c0503c.setDuration(150L);
        this.f2822v.m23270b(animationListener);
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(this.f2792E);
    }

    /* renamed from: r */
    public final void m3013r(int i9, Animation.AnimationListener animationListener) {
        this.f2824x = i9;
        this.f2825y = this.f2822v.getScaleX();
        C0508h c0508h = new C0508h();
        this.f2795H = c0508h;
        c0508h.setDuration(150L);
        if (animationListener != null) {
            this.f2822v.m23270b(animationListener);
        }
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(this.f2795H);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        View view = this.f2802b;
        if (view == null || C4647u.m18514J(view)) {
            super.requestDisallowInterceptTouchEvent(z8);
        }
    }

    /* renamed from: s */
    public final void m3014s(Animation.AnimationListener animationListener) {
        this.f2822v.setVisibility(0);
        this.f2790C.setAlpha(255);
        C0502b c0502b = new C0502b();
        this.f2791D = c0502b;
        c0502b.setDuration(this.f2813m);
        if (animationListener != null) {
            this.f2822v.m23270b(animationListener);
        }
        this.f2822v.clearAnimation();
        this.f2822v.startAnimation(this.f2791D);
    }

    public void setAnimationProgress(float f9) {
        this.f2822v.setScaleX(f9);
        this.f2822v.setScaleY(f9);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        m3000e();
        this.f2790C.m23277f(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i9 = 0; i9 < iArr.length; i9++) {
            iArr2[i9] = C6273a.m24024c(context, iArr[i9]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i9) {
        this.f2806f = i9;
    }

    @Override // android.view.View
    public void setEnabled(boolean z8) {
        super.setEnabled(z8);
        if (z8) {
            return;
        }
        m3006k();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z8) {
        this.f2809i.m18487n(z8);
    }

    public void setOnChildScrollUpCallback(InterfaceC0509i interfaceC0509i) {
    }

    public void setOnRefreshListener(InterfaceC0510j interfaceC0510j) {
        this.f2803c = interfaceC0510j;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i9) {
        setProgressBackgroundColorSchemeResource(i9);
    }

    public void setProgressBackgroundColorSchemeColor(int i9) {
        this.f2822v.setBackgroundColor(i9);
    }

    public void setProgressBackgroundColorSchemeResource(int i9) {
        setProgressBackgroundColorSchemeColor(C6273a.m24024c(getContext(), i9));
    }

    public void setRefreshing(boolean z8) {
        if (!z8 || this.f2804d == z8) {
            m3007l(z8, false);
            return;
        }
        this.f2804d = z8;
        setTargetOffsetTopAndBottom((!this.f2798K ? this.f2788A + this.f2826z : this.f2788A) - this.f2814n);
        this.f2796I = false;
        m3014s(this.f2799L);
    }

    public void setSize(int i9) {
        if (i9 == 0 || i9 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i9 == 0) {
                this.f2797J = (int) (displayMetrics.density * 56.0f);
            } else {
                this.f2797J = (int) (displayMetrics.density * 40.0f);
            }
            this.f2822v.setImageDrawable(null);
            this.f2790C.m23283l(i9);
            this.f2822v.setImageDrawable(this.f2790C);
        }
    }

    public void setSlingshotDistance(int i9) {
        this.f2789B = i9;
    }

    public void setTargetOffsetTopAndBottom(int i9) {
        this.f2822v.bringToFront();
        C4647u.m18519O(this.f2822v, i9);
        this.f2814n = this.f2822v.getTop();
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i9) {
        return this.f2809i.m18489p(i9);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.f2809i.m18491r();
    }
}
