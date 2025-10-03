package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.lang.Comparable;
import java.util.Map;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public interface RangeMap<K extends Comparable, V> {
    Map<Range<K>, V> asDescendingMapOfRanges();

    Map<Range<K>, V> asMapOfRanges();

    void clear();

    boolean equals(Object obj);

    V get(K k9);

    Map.Entry<Range<K>, V> getEntry(K k9);

    int hashCode();

    void put(Range<K> range, V v8);

    void putAll(RangeMap<K, V> rangeMap);

    void putCoalescing(Range<K> range, V v8);

    void remove(Range<K> range);

    Range<K> span();

    RangeMap<K, V> subRangeMap(Range<K> range);

    String toString();
}
