package kotlin.collections;

import java.util.Collection;
import p007a6.C0042f;

/* renamed from: kotlin.collections.j */
/* loaded from: classes2.dex */
public class C5227j extends C5226i {
    /* renamed from: n */
    public static final <T> int m20408n(Iterable<? extends T> iterable, int i9) {
        C0042f.m158e(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i9;
    }
}
