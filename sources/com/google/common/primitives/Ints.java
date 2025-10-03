package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Ints {
    public static final int BYTES = 4;
    public static final int MAX_POWER_OF_TWO = 1073741824;

    @GwtCompatible
    public static class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final int[] array;
        final int end;
        final int start;

        public IntArrayAsList(int[] iArr) {
            this(iArr, 0, iArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Integer) && Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IntArrayAsList)) {
                return super.equals(obj);
            }
            IntArrayAsList intArrayAsList = (IntArrayAsList) obj;
            int size = size();
            if (intArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != intArrayAsList.array[intArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Ints.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Integer) || (iIndexOf = Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int iLastIndexOf;
            if (!(obj instanceof Integer) || (iLastIndexOf = Ints.lastIndexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Integer> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            int[] iArr = this.array;
            int i11 = this.start;
            return new IntArrayAsList(iArr, i9 + i11, i11 + i10);
        }

        public int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        public IntArrayAsList(int[] iArr, int i9, int i10) {
            this.array = iArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Integer.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer set(int i9, Integer num) {
            Preconditions.checkElementIndex(i9, size());
            int[] iArr = this.array;
            int i10 = this.start;
            int i11 = iArr[i10 + i9];
            iArr[i10 + i9] = ((Integer) Preconditions.checkNotNull(num)).intValue();
            return Integer.valueOf(i11);
        }
    }

    public static final class IntConverter extends Converter<String, Integer> implements Serializable {
        static final IntConverter INSTANCE = new IntConverter();
        private static final long serialVersionUID = 1;

        private IntConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Ints.stringConverter()";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Integer num) {
            return num.toString();
        }

        @Override // com.google.common.base.Converter
        public Integer doForward(String str) {
            return Integer.decode(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Ints.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int iMin = Math.min(iArr.length, iArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Ints.compare(iArr[i9], iArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return iArr.length - iArr2.length;
        }
    }

    private Ints() {
    }

    public static List<Integer> asList(int... iArr) {
        return iArr.length == 0 ? Collections.emptyList() : new IntArrayAsList(iArr);
    }

    public static int checkedCast(long j9) {
        int i9 = (int) j9;
        Preconditions.checkArgument(((long) i9) == j9, "Out of range: %s", j9);
        return i9;
    }

    public static int compare(int i9, int i10) {
        if (i9 < i10) {
            return -1;
        }
        return i9 > i10 ? 1 : 0;
    }

    public static int[] concat(int[]... iArr) {
        int length = 0;
        for (int[] iArr2 : iArr) {
            length += iArr2.length;
        }
        int[] iArr3 = new int[length];
        int length2 = 0;
        for (int[] iArr4 : iArr) {
            System.arraycopy(iArr4, 0, iArr3, length2, iArr4.length);
            length2 += iArr4.length;
        }
        return iArr3;
    }

    @Beta
    public static int constrainToRange(int i9, int i10, int i11) {
        Preconditions.checkArgument(i10 <= i11, "min (%s) must be less than or equal to max (%s)", i10, i11);
        return Math.min(Math.max(i9, i10), i11);
    }

    public static boolean contains(int[] iArr, int i9) {
        for (int i10 : iArr) {
            if (i10 == i9) {
                return true;
            }
        }
        return false;
    }

    public static int[] ensureCapacity(int[] iArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return iArr.length < i9 ? Arrays.copyOf(iArr, i9 + i10) : iArr;
    }

    public static int fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 4, "array too small: %s < %s", bArr.length, 4);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3]);
    }

    public static int fromBytes(byte b9, byte b10, byte b11, byte b12) {
        return (b9 << Ascii.CAN) | ((b10 & UnsignedBytes.MAX_VALUE) << 16) | ((b11 & UnsignedBytes.MAX_VALUE) << 8) | (b12 & UnsignedBytes.MAX_VALUE);
    }

    public static int hashCode(int i9) {
        return i9;
    }

    public static int indexOf(int[] iArr, int i9) {
        return indexOf(iArr, i9, 0, iArr.length);
    }

    public static String join(String str, int... iArr) {
        Preconditions.checkNotNull(str);
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iArr.length * 5);
        sb.append(iArr[0]);
        for (int i9 = 1; i9 < iArr.length; i9++) {
            sb.append(str);
            sb.append(iArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(int[] iArr, int i9) {
        return lastIndexOf(iArr, i9, 0, iArr.length);
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int max(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int i9 = iArr[0];
        for (int i10 = 1; i10 < iArr.length; i10++) {
            int i11 = iArr[i10];
            if (i11 > i9) {
                i9 = i11;
            }
        }
        return i9;
    }

    public static int min(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int i9 = iArr[0];
        for (int i10 = 1; i10 < iArr.length; i10++) {
            int i11 = iArr[i10];
            if (i11 < i9) {
                i9 = i11;
            }
        }
        return i9;
    }

    public static int saturatedCast(long j9) {
        if (j9 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j9 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j9;
    }

    @Beta
    public static Converter<String, Integer> stringConverter() {
        return IntConverter.INSTANCE;
    }

    public static int[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof IntArrayAsList) {
            return ((IntArrayAsList) collection).toIntArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i9 = 0; i9 < length; i9++) {
            iArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).intValue();
        }
        return iArr;
    }

    public static byte[] toByteArray(int i9) {
        return new byte[]{(byte) (i9 >> 24), (byte) (i9 >> 16), (byte) (i9 >> 8), (byte) i9};
    }

    @Beta
    public static Integer tryParse(String str) {
        return tryParse(str, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(int[] iArr, int i9, int i10, int i11) {
        while (i10 < i11) {
            if (iArr[i10] == i9) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(int[] iArr, int i9, int i10, int i11) {
        for (int i12 = i11 - 1; i12 >= i10; i12--) {
            if (iArr[i12] == i9) {
                return i12;
            }
        }
        return -1;
    }

    @Beta
    public static Integer tryParse(String str, int i9) {
        Long lTryParse = Longs.tryParse(str, i9);
        if (lTryParse == null || lTryParse.longValue() != lTryParse.intValue()) {
            return null;
        }
        return Integer.valueOf(lTryParse.intValue());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(int[] iArr, int[] iArr2) {
        Preconditions.checkNotNull(iArr, "array");
        Preconditions.checkNotNull(iArr2, "target");
        if (iArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (iArr.length - iArr2.length) + 1) {
            for (int i10 = 0; i10 < iArr2.length; i10++) {
                if (iArr[i9 + i10] != iArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
