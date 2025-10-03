package p132m;

/* renamed from: m.h */
/* loaded from: classes.dex */
public class C5309h<E> implements Cloneable {

    /* renamed from: f */
    public static final Object f18044f = new Object();

    /* renamed from: b */
    public boolean f18045b;

    /* renamed from: c */
    public int[] f18046c;

    /* renamed from: d */
    public Object[] f18047d;

    /* renamed from: e */
    public int f18048e;

    public C5309h() {
        this(10);
    }

    /* renamed from: a */
    public void m20756a(int i9, E e9) {
        int i10 = this.f18048e;
        if (i10 != 0 && i9 <= this.f18046c[i10 - 1]) {
            m20765k(i9, e9);
            return;
        }
        if (this.f18045b && i10 >= this.f18046c.length) {
            m20759d();
        }
        int i11 = this.f18048e;
        if (i11 >= this.f18046c.length) {
            int iM20716e = C5304c.m20716e(i11 + 1);
            int[] iArr = new int[iM20716e];
            Object[] objArr = new Object[iM20716e];
            int[] iArr2 = this.f18046c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f18047d;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f18046c = iArr;
            this.f18047d = objArr;
        }
        this.f18046c[i11] = i9;
        this.f18047d[i11] = e9;
        this.f18048e = i11 + 1;
    }

    /* renamed from: b */
    public void m20757b() {
        int i9 = this.f18048e;
        Object[] objArr = this.f18047d;
        for (int i10 = 0; i10 < i9; i10++) {
            objArr[i10] = null;
        }
        this.f18048e = 0;
        this.f18045b = false;
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C5309h<E> clone() {
        try {
            C5309h<E> c5309h = (C5309h) super.clone();
            c5309h.f18046c = (int[]) this.f18046c.clone();
            c5309h.f18047d = (Object[]) this.f18047d.clone();
            return c5309h;
        } catch (CloneNotSupportedException e9) {
            throw new AssertionError(e9);
        }
    }

    /* renamed from: d */
    public final void m20759d() {
        int i9 = this.f18048e;
        int[] iArr = this.f18046c;
        Object[] objArr = this.f18047d;
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            Object obj = objArr[i11];
            if (obj != f18044f) {
                if (i11 != i10) {
                    iArr[i10] = iArr[i11];
                    objArr[i10] = obj;
                    objArr[i11] = null;
                }
                i10++;
            }
        }
        this.f18045b = false;
        this.f18048e = i10;
    }

    /* renamed from: e */
    public E m20760e(int i9) {
        return m20761g(i9, null);
    }

    /* renamed from: g */
    public E m20761g(int i9, E e9) {
        E e10;
        int iM20712a = C5304c.m20712a(this.f18046c, this.f18048e, i9);
        return (iM20712a < 0 || (e10 = (E) this.f18047d[iM20712a]) == f18044f) ? e9 : e10;
    }

    /* renamed from: h */
    public int m20762h(int i9) {
        if (this.f18045b) {
            m20759d();
        }
        return C5304c.m20712a(this.f18046c, this.f18048e, i9);
    }

    /* renamed from: i */
    public int m20763i(E e9) {
        if (this.f18045b) {
            m20759d();
        }
        for (int i9 = 0; i9 < this.f18048e; i9++) {
            if (this.f18047d[i9] == e9) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: j */
    public int m20764j(int i9) {
        if (this.f18045b) {
            m20759d();
        }
        return this.f18046c[i9];
    }

    /* renamed from: k */
    public void m20765k(int i9, E e9) {
        int iM20712a = C5304c.m20712a(this.f18046c, this.f18048e, i9);
        if (iM20712a >= 0) {
            this.f18047d[iM20712a] = e9;
            return;
        }
        int i10 = ~iM20712a;
        int i11 = this.f18048e;
        if (i10 < i11) {
            Object[] objArr = this.f18047d;
            if (objArr[i10] == f18044f) {
                this.f18046c[i10] = i9;
                objArr[i10] = e9;
                return;
            }
        }
        if (this.f18045b && i11 >= this.f18046c.length) {
            m20759d();
            i10 = ~C5304c.m20712a(this.f18046c, this.f18048e, i9);
        }
        int i12 = this.f18048e;
        if (i12 >= this.f18046c.length) {
            int iM20716e = C5304c.m20716e(i12 + 1);
            int[] iArr = new int[iM20716e];
            Object[] objArr2 = new Object[iM20716e];
            int[] iArr2 = this.f18046c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f18047d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f18046c = iArr;
            this.f18047d = objArr2;
        }
        int i13 = this.f18048e;
        if (i13 - i10 != 0) {
            int[] iArr3 = this.f18046c;
            int i14 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i14, i13 - i10);
            Object[] objArr4 = this.f18047d;
            System.arraycopy(objArr4, i10, objArr4, i14, this.f18048e - i10);
        }
        this.f18046c[i10] = i9;
        this.f18047d[i10] = e9;
        this.f18048e++;
    }

    /* renamed from: l */
    public void m20766l(int i9) {
        int iM20712a = C5304c.m20712a(this.f18046c, this.f18048e, i9);
        if (iM20712a >= 0) {
            Object[] objArr = this.f18047d;
            Object obj = objArr[iM20712a];
            Object obj2 = f18044f;
            if (obj != obj2) {
                objArr[iM20712a] = obj2;
                this.f18045b = true;
            }
        }
    }

    /* renamed from: m */
    public void m20767m(int i9) {
        Object[] objArr = this.f18047d;
        Object obj = objArr[i9];
        Object obj2 = f18044f;
        if (obj != obj2) {
            objArr[i9] = obj2;
            this.f18045b = true;
        }
    }

    /* renamed from: n */
    public int m20768n() {
        if (this.f18045b) {
            m20759d();
        }
        return this.f18048e;
    }

    /* renamed from: o */
    public E m20769o(int i9) {
        if (this.f18045b) {
            m20759d();
        }
        return (E) this.f18047d[i9];
    }

    public String toString() {
        if (m20768n() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f18048e * 28);
        sb.append('{');
        for (int i9 = 0; i9 < this.f18048e; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            sb.append(m20764j(i9));
            sb.append('=');
            E eM20769o = m20769o(i9);
            if (eM20769o != this) {
                sb.append(eM20769o);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public C5309h(int i9) {
        this.f18045b = false;
        if (i9 == 0) {
            this.f18046c = C5304c.f18014a;
            this.f18047d = C5304c.f18016c;
        } else {
            int iM20716e = C5304c.m20716e(i9);
            this.f18046c = new int[iM20716e];
            this.f18047d = new Object[iM20716e];
        }
    }
}
