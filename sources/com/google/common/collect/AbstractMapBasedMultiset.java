package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {

    @GwtIncompatible
    private static final long serialVersionUID = -2250766705698539974L;
    transient AbstractObjectCountMap<E> backingMap;
    private transient long size = super.size();

    public class MapBasedMultisetIterator implements Iterator<E> {
        Multiset.Entry<E> currentEntry;
        final Iterator<Multiset.Entry<E>> entryIterator;
        int occurrencesLeft = 0;
        boolean canRemove = false;

        public MapBasedMultisetIterator() {
            this.entryIterator = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.occurrencesLeft > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.occurrencesLeft == 0) {
                Multiset.Entry<E> next = this.entryIterator.next();
                this.currentEntry = next;
                this.occurrencesLeft = next.getCount();
            }
            this.occurrencesLeft--;
            this.canRemove = true;
            return this.currentEntry.getElement();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            int count = this.currentEntry.getCount();
            if (count <= 0) {
                throw new ConcurrentModificationException();
            }
            if (count == 1) {
                this.entryIterator.remove();
            } else {
                ((AbstractObjectCountMap.MapEntry) this.currentEntry).setCount(count - 1);
            }
            AbstractMapBasedMultiset.access$010(AbstractMapBasedMultiset.this);
            this.canRemove = false;
        }
    }

    public AbstractMapBasedMultiset(AbstractObjectCountMap<E> abstractObjectCountMap) {
        this.backingMap = (AbstractObjectCountMap) Preconditions.checkNotNull(abstractObjectCountMap);
    }

    public static /* synthetic */ long access$010(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        long j9 = abstractMapBasedMultiset.size;
        abstractMapBasedMultiset.size = j9 - 1;
        return j9;
    }

    @GwtIncompatible
    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("Stream data required");
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(E e9, int i9) {
        if (i9 == 0) {
            return count(e9);
        }
        Preconditions.checkArgument(i9 > 0, "occurrences cannot be negative: %s", i9);
        int i10 = this.backingMap.get(e9);
        long j9 = i9;
        long j10 = i10 + j9;
        Preconditions.checkArgument(j10 <= 2147483647L, "too many occurrences: %s", j10);
        this.backingMap.put(e9, (int) j10);
        this.size += j9;
        return i10;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.backingMap.clear();
        this.size = 0L;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int count(Object obj) {
        return this.backingMap.get(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Set<E> createElementSet() {
        return this.backingMap.keySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new AbstractMultiset.EntrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return this.backingMap.size();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        final Iterator<Multiset.Entry<E>> it = this.backingMap.entrySet().iterator();
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.AbstractMapBasedMultiset.1
            boolean canRemove;
            Multiset.Entry<E> toRemove;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.canRemove);
                AbstractMapBasedMultiset.this.size -= this.toRemove.getCount();
                it.remove();
                this.canRemove = false;
                this.toRemove = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                Multiset.Entry<E> entry = (Multiset.Entry) it.next();
                this.toRemove = entry;
                this.canRemove = true;
                return entry;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return new MapBasedMultisetIterator();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object obj, int i9) {
        if (i9 == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i9 > 0, "occurrences cannot be negative: %s", i9);
        int i10 = this.backingMap.get(obj);
        if (i10 > i9) {
            this.backingMap.put(obj, i10 - i9);
        } else {
            this.backingMap.remove(obj);
            i9 = i10;
        }
        this.size -= i9;
        return i10;
    }

    public void setBackingMap(AbstractObjectCountMap<E> abstractObjectCountMap) {
        this.backingMap = abstractObjectCountMap;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(E e9, int i9) {
        CollectPreconditions.checkNonnegative(i9, "count");
        AbstractObjectCountMap<E> abstractObjectCountMap = this.backingMap;
        int iRemove = i9 == 0 ? abstractObjectCountMap.remove(e9) : abstractObjectCountMap.put(e9, i9);
        this.size += i9 - iRemove;
        return iRemove;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
