package p042d0;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: d0.r */
/* loaded from: classes.dex */
public final class ViewTreeObserverOnPreDrawListenerC4644r implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: b */
    public final View f16272b;

    /* renamed from: c */
    public ViewTreeObserver f16273c;

    /* renamed from: d */
    public final Runnable f16274d;

    public ViewTreeObserverOnPreDrawListenerC4644r(View view, Runnable runnable) {
        this.f16272b = view;
        this.f16273c = view.getViewTreeObserver();
        this.f16274d = runnable;
    }

    /* renamed from: a */
    public static ViewTreeObserverOnPreDrawListenerC4644r m18500a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        ViewTreeObserverOnPreDrawListenerC4644r viewTreeObserverOnPreDrawListenerC4644r = new ViewTreeObserverOnPreDrawListenerC4644r(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC4644r);
        view.addOnAttachStateChangeListener(viewTreeObserverOnPreDrawListenerC4644r);
        return viewTreeObserverOnPreDrawListenerC4644r;
    }

    /* renamed from: b */
    public void m18501b() {
        if (this.f16273c.isAlive()) {
            this.f16273c.removeOnPreDrawListener(this);
        } else {
            this.f16272b.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f16272b.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        m18501b();
        this.f16274d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f16273c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        m18501b();
    }
}
