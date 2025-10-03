package p212u5;

import java.util.Comparator;
import p007a6.C0042f;

/* renamed from: u5.b */
/* loaded from: classes2.dex */
public final class C6421b implements Comparator<Comparable<? super Object>> {

    /* renamed from: b */
    public static final C6421b f21650b = new C6421b();

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Comparable<Object> comparable, Comparable<Object> comparable2) {
        C0042f.m158e(comparable, "a");
        C0042f.m158e(comparable2, "b");
        return comparable.compareTo(comparable2);
    }

    @Override // java.util.Comparator
    public final Comparator<Comparable<? super Object>> reversed() {
        return C6422c.f21651b;
    }
}
