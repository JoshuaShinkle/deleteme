package p116k4;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: k4.q1 */
/* loaded from: classes.dex */
public class C5177q1 {

    /* renamed from: k4.q1$a */
    public class a implements Animator.AnimatorListener {

        /* renamed from: a */
        public boolean f17738a;

        /* renamed from: b */
        public final /* synthetic */ float f17739b;

        /* renamed from: c */
        public final /* synthetic */ int f17740c;

        /* renamed from: d */
        public final /* synthetic */ float f17741d;

        /* renamed from: e */
        public final /* synthetic */ int f17742e;

        /* renamed from: f */
        public final /* synthetic */ View f17743f;

        public a(float f9, int i9, float f10, int i10, View view) {
            this.f17739b = f9;
            this.f17740c = i9;
            this.f17741d = f10;
            this.f17742e = i10;
            this.f17743f = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m20231b(View view, int i9, int i10, float f9, float f10) {
            if (this.f17738a) {
                return;
            }
            view.getLayoutParams().width = i9;
            view.getLayoutParams().height = i10;
            view.setX(f9);
            view.setY(f10);
            view.requestLayout();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f17738a = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            float f9 = this.f17739b;
            final int i9 = this.f17740c;
            final float f10 = f9 - (i9 / 2.0f);
            float f11 = this.f17741d;
            final int i10 = this.f17742e;
            final float f12 = f11 - (i10 / 2.0f);
            final View view = this.f17743f;
            view.postDelayed(new Runnable() { // from class: k4.p1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17732b.m20231b(view, i9, i10, f10, f12);
                }
            }, 100L);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f17738a = false;
        }
    }

    /* renamed from: k4.q1$b */
    public static class b implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: b */
    public static ValueAnimator m20228b(final View view, int i9, int i10, float f9, float f10, int i11) {
        final int width = view.getWidth();
        final int height = view.getHeight();
        final float x8 = view.getX() + (view.getWidth() / 2.0f);
        final float y8 = view.getY() + (view.getHeight() / 2.0f);
        final int i12 = i9 - width;
        final int i13 = i10 - height;
        final float f11 = f9 - x8;
        final float f12 = f10 - y8;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        valueAnimatorOfFloat.setDuration(i11);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: k4.o1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                C5177q1.m20229c(width, i12, height, i13, x8, f11, y8, f12, view, valueAnimator);
            }
        });
        valueAnimatorOfFloat.addListener(new a(f9, i9, f10, i10, view));
        return valueAnimatorOfFloat;
    }

    /* renamed from: c */
    public static /* synthetic */ void m20229c(int i9, int i10, int i11, int i12, float f9, float f10, float f11, float f12, View view, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int i13 = (int) (i9 + (i10 * fFloatValue));
        int i14 = (int) (i11 + (i12 * fFloatValue));
        view.getLayoutParams().width = i13;
        view.getLayoutParams().height = i14;
        view.setX((f9 + (f10 * fFloatValue)) - (i13 / 2.0f));
        view.setY((f11 + (f12 * fFloatValue)) - (i14 / 2.0f));
        view.requestLayout();
    }
}
