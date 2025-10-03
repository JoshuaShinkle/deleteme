package com.google.common.hash;

import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class LittleEndianByteArray {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final LittleEndianBytes byteArray;

    public enum JavaLittleEndianBytes implements LittleEndianBytes {
        INSTANCE { // from class: com.google.common.hash.LittleEndianByteArray.JavaLittleEndianBytes.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int i9) {
                return Longs.fromBytes(bArr[i9 + 7], bArr[i9 + 6], bArr[i9 + 5], bArr[i9 + 4], bArr[i9 + 3], bArr[i9 + 2], bArr[i9 + 1], bArr[i9]);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int i9, long j9) {
                long j10 = 255;
                for (int i10 = 0; i10 < 8; i10++) {
                    bArr[i9 + i10] = (byte) ((j9 & j10) >> (i10 * 8));
                    j10 <<= 8;
                }
            }
        }
    }

    public interface LittleEndianBytes {
        long getLongLittleEndian(byte[] bArr, int i9);

        void putLongLittleEndian(byte[] bArr, int i9, long j9);
    }

    public enum UnsafeByteArray implements LittleEndianBytes {
        UNSAFE_LITTLE_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int i9) {
                return UnsafeByteArray.theUnsafe.getLong(bArr, i9 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int i9, long j9) {
                UnsafeByteArray.theUnsafe.putLong(bArr, i9 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET, j9);
            }
        },
        UNSAFE_BIG_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.2
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int i9) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(bArr, i9 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int i9, long j9) {
                UnsafeByteArray.theUnsafe.putLong(bArr, i9 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET, Long.reverseBytes(j9));
            }
        };

        private static final int BYTE_ARRAY_BASE_OFFSET;
        private static final Unsafe theUnsafe;

        static {
            Unsafe unsafe = getUnsafe();
            theUnsafe = unsafe;
            BYTE_ARRAY_BASE_OFFSET = unsafe.arrayBaseOffset(byte[].class);
            if (unsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        private static Unsafe getUnsafe() {
            try {
                try {
                    return Unsafe.getUnsafe();
                } catch (PrivilegedActionException e9) {
                    throw new RuntimeException("Could not initialize intrinsics", e9.getCause());
                }
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.3
                    @Override // java.security.PrivilegedExceptionAction
                    public Unsafe run() throws IllegalAccessException, SecurityException, IllegalArgumentException {
                        for (Field field : Unsafe.class.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get(null);
                            if (Unsafe.class.isInstance(obj)) {
                                return (Unsafe) Unsafe.class.cast(obj);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            }
        }
    }

    static {
        LittleEndianBytes littleEndianBytes = JavaLittleEndianBytes.INSTANCE;
        try {
            String property = System.getProperty("os.arch");
            if ("amd64".equals(property) || "aarch64".equals(property)) {
                littleEndianBytes = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? UnsafeByteArray.UNSAFE_LITTLE_ENDIAN : UnsafeByteArray.UNSAFE_BIG_ENDIAN;
            }
        } catch (Throwable unused) {
        }
        byteArray = littleEndianBytes;
    }

    private LittleEndianByteArray() {
    }

    public static int load32(byte[] bArr, int i9) {
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    public static long load64(byte[] bArr, int i9) {
        return byteArray.getLongLittleEndian(bArr, i9);
    }

    public static long load64Safely(byte[] bArr, int i9, int i10) {
        long j9 = 0;
        for (int i11 = 0; i11 < Math.min(i10, 8); i11++) {
            j9 |= (bArr[i9 + i11] & 255) << (i11 * 8);
        }
        return j9;
    }

    public static void store64(byte[] bArr, int i9, long j9) {
        byteArray.putLongLittleEndian(bArr, i9, j9);
    }

    public static boolean usingUnsafe() {
        return byteArray instanceof UnsafeByteArray;
    }
}
