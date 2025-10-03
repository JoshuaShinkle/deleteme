package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
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
public final class Chars {
    public static final int BYTES = 2;

    @GwtCompatible
    public static class CharArrayAsList extends AbstractList<Character> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final char[] array;
        final int end;
        final int start;

        public CharArrayAsList(char[] cArr) {
            this(cArr, 0, cArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CharArrayAsList)) {
                return super.equals(obj);
            }
            CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
            int size = size();
            if (charArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != charArrayAsList.array[charArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Chars.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Character) || (iIndexOf = Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Character) || (iLastIndexOf = Chars.lastIndexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Character> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            char[] cArr = this.array;
            int i11 = this.start;
            return new CharArrayAsList(cArr, i9 + i11, i11 + i10);
        }

        public char[] toCharArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
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

        public CharArrayAsList(char[] cArr, int i9, int i10) {
            this.array = cArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Character.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Character set(int i9, Character ch) {
            Preconditions.checkElementIndex(i9, size());
            char[] cArr = this.array;
            int i10 = this.start;
            char c9 = cArr[i10 + i9];
            cArr[i10 + i9] = ((Character) Preconditions.checkNotNull(ch)).charValue();
            return Character.valueOf(c9);
        }
    }

    public enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Chars.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(char[] cArr, char[] cArr2) {
            int iMin = Math.min(cArr.length, cArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Chars.compare(cArr[i9], cArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return cArr.length - cArr2.length;
        }
    }

    private Chars() {
    }

    public static List<Character> asList(char... cArr) {
        return cArr.length == 0 ? Collections.emptyList() : new CharArrayAsList(cArr);
    }

    public static char checkedCast(long j9) {
        char c9 = (char) j9;
        Preconditions.checkArgument(((long) c9) == j9, "Out of range: %s", j9);
        return c9;
    }

    public static int compare(char c9, char c10) {
        return c9 - c10;
    }

    public static char[] concat(char[]... cArr) {
        int length = 0;
        for (char[] cArr2 : cArr) {
            length += cArr2.length;
        }
        char[] cArr3 = new char[length];
        int length2 = 0;
        for (char[] cArr4 : cArr) {
            System.arraycopy(cArr4, 0, cArr3, length2, cArr4.length);
            length2 += cArr4.length;
        }
        return cArr3;
    }

    @Beta
    public static char constrainToRange(char c9, char c10, char c11) {
        Preconditions.checkArgument(c10 <= c11, "min (%s) must be less than or equal to max (%s)", c10, c11);
        return c9 < c10 ? c10 : c9 < c11 ? c9 : c11;
    }

    public static boolean contains(char[] cArr, char c9) {
        for (char c10 : cArr) {
            if (c10 == c9) {
                return true;
            }
        }
        return false;
    }

    public static char[] ensureCapacity(char[] cArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return cArr.length < i9 ? Arrays.copyOf(cArr, i9 + i10) : cArr;
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b9, byte b10) {
        return (char) ((b9 << 8) | (b10 & UnsignedBytes.MAX_VALUE));
    }

    public static int hashCode(char c9) {
        return c9;
    }

    public static int indexOf(char[] cArr, char c9) {
        return indexOf(cArr, c9, 0, cArr.length);
    }

    public static String join(String str, char... cArr) {
        Preconditions.checkNotNull(str);
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() * (length - 1)) + length);
        sb.append(cArr[0]);
        for (int i9 = 1; i9 < length; i9++) {
            sb.append(str);
            sb.append(cArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(char[] cArr, char c9) {
        return lastIndexOf(cArr, c9, 0, cArr.length);
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c9 = cArr[0];
        for (int i9 = 1; i9 < cArr.length; i9++) {
            char c10 = cArr[i9];
            if (c10 > c9) {
                c9 = c10;
            }
        }
        return c9;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c9 = cArr[0];
        for (int i9 = 1; i9 < cArr.length; i9++) {
            char c10 = cArr[i9];
            if (c10 < c9) {
                c9 = c10;
            }
        }
        return c9;
    }

    public static char saturatedCast(long j9) {
        if (j9 > 65535) {
            return (char) 65535;
        }
        if (j9 < 0) {
            return (char) 0;
        }
        return (char) j9;
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).toCharArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int i9 = 0; i9 < length; i9++) {
            cArr[i9] = ((Character) Preconditions.checkNotNull(array[i9])).charValue();
        }
        return cArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char c9) {
        return new byte[]{(byte) (c9 >> '\b'), (byte) c9};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(char[] cArr, char c9, int i9, int i10) {
        while (i9 < i10) {
            if (cArr[i9] == c9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(char[] cArr, char c9, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (cArr[i11] == c9) {
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
    public static int indexOf(char[] cArr, char[] cArr2) {
        Preconditions.checkNotNull(cArr, "array");
        Preconditions.checkNotNull(cArr2, "target");
        if (cArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (cArr.length - cArr2.length) + 1) {
            for (int i10 = 0; i10 < cArr2.length; i10++) {
                if (cArr[i9 + i10] != cArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
