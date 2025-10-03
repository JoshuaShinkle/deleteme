package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrBuilder;

/* loaded from: classes.dex */
public class ObjectUtils {
    public static final Null NULL = new Null();

    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }

    public static byte CONST(byte b9) {
        return b9;
    }

    public static char CONST(char c9) {
        return c9;
    }

    public static double CONST(double d9) {
        return d9;
    }

    public static float CONST(float f9) {
        return f9;
    }

    public static int CONST(int i9) {
        return i9;
    }

    public static long CONST(long j9) {
        return j9;
    }

    public static <T> T CONST(T t8) {
        return t8;
    }

    public static short CONST(short s8) {
        return s8;
    }

    public static boolean CONST(boolean z8) {
        return z8;
    }

    public static byte CONST_BYTE(int i9) {
        if (i9 >= -128 && i9 <= 127) {
            return (byte) i9;
        }
        throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + i9 + "]");
    }

    public static short CONST_SHORT(int i9) {
        if (i9 >= -32768 && i9 <= 32767) {
            return (short) i9;
        }
        throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + i9 + "]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T clone(T t8) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (!(t8 instanceof Cloneable)) {
            return null;
        }
        if (!t8.getClass().isArray()) {
            try {
                return (T) t8.getClass().getMethod("clone", new Class[0]).invoke(t8, new Object[0]);
            } catch (IllegalAccessException e9) {
                throw new CloneFailedException("Cannot clone Cloneable type " + t8.getClass().getName(), e9);
            } catch (NoSuchMethodException e10) {
                throw new CloneFailedException("Cloneable type " + t8.getClass().getName() + " has no clone method", e10);
            } catch (InvocationTargetException e11) {
                throw new CloneFailedException("Exception cloning Cloneable type " + t8.getClass().getName(), e11.getCause());
            }
        }
        Class<?> componentType = t8.getClass().getComponentType();
        if (!componentType.isPrimitive()) {
            return (T) ((Object[]) t8).clone();
        }
        int length = Array.getLength(t8);
        T t9 = (T) Array.newInstance(componentType, length);
        while (true) {
            int i9 = length - 1;
            if (length <= 0) {
                return t9;
            }
            Array.set(t9, i9, Array.get(t8, i9));
            length = i9;
        }
    }

    public static <T> T cloneIfPossible(T t8) {
        T t9 = (T) clone(t8);
        return t9 == null ? t8 : t9;
    }

    public static <T extends Comparable<? super T>> int compare(T t8, T t9) {
        return compare(t8, t9, false);
    }

    public static <T> T defaultIfNull(T t8, T t9) {
        return t8 != null ? t8 : t9;
    }

    @Deprecated
    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static <T> T firstNonNull(T... tArr) {
        if (tArr == null) {
            return null;
        }
        for (T t8 : tArr) {
            if (t8 != null) {
                return t8;
            }
        }
        return null;
    }

    @Deprecated
    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Deprecated
    public static int hashCodeMulti(Object... objArr) {
        int iHashCode = 1;
        if (objArr != null) {
            for (Object obj : objArr) {
                iHashCode = (iHashCode * 31) + hashCode(obj);
            }
        }
        return iHashCode;
    }

    public static String identityToString(Object obj) {
        if (obj == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        identityToString(sb, obj);
        return sb.toString();
    }

    public static <T extends Comparable<? super T>> T max(T... tArr) {
        T t8 = null;
        if (tArr != null) {
            for (T t9 : tArr) {
                if (compare(t9, t8, false) > 0) {
                    t8 = t9;
                }
            }
        }
        return t8;
    }

    public static <T extends Comparable<? super T>> T median(T... tArr) {
        Validate.notEmpty(tArr);
        Validate.noNullElements(tArr);
        TreeSet treeSet = new TreeSet();
        Collections.addAll(treeSet, tArr);
        return (T) treeSet.toArray()[(treeSet.size() - 1) / 2];
    }

    public static <T extends Comparable<? super T>> T min(T... tArr) {
        T t8 = null;
        if (tArr != null) {
            for (T t9 : tArr) {
                if (compare(t9, t8, true) < 0) {
                    t8 = t9;
                }
            }
        }
        return t8;
    }

    public static <T> T mode(T... tArr) {
        if (!ArrayUtils.isNotEmpty(tArr)) {
            return null;
        }
        HashMap map = new HashMap(tArr.length);
        int i9 = 0;
        for (T t8 : tArr) {
            MutableInt mutableInt = (MutableInt) map.get(t8);
            if (mutableInt == null) {
                map.put(t8, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        while (true) {
            T t9 = null;
            for (Map.Entry entry : map.entrySet()) {
                int iIntValue = ((MutableInt) entry.getValue()).intValue();
                if (iIntValue == i9) {
                    break;
                }
                if (iIntValue > i9) {
                    t9 = (T) entry.getKey();
                    i9 = iIntValue;
                }
            }
            return t9;
        }
    }

    public static boolean notEqual(Object obj, Object obj2) {
        return !equals(obj, obj2);
    }

    public String toString() {
        return super.toString();
    }

    public static <T extends Comparable<? super T>> int compare(T t8, T t9, boolean z8) {
        if (t8 == t9) {
            return 0;
        }
        return t8 == null ? z8 ? 1 : -1 : t9 == null ? z8 ? -1 : 1 : t8.compareTo(t9);
    }

    @Deprecated
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    @Deprecated
    public static String toString(Object obj, String str) {
        return obj == null ? str : obj.toString();
    }

    public static void identityToString(Appendable appendable, Object obj) throws IOException {
        if (obj != null) {
            appendable.append(obj.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }

    public static void identityToString(StrBuilder strBuilder, Object obj) {
        if (obj != null) {
            strBuilder.append(obj.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }

    public static <T> T median(Comparator<T> comparator, T... tArr) {
        Validate.notEmpty(tArr, "null/empty items", new Object[0]);
        Validate.noNullElements(tArr);
        Validate.notNull(comparator, "null comparator", new Object[0]);
        TreeSet treeSet = new TreeSet(comparator);
        Collections.addAll(treeSet, tArr);
        return (T) treeSet.toArray()[(treeSet.size() - 1) / 2];
    }

    public static void identityToString(StringBuffer stringBuffer, Object obj) {
        if (obj != null) {
            stringBuffer.append(obj.getClass().getName());
            stringBuffer.append('@');
            stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }

    public static void identityToString(StringBuilder sb, Object obj) {
        if (obj != null) {
            sb.append(obj.getClass().getName());
            sb.append('@');
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }
}
