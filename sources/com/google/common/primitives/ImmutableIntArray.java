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
public final class ImmutableIntArray implements Serializable {
    private static final ImmutableIntArray EMPTY = new ImmutableIntArray(new int[0]);
    private final int[] array;
    private final int end;
    private final transient int start;

    public static class AsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private final ImmutableIntArray parent;

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
                if (obj2 instanceof Integer) {
                    int i10 = i9 + 1;
                    if (this.parent.array[i9] == ((Integer) obj2).intValue()) {
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
            if (obj instanceof Integer) {
                return this.parent.indexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            if (obj instanceof Integer) {
                return this.parent.lastIndexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Integer> subList(int i9, int i10) {
            return this.parent.subArray(i9, i10).asList();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableIntArray immutableIntArray) {
            this.parent = immutableIntArray;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int i9) {
            return Integer.valueOf(this.parent.get(i9));
        }
    }

    public static Builder builder(int i9) {
        Preconditions.checkArgument(i9 >= 0, "Invalid initialCapacity: %s", i9);
        return new Builder(i9);
    }

    public static ImmutableIntArray copyOf(int[] iArr) {
        return iArr.length == 0 ? EMPTY : new ImmutableIntArray(Arrays.copyOf(iArr, iArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    /* renamed from: of */
    public static ImmutableIntArray m17692of() {
        return EMPTY;
    }

    public List<Integer> asList() {
        return new AsList();
    }

    public boolean contains(int i9) {
        return indexOf(i9) >= 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableIntArray)) {
            return false;
        }
        ImmutableIntArray immutableIntArray = (ImmutableIntArray) obj;
        if (length() != immutableIntArray.length()) {
            return false;
        }
        for (int i9 = 0; i9 < length(); i9++) {
            if (get(i9) != immutableIntArray.get(i9)) {
                return false;
            }
        }
        return true;
    }

    public int get(int i9) {
        Preconditions.checkElementIndex(i9, length());
        return this.array[this.start + i9];
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i9 = this.start; i9 < this.end; i9++) {
            iHashCode = (iHashCode * 31) + Ints.hashCode(this.array[i9]);
        }
        return iHashCode;
    }

    public int indexOf(int i9) {
        for (int i10 = this.start; i10 < this.end; i10++) {
            if (this.array[i10] == i9) {
                return i10 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(int i9) {
        int i10;
        int i11 = this.end;
        do {
            i11--;
            i10 = this.start;
            if (i11 < i10) {
                return -1;
            }
        } while (this.array[i11] != i9);
        return i11 - i10;
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableIntArray subArray(int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i10, length());
        if (i9 == i10) {
            return EMPTY;
        }
        int[] iArr = this.array;
        int i11 = this.start;
        return new ImmutableIntArray(iArr, i9 + i11, i11 + i10);
    }

    public int[] toArray() {
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

    public ImmutableIntArray trimmed() {
        return isPartialView() ? new ImmutableIntArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableIntArray(int[] iArr) {
        this(iArr, 0, iArr.length);
    }

    public static ImmutableIntArray copyOf(Collection<Integer> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableIntArray(Ints.toArray(collection));
    }

    /* renamed from: of */
    public static ImmutableIntArray m17693of(int i9) {
        return new ImmutableIntArray(new int[]{i9});
    }

    @CanIgnoreReturnValue
    public static final class Builder {
        private int[] array;
        private int count = 0;

        public Builder(int i9) {
            this.array = new int[i9];
        }

        private void ensureRoomFor(int i9) {
            int i10 = this.count + i9;
            int[] iArr = this.array;
            if (i10 > iArr.length) {
                int[] iArr2 = new int[expandedCapacity(iArr.length, i10)];
                System.arraycopy(this.array, 0, iArr2, 0, this.count);
                this.array = iArr2;
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

        public Builder add(int i9) {
            ensureRoomFor(1);
            int[] iArr = this.array;
            int i10 = this.count;
            iArr[i10] = i9;
            this.count = i10 + 1;
            return this;
        }

        public Builder addAll(int[] iArr) {
            ensureRoomFor(iArr.length);
            System.arraycopy(iArr, 0, this.array, this.count, iArr.length);
            this.count += iArr.length;
            return this;
        }

        public ImmutableIntArray build() {
            if (this.count == 0) {
                return ImmutableIntArray.EMPTY;
            }
            return new ImmutableIntArray(this.array, 0, this.count);
        }

        public Builder addAll(Iterable<Integer> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Integer>) iterable);
            }
            Iterator<Integer> it = iterable.iterator();
            while (it.hasNext()) {
                add(it.next().intValue());
            }
            return this;
        }

        public Builder addAll(Collection<Integer> collection) {
            ensureRoomFor(collection.size());
            for (Integer num : collection) {
                int[] iArr = this.array;
                int i9 = this.count;
                this.count = i9 + 1;
                iArr[i9] = num.intValue();
            }
            return this;
        }

        public Builder addAll(ImmutableIntArray immutableIntArray) {
            ensureRoomFor(immutableIntArray.length());
            System.arraycopy(immutableIntArray.array, immutableIntArray.start, this.array, this.count, immutableIntArray.length());
            this.count += immutableIntArray.length();
            return this;
        }
    }

    private ImmutableIntArray(int[] iArr, int i9, int i10) {
        this.array = iArr;
        this.start = i9;
        this.end = i10;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableIntArray copyOf(Iterable<Integer> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Integer>) iterable);
        }
        return builder().addAll(iterable).build();
    }

    /* renamed from: of */
    public static ImmutableIntArray m17694of(int i9, int i10) {
        return new ImmutableIntArray(new int[]{i9, i10});
    }

    /* renamed from: of */
    public static ImmutableIntArray m17695of(int i9, int i10, int i11) {
        return new ImmutableIntArray(new int[]{i9, i10, i11});
    }

    /* renamed from: of */
    public static ImmutableIntArray m17696of(int i9, int i10, int i11, int i12) {
        return new ImmutableIntArray(new int[]{i9, i10, i11, i12});
    }

    /* renamed from: of */
    public static ImmutableIntArray m17697of(int i9, int i10, int i11, int i12, int i13) {
        return new ImmutableIntArray(new int[]{i9, i10, i11, i12, i13});
    }

    /* renamed from: of */
    public static ImmutableIntArray m17698of(int i9, int i10, int i11, int i12, int i13, int i14) {
        return new ImmutableIntArray(new int[]{i9, i10, i11, i12, i13, i14});
    }

    /* renamed from: of */
    public static ImmutableIntArray m17699of(int i9, int... iArr) {
        int[] iArr2 = new int[iArr.length + 1];
        iArr2[0] = i9;
        System.arraycopy(iArr, 0, iArr2, 1, iArr.length);
        return new ImmutableIntArray(iArr2);
    }
}
