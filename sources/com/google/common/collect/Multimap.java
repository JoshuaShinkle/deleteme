package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@GwtCompatible
/* loaded from: classes2.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@CompatibleWith("K") Object obj, @CompatibleWith("V") Object obj2);

    boolean containsKey(@CompatibleWith("K") Object obj);

    boolean containsValue(@CompatibleWith("V") Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(Object obj);

    Collection<V> get(K k9);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    @CanIgnoreReturnValue
    boolean put(K k9, V v8);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    boolean putAll(K k9, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    boolean remove(@CompatibleWith("K") Object obj, @CompatibleWith("V") Object obj2);

    @CanIgnoreReturnValue
    Collection<V> removeAll(@CompatibleWith("K") Object obj);

    @CanIgnoreReturnValue
    Collection<V> replaceValues(K k9, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();
}
