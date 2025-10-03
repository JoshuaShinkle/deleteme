package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V> {

    @Beta
    public class StandardDescendingMap extends Maps.DescendingMap<K, V> {
        public StandardDescendingMap() {
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.ForwardingNavigableMap.StandardDescendingMap.1
                private Map.Entry<K, V> nextOrNull;
                private Map.Entry<K, V> toRemove = null;

                {
                    this.nextOrNull = StandardDescendingMap.this.forward().lastEntry();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    try {
                        Map.Entry<K, V> entry = this.nextOrNull;
                        this.toRemove = entry;
                        this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                        return entry;
                    } catch (Throwable th) {
                        this.toRemove = this.nextOrNull;
                        this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                        throw th;
                    }
                }
            };
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public NavigableMap<K, V> forward() {
            return ForwardingNavigableMap.this;
        }
    }

    @Beta
    public class StandardNavigableKeySet extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet() {
            super(ForwardingNavigableMap.this);
        }
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K k9) {
        return delegate().ceilingEntry(k9);
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K k9) {
        return delegate().ceilingKey(k9);
    }

    @Override // com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public abstract NavigableMap<K, V> delegate();

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return delegate().descendingKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        return delegate().descendingMap();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return delegate().firstEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K k9) {
        return delegate().floorEntry(k9);
    }

    @Override // java.util.NavigableMap
    public K floorKey(K k9) {
        return delegate().floorKey(k9);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> headMap(K k9, boolean z8) {
        return delegate().headMap(k9, z8);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K k9) {
        return delegate().higherEntry(k9);
    }

    @Override // java.util.NavigableMap
    public K higherKey(K k9) {
        return delegate().higherKey(k9);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return delegate().lastEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K k9) {
        return delegate().lowerEntry(k9);
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K k9) {
        return delegate().lowerKey(k9);
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        return delegate().navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    public Map.Entry<K, V> standardCeilingEntry(K k9) {
        return tailMap(k9, true).firstEntry();
    }

    public K standardCeilingKey(K k9) {
        return (K) Maps.keyOrNull(ceilingEntry(k9));
    }

    @Beta
    public NavigableSet<K> standardDescendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    public Map.Entry<K, V> standardFirstEntry() {
        return (Map.Entry) Iterables.getFirst(entrySet(), null);
    }

    public K standardFirstKey() {
        Map.Entry<K, V> entryFirstEntry = firstEntry();
        if (entryFirstEntry != null) {
            return entryFirstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry<K, V> standardFloorEntry(K k9) {
        return headMap(k9, true).lastEntry();
    }

    public K standardFloorKey(K k9) {
        return (K) Maps.keyOrNull(floorEntry(k9));
    }

    public SortedMap<K, V> standardHeadMap(K k9) {
        return headMap(k9, false);
    }

    public Map.Entry<K, V> standardHigherEntry(K k9) {
        return tailMap(k9, false).firstEntry();
    }

    public K standardHigherKey(K k9) {
        return (K) Maps.keyOrNull(higherEntry(k9));
    }

    public Map.Entry<K, V> standardLastEntry() {
        return (Map.Entry) Iterables.getFirst(descendingMap().entrySet(), null);
    }

    public K standardLastKey() {
        Map.Entry<K, V> entryLastEntry = lastEntry();
        if (entryLastEntry != null) {
            return entryLastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry<K, V> standardLowerEntry(K k9) {
        return headMap(k9, false).lastEntry();
    }

    public K standardLowerKey(K k9) {
        return (K) Maps.keyOrNull(lowerEntry(k9));
    }

    public Map.Entry<K, V> standardPollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entrySet().iterator());
    }

    public Map.Entry<K, V> standardPollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingMap().entrySet().iterator());
    }

    @Override // com.google.common.collect.ForwardingSortedMap
    public SortedMap<K, V> standardSubMap(K k9, K k10) {
        return subMap(k9, true, k10, false);
    }

    public SortedMap<K, V> standardTailMap(K k9) {
        return tailMap(k9, true);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> subMap(K k9, boolean z8, K k10, boolean z9) {
        return delegate().subMap(k9, z8, k10, z9);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> tailMap(K k9, boolean z8) {
        return delegate().tailMap(k9, z8);
    }
}
