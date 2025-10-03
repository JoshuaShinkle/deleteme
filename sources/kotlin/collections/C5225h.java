package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p007a6.C0042f;

/* renamed from: kotlin.collections.h */
/* loaded from: classes2.dex */
public class C5225h {
    /* renamed from: a */
    public static final <T> Object[] m20395a(T[] tArr, boolean z8) {
        C0042f.m158e(tArr, "<this>");
        if (z8 && C0042f.m154a(tArr.getClass(), Object[].class)) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        C0042f.m157d(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }

    /* renamed from: b */
    public static final <T> List<T> m20396b(T t8) {
        List<T> listSingletonList = Collections.singletonList(t8);
        C0042f.m157d(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }
}
