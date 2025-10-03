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
public final class ImmutableLongArray implements Serializable {
    private static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0]);
    private final long[] array;
    private final int end;
    private final transient int start;

    public static class AsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private final ImmutableLongArray parent;

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
                if (obj2 instanceof Long) {
                    int i10 = i9 + 1;
                    if (this.parent.array[i9] == ((Long) obj2).longValue()) {
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
            if (obj instanceof Long) {
                return this.parent.indexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            if (obj instanceof Long) {
                return this.parent.lastIndexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int i9, int i10) {
            return this.parent.subArray(i9, i10).asList();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableLongArray immutableLongArray) {
            this.parent = immutableLongArray;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int i9) {
            return Long.valueOf(this.parent.get(i9));
        }
    }

    public static Builder builder(int i9) {
        Preconditions.checkArgument(i9 >= 0, "Invalid initialCapacity: %s", i9);
        return new Builder(i9);
    }

    public static ImmutableLongArray copyOf(long[] jArr) {
        return jArr.length == 0 ? EMPTY : new ImmutableLongArray(Arrays.copyOf(jArr, jArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    /* renamed from: of */
    public static ImmutableLongArray m17700of() {
        return EMPTY;
    }

    public List<Long> asList() {
        return new AsList();
    }

    public boolean contains(long j9) {
        return indexOf(j9) >= 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableLongArray)) {
            return false;
        }
        ImmutableLongArray immutableLongArray = (ImmutableLongArray) obj;
        if (length() != immutableLongArray.length()) {
            return false;
        }
        for (int i9 = 0; i9 < length(); i9++) {
            if (get(i9) != immutableLongArray.get(i9)) {
                return false;
            }
        }
        return true;
    }

    public long get(int i9) {
        Preconditions.checkElementIndex(i9, length());
        return this.array[this.start + i9];
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i9 = this.start; i9 < this.end; i9++) {
            iHashCode = (iHashCode * 31) + Longs.hashCode(this.array[i9]);
        }
        return iHashCode;
    }

    public int indexOf(long j9) {
        for (int i9 = this.start; i9 < this.end; i9++) {
            if (this.array[i9] == j9) {
                return i9 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(long j9) {
        int i9;
        int i10 = this.end;
        do {
            i10--;
            i9 = this.start;
            if (i10 < i9) {
                return -1;
            }
        } while (this.array[i10] != j9);
        return i10 - i9;
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableLongArray subArray(int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i10, length());
        if (i9 == i10) {
            return EMPTY;
        }
        long[] jArr = this.array;
        int i11 = this.start;
        return new ImmutableLongArray(jArr, i9 + i11, i11 + i10);
    }

    public long[] toArray() {
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

    public ImmutableLongArray trimmed() {
        return isPartialView() ? new ImmutableLongArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableLongArray(long[] jArr) {
        this(jArr, 0, jArr.length);
    }

    /* renamed from: of */
    public static ImmutableLongArray m17701of(long j9) {
        return new ImmutableLongArray(new long[]{j9});
    }

    @CanIgnoreReturnValue
    public static final class Builder {
        private long[] array;
        private int count = 0;

        public Builder(int i9) {
            this.array = new long[i9];
        }

        private void ensureRoomFor(int i9) {
            int i10 = this.count + i9;
            long[] jArr = this.array;
            if (i10 > jArr.length) {
                long[] jArr2 = new long[expandedCapacity(jArr.length, i10)];
                System.arraycopy(this.array, 0, jArr2, 0, this.count);
                this.array = jArr2;
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

        public Builder add(long j9) {
            ensureRoomFor(1);
            long[] jArr = this.array;
            int i9 = this.count;
            jArr[i9] = j9;
            this.count = i9 + 1;
            return this;
        }

        public Builder addAll(long[] jArr) {
            ensureRoomFor(jArr.length);
            System.arraycopy(jArr, 0, this.array, this.count, jArr.length);
            this.count += jArr.length;
            return this;
        }

        public ImmutableLongArray build() {
            if (this.count == 0) {
                return ImmutableLongArray.EMPTY;
            }
            return new ImmutableLongArray(this.array, 0, this.count);
        }

        public Builder addAll(Iterable<Long> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Long>) iterable);
            }
            Iterator<Long> it = iterable.iterator();
            while (it.hasNext()) {
                add(it.next().longValue());
            }
            return this;
        }

        public Builder addAll(Collection<Long> collection) {
            ensureRoomFor(collection.size());
            for (Long l9 : collection) {
                long[] jArr = this.array;
                int i9 = this.count;
                this.count = i9 + 1;
                jArr[i9] = l9.longValue();
            }
            return this;
        }

        public Builder addAll(ImmutableLongArray immutableLongArray) {
            ensureRoomFor(immutableLongArray.length());
            System.arraycopy(immutableLongArray.array, immutableLongArray.start, this.array, this.count, immutableLongArray.length());
            this.count += immutableLongArray.length();
            return this;
        }
    }

    private ImmutableLongArray(long[] jArr, int i9, int i10) {
        this.array = jArr;
        this.start = i9;
        this.end = i10;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableLongArray copyOf(Collection<Long> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(collection));
    }

    /* renamed from: of */
    public static ImmutableLongArray m17702of(long j9, long j10) {
        return new ImmutableLongArray(new long[]{j9, j10});
    }

    public static ImmutableLongArray copyOf(Iterable<Long> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Long>) iterable);
        }
        return builder().addAll(iterable).build();
    }

    /* renamed from: of */
    public static ImmutableLongArray m17703of(long j9, long j10, long j11) {
        return new ImmutableLongArray(new long[]{j9, j10, j11});
    }

    /* renamed from: of */
    public static ImmutableLongArray m17704of(long j9, long j10, long j11, long j12) {
        return new ImmutableLongArray(new long[]{j9, j10, j11, j12});
    }

    /* renamed from: of */
    public static ImmutableLongArray m17705of(long j9, long j10, long j11, long j12, long j13) {
        return new ImmutableLongArray(new long[]{j9, j10, j11, j12, j13});
    }

    /* renamed from: of */
    public static ImmutableLongArray m17706of(long j9, long j10, long j11, long j12, long j13, long j14) {
        return new ImmutableLongArray(new long[]{j9, j10, j11, j12, j13, j14});
    }

    /* renamed from: of */
    public static ImmutableLongArray m17707of(long j9, long... jArr) {
        long[] jArr2 = new long[jArr.length + 1];
        jArr2[0] = j9;
        System.arraycopy(jArr, 0, jArr2, 1, jArr.length);
        return new ImmutableLongArray(jArr2);
    }
}
