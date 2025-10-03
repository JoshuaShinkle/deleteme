package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p042d0.C4616b0;
import p042d0.C4647u;
import p042d0.ViewTreeObserverOnPreDrawListenerC4644r;

@SuppressLint({"UnknownNullness"})
/* renamed from: androidx.fragment.app.n */
/* loaded from: classes.dex */
public abstract class AbstractC0375n {

    /* renamed from: androidx.fragment.app.n$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ int f2165b;

        /* renamed from: c */
        public final /* synthetic */ ArrayList f2166c;

        /* renamed from: d */
        public final /* synthetic */ ArrayList f2167d;

        /* renamed from: e */
        public final /* synthetic */ ArrayList f2168e;

        /* renamed from: f */
        public final /* synthetic */ ArrayList f2169f;

        public a(int i9, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.f2165b = i9;
            this.f2166c = arrayList;
            this.f2167d = arrayList2;
            this.f2168e = arrayList3;
            this.f2169f = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i9 = 0; i9 < this.f2165b; i9++) {
                C4647u.m18562p0((View) this.f2166c.get(i9), (String) this.f2167d.get(i9));
                C4647u.m18562p0((View) this.f2168e.get(i9), (String) this.f2169f.get(i9));
            }
        }
    }

    /* renamed from: androidx.fragment.app.n$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2171b;

        /* renamed from: c */
        public final /* synthetic */ Map f2172c;

        public b(ArrayList arrayList, Map map) {
            this.f2171b = arrayList;
            this.f2172c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f2171b.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = (View) this.f2171b.get(i9);
                String strM18574z = C4647u.m18574z(view);
                if (strM18574z != null) {
                    C4647u.m18562p0(view, AbstractC0375n.m2034i(this.f2172c, strM18574z));
                }
            }
        }
    }

    /* renamed from: androidx.fragment.app.n$c */
    public class c implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ArrayList f2174b;

        /* renamed from: c */
        public final /* synthetic */ Map f2175c;

        public c(ArrayList arrayList, Map map) {
            this.f2174b = arrayList;
            this.f2175c = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.f2174b.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = (View) this.f2174b.get(i9);
                C4647u.m18562p0(view, (String) this.f2175c.get(C4647u.m18574z(view)));
            }
        }
    }

    /* renamed from: d */
    public static void m2032d(List<View> list, View view) {
        int size = list.size();
        if (m2033h(list, view, size)) {
            return;
        }
        list.add(view);
        for (int i9 = size; i9 < list.size(); i9++) {
            View view2 = list.get(i9);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i10 = 0; i10 < childCount; i10++) {
                    View childAt = viewGroup.getChildAt(i10);
                    if (!m2033h(list, childAt, size)) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    /* renamed from: h */
    public static boolean m2033h(List<View> list, View view, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            if (list.get(i10) == view) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: i */
    public static String m2034i(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* renamed from: l */
    public static boolean m2035l(List list) {
        return list == null || list.isEmpty();
    }

    /* renamed from: A */
    public abstract Object mo2016A(Object obj);

    /* renamed from: a */
    public abstract void mo2017a(Object obj, View view);

    /* renamed from: b */
    public abstract void mo2018b(Object obj, ArrayList<View> arrayList);

    /* renamed from: c */
    public abstract void mo2019c(ViewGroup viewGroup, Object obj);

    /* renamed from: e */
    public abstract boolean mo2020e(Object obj);

    /* renamed from: f */
    public void m2036f(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (!(view instanceof ViewGroup)) {
                arrayList.add(view);
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (C4616b0.m18396a(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                m2036f(arrayList, viewGroup.getChildAt(i9));
            }
        }
    }

    /* renamed from: g */
    public abstract Object mo2021g(Object obj);

    /* renamed from: j */
    public void m2037j(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String strM18574z = C4647u.m18574z(view);
            if (strM18574z != null) {
                map.put(strM18574z, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i9 = 0; i9 < childCount; i9++) {
                    m2037j(map, viewGroup.getChildAt(i9));
                }
            }
        }
    }

    /* renamed from: k */
    public void m2038k(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i9 = iArr[0];
        rect.set(i9, iArr[1], view.getWidth() + i9, iArr[1] + view.getHeight());
    }

    /* renamed from: m */
    public abstract Object mo2022m(Object obj, Object obj2, Object obj3);

    /* renamed from: n */
    public abstract Object mo2023n(Object obj, Object obj2, Object obj3);

    /* renamed from: o */
    public ArrayList<String> m2039o(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            View view = arrayList.get(i9);
            arrayList2.add(C4647u.m18574z(view));
            C4647u.m18562p0(view, null);
        }
        return arrayList2;
    }

    /* renamed from: p */
    public abstract void mo2024p(Object obj, View view);

    /* renamed from: q */
    public abstract void mo2025q(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    /* renamed from: r */
    public abstract void mo2026r(Object obj, View view, ArrayList<View> arrayList);

    /* renamed from: s */
    public void m2040s(ViewGroup viewGroup, ArrayList<View> arrayList, Map<String, String> map) {
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(viewGroup, new c(arrayList, map));
    }

    /* renamed from: t */
    public abstract void mo2027t(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    /* renamed from: u */
    public abstract void mo2028u(Object obj, Rect rect);

    /* renamed from: v */
    public abstract void mo2029v(Object obj, View view);

    /* renamed from: w */
    public void m2041w(View view, ArrayList<View> arrayList, Map<String, String> map) {
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(view, new b(arrayList, map));
    }

    /* renamed from: x */
    public void m2042x(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i9 = 0; i9 < size; i9++) {
            View view2 = arrayList.get(i9);
            String strM18574z = C4647u.m18574z(view2);
            arrayList4.add(strM18574z);
            if (strM18574z != null) {
                C4647u.m18562p0(view2, null);
                String str = map.get(strM18574z);
                int i10 = 0;
                while (true) {
                    if (i10 >= size) {
                        break;
                    }
                    if (str.equals(arrayList3.get(i10))) {
                        C4647u.m18562p0(arrayList2.get(i10), strM18574z);
                        break;
                    }
                    i10++;
                }
            }
        }
        ViewTreeObserverOnPreDrawListenerC4644r.m18500a(view, new a(size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    /* renamed from: y */
    public abstract void mo2030y(Object obj, View view, ArrayList<View> arrayList);

    /* renamed from: z */
    public abstract void mo2031z(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);
}
