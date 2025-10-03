package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.mutable.MutableObject;

/* loaded from: classes.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    private static final Map<String, String> abbreviationMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<String, String> reverseAbbreviationMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final String PACKAGE_SEPARATOR = String.valueOf(PACKAGE_SEPARATOR_CHAR);
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');

    public enum Interfaces {
        INCLUDE,
        EXCLUDE
    }

    static {
        HashMap map = new HashMap();
        primitiveWrapperMap = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Character.TYPE, Character.class);
        map.put(Short.TYPE, Short.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Double.TYPE, Double.class);
        map.put(Float.TYPE, Float.class);
        Class cls = Void.TYPE;
        map.put(cls, cls);
        wrapperPrimitiveMap = new HashMap();
        for (Class<?> cls2 : map.keySet()) {
            Class<?> cls3 = primitiveWrapperMap.get(cls2);
            if (!cls2.equals(cls3)) {
                wrapperPrimitiveMap.put(cls3, cls2);
            }
        }
        HashMap map2 = new HashMap();
        map2.put("int", "I");
        map2.put("boolean", "Z");
        map2.put("float", "F");
        map2.put("long", "J");
        map2.put("short", "S");
        map2.put("byte", "B");
        map2.put("double", "D");
        map2.put("char", "C");
        map2.put("void", "V");
        HashMap map3 = new HashMap();
        for (Map.Entry entry : map2.entrySet()) {
            map3.put(entry.getValue(), entry.getKey());
        }
        abbreviationMap = Collections.unmodifiableMap(map2);
        reverseAbbreviationMap = Collections.unmodifiableMap(map3);
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(Class.forName(it.next()));
            } catch (Exception unused) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Class<?> cls : list) {
            if (cls == null) {
                arrayList.add(null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        getAllInterfaces(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    private static String getCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            return null;
        }
        int i9 = 0;
        while (strDeleteWhitespace.startsWith("[")) {
            i9++;
            strDeleteWhitespace = strDeleteWhitespace.substring(1);
        }
        if (i9 < 1) {
            return strDeleteWhitespace;
        }
        if (strDeleteWhitespace.startsWith("L")) {
            strDeleteWhitespace = strDeleteWhitespace.substring(1, strDeleteWhitespace.endsWith(";") ? strDeleteWhitespace.length() - 1 : strDeleteWhitespace.length());
        } else if (strDeleteWhitespace.length() > 0) {
            strDeleteWhitespace = reverseAbbreviationMap.get(strDeleteWhitespace.substring(0, 1));
        }
        StringBuilder sb = new StringBuilder(strDeleteWhitespace);
        for (int i10 = 0; i10 < i9; i10++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public static Class<?> getClass(ClassLoader classLoader, String str, boolean z8) throws ClassNotFoundException {
        try {
            Map<String, String> map = abbreviationMap;
            if (!map.containsKey(str)) {
                return Class.forName(toCanonicalName(str), z8, classLoader);
            }
            return Class.forName("[" + map.get(str), z8, classLoader).getComponentType();
        } catch (ClassNotFoundException e9) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                try {
                    return getClass(classLoader, str.substring(0, iLastIndexOf) + '$' + str.substring(iLastIndexOf + 1), z8);
                } catch (ClassNotFoundException unused) {
                    throw e9;
                }
            }
            throw e9;
        }
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    public static Method getPublicMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Method method = cls.getMethod(str, clsArr);
        if (Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.addAll(getAllInterfaces(cls));
        arrayList.addAll(getAllSuperclasses(cls));
        for (Class cls2 : arrayList) {
            if (Modifier.isPublic(cls2.getModifiers())) {
                try {
                    Method method2 = cls2.getMethod(str, clsArr);
                    if (Modifier.isPublic(method2.getDeclaringClass().getModifiers())) {
                        return method2;
                    }
                } catch (NoSuchMethodException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchMethodException("Can't find a public method for " + str + StringUtils.SPACE + ArrayUtils.toString(clsArr));
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getName());
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static String getSimpleName(Class<?> cls) {
        return cls == null ? "" : cls.getSimpleName();
    }

    public static Iterable<Class<?>> hierarchy(Class<?> cls) {
        return hierarchy(cls, Interfaces.EXCLUDE);
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>... clsArr2) {
        return isAssignable(clsArr, clsArr2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static boolean isInnerClass(Class<?> cls) {
        return (cls == null || cls.getEnclosingClass() == null) ? false : true;
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return cls.isPrimitive() || isPrimitiveWrapper(cls);
    }

    public static boolean isPrimitiveWrapper(Class<?> cls) {
        return wrapperPrimitiveMap.containsKey(cls);
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : primitiveWrapperMap.get(cls);
    }

    public static Class<?>[] primitivesToWrappers(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i9 = 0; i9 < clsArr.length; i9++) {
            clsArr2[i9] = primitiveToWrapper(clsArr[i9]);
        }
        return clsArr2;
    }

    private static String toCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            throw new NullPointerException("className must not be null.");
        }
        if (!strDeleteWhitespace.endsWith("[]")) {
            return strDeleteWhitespace;
        }
        StringBuilder sb = new StringBuilder();
        while (strDeleteWhitespace.endsWith("[]")) {
            strDeleteWhitespace = strDeleteWhitespace.substring(0, strDeleteWhitespace.length() - 2);
            sb.append("[");
        }
        String str2 = abbreviationMap.get(strDeleteWhitespace);
        if (str2 != null) {
            sb.append(str2);
        } else {
            sb.append("L");
            sb.append(strDeleteWhitespace);
            sb.append(";");
        }
        return sb.toString();
    }

    public static Class<?>[] toClass(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i9 = 0; i9 < objArr.length; i9++) {
            Object obj = objArr[i9];
            clsArr[i9] = obj == null ? null : obj.getClass();
        }
        return clsArr;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }

    public static Class<?>[] wrappersToPrimitives(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i9 = 0; i9 < clsArr.length; i9++) {
            clsArr2[i9] = wrapperToPrimitive(clsArr[i9]);
        }
        return clsArr2;
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageName(Class<?> cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getShortCanonicalName(Class<?> cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getName());
    }

    public static String getShortClassName(Class<?> cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getSimpleName(Object obj, String str) {
        return obj == null ? str : getSimpleName(obj.getClass());
    }

    public static Iterable<Class<?>> hierarchy(final Class<?> cls, Interfaces interfaces) {
        final Iterable<Class<?>> iterable = new Iterable<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.1
            @Override // java.lang.Iterable
            public Iterator<Class<?>> iterator() {
                final MutableObject mutableObject = new MutableObject(cls);
                return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return mutableObject.getValue2() != null;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Class<?> next() {
                        Class<?> cls2 = (Class) mutableObject.getValue2();
                        mutableObject.setValue(cls2.getSuperclass());
                        return cls2;
                    }
                };
            }
        };
        return interfaces != Interfaces.INCLUDE ? iterable : new Iterable<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2
            @Override // java.lang.Iterable
            public Iterator<Class<?>> iterator() {
                final HashSet hashSet = new HashSet();
                final Iterator it = iterable.iterator();
                return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2.1
                    Iterator<Class<?>> interfaces = Collections.emptySet().iterator();

                    private void walkInterfaces(Set<Class<?>> set, Class<?> cls2) {
                        for (Class<?> cls3 : cls2.getInterfaces()) {
                            if (!hashSet.contains(cls3)) {
                                set.add(cls3);
                            }
                            walkInterfaces(set, cls3);
                        }
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.interfaces.hasNext() || it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Class<?> next() {
                        if (this.interfaces.hasNext()) {
                            Class<?> next = this.interfaces.next();
                            hashSet.add(next);
                            return next;
                        }
                        Class<?> cls2 = (Class) it.next();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        walkInterfaces(linkedHashSet, cls2);
                        this.interfaces = linkedHashSet.iterator();
                        return cls2;
                    }
                };
            }
        };
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z8) {
        if (!ArrayUtils.isSameLength(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (clsArr2 == null) {
            clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i9 = 0; i9 < clsArr.length; i9++) {
            if (!isAssignable(clsArr[i9], clsArr2[i9], z8)) {
                return false;
            }
        }
        return true;
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    public static String getPackageName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf);
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getShortClassName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                sb.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
            Map<String, String> map = reverseAbbreviationMap;
            if (map.containsKey(str)) {
                str = map.get(str);
            }
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iIndexOf = str.indexOf(36, iLastIndexOf != -1 ? iLastIndexOf + 1 : 0);
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.replace('$', PACKAGE_SEPARATOR_CHAR);
        }
        return strSubstring + ((Object) sb);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> hashSet) {
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (hashSet.add(cls2)) {
                    getAllInterfaces(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        return isAssignable(cls, cls2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static Class<?> getClass(ClassLoader classLoader, String str) {
        return getClass(classLoader, str, true);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2, boolean z8) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z8) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return false;
            }
            Class cls3 = Integer.TYPE;
            if (cls3.equals(cls)) {
                return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            Class cls4 = Long.TYPE;
            if (cls4.equals(cls)) {
                return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                return false;
            }
            Class cls5 = Float.TYPE;
            if (cls5.equals(cls)) {
                return Double.TYPE.equals(cls2);
            }
            if (Character.TYPE.equals(cls)) {
                return cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Short.TYPE.equals(cls)) {
                return cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(cls2) || cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class<?> getClass(String str) {
        return getClass(str, true);
    }

    public static Class<?> getClass(String str, boolean z8) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return getClass(contextClassLoader, str, z8);
    }
}
