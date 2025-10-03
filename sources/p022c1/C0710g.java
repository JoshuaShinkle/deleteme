package p022c1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p022c1.InterfaceC0715l;

/* renamed from: c1.g */
/* loaded from: classes.dex */
public class C0710g<K extends InterfaceC0715l, V> {

    /* renamed from: a */
    public final a<K, V> f3361a = new a<>();

    /* renamed from: b */
    public final Map<K, a<K, V>> f3362b = new HashMap();

    /* renamed from: c1.g$a */
    public static class a<K, V> {

        /* renamed from: a */
        public final K f3363a;

        /* renamed from: b */
        public List<V> f3364b;

        /* renamed from: c */
        public a<K, V> f3365c;

        /* renamed from: d */
        public a<K, V> f3366d;

        public a() {
            this(null);
        }

        /* renamed from: a */
        public void m3499a(V v8) {
            if (this.f3364b == null) {
                this.f3364b = new ArrayList();
            }
            this.f3364b.add(v8);
        }

        /* renamed from: b */
        public V m3500b() {
            int iM3501c = m3501c();
            if (iM3501c > 0) {
                return this.f3364b.remove(iM3501c - 1);
            }
            return null;
        }

        /* renamed from: c */
        public int m3501c() {
            List<V> list = this.f3364b;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public a(K k9) {
            this.f3366d = this;
            this.f3365c = this;
            this.f3363a = k9;
        }
    }

    /* renamed from: e */
    public static <K, V> void m3492e(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.f3366d;
        aVar2.f3365c = aVar.f3365c;
        aVar.f3365c.f3366d = aVar2;
    }

    /* renamed from: g */
    public static <K, V> void m3493g(a<K, V> aVar) {
        aVar.f3365c.f3366d = aVar;
        aVar.f3366d.f3365c = aVar;
    }

    /* renamed from: a */
    public V m3494a(K k9) {
        a<K, V> aVar = this.f3362b.get(k9);
        if (aVar == null) {
            aVar = new a<>(k9);
            this.f3362b.put(k9, aVar);
        } else {
            k9.mo3515a();
        }
        m3495b(aVar);
        return aVar.m3500b();
    }

    /* renamed from: b */
    public final void m3495b(a<K, V> aVar) {
        m3492e(aVar);
        a<K, V> aVar2 = this.f3361a;
        aVar.f3366d = aVar2;
        aVar.f3365c = aVar2.f3365c;
        m3493g(aVar);
    }

    /* renamed from: c */
    public final void m3496c(a<K, V> aVar) {
        m3492e(aVar);
        a<K, V> aVar2 = this.f3361a;
        aVar.f3366d = aVar2.f3366d;
        aVar.f3365c = aVar2;
        m3493g(aVar);
    }

    /* renamed from: d */
    public void m3497d(K k9, V v8) {
        a<K, V> aVar = this.f3362b.get(k9);
        if (aVar == null) {
            aVar = new a<>(k9);
            m3496c(aVar);
            this.f3362b.put(k9, aVar);
        } else {
            k9.mo3515a();
        }
        aVar.m3499a(v8);
    }

    /* renamed from: f */
    public V m3498f() {
        for (a aVar = this.f3361a.f3366d; !aVar.equals(this.f3361a); aVar = aVar.f3366d) {
            V v8 = (V) aVar.m3500b();
            if (v8 != null) {
                return v8;
            }
            m3492e(aVar);
            this.f3362b.remove(aVar.f3363a);
            ((InterfaceC0715l) aVar.f3363a).mo3515a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        a aVar = this.f3361a.f3365c;
        boolean z8 = false;
        while (!aVar.equals(this.f3361a)) {
            sb.append('{');
            sb.append(aVar.f3363a);
            sb.append(':');
            sb.append(aVar.m3501c());
            sb.append("}, ");
            aVar = aVar.f3365c;
            z8 = true;
        }
        if (z8) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
