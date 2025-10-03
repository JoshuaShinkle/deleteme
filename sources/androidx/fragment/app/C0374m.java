package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.fragment.app.m */
/* loaded from: classes.dex */
public class C0374m extends AbstractC0375n {

    /* renamed from: androidx.fragment.app.m$a */
    public class a extends Transition.EpicenterCallback {

        /* renamed from: a */
        public final /* synthetic */ Rect f2151a;

        public a(Rect rect) {
            this.f2151a = rect;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition transition) {
            return this.f2151a;
        }
    }

    /* renamed from: androidx.fragment.app.m$b */
    public class b implements Transition.TransitionListener {

        /* renamed from: a */
        public final /* synthetic */ View f2153a;

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2154b;

        public b(View view, ArrayList arrayList) {
            this.f2153a = view;
            this.f2154b = arrayList;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
            this.f2153a.setVisibility(8);
            int size = this.f2154b.size();
            for (int i9 = 0; i9 < size; i9++) {
                ((View) this.f2154b.get(i9)).setVisibility(0);
            }
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
        }
    }

    /* renamed from: androidx.fragment.app.m$c */
    public class c implements Transition.TransitionListener {

        /* renamed from: a */
        public final /* synthetic */ Object f2156a;

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2157b;

        /* renamed from: c */
        public final /* synthetic */ Object f2158c;

        /* renamed from: d */
        public final /* synthetic */ ArrayList f2159d;

        /* renamed from: e */
        public final /* synthetic */ Object f2160e;

        /* renamed from: f */
        public final /* synthetic */ ArrayList f2161f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f2156a = obj;
            this.f2157b = arrayList;
            this.f2158c = obj2;
            this.f2159d = arrayList2;
            this.f2160e = obj3;
            this.f2161f = arrayList3;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition transition) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
            Object obj = this.f2156a;
            if (obj != null) {
                C0374m.this.mo2025q(obj, this.f2157b, null);
            }
            Object obj2 = this.f2158c;
            if (obj2 != null) {
                C0374m.this.mo2025q(obj2, this.f2159d, null);
            }
            Object obj3 = this.f2160e;
            if (obj3 != null) {
                C0374m.this.mo2025q(obj3, this.f2161f, null);
            }
        }
    }

    /* renamed from: androidx.fragment.app.m$d */
    public class d extends Transition.EpicenterCallback {

        /* renamed from: a */
        public final /* synthetic */ Rect f2163a;

        public d(Rect rect) {
            this.f2163a = rect;
        }

        @Override // android.transition.Transition.EpicenterCallback
        public Rect onGetEpicenter(Transition transition) {
            Rect rect = this.f2163a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return this.f2163a;
        }
    }

    /* renamed from: B */
    public static boolean m2015B(Transition transition) {
        return (AbstractC0375n.m2035l(transition.getTargetIds()) && AbstractC0375n.m2035l(transition.getTargetNames()) && AbstractC0375n.m2035l(transition.getTargetTypes())) ? false : true;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: A */
    public Object mo2016A(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) obj);
        return transitionSet;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: a */
    public void mo2017a(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: b */
    public void mo2018b(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition == null) {
            return;
        }
        int i9 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i9 < transitionCount) {
                mo2018b(transitionSet.getTransitionAt(i9), arrayList);
                i9++;
            }
            return;
        }
        if (m2015B(transition) || !AbstractC0375n.m2035l(transition.getTargets())) {
            return;
        }
        int size = arrayList.size();
        while (i9 < size) {
            transition.addTarget(arrayList.get(i9));
            i9++;
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: c */
    public void mo2019c(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: e */
    public boolean mo2020e(Object obj) {
        return obj instanceof Transition;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: g */
    public Object mo2021g(Object obj) {
        if (obj != null) {
            return ((Transition) obj).clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: m */
    public Object mo2022m(Object obj, Object obj2, Object obj3) {
        Transition ordering = (Transition) obj;
        Transition transition = (Transition) obj2;
        Transition transition2 = (Transition) obj3;
        if (ordering != null && transition != null) {
            ordering = new TransitionSet().addTransition(ordering).addTransition(transition).setOrdering(1);
        } else if (ordering == null) {
            ordering = transition != null ? transition : null;
        }
        if (transition2 == null) {
            return ordering;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (ordering != null) {
            transitionSet.addTransition(ordering);
        }
        transitionSet.addTransition(transition2);
        return transitionSet;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: n */
    public Object mo2023n(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.addTransition((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.addTransition((Transition) obj3);
        }
        return transitionSet;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: p */
    public void mo2024p(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: q */
    public void mo2025q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List<View> targets;
        Transition transition = (Transition) obj;
        int i9 = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i9 < transitionCount) {
                mo2025q(transitionSet.getTransitionAt(i9), arrayList, arrayList2);
                i9++;
            }
            return;
        }
        if (m2015B(transition) || (targets = transition.getTargets()) == null || targets.size() != arrayList.size() || !targets.containsAll(arrayList)) {
            return;
        }
        int size = arrayList2 == null ? 0 : arrayList2.size();
        while (i9 < size) {
            transition.addTarget(arrayList2.get(i9));
            i9++;
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            transition.removeTarget(arrayList.get(size2));
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: r */
    public void mo2026r(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).addListener(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: t */
    public void mo2027t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((Transition) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: u */
    public void mo2028u(Object obj, Rect rect) {
        if (obj != null) {
            ((Transition) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: v */
    public void mo2029v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            m2038k(view, rect);
            ((Transition) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: y */
    public void mo2030y(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List<View> targets = transitionSet.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC0375n.m2032d(targets, arrayList.get(i9));
        }
        targets.add(view);
        arrayList.add(view);
        mo2018b(transitionSet, arrayList);
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: z */
    public void mo2031z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.getTargets().clear();
            transitionSet.getTargets().addAll(arrayList2);
            mo2025q(transitionSet, arrayList, arrayList2);
        }
    }
}
