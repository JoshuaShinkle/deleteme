package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.AbstractC0394o;
import androidx.lifecycle.C0395p;
import androidx.lifecycle.C0396q;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: androidx.fragment.app.i */
/* loaded from: classes.dex */
public class C0370i extends AbstractC0394o {

    /* renamed from: i */
    public static final C0395p.a f2081i = new a();

    /* renamed from: f */
    public final boolean f2085f;

    /* renamed from: c */
    public final HashSet<Fragment> f2082c = new HashSet<>();

    /* renamed from: d */
    public final HashMap<String, C0370i> f2083d = new HashMap<>();

    /* renamed from: e */
    public final HashMap<String, C0396q> f2084e = new HashMap<>();

    /* renamed from: g */
    public boolean f2086g = false;

    /* renamed from: h */
    public boolean f2087h = false;

    /* renamed from: androidx.fragment.app.i$a */
    public static class a implements C0395p.a {
        @Override // androidx.lifecycle.C0395p.a
        /* renamed from: a */
        public <T extends AbstractC0394o> T mo1979a(Class<T> cls) {
            return new C0370i(true);
        }
    }

    public C0370i(boolean z8) {
        this.f2085f = z8;
    }

    /* renamed from: g */
    public static C0370i m1969g(C0396q c0396q) {
        return (C0370i) new C0395p(c0396q, f2081i).m2113a(C0370i.class);
    }

    @Override // androidx.lifecycle.AbstractC0394o
    /* renamed from: c */
    public void mo1970c() {
        if (LayoutInflaterFactory2C0369h.f2016I) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f2086g = true;
    }

    /* renamed from: d */
    public boolean m1971d(Fragment fragment) {
        return this.f2082c.add(fragment);
    }

    /* renamed from: e */
    public void m1972e(Fragment fragment) {
        if (LayoutInflaterFactory2C0369h.f2016I) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        C0370i c0370i = this.f2083d.get(fragment.mWho);
        if (c0370i != null) {
            c0370i.mo1970c();
            this.f2083d.remove(fragment.mWho);
        }
        C0396q c0396q = this.f2084e.get(fragment.mWho);
        if (c0396q != null) {
            c0396q.m2116a();
            this.f2084e.remove(fragment.mWho);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0370i c0370i = (C0370i) obj;
        return this.f2082c.equals(c0370i.f2082c) && this.f2083d.equals(c0370i.f2083d) && this.f2084e.equals(c0370i.f2084e);
    }

    /* renamed from: f */
    public C0370i m1973f(Fragment fragment) {
        C0370i c0370i = this.f2083d.get(fragment.mWho);
        if (c0370i != null) {
            return c0370i;
        }
        C0370i c0370i2 = new C0370i(this.f2085f);
        this.f2083d.put(fragment.mWho, c0370i2);
        return c0370i2;
    }

    /* renamed from: h */
    public Collection<Fragment> m1974h() {
        return this.f2082c;
    }

    public int hashCode() {
        return (((this.f2082c.hashCode() * 31) + this.f2083d.hashCode()) * 31) + this.f2084e.hashCode();
    }

    /* renamed from: i */
    public C0396q m1975i(Fragment fragment) {
        C0396q c0396q = this.f2084e.get(fragment.mWho);
        if (c0396q != null) {
            return c0396q;
        }
        C0396q c0396q2 = new C0396q();
        this.f2084e.put(fragment.mWho, c0396q2);
        return c0396q2;
    }

    /* renamed from: j */
    public boolean m1976j() {
        return this.f2086g;
    }

    /* renamed from: k */
    public boolean m1977k(Fragment fragment) {
        return this.f2082c.remove(fragment);
    }

    /* renamed from: l */
    public boolean m1978l(Fragment fragment) {
        if (this.f2082c.contains(fragment)) {
            return this.f2085f ? this.f2086g : !this.f2087h;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f2082c.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f2083d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f2084e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
