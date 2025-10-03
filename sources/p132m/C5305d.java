package p132m;

/* renamed from: m.d */
/* loaded from: classes.dex */
public class C5305d<E> implements Cloneable {

    /* renamed from: f */
    public static final Object f18017f = new Object();

    /* renamed from: b */
    public boolean f18018b;

    /* renamed from: c */
    public long[] f18019c;

    /* renamed from: d */
    public Object[] f18020d;

    /* renamed from: e */
    public int f18021e;

    public C5305d() {
        this(10);
    }

    /* renamed from: a */
    public void m20718a() {
        int i9 = this.f18021e;
        Object[] objArr = this.f18020d;
        for (int i10 = 0; i10 < i9; i10++) {
            objArr[i10] = null;
        }
        this.f18021e = 0;
        this.f18018b = false;
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C5305d<E> clone() {
        try {
            C5305d<E> c5305d = (C5305d) super.clone();
            c5305d.f18019c = (long[]) this.f18019c.clone();
            c5305d.f18020d = (Object[]) this.f18020d.clone();
            return c5305d;
        } catch (CloneNotSupportedException e9) {
            throw new AssertionError(e9);
        }
    }

    @Deprecated
    /* renamed from: c */
    public void m20720c(long j9) {
        m20727k(j9);
    }

    /* renamed from: d */
    public final void m20721d() {
        int i9 = this.f18021e;
        long[] jArr = this.f18019c;
        Object[] objArr = this.f18020d;
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            Object obj = objArr[i11];
            if (obj != f18017f) {
                if (i11 != i10) {
                    jArr[i10] = jArr[i11];
                    objArr[i10] = obj;
                    objArr[i11] = null;
                }
                i10++;
            }
        }
        this.f18018b = false;
        this.f18021e = i10;
    }

    /* renamed from: e */
    public E m20722e(long j9) {
        return m20723g(j9, null);
    }

    /* renamed from: g */
    public E m20723g(long j9, E e9) {
        E e10;
        int iM20713b = C5304c.m20713b(this.f18019c, this.f18021e, j9);
        return (iM20713b < 0 || (e10 = (E) this.f18020d[iM20713b]) == f18017f) ? e9 : e10;
    }

    /* renamed from: h */
    public int m20724h(long j9) {
        if (this.f18018b) {
            m20721d();
        }
        return C5304c.m20713b(this.f18019c, this.f18021e, j9);
    }

    /* renamed from: i */
    public long m20725i(int i9) {
        if (this.f18018b) {
            m20721d();
        }
        return this.f18019c[i9];
    }

    /* renamed from: j */
    public void m20726j(long j9, E e9) {
        int iM20713b = C5304c.m20713b(this.f18019c, this.f18021e, j9);
        if (iM20713b >= 0) {
            this.f18020d[iM20713b] = e9;
            return;
        }
        int i9 = ~iM20713b;
        int i10 = this.f18021e;
        if (i9 < i10) {
            Object[] objArr = this.f18020d;
            if (objArr[i9] == f18017f) {
                this.f18019c[i9] = j9;
                objArr[i9] = e9;
                return;
            }
        }
        if (this.f18018b && i10 >= this.f18019c.length) {
            m20721d();
            i9 = ~C5304c.m20713b(this.f18019c, this.f18021e, j9);
        }
        int i11 = this.f18021e;
        if (i11 >= this.f18019c.length) {
            int iM20717f = C5304c.m20717f(i11 + 1);
            long[] jArr = new long[iM20717f];
            Object[] objArr2 = new Object[iM20717f];
            long[] jArr2 = this.f18019c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f18020d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f18019c = jArr;
            this.f18020d = objArr2;
        }
        int i12 = this.f18021e;
        if (i12 - i9 != 0) {
            long[] jArr3 = this.f18019c;
            int i13 = i9 + 1;
            System.arraycopy(jArr3, i9, jArr3, i13, i12 - i9);
            Object[] objArr4 = this.f18020d;
            System.arraycopy(objArr4, i9, objArr4, i13, this.f18021e - i9);
        }
        this.f18019c[i9] = j9;
        this.f18020d[i9] = e9;
        this.f18021e++;
    }

    /* renamed from: k */
    public void m20727k(long j9) {
        int iM20713b = C5304c.m20713b(this.f18019c, this.f18021e, j9);
        if (iM20713b >= 0) {
            Object[] objArr = this.f18020d;
            Object obj = objArr[iM20713b];
            Object obj2 = f18017f;
            if (obj != obj2) {
                objArr[iM20713b] = obj2;
                this.f18018b = true;
            }
        }
    }

    /* renamed from: l */
    public void m20728l(int i9) {
        Object[] objArr = this.f18020d;
        Object obj = objArr[i9];
        Object obj2 = f18017f;
        if (obj != obj2) {
            objArr[i9] = obj2;
            this.f18018b = true;
        }
    }

    /* renamed from: m */
    public void m20729m(int i9, E e9) {
        if (this.f18018b) {
            m20721d();
        }
        this.f18020d[i9] = e9;
    }

    /* renamed from: n */
    public int m20730n() {
        if (this.f18018b) {
            m20721d();
        }
        return this.f18021e;
    }

    /* renamed from: o */
    public E m20731o(int i9) {
        if (this.f18018b) {
            m20721d();
        }
        return (E) this.f18020d[i9];
    }

    public String toString() {
        if (m20730n() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f18021e * 28);
        sb.append('{');
        for (int i9 = 0; i9 < this.f18021e; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            sb.append(m20725i(i9));
            sb.append('=');
            E eM20731o = m20731o(i9);
            if (eM20731o != this) {
                sb.append(eM20731o);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public C5305d(int i9) {
        this.f18018b = false;
        if (i9 == 0) {
            this.f18019c = C5304c.f18015b;
            this.f18020d = C5304c.f18016c;
        } else {
            int iM20717f = C5304c.m20717f(i9);
            this.f18019c = new long[iM20717f];
            this.f18020d = new Object[iM20717f];
        }
    }
}
