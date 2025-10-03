package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;

@GwtCompatible
/* loaded from: classes2.dex */
public interface SortedSetMultimap<K, V> extends SetMultimap<K, V> {
    @Override // com.google.common.collect.SetMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    Map<K, Collection<V>> asMap();

    @Override // com.google.common.collect.SetMultimap, com.google.common.collect.Multimap
    SortedSet<V> get(K k9);

    @Override // com.google.common.collect.SetMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    SortedSet<V> removeAll(Object obj);

    @Override // com.google.common.collect.SetMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    SortedSet<V> replaceValues(K k9, Iterable<? extends V> iterable);

    Comparator<? super V> valueComparator();
}
