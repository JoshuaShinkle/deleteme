package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

@Immutable
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class ImmutableDoubleArray implements Serializable {
    private static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);
    private final double[] array;
    private final int end;
    private final transient int start;

    public static class AsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private final ImmutableDoubleArray parent;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return indexOf(obj) >= 0;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj instanceof AsList) {
                return this.parent.equals(((AsList) obj).parent);
            }
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            if (size() != list.size()) {
                return false;
            }
            int i9 = this.parent.start;
            for (Object obj2 : list) {
                if (obj2 instanceof Double) {
                    int i10 = i9 + 1;
                    if (ImmutableDoubleArray.areEqual(this.parent.array[i9], ((Double) obj2).doubleValue())) {
                        i9 = i10;
                    }
                }
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            return this.parent.hashCode();
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            if (obj instanceof Double) {
                return this.parent.indexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            if (obj instanceof Double) {
                return this.parent.lastIndexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Double> subList(int i9, int i10) {
            return this.parent.subArray(i9, i10).asList();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableDoubleArray immutableDoubleArray) {
            this.parent = immutableDoubleArray;
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int i9) {
            return Double.valueOf(this.parent.get(i9));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean areEqual(double d9, double d10) {
        return Double.doubleToLongBits(d9) == Double.doubleToLongBits(d10);
    }

    public static Builder builder(int i9) {
        Preconditions.checkArgument(i9 >= 0, "Invalid initialCapacity: %s", i9);
        return new Builder(i9);
    }

    public static ImmutableDoubleArray copyOf(double[] dArr) {
        return dArr.length == 0 ? EMPTY : new ImmutableDoubleArray(Arrays.copyOf(dArr, dArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17684of() {
        return EMPTY;
    }

    public List<Double> asList() {
        return new AsList();
    }

    public boolean contains(double d9) {
        return indexOf(d9) >= 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableDoubleArray)) {
            return false;
        }
        ImmutableDoubleArray immutableDoubleArray = (ImmutableDoubleArray) obj;
        if (length() != immutableDoubleArray.length()) {
            return false;
        }
        for (int i9 = 0; i9 < length(); i9++) {
            if (!areEqual(get(i9), immutableDoubleArray.get(i9))) {
                return false;
            }
        }
        return true;
    }

    public double get(int i9) {
        Preconditions.checkElementIndex(i9, length());
        return this.array[this.start + i9];
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i9 = this.start; i9 < this.end; i9++) {
            iHashCode = (iHashCode * 31) + Doubles.hashCode(this.array[i9]);
        }
        return iHashCode;
    }

    public int indexOf(double d9) {
        for (int i9 = this.start; i9 < this.end; i9++) {
            if (areEqual(this.array[i9], d9)) {
                return i9 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(double d9) {
        int i9 = this.end;
        do {
            i9--;
            if (i9 < this.start) {
                return -1;
            }
        } while (!areEqual(this.array[i9], d9));
        return i9 - this.start;
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableDoubleArray subArray(int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i10, length());
        if (i9 == i10) {
            return EMPTY;
        }
        double[] dArr = this.array;
        int i11 = this.start;
        return new ImmutableDoubleArray(dArr, i9 + i11, i11 + i10);
    }

    public double[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(length() * 5);
        sb.append('[');
        sb.append(this.array[this.start]);
        int i9 = this.start;
        while (true) {
            i9++;
            if (i9 >= this.end) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            sb.append(this.array[i9]);
        }
    }

    public ImmutableDoubleArray trimmed() {
        return isPartialView() ? new ImmutableDoubleArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableDoubleArray(double[] dArr) {
        this(dArr, 0, dArr.length);
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17685of(double d9) {
        return new ImmutableDoubleArray(new double[]{d9});
    }

    @CanIgnoreReturnValue
    public static final class Builder {
        private double[] array;
        private int count = 0;

        public Builder(int i9) {
            this.array = new double[i9];
        }

        private void ensureRoomFor(int i9) {
            int i10 = this.count + i9;
            double[] dArr = this.array;
            if (i10 > dArr.length) {
                double[] dArr2 = new double[expandedCapacity(dArr.length, i10)];
                System.arraycopy(this.array, 0, dArr2, 0, this.count);
                this.array = dArr2;
            }
        }

        private static int expandedCapacity(int i9, int i10) {
            if (i10 < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int iHighestOneBit = i9 + (i9 >> 1) + 1;
            if (iHighestOneBit < i10) {
                iHighestOneBit = Integer.highestOneBit(i10 - 1) << 1;
            }
            if (iHighestOneBit < 0) {
                return Integer.MAX_VALUE;
            }
            return iHighestOneBit;
        }

        public Builder add(double d9) {
            ensureRoomFor(1);
            double[] dArr = this.array;
            int i9 = this.count;
            dArr[i9] = d9;
            this.count = i9 + 1;
            return this;
        }

        public Builder addAll(double[] dArr) {
            ensureRoomFor(dArr.length);
            System.arraycopy(dArr, 0, this.array, this.count, dArr.length);
            this.count += dArr.length;
            return this;
        }

        public ImmutableDoubleArray build() {
            if (this.count == 0) {
                return ImmutableDoubleArray.EMPTY;
            }
            return new ImmutableDoubleArray(this.array, 0, this.count);
        }

        public Builder addAll(Iterable<Double> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Double>) iterable);
            }
            Iterator<Double> it = iterable.iterator();
            while (it.hasNext()) {
                add(it.next().doubleValue());
            }
            return this;
        }

        public Builder addAll(Collection<Double> collection) {
            ensureRoomFor(collection.size());
            for (Double d9 : collection) {
                double[] dArr = this.array;
                int i9 = this.count;
                this.count = i9 + 1;
                dArr[i9] = d9.doubleValue();
            }
            return this;
        }

        public Builder addAll(ImmutableDoubleArray immutableDoubleArray) {
            ensureRoomFor(immutableDoubleArray.length());
            System.arraycopy(immutableDoubleArray.array, immutableDoubleArray.start, this.array, this.count, immutableDoubleArray.length());
            this.count += immutableDoubleArray.length();
            return this;
        }
    }

    private ImmutableDoubleArray(double[] dArr, int i9, int i10) {
        this.array = dArr;
        this.start = i9;
        this.end = i10;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableDoubleArray copyOf(Collection<Double> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(collection));
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17686of(double d9, double d10) {
        return new ImmutableDoubleArray(new double[]{d9, d10});
    }

    public static ImmutableDoubleArray copyOf(Iterable<Double> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Double>) iterable);
        }
        return builder().addAll(iterable).build();
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17687of(double d9, double d10, double d11) {
        return new ImmutableDoubleArray(new double[]{d9, d10, d11});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17688of(double d9, double d10, double d11, double d12) {
        return new ImmutableDoubleArray(new double[]{d9, d10, d11, d12});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17689of(double d9, double d10, double d11, double d12, double d13) {
        return new ImmutableDoubleArray(new double[]{d9, d10, d11, d12, d13});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17690of(double d9, double d10, double d11, double d12, double d13, double d14) {
        return new ImmutableDoubleArray(new double[]{d9, d10, d11, d12, d13, d14});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m17691of(double d9, double... dArr) {
        double[] dArr2 = new double[dArr.length + 1];
        dArr2[0] = d9;
        System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
        return new ImmutableDoubleArray(dArr2);
    }
}
