package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzkt {
    static final boolean zza;
    private static final Logger zzb = Logger.getLogger(zzkt.class.getName());
    private static final Unsafe zzc;
    private static final Class<?> zzd;
    private static final boolean zze;
    private static final boolean zzf;
    private static final zzd zzg;
    private static final boolean zzh;
    private static final boolean zzi;
    private static final long zzj;
    private static final long zzk;
    private static final long zzl;
    private static final long zzm;
    private static final long zzn;
    private static final long zzo;
    private static final long zzp;
    private static final long zzq;
    private static final long zzr;
    private static final long zzs;
    private static final long zzt;
    private static final long zzu;
    private static final long zzv;
    private static final long zzw;
    private static final int zzx;

    public static final class zzb extends zzd {
        public zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final byte zza(Object obj, long j9) {
            return this.zza.getByte(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final boolean zzb(Object obj, long j9) {
            return this.zza.getBoolean(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final float zzc(Object obj, long j9) {
            return this.zza.getFloat(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final double zzd(Object obj, long j9) {
            return this.zza.getDouble(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, byte b9) {
            this.zza.putByte(obj, j9, b9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            this.zza.putBoolean(obj, j9, z8);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, float f9) {
            this.zza.putFloat(obj, j9, f9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, double d9) {
            this.zza.putDouble(obj, j9, d9);
        }
    }

    public static abstract class zzd {
        Unsafe zza;

        public zzd(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public abstract byte zza(Object obj, long j9);

        public abstract void zza(Object obj, long j9, byte b9);

        public abstract void zza(Object obj, long j9, double d9);

        public abstract void zza(Object obj, long j9, float f9);

        public final void zza(Object obj, long j9, int i9) {
            this.zza.putInt(obj, j9, i9);
        }

        public abstract void zza(Object obj, long j9, boolean z8);

        public abstract boolean zzb(Object obj, long j9);

        public abstract float zzc(Object obj, long j9);

        public abstract double zzd(Object obj, long j9);

        public final int zze(Object obj, long j9) {
            return this.zza.getInt(obj, j9);
        }

        public final long zzf(Object obj, long j9) {
            return this.zza.getLong(obj, j9);
        }

        public final void zza(Object obj, long j9, long j10) {
            this.zza.putLong(obj, j9, j10);
        }
    }

    static {
        Unsafe unsafeZzc = zzc();
        zzc = unsafeZzc;
        zzd = zzgj.zzb();
        boolean zZzd = zzd(Long.TYPE);
        zze = zZzd;
        boolean zZzd2 = zzd(Integer.TYPE);
        zzf = zZzd2;
        zzd zzbVar = null;
        if (unsafeZzc != null) {
            if (!zzgj.zza()) {
                zzbVar = new zzb(unsafeZzc);
            } else if (zZzd) {
                zzbVar = new zzc(unsafeZzc);
            } else if (zZzd2) {
                zzbVar = new zza(unsafeZzc);
            }
        }
        zzg = zzbVar;
        zzh = zze();
        zzi = zzd();
        long jZzb = zzb(byte[].class);
        zzj = jZzb;
        zzk = zzb(boolean[].class);
        zzl = zzc(boolean[].class);
        zzm = zzb(int[].class);
        zzn = zzc(int[].class);
        zzo = zzb(long[].class);
        zzp = zzc(long[].class);
        zzq = zzb(float[].class);
        zzr = zzc(float[].class);
        zzs = zzb(double[].class);
        zzt = zzc(double[].class);
        zzu = zzb(Object[].class);
        zzv = zzc(Object[].class);
        Field fieldZzf = zzf();
        zzw = (fieldZzf == null || zzbVar == null) ? -1L : zzbVar.zza.objectFieldOffset(fieldZzf);
        zzx = (int) (jZzb & 7);
        zza = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private zzkt() {
    }

    public static boolean zza() {
        return zzi;
    }

    public static boolean zzb() {
        return zzh;
    }

    private static int zzc(Class<?> cls) {
        if (zzi) {
            return zzg.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    public static float zzd(Object obj, long j9) {
        return zzg.zzc(obj, j9);
    }

    public static double zze(Object obj, long j9) {
        return zzg.zzd(obj, j9);
    }

    public static Object zzf(Object obj, long j9) {
        return zzg.zza.getObject(obj, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzk(Object obj, long j9) {
        return (byte) (zza(obj, (-4) & j9) >>> ((int) (((~j9) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzl(Object obj, long j9) {
        return (byte) (zza(obj, (-4) & j9) >>> ((int) ((j9 & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzm(Object obj, long j9) {
        return zzk(obj, j9) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzn(Object obj, long j9) {
        return zzl(obj, j9) != 0;
    }

    public static <T> T zza(Class<T> cls) {
        try {
            return (T) zzc.allocateInstance(cls);
        } catch (InstantiationException e9) {
            throw new IllegalStateException(e9);
        }
    }

    private static int zzb(Class<?> cls) {
        if (zzi) {
            return zzg.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static boolean zzd() {
        Unsafe unsafe = zzc;
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
            if (zzgj.zza()) {
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
            Logger logger = zzb;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zze() {
        Unsafe unsafe = zzc;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (zzf() == null) {
                return false;
            }
            if (zzgj.zza()) {
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
            Logger logger = zzb;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    public static final class zza extends zzd {
        public zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final byte zza(Object obj, long j9) {
            return zzkt.zza ? zzkt.zzk(obj, j9) : zzkt.zzl(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final boolean zzb(Object obj, long j9) {
            return zzkt.zza ? zzkt.zzm(obj, j9) : zzkt.zzn(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final float zzc(Object obj, long j9) {
            return Float.intBitsToFloat(zze(obj, j9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final double zzd(Object obj, long j9) {
            return Double.longBitsToDouble(zzf(obj, j9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, byte b9) {
            if (!zzkt.zza) {
                zzkt.zzd(obj, j9, b9);
            } else {
                zzkt.zzc(obj, j9, b9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            if (zzkt.zza) {
                zzkt.zzd(obj, j9, z8);
            } else {
                zzkt.zze(obj, j9, z8);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, float f9) {
            zza(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, double d9) {
            zza(obj, j9, Double.doubleToLongBits(d9));
        }
    }

    public static final class zzc extends zzd {
        public zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final byte zza(Object obj, long j9) {
            return zzkt.zza ? zzkt.zzk(obj, j9) : zzkt.zzl(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final boolean zzb(Object obj, long j9) {
            return zzkt.zza ? zzkt.zzm(obj, j9) : zzkt.zzn(obj, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final float zzc(Object obj, long j9) {
            return Float.intBitsToFloat(zze(obj, j9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final double zzd(Object obj, long j9) {
            return Double.longBitsToDouble(zzf(obj, j9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, byte b9) {
            if (!zzkt.zza) {
                zzkt.zzd(obj, j9, b9);
            } else {
                zzkt.zzc(obj, j9, b9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, boolean z8) {
            if (zzkt.zza) {
                zzkt.zzd(obj, j9, z8);
            } else {
                zzkt.zze(obj, j9, z8);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, float f9) {
            zza(obj, j9, Float.floatToIntBits(f9));
        }

        @Override // com.google.android.gms.internal.measurement.zzkt.zzd
        public final void zza(Object obj, long j9, double d9) {
            zza(obj, j9, Double.doubleToLongBits(d9));
        }
    }

    public static boolean zzc(Object obj, long j9) {
        return zzg.zzb(obj, j9);
    }

    private static Field zzf() {
        Field fieldZza;
        if (zzgj.zza() && (fieldZza = zza((Class<?>) Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldZza;
        }
        Field fieldZza2 = zza((Class<?>) Buffer.class, "address");
        if (fieldZza2 == null || fieldZza2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZza2;
    }

    public static int zza(Object obj, long j9) {
        return zzg.zze(obj, j9);
    }

    public static long zzb(Object obj, long j9) {
        return zzg.zzf(obj, j9);
    }

    public static Unsafe zzc() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzkv());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zza(Object obj, long j9, int i9) {
        zzg.zza(obj, j9, i9);
    }

    public static void zza(Object obj, long j9, long j10) {
        zzg.zza(obj, j9, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int iZza = zza(obj, j10);
        int i9 = ((~((int) j9)) & 3) << 3;
        zza(obj, j10, ((255 & b9) << i9) | (iZza & (~(255 << i9))));
    }

    public static void zza(Object obj, long j9, boolean z8) {
        zzg.zza(obj, j9, z8);
    }

    public static void zza(Object obj, long j9, float f9) {
        zzg.zza(obj, j9, f9);
    }

    public static void zza(Object obj, long j9, double d9) {
        zzg.zza(obj, j9, d9);
    }

    public static void zza(Object obj, long j9, Object obj2) {
        zzg.zza.putObject(obj, j9, obj2);
    }

    public static byte zza(byte[] bArr, long j9) {
        return zzg.zza(bArr, zzj + j9);
    }

    public static void zza(byte[] bArr, long j9, byte b9) {
        zzg.zza((Object) bArr, zzj + j9, b9);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zze(Object obj, long j9, boolean z8) {
        zzd(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }

    private static boolean zzd(Class<?> cls) {
        if (!zzgj.zza()) {
            return false;
        }
        try {
            Class<?> cls2 = zzd;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j9, byte b9) {
        long j10 = (-4) & j9;
        int i9 = (((int) j9) & 3) << 3;
        zza(obj, j10, ((255 & b9) << i9) | (zza(obj, j10) & (~(255 << i9))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j9, boolean z8) {
        zzc(obj, j9, z8 ? (byte) 1 : (byte) 0);
    }
}
