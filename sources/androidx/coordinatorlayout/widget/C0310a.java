package androidx.coordinatorlayout.widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import p021c0.C0700f;
import p021c0.InterfaceC0699e;
import p132m.C5308g;

/* renamed from: androidx.coordinatorlayout.widget.a */
/* loaded from: classes.dex */
public final class C0310a<T> {

    /* renamed from: a */
    public final InterfaceC0699e<ArrayList<T>> f1740a = new C0700f(10);

    /* renamed from: b */
    public final C5308g<T, ArrayList<T>> f1741b = new C5308g<>();

    /* renamed from: c */
    public final ArrayList<T> f1742c = new ArrayList<>();

    /* renamed from: d */
    public final HashSet<T> f1743d = new HashSet<>();

    /* renamed from: a */
    public void m1455a(T t8, T t9) {
        if (!this.f1741b.containsKey(t8) || !this.f1741b.containsKey(t9)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListM1460f = this.f1741b.get(t8);
        if (arrayListM1460f == null) {
            arrayListM1460f = m1460f();
            this.f1741b.put(t8, arrayListM1460f);
        }
        arrayListM1460f.add(t9);
    }

    /* renamed from: b */
    public void m1456b(T t8) {
        if (this.f1741b.containsKey(t8)) {
            return;
        }
        this.f1741b.put(t8, null);
    }

    /* renamed from: c */
    public void m1457c() {
        int size = this.f1741b.size();
        for (int i9 = 0; i9 < size; i9++) {
            ArrayList<T> arrayListM20755m = this.f1741b.m20755m(i9);
            if (arrayListM20755m != null) {
                m1465k(arrayListM20755m);
            }
        }
        this.f1741b.clear();
    }

    /* renamed from: d */
    public boolean m1458d(T t8) {
        return this.f1741b.containsKey(t8);
    }

    /* renamed from: e */
    public final void m1459e(T t8, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t8)) {
            return;
        }
        if (hashSet.contains(t8)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t8);
        ArrayList<T> arrayList2 = this.f1741b.get(t8);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i9 = 0; i9 < size; i9++) {
                m1459e(arrayList2.get(i9), arrayList, hashSet);
            }
        }
        hashSet.remove(t8);
        arrayList.add(t8);
    }

    /* renamed from: f */
    public final ArrayList<T> m1460f() {
        ArrayList<T> arrayListMo3465b = this.f1740a.mo3465b();
        return arrayListMo3465b == null ? new ArrayList<>() : arrayListMo3465b;
    }

    /* renamed from: g */
    public List m1461g(T t8) {
        return this.f1741b.get(t8);
    }

    /* renamed from: h */
    public List<T> m1462h(T t8) {
        int size = this.f1741b.size();
        ArrayList arrayList = null;
        for (int i9 = 0; i9 < size; i9++) {
            ArrayList<T> arrayListM20755m = this.f1741b.m20755m(i9);
            if (arrayListM20755m != null && arrayListM20755m.contains(t8)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f1741b.m20751i(i9));
            }
        }
        return arrayList;
    }

    /* renamed from: i */
    public ArrayList<T> m1463i() {
        this.f1742c.clear();
        this.f1743d.clear();
        int size = this.f1741b.size();
        for (int i9 = 0; i9 < size; i9++) {
            m1459e(this.f1741b.m20751i(i9), this.f1742c, this.f1743d);
        }
        return this.f1742c;
    }

    /* renamed from: j */
    public boolean m1464j(T t8) {
        int size = this.f1741b.size();
        for (int i9 = 0; i9 < size; i9++) {
            ArrayList<T> arrayListM20755m = this.f1741b.m20755m(i9);
            if (arrayListM20755m != null && arrayListM20755m.contains(t8)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: k */
    public final void m1465k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f1740a.mo3464a(arrayList);
    }
}
