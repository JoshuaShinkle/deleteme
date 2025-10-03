package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.k */
/* loaded from: classes.dex */
public abstract class AbstractC0372k {

    /* renamed from: b */
    public int f2089b;

    /* renamed from: c */
    public int f2090c;

    /* renamed from: d */
    public int f2091d;

    /* renamed from: e */
    public int f2092e;

    /* renamed from: f */
    public int f2093f;

    /* renamed from: g */
    public int f2094g;

    /* renamed from: h */
    public boolean f2095h;

    /* renamed from: j */
    public String f2097j;

    /* renamed from: k */
    public int f2098k;

    /* renamed from: l */
    public CharSequence f2099l;

    /* renamed from: m */
    public int f2100m;

    /* renamed from: n */
    public CharSequence f2101n;

    /* renamed from: o */
    public ArrayList<String> f2102o;

    /* renamed from: p */
    public ArrayList<String> f2103p;

    /* renamed from: r */
    public ArrayList<Runnable> f2105r;

    /* renamed from: a */
    public ArrayList<a> f2088a = new ArrayList<>();

    /* renamed from: i */
    public boolean f2096i = true;

    /* renamed from: q */
    public boolean f2104q = false;

    /* renamed from: androidx.fragment.app.k$a */
    public static final class a {

        /* renamed from: a */
        public int f2106a;

        /* renamed from: b */
        public Fragment f2107b;

        /* renamed from: c */
        public int f2108c;

        /* renamed from: d */
        public int f2109d;

        /* renamed from: e */
        public int f2110e;

        /* renamed from: f */
        public int f2111f;

        /* renamed from: g */
        public Lifecycle.State f2112g;

        /* renamed from: h */
        public Lifecycle.State f2113h;

        public a() {
        }

        public a(int i9, Fragment fragment) {
            this.f2106a = i9;
            this.f2107b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f2112g = state;
            this.f2113h = state;
        }

        public a(int i9, Fragment fragment, Lifecycle.State state) {
            this.f2106a = i9;
            this.f2107b = fragment;
            this.f2112g = fragment.mMaxState;
            this.f2113h = state;
        }
    }

    /* renamed from: b */
    public AbstractC0372k m1980b(int i9, Fragment fragment) {
        mo1798m(i9, fragment, null, 1);
        return this;
    }

    /* renamed from: c */
    public AbstractC0372k m1981c(int i9, Fragment fragment, String str) {
        mo1798m(i9, fragment, str, 1);
        return this;
    }

    /* renamed from: d */
    public AbstractC0372k m1982d(Fragment fragment, String str) {
        mo1798m(0, fragment, str, 1);
        return this;
    }

    /* renamed from: e */
    public void m1983e(a aVar) {
        this.f2088a.add(aVar);
        aVar.f2108c = this.f2089b;
        aVar.f2109d = this.f2090c;
        aVar.f2110e = this.f2091d;
        aVar.f2111f = this.f2092e;
    }

    /* renamed from: f */
    public AbstractC0372k m1984f(Fragment fragment) {
        m1983e(new a(7, fragment));
        return this;
    }

    /* renamed from: g */
    public abstract int mo1793g();

    /* renamed from: h */
    public abstract int mo1794h();

    /* renamed from: i */
    public abstract void mo1795i();

    /* renamed from: j */
    public abstract void mo1796j();

    /* renamed from: k */
    public AbstractC0372k mo1797k(Fragment fragment) {
        m1983e(new a(6, fragment));
        return this;
    }

    /* renamed from: l */
    public AbstractC0372k m1985l() {
        if (this.f2095h) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f2096i = false;
        return this;
    }

    /* renamed from: m */
    public void mo1798m(int i9, Fragment fragment, String str, int i10) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i9 != 0) {
            if (i9 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i11 = fragment.mFragmentId;
            if (i11 != 0 && i11 != i9) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i9);
            }
            fragment.mFragmentId = i9;
            fragment.mContainerId = i9;
        }
        m1983e(new a(i10, fragment));
    }

    /* renamed from: n */
    public AbstractC0372k mo1799n(Fragment fragment) {
        m1983e(new a(4, fragment));
        return this;
    }

    /* renamed from: o */
    public AbstractC0372k mo1800o(Fragment fragment) {
        m1983e(new a(3, fragment));
        return this;
    }

    /* renamed from: p */
    public AbstractC0372k m1986p(int i9, Fragment fragment, String str) {
        if (i9 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        mo1798m(i9, fragment, str, 2);
        return this;
    }

    /* renamed from: q */
    public AbstractC0372k mo1801q(Fragment fragment, Lifecycle.State state) {
        m1983e(new a(10, fragment, state));
        return this;
    }

    /* renamed from: r */
    public AbstractC0372k mo1802r(Fragment fragment) {
        m1983e(new a(5, fragment));
        return this;
    }
}
