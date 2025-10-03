package p132m;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: m.g */
/* loaded from: classes.dex */
public class C5308g<K, V> {

    /* renamed from: e */
    public static Object[] f18037e;

    /* renamed from: f */
    public static int f18038f;

    /* renamed from: g */
    public static Object[] f18039g;

    /* renamed from: h */
    public static int f18040h;

    /* renamed from: b */
    public int[] f18041b;

    /* renamed from: c */
    public Object[] f18042c;

    /* renamed from: d */
    public int f18043d;

    public C5308g() {
        this.f18041b = C5304c.f18014a;
        this.f18042c = C5304c.f18016c;
        this.f18043d = 0;
    }

    /* renamed from: a */
    private void m20743a(int i9) {
        if (i9 == 8) {
            synchronized (C5308g.class) {
                Object[] objArr = f18039g;
                if (objArr != null) {
                    this.f18042c = objArr;
                    f18039g = (Object[]) objArr[0];
                    this.f18041b = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f18040h--;
                    return;
                }
            }
        } else if (i9 == 4) {
            synchronized (C5308g.class) {
                Object[] objArr2 = f18037e;
                if (objArr2 != null) {
                    this.f18042c = objArr2;
                    f18037e = (Object[]) objArr2[0];
                    this.f18041b = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f18038f--;
                    return;
                }
            }
        }
        this.f18041b = new int[i9];
        this.f18042c = new Object[i9 << 1];
    }

    /* renamed from: b */
    public static int m20744b(int[] iArr, int i9, int i10) {
        try {
            return C5304c.m20712a(iArr, i9, i10);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    /* renamed from: d */
    public static void m20745d(int[] iArr, Object[] objArr, int i9) {
        if (iArr.length == 8) {
            synchronized (C5308g.class) {
                if (f18040h < 10) {
                    objArr[0] = f18039g;
                    objArr[1] = iArr;
                    for (int i10 = (i9 << 1) - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f18039g = objArr;
                    f18040h++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (C5308g.class) {
                if (f18038f < 10) {
                    objArr[0] = f18037e;
                    objArr[1] = iArr;
                    for (int i11 = (i9 << 1) - 1; i11 >= 2; i11--) {
                        objArr[i11] = null;
                    }
                    f18037e = objArr;
                    f18038f++;
                }
            }
        }
    }

    /* renamed from: c */
    public void m20746c(int i9) {
        int i10 = this.f18043d;
        int[] iArr = this.f18041b;
        if (iArr.length < i9) {
            Object[] objArr = this.f18042c;
            m20743a(i9);
            if (this.f18043d > 0) {
                System.arraycopy(iArr, 0, this.f18041b, 0, i10);
                System.arraycopy(objArr, 0, this.f18042c, 0, i10 << 1);
            }
            m20745d(iArr, objArr, i10);
        }
        if (this.f18043d != i10) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i9 = this.f18043d;
        if (i9 > 0) {
            int[] iArr = this.f18041b;
            Object[] objArr = this.f18042c;
            this.f18041b = C5304c.f18014a;
            this.f18042c = C5304c.f18016c;
            this.f18043d = 0;
            m20745d(iArr, objArr, i9);
        }
        if (this.f18043d > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return m20748f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return m20750h(obj) >= 0;
    }

    /* renamed from: e */
    public int m20747e(Object obj, int i9) {
        int i10 = this.f18043d;
        if (i10 == 0) {
            return -1;
        }
        int iM20744b = m20744b(this.f18041b, i10, i9);
        if (iM20744b < 0 || obj.equals(this.f18042c[iM20744b << 1])) {
            return iM20744b;
        }
        int i11 = iM20744b + 1;
        while (i11 < i10 && this.f18041b[i11] == i9) {
            if (obj.equals(this.f18042c[i11 << 1])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iM20744b - 1; i12 >= 0 && this.f18041b[i12] == i9; i12--) {
            if (obj.equals(this.f18042c[i12 << 1])) {
                return i12;
            }
        }
        return ~i11;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C5308g) {
            C5308g c5308g = (C5308g) obj;
            if (size() != c5308g.size()) {
                return false;
            }
            for (int i9 = 0; i9 < this.f18043d; i9++) {
                try {
                    K kM20751i = m20751i(i9);
                    V vM20755m = m20755m(i9);
                    Object obj2 = c5308g.get(kM20751i);
                    if (vM20755m == null) {
                        if (obj2 != null || !c5308g.containsKey(kM20751i)) {
                            return false;
                        }
                    } else if (!vM20755m.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i10 = 0; i10 < this.f18043d; i10++) {
                try {
                    K kM20751i2 = m20751i(i10);
                    V vM20755m2 = m20755m(i10);
                    Object obj3 = map.get(kM20751i2);
                    if (vM20755m2 == null) {
                        if (obj3 != null || !map.containsKey(kM20751i2)) {
                            return false;
                        }
                    } else if (!vM20755m2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public int m20748f(Object obj) {
        return obj == null ? m20749g() : m20747e(obj, obj.hashCode());
    }

    /* renamed from: g */
    public int m20749g() {
        int i9 = this.f18043d;
        if (i9 == 0) {
            return -1;
        }
        int iM20744b = m20744b(this.f18041b, i9, 0);
        if (iM20744b < 0 || this.f18042c[iM20744b << 1] == null) {
            return iM20744b;
        }
        int i10 = iM20744b + 1;
        while (i10 < i9 && this.f18041b[i10] == 0) {
            if (this.f18042c[i10 << 1] == null) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iM20744b - 1; i11 >= 0 && this.f18041b[i11] == 0; i11--) {
            if (this.f18042c[i11 << 1] == null) {
                return i11;
            }
        }
        return ~i10;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v8) {
        int iM20748f = m20748f(obj);
        return iM20748f >= 0 ? (V) this.f18042c[(iM20748f << 1) + 1] : v8;
    }

    /* renamed from: h */
    int m20750h(Object obj) {
        int i9 = this.f18043d * 2;
        Object[] objArr = this.f18042c;
        if (obj == null) {
            for (int i10 = 1; i10 < i9; i10 += 2) {
                if (objArr[i10] == null) {
                    return i10 >> 1;
                }
            }
            return -1;
        }
        for (int i11 = 1; i11 < i9; i11 += 2) {
            if (obj.equals(objArr[i11])) {
                return i11 >> 1;
            }
        }
        return -1;
    }

    public int hashCode() {
        int[] iArr = this.f18041b;
        Object[] objArr = this.f18042c;
        int i9 = this.f18043d;
        int i10 = 1;
        int i11 = 0;
        int iHashCode = 0;
        while (i11 < i9) {
            Object obj = objArr[i10];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i11];
            i11++;
            i10 += 2;
        }
        return iHashCode;
    }

    /* renamed from: i */
    public K m20751i(int i9) {
        return (K) this.f18042c[i9 << 1];
    }

    public boolean isEmpty() {
        return this.f18043d <= 0;
    }

    /* renamed from: j */
    public void mo20752j(C5308g<? extends K, ? extends V> c5308g) {
        int i9 = c5308g.f18043d;
        m20746c(this.f18043d + i9);
        if (this.f18043d != 0) {
            for (int i10 = 0; i10 < i9; i10++) {
                put(c5308g.m20751i(i10), c5308g.m20755m(i10));
            }
        } else if (i9 > 0) {
            System.arraycopy(c5308g.f18041b, 0, this.f18041b, 0, i9);
            System.arraycopy(c5308g.f18042c, 0, this.f18042c, 0, i9 << 1);
            this.f18043d = i9;
        }
    }

    /* renamed from: k */
    public V mo20753k(int i9) {
        Object[] objArr = this.f18042c;
        int i10 = i9 << 1;
        V v8 = (V) objArr[i10 + 1];
        int i11 = this.f18043d;
        int i12 = 0;
        if (i11 <= 1) {
            m20745d(this.f18041b, objArr, i11);
            this.f18041b = C5304c.f18014a;
            this.f18042c = C5304c.f18016c;
        } else {
            int i13 = i11 - 1;
            int[] iArr = this.f18041b;
            if (iArr.length <= 8 || i11 >= iArr.length / 3) {
                if (i9 < i13) {
                    int i14 = i9 + 1;
                    int i15 = i13 - i9;
                    System.arraycopy(iArr, i14, iArr, i9, i15);
                    Object[] objArr2 = this.f18042c;
                    System.arraycopy(objArr2, i14 << 1, objArr2, i10, i15 << 1);
                }
                Object[] objArr3 = this.f18042c;
                int i16 = i13 << 1;
                objArr3[i16] = null;
                objArr3[i16 + 1] = null;
            } else {
                m20743a(i11 > 8 ? i11 + (i11 >> 1) : 8);
                if (i11 != this.f18043d) {
                    throw new ConcurrentModificationException();
                }
                if (i9 > 0) {
                    System.arraycopy(iArr, 0, this.f18041b, 0, i9);
                    System.arraycopy(objArr, 0, this.f18042c, 0, i10);
                }
                if (i9 < i13) {
                    int i17 = i9 + 1;
                    int i18 = i13 - i9;
                    System.arraycopy(iArr, i17, this.f18041b, i9, i18);
                    System.arraycopy(objArr, i17 << 1, this.f18042c, i10, i18 << 1);
                }
            }
            i12 = i13;
        }
        if (i11 != this.f18043d) {
            throw new ConcurrentModificationException();
        }
        this.f18043d = i12;
        return v8;
    }

    /* renamed from: l */
    public V mo20754l(int i9, V v8) {
        int i10 = (i9 << 1) + 1;
        Object[] objArr = this.f18042c;
        V v9 = (V) objArr[i10];
        objArr[i10] = v8;
        return v9;
    }

    /* renamed from: m */
    public V m20755m(int i9) {
        return (V) this.f18042c[(i9 << 1) + 1];
    }

    public V put(K k9, V v8) {
        int i9;
        int iM20747e;
        int i10 = this.f18043d;
        if (k9 == null) {
            iM20747e = m20749g();
            i9 = 0;
        } else {
            int iHashCode = k9.hashCode();
            i9 = iHashCode;
            iM20747e = m20747e(k9, iHashCode);
        }
        if (iM20747e >= 0) {
            int i11 = (iM20747e << 1) + 1;
            Object[] objArr = this.f18042c;
            V v9 = (V) objArr[i11];
            objArr[i11] = v8;
            return v9;
        }
        int i12 = ~iM20747e;
        int[] iArr = this.f18041b;
        if (i10 >= iArr.length) {
            int i13 = 8;
            if (i10 >= 8) {
                i13 = (i10 >> 1) + i10;
            } else if (i10 < 4) {
                i13 = 4;
            }
            Object[] objArr2 = this.f18042c;
            m20743a(i13);
            if (i10 != this.f18043d) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f18041b;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f18042c, 0, objArr2.length);
            }
            m20745d(iArr, objArr2, i10);
        }
        if (i12 < i10) {
            int[] iArr3 = this.f18041b;
            int i14 = i12 + 1;
            System.arraycopy(iArr3, i12, iArr3, i14, i10 - i12);
            Object[] objArr3 = this.f18042c;
            System.arraycopy(objArr3, i12 << 1, objArr3, i14 << 1, (this.f18043d - i12) << 1);
        }
        int i15 = this.f18043d;
        if (i10 == i15) {
            int[] iArr4 = this.f18041b;
            if (i12 < iArr4.length) {
                iArr4[i12] = i9;
                Object[] objArr4 = this.f18042c;
                int i16 = i12 << 1;
                objArr4[i16] = k9;
                objArr4[i16 + 1] = v8;
                this.f18043d = i15 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public V putIfAbsent(K k9, V v8) {
        V v9 = get(k9);
        return v9 == null ? put(k9, v8) : v9;
    }

    public V remove(Object obj) {
        int iM20748f = m20748f(obj);
        if (iM20748f >= 0) {
            return mo20753k(iM20748f);
        }
        return null;
    }

    public V replace(K k9, V v8) {
        int iM20748f = m20748f(k9);
        if (iM20748f >= 0) {
            return mo20754l(iM20748f, v8);
        }
        return null;
    }

    public int size() {
        return this.f18043d;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f18043d * 28);
        sb.append('{');
        for (int i9 = 0; i9 < this.f18043d; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            K kM20751i = m20751i(i9);
            if (kM20751i != this) {
                sb.append(kM20751i);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vM20755m = m20755m(i9);
            if (vM20755m != this) {
                sb.append(vM20755m);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean remove(Object obj, Object obj2) {
        int iM20748f = m20748f(obj);
        if (iM20748f < 0) {
            return false;
        }
        V vM20755m = m20755m(iM20748f);
        if (obj2 != vM20755m && (obj2 == null || !obj2.equals(vM20755m))) {
            return false;
        }
        mo20753k(iM20748f);
        return true;
    }

    public boolean replace(K k9, V v8, V v9) {
        int iM20748f = m20748f(k9);
        if (iM20748f < 0) {
            return false;
        }
        V vM20755m = m20755m(iM20748f);
        if (vM20755m != v8 && (v8 == null || !v8.equals(vM20755m))) {
            return false;
        }
        mo20754l(iM20748f, v9);
        return true;
    }

    public C5308g(int i9) {
        if (i9 == 0) {
            this.f18041b = C5304c.f18014a;
            this.f18042c = C5304c.f18016c;
        } else {
            m20743a(i9);
        }
        this.f18043d = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C5308g(C5308g<K, V> c5308g) {
        this();
        if (c5308g != 0) {
            mo20752j(c5308g);
        }
    }
}
