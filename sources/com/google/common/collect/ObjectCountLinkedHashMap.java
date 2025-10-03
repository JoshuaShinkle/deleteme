package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
class ObjectCountLinkedHashMap<K> extends ObjectCountHashMap<K> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;

    @VisibleForTesting
    transient long[] links;

    public abstract class LinkedItr<T> implements Iterator<T> {
        private int expectedModCount;
        private int nextEntry;
        private int toRemove;

        private LinkedItr() {
            this.nextEntry = ObjectCountLinkedHashMap.this.firstEntry;
            this.toRemove = -1;
            this.expectedModCount = ObjectCountLinkedHashMap.this.modCount;
        }

        private void checkForConcurrentModification() {
            if (ObjectCountLinkedHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public abstract T getOutput(int i9);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextEntry != -2;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T output = getOutput(this.nextEntry);
            int i9 = this.nextEntry;
            this.toRemove = i9;
            this.nextEntry = ObjectCountLinkedHashMap.this.getSuccessor(i9);
            return output;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.toRemove != -1);
            ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
            objectCountLinkedHashMap.remove(objectCountLinkedHashMap.keys[this.toRemove]);
            if (this.nextEntry >= ObjectCountLinkedHashMap.this.size()) {
                this.nextEntry = this.toRemove;
            }
            this.expectedModCount = ObjectCountLinkedHashMap.this.modCount;
            this.toRemove = -1;
        }
    }

    public ObjectCountLinkedHashMap() {
        this(3);
    }

    public static <K> ObjectCountLinkedHashMap<K> create() {
        return new ObjectCountLinkedHashMap<>();
    }

    public static <K> ObjectCountLinkedHashMap<K> createWithExpectedSize(int i9) {
        return new ObjectCountLinkedHashMap<>(i9);
    }

    private int getPredecessor(int i9) {
        return (int) (this.links[i9] >>> 32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSuccessor(int i9) {
        return (int) this.links[i9];
    }

    private void setPredecessor(int i9, int i10) {
        long[] jArr = this.links;
        jArr[i9] = (jArr[i9] & 4294967295L) | (i10 << 32);
    }

    private void setSucceeds(int i9, int i10) {
        if (i9 == -2) {
            this.firstEntry = i10;
        } else {
            setSuccessor(i9, i10);
        }
        if (i10 == -2) {
            this.lastEntry = i9;
        } else {
            setPredecessor(i10, i9);
        }
    }

    private void setSuccessor(int i9, int i10) {
        long[] jArr = this.links;
        jArr[i9] = (jArr[i9] & (-4294967296L)) | (i10 & 4294967295L);
    }

    @Override // com.google.common.collect.ObjectCountHashMap, com.google.common.collect.AbstractObjectCountMap
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.ObjectCountHashMap, com.google.common.collect.AbstractObjectCountMap
    public Set<Multiset.Entry<K>> createEntrySet() {
        return new AbstractObjectCountMap<K>.EntrySetView() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<K>> iterator() {
                return new ObjectCountLinkedHashMap<K>.LinkedItr<Multiset.Entry<K>>() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.2.1
                    {
                        ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
                    }

                    @Override // com.google.common.collect.ObjectCountLinkedHashMap.LinkedItr
                    public Multiset.Entry<K> getOutput(int i9) {
                        return new AbstractObjectCountMap.MapEntry(i9);
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public Set<K> createKeySet() {
        return new AbstractObjectCountMap<K>.KeySetView() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.1
            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<K> iterator() {
                return new ObjectCountLinkedHashMap<K>.LinkedItr<K>() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.1.1
                    {
                        ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
                    }

                    @Override // com.google.common.collect.ObjectCountLinkedHashMap.LinkedItr
                    public K getOutput(int i9) {
                        return (K) ObjectCountLinkedHashMap.this.keys[i9];
                    }
                };
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) ObjectArrays.toArrayImpl(this, tArr);
            }
        };
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int firstIndex() {
        int i9 = this.firstEntry;
        if (i9 == -2) {
            return -1;
        }
        return i9;
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void init(int i9, float f9) {
        super.init(i9, f9);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[i9];
        this.links = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void insertEntry(int i9, K k9, int i10, int i11) {
        super.insertEntry(i9, k9, i10, i11);
        setSucceeds(this.lastEntry, i9);
        setSucceeds(i9, -2);
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void moveLastEntry(int i9) {
        int size = size() - 1;
        setSucceeds(getPredecessor(i9), getSuccessor(i9));
        if (i9 < size) {
            setSucceeds(getPredecessor(size), i9);
            setSucceeds(i9, getSuccessor(size));
        }
        super.moveLastEntry(i9);
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int nextIndex(int i9) {
        int successor = getSuccessor(i9);
        if (successor == -2) {
            return -1;
        }
        return successor;
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void resizeEntries(int i9) {
        super.resizeEntries(i9);
        this.links = Arrays.copyOf(this.links, i9);
    }

    public ObjectCountLinkedHashMap(int i9) {
        this(i9, 1.0f);
    }

    public ObjectCountLinkedHashMap(int i9, float f9) {
        super(i9, f9);
    }

    public ObjectCountLinkedHashMap(AbstractObjectCountMap<K> abstractObjectCountMap) {
        init(abstractObjectCountMap.size(), 1.0f);
        int iFirstIndex = abstractObjectCountMap.firstIndex();
        while (iFirstIndex != -1) {
            put(abstractObjectCountMap.getKey(iFirstIndex), abstractObjectCountMap.getValue(iFirstIndex));
            iFirstIndex = abstractObjectCountMap.nextIndex(iFirstIndex);
        }
    }
}
