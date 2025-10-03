package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class AbstractNavigableMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> implements NavigableMap<K, V> {

    public final class DescendingMap extends Maps.DescendingMap<K, V> {
        private DescendingMap() {
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return AbstractNavigableMap.this.descendingEntryIterator();
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        public NavigableMap<K, V> forward() {
            return AbstractNavigableMap.this;
        }
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K k9) {
        return tailMap(k9, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K k9) {
        return (K) Maps.keyOrNull(ceilingEntry(k9));
    }

    public abstract Iterator<Map.Entry<K, V>> descendingEntryIterator();

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        return new DescendingMap();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return (Map.Entry) Iterators.getNext(entryIterator(), null);
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        Map.Entry<K, V> entryFirstEntry = firstEntry();
        if (entryFirstEntry != null) {
            return entryFirstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K k9) {
        return headMap(k9, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K floorKey(K k9) {
        return (K) Maps.keyOrNull(floorEntry(k9));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public abstract V get(Object obj);

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> headMap(K k9) {
        return headMap(k9, false);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K k9) {
        return tailMap(k9, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K higherKey(K k9) {
        return (K) Maps.keyOrNull(higherEntry(k9));
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set<K> keySet() {
        return navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return (Map.Entry) Iterators.getNext(descendingEntryIterator(), null);
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        Map.Entry<K, V> entryLastEntry = lastEntry();
        if (entryLastEntry != null) {
            return entryLastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K k9) {
        return headMap(k9, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K k9) {
        return (K) Maps.keyOrNull(lowerEntry(k9));
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        return new Maps.NavigableKeySet(this);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entryIterator());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingEntryIterator());
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> subMap(K k9, K k10) {
        return subMap(k9, true, k10, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> tailMap(K k9) {
        return tailMap(k9, true);
    }
}
