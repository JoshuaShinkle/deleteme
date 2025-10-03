package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import p007a6.C0042f;

/* renamed from: kotlin.collections.f */
/* loaded from: classes2.dex */
public class C5223f extends C5222e {
    /* renamed from: h */
    public static final <T> boolean m20385h(T[] tArr, T t8) {
        C0042f.m158e(tArr, "<this>");
        return m20389l(tArr, t8) >= 0;
    }

    /* renamed from: i */
    public static final <T> List<T> m20386i(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        return (List) m20387j(tArr, new ArrayList());
    }

    /* renamed from: j */
    public static final <C extends Collection<? super T>, T> C m20387j(T[] tArr, C c9) {
        C0042f.m158e(tArr, "<this>");
        C0042f.m158e(c9, FirebaseAnalytics.Param.DESTINATION);
        for (T t8 : tArr) {
            if (t8 != null) {
                c9.add(t8);
            }
        }
        return c9;
    }

    /* renamed from: k */
    public static final <T> int m20388k(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        return tArr.length - 1;
    }

    /* renamed from: l */
    public static final <T> int m20389l(T[] tArr, T t8) {
        C0042f.m158e(tArr, "<this>");
        int i9 = 0;
        if (t8 == null) {
            int length = tArr.length;
            while (i9 < length) {
                if (tArr[i9] == null) {
                    return i9;
                }
                i9++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i9 < length2) {
            if (C0042f.m154a(t8, tArr[i9])) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* renamed from: m */
    public static final char m20390m(char[] cArr) {
        C0042f.m158e(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    /* renamed from: n */
    public static final <T> T m20391n(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    /* renamed from: o */
    public static final <T> List<T> m20392o(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        int length = tArr.length;
        return length != 0 ? length != 1 ? m20393p(tArr) : C5225h.m20396b(tArr[0]) : C5226i.m20400f();
    }

    /* renamed from: p */
    public static final <T> List<T> m20393p(T[] tArr) {
        C0042f.m158e(tArr, "<this>");
        return new ArrayList(C5226i.m20397c(tArr));
    }
}
