package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final DoubleArrayList EMPTY_LIST;
    private double[] array;
    private int size;

    static {
        DoubleArrayList doubleArrayList = new DoubleArrayList(new double[0], 0);
        EMPTY_LIST = doubleArrayList;
        doubleArrayList.makeImmutable();
    }

    public DoubleArrayList() {
        this(new double[10], 0);
    }

    public static DoubleArrayList emptyList() {
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
    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof DoubleArrayList)) {
            return super.addAll(collection);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
        int i9 = doubleArrayList.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        double[] dArr = this.array;
        if (i11 > dArr.length) {
            this.array = Arrays.copyOf(dArr, i11);
        }
        System.arraycopy(doubleArrayList.array, 0, this.array, this.size, doubleArrayList.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public void addDouble(double d9) {
        ensureIsMutable();
        int i9 = this.size;
        double[] dArr = this.array;
        if (i9 == dArr.length) {
            double[] dArr2 = new double[((i9 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i9);
            this.array = dArr2;
        }
        double[] dArr3 = this.array;
        int i10 = this.size;
        this.size = i10 + 1;
        dArr3[i10] = d9;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleArrayList)) {
            return super.equals(obj);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
        if (this.size != doubleArrayList.size) {
            return false;
        }
        double[] dArr = doubleArrayList.array;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (Double.doubleToLongBits(this.array[i9]) != Double.doubleToLongBits(dArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double getDouble(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int iHashLong = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iHashLong = (iHashLong * 31) + Internal.hashLong(Double.doubleToLongBits(this.array[i9]));
        }
        return iHashLong;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i9, int i10) {
        ensureIsMutable();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.array;
        System.arraycopy(dArr, i10, dArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.Internal.DoubleList
    public double setDouble(int i9, double d9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        double[] dArr = this.array;
        double d10 = dArr[i9];
        dArr[i9] = d9;
        return d10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private DoubleArrayList(double[] dArr, int i9) {
        this.array = dArr;
        this.size = i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public Double get(int i9) {
        return Double.valueOf(getDouble(i9));
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Double> mutableCopyWithCapacity2(int i9) {
        if (i9 >= this.size) {
            return new DoubleArrayList(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Double.valueOf(this.array[i9]))) {
                double[] dArr = this.array;
                System.arraycopy(dArr, i9 + 1, dArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Double set(int i9, Double d9) {
        return Double.valueOf(setDouble(i9, d9.doubleValue()));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Double d9) {
        addDouble(d9.doubleValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, Double d9) {
        addDouble(i9, d9.doubleValue());
    }

    private void addDouble(int i9, double d9) {
        int i10;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i10 = this.size)) {
            double[] dArr = this.array;
            if (i10 < dArr.length) {
                System.arraycopy(dArr, i9, dArr, i9 + 1, i10 - i9);
            } else {
                double[] dArr2 = new double[((i10 * 3) / 2) + 1];
                System.arraycopy(dArr, 0, dArr2, 0, i9);
                System.arraycopy(this.array, i9, dArr2, i9 + 1, this.size - i9);
                this.array = dArr2;
            }
            this.array[i9] = d9;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Double remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        double[] dArr = this.array;
        double d9 = dArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(dArr, i9 + 1, dArr, i9, (r3 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d9);
    }
}
