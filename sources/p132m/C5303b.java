package p132m;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: m.b */
/* loaded from: classes.dex */
public final class C5303b<E> implements Collection<E>, Set<E> {

    /* renamed from: f */
    public static final int[] f18003f = new int[0];

    /* renamed from: g */
    public static final Object[] f18004g = new Object[0];

    /* renamed from: h */
    public static Object[] f18005h;

    /* renamed from: i */
    public static int f18006i;

    /* renamed from: j */
    public static Object[] f18007j;

    /* renamed from: k */
    public static int f18008k;

    /* renamed from: b */
    public int[] f18009b;

    /* renamed from: c */
    public Object[] f18010c;

    /* renamed from: d */
    public int f18011d;

    /* renamed from: e */
    public AbstractC5307f<E, E> f18012e;

    /* renamed from: m.b$a */
    public class a extends AbstractC5307f<E, E> {
        public a() {
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: a */
        public void mo20695a() {
            C5303b.this.clear();
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: b */
        public Object mo20696b(int i9, int i10) {
            return C5303b.this.f18010c[i9];
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: c */
        public Map<E, E> mo20697c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: d */
        public int mo20698d() {
            return C5303b.this.f18011d;
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: e */
        public int mo20699e(Object obj) {
            return C5303b.this.indexOf(obj);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: f */
        public int mo20700f(Object obj) {
            return C5303b.this.indexOf(obj);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: g */
        public void mo20701g(E e9, E e10) {
            C5303b.this.add(e9);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: h */
        public void mo20702h(int i9) {
            C5303b.this.m20710g(i9);
        }

        @Override // p132m.AbstractC5307f
        /* renamed from: i */
        public E mo20703i(int i9, E e9) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public C5303b() {
        this(0);
    }

    /* renamed from: c */
    public static void m20704c(int[] iArr, Object[] objArr, int i9) {
        if (iArr.length == 8) {
            synchronized (C5303b.class) {
                if (f18008k < 10) {
                    objArr[0] = f18007j;
                    objArr[1] = iArr;
                    for (int i10 = i9 - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f18007j = objArr;
                    f18008k++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (C5303b.class) {
                if (f18006i < 10) {
                    objArr[0] = f18005h;
                    objArr[1] = iArr;
                    for (int i11 = i9 - 1; i11 >= 2; i11--) {
                        objArr[i11] = null;
                    }
                    f18005h = objArr;
                    f18006i++;
                }
            }
        }
    }

    /* renamed from: a */
    public final void m20705a(int i9) {
        if (i9 == 8) {
            synchronized (C5303b.class) {
                Object[] objArr = f18007j;
                if (objArr != null) {
                    this.f18010c = objArr;
                    f18007j = (Object[]) objArr[0];
                    this.f18009b = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f18008k--;
                    return;
                }
            }
        } else if (i9 == 4) {
            synchronized (C5303b.class) {
                Object[] objArr2 = f18005h;
                if (objArr2 != null) {
                    this.f18010c = objArr2;
                    f18005h = (Object[]) objArr2[0];
                    this.f18009b = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f18006i--;
                    return;
                }
            }
        }
        this.f18009b = new int[i9];
        this.f18010c = new Object[i9];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e9) {
        int i9;
        int iM20708e;
        if (e9 == null) {
            iM20708e = m20709f();
            i9 = 0;
        } else {
            int iHashCode = e9.hashCode();
            i9 = iHashCode;
            iM20708e = m20708e(e9, iHashCode);
        }
        if (iM20708e >= 0) {
            return false;
        }
        int i10 = ~iM20708e;
        int i11 = this.f18011d;
        int[] iArr = this.f18009b;
        if (i11 >= iArr.length) {
            int i12 = 8;
            if (i11 >= 8) {
                i12 = (i11 >> 1) + i11;
            } else if (i11 < 4) {
                i12 = 4;
            }
            Object[] objArr = this.f18010c;
            m20705a(i12);
            int[] iArr2 = this.f18009b;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f18010c, 0, objArr.length);
            }
            m20704c(iArr, objArr, this.f18011d);
        }
        int i13 = this.f18011d;
        if (i10 < i13) {
            int[] iArr3 = this.f18009b;
            int i14 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i14, i13 - i10);
            Object[] objArr2 = this.f18010c;
            System.arraycopy(objArr2, i10, objArr2, i14, this.f18011d - i10);
        }
        this.f18009b[i10] = i9;
        this.f18010c[i10] = e9;
        this.f18011d++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        m20706b(this.f18011d + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    /* renamed from: b */
    public void m20706b(int i9) {
        int[] iArr = this.f18009b;
        if (iArr.length < i9) {
            Object[] objArr = this.f18010c;
            m20705a(i9);
            int i10 = this.f18011d;
            if (i10 > 0) {
                System.arraycopy(iArr, 0, this.f18009b, 0, i10);
                System.arraycopy(objArr, 0, this.f18010c, 0, this.f18011d);
            }
            m20704c(iArr, objArr, this.f18011d);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i9 = this.f18011d;
        if (i9 != 0) {
            m20704c(this.f18009b, this.f18010c, i9);
            this.f18009b = f18003f;
            this.f18010c = f18004g;
            this.f18011d = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    public final AbstractC5307f<E, E> m20707d() {
        if (this.f18012e == null) {
            this.f18012e = new a();
        }
        return this.f18012e;
    }

    /* renamed from: e */
    public final int m20708e(Object obj, int i9) {
        int i10 = this.f18011d;
        if (i10 == 0) {
            return -1;
        }
        int iM20712a = C5304c.m20712a(this.f18009b, i10, i9);
        if (iM20712a < 0 || obj.equals(this.f18010c[iM20712a])) {
            return iM20712a;
        }
        int i11 = iM20712a + 1;
        while (i11 < i10 && this.f18009b[i11] == i9) {
            if (obj.equals(this.f18010c[i11])) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iM20712a - 1; i12 >= 0 && this.f18009b[i12] == i9; i12--) {
            if (obj.equals(this.f18010c[i12])) {
                return i12;
            }
        }
        return ~i11;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i9 = 0; i9 < this.f18011d; i9++) {
                try {
                    if (!set.contains(m20711h(i9))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public final int m20709f() {
        int i9 = this.f18011d;
        if (i9 == 0) {
            return -1;
        }
        int iM20712a = C5304c.m20712a(this.f18009b, i9, 0);
        if (iM20712a < 0 || this.f18010c[iM20712a] == null) {
            return iM20712a;
        }
        int i10 = iM20712a + 1;
        while (i10 < i9 && this.f18009b[i10] == 0) {
            if (this.f18010c[i10] == null) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iM20712a - 1; i11 >= 0 && this.f18009b[i11] == 0; i11--) {
            if (this.f18010c[i11] == null) {
                return i11;
            }
        }
        return ~i10;
    }

    /* renamed from: g */
    public E m20710g(int i9) {
        Object[] objArr = this.f18010c;
        E e9 = (E) objArr[i9];
        int i10 = this.f18011d;
        if (i10 <= 1) {
            m20704c(this.f18009b, objArr, i10);
            this.f18009b = f18003f;
            this.f18010c = f18004g;
            this.f18011d = 0;
        } else {
            int[] iArr = this.f18009b;
            if (iArr.length <= 8 || i10 >= iArr.length / 3) {
                int i11 = i10 - 1;
                this.f18011d = i11;
                if (i9 < i11) {
                    int i12 = i9 + 1;
                    System.arraycopy(iArr, i12, iArr, i9, i11 - i9);
                    Object[] objArr2 = this.f18010c;
                    System.arraycopy(objArr2, i12, objArr2, i9, this.f18011d - i9);
                }
                this.f18010c[this.f18011d] = null;
            } else {
                m20705a(i10 > 8 ? i10 + (i10 >> 1) : 8);
                this.f18011d--;
                if (i9 > 0) {
                    System.arraycopy(iArr, 0, this.f18009b, 0, i9);
                    System.arraycopy(objArr, 0, this.f18010c, 0, i9);
                }
                int i13 = this.f18011d;
                if (i9 < i13) {
                    int i14 = i9 + 1;
                    System.arraycopy(iArr, i14, this.f18009b, i9, i13 - i9);
                    System.arraycopy(objArr, i14, this.f18010c, i9, this.f18011d - i9);
                }
            }
        }
        return e9;
    }

    /* renamed from: h */
    public E m20711h(int i9) {
        return (E) this.f18010c[i9];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f18009b;
        int i9 = this.f18011d;
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            i10 += iArr[i11];
        }
        return i10;
    }

    public int indexOf(Object obj) {
        return obj == null ? m20709f() : m20708e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f18011d <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return m20707d().m20737m().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        m20710g(iIndexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z8 = false;
        for (int i9 = this.f18011d - 1; i9 >= 0; i9--) {
            if (!collection.contains(this.f18010c[i9])) {
                m20710g(i9);
                z8 = true;
            }
        }
        return z8;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f18011d;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i9 = this.f18011d;
        Object[] objArr = new Object[i9];
        System.arraycopy(this.f18010c, 0, objArr, 0, i9);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f18011d * 14);
        sb.append('{');
        for (int i9 = 0; i9 < this.f18011d; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            E eM20711h = m20711h(i9);
            if (eM20711h != this) {
                sb.append(eM20711h);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public C5303b(int i9) {
        if (i9 == 0) {
            this.f18009b = f18003f;
            this.f18010c = f18004g;
        } else {
            m20705a(i9);
        }
        this.f18011d = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f18011d) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f18011d));
        }
        System.arraycopy(this.f18010c, 0, tArr, 0, this.f18011d);
        int length = tArr.length;
        int i9 = this.f18011d;
        if (length > i9) {
            tArr[i9] = null;
        }
        return tArr;
    }
}
