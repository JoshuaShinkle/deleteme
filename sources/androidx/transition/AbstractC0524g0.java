package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.AbstractC0532m;
import java.lang.reflect.InvocationTargetException;

/* renamed from: androidx.transition.g0 */
/* loaded from: classes.dex */
public abstract class AbstractC0524g0 extends AbstractC0532m {

    /* renamed from: c */
    public static final String[] f2907c = {"android:visibility:visibility", "android:visibility:parent"};

    /* renamed from: b */
    public int f2908b = 3;

    /* renamed from: androidx.transition.g0$a */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC0542w f2909a;

        /* renamed from: b */
        public final /* synthetic */ View f2910b;

        public a(InterfaceC0542w interfaceC0542w, View view) {
            this.f2909a = interfaceC0542w;
            this.f2910b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2909a.mo3120d(this.f2910b);
        }
    }

    /* renamed from: androidx.transition.g0$b */
    public static class b extends AnimatorListenerAdapter implements AbstractC0532m.g {

        /* renamed from: a */
        public final View f2912a;

        /* renamed from: b */
        public final int f2913b;

        /* renamed from: c */
        public final ViewGroup f2914c;

        /* renamed from: d */
        public final boolean f2915d;

        /* renamed from: e */
        public boolean f2916e;

        /* renamed from: f */
        public boolean f2917f = false;

        public b(View view, int i9, boolean z8) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            this.f2912a = view;
            this.f2913b = i9;
            this.f2914c = (ViewGroup) view.getParent();
            this.f2915d = z8;
            m3083g(true);
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: a */
        public void mo3073a(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: b */
        public void mo3050b(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            m3083g(false);
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            m3082f();
            abstractC0532m.removeListener(this);
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: d */
        public void mo3052d(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: e */
        public void mo3053e(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            m3083g(true);
        }

        /* renamed from: f */
        public final void m3082f() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            if (!this.f2917f) {
                C0514b0.m3030i(this.f2912a, this.f2913b);
                ViewGroup viewGroup = this.f2914c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            m3083g(false);
        }

        /* renamed from: g */
        public final void m3083g(boolean z8) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            ViewGroup viewGroup;
            if (!this.f2915d || this.f2916e == z8 || (viewGroup = this.f2914c) == null) {
                return;
            }
            this.f2916e = z8;
            C0543x.m3122b(viewGroup, z8);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2917f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            m3082f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
            if (this.f2917f) {
                return;
            }
            C0514b0.m3030i(this.f2912a, this.f2913b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
            if (this.f2917f) {
                return;
            }
            C0514b0.m3030i(this.f2912a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: androidx.transition.g0$c */
    public static class c {

        /* renamed from: a */
        public boolean f2918a;

        /* renamed from: b */
        public boolean f2919b;

        /* renamed from: c */
        public int f2920c;

        /* renamed from: d */
        public int f2921d;

        /* renamed from: e */
        public ViewGroup f2922e;

        /* renamed from: f */
        public ViewGroup f2923f;
    }

    /* renamed from: a */
    public final c m3078a(C0539t c0539t, C0539t c0539t2) {
        c cVar = new c();
        cVar.f2918a = false;
        cVar.f2919b = false;
        if (c0539t == null || !c0539t.f2965a.containsKey("android:visibility:visibility")) {
            cVar.f2920c = -1;
            cVar.f2922e = null;
        } else {
            cVar.f2920c = ((Integer) c0539t.f2965a.get("android:visibility:visibility")).intValue();
            cVar.f2922e = (ViewGroup) c0539t.f2965a.get("android:visibility:parent");
        }
        if (c0539t2 == null || !c0539t2.f2965a.containsKey("android:visibility:visibility")) {
            cVar.f2921d = -1;
            cVar.f2923f = null;
        } else {
            cVar.f2921d = ((Integer) c0539t2.f2965a.get("android:visibility:visibility")).intValue();
            cVar.f2923f = (ViewGroup) c0539t2.f2965a.get("android:visibility:parent");
        }
        if (c0539t != null && c0539t2 != null) {
            int i9 = cVar.f2920c;
            int i10 = cVar.f2921d;
            if (i9 == i10 && cVar.f2922e == cVar.f2923f) {
                return cVar;
            }
            if (i9 != i10) {
                if (i9 == 0) {
                    cVar.f2919b = false;
                    cVar.f2918a = true;
                } else if (i10 == 0) {
                    cVar.f2919b = true;
                    cVar.f2918a = true;
                }
            } else if (cVar.f2923f == null) {
                cVar.f2919b = false;
                cVar.f2918a = true;
            } else if (cVar.f2922e == null) {
                cVar.f2919b = true;
                cVar.f2918a = true;
            }
        } else if (c0539t == null && cVar.f2921d == 0) {
            cVar.f2919b = true;
            cVar.f2918a = true;
        } else if (c0539t2 == null && cVar.f2920c == 0) {
            cVar.f2919b = false;
            cVar.f2918a = true;
        }
        return cVar;
    }

    /* renamed from: b */
    public abstract Animator mo3064b(ViewGroup viewGroup, View view, C0539t c0539t, C0539t c0539t2);

    /* renamed from: c */
    public Animator m3079c(ViewGroup viewGroup, C0539t c0539t, int i9, C0539t c0539t2, int i10) {
        if ((this.f2908b & 1) != 1 || c0539t2 == null) {
            return null;
        }
        if (c0539t == null) {
            View view = (View) c0539t2.f2966b.getParent();
            if (m3078a(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).f2918a) {
                return null;
            }
        }
        return mo3064b(viewGroup, c0539t2.f2966b, c0539t, c0539t2);
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureEndValues(C0539t c0539t) {
        captureValues(c0539t);
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureStartValues(C0539t c0539t) {
        captureValues(c0539t);
    }

    public final void captureValues(C0539t c0539t) {
        c0539t.f2965a.put("android:visibility:visibility", Integer.valueOf(c0539t.f2966b.getVisibility()));
        c0539t.f2965a.put("android:visibility:parent", c0539t.f2966b.getParent());
        int[] iArr = new int[2];
        c0539t.f2966b.getLocationOnScreen(iArr);
        c0539t.f2965a.put("android:visibility:screenLocation", iArr);
    }

    @Override // androidx.transition.AbstractC0532m
    public Animator createAnimator(ViewGroup viewGroup, C0539t c0539t, C0539t c0539t2) {
        c cVarM3078a = m3078a(c0539t, c0539t2);
        if (!cVarM3078a.f2918a) {
            return null;
        }
        if (cVarM3078a.f2922e == null && cVarM3078a.f2923f == null) {
            return null;
        }
        return cVarM3078a.f2919b ? m3079c(viewGroup, c0539t, cVarM3078a.f2920c, c0539t2, cVarM3078a.f2921d) : m3080e(viewGroup, c0539t, cVarM3078a.f2920c, c0539t2, cVarM3078a.f2921d);
    }

    /* renamed from: d */
    public abstract Animator mo3065d(ViewGroup viewGroup, View view, C0539t c0539t, C0539t c0539t2);

    /* renamed from: e */
    public Animator m3080e(ViewGroup viewGroup, C0539t c0539t, int i9, C0539t c0539t2, int i10) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        int id;
        if ((this.f2908b & 2) != 2) {
            return null;
        }
        View viewM3116a = c0539t != null ? c0539t.f2966b : null;
        View view = c0539t2 != null ? c0539t2.f2966b : null;
        if (view == null || view.getParent() == null) {
            if (view != null) {
                viewM3116a = view;
            } else {
                if (viewM3116a != null) {
                    if (viewM3116a.getParent() != null) {
                        if (viewM3116a.getParent() instanceof View) {
                            View view2 = (View) viewM3116a.getParent();
                            if (!m3078a(getTransitionValues(view2, true), getMatchedTransitionValues(view2, true)).f2918a) {
                                viewM3116a = C0538s.m3116a(viewGroup, viewM3116a, view2);
                            } else if (view2.getParent() != null || (id = view2.getId()) == -1 || viewGroup.findViewById(id) == null || !this.mCanRemoveViews) {
                                viewM3116a = null;
                            }
                        }
                    }
                }
                viewM3116a = null;
                view = null;
            }
            view = null;
        } else if (i10 == 4 || viewM3116a == view) {
            viewM3116a = null;
        } else {
            if (!this.mCanRemoveViews) {
                viewM3116a = C0538s.m3116a(viewGroup, viewM3116a, (View) viewM3116a.getParent());
            }
            view = null;
        }
        if (viewM3116a == null || c0539t == null) {
            if (view == null) {
                return null;
            }
            int visibility = view.getVisibility();
            C0514b0.m3030i(view, 0);
            Animator animatorMo3065d = mo3065d(viewGroup, view, c0539t, c0539t2);
            if (animatorMo3065d != null) {
                b bVar = new b(view, i10, true);
                animatorMo3065d.addListener(bVar);
                C0511a.m3016a(animatorMo3065d, bVar);
                addListener(bVar);
            } else {
                C0514b0.m3030i(view, visibility);
            }
            return animatorMo3065d;
        }
        int[] iArr = (int[]) c0539t.f2965a.get("android:visibility:screenLocation");
        int i11 = iArr[0];
        int i12 = iArr[1];
        int[] iArr2 = new int[2];
        viewGroup.getLocationOnScreen(iArr2);
        viewM3116a.offsetLeftAndRight((i11 - iArr2[0]) - viewM3116a.getLeft());
        viewM3116a.offsetTopAndBottom((i12 - iArr2[1]) - viewM3116a.getTop());
        InterfaceC0542w interfaceC0542wM3121a = C0543x.m3121a(viewGroup);
        interfaceC0542wM3121a.mo3119c(viewM3116a);
        Animator animatorMo3065d2 = mo3065d(viewGroup, viewM3116a, c0539t, c0539t2);
        if (animatorMo3065d2 == null) {
            interfaceC0542wM3121a.mo3120d(viewM3116a);
        } else {
            animatorMo3065d2.addListener(new a(interfaceC0542wM3121a, viewM3116a));
        }
        return animatorMo3065d2;
    }

    /* renamed from: g */
    public void m3081g(int i9) {
        if ((i9 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.f2908b = i9;
    }

    @Override // androidx.transition.AbstractC0532m
    public String[] getTransitionProperties() {
        return f2907c;
    }

    @Override // androidx.transition.AbstractC0532m
    public boolean isTransitionRequired(C0539t c0539t, C0539t c0539t2) {
        if (c0539t == null && c0539t2 == null) {
            return false;
        }
        if (c0539t != null && c0539t2 != null && c0539t2.f2965a.containsKey("android:visibility:visibility") != c0539t.f2965a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarM3078a = m3078a(c0539t, c0539t2);
        if (cVarM3078a.f2918a) {
            return cVarM3078a.f2920c == 0 || cVarM3078a.f2921d == 0;
        }
        return false;
    }
}
