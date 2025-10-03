package com.google.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class ProtobufArrayList<E> extends AbstractProtobufList<E> implements RandomAccess {
    private static final ProtobufArrayList<Object> EMPTY_LIST;
    private E[] array;
    private int size;

    static {
        ProtobufArrayList<Object> protobufArrayList = new ProtobufArrayList<>(new Object[0], 0);
        EMPTY_LIST = protobufArrayList;
        protobufArrayList.makeImmutable();
    }

    public ProtobufArrayList() {
        this(new Object[10], 0);
    }

    private static <E> E[] createArray(int i9) {
        return (E[]) new Object[i9];
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return (ProtobufArrayList<E>) EMPTY_LIST;
    }

    private void ensureIndexInRange(int i9) {
        if (i9 < 0 || i9 >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i9) {
        return "Index:" + i9 + ", Size:" + this.size;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e9) {
        ensureIsMutable();
        int i9 = this.size;
        E[] eArr = this.array;
        if (i9 == eArr.length) {
            this.array = (E[]) Arrays.copyOf(eArr, ((i9 * 3) / 2) + 1);
        }
        E[] eArr2 = this.array;
        int i10 = this.size;
        this.size = i10 + 1;
        eArr2[i10] = e9;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public E remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        E[] eArr = this.array;
        E e9 = eArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(eArr, i9 + 1, eArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return e9;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public E set(int i9, E e9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        E[] eArr = this.array;
        E e10 = eArr[i9];
        eArr[i9] = e9;
        ((AbstractList) this).modCount++;
        return e10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private ProtobufArrayList(E[] eArr, int i9) {
        this.array = eArr;
        this.size = i9;
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public ProtobufArrayList<E> mutableCopyWithCapacity(int i9) {
        if (i9 >= this.size) {
            return new ProtobufArrayList<>(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, E e9) {
        int i10;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i10 = this.size)) {
            E[] eArr = this.array;
            if (i10 < eArr.length) {
                System.arraycopy(eArr, i9, eArr, i9 + 1, i10 - i9);
            } else {
                E[] eArr2 = (E[]) createArray(((i10 * 3) / 2) + 1);
                System.arraycopy(this.array, 0, eArr2, 0, i9);
                System.arraycopy(this.array, i9, eArr2, i9 + 1, this.size - i9);
                this.array = eArr2;
            }
            this.array[i9] = e9;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }
}
