package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

@GwtIncompatible
/* loaded from: classes2.dex */
class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final long CLEANUP_EXECUTOR_DELAY_SECS = 60;
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final WeakValueReference<Object, Object, DummyInternalEntry> UNSET_WEAK_VALUE_REFERENCE = new WeakValueReference<Object, Object, DummyInternalEntry>() { // from class: com.google.common.collect.MapMakerInternalMap.1
        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public void clear() {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry dummyInternalEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public Object get() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public DummyInternalEntry getEntry() {
            return null;
        }
    };
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient InternalEntryHelper<K, V, E, S> entryHelper;
    transient Set<Map.Entry<K, V>> entrySet;
    final Equivalence<Object> keyEquivalence;
    transient Set<K> keySet;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V, E, S>[] segments;
    transient Collection<V> values;

    public static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {
        final int hash;
        final K key;
        final E next;

        public AbstractStrongKeyEntry(K k9, int i9, E e9) {
            this.key = k9;
            this.hash = i9;
            this.next = e9;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public E getNext() {
            return this.next;
        }
    }

    public static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {
        final int hash;
        final E next;

        public AbstractWeakKeyEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, E e9) {
            super(k9, referenceQueue);
            this.hash = i9;
            this.next = e9;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public K getKey() {
            return get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public E getNext() {
            return this.next;
        }
    }

    public static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?, ?, ?>> mapReference;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap) {
            this.mapReference = new WeakReference<>(mapMakerInternalMap);
        }

        @Override // java.lang.Runnable
        public void run() {
            MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap = this.mapReference.get();
            if (mapMakerInternalMap == null) {
                throw new CancellationException();
            }
            for (Segment<?, ?, E, S> segment : mapMakerInternalMap.segments) {
                segment.runCleanup();
            }
        }
    }

    public static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public int getHash() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public Object getKey() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public Object getValue() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }
    }

    public final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        public EntryIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public final class EntrySet extends SafeToArraySet<Map.Entry<K, V>> {
        public EntrySet() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = MapMakerInternalMap.this.get(key)) != null && MapMakerInternalMap.this.valueEquivalence().equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && MapMakerInternalMap.this.remove(key, entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    public abstract class HashIterator<T> implements Iterator<T> {
        Segment<K, V, E, S> currentSegment;
        AtomicReferenceArray<E> currentTable;
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry lastReturned;
        E nextEntry;
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            advance();
        }

        public final void advance() {
            this.nextExternal = null;
            if (nextInChain() || nextInTable()) {
                return;
            }
            while (true) {
                int i9 = this.nextSegmentIndex;
                if (i9 < 0) {
                    return;
                }
                Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.segments;
                this.nextSegmentIndex = i9 - 1;
                Segment<K, V, E, S> segment = segmentArr[i9];
                this.currentSegment = segment;
                if (segment.count != 0) {
                    this.currentTable = this.currentSegment.table;
                    this.nextTableIndex = r0.length() - 1;
                    if (nextInTable()) {
                        return;
                    }
                }
            }
        }

        public boolean advanceTo(E e9) {
            try {
                Object key = e9.getKey();
                Object liveValue = MapMakerInternalMap.this.getLiveValue(e9);
                if (liveValue == null) {
                    this.currentSegment.postReadCleanup();
                    return false;
                }
                this.nextExternal = new WriteThroughEntry(key, liveValue);
                this.currentSegment.postReadCleanup();
                return true;
            } catch (Throwable th) {
                this.currentSegment.postReadCleanup();
                throw th;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextExternal != null;
        }

        @Override // java.util.Iterator
        public abstract T next();

        public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextEntry() {
            MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = writeThroughEntry;
            advance();
            return this.lastReturned;
        }

        public boolean nextInChain() {
            E e9 = this.nextEntry;
            if (e9 == null) {
                return false;
            }
            while (true) {
                this.nextEntry = (E) e9.getNext();
                E e10 = this.nextEntry;
                if (e10 == null) {
                    return false;
                }
                if (advanceTo(e10)) {
                    return true;
                }
                e9 = this.nextEntry;
            }
        }

        public boolean nextInTable() {
            while (true) {
                int i9 = this.nextTableIndex;
                if (i9 < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i9 - 1;
                E e9 = atomicReferenceArray.get(i9);
                this.nextEntry = e9;
                if (e9 != null && (advanceTo(e9) || nextInChain())) {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.lastReturned != null);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    public interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    public interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s8, E e9, E e10);

        Strength keyStrength();

        E newEntry(S s8, K k9, int i9, E e9);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i9, int i10);

        void setValue(S s8, E e9, V v8);

        Strength valueStrength();
    }

    public final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        public KeyIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    public final class KeySet extends SafeToArraySet<K> {
        public KeySet() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }
    }

    public static abstract class SafeToArraySet<E> extends AbstractSet<E> {
        private SafeToArraySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <E> E[] toArray(E[] eArr) {
            return (E[]) MapMakerInternalMap.toArrayList(this).toArray(eArr);
        }
    }

    public static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        public SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i9, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i9, concurrentMap);
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeMap();
            readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }
    }

    public enum Strength {
        STRONG { // from class: com.google.common.collect.MapMakerInternalMap.Strength.1
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        WEAK { // from class: com.google.common.collect.MapMakerInternalMap.Strength.2
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        public abstract Equivalence<Object> defaultEquivalence();
    }

    public static final class StrongKeyDummyValueEntry<K> extends AbstractStrongKeyEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> {

        public static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public static <K> Helper<K> instance() {
                return (Helper<K>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((StrongKeyDummyValueSegment<StrongKeyDummyValueSegment<K>>) segment, (StrongKeyDummyValueSegment<K>) obj, i9, (StrongKeyDummyValueEntry<StrongKeyDummyValueSegment<K>>) internalEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public void setValue(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry2) {
                return strongKeyDummyValueEntry.copy(strongKeyDummyValueEntry2);
            }

            public StrongKeyDummyValueEntry<K> newEntry(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K k9, int i9, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                return new StrongKeyDummyValueEntry<>(k9, i9, strongKeyDummyValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i9, int i10) {
                return new StrongKeyDummyValueSegment<>(mapMakerInternalMap, i9, i10);
            }
        }

        public StrongKeyDummyValueEntry(K k9, int i9, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
            super(k9, i9, strongKeyDummyValueEntry);
        }

        public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
            return new StrongKeyDummyValueEntry<>(this.key, this.hash, strongKeyDummyValueEntry);
        }

        public void setValue(MapMaker.Dummy dummy) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    public static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
        public StrongKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyDummyValueSegment<K> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (StrongKeyDummyValueEntry) internalEntry;
        }
    }

    public static final class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        private volatile V value;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return (Helper<K, V>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((StrongKeyStrongValueSegment<StrongKeyStrongValueSegment<K, V>, V>) segment, (StrongKeyStrongValueSegment<K, V>) obj, i9, (StrongKeyStrongValueEntry<StrongKeyStrongValueSegment<K, V>, V>) internalEntry);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((StrongKeyStrongValueSegment<K, StrongKeyStrongValueEntry<K, V>>) segment, (StrongKeyStrongValueEntry<K, StrongKeyStrongValueEntry<K, V>>) internalEntry, (StrongKeyStrongValueEntry<K, V>) obj);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2) {
                return strongKeyStrongValueEntry.copy(strongKeyStrongValueEntry2);
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k9, int i9, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                return new StrongKeyStrongValueEntry<>(k9, i9, strongKeyStrongValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i9, i10);
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, V v8) {
                strongKeyStrongValueEntry.setValue(v8);
            }
        }

        public StrongKeyStrongValueEntry(K k9, int i9, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
            super(k9, i9, strongKeyStrongValueEntry);
            this.value = null;
        }

        public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
            StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2 = new StrongKeyStrongValueEntry<>(this.key, this.hash, strongKeyStrongValueEntry);
            strongKeyStrongValueEntry2.value = this.value;
            return strongKeyStrongValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.value;
        }

        public void setValue(V v8) {
            this.value = v8;
        }
    }

    public static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        public StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyStrongValueSegment<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyStrongValueEntry) internalEntry;
        }
    }

    public static final class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        private volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return (Helper<K, V>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.STRONG;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((StrongKeyWeakValueSegment<StrongKeyWeakValueSegment<K, V>, V>) segment, (StrongKeyWeakValueSegment<K, V>) obj, i9, (StrongKeyWeakValueEntry<StrongKeyWeakValueSegment<K, V>, V>) internalEntry);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((StrongKeyWeakValueSegment<K, StrongKeyWeakValueEntry<K, V>>) segment, (StrongKeyWeakValueEntry<K, StrongKeyWeakValueEntry<K, V>>) internalEntry, (StrongKeyWeakValueEntry<K, V>) obj);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.WEAK;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2) {
                if (Segment.isCollected(strongKeyWeakValueEntry)) {
                    return null;
                }
                return strongKeyWeakValueEntry.copy(((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues, strongKeyWeakValueEntry2);
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k9, int i9, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                return new StrongKeyWeakValueEntry<>(k9, i9, strongKeyWeakValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i9, i10);
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, V v8) {
                strongKeyWeakValueEntry.setValue(v8, ((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues);
            }
        }

        public StrongKeyWeakValueEntry(K k9, int i9, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
            super(k9, i9, strongKeyWeakValueEntry);
            this.valueReference = MapMakerInternalMap.unsetWeakValueReference();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public void clearValue() {
            this.valueReference.clear();
        }

        public StrongKeyWeakValueEntry<K, V> copy(ReferenceQueue<V> referenceQueue, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
            StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2 = new StrongKeyWeakValueEntry<>(this.key, this.hash, strongKeyWeakValueEntry);
            strongKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue, strongKeyWeakValueEntry2);
            return strongKeyWeakValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.valueReference.get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        public void setValue(V v8, ReferenceQueue<V> referenceQueue) {
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, v8, this);
            weakValueReference.clear();
        }
    }

    public static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<V> queueForValues;

        public StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
            this.queueForValues = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v8) {
            return new WeakValueReferenceImpl(this.queueForValues, v8, castForTesting((InternalEntry) internalEntry));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyWeakValueSegment<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntryCastForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference weakValueReference2 = ((StrongKeyWeakValueEntry) strongKeyWeakValueEntryCastForTesting).valueReference;
            ((StrongKeyWeakValueEntry) strongKeyWeakValueEntryCastForTesting).valueReference = weakValueReference;
            weakValueReference2.clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyWeakValueEntry) internalEntry;
        }
    }

    public interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    public final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        public ValueIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    public final class Values extends AbstractCollection<V> {
        public Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MapMakerInternalMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) MapMakerInternalMap.toArrayList(this).toArray(eArr);
        }
    }

    public static final class WeakKeyDummyValueEntry<K> extends AbstractWeakKeyEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> {

        public static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public static <K> Helper<K> instance() {
                return (Helper<K>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((WeakKeyDummyValueSegment<WeakKeyDummyValueSegment<K>>) segment, (WeakKeyDummyValueSegment<K>) obj, i9, (WeakKeyDummyValueEntry<WeakKeyDummyValueSegment<K>>) internalEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public void setValue(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyDummyValueEntry<K> copy(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry2) {
                if (weakKeyDummyValueEntry.getKey() == null) {
                    return null;
                }
                return weakKeyDummyValueEntry.copy(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, weakKeyDummyValueEntry2);
            }

            public WeakKeyDummyValueEntry<K> newEntry(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, K k9, int i9, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                return new WeakKeyDummyValueEntry<>(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, k9, i9, weakKeyDummyValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i9, int i10) {
                return new WeakKeyDummyValueSegment<>(mapMakerInternalMap, i9, i10);
            }
        }

        public WeakKeyDummyValueEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
            super(referenceQueue, k9, i9, weakKeyDummyValueEntry);
        }

        public WeakKeyDummyValueEntry<K> copy(ReferenceQueue<K> referenceQueue, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
            return new WeakKeyDummyValueEntry<>(referenceQueue, getKey(), this.hash, weakKeyDummyValueEntry);
        }

        public void setValue(MapMaker.Dummy dummy) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }
    }

    public static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
        private final ReferenceQueue<K> queueForKeys;

        public WeakKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
            this.queueForKeys = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyDummyValueSegment<K> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (WeakKeyDummyValueEntry) internalEntry;
        }
    }

    public static final class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        private volatile V value;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return (Helper<K, V>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((WeakKeyStrongValueSegment<WeakKeyStrongValueSegment<K, V>, V>) segment, (WeakKeyStrongValueSegment<K, V>) obj, i9, (WeakKeyStrongValueEntry<WeakKeyStrongValueSegment<K, V>, V>) internalEntry);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((WeakKeyStrongValueSegment<K, WeakKeyStrongValueEntry<K, V>>) segment, (WeakKeyStrongValueEntry<K, WeakKeyStrongValueEntry<K, V>>) internalEntry, (WeakKeyStrongValueEntry<K, V>) obj);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2) {
                if (weakKeyStrongValueEntry.getKey() == null) {
                    return null;
                }
                return weakKeyStrongValueEntry.copy(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, weakKeyStrongValueEntry2);
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k9, int i9, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                return new WeakKeyStrongValueEntry<>(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, k9, i9, weakKeyStrongValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i9, i10);
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, V v8) {
                weakKeyStrongValueEntry.setValue(v8);
            }
        }

        public WeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
            super(referenceQueue, k9, i9, weakKeyStrongValueEntry);
            this.value = null;
        }

        public WeakKeyStrongValueEntry<K, V> copy(ReferenceQueue<K> referenceQueue, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
            WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2 = new WeakKeyStrongValueEntry<>(referenceQueue, getKey(), this.hash, weakKeyStrongValueEntry);
            weakKeyStrongValueEntry2.setValue(this.value);
            return weakKeyStrongValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.value;
        }

        public void setValue(V v8) {
            this.value = v8;
        }
    }

    public static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys;

        public WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
            this.queueForKeys = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyStrongValueSegment<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyStrongValueEntry) internalEntry;
        }
    }

    public static final class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        private volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference;

        public static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            public static <K, V> Helper<K, V> instance() {
                return (Helper<K, V>) INSTANCE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength keyStrength() {
                return Strength.WEAK;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ InternalEntry newEntry(Segment segment, Object obj, int i9, InternalEntry internalEntry) {
                return newEntry((WeakKeyWeakValueSegment<WeakKeyWeakValueSegment<K, V>, V>) segment, (WeakKeyWeakValueSegment<K, V>) obj, i9, (WeakKeyWeakValueEntry<WeakKeyWeakValueSegment<K, V>, V>) internalEntry);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public /* bridge */ /* synthetic */ void setValue(Segment segment, InternalEntry internalEntry, Object obj) {
                setValue((WeakKeyWeakValueSegment<K, WeakKeyWeakValueEntry<K, V>>) segment, (WeakKeyWeakValueEntry<K, WeakKeyWeakValueEntry<K, V>>) internalEntry, (WeakKeyWeakValueEntry<K, V>) obj);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public Strength valueStrength() {
                return Strength.WEAK;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2) {
                if (weakKeyWeakValueEntry.getKey() == null || Segment.isCollected(weakKeyWeakValueEntry)) {
                    return null;
                }
                return weakKeyWeakValueEntry.copy(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues, weakKeyWeakValueEntry2);
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k9, int i9, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                return new WeakKeyWeakValueEntry<>(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, k9, i9, weakKeyWeakValueEntry);
            }

            @Override // com.google.common.collect.MapMakerInternalMap.InternalEntryHelper
            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i9, i10);
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, V v8) {
                weakKeyWeakValueEntry.setValue(v8, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues);
            }
        }

        public WeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
            super(referenceQueue, k9, i9, weakKeyWeakValueEntry);
            this.valueReference = MapMakerInternalMap.unsetWeakValueReference();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public void clearValue() {
            this.valueReference.clear();
        }

        public WeakKeyWeakValueEntry<K, V> copy(ReferenceQueue<K> referenceQueue, ReferenceQueue<V> referenceQueue2, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
            WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2 = new WeakKeyWeakValueEntry<>(referenceQueue, getKey(), this.hash, weakKeyWeakValueEntry);
            weakKeyWeakValueEntry2.valueReference = this.valueReference.copyFor(referenceQueue2, weakKeyWeakValueEntry2);
            return weakKeyWeakValueEntry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.InternalEntry
        public V getValue() {
            return this.valueReference.get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueEntry
        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        public void setValue(V v8, ReferenceQueue<V> referenceQueue) {
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> weakValueReference = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(referenceQueue, v8, this);
            weakValueReference.clear();
        }
    }

    public static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys;
        private final ReferenceQueue<V> queueForValues;

        public WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i9, int i10) {
            super(mapMakerInternalMap, i9, i10);
            this.queueForKeys = new ReferenceQueue<>();
            this.queueForValues = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v8) {
            return new WeakValueReferenceImpl(this.queueForValues, v8, castForTesting((InternalEntry) internalEntry));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyWeakValueSegment<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntryCastForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference weakValueReference2 = ((WeakKeyWeakValueEntry) weakKeyWeakValueEntryCastForTesting).valueReference;
            ((WeakKeyWeakValueEntry) weakKeyWeakValueEntryCastForTesting).valueReference = weakValueReference;
            weakValueReference2.clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyWeakValueEntry) internalEntry;
        }
    }

    public interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        void clearValue();

        WeakValueReference<K, V, E> getValueReference();
    }

    public interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e9);

        V get();

        E getEntry();
    }

    public static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {

        @Weak
        final E entry;

        public WeakValueReferenceImpl(ReferenceQueue<V> referenceQueue, V v8, E e9) {
            super(v8, referenceQueue);
            this.entry = e9;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e9) {
            return new WeakValueReferenceImpl(referenceQueue, get(), e9);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakValueReference
        public E getEntry() {
            return this.entry;
        }
    }

    public final class WriteThroughEntry extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        public WriteThroughEntry(K k9, V v8) {
            this.key = k9;
            this.value = v8;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v8) {
            V v9 = (V) MapMakerInternalMap.this.put(this.key, v8);
            this.value = v8;
            return v9;
        }
    }

    private MapMakerInternalMap(MapMaker mapMaker, InternalEntryHelper<K, V, E, S> internalEntryHelper) {
        this.concurrencyLevel = Math.min(mapMaker.getConcurrencyLevel(), 65536);
        this.keyEquivalence = mapMaker.getKeyEquivalence();
        this.entryHelper = internalEntryHelper;
        int iMin = Math.min(mapMaker.getInitialCapacity(), 1073741824);
        int i9 = 0;
        int i10 = 1;
        int i11 = 0;
        int i12 = 1;
        while (i12 < this.concurrencyLevel) {
            i11++;
            i12 <<= 1;
        }
        this.segmentShift = 32 - i11;
        this.segmentMask = i12 - 1;
        this.segments = newSegmentArray(i12);
        int i13 = iMin / i12;
        while (i10 < (i12 * i13 < iMin ? i13 + 1 : i13)) {
            i10 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.segments;
            if (i9 >= segmentArr.length) {
                return;
            }
            segmentArr[i9] = createSegment(i10, -1);
            i9++;
        }
    }

    public static <K, V> MapMakerInternalMap<K, V, ? extends InternalEntry<K, V, ?>, ?> create(MapMaker mapMaker) {
        Strength keyStrength = mapMaker.getKeyStrength();
        Strength strength = Strength.STRONG;
        if (keyStrength == strength && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == strength && mapMaker.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyWeakValueEntry.Helper.instance());
        }
        Strength keyStrength2 = mapMaker.getKeyStrength();
        Strength strength2 = Strength.WEAK;
        if (keyStrength2 == strength2 && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == strength2 && mapMaker.getValueStrength() == strength2) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyWeakValueEntry.Helper.instance());
        }
        throw new AssertionError();
    }

    public static <K> MapMakerInternalMap<K, MapMaker.Dummy, ? extends InternalEntry<K, MapMaker.Dummy, ?>, ?> createWithDummyValues(MapMaker mapMaker) {
        Strength keyStrength = mapMaker.getKeyStrength();
        Strength strength = Strength.STRONG;
        if (keyStrength == strength && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyDummyValueEntry.Helper.instance());
        }
        Strength keyStrength2 = mapMaker.getKeyStrength();
        Strength strength2 = Strength.WEAK;
        if (keyStrength2 == strength2 && mapMaker.getValueStrength() == strength) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyDummyValueEntry.Helper.instance());
        }
        if (mapMaker.getValueStrength() == strength2) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    public static int rehash(int i9) {
        int i10 = i9 + ((i9 << 15) ^ (-12931));
        int i11 = i10 ^ (i10 >>> 10);
        int i12 = i11 + (i11 << 3);
        int i13 = i12 ^ (i12 >>> 6);
        int i14 = i13 + (i13 << 2) + (i13 << 14);
        return i14 ^ (i14 >>> 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    public static <K, V, E extends InternalEntry<K, V, E>> WeakValueReference<K, V, E> unsetWeakValueReference() {
        return (WeakValueReference<K, V, E>) UNSET_WEAK_VALUE_REFERENCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V, E, S> segment : this.segments) {
            segment.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).containsKey(obj, iHash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    /* JADX WARN: Type inference failed for: r13v3 */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        boolean z8 = false;
        if (obj == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j9 = -1;
        int i9 = 0;
        while (i9 < 3) {
            int length = segmentArr.length;
            long j10 = 0;
            for (?? r10 = z8; r10 < length; r10++) {
                Segment<K, V, E, S> segment = segmentArr[r10];
                int i10 = segment.count;
                AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                for (?? r13 = z8; r13 < atomicReferenceArray.length(); r13++) {
                    for (E next = atomicReferenceArray.get(r13); next != null; next = next.getNext()) {
                        V liveValue = segment.getLiveValue(next);
                        if (liveValue != null && valueEquivalence().equivalent(obj, liveValue)) {
                            return true;
                        }
                    }
                }
                j10 += segment.modCount;
                z8 = false;
            }
            if (j10 == j9) {
                return false;
            }
            i9++;
            j9 = j10;
            z8 = false;
        }
        return z8;
    }

    @VisibleForTesting
    public E copyEntry(E e9, E e10) {
        return (E) segmentFor(e9.getHash()).copyEntry(e9, e10);
    }

    public Segment<K, V, E, S> createSegment(int i9, int i10) {
        return this.entryHelper.newSegment(this, i9, i10);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).get(obj, iHash);
    }

    public E getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return (E) segmentFor(iHash).getEntry(obj, iHash);
    }

    public V getLiveValue(E e9) {
        V v8;
        if (e9.getKey() == null || (v8 = (V) e9.getValue()) == null) {
            return null;
        }
        return v8;
    }

    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j9 = 0;
        for (int i9 = 0; i9 < segmentArr.length; i9++) {
            if (segmentArr[i9].count != 0) {
                return false;
            }
            j9 += segmentArr[i9].modCount;
        }
        if (j9 == 0) {
            return true;
        }
        for (int i10 = 0; i10 < segmentArr.length; i10++) {
            if (segmentArr[i10].count != 0) {
                return false;
            }
            j9 -= segmentArr[i10].modCount;
        }
        return j9 == 0;
    }

    @VisibleForTesting
    public boolean isLiveForTesting(InternalEntry<K, V, ?> internalEntry) {
        return segmentFor(internalEntry.getHash()).getLiveValueForTesting(internalEntry) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    @VisibleForTesting
    public Strength keyStrength() {
        return this.entryHelper.keyStrength();
    }

    public final Segment<K, V, E, S>[] newSegmentArray(int i9) {
        return new Segment[i9];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        int iHash = hash(k9);
        return segmentFor(iHash).put(k9, iHash, v8, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V putIfAbsent(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        int iHash = hash(k9);
        return segmentFor(iHash).put(k9, iHash, v8, true);
    }

    public void reclaimKey(E e9) {
        int hash = e9.getHash();
        segmentFor(hash).reclaimKey(e9, hash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reclaimValue(WeakValueReference<K, V, E> weakValueReference) {
        InternalEntry entry = weakValueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, weakValueReference);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean replace(K k9, V v8, V v9) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v9);
        if (v8 == null) {
            return false;
        }
        int iHash = hash(k9);
        return segmentFor(iHash).replace(k9, iHash, v8, v9);
    }

    public Segment<K, V, E, S> segmentFor(int i9) {
        return this.segments[(i9 >>> this.segmentShift) & this.segmentMask];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long j9 = 0;
        for (int i9 = 0; i9 < this.segments.length; i9++) {
            j9 += r0[i9].count;
        }
        return Ints.saturatedCast(j9);
    }

    @VisibleForTesting
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.valueStrength().defaultEquivalence();
    }

    @VisibleForTesting
    public Strength valueStrength() {
        return this.entryHelper.valueStrength();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    public Object writeReplace() {
        return new SerializationProxy(this.entryHelper.keyStrength(), this.entryHelper.valueStrength(), this.keyEquivalence, this.entryHelper.valueStrength().defaultEquivalence(), this.concurrencyLevel, this);
    }

    public static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        public AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i9, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i9;
            this.delegate = concurrentMap;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void readEntries(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            while (true) {
                Object object = objectInputStream.readObject();
                if (object == null) {
                    return;
                }
                this.delegate.put(object, objectInputStream.readObject());
            }
        }

        public MapMaker readMapMaker(ObjectInputStream objectInputStream) throws IOException {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
        }

        public void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry<K, V> entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject(null);
        }

        @Override // com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V replace(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        int iHash = hash(k9);
        return segmentFor(iHash).replace(k9, iHash, v8);
    }

    public static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        volatile int count;

        @Weak
        final MapMakerInternalMap<K, V, E, S> map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        volatile AtomicReferenceArray<E> table;
        int threshold;

        public Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i9, int i10) {
            this.map = mapMakerInternalMap;
            this.maxSegmentSize = i10;
            initTable(newEntryArray(i9));
        }

        public static <K, V, E extends InternalEntry<K, V, E>> boolean isCollected(E e9) {
            return e9.getValue() == null;
        }

        public abstract E castForTesting(InternalEntry<K, V, ?> internalEntry);

        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    for (int i9 = 0; i9 < atomicReferenceArray.length(); i9++) {
                        atomicReferenceArray.set(i9, null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            while (referenceQueue.poll() != null) {
            }
        }

        @CanIgnoreReturnValue
        public boolean clearValueForTesting(K k9, int i9, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        if (((WeakValueEntry) next).getValueReference() != weakValueReference) {
                            return false;
                        }
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, next));
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        public boolean containsKey(Object obj, int i9) {
            try {
                boolean z8 = false;
                if (this.count == 0) {
                    return false;
                }
                InternalEntry liveEntry = getLiveEntry(obj, i9);
                if (liveEntry != null) {
                    if (liveEntry.getValue() != null) {
                        z8 = true;
                    }
                }
                return z8;
            } finally {
                postReadCleanup();
            }
        }

        @VisibleForTesting
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i9 = 0; i9 < length; i9++) {
                        for (E next = atomicReferenceArray.get(i9); next != null; next = next.getNext()) {
                            V liveValue = getLiveValue(next);
                            if (liveValue != null && this.map.valueEquivalence().equivalent(obj, liveValue)) {
                                postReadCleanup();
                                return true;
                            }
                        }
                    }
                }
                return false;
            } finally {
                postReadCleanup();
            }
        }

        public E copyEntry(E e9, E e10) {
            return (E) this.map.entryHelper.copy(self(), e9, e10);
        }

        public E copyForTesting(InternalEntry<K, V, ?> internalEntry, InternalEntry<K, V, ?> internalEntry2) {
            return (E) this.map.entryHelper.copy(self(), castForTesting(internalEntry), castForTesting(internalEntry2));
        }

        public void drainKeyReferenceQueue(ReferenceQueue<K> referenceQueue) {
            int i9 = 0;
            do {
                Reference<? extends K> referencePoll = referenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimKey((InternalEntry) referencePoll);
                i9++;
            } while (i9 != 16);
        }

        public void drainValueReferenceQueue(ReferenceQueue<V> referenceQueue) {
            int i9 = 0;
            do {
                Reference<? extends V> referencePoll = referenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimValue((WeakValueReference) referencePoll);
                i9++;
            } while (i9 != 16);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void expand() {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i9 = this.count;
            AtomicReferenceArray<E> atomicReferenceArrayNewEntryArray = newEntryArray(length << 1);
            this.threshold = (atomicReferenceArrayNewEntryArray.length() * 3) / 4;
            int length2 = atomicReferenceArrayNewEntryArray.length() - 1;
            for (int i10 = 0; i10 < length; i10++) {
                E next = atomicReferenceArray.get(i10);
                if (next != null) {
                    InternalEntry next2 = next.getNext();
                    int hash = next.getHash() & length2;
                    if (next2 == null) {
                        atomicReferenceArrayNewEntryArray.set(hash, next);
                    } else {
                        InternalEntry internalEntry = next;
                        while (next2 != null) {
                            int hash2 = next2.getHash() & length2;
                            if (hash2 != hash) {
                                internalEntry = next2;
                                hash = hash2;
                            }
                            next2 = next2.getNext();
                        }
                        atomicReferenceArrayNewEntryArray.set(hash, internalEntry);
                        while (next != internalEntry) {
                            int hash3 = next.getHash() & length2;
                            InternalEntry internalEntryCopyEntry = copyEntry(next, (InternalEntry) atomicReferenceArrayNewEntryArray.get(hash3));
                            if (internalEntryCopyEntry != null) {
                                atomicReferenceArrayNewEntryArray.set(hash3, internalEntryCopyEntry);
                            } else {
                                i9--;
                            }
                            next = next.getNext();
                        }
                    }
                }
            }
            this.table = atomicReferenceArrayNewEntryArray;
            this.count = i9;
        }

        public V get(Object obj, int i9) {
            try {
                InternalEntry liveEntry = getLiveEntry(obj, i9);
                if (liveEntry == null) {
                    postReadCleanup();
                    return null;
                }
                V v8 = (V) liveEntry.getValue();
                if (v8 == null) {
                    tryDrainReferenceQueues();
                }
                return v8;
            } finally {
                postReadCleanup();
            }
        }

        public E getEntry(Object obj, int i9) {
            if (this.count == 0) {
                return null;
            }
            for (E e9 = (E) getFirst(i9); e9 != null; e9 = (E) e9.getNext()) {
                if (e9.getHash() == i9) {
                    Object key = e9.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return e9;
                    }
                }
            }
            return null;
        }

        public E getFirst(int i9) {
            return this.table.get(i9 & (r0.length() - 1));
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        public E getLiveEntry(Object obj, int i9) {
            return (E) getEntry(obj, i9);
        }

        public V getLiveValue(E e9) {
            if (e9.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v8 = (V) e9.getValue();
            if (v8 != null) {
                return v8;
            }
            tryDrainReferenceQueues();
            return null;
        }

        public V getLiveValueForTesting(InternalEntry<K, V, ?> internalEntry) {
            return getLiveValue(castForTesting(internalEntry));
        }

        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        public WeakValueReference<K, V, E> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<E> atomicReferenceArray) {
            int length = (atomicReferenceArray.length() * 3) / 4;
            this.threshold = length;
            if (length == this.maxSegmentSize) {
                this.threshold = length + 1;
            }
            this.table = atomicReferenceArray;
        }

        public void maybeClearReferenceQueues() {
        }

        public void maybeDrainReferenceQueues() {
        }

        public AtomicReferenceArray<E> newEntryArray(int i9) {
            return new AtomicReferenceArray<>(i9);
        }

        public E newEntryForTesting(K k9, int i9, InternalEntry<K, V, ?> internalEntry) {
            return (E) this.map.entryHelper.newEntry(self(), k9, i9, castForTesting(internalEntry));
        }

        public WeakValueReference<K, V, E> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v8) {
            throw new AssertionError();
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        public void preWriteCleanup() {
            runLockedCleanup();
        }

        public V put(K k9, int i9, V v8, boolean z8) {
            lock();
            try {
                preWriteCleanup();
                int i10 = this.count + 1;
                if (i10 > this.threshold) {
                    expand();
                    i10 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        V v9 = (V) next.getValue();
                        if (v9 == null) {
                            this.modCount++;
                            setValue(next, v8);
                            this.count = this.count;
                            return null;
                        }
                        if (z8) {
                            return v9;
                        }
                        this.modCount++;
                        setValue(next, v8);
                        return v9;
                    }
                }
                this.modCount++;
                InternalEntry internalEntryNewEntry = this.map.entryHelper.newEntry(self(), k9, i9, internalEntry);
                setValue(internalEntryNewEntry, v8);
                atomicReferenceArray.set(length, internalEntryNewEntry);
                this.count = i10;
                return null;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        public boolean reclaimKey(E e9, int i9) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    if (next == e9) {
                        this.modCount++;
                        InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                        int i10 = this.count - 1;
                        atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                        this.count = i10;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        public boolean reclaimValue(K k9, int i9, WeakValueReference<K, V, E> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        if (((WeakValueEntry) next).getValueReference() != weakValueReference) {
                            return false;
                        }
                        this.modCount++;
                        InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                        int i10 = this.count - 1;
                        atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                        this.count = i10;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        @CanIgnoreReturnValue
        public V remove(Object obj, int i9) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        V v8 = (V) next.getValue();
                        if (v8 == null && !isCollected(next)) {
                            return null;
                        }
                        this.modCount++;
                        InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                        int i10 = this.count - 1;
                        atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                        this.count = i10;
                        return v8;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }

        public boolean removeEntryForTesting(E e9) {
            int hash = e9.getHash();
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = hash & (atomicReferenceArray.length() - 1);
            InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
            for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                if (next == e9) {
                    this.modCount++;
                    InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                    int i9 = this.count - 1;
                    atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                    this.count = i9;
                    return true;
                }
            }
            return false;
        }

        public E removeFromChain(E e9, E e10) {
            int i9 = this.count;
            E e11 = (E) e10.getNext();
            while (e9 != e10) {
                InternalEntry internalEntryCopyEntry = copyEntry(e9, e11);
                if (internalEntryCopyEntry != null) {
                    e11 = (E) internalEntryCopyEntry;
                } else {
                    i9--;
                }
                e9 = (E) e9.getNext();
            }
            this.count = i9;
            return e11;
        }

        public E removeFromChainForTesting(InternalEntry<K, V, ?> internalEntry, InternalEntry<K, V, ?> internalEntry2) {
            return (E) removeFromChain(castForTesting(internalEntry), castForTesting(internalEntry2));
        }

        @CanIgnoreReturnValue
        public boolean removeTableEntryForTesting(InternalEntry<K, V, ?> internalEntry) {
            return removeEntryForTesting(castForTesting(internalEntry));
        }

        public boolean replace(K k9, int i9, V v8, V v9) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        Object value = next.getValue();
                        if (value != null) {
                            if (!this.map.valueEquivalence().equivalent(v8, value)) {
                                return false;
                            }
                            this.modCount++;
                            setValue(next, v9);
                            return true;
                        }
                        if (isCollected(next)) {
                            this.modCount++;
                            InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                            int i10 = this.count - 1;
                            atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                            this.count = i10;
                        }
                        return false;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        public void runCleanup() {
            runLockedCleanup();
        }

        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public abstract S self();

        public void setTableEntryForTesting(int i9, InternalEntry<K, V, ?> internalEntry) {
            this.table.set(i9, castForTesting(internalEntry));
        }

        public void setValue(E e9, V v8) {
            this.map.entryHelper.setValue(self(), e9, v8);
        }

        public void setValueForTesting(InternalEntry<K, V, ?> internalEntry, V v8) {
            this.map.entryHelper.setValue(self(), castForTesting(internalEntry), v8);
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            throw new AssertionError();
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
        
            if (r8.map.valueEquivalence().equivalent(r11, r4.getValue()) == false) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
        
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
        
            if (isCollected(r4) == false) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
        
            r8.modCount++;
            r9 = removeFromChain(r3, r4);
            r10 = r8.count - 1;
            r0.set(r1, r9);
            r8.count = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
        
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
        
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean remove(Object obj, int i9, Object obj2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry next = internalEntry;
                while (true) {
                    boolean z8 = false;
                    if (next == null) {
                        return false;
                    }
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        break;
                    }
                    next = next.getNext();
                }
            } finally {
                unlock();
            }
        }

        public V replace(K k9, int i9, V v8) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                for (InternalEntry next = internalEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        V v9 = (V) next.getValue();
                        if (v9 == null) {
                            if (isCollected(next)) {
                                this.modCount++;
                                InternalEntry internalEntryRemoveFromChain = removeFromChain(internalEntry, next);
                                int i10 = this.count - 1;
                                atomicReferenceArray.set(length, internalEntryRemoveFromChain);
                                this.count = i10;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(next, v8);
                        return v9;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }
    }
}
