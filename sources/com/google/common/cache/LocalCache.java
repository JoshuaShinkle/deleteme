package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    final int concurrencyLevel;
    final CacheLoader<? super K, V> defaultLoader;
    final EntryFactory entryFactory;
    Set<Map.Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final AbstractCache.StatsCounter globalStatsCounter;
    final Equivalence<Object> keyEquivalence;
    Set<K> keySet;
    final Strength keyStrength;
    final long maxWeight;
    final long refreshNanos;
    final RemovalListener<K, V> removalListener;
    final Queue<RemovalNotification<K, V>> removalNotificationQueue;
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    final Ticker ticker;
    final Equivalence<Object> valueEquivalence;
    final Strength valueStrength;
    Collection<V> values;
    final Weigher<K, V> weigher;
    static final Logger logger = Logger.getLogger(LocalCache.class.getName());
    static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>() { // from class: com.google.common.cache.LocalCache.1
        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, Object obj, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public Object get() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 0;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(Object obj) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public Object waitForValue() {
            return null;
        }
    };
    static final Queue<? extends Object> DISCARDING_QUEUE = new AbstractQueue<Object>() { // from class: com.google.common.cache.LocalCache.2
        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return ImmutableSet.m17615of().iterator();
        }

        @Override // java.util.Queue
        public boolean offer(Object obj) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }
    };

    public abstract class AbstractCacheSet<T> extends AbstractSet<T> {

        @Weak
        final ConcurrentMap<?, ?> map;

        public AbstractCacheSet(ConcurrentMap<?, ?> concurrentMap) {
            this.map = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    public static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
            throw new UnsupportedOperationException();
        }
    }

    public static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() { // from class: com.google.common.cache.LocalCache.AccessQueue.1
            ReferenceEntry<K, V> nextAccess = this;
            ReferenceEntry<K, V> previousAccess = this;

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getNextInAccessQueue() {
                return this.nextAccess;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getPreviousInAccessQueue() {
                return this.previousAccess;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setAccessTime(long j9) {
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextAccess = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousAccess = referenceEntry;
            }
        };

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInAccessQueue == referenceEntry) {
                    referenceEntry.setNextInAccessQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInAccessQueue(referenceEntry2);
                    return;
                } else {
                    ReferenceEntry<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    LocalCache.nullifyAccessOrder(nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.head.getNextInAccessQueue() == this.head;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.google.common.cache.LocalCache.AccessQueue.2
                @Override // com.google.common.collect.AbstractSequentialIterator
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
                    if (nextInAccessQueue == AccessQueue.this.head) {
                        return null;
                    }
                    return nextInAccessQueue;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInAccessQueue = referenceEntry.getPreviousInAccessQueue();
            ReferenceEntry<K, V> nextInAccessQueue = referenceEntry.getNextInAccessQueue();
            LocalCache.connectAccessOrder(previousInAccessQueue, nextInAccessQueue);
            LocalCache.nullifyAccessOrder(referenceEntry);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i9 = 0;
            for (ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue(); nextInAccessQueue != this.head; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i9++;
            }
            return i9;
        }

        @Override // java.util.Queue
        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry.getNextInAccessQueue());
            LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), referenceEntry);
            LocalCache.connectAccessOrder(referenceEntry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            return nextInAccessQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInAccessQueue = this.head.getNextInAccessQueue();
            if (nextInAccessQueue == this.head) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }
    }

    public enum EntryFactory {
        STRONG { // from class: com.google.common.cache.LocalCache.EntryFactory.1
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new StrongEntry(k9, i9, referenceEntry);
            }
        },
        STRONG_ACCESS { // from class: com.google.common.cache.LocalCache.EntryFactory.2
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessEntry(k9, i9, referenceEntry);
            }
        },
        STRONG_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.3
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new StrongWriteEntry(k9, i9, referenceEntry);
            }
        },
        STRONG_ACCESS_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.4
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new StrongAccessWriteEntry(k9, i9, referenceEntry);
            }
        },
        WEAK { // from class: com.google.common.cache.LocalCache.EntryFactory.5
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new WeakEntry(segment.keyReferenceQueue, k9, i9, referenceEntry);
            }
        },
        WEAK_ACCESS { // from class: com.google.common.cache.LocalCache.EntryFactory.6
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessEntry(segment.keyReferenceQueue, k9, i9, referenceEntry);
            }
        },
        WEAK_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.7
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new WeakWriteEntry(segment.keyReferenceQueue, k9, i9, referenceEntry);
            }
        },
        WEAK_ACCESS_WRITE { // from class: com.google.common.cache.LocalCache.EntryFactory.8
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = super.copyEntry(segment, referenceEntry, referenceEntry2);
                copyAccessEntry(referenceEntry, referenceEntryCopyEntry);
                copyWriteEntry(referenceEntry, referenceEntryCopyEntry);
                return referenceEntryCopyEntry;
            }

            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
                return new WeakAccessWriteEntry(segment.keyReferenceQueue, k9, i9, referenceEntry);
            }
        };

        static final int ACCESS_MASK = 1;
        static final int WEAK_MASK = 4;
        static final int WRITE_MASK = 2;
        static final EntryFactory[] factories = {STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};

        /* JADX WARN: Multi-variable type inference failed */
        public static EntryFactory getFactory(Strength strength, boolean z8, boolean z9) {
            return factories[(strength == Strength.WEAK ? (char) 4 : (char) 0) | (z8 ? 1 : 0) | (z9 ? 2 : 0)];
        }

        public <K, V> void copyAccessEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setAccessTime(referenceEntry.getAccessTime());
            LocalCache.connectAccessOrder(referenceEntry.getPreviousInAccessQueue(), referenceEntry2);
            LocalCache.connectAccessOrder(referenceEntry2, referenceEntry.getNextInAccessQueue());
            LocalCache.nullifyAccessOrder(referenceEntry);
        }

        public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            return newEntry(segment, referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry2);
        }

        public <K, V> void copyWriteEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            referenceEntry2.setWriteTime(referenceEntry.getWriteTime());
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry2);
            LocalCache.connectWriteOrder(referenceEntry2, referenceEntry.getNextInWriteQueue());
            LocalCache.nullifyWriteOrder(referenceEntry);
        }

        public abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k9, int i9, ReferenceEntry<K, V> referenceEntry);
    }

    public final class EntryIterator extends LocalCache<K, V>.HashIterator<Map.Entry<K, V>> {
        public EntryIterator() {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    public final class EntrySet extends LocalCache<K, V>.AbstractCacheSet<Map.Entry<K, V>> {
        public EntrySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = LocalCache.this.get(key)) != null && LocalCache.this.valueEquivalence.equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && LocalCache.this.remove(key, entry.getValue());
        }
    }

    public abstract class HashIterator<T> implements Iterator<T> {
        Segment<K, V> currentSegment;
        AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
        LocalCache<K, V>.WriteThroughEntry lastReturned;
        ReferenceEntry<K, V> nextEntry;
        LocalCache<K, V>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public HashIterator() {
            this.nextSegmentIndex = LocalCache.this.segments.length - 1;
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
                Segment<K, V>[] segmentArr = LocalCache.this.segments;
                this.nextSegmentIndex = i9 - 1;
                Segment<K, V> segment = segmentArr[i9];
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

        public boolean advanceTo(ReferenceEntry<K, V> referenceEntry) {
            try {
                long j9 = LocalCache.this.ticker.read();
                K key = referenceEntry.getKey();
                Object liveValue = LocalCache.this.getLiveValue(referenceEntry, j9);
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

        public LocalCache<K, V>.WriteThroughEntry nextEntry() {
            LocalCache<K, V>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = writeThroughEntry;
            advance();
            return this.lastReturned;
        }

        public boolean nextInChain() {
            ReferenceEntry<K, V> referenceEntry = this.nextEntry;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.nextEntry = referenceEntry.getNext();
                ReferenceEntry<K, V> referenceEntry2 = this.nextEntry;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (advanceTo(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.nextEntry;
            }
        }

        public boolean nextInTable() {
            while (true) {
                int i9 = this.nextTableIndex;
                if (i9 < 0) {
                    return false;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i9 - 1;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(i9);
                this.nextEntry = referenceEntry;
                if (referenceEntry != null && (advanceTo(referenceEntry) || nextInChain())) {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.lastReturned != null);
            LocalCache.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    public final class KeyIterator extends LocalCache<K, V>.HashIterator<K> {
        public KeyIterator() {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    public final class KeySet extends LocalCache<K, V>.AbstractCacheSet<K> {
        public KeySet(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }
    }

    public static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;
        transient LoadingCache<K, V> autoDelegate;

        public LoadingSerializationProxy(LocalCache<K, V> localCache) {
            super(localCache);
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.autoDelegate = (LoadingCache<K, V>) recreateCacheBuilder().build(this.loader);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k9) {
            return this.autoDelegate.apply(k9);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k9) {
            return this.autoDelegate.get(k9);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.autoDelegate.getAll(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k9) {
            return this.autoDelegate.getUnchecked(k9);
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k9) {
            this.autoDelegate.refresh(k9);
        }
    }

    public static class LoadingValueReference<K, V> implements ValueReference<K, V> {
        final SettableFuture<V> futureValue;
        volatile ValueReference<K, V> oldValue;
        final Stopwatch stopwatch;

        public LoadingValueReference() {
            this(LocalCache.unset());
        }

        private ListenableFuture<V> fullyFailedFuture(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public long elapsedNanos() {
            return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V get() {
            return this.oldValue.get();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> getOldValue() {
            return this.oldValue;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.oldValue.getWeight();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return this.oldValue.isActive();
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return true;
        }

        public ListenableFuture<V> loadFuture(K k9, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.stopwatch.start();
                V v8 = this.oldValue.get();
                if (v8 == null) {
                    V vLoad = cacheLoader.load(k9);
                    return set(vLoad) ? this.futureValue : Futures.immediateFuture(vLoad);
                }
                ListenableFuture<V> listenableFutureReload = cacheLoader.reload(k9, v8);
                return listenableFutureReload == null ? Futures.immediateFuture(null) : Futures.transform(listenableFutureReload, new Function<V, V>() { // from class: com.google.common.cache.LocalCache.LoadingValueReference.1
                    @Override // com.google.common.base.Function
                    public V apply(V v9) {
                        LoadingValueReference.this.set(v9);
                        return v9;
                    }
                }, MoreExecutors.directExecutor());
            } catch (Throwable th) {
                ListenableFuture<V> listenableFutureFullyFailedFuture = setException(th) ? this.futureValue : fullyFailedFuture(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return listenableFutureFullyFailedFuture;
            }
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v8) {
            if (v8 != null) {
                set(v8);
            } else {
                this.oldValue = LocalCache.unset();
            }
        }

        public boolean set(V v8) {
            return this.futureValue.set(v8);
        }

        public boolean setException(Throwable th) {
            return this.futureValue.setException(th);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return (V) Uninterruptibles.getUninterruptibly(this.futureValue);
        }

        public LoadingValueReference(ValueReference<K, V> valueReference) {
            this.futureValue = SettableFuture.create();
            this.stopwatch = Stopwatch.createUnstarted();
            this.oldValue = valueReference;
        }
    }

    public static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        public LocalLoadingCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super();
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k9) {
            return getUnchecked(k9);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k9) {
            return this.localCache.getOrLoad(k9);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
            return this.localCache.getAll(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k9) {
            try {
                return get(k9);
            } catch (ExecutionException e9) {
                throw new UncheckedExecutionException(e9.getCause());
            }
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k9) {
            this.localCache.refresh(k9);
        }

        @Override // com.google.common.cache.LocalCache.LocalManualCache
        public Object writeReplace() {
            return new LoadingSerializationProxy(this.localCache);
        }
    }

    public static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> localCache;

        @Override // com.google.common.cache.Cache
        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        @Override // com.google.common.cache.Cache
        public void cleanUp() {
            this.localCache.cleanUp();
        }

        @Override // com.google.common.cache.Cache
        public V get(K k9, final Callable<? extends V> callable) {
            Preconditions.checkNotNull(callable);
            return this.localCache.get(k9, new CacheLoader<Object, V>() { // from class: com.google.common.cache.LocalCache.LocalManualCache.1
                @Override // com.google.common.cache.CacheLoader
                public V load(Object obj) {
                    return (V) callable.call();
                }
            });
        }

        @Override // com.google.common.cache.Cache
        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.localCache.getAllPresent(iterable);
        }

        @Override // com.google.common.cache.Cache
        public V getIfPresent(Object obj) {
            return this.localCache.getIfPresent(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidate(Object obj) {
            Preconditions.checkNotNull(obj);
            this.localCache.remove(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll(Iterable<?> iterable) {
            this.localCache.invalidateAll(iterable);
        }

        @Override // com.google.common.cache.Cache
        public void put(K k9, V v8) {
            this.localCache.put(k9, v8);
        }

        @Override // com.google.common.cache.Cache
        public void putAll(Map<? extends K, ? extends V> map) {
            this.localCache.putAll(map);
        }

        @Override // com.google.common.cache.Cache
        public long size() {
            return this.localCache.longSize();
        }

        @Override // com.google.common.cache.Cache
        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter simpleStatsCounter = new AbstractCache.SimpleStatsCounter();
            simpleStatsCounter.incrementBy(this.localCache.globalStatsCounter);
            for (Segment<K, V> segment : this.localCache.segments) {
                simpleStatsCounter.incrementBy(segment.statsCounter);
            }
            return simpleStatsCounter.snapshot();
        }

        Object writeReplace() {
            return new ManualSerializationProxy(this.localCache);
        }

        public LocalManualCache(CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new LocalCache(cacheBuilder, null));
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll() {
            this.localCache.clear();
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }
    }

    public static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        final int concurrencyLevel;
        transient Cache<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final CacheLoader<? super K, V> loader;
        final long maxWeight;
        final RemovalListener<? super K, ? super V> removalListener;
        final Ticker ticker;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;
        final Weigher<K, V> weigher;

        public ManualSerializationProxy(LocalCache<K, V> localCache) {
            this(localCache.keyStrength, localCache.valueStrength, localCache.keyEquivalence, localCache.valueEquivalence, localCache.expireAfterWriteNanos, localCache.expireAfterAccessNanos, localCache.maxWeight, localCache.weigher, localCache.concurrencyLevel, localCache.removalListener, localCache.ticker, localCache.defaultLoader);
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.delegate = (Cache<K, V>) recreateCacheBuilder().build();
        }

        private Object readResolve() {
            return this.delegate;
        }

        public CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K, V> cacheBuilder = (CacheBuilder<K, V>) CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
            cacheBuilder.strictParsing = false;
            long j9 = this.expireAfterWriteNanos;
            if (j9 > 0) {
                cacheBuilder.expireAfterWrite(j9, TimeUnit.NANOSECONDS);
            }
            long j10 = this.expireAfterAccessNanos;
            if (j10 > 0) {
                cacheBuilder.expireAfterAccess(j10, TimeUnit.NANOSECONDS);
            }
            Weigher weigher = this.weigher;
            if (weigher != CacheBuilder.OneWeigher.INSTANCE) {
                cacheBuilder.weigher(weigher);
                long j11 = this.maxWeight;
                if (j11 != -1) {
                    cacheBuilder.maximumWeight(j11);
                }
            } else {
                long j12 = this.maxWeight;
                if (j12 != -1) {
                    cacheBuilder.maximumSize(j12);
                }
            }
            Ticker ticker = this.ticker;
            if (ticker != null) {
                cacheBuilder.ticker(ticker);
            }
            return cacheBuilder;
        }

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j9, long j10, long j11, Weigher<K, V> weigher, int i9, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> cacheLoader) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = j9;
            this.expireAfterAccessNanos = j10;
            this.maxWeight = j11;
            this.weigher = weigher;
            this.concurrencyLevel = i9;
            this.removalListener = removalListener;
            this.ticker = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.NULL_TICKER) ? null : ticker;
            this.loader = cacheLoader;
        }

        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    public enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return 0L;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return 0;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public Object getKey() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return 0L;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
        }
    }

    public interface ReferenceEntry<K, V> {
        long getAccessTime();

        int getHash();

        K getKey();

        ReferenceEntry<K, V> getNext();

        ReferenceEntry<K, V> getNextInAccessQueue();

        ReferenceEntry<K, V> getNextInWriteQueue();

        ReferenceEntry<K, V> getPreviousInAccessQueue();

        ReferenceEntry<K, V> getPreviousInWriteQueue();

        ValueReference<K, V> getValueReference();

        long getWriteTime();

        void setAccessTime(long j9);

        void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setValueReference(ValueReference<K, V> valueReference);

        void setWriteTime(long j9);
    }

    public static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        public SoftValueReference(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            super(v8, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return new SoftValueReference(referenceQueue, v8, referenceEntry);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v8) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    public enum Strength {
        STRONG { // from class: com.google.common.cache.LocalCache.Strength.1
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v8, int i9) {
                return i9 == 1 ? new StrongValueReference(v8) : new WeightedStrongValueReference(v8, i9);
            }
        },
        SOFT { // from class: com.google.common.cache.LocalCache.Strength.2
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v8, int i9) {
                return i9 == 1 ? new SoftValueReference(segment.valueReferenceQueue, v8, referenceEntry) : new WeightedSoftValueReference(segment.valueReferenceQueue, v8, referenceEntry, i9);
            }
        },
        WEAK { // from class: com.google.common.cache.LocalCache.Strength.3
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v8, int i9) {
                return i9 == 1 ? new WeakValueReference(segment.valueReferenceQueue, v8, referenceEntry) : new WeightedWeakValueReference(segment.valueReferenceQueue, v8, referenceEntry, i9);
            }
        };

        public abstract Equivalence<Object> defaultEquivalence();

        public abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v8, int i9);
    }

    public static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> previousAccess;

        public StrongAccessEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(k9, i9, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
            this.accessTime = j9;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    public static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousAccess;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        public StrongAccessWriteEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(k9, i9, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
            this.accessTime = j9;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
            this.writeTime = j9;
        }
    }

    public static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {
        final int hash;
        final K key;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = LocalCache.unset();

        public StrongEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            this.key = k9;
            this.hash = i9;
            this.next = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }
    }

    public static class StrongValueReference<K, V> implements ValueReference<K, V> {
        final V referent;

        public StrongValueReference(V v8) {
            this.referent = v8;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V get() {
            return this.referent;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v8) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    public static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        public StrongWriteEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(k9, i9, referenceEntry);
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
            this.writeTime = j9;
        }
    }

    public final class ValueIterator extends LocalCache<K, V>.HashIterator<V> {
        public ValueIterator() {
            super();
        }

        @Override // com.google.common.cache.LocalCache.HashIterator, java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    public interface ValueReference<K, V> {
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry);

        V get();

        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(V v8);

        V waitForValue();
    }

    public final class Values extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> map;

        public Values(ConcurrentMap<?, ?> concurrentMap) {
            this.map = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return LocalCache.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.toArrayList(this).toArray(eArr);
        }
    }

    public static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> previousAccess;

        public WeakAccessEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k9, i9, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
            this.accessTime = j9;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }
    }

    public static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {
        volatile long accessTime;
        ReferenceEntry<K, V> nextAccess;
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousAccess;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        public WeakAccessWriteEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k9, i9, referenceEntry);
            this.accessTime = Long.MAX_VALUE;
            this.nextAccess = LocalCache.nullEntry();
            this.previousAccess = LocalCache.nullEntry();
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getAccessTime() {
            return this.accessTime;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setAccessTime(long j9) {
            this.accessTime = j9;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousAccess = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
            this.writeTime = j9;
        }
    }

    public static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference;

        public WeakEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(k9, referenceQueue);
            this.valueReference = LocalCache.unset();
            this.hash = i9;
            this.next = referenceEntry;
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public K getKey() {
            return get();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j9) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.LocalCache.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            this.valueReference = valueReference;
        }

        public void setWriteTime(long j9) {
            throw new UnsupportedOperationException();
        }
    }

    public static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        public WeakValueReference(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            super(v8, referenceQueue);
            this.entry = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return new WeakValueReference(referenceQueue, v8, referenceEntry);
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isActive() {
            return true;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public boolean isLoading() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public void notifyNewValue(V v8) {
        }

        @Override // com.google.common.cache.LocalCache.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    public static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {
        ReferenceEntry<K, V> nextWrite;
        ReferenceEntry<K, V> previousWrite;
        volatile long writeTime;

        public WeakWriteEntry(ReferenceQueue<K> referenceQueue, K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            super(referenceQueue, k9, i9, referenceEntry);
            this.writeTime = Long.MAX_VALUE;
            this.nextWrite = LocalCache.nullEntry();
            this.previousWrite = LocalCache.nullEntry();
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public long getWriteTime() {
            return this.writeTime;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.nextWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
            this.previousWrite = referenceEntry;
        }

        @Override // com.google.common.cache.LocalCache.WeakEntry, com.google.common.cache.LocalCache.ReferenceEntry
        public void setWriteTime(long j9) {
            this.writeTime = j9;
        }
    }

    public static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {
        final int weight;

        public WeightedSoftValueReference(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry, int i9) {
            super(referenceQueue, v8, referenceEntry);
            this.weight = i9;
        }

        @Override // com.google.common.cache.LocalCache.SoftValueReference, com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedSoftValueReference(referenceQueue, v8, referenceEntry, this.weight);
        }

        @Override // com.google.common.cache.LocalCache.SoftValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {
        final int weight;

        public WeightedStrongValueReference(V v8, int i9) {
            super(v8);
            this.weight = i9;
        }

        @Override // com.google.common.cache.LocalCache.StrongValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {
        final int weight;

        public WeightedWeakValueReference(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry, int i9) {
            super(referenceQueue, v8, referenceEntry);
            this.weight = i9;
        }

        @Override // com.google.common.cache.LocalCache.WeakValueReference, com.google.common.cache.LocalCache.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v8, ReferenceEntry<K, V> referenceEntry) {
            return new WeightedWeakValueReference(referenceQueue, v8, referenceEntry, this.weight);
        }

        @Override // com.google.common.cache.LocalCache.WeakValueReference, com.google.common.cache.LocalCache.ValueReference
        public int getWeight() {
            return this.weight;
        }
    }

    public static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() { // from class: com.google.common.cache.LocalCache.WriteQueue.1
            ReferenceEntry<K, V> nextWrite = this;
            ReferenceEntry<K, V> previousWrite = this;

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getNextInWriteQueue() {
                return this.nextWrite;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public ReferenceEntry<K, V> getPreviousInWriteQueue() {
                return this.previousWrite;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.nextWrite = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
                this.previousWrite = referenceEntry;
            }

            @Override // com.google.common.cache.LocalCache.AbstractReferenceEntry, com.google.common.cache.LocalCache.ReferenceEntry
            public void setWriteTime(long j9) {
            }
        };

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (nextInWriteQueue == referenceEntry) {
                    referenceEntry.setNextInWriteQueue(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousInWriteQueue(referenceEntry2);
                    return;
                } else {
                    ReferenceEntry<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    LocalCache.nullifyWriteOrder(nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((ReferenceEntry) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.head.getNextInWriteQueue() == this.head;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) { // from class: com.google.common.cache.LocalCache.WriteQueue.2
                @Override // com.google.common.collect.AbstractSequentialIterator
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> referenceEntry) {
                    ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
                    if (nextInWriteQueue == WriteQueue.this.head) {
                        return null;
                    }
                    return nextInWriteQueue;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            ReferenceEntry referenceEntry = (ReferenceEntry) obj;
            ReferenceEntry<K, V> previousInWriteQueue = referenceEntry.getPreviousInWriteQueue();
            ReferenceEntry<K, V> nextInWriteQueue = referenceEntry.getNextInWriteQueue();
            LocalCache.connectWriteOrder(previousInWriteQueue, nextInWriteQueue);
            LocalCache.nullifyWriteOrder(referenceEntry);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i9 = 0;
            for (ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue(); nextInWriteQueue != this.head; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i9++;
            }
            return i9;
        }

        @Override // java.util.Queue
        public boolean offer(ReferenceEntry<K, V> referenceEntry) {
            LocalCache.connectWriteOrder(referenceEntry.getPreviousInWriteQueue(), referenceEntry.getNextInWriteQueue());
            LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), referenceEntry);
            LocalCache.connectWriteOrder(referenceEntry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            return nextInWriteQueue;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> nextInWriteQueue = this.head.getNextInWriteQueue();
            if (nextInWriteQueue == this.head) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }
    }

    public final class WriteThroughEntry implements Map.Entry<K, V> {
        final K key;
        V value;

        public WriteThroughEntry(K k9, V v8) {
            this.key = k9;
            this.value = v8;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v8) {
            V v9 = (V) LocalCache.this.put(this.key, v8);
            this.value = v8;
            return v9;
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
        this.concurrencyLevel = Math.min(cacheBuilder.getConcurrencyLevel(), 65536);
        Strength keyStrength = cacheBuilder.getKeyStrength();
        this.keyStrength = keyStrength;
        this.valueStrength = cacheBuilder.getValueStrength();
        this.keyEquivalence = cacheBuilder.getKeyEquivalence();
        this.valueEquivalence = cacheBuilder.getValueEquivalence();
        long maximumWeight = cacheBuilder.getMaximumWeight();
        this.maxWeight = maximumWeight;
        this.weigher = (Weigher<K, V>) cacheBuilder.getWeigher();
        this.expireAfterAccessNanos = cacheBuilder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = cacheBuilder.getExpireAfterWriteNanos();
        this.refreshNanos = cacheBuilder.getRefreshNanos();
        CacheBuilder.NullListener nullListener = (RemovalListener<K, V>) cacheBuilder.getRemovalListener();
        this.removalListener = nullListener;
        this.removalNotificationQueue = nullListener == CacheBuilder.NullListener.INSTANCE ? discardingQueue() : new ConcurrentLinkedQueue<>();
        this.ticker = cacheBuilder.getTicker(recordsTime());
        this.entryFactory = EntryFactory.getFactory(keyStrength, usesAccessEntries(), usesWriteEntries());
        this.globalStatsCounter = cacheBuilder.getStatsCounterSupplier().get();
        this.defaultLoader = cacheLoader;
        int iMin = Math.min(cacheBuilder.getInitialCapacity(), 1073741824);
        if (evictsBySize() && !customWeigher()) {
            iMin = Math.min(iMin, (int) maximumWeight);
        }
        int i9 = 0;
        int i10 = 1;
        int i11 = 0;
        int i12 = 1;
        while (i12 < this.concurrencyLevel && (!evictsBySize() || i12 * 20 <= this.maxWeight)) {
            i11++;
            i12 <<= 1;
        }
        this.segmentShift = 32 - i11;
        this.segmentMask = i12 - 1;
        this.segments = newSegmentArray(i12);
        int i13 = iMin / i12;
        while (i10 < (i13 * i12 < iMin ? i13 + 1 : i13)) {
            i10 <<= 1;
        }
        if (evictsBySize()) {
            long j9 = this.maxWeight;
            long j10 = i12;
            long j11 = (j9 / j10) + 1;
            long j12 = j9 % j10;
            while (true) {
                Segment<K, V>[] segmentArr = this.segments;
                if (i9 >= segmentArr.length) {
                    return;
                }
                if (i9 == j12) {
                    j11--;
                }
                segmentArr[i9] = createSegment(i10, j11, cacheBuilder.getStatsCounterSupplier().get());
                i9++;
            }
        } else {
            while (true) {
                Segment<K, V>[] segmentArr2 = this.segments;
                if (i9 >= segmentArr2.length) {
                    return;
                }
                segmentArr2[i9] = createSegment(i10, -1L, cacheBuilder.getStatsCounterSupplier().get());
                i9++;
            }
        }
    }

    public static <K, V> void connectAccessOrder(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInAccessQueue(referenceEntry2);
        referenceEntry2.setPreviousInAccessQueue(referenceEntry);
    }

    public static <K, V> void connectWriteOrder(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        referenceEntry.setNextInWriteQueue(referenceEntry2);
        referenceEntry2.setPreviousInWriteQueue(referenceEntry);
    }

    public static <E> Queue<E> discardingQueue() {
        return (Queue<E>) DISCARDING_QUEUE;
    }

    public static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    public static <K, V> void nullifyAccessOrder(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntryNullEntry = nullEntry();
        referenceEntry.setNextInAccessQueue(referenceEntryNullEntry);
        referenceEntry.setPreviousInAccessQueue(referenceEntryNullEntry);
    }

    public static <K, V> void nullifyWriteOrder(ReferenceEntry<K, V> referenceEntry) {
        ReferenceEntry<K, V> referenceEntryNullEntry = nullEntry();
        referenceEntry.setNextInWriteQueue(referenceEntryNullEntry);
        referenceEntry.setPreviousInWriteQueue(referenceEntryNullEntry);
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

    public static <K, V> ValueReference<K, V> unset() {
        return (ValueReference<K, V>) UNSET;
    }

    public void cleanUp() {
        for (Segment<K, V> segment : this.segments) {
            segment.cleanUp();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V> segment : this.segments) {
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
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v3 */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        boolean z8 = false;
        if (obj == null) {
            return false;
        }
        long j9 = this.ticker.read();
        Segment<K, V>[] segmentArr = this.segments;
        long j10 = -1;
        int i9 = 0;
        while (i9 < 3) {
            int length = segmentArr.length;
            long j11 = 0;
            for (?? r12 = z8; r12 < length; r12++) {
                Segment<K, V> segment = segmentArr[r12];
                int i10 = segment.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = segment.table;
                for (?? r15 = z8; r15 < atomicReferenceArray.length(); r15++) {
                    ReferenceEntry<K, V> next = atomicReferenceArray.get(r15);
                    while (next != null) {
                        Segment<K, V>[] segmentArr2 = segmentArr;
                        V liveValue = segment.getLiveValue(next, j9);
                        long j12 = j9;
                        if (liveValue != null && this.valueEquivalence.equivalent(obj, liveValue)) {
                            return true;
                        }
                        next = next.getNext();
                        segmentArr = segmentArr2;
                        j9 = j12;
                    }
                }
                j11 += segment.modCount;
                j9 = j9;
                z8 = false;
            }
            long j13 = j9;
            Segment<K, V>[] segmentArr3 = segmentArr;
            if (j11 == j10) {
                return false;
            }
            i9++;
            j10 = j11;
            segmentArr = segmentArr3;
            j9 = j13;
            z8 = false;
        }
        return z8;
    }

    @VisibleForTesting
    public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
        return segmentFor(referenceEntry.getHash()).copyEntry(referenceEntry, referenceEntry2);
    }

    public Segment<K, V> createSegment(int i9, long j9, AbstractCache.StatsCounter statsCounter) {
        return new Segment<>(this, i9, j9, statsCounter);
    }

    public boolean customWeigher() {
        return this.weigher != CacheBuilder.OneWeigher.INSTANCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet(this);
        this.entrySet = entrySet;
        return entrySet;
    }

    public boolean evictsBySize() {
        return this.maxWeight >= 0;
    }

    public boolean expires() {
        return expiresAfterWrite() || expiresAfterAccess();
    }

    public boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0;
    }

    public boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).get(obj, iHash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
        LinkedHashMap linkedHashMapNewLinkedHashMap = Maps.newLinkedHashMap();
        LinkedHashSet linkedHashSetNewLinkedHashSet = Sets.newLinkedHashSet();
        int i9 = 0;
        int i10 = 0;
        for (K k9 : iterable) {
            Object obj = get(k9);
            if (!linkedHashMapNewLinkedHashMap.containsKey(k9)) {
                linkedHashMapNewLinkedHashMap.put(k9, obj);
                if (obj == null) {
                    i10++;
                    linkedHashSetNewLinkedHashSet.add(k9);
                } else {
                    i9++;
                }
            }
        }
        try {
            if (!linkedHashSetNewLinkedHashSet.isEmpty()) {
                try {
                    Map mapLoadAll = loadAll(linkedHashSetNewLinkedHashSet, this.defaultLoader);
                    for (Object obj2 : linkedHashSetNewLinkedHashSet) {
                        Object obj3 = mapLoadAll.get(obj2);
                        if (obj3 == null) {
                            throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + obj2);
                        }
                        linkedHashMapNewLinkedHashMap.put(obj2, obj3);
                    }
                } catch (CacheLoader.UnsupportedLoadingOperationException unused) {
                    for (Object obj4 : linkedHashSetNewLinkedHashSet) {
                        i10--;
                        linkedHashMapNewLinkedHashMap.put(obj4, get(obj4, this.defaultLoader));
                    }
                }
            }
            return ImmutableMap.copyOf((Map) linkedHashMapNewLinkedHashMap);
        } finally {
            this.globalStatsCounter.recordHits(i9);
            this.globalStatsCounter.recordMisses(i10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        LinkedHashMap linkedHashMapNewLinkedHashMap = Maps.newLinkedHashMap();
        int i9 = 0;
        int i10 = 0;
        for (Object obj : iterable) {
            V v8 = get(obj);
            if (v8 == null) {
                i10++;
            } else {
                linkedHashMapNewLinkedHashMap.put(obj, v8);
                i9++;
            }
        }
        this.globalStatsCounter.recordHits(i9);
        this.globalStatsCounter.recordMisses(i10);
        return ImmutableMap.copyOf((Map) linkedHashMapNewLinkedHashMap);
    }

    public ReferenceEntry<K, V> getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).getEntry(obj, iHash);
    }

    public V getIfPresent(Object obj) {
        int iHash = hash(Preconditions.checkNotNull(obj));
        V v8 = segmentFor(iHash).get(obj, iHash);
        if (v8 == null) {
            this.globalStatsCounter.recordMisses(1);
        } else {
            this.globalStatsCounter.recordHits(1);
        }
        return v8;
    }

    public V getLiveValue(ReferenceEntry<K, V> referenceEntry, long j9) {
        V v8;
        if (referenceEntry.getKey() == null || (v8 = referenceEntry.getValueReference().get()) == null || isExpired(referenceEntry, j9)) {
            return null;
        }
        return v8;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V getOrDefault(Object obj, V v8) {
        V v9 = get(obj);
        return v9 != null ? v9 : v8;
    }

    public V getOrLoad(K k9) {
        return get(k9, this.defaultLoader);
    }

    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    public void invalidateAll(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.segments;
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

    public boolean isExpired(ReferenceEntry<K, V> referenceEntry, long j9) {
        Preconditions.checkNotNull(referenceEntry);
        if (!expiresAfterAccess() || j9 - referenceEntry.getAccessTime() < this.expireAfterAccessNanos) {
            return expiresAfterWrite() && j9 - referenceEntry.getWriteTime() >= this.expireAfterWriteNanos;
        }
        return true;
    }

    @VisibleForTesting
    public boolean isLive(ReferenceEntry<K, V> referenceEntry, long j9) {
        return segmentFor(referenceEntry.getHash()).getLiveValue(referenceEntry, j9) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet(this);
        this.keySet = keySet;
        return keySet;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Map<K, V> loadAll(Set<? extends K> set, CacheLoader<? super K, V> cacheLoader) throws Throwable {
        Preconditions.checkNotNull(cacheLoader);
        Preconditions.checkNotNull(set);
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted();
        boolean z8 = true;
        boolean z9 = false;
        try {
            try {
                try {
                    Map<? super K, V> mapLoadAll = cacheLoader.loadAll(set);
                    if (mapLoadAll == null) {
                        this.globalStatsCounter.recordLoadException(stopwatchCreateStarted.elapsed(TimeUnit.NANOSECONDS));
                        throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
                    }
                    stopwatchCreateStarted.stop();
                    for (Map.Entry<K, V> entry : mapLoadAll.entrySet()) {
                        K key = entry.getKey();
                        V value = entry.getValue();
                        if (key == null || value == null) {
                            z9 = true;
                        } else {
                            put(key, value);
                        }
                    }
                    if (!z9) {
                        this.globalStatsCounter.recordLoadSuccess(stopwatchCreateStarted.elapsed(TimeUnit.NANOSECONDS));
                        return mapLoadAll;
                    }
                    this.globalStatsCounter.recordLoadException(stopwatchCreateStarted.elapsed(TimeUnit.NANOSECONDS));
                    throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
                } catch (CacheLoader.UnsupportedLoadingOperationException e9) {
                    try {
                        throw e9;
                    } catch (Throwable th) {
                        th = th;
                        if (!z8) {
                            this.globalStatsCounter.recordLoadException(stopwatchCreateStarted.elapsed(TimeUnit.NANOSECONDS));
                        }
                        throw th;
                    }
                } catch (Exception e10) {
                    throw new ExecutionException(e10);
                }
            } catch (Error e11) {
                throw new ExecutionError(e11);
            } catch (InterruptedException e12) {
                Thread.currentThread().interrupt();
                throw new ExecutionException(e12);
            } catch (RuntimeException e13) {
                throw new UncheckedExecutionException(e13);
            }
        } catch (Throwable th2) {
            th = th2;
            z8 = false;
            if (!z8) {
            }
            throw th;
        }
    }

    public long longSize() {
        long jMax = 0;
        for (int i9 = 0; i9 < this.segments.length; i9++) {
            jMax += Math.max(0, r0[i9].count);
        }
        return jMax;
    }

    @VisibleForTesting
    public ReferenceEntry<K, V> newEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
        Segment<K, V> segmentSegmentFor = segmentFor(i9);
        segmentSegmentFor.lock();
        try {
            return segmentSegmentFor.newEntry(k9, i9, referenceEntry);
        } finally {
            segmentSegmentFor.unlock();
        }
    }

    public final Segment<K, V>[] newSegmentArray(int i9) {
        return new Segment[i9];
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    public ValueReference<K, V> newValueReference(ReferenceEntry<K, V> referenceEntry, V v8, int i9) {
        return this.valueStrength.referenceValue(segmentFor(referenceEntry.getHash()), referenceEntry, Preconditions.checkNotNull(v8), i9);
    }

    public void processPendingNotifications() {
        while (true) {
            RemovalNotification<K, V> removalNotificationPoll = this.removalNotificationQueue.poll();
            if (removalNotificationPoll == null) {
                return;
            }
            try {
                this.removalListener.onRemoval(removalNotificationPoll);
            } catch (Throwable th) {
                logger.log(Level.WARNING, "Exception thrown by removal listener", th);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
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
    public V putIfAbsent(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        int iHash = hash(k9);
        return segmentFor(iHash).put(k9, iHash, v8, true);
    }

    public void reclaimKey(ReferenceEntry<K, V> referenceEntry) {
        int hash = referenceEntry.getHash();
        segmentFor(hash).reclaimKey(referenceEntry, hash);
    }

    public void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    public boolean recordsAccess() {
        return expiresAfterAccess();
    }

    public boolean recordsTime() {
        return recordsWrite() || recordsAccess();
    }

    public boolean recordsWrite() {
        return expiresAfterWrite() || refreshes();
    }

    public void refresh(K k9) {
        int iHash = hash(Preconditions.checkNotNull(k9));
        segmentFor(iHash).refresh(k9, iHash, this.defaultLoader, false);
    }

    public boolean refreshes() {
        return this.refreshNanos > 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k9, V v8, V v9) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v9);
        if (v8 == null) {
            return false;
        }
        int iHash = hash(k9);
        return segmentFor(iHash).replace(k9, iHash, v8, v9);
    }

    public Segment<K, V> segmentFor(int i9) {
        return this.segments[(i9 >>> this.segmentShift) & this.segmentMask];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return Ints.saturatedCast(longSize());
    }

    public boolean usesAccessEntries() {
        return usesAccessQueue() || recordsAccess();
    }

    public boolean usesAccessQueue() {
        return expiresAfterAccess() || evictsBySize();
    }

    public boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    public boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    public boolean usesWriteEntries() {
        return usesWriteQueue() || recordsWrite();
    }

    public boolean usesWriteQueue() {
        return expiresAfterWrite();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values(this);
        this.values = values;
        return values;
    }

    public V get(K k9, CacheLoader<? super K, V> cacheLoader) {
        int iHash = hash(Preconditions.checkNotNull(k9));
        return segmentFor(iHash).get(k9, iHash, cacheLoader);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int iHash = hash(obj);
        return segmentFor(iHash).remove(obj, iHash, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k9, V v8) {
        Preconditions.checkNotNull(k9);
        Preconditions.checkNotNull(v8);
        int iHash = hash(k9);
        return segmentFor(iHash).replace(k9, iHash, v8);
    }

    public static class Segment<K, V> extends ReentrantLock {
        final Queue<ReferenceEntry<K, V>> accessQueue;
        volatile int count;
        final ReferenceQueue<K> keyReferenceQueue;

        @Weak
        final LocalCache<K, V> map;
        final long maxSegmentWeight;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        final AbstractCache.StatsCounter statsCounter;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        long totalWeight;
        final ReferenceQueue<V> valueReferenceQueue;
        final Queue<ReferenceEntry<K, V>> writeQueue;

        public Segment(LocalCache<K, V> localCache, int i9, long j9, AbstractCache.StatsCounter statsCounter) {
            this.map = localCache;
            this.maxSegmentWeight = j9;
            this.statsCounter = (AbstractCache.StatsCounter) Preconditions.checkNotNull(statsCounter);
            initTable(newEntryArray(i9));
            this.keyReferenceQueue = localCache.usesKeyReferences() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = localCache.usesValueReferences() ? new ReferenceQueue<>() : null;
            this.recencyQueue = localCache.usesAccessQueue() ? new ConcurrentLinkedQueue<>() : LocalCache.discardingQueue();
            this.writeQueue = localCache.usesWriteQueue() ? new WriteQueue<>() : LocalCache.discardingQueue();
            this.accessQueue = localCache.usesAccessQueue() ? new AccessQueue<>() : LocalCache.discardingQueue();
        }

        public void cleanUp() {
            runLockedCleanup(this.map.ticker.read());
            runUnlockedCleanup();
        }

        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    preWriteCleanup(this.map.ticker.read());
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    for (int i9 = 0; i9 < atomicReferenceArray.length(); i9++) {
                        for (ReferenceEntry<K, V> next = atomicReferenceArray.get(i9); next != null; next = next.getNext()) {
                            if (next.getValueReference().isActive()) {
                                K key = next.getKey();
                                V v8 = next.getValueReference().get();
                                enqueueNotification(key, next.getHash(), v8, next.getValueReference().getWeight(), (key == null || v8 == null) ? RemovalCause.COLLECTED : RemovalCause.EXPLICIT);
                            }
                        }
                    }
                    for (int i10 = 0; i10 < atomicReferenceArray.length(); i10++) {
                        atomicReferenceArray.set(i10, null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        public void clearKeyReferenceQueue() {
            while (this.keyReferenceQueue.poll() != null) {
            }
        }

        public void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                clearValueReferenceQueue();
            }
        }

        public void clearValueReferenceQueue() {
            while (this.valueReferenceQueue.poll() != null) {
            }
        }

        public boolean containsKey(Object obj, int i9) {
            try {
                if (this.count == 0) {
                    return false;
                }
                ReferenceEntry<K, V> liveEntry = getLiveEntry(obj, i9, this.map.ticker.read());
                if (liveEntry == null) {
                    return false;
                }
                return liveEntry.getValueReference().get() != null;
            } finally {
                postReadCleanup();
            }
        }

        @VisibleForTesting
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    long j9 = this.map.ticker.read();
                    AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i9 = 0; i9 < length; i9++) {
                        for (ReferenceEntry<K, V> next = atomicReferenceArray.get(i9); next != null; next = next.getNext()) {
                            V liveValue = getLiveValue(next, j9);
                            if (liveValue != null && this.map.valueEquivalence.equivalent(obj, liveValue)) {
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

        public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            if (referenceEntry.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            V v8 = valueReference.get();
            if (v8 == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> referenceEntryCopyEntry = this.map.entryFactory.copyEntry(this, referenceEntry, referenceEntry2);
            referenceEntryCopyEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, v8, referenceEntryCopyEntry));
            return referenceEntryCopyEntry;
        }

        public void drainKeyReferenceQueue() {
            int i9 = 0;
            do {
                Reference<? extends K> referencePoll = this.keyReferenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimKey((ReferenceEntry) referencePoll);
                i9++;
            } while (i9 != 16);
        }

        public void drainRecencyQueue() {
            while (true) {
                ReferenceEntry<K, V> referenceEntryPoll = this.recencyQueue.poll();
                if (referenceEntryPoll == null) {
                    return;
                }
                if (this.accessQueue.contains(referenceEntryPoll)) {
                    this.accessQueue.add(referenceEntryPoll);
                }
            }
        }

        public void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                drainValueReferenceQueue();
            }
        }

        public void drainValueReferenceQueue() {
            int i9 = 0;
            do {
                Reference<? extends V> referencePoll = this.valueReferenceQueue.poll();
                if (referencePoll == null) {
                    return;
                }
                this.map.reclaimValue((ValueReference) referencePoll);
                i9++;
            } while (i9 != 16);
        }

        public void enqueueNotification(K k9, int i9, V v8, int i10, RemovalCause removalCause) {
            this.totalWeight -= i10;
            if (removalCause.wasEvicted()) {
                this.statsCounter.recordEviction();
            }
            if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
                this.map.removalNotificationQueue.offer(RemovalNotification.create(k9, v8, removalCause));
            }
        }

        public void evictEntries(ReferenceEntry<K, V> referenceEntry) {
            if (this.map.evictsBySize()) {
                drainRecencyQueue();
                if (referenceEntry.getValueReference().getWeight() > this.maxSegmentWeight && !removeEntry(referenceEntry, referenceEntry.getHash(), RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
                while (this.totalWeight > this.maxSegmentWeight) {
                    ReferenceEntry<K, V> nextEvictable = getNextEvictable();
                    if (!removeEntry(nextEvictable, nextEvictable.getHash(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        public void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i9 = this.count;
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArrayNewEntryArray = newEntryArray(length << 1);
            this.threshold = (atomicReferenceArrayNewEntryArray.length() * 3) / 4;
            int length2 = atomicReferenceArrayNewEntryArray.length() - 1;
            for (int i10 = 0; i10 < length; i10++) {
                ReferenceEntry<K, V> next = atomicReferenceArray.get(i10);
                if (next != null) {
                    ReferenceEntry<K, V> next2 = next.getNext();
                    int hash = next.getHash() & length2;
                    if (next2 == null) {
                        atomicReferenceArrayNewEntryArray.set(hash, next);
                    } else {
                        ReferenceEntry<K, V> referenceEntry = next;
                        while (next2 != null) {
                            int hash2 = next2.getHash() & length2;
                            if (hash2 != hash) {
                                referenceEntry = next2;
                                hash = hash2;
                            }
                            next2 = next2.getNext();
                        }
                        atomicReferenceArrayNewEntryArray.set(hash, referenceEntry);
                        while (next != referenceEntry) {
                            int hash3 = next.getHash() & length2;
                            ReferenceEntry<K, V> referenceEntryCopyEntry = copyEntry(next, atomicReferenceArrayNewEntryArray.get(hash3));
                            if (referenceEntryCopyEntry != null) {
                                atomicReferenceArrayNewEntryArray.set(hash3, referenceEntryCopyEntry);
                            } else {
                                removeCollectedEntry(next);
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

        public void expireEntries(long j9) {
            ReferenceEntry<K, V> referenceEntryPeek;
            ReferenceEntry<K, V> referenceEntryPeek2;
            drainRecencyQueue();
            do {
                referenceEntryPeek = this.writeQueue.peek();
                if (referenceEntryPeek == null || !this.map.isExpired(referenceEntryPeek, j9)) {
                    do {
                        referenceEntryPeek2 = this.accessQueue.peek();
                        if (referenceEntryPeek2 == null || !this.map.isExpired(referenceEntryPeek2, j9)) {
                            return;
                        }
                    } while (removeEntry(referenceEntryPeek2, referenceEntryPeek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(referenceEntryPeek, referenceEntryPeek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        public V get(K k9, int i9, CacheLoader<? super K, V> cacheLoader) {
            ReferenceEntry<K, V> entry;
            Preconditions.checkNotNull(k9);
            Preconditions.checkNotNull(cacheLoader);
            try {
                try {
                    if (this.count != 0 && (entry = getEntry(k9, i9)) != null) {
                        long j9 = this.map.ticker.read();
                        V liveValue = getLiveValue(entry, j9);
                        if (liveValue != null) {
                            recordRead(entry, j9);
                            this.statsCounter.recordHits(1);
                            return scheduleRefresh(entry, k9, i9, liveValue, j9, cacheLoader);
                        }
                        ValueReference<K, V> valueReference = entry.getValueReference();
                        if (valueReference.isLoading()) {
                            return waitForLoadingValue(entry, k9, valueReference);
                        }
                    }
                    return lockedGetOrLoad(k9, i9, cacheLoader);
                } catch (ExecutionException e9) {
                    Throwable cause = e9.getCause();
                    if (cause instanceof Error) {
                        throw new ExecutionError((Error) cause);
                    }
                    if (cause instanceof RuntimeException) {
                        throw new UncheckedExecutionException(cause);
                    }
                    throw e9;
                }
            } finally {
                postReadCleanup();
            }
        }

        public V getAndRecordStats(K k9, int i9, LoadingValueReference<K, V> loadingValueReference, ListenableFuture<V> listenableFuture) throws Throwable {
            V v8;
            try {
                v8 = (V) Uninterruptibles.getUninterruptibly(listenableFuture);
            } catch (Throwable th) {
                th = th;
                v8 = null;
            }
            try {
                if (v8 != null) {
                    this.statsCounter.recordLoadSuccess(loadingValueReference.elapsedNanos());
                    storeLoadedValue(k9, i9, loadingValueReference, v8);
                    return v8;
                }
                throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k9 + ".");
            } catch (Throwable th2) {
                th = th2;
                if (v8 == null) {
                    this.statsCounter.recordLoadException(loadingValueReference.elapsedNanos());
                    removeLoadingValue(k9, i9, loadingValueReference);
                }
                throw th;
            }
        }

        public ReferenceEntry<K, V> getEntry(Object obj, int i9) {
            for (ReferenceEntry<K, V> first = getFirst(i9); first != null; first = first.getNext()) {
                if (first.getHash() == i9) {
                    K key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        public ReferenceEntry<K, V> getFirst(int i9) {
            return this.table.get(i9 & (r0.length() - 1));
        }

        public ReferenceEntry<K, V> getLiveEntry(Object obj, int i9, long j9) {
            ReferenceEntry<K, V> entry = getEntry(obj, i9);
            if (entry == null) {
                return null;
            }
            if (!this.map.isExpired(entry, j9)) {
                return entry;
            }
            tryExpireEntries(j9);
            return null;
        }

        public V getLiveValue(ReferenceEntry<K, V> referenceEntry, long j9) {
            if (referenceEntry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v8 = referenceEntry.getValueReference().get();
            if (v8 == null) {
                tryDrainReferenceQueues();
                return null;
            }
            if (!this.map.isExpired(referenceEntry, j9)) {
                return v8;
            }
            tryExpireEntries(j9);
            return null;
        }

        public ReferenceEntry<K, V> getNextEvictable() {
            for (ReferenceEntry<K, V> referenceEntry : this.accessQueue) {
                if (referenceEntry.getValueReference().getWeight() > 0) {
                    return referenceEntry;
                }
            }
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            if (!this.map.customWeigher()) {
                int i9 = this.threshold;
                if (i9 == this.maxSegmentWeight) {
                    this.threshold = i9 + 1;
                }
            }
            this.table = atomicReferenceArray;
        }

        public LoadingValueReference<K, V> insertLoadingValueReference(K k9, int i9, boolean z8) {
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry = (ReferenceEntry) atomicReferenceArray.get(length);
                for (ReferenceEntry next = referenceEntry; next != null; next = next.getNext()) {
                    Object key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        if (!valueReference.isLoading() && (!z8 || j9 - next.getWriteTime() >= this.map.refreshNanos)) {
                            this.modCount++;
                            LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference<>(valueReference);
                            next.setValueReference(loadingValueReference);
                            return loadingValueReference;
                        }
                        unlock();
                        postWriteCleanup();
                        return null;
                    }
                }
                this.modCount++;
                LoadingValueReference<K, V> loadingValueReference2 = new LoadingValueReference<>();
                ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k9, i9, referenceEntry);
                referenceEntryNewEntry.setValueReference(loadingValueReference2);
                atomicReferenceArray.set(length, referenceEntryNewEntry);
                return loadingValueReference2;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public ListenableFuture<V> loadAsync(final K k9, final int i9, final LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) {
            final ListenableFuture<V> listenableFutureLoadFuture = loadingValueReference.loadFuture(k9, cacheLoader);
            listenableFutureLoadFuture.addListener(new Runnable() { // from class: com.google.common.cache.LocalCache.Segment.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Segment.this.getAndRecordStats(k9, i9, loadingValueReference, listenableFutureLoadFuture);
                    } catch (Throwable th) {
                        LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", th);
                        loadingValueReference.setException(th);
                    }
                }
            }, MoreExecutors.directExecutor());
            return listenableFutureLoadFuture;
        }

        public V loadSync(K k9, int i9, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> cacheLoader) {
            return getAndRecordStats(k9, i9, loadingValueReference, loadingValueReference.loadFuture(k9, cacheLoader));
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x00a0, code lost:
        
            if (r1 == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00a2, code lost:
        
            r15 = new com.google.common.cache.LocalCache.LoadingValueReference<>();
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00a7, code lost:
        
            if (r14 != null) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00a9, code lost:
        
            r14 = newEntry(r18, r19, r13);
            r14.setValueReference(r15);
            r11.set(r12, r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00b4, code lost:
        
            r14.setValueReference(r15);
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00bd, code lost:
        
            if (r1 == false) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00bf, code lost:
        
            monitor-enter(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00c2, code lost:
        
            r0 = loadSync(r18, r19, r15, r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00c6, code lost:
        
            monitor-exit(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00cc, code lost:
        
            return r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00d0, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00d1, code lost:
        
            r17.statsCounter.recordMisses(1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00d6, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00db, code lost:
        
            return waitForLoadingValue(r14, r18, r2);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public V lockedGetOrLoad(K k9, int i9, CacheLoader<? super K, V> cacheLoader) {
            boolean z8;
            ValueReference<K, V> valueReference;
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                int i10 = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> referenceEntryNewEntry = referenceEntry;
                while (true) {
                    LoadingValueReference<K, V> loadingValueReference = null;
                    if (referenceEntryNewEntry == null) {
                        z8 = true;
                        valueReference = null;
                        break;
                    }
                    K key = referenceEntryNewEntry.getKey();
                    if (referenceEntryNewEntry.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        ValueReference<K, V> valueReference2 = referenceEntryNewEntry.getValueReference();
                        if (valueReference2.isLoading()) {
                            z8 = false;
                        } else {
                            V v8 = valueReference2.get();
                            if (v8 == null) {
                                enqueueNotification(key, i9, v8, valueReference2.getWeight(), RemovalCause.COLLECTED);
                            } else {
                                if (!this.map.isExpired(referenceEntryNewEntry, j9)) {
                                    recordLockedRead(referenceEntryNewEntry, j9);
                                    this.statsCounter.recordHits(1);
                                    return v8;
                                }
                                enqueueNotification(key, i9, v8, valueReference2.getWeight(), RemovalCause.EXPIRED);
                            }
                            this.writeQueue.remove(referenceEntryNewEntry);
                            this.accessQueue.remove(referenceEntryNewEntry);
                            this.count = i10;
                            z8 = true;
                        }
                        valueReference = valueReference2;
                    } else {
                        referenceEntryNewEntry = referenceEntryNewEntry.getNext();
                    }
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ReferenceEntry<K, V> newEntry(K k9, int i9, ReferenceEntry<K, V> referenceEntry) {
            return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(k9), i9, referenceEntry);
        }

        public AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int i9) {
            return new AtomicReferenceArray<>(i9);
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        public void postWriteCleanup() {
            runUnlockedCleanup();
        }

        public void preWriteCleanup(long j9) {
            runLockedCleanup(j9);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x008f, code lost:
        
            return null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public V put(K k9, int i9, V v8, boolean z8) {
            int i10;
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                if (this.count + 1 > this.threshold) {
                    expand();
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k9, i9, referenceEntry);
                        setValue(referenceEntryNewEntry, k9, v8, j9);
                        atomicReferenceArray.set(length, referenceEntryNewEntry);
                        this.count++;
                        evictEntries(referenceEntryNewEntry);
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v9 = valueReference.get();
                        if (v9 != null) {
                            if (z8) {
                                recordLockedRead(next, j9);
                            } else {
                                this.modCount++;
                                enqueueNotification(k9, i9, v9, valueReference.getWeight(), RemovalCause.REPLACED);
                                setValue(next, k9, v8, j9);
                                evictEntries(next);
                            }
                            return v9;
                        }
                        this.modCount++;
                        if (valueReference.isActive()) {
                            enqueueNotification(k9, i9, v9, valueReference.getWeight(), RemovalCause.COLLECTED);
                            setValue(next, k9, v8, j9);
                            i10 = this.count;
                        } else {
                            setValue(next, k9, v8, j9);
                            i10 = this.count + 1;
                        }
                        this.count = i10;
                        evictEntries(next);
                    } else {
                        next = next.getNext();
                    }
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public boolean reclaimKey(ReferenceEntry<K, V> referenceEntry, int i9) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry2; next != null; next = next.getNext()) {
                    if (next == referenceEntry) {
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry2, next, next.getKey(), i9, next.getValueReference().get(), next.getValueReference(), RemovalCause.COLLECTED);
                        int i10 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i10;
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public boolean reclaimValue(K k9, int i9, ValueReference<K, V> valueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                for (ReferenceEntry<K, V> next = referenceEntry; next != null; next = next.getNext()) {
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        if (next.getValueReference() != valueReference) {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                postWriteCleanup();
                            }
                            return false;
                        }
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i9, valueReference.get(), valueReference, RemovalCause.COLLECTED);
                        int i10 = this.count - 1;
                        atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                        this.count = i10;
                        return true;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        public void recordLockedRead(ReferenceEntry<K, V> referenceEntry, long j9) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j9);
            }
            this.accessQueue.add(referenceEntry);
        }

        public void recordRead(ReferenceEntry<K, V> referenceEntry, long j9) {
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j9);
            }
            this.recencyQueue.add(referenceEntry);
        }

        public void recordWrite(ReferenceEntry<K, V> referenceEntry, int i9, long j9) {
            drainRecencyQueue();
            this.totalWeight += i9;
            if (this.map.recordsAccess()) {
                referenceEntry.setAccessTime(j9);
            }
            if (this.map.recordsWrite()) {
                referenceEntry.setWriteTime(j9);
            }
            this.accessQueue.add(referenceEntry);
            this.writeQueue.add(referenceEntry);
        }

        public V refresh(K k9, int i9, CacheLoader<? super K, V> cacheLoader, boolean z8) {
            LoadingValueReference<K, V> loadingValueReferenceInsertLoadingValueReference = insertLoadingValueReference(k9, i9, z8);
            if (loadingValueReferenceInsertLoadingValueReference == null) {
                return null;
            }
            ListenableFuture<V> listenableFutureLoadAsync = loadAsync(k9, i9, loadingValueReferenceInsertLoadingValueReference, cacheLoader);
            if (listenableFutureLoadAsync.isDone()) {
                try {
                    return (V) Uninterruptibles.getUninterruptibly(listenableFutureLoadAsync);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        
            r9 = r5.getValueReference();
            r12 = r9.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
        
            if (r12 == null) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
        
            r2 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        
            r10 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
        
            if (r9.isActive() == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
        
            r2 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
        
            r11.modCount++;
            r13 = removeValueFromChain(r4, r5, r6, r13, r12, r9, r10);
            r2 = r11.count - 1;
            r0.set(r1, r13);
            r11.count = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
        
            return r12;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public V remove(Object obj, int i9) {
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        break;
                    }
                    next = next.getNext();
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public void removeCollectedEntry(ReferenceEntry<K, V> referenceEntry) {
            enqueueNotification(referenceEntry.getKey(), referenceEntry.getHash(), referenceEntry.getValueReference().get(), referenceEntry.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.writeQueue.remove(referenceEntry);
            this.accessQueue.remove(referenceEntry);
        }

        @VisibleForTesting
        public boolean removeEntry(ReferenceEntry<K, V> referenceEntry, int i9, RemovalCause removalCause) {
            AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i9;
            ReferenceEntry<K, V> referenceEntry2 = atomicReferenceArray.get(length);
            for (ReferenceEntry<K, V> next = referenceEntry2; next != null; next = next.getNext()) {
                if (next == referenceEntry) {
                    this.modCount++;
                    ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry2, next, next.getKey(), i9, next.getValueReference().get(), next.getValueReference(), removalCause);
                    int i10 = this.count - 1;
                    atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                    this.count = i10;
                    return true;
                }
            }
            return false;
        }

        public ReferenceEntry<K, V> removeEntryFromChain(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2) {
            int i9 = this.count;
            ReferenceEntry<K, V> next = referenceEntry2.getNext();
            while (referenceEntry != referenceEntry2) {
                ReferenceEntry<K, V> referenceEntryCopyEntry = copyEntry(referenceEntry, next);
                if (referenceEntryCopyEntry != null) {
                    next = referenceEntryCopyEntry;
                } else {
                    removeCollectedEntry(referenceEntry);
                    i9--;
                }
                referenceEntry = referenceEntry.getNext();
            }
            this.count = i9;
            return next;
        }

        public boolean removeLoadingValue(K k9, int i9, LoadingValueReference<K, V> loadingValueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() != i9 || key == null || !this.map.keyEquivalence.equivalent(k9, key)) {
                        next = next.getNext();
                    } else if (next.getValueReference() == loadingValueReference) {
                        if (loadingValueReference.isActive()) {
                            next.setValueReference(loadingValueReference.getOldValue());
                        } else {
                            atomicReferenceArray.set(length, removeEntryFromChain(referenceEntry, next));
                        }
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public ReferenceEntry<K, V> removeValueFromChain(ReferenceEntry<K, V> referenceEntry, ReferenceEntry<K, V> referenceEntry2, K k9, int i9, V v8, ValueReference<K, V> valueReference, RemovalCause removalCause) {
            enqueueNotification(k9, i9, v8, valueReference.getWeight(), removalCause);
            this.writeQueue.remove(referenceEntry2);
            this.accessQueue.remove(referenceEntry2);
            if (!valueReference.isLoading()) {
                return removeEntryFromChain(referenceEntry, referenceEntry2);
            }
            valueReference.notifyNewValue(null);
            return referenceEntry;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x006f, code lost:
        
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean replace(K k9, int i9, V v8, V v9) {
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null) {
                        if (this.map.keyEquivalence.equivalent(k9, key)) {
                            ValueReference<K, V> valueReference = next.getValueReference();
                            V v10 = valueReference.get();
                            if (v10 == null) {
                                if (valueReference.isActive()) {
                                    this.modCount++;
                                    ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i9, v10, valueReference, RemovalCause.COLLECTED);
                                    int i10 = this.count - 1;
                                    atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                                    this.count = i10;
                                }
                            } else {
                                if (this.map.valueEquivalence.equivalent(v8, v10)) {
                                    this.modCount++;
                                    enqueueNotification(k9, i9, v10, valueReference.getWeight(), RemovalCause.REPLACED);
                                    setValue(next, k9, v9, j9);
                                    evictEntries(next);
                                    return true;
                                }
                                recordLockedRead(next, j9);
                            }
                        }
                    }
                    next = next.getNext();
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public void runLockedCleanup(long j9) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(j9);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public void runUnlockedCleanup() {
            if (isHeldByCurrentThread()) {
                return;
            }
            this.map.processPendingNotifications();
        }

        public V scheduleRefresh(ReferenceEntry<K, V> referenceEntry, K k9, int i9, V v8, long j9, CacheLoader<? super K, V> cacheLoader) {
            V vRefresh;
            return (!this.map.refreshes() || j9 - referenceEntry.getWriteTime() <= this.map.refreshNanos || referenceEntry.getValueReference().isLoading() || (vRefresh = refresh(k9, i9, cacheLoader, true)) == null) ? v8 : vRefresh;
        }

        public void setValue(ReferenceEntry<K, V> referenceEntry, K k9, V v8, long j9) {
            ValueReference<K, V> valueReference = referenceEntry.getValueReference();
            int iWeigh = this.map.weigher.weigh(k9, v8);
            Preconditions.checkState(iWeigh >= 0, "Weights must be non-negative");
            referenceEntry.setValueReference(this.map.valueStrength.referenceValue(this, referenceEntry, v8, iWeigh));
            recordWrite(referenceEntry, iWeigh, j9);
            valueReference.notifyNewValue(v8);
        }

        public boolean storeLoadedValue(K k9, int i9, LoadingValueReference<K, V> loadingValueReference, V v8) {
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                int i10 = this.count + 1;
                if (i10 > this.threshold) {
                    expand();
                    i10 = this.count + 1;
                }
                int i11 = i10;
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        this.modCount++;
                        ReferenceEntry<K, V> referenceEntryNewEntry = newEntry(k9, i9, referenceEntry);
                        setValue(referenceEntryNewEntry, k9, v8, j9);
                        atomicReferenceArray.set(length, referenceEntryNewEntry);
                        this.count = i11;
                        evictEntries(referenceEntryNewEntry);
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(k9, key)) {
                        ValueReference<K, V> valueReference = next.getValueReference();
                        V v9 = valueReference.get();
                        if (loadingValueReference != valueReference && (v9 != null || valueReference == LocalCache.UNSET)) {
                            enqueueNotification(k9, i9, v8, 0, RemovalCause.REPLACED);
                            unlock();
                            postWriteCleanup();
                            return false;
                        }
                        this.modCount++;
                        if (loadingValueReference.isActive()) {
                            enqueueNotification(k9, i9, v9, loadingValueReference.getWeight(), v9 == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                            i11--;
                        }
                        setValue(next, k9, v8, j9);
                        this.count = i11;
                        evictEntries(next);
                    } else {
                        next = next.getNext();
                    }
                }
                return true;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        public void tryExpireEntries(long j9) {
            if (tryLock()) {
                try {
                    expireEntries(j9);
                } finally {
                    unlock();
                }
            }
        }

        public V waitForLoadingValue(ReferenceEntry<K, V> referenceEntry, K k9, ValueReference<K, V> valueReference) {
            if (!valueReference.isLoading()) {
                throw new AssertionError();
            }
            Preconditions.checkState(!Thread.holdsLock(referenceEntry), "Recursive load of: %s", k9);
            try {
                V vWaitForValue = valueReference.waitForValue();
                if (vWaitForValue != null) {
                    recordRead(referenceEntry, this.map.ticker.read());
                    return vWaitForValue;
                }
                throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + k9 + ".");
            } finally {
                this.statsCounter.recordMisses(1);
            }
        }

        public V get(Object obj, int i9) {
            try {
                if (this.count != 0) {
                    long j9 = this.map.ticker.read();
                    ReferenceEntry<K, V> liveEntry = getLiveEntry(obj, i9, j9);
                    if (liveEntry == null) {
                        return null;
                    }
                    V v8 = liveEntry.getValueReference().get();
                    if (v8 != null) {
                        recordRead(liveEntry, j9);
                        return scheduleRefresh(liveEntry, liveEntry.getKey(), i9, v8, j9, this.map.defaultLoader);
                    }
                    tryDrainReferenceQueues();
                }
                return null;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        
            r10 = r6.getValueReference();
            r9 = r10.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
        
            if (r12.map.valueEquivalence.equivalent(r15, r9) == false) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
        
            r13 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
        
            if (r9 != null) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
        
            if (r10.isActive() == false) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
        
            r13 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
        
            r12.modCount++;
            r14 = removeValueFromChain(r5, r6, r7, r14, r9, r10, r13);
            r15 = r12.count - 1;
            r0.set(r1, r14);
            r12.count = r15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
        
            if (r13 != com.google.common.cache.RemovalCause.EXPLICIT) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0070, code lost:
        
            r2 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
        
            return r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean remove(Object obj, int i9, Object obj2) {
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                boolean z8 = true;
                int length = (atomicReferenceArray.length() - 1) & i9;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        break;
                    }
                    next = next.getNext();
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0072, code lost:
        
            return null;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public V replace(K k9, int i9, V v8) {
            lock();
            try {
                long j9 = this.map.ticker.read();
                preWriteCleanup(j9);
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.table;
                int length = i9 & (atomicReferenceArray.length() - 1);
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(length);
                ReferenceEntry<K, V> next = referenceEntry;
                while (true) {
                    if (next == null) {
                        break;
                    }
                    K key = next.getKey();
                    if (next.getHash() == i9 && key != null) {
                        if (this.map.keyEquivalence.equivalent(k9, key)) {
                            ValueReference<K, V> valueReference = next.getValueReference();
                            V v9 = valueReference.get();
                            if (v9 == null) {
                                if (valueReference.isActive()) {
                                    this.modCount++;
                                    ReferenceEntry<K, V> referenceEntryRemoveValueFromChain = removeValueFromChain(referenceEntry, next, key, i9, v9, valueReference, RemovalCause.COLLECTED);
                                    int i10 = this.count - 1;
                                    atomicReferenceArray.set(length, referenceEntryRemoveValueFromChain);
                                    this.count = i10;
                                }
                            } else {
                                this.modCount++;
                                enqueueNotification(k9, i9, v9, valueReference.getWeight(), RemovalCause.REPLACED);
                                setValue(next, k9, v8, j9);
                                evictEntries(next);
                                return v9;
                            }
                        }
                    }
                    next = next.getNext();
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }
    }
}
