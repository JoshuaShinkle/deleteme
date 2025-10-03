package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p042d0.C4647u;

/* renamed from: androidx.transition.d */
/* loaded from: classes.dex */
public class C0517d extends AbstractC0524g0 {

    /* renamed from: androidx.transition.d$a */
    public class a extends C0533n {

        /* renamed from: a */
        public final /* synthetic */ View f2883a;

        public a(View view) {
            this.f2883a = view;
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) {
            C0514b0.m3029h(this.f2883a, 1.0f);
            C0514b0.m3022a(this.f2883a);
            abstractC0532m.removeListener(this);
        }
    }

    /* renamed from: androidx.transition.d$b */
    public static class b extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final View f2885a;

        /* renamed from: b */
        public boolean f2886b = false;

        public b(View view) {
            this.f2885a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            C0514b0.m3029h(this.f2885a, 1.0f);
            if (this.f2886b) {
                this.f2885a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (C4647u.m18509E(this.f2885a) && this.f2885a.getLayerType() == 0) {
                this.f2886b = true;
                this.f2885a.setLayerType(2, null);
            }
        }
    }

    public C0517d(int i9) {
        m3081g(i9);
    }

    /* renamed from: i */
    public static float m3063i(C0539t c0539t, float f9) {
        Float f10;
        return (c0539t == null || (f10 = (Float) c0539t.f2965a.get("android:fade:transitionAlpha")) == null) ? f9 : f10.floatValue();
    }

    @Override // androidx.transition.AbstractC0524g0
    /* renamed from: b */
    public Animator mo3064b(ViewGroup viewGroup, View view, C0539t c0539t, C0539t c0539t2) {
        float f9 = BitmapDescriptorFactory.HUE_RED;
        float fM3063i = m3063i(c0539t, BitmapDescriptorFactory.HUE_RED);
        if (fM3063i != 1.0f) {
            f9 = fM3063i;
        }
        return m3066h(view, f9, 1.0f);
    }

    @Override // androidx.transition.AbstractC0524g0, androidx.transition.AbstractC0532m
    public void captureStartValues(C0539t c0539t) {
        super.captureStartValues(c0539t);
        c0539t.f2965a.put("android:fade:transitionAlpha", Float.valueOf(C0514b0.m3025d(c0539t.f2966b)));
    }

    @Override // androidx.transition.AbstractC0524g0
    /* renamed from: d */
    public Animator mo3065d(ViewGroup viewGroup, View view, C0539t c0539t, C0539t c0539t2) {
        C0514b0.m3027f(view);
        return m3066h(view, m3063i(c0539t, 1.0f), BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: h */
    public final Animator m3066h(View view, float f9, float f10) {
        if (f9 == f10) {
            return null;
        }
        C0514b0.m3029h(view, f9);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, C0514b0.f2840d, f10);
        objectAnimatorOfFloat.addListener(new b(view));
        addListener(new a(view));
        return objectAnimatorOfFloat;
    }
}
