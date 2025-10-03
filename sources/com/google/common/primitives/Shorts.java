package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
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

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Shorts {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    public enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(short[] sArr, short[] sArr2) {
            int iMin = Math.min(sArr.length, sArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Shorts.compare(sArr[i9], sArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return sArr.length - sArr2.length;
        }
    }

    @GwtCompatible
    public static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final short[] array;
        final int end;
        final int start;

        public ShortArrayAsList(short[] sArr) {
            this(sArr, 0, sArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Short) && Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShortArrayAsList)) {
                return super.equals(obj);
            }
            ShortArrayAsList shortArrayAsList = (ShortArrayAsList) obj;
            int size = size();
            if (shortArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != shortArrayAsList.array[shortArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Shorts.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Short) || (iIndexOf = Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Short) || (iLastIndexOf = Shorts.lastIndexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Short> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            short[] sArr = this.array;
            int i11 = this.start;
            return new ShortArrayAsList(sArr, i9 + i11, i11 + i10);
        }

        public short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 6);
            sb.append('[');
            sb.append((int) this.array[this.start]);
            int i9 = this.start;
            while (true) {
                i9++;
                if (i9 >= this.end) {
                    sb.append(']');
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.array[i9]);
            }
        }

        public ShortArrayAsList(short[] sArr, int i9, int i10) {
            this.array = sArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Short get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Short.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Short set(int i9, Short sh) {
            Preconditions.checkElementIndex(i9, size());
            short[] sArr = this.array;
            int i10 = this.start;
            short s8 = sArr[i10 + i9];
            sArr[i10 + i9] = ((Short) Preconditions.checkNotNull(sh)).shortValue();
            return Short.valueOf(s8);
        }
    }

    public static final class ShortConverter extends Converter<String, Short> implements Serializable {
        static final ShortConverter INSTANCE = new ShortConverter();
        private static final long serialVersionUID = 1;

        private ShortConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Short sh) {
            return sh.toString();
        }

        @Override // com.google.common.base.Converter
        public Short doForward(String str) {
            return Short.decode(str);
        }
    }

    private Shorts() {
    }

    public static List<Short> asList(short... sArr) {
        return sArr.length == 0 ? Collections.emptyList() : new ShortArrayAsList(sArr);
    }

    public static short checkedCast(long j9) {
        short s8 = (short) j9;
        Preconditions.checkArgument(((long) s8) == j9, "Out of range: %s", j9);
        return s8;
    }

    public static int compare(short s8, short s9) {
        return s8 - s9;
    }

    public static short[] concat(short[]... sArr) {
        int length = 0;
        for (short[] sArr2 : sArr) {
            length += sArr2.length;
        }
        short[] sArr3 = new short[length];
        int length2 = 0;
        for (short[] sArr4 : sArr) {
            System.arraycopy(sArr4, 0, sArr3, length2, sArr4.length);
            length2 += sArr4.length;
        }
        return sArr3;
    }

    @Beta
    public static short constrainToRange(short s8, short s9, short s10) {
        Preconditions.checkArgument(s9 <= s10, "min (%s) must be less than or equal to max (%s)", (int) s9, (int) s10);
        return s8 < s9 ? s9 : s8 < s10 ? s8 : s10;
    }

    public static boolean contains(short[] sArr, short s8) {
        for (short s9 : sArr) {
            if (s9 == s8) {
                return true;
            }
        }
        return false;
    }

    public static short[] ensureCapacity(short[] sArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return sArr.length < i9 ? Arrays.copyOf(sArr, i9 + i10) : sArr;
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b9, byte b10) {
        return (short) ((b9 << 8) | (b10 & UnsignedBytes.MAX_VALUE));
    }

    public static int hashCode(short s8) {
        return s8;
    }

    public static int indexOf(short[] sArr, short s8) {
        return indexOf(sArr, s8, 0, sArr.length);
    }

    public static String join(String str, short... sArr) {
        Preconditions.checkNotNull(str);
        if (sArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(sArr.length * 6);
        sb.append((int) sArr[0]);
        for (int i9 = 1; i9 < sArr.length; i9++) {
            sb.append(str);
            sb.append((int) sArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(short[] sArr, short s8) {
        return lastIndexOf(sArr, s8, 0, sArr.length);
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static short max(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s8 = sArr[0];
        for (int i9 = 1; i9 < sArr.length; i9++) {
            short s9 = sArr[i9];
            if (s9 > s8) {
                s8 = s9;
            }
        }
        return s8;
    }

    public static short min(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s8 = sArr[0];
        for (int i9 = 1; i9 < sArr.length; i9++) {
            short s9 = sArr[i9];
            if (s9 < s8) {
                s8 = s9;
            }
        }
        return s8;
    }

    public static short saturatedCast(long j9) {
        if (j9 > 32767) {
            return Short.MAX_VALUE;
        }
        if (j9 < -32768) {
            return Short.MIN_VALUE;
        }
        return (short) j9;
    }

    @Beta
    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).toShortArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        short[] sArr = new short[length];
        for (int i9 = 0; i9 < length; i9++) {
            sArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).shortValue();
        }
        return sArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short s8) {
        return new byte[]{(byte) (s8 >> 8), (byte) s8};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(short[] sArr, short s8, int i9, int i10) {
        while (i9 < i10) {
            if (sArr[i9] == s8) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(short[] sArr, short s8, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (sArr[i11] == s8) {
                return i11;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(short[] sArr, short[] sArr2) {
        Preconditions.checkNotNull(sArr, "array");
        Preconditions.checkNotNull(sArr2, "target");
        if (sArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (sArr.length - sArr2.length) + 1) {
            for (int i10 = 0; i10 < sArr2.length; i10++) {
                if (sArr[i9 + i10] != sArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
