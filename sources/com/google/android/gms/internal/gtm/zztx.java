package com.google.android.gms.internal.gtm;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zztx {
    private static final Logger logger = Logger.getLogger(zztx.class.getName());
    private static final Class<?> zzavt;
    private static final boolean zzawt;
    private static final Unsafe zzbcx;
    private static final boolean zzbet;
    private static final boolean zzbeu;
    private static final zzd zzbev;
    private static final boolean zzbew;
    static final long zzbex;
    private static final long zzbey;
    private static final long zzbez;
    private static final long zzbfa;
    private static final long zzbfb;
    private static final long zzbfc;
    private static final long zzbfd;
    private static final long zzbfe;
    private static final long zzbff;
    private static final long zzbfg;
    private static final long zzbfh;
    private static final long zzbfi;
    private static final long zzbfj;
    private static final long zzbfk;
    private static final int zzbfl;
    static final boolean zzbfm;

    public static final class zza extends zzd {
        public zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(long j9, byte b9) {
            Memory.pokeByte((int) (j9 & (-1)), b9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zze(Object obj, long j9, byte b9) {
            if (zztx.zzbfm) {
                zztx.zza(obj, j9, b9);
            } else {
                zztx.zzb(obj, j9, b9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final boolean zzm(Object obj, long j9) {
            return zztx.zzbfm ? zztx.zzs(obj, j9) : zztx.zzt(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final float zzn(Object obj, long j9) {
            return Float.intBitsToFloat(zzk(obj, j9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final double zzo(Object obj, long j9) {
            return Double.longBitsToDouble(zzl(obj, j9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final byte zzy(Object obj, long j9) {
            return zztx.zzbfm ? zztx.zzq(obj, j9) : zztx.zzr(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            if (zztx.zzbfm) {
                zztx.zzb(obj, j9, z8);
            } else {
                zztx.zzc(obj, j9, z8);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, float f9) {
            zzb(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, double d9) {
            zza(obj, j9, Double.doubleToLongBits(d9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(byte[] bArr, long j9, long j10, long j11) {
            Memory.pokeByteArray((int) (j10 & (-1)), bArr, (int) j9, (int) j11);
        }
    }

    public static final class zzb extends zzd {
        public zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(long j9, byte b9) {
            Memory.pokeByte(j9, b9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zze(Object obj, long j9, byte b9) {
            if (zztx.zzbfm) {
                zztx.zza(obj, j9, b9);
            } else {
                zztx.zzb(obj, j9, b9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final boolean zzm(Object obj, long j9) {
            return zztx.zzbfm ? zztx.zzs(obj, j9) : zztx.zzt(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final float zzn(Object obj, long j9) {
            return Float.intBitsToFloat(zzk(obj, j9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final double zzo(Object obj, long j9) {
            return Double.longBitsToDouble(zzl(obj, j9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final byte zzy(Object obj, long j9) {
            return zztx.zzbfm ? zztx.zzq(obj, j9) : zztx.zzr(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            if (zztx.zzbfm) {
                zztx.zzb(obj, j9, z8);
            } else {
                zztx.zzc(obj, j9, z8);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, float f9) {
            zzb(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, double d9) {
            zza(obj, j9, Double.doubleToLongBits(d9));
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(byte[] bArr, long j9, long j10, long j11) {
            Memory.pokeByteArray(j10, bArr, (int) j9, (int) j11);
        }
    }

    public static final class zzc extends zzd {
        public zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(long j9, byte b9) {
            this.zzbfn.putByte(j9, b9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zze(Object obj, long j9, byte b9) {
            this.zzbfn.putByte(obj, j9, b9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final boolean zzm(Object obj, long j9) {
            return this.zzbfn.getBoolean(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final float zzn(Object obj, long j9) {
            return this.zzbfn.getFloat(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final double zzo(Object obj, long j9) {
            return this.zzbfn.getDouble(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final byte zzy(Object obj, long j9) {
            return this.zzbfn.getByte(obj, j9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            this.zzbfn.putBoolean(obj, j9, z8);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, float f9) {
            this.zzbfn.putFloat(obj, j9, f9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(Object obj, long j9, double d9) {
            this.zzbfn.putDouble(obj, j9, d9);
        }

        @Override // com.google.android.gms.internal.gtm.zztx.zzd
        public final void zza(byte[] bArr, long j9, long j10, long j11) {
            this.zzbfn.copyMemory(bArr, zztx.zzbex + j9, (Object) null, j10, j11);
        }
    }

    public static abstract class zzd {
        Unsafe zzbfn;

        public zzd(Unsafe unsafe) {
            this.zzbfn = unsafe;
        }

        public abstract void zza(long j9, byte b9);

        public abstract void zza(Object obj, long j9, double d9);

        public abstract void zza(Object obj, long j9, float f9);

        public final void zza(Object obj, long j9, long j10) {
            this.zzbfn.putLong(obj, j9, j10);
        }

        public abstract void zza(Object obj, long j9, boolean z8);

        public abstract void zza(byte[] bArr, long j9, long j10, long j11);

        public final void zzb(Object obj, long j9, int i9) {
            this.zzbfn.putInt(obj, j9, i9);
        }

        public abstract void zze(Object obj, long j9, byte b9);

        public final int zzk(Object obj, long j9) {
            return this.zzbfn.getInt(obj, j9);
        }

        public final long zzl(Object obj, long j9) {
            return this.zzbfn.getLong(obj, j9);
        }

        public abstract boolean zzm(Object obj, long j9);

        public abstract float zzn(Object obj, long j9);

        public abstract double zzo(Object obj, long j9);

        public abstract byte zzy(Object obj, long j9);
    }

    static {
        Unsafe unsafeZzro = zzro();
        zzbcx = unsafeZzro;
        zzavt = zzpp.zznb();
        boolean zZzn = zzn(Long.TYPE);
        zzbet = zZzn;
        boolean zZzn2 = zzn(Integer.TYPE);
        zzbeu = zZzn2;
        zzd zzcVar = null;
        if (unsafeZzro != null) {
            if (!zzpp.zzna()) {
                zzcVar = new zzc(unsafeZzro);
            } else if (zZzn) {
                zzcVar = new zzb(unsafeZzro);
            } else if (zZzn2) {
                zzcVar = new zza(unsafeZzro);
            }
        }
        zzbev = zzcVar;
        zzbew = zzrq();
        zzawt = zzrp();
        long jZzl = zzl(byte[].class);
        zzbex = jZzl;
        zzbey = zzl(boolean[].class);
        zzbez = zzm(boolean[].class);
        zzbfa = zzl(int[].class);
        zzbfb = zzm(int[].class);
        zzbfc = zzl(long[].class);
        zzbfd = zzm(long[].class);
        zzbfe = zzl(float[].class);
        zzbff = zzm(float[].class);
        zzbfg = zzl(double[].class);
        zzbfh = zzm(double[].class);
        zzbfi = zzl(Object[].class);
        zzbfj = zzm(Object[].class);
        Field fieldZzrr = zzrr();
        zzbfk = (fieldZzrr == null || zzcVar == null) ? -1L : zzcVar.zzbfn.objectFieldOffset(fieldZzrr);
        zzbfl = (int) (jZzl & 7);
        zzbfm = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private zztx() {
    }

    public static void zza(Object obj, long j9, long j10) {
        zzbev.zza(obj, j9, j10);
    }

    public static void zzb(Object obj, long j9, int i9) {
        zzbev.zzb(obj, j9, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j9, boolean z8) {
        zzb(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }

    public static <T> T zzk(Class<T> cls) {
        try {
            return (T) zzbcx.allocateInstance(cls);
        } catch (InstantiationException e9) {
            throw new IllegalStateException(e9);
        }
    }

    private static int zzl(Class<?> cls) {
        if (zzawt) {
            return zzbev.zzbfn.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzm(Class<?> cls) {
        if (zzawt) {
            return zzbev.zzbfn.arrayIndexScale(cls);
        }
        return -1;
    }

    public static float zzn(Object obj, long j9) {
        return zzbev.zzn(obj, j9);
    }

    public static double zzo(Object obj, long j9) {
        return zzbev.zzo(obj, j9);
    }

    public static Object zzp(Object obj, long j9) {
        return zzbev.zzbfn.getObject(obj, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzq(Object obj, long j9) {
        return (byte) (zzk(obj, (-4) & j9) >>> ((int) (((~j9) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzr(Object obj, long j9) {
        return (byte) (zzk(obj, (-4) & j9) >>> ((int) ((j9 & 3) << 3)));
    }

    public static boolean zzrm() {
        return zzawt;
    }

    public static boolean zzrn() {
        return zzbew;
    }

    public static Unsafe zzro() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzty());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzrp() {
        Unsafe unsafe = zzbcx;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getInt", Object.class, cls2);
            cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
            cls.getMethod("getLong", Object.class, cls2);
            cls.getMethod("putLong", Object.class, cls2, cls2);
            cls.getMethod("getObject", Object.class, cls2);
            cls.getMethod("putObject", Object.class, cls2, Object.class);
            if (zzpp.zzna()) {
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
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzrq() {
        Unsafe unsafe = zzbcx;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (zzrr() == null) {
                return false;
            }
            if (zzpp.zzna()) {
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
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static Field zzrr() {
        Field fieldZzb;
        if (zzpp.zzna() && (fieldZzb = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldZzb;
        }
        Field fieldZzb2 = zzb(Buffer.class, "address");
        if (fieldZzb2 == null || fieldZzb2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzb2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzs(Object obj, long j9) {
        return zzq(obj, j9) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzt(Object obj, long j9) {
        return zzr(obj, j9) != 0;
    }

    public static void zza(Object obj, long j9, boolean z8) {
        zzbev.zza(obj, j9, z8);
    }

    public static long zzb(ByteBuffer byteBuffer) {
        return zzbev.zzl(byteBuffer, zzbfk);
    }

    private static boolean zzn(Class<?> cls) {
        if (!zzpp.zzna()) {
            return false;
        }
        try {
            Class<?> cls2 = zzavt;
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

    public static void zza(Object obj, long j9, float f9) {
        zzbev.zza(obj, j9, f9);
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int zzk(Object obj, long j9) {
        return zzbev.zzk(obj, j9);
    }

    public static long zzl(Object obj, long j9) {
        return zzbev.zzl(obj, j9);
    }

    public static boolean zzm(Object obj, long j9) {
        return zzbev.zzm(obj, j9);
    }

    public static void zza(Object obj, long j9, double d9) {
        zzbev.zza(obj, j9, d9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int i9 = (((int) j9) & 3) << 3;
        zzb(obj, j10, ((255 & b9) << i9) | (zzk(obj, j10) & (~(255 << i9))));
    }

    public static void zza(Object obj, long j9, Object obj2) {
        zzbev.zzbfn.putObject(obj, j9, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j9, boolean z8) {
        zza(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }

    public static byte zza(byte[] bArr, long j9) {
        return zzbev.zzy(bArr, zzbex + j9);
    }

    public static void zza(byte[] bArr, long j9, byte b9) {
        zzbev.zze(bArr, zzbex + j9, b9);
    }

    public static void zza(byte[] bArr, long j9, long j10, long j11) {
        zzbev.zza(bArr, j9, j10, j11);
    }

    public static void zza(long j9, byte b9) {
        zzbev.zza(j9, b9);
    }

    public static int zza(byte[] bArr, int i9, byte[] bArr2, int i10, int i11) {
        int iNumberOfTrailingZeros;
        if (i9 >= 0 && i10 >= 0 && i11 >= 0 && i9 + i11 <= bArr.length && i10 + i11 <= bArr2.length) {
            int i12 = 0;
            if (zzawt) {
                for (int i13 = (zzbfl + i9) & 7; i12 < i11 && (i13 & 7) != 0; i13++) {
                    if (bArr[i9 + i12] != bArr2[i10 + i12]) {
                        return i12;
                    }
                    i12++;
                }
                int i14 = ((i11 - i12) & (-8)) + i12;
                while (i12 < i14) {
                    long j9 = zzbex;
                    long j10 = i12;
                    long jZzl = zzl(bArr, i9 + j9 + j10);
                    long jZzl2 = zzl(bArr2, j9 + i10 + j10);
                    if (jZzl != jZzl2) {
                        if (zzbfm) {
                            iNumberOfTrailingZeros = Long.numberOfLeadingZeros(jZzl ^ jZzl2);
                        } else {
                            iNumberOfTrailingZeros = Long.numberOfTrailingZeros(jZzl ^ jZzl2);
                        }
                        return i12 + (iNumberOfTrailingZeros >> 3);
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
        throw new IndexOutOfBoundsException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int iZzk = zzk(obj, j10);
        int i9 = ((~((int) j9)) & 3) << 3;
        zzb(obj, j10, ((255 & b9) << i9) | (iZzk & (~(255 << i9))));
    }
}
