package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class ReflectionHelper {
    private ReflectionHelper() {
    }

    private static String constructorToString(Constructor<?> constructor) {
        StringBuilder sb = new StringBuilder(constructor.getDeclaringClass().getName());
        sb.append('#');
        sb.append(constructor.getDeclaringClass().getSimpleName());
        sb.append('(');
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i9 = 0; i9 < parameterTypes.length; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i9].getSimpleName());
        }
        sb.append(')');
        return sb.toString();
    }

    public static void makeAccessible(Field field) throws SecurityException {
        try {
            field.setAccessible(true);
        } catch (Exception e9) {
            throw new JsonIOException("Failed making field '" + field.getDeclaringClass().getName() + "#" + field.getName() + "' accessible; either change its visibility or write a custom TypeAdapter for its declaring type", e9);
        }
    }

    public static String tryMakeAccessible(Constructor<?> constructor) throws SecurityException {
        try {
            constructor.setAccessible(true);
            return null;
        } catch (Exception e9) {
            return "Failed making constructor '" + constructorToString(constructor) + "' accessible; either change its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e9.getMessage();
        }
    }
}
