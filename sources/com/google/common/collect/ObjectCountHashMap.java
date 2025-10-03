package com.google.common.collect;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
class ObjectCountHashMap<K> extends AbstractObjectCountMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;

    @VisibleForTesting
    transient long[] entries;
    private transient float loadFactor;
    private transient int[] table;
    private transient int threshold;

    public class HashEntrySetView extends AbstractObjectCountMap<K>.EntrySetView {
        public HashEntrySetView() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Multiset.Entry<K>> iterator() {
            return new AbstractObjectCountMap<K>.Itr<Multiset.Entry<K>>() { // from class: com.google.common.collect.ObjectCountHashMap.HashEntrySetView.1
                {
                    ObjectCountHashMap objectCountHashMap = ObjectCountHashMap.this;
                }

                @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                public Multiset.Entry<K> getOutput(int i9) {
                    return new AbstractObjectCountMap.MapEntry(i9);
                }
            };
        }
    }

    public ObjectCountHashMap() {
        init(3, DEFAULT_LOAD_FACTOR);
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i9) {
        return new ObjectCountHashMap<>(i9);
    }

    private static int getHash(long j9) {
        return (int) (j9 >>> 32);
    }

    private static int getNext(long j9) {
        return (int) j9;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static long[] newEntries(int i9) {
        long[] jArr = new long[i9];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private static int[] newTable(int i9) {
        int[] iArr = new int[i9];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private void resizeMeMaybe(int i9) {
        int length = this.entries.length;
        if (i9 > length) {
            int iMax = Math.max(1, length >>> 1) + length;
            if (iMax < 0) {
                iMax = Integer.MAX_VALUE;
            }
            if (iMax != length) {
                resizeEntries(iMax);
            }
        }
    }

    private void resizeTable(int i9) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i10 = ((int) (i9 * this.loadFactor)) + 1;
        int[] iArrNewTable = newTable(i9);
        long[] jArr = this.entries;
        int length = iArrNewTable.length - 1;
        for (int i11 = 0; i11 < this.size; i11++) {
            int hash = getHash(jArr[i11]);
            int i12 = hash & length;
            int i13 = iArrNewTable[i12];
            iArrNewTable[i12] = i11;
            jArr[i11] = (hash << 32) | (i13 & NEXT_MASK);
        }
        this.threshold = i10;
        this.table = iArrNewTable;
    }

    private static long swapNext(long j9, int i9) {
        return (j9 & HASH_MASK) | (NEXT_MASK & i9);
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public boolean containsKey(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public Set<Multiset.Entry<K>> createEntrySet() {
        return new HashEntrySetView();
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int get(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return 0;
        }
        return this.values[iIndexOf];
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int indexOf(Object obj) {
        int iSmearedHash = Hashing.smearedHash(obj);
        int next = this.table[hashTableMask() & iSmearedHash];
        while (next != -1) {
            long j9 = this.entries[next];
            if (getHash(j9) == iSmearedHash && Objects.equal(obj, this.keys[next])) {
                return next;
            }
            next = getNext(j9);
        }
        return -1;
    }

    public void init(int i9, float f9) {
        Preconditions.checkArgument(i9 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f9 > BitmapDescriptorFactory.HUE_RED, "Illegal load factor");
        int iClosedTableSize = Hashing.closedTableSize(i9, f9);
        this.table = newTable(iClosedTableSize);
        this.loadFactor = f9;
        this.keys = new Object[i9];
        this.values = new int[i9];
        this.entries = newEntries(i9);
        this.threshold = Math.max(1, (int) (iClosedTableSize * f9));
    }

    public void insertEntry(int i9, K k9, int i10, int i11) {
        this.entries[i9] = (i11 << 32) | NEXT_MASK;
        this.keys[i9] = k9;
        this.values[i9] = i10;
    }

    public void moveLastEntry(int i9) {
        int size = size() - 1;
        if (i9 >= size) {
            this.keys[i9] = null;
            this.values[i9] = 0;
            this.entries[i9] = -1;
            return;
        }
        Object[] objArr = this.keys;
        objArr[i9] = objArr[size];
        int[] iArr = this.values;
        iArr[i9] = iArr[size];
        objArr[size] = null;
        iArr[size] = 0;
        long[] jArr = this.entries;
        long j9 = jArr[size];
        jArr[i9] = j9;
        jArr[size] = -1;
        int hash = getHash(j9) & hashTableMask();
        int[] iArr2 = this.table;
        int i10 = iArr2[hash];
        if (i10 == size) {
            iArr2[hash] = i9;
            return;
        }
        while (true) {
            long j10 = this.entries[i10];
            int next = getNext(j10);
            if (next == size) {
                this.entries[i10] = swapNext(j10, i9);
                return;
            }
            i10 = next;
        }
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int put(K k9, int i9) {
        CollectPreconditions.checkPositive(i9, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int iSmearedHash = Hashing.smearedHash(k9);
        int iHashTableMask = hashTableMask() & iSmearedHash;
        int i10 = this.size;
        int[] iArr2 = this.table;
        int i11 = iArr2[iHashTableMask];
        if (i11 == -1) {
            iArr2[iHashTableMask] = i10;
        } else {
            while (true) {
                long j9 = jArr[i11];
                if (getHash(j9) == iSmearedHash && Objects.equal(k9, objArr[i11])) {
                    int i12 = iArr[i11];
                    iArr[i11] = i9;
                    return i12;
                }
                int next = getNext(j9);
                if (next == -1) {
                    jArr[i11] = swapNext(j9, i10);
                    break;
                }
                i11 = next;
            }
        }
        if (i10 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i13 = i10 + 1;
        resizeMeMaybe(i13);
        insertEntry(i10, k9, i9, iSmearedHash);
        this.size = i13;
        if (i10 >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return 0;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int remove(Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int removeEntry(int i9) {
        return remove(this.keys[i9], getHash(this.entries[i9]));
    }

    public void resizeEntries(int i9) {
        this.keys = Arrays.copyOf(this.keys, i9);
        this.values = Arrays.copyOf(this.values, i9);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, i9);
        if (i9 > length) {
            Arrays.fill(jArrCopyOf, length, i9, -1L);
        }
        this.entries = jArrCopyOf;
    }

    private int remove(Object obj, int i9) {
        int iHashTableMask = hashTableMask() & i9;
        int i10 = this.table[iHashTableMask];
        if (i10 == -1) {
            return 0;
        }
        int i11 = -1;
        while (true) {
            if (getHash(this.entries[i10]) == i9 && Objects.equal(obj, this.keys[i10])) {
                int i12 = this.values[i10];
                if (i11 == -1) {
                    this.table[iHashTableMask] = getNext(this.entries[i10]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i11] = swapNext(jArr[i11], getNext(jArr[i10]));
                }
                moveLastEntry(i10);
                this.size--;
                this.modCount++;
                return i12;
            }
            int next = getNext(this.entries[i10]);
            if (next == -1) {
                return 0;
            }
            i11 = i10;
            i10 = next;
        }
    }

    public ObjectCountHashMap(AbstractObjectCountMap<K> abstractObjectCountMap) {
        init(abstractObjectCountMap.size(), DEFAULT_LOAD_FACTOR);
        int iFirstIndex = abstractObjectCountMap.firstIndex();
        while (iFirstIndex != -1) {
            put(abstractObjectCountMap.getKey(iFirstIndex), abstractObjectCountMap.getValue(iFirstIndex));
            iFirstIndex = abstractObjectCountMap.nextIndex(iFirstIndex);
        }
    }

    public ObjectCountHashMap(int i9) {
        this(i9, DEFAULT_LOAD_FACTOR);
    }

    public ObjectCountHashMap(int i9, float f9) {
        init(i9, f9);
    }
}
