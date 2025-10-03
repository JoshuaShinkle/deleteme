package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class LongArrayList extends AbstractProtobufList<Long> implements Internal.LongList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final LongArrayList EMPTY_LIST;
    private long[] array;
    private int size;

    static {
        LongArrayList longArrayList = new LongArrayList(new long[0], 0);
        EMPTY_LIST = longArrayList;
        longArrayList.makeImmutable();
    }

    public LongArrayList() {
        this(new long[10], 0);
    }

    public static LongArrayList emptyList() {
        return EMPTY_LIST;
    }

    private void ensureIndexInRange(int i9) {
        if (i9 < 0 || i9 >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i9) {
        return "Index:" + i9 + ", Size:" + this.size;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Long> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof LongArrayList)) {
            return super.addAll(collection);
        }
        LongArrayList longArrayList = (LongArrayList) collection;
        int i9 = longArrayList.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        long[] jArr = this.array;
        if (i11 > jArr.length) {
            this.array = Arrays.copyOf(jArr, i11);
        }
        System.arraycopy(longArrayList.array, 0, this.array, this.size, longArrayList.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.LongList
    public void addLong(long j9) {
        ensureIsMutable();
        int i9 = this.size;
        long[] jArr = this.array;
        if (i9 == jArr.length) {
            long[] jArr2 = new long[((i9 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i9);
            this.array = jArr2;
        }
        long[] jArr3 = this.array;
        int i10 = this.size;
        this.size = i10 + 1;
        jArr3[i10] = j9;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongArrayList)) {
            return super.equals(obj);
        }
        LongArrayList longArrayList = (LongArrayList) obj;
        if (this.size != longArrayList.size) {
            return false;
        }
        long[] jArr = longArrayList.array;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.array[i9] != jArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Internal.LongList
    public long getLong(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int iHashLong = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iHashLong = (iHashLong * 31) + Internal.hashLong(this.array[i9]);
        }
        return iHashLong;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i9, int i10) {
        ensureIsMutable();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.array;
        System.arraycopy(jArr, i10, jArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.Internal.LongList
    public long setLong(int i9, long j9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        long[] jArr = this.array;
        long j10 = jArr[i9];
        jArr[i9] = j9;
        return j10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private LongArrayList(long[] jArr, int i9) {
        this.array = jArr;
        this.size = i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public Long get(int i9) {
        return Long.valueOf(getLong(i9));
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Long> mutableCopyWithCapacity2(int i9) {
        if (i9 >= this.size) {
            return new LongArrayList(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Long.valueOf(this.array[i9]))) {
                long[] jArr = this.array;
                System.arraycopy(jArr, i9 + 1, jArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Long set(int i9, Long l9) {
        return Long.valueOf(setLong(i9, l9.longValue()));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Long l9) {
        addLong(l9.longValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, Long l9) {
        addLong(i9, l9.longValue());
    }

    private void addLong(int i9, long j9) {
        int i10;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i10 = this.size)) {
            long[] jArr = this.array;
            if (i10 < jArr.length) {
                System.arraycopy(jArr, i9, jArr, i9 + 1, i10 - i9);
            } else {
                long[] jArr2 = new long[((i10 * 3) / 2) + 1];
                System.arraycopy(jArr, 0, jArr2, 0, i9);
                System.arraycopy(this.array, i9, jArr2, i9 + 1, this.size - i9);
                this.array = jArr2;
            }
            this.array[i9] = j9;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Long remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        long[] jArr = this.array;
        long j9 = jArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(jArr, i9 + 1, jArr, i9, (r3 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j9);
    }
}
