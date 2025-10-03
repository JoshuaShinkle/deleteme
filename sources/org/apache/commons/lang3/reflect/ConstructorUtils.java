package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class ConstructorUtils {
    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        Validate.notNull(cls, "class cannot be null", new Object[0]);
        try {
            return getAccessibleConstructor(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Constructor<T> accessibleConstructor;
        Validate.notNull(cls, "class cannot be null", new Object[0]);
        try {
            Constructor<T> constructor = cls.getConstructor(clsArr);
            MemberUtils.setAccessibleWorkaround(constructor);
            return constructor;
        } catch (NoSuchMethodException unused) {
            Constructor<T> constructor2 = null;
            for (Constructor<?> constructor3 : cls.getConstructors()) {
                if (ClassUtils.isAssignable(clsArr, constructor3.getParameterTypes(), true) && (accessibleConstructor = getAccessibleConstructor(constructor3)) != null) {
                    MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                    if (constructor2 == null || MemberUtils.compareParameterTypes(accessibleConstructor.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0) {
                        constructor2 = accessibleConstructor;
                    }
                }
            }
            return constructor2;
        }
    }

    public static <T> T invokeConstructor(Class<T> cls, Object... objArr) {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeConstructor(cls, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object... objArr) {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeExactConstructor(cls, objArrNullToEmpty, ClassUtils.toClass(objArrNullToEmpty));
    }

    private static boolean isAccessible(Class<?> cls) {
        while (cls != null) {
            if (!Modifier.isPublic(cls.getModifiers())) {
                return false;
            }
            cls = cls.getEnclosingClass();
        }
        return true;
    }

    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> constructor) {
        Validate.notNull(constructor, "constructor cannot be null", new Object[0]);
        if (MemberUtils.isAccessible(constructor) && isAccessible(constructor.getDeclaringClass())) {
            return constructor;
        }
        return null;
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, SecurityException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, ArrayUtils.nullToEmpty(clsArr));
        if (matchingAccessibleConstructor != null) {
            return (T) matchingAccessibleConstructor.newInstance(objArrNullToEmpty);
        }
        throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException {
        Object[] objArrNullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Constructor accessibleConstructor = getAccessibleConstructor(cls, ArrayUtils.nullToEmpty(clsArr));
        if (accessibleConstructor != null) {
            return (T) accessibleConstructor.newInstance(objArrNullToEmpty);
        }
        throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }
}
