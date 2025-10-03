package com.google.protobuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class UnsafeUtil {
    private static final long BOOLEAN_ARRAY_BASE_OFFSET;
    private static final long BOOLEAN_ARRAY_INDEX_SCALE;
    private static final long BUFFER_ADDRESS_OFFSET;
    private static final int BYTE_ARRAY_ALIGNMENT;
    static final long BYTE_ARRAY_BASE_OFFSET;
    private static final long DOUBLE_ARRAY_BASE_OFFSET;
    private static final long DOUBLE_ARRAY_INDEX_SCALE;
    private static final long FLOAT_ARRAY_BASE_OFFSET;
    private static final long FLOAT_ARRAY_INDEX_SCALE;
    private static final long INT_ARRAY_BASE_OFFSET;
    private static final long INT_ARRAY_INDEX_SCALE;
    static final boolean IS_BIG_ENDIAN;
    private static final long LONG_ARRAY_BASE_OFFSET;
    private static final long LONG_ARRAY_INDEX_SCALE;
    private static final long OBJECT_ARRAY_BASE_OFFSET;
    private static final long OBJECT_ARRAY_INDEX_SCALE;
    private static final int STRIDE = 8;
    private static final int STRIDE_ALIGNMENT_MASK = 7;
    private static final Logger logger = Logger.getLogger(UnsafeUtil.class.getName());
    private static final Unsafe UNSAFE = getUnsafe();
    private static final Class<?> MEMORY_CLASS = Android.getMemoryClass();
    private static final boolean IS_ANDROID_64 = determineAndroidSupportByAddressSize(Long.TYPE);
    private static final boolean IS_ANDROID_32 = determineAndroidSupportByAddressSize(Integer.TYPE);
    private static final MemoryAccessor MEMORY_ACCESSOR = getMemoryAccessor();
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();

    public static final class Android32MemoryAccessor extends MemoryAccessor {
        private static final long SMALL_ADDRESS_MASK = -1;

        public Android32MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        private static int smallAddress(long j9) {
            return (int) (j9 & (-1));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j9, byte[] bArr, long j10, long j11) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j9) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(obj, j9) : UnsafeUtil.getBooleanLittleEndian(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j9) {
            return Double.longBitsToDouble(getLong(obj, j9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j9) {
            return Float.intBitsToFloat(getInt(obj, j9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j9, boolean z8) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(obj, j9, z8);
            } else {
                UnsafeUtil.putBooleanLittleEndian(obj, j9, z8);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j9, byte b9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j9, double d9) {
            putLong(obj, j9, Double.doubleToLongBits(d9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j9, float f9) {
            putInt(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j9, int i9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j9, long j10) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j9, long j10, long j11) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j9) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(obj, j9) : UnsafeUtil.getByteLittleEndian(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j9, byte b9) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j9, b9);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j9, b9);
            }
        }
    }

    public static final class Android64MemoryAccessor extends MemoryAccessor {
        public Android64MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j9, byte[] bArr, long j10, long j11) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j9) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(obj, j9) : UnsafeUtil.getBooleanLittleEndian(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j9) {
            return Double.longBitsToDouble(getLong(obj, j9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j9) {
            return Float.intBitsToFloat(getInt(obj, j9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j9, boolean z8) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(obj, j9, z8);
            } else {
                UnsafeUtil.putBooleanLittleEndian(obj, j9, z8);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j9, byte b9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j9, double d9) {
            putLong(obj, j9, Double.doubleToLongBits(d9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j9, float f9) {
            putInt(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j9, int i9) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j9, long j10) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j9, long j10, long j11) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j9) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(obj, j9) : UnsafeUtil.getByteLittleEndian(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j9, byte b9) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j9, b9);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j9, b9);
            }
        }
    }

    public static final class JvmMemoryAccessor extends MemoryAccessor {
        public JvmMemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j9, byte[] bArr, long j10, long j11) {
            this.unsafe.copyMemory((Object) null, j9, bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j10, j11);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j9) {
            return this.unsafe.getBoolean(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j9) {
            return this.unsafe.getByte(j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j9) {
            return this.unsafe.getDouble(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j9) {
            return this.unsafe.getFloat(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j9) {
            return this.unsafe.getInt(j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j9) {
            return this.unsafe.getLong(j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            return getObject(this.unsafe.staticFieldBase(field), this.unsafe.staticFieldOffset(field));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j9, boolean z8) {
            this.unsafe.putBoolean(obj, j9, z8);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j9, byte b9) {
            this.unsafe.putByte(j9, b9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j9, double d9) {
            this.unsafe.putDouble(obj, j9, d9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j9, float f9) {
            this.unsafe.putFloat(obj, j9, f9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j9, int i9) {
            this.unsafe.putInt(j9, i9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j9, long j10) {
            this.unsafe.putLong(j9, j10);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j9, long j10, long j11) {
            this.unsafe.copyMemory(bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j9, (Object) null, j10, j11);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j9) {
            return this.unsafe.getByte(obj, j9);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j9, byte b9) {
            this.unsafe.putByte(obj, j9, b9);
        }
    }

    public static abstract class MemoryAccessor {
        Unsafe unsafe;

        public MemoryAccessor(Unsafe unsafe) {
            this.unsafe = unsafe;
        }

        public final int arrayBaseOffset(Class<?> cls) {
            return this.unsafe.arrayBaseOffset(cls);
        }

        public final int arrayIndexScale(Class<?> cls) {
            return this.unsafe.arrayIndexScale(cls);
        }

        public abstract void copyMemory(long j9, byte[] bArr, long j10, long j11);

        public abstract void copyMemory(byte[] bArr, long j9, long j10, long j11);

        public abstract boolean getBoolean(Object obj, long j9);

        public abstract byte getByte(long j9);

        public abstract byte getByte(Object obj, long j9);

        public abstract double getDouble(Object obj, long j9);

        public abstract float getFloat(Object obj, long j9);

        public abstract int getInt(long j9);

        public final int getInt(Object obj, long j9) {
            return this.unsafe.getInt(obj, j9);
        }

        public abstract long getLong(long j9);

        public final long getLong(Object obj, long j9) {
            return this.unsafe.getLong(obj, j9);
        }

        public final Object getObject(Object obj, long j9) {
            return this.unsafe.getObject(obj, j9);
        }

        public abstract Object getStaticObject(java.lang.reflect.Field field);

        public final long objectFieldOffset(java.lang.reflect.Field field) {
            return this.unsafe.objectFieldOffset(field);
        }

        public abstract void putBoolean(Object obj, long j9, boolean z8);

        public abstract void putByte(long j9, byte b9);

        public abstract void putByte(Object obj, long j9, byte b9);

        public abstract void putDouble(Object obj, long j9, double d9);

        public abstract void putFloat(Object obj, long j9, float f9);

        public abstract void putInt(long j9, int i9);

        public final void putInt(Object obj, long j9, int i9) {
            this.unsafe.putInt(obj, j9, i9);
        }

        public abstract void putLong(long j9, long j10);

        public final void putLong(Object obj, long j9, long j10) {
            this.unsafe.putLong(obj, j9, j10);
        }

        public final void putObject(Object obj, long j9, Object obj2) {
            this.unsafe.putObject(obj, j9, obj2);
        }
    }

    static {
        long jArrayBaseOffset = arrayBaseOffset(byte[].class);
        BYTE_ARRAY_BASE_OFFSET = jArrayBaseOffset;
        BOOLEAN_ARRAY_BASE_OFFSET = arrayBaseOffset(boolean[].class);
        BOOLEAN_ARRAY_INDEX_SCALE = arrayIndexScale(boolean[].class);
        INT_ARRAY_BASE_OFFSET = arrayBaseOffset(int[].class);
        INT_ARRAY_INDEX_SCALE = arrayIndexScale(int[].class);
        LONG_ARRAY_BASE_OFFSET = arrayBaseOffset(long[].class);
        LONG_ARRAY_INDEX_SCALE = arrayIndexScale(long[].class);
        FLOAT_ARRAY_BASE_OFFSET = arrayBaseOffset(float[].class);
        FLOAT_ARRAY_INDEX_SCALE = arrayIndexScale(float[].class);
        DOUBLE_ARRAY_BASE_OFFSET = arrayBaseOffset(double[].class);
        DOUBLE_ARRAY_INDEX_SCALE = arrayIndexScale(double[].class);
        OBJECT_ARRAY_BASE_OFFSET = arrayBaseOffset(Object[].class);
        OBJECT_ARRAY_INDEX_SCALE = arrayIndexScale(Object[].class);
        BUFFER_ADDRESS_OFFSET = fieldOffset(bufferAddressField());
        BYTE_ARRAY_ALIGNMENT = (int) (jArrayBaseOffset & 7);
        IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private UnsafeUtil() {
    }

    public static long addressOffset(ByteBuffer byteBuffer) {
        return MEMORY_ACCESSOR.getLong(byteBuffer, BUFFER_ADDRESS_OFFSET);
    }

    public static <T> T allocateInstance(Class<T> cls) {
        try {
            return (T) UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e9) {
            throw new IllegalStateException(e9);
        }
    }

    private static int arrayBaseOffset(Class<?> cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int arrayIndexScale(Class<?> cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayIndexScale(cls);
        }
        return -1;
    }

    private static java.lang.reflect.Field bufferAddressField() {
        java.lang.reflect.Field field;
        if (Android.isOnAndroidDevice() && (field = field(Buffer.class, "effectiveDirectAddress")) != null) {
            return field;
        }
        java.lang.reflect.Field field2 = field(Buffer.class, "address");
        if (field2 == null || field2.getType() != Long.TYPE) {
            return null;
        }
        return field2;
    }

    public static void copyMemory(byte[] bArr, long j9, long j10, long j11) {
        MEMORY_ACCESSOR.copyMemory(bArr, j9, j10, j11);
    }

    private static boolean determineAndroidSupportByAddressSize(Class<?> cls) {
        if (!Android.isOnAndroidDevice()) {
            return false;
        }
        try {
            Class<?> cls2 = MEMORY_CLASS;
            Class<?> cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class<?> cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static java.lang.reflect.Field field(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static long fieldOffset(java.lang.reflect.Field field) {
        MemoryAccessor memoryAccessor;
        if (field == null || (memoryAccessor = MEMORY_ACCESSOR) == null) {
            return -1L;
        }
        return memoryAccessor.objectFieldOffset(field);
    }

    private static int firstDifferingByteIndexNativeEndian(long j9, long j10) {
        return (IS_BIG_ENDIAN ? Long.numberOfLeadingZeros(j9 ^ j10) : Long.numberOfTrailingZeros(j9 ^ j10)) >> 3;
    }

    public static boolean getBoolean(Object obj, long j9) {
        return MEMORY_ACCESSOR.getBoolean(obj, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getBooleanBigEndian(Object obj, long j9) {
        return getByteBigEndian(obj, j9) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getBooleanLittleEndian(Object obj, long j9) {
        return getByteLittleEndian(obj, j9) != 0;
    }

    public static byte getByte(Object obj, long j9) {
        return MEMORY_ACCESSOR.getByte(obj, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte getByteBigEndian(Object obj, long j9) {
        return (byte) ((getInt(obj, (-4) & j9) >>> ((int) (((~j9) & 3) << 3))) & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte getByteLittleEndian(Object obj, long j9) {
        return (byte) ((getInt(obj, (-4) & j9) >>> ((int) ((j9 & 3) << 3))) & 255);
    }

    public static double getDouble(Object obj, long j9) {
        return MEMORY_ACCESSOR.getDouble(obj, j9);
    }

    public static float getFloat(Object obj, long j9) {
        return MEMORY_ACCESSOR.getFloat(obj, j9);
    }

    public static int getInt(Object obj, long j9) {
        return MEMORY_ACCESSOR.getInt(obj, j9);
    }

    public static long getLong(Object obj, long j9) {
        return MEMORY_ACCESSOR.getLong(obj, j9);
    }

    private static MemoryAccessor getMemoryAccessor() {
        Unsafe unsafe = UNSAFE;
        if (unsafe == null) {
            return null;
        }
        if (!Android.isOnAndroidDevice()) {
            return new JvmMemoryAccessor(unsafe);
        }
        if (IS_ANDROID_64) {
            return new Android64MemoryAccessor(unsafe);
        }
        if (IS_ANDROID_32) {
            return new Android32MemoryAccessor(unsafe);
        }
        return null;
    }

    public static Object getObject(Object obj, long j9) {
        return MEMORY_ACCESSOR.getObject(obj, j9);
    }

    public static Object getStaticObject(java.lang.reflect.Field field) {
        return MEMORY_ACCESSOR.getStaticObject(field);
    }

    public static Unsafe getUnsafe() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.protobuf.UnsafeUtil.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws IllegalAccessException, SecurityException, IllegalArgumentException {
                    for (java.lang.reflect.Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    public static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    public static boolean isAndroid64() {
        return IS_ANDROID_64;
    }

    public static int mismatch(byte[] bArr, int i9, byte[] bArr2, int i10, int i11) {
        if (i9 < 0 || i10 < 0 || i11 < 0 || i9 + i11 > bArr.length || i10 + i11 > bArr2.length) {
            throw new IndexOutOfBoundsException();
        }
        int i12 = 0;
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            for (int i13 = (BYTE_ARRAY_ALIGNMENT + i9) & 7; i12 < i11 && (i13 & 7) != 0; i13++) {
                if (bArr[i9 + i12] != bArr2[i10 + i12]) {
                    return i12;
                }
                i12++;
            }
            int i14 = ((i11 - i12) & (-8)) + i12;
            while (i12 < i14) {
                long j9 = BYTE_ARRAY_BASE_OFFSET;
                long j10 = i12;
                long j11 = getLong((Object) bArr, i9 + j9 + j10);
                long j12 = getLong((Object) bArr2, j9 + i10 + j10);
                if (j11 != j12) {
                    return i12 + firstDifferingByteIndexNativeEndian(j11, j12);
                }
                i12 += 8;
            }
        }
        while (i12 < i11) {
            if (bArr[i9 + i12] != bArr2[i10 + i12]) {
                return i12;
            }
            i12++;
        }
        return -1;
    }

    public static long objectFieldOffset(java.lang.reflect.Field field) {
        return MEMORY_ACCESSOR.objectFieldOffset(field);
    }

    public static void putBoolean(Object obj, long j9, boolean z8) {
        MEMORY_ACCESSOR.putBoolean(obj, j9, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putBooleanBigEndian(Object obj, long j9, boolean z8) {
        putByteBigEndian(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putBooleanLittleEndian(Object obj, long j9, boolean z8) {
        putByteLittleEndian(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }

    public static void putByte(Object obj, long j9, byte b9) {
        MEMORY_ACCESSOR.putByte(obj, j9, b9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putByteBigEndian(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int i9 = getInt(obj, j10);
        int i10 = ((~((int) j9)) & 3) << 3;
        putInt(obj, j10, ((255 & b9) << i10) | (i9 & (~(255 << i10))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putByteLittleEndian(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int i9 = (((int) j9) & 3) << 3;
        putInt(obj, j10, ((255 & b9) << i9) | (getInt(obj, j10) & (~(255 << i9))));
    }

    public static void putDouble(Object obj, long j9, double d9) {
        MEMORY_ACCESSOR.putDouble(obj, j9, d9);
    }

    public static void putFloat(Object obj, long j9, float f9) {
        MEMORY_ACCESSOR.putFloat(obj, j9, f9);
    }

    public static void putInt(Object obj, long j9, int i9) {
        MEMORY_ACCESSOR.putInt(obj, j9, i9);
    }

    public static void putLong(Object obj, long j9, long j10) {
        MEMORY_ACCESSOR.putLong(obj, j9, j10);
    }

    public static void putObject(Object obj, long j9, Object obj2) {
        MEMORY_ACCESSOR.putObject(obj, j9, obj2);
    }

    private static boolean supportsUnsafeArrayOperations() {
        Unsafe unsafe = UNSAFE;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getInt", Object.class, cls2);
            cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
            cls.getMethod("getLong", Object.class, cls2);
            cls.getMethod("putLong", Object.class, cls2, cls2);
            cls.getMethod("getObject", Object.class, cls2);
            cls.getMethod("putObject", Object.class, cls2, Object.class);
            if (Android.isOnAndroidDevice()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, cls2);
            cls.getMethod("putByte", Object.class, cls2, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, cls2);
            cls.getMethod("putBoolean", Object.class, cls2, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, cls2);
            cls.getMethod("putFloat", Object.class, cls2, Float.TYPE);
            cls.getMethod("getDouble", Object.class, cls2);
            cls.getMethod("putDouble", Object.class, cls2, Double.TYPE);
            return true;
        } catch (Throwable th) {
            logger.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        Unsafe unsafe = UNSAFE;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (bufferAddressField() == null) {
                return false;
            }
            if (Android.isOnAndroidDevice()) {
                return true;
            }
            cls.getMethod("getByte", cls2);
            cls.getMethod("putByte", cls2, Byte.TYPE);
            cls.getMethod("getInt", cls2);
            cls.getMethod("putInt", cls2, Integer.TYPE);
            cls.getMethod("getLong", cls2);
            cls.getMethod("putLong", cls2, cls2);
            cls.getMethod("copyMemory", cls2, cls2, cls2);
            cls.getMethod("copyMemory", Object.class, cls2, Object.class, cls2, cls2);
            return true;
        } catch (Throwable th) {
            logger.log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    public static void copyMemory(long j9, byte[] bArr, long j10, long j11) {
        MEMORY_ACCESSOR.copyMemory(j9, bArr, j10, j11);
    }

    public static boolean getBoolean(boolean[] zArr, long j9) {
        return MEMORY_ACCESSOR.getBoolean(zArr, BOOLEAN_ARRAY_BASE_OFFSET + (j9 * BOOLEAN_ARRAY_INDEX_SCALE));
    }

    public static byte getByte(byte[] bArr, long j9) {
        return MEMORY_ACCESSOR.getByte(bArr, BYTE_ARRAY_BASE_OFFSET + j9);
    }

    public static double getDouble(double[] dArr, long j9) {
        return MEMORY_ACCESSOR.getDouble(dArr, DOUBLE_ARRAY_BASE_OFFSET + (j9 * DOUBLE_ARRAY_INDEX_SCALE));
    }

    public static float getFloat(float[] fArr, long j9) {
        return MEMORY_ACCESSOR.getFloat(fArr, FLOAT_ARRAY_BASE_OFFSET + (j9 * FLOAT_ARRAY_INDEX_SCALE));
    }

    public static int getInt(int[] iArr, long j9) {
        return MEMORY_ACCESSOR.getInt(iArr, INT_ARRAY_BASE_OFFSET + (j9 * INT_ARRAY_INDEX_SCALE));
    }

    public static long getLong(long[] jArr, long j9) {
        return MEMORY_ACCESSOR.getLong(jArr, LONG_ARRAY_BASE_OFFSET + (j9 * LONG_ARRAY_INDEX_SCALE));
    }

    public static Object getObject(Object[] objArr, long j9) {
        return MEMORY_ACCESSOR.getObject(objArr, OBJECT_ARRAY_BASE_OFFSET + (j9 * OBJECT_ARRAY_INDEX_SCALE));
    }

    public static void putBoolean(boolean[] zArr, long j9, boolean z8) {
        MEMORY_ACCESSOR.putBoolean(zArr, BOOLEAN_ARRAY_BASE_OFFSET + (j9 * BOOLEAN_ARRAY_INDEX_SCALE), z8);
    }

    public static void putByte(byte[] bArr, long j9, byte b9) {
        MEMORY_ACCESSOR.putByte(bArr, BYTE_ARRAY_BASE_OFFSET + j9, b9);
    }

    public static void putDouble(double[] dArr, long j9, double d9) {
        MEMORY_ACCESSOR.putDouble(dArr, DOUBLE_ARRAY_BASE_OFFSET + (j9 * DOUBLE_ARRAY_INDEX_SCALE), d9);
    }

    public static void putFloat(float[] fArr, long j9, float f9) {
        MEMORY_ACCESSOR.putFloat(fArr, FLOAT_ARRAY_BASE_OFFSET + (j9 * FLOAT_ARRAY_INDEX_SCALE), f9);
    }

    public static void putInt(int[] iArr, long j9, int i9) {
        MEMORY_ACCESSOR.putInt(iArr, INT_ARRAY_BASE_OFFSET + (j9 * INT_ARRAY_INDEX_SCALE), i9);
    }

    public static void putLong(long[] jArr, long j9, long j10) {
        MEMORY_ACCESSOR.putLong(jArr, LONG_ARRAY_BASE_OFFSET + (j9 * LONG_ARRAY_INDEX_SCALE), j10);
    }

    public static void putObject(Object[] objArr, long j9, Object obj) {
        MEMORY_ACCESSOR.putObject(objArr, OBJECT_ARRAY_BASE_OFFSET + (j9 * OBJECT_ARRAY_INDEX_SCALE), obj);
    }

    public static void copyMemory(byte[] bArr, long j9, byte[] bArr2, long j10, long j11) {
        System.arraycopy(bArr, (int) j9, bArr2, (int) j10, (int) j11);
    }

    public static byte getByte(long j9) {
        return MEMORY_ACCESSOR.getByte(j9);
    }

    public static int getInt(long j9) {
        return MEMORY_ACCESSOR.getInt(j9);
    }

    public static long getLong(long j9) {
        return MEMORY_ACCESSOR.getLong(j9);
    }

    public static void putByte(long j9, byte b9) {
        MEMORY_ACCESSOR.putByte(j9, b9);
    }

    public static void putInt(long j9, int i9) {
        MEMORY_ACCESSOR.putInt(j9, i9);
    }

    public static void putLong(long j9, long j10) {
        MEMORY_ACCESSOR.putLong(j9, j10);
    }
}
