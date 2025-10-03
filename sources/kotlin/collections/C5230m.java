package kotlin.collections;

import java.util.Collections;
import java.util.List;
import p007a6.C0042f;

/* renamed from: kotlin.collections.m */
/* loaded from: classes2.dex */
public class C5230m extends C5229l {
    /* renamed from: o */
    public static final <T extends Comparable<? super T>> void m20409o(List<T> list) {
        C0042f.m158e(list, "<this>");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
