package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: androidx.activity.b */
/* loaded from: classes.dex */
public abstract class AbstractC0095b {

    /* renamed from: a */
    public boolean f174a;

    /* renamed from: b */
    public CopyOnWriteArrayList<InterfaceC0094a> f175b = new CopyOnWriteArrayList<>();

    public AbstractC0095b(boolean z8) {
        this.f174a = z8;
    }

    /* renamed from: a */
    public void m214a(InterfaceC0094a interfaceC0094a) {
        this.f175b.add(interfaceC0094a);
    }

    /* renamed from: b */
    public abstract void mo215b();

    /* renamed from: c */
    public final boolean m216c() {
        return this.f174a;
    }

    /* renamed from: d */
    public final void m217d() {
        Iterator<InterfaceC0094a> it = this.f175b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    /* renamed from: e */
    public void m218e(InterfaceC0094a interfaceC0094a) {
        this.f175b.remove(interfaceC0094a);
    }

    /* renamed from: f */
    public final void m219f(boolean z8) {
        this.f174a = z8;
    }
}
