package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Comparator;
import sun.misc.Unsafe;

@GwtIncompatible
/* loaded from: classes2.dex */
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = -128;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    @VisibleForTesting
    public static class LexicographicalComparatorHolder {
        static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();

        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int iMin = Math.min(bArr.length, bArr2.length);
                for (int i9 = 0; i9 < iMin; i9++) {
                    int iCompare = UnsignedBytes.compare(bArr[i9], bArr2[i9]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        @VisibleForTesting
        public enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;

            static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            static final int BYTE_ARRAY_BASE_OFFSET;
            static final Unsafe theUnsafe;

            static {
                Unsafe unsafe = getUnsafe();
                theUnsafe = unsafe;
                int iArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
                BYTE_ARRAY_BASE_OFFSET = iArrayBaseOffset;
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || iArrayBaseOffset % 8 != 0 || unsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
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
                    return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.1
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

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int iMin = Math.min(bArr.length, bArr2.length);
                int i9 = iMin & (-8);
                int i10 = 0;
                while (i10 < i9) {
                    Unsafe unsafe = theUnsafe;
                    int i11 = BYTE_ARRAY_BASE_OFFSET;
                    long j9 = i10;
                    long j10 = unsafe.getLong(bArr, i11 + j9);
                    long j11 = unsafe.getLong(bArr2, i11 + j9);
                    if (j10 != j11) {
                        if (BIG_ENDIAN) {
                            return UnsignedLongs.compare(j10, j11);
                        }
                        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j10 ^ j11) & (-8);
                        return ((int) ((j10 >>> iNumberOfTrailingZeros) & 255)) - ((int) ((j11 >>> iNumberOfTrailingZeros) & 255));
                    }
                    i10 += 8;
                }
                while (i10 < iMin) {
                    int iCompare = UnsignedBytes.compare(bArr[i10], bArr2[i10]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                    i10++;
                }
                return bArr.length - bArr2.length;
            }
        }

        public static Comparator<byte[]> getBestComparator() {
            try {
                return (Comparator) Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    private UnsignedBytes() {
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long j9) {
        Preconditions.checkArgument((j9 >> 8) == 0, "out of range: %s", j9);
        return (byte) j9;
    }

    public static int compare(byte b9, byte b10) {
        return toInt(b9) - toInt(b10);
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * (str.length() + 3));
        sb.append(toInt(bArr[0]));
        for (int i9 = 1; i9 < bArr.length; i9++) {
            sb.append(str);
            sb.append(toString(bArr[i9]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    public static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i9 = toInt(bArr[0]);
        for (int i10 = 1; i10 < bArr.length; i10++) {
            int i11 = toInt(bArr[i10]);
            if (i11 > i9) {
                i9 = i11;
            }
        }
        return (byte) i9;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i9 = toInt(bArr[0]);
        for (int i10 = 1; i10 < bArr.length; i10++) {
            int i11 = toInt(bArr[i10]);
            if (i11 < i9) {
                i9 = i11;
            }
        }
        return (byte) i9;
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    public static byte saturatedCast(long j9) {
        if (j9 > toInt((byte) -1)) {
            return (byte) -1;
        }
        if (j9 < 0) {
            return (byte) 0;
        }
        return (byte) j9;
    }

    public static int toInt(byte b9) {
        return b9 & MAX_VALUE;
    }

    @Beta
    public static String toString(byte b9) {
        return toString(b9, 10);
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str, int i9) throws NumberFormatException {
        int i10 = Integer.parseInt((String) Preconditions.checkNotNull(str), i9);
        if ((i10 >> 8) == 0) {
            return (byte) i10;
        }
        throw new NumberFormatException("out of range: " + i10);
    }

    @Beta
    public static String toString(byte b9, int i9) {
        Preconditions.checkArgument(i9 >= 2 && i9 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i9);
        return Integer.toString(toInt(b9), i9);
    }
}
