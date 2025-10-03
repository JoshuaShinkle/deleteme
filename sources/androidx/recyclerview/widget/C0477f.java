package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.iid.ServiceStarter;
import p042d0.C4647u;

/* renamed from: androidx.recyclerview.widget.f */
/* loaded from: classes.dex */
public class C0477f extends RecyclerView.AbstractC0453n implements RecyclerView.InterfaceC0458s {

    /* renamed from: D */
    public static final int[] f2650D = {R.attr.state_pressed};

    /* renamed from: E */
    public static final int[] f2651E = new int[0];

    /* renamed from: A */
    public int f2652A;

    /* renamed from: B */
    public final Runnable f2653B;

    /* renamed from: C */
    public final RecyclerView.AbstractC0459t f2654C;

    /* renamed from: a */
    public final int f2655a;

    /* renamed from: b */
    public final int f2656b;

    /* renamed from: c */
    public final StateListDrawable f2657c;

    /* renamed from: d */
    public final Drawable f2658d;

    /* renamed from: e */
    public final int f2659e;

    /* renamed from: f */
    public final int f2660f;

    /* renamed from: g */
    public final StateListDrawable f2661g;

    /* renamed from: h */
    public final Drawable f2662h;

    /* renamed from: i */
    public final int f2663i;

    /* renamed from: j */
    public final int f2664j;

    /* renamed from: k */
    public int f2665k;

    /* renamed from: l */
    public int f2666l;

    /* renamed from: m */
    public float f2667m;

    /* renamed from: n */
    public int f2668n;

    /* renamed from: o */
    public int f2669o;

    /* renamed from: p */
    public float f2670p;

    /* renamed from: s */
    public RecyclerView f2673s;

    /* renamed from: z */
    public final ValueAnimator f2680z;

    /* renamed from: q */
    public int f2671q = 0;

    /* renamed from: r */
    public int f2672r = 0;

    /* renamed from: t */
    public boolean f2674t = false;

    /* renamed from: u */
    public boolean f2675u = false;

    /* renamed from: v */
    public int f2676v = 0;

    /* renamed from: w */
    public int f2677w = 0;

    /* renamed from: x */
    public final int[] f2678x = new int[2];

    /* renamed from: y */
    public final int[] f2679y = new int[2];

    /* renamed from: androidx.recyclerview.widget.f$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0477f.this.m2821q(ServiceStarter.ERROR_UNKNOWN);
        }
    }

    /* renamed from: androidx.recyclerview.widget.f$b */
    public class b extends RecyclerView.AbstractC0459t {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0459t
        /* renamed from: b */
        public void mo2543b(RecyclerView recyclerView, int i9, int i10) {
            C0477f.this.m2812B(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* renamed from: androidx.recyclerview.widget.f$c */
    public class c extends AnimatorListenerAdapter {

        /* renamed from: a */
        public boolean f2683a = false;

        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2683a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2683a) {
                this.f2683a = false;
                return;
            }
            if (((Float) C0477f.this.f2680z.getAnimatedValue()).floatValue() == BitmapDescriptorFactory.HUE_RED) {
                C0477f c0477f = C0477f.this;
                c0477f.f2652A = 0;
                c0477f.m2829y(0);
            } else {
                C0477f c0477f2 = C0477f.this;
                c0477f2.f2652A = 2;
                c0477f2.m2826v();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.f$d */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        public d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            C0477f.this.f2657c.setAlpha(iFloatValue);
            C0477f.this.f2658d.setAlpha(iFloatValue);
            C0477f.this.m2826v();
        }
    }

    public C0477f(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i9, int i10, int i11) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        this.f2680z = valueAnimatorOfFloat;
        this.f2652A = 0;
        this.f2653B = new a();
        this.f2654C = new b();
        this.f2657c = stateListDrawable;
        this.f2658d = drawable;
        this.f2661g = stateListDrawable2;
        this.f2662h = drawable2;
        this.f2659e = Math.max(i9, stateListDrawable.getIntrinsicWidth());
        this.f2660f = Math.max(i9, drawable.getIntrinsicWidth());
        this.f2663i = Math.max(i9, stateListDrawable2.getIntrinsicWidth());
        this.f2664j = Math.max(i9, drawable2.getIntrinsicWidth());
        this.f2655a = i10;
        this.f2656b = i11;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        valueAnimatorOfFloat.addListener(new c());
        valueAnimatorOfFloat.addUpdateListener(new d());
        m2814j(recyclerView);
    }

    /* renamed from: A */
    public void m2811A() {
        int i9 = this.f2652A;
        if (i9 != 0) {
            if (i9 != 3) {
                return;
            } else {
                this.f2680z.cancel();
            }
        }
        this.f2652A = 1;
        ValueAnimator valueAnimator = this.f2680z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.f2680z.setDuration(500L);
        this.f2680z.setStartDelay(0L);
        this.f2680z.start();
    }

    /* renamed from: B */
    public void m2812B(int i9, int i10) {
        int iComputeVerticalScrollRange = this.f2673s.computeVerticalScrollRange();
        int i11 = this.f2672r;
        this.f2674t = iComputeVerticalScrollRange - i11 > 0 && i11 >= this.f2655a;
        int iComputeHorizontalScrollRange = this.f2673s.computeHorizontalScrollRange();
        int i12 = this.f2671q;
        boolean z8 = iComputeHorizontalScrollRange - i12 > 0 && i12 >= this.f2655a;
        this.f2675u = z8;
        boolean z9 = this.f2674t;
        if (!z9 && !z8) {
            if (this.f2676v != 0) {
                m2829y(0);
                return;
            }
            return;
        }
        if (z9) {
            float f9 = i11;
            this.f2666l = (int) ((f9 * (i10 + (f9 / 2.0f))) / iComputeVerticalScrollRange);
            this.f2665k = Math.min(i11, (i11 * i11) / iComputeVerticalScrollRange);
        }
        if (this.f2675u) {
            float f10 = i12;
            this.f2669o = (int) ((f10 * (i9 + (f10 / 2.0f))) / iComputeHorizontalScrollRange);
            this.f2668n = Math.min(i12, (i12 * i12) / iComputeHorizontalScrollRange);
        }
        int i13 = this.f2676v;
        if (i13 == 0 || i13 == 1) {
            m2829y(1);
        }
    }

    /* renamed from: C */
    public final void m2813C(float f9) {
        int[] iArrM2820p = m2820p();
        float fMax = Math.max(iArrM2820p[0], Math.min(iArrM2820p[1], f9));
        if (Math.abs(this.f2666l - fMax) < 2.0f) {
            return;
        }
        int iM2828x = m2828x(this.f2667m, fMax, iArrM2820p, this.f2673s.computeVerticalScrollRange(), this.f2673s.computeVerticalScrollOffset(), this.f2672r);
        if (iM2828x != 0) {
            this.f2673s.scrollBy(0, iM2828x);
        }
        this.f2667m = fMax;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.InterfaceC0458s
    /* renamed from: a */
    public void mo2539a(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.f2676v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zM2825u = m2825u(motionEvent.getX(), motionEvent.getY());
            boolean zM2824t = m2824t(motionEvent.getX(), motionEvent.getY());
            if (zM2825u || zM2824t) {
                if (zM2824t) {
                    this.f2677w = 1;
                    this.f2670p = (int) motionEvent.getX();
                } else if (zM2825u) {
                    this.f2677w = 2;
                    this.f2667m = (int) motionEvent.getY();
                }
                m2829y(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f2676v == 2) {
            this.f2667m = BitmapDescriptorFactory.HUE_RED;
            this.f2670p = BitmapDescriptorFactory.HUE_RED;
            m2829y(1);
            this.f2677w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f2676v == 2) {
            m2811A();
            if (this.f2677w == 1) {
                m2822r(motionEvent.getX());
            }
            if (this.f2677w == 2) {
                m2813C(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.InterfaceC0458s
    /* renamed from: b */
    public boolean mo2540b(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i9 = this.f2676v;
        if (i9 == 1) {
            boolean zM2825u = m2825u(motionEvent.getX(), motionEvent.getY());
            boolean zM2824t = m2824t(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zM2825u && !zM2824t) {
                return false;
            }
            if (zM2824t) {
                this.f2677w = 1;
                this.f2670p = (int) motionEvent.getX();
            } else if (zM2825u) {
                this.f2677w = 2;
                this.f2667m = (int) motionEvent.getY();
            }
            m2829y(2);
        } else if (i9 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.InterfaceC0458s
    /* renamed from: c */
    public void mo2541c(boolean z8) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0453n
    /* renamed from: i */
    public void mo2425i(Canvas canvas, RecyclerView recyclerView, RecyclerView.C0465z c0465z) {
        if (this.f2671q != this.f2673s.getWidth() || this.f2672r != this.f2673s.getHeight()) {
            this.f2671q = this.f2673s.getWidth();
            this.f2672r = this.f2673s.getHeight();
            m2829y(0);
        } else if (this.f2652A != 0) {
            if (this.f2674t) {
                m2818n(canvas);
            }
            if (this.f2675u) {
                m2817m(canvas);
            }
        }
    }

    /* renamed from: j */
    public void m2814j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2673s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            m2816l();
        }
        this.f2673s = recyclerView;
        if (recyclerView != null) {
            m2830z();
        }
    }

    /* renamed from: k */
    public final void m2815k() {
        this.f2673s.removeCallbacks(this.f2653B);
    }

    /* renamed from: l */
    public final void m2816l() {
        this.f2673s.removeItemDecoration(this);
        this.f2673s.removeOnItemTouchListener(this);
        this.f2673s.removeOnScrollListener(this.f2654C);
        m2815k();
    }

    /* renamed from: m */
    public final void m2817m(Canvas canvas) {
        int i9 = this.f2672r;
        int i10 = this.f2663i;
        int i11 = this.f2669o;
        int i12 = this.f2668n;
        this.f2661g.setBounds(0, 0, i12, i10);
        this.f2662h.setBounds(0, 0, this.f2671q, this.f2664j);
        canvas.translate(BitmapDescriptorFactory.HUE_RED, i9 - i10);
        this.f2662h.draw(canvas);
        canvas.translate(i11 - (i12 / 2), BitmapDescriptorFactory.HUE_RED);
        this.f2661g.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    /* renamed from: n */
    public final void m2818n(Canvas canvas) {
        int i9 = this.f2671q;
        int i10 = this.f2659e;
        int i11 = i9 - i10;
        int i12 = this.f2666l;
        int i13 = this.f2665k;
        int i14 = i12 - (i13 / 2);
        this.f2657c.setBounds(0, 0, i10, i13);
        this.f2658d.setBounds(0, 0, this.f2660f, this.f2672r);
        if (!m2823s()) {
            canvas.translate(i11, BitmapDescriptorFactory.HUE_RED);
            this.f2658d.draw(canvas);
            canvas.translate(BitmapDescriptorFactory.HUE_RED, i14);
            this.f2657c.draw(canvas);
            canvas.translate(-i11, -i14);
            return;
        }
        this.f2658d.draw(canvas);
        canvas.translate(this.f2659e, i14);
        canvas.scale(-1.0f, 1.0f);
        this.f2657c.draw(canvas);
        canvas.scale(1.0f, 1.0f);
        canvas.translate(-this.f2659e, -i14);
    }

    /* renamed from: o */
    public final int[] m2819o() {
        int[] iArr = this.f2679y;
        int i9 = this.f2656b;
        iArr[0] = i9;
        iArr[1] = this.f2671q - i9;
        return iArr;
    }

    /* renamed from: p */
    public final int[] m2820p() {
        int[] iArr = this.f2678x;
        int i9 = this.f2656b;
        iArr[0] = i9;
        iArr[1] = this.f2672r - i9;
        return iArr;
    }

    /* renamed from: q */
    public void m2821q(int i9) {
        int i10 = this.f2652A;
        if (i10 == 1) {
            this.f2680z.cancel();
        } else if (i10 != 2) {
            return;
        }
        this.f2652A = 3;
        ValueAnimator valueAnimator = this.f2680z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), BitmapDescriptorFactory.HUE_RED);
        this.f2680z.setDuration(i9);
        this.f2680z.start();
    }

    /* renamed from: r */
    public final void m2822r(float f9) {
        int[] iArrM2819o = m2819o();
        float fMax = Math.max(iArrM2819o[0], Math.min(iArrM2819o[1], f9));
        if (Math.abs(this.f2669o - fMax) < 2.0f) {
            return;
        }
        int iM2828x = m2828x(this.f2670p, fMax, iArrM2819o, this.f2673s.computeHorizontalScrollRange(), this.f2673s.computeHorizontalScrollOffset(), this.f2671q);
        if (iM2828x != 0) {
            this.f2673s.scrollBy(iM2828x, 0);
        }
        this.f2670p = fMax;
    }

    /* renamed from: s */
    public final boolean m2823s() {
        return C4647u.m18567s(this.f2673s) == 1;
    }

    /* renamed from: t */
    public boolean m2824t(float f9, float f10) {
        if (f10 >= this.f2672r - this.f2663i) {
            int i9 = this.f2669o;
            int i10 = this.f2668n;
            if (f9 >= i9 - (i10 / 2) && f9 <= i9 + (i10 / 2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: u */
    public boolean m2825u(float f9, float f10) {
        if (!m2823s() ? f9 >= this.f2671q - this.f2659e : f9 <= this.f2659e / 2) {
            int i9 = this.f2666l;
            int i10 = this.f2665k;
            if (f10 >= i9 - (i10 / 2) && f10 <= i9 + (i10 / 2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: v */
    public void m2826v() {
        this.f2673s.invalidate();
    }

    /* renamed from: w */
    public final void m2827w(int i9) {
        m2815k();
        this.f2673s.postDelayed(this.f2653B, i9);
    }

    /* renamed from: x */
    public final int m2828x(float f9, float f10, int[] iArr, int i9, int i10, int i11) {
        int i12 = iArr[1] - iArr[0];
        if (i12 == 0) {
            return 0;
        }
        int i13 = i9 - i11;
        int i14 = (int) (((f10 - f9) / i12) * i13);
        int i15 = i10 + i14;
        if (i15 >= i13 || i15 < 0) {
            return 0;
        }
        return i14;
    }

    /* renamed from: y */
    public void m2829y(int i9) {
        if (i9 == 2 && this.f2676v != 2) {
            this.f2657c.setState(f2650D);
            m2815k();
        }
        if (i9 == 0) {
            m2826v();
        } else {
            m2811A();
        }
        if (this.f2676v == 2 && i9 != 2) {
            this.f2657c.setState(f2651E);
            m2827w(1200);
        } else if (i9 == 1) {
            m2827w(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        this.f2676v = i9;
    }

    /* renamed from: z */
    public final void m2830z() {
        this.f2673s.addItemDecoration(this);
        this.f2673s.addOnItemTouchListener(this);
        this.f2673s.addOnScrollListener(this.f2654C);
    }
}
