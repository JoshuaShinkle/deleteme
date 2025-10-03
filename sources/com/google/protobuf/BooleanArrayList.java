package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class BooleanArrayList extends AbstractProtobufList<Boolean> implements Internal.BooleanList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final BooleanArrayList EMPTY_LIST;
    private boolean[] array;
    private int size;

    static {
        BooleanArrayList booleanArrayList = new BooleanArrayList(new boolean[0], 0);
        EMPTY_LIST = booleanArrayList;
        booleanArrayList.makeImmutable();
    }

    public BooleanArrayList() {
        this(new boolean[10], 0);
    }

    public static BooleanArrayList emptyList() {
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
    public boolean addAll(Collection<? extends Boolean> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof BooleanArrayList)) {
            return super.addAll(collection);
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) collection;
        int i9 = booleanArrayList.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        boolean[] zArr = this.array;
        if (i11 > zArr.length) {
            this.array = Arrays.copyOf(zArr, i11);
        }
        System.arraycopy(booleanArrayList.array, 0, this.array, this.size, booleanArrayList.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public void addBoolean(boolean z8) {
        ensureIsMutable();
        int i9 = this.size;
        boolean[] zArr = this.array;
        if (i9 == zArr.length) {
            boolean[] zArr2 = new boolean[((i9 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i9);
            this.array = zArr2;
        }
        boolean[] zArr3 = this.array;
        int i10 = this.size;
        this.size = i10 + 1;
        zArr3[i10] = z8;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BooleanArrayList)) {
            return super.equals(obj);
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) obj;
        if (this.size != booleanArrayList.size) {
            return false;
        }
        boolean[] zArr = booleanArrayList.array;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.array[i9] != zArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public boolean getBoolean(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int iHashBoolean = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iHashBoolean = (iHashBoolean * 31) + Internal.hashBoolean(this.array[i9]);
        }
        return iHashBoolean;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i9, int i10) {
        ensureIsMutable();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.array;
        System.arraycopy(zArr, i10, zArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.Internal.BooleanList
    public boolean setBoolean(int i9, boolean z8) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        boolean[] zArr = this.array;
        boolean z9 = zArr[i9];
        zArr[i9] = z8;
        return z9;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private BooleanArrayList(boolean[] zArr, int i9) {
        this.array = zArr;
        this.size = i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public Boolean get(int i9) {
        return Boolean.valueOf(getBoolean(i9));
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity, reason: merged with bridge method [inline-methods] */
    public Internal.ProtobufList<Boolean> mutableCopyWithCapacity2(int i9) {
        if (i9 >= this.size) {
            return new BooleanArrayList(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Boolean.valueOf(this.array[i9]))) {
                boolean[] zArr = this.array;
                System.arraycopy(zArr, i9 + 1, zArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Boolean set(int i9, Boolean bool) {
        return Boolean.valueOf(setBoolean(i9, bool.booleanValue()));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Boolean bool) {
        addBoolean(bool.booleanValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, Boolean bool) {
        addBoolean(i9, bool.booleanValue());
    }

    private void addBoolean(int i9, boolean z8) {
        int i10;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i10 = this.size)) {
            boolean[] zArr = this.array;
            if (i10 < zArr.length) {
                System.arraycopy(zArr, i9, zArr, i9 + 1, i10 - i9);
            } else {
                boolean[] zArr2 = new boolean[((i10 * 3) / 2) + 1];
                System.arraycopy(zArr, 0, zArr2, 0, i9);
                System.arraycopy(this.array, i9, zArr2, i9 + 1, this.size - i9);
                this.array = zArr2;
            }
            this.array[i9] = z8;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Boolean remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        boolean[] zArr = this.array;
        boolean z8 = zArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(zArr, i9 + 1, zArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z8);
    }
}
