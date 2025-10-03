package p181r1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p243y0.InterfaceC6593f;

/* renamed from: r1.e */
/* loaded from: classes.dex */
public class C6187e {

    /* renamed from: a */
    public final List<String> f20844a = new ArrayList();

    /* renamed from: b */
    public final Map<String, List<a<?, ?>>> f20845b = new HashMap();

    /* renamed from: r1.e$a */
    public static class a<T, R> {

        /* renamed from: a */
        public final Class<T> f20846a;

        /* renamed from: b */
        public final Class<R> f20847b;

        /* renamed from: c */
        public final InterfaceC6593f<T, R> f20848c;

        public a(Class<T> cls, Class<R> cls2, InterfaceC6593f<T, R> interfaceC6593f) {
            this.f20846a = cls;
            this.f20847b = cls2;
            this.f20848c = interfaceC6593f;
        }

        /* renamed from: a */
        public boolean m23665a(Class<?> cls, Class<?> cls2) {
            return this.f20846a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f20847b);
        }
    }

    /* renamed from: a */
    public synchronized <T, R> void m23660a(String str, InterfaceC6593f<T, R> interfaceC6593f, Class<T> cls, Class<R> cls2) {
        m23662c(str).add(new a<>(cls, cls2, interfaceC6593f));
    }

    /* renamed from: b */
    public synchronized <T, R> List<InterfaceC6593f<T, R>> m23661b(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f20844a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f20845b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.m23665a(cls, cls2)) {
                        arrayList.add(aVar.f20848c);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public final synchronized List<a<?, ?>> m23662c(String str) {
        List<a<?, ?>> arrayList;
        if (!this.f20844a.contains(str)) {
            this.f20844a.add(str);
        }
        arrayList = this.f20845b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f20845b.put(str, arrayList);
        }
        return arrayList;
    }

    /* renamed from: d */
    public synchronized <T, R> List<Class<R>> m23663d(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f20844a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f20845b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.m23665a(cls, cls2) && !arrayList.contains(aVar.f20847b)) {
                        arrayList.add(aVar.f20847b);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public synchronized void m23664e(List<String> list) {
        ArrayList<String> arrayList = new ArrayList(this.f20844a);
        this.f20844a.clear();
        this.f20844a.addAll(list);
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f20844a.add(str);
            }
        }
    }
}
