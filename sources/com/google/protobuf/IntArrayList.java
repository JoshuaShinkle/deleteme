package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final IntArrayList EMPTY_LIST;
    private int[] array;
    private int size;

    static {
        IntArrayList intArrayList = new IntArrayList(new int[0], 0);
        EMPTY_LIST = intArrayList;
        intArrayList.makeImmutable();
    }

    public IntArrayList() {
        this(new int[10], 0);
    }

    public static IntArrayList emptyList() {
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
    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof IntArrayList)) {
            return super.addAll(collection);
        }
        IntArrayList intArrayList = (IntArrayList) collection;
        int i9 = intArrayList.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        int[] iArr = this.array;
        if (i11 > iArr.length) {
            this.array = Arrays.copyOf(iArr, i11);
        }
        System.arraycopy(intArrayList.array, 0, this.array, this.size, intArrayList.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.IntList
    public void addInt(int i9) {
        ensureIsMutable();
        int i10 = this.size;
        int[] iArr = this.array;
        if (i10 == iArr.length) {
            int[] iArr2 = new int[((i10 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i10);
            this.array = iArr2;
        }
        int[] iArr3 = this.array;
        int i11 = this.size;
        this.size = i11 + 1;
        iArr3[i11] = i9;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntArrayList)) {
            return super.equals(obj);
        }
        IntArrayList intArrayList = (IntArrayList) obj;
        if (this.size != intArrayList.size) {
            return false;
        }
        int[] iArr = intArrayList.array;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.array[i9] != iArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Internal.IntList
    public int getInt(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i9 = 1;
        for (int i10 = 0; i10 < this.size; i10++) {
            i9 = (i9 * 31) + this.array[i10];
        }
        return i9;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i9, int i10) {
        ensureIsMutable();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.array;
        System.arraycopy(iArr, i10, iArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.Internal.IntList
    public int setInt(int i9, int i10) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        int[] iArr = this.array;
        int i11 = iArr[i9];
        iArr[i9] = i10;
        return i11;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private IntArrayList(int[] iArr, int i9) {
        this.array = iArr;
        this.size = i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public Integer get(int i9) {
        return Integer.valueOf(getInt(i9));
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Integer> mutableCopyWithCapacity2(int i9) {
        if (i9 >= this.size) {
            return new IntArrayList(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Integer.valueOf(this.array[i9]))) {
                int[] iArr = this.array;
                System.arraycopy(iArr, i9 + 1, iArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Integer set(int i9, Integer num) {
        return Integer.valueOf(setInt(i9, num.intValue()));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Integer num) {
        addInt(num.intValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, Integer num) {
        addInt(i9, num.intValue());
    }

    private void addInt(int i9, int i10) {
        int i11;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i11 = this.size)) {
            int[] iArr = this.array;
            if (i11 < iArr.length) {
                System.arraycopy(iArr, i9, iArr, i9 + 1, i11 - i9);
            } else {
                int[] iArr2 = new int[((i11 * 3) / 2) + 1];
                System.arraycopy(iArr, 0, iArr2, 0, i9);
                System.arraycopy(this.array, i9, iArr2, i9 + 1, this.size - i9);
                this.array = iArr2;
            }
            this.array[i9] = i10;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Integer remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        int[] iArr = this.array;
        int i10 = iArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(iArr, i9 + 1, iArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i10);
    }
}
