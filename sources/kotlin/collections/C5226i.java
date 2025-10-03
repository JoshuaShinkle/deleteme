package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p007a6.C0042f;
import p212u5.C6420a;

/* renamed from: kotlin.collections.i */
/* loaded from: classes2.dex */
public class C5226i extends C5225h {
    /* renamed from: c */
    public static final <T> Collection<T> m20397c(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        return new C5219b(tArr, false);
    }

    /* renamed from: d */
    public static final <T extends Comparable<? super T>> int m20398d(List<? extends T> list, T t8, int i9, int i10) {
        C0042f.m158e(list, "<this>");
        m20406l(list.size(), i9, i10);
        int i11 = i10 - 1;
        while (i9 <= i11) {
            int i12 = (i9 + i11) >>> 1;
            int iM24571a = C6420a.m24571a(list.get(i12), t8);
            if (iM24571a < 0) {
                i9 = i12 + 1;
            } else {
                if (iM24571a <= 0) {
                    return i12;
                }
                i11 = i12 - 1;
            }
        }
        return -(i9 + 1);
    }

    /* renamed from: e */
    public static /* synthetic */ int m20399e(List list, Comparable comparable, int i9, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i9 = 0;
        }
        if ((i11 & 4) != 0) {
            i10 = list.size();
        }
        return m20398d(list, comparable, i9, i10);
    }

    /* renamed from: f */
    public static final <T> List<T> m20400f() {
        return EmptyList.f17828b;
    }

    /* renamed from: g */
    public static final <T> int m20401g(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        return list.size() - 1;
    }

    /* renamed from: h */
    public static final <T> List<T> m20402h(T... tArr) {
        C0042f.m158e(tArr, "elements");
        return tArr.length > 0 ? C5222e.m20379b(tArr) : m20400f();
    }

    /* renamed from: i */
    public static final <T> List<T> m20403i(T... tArr) {
        C0042f.m158e(tArr, "elements");
        return C5223f.m20386i(tArr);
    }

    /* renamed from: j */
    public static final <T> List<T> m20404j(T... tArr) {
        C0042f.m158e(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new C5219b(tArr, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: k */
    public static final <T> List<T> m20405k(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        int size = list.size();
        return size != 0 ? size != 1 ? list : C5225h.m20396b(list.get(0)) : m20400f();
    }

    /* renamed from: l */
    public static final void m20406l(int i9, int i10, int i11) {
        if (i10 > i11) {
            throw new IllegalArgumentException("fromIndex (" + i10 + ") is greater than toIndex (" + i11 + ").");
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i10 + ") is less than zero.");
        }
        if (i11 <= i9) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i11 + ") is greater than size (" + i9 + ").");
    }

    /* renamed from: m */
    public static final void m20407m() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
