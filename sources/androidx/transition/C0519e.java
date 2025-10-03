package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.AbstractC0375n;
import androidx.transition.AbstractC0532m;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.transition.e */
/* loaded from: classes.dex */
public class C0519e extends AbstractC0375n {

    /* renamed from: androidx.transition.e$a */
    public class a extends AbstractC0532m.f {

        /* renamed from: a */
        public final /* synthetic */ Rect f2891a;

        public a(Rect rect) {
            this.f2891a = rect;
        }

        @Override // androidx.transition.AbstractC0532m.f
        /* renamed from: a */
        public Rect mo3072a(AbstractC0532m abstractC0532m) {
            return this.f2891a;
        }
    }

    /* renamed from: androidx.transition.e$b */
    public class b implements AbstractC0532m.g {

        /* renamed from: a */
        public final /* synthetic */ View f2893a;

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2894b;

        public b(View view, ArrayList arrayList) {
            this.f2893a = view;
            this.f2894b = arrayList;
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: a */
        public void mo3073a(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: b */
        public void mo3050b(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) {
            abstractC0532m.removeListener(this);
            this.f2893a.setVisibility(8);
            int size = this.f2894b.size();
            for (int i9 = 0; i9 < size; i9++) {
                ((View) this.f2894b.get(i9)).setVisibility(0);
            }
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: d */
        public void mo3052d(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: e */
        public void mo3053e(AbstractC0532m abstractC0532m) {
        }
    }

    /* renamed from: androidx.transition.e$c */
    public class c implements AbstractC0532m.g {

        /* renamed from: a */
        public final /* synthetic */ Object f2896a;

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2897b;

        /* renamed from: c */
        public final /* synthetic */ Object f2898c;

        /* renamed from: d */
        public final /* synthetic */ ArrayList f2899d;

        /* renamed from: e */
        public final /* synthetic */ Object f2900e;

        /* renamed from: f */
        public final /* synthetic */ ArrayList f2901f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f2896a = obj;
            this.f2897b = arrayList;
            this.f2898c = obj2;
            this.f2899d = arrayList2;
            this.f2900e = obj3;
            this.f2901f = arrayList3;
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: a */
        public void mo3073a(AbstractC0532m abstractC0532m) {
            Object obj = this.f2896a;
            if (obj != null) {
                C0519e.this.mo2025q(obj, this.f2897b, null);
            }
            Object obj2 = this.f2898c;
            if (obj2 != null) {
                C0519e.this.mo2025q(obj2, this.f2899d, null);
            }
            Object obj3 = this.f2900e;
            if (obj3 != null) {
                C0519e.this.mo2025q(obj3, this.f2901f, null);
            }
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: b */
        public void mo3050b(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: d */
        public void mo3052d(AbstractC0532m abstractC0532m) {
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: e */
        public void mo3053e(AbstractC0532m abstractC0532m) {
        }
    }

    /* renamed from: androidx.transition.e$d */
    public class d extends AbstractC0532m.f {

        /* renamed from: a */
        public final /* synthetic */ Rect f2903a;

        public d(Rect rect) {
            this.f2903a = rect;
        }

        @Override // androidx.transition.AbstractC0532m.f
        /* renamed from: a */
        public Rect mo3072a(AbstractC0532m abstractC0532m) {
            Rect rect = this.f2903a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return this.f2903a;
        }
    }

    /* renamed from: B */
    public static boolean m3071B(AbstractC0532m abstractC0532m) {
        return (AbstractC0375n.m2035l(abstractC0532m.getTargetIds()) && AbstractC0375n.m2035l(abstractC0532m.getTargetNames()) && AbstractC0375n.m2035l(abstractC0532m.getTargetTypes())) ? false : true;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: A */
    public Object mo2016A(Object obj) {
        if (obj == null) {
            return null;
        }
        C0536q c0536q = new C0536q();
        c0536q.m3101g((AbstractC0532m) obj);
        return c0536q;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: a */
    public void mo2017a(Object obj, View view) {
        if (obj != null) {
            ((AbstractC0532m) obj).addTarget(view);
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: b */
    public void mo2018b(Object obj, ArrayList<View> arrayList) {
        AbstractC0532m abstractC0532m = (AbstractC0532m) obj;
        if (abstractC0532m == null) {
            return;
        }
        int i9 = 0;
        if (abstractC0532m instanceof C0536q) {
            C0536q c0536q = (C0536q) abstractC0532m;
            int iM3103i = c0536q.m3103i();
            while (i9 < iM3103i) {
                mo2018b(c0536q.m3102h(i9), arrayList);
                i9++;
            }
            return;
        }
        if (m3071B(abstractC0532m) || !AbstractC0375n.m2035l(abstractC0532m.getTargets())) {
            return;
        }
        int size = arrayList.size();
        while (i9 < size) {
            abstractC0532m.addTarget(arrayList.get(i9));
            i9++;
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: c */
    public void mo2019c(ViewGroup viewGroup, Object obj) {
        C0534o.m3091a(viewGroup, (AbstractC0532m) obj);
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: e */
    public boolean mo2020e(Object obj) {
        return obj instanceof AbstractC0532m;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: g */
    public Object mo2021g(Object obj) {
        if (obj != null) {
            return ((AbstractC0532m) obj).mo25565clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: m */
    public Object mo2022m(Object obj, Object obj2, Object obj3) {
        AbstractC0532m abstractC0532mM3111q = (AbstractC0532m) obj;
        AbstractC0532m abstractC0532m = (AbstractC0532m) obj2;
        AbstractC0532m abstractC0532m2 = (AbstractC0532m) obj3;
        if (abstractC0532mM3111q != null && abstractC0532m != null) {
            abstractC0532mM3111q = new C0536q().m3101g(abstractC0532mM3111q).m3101g(abstractC0532m).m3111q(1);
        } else if (abstractC0532mM3111q == null) {
            abstractC0532mM3111q = abstractC0532m != null ? abstractC0532m : null;
        }
        if (abstractC0532m2 == null) {
            return abstractC0532mM3111q;
        }
        C0536q c0536q = new C0536q();
        if (abstractC0532mM3111q != null) {
            c0536q.m3101g(abstractC0532mM3111q);
        }
        c0536q.m3101g(abstractC0532m2);
        return c0536q;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: n */
    public Object mo2023n(Object obj, Object obj2, Object obj3) {
        C0536q c0536q = new C0536q();
        if (obj != null) {
            c0536q.m3101g((AbstractC0532m) obj);
        }
        if (obj2 != null) {
            c0536q.m3101g((AbstractC0532m) obj2);
        }
        if (obj3 != null) {
            c0536q.m3101g((AbstractC0532m) obj3);
        }
        return c0536q;
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: p */
    public void mo2024p(Object obj, View view) {
        if (obj != null) {
            ((AbstractC0532m) obj).removeTarget(view);
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: q */
    public void mo2025q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        AbstractC0532m abstractC0532m = (AbstractC0532m) obj;
        int i9 = 0;
        if (abstractC0532m instanceof C0536q) {
            C0536q c0536q = (C0536q) abstractC0532m;
            int iM3103i = c0536q.m3103i();
            while (i9 < iM3103i) {
                mo2025q(c0536q.m3102h(i9), arrayList, arrayList2);
                i9++;
            }
            return;
        }
        if (m3071B(abstractC0532m)) {
            return;
        }
        List<View> targets = abstractC0532m.getTargets();
        if (targets.size() == arrayList.size() && targets.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i9 < size) {
                abstractC0532m.addTarget(arrayList2.get(i9));
                i9++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                abstractC0532m.removeTarget(arrayList.get(size2));
            }
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: r */
    public void mo2026r(Object obj, View view, ArrayList<View> arrayList) {
        ((AbstractC0532m) obj).addListener(new b(view, arrayList));
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: t */
    public void mo2027t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((AbstractC0532m) obj).addListener(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: u */
    public void mo2028u(Object obj, Rect rect) {
        if (obj != null) {
            ((AbstractC0532m) obj).setEpicenterCallback(new d(rect));
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: v */
    public void mo2029v(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            m2038k(view, rect);
            ((AbstractC0532m) obj).setEpicenterCallback(new a(rect));
        }
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: y */
    public void mo2030y(Object obj, View view, ArrayList<View> arrayList) {
        C0536q c0536q = (C0536q) obj;
        List<View> targets = c0536q.getTargets();
        targets.clear();
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC0375n.m2032d(targets, arrayList.get(i9));
        }
        targets.add(view);
        arrayList.add(view);
        mo2018b(c0536q, arrayList);
    }

    @Override // androidx.fragment.app.AbstractC0375n
    /* renamed from: z */
    public void mo2031z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        C0536q c0536q = (C0536q) obj;
        if (c0536q != null) {
            c0536q.getTargets().clear();
            c0536q.getTargets().addAll(arrayList2);
            mo2025q(c0536q, arrayList, arrayList2);
        }
    }
}
