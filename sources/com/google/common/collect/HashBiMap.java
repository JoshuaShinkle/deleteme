package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class HashBiMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final double LOAD_FACTOR = 1.0d;

    @GwtIncompatible
    private static final long serialVersionUID = 0;
    private transient BiEntry<K, V> firstInKeyInsertionOrder;
    private transient BiEntry<K, V>[] hashTableKToV;
    private transient BiEntry<K, V>[] hashTableVToK;

    @RetainedWith
    private transient BiMap<V, K> inverse;
    private transient BiEntry<K, V> lastInKeyInsertionOrder;
    private transient int mask;
    private transient int modCount;
    private transient int size;

    public static final class BiEntry<K, V> extends ImmutableEntry<K, V> {
        final int keyHash;
        BiEntry<K, V> nextInKToVBucket;
        BiEntry<K, V> nextInKeyInsertionOrder;
        BiEntry<K, V> nextInVToKBucket;
        BiEntry<K, V> prevInKeyInsertionOrder;
        final int valueHash;

        public BiEntry(K k9, int i9, V v8, int i10) {
            super(k9, v8);
            this.keyHash = i9;
            this.valueHash = i10;
        }
    }

    public final class Inverse extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {

        /* renamed from: com.google.common.collect.HashBiMap$Inverse$1 */
        public class C36561 extends Maps.EntrySet<V, K> {
            public C36561() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<V, K>> iterator() {
                return new HashBiMap<K, V>.Itr<Map.Entry<V, K>>() { // from class: com.google.common.collect.HashBiMap.Inverse.1.1

                    /* renamed from: com.google.common.collect.HashBiMap$Inverse$1$1$InverseEntry */
                    public class InverseEntry extends AbstractMapEntry<V, K> {
                        BiEntry<K, V> delegate;

                        public InverseEntry(BiEntry<K, V> biEntry) {
                            this.delegate = biEntry;
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public V getKey() {
                            return this.delegate.value;
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public K getValue() {
                            return this.delegate.key;
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public K setValue(K k9) {
                            K k10 = this.delegate.key;
                            int iSmearedHash = Hashing.smearedHash(k9);
                            if (iSmearedHash == this.delegate.keyHash && Objects.equal(k9, k10)) {
                                return k9;
                            }
                            Preconditions.checkArgument(HashBiMap.this.seekByKey(k9, iSmearedHash) == null, "value already present: %s", k9);
                            HashBiMap.this.delete(this.delegate);
                            BiEntry<K, V> biEntry = this.delegate;
                            BiEntry<K, V> biEntry2 = new BiEntry<>(k9, iSmearedHash, biEntry.value, biEntry.valueHash);
                            this.delegate = biEntry2;
                            HashBiMap.this.insert(biEntry2, null);
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            anonymousClass1.expectedModCount = HashBiMap.this.modCount;
                            return k10;
                        }
                    }

                    {
                        HashBiMap hashBiMap = HashBiMap.this;
                    }

                    @Override // com.google.common.collect.HashBiMap.Itr
                    public Map.Entry<V, K> output(BiEntry<K, V> biEntry) {
                        return new InverseEntry(biEntry);
                    }
                };
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public Map<V, K> map() {
                return Inverse.this;
            }
        }

        public final class InverseKeySet extends Maps.KeySet<V, K> {
            public InverseKeySet() {
                super(Inverse.this);
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<V> iterator() {
                return new HashBiMap<K, V>.Itr<V>() { // from class: com.google.common.collect.HashBiMap.Inverse.InverseKeySet.1
                    {
                        HashBiMap hashBiMap = HashBiMap.this;
                    }

                    @Override // com.google.common.collect.HashBiMap.Itr
                    public V output(BiEntry<K, V> biEntry) {
                        return biEntry.value;
                    }
                };
            }

            @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                BiEntry biEntrySeekByValue = HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj));
                if (biEntrySeekByValue == null) {
                    return false;
                }
                HashBiMap.this.delete(biEntrySeekByValue);
                return true;
            }
        }

        private Inverse() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            forward().clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return forward().containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            return new C36561();
        }

        @Override // com.google.common.collect.BiMap
        public K forcePut(V v8, K k9) {
            return (K) HashBiMap.this.putInverse(v8, k9, true);
        }

        public BiMap<K, V> forward() {
            return HashBiMap.this;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K get(Object obj) {
            return (K) Maps.keyOrNull(HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj)));
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return forward();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return new InverseKeySet();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public K put(V v8, K k9) {
            return (K) HashBiMap.this.putInverse(v8, k9, false);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K remove(Object obj) {
            BiEntry biEntrySeekByValue = HashBiMap.this.seekByValue(obj, Hashing.smearedHash(obj));
            if (biEntrySeekByValue == null) {
                return null;
            }
            HashBiMap.this.delete(biEntrySeekByValue);
            biEntrySeekByValue.prevInKeyInsertionOrder = null;
            biEntrySeekByValue.nextInKeyInsertionOrder = null;
            return biEntrySeekByValue.key;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return HashBiMap.this.size;
        }

        public Object writeReplace() {
            return new InverseSerializedForm(HashBiMap.this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> values() {
            return forward().keySet();
        }
    }

    public static final class InverseSerializedForm<K, V> implements Serializable {
        private final HashBiMap<K, V> bimap;

        public InverseSerializedForm(HashBiMap<K, V> hashBiMap) {
            this.bimap = hashBiMap;
        }

        public Object readResolve() {
            return this.bimap.inverse();
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        int expectedModCount;
        BiEntry<K, V> next;
        BiEntry<K, V> toRemove = null;

        public Itr() {
            this.next = HashBiMap.this.firstInKeyInsertionOrder;
            this.expectedModCount = HashBiMap.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (HashBiMap.this.modCount == this.expectedModCount) {
                return this.next != null;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BiEntry<K, V> biEntry = this.next;
            this.next = biEntry.nextInKeyInsertionOrder;
            this.toRemove = biEntry;
            return output(biEntry);
        }

        public abstract T output(BiEntry<K, V> biEntry);

        @Override // java.util.Iterator
        public void remove() {
            if (HashBiMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            CollectPreconditions.checkRemove(this.toRemove != null);
            HashBiMap.this.delete(this.toRemove);
            this.expectedModCount = HashBiMap.this.modCount;
            this.toRemove = null;
        }
    }

    public final class KeySet extends Maps.KeySet<K, V> {
        public KeySet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new HashBiMap<K, V>.Itr<K>() { // from class: com.google.common.collect.HashBiMap.KeySet.1
                {
                    HashBiMap hashBiMap = HashBiMap.this;
                }

                @Override // com.google.common.collect.HashBiMap.Itr
                public K output(BiEntry<K, V> biEntry) {
                    return biEntry.key;
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            BiEntry biEntrySeekByKey = HashBiMap.this.seekByKey(obj, Hashing.smearedHash(obj));
            if (biEntrySeekByKey == null) {
                return false;
            }
            HashBiMap.this.delete(biEntrySeekByKey);
            biEntrySeekByKey.prevInKeyInsertionOrder = null;
            biEntrySeekByKey.nextInKeyInsertionOrder = null;
            return true;
        }
    }

    private HashBiMap(int i9) {
        init(i9);
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    private BiEntry<K, V>[] createTable(int i9) {
        return new BiEntry[i9];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete(BiEntry<K, V> biEntry) {
        BiEntry<K, V> biEntry2;
        int i9 = biEntry.keyHash & this.mask;
        BiEntry<K, V> biEntry3 = null;
        BiEntry<K, V> biEntry4 = null;
        for (BiEntry<K, V> biEntry5 = this.hashTableKToV[i9]; biEntry5 != biEntry; biEntry5 = biEntry5.nextInKToVBucket) {
            biEntry4 = biEntry5;
        }
        if (biEntry4 == null) {
            this.hashTableKToV[i9] = biEntry.nextInKToVBucket;
        } else {
            biEntry4.nextInKToVBucket = biEntry.nextInKToVBucket;
        }
        int i10 = biEntry.valueHash & this.mask;
        BiEntry<K, V> biEntry6 = this.hashTableVToK[i10];
        while (true) {
            biEntry2 = biEntry3;
            biEntry3 = biEntry6;
            if (biEntry3 == biEntry) {
                break;
            } else {
                biEntry6 = biEntry3.nextInVToKBucket;
            }
        }
        if (biEntry2 == null) {
            this.hashTableVToK[i10] = biEntry.nextInVToKBucket;
        } else {
            biEntry2.nextInVToKBucket = biEntry.nextInVToKBucket;
        }
        BiEntry<K, V> biEntry7 = biEntry.prevInKeyInsertionOrder;
        if (biEntry7 == null) {
            this.firstInKeyInsertionOrder = biEntry.nextInKeyInsertionOrder;
        } else {
            biEntry7.nextInKeyInsertionOrder = biEntry.nextInKeyInsertionOrder;
        }
        BiEntry<K, V> biEntry8 = biEntry.nextInKeyInsertionOrder;
        if (biEntry8 == null) {
            this.lastInKeyInsertionOrder = biEntry7;
        } else {
            biEntry8.prevInKeyInsertionOrder = biEntry7;
        }
        this.size--;
        this.modCount++;
    }

    private void init(int i9) {
        CollectPreconditions.checkNonnegative(i9, "expectedSize");
        int iClosedTableSize = Hashing.closedTableSize(i9, LOAD_FACTOR);
        this.hashTableKToV = createTable(iClosedTableSize);
        this.hashTableVToK = createTable(iClosedTableSize);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.size = 0;
        this.mask = iClosedTableSize - 1;
        this.modCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insert(BiEntry<K, V> biEntry, BiEntry<K, V> biEntry2) {
        int i9 = biEntry.keyHash;
        int i10 = this.mask;
        int i11 = i9 & i10;
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        biEntry.nextInKToVBucket = biEntryArr[i11];
        biEntryArr[i11] = biEntry;
        int i12 = biEntry.valueHash & i10;
        BiEntry<K, V>[] biEntryArr2 = this.hashTableVToK;
        biEntry.nextInVToKBucket = biEntryArr2[i12];
        biEntryArr2[i12] = biEntry;
        if (biEntry2 == null) {
            BiEntry<K, V> biEntry3 = this.lastInKeyInsertionOrder;
            biEntry.prevInKeyInsertionOrder = biEntry3;
            biEntry.nextInKeyInsertionOrder = null;
            if (biEntry3 == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry3.nextInKeyInsertionOrder = biEntry;
            }
            this.lastInKeyInsertionOrder = biEntry;
        } else {
            BiEntry<K, V> biEntry4 = biEntry2.prevInKeyInsertionOrder;
            biEntry.prevInKeyInsertionOrder = biEntry4;
            if (biEntry4 == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry4.nextInKeyInsertionOrder = biEntry;
            }
            BiEntry<K, V> biEntry5 = biEntry2.nextInKeyInsertionOrder;
            biEntry.nextInKeyInsertionOrder = biEntry5;
            if (biEntry5 == null) {
                this.lastInKeyInsertionOrder = biEntry;
            } else {
                biEntry5.prevInKeyInsertionOrder = biEntry;
            }
        }
        this.size++;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public K putInverse(V v8, K k9, boolean z8) {
        int iSmearedHash = Hashing.smearedHash(v8);
        int iSmearedHash2 = Hashing.smearedHash(k9);
        BiEntry<K, V> biEntrySeekByValue = seekByValue(v8, iSmearedHash);
        if (biEntrySeekByValue != null && iSmearedHash2 == biEntrySeekByValue.keyHash && Objects.equal(k9, biEntrySeekByValue.key)) {
            return k9;
        }
        BiEntry<K, V> biEntrySeekByKey = seekByKey(k9, iSmearedHash2);
        if (biEntrySeekByKey != null) {
            if (!z8) {
                throw new IllegalArgumentException("value already present: " + k9);
            }
            delete(biEntrySeekByKey);
        }
        if (biEntrySeekByValue != null) {
            delete(biEntrySeekByValue);
        }
        insert(new BiEntry<>(k9, iSmearedHash2, v8, iSmearedHash), biEntrySeekByKey);
        if (biEntrySeekByKey != null) {
            biEntrySeekByKey.prevInKeyInsertionOrder = null;
            biEntrySeekByKey.nextInKeyInsertionOrder = null;
        }
        rehashIfNecessary();
        return (K) Maps.keyOrNull(biEntrySeekByValue);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        init(16);
        Serialization.populateMap(this, objectInputStream, Serialization.readCount(objectInputStream));
    }

    private void rehashIfNecessary() {
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        if (Hashing.needsResizing(this.size, biEntryArr.length, LOAD_FACTOR)) {
            int length = biEntryArr.length * 2;
            this.hashTableKToV = createTable(length);
            this.hashTableVToK = createTable(length);
            this.mask = length - 1;
            this.size = 0;
            for (BiEntry<K, V> biEntry = this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.nextInKeyInsertionOrder) {
                insert(biEntry, biEntry);
            }
            this.modCount++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BiEntry<K, V> seekByKey(Object obj, int i9) {
        for (BiEntry<K, V> biEntry = this.hashTableKToV[this.mask & i9]; biEntry != null; biEntry = biEntry.nextInKToVBucket) {
            if (i9 == biEntry.keyHash && Objects.equal(obj, biEntry.key)) {
                return biEntry;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BiEntry<K, V> seekByValue(Object obj, int i9) {
        for (BiEntry<K, V> biEntry = this.hashTableVToK[this.mask & i9]; biEntry != null; biEntry = biEntry.nextInVToKBucket) {
            if (i9 == biEntry.valueHash && Objects.equal(obj, biEntry.value)) {
                return biEntry;
            }
        }
        return null;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMap(this, objectOutputStream);
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        this.size = 0;
        Arrays.fill(this.hashTableKToV, (Object) null);
        Arrays.fill(this.hashTableVToK, (Object) null);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return seekByKey(obj, Hashing.smearedHash(obj)) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        return seekByValue(obj, Hashing.smearedHash(obj)) != null;
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new HashBiMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.HashBiMap.1

            /* renamed from: com.google.common.collect.HashBiMap$1$MapEntry */
            public class MapEntry extends AbstractMapEntry<K, V> {
                BiEntry<K, V> delegate;

                public MapEntry(BiEntry<K, V> biEntry) {
                    this.delegate = biEntry;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public K getKey() {
                    return this.delegate.key;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V getValue() {
                    return this.delegate.value;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V setValue(V v8) {
                    V v9 = this.delegate.value;
                    int iSmearedHash = Hashing.smearedHash(v8);
                    if (iSmearedHash == this.delegate.valueHash && Objects.equal(v8, v9)) {
                        return v8;
                    }
                    Preconditions.checkArgument(HashBiMap.this.seekByValue(v8, iSmearedHash) == null, "value already present: %s", v8);
                    HashBiMap.this.delete(this.delegate);
                    BiEntry<K, V> biEntry = this.delegate;
                    BiEntry<K, V> biEntry2 = new BiEntry<>(biEntry.key, biEntry.keyHash, v8, iSmearedHash);
                    HashBiMap.this.insert(biEntry2, this.delegate);
                    BiEntry<K, V> biEntry3 = this.delegate;
                    biEntry3.prevInKeyInsertionOrder = null;
                    biEntry3.nextInKeyInsertionOrder = null;
                    C36551 c36551 = C36551.this;
                    c36551.expectedModCount = HashBiMap.this.modCount;
                    C36551 c365512 = C36551.this;
                    if (c365512.toRemove == this.delegate) {
                        c365512.toRemove = biEntry2;
                    }
                    this.delegate = biEntry2;
                    return v9;
                }
            }

            @Override // com.google.common.collect.HashBiMap.Itr
            public Map.Entry<K, V> output(BiEntry<K, V> biEntry) {
                return new MapEntry(biEntry);
            }
        };
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    public V forcePut(K k9, V v8) {
        return put(k9, v8, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        return (V) Maps.valueOrNull(seekByKey(obj, Hashing.smearedHash(obj)));
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap != null) {
            return biMap;
        }
        Inverse inverse = new Inverse();
        this.inverse = inverse;
        return inverse;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    public V put(K k9, V v8) {
        return put(k9, v8, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object obj) {
        BiEntry<K, V> biEntrySeekByKey = seekByKey(obj, Hashing.smearedHash(obj));
        if (biEntrySeekByKey == null) {
            return null;
        }
        delete(biEntrySeekByKey);
        biEntrySeekByKey.prevInKeyInsertionOrder = null;
        biEntrySeekByKey.nextInKeyInsertionOrder = null;
        return biEntrySeekByKey.value;
    }

    @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    public static <K, V> HashBiMap<K, V> create(int i9) {
        return new HashBiMap<>(i9);
    }

    private V put(K k9, V v8, boolean z8) {
        int iSmearedHash = Hashing.smearedHash(k9);
        int iSmearedHash2 = Hashing.smearedHash(v8);
        BiEntry<K, V> biEntrySeekByKey = seekByKey(k9, iSmearedHash);
        if (biEntrySeekByKey != null && iSmearedHash2 == biEntrySeekByKey.valueHash && Objects.equal(v8, biEntrySeekByKey.value)) {
            return v8;
        }
        BiEntry<K, V> biEntrySeekByValue = seekByValue(v8, iSmearedHash2);
        if (biEntrySeekByValue != null) {
            if (!z8) {
                throw new IllegalArgumentException("value already present: " + v8);
            }
            delete(biEntrySeekByValue);
        }
        BiEntry<K, V> biEntry = new BiEntry<>(k9, iSmearedHash, v8, iSmearedHash2);
        if (biEntrySeekByKey == null) {
            insert(biEntry, null);
            rehashIfNecessary();
            return null;
        }
        delete(biEntrySeekByKey);
        insert(biEntry, biEntrySeekByKey);
        biEntrySeekByKey.prevInKeyInsertionOrder = null;
        biEntrySeekByKey.nextInKeyInsertionOrder = null;
        rehashIfNecessary();
        return biEntrySeekByKey.value;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<V> values() {
        return inverse().keySet();
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> hashBiMapCreate = create(map.size());
        hashBiMapCreate.putAll(map);
        return hashBiMapCreate;
    }
}
