package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: androidx.transition.a */
/* loaded from: classes.dex */
public class C0511a {
    /* renamed from: a */
    public static void m3016a(Animator animator, AnimatorListenerAdapter animatorListenerAdapter) {
        animator.addPauseListener(animatorListenerAdapter);
    }

    /* renamed from: b */
    public static void m3017b(Animator animator) {
        animator.pause();
    }

    /* renamed from: c */
    public static void m3018c(Animator animator) {
        animator.resume();
    }
}
