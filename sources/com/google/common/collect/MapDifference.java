package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible
/* loaded from: classes2.dex */
public interface MapDifference<K, V> {

    public interface ValueDifference<V> {
        boolean equals(Object obj);

        int hashCode();

        V leftValue();

        V rightValue();
    }

    boolean areEqual();

    Map<K, ValueDifference<V>> entriesDiffering();

    Map<K, V> entriesInCommon();

    Map<K, V> entriesOnlyOnLeft();

    Map<K, V> entriesOnlyOnRight();

    boolean equals(Object obj);

    int hashCode();
}
