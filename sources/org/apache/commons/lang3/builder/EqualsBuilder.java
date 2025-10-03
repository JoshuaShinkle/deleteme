package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

/* loaded from: classes.dex */
public class EqualsBuilder implements Builder<Boolean> {
    private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal<>();
    private boolean isEquals = true;

    public static Pair<IDKey, IDKey> getRegisterPair(Object obj, Object obj2) {
        return Pair.m21920of(new IDKey(obj), new IDKey(obj2));
    }

    public static Set<Pair<IDKey, IDKey>> getRegistry() {
        return REGISTRY.get();
    }

    public static boolean isRegistered(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        Pair<IDKey, IDKey> registerPair = getRegisterPair(obj, obj2);
        return registry != null && (registry.contains(registerPair) || registry.contains(Pair.m21920of(registerPair.getLeft(), registerPair.getRight())));
    }

    private static void reflectionAppend(Object obj, Object obj2, Class<?> cls, EqualsBuilder equalsBuilder, boolean z8, String[] strArr) {
        if (isRegistered(obj, obj2)) {
            return;
        }
        try {
            register(obj, obj2);
            Field[] declaredFields = cls.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);
            for (int i9 = 0; i9 < declaredFields.length && equalsBuilder.isEquals; i9++) {
                Field field = declaredFields[i9];
                if (!ArrayUtils.contains(strArr, field.getName()) && field.getName().indexOf(36) == -1 && ((z8 || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                    try {
                        equalsBuilder.append(field.get(obj), field.get(obj2));
                    } catch (IllegalAccessException unused) {
                        throw new InternalError("Unexpected IllegalAccessException");
                    }
                }
            }
        } finally {
            unregister(obj, obj2);
        }
    }

    public static boolean reflectionEquals(Object obj, Object obj2, Collection<String> collection) {
        return reflectionEquals(obj, obj2, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static void register(Object obj, Object obj2) {
        synchronized (EqualsBuilder.class) {
            if (getRegistry() == null) {
                REGISTRY.set(new HashSet());
            }
        }
        getRegistry().add(getRegisterPair(obj, obj2));
    }

    public static void unregister(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        if (registry != null) {
            registry.remove(getRegisterPair(obj, obj2));
            synchronized (EqualsBuilder.class) {
                Set<Pair<IDKey, IDKey>> registry2 = getRegistry();
                if (registry2 != null && registry2.isEmpty()) {
                    REGISTRY.remove();
                }
            }
        }
    }

    public EqualsBuilder append(Object obj, Object obj2) {
        if (!this.isEquals || obj == obj2) {
            return this;
        }
        if (obj == null || obj2 == null) {
            setEquals(false);
            return this;
        }
        if (!obj.getClass().isArray()) {
            this.isEquals = obj.equals(obj2);
        } else if (obj.getClass() != obj2.getClass()) {
            setEquals(false);
        } else if (obj instanceof long[]) {
            append((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            append((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            append((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            append((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            append((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            append((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            append((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            append((boolean[]) obj, (boolean[]) obj2);
        } else {
            append((Object[]) obj, (Object[]) obj2);
        }
        return this;
    }

    public EqualsBuilder appendSuper(boolean z8) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z8;
        return this;
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    public void reset() {
        this.isEquals = true;
    }

    public void setEquals(boolean z8) {
        this.isEquals = z8;
    }

    public static boolean reflectionEquals(Object obj, Object obj2, String... strArr) {
        return reflectionEquals(obj, obj2, false, null, strArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.builder.Builder
    public Boolean build() {
        return Boolean.valueOf(isEquals());
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z8) {
        return reflectionEquals(obj, obj2, z8, null, new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0039 A[Catch: IllegalArgumentException -> 0x0061, TryCatch #0 {IllegalArgumentException -> 0x0061, blocks: (B:21:0x0033, B:23:0x0039, B:24:0x003d, B:25:0x0046, B:28:0x004e), top: B:34:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d A[Catch: IllegalArgumentException -> 0x0061, TryCatch #0 {IllegalArgumentException -> 0x0061, blocks: (B:21:0x0033, B:23:0x0039, B:24:0x003d, B:25:0x0046, B:28:0x004e), top: B:34:0x0033 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean reflectionEquals(Object obj, Object obj2, boolean z8, Class<?> cls, String... strArr) {
        EqualsBuilder equalsBuilder;
        if (obj == obj2) {
            return true;
        }
        if (obj != null && obj2 != null) {
            Class<?> superclass = obj.getClass();
            Class<?> cls2 = obj2.getClass();
            if (superclass.isInstance(obj2)) {
                if (!cls2.isInstance(obj)) {
                    superclass = cls2;
                }
                equalsBuilder = new EqualsBuilder();
                try {
                    if (!superclass.isArray()) {
                        equalsBuilder.append(obj, obj2);
                    } else {
                        reflectionAppend(obj, obj2, superclass, equalsBuilder, z8, strArr);
                        while (superclass.getSuperclass() != null && superclass != cls) {
                            superclass = superclass.getSuperclass();
                            reflectionAppend(obj, obj2, superclass, equalsBuilder, z8, strArr);
                        }
                    }
                    return equalsBuilder.isEquals();
                } catch (IllegalArgumentException unused) {
                }
            } else if (cls2.isInstance(obj)) {
                if (superclass.isInstance(obj2)) {
                }
                equalsBuilder = new EqualsBuilder();
                if (!superclass.isArray()) {
                }
                return equalsBuilder.isEquals();
            }
        }
        return false;
    }

    public EqualsBuilder append(long j9, long j10) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = j9 == j10;
        return this;
    }

    public EqualsBuilder append(int i9, int i10) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = i9 == i10;
        return this;
    }

    public EqualsBuilder append(short s8, short s9) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = s8 == s9;
        return this;
    }

    public EqualsBuilder append(char c9, char c10) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = c9 == c10;
        return this;
    }

    public EqualsBuilder append(byte b9, byte b10) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = b9 == b10;
        return this;
    }

    public EqualsBuilder append(double d9, double d10) {
        return !this.isEquals ? this : append(Double.doubleToLongBits(d9), Double.doubleToLongBits(d10));
    }

    public EqualsBuilder append(float f9, float f10) {
        return !this.isEquals ? this : append(Float.floatToIntBits(f9), Float.floatToIntBits(f10));
    }

    public EqualsBuilder append(boolean z8, boolean z9) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z8 == z9;
        return this;
    }

    public EqualsBuilder append(Object[] objArr, Object[] objArr2) {
        if (!this.isEquals || objArr == objArr2) {
            return this;
        }
        if (objArr != null && objArr2 != null) {
            if (objArr.length != objArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < objArr.length && this.isEquals; i9++) {
                append(objArr[i9], objArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(long[] jArr, long[] jArr2) {
        if (!this.isEquals || jArr == jArr2) {
            return this;
        }
        if (jArr != null && jArr2 != null) {
            if (jArr.length != jArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < jArr.length && this.isEquals; i9++) {
                append(jArr[i9], jArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(int[] iArr, int[] iArr2) {
        if (!this.isEquals || iArr == iArr2) {
            return this;
        }
        if (iArr != null && iArr2 != null) {
            if (iArr.length != iArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < iArr.length && this.isEquals; i9++) {
                append(iArr[i9], iArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(short[] sArr, short[] sArr2) {
        if (!this.isEquals || sArr == sArr2) {
            return this;
        }
        if (sArr != null && sArr2 != null) {
            if (sArr.length != sArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < sArr.length && this.isEquals; i9++) {
                append(sArr[i9], sArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(char[] cArr, char[] cArr2) {
        if (!this.isEquals || cArr == cArr2) {
            return this;
        }
        if (cArr != null && cArr2 != null) {
            if (cArr.length != cArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < cArr.length && this.isEquals; i9++) {
                append(cArr[i9], cArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(byte[] bArr, byte[] bArr2) {
        if (!this.isEquals || bArr == bArr2) {
            return this;
        }
        if (bArr != null && bArr2 != null) {
            if (bArr.length != bArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < bArr.length && this.isEquals; i9++) {
                append(bArr[i9], bArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(double[] dArr, double[] dArr2) {
        if (!this.isEquals || dArr == dArr2) {
            return this;
        }
        if (dArr != null && dArr2 != null) {
            if (dArr.length != dArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < dArr.length && this.isEquals; i9++) {
                append(dArr[i9], dArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(float[] fArr, float[] fArr2) {
        if (!this.isEquals || fArr == fArr2) {
            return this;
        }
        if (fArr != null && fArr2 != null) {
            if (fArr.length != fArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < fArr.length && this.isEquals; i9++) {
                append(fArr[i9], fArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }

    public EqualsBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (!this.isEquals || zArr == zArr2) {
            return this;
        }
        if (zArr != null && zArr2 != null) {
            if (zArr.length != zArr2.length) {
                setEquals(false);
                return this;
            }
            for (int i9 = 0; i9 < zArr.length && this.isEquals; i9++) {
                append(zArr[i9], zArr2[i9]);
            }
            return this;
        }
        setEquals(false);
        return this;
    }
}
