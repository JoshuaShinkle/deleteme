package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import p007a6.C0042f;

/* renamed from: kotlin.collections.n */
/* loaded from: classes2.dex */
public class C5231n extends C5230m {
    /* renamed from: p */
    public static final <T> boolean m20410p(Collection<? super T> collection, Iterable<? extends T> iterable) {
        C0042f.m158e(collection, "<this>");
        C0042f.m158e(iterable, "elements");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        boolean z8 = false;
        while (it.hasNext()) {
            if (collection.add(it.next())) {
                z8 = true;
            }
        }
        return z8;
    }

    /* renamed from: q */
    public static final <T> boolean m20411q(Collection<? super T> collection, T[] tArr) {
        C0042f.m158e(collection, "<this>");
        C0042f.m158e(tArr, "elements");
        return collection.addAll(C5222e.m20379b(tArr));
    }
}
