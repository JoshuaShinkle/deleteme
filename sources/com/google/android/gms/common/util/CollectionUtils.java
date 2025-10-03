package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p132m.C5302a;
import p132m.C5303b;

@KeepForSdk
/* loaded from: classes2.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @KeepForSdk
    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k9, V v8, K k10, V v9, K k11, V v10) {
        Map mapZza = zza(3, false);
        mapZza.put(k9, v8);
        mapZza.put(k10, v9);
        mapZza.put(k11, v10);
        return Collections.unmodifiableMap(mapZza);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] kArr, V[] vArr) {
        int length = kArr.length;
        int length2 = vArr.length;
        if (length != length2) {
            throw new IllegalArgumentException("Key and values array lengths not equal: " + length + " != " + length2);
        }
        if (length == 0) {
            return Collections.emptyMap();
        }
        if (length == 1) {
            return Collections.singletonMap(kArr[0], vArr[0]);
        }
        Map mapZza = zza(length, false);
        for (int i9 = 0; i9 < kArr.length; i9++) {
            mapZza.put(kArr[i9], vArr[i9]);
        }
        return Collections.unmodifiableMap(mapZza);
    }

    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(int i9) {
        return i9 == 0 ? new C5303b() : zzb(i9, true);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T t8, T t9, T t10) {
        Set setZzb = zzb(3, false);
        setZzb.add(t8);
        setZzb.add(t9);
        setZzb.add(t10);
        return Collections.unmodifiableSet(setZzb);
    }

    private static Map zza(int i9, boolean z8) {
        return i9 <= 256 ? new C5302a(i9) : new HashMap(i9, 1.0f);
    }

    private static Set zzb(int i9, boolean z8) {
        return i9 <= (true != z8 ? 256 : 128) ? new C5303b(i9) : new HashSet(i9, true != z8 ? 1.0f : 0.75f);
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T t8) {
        return Collections.singletonList(t8);
    }

    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(T... tArr) {
        int length = tArr.length;
        if (length == 0) {
            return listOf();
        }
        if (length != 1) {
            return Collections.unmodifiableList(Arrays.asList(tArr));
        }
        return listOf(tArr[0]);
    }

    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(K k9, V v8, K k10, V v9, K k11, V v10, K k12, V v11, K k13, V v12, K k14, V v13) {
        Map mapZza = zza(6, false);
        mapZza.put(k9, v8);
        mapZza.put(k10, v9);
        mapZza.put(k11, v10);
        mapZza.put(k12, v11);
        mapZza.put(k13, v12);
        mapZza.put(k14, v13);
        return Collections.unmodifiableMap(mapZza);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Set<T> setOf(T... tArr) {
        int length = tArr.length;
        if (length == 0) {
            return Collections.emptySet();
        }
        if (length == 1) {
            return Collections.singleton(tArr[0]);
        }
        if (length == 2) {
            T t8 = tArr[0];
            T t9 = tArr[1];
            Set setZzb = zzb(2, false);
            setZzb.add(t8);
            setZzb.add(t9);
            return Collections.unmodifiableSet(setZzb);
        }
        if (length == 3) {
            return setOf(tArr[0], tArr[1], tArr[2]);
        }
        if (length != 4) {
            Set setZzb2 = zzb(length, false);
            Collections.addAll(setZzb2, tArr);
            return Collections.unmodifiableSet(setZzb2);
        }
        T t10 = tArr[0];
        T t11 = tArr[1];
        T t12 = tArr[2];
        T t13 = tArr[3];
        Set setZzb3 = zzb(4, false);
        setZzb3.add(t10);
        setZzb3.add(t11);
        setZzb3.add(t12);
        setZzb3.add(t13);
        return Collections.unmodifiableSet(setZzb3);
    }
}
