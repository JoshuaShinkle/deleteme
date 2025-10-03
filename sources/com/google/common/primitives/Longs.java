package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
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
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    public static final class AsciiDigits {
        private static final byte[] asciiDigits;

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i9 = 0; i9 <= 9; i9++) {
                bArr[i9 + 48] = (byte) i9;
            }
            for (int i10 = 0; i10 <= 26; i10++) {
                byte b9 = (byte) (i10 + 10);
                bArr[i10 + 65] = b9;
                bArr[i10 + 97] = b9;
            }
            asciiDigits = bArr;
        }

        private AsciiDigits() {
        }

        public static int digit(char c9) {
            if (c9 < 128) {
                return asciiDigits[c9];
            }
            return -1;
        }
    }

    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Longs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int iMin = Math.min(jArr.length, jArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Longs.compare(jArr[i9], jArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    @GwtCompatible
    public static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final long[] array;
        final int end;
        final int start;

        public LongArrayAsList(long[] jArr) {
            this(jArr, 0, jArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Long) && Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LongArrayAsList)) {
                return super.equals(obj);
            }
            LongArrayAsList longArrayAsList = (LongArrayAsList) obj;
            int size = size();
            if (longArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != longArrayAsList.array[longArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Longs.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Long) || (iIndexOf = Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Long) || (iLastIndexOf = Longs.lastIndexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            long[] jArr = this.array;
            int i11 = this.start;
            return new LongArrayAsList(jArr, i9 + i11, i11 + i10);
        }

        public long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 10);
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

        public LongArrayAsList(long[] jArr, int i9, int i10) {
            this.array = jArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Long.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Long set(int i9, Long l9) {
            Preconditions.checkElementIndex(i9, size());
            long[] jArr = this.array;
            int i10 = this.start;
            long j9 = jArr[i10 + i9];
            jArr[i10 + i9] = ((Long) Preconditions.checkNotNull(l9)).longValue();
            return Long.valueOf(j9);
        }
    }

    public static final class LongConverter extends Converter<String, Long> implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1;

        private LongConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Long l9) {
            return l9.toString();
        }

        @Override // com.google.common.base.Converter
        public Long doForward(String str) {
            return Long.decode(str);
        }
    }

    private Longs() {
    }

    public static List<Long> asList(long... jArr) {
        return jArr.length == 0 ? Collections.emptyList() : new LongArrayAsList(jArr);
    }

    public static int compare(long j9, long j10) {
        if (j9 < j10) {
            return -1;
        }
        return j9 > j10 ? 1 : 0;
    }

    public static long[] concat(long[]... jArr) {
        int length = 0;
        for (long[] jArr2 : jArr) {
            length += jArr2.length;
        }
        long[] jArr3 = new long[length];
        int length2 = 0;
        for (long[] jArr4 : jArr) {
            System.arraycopy(jArr4, 0, jArr3, length2, jArr4.length);
            length2 += jArr4.length;
        }
        return jArr3;
    }

    @Beta
    public static long constrainToRange(long j9, long j10, long j11) {
        Preconditions.checkArgument(j10 <= j11, "min (%s) must be less than or equal to max (%s)", j10, j11);
        return Math.min(Math.max(j9, j10), j11);
    }

    public static boolean contains(long[] jArr, long j9) {
        for (long j10 : jArr) {
            if (j10 == j9) {
                return true;
            }
        }
        return false;
    }

    public static long[] ensureCapacity(long[] jArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return jArr.length < i9 ? Arrays.copyOf(jArr, i9 + i10) : jArr;
    }

    public static long fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 8, "array too small: %s < %s", bArr.length, 8);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]);
    }

    public static long fromBytes(byte b9, byte b10, byte b11, byte b12, byte b13, byte b14, byte b15, byte b16) {
        return ((b10 & 255) << 48) | ((b9 & 255) << 56) | ((b11 & 255) << 40) | ((b12 & 255) << 32) | ((b13 & 255) << 24) | ((b14 & 255) << 16) | ((b15 & 255) << 8) | (b16 & 255);
    }

    public static int hashCode(long j9) {
        return (int) (j9 ^ (j9 >>> 32));
    }

    public static int indexOf(long[] jArr, long j9) {
        return indexOf(jArr, j9, 0, jArr.length);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 10);
        sb.append(jArr[0]);
        for (int i9 = 1; i9 < jArr.length; i9++) {
            sb.append(str);
            sb.append(jArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(long[] jArr, long j9) {
        return lastIndexOf(jArr, j9, 0, jArr.length);
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j9 = jArr[0];
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long j10 = jArr[i9];
            if (j10 > j9) {
                j9 = j10;
            }
        }
        return j9;
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j9 = jArr[0];
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long j10 = jArr[i9];
            if (j10 < j9) {
                j9 = j10;
            }
        }
        return j9;
    }

    @Beta
    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        long[] jArr = new long[length];
        for (int i9 = 0; i9 < length; i9++) {
            jArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).longValue();
        }
        return jArr;
    }

    public static byte[] toByteArray(long j9) {
        byte[] bArr = new byte[8];
        for (int i9 = 7; i9 >= 0; i9--) {
            bArr[i9] = (byte) (255 & j9);
            j9 >>= 8;
        }
        return bArr;
    }

    @Beta
    public static Long tryParse(String str) {
        return tryParse(str, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(long[] jArr, long j9, int i9, int i10) {
        while (i9 < i10) {
            if (jArr[i9] == j9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(long[] jArr, long j9, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (jArr[i11] == j9) {
                return i11;
            }
        }
        return -1;
    }

    @Beta
    public static Long tryParse(String str, int i9) {
        if (((String) Preconditions.checkNotNull(str)).isEmpty()) {
            return null;
        }
        if (i9 < 2 || i9 > 36) {
            throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + i9);
        }
        int i10 = str.charAt(0) == '-' ? 1 : 0;
        if (i10 == str.length()) {
            return null;
        }
        int i11 = i10 + 1;
        int iDigit = AsciiDigits.digit(str.charAt(i10));
        if (iDigit < 0 || iDigit >= i9) {
            return null;
        }
        long j9 = -iDigit;
        long j10 = i9;
        long j11 = Long.MIN_VALUE / j10;
        while (i11 < str.length()) {
            int i12 = i11 + 1;
            int iDigit2 = AsciiDigits.digit(str.charAt(i11));
            if (iDigit2 < 0 || iDigit2 >= i9 || j9 < j11) {
                return null;
            }
            long j12 = j9 * j10;
            long j13 = iDigit2;
            if (j12 < j13 - Long.MIN_VALUE) {
                return null;
            }
            j9 = j12 - j13;
            i11 = i12;
        }
        if (i10 != 0) {
            return Long.valueOf(j9);
        }
        if (j9 == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-j9);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(long[] jArr, long[] jArr2) {
        Preconditions.checkNotNull(jArr, "array");
        Preconditions.checkNotNull(jArr2, "target");
        if (jArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (jArr.length - jArr2.length) + 1) {
            for (int i10 = 0; i10 < jArr2.length; i10++) {
                if (jArr[i9 + i10] != jArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
