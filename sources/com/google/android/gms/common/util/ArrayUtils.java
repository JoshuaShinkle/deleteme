package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    @KeepForSdk
    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length == 0) {
            return (T[]) ((Object[]) Array.newInstance(tArr.getClass(), 0));
        }
        int length = 0;
        for (T[] tArr2 : tArr) {
            length += tArr2.length;
        }
        T[] tArr3 = (T[]) Arrays.copyOf(tArr[0], length);
        int length2 = tArr[0].length;
        for (int i9 = 1; i9 < tArr.length; i9++) {
            T[] tArr4 = tArr[i9];
            int length3 = tArr4.length;
            System.arraycopy(tArr4, 0, tArr3, length2, length3);
            length2 += length3;
        }
        return tArr3;
    }

    @KeepForSdk
    public static byte[] concatByteArrays(byte[]... bArr) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr[0], length);
        int length2 = bArr[0].length;
        for (int i9 = 1; i9 < bArr.length; i9++) {
            byte[] bArr3 = bArr[i9];
            int length3 = bArr3.length;
            System.arraycopy(bArr3, 0, bArrCopyOf, length2, length3);
            length2 += length3;
        }
        return bArrCopyOf;
    }

    @KeepForSdk
    public static boolean contains(int[] iArr, int i9) {
        if (iArr == null) {
            return false;
        }
        for (int i10 : iArr) {
            if (i10 == i9) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    @KeepForSdk
    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int length;
        int i9;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || (length = tArr2.length) == 0) {
            return (T[]) Arrays.copyOf(tArr, tArr.length);
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr2.getClass().getComponentType(), tArr.length));
        if (length == 1) {
            i9 = 0;
            for (T t8 : tArr) {
                if (!Objects.equal(tArr2[0], t8)) {
                    tArr3[i9] = t8;
                    i9++;
                }
            }
        } else {
            int i10 = 0;
            for (T t9 : tArr) {
                if (!contains(tArr2, t9)) {
                    tArr3[i10] = t9;
                    i10++;
                }
            }
            i9 = i10;
        }
        if (tArr3 == null) {
            return null;
        }
        return i9 == tArr3.length ? tArr3 : (T[]) Arrays.copyOf(tArr3, i9);
    }

    @KeepForSdk
    public static <T> ArrayList<T> toArrayList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        for (T t8 : tArr) {
            arrayList.add(t8);
        }
        return arrayList;
    }

    @KeepForSdk
    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        int i9 = 0;
        if (collection == null || collection.isEmpty()) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            iArr[i9] = it.next().intValue();
            i9++;
        }
        return iArr;
    }

    @KeepForSdk
    public static Integer[] toWrapperArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i9 = 0; i9 < length; i9++) {
            numArr[i9] = Integer.valueOf(iArr[i9]);
        }
        return numArr;
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, double[] dArr) {
        int length = dArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(dArr[i9]));
        }
    }

    @KeepForSdk
    public static void writeStringArray(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i9]);
            sb.append("\"");
        }
    }

    @KeepForSdk
    public static <T> boolean contains(T[] tArr, T t8) {
        int length = tArr != null ? tArr.length : 0;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                break;
            }
            if (!Objects.equal(tArr[i9], t8)) {
                i9++;
            } else if (i9 >= 0) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, float[] fArr) {
        int length = fArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(fArr[i9]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, int[] iArr) {
        int length = iArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(iArr[i9]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, long[] jArr) {
        int length = jArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(jArr[i9]));
        }
    }

    @KeepForSdk
    public static <T> void writeArray(StringBuilder sb, T[] tArr) {
        int length = tArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(tArr[i9]);
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder sb, boolean[] zArr) {
        int length = zArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(zArr[i9]));
        }
    }
}
