package org.apache.commons.lang3.reflect;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes.dex */
abstract class MemberUtils {
    private static final int ACCESS_TEST = 7;
    private static final Class<?>[] ORDERED_PRIMITIVE_TYPES = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    public static int compareParameterTypes(Class<?>[] clsArr, Class<?>[] clsArr2, Class<?>[] clsArr3) {
        float totalTransformationCost = getTotalTransformationCost(clsArr3, clsArr);
        float totalTransformationCost2 = getTotalTransformationCost(clsArr3, clsArr2);
        if (totalTransformationCost < totalTransformationCost2) {
            return -1;
        }
        return totalTransformationCost2 < totalTransformationCost ? 1 : 0;
    }

    private static float getObjectTransformationCost(Class<?> cls, Class<?> cls2) {
        if (cls2.isPrimitive()) {
            return getPrimitivePromotionCost(cls, cls2);
        }
        float f9 = BitmapDescriptorFactory.HUE_RED;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && ClassUtils.isAssignable(cls, cls2)) {
                    f9 += 0.25f;
                    break;
                }
                f9 += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f9 + 1.5f : f9;
    }

    private static float getPrimitivePromotionCost(Class<?> cls, Class<?> cls2) {
        float f9;
        if (cls.isPrimitive()) {
            f9 = BitmapDescriptorFactory.HUE_RED;
        } else {
            cls = ClassUtils.wrapperToPrimitive(cls);
            f9 = 0.1f;
        }
        int i9 = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = ORDERED_PRIMITIVE_TYPES;
            if (i9 >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i9]) {
                f9 += 0.1f;
                if (i9 < clsArr.length - 1) {
                    cls = clsArr[i9 + 1];
                }
            }
            i9++;
        }
        return f9;
    }

    private static float getTotalTransformationCost(Class<?>[] clsArr, Class<?>[] clsArr2) {
        float objectTransformationCost = BitmapDescriptorFactory.HUE_RED;
        for (int i9 = 0; i9 < clsArr.length; i9++) {
            objectTransformationCost += getObjectTransformationCost(clsArr[i9], clsArr2[i9]);
        }
        return objectTransformationCost;
    }

    public static boolean isAccessible(Member member) {
        return (member == null || !Modifier.isPublic(member.getModifiers()) || member.isSynthetic()) ? false : true;
    }

    public static boolean isPackageAccess(int i9) {
        return (i9 & 7) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean setAccessibleWorkaround(AccessibleObject accessibleObject) throws SecurityException {
        if (accessibleObject != 0 && !accessibleObject.isAccessible()) {
            Member member = (Member) accessibleObject;
            if (!accessibleObject.isAccessible() && Modifier.isPublic(member.getModifiers()) && isPackageAccess(member.getDeclaringClass().getModifiers())) {
                try {
                    accessibleObject.setAccessible(true);
                    return true;
                } catch (SecurityException unused) {
                }
            }
        }
        return false;
    }
}
