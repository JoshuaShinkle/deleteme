package androidx.fragment.app;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.AbstractC0372k;
import androidx.transition.C0519e;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p042d0.C4647u;
import p042d0.ViewTreeObserverOnPreDrawListenerC4644r;
import p132m.C5302a;

/* renamed from: androidx.fragment.app.l */
/* loaded from: classes.dex */
public class C0373l {

    /* renamed from: a */
    public static final int[] f2114a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* renamed from: b */
    public static final AbstractC0375n f2115b = new C0374m();

    /* renamed from: c */
    public static final AbstractC0375n f2116c = m2011w();

    /* renamed from: androidx.fragment.app.l$a */
    public static class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2117b;

        public a(ArrayList arrayList) {
            this.f2117b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0373l.m1987A(this.f2117b, 4);
        }
    }

    /* renamed from: androidx.fragment.app.l$b */
    public static class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Object f2118b;

        /* renamed from: c */
        public final /* synthetic */ AbstractC0375n f2119c;

        /* renamed from: d */
        public final /* synthetic */ View f2120d;

        /* renamed from: e */
        public final /* synthetic */ Fragment f2121e;

        /* renamed from: f */
        public final /* synthetic */ ArrayList f2122f;

        /* renamed from: g */
        public final /* synthetic */ ArrayList f2123g;

        /* renamed from: h */
        public final /* synthetic */ ArrayList f2124h;

        /* renamed from: i */
        public final /* synthetic */ Object f2125i;

        public b(Object obj, AbstractC0375n abstractC0375n, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f2118b = obj;
            this.f2119c = abstractC0375n;
            this.f2120d = view;
            this.f2121e = fragment;
            this.f2122f = arrayList;
            this.f2123g = arrayList2;
            this.f2124h = arrayList3;
            this.f2125i = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f2118b;
            if (obj != null) {
                this.f2119c.mo2024p(obj, this.f2120d);
                this.f2123g.addAll(C0373l.m1999k(this.f2119c, this.f2118b, this.f2121e, this.f2122f, this.f2120d));
            }
            if (this.f2124h != null) {
                if (this.f2125i != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f2120d);
                    this.f2119c.mo2025q(this.f2125i, this.f2124h, arrayList);
                }
                this.f2124h.clear();
                this.f2124h.add(this.f2120d);
            }
        }
    }

    /* renamed from: androidx.fragment.app.l$c */
    public static class c implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Fragment f2126b;

        /* renamed from: c */
        public final /* synthetic */ Fragment f2127c;

        /* renamed from: d */
        public final /* synthetic */ boolean f2128d;

        /* renamed from: e */
        public final /* synthetic */ C5302a f2129e;

        /* renamed from: f */
        public final /* synthetic */ View f2130f;

        /* renamed from: g */
        public final /* synthetic */ AbstractC0375n f2131g;

        /* renamed from: h */
        public final /* synthetic */ Rect f2132h;

        public c(Fragment fragment, Fragment fragment2, boolean z8, C5302a c5302a, View view, AbstractC0375n abstractC0375n, Rect rect) {
            this.f2126b = fragment;
            this.f2127c = fragment2;
            this.f2128d = z8;
            this.f2129e = c5302a;
            this.f2130f = view;
            this.f2131g = abstractC0375n;
            this.f2132h = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0373l.m1994f(this.f2126b, this.f2127c, this.f2128d, this.f2129e, false);
            View view = this.f2130f;
            if (view != null) {
                this.f2131g.m2038k(view, this.f2132h);
            }
        }
    }

    /* renamed from: androidx.fragment.app.l$d */
    public static class d implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ AbstractC0375n f2133b;

        /* renamed from: c */
        public final /* synthetic */ C5302a f2134c;

        /* renamed from: d */
        public final /* synthetic */ Object f2135d;

        /* renamed from: e */
        public final /* synthetic */ e f2136e;

        /* renamed from: f */
        public final /* synthetic */ ArrayList f2137f;

        /* renamed from: g */
        public final /* synthetic */ View f2138g;

        /* renamed from: h */
        public final /* synthetic */ Fragment f2139h;

        /* renamed from: i */
        public final /* synthetic */ Fragment f2140i;

        /* renamed from: j */
        public final /* synthetic */ boolean f2141j;

        /* renamed from: k */
        public final /* synthetic */ ArrayList f2142k;

        /* renamed from: l */
        public final /* synthetic */ Object f2143l;

        /* renamed from: m */
        public final /* synthetic */ Rect f2144m;

        public d(AbstractC0375n abstractC0375n, C5302a c5302a, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z8, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f2133b = abstractC0375n;
            this.f2134c = c5302a;
            this.f2135d = obj;
            this.f2136e = eVar;
            this.f2137f = arrayList;
            this.f2138g = view;
            this.f2139h = fragment;
            this.f2140i = fragment2;
            this.f2141j = z8;
            this.f2142k = arrayList2;
            this.f2143l = obj2;
            this.f2144m = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            C5302a<String, View> c5302aM1996h = C0373l.m1996h(this.f2133b, this.f2134c, this.f2135d, this.f2136e);
            if (c5302aM1996h != null) {
                this.f2137f.addAll(c5302aM1996h.values());
                this.f2137f.add(this.f2138g);
            }
            C0373l.m1994f(this.f2139h, this.f2140i, this.f2141j, c5302aM1996h, false);
            Object obj = this.f2135d;
            if (obj != null) {
                this.f2133b.mo2031z(obj, this.f2142k, this.f2137f);
                View viewM2007s = C0373l.m2007s(c5302aM1996h, this.f2136e, this.f2143l, this.f2141j);
                if (viewM2007s != null) {
                    this.f2133b.m2038k(viewM2007s, this.f2144m);
                }
            }
        }
    }

    /* renamed from: androidx.fragment.app.l$e */
    public static class e {

        /* renamed from: a */
        public Fragment f2145a;

        /* renamed from: b */
        public boolean f2146b;

        /* renamed from: c */
        public C0362a f2147c;

        /* renamed from: d */
        public Fragment f2148d;

        /* renamed from: e */
        public boolean f2149e;

        /* renamed from: f */
        public C0362a f2150f;
    }

    /* renamed from: A */
    public static void m1987A(ArrayList<View> arrayList, int i9) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i9);
        }
    }

    /* renamed from: B */
    public static void m1988B(LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h, ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, int i9, int i10, boolean z8) {
        if (layoutInflaterFactory2C0369h.f2040q < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i11 = i9; i11 < i10; i11++) {
            C0362a c0362a = arrayList.get(i11);
            if (arrayList2.get(i11).booleanValue()) {
                m1993e(c0362a, sparseArray, z8);
            } else {
                m1991c(c0362a, sparseArray, z8);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(layoutInflaterFactory2C0369h.f2041r.m1842e());
            int size = sparseArray.size();
            for (int i12 = 0; i12 < size; i12++) {
                int iKeyAt = sparseArray.keyAt(i12);
                C5302a<String, String> c5302aM1992d = m1992d(iKeyAt, arrayList, arrayList2, i9, i10);
                e eVar = (e) sparseArray.valueAt(i12);
                if (z8) {
                    m2003o(layoutInflaterFactory2C0369h, iKeyAt, eVar, view, c5302aM1992d);
                } else {
                    m2002n(layoutInflaterFactory2C0369h, iKeyAt, eVar, view, c5302aM1992d);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1989a(ArrayList<View> arrayList, C5302a<String, View> c5302a, Collection<String> collection) {
        for (int size = c5302a.size() - 1; size >= 0; size--) {
            View viewM20755m = c5302a.m20755m(size);
            if (collection.contains(C4647u.m18574z(viewM20755m))) {
                arrayList.add(viewM20755m);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0097  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m1990b(C0362a c0362a, AbstractC0372k.a aVar, SparseArray<e> sparseArray, boolean z8, boolean z9) throws Resources.NotFoundException {
        int i9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        View view;
        Fragment fragment = aVar.f2107b;
        if (fragment == null || (i9 = fragment.mContainerId) == 0) {
            return;
        }
        int i10 = z8 ? f2114a[aVar.f2106a] : aVar.f2106a;
        boolean z14 = false;
        if (i10 == 1) {
            if (z9) {
                z10 = (fragment.mAdded || fragment.mHidden) ? false : true;
                z11 = false;
                z12 = false;
                z14 = z10;
                z13 = true;
            } else {
                z10 = fragment.mIsNewlyAdded;
                z11 = false;
                z12 = false;
                z14 = z10;
                z13 = true;
            }
        } else if (i10 == 3) {
            boolean z15 = z9 ? !(!fragment.mAdded || fragment.mHidden) : !(fragment.mAdded || (view = fragment.mView) == null || view.getVisibility() != 0 || fragment.mPostponedAlpha < BitmapDescriptorFactory.HUE_RED);
            z12 = z15;
            z13 = false;
            z11 = true;
        } else if (i10 == 4) {
            if (!z9 ? !fragment.mAdded || fragment.mHidden : !fragment.mHiddenChanged || !fragment.mAdded || !fragment.mHidden) {
            }
            z12 = z15;
            z13 = false;
            z11 = true;
        } else if (i10 != 5) {
            if (i10 != 6) {
                if (i10 != 7) {
                    z13 = false;
                    z11 = false;
                    z12 = false;
                }
                if (z9) {
                }
            }
            if (z9) {
            }
            z12 = z15;
            z13 = false;
            z11 = true;
        } else if (z9) {
            if (!fragment.mHiddenChanged || fragment.mHidden || !fragment.mAdded) {
            }
            z11 = false;
            z12 = false;
            z14 = z10;
            z13 = true;
        } else {
            z10 = fragment.mHidden;
            z11 = false;
            z12 = false;
            z14 = z10;
            z13 = true;
        }
        e eVarM2004p = sparseArray.get(i9);
        if (z14) {
            eVarM2004p = m2004p(eVarM2004p, sparseArray, i9);
            eVarM2004p.f2145a = fragment;
            eVarM2004p.f2146b = z8;
            eVarM2004p.f2147c = c0362a;
        }
        e eVarM2004p2 = eVarM2004p;
        if (!z9 && z13) {
            if (eVarM2004p2 != null && eVarM2004p2.f2148d == fragment) {
                eVarM2004p2.f2148d = null;
            }
            LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = c0362a.f2003s;
            if (fragment.mState < 1 && layoutInflaterFactory2C0369h.f2040q >= 1 && !c0362a.f2104q) {
                layoutInflaterFactory2C0369h.m1888M0(fragment);
                layoutInflaterFactory2C0369h.m1902U0(fragment, 1, 0, 0, false);
            }
        }
        if (z12 && (eVarM2004p2 == null || eVarM2004p2.f2148d == null)) {
            eVarM2004p2 = m2004p(eVarM2004p2, sparseArray, i9);
            eVarM2004p2.f2148d = fragment;
            eVarM2004p2.f2149e = z8;
            eVarM2004p2.f2150f = c0362a;
        }
        if (z9 || !z11 || eVarM2004p2 == null || eVarM2004p2.f2145a != fragment) {
            return;
        }
        eVarM2004p2.f2145a = null;
    }

    /* renamed from: c */
    public static void m1991c(C0362a c0362a, SparseArray<e> sparseArray, boolean z8) throws Resources.NotFoundException {
        int size = c0362a.f2088a.size();
        for (int i9 = 0; i9 < size; i9++) {
            m1990b(c0362a, c0362a.f2088a.get(i9), sparseArray, false, z8);
        }
    }

    /* renamed from: d */
    public static C5302a<String, String> m1992d(int i9, ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, int i10, int i11) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        C5302a<String, String> c5302a = new C5302a<>();
        for (int i12 = i11 - 1; i12 >= i10; i12--) {
            C0362a c0362a = arrayList.get(i12);
            if (c0362a.m1786A(i9)) {
                boolean zBooleanValue = arrayList2.get(i12).booleanValue();
                ArrayList<String> arrayList5 = c0362a.f2102o;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = c0362a.f2102o;
                        arrayList4 = c0362a.f2103p;
                    } else {
                        ArrayList<String> arrayList6 = c0362a.f2102o;
                        arrayList3 = c0362a.f2103p;
                        arrayList4 = arrayList6;
                    }
                    for (int i13 = 0; i13 < size; i13++) {
                        String str = arrayList4.get(i13);
                        String str2 = arrayList3.get(i13);
                        String strRemove = c5302a.remove(str2);
                        if (strRemove != null) {
                            c5302a.put(str, strRemove);
                        } else {
                            c5302a.put(str, str2);
                        }
                    }
                }
            }
        }
        return c5302a;
    }

    /* renamed from: e */
    public static void m1993e(C0362a c0362a, SparseArray<e> sparseArray, boolean z8) throws Resources.NotFoundException {
        if (c0362a.f2003s.f2042s.mo1762c()) {
            for (int size = c0362a.f2088a.size() - 1; size >= 0; size--) {
                m1990b(c0362a, c0362a.f2088a.get(size), sparseArray, true, z8);
            }
        }
    }

    /* renamed from: f */
    public static void m1994f(Fragment fragment, Fragment fragment2, boolean z8, C5302a<String, View> c5302a, boolean z9) {
        if (z8) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    /* renamed from: g */
    public static boolean m1995g(AbstractC0375n abstractC0375n, List<Object> list) {
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (!abstractC0375n.mo2020e(list.get(i9))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    public static C5302a<String, View> m1996h(AbstractC0375n abstractC0375n, C5302a<String, String> c5302a, Object obj, e eVar) {
        ArrayList<String> arrayList;
        Fragment fragment = eVar.f2145a;
        View view = fragment.getView();
        if (c5302a.isEmpty() || obj == null || view == null) {
            c5302a.clear();
            return null;
        }
        C5302a<String, View> c5302a2 = new C5302a<>();
        abstractC0375n.m2037j(c5302a2, view);
        C0362a c0362a = eVar.f2147c;
        if (eVar.f2146b) {
            fragment.getExitTransitionCallback();
            arrayList = c0362a.f2102o;
        } else {
            fragment.getEnterTransitionCallback();
            arrayList = c0362a.f2103p;
        }
        if (arrayList != null) {
            c5302a2.m20694o(arrayList);
            c5302a2.m20694o(c5302a.values());
        }
        m2012x(c5302a, c5302a2);
        return c5302a2;
    }

    /* renamed from: i */
    public static C5302a<String, View> m1997i(AbstractC0375n abstractC0375n, C5302a<String, String> c5302a, Object obj, e eVar) {
        ArrayList<String> arrayList;
        if (c5302a.isEmpty() || obj == null) {
            c5302a.clear();
            return null;
        }
        Fragment fragment = eVar.f2148d;
        C5302a<String, View> c5302a2 = new C5302a<>();
        abstractC0375n.m2037j(c5302a2, fragment.requireView());
        C0362a c0362a = eVar.f2150f;
        if (eVar.f2149e) {
            fragment.getEnterTransitionCallback();
            arrayList = c0362a.f2103p;
        } else {
            fragment.getExitTransitionCallback();
            arrayList = c0362a.f2102o;
        }
        c5302a2.m20694o(arrayList);
        c5302a.m20694o(c5302a2.keySet());
        return c5302a2;
    }

    /* renamed from: j */
    public static AbstractC0375n m1998j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        AbstractC0375n abstractC0375n = f2115b;
        if (abstractC0375n != null && m1995g(abstractC0375n, arrayList)) {
            return abstractC0375n;
        }
        AbstractC0375n abstractC0375n2 = f2116c;
        if (abstractC0375n2 != null && m1995g(abstractC0375n2, arrayList)) {
            return abstractC0375n2;
        }
        if (abstractC0375n == null && abstractC0375n2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    /* renamed from: k */
    public static ArrayList<View> m1999k(AbstractC0375n abstractC0375n, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            abstractC0375n.m2036f(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        abstractC0375n.mo2018b(obj, arrayList2);
        return arrayList2;
    }

    /* renamed from: l */
    public static Object m2000l(AbstractC0375n abstractC0375n, ViewGroup viewGroup, View view, C5302a<String, String> c5302a, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objM2008t;
        C5302a<String, String> c5302a2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f2145a;
        Fragment fragment2 = eVar.f2148d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z8 = eVar.f2146b;
        if (c5302a.isEmpty()) {
            c5302a2 = c5302a;
            objM2008t = null;
        } else {
            objM2008t = m2008t(abstractC0375n, fragment, fragment2, z8);
            c5302a2 = c5302a;
        }
        C5302a<String, View> c5302aM1997i = m1997i(abstractC0375n, c5302a2, objM2008t, eVar);
        if (c5302a.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(c5302aM1997i.values());
            obj3 = objM2008t;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        m1994f(fragment, fragment2, z8, c5302aM1997i, true);
        if (obj3 != null) {
            rect = new Rect();
            abstractC0375n.mo2030y(obj3, view, arrayList);
            m2014z(abstractC0375n, obj3, obj2, c5302aM1997i, eVar.f2149e, eVar.f2150f);
            if (obj != null) {
                abstractC0375n.mo2028u(obj, rect);
            }
        } else {
            rect = null;
        }
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(viewGroup, new d(abstractC0375n, c5302a, obj3, eVar, arrayList2, view, fragment, fragment2, z8, arrayList, obj, rect));
        return obj3;
    }

    /* renamed from: m */
    public static Object m2001m(AbstractC0375n abstractC0375n, ViewGroup viewGroup, View view, C5302a<String, String> c5302a, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f2145a;
        Fragment fragment2 = eVar.f2148d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z8 = eVar.f2146b;
        Object objM2008t = c5302a.isEmpty() ? null : m2008t(abstractC0375n, fragment, fragment2, z8);
        C5302a<String, View> c5302aM1997i = m1997i(abstractC0375n, c5302a, objM2008t, eVar);
        C5302a<String, View> c5302aM1996h = m1996h(abstractC0375n, c5302a, objM2008t, eVar);
        if (c5302a.isEmpty()) {
            if (c5302aM1997i != null) {
                c5302aM1997i.clear();
            }
            if (c5302aM1996h != null) {
                c5302aM1996h.clear();
            }
            obj3 = null;
        } else {
            m1989a(arrayList, c5302aM1997i, c5302a.keySet());
            m1989a(arrayList2, c5302aM1996h, c5302a.values());
            obj3 = objM2008t;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        m1994f(fragment, fragment2, z8, c5302aM1997i, true);
        if (obj3 != null) {
            arrayList2.add(view);
            abstractC0375n.mo2030y(obj3, view, arrayList);
            m2014z(abstractC0375n, obj3, obj2, c5302aM1997i, eVar.f2149e, eVar.f2150f);
            Rect rect2 = new Rect();
            View viewM2007s = m2007s(c5302aM1996h, eVar, obj, z8);
            if (viewM2007s != null) {
                abstractC0375n.mo2028u(obj, rect2);
            }
            rect = rect2;
            view2 = viewM2007s;
        } else {
            view2 = null;
            rect = null;
        }
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(viewGroup, new c(fragment, fragment2, z8, c5302aM1996h, view2, abstractC0375n, rect));
        return obj3;
    }

    /* renamed from: n */
    public static void m2002n(LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h, int i9, e eVar, View view, C5302a<String, String> c5302a) {
        Fragment fragment;
        Fragment fragment2;
        AbstractC0375n abstractC0375nM1998j;
        Object obj;
        ViewGroup viewGroup = layoutInflaterFactory2C0369h.f2042s.mo1762c() ? (ViewGroup) layoutInflaterFactory2C0369h.f2042s.mo1761b(i9) : null;
        if (viewGroup == null || (abstractC0375nM1998j = m1998j((fragment2 = eVar.f2148d), (fragment = eVar.f2145a))) == null) {
            return;
        }
        boolean z8 = eVar.f2146b;
        boolean z9 = eVar.f2149e;
        Object objM2005q = m2005q(abstractC0375nM1998j, fragment, z8);
        Object objM2006r = m2006r(abstractC0375nM1998j, fragment2, z9);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objM2000l = m2000l(abstractC0375nM1998j, viewGroup, view, c5302a, eVar, arrayList, arrayList2, objM2005q, objM2006r);
        if (objM2005q == null && objM2000l == null) {
            obj = objM2006r;
            if (obj == null) {
                return;
            }
        } else {
            obj = objM2006r;
        }
        ArrayList<View> arrayListM1999k = m1999k(abstractC0375nM1998j, obj, fragment2, arrayList, view);
        Object obj2 = (arrayListM1999k == null || arrayListM1999k.isEmpty()) ? null : obj;
        abstractC0375nM1998j.mo2017a(objM2005q, view);
        Object objM2009u = m2009u(abstractC0375nM1998j, objM2005q, obj2, objM2000l, fragment, eVar.f2146b);
        if (objM2009u != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            abstractC0375nM1998j.mo2027t(objM2009u, objM2005q, arrayList3, obj2, arrayListM1999k, objM2000l, arrayList2);
            m2013y(abstractC0375nM1998j, viewGroup, fragment, view, arrayList2, objM2005q, arrayList3, obj2, arrayListM1999k);
            abstractC0375nM1998j.m2041w(viewGroup, arrayList2, c5302a);
            abstractC0375nM1998j.mo2019c(viewGroup, objM2009u);
            abstractC0375nM1998j.m2040s(viewGroup, arrayList2, c5302a);
        }
    }

    /* renamed from: o */
    public static void m2003o(LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h, int i9, e eVar, View view, C5302a<String, String> c5302a) {
        Fragment fragment;
        Fragment fragment2;
        AbstractC0375n abstractC0375nM1998j;
        Object obj;
        ViewGroup viewGroup = layoutInflaterFactory2C0369h.f2042s.mo1762c() ? (ViewGroup) layoutInflaterFactory2C0369h.f2042s.mo1761b(i9) : null;
        if (viewGroup == null || (abstractC0375nM1998j = m1998j((fragment2 = eVar.f2148d), (fragment = eVar.f2145a))) == null) {
            return;
        }
        boolean z8 = eVar.f2146b;
        boolean z9 = eVar.f2149e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objM2005q = m2005q(abstractC0375nM1998j, fragment, z8);
        Object objM2006r = m2006r(abstractC0375nM1998j, fragment2, z9);
        Object objM2001m = m2001m(abstractC0375nM1998j, viewGroup, view, c5302a, eVar, arrayList2, arrayList, objM2005q, objM2006r);
        if (objM2005q == null && objM2001m == null) {
            obj = objM2006r;
            if (obj == null) {
                return;
            }
        } else {
            obj = objM2006r;
        }
        ArrayList<View> arrayListM1999k = m1999k(abstractC0375nM1998j, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListM1999k2 = m1999k(abstractC0375nM1998j, objM2005q, fragment, arrayList, view);
        m1987A(arrayListM1999k2, 4);
        Object objM2009u = m2009u(abstractC0375nM1998j, objM2005q, obj, objM2001m, fragment, z8);
        if (objM2009u != null) {
            m2010v(abstractC0375nM1998j, obj, fragment2, arrayListM1999k);
            ArrayList<String> arrayListM2039o = abstractC0375nM1998j.m2039o(arrayList);
            abstractC0375nM1998j.mo2027t(objM2009u, objM2005q, arrayListM1999k2, obj, arrayListM1999k, objM2001m, arrayList);
            abstractC0375nM1998j.mo2019c(viewGroup, objM2009u);
            abstractC0375nM1998j.m2042x(viewGroup, arrayList2, arrayList, arrayListM2039o, c5302a);
            m1987A(arrayListM1999k2, 0);
            abstractC0375nM1998j.mo2031z(objM2001m, arrayList2, arrayList);
        }
    }

    /* renamed from: p */
    public static e m2004p(e eVar, SparseArray<e> sparseArray, int i9) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i9, eVar2);
        return eVar2;
    }

    /* renamed from: q */
    public static Object m2005q(AbstractC0375n abstractC0375n, Fragment fragment, boolean z8) {
        if (fragment == null) {
            return null;
        }
        return abstractC0375n.mo2021g(z8 ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    /* renamed from: r */
    public static Object m2006r(AbstractC0375n abstractC0375n, Fragment fragment, boolean z8) {
        if (fragment == null) {
            return null;
        }
        return abstractC0375n.mo2021g(z8 ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    /* renamed from: s */
    public static View m2007s(C5302a<String, View> c5302a, e eVar, Object obj, boolean z8) {
        ArrayList<String> arrayList;
        C0362a c0362a = eVar.f2147c;
        if (obj == null || c5302a == null || (arrayList = c0362a.f2102o) == null || arrayList.isEmpty()) {
            return null;
        }
        return c5302a.get(z8 ? c0362a.f2102o.get(0) : c0362a.f2103p.get(0));
    }

    /* renamed from: t */
    public static Object m2008t(AbstractC0375n abstractC0375n, Fragment fragment, Fragment fragment2, boolean z8) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return abstractC0375n.mo2016A(abstractC0375n.mo2021g(z8 ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    /* renamed from: u */
    public static Object m2009u(AbstractC0375n abstractC0375n, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z8) {
        return (obj == null || obj2 == null || fragment == null) ? true : z8 ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? abstractC0375n.mo2023n(obj2, obj, obj3) : abstractC0375n.mo2022m(obj2, obj, obj3);
    }

    /* renamed from: v */
    public static void m2010v(AbstractC0375n abstractC0375n, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            abstractC0375n.mo2026r(obj, fragment.getView(), arrayList);
            ViewTreeObserverOnPreDrawListenerC4644r.m18500a(fragment.mContainer, new a(arrayList));
        }
    }

    /* renamed from: w */
    public static AbstractC0375n m2011w() {
        try {
            return (AbstractC0375n) C0519e.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: x */
    public static void m2012x(C5302a<String, String> c5302a, C5302a<String, View> c5302a2) {
        for (int size = c5302a.size() - 1; size >= 0; size--) {
            if (!c5302a2.containsKey(c5302a.m20755m(size))) {
                c5302a.mo20753k(size);
            }
        }
    }

    /* renamed from: y */
    public static void m2013y(AbstractC0375n abstractC0375n, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(viewGroup, new b(obj, abstractC0375n, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    /* renamed from: z */
    public static void m2014z(AbstractC0375n abstractC0375n, Object obj, Object obj2, C5302a<String, View> c5302a, boolean z8, C0362a c0362a) {
        ArrayList<String> arrayList = c0362a.f2102o;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = c5302a.get(z8 ? c0362a.f2103p.get(0) : c0362a.f2102o.get(0));
        abstractC0375n.mo2029v(obj, view);
        if (obj2 != null) {
            abstractC0375n.mo2029v(obj2, view);
        }
    }
}
