package kotlin.collections;

import java.util.Collections;
import java.util.Map;
import p007a6.C0042f;

/* renamed from: kotlin.collections.u */
/* loaded from: classes2.dex */
public class C5238u extends C5237t {
    /* renamed from: a */
    public static final int m20433a(int i9) {
        if (i9 < 0) {
            return i9;
        }
        if (i9 < 3) {
            return i9 + 1;
        }
        if (i9 < 1073741824) {
            return (int) ((i9 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    /* renamed from: b */
    public static final <K, V> Map<K, V> m20434b(Map<? extends K, ? extends V> map) {
        C0042f.m158e(map, "<this>");
        Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
        Map<K, V> mapSingletonMap = Collections.singletonMap(next.getKey(), next.getValue());
        C0042f.m157d(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }
}
