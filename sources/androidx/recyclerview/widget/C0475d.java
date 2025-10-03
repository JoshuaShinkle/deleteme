package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p042d0.C4647u;

/* renamed from: androidx.recyclerview.widget.d */
/* loaded from: classes.dex */
public class C0475d extends AbstractC0486o {

    /* renamed from: s */
    public static TimeInterpolator f2595s;

    /* renamed from: h */
    public ArrayList<RecyclerView.AbstractC0442c0> f2596h = new ArrayList<>();

    /* renamed from: i */
    public ArrayList<RecyclerView.AbstractC0442c0> f2597i = new ArrayList<>();

    /* renamed from: j */
    public ArrayList<j> f2598j = new ArrayList<>();

    /* renamed from: k */
    public ArrayList<i> f2599k = new ArrayList<>();

    /* renamed from: l */
    public ArrayList<ArrayList<RecyclerView.AbstractC0442c0>> f2600l = new ArrayList<>();

    /* renamed from: m */
    public ArrayList<ArrayList<j>> f2601m = new ArrayList<>();

    /* renamed from: n */
    public ArrayList<ArrayList<i>> f2602n = new ArrayList<>();

    /* renamed from: o */
    public ArrayList<RecyclerView.AbstractC0442c0> f2603o = new ArrayList<>();

    /* renamed from: p */
    public ArrayList<RecyclerView.AbstractC0442c0> f2604p = new ArrayList<>();

    /* renamed from: q */
    public ArrayList<RecyclerView.AbstractC0442c0> f2605q = new ArrayList<>();

    /* renamed from: r */
    public ArrayList<RecyclerView.AbstractC0442c0> f2606r = new ArrayList<>();

    /* renamed from: androidx.recyclerview.widget.d$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2607b;

        public a(ArrayList arrayList) {
            this.f2607b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2607b.iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                C0475d.this.m2796T(jVar.f2641a, jVar.f2642b, jVar.f2643c, jVar.f2644d, jVar.f2645e);
            }
            this.f2607b.clear();
            C0475d.this.f2601m.remove(this.f2607b);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2609b;

        public b(ArrayList arrayList) {
            this.f2609b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2609b.iterator();
            while (it.hasNext()) {
                C0475d.this.m2795S((i) it.next());
            }
            this.f2609b.clear();
            C0475d.this.f2602n.remove(this.f2609b);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$c */
    public class c implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2611b;

        public c(ArrayList arrayList) {
            this.f2611b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f2611b.iterator();
            while (it.hasNext()) {
                C0475d.this.m2794R((RecyclerView.AbstractC0442c0) it.next());
            }
            this.f2611b.clear();
            C0475d.this.f2600l.remove(this.f2611b);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$d */
    public class d extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ RecyclerView.AbstractC0442c0 f2613a;

        /* renamed from: b */
        public final /* synthetic */ ViewPropertyAnimator f2614b;

        /* renamed from: c */
        public final /* synthetic */ View f2615c;

        public d(RecyclerView.AbstractC0442c0 abstractC0442c0, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2613a = abstractC0442c0;
            this.f2614b = viewPropertyAnimator;
            this.f2615c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2614b.setListener(null);
            this.f2615c.setAlpha(1.0f);
            C0475d.this.m2895G(this.f2613a);
            C0475d.this.f2605q.remove(this.f2613a);
            C0475d.this.m2799W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C0475d.this.m2896H(this.f2613a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$e */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ RecyclerView.AbstractC0442c0 f2617a;

        /* renamed from: b */
        public final /* synthetic */ View f2618b;

        /* renamed from: c */
        public final /* synthetic */ ViewPropertyAnimator f2619c;

        public e(RecyclerView.AbstractC0442c0 abstractC0442c0, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2617a = abstractC0442c0;
            this.f2618b = view;
            this.f2619c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2618b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2619c.setListener(null);
            C0475d.this.m2889A(this.f2617a);
            C0475d.this.f2603o.remove(this.f2617a);
            C0475d.this.m2799W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C0475d.this.m2890B(this.f2617a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$f */
    public class f extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ RecyclerView.AbstractC0442c0 f2621a;

        /* renamed from: b */
        public final /* synthetic */ int f2622b;

        /* renamed from: c */
        public final /* synthetic */ View f2623c;

        /* renamed from: d */
        public final /* synthetic */ int f2624d;

        /* renamed from: e */
        public final /* synthetic */ ViewPropertyAnimator f2625e;

        public f(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9, View view, int i10, ViewPropertyAnimator viewPropertyAnimator) {
            this.f2621a = abstractC0442c0;
            this.f2622b = i9;
            this.f2623c = view;
            this.f2624d = i10;
            this.f2625e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f2622b != 0) {
                this.f2623c.setTranslationX(BitmapDescriptorFactory.HUE_RED);
            }
            if (this.f2624d != 0) {
                this.f2623c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2625e.setListener(null);
            C0475d.this.m2893E(this.f2621a);
            C0475d.this.f2604p.remove(this.f2621a);
            C0475d.this.m2799W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C0475d.this.m2894F(this.f2621a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$g */
    public class g extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ i f2627a;

        /* renamed from: b */
        public final /* synthetic */ ViewPropertyAnimator f2628b;

        /* renamed from: c */
        public final /* synthetic */ View f2629c;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2627a = iVar;
            this.f2628b = viewPropertyAnimator;
            this.f2629c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2628b.setListener(null);
            this.f2629c.setAlpha(1.0f);
            this.f2629c.setTranslationX(BitmapDescriptorFactory.HUE_RED);
            this.f2629c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            C0475d.this.m2891C(this.f2627a.f2635a, true);
            C0475d.this.f2606r.remove(this.f2627a.f2635a);
            C0475d.this.m2799W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C0475d.this.m2892D(this.f2627a.f2635a, true);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$h */
    public class h extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ i f2631a;

        /* renamed from: b */
        public final /* synthetic */ ViewPropertyAnimator f2632b;

        /* renamed from: c */
        public final /* synthetic */ View f2633c;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f2631a = iVar;
            this.f2632b = viewPropertyAnimator;
            this.f2633c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2632b.setListener(null);
            this.f2633c.setAlpha(1.0f);
            this.f2633c.setTranslationX(BitmapDescriptorFactory.HUE_RED);
            this.f2633c.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            C0475d.this.m2891C(this.f2631a.f2636b, false);
            C0475d.this.f2606r.remove(this.f2631a.f2636b);
            C0475d.this.m2799W();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C0475d.this.m2892D(this.f2631a.f2636b, false);
        }
    }

    /* renamed from: androidx.recyclerview.widget.d$j */
    public static class j {

        /* renamed from: a */
        public RecyclerView.AbstractC0442c0 f2641a;

        /* renamed from: b */
        public int f2642b;

        /* renamed from: c */
        public int f2643c;

        /* renamed from: d */
        public int f2644d;

        /* renamed from: e */
        public int f2645e;

        public j(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9, int i10, int i11, int i12) {
            this.f2641a = abstractC0442c0;
            this.f2642b = i9;
            this.f2643c = i10;
            this.f2644d = i11;
            this.f2645e = i12;
        }
    }

    /* renamed from: R */
    public void m2794R(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        View view = abstractC0442c0.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2603o.add(abstractC0442c0);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(m2405l()).setListener(new e(abstractC0442c0, view, viewPropertyAnimatorAnimate)).start();
    }

    /* renamed from: S */
    public void m2795S(i iVar) {
        RecyclerView.AbstractC0442c0 abstractC0442c0 = iVar.f2635a;
        View view = abstractC0442c0 == null ? null : abstractC0442c0.itemView;
        RecyclerView.AbstractC0442c0 abstractC0442c02 = iVar.f2636b;
        View view2 = abstractC0442c02 != null ? abstractC0442c02.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(m2406m());
            this.f2606r.add(iVar.f2635a);
            duration.translationX(iVar.f2639e - iVar.f2637c);
            duration.translationY(iVar.f2640f - iVar.f2638d);
            duration.alpha(BitmapDescriptorFactory.HUE_RED).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.f2606r.add(iVar.f2636b);
            viewPropertyAnimatorAnimate.translationX(BitmapDescriptorFactory.HUE_RED).translationY(BitmapDescriptorFactory.HUE_RED).setDuration(m2406m()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    /* renamed from: T */
    public void m2796T(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9, int i10, int i11, int i12) {
        View view = abstractC0442c0.itemView;
        int i13 = i11 - i9;
        int i14 = i12 - i10;
        if (i13 != 0) {
            view.animate().translationX(BitmapDescriptorFactory.HUE_RED);
        }
        if (i14 != 0) {
            view.animate().translationY(BitmapDescriptorFactory.HUE_RED);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2604p.add(abstractC0442c0);
        viewPropertyAnimatorAnimate.setDuration(m2407n()).setListener(new f(abstractC0442c0, i13, view, i14, viewPropertyAnimatorAnimate)).start();
    }

    /* renamed from: U */
    public final void m2797U(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        View view = abstractC0442c0.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.f2605q.add(abstractC0442c0);
        viewPropertyAnimatorAnimate.setDuration(m2408o()).alpha(BitmapDescriptorFactory.HUE_RED).setListener(new d(abstractC0442c0, viewPropertyAnimatorAnimate, view)).start();
    }

    /* renamed from: V */
    public void m2798V(List<RecyclerView.AbstractC0442c0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
        }
    }

    /* renamed from: W */
    public void m2799W() {
        if (mo2409p()) {
            return;
        }
        m2402i();
    }

    /* renamed from: X */
    public final void m2800X(List<i> list, RecyclerView.AbstractC0442c0 abstractC0442c0) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (m2802Z(iVar, abstractC0442c0) && iVar.f2635a == null && iVar.f2636b == null) {
                list.remove(iVar);
            }
        }
    }

    /* renamed from: Y */
    public final void m2801Y(i iVar) {
        RecyclerView.AbstractC0442c0 abstractC0442c0 = iVar.f2635a;
        if (abstractC0442c0 != null) {
            m2802Z(iVar, abstractC0442c0);
        }
        RecyclerView.AbstractC0442c0 abstractC0442c02 = iVar.f2636b;
        if (abstractC0442c02 != null) {
            m2802Z(iVar, abstractC0442c02);
        }
    }

    /* renamed from: Z */
    public final boolean m2802Z(i iVar, RecyclerView.AbstractC0442c0 abstractC0442c0) {
        boolean z8 = false;
        if (iVar.f2636b == abstractC0442c0) {
            iVar.f2636b = null;
        } else {
            if (iVar.f2635a != abstractC0442c0) {
                return false;
            }
            iVar.f2635a = null;
            z8 = true;
        }
        abstractC0442c0.itemView.setAlpha(1.0f);
        abstractC0442c0.itemView.setTranslationX(BitmapDescriptorFactory.HUE_RED);
        abstractC0442c0.itemView.setTranslationY(BitmapDescriptorFactory.HUE_RED);
        m2891C(abstractC0442c0, z8);
        return true;
    }

    /* renamed from: a0 */
    public final void m2803a0(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        if (f2595s == null) {
            f2595s = new ValueAnimator().getInterpolator();
        }
        abstractC0442c0.itemView.animate().setInterpolator(f2595s);
        mo2403j(abstractC0442c0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: g */
    public boolean mo2400g(RecyclerView.AbstractC0442c0 abstractC0442c0, List<Object> list) {
        return !list.isEmpty() || super.mo2400g(abstractC0442c0, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: j */
    public void mo2403j(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        View view = abstractC0442c0.itemView;
        view.animate().cancel();
        int size = this.f2598j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.f2598j.get(size).f2641a == abstractC0442c0) {
                view.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                view.setTranslationX(BitmapDescriptorFactory.HUE_RED);
                m2893E(abstractC0442c0);
                this.f2598j.remove(size);
            }
        }
        m2800X(this.f2599k, abstractC0442c0);
        if (this.f2596h.remove(abstractC0442c0)) {
            view.setAlpha(1.0f);
            m2895G(abstractC0442c0);
        }
        if (this.f2597i.remove(abstractC0442c0)) {
            view.setAlpha(1.0f);
            m2889A(abstractC0442c0);
        }
        for (int size2 = this.f2602n.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.f2602n.get(size2);
            m2800X(arrayList, abstractC0442c0);
            if (arrayList.isEmpty()) {
                this.f2602n.remove(size2);
            }
        }
        for (int size3 = this.f2601m.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.f2601m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f2641a == abstractC0442c0) {
                    view.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                    view.setTranslationX(BitmapDescriptorFactory.HUE_RED);
                    m2893E(abstractC0442c0);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f2601m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f2600l.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.AbstractC0442c0> arrayList3 = this.f2600l.get(size5);
            if (arrayList3.remove(abstractC0442c0)) {
                view.setAlpha(1.0f);
                m2889A(abstractC0442c0);
                if (arrayList3.isEmpty()) {
                    this.f2600l.remove(size5);
                }
            }
        }
        this.f2605q.remove(abstractC0442c0);
        this.f2603o.remove(abstractC0442c0);
        this.f2606r.remove(abstractC0442c0);
        this.f2604p.remove(abstractC0442c0);
        m2799W();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: k */
    public void mo2404k() {
        int size = this.f2598j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.f2598j.get(size);
            View view = jVar.f2641a.itemView;
            view.setTranslationY(BitmapDescriptorFactory.HUE_RED);
            view.setTranslationX(BitmapDescriptorFactory.HUE_RED);
            m2893E(jVar.f2641a);
            this.f2598j.remove(size);
        }
        for (int size2 = this.f2596h.size() - 1; size2 >= 0; size2--) {
            m2895G(this.f2596h.get(size2));
            this.f2596h.remove(size2);
        }
        int size3 = this.f2597i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.AbstractC0442c0 abstractC0442c0 = this.f2597i.get(size3);
            abstractC0442c0.itemView.setAlpha(1.0f);
            m2889A(abstractC0442c0);
            this.f2597i.remove(size3);
        }
        for (int size4 = this.f2599k.size() - 1; size4 >= 0; size4--) {
            m2801Y(this.f2599k.get(size4));
        }
        this.f2599k.clear();
        if (mo2409p()) {
            for (int size5 = this.f2601m.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.f2601m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f2641a.itemView;
                    view2.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                    view2.setTranslationX(BitmapDescriptorFactory.HUE_RED);
                    m2893E(jVar2.f2641a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f2601m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f2600l.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.AbstractC0442c0> arrayList2 = this.f2600l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.AbstractC0442c0 abstractC0442c02 = arrayList2.get(size8);
                    abstractC0442c02.itemView.setAlpha(1.0f);
                    m2889A(abstractC0442c02);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f2600l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f2602n.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.f2602n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    m2801Y(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f2602n.remove(arrayList3);
                    }
                }
            }
            m2798V(this.f2605q);
            m2798V(this.f2604p);
            m2798V(this.f2603o);
            m2798V(this.f2606r);
            m2402i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: p */
    public boolean mo2409p() {
        return (this.f2597i.isEmpty() && this.f2599k.isEmpty() && this.f2598j.isEmpty() && this.f2596h.isEmpty() && this.f2604p.isEmpty() && this.f2605q.isEmpty() && this.f2603o.isEmpty() && this.f2606r.isEmpty() && this.f2601m.isEmpty() && this.f2600l.isEmpty() && this.f2602n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l
    /* renamed from: u */
    public void mo2414u() {
        boolean z8 = !this.f2596h.isEmpty();
        boolean z9 = !this.f2598j.isEmpty();
        boolean z10 = !this.f2599k.isEmpty();
        boolean z11 = !this.f2597i.isEmpty();
        if (z8 || z9 || z11 || z10) {
            Iterator<RecyclerView.AbstractC0442c0> it = this.f2596h.iterator();
            while (it.hasNext()) {
                m2797U(it.next());
            }
            this.f2596h.clear();
            if (z9) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.f2598j);
                this.f2601m.add(arrayList);
                this.f2598j.clear();
                a aVar = new a(arrayList);
                if (z8) {
                    C4647u.m18526V(arrayList.get(0).f2641a.itemView, aVar, m2408o());
                } else {
                    aVar.run();
                }
            }
            if (z10) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.f2599k);
                this.f2602n.add(arrayList2);
                this.f2599k.clear();
                b bVar = new b(arrayList2);
                if (z8) {
                    C4647u.m18526V(arrayList2.get(0).f2635a.itemView, bVar, m2408o());
                } else {
                    bVar.run();
                }
            }
            if (z11) {
                ArrayList<RecyclerView.AbstractC0442c0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.f2597i);
                this.f2600l.add(arrayList3);
                this.f2597i.clear();
                c cVar = new c(arrayList3);
                if (z8 || z9 || z10) {
                    C4647u.m18526V(arrayList3.get(0).itemView, cVar, (z8 ? m2408o() : 0L) + Math.max(z9 ? m2407n() : 0L, z10 ? m2406m() : 0L));
                } else {
                    cVar.run();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0486o
    /* renamed from: w */
    public boolean mo2804w(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2803a0(abstractC0442c0);
        abstractC0442c0.itemView.setAlpha(BitmapDescriptorFactory.HUE_RED);
        this.f2597i.add(abstractC0442c0);
        return true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0486o
    /* renamed from: x */
    public boolean mo2805x(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0442c0 abstractC0442c02, int i9, int i10, int i11, int i12) {
        if (abstractC0442c0 == abstractC0442c02) {
            return mo2806y(abstractC0442c0, i9, i10, i11, i12);
        }
        float translationX = abstractC0442c0.itemView.getTranslationX();
        float translationY = abstractC0442c0.itemView.getTranslationY();
        float alpha = abstractC0442c0.itemView.getAlpha();
        m2803a0(abstractC0442c0);
        int i13 = (int) ((i11 - i9) - translationX);
        int i14 = (int) ((i12 - i10) - translationY);
        abstractC0442c0.itemView.setTranslationX(translationX);
        abstractC0442c0.itemView.setTranslationY(translationY);
        abstractC0442c0.itemView.setAlpha(alpha);
        if (abstractC0442c02 != null) {
            m2803a0(abstractC0442c02);
            abstractC0442c02.itemView.setTranslationX(-i13);
            abstractC0442c02.itemView.setTranslationY(-i14);
            abstractC0442c02.itemView.setAlpha(BitmapDescriptorFactory.HUE_RED);
        }
        this.f2599k.add(new i(abstractC0442c0, abstractC0442c02, i9, i10, i11, i12));
        return true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0486o
    /* renamed from: y */
    public boolean mo2806y(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9, int i10, int i11, int i12) {
        View view = abstractC0442c0.itemView;
        int translationX = i9 + ((int) view.getTranslationX());
        int translationY = i10 + ((int) abstractC0442c0.itemView.getTranslationY());
        m2803a0(abstractC0442c0);
        int i13 = i11 - translationX;
        int i14 = i12 - translationY;
        if (i13 == 0 && i14 == 0) {
            m2893E(abstractC0442c0);
            return false;
        }
        if (i13 != 0) {
            view.setTranslationX(-i13);
        }
        if (i14 != 0) {
            view.setTranslationY(-i14);
        }
        this.f2598j.add(new j(abstractC0442c0, translationX, translationY, i11, i12));
        return true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0486o
    /* renamed from: z */
    public boolean mo2807z(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2803a0(abstractC0442c0);
        this.f2596h.add(abstractC0442c0);
        return true;
    }

    /* renamed from: androidx.recyclerview.widget.d$i */
    public static class i {

        /* renamed from: a */
        public RecyclerView.AbstractC0442c0 f2635a;

        /* renamed from: b */
        public RecyclerView.AbstractC0442c0 f2636b;

        /* renamed from: c */
        public int f2637c;

        /* renamed from: d */
        public int f2638d;

        /* renamed from: e */
        public int f2639e;

        /* renamed from: f */
        public int f2640f;

        public i(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0442c0 abstractC0442c02) {
            this.f2635a = abstractC0442c0;
            this.f2636b = abstractC0442c02;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f2635a + ", newHolder=" + this.f2636b + ", fromX=" + this.f2637c + ", fromY=" + this.f2638d + ", toX=" + this.f2639e + ", toY=" + this.f2640f + '}';
        }

        public i(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0442c0 abstractC0442c02, int i9, int i10, int i11, int i12) {
            this(abstractC0442c0, abstractC0442c02);
            this.f2637c = i9;
            this.f2638d = i10;
            this.f2639e = i11;
            this.f2640f = i12;
        }
    }
}
