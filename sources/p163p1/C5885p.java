package p163p1;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import p199t1.InterfaceC6283h;
import p226w1.C6517j;

/* renamed from: p1.p */
/* loaded from: classes.dex */
public final class C5885p implements InterfaceC5878i {

    /* renamed from: b */
    public final Set<InterfaceC6283h<?>> f20352b = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: c */
    public void m23363c() {
        this.f20352b.clear();
    }

    /* renamed from: d */
    public List<InterfaceC6283h<?>> m23364d() {
        return C6517j.m24949j(this.f20352b);
    }

    /* renamed from: k */
    public void m23365k(InterfaceC6283h<?> interfaceC6283h) {
        this.f20352b.add(interfaceC6283h);
    }

    /* renamed from: l */
    public void m23366l(InterfaceC6283h<?> interfaceC6283h) {
        this.f20352b.remove(interfaceC6283h);
    }

    @Override // p163p1.InterfaceC5878i
    public void onDestroy() {
        Iterator it = C6517j.m24949j(this.f20352b).iterator();
        while (it.hasNext()) {
            ((InterfaceC6283h) it.next()).onDestroy();
        }
    }

    @Override // p163p1.InterfaceC5878i
    public void onStart() {
        Iterator it = C6517j.m24949j(this.f20352b).iterator();
        while (it.hasNext()) {
            ((InterfaceC6283h) it.next()).onStart();
        }
    }

    @Override // p163p1.InterfaceC5878i
    public void onStop() {
        Iterator it = C6517j.m24949j(this.f20352b).iterator();
        while (it.hasNext()) {
            ((InterfaceC6283h) it.next()).onStop();
        }
    }
}
