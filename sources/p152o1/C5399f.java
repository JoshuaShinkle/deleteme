package p152o1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: o1.f */
/* loaded from: classes.dex */
public class C5399f {

    /* renamed from: a */
    public final List<a<?, ?>> f18295a = new ArrayList();

    /* renamed from: o1.f$a */
    public static final class a<Z, R> {

        /* renamed from: a */
        public final Class<Z> f18296a;

        /* renamed from: b */
        public final Class<R> f18297b;

        /* renamed from: c */
        public final InterfaceC5398e<Z, R> f18298c;

        public a(Class<Z> cls, Class<R> cls2, InterfaceC5398e<Z, R> interfaceC5398e) {
            this.f18296a = cls;
            this.f18297b = cls2;
            this.f18298c = interfaceC5398e;
        }

        /* renamed from: a */
        public boolean m21107a(Class<?> cls, Class<?> cls2) {
            return this.f18296a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f18297b);
        }
    }

    /* renamed from: a */
    public synchronized <Z, R> InterfaceC5398e<Z, R> m21104a(Class<Z> cls, Class<R> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return C5400g.m21108b();
        }
        for (a<?, ?> aVar : this.f18295a) {
            if (aVar.m21107a(cls, cls2)) {
                return (InterfaceC5398e<Z, R>) aVar.f18298c;
            }
        }
        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls + " to " + cls2);
    }

    /* renamed from: b */
    public synchronized <Z, R> List<Class<R>> m21105b(Class<Z> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        Iterator<a<?, ?>> it = this.f18295a.iterator();
        while (it.hasNext()) {
            if (it.next().m21107a(cls, cls2)) {
                arrayList.add(cls2);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public synchronized <Z, R> void m21106c(Class<Z> cls, Class<R> cls2, InterfaceC5398e<Z, R> interfaceC5398e) {
        this.f18295a.add(new a<>(cls, cls2, interfaceC5398e));
    }
}
