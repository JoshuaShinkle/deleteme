package p212u5;

import java.util.Comparator;
import p007a6.C0042f;

/* renamed from: u5.a */
/* loaded from: classes2.dex */
public class C6420a {
    /* renamed from: a */
    public static final <T extends Comparable<?>> int m24571a(T t8, T t9) {
        if (t8 == t9) {
            return 0;
        }
        if (t8 == null) {
            return -1;
        }
        if (t9 == null) {
            return 1;
        }
        return t8.compareTo(t9);
    }

    /* renamed from: b */
    public static final <T extends Comparable<? super T>> Comparator<T> m24572b() {
        C6421b c6421b = C6421b.f21650b;
        C0042f.m156c(c6421b, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }");
        return c6421b;
    }
}
