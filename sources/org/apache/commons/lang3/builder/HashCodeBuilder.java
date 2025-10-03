package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes.dex */
public class HashCodeBuilder implements Builder<Integer> {
    private static final ThreadLocal<Set<IDKey>> REGISTRY = new ThreadLocal<>();
    private final int iConstant;
    private int iTotal;

    public HashCodeBuilder() {
        this.iConstant = 37;
        this.iTotal = 17;
    }

    public static Set<IDKey> getRegistry() {
        return REGISTRY.get();
    }

    public static boolean isRegistered(Object obj) {
        Set<IDKey> registry = getRegistry();
        return registry != null && registry.contains(new IDKey(obj));
    }

    private static void reflectionAppend(Object obj, Class<?> cls, HashCodeBuilder hashCodeBuilder, boolean z8, String[] strArr) {
        if (isRegistered(obj)) {
            return;
        }
        try {
            register(obj);
            Field[] declaredFields = cls.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);
            for (Field field : declaredFields) {
                if (!ArrayUtils.contains(strArr, field.getName()) && field.getName().indexOf(36) == -1 && ((z8 || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                    try {
                        hashCodeBuilder.append(field.get(obj));
                    } catch (IllegalAccessException unused) {
                        throw new InternalError("Unexpected IllegalAccessException");
                    }
                }
            }
        } finally {
            unregister(obj);
        }
    }

    public static int reflectionHashCode(int i9, int i10, Object obj) {
        return reflectionHashCode(i9, i10, obj, false, null, new String[0]);
    }

    public static void register(Object obj) {
        synchronized (HashCodeBuilder.class) {
            if (getRegistry() == null) {
                REGISTRY.set(new HashSet());
            }
        }
        getRegistry().add(new IDKey(obj));
    }

    public static void unregister(Object obj) {
        Set<IDKey> registry = getRegistry();
        if (registry != null) {
            registry.remove(new IDKey(obj));
            synchronized (HashCodeBuilder.class) {
                Set<IDKey> registry2 = getRegistry();
                if (registry2 != null && registry2.isEmpty()) {
                    REGISTRY.remove();
                }
            }
        }
    }

    public HashCodeBuilder append(boolean z8) {
        this.iTotal = (this.iTotal * this.iConstant) + (!z8 ? 1 : 0);
        return this;
    }

    public HashCodeBuilder appendSuper(int i9) {
        this.iTotal = (this.iTotal * this.iConstant) + i9;
        return this;
    }

    public int hashCode() {
        return toHashCode();
    }

    public int toHashCode() {
        return this.iTotal;
    }

    public static int reflectionHashCode(int i9, int i10, Object obj, boolean z8) {
        return reflectionHashCode(i9, i10, obj, z8, null, new String[0]);
    }

    public HashCodeBuilder append(boolean[] zArr) {
        if (zArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (boolean z8 : zArr) {
                append(z8);
            }
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.builder.Builder
    public Integer build() {
        return Integer.valueOf(toHashCode());
    }

    public static <T> int reflectionHashCode(int i9, int i10, T t8, boolean z8, Class<? super T> cls, String... strArr) {
        if (t8 != null) {
            HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(i9, i10);
            Class<?> superclass = t8.getClass();
            reflectionAppend(t8, superclass, hashCodeBuilder, z8, strArr);
            while (superclass.getSuperclass() != null && superclass != cls) {
                superclass = superclass.getSuperclass();
                reflectionAppend(t8, superclass, hashCodeBuilder, z8, strArr);
            }
            return hashCodeBuilder.toHashCode();
        }
        throw new IllegalArgumentException("The object to build a hash code for must not be null");
    }

    public HashCodeBuilder(int i9, int i10) {
        this.iTotal = 0;
        if (i9 % 2 != 0) {
            if (i10 % 2 != 0) {
                this.iConstant = i10;
                this.iTotal = i9;
                return;
            }
            throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
        }
        throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
    }

    public HashCodeBuilder append(byte b9) {
        this.iTotal = (this.iTotal * this.iConstant) + b9;
        return this;
    }

    public HashCodeBuilder append(byte[] bArr) {
        if (bArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (byte b9 : bArr) {
                append(b9);
            }
        }
        return this;
    }

    public HashCodeBuilder append(char c9) {
        this.iTotal = (this.iTotal * this.iConstant) + c9;
        return this;
    }

    public HashCodeBuilder append(char[] cArr) {
        if (cArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (char c9 : cArr) {
                append(c9);
            }
        }
        return this;
    }

    public static int reflectionHashCode(Object obj, boolean z8) {
        return reflectionHashCode(17, 37, obj, z8, null, new String[0]);
    }

    public static int reflectionHashCode(Object obj, Collection<String> collection) {
        return reflectionHashCode(obj, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static int reflectionHashCode(Object obj, String... strArr) {
        return reflectionHashCode(17, 37, obj, false, null, strArr);
    }

    public HashCodeBuilder append(double d9) {
        return append(Double.doubleToLongBits(d9));
    }

    public HashCodeBuilder append(double[] dArr) {
        if (dArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (double d9 : dArr) {
                append(d9);
            }
        }
        return this;
    }

    public HashCodeBuilder append(float f9) {
        this.iTotal = (this.iTotal * this.iConstant) + Float.floatToIntBits(f9);
        return this;
    }

    public HashCodeBuilder append(float[] fArr) {
        if (fArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (float f9 : fArr) {
                append(f9);
            }
        }
        return this;
    }

    public HashCodeBuilder append(int i9) {
        this.iTotal = (this.iTotal * this.iConstant) + i9;
        return this;
    }

    public HashCodeBuilder append(int[] iArr) {
        if (iArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (int i9 : iArr) {
                append(i9);
            }
        }
        return this;
    }

    public HashCodeBuilder append(long j9) {
        this.iTotal = (this.iTotal * this.iConstant) + ((int) (j9 ^ (j9 >> 32)));
        return this;
    }

    public HashCodeBuilder append(long[] jArr) {
        if (jArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (long j9 : jArr) {
                append(j9);
            }
        }
        return this;
    }

    public HashCodeBuilder append(Object obj) {
        if (obj == null) {
            this.iTotal *= this.iConstant;
        } else if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                append((long[]) obj);
            } else if (obj instanceof int[]) {
                append((int[]) obj);
            } else if (obj instanceof short[]) {
                append((short[]) obj);
            } else if (obj instanceof char[]) {
                append((char[]) obj);
            } else if (obj instanceof byte[]) {
                append((byte[]) obj);
            } else if (obj instanceof double[]) {
                append((double[]) obj);
            } else if (obj instanceof float[]) {
                append((float[]) obj);
            } else if (obj instanceof boolean[]) {
                append((boolean[]) obj);
            } else {
                append((Object[]) obj);
            }
        } else {
            this.iTotal = (this.iTotal * this.iConstant) + obj.hashCode();
        }
        return this;
    }

    public HashCodeBuilder append(Object[] objArr) {
        if (objArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (Object obj : objArr) {
                append(obj);
            }
        }
        return this;
    }

    public HashCodeBuilder append(short s8) {
        this.iTotal = (this.iTotal * this.iConstant) + s8;
        return this;
    }

    public HashCodeBuilder append(short[] sArr) {
        if (sArr == null) {
            this.iTotal *= this.iConstant;
        } else {
            for (short s8 : sArr) {
                append(s8);
            }
        }
        return this;
    }
}
