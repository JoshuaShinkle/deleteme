package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
abstract class AbstractObjectCountMap<K> {
    static final int UNSET = -1;
    private transient Set<Multiset.Entry<K>> entrySetView;
    private transient Set<K> keySetView;
    transient Object[] keys;
    transient int modCount;
    transient int size;
    transient int[] values;

    public abstract class EntrySetView extends Sets.ImprovedAbstractSet<Multiset.Entry<K>> {
        public EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            int iIndexOf = AbstractObjectCountMap.this.indexOf(entry.getElement());
            return iIndexOf != -1 && AbstractObjectCountMap.this.values[iIndexOf] == entry.getCount();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            int iIndexOf = AbstractObjectCountMap.this.indexOf(entry.getElement());
            if (iIndexOf == -1 || AbstractObjectCountMap.this.values[iIndexOf] != entry.getCount()) {
                return false;
            }
            AbstractObjectCountMap.this.removeEntry(iIndexOf);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractObjectCountMap.this.size;
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        int expectedModCount;
        boolean nextCalled = false;
        int index = 0;

        public Itr() {
            this.expectedModCount = AbstractObjectCountMap.this.modCount;
        }

        public void checkForConcurrentModification() {
            if (AbstractObjectCountMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public abstract T getOutput(int i9);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < AbstractObjectCountMap.this.size;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.nextCalled = true;
            int i9 = this.index;
            this.index = i9 + 1;
            return getOutput(i9);
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.nextCalled);
            this.expectedModCount++;
            int i9 = this.index - 1;
            this.index = i9;
            AbstractObjectCountMap.this.removeEntry(i9);
            this.nextCalled = false;
        }
    }

    public class KeySetView extends Sets.ImprovedAbstractSet<K> {
        public KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new AbstractObjectCountMap<K>.Itr<K>() { // from class: com.google.common.collect.AbstractObjectCountMap.KeySetView.1
                {
                    AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
                }

                @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                public K getOutput(int i9) {
                    return (K) AbstractObjectCountMap.this.keys[i9];
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractObjectCountMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
            return ObjectArrays.copyAsObjectArray(abstractObjectCountMap.keys, 0, abstractObjectCountMap.size);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
            return (T[]) ObjectArrays.toArrayImpl(abstractObjectCountMap.keys, 0, abstractObjectCountMap.size, tArr);
        }
    }

    public class MapEntry extends Multisets.AbstractEntry<K> {
        final K key;
        int lastKnownIndex;

        public MapEntry(int i9) {
            this.key = (K) AbstractObjectCountMap.this.keys[i9];
            this.lastKnownIndex = i9;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            int i9 = this.lastKnownIndex;
            if (i9 == -1) {
                return 0;
            }
            return AbstractObjectCountMap.this.values[i9];
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return this.key;
        }

        @CanIgnoreReturnValue
        public int setCount(int i9) {
            updateLastKnownIndex();
            int i10 = this.lastKnownIndex;
            if (i10 == -1) {
                AbstractObjectCountMap.this.put(this.key, i9);
                return 0;
            }
            int[] iArr = AbstractObjectCountMap.this.values;
            int i11 = iArr[i10];
            iArr[i10] = i9;
            return i11;
        }

        public void updateLastKnownIndex() {
            int i9 = this.lastKnownIndex;
            if (i9 == -1 || i9 >= AbstractObjectCountMap.this.size() || !Objects.equal(this.key, AbstractObjectCountMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = AbstractObjectCountMap.this.indexOf(this.key);
            }
        }
    }

    public abstract void clear();

    public abstract boolean containsKey(Object obj);

    public abstract Set<Multiset.Entry<K>> createEntrySet();

    public Set<K> createKeySet() {
        return new KeySetView();
    }

    public Set<Multiset.Entry<K>> entrySet() {
        Set<Multiset.Entry<K>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<K>> setCreateEntrySet = createEntrySet();
        this.entrySetView = setCreateEntrySet;
        return setCreateEntrySet;
    }

    public int firstIndex() {
        return 0;
    }

    public abstract int get(Object obj);

    public Multiset.Entry<K> getEntry(int i9) {
        Preconditions.checkElementIndex(i9, this.size);
        return new MapEntry(i9);
    }

    public K getKey(int i9) {
        Preconditions.checkElementIndex(i9, this.size);
        return (K) this.keys[i9];
    }

    public int getValue(int i9) {
        Preconditions.checkElementIndex(i9, this.size);
        return this.values[i9];
    }

    public abstract int indexOf(Object obj);

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> setCreateKeySet = createKeySet();
        this.keySetView = setCreateKeySet;
        return setCreateKeySet;
    }

    public int nextIndex(int i9) {
        int i10 = i9 + 1;
        if (i10 < this.size) {
            return i10;
        }
        return -1;
    }

    @CanIgnoreReturnValue
    public abstract int put(K k9, int i9);

    @CanIgnoreReturnValue
    public abstract int remove(Object obj);

    @CanIgnoreReturnValue
    public abstract int removeEntry(int i9);

    public int size() {
        return this.size;
    }
}
