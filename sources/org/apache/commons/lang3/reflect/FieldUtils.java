package org.apache.commons.lang3.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class FieldUtils {
    public static Field[] getAllFields(Class<?> cls) {
        List<Field> allFieldsList = getAllFieldsList(cls);
        return (Field[]) allFieldsList.toArray(new Field[allFieldsList.size()]);
    }

    public static List<Field> getAllFieldsList(Class<?> cls) {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                arrayList.add(field);
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    public static Field getDeclaredField(Class<?> cls, String str) {
        return getDeclaredField(cls, str, false);
    }

    public static Field getField(Class<?> cls, String str) throws NoSuchFieldException, SecurityException {
        Field field = getField(cls, str, false);
        MemberUtils.setAccessibleWorkaround(field);
        return field;
    }

    public static Object readDeclaredField(Object obj, String str) {
        return readDeclaredField(obj, str, false);
    }

    public static Object readDeclaredStaticField(Class<?> cls, String str) {
        return readDeclaredStaticField(cls, str, false);
    }

    public static Object readField(Field field, Object obj) {
        return readField(field, obj, false);
    }

    public static Object readStaticField(Field field) {
        return readStaticField(field, false);
    }

    public static void removeFinalModifier(Field field) throws NoSuchFieldException, SecurityException {
        removeFinalModifier(field, true);
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredField(obj, str, obj2, false);
    }

    public static void writeDeclaredStaticField(Class<?> cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeDeclaredStaticField(cls, str, obj, false);
    }

    public static void writeField(Field field, Object obj, Object obj2) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeField(field, obj, obj2, false);
    }

    public static void writeStaticField(Field field, Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        writeStaticField(field, obj, false);
    }

    public static Field getDeclaredField(Class<?> cls, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(StringUtils.isNotBlank(str), "The field name must not be blank/empty", new Object[0]);
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!MemberUtils.isAccessible(declaredField)) {
                if (!z8) {
                    return null;
                }
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    public static Object readDeclaredField(Object obj, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Validate.isTrue(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z8);
        Validate.isTrue(declaredField != null, "Cannot locate declared field %s.%s", cls, str);
        return readField(declaredField, obj, false);
    }

    public static Object readDeclaredStaticField(Class<?> cls, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Field declaredField = getDeclaredField(cls, str, z8);
        Validate.isTrue(declaredField != null, "Cannot locate declared field %s.%s", cls.getName(), str);
        return readStaticField(declaredField, false);
    }

    public static Object readField(Field field, Object obj, boolean z8) throws SecurityException {
        Validate.isTrue(field != null, "The field must not be null", new Object[0]);
        if (!z8 || field.isAccessible()) {
            MemberUtils.setAccessibleWorkaround(field);
        } else {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static Object readStaticField(Field field, boolean z8) {
        Validate.isTrue(field != null, "The field must not be null", new Object[0]);
        Validate.isTrue(Modifier.isStatic(field.getModifiers()), "The field '%s' is not static", field.getName());
        return readField(field, (Object) null, z8);
    }

    /* JADX WARN: Finally extract failed */
    public static void removeFinalModifier(Field field, boolean z8) throws NoSuchFieldException, SecurityException {
        Validate.isTrue(field != null, "The field must not be null", new Object[0]);
        try {
            if (Modifier.isFinal(field.getModifiers())) {
                Field declaredField = Field.class.getDeclaredField("modifiers");
                boolean z9 = z8 && !declaredField.isAccessible();
                if (z9) {
                    declaredField.setAccessible(true);
                }
                try {
                    declaredField.setInt(field, field.getModifiers() & (-17));
                    if (z9) {
                        declaredField.setAccessible(false);
                    }
                } catch (Throwable th) {
                    if (z9) {
                        declaredField.setAccessible(false);
                    }
                    throw th;
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    public static void writeDeclaredField(Object obj, String str, Object obj2, boolean z8) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Validate.isTrue(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field declaredField = getDeclaredField(cls, str, z8);
        Validate.isTrue(declaredField != null, "Cannot locate declared field %s.%s", cls.getName(), str);
        writeField(declaredField, obj, obj2, false);
    }

    public static void writeDeclaredStaticField(Class<?> cls, String str, Object obj, boolean z8) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field declaredField = getDeclaredField(cls, str, z8);
        Validate.isTrue(declaredField != null, "Cannot locate declared field %s.%s", cls.getName(), str);
        writeField(declaredField, (Object) null, obj, false);
    }

    public static void writeField(Field field, Object obj, Object obj2, boolean z8) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        Validate.isTrue(field != null, "The field must not be null", new Object[0]);
        if (!z8 || field.isAccessible()) {
            MemberUtils.setAccessibleWorkaround(field);
        } else {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    public static void writeStaticField(Field field, Object obj, boolean z8) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        Validate.isTrue(field != null, "The field must not be null", new Object[0]);
        Validate.isTrue(Modifier.isStatic(field.getModifiers()), "The field %s.%s is not static", field.getDeclaringClass().getName(), field.getName());
        writeField(field, (Object) null, obj, z8);
    }

    public static Field getField(Class<?> cls, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Field declaredField;
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(StringUtils.isNotBlank(str), "The field name must not be blank/empty", new Object[0]);
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                declaredField = superclass.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
            }
            if (!Modifier.isPublic(declaredField.getModifiers())) {
                if (z8) {
                    declaredField.setAccessible(true);
                } else {
                    continue;
                }
            }
            return declaredField;
        }
        Iterator<Class<?>> it = ClassUtils.getAllInterfaces(cls).iterator();
        Field field = null;
        while (it.hasNext()) {
            try {
                Field field2 = it.next().getField(str);
                Validate.isTrue(field == null, "Reference to field %s is ambiguous relative to %s; a matching field exists on two or more implemented interfaces.", str, cls);
                field = field2;
            } catch (NoSuchFieldException unused2) {
            }
        }
        return field;
    }

    public static Object readStaticField(Class<?> cls, String str) {
        return readStaticField(cls, str, false);
    }

    public static void writeStaticField(Class<?> cls, String str, Object obj) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeStaticField(cls, str, obj, false);
    }

    public static Object readStaticField(Class<?> cls, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Field field = getField(cls, str, z8);
        Validate.isTrue(field != null, "Cannot locate field '%s' on %s", str, cls);
        return readStaticField(field, false);
    }

    public static void writeStaticField(Class<?> cls, String str, Object obj, boolean z8) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field field = getField(cls, str, z8);
        Validate.isTrue(field != null, "Cannot locate field %s on %s", str, cls);
        writeStaticField(field, obj, false);
    }

    public static Object readField(Object obj, String str) {
        return readField(obj, str, false);
    }

    public static void writeField(Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        writeField(obj, str, obj2, false);
    }

    public static Object readField(Object obj, String str, boolean z8) throws NoSuchFieldException, SecurityException {
        Validate.isTrue(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z8);
        Validate.isTrue(field != null, "Cannot locate field %s on %s", str, cls);
        return readField(field, obj, false);
    }

    public static void writeField(Object obj, String str, Object obj2, boolean z8) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Validate.isTrue(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field field = getField(cls, str, z8);
        Validate.isTrue(field != null, "Cannot locate declared field %s.%s", cls.getName(), str);
        writeField(field, obj, obj2, false);
    }
}
