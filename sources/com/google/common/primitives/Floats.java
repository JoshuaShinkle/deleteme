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
public final class Floats {
    public static final int BYTES = 4;

    @GwtCompatible
    public static class FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final float[] array;
        final int end;
        final int start;

        public FloatArrayAsList(float[] fArr) {
            this(fArr, 0, fArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Float) && Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FloatArrayAsList)) {
                return super.equals(obj);
            }
            FloatArrayAsList floatArrayAsList = (FloatArrayAsList) obj;
            int size = size();
            if (floatArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != floatArrayAsList.array[floatArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Floats.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Float) || (iIndexOf = Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
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
            if (!(obj instanceof Float) || (iLastIndexOf = Floats.lastIndexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Float> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            float[] fArr = this.array;
            int i11 = this.start;
            return new FloatArrayAsList(fArr, i9 + i11, i11 + i10);
        }

        public float[] toFloatArray() {
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

        public FloatArrayAsList(float[] fArr, int i9, int i10) {
            this.array = fArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Float get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Float.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Float set(int i9, Float f9) {
            Preconditions.checkElementIndex(i9, size());
            float[] fArr = this.array;
            int i10 = this.start;
            float f10 = fArr[i10 + i9];
            fArr[i10 + i9] = ((Float) Preconditions.checkNotNull(f9)).floatValue();
            return Float.valueOf(f10);
        }
    }

    public static final class FloatConverter extends Converter<String, Float> implements Serializable {
        static final FloatConverter INSTANCE = new FloatConverter();
        private static final long serialVersionUID = 1;

        private FloatConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Floats.stringConverter()";
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Float f9) {
            return f9.toString();
        }

        @Override // com.google.common.base.Converter
        public Float doForward(String str) {
            return Float.valueOf(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<float[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Floats.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(float[] fArr, float[] fArr2) {
            int iMin = Math.min(fArr.length, fArr2.length);
            for (int i9 = 0; i9 < iMin; i9++) {
                int iCompare = Float.compare(fArr[i9], fArr2[i9]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return fArr.length - fArr2.length;
        }
    }

    private Floats() {
    }

    public static List<Float> asList(float... fArr) {
        return fArr.length == 0 ? Collections.emptyList() : new FloatArrayAsList(fArr);
    }

    public static int compare(float f9, float f10) {
        return Float.compare(f9, f10);
    }

    public static float[] concat(float[]... fArr) {
        int length = 0;
        for (float[] fArr2 : fArr) {
            length += fArr2.length;
        }
        float[] fArr3 = new float[length];
        int length2 = 0;
        for (float[] fArr4 : fArr) {
            System.arraycopy(fArr4, 0, fArr3, length2, fArr4.length);
            length2 += fArr4.length;
        }
        return fArr3;
    }

    @Beta
    public static float constrainToRange(float f9, float f10, float f11) {
        Preconditions.checkArgument(f10 <= f11, "min (%s) must be less than or equal to max (%s)", Float.valueOf(f10), Float.valueOf(f11));
        return Math.min(Math.max(f9, f10), f11);
    }

    public static boolean contains(float[] fArr, float f9) {
        for (float f10 : fArr) {
            if (f10 == f9) {
                return true;
            }
        }
        return false;
    }

    public static float[] ensureCapacity(float[] fArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return fArr.length < i9 ? Arrays.copyOf(fArr, i9 + i10) : fArr;
    }

    public static int hashCode(float f9) {
        return Float.valueOf(f9).hashCode();
    }

    public static int indexOf(float[] fArr, float f9) {
        return indexOf(fArr, f9, 0, fArr.length);
    }

    public static boolean isFinite(float f9) {
        return Float.NEGATIVE_INFINITY < f9 && f9 < Float.POSITIVE_INFINITY;
    }

    public static String join(String str, float... fArr) {
        Preconditions.checkNotNull(str);
        if (fArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(fArr.length * 12);
        sb.append(fArr[0]);
        for (int i9 = 1; i9 < fArr.length; i9++) {
            sb.append(str);
            sb.append(fArr[i9]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(float[] fArr, float f9) {
        return lastIndexOf(fArr, f9, 0, fArr.length);
    }

    public static Comparator<float[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static float max(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float fMax = fArr[0];
        for (int i9 = 1; i9 < fArr.length; i9++) {
            fMax = Math.max(fMax, fArr[i9]);
        }
        return fMax;
    }

    public static float min(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float fMin = fArr[0];
        for (int i9 = 1; i9 < fArr.length; i9++) {
            fMin = Math.min(fMin, fArr[i9]);
        }
        return fMin;
    }

    @Beta
    public static Converter<String, Float> stringConverter() {
        return FloatConverter.INSTANCE;
    }

    public static float[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof FloatArrayAsList) {
            return ((FloatArrayAsList) collection).toFloatArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        float[] fArr = new float[length];
        for (int i9 = 0; i9 < length; i9++) {
            fArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).floatValue();
        }
        return fArr;
    }

    @Beta
    @GwtIncompatible
    public static Float tryParse(String str) {
        if (!Doubles.FLOATING_POINT_PATTERN.matcher(str).matches()) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(float[] fArr, float f9, int i9, int i10) {
        while (i9 < i10) {
            if (fArr[i9] == f9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(float[] fArr, float f9, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (fArr[i11] == f9) {
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
    public static int indexOf(float[] fArr, float[] fArr2) {
        Preconditions.checkNotNull(fArr, "array");
        Preconditions.checkNotNull(fArr2, "target");
        if (fArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (fArr.length - fArr2.length) + 1) {
            for (int i10 = 0; i10 < fArr2.length; i10++) {
                if (fArr[i9 + i10] != fArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
