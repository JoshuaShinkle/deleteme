package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class FloatArrayList extends AbstractProtobufList<Float> implements Internal.FloatList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final FloatArrayList EMPTY_LIST;
    private float[] array;
    private int size;

    static {
        FloatArrayList floatArrayList = new FloatArrayList(new float[0], 0);
        EMPTY_LIST = floatArrayList;
        floatArrayList.makeImmutable();
    }

    public FloatArrayList() {
        this(new float[10], 0);
    }

    public static FloatArrayList emptyList() {
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
    public boolean addAll(Collection<? extends Float> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof FloatArrayList)) {
            return super.addAll(collection);
        }
        FloatArrayList floatArrayList = (FloatArrayList) collection;
        int i9 = floatArrayList.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        float[] fArr = this.array;
        if (i11 > fArr.length) {
            this.array = Arrays.copyOf(fArr, i11);
        }
        System.arraycopy(floatArrayList.array, 0, this.array, this.size, floatArrayList.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.protobuf.Internal.FloatList
    public void addFloat(float f9) {
        ensureIsMutable();
        int i9 = this.size;
        float[] fArr = this.array;
        if (i9 == fArr.length) {
            float[] fArr2 = new float[((i9 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i9);
            this.array = fArr2;
        }
        float[] fArr3 = this.array;
        int i10 = this.size;
        this.size = i10 + 1;
        fArr3[i10] = f9;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FloatArrayList)) {
            return super.equals(obj);
        }
        FloatArrayList floatArrayList = (FloatArrayList) obj;
        if (this.size != floatArrayList.size) {
            return false;
        }
        float[] fArr = floatArrayList.array;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (Float.floatToIntBits(this.array[i9]) != Float.floatToIntBits(fArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.Internal.FloatList
    public float getFloat(int i9) {
        ensureIndexInRange(i9);
        return this.array[i9];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int iFloatToIntBits = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.array[i9]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i9, int i10) {
        ensureIsMutable();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.array;
        System.arraycopy(fArr, i10, fArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.Internal.FloatList
    public float setFloat(int i9, float f9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        float[] fArr = this.array;
        float f10 = fArr[i9];
        fArr[i9] = f9;
        return f10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private FloatArrayList(float[] fArr, int i9) {
        this.array = fArr;
        this.size = i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public Float get(int i9) {
        return Float.valueOf(getFloat(i9));
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Float> mutableCopyWithCapacity2(int i9) {
        if (i9 >= this.size) {
            return new FloatArrayList(Arrays.copyOf(this.array, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Float.valueOf(this.array[i9]))) {
                float[] fArr = this.array;
                System.arraycopy(fArr, i9 + 1, fArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Float set(int i9, Float f9) {
        return Float.valueOf(setFloat(i9, f9.floatValue()));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Float f9) {
        addFloat(f9.floatValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i9, Float f9) {
        addFloat(i9, f9.floatValue());
    }

    private void addFloat(int i9, float f9) {
        int i10;
        ensureIsMutable();
        if (i9 >= 0 && i9 <= (i10 = this.size)) {
            float[] fArr = this.array;
            if (i10 < fArr.length) {
                System.arraycopy(fArr, i9, fArr, i9 + 1, i10 - i9);
            } else {
                float[] fArr2 = new float[((i10 * 3) / 2) + 1];
                System.arraycopy(fArr, 0, fArr2, 0, i9);
                System.arraycopy(this.array, i9, fArr2, i9 + 1, this.size - i9);
                this.array = fArr2;
            }
            this.array[i9] = f9;
            this.size++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i9));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Float remove(int i9) {
        ensureIsMutable();
        ensureIndexInRange(i9);
        float[] fArr = this.array;
        float f9 = fArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(fArr, i9 + 1, fArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f9);
    }
}
