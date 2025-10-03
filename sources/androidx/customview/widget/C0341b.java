package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* renamed from: androidx.customview.widget.b */
/* loaded from: classes.dex */
public class C0341b {

    /* renamed from: androidx.customview.widget.b$a */
    public interface a<T> {
        /* renamed from: a */
        void mo1632a(T t8, Rect rect);
    }

    /* renamed from: androidx.customview.widget.b$b */
    public interface b<T, V> {
        /* renamed from: a */
        V mo1634a(T t8, int i9);

        /* renamed from: b */
        int mo1635b(T t8);
    }

    /* renamed from: androidx.customview.widget.b$c */
    public static class c<T> implements Comparator<T> {

        /* renamed from: b */
        public final Rect f1859b = new Rect();

        /* renamed from: c */
        public final Rect f1860c = new Rect();

        /* renamed from: d */
        public final boolean f1861d;

        /* renamed from: e */
        public final a<T> f1862e;

        public c(boolean z8, a<T> aVar) {
            this.f1861d = z8;
            this.f1862e = aVar;
        }

        @Override // java.util.Comparator
        public int compare(T t8, T t9) {
            Rect rect = this.f1859b;
            Rect rect2 = this.f1860c;
            this.f1862e.mo1632a(t8, rect);
            this.f1862e.mo1632a(t9, rect2);
            int i9 = rect.top;
            int i10 = rect2.top;
            if (i9 < i10) {
                return -1;
            }
            if (i9 > i10) {
                return 1;
            }
            int i11 = rect.left;
            int i12 = rect2.left;
            if (i11 < i12) {
                return this.f1861d ? 1 : -1;
            }
            if (i11 > i12) {
                return this.f1861d ? -1 : 1;
            }
            int i13 = rect.bottom;
            int i14 = rect2.bottom;
            if (i13 < i14) {
                return -1;
            }
            if (i13 > i14) {
                return 1;
            }
            int i15 = rect.right;
            int i16 = rect2.right;
            if (i15 < i16) {
                return this.f1861d ? 1 : -1;
            }
            if (i15 > i16) {
                return this.f1861d ? -1 : 1;
            }
            return 0;
        }
    }

    /* renamed from: a */
    public static boolean m1641a(int i9, Rect rect, Rect rect2, Rect rect3) {
        boolean zM1642b = m1642b(i9, rect, rect2);
        if (m1642b(i9, rect, rect3) || !zM1642b) {
            return false;
        }
        return !m1650j(i9, rect, rect3) || i9 == 17 || i9 == 66 || m1651k(i9, rect, rect2) < m1653m(i9, rect, rect3);
    }

    /* renamed from: b */
    public static boolean m1642b(int i9, Rect rect, Rect rect2) {
        if (i9 != 17) {
            if (i9 != 33) {
                if (i9 != 66) {
                    if (i9 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    /* renamed from: c */
    public static <L, T> T m1643c(L l9, b<L, T> bVar, a<T> aVar, T t8, Rect rect, int i9) {
        Rect rect2 = new Rect(rect);
        if (i9 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i9 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i9 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i9 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        int iMo1635b = bVar.mo1635b(l9);
        Rect rect3 = new Rect();
        T t9 = null;
        for (int i10 = 0; i10 < iMo1635b; i10++) {
            T tMo1634a = bVar.mo1634a(l9, i10);
            if (tMo1634a != t8) {
                aVar.mo1632a(tMo1634a, rect3);
                if (m1648h(i9, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t9 = tMo1634a;
                }
            }
        }
        return t9;
    }

    /* renamed from: d */
    public static <L, T> T m1644d(L l9, b<L, T> bVar, a<T> aVar, T t8, int i9, boolean z8, boolean z9) {
        int iMo1635b = bVar.mo1635b(l9);
        ArrayList arrayList = new ArrayList(iMo1635b);
        for (int i10 = 0; i10 < iMo1635b; i10++) {
            arrayList.add(bVar.mo1634a(l9, i10));
        }
        Collections.sort(arrayList, new c(z8, aVar));
        if (i9 == 1) {
            return (T) m1646f(t8, arrayList, z9);
        }
        if (i9 == 2) {
            return (T) m1645e(t8, arrayList, z9);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    /* renamed from: e */
    public static <T> T m1645e(T t8, ArrayList<T> arrayList, boolean z8) {
        int size = arrayList.size();
        int iLastIndexOf = (t8 == null ? -1 : arrayList.lastIndexOf(t8)) + 1;
        if (iLastIndexOf < size) {
            return arrayList.get(iLastIndexOf);
        }
        if (!z8 || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    /* renamed from: f */
    public static <T> T m1646f(T t8, ArrayList<T> arrayList, boolean z8) {
        int size = arrayList.size();
        int iIndexOf = (t8 == null ? size : arrayList.indexOf(t8)) - 1;
        if (iIndexOf >= 0) {
            return arrayList.get(iIndexOf);
        }
        if (!z8 || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    /* renamed from: g */
    public static int m1647g(int i9, int i10) {
        return (i9 * 13 * i9) + (i10 * i10);
    }

    /* renamed from: h */
    public static boolean m1648h(int i9, Rect rect, Rect rect2, Rect rect3) {
        if (!m1649i(rect, rect2, i9)) {
            return false;
        }
        if (m1649i(rect, rect3, i9) && !m1641a(i9, rect, rect2, rect3)) {
            return !m1641a(i9, rect, rect3, rect2) && m1647g(m1651k(i9, rect, rect2), m1655o(i9, rect, rect2)) < m1647g(m1651k(i9, rect, rect3), m1655o(i9, rect, rect3));
        }
        return true;
    }

    /* renamed from: i */
    public static boolean m1649i(Rect rect, Rect rect2, int i9) {
        if (i9 == 17) {
            int i10 = rect.right;
            int i11 = rect2.right;
            return (i10 > i11 || rect.left >= i11) && rect.left > rect2.left;
        }
        if (i9 == 33) {
            int i12 = rect.bottom;
            int i13 = rect2.bottom;
            return (i12 > i13 || rect.top >= i13) && rect.top > rect2.top;
        }
        if (i9 == 66) {
            int i14 = rect.left;
            int i15 = rect2.left;
            return (i14 < i15 || rect.right <= i15) && rect.right < rect2.right;
        }
        if (i9 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i16 = rect.top;
        int i17 = rect2.top;
        return (i16 < i17 || rect.bottom <= i17) && rect.bottom < rect2.bottom;
    }

    /* renamed from: j */
    public static boolean m1650j(int i9, Rect rect, Rect rect2) {
        if (i9 == 17) {
            return rect.left >= rect2.right;
        }
        if (i9 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i9 == 66) {
            return rect.right <= rect2.left;
        }
        if (i9 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    /* renamed from: k */
    public static int m1651k(int i9, Rect rect, Rect rect2) {
        return Math.max(0, m1652l(i9, rect, rect2));
    }

    /* renamed from: l */
    public static int m1652l(int i9, Rect rect, Rect rect2) {
        int i10;
        int i11;
        if (i9 == 17) {
            i10 = rect.left;
            i11 = rect2.right;
        } else if (i9 == 33) {
            i10 = rect.top;
            i11 = rect2.bottom;
        } else if (i9 == 66) {
            i10 = rect2.left;
            i11 = rect.right;
        } else {
            if (i9 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i10 = rect2.top;
            i11 = rect.bottom;
        }
        return i10 - i11;
    }

    /* renamed from: m */
    public static int m1653m(int i9, Rect rect, Rect rect2) {
        return Math.max(1, m1654n(i9, rect, rect2));
    }

    /* renamed from: n */
    public static int m1654n(int i9, Rect rect, Rect rect2) {
        int i10;
        int i11;
        if (i9 == 17) {
            i10 = rect.left;
            i11 = rect2.left;
        } else if (i9 == 33) {
            i10 = rect.top;
            i11 = rect2.top;
        } else if (i9 == 66) {
            i10 = rect2.right;
            i11 = rect.right;
        } else {
            if (i9 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i10 = rect2.bottom;
            i11 = rect.bottom;
        }
        return i10 - i11;
    }

    /* renamed from: o */
    public static int m1655o(int i9, Rect rect, Rect rect2) {
        if (i9 != 17) {
            if (i9 != 33) {
                if (i9 != 66) {
                    if (i9 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
