package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import p042d0.C4647u;

/* renamed from: androidx.transition.c */
/* loaded from: classes.dex */
public class C0515c extends AbstractC0532m {

    /* renamed from: e */
    public static final String[] f2842e = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};

    /* renamed from: f */
    public static final Property<Drawable, PointF> f2843f = new b(PointF.class, "boundsOrigin");

    /* renamed from: g */
    public static final Property<k, PointF> f2844g = new c(PointF.class, "topLeft");

    /* renamed from: h */
    public static final Property<k, PointF> f2845h = new d(PointF.class, "bottomRight");

    /* renamed from: i */
    public static final Property<View, PointF> f2846i = new e(PointF.class, "bottomRight");

    /* renamed from: j */
    public static final Property<View, PointF> f2847j = new f(PointF.class, "topLeft");

    /* renamed from: k */
    public static final Property<View, PointF> f2848k = new g(PointF.class, "position");

    /* renamed from: l */
    public static C0529j f2849l = new C0529j();

    /* renamed from: b */
    public int[] f2850b = new int[2];

    /* renamed from: c */
    public boolean f2851c = false;

    /* renamed from: d */
    public boolean f2852d = false;

    /* renamed from: androidx.transition.c$a */
    public class a extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ ViewGroup f2853a;

        /* renamed from: b */
        public final /* synthetic */ BitmapDrawable f2854b;

        /* renamed from: c */
        public final /* synthetic */ View f2855c;

        /* renamed from: d */
        public final /* synthetic */ float f2856d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f9) {
            this.f2853a = viewGroup;
            this.f2854b = bitmapDrawable;
            this.f2855c = view;
            this.f2856d = f9;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            C0514b0.m3024c(this.f2853a).mo3020b(this.f2854b);
            C0514b0.m3029h(this.f2855c, this.f2856d);
        }
    }

    /* renamed from: androidx.transition.c$b */
    public static class b extends Property<Drawable, PointF> {

        /* renamed from: a */
        public Rect f2858a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f2858a = new Rect();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f2858a);
            Rect rect = this.f2858a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f2858a);
            this.f2858a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f2858a);
        }
    }

    /* renamed from: androidx.transition.c$c */
    public static class c extends Property<k, PointF> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.m3056c(pointF);
        }
    }

    /* renamed from: androidx.transition.c$d */
    public static class d extends Property<k, PointF> {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.m3054a(pointF);
        }
    }

    /* renamed from: androidx.transition.c$e */
    public static class e extends Property<View, PointF> {
        public e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            C0514b0.m3028g(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* renamed from: androidx.transition.c$f */
    public static class f extends Property<View, PointF> {
        public f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            C0514b0.m3028g(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* renamed from: androidx.transition.c$g */
    public static class g extends Property<View, PointF> {
        public g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int iRound = Math.round(pointF.x);
            int iRound2 = Math.round(pointF.y);
            C0514b0.m3028g(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    /* renamed from: androidx.transition.c$h */
    public class h extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ k f2859a;
        private k mViewBounds;

        public h(k kVar) {
            this.f2859a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* renamed from: androidx.transition.c$i */
    public class i extends AnimatorListenerAdapter {

        /* renamed from: a */
        public boolean f2861a;

        /* renamed from: b */
        public final /* synthetic */ View f2862b;

        /* renamed from: c */
        public final /* synthetic */ Rect f2863c;

        /* renamed from: d */
        public final /* synthetic */ int f2864d;

        /* renamed from: e */
        public final /* synthetic */ int f2865e;

        /* renamed from: f */
        public final /* synthetic */ int f2866f;

        /* renamed from: g */
        public final /* synthetic */ int f2867g;

        public i(View view, Rect rect, int i9, int i10, int i11, int i12) {
            this.f2862b = view;
            this.f2863c = rect;
            this.f2864d = i9;
            this.f2865e = i10;
            this.f2866f = i11;
            this.f2867g = i12;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2861a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2861a) {
                return;
            }
            C4647u.m18540e0(this.f2862b, this.f2863c);
            C0514b0.m3028g(this.f2862b, this.f2864d, this.f2865e, this.f2866f, this.f2867g);
        }
    }

    /* renamed from: androidx.transition.c$j */
    public class j extends C0533n {

        /* renamed from: a */
        public boolean f2869a = false;

        /* renamed from: b */
        public final /* synthetic */ ViewGroup f2870b;

        public j(ViewGroup viewGroup) {
            this.f2870b = viewGroup;
        }

        @Override // androidx.transition.C0533n, androidx.transition.AbstractC0532m.g
        /* renamed from: b */
        public void mo3050b(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            C0543x.m3122b(this.f2870b, false);
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            if (!this.f2869a) {
                C0543x.m3122b(this.f2870b, false);
            }
            abstractC0532m.removeListener(this);
        }

        @Override // androidx.transition.C0533n, androidx.transition.AbstractC0532m.g
        /* renamed from: d */
        public void mo3052d(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            C0543x.m3122b(this.f2870b, false);
            this.f2869a = true;
        }

        @Override // androidx.transition.C0533n, androidx.transition.AbstractC0532m.g
        /* renamed from: e */
        public void mo3053e(AbstractC0532m abstractC0532m) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            C0543x.m3122b(this.f2870b, true);
        }
    }

    /* renamed from: androidx.transition.c$k */
    public static class k {

        /* renamed from: a */
        public int f2872a;

        /* renamed from: b */
        public int f2873b;

        /* renamed from: c */
        public int f2874c;

        /* renamed from: d */
        public int f2875d;

        /* renamed from: e */
        public View f2876e;

        /* renamed from: f */
        public int f2877f;

        /* renamed from: g */
        public int f2878g;

        public k(View view) {
            this.f2876e = view;
        }

        /* renamed from: a */
        public void m3054a(PointF pointF) {
            this.f2874c = Math.round(pointF.x);
            this.f2875d = Math.round(pointF.y);
            int i9 = this.f2878g + 1;
            this.f2878g = i9;
            if (this.f2877f == i9) {
                m3055b();
            }
        }

        /* renamed from: b */
        public final void m3055b() {
            C0514b0.m3028g(this.f2876e, this.f2872a, this.f2873b, this.f2874c, this.f2875d);
            this.f2877f = 0;
            this.f2878g = 0;
        }

        /* renamed from: c */
        public void m3056c(PointF pointF) {
            this.f2872a = Math.round(pointF.x);
            this.f2873b = Math.round(pointF.y);
            int i9 = this.f2877f + 1;
            this.f2877f = i9;
            if (i9 == this.f2878g) {
                m3055b();
            }
        }
    }

    /* renamed from: a */
    public final boolean m3037a(View view, View view2) {
        if (!this.f2852d) {
            return true;
        }
        C0539t matchedTransitionValues = getMatchedTransitionValues(view, true);
        if (matchedTransitionValues == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == matchedTransitionValues.f2966b) {
            return true;
        }
        return false;
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
        View view = c0539t.f2966b;
        if (!C4647u.m18513I(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        c0539t.f2965a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        c0539t.f2965a.put("android:changeBounds:parent", c0539t.f2966b.getParent());
        if (this.f2852d) {
            c0539t.f2966b.getLocationInWindow(this.f2850b);
            c0539t.f2965a.put("android:changeBounds:windowX", Integer.valueOf(this.f2850b[0]));
            c0539t.f2965a.put("android:changeBounds:windowY", Integer.valueOf(this.f2850b[1]));
        }
        if (this.f2851c) {
            c0539t.f2965a.put("android:changeBounds:clip", C4647u.m18555m(view));
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public Animator createAnimator(ViewGroup viewGroup, C0539t c0539t, C0539t c0539t2) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i9;
        View view;
        int i10;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorM3118c;
        if (c0539t == null || c0539t2 == null) {
            return null;
        }
        Map<String, Object> map = c0539t.f2965a;
        Map<String, Object> map2 = c0539t2.f2965a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = c0539t2.f2966b;
        if (!m3037a(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) c0539t.f2965a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) c0539t.f2965a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) c0539t2.f2965a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) c0539t2.f2965a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.f2850b);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fM3025d = C0514b0.m3025d(view2);
            C0514b0.m3029h(view2, BitmapDescriptorFactory.HUE_RED);
            C0514b0.m3024c(viewGroup).mo3019a(bitmapDrawable);
            AbstractC0523g pathMotion = getPathMotion();
            int[] iArr = this.f2850b;
            int i11 = iArr[0];
            int i12 = iArr[1];
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, C0525h.m3084a(f2843f, pathMotion.mo3077a(iIntValue - i11, iIntValue2 - i12, iIntValue3 - i11, iIntValue4 - i12)));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fM3025d));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) c0539t.f2965a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) c0539t2.f2965a.get("android:changeBounds:bounds");
        int i13 = rect2.left;
        int i14 = rect3.left;
        int i15 = rect2.top;
        int i16 = rect3.top;
        int i17 = rect2.right;
        int i18 = rect3.right;
        int i19 = rect2.bottom;
        int i20 = rect3.bottom;
        int i21 = i17 - i13;
        int i22 = i19 - i15;
        int i23 = i18 - i14;
        int i24 = i20 - i16;
        Rect rect4 = (Rect) c0539t.f2965a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) c0539t2.f2965a.get("android:changeBounds:clip");
        if ((i21 == 0 || i22 == 0) && (i23 == 0 || i24 == 0)) {
            i9 = 0;
        } else {
            i9 = (i13 == i14 && i15 == i16) ? 0 : 1;
            if (i17 != i18 || i19 != i20) {
                i9++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i9++;
        }
        if (i9 <= 0) {
            return null;
        }
        if (this.f2851c) {
            view = view2;
            C0514b0.m3028g(view, i13, i15, Math.max(i21, i23) + i13, Math.max(i22, i24) + i15);
            ObjectAnimator objectAnimatorM3076a = (i13 == i14 && i15 == i16) ? null : C0521f.m3076a(view, f2848k, getPathMotion().mo3077a(i13, i15, i14, i16));
            if (rect4 == null) {
                i10 = 0;
                rect = new Rect(0, 0, i21, i22);
            } else {
                i10 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i10, i10, i23, i24) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                C4647u.m18540e0(view, rect);
                C0529j c0529j = f2849l;
                Object[] objArr = new Object[2];
                objArr[i10] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", c0529j, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i14, i16, i18, i20));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorM3118c = C0538s.m3118c(objectAnimatorM3076a, objectAnimator);
        } else {
            view = view2;
            C0514b0.m3028g(view, i13, i15, i17, i19);
            if (i9 != 2) {
                animatorM3118c = (i13 == i14 && i15 == i16) ? C0521f.m3076a(view, f2846i, getPathMotion().mo3077a(i17, i19, i18, i20)) : C0521f.m3076a(view, f2847j, getPathMotion().mo3077a(i13, i15, i14, i16));
            } else if (i21 == i23 && i22 == i24) {
                animatorM3118c = C0521f.m3076a(view, f2848k, getPathMotion().mo3077a(i13, i15, i14, i16));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorM3076a2 = C0521f.m3076a(kVar, f2844g, getPathMotion().mo3077a(i13, i15, i14, i16));
                ObjectAnimator objectAnimatorM3076a3 = C0521f.m3076a(kVar, f2845h, getPathMotion().mo3077a(i17, i19, i18, i20));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorM3076a2, objectAnimatorM3076a3);
                animatorSet.addListener(new h(kVar));
                animatorM3118c = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            C0543x.m3122b(viewGroup4, true);
            addListener(new j(viewGroup4));
        }
        return animatorM3118c;
    }

    @Override // androidx.transition.AbstractC0532m
    public String[] getTransitionProperties() {
        return f2842e;
    }
}
