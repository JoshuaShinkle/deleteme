package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
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
public final class Booleans {

    @GwtCompatible
    public static class BooleanArrayAsList extends AbstractList<Boolean> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final boolean[] array;
        final int end;
        final int start;

        public BooleanArrayAsList(boolean[] zArr) {
            this(zArr, 0, zArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Boolean) && Booleans.indexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BooleanArrayAsList)) {
                return super.equals(obj);
            }
            BooleanArrayAsList booleanArrayAsList = (BooleanArrayAsList) obj;
            int size = size();
            if (booleanArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != booleanArrayAsList.array[booleanArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Booleans.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Boolean) || (iIndexOf = Booleans.indexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Boolean) || (iLastIndexOf = Booleans.lastIndexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Boolean> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            boolean[] zArr = this.array;
            int i11 = this.start;
            return new BooleanArrayAsList(zArr, i9 + i11, i11 + i10);
        }

        public boolean[] toBooleanArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 7);
            sb.append(this.array[this.start] ? "[true" : "[false");
            int i9 = this.start;
            while (true) {
                i9++;
                if (i9 >= this.end) {
                    sb.append(']');
                    return sb.toString();
                }
                sb.append(this.array[i9] ? ", true" : ", false");
            }
        }

        public BooleanArrayAsList(boolean[] zArr, int i9, int i10) {
            this.array = zArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Boolean get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Boolean.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Boolean set(int i9, Boolean bool) {
            Preconditions.checkElementIndex(i9, size());
            boolean[] zArr = this.array;
            int i10 = this.start;
            boolean z8 = zArr[i10 + i9];
            zArr[i10 + i9] = ((Boolean) Preconditions.checkNotNull(bool)).booleanValue();
            return Boolean.valueOf(z8);
        }
    }

    public enum BooleanComparator implements Comparator<Boolean> {
        TRUE_FIRST(1, "Booleans.trueFirst()"),
        FALSE_FIRST(-1, "Booleans.falseFirst()");

        private final String toString;
        private final int trueValue;

        BooleanComparator(int i9, String str) {
            this.trueValue = i9;
            this.toString = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.toString;
        }

        @Override // java.util.Comparator
        public int compare(Boolean bool, Boolean bool2) {
            return (bool2.booleanValue() ? this.trueValue : 0) - (bool.booleanValue() ? this.trueValue : 0);
        }
    }

    public enum LexicographicalComparator implements Comparator<boolean[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Booleans.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(boolean[] zArr, boolean[] zArr2) {
            int iMin = Math.min(zArr.length, zArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Booleans.compare(zArr[i9], zArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return zArr.length - zArr2.length;
        }
    }

    private Booleans() {
    }

    public static List<Boolean> asList(boolean... zArr) {
        return zArr.length == 0 ? Collections.emptyList() : new BooleanArrayAsList(zArr);
    }

    public static int compare(boolean z8, boolean z9) {
        if (z8 == z9) {
            return 0;
        }
        return z8 ? 1 : -1;
    }

    public static boolean[] concat(boolean[]... zArr) {
        int length = 0;
        for (boolean[] zArr2 : zArr) {
            length += zArr2.length;
        }
        boolean[] zArr3 = new boolean[length];
        int length2 = 0;
        for (boolean[] zArr4 : zArr) {
            System.arraycopy(zArr4, 0, zArr3, length2, zArr4.length);
            length2 += zArr4.length;
        }
        return zArr3;
    }

    public static boolean contains(boolean[] zArr, boolean z8) {
        for (boolean z9 : zArr) {
            if (z9 == z8) {
                return true;
            }
        }
        return false;
    }

    @Beta
    public static int countTrue(boolean... zArr) {
        int i9 = 0;
        for (boolean z8 : zArr) {
            if (z8) {
                i9++;
            }
        }
        return i9;
    }

    public static boolean[] ensureCapacity(boolean[] zArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return zArr.length < i9 ? Arrays.copyOf(zArr, i9 + i10) : zArr;
    }

    @Beta
    public static Comparator<Boolean> falseFirst() {
        return BooleanComparator.FALSE_FIRST;
    }

    public static int hashCode(boolean z8) {
        return z8 ? 1231 : 1237;
    }

    public static int indexOf(boolean[] zArr, boolean z8) {
        return indexOf(zArr, z8, 0, zArr.length);
    }

    public static String join(String str, boolean... zArr) {
        Preconditions.checkNotNull(str);
        if (zArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zArr.length * 7);
        sb.append(zArr[0]);
        for (int i9 = 1; i9 < zArr.length; i9++) {
            sb.append(str);
            sb.append(zArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(boolean[] zArr, boolean z8) {
        return lastIndexOf(zArr, z8, 0, zArr.length);
    }

    public static Comparator<boolean[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static boolean[] toArray(Collection<Boolean> collection) {
        if (collection instanceof BooleanArrayAsList) {
            return ((BooleanArrayAsList) collection).toBooleanArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        boolean[] zArr = new boolean[length];
        for (int i9 = 0; i9 < length; i9++) {
            zArr[i9] = ((Boolean) Preconditions.checkNotNull(array[i9])).booleanValue();
        }
        return zArr;
    }

    @Beta
    public static Comparator<Boolean> trueFirst() {
        return BooleanComparator.TRUE_FIRST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(boolean[] zArr, boolean z8, int i9, int i10) {
        while (i9 < i10) {
            if (zArr[i9] == z8) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(boolean[] zArr, boolean z8, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (zArr[i11] == z8) {
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
    public static int indexOf(boolean[] zArr, boolean[] zArr2) {
        Preconditions.checkNotNull(zArr, "array");
        Preconditions.checkNotNull(zArr2, "target");
        if (zArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (zArr.length - zArr2.length) + 1) {
            for (int i10 = 0; i10 < zArr2.length; i10++) {
                if (zArr[i9 + i10] != zArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
