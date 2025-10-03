package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* renamed from: androidx.lifecycle.q */
/* loaded from: classes.dex */
public class C0396q {

    /* renamed from: a */
    public final HashMap<String, AbstractC0394o> f2235a = new HashMap<>();

    /* renamed from: a */
    public final void m2116a() {
        Iterator<AbstractC0394o> it = this.f2235a.values().iterator();
        while (it.hasNext()) {
            it.next().m2112a();
        }
        this.f2235a.clear();
    }

    /* renamed from: b */
    public final AbstractC0394o m2117b(String str) {
        return this.f2235a.get(str);
    }

    /* renamed from: c */
    public final void m2118c(String str, AbstractC0394o abstractC0394o) {
        AbstractC0394o abstractC0394oPut = this.f2235a.put(str, abstractC0394o);
        if (abstractC0394oPut != null) {
            abstractC0394oPut.mo1970c();
        }
    }
}
