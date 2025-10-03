package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class ObjectArrays {
    private ObjectArrays() {
    }

    @CanIgnoreReturnValue
    public static Object checkElementNotNull(Object obj, int i9) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i9);
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object... objArr) {
        return checkElementsNotNull(objArr, objArr.length);
    }

    @GwtIncompatible
    public static <T> T[] concat(T[] tArr, T[] tArr2, Class<T> cls) {
        T[] tArr3 = (T[]) newArray(cls, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    public static Object[] copyAsObjectArray(Object[] objArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, objArr.length);
        if (i10 == 0) {
            return new Object[0];
        }
        Object[] objArr2 = new Object[i10];
        System.arraycopy(objArr, i9, objArr2, 0, i10);
        return objArr2;
    }

    @CanIgnoreReturnValue
    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        Iterator<?> it = iterable.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            objArr[i9] = it.next();
            i9++;
        }
        return objArr;
    }

    @GwtIncompatible
    public static <T> T[] newArray(Class<T> cls, int i9) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i9));
    }

    public static void swap(Object[] objArr, int i9, int i10) {
        Object obj = objArr[i9];
        objArr[i9] = objArr[i10];
        objArr[i10] = obj;
    }

    public static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = (T[]) newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @CanIgnoreReturnValue
    public static Object[] checkElementsNotNull(Object[] objArr, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            checkElementNotNull(objArr[i10], i10);
        }
        return objArr;
    }

    public static <T> T[] newArray(T[] tArr, int i9) {
        return (T[]) Platform.newArray(tArr, i9);
    }

    public static <T> T[] concat(T t8, T[] tArr) {
        T[] tArr2 = (T[]) newArray(tArr, tArr.length + 1);
        tArr2[0] = t8;
        System.arraycopy(tArr, 0, tArr2, 1, tArr.length);
        return tArr2;
    }

    public static <T> T[] concat(T[] tArr, T t8) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length + 1);
        tArr2[tArr.length] = t8;
        return tArr2;
    }

    public static <T> T[] toArrayImpl(Object[] objArr, int i9, int i10, T[] tArr) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, objArr.length);
        if (tArr.length < i10) {
            tArr = (T[]) newArray(tArr, i10);
        } else if (tArr.length > i10) {
            tArr[i10] = null;
        }
        System.arraycopy(objArr, i9, tArr, 0, i10);
        return tArr;
    }

    public static Object[] toArrayImpl(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }
}
