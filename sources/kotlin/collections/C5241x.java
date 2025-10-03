package kotlin.collections;

import java.util.Set;
import p007a6.C0042f;

/* renamed from: kotlin.collections.x */
/* loaded from: classes2.dex */
public class C5241x extends C5240w {
    /* renamed from: b */
    public static final <T> Set<T> m20439b() {
        return EmptySet.f17830b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    public static final <T> Set<T> m20440c(Set<? extends T> set) {
        C0042f.m158e(set, "<this>");
        int size = set.size();
        return size != 0 ? size != 1 ? set : C5240w.m20438a(set.iterator().next()) : m20439b();
    }
}
