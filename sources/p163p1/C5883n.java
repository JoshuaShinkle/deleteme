package p163p1;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import p190s1.InterfaceC6246c;
import p226w1.C6517j;

/* renamed from: p1.n */
/* loaded from: classes.dex */
public class C5883n {

    /* renamed from: a */
    public final Set<InterfaceC6246c> f20342a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    public final List<InterfaceC6246c> f20343b = new ArrayList();

    /* renamed from: c */
    public boolean f20344c;

    /* renamed from: a */
    public final boolean m23346a(InterfaceC6246c interfaceC6246c, boolean z8) {
        boolean z9 = true;
        if (interfaceC6246c == null) {
            return true;
        }
        boolean zRemove = this.f20342a.remove(interfaceC6246c);
        if (!this.f20343b.remove(interfaceC6246c) && !zRemove) {
            z9 = false;
        }
        if (z9) {
            interfaceC6246c.clear();
            if (z8) {
                interfaceC6246c.mo4009b();
            }
        }
        return z9;
    }

    /* renamed from: b */
    public boolean m23347b(InterfaceC6246c interfaceC6246c) {
        return m23346a(interfaceC6246c, true);
    }

    /* renamed from: c */
    public void m23348c() {
        Iterator it = C6517j.m24949j(this.f20342a).iterator();
        while (it.hasNext()) {
            m23346a((InterfaceC6246c) it.next(), false);
        }
        this.f20343b.clear();
    }

    /* renamed from: d */
    public void m23349d() {
        this.f20344c = true;
        for (InterfaceC6246c interfaceC6246c : C6517j.m24949j(this.f20342a)) {
            if (interfaceC6246c.isRunning()) {
                interfaceC6246c.clear();
                this.f20343b.add(interfaceC6246c);
            }
        }
    }

    /* renamed from: e */
    public void m23350e() {
        for (InterfaceC6246c interfaceC6246c : C6517j.m24949j(this.f20342a)) {
            if (!interfaceC6246c.isComplete() && !interfaceC6246c.mo4015h()) {
                interfaceC6246c.clear();
                if (this.f20344c) {
                    this.f20343b.add(interfaceC6246c);
                } else {
                    interfaceC6246c.mo4016i();
                }
            }
        }
    }

    /* renamed from: f */
    public void m23351f() {
        this.f20344c = false;
        for (InterfaceC6246c interfaceC6246c : C6517j.m24949j(this.f20342a)) {
            if (!interfaceC6246c.isComplete() && !interfaceC6246c.isRunning()) {
                interfaceC6246c.mo4016i();
            }
        }
        this.f20343b.clear();
    }

    /* renamed from: g */
    public void m23352g(InterfaceC6246c interfaceC6246c) {
        this.f20342a.add(interfaceC6246c);
        if (!this.f20344c) {
            interfaceC6246c.mo4016i();
            return;
        }
        interfaceC6246c.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.f20343b.add(interfaceC6246c);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f20342a.size() + ", isPaused=" + this.f20344c + "}";
    }
}
