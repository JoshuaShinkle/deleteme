package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.mutable.MutableInt;

/* loaded from: classes.dex */
public class ArrayUtils {
    public static final int INDEX_NOT_FOUND = -1;
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

    public static <T> T[] add(T[] tArr, T t8) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass();
        } else {
            if (t8 == null) {
                throw new IllegalArgumentException("Arguments cannot both be null");
            }
            cls = t8.getClass();
        }
        T[] tArr2 = (T[]) ((Object[]) copyArrayGrow1(tArr, cls));
        tArr2[tArr2.length - 1] = t8;
        return tArr2;
    }

    public static <T> T[] addAll(T[] tArr, T... tArr2) {
        if (tArr == null) {
            return (T[]) clone(tArr2);
        }
        if (tArr2 == null) {
            return (T[]) clone(tArr);
        }
        Class<?> componentType = tArr.getClass().getComponentType();
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(componentType, tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        try {
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        } catch (ArrayStoreException e9) {
            Class<?> componentType2 = tArr2.getClass().getComponentType();
            if (componentType.isAssignableFrom(componentType2)) {
                throw e9;
            }
            throw new IllegalArgumentException("Cannot store " + componentType2.getName() + " in an array of " + componentType.getName(), e9);
        }
    }

    public static <T> T[] clone(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Object[]) tArr.clone());
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) != -1;
    }

    private static Object copyArrayGrow1(Object obj, Class<?> cls) throws NegativeArraySizeException {
        if (obj == null) {
            return Array.newInstance(cls, 1);
        }
        int length = Array.getLength(obj);
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
        System.arraycopy(obj, 0, objNewInstance, 0, length);
        return objNewInstance;
    }

    public static int getLength(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static int hashCode(Object obj) {
        return new HashCodeBuilder().append(obj).toHashCode();
    }

    public static int indexOf(Object[] objArr, Object obj) {
        return indexOf(objArr, obj, 0);
    }

    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    @Deprecated
    public static boolean isEquals(Object obj, Object obj2) {
        return new EqualsBuilder().append(obj, obj2).isEquals();
    }

    public static <T> boolean isNotEmpty(T[] tArr) {
        return (tArr == null || tArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(Object[] objArr, Object[] objArr2) {
        if (objArr == null && objArr2 != null && objArr2.length > 0) {
            return false;
        }
        if (objArr2 != null || objArr == null || objArr.length <= 0) {
            return objArr == null || objArr2 == null || objArr.length == objArr2.length;
        }
        return false;
    }

    public static boolean isSameType(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        return obj.getClass().getName().equals(obj2.getClass().getName());
    }

    public static int lastIndexOf(Object[] objArr, Object obj) {
        return lastIndexOf(objArr, obj, Integer.MAX_VALUE);
    }

    public static Object[] nullToEmpty(Object[] objArr) {
        return (objArr == null || objArr.length == 0) ? EMPTY_OBJECT_ARRAY : objArr;
    }

    public static <T> T[] remove(T[] tArr, int i9) {
        return (T[]) ((Object[]) remove((Object) tArr, i9));
    }

    public static <T> T[] removeAll(T[] tArr, int... iArr) {
        return (T[]) ((Object[]) removeAll((Object) tArr, clone(iArr)));
    }

    public static <T> T[] removeElement(T[] tArr, Object obj) {
        int iIndexOf = indexOf(tArr, obj);
        return iIndexOf == -1 ? (T[]) clone(tArr) : (T[]) remove((Object[]) tArr, iIndexOf);
    }

    public static <T> T[] removeElements(T[] tArr, T... tArr2) {
        if (isEmpty(tArr) || isEmpty(tArr2)) {
            return (T[]) clone(tArr);
        }
        HashMap map = new HashMap(tArr2.length);
        for (T t8 : tArr2) {
            MutableInt mutableInt = (MutableInt) map.get(t8);
            if (mutableInt == null) {
                map.put(t8, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            int iIntValue = ((MutableInt) entry.getValue()).intValue();
            int i9 = 0;
            int i10 = 0;
            while (i9 < iIntValue) {
                int iIndexOf = indexOf(tArr, key, i10);
                if (iIndexOf < 0) {
                    break;
                }
                bitSet.set(iIndexOf);
                i9++;
                i10 = iIndexOf + 1;
            }
        }
        return (T[]) ((Object[]) removeAll(tArr, bitSet));
    }

    public static void reverse(Object[] objArr) {
        if (objArr == null) {
            return;
        }
        reverse(objArr, 0, objArr.length);
    }

    public static <T> T[] subarray(T[] tArr, int i9, int i10) {
        if (tArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > tArr.length) {
            i10 = tArr.length;
        }
        int i11 = i10 - i9;
        Class<?> componentType = tArr.getClass().getComponentType();
        if (i11 <= 0) {
            return (T[]) ((Object[]) Array.newInstance(componentType, 0));
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(componentType, i11));
        System.arraycopy(tArr, i9, tArr2, 0, i11);
        return tArr2;
    }

    public static <T> T[] toArray(T... tArr) {
        return tArr;
    }

    public static Map<Object, Object> toMap(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        HashMap map = new HashMap((int) (objArr.length * 1.5d));
        for (int i9 = 0; i9 < objArr.length; i9++) {
            Object obj = objArr[i9];
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                map.put(entry.getKey(), entry.getValue());
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalArgumentException("Array element " + i9 + ", '" + obj + "', is neither of type Map.Entry nor an Array");
                }
                Object[] objArr2 = (Object[]) obj;
                if (objArr2.length < 2) {
                    throw new IllegalArgumentException("Array element " + i9 + ", '" + obj + "', has a length less than 2");
                }
                map.put(objArr2[0], objArr2[1]);
            }
        }
        return map;
    }

    public static Character[] toObject(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        if (cArr.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        Character[] chArr = new Character[cArr.length];
        for (int i9 = 0; i9 < cArr.length; i9++) {
            chArr[i9] = Character.valueOf(cArr[i9]);
        }
        return chArr;
    }

    public static char[] toPrimitive(Character[] chArr) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int i9 = 0; i9 < chArr.length; i9++) {
            cArr[i9] = chArr[i9].charValue();
        }
        return cArr;
    }

    public static String toString(Object obj) {
        return toString(obj, "{}");
    }

    public static long[] clone(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) jArr.clone();
    }

    public static boolean contains(long[] jArr, long j9) {
        return indexOf(jArr, j9) != -1;
    }

    public static int indexOf(Object[] objArr, Object obj, int i9) {
        if (objArr == null) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (obj == null) {
            while (i9 < objArr.length) {
                if (objArr[i9] == null) {
                    return i9;
                }
                i9++;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i9 < objArr.length) {
                if (obj.equals(objArr[i9])) {
                    return i9;
                }
                i9++;
            }
        }
        return -1;
    }

    public static boolean isEmpty(long[] jArr) {
        return jArr == null || jArr.length == 0;
    }

    public static boolean isNotEmpty(long[] jArr) {
        return (jArr == null || jArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(long[] jArr, long[] jArr2) {
        if (jArr == null && jArr2 != null && jArr2.length > 0) {
            return false;
        }
        if (jArr2 != null || jArr == null || jArr.length <= 0) {
            return jArr == null || jArr2 == null || jArr.length == jArr2.length;
        }
        return false;
    }

    public static int lastIndexOf(Object[] objArr, Object obj, int i9) {
        if (objArr == null || i9 < 0) {
            return -1;
        }
        if (i9 >= objArr.length) {
            i9 = objArr.length - 1;
        }
        if (obj == null) {
            while (i9 >= 0) {
                if (objArr[i9] == null) {
                    return i9;
                }
                i9--;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i9 >= 0) {
                if (obj.equals(objArr[i9])) {
                    return i9;
                }
                i9--;
            }
        }
        return -1;
    }

    public static boolean[] remove(boolean[] zArr, int i9) {
        return (boolean[]) remove((Object) zArr, i9);
    }

    public static byte[] removeAll(byte[] bArr, int... iArr) {
        return (byte[]) removeAll((Object) bArr, clone(iArr));
    }

    public static void reverse(long[] jArr) {
        if (jArr == null) {
            return;
        }
        reverse(jArr, 0, jArr.length);
    }

    public static String toString(Object obj, String str) {
        return obj == null ? str : new ToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE).append(obj).toString();
    }

    public static int[] clone(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public static boolean contains(int[] iArr, int i9) {
        return indexOf(iArr, i9) != -1;
    }

    public static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    public static boolean isNotEmpty(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(int[] iArr, int[] iArr2) {
        if (iArr == null && iArr2 != null && iArr2.length > 0) {
            return false;
        }
        if (iArr2 != null || iArr == null || iArr.length <= 0) {
            return iArr == null || iArr2 == null || iArr.length == iArr2.length;
        }
        return false;
    }

    public static Class<?>[] nullToEmpty(Class<?>[] clsArr) {
        return (clsArr == null || clsArr.length == 0) ? EMPTY_CLASS_ARRAY : clsArr;
    }

    public static byte[] remove(byte[] bArr, int i9) {
        return (byte[]) remove((Object) bArr, i9);
    }

    public static short[] removeAll(short[] sArr, int... iArr) {
        return (short[]) removeAll((Object) sArr, clone(iArr));
    }

    public static void reverse(int[] iArr) {
        if (iArr == null) {
            return;
        }
        reverse(iArr, 0, iArr.length);
    }

    public static short[] clone(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) sArr.clone();
    }

    public static boolean contains(short[] sArr, short s8) {
        return indexOf(sArr, s8) != -1;
    }

    public static boolean isEmpty(short[] sArr) {
        return sArr == null || sArr.length == 0;
    }

    public static boolean isNotEmpty(short[] sArr) {
        return (sArr == null || sArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(short[] sArr, short[] sArr2) {
        if (sArr == null && sArr2 != null && sArr2.length > 0) {
            return false;
        }
        if (sArr2 != null || sArr == null || sArr.length <= 0) {
            return sArr == null || sArr2 == null || sArr.length == sArr2.length;
        }
        return false;
    }

    public static char[] remove(char[] cArr, int i9) {
        return (char[]) remove((Object) cArr, i9);
    }

    public static int[] removeAll(int[] iArr, int... iArr2) {
        return (int[]) removeAll((Object) iArr, clone(iArr2));
    }

    public static boolean[] removeElement(boolean[] zArr, boolean z8) {
        int iIndexOf = indexOf(zArr, z8);
        if (iIndexOf == -1) {
            return clone(zArr);
        }
        return remove(zArr, iIndexOf);
    }

    public static void reverse(short[] sArr) {
        if (sArr == null) {
            return;
        }
        reverse(sArr, 0, sArr.length);
    }

    public static char[] clone(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public static boolean contains(char[] cArr, char c9) {
        return indexOf(cArr, c9) != -1;
    }

    public static boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    public static boolean isNotEmpty(char[] cArr) {
        return (cArr == null || cArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(char[] cArr, char[] cArr2) {
        if (cArr == null && cArr2 != null && cArr2.length > 0) {
            return false;
        }
        if (cArr2 != null || cArr == null || cArr.length <= 0) {
            return cArr == null || cArr2 == null || cArr.length == cArr2.length;
        }
        return false;
    }

    public static String[] nullToEmpty(String[] strArr) {
        return (strArr == null || strArr.length == 0) ? EMPTY_STRING_ARRAY : strArr;
    }

    public static double[] remove(double[] dArr, int i9) {
        return (double[]) remove((Object) dArr, i9);
    }

    public static char[] removeAll(char[] cArr, int... iArr) {
        return (char[]) removeAll((Object) cArr, clone(iArr));
    }

    public static void reverse(char[] cArr) {
        if (cArr == null) {
            return;
        }
        reverse(cArr, 0, cArr.length);
    }

    public static boolean[] add(boolean[] zArr, boolean z8) {
        boolean[] zArr2 = (boolean[]) copyArrayGrow1(zArr, Boolean.TYPE);
        zArr2[zArr2.length - 1] = z8;
        return zArr2;
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static boolean contains(byte[] bArr, byte b9) {
        return indexOf(bArr, b9) != -1;
    }

    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean isNotEmpty(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 != null && bArr2.length > 0) {
            return false;
        }
        if (bArr2 != null || bArr == null || bArr.length <= 0) {
            return bArr == null || bArr2 == null || bArr.length == bArr2.length;
        }
        return false;
    }

    public static float[] remove(float[] fArr, int i9) {
        return (float[]) remove((Object) fArr, i9);
    }

    public static long[] removeAll(long[] jArr, int... iArr) {
        return (long[]) removeAll((Object) jArr, clone(iArr));
    }

    public static void reverse(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        reverse(bArr, 0, bArr.length);
    }

    public static Long[] toObject(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        if (jArr.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        Long[] lArr = new Long[jArr.length];
        for (int i9 = 0; i9 < jArr.length; i9++) {
            lArr[i9] = Long.valueOf(jArr[i9]);
        }
        return lArr;
    }

    public static char[] toPrimitive(Character[] chArr, char c9) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int i9 = 0; i9 < chArr.length; i9++) {
            Character ch = chArr[i9];
            cArr[i9] = ch == null ? c9 : ch.charValue();
        }
        return cArr;
    }

    public static double[] clone(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public static boolean contains(double[] dArr, double d9) {
        return indexOf(dArr, d9) != -1;
    }

    public static int indexOf(long[] jArr, long j9) {
        return indexOf(jArr, j9, 0);
    }

    public static boolean isEmpty(double[] dArr) {
        return dArr == null || dArr.length == 0;
    }

    public static boolean isNotEmpty(double[] dArr) {
        return (dArr == null || dArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(double[] dArr, double[] dArr2) {
        if (dArr == null && dArr2 != null && dArr2.length > 0) {
            return false;
        }
        if (dArr2 != null || dArr == null || dArr.length <= 0) {
            return dArr == null || dArr2 == null || dArr.length == dArr2.length;
        }
        return false;
    }

    public static int lastIndexOf(long[] jArr, long j9) {
        return lastIndexOf(jArr, j9, Integer.MAX_VALUE);
    }

    public static long[] nullToEmpty(long[] jArr) {
        return (jArr == null || jArr.length == 0) ? EMPTY_LONG_ARRAY : jArr;
    }

    public static int[] remove(int[] iArr, int i9) {
        return (int[]) remove((Object) iArr, i9);
    }

    public static float[] removeAll(float[] fArr, int... iArr) {
        return (float[]) removeAll((Object) fArr, clone(iArr));
    }

    public static byte[] removeElement(byte[] bArr, byte b9) {
        int iIndexOf = indexOf(bArr, b9);
        if (iIndexOf == -1) {
            return clone(bArr);
        }
        return remove(bArr, iIndexOf);
    }

    public static void reverse(double[] dArr) {
        if (dArr == null) {
            return;
        }
        reverse(dArr, 0, dArr.length);
    }

    public static long[] subarray(long[] jArr, int i9, int i10) {
        if (jArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > jArr.length) {
            i10 = jArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr2 = new long[i11];
        System.arraycopy(jArr, i9, jArr2, 0, i11);
        return jArr2;
    }

    public static byte[] add(byte[] bArr, byte b9) {
        byte[] bArr2 = (byte[]) copyArrayGrow1(bArr, Byte.TYPE);
        bArr2[bArr2.length - 1] = b9;
        return bArr2;
    }

    public static float[] clone(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return (float[]) fArr.clone();
    }

    public static boolean contains(double[] dArr, double d9, double d10) {
        return indexOf(dArr, d9, 0, d10) != -1;
    }

    public static int indexOf(long[] jArr, long j9, int i9) {
        if (jArr == null) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < jArr.length) {
            if (j9 == jArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static boolean isEmpty(float[] fArr) {
        return fArr == null || fArr.length == 0;
    }

    public static boolean isNotEmpty(float[] fArr) {
        return (fArr == null || fArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(float[] fArr, float[] fArr2) {
        if (fArr == null && fArr2 != null && fArr2.length > 0) {
            return false;
        }
        if (fArr2 != null || fArr == null || fArr.length <= 0) {
            return fArr == null || fArr2 == null || fArr.length == fArr2.length;
        }
        return false;
    }

    public static int lastIndexOf(long[] jArr, long j9, int i9) {
        if (jArr == null || i9 < 0) {
            return -1;
        }
        if (i9 >= jArr.length) {
            i9 = jArr.length - 1;
        }
        while (i9 >= 0) {
            if (j9 == jArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static long[] remove(long[] jArr, int i9) {
        return (long[]) remove((Object) jArr, i9);
    }

    public static double[] removeAll(double[] dArr, int... iArr) {
        return (double[]) removeAll((Object) dArr, clone(iArr));
    }

    public static void reverse(float[] fArr) {
        if (fArr == null) {
            return;
        }
        reverse(fArr, 0, fArr.length);
    }

    public static boolean[] clone(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public static boolean contains(float[] fArr, float f9) {
        return indexOf(fArr, f9) != -1;
    }

    public static boolean isEmpty(boolean[] zArr) {
        return zArr == null || zArr.length == 0;
    }

    public static boolean isNotEmpty(boolean[] zArr) {
        return (zArr == null || zArr.length == 0) ? false : true;
    }

    public static boolean isSameLength(boolean[] zArr, boolean[] zArr2) {
        if (zArr == null && zArr2 != null && zArr2.length > 0) {
            return false;
        }
        if (zArr2 != null || zArr == null || zArr.length <= 0) {
            return zArr == null || zArr2 == null || zArr.length == zArr2.length;
        }
        return false;
    }

    public static int[] nullToEmpty(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? EMPTY_INT_ARRAY : iArr;
    }

    public static short[] remove(short[] sArr, int i9) {
        return (short[]) remove((Object) sArr, i9);
    }

    public static boolean[] removeAll(boolean[] zArr, int... iArr) {
        return (boolean[]) removeAll((Object) zArr, clone(iArr));
    }

    public static void reverse(boolean[] zArr) {
        if (zArr == null) {
            return;
        }
        reverse(zArr, 0, zArr.length);
    }

    public static char[] add(char[] cArr, char c9) {
        char[] cArr2 = (char[]) copyArrayGrow1(cArr, Character.TYPE);
        cArr2[cArr2.length - 1] = c9;
        return cArr2;
    }

    public static boolean contains(boolean[] zArr, boolean z8) {
        return indexOf(zArr, z8) != -1;
    }

    public static int indexOf(int[] iArr, int i9) {
        return indexOf(iArr, i9, 0);
    }

    private static Object remove(Object obj, int i9) throws NegativeArraySizeException {
        int length = getLength(obj);
        if (i9 >= 0 && i9 < length) {
            int i10 = length - 1;
            Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), i10);
            System.arraycopy(obj, 0, objNewInstance, 0, i9);
            if (i9 < i10) {
                System.arraycopy(obj, i9 + 1, objNewInstance, i9, (length - i9) - 1);
            }
            return objNewInstance;
        }
        throw new IndexOutOfBoundsException("Index: " + i9 + ", Length: " + length);
    }

    public static Object removeAll(Object obj, int... iArr) throws NegativeArraySizeException {
        int i9;
        int i10;
        int length = getLength(obj);
        if (isNotEmpty(iArr)) {
            Arrays.sort(iArr);
            int length2 = iArr.length;
            int i11 = length;
            i9 = 0;
            while (true) {
                length2--;
                if (length2 < 0) {
                    break;
                }
                i10 = iArr[length2];
                if (i10 < 0 || i10 >= length) {
                    break;
                }
                if (i10 < i11) {
                    i9++;
                    i11 = i10;
                }
            }
            throw new IndexOutOfBoundsException("Index: " + i10 + ", Length: " + length);
        }
        i9 = 0;
        int i12 = length - i9;
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), i12);
        if (i9 < length) {
            int length3 = iArr.length - 1;
            while (length3 >= 0) {
                int i13 = iArr[length3];
                int i14 = length - i13;
                if (i14 > 1) {
                    int i15 = i14 - 1;
                    i12 -= i15;
                    System.arraycopy(obj, i13 + 1, objNewInstance, i12, i15);
                }
                length3--;
                length = i13;
            }
            if (length > 0) {
                System.arraycopy(obj, 0, objNewInstance, 0, length);
            }
        }
        return objNewInstance;
    }

    public static char[] removeElement(char[] cArr, char c9) {
        int iIndexOf = indexOf(cArr, c9);
        if (iIndexOf == -1) {
            return clone(cArr);
        }
        return remove(cArr, iIndexOf);
    }

    public static void reverse(boolean[] zArr, int i9, int i10) {
        if (zArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(zArr.length, i10) - 1;
        while (iMin > i9) {
            boolean z8 = zArr[iMin];
            zArr[iMin] = zArr[i9];
            zArr[i9] = z8;
            iMin--;
            i9++;
        }
    }

    public static boolean[] addAll(boolean[] zArr, boolean... zArr2) {
        if (zArr == null) {
            return clone(zArr2);
        }
        if (zArr2 == null) {
            return clone(zArr);
        }
        boolean[] zArr3 = new boolean[zArr.length + zArr2.length];
        System.arraycopy(zArr, 0, zArr3, 0, zArr.length);
        System.arraycopy(zArr2, 0, zArr3, zArr.length, zArr2.length);
        return zArr3;
    }

    public static int indexOf(int[] iArr, int i9, int i10) {
        if (iArr == null) {
            return -1;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        while (i10 < iArr.length) {
            if (i9 == iArr[i10]) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    public static int lastIndexOf(int[] iArr, int i9) {
        return lastIndexOf(iArr, i9, Integer.MAX_VALUE);
    }

    public static short[] nullToEmpty(short[] sArr) {
        return (sArr == null || sArr.length == 0) ? EMPTY_SHORT_ARRAY : sArr;
    }

    public static Integer[] toObject(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        Integer[] numArr = new Integer[iArr.length];
        for (int i9 = 0; i9 < iArr.length; i9++) {
            numArr[i9] = Integer.valueOf(iArr[i9]);
        }
        return numArr;
    }

    public static double[] add(double[] dArr, double d9) {
        double[] dArr2 = (double[]) copyArrayGrow1(dArr, Double.TYPE);
        dArr2[dArr2.length - 1] = d9;
        return dArr2;
    }

    public static int lastIndexOf(int[] iArr, int i9, int i10) {
        if (iArr == null || i10 < 0) {
            return -1;
        }
        if (i10 >= iArr.length) {
            i10 = iArr.length - 1;
        }
        while (i10 >= 0) {
            if (i9 == iArr[i10]) {
                return i10;
            }
            i10--;
        }
        return -1;
    }

    public static int[] subarray(int[] iArr, int i9, int i10) {
        if (iArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > iArr.length) {
            i10 = iArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr2 = new int[i11];
        System.arraycopy(iArr, i9, iArr2, 0, i11);
        return iArr2;
    }

    public static long[] toPrimitive(Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int i9 = 0; i9 < lArr.length; i9++) {
            jArr[i9] = lArr[i9].longValue();
        }
        return jArr;
    }

    public static int indexOf(short[] sArr, short s8) {
        return indexOf(sArr, s8, 0);
    }

    public static char[] nullToEmpty(char[] cArr) {
        return (cArr == null || cArr.length == 0) ? EMPTY_CHAR_ARRAY : cArr;
    }

    public static double[] removeElement(double[] dArr, double d9) {
        int iIndexOf = indexOf(dArr, d9);
        if (iIndexOf == -1) {
            return clone(dArr);
        }
        return remove(dArr, iIndexOf);
    }

    public static float[] add(float[] fArr, float f9) {
        float[] fArr2 = (float[]) copyArrayGrow1(fArr, Float.TYPE);
        fArr2[fArr2.length - 1] = f9;
        return fArr2;
    }

    public static int indexOf(short[] sArr, short s8, int i9) {
        if (sArr == null) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < sArr.length) {
            if (s8 == sArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static void reverse(byte[] bArr, int i9, int i10) {
        if (bArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(bArr.length, i10) - 1;
        while (iMin > i9) {
            byte b9 = bArr[iMin];
            bArr[iMin] = bArr[i9];
            bArr[i9] = b9;
            iMin--;
            i9++;
        }
    }

    public static int lastIndexOf(short[] sArr, short s8) {
        return lastIndexOf(sArr, s8, Integer.MAX_VALUE);
    }

    public static byte[] nullToEmpty(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? EMPTY_BYTE_ARRAY : bArr;
    }

    public static byte[] removeElements(byte[] bArr, byte... bArr2) {
        if (!isEmpty(bArr) && !isEmpty(bArr2)) {
            HashMap map = new HashMap(bArr2.length);
            for (byte b9 : bArr2) {
                Byte bValueOf = Byte.valueOf(b9);
                MutableInt mutableInt = (MutableInt) map.get(bValueOf);
                if (mutableInt == null) {
                    map.put(bValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Byte b10 = (Byte) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(bArr, b10.byteValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (byte[]) removeAll(bArr, bitSet);
        }
        return clone(bArr);
    }

    public static int[] add(int[] iArr, int i9) {
        int[] iArr2 = (int[]) copyArrayGrow1(iArr, Integer.TYPE);
        iArr2[iArr2.length - 1] = i9;
        return iArr2;
    }

    public static char[] addAll(char[] cArr, char... cArr2) {
        if (cArr == null) {
            return clone(cArr2);
        }
        if (cArr2 == null) {
            return clone(cArr);
        }
        char[] cArr3 = new char[cArr.length + cArr2.length];
        System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
        System.arraycopy(cArr2, 0, cArr3, cArr.length, cArr2.length);
        return cArr3;
    }

    public static int indexOf(char[] cArr, char c9) {
        return indexOf(cArr, c9, 0);
    }

    public static int lastIndexOf(short[] sArr, short s8, int i9) {
        if (sArr == null || i9 < 0) {
            return -1;
        }
        if (i9 >= sArr.length) {
            i9 = sArr.length - 1;
        }
        while (i9 >= 0) {
            if (s8 == sArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static float[] removeElement(float[] fArr, float f9) {
        int iIndexOf = indexOf(fArr, f9);
        if (iIndexOf == -1) {
            return clone(fArr);
        }
        return remove(fArr, iIndexOf);
    }

    public static Short[] toObject(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        if (sArr.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        Short[] shArr = new Short[sArr.length];
        for (int i9 = 0; i9 < sArr.length; i9++) {
            shArr[i9] = Short.valueOf(sArr[i9]);
        }
        return shArr;
    }

    public static int indexOf(char[] cArr, char c9, int i9) {
        if (cArr == null) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < cArr.length) {
            if (c9 == cArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static double[] nullToEmpty(double[] dArr) {
        return (dArr == null || dArr.length == 0) ? EMPTY_DOUBLE_ARRAY : dArr;
    }

    public static short[] subarray(short[] sArr, int i9, int i10) {
        if (sArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > sArr.length) {
            i10 = sArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr2 = new short[i11];
        System.arraycopy(sArr, i9, sArr2, 0, i11);
        return sArr2;
    }

    public static long[] toPrimitive(Long[] lArr, long j9) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int i9 = 0; i9 < lArr.length; i9++) {
            Long l9 = lArr[i9];
            jArr[i9] = l9 == null ? j9 : l9.longValue();
        }
        return jArr;
    }

    public static long[] add(long[] jArr, long j9) {
        long[] jArr2 = (long[]) copyArrayGrow1(jArr, Long.TYPE);
        jArr2[jArr2.length - 1] = j9;
        return jArr2;
    }

    public static void reverse(char[] cArr, int i9, int i10) {
        if (cArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(cArr.length, i10) - 1;
        while (iMin > i9) {
            char c9 = cArr[iMin];
            cArr[iMin] = cArr[i9];
            cArr[i9] = c9;
            iMin--;
            i9++;
        }
    }

    public static int indexOf(byte[] bArr, byte b9) {
        return indexOf(bArr, b9, 0);
    }

    public static int lastIndexOf(char[] cArr, char c9) {
        return lastIndexOf(cArr, c9, Integer.MAX_VALUE);
    }

    public static float[] nullToEmpty(float[] fArr) {
        return (fArr == null || fArr.length == 0) ? EMPTY_FLOAT_ARRAY : fArr;
    }

    public static int[] removeElement(int[] iArr, int i9) {
        int iIndexOf = indexOf(iArr, i9);
        if (iIndexOf == -1) {
            return clone(iArr);
        }
        return remove(iArr, iIndexOf);
    }

    public static short[] add(short[] sArr, short s8) {
        short[] sArr2 = (short[]) copyArrayGrow1(sArr, Short.TYPE);
        sArr2[sArr2.length - 1] = s8;
        return sArr2;
    }

    public static int indexOf(byte[] bArr, byte b9, int i9) {
        if (bArr == null) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < bArr.length) {
            if (b9 == bArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static int lastIndexOf(char[] cArr, char c9, int i9) {
        if (cArr == null || i9 < 0) {
            return -1;
        }
        if (i9 >= cArr.length) {
            i9 = cArr.length - 1;
        }
        while (i9 >= 0) {
            if (c9 == cArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static byte[] addAll(byte[] bArr, byte... bArr2) {
        if (bArr == null) {
            return clone(bArr2);
        }
        if (bArr2 == null) {
            return clone(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static boolean[] nullToEmpty(boolean[] zArr) {
        return (zArr == null || zArr.length == 0) ? EMPTY_BOOLEAN_ARRAY : zArr;
    }

    public static Object removeAll(Object obj, BitSet bitSet) throws NegativeArraySizeException {
        int length = getLength(obj);
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length - bitSet.cardinality());
        int iNextClearBit = 0;
        int i9 = 0;
        while (true) {
            int iNextSetBit = bitSet.nextSetBit(iNextClearBit);
            if (iNextSetBit == -1) {
                break;
            }
            int i10 = iNextSetBit - iNextClearBit;
            if (i10 > 0) {
                System.arraycopy(obj, iNextClearBit, objNewInstance, i9, i10);
                i9 += i10;
            }
            iNextClearBit = bitSet.nextClearBit(iNextSetBit);
        }
        int i11 = length - iNextClearBit;
        if (i11 > 0) {
            System.arraycopy(obj, iNextClearBit, objNewInstance, i9, i11);
        }
        return objNewInstance;
    }

    public static Byte[] toObject(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i9 = 0; i9 < bArr.length; i9++) {
            bArr2[i9] = Byte.valueOf(bArr[i9]);
        }
        return bArr2;
    }

    public static <T> T[] add(T[] tArr, int i9, T t8) {
        Class<?> componentType;
        if (tArr != null) {
            componentType = tArr.getClass().getComponentType();
        } else if (t8 != null) {
            componentType = t8.getClass();
        } else {
            throw new IllegalArgumentException("Array and element cannot both be null");
        }
        return (T[]) ((Object[]) add(tArr, i9, t8, componentType));
    }

    public static int indexOf(double[] dArr, double d9) {
        return indexOf(dArr, d9, 0);
    }

    public static long[] removeElement(long[] jArr, long j9) {
        int iIndexOf = indexOf(jArr, j9);
        if (iIndexOf == -1) {
            return clone(jArr);
        }
        return remove(jArr, iIndexOf);
    }

    public static void reverse(double[] dArr, int i9, int i10) {
        if (dArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(dArr.length, i10) - 1;
        while (iMin > i9) {
            double d9 = dArr[iMin];
            dArr[iMin] = dArr[i9];
            dArr[i9] = d9;
            iMin--;
            i9++;
        }
    }

    public static char[] subarray(char[] cArr, int i9, int i10) {
        if (cArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > cArr.length) {
            i10 = cArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr2 = new char[i11];
        System.arraycopy(cArr, i9, cArr2, 0, i11);
        return cArr2;
    }

    public static int indexOf(double[] dArr, double d9, double d10) {
        return indexOf(dArr, d9, 0, d10);
    }

    public static int lastIndexOf(byte[] bArr, byte b9) {
        return lastIndexOf(bArr, b9, Integer.MAX_VALUE);
    }

    public static Long[] nullToEmpty(Long[] lArr) {
        return (lArr == null || lArr.length == 0) ? EMPTY_LONG_OBJECT_ARRAY : lArr;
    }

    public static int[] toPrimitive(Integer[] numArr) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[numArr.length];
        for (int i9 = 0; i9 < numArr.length; i9++) {
            iArr[i9] = numArr[i9].intValue();
        }
        return iArr;
    }

    public static int indexOf(double[] dArr, double d9, int i9) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < dArr.length) {
            if (d9 == dArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] bArr, byte b9, int i9) {
        if (bArr == null || i9 < 0) {
            return -1;
        }
        if (i9 >= bArr.length) {
            i9 = bArr.length - 1;
        }
        while (i9 >= 0) {
            if (b9 == bArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static Integer[] nullToEmpty(Integer[] numArr) {
        return (numArr == null || numArr.length == 0) ? EMPTY_INTEGER_OBJECT_ARRAY : numArr;
    }

    public static short[] removeElement(short[] sArr, short s8) {
        int iIndexOf = indexOf(sArr, s8);
        if (iIndexOf == -1) {
            return clone(sArr);
        }
        return remove(sArr, iIndexOf);
    }

    public static boolean[] add(boolean[] zArr, int i9, boolean z8) {
        return (boolean[]) add(zArr, i9, Boolean.valueOf(z8), Boolean.TYPE);
    }

    public static short[] addAll(short[] sArr, short... sArr2) {
        if (sArr == null) {
            return clone(sArr2);
        }
        if (sArr2 == null) {
            return clone(sArr);
        }
        short[] sArr3 = new short[sArr.length + sArr2.length];
        System.arraycopy(sArr, 0, sArr3, 0, sArr.length);
        System.arraycopy(sArr2, 0, sArr3, sArr.length, sArr2.length);
        return sArr3;
    }

    public static void reverse(float[] fArr, int i9, int i10) {
        if (fArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(fArr.length, i10) - 1;
        while (iMin > i9) {
            float f9 = fArr[iMin];
            fArr[iMin] = fArr[i9];
            fArr[i9] = f9;
            iMin--;
            i9++;
        }
    }

    public static Double[] toObject(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        Double[] dArr2 = new Double[dArr.length];
        for (int i9 = 0; i9 < dArr.length; i9++) {
            dArr2[i9] = Double.valueOf(dArr[i9]);
        }
        return dArr2;
    }

    public static char[] add(char[] cArr, int i9, char c9) {
        return (char[]) add(cArr, i9, Character.valueOf(c9), Character.TYPE);
    }

    public static int indexOf(double[] dArr, double d9, int i9, double d10) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        double d11 = d9 - d10;
        double d12 = d9 + d10;
        while (i9 < dArr.length) {
            double d13 = dArr[i9];
            if (d13 >= d11 && d13 <= d12) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d9) {
        return lastIndexOf(dArr, d9, Integer.MAX_VALUE);
    }

    public static Short[] nullToEmpty(Short[] shArr) {
        return (shArr == null || shArr.length == 0) ? EMPTY_SHORT_OBJECT_ARRAY : shArr;
    }

    public static byte[] subarray(byte[] bArr, int i9, int i10) {
        if (bArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > bArr.length) {
            i10 = bArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[i11];
        System.arraycopy(bArr, i9, bArr2, 0, i11);
        return bArr2;
    }

    public static byte[] add(byte[] bArr, int i9, byte b9) {
        return (byte[]) add(bArr, i9, Byte.valueOf(b9), Byte.TYPE);
    }

    public static int lastIndexOf(double[] dArr, double d9, double d10) {
        return lastIndexOf(dArr, d9, Integer.MAX_VALUE, d10);
    }

    public static int[] toPrimitive(Integer[] numArr, int i9) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[numArr.length];
        for (int i10 = 0; i10 < numArr.length; i10++) {
            Integer num = numArr[i10];
            iArr[i10] = num == null ? i9 : num.intValue();
        }
        return iArr;
    }

    public static short[] add(short[] sArr, int i9, short s8) {
        return (short[]) add(sArr, i9, Short.valueOf(s8), Short.TYPE);
    }

    public static int lastIndexOf(double[] dArr, double d9, int i9) {
        if (isEmpty(dArr) || i9 < 0) {
            return -1;
        }
        if (i9 >= dArr.length) {
            i9 = dArr.length - 1;
        }
        while (i9 >= 0) {
            if (d9 == dArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static Character[] nullToEmpty(Character[] chArr) {
        return (chArr == null || chArr.length == 0) ? EMPTY_CHARACTER_OBJECT_ARRAY : chArr;
    }

    public static int[] add(int[] iArr, int i9, int i10) {
        return (int[]) add(iArr, i9, Integer.valueOf(i10), Integer.TYPE);
    }

    public static int indexOf(float[] fArr, float f9) {
        return indexOf(fArr, f9, 0);
    }

    public static short[] removeElements(short[] sArr, short... sArr2) {
        if (!isEmpty(sArr) && !isEmpty(sArr2)) {
            HashMap map = new HashMap(sArr2.length);
            for (short s8 : sArr2) {
                Short shValueOf = Short.valueOf(s8);
                MutableInt mutableInt = (MutableInt) map.get(shValueOf);
                if (mutableInt == null) {
                    map.put(shValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Short sh = (Short) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(sArr, sh.shortValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (short[]) removeAll(sArr, bitSet);
        }
        return clone(sArr);
    }

    public static void reverse(int[] iArr, int i9, int i10) {
        if (iArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(iArr.length, i10) - 1;
        while (iMin > i9) {
            int i11 = iArr[iMin];
            iArr[iMin] = iArr[i9];
            iArr[i9] = i11;
            iMin--;
            i9++;
        }
    }

    public static long[] add(long[] jArr, int i9, long j9) {
        return (long[]) add(jArr, i9, Long.valueOf(j9), Long.TYPE);
    }

    public static int[] addAll(int[] iArr, int... iArr2) {
        if (iArr == null) {
            return clone(iArr2);
        }
        if (iArr2 == null) {
            return clone(iArr);
        }
        int[] iArr3 = new int[iArr.length + iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    public static int indexOf(float[] fArr, float f9, int i9) {
        if (isEmpty(fArr)) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < fArr.length) {
            if (f9 == fArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static Byte[] nullToEmpty(Byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? EMPTY_BYTE_OBJECT_ARRAY : bArr;
    }

    public static Float[] toObject(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        Float[] fArr2 = new Float[fArr.length];
        for (int i9 = 0; i9 < fArr.length; i9++) {
            fArr2[i9] = Float.valueOf(fArr[i9]);
        }
        return fArr2;
    }

    public static float[] add(float[] fArr, int i9, float f9) {
        return (float[]) add(fArr, i9, Float.valueOf(f9), Float.TYPE);
    }

    public static double[] subarray(double[] dArr, int i9, int i10) {
        if (dArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > dArr.length) {
            i10 = dArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[i11];
        System.arraycopy(dArr, i9, dArr2, 0, i11);
        return dArr2;
    }

    public static double[] add(double[] dArr, int i9, double d9) {
        return (double[]) add(dArr, i9, Double.valueOf(d9), Double.TYPE);
    }

    public static int lastIndexOf(double[] dArr, double d9, int i9, double d10) {
        if (isEmpty(dArr) || i9 < 0) {
            return -1;
        }
        if (i9 >= dArr.length) {
            i9 = dArr.length - 1;
        }
        double d11 = d9 - d10;
        double d12 = d9 + d10;
        while (i9 >= 0) {
            double d13 = dArr[i9];
            if (d13 >= d11 && d13 <= d12) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static Double[] nullToEmpty(Double[] dArr) {
        return (dArr == null || dArr.length == 0) ? EMPTY_DOUBLE_OBJECT_ARRAY : dArr;
    }

    private static Object add(Object obj, int i9, Object obj2, Class<?> cls) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        if (obj == null) {
            if (i9 == 0) {
                Object objNewInstance = Array.newInstance(cls, 1);
                Array.set(objNewInstance, 0, obj2);
                return objNewInstance;
            }
            throw new IndexOutOfBoundsException("Index: " + i9 + ", Length: 0");
        }
        int length = Array.getLength(obj);
        if (i9 <= length && i9 >= 0) {
            Object objNewInstance2 = Array.newInstance(cls, length + 1);
            System.arraycopy(obj, 0, objNewInstance2, 0, i9);
            Array.set(objNewInstance2, i9, obj2);
            if (i9 < length) {
                System.arraycopy(obj, i9, objNewInstance2, i9 + 1, length - i9);
            }
            return objNewInstance2;
        }
        throw new IndexOutOfBoundsException("Index: " + i9 + ", Length: " + length);
    }

    public static int indexOf(boolean[] zArr, boolean z8) {
        return indexOf(zArr, z8, 0);
    }

    public static void reverse(long[] jArr, int i9, int i10) {
        if (jArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(jArr.length, i10) - 1;
        while (iMin > i9) {
            long j9 = jArr[iMin];
            jArr[iMin] = jArr[i9];
            jArr[i9] = j9;
            iMin--;
            i9++;
        }
    }

    public static short[] toPrimitive(Short[] shArr) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int i9 = 0; i9 < shArr.length; i9++) {
            sArr[i9] = shArr[i9].shortValue();
        }
        return sArr;
    }

    public static int indexOf(boolean[] zArr, boolean z8, int i9) {
        if (isEmpty(zArr)) {
            return -1;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        while (i9 < zArr.length) {
            if (z8 == zArr[i9]) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public static Float[] nullToEmpty(Float[] fArr) {
        return (fArr == null || fArr.length == 0) ? EMPTY_FLOAT_OBJECT_ARRAY : fArr;
    }

    public static long[] addAll(long[] jArr, long... jArr2) {
        if (jArr == null) {
            return clone(jArr2);
        }
        if (jArr2 == null) {
            return clone(jArr);
        }
        long[] jArr3 = new long[jArr.length + jArr2.length];
        System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
        System.arraycopy(jArr2, 0, jArr3, jArr.length, jArr2.length);
        return jArr3;
    }

    public static Boolean[] toObject(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        if (zArr.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i9 = 0; i9 < zArr.length; i9++) {
            boolArr[i9] = zArr[i9] ? Boolean.TRUE : Boolean.FALSE;
        }
        return boolArr;
    }

    public static int lastIndexOf(float[] fArr, float f9) {
        return lastIndexOf(fArr, f9, Integer.MAX_VALUE);
    }

    public static Boolean[] nullToEmpty(Boolean[] boolArr) {
        return (boolArr == null || boolArr.length == 0) ? EMPTY_BOOLEAN_OBJECT_ARRAY : boolArr;
    }

    public static float[] subarray(float[] fArr, int i9, int i10) {
        if (fArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > fArr.length) {
            i10 = fArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[i11];
        System.arraycopy(fArr, i9, fArr2, 0, i11);
        return fArr2;
    }

    public static int lastIndexOf(float[] fArr, float f9, int i9) {
        if (isEmpty(fArr) || i9 < 0) {
            return -1;
        }
        if (i9 >= fArr.length) {
            i9 = fArr.length - 1;
        }
        while (i9 >= 0) {
            if (f9 == fArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static void reverse(Object[] objArr, int i9, int i10) {
        if (objArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(objArr.length, i10) - 1;
        while (iMin > i9) {
            Object obj = objArr[iMin];
            objArr[iMin] = objArr[i9];
            objArr[i9] = obj;
            iMin--;
            i9++;
        }
    }

    public static short[] toPrimitive(Short[] shArr, short s8) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int i9 = 0; i9 < shArr.length; i9++) {
            Short sh = shArr[i9];
            sArr[i9] = sh == null ? s8 : sh.shortValue();
        }
        return sArr;
    }

    public static float[] addAll(float[] fArr, float... fArr2) {
        if (fArr == null) {
            return clone(fArr2);
        }
        if (fArr2 == null) {
            return clone(fArr);
        }
        float[] fArr3 = new float[fArr.length + fArr2.length];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        return fArr3;
    }

    public static int lastIndexOf(boolean[] zArr, boolean z8) {
        return lastIndexOf(zArr, z8, Integer.MAX_VALUE);
    }

    public static void reverse(short[] sArr, int i9, int i10) {
        if (sArr == null) {
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        int iMin = Math.min(sArr.length, i10) - 1;
        while (iMin > i9) {
            short s8 = sArr[iMin];
            sArr[iMin] = sArr[i9];
            sArr[i9] = s8;
            iMin--;
            i9++;
        }
    }

    public static boolean[] subarray(boolean[] zArr, int i9, int i10) {
        if (zArr == null) {
            return null;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 > zArr.length) {
            i10 = zArr.length;
        }
        int i11 = i10 - i9;
        if (i11 <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr2 = new boolean[i11];
        System.arraycopy(zArr, i9, zArr2, 0, i11);
        return zArr2;
    }

    public static int lastIndexOf(boolean[] zArr, boolean z8, int i9) {
        if (isEmpty(zArr) || i9 < 0) {
            return -1;
        }
        if (i9 >= zArr.length) {
            i9 = zArr.length - 1;
        }
        while (i9 >= 0) {
            if (z8 == zArr[i9]) {
                return i9;
            }
            i9--;
        }
        return -1;
    }

    public static int[] removeElements(int[] iArr, int... iArr2) {
        if (!isEmpty(iArr) && !isEmpty(iArr2)) {
            HashMap map = new HashMap(iArr2.length);
            for (int i9 : iArr2) {
                Integer numValueOf = Integer.valueOf(i9);
                MutableInt mutableInt = (MutableInt) map.get(numValueOf);
                if (mutableInt == null) {
                    map.put(numValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Integer num = (Integer) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i10 = 0;
                int i11 = 0;
                while (i10 < iIntValue) {
                    int iIndexOf = indexOf(iArr, num.intValue(), i11);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i10++;
                    i11 = iIndexOf + 1;
                }
            }
            return (int[]) removeAll(iArr, bitSet);
        }
        return clone(iArr);
    }

    public static byte[] toPrimitive(Byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i9 = 0; i9 < bArr.length; i9++) {
            bArr2[i9] = bArr[i9].byteValue();
        }
        return bArr2;
    }

    public static double[] addAll(double[] dArr, double... dArr2) {
        if (dArr == null) {
            return clone(dArr2);
        }
        if (dArr2 == null) {
            return clone(dArr);
        }
        double[] dArr3 = new double[dArr.length + dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr3, dArr.length, dArr2.length);
        return dArr3;
    }

    public static byte[] toPrimitive(Byte[] bArr, byte b9) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i9 = 0; i9 < bArr.length; i9++) {
            Byte b10 = bArr[i9];
            bArr2[i9] = b10 == null ? b9 : b10.byteValue();
        }
        return bArr2;
    }

    public static double[] toPrimitive(Double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i9 = 0; i9 < dArr.length; i9++) {
            dArr2[i9] = dArr[i9].doubleValue();
        }
        return dArr2;
    }

    public static char[] removeElements(char[] cArr, char... cArr2) {
        if (!isEmpty(cArr) && !isEmpty(cArr2)) {
            HashMap map = new HashMap(cArr2.length);
            for (char c9 : cArr2) {
                Character chValueOf = Character.valueOf(c9);
                MutableInt mutableInt = (MutableInt) map.get(chValueOf);
                if (mutableInt == null) {
                    map.put(chValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Character ch = (Character) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(cArr, ch.charValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (char[]) removeAll(cArr, bitSet);
        }
        return clone(cArr);
    }

    public static double[] toPrimitive(Double[] dArr, double d9) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i9 = 0; i9 < dArr.length; i9++) {
            Double d10 = dArr[i9];
            dArr2[i9] = d10 == null ? d9 : d10.doubleValue();
        }
        return dArr2;
    }

    public static float[] toPrimitive(Float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i9 = 0; i9 < fArr.length; i9++) {
            fArr2[i9] = fArr[i9].floatValue();
        }
        return fArr2;
    }

    public static float[] toPrimitive(Float[] fArr, float f9) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i9 = 0; i9 < fArr.length; i9++) {
            Float f10 = fArr[i9];
            fArr2[i9] = f10 == null ? f9 : f10.floatValue();
        }
        return fArr2;
    }

    public static long[] removeElements(long[] jArr, long... jArr2) {
        if (!isEmpty(jArr) && !isEmpty(jArr2)) {
            HashMap map = new HashMap(jArr2.length);
            for (long j9 : jArr2) {
                Long lValueOf = Long.valueOf(j9);
                MutableInt mutableInt = (MutableInt) map.get(lValueOf);
                if (mutableInt == null) {
                    map.put(lValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Long l9 = (Long) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(jArr, l9.longValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (long[]) removeAll(jArr, bitSet);
        }
        return clone(jArr);
    }

    public static boolean[] toPrimitive(Boolean[] boolArr) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i9 = 0; i9 < boolArr.length; i9++) {
            zArr[i9] = boolArr[i9].booleanValue();
        }
        return zArr;
    }

    public static boolean[] toPrimitive(Boolean[] boolArr, boolean z8) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i9 = 0; i9 < boolArr.length; i9++) {
            Boolean bool = boolArr[i9];
            zArr[i9] = bool == null ? z8 : bool.booleanValue();
        }
        return zArr;
    }

    public static float[] removeElements(float[] fArr, float... fArr2) {
        if (!isEmpty(fArr) && !isEmpty(fArr2)) {
            HashMap map = new HashMap(fArr2.length);
            for (float f9 : fArr2) {
                Float fValueOf = Float.valueOf(f9);
                MutableInt mutableInt = (MutableInt) map.get(fValueOf);
                if (mutableInt == null) {
                    map.put(fValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Float f10 = (Float) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(fArr, f10.floatValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (float[]) removeAll(fArr, bitSet);
        }
        return clone(fArr);
    }

    public static double[] removeElements(double[] dArr, double... dArr2) {
        if (!isEmpty(dArr) && !isEmpty(dArr2)) {
            HashMap map = new HashMap(dArr2.length);
            for (double d9 : dArr2) {
                Double dValueOf = Double.valueOf(d9);
                MutableInt mutableInt = (MutableInt) map.get(dValueOf);
                if (mutableInt == null) {
                    map.put(dValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Double d10 = (Double) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(dArr, d10.doubleValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (double[]) removeAll(dArr, bitSet);
        }
        return clone(dArr);
    }

    public static boolean[] removeElements(boolean[] zArr, boolean... zArr2) {
        if (!isEmpty(zArr) && !isEmpty(zArr2)) {
            HashMap map = new HashMap(2);
            for (boolean z8 : zArr2) {
                Boolean boolValueOf = Boolean.valueOf(z8);
                MutableInt mutableInt = (MutableInt) map.get(boolValueOf);
                if (mutableInt == null) {
                    map.put(boolValueOf, new MutableInt(1));
                } else {
                    mutableInt.increment();
                }
            }
            BitSet bitSet = new BitSet();
            for (Map.Entry entry : map.entrySet()) {
                Boolean bool = (Boolean) entry.getKey();
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                int i9 = 0;
                int i10 = 0;
                while (i9 < iIntValue) {
                    int iIndexOf = indexOf(zArr, bool.booleanValue(), i10);
                    if (iIndexOf < 0) {
                        break;
                    }
                    bitSet.set(iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
            }
            return (boolean[]) removeAll(zArr, bitSet);
        }
        return clone(zArr);
    }
}
