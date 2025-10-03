package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        public class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            public AsMapEntries() {
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return AsMap.this.new AsMapIterator();
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                return true;
            }
        }

        public class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator;

            public AsMapIterator() {
                this.delegateIterator = AsMap.this.submap.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.this.totalSize -= this.collection.size();
                this.collection.clear();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> next = this.delegateIterator.next();
                this.collection = next.getValue();
                return AsMap.this.wrapEntry(next);
            }
        }

        public AsMap(Map<K, Collection<V>> map) {
            this.submap = map;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.clear(new AsMapIterator());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Maps.safeContainsKey(this.submap, obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(Object obj) {
            return this == obj || this.submap.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.submap.hashCode();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.submap.size();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            return this.submap.toString();
        }

        public Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object obj) {
            Collection<V> collection = (Collection) Maps.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object obj) {
            Collection<V> collectionRemove = this.submap.remove(obj);
            if (collectionRemove == null) {
                return null;
            }
            Collection<V> collectionCreateCollection = AbstractMapBasedMultimap.this.createCollection();
            collectionCreateCollection.addAll(collectionRemove);
            AbstractMapBasedMultimap.this.totalSize -= collectionRemove.size();
            collectionRemove.clear();
            return collectionCreateCollection;
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        K key = null;
        Collection<V> collection = null;
        Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

        public Itr() {
            this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.valueIterator.hasNext()) {
                Map.Entry<K, Collection<V>> next = this.keyIterator.next();
                this.key = next.getKey();
                Collection<V> value = next.getValue();
                this.collection = value;
                this.valueIterator = value.iterator();
            }
            return output(this.key, this.valueIterator.next());
        }

        public abstract T output(K k9, V v8);

        @Override // java.util.Iterator
        public void remove() {
            this.valueIterator.remove();
            if (this.collection.isEmpty()) {
                this.keyIterator.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    public class KeySet extends Maps.KeySet<K, Collection<V>> {
        public KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Iterators.clear(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return map().keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return this == obj || map().keySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return map().keySet().hashCode();
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> it = map().entrySet().iterator();
            return new Iterator<K>() { // from class: com.google.common.collect.AbstractMapBasedMultimap.KeySet.1
                Map.Entry<K, Collection<V>> entry;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public K next() {
                    Map.Entry<K, Collection<V>> entry = (Map.Entry) it.next();
                    this.entry = entry;
                    return entry.getKey();
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.entry != null);
                    Collection<V> value = this.entry.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.this.totalSize -= value.size();
                    value.clear();
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int size;
            Collection<V> collectionRemove = map().remove(obj);
            if (collectionRemove != null) {
                size = collectionRemove.size();
                collectionRemove.clear();
                AbstractMapBasedMultimap.this.totalSize -= size;
            } else {
                size = 0;
            }
            return size > 0;
        }
    }

    public class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        public NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K k9) {
            Map.Entry<K, Collection<V>> entryCeilingEntry = sortedMap().ceilingEntry(k9);
            if (entryCeilingEntry == null) {
                return null;
            }
            return wrapEntry(entryCeilingEntry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k9) {
            return sortedMap().ceilingKey(k9);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(sortedMap().descendingMap());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> entryFirstEntry = sortedMap().firstEntry();
            if (entryFirstEntry == null) {
                return null;
            }
            return wrapEntry(entryFirstEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K k9) {
            Map.Entry<K, Collection<V>> entryFloorEntry = sortedMap().floorEntry(k9);
            if (entryFloorEntry == null) {
                return null;
            }
            return wrapEntry(entryFloorEntry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k9) {
            return sortedMap().floorKey(k9);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap headMap(Object obj) {
            return headMap((NavigableAsMap) obj);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K k9) {
            Map.Entry<K, Collection<V>> entryHigherEntry = sortedMap().higherEntry(k9);
            if (entryHigherEntry == null) {
                return null;
            }
            return wrapEntry(entryHigherEntry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k9) {
            return sortedMap().higherKey(k9);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> entryLastEntry = sortedMap().lastEntry();
            if (entryLastEntry == null) {
                return null;
            }
            return wrapEntry(entryLastEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K k9) {
            Map.Entry<K, Collection<V>> entryLowerEntry = sortedMap().lowerEntry(k9);
            if (entryLowerEntry == null) {
                return null;
            }
            return wrapEntry(entryLowerEntry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k9) {
            return sortedMap().lowerKey(k9);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        public Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> it) {
            if (!it.hasNext()) {
                return null;
            }
            Map.Entry<K, Collection<V>> next = it.next();
            Collection<V> collectionCreateCollection = AbstractMapBasedMultimap.this.createCollection();
            collectionCreateCollection.addAll(next.getValue());
            it.remove();
            return Maps.immutableEntry(next.getKey(), AbstractMapBasedMultimap.unmodifiableCollectionSubclass(collectionCreateCollection));
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap tailMap(Object obj) {
            return tailMap((NavigableAsMap) obj);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k9) {
            return headMap(k9, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k9, K k10) {
            return subMap(k9, true, k10, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k9) {
            return tailMap(k9, true);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        public NavigableSet<K> createKeySet() {
            return new NavigableKeySet(sortedMap());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k9, boolean z8) {
            return new NavigableAsMap(sortedMap().headMap(k9, z8));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k9, boolean z8, K k10, boolean z9) {
            return new NavigableAsMap(sortedMap().subMap(k9, z8, k10, z9));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k9, boolean z8) {
            return new NavigableAsMap(sortedMap().tailMap(k9, z8));
        }
    }

    public class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        public NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k9) {
            return sortedMap().ceilingKey(k9);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(sortedMap().descendingMap());
        }

        @Override // java.util.NavigableSet
        public K floor(K k9) {
            return sortedMap().floorKey(k9);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet headSet(Object obj) {
            return headSet((NavigableKeySet) obj);
        }

        @Override // java.util.NavigableSet
        public K higher(K k9) {
            return sortedMap().higherKey(k9);
        }

        @Override // java.util.NavigableSet
        public K lower(K k9) {
            return sortedMap().lowerKey(k9);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.pollNext(descendingIterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet tailSet(Object obj) {
            return tailSet((NavigableKeySet) obj);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> headSet(K k9) {
            return headSet(k9, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> subSet(K k9, K k10) {
            return subSet(k9, true, k10, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> tailSet(K k9) {
            return tailSet(k9, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k9, boolean z8) {
            return new NavigableKeySet(sortedMap().headMap(k9, z8));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k9, boolean z8, K k10, boolean z9) {
            return new NavigableKeySet(sortedMap().subMap(k9, z8, k10, z9));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k9, boolean z8) {
            return new NavigableKeySet(sortedMap().tailMap(k9, z8));
        }
    }

    public class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        public RandomAccessWrappedList(K k9, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k9, list, wrappedCollection);
        }
    }

    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        SortedSet<K> sortedKeySet;

        public SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return sortedMap().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k9) {
            return new SortedAsMap(sortedMap().headMap(k9));
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        public SortedMap<K, Collection<V>> subMap(K k9, K k10) {
            return new SortedAsMap(sortedMap().subMap(k9, k10));
        }

        public SortedMap<K, Collection<V>> tailMap(K k9) {
            return new SortedAsMap(sortedMap().tailMap(k9));
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<K> createKeySet() {
            return new SortedKeySet(sortedMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> sortedSetCreateKeySet = createKeySet();
            this.sortedKeySet = sortedSetCreateKeySet;
            return sortedSetCreateKeySet;
        }
    }

    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        public SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return sortedMap().firstKey();
        }

        public SortedSet<K> headSet(K k9) {
            return new SortedKeySet(sortedMap().headMap(k9));
        }

        @Override // java.util.SortedSet
        public K last() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) super.map();
        }

        public SortedSet<K> subSet(K k9, K k10) {
            return new SortedKeySet(sortedMap().subMap(k9, k10));
        }

        public SortedSet<K> tailSet(K k9) {
            return new SortedKeySet(sortedMap().tailMap(k9));
        }
    }

    public class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        public WrappedNavigableSet(K k9, NavigableSet<V> navigableSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k9, navigableSet, wrappedCollection);
        }

        private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
            return new WrappedNavigableSet(this.key, navigableSet, getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v8) {
            return getSortedSetDelegate().ceiling(v8);
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(getSortedSetDelegate().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return wrap(getSortedSetDelegate().descendingSet());
        }

        @Override // java.util.NavigableSet
        public V floor(V v8) {
            return getSortedSetDelegate().floor(v8);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V v8, boolean z8) {
            return wrap(getSortedSetDelegate().headSet(v8, z8));
        }

        @Override // java.util.NavigableSet
        public V higher(V v8) {
            return getSortedSetDelegate().higher(v8);
        }

        @Override // java.util.NavigableSet
        public V lower(V v8) {
            return getSortedSetDelegate().lower(v8);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.pollNext(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V v8, boolean z8, V v9, boolean z9) {
            return wrap(getSortedSetDelegate().subSet(v8, z8, v9, z9));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V v8, boolean z8) {
            return wrap(getSortedSetDelegate().tailSet(v8, z8));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet
        public NavigableSet<V> getSortedSetDelegate() {
            return (NavigableSet) super.getSortedSetDelegate();
        }
    }

    public class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        public WrappedSet(K k9, Set<V> set) {
            super(k9, set, null);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean zRemoveAllImpl = Sets.removeAllImpl((Set<?>) this.delegate, collection);
            if (zRemoveAllImpl) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                removeIfEmpty();
            }
            return zRemoveAllImpl;
        }
    }

    public class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        public WrappedSortedSet(K k9, SortedSet<V> sortedSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k9, sortedSet, wrappedCollection);
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return getSortedSetDelegate().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            refreshIfEmpty();
            return getSortedSetDelegate().first();
        }

        public SortedSet<V> getSortedSetDelegate() {
            return (SortedSet) getDelegate();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V v8) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), getSortedSetDelegate().headSet(v8), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.SortedSet
        public V last() {
            refreshIfEmpty();
            return getSortedSetDelegate().last();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V v8, V v9) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), getSortedSetDelegate().subSet(v8, v9), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V v8) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), getSortedSetDelegate().tailSet(v8), getAncestor() == null ? this : getAncestor());
        }
    }

    public AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
        Preconditions.checkArgument(map.isEmpty());
        this.map = map;
    }

    public static /* synthetic */ int access$208(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i9 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i9 + 1;
        return i9;
    }

    public static /* synthetic */ int access$210(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i9 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i9 - 1;
        return i9;
    }

    private Collection<V> getOrCreateCollection(K k9) {
        Collection<V> collection = this.map.get(k9);
        if (collection != null) {
            return collection;
        }
        Collection<V> collectionCreateCollection = createCollection(k9);
        this.map.put(k9, collectionCreateCollection);
        return collectionCreateCollection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.safeRemove(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    public static <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet) collection) : collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? Collections.unmodifiableSet((Set) collection) : collection instanceof List ? Collections.unmodifiableList((List) collection) : Collections.unmodifiableCollection(collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<V> wrapList(K k9, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        return list instanceof RandomAccess ? new RandomAccessWrappedList(k9, list, wrappedCollection) : new WrappedList(k9, list, wrappedCollection);
    }

    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        Iterator<Collection<V>> it = this.map.values().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        Map<K, Collection<V>> map = this.map;
        return map instanceof NavigableMap ? new NavigableAsMap((NavigableMap) this.map) : map instanceof SortedMap ? new SortedAsMap((SortedMap) this.map) : new AsMap(this.map);
    }

    public abstract Collection<V> createCollection();

    public Collection<V> createCollection(K k9) {
        return createCollection();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        Map<K, Collection<V>> map = this.map;
        return map instanceof NavigableMap ? new NavigableKeySet((NavigableMap) this.map) : map instanceof SortedMap ? new SortedKeySet((SortedMap) this.map) : new KeySet(this.map);
    }

    public Collection<V> createUnmodifiableEmptyCollection() {
        return unmodifiableCollectionSubclass(createCollection());
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.AbstractMapBasedMultimap.2
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            public /* bridge */ /* synthetic */ Object output(Object obj, Object obj2) {
                return output((C36232) obj, obj2);
            }

            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            public Map.Entry<K, V> output(K k9, V v8) {
                return Maps.immutableEntry(k9, v8);
            }
        };
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(K k9) {
        Collection<V> collectionCreateCollection = this.map.get(k9);
        if (collectionCreateCollection == null) {
            collectionCreateCollection = createCollection(k9);
        }
        return wrapCollection(k9, collectionCreateCollection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(K k9, V v8) {
        Collection<V> collection = this.map.get(k9);
        if (collection != null) {
            if (!collection.add(v8)) {
                return false;
            }
            this.totalSize++;
            return true;
        }
        Collection<V> collectionCreateCollection = createCollection(k9);
        if (!collectionCreateCollection.add(v8)) {
            throw new AssertionError("New Collection violated the Collection spec");
        }
        this.totalSize++;
        this.map.put(k9, collectionCreateCollection);
        return true;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(Object obj) {
        Collection<V> collectionRemove = this.map.remove(obj);
        if (collectionRemove == null) {
            return createUnmodifiableEmptyCollection();
        }
        Collection<V> collectionCreateCollection = createCollection();
        collectionCreateCollection.addAll(collectionRemove);
        this.totalSize -= collectionRemove.size();
        collectionRemove.clear();
        return unmodifiableCollectionSubclass(collectionCreateCollection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> replaceValues(K k9, Iterable<? extends V> iterable) {
        Iterator<? extends V> it = iterable.iterator();
        if (!it.hasNext()) {
            return removeAll(k9);
        }
        Collection<V> orCreateCollection = getOrCreateCollection(k9);
        Collection<V> collectionCreateCollection = createCollection();
        collectionCreateCollection.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it.hasNext()) {
            if (orCreateCollection.add(it.next())) {
                this.totalSize++;
            }
        }
        return unmodifiableCollectionSubclass(collectionCreateCollection);
    }

    public final void setMap(Map<K, Collection<V>> map) {
        this.map = map;
        this.totalSize = 0;
        for (Collection<V> collection : map.values()) {
            Preconditions.checkArgument(!collection.isEmpty());
            this.totalSize += collection.size();
        }
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<V> valueIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<V>() { // from class: com.google.common.collect.AbstractMapBasedMultimap.1
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            public V output(K k9, V v8) {
                return v8;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    public Collection<V> wrapCollection(K k9, Collection<V> collection) {
        return collection instanceof NavigableSet ? new WrappedNavigableSet(k9, (NavigableSet) collection, null) : collection instanceof SortedSet ? new WrappedSortedSet(k9, (SortedSet) collection, null) : collection instanceof Set ? new WrappedSet(k9, (Set) collection) : collection instanceof List ? wrapList(k9, (List) collection, null) : new WrappedCollection(k9, collection, null);
    }

    public class WrappedCollection extends AbstractCollection<V> {
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        final K key;

        public WrappedCollection(K k9, Collection<V> collection, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            this.key = k9;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            this.ancestorDelegate = wrappedCollection == null ? null : wrappedCollection.getDelegate();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V v8) {
            refreshIfEmpty();
            boolean zIsEmpty = this.delegate.isEmpty();
            boolean zAdd = this.delegate.add(v8);
            if (zAdd) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (zIsEmpty) {
                    addToMap();
                }
            }
            return zAdd;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean zAddAll = this.delegate.addAll(collection);
            if (zAddAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    addToMap();
                }
            }
            return zAddAll;
        }

        public void addToMap() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.addToMap();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            int size = size();
            if (size == 0) {
                return;
            }
            this.delegate.clear();
            AbstractMapBasedMultimap.this.totalSize -= size;
            removeIfEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            refreshIfEmpty();
            return this.delegate.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            refreshIfEmpty();
            return this.delegate.containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        public AbstractMapBasedMultimap<K, V>.WrappedCollection getAncestor() {
            return this.ancestor;
        }

        public Collection<V> getDelegate() {
            return this.delegate;
        }

        K getKey() {
            return this.key;
        }

        @Override // java.util.Collection
        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        public void refreshIfEmpty() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                if (this.ancestor.getDelegate() != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else {
                if (!this.delegate.isEmpty() || (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.key)) == null) {
                    return;
                }
                this.delegate = collection;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            refreshIfEmpty();
            boolean zRemove = this.delegate.remove(obj);
            if (zRemove) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                removeIfEmpty();
            }
            return zRemove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean zRemoveAll = this.delegate.removeAll(collection);
            if (zRemoveAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                removeIfEmpty();
            }
            return zRemoveAll;
        }

        public void removeIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean zRetainAll = this.delegate.retainAll(collection);
            if (zRetainAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                removeIfEmpty();
            }
            return zRetainAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        public class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate;

            public WrappedIterator() {
                Collection<V> collection = WrappedCollection.this.delegate;
                this.originalDelegate = collection;
                this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(collection);
            }

            public Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                WrappedCollection.this.removeIfEmpty();
            }

            public void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate != this.originalDelegate) {
                    throw new ConcurrentModificationException();
                }
            }

            public WrappedIterator(Iterator<V> it) {
                this.originalDelegate = WrappedCollection.this.delegate;
                this.delegateIterator = it;
            }
        }
    }

    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        public class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedCollection.WrappedIterator implements ListIterator<V> {
            public WrappedListIterator() {
                super();
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) getDelegateIterator();
            }

            @Override // java.util.ListIterator
            public void add(V v8) {
                boolean zIsEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v8);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (zIsEmpty) {
                    WrappedList.this.addToMap();
                }
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return getDelegateListIterator().previous();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v8) {
                getDelegateListIterator().set(v8);
            }

            public WrappedListIterator(int i9) {
                super(WrappedList.this.getListDelegate().listIterator(i9));
            }
        }

        public WrappedList(K k9, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k9, list, wrappedCollection);
        }

        @Override // java.util.List
        public void add(int i9, V v8) {
            refreshIfEmpty();
            boolean zIsEmpty = getDelegate().isEmpty();
            getListDelegate().add(i9, v8);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (zIsEmpty) {
                addToMap();
            }
        }

        @Override // java.util.List
        public boolean addAll(int i9, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean zAddAll = getListDelegate().addAll(i9, collection);
            if (zAddAll) {
                int size2 = getDelegate().size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    addToMap();
                }
            }
            return zAddAll;
        }

        @Override // java.util.List
        public V get(int i9) {
            refreshIfEmpty();
            return getListDelegate().get(i9);
        }

        public List<V> getListDelegate() {
            return (List) getDelegate();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        @Override // java.util.List
        public V remove(int i9) {
            refreshIfEmpty();
            V vRemove = getListDelegate().remove(i9);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            removeIfEmpty();
            return vRemove;
        }

        @Override // java.util.List
        public V set(int i9, V v8) {
            refreshIfEmpty();
            return getListDelegate().set(i9, v8);
        }

        @Override // java.util.List
        public List<V> subList(int i9, int i10) {
            refreshIfEmpty();
            return AbstractMapBasedMultimap.this.wrapList(getKey(), getListDelegate().subList(i9, i10), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i9) {
            refreshIfEmpty();
            return new WrappedListIterator(i9);
        }
    }
}
