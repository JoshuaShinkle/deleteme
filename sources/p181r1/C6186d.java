package p181r1;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import p132m.C5302a;
import p226w1.C6515h;

/* renamed from: r1.d */
/* loaded from: classes.dex */
public class C6186d {

    /* renamed from: a */
    public final AtomicReference<C6515h> f20842a = new AtomicReference<>();

    /* renamed from: b */
    public final C5302a<C6515h, List<Class<?>>> f20843b = new C5302a<>();

    /* renamed from: a */
    public List<Class<?>> m23658a(Class<?> cls, Class<?> cls2) {
        List<Class<?>> list;
        C6515h andSet = this.f20842a.getAndSet(null);
        if (andSet == null) {
            andSet = new C6515h(cls, cls2);
        } else {
            andSet.m24933a(cls, cls2);
        }
        synchronized (this.f20843b) {
            list = this.f20843b.get(andSet);
        }
        this.f20842a.set(andSet);
        return list;
    }

    /* renamed from: b */
    public void m23659b(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        synchronized (this.f20843b) {
            this.f20843b.put(new C6515h(cls, cls2), list);
        }
    }
}
