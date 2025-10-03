package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private DaggerCollections() {
    }

    private static int calculateInitialCapacity(int i9) {
        if (i9 < 3) {
            return i9 + 1;
        }
        if (i9 < 1073741824) {
            return (int) ((i9 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    public static <T> HashSet<T> newHashSetWithExpectedSize(int i9) {
        return new HashSet<>(calculateInitialCapacity(i9));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i9) {
        return new LinkedHashMap<>(calculateInitialCapacity(i9));
    }

    public static <T> List<T> presizedList(int i9) {
        return i9 == 0 ? Collections.emptyList() : new ArrayList(i9);
    }
}
