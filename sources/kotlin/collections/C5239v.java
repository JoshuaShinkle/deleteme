package kotlin.collections;

import java.util.LinkedHashMap;
import java.util.Map;
import p007a6.C0042f;

/* renamed from: kotlin.collections.v */
/* loaded from: classes2.dex */
public class C5239v extends C5238u {
    /* renamed from: c */
    public static final <K, V> Map<K, V> m20435c() {
        EmptyMap emptyMap = EmptyMap.f17829b;
        C0042f.m156c(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    /* renamed from: d */
    public static final <K, V> Map<K, V> m20436d(Map<? extends K, ? extends V> map) {
        C0042f.m158e(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? m20437e(map) : C5238u.m20434b(map) : m20435c();
    }

    /* renamed from: e */
    public static final <K, V> Map<K, V> m20437e(Map<? extends K, ? extends V> map) {
        C0042f.m158e(map, "<this>");
        return new LinkedHashMap(map);
    }
}
