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
import java.util.regex.Pattern;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Doubles {
    public static final int BYTES = 8;

    @GwtIncompatible
    static final Pattern FLOATING_POINT_PATTERN = fpPattern();

    @GwtCompatible
    public static class DoubleArrayAsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final double[] array;
        final int end;
        final int start;

        public DoubleArrayAsList(double[] dArr) {
            this(dArr, 0, dArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Double) && Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DoubleArrayAsList)) {
                return super.equals(obj);
            }
            DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) obj;
            int size = size();
            if (doubleArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != doubleArrayAsList.array[doubleArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Doubles.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Double) || (iIndexOf = Doubles.indexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Double) || (iLastIndexOf = Doubles.lastIndexOf(this.array, ((Double) obj).doubleValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Double> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            double[] dArr = this.array;
            int i11 = this.start;
            return new DoubleArrayAsList(dArr, i9 + i11, i11 + i10);
        }

        public double[] toDoubleArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
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

        public DoubleArrayAsList(double[] dArr, int i9, int i10) {
            this.array = dArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Double.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Double set(int i9, Double d9) {
            Preconditions.checkElementIndex(i9, size());
            double[] dArr = this.array;
            int i10 = this.start;
            double d10 = dArr[i10 + i9];
            dArr[i10 + i9] = ((Double) Preconditions.checkNotNull(d9)).doubleValue();
            return Double.valueOf(d10);
        }
    }

    public static final class DoubleConverter extends Converter<String, Double> implements Serializable {
        static final DoubleConverter INSTANCE = new DoubleConverter();
        private static final long serialVersionUID = 1;

        private DoubleConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Double d9) {
            return d9.toString();
        }

        @Override // com.google.common.base.Converter
        public Double doForward(String str) {
            return Double.valueOf(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Doubles.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(double[] dArr, double[] dArr2) {
            int iMin = Math.min(dArr.length, dArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Double.compare(dArr[i9], dArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return dArr.length - dArr2.length;
        }
    }

    private Doubles() {
    }

    public static List<Double> asList(double... dArr) {
        return dArr.length == 0 ? Collections.emptyList() : new DoubleArrayAsList(dArr);
    }

    public static int compare(double d9, double d10) {
        return Double.compare(d9, d10);
    }

    public static double[] concat(double[]... dArr) {
        int length = 0;
        for (double[] dArr2 : dArr) {
            length += dArr2.length;
        }
        double[] dArr3 = new double[length];
        int length2 = 0;
        for (double[] dArr4 : dArr) {
            System.arraycopy(dArr4, 0, dArr3, length2, dArr4.length);
            length2 += dArr4.length;
        }
        return dArr3;
    }

    @Beta
    public static double constrainToRange(double d9, double d10, double d11) {
        Preconditions.checkArgument(d10 <= d11, "min (%s) must be less than or equal to max (%s)", Double.valueOf(d10), Double.valueOf(d11));
        return Math.min(Math.max(d9, d10), d11);
    }

    public static boolean contains(double[] dArr, double d9) {
        for (double d10 : dArr) {
            if (d10 == d9) {
                return true;
            }
        }
        return false;
    }

    public static double[] ensureCapacity(double[] dArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return dArr.length < i9 ? Arrays.copyOf(dArr, i9 + i10) : dArr;
    }

    @GwtIncompatible
    private static Pattern fpPattern() {
        return Pattern.compile("[+-]?(?:NaN|Infinity|" + ("(?:\\d++(?:\\.\\d*+)?|\\.\\d++)(?:[eE][+-]?\\d++)?[fFdD]?") + "|" + ("0[xX](?:\\p{XDigit}++(?:\\.\\p{XDigit}*+)?|\\.\\p{XDigit}++)[pP][+-]?\\d++[fFdD]?") + ")");
    }

    public static int hashCode(double d9) {
        return Double.valueOf(d9).hashCode();
    }

    public static int indexOf(double[] dArr, double d9) {
        return indexOf(dArr, d9, 0, dArr.length);
    }

    public static boolean isFinite(double d9) {
        return Double.NEGATIVE_INFINITY < d9 && d9 < Double.POSITIVE_INFINITY;
    }

    public static String join(String str, double... dArr) {
        Preconditions.checkNotNull(str);
        if (dArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(dArr.length * 12);
        sb.append(dArr[0]);
        for (int i9 = 1; i9 < dArr.length; i9++) {
            sb.append(str);
            sb.append(dArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(double[] dArr, double d9) {
        return lastIndexOf(dArr, d9, 0, dArr.length);
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static double max(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double dMax = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            dMax = Math.max(dMax, dArr[i9]);
        }
        return dMax;
    }

    public static double min(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double dMin = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            dMin = Math.min(dMin, dArr[i9]);
        }
        return dMin;
    }

    @Beta
    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.INSTANCE;
    }

    public static double[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).toDoubleArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        double[] dArr = new double[length];
        for (int i9 = 0; i9 < length; i9++) {
            dArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).doubleValue();
        }
        return dArr;
    }

    @Beta
    @GwtIncompatible
    public static Double tryParse(String str) {
        if (!FLOATING_POINT_PATTERN.matcher(str).matches()) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(double[] dArr, double d9, int i9, int i10) {
        while (i9 < i10) {
            if (dArr[i9] == d9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(double[] dArr, double d9, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (dArr[i11] == d9) {
                return i11;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(double[] dArr, double[] dArr2) {
        Preconditions.checkNotNull(dArr, "array");
        Preconditions.checkNotNull(dArr2, "target");
        if (dArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (dArr.length - dArr2.length) + 1) {
            for (int i10 = 0; i10 < dArr2.length; i10++) {
                if (dArr[i9 + i10] != dArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
