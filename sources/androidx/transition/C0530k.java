package androidx.transition;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: androidx.transition.k */
/* loaded from: classes.dex */
public class C0530k {

    /* renamed from: a */
    public ViewGroup f2926a;

    /* renamed from: b */
    public Runnable f2927b;

    /* renamed from: b */
    public static C0530k m3086b(View view) {
        return (C0530k) view.getTag(C0527i.transition_current_scene);
    }

    /* renamed from: c */
    public static void m3087c(View view, C0530k c0530k) {
        view.setTag(C0527i.transition_current_scene, c0530k);
    }

    /* renamed from: a */
    public void m3088a() {
        Runnable runnable;
        if (m3086b(this.f2926a) != this || (runnable = this.f2927b) == null) {
            return;
        }
        runnable.run();
    }
}
