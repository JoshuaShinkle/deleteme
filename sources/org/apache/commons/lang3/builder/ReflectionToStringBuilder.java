package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes.dex */
public class ReflectionToStringBuilder extends ToStringBuilder {
    private boolean appendStatics;
    private boolean appendTransients;
    protected String[] excludeFieldNames;
    private Class<?> upToClass;

    public ReflectionToStringBuilder(Object obj) {
        super(obj);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    public static String[] toNoNullStringArray(Collection<String> collection) {
        return collection == null ? ArrayUtils.EMPTY_STRING_ARRAY : toNoNullStringArray(collection.toArray());
    }

    public static String toString(Object obj) {
        return toString(obj, null, false, false, null);
    }

    public static String toStringExclude(Object obj, Collection<String> collection) {
        return toStringExclude(obj, toNoNullStringArray(collection));
    }

    public boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers()) && !isAppendTransients()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !isAppendStatics()) {
            return false;
        }
        String[] strArr = this.excludeFieldNames;
        return strArr == null || Arrays.binarySearch(strArr, field.getName()) < 0;
    }

    public void appendFieldsIn(Class<?> cls) throws SecurityException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (cls.isArray()) {
            reflectionAppendArray(getObject());
            return;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (Field field : declaredFields) {
            String name = field.getName();
            if (accept(field)) {
                try {
                    append(name, getValue(field));
                } catch (IllegalAccessException e9) {
                    throw new InternalError("Unexpected IllegalAccessException: " + e9.getMessage());
                }
            }
        }
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    public Class<?> getUpToClass() {
        return this.upToClass;
    }

    public Object getValue(Field field) {
        return field.get(getObject());
    }

    public boolean isAppendStatics() {
        return this.appendStatics;
    }

    public boolean isAppendTransients() {
        return this.appendTransients;
    }

    public ReflectionToStringBuilder reflectionAppendArray(Object obj) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, obj);
        return this;
    }

    public void setAppendStatics(boolean z8) {
        this.appendStatics = z8;
    }

    public void setAppendTransients(boolean z8) {
        this.appendTransients = z8;
    }

    public ReflectionToStringBuilder setExcludeFieldNames(String... strArr) {
        if (strArr == null) {
            this.excludeFieldNames = null;
        } else {
            String[] noNullStringArray = toNoNullStringArray(strArr);
            this.excludeFieldNames = noNullStringArray;
            Arrays.sort(noNullStringArray);
        }
        return this;
    }

    public void setUpToClass(Class<?> cls) {
        Object object;
        if (cls != null && (object = getObject()) != null && !cls.isInstance(object)) {
            throw new IllegalArgumentException("Specified class is not a superclass of the object");
        }
        this.upToClass = cls;
    }

    public static String toString(Object obj, ToStringStyle toStringStyle) {
        return toString(obj, toStringStyle, false, false, null);
    }

    public static String toStringExclude(Object obj, String... strArr) {
        return new ReflectionToStringBuilder(obj).setExcludeFieldNames(strArr).toString();
    }

    public static String[] toNoNullStringArray(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj.toString());
            }
        }
        return (String[]) arrayList.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z8) {
        return toString(obj, toStringStyle, z8, false, null);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z8, boolean z9) {
        return toString(obj, toStringStyle, z8, z9, null);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        super(obj, toStringStyle);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    public static <T> String toString(T t8, ToStringStyle toStringStyle, boolean z8, boolean z9, Class<? super T> cls) {
        return new ReflectionToStringBuilder(t8, toStringStyle, null, cls, z8, z9).toString();
    }

    @Override // org.apache.commons.lang3.builder.ToStringBuilder
    public String toString() throws SecurityException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (getObject() == null) {
            return getStyle().getNullText();
        }
        Class<?> superclass = getObject().getClass();
        appendFieldsIn(superclass);
        while (superclass.getSuperclass() != null && superclass != getUpToClass()) {
            superclass = superclass.getSuperclass();
            appendFieldsIn(superclass);
        }
        return super.toString();
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        super(obj, toStringStyle, stringBuffer);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    public <T> ReflectionToStringBuilder(T t8, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class<? super T> cls, boolean z8, boolean z9) {
        super(t8, toStringStyle, stringBuffer);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
        setUpToClass(cls);
        setAppendTransients(z8);
        setAppendStatics(z9);
    }
}
