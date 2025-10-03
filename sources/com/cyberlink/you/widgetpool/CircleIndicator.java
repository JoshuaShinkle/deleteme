package com.cyberlink.you.widgetpool;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.p030U.R;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class CircleIndicator extends LinearLayout {

    /* renamed from: b */
    public ViewPager f14791b;

    /* renamed from: c */
    public int f14792c;

    /* renamed from: d */
    public int f14793d;

    /* renamed from: e */
    public int f14794e;

    /* renamed from: f */
    public int f14795f;

    /* renamed from: g */
    public int f14796g;

    /* renamed from: h */
    public int f14797h;

    /* renamed from: i */
    public int f14798i;

    /* renamed from: j */
    public Animator f14799j;

    /* renamed from: k */
    public Animator f14800k;

    /* renamed from: l */
    public Animator f14801l;

    /* renamed from: m */
    public Animator f14802m;

    /* renamed from: n */
    public int f14803n;

    /* renamed from: o */
    public final ViewPager.InterfaceC0557j f14804o;

    /* renamed from: p */
    public DataSetObserver f14805p;

    /* renamed from: com.cyberlink.you.widgetpool.CircleIndicator$a */
    public class C3200a implements ViewPager.InterfaceC0557j {
        public C3200a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            CircleIndicator circleIndicator;
            View childAt;
            if (CircleIndicator.this.f14791b.getAdapter() == null || CircleIndicator.this.f14791b.getAdapter().getCount() <= 0) {
                return;
            }
            if (CircleIndicator.this.f14800k.isRunning()) {
                CircleIndicator.this.f14800k.end();
                CircleIndicator.this.f14800k.cancel();
            }
            if (CircleIndicator.this.f14799j.isRunning()) {
                CircleIndicator.this.f14799j.end();
                CircleIndicator.this.f14799j.cancel();
            }
            if (CircleIndicator.this.f14803n >= 0 && (childAt = (circleIndicator = CircleIndicator.this).getChildAt(circleIndicator.f14803n)) != null) {
                childAt.setBackgroundResource(CircleIndicator.this.f14798i);
                CircleIndicator.this.f14800k.setTarget(childAt);
                CircleIndicator.this.f14800k.start();
            }
            View childAt2 = CircleIndicator.this.getChildAt(i9);
            if (childAt2 != null) {
                childAt2.setBackgroundResource(CircleIndicator.this.f14797h);
                CircleIndicator.this.f14799j.setTarget(childAt2);
                CircleIndicator.this.f14799j.start();
            }
            CircleIndicator.this.f14803n = i9;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.CircleIndicator$b */
    public class C3201b extends DataSetObserver {
        public C3201b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            int count;
            super.onChanged();
            if (CircleIndicator.this.f14791b == null || (count = CircleIndicator.this.f14791b.getAdapter().getCount()) == CircleIndicator.this.getChildCount()) {
                return;
            }
            if (CircleIndicator.this.f14803n < count) {
                CircleIndicator circleIndicator = CircleIndicator.this;
                circleIndicator.f14803n = circleIndicator.f14791b.getCurrentItem();
            } else {
                CircleIndicator.this.f14803n = -1;
            }
            CircleIndicator.this.m17059m();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.CircleIndicator$c */
    public class InterpolatorC3202c implements Interpolator {
        public InterpolatorC3202c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f9) {
            return Math.abs(1.0f - f9);
        }

        public /* synthetic */ InterpolatorC3202c(CircleIndicator circleIndicator, C3200a c3200a) {
            this();
        }
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i9) throws Resources.NotFoundException {
        super(context, attributeSet, i9);
        this.f14792c = -1;
        this.f14793d = -1;
        this.f14794e = -1;
        this.f14795f = R.animator.scale_with_alpha;
        this.f14796g = 0;
        this.f14797h = R.drawable.white_radius;
        this.f14798i = R.drawable.white_radius;
        this.f14803n = -1;
        this.f14804o = new C3200a();
        this.f14805p = new C3201b();
        m17062p(context, attributeSet);
    }

    public DataSetObserver getDataSetObserver() {
        return this.f14805p;
    }

    /* renamed from: i */
    public final void m17055i(int i9, int i10, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i10);
        addView(view, this.f14793d, this.f14794e);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (i9 == 0) {
            int i11 = this.f14792c;
            layoutParams.leftMargin = i11;
            layoutParams.rightMargin = i11;
        } else {
            int i12 = this.f14792c;
            layoutParams.topMargin = i12;
            layoutParams.bottomMargin = i12;
        }
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    /* renamed from: j */
    public final void m17056j(Context context) throws Resources.NotFoundException {
        int iM17060n = this.f14793d;
        if (iM17060n < 0) {
            iM17060n = m17060n(5.0f);
        }
        this.f14793d = iM17060n;
        int iM17060n2 = this.f14794e;
        if (iM17060n2 < 0) {
            iM17060n2 = m17060n(5.0f);
        }
        this.f14794e = iM17060n2;
        int iM17060n3 = this.f14792c;
        if (iM17060n3 < 0) {
            iM17060n3 = m17060n(5.0f);
        }
        this.f14792c = iM17060n3;
        int i9 = this.f14795f;
        if (i9 == 0) {
            i9 = R.animator.scale_with_alpha;
        }
        this.f14795f = i9;
        this.f14799j = m17058l(context);
        Animator animatorM17058l = m17058l(context);
        this.f14801l = animatorM17058l;
        animatorM17058l.setDuration(0L);
        this.f14800k = m17057k(context);
        Animator animatorM17057k = m17057k(context);
        this.f14802m = animatorM17057k;
        animatorM17057k.setDuration(0L);
        int i10 = this.f14797h;
        if (i10 == 0) {
            i10 = R.drawable.white_radius;
        }
        this.f14797h = i10;
        int i11 = this.f14798i;
        if (i11 != 0) {
            i10 = i11;
        }
        this.f14798i = i10;
    }

    /* renamed from: k */
    public final Animator m17057k(Context context) throws Resources.NotFoundException {
        int i9 = this.f14796g;
        if (i9 != 0) {
            return AnimatorInflater.loadAnimator(context, i9);
        }
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, this.f14795f);
        animatorLoadAnimator.setInterpolator(new InterpolatorC3202c(this, null));
        return animatorLoadAnimator;
    }

    /* renamed from: l */
    public final Animator m17058l(Context context) {
        return AnimatorInflater.loadAnimator(context, this.f14795f);
    }

    /* renamed from: m */
    public final void m17059m() {
        removeAllViews();
        int count = this.f14791b.getAdapter().getCount();
        if (count <= 0) {
            return;
        }
        int currentItem = this.f14791b.getCurrentItem();
        int orientation = getOrientation();
        for (int i9 = 0; i9 < count; i9++) {
            if (currentItem == i9) {
                m17055i(orientation, this.f14797h, this.f14801l);
            } else {
                m17055i(orientation, this.f14798i, this.f14802m);
            }
        }
    }

    /* renamed from: n */
    public int m17060n(float f9) {
        return (int) ((f9 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: o */
    public final void m17061o(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.CircleIndicator);
        this.f14793d = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, -1);
        this.f14794e = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, -1);
        this.f14792c = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, -1);
        this.f14795f = typedArrayObtainStyledAttributes.getResourceId(0, R.animator.scale_with_alpha);
        this.f14796g = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(2, R.drawable.white_radius);
        this.f14797h = resourceId;
        this.f14798i = typedArrayObtainStyledAttributes.getResourceId(3, resourceId);
        setOrientation(typedArrayObtainStyledAttributes.getInt(7, -1) == 1 ? 1 : 0);
        int i9 = typedArrayObtainStyledAttributes.getInt(4, -1);
        if (i9 < 0) {
            i9 = 17;
        }
        setGravity(i9);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* renamed from: p */
    public final void m17062p(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        m17061o(context, attributeSet);
        m17056j(context);
    }

    @Deprecated
    public void setOnPageChangeListener(ViewPager.InterfaceC0557j interfaceC0557j) {
        ViewPager viewPager = this.f14791b;
        if (viewPager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        viewPager.m3176I(interfaceC0557j);
        this.f14791b.m3187c(interfaceC0557j);
    }

    public void setViewPager(ViewPager viewPager) {
        this.f14791b = viewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        this.f14803n = -1;
        m17059m();
        this.f14791b.m3176I(this.f14804o);
        this.f14791b.m3187c(this.f14804o);
        this.f14804o.onPageSelected(this.f14791b.getCurrentItem());
    }
}
