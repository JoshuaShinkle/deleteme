package androidx.recyclerview.widget;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/* renamed from: androidx.recyclerview.widget.p */
/* loaded from: classes.dex */
public class C0487p<T> {

    /* renamed from: a */
    public T[] f2724a;

    /* renamed from: b */
    public T[] f2725b;

    /* renamed from: c */
    public int f2726c;

    /* renamed from: d */
    public int f2727d;

    /* renamed from: e */
    public int f2728e;

    /* renamed from: f */
    public b f2729f;

    /* renamed from: g */
    public a f2730g;

    /* renamed from: h */
    public int f2731h;

    /* renamed from: i */
    public final Class<T> f2732i;

    /* renamed from: androidx.recyclerview.widget.p$a */
    public static class a<T2> extends b<T2> {

        /* renamed from: b */
        public final b<T2> f2733b;

        /* renamed from: c */
        public final C0473b f2734c;

        public a(b<T2> bVar) {
            this.f2733b = bVar;
            this.f2734c = new C0473b(bVar);
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: a */
        public void mo2761a(int i9, int i10) {
            this.f2734c.mo2761a(i9, i10);
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: b */
        public void mo2762b(int i9, int i10) {
            this.f2734c.mo2762b(i9, i10);
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: c */
        public void mo2763c(int i9, int i10) {
            this.f2734c.mo2763c(i9, i10);
        }

        @Override // androidx.recyclerview.widget.C0487p.b, java.util.Comparator
        public int compare(T2 t22, T2 t23) {
            return this.f2733b.compare(t22, t23);
        }

        @Override // androidx.recyclerview.widget.C0487p.b, androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: d */
        public void mo2764d(int i9, int i10, Object obj) {
            this.f2734c.mo2764d(i9, i10, obj);
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: e */
        public boolean mo2930e(T2 t22, T2 t23) {
            return this.f2733b.mo2930e(t22, t23);
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: f */
        public boolean mo2931f(T2 t22, T2 t23) {
            return this.f2733b.mo2931f(t22, t23);
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: g */
        public Object mo2932g(T2 t22, T2 t23) {
            return this.f2733b.mo2932g(t22, t23);
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: h */
        public void mo2933h(int i9, int i10) {
            this.f2734c.mo2764d(i9, i10, null);
        }

        /* renamed from: i */
        public void m2934i() {
            this.f2734c.m2765e();
        }
    }

    /* renamed from: androidx.recyclerview.widget.p$b */
    public static abstract class b<T2> implements Comparator<T2>, InterfaceC0481j {
        @Override // java.util.Comparator
        public abstract int compare(T2 t22, T2 t23);

        /* renamed from: d */
        public void mo2764d(int i9, int i10, Object obj) {
            mo2933h(i9, i10);
        }

        /* renamed from: e */
        public abstract boolean mo2930e(T2 t22, T2 t23);

        /* renamed from: f */
        public abstract boolean mo2931f(T2 t22, T2 t23);

        /* renamed from: g */
        public Object mo2932g(T2 t22, T2 t23) {
            return null;
        }

        /* renamed from: h */
        public abstract void mo2933h(int i9, int i10);
    }

    public C0487p(Class<T> cls, b<T> bVar) {
        this(cls, bVar, 10);
    }

    /* renamed from: a */
    public int m2906a(T t8) {
        m2928w();
        return m2907b(t8, true);
    }

    /* renamed from: b */
    public final int m2907b(T t8, boolean z8) {
        int iM2916k = m2916k(t8, this.f2724a, 0, this.f2731h, 1);
        if (iM2916k == -1) {
            iM2916k = 0;
        } else if (iM2916k < this.f2731h) {
            T t9 = this.f2724a[iM2916k];
            if (this.f2729f.mo2931f(t9, t8)) {
                if (this.f2729f.mo2930e(t9, t8)) {
                    this.f2724a[iM2916k] = t8;
                    return iM2916k;
                }
                this.f2724a[iM2916k] = t8;
                b bVar = this.f2729f;
                bVar.mo2764d(iM2916k, 1, bVar.mo2932g(t9, t8));
                return iM2916k;
            }
        }
        m2911f(iM2916k, t8);
        if (z8) {
            this.f2729f.mo2762b(iM2916k, 1);
        }
        return iM2916k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    public void m2908c(Collection<T> collection) {
        m2909d(collection.toArray((Object[]) Array.newInstance((Class<?>) this.f2732i, collection.size())), true);
    }

    /* renamed from: d */
    public void m2909d(T[] tArr, boolean z8) {
        m2928w();
        if (tArr.length == 0) {
            return;
        }
        if (z8) {
            m2910e(tArr);
        } else {
            m2910e(m2914i(tArr));
        }
    }

    /* renamed from: e */
    public final void m2910e(T[] tArr) {
        if (tArr.length < 1) {
            return;
        }
        int iM2927v = m2927v(tArr);
        if (this.f2731h != 0) {
            m2921p(tArr, iM2927v);
            return;
        }
        this.f2724a = tArr;
        this.f2731h = iM2927v;
        this.f2729f.mo2762b(0, iM2927v);
    }

    /* renamed from: f */
    public final void m2911f(int i9, T t8) {
        int i10 = this.f2731h;
        if (i9 > i10) {
            throw new IndexOutOfBoundsException("cannot add item to " + i9 + " because size is " + this.f2731h);
        }
        T[] tArr = this.f2724a;
        if (i10 == tArr.length) {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f2732i, tArr.length + 10));
            System.arraycopy(this.f2724a, 0, tArr2, 0, i9);
            tArr2[i9] = t8;
            System.arraycopy(this.f2724a, i9, tArr2, i9 + 1, this.f2731h - i9);
            this.f2724a = tArr2;
        } else {
            System.arraycopy(tArr, i9, tArr, i9 + 1, i10 - i9);
            this.f2724a[i9] = t8;
        }
        this.f2731h++;
    }

    /* renamed from: g */
    public void m2912g() {
        m2928w();
        b bVar = this.f2729f;
        if (bVar instanceof a) {
            return;
        }
        if (this.f2730g == null) {
            this.f2730g = new a(bVar);
        }
        this.f2729f = this.f2730g;
    }

    /* renamed from: h */
    public void m2913h() {
        m2928w();
        int i9 = this.f2731h;
        if (i9 == 0) {
            return;
        }
        Arrays.fill(this.f2724a, 0, i9, (Object) null);
        this.f2731h = 0;
        this.f2729f.mo2763c(0, i9);
    }

    /* renamed from: i */
    public final T[] m2914i(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f2732i, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    /* renamed from: j */
    public void m2915j() {
        m2928w();
        b bVar = this.f2729f;
        if (bVar instanceof a) {
            ((a) bVar).m2934i();
        }
        b bVar2 = this.f2729f;
        a aVar = this.f2730g;
        if (bVar2 == aVar) {
            this.f2729f = aVar.f2733b;
        }
    }

    /* renamed from: k */
    public final int m2916k(T t8, T[] tArr, int i9, int i10, int i11) {
        while (i9 < i10) {
            int i12 = (i9 + i10) / 2;
            T t9 = tArr[i12];
            int iCompare = this.f2729f.compare(t9, t8);
            if (iCompare < 0) {
                i9 = i12 + 1;
            } else {
                if (iCompare == 0) {
                    if (this.f2729f.mo2931f(t9, t8)) {
                        return i12;
                    }
                    int iM2920o = m2920o(t8, i12, i9, i10);
                    return (i11 == 1 && iM2920o == -1) ? i12 : iM2920o;
                }
                i10 = i12;
            }
        }
        if (i11 == 1) {
            return i9;
        }
        return -1;
    }

    /* renamed from: l */
    public final int m2917l(T t8, T[] tArr, int i9, int i10) {
        while (i9 < i10) {
            if (this.f2729f.mo2931f(tArr[i9], t8)) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* renamed from: m */
    public T m2918m(int i9) {
        int i10;
        if (i9 < this.f2731h && i9 >= 0) {
            T[] tArr = this.f2725b;
            return (tArr == null || i9 < (i10 = this.f2728e)) ? this.f2724a[i9] : tArr[(i9 - i10) + this.f2726c];
        }
        throw new IndexOutOfBoundsException("Asked to get item at " + i9 + " but size is " + this.f2731h);
    }

    /* renamed from: n */
    public int m2919n(T t8) {
        if (this.f2725b == null) {
            return m2916k(t8, this.f2724a, 0, this.f2731h, 4);
        }
        int iM2916k = m2916k(t8, this.f2724a, 0, this.f2728e, 4);
        if (iM2916k != -1) {
            return iM2916k;
        }
        int iM2916k2 = m2916k(t8, this.f2725b, this.f2726c, this.f2727d, 4);
        if (iM2916k2 != -1) {
            return (iM2916k2 - this.f2726c) + this.f2728e;
        }
        return -1;
    }

    /* renamed from: o */
    public final int m2920o(T t8, int i9, int i10, int i11) {
        T t9;
        for (int i12 = i9 - 1; i12 >= i10; i12--) {
            T t10 = this.f2724a[i12];
            if (this.f2729f.compare(t10, t8) != 0) {
                break;
            }
            if (this.f2729f.mo2931f(t10, t8)) {
                return i12;
            }
        }
        do {
            i9++;
            if (i9 >= i11) {
                return -1;
            }
            t9 = this.f2724a[i9];
            if (this.f2729f.compare(t9, t8) != 0) {
                return -1;
            }
        } while (!this.f2729f.mo2931f(t9, t8));
        return i9;
    }

    /* renamed from: p */
    public final void m2921p(T[] tArr, int i9) {
        boolean z8 = !(this.f2729f instanceof a);
        if (z8) {
            m2912g();
        }
        this.f2725b = this.f2724a;
        int i10 = 0;
        this.f2726c = 0;
        int i11 = this.f2731h;
        this.f2727d = i11;
        this.f2724a = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f2732i, i11 + i9 + 10));
        this.f2728e = 0;
        while (true) {
            int i12 = this.f2726c;
            int i13 = this.f2727d;
            if (i12 >= i13 && i10 >= i9) {
                break;
            }
            if (i12 == i13) {
                int i14 = i9 - i10;
                System.arraycopy(tArr, i10, this.f2724a, this.f2728e, i14);
                int i15 = this.f2728e + i14;
                this.f2728e = i15;
                this.f2731h += i14;
                this.f2729f.mo2762b(i15 - i14, i14);
                break;
            }
            if (i10 == i9) {
                int i16 = i13 - i12;
                System.arraycopy(this.f2725b, i12, this.f2724a, this.f2728e, i16);
                this.f2728e += i16;
                break;
            }
            T t8 = this.f2725b[i12];
            T t9 = tArr[i10];
            int iCompare = this.f2729f.compare(t8, t9);
            if (iCompare > 0) {
                T[] tArr2 = this.f2724a;
                int i17 = this.f2728e;
                int i18 = i17 + 1;
                this.f2728e = i18;
                tArr2[i17] = t9;
                this.f2731h++;
                i10++;
                this.f2729f.mo2762b(i18 - 1, 1);
            } else if (iCompare == 0 && this.f2729f.mo2931f(t8, t9)) {
                T[] tArr3 = this.f2724a;
                int i19 = this.f2728e;
                this.f2728e = i19 + 1;
                tArr3[i19] = t9;
                i10++;
                this.f2726c++;
                if (!this.f2729f.mo2930e(t8, t9)) {
                    b bVar = this.f2729f;
                    bVar.mo2764d(this.f2728e - 1, 1, bVar.mo2932g(t8, t9));
                }
            } else {
                T[] tArr4 = this.f2724a;
                int i20 = this.f2728e;
                this.f2728e = i20 + 1;
                tArr4[i20] = t8;
                this.f2726c++;
            }
        }
        this.f2725b = null;
        if (z8) {
            m2915j();
        }
    }

    /* renamed from: q */
    public boolean m2922q(T t8) {
        m2928w();
        return m2923r(t8, true);
    }

    /* renamed from: r */
    public final boolean m2923r(T t8, boolean z8) {
        int iM2916k = m2916k(t8, this.f2724a, 0, this.f2731h, 2);
        if (iM2916k == -1) {
            return false;
        }
        m2925t(iM2916k, z8);
        return true;
    }

    /* renamed from: s */
    public T m2924s(int i9) {
        m2928w();
        T tM2918m = m2918m(i9);
        m2925t(i9, true);
        return tM2918m;
    }

    /* renamed from: t */
    public final void m2925t(int i9, boolean z8) {
        T[] tArr = this.f2724a;
        System.arraycopy(tArr, i9 + 1, tArr, i9, (this.f2731h - i9) - 1);
        int i10 = this.f2731h - 1;
        this.f2731h = i10;
        this.f2724a[i10] = null;
        if (z8) {
            this.f2729f.mo2763c(i9, 1);
        }
    }

    /* renamed from: u */
    public int m2926u() {
        return this.f2731h;
    }

    /* renamed from: v */
    public final int m2927v(T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.f2729f);
        int i9 = 0;
        int i10 = 1;
        for (int i11 = 1; i11 < tArr.length; i11++) {
            T t8 = tArr[i11];
            if (this.f2729f.compare(tArr[i9], t8) == 0) {
                int iM2917l = m2917l(t8, tArr, i9, i10);
                if (iM2917l != -1) {
                    tArr[iM2917l] = t8;
                } else {
                    if (i10 != i11) {
                        tArr[i10] = t8;
                    }
                    i10++;
                }
            } else {
                if (i10 != i11) {
                    tArr[i10] = t8;
                }
                i9 = i10;
                i10++;
            }
        }
        return i10;
    }

    /* renamed from: w */
    public final void m2928w() {
        if (this.f2725b != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    /* renamed from: x */
    public void m2929x(int i9, T t8) {
        m2928w();
        T tM2918m = m2918m(i9);
        boolean z8 = tM2918m == t8 || !this.f2729f.mo2930e(tM2918m, t8);
        if (tM2918m != t8 && this.f2729f.compare(tM2918m, t8) == 0) {
            this.f2724a[i9] = t8;
            if (z8) {
                b bVar = this.f2729f;
                bVar.mo2764d(i9, 1, bVar.mo2932g(tM2918m, t8));
                return;
            }
            return;
        }
        if (z8) {
            b bVar2 = this.f2729f;
            bVar2.mo2764d(i9, 1, bVar2.mo2932g(tM2918m, t8));
        }
        m2925t(i9, false);
        int iM2907b = m2907b(t8, false);
        if (i9 != iM2907b) {
            this.f2729f.mo2761a(i9, iM2907b);
        }
    }

    public C0487p(Class<T> cls, b<T> bVar, int i9) {
        this.f2732i = cls;
        this.f2724a = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i9));
        this.f2729f = bVar;
        this.f2731h = 0;
    }
}
