package p163p1;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import p226w1.C6517j;

/* renamed from: p1.a */
/* loaded from: classes.dex */
public class C5870a implements InterfaceC5877h {

    /* renamed from: a */
    public final Set<InterfaceC5878i> f20317a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    public boolean f20318b;

    /* renamed from: c */
    public boolean f20319c;

    @Override // p163p1.InterfaceC5877h
    /* renamed from: a */
    public void mo23313a(InterfaceC5878i interfaceC5878i) {
        this.f20317a.remove(interfaceC5878i);
    }

    @Override // p163p1.InterfaceC5877h
    /* renamed from: b */
    public void mo23314b(InterfaceC5878i interfaceC5878i) {
        this.f20317a.add(interfaceC5878i);
        if (this.f20319c) {
            interfaceC5878i.onDestroy();
        } else if (this.f20318b) {
            interfaceC5878i.onStart();
        } else {
            interfaceC5878i.onStop();
        }
    }

    /* renamed from: c */
    public void m23315c() {
        this.f20319c = true;
        Iterator it = C6517j.m24949j(this.f20317a).iterator();
        while (it.hasNext()) {
            ((InterfaceC5878i) it.next()).onDestroy();
        }
    }

    /* renamed from: d */
    public void m23316d() {
        this.f20318b = true;
        Iterator it = C6517j.m24949j(this.f20317a).iterator();
        while (it.hasNext()) {
            ((InterfaceC5878i) it.next()).onStart();
        }
    }

    /* renamed from: e */
    public void m23317e() {
        this.f20318b = false;
        Iterator it = C6517j.m24949j(this.f20317a).iterator();
        while (it.hasNext()) {
            ((InterfaceC5878i) it.next()).onStop();
        }
    }
}
