package p042d0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* renamed from: d0.d0 */
/* loaded from: classes.dex */
public final class C4620d0 {

    /* renamed from: a */
    public WeakReference<View> f16234a;

    /* renamed from: b */
    public Runnable f16235b = null;

    /* renamed from: c */
    public Runnable f16236c = null;

    /* renamed from: d */
    public int f16237d = -1;

    /* renamed from: d0.d0$a */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC4622e0 f16238a;

        /* renamed from: b */
        public final /* synthetic */ View f16239b;

        public a(InterfaceC4622e0 interfaceC4622e0, View view) {
            this.f16238a = interfaceC4622e0;
            this.f16239b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f16238a.mo787a(this.f16239b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f16238a.mo350b(this.f16239b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f16238a.mo351c(this.f16239b);
        }
    }

    /* renamed from: d0.d0$b */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC4626g0 f16241a;

        /* renamed from: b */
        public final /* synthetic */ View f16242b;

        public b(InterfaceC4626g0 interfaceC4626g0, View view) {
            this.f16241a = interfaceC4626g0;
            this.f16242b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f16241a.mo437a(this.f16242b);
        }
    }

    public C4620d0(View view) {
        this.f16234a = new WeakReference<>(view);
    }

    /* renamed from: a */
    public C4620d0 m18408a(float f9) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().alpha(f9);
        }
        return this;
    }

    /* renamed from: b */
    public void m18409b() {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    /* renamed from: c */
    public long m18410c() {
        View view = this.f16234a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    /* renamed from: d */
    public C4620d0 m18411d(long j9) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().setDuration(j9);
        }
        return this;
    }

    /* renamed from: e */
    public C4620d0 m18412e(Interpolator interpolator) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    /* renamed from: f */
    public C4620d0 m18413f(InterfaceC4622e0 interfaceC4622e0) {
        View view = this.f16234a.get();
        if (view != null) {
            m18414g(view, interfaceC4622e0);
        }
        return this;
    }

    /* renamed from: g */
    public final void m18414g(View view, InterfaceC4622e0 interfaceC4622e0) {
        if (interfaceC4622e0 != null) {
            view.animate().setListener(new a(interfaceC4622e0, view));
        } else {
            view.animate().setListener(null);
        }
    }

    /* renamed from: h */
    public C4620d0 m18415h(long j9) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().setStartDelay(j9);
        }
        return this;
    }

    /* renamed from: i */
    public C4620d0 m18416i(InterfaceC4626g0 interfaceC4626g0) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().setUpdateListener(interfaceC4626g0 != null ? new b(interfaceC4626g0, view) : null);
        }
        return this;
    }

    /* renamed from: j */
    public void m18417j() {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    /* renamed from: k */
    public C4620d0 m18418k(float f9) {
        View view = this.f16234a.get();
        if (view != null) {
            view.animate().translationY(f9);
        }
        return this;
    }
}
