package com.google.android.gms.internal.play_billing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzeq {
    static final long zza;
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zzep zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    static {
        boolean z8;
        boolean z9;
        zzep zzepVar;
        Unsafe unsafeZzg = zzg();
        zzc = unsafeZzg;
        int i9 = zzam.zza;
        zzd = Memory.class;
        Class<?> cls = Long.TYPE;
        boolean zZzv = zzv(cls);
        zze = zZzv;
        boolean zZzv2 = zzv(Integer.TYPE);
        zzep zzenVar = null;
        if (unsafeZzg != null) {
            if (zZzv) {
                zzenVar = new zzeo(unsafeZzg);
            } else if (zZzv2) {
                zzenVar = new zzen(unsafeZzg);
            }
        }
        zzf = zzenVar;
        if (zzenVar == null) {
            z8 = false;
        } else {
            try {
                Class<?> cls2 = zzenVar.zza.getClass();
                cls2.getMethod("objectFieldOffset", Field.class);
                cls2.getMethod("getLong", Object.class, cls);
                if (zzB() != null) {
                    z8 = true;
                }
            } catch (Throwable th) {
                zzh(th);
            }
        }
        zzg = z8;
        zzep zzepVar2 = zzf;
        if (zzepVar2 == null) {
            z9 = false;
        } else {
            try {
                Class<?> cls3 = zzepVar2.zza.getClass();
                cls3.getMethod("objectFieldOffset", Field.class);
                cls3.getMethod("arrayBaseOffset", Class.class);
                cls3.getMethod("arrayIndexScale", Class.class);
                Class<?> cls4 = Long.TYPE;
                cls3.getMethod("getInt", Object.class, cls4);
                cls3.getMethod("putInt", Object.class, cls4, Integer.TYPE);
                cls3.getMethod("getLong", Object.class, cls4);
                cls3.getMethod("putLong", Object.class, cls4, cls4);
                cls3.getMethod("getObject", Object.class, cls4);
                cls3.getMethod("putObject", Object.class, cls4, Object.class);
                z9 = true;
            } catch (Throwable th2) {
                zzh(th2);
            }
        }
        zzh = z9;
        zza = zzz(byte[].class);
        zzz(boolean[].class);
        zzA(boolean[].class);
        zzz(int[].class);
        zzA(int[].class);
        zzz(long[].class);
        zzA(long[].class);
        zzz(float[].class);
        zzA(float[].class);
        zzz(double[].class);
        zzA(double[].class);
        zzz(Object[].class);
        zzA(Object[].class);
        Field fieldZzB = zzB();
        if (fieldZzB != null && (zzepVar = zzf) != null) {
            zzepVar.zza.objectFieldOffset(fieldZzB);
        }
        zzb = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private zzeq() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i9 = zzam.zza;
        Field fieldZzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (fieldZzC != null) {
            return fieldZzC;
        }
        Field fieldZzC2 = zzC(Buffer.class, "address");
        if (fieldZzC2 == null || fieldZzC2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzD(Object obj, long j9, byte b9) {
        zzep zzepVar = zzf;
        long j10 = (-4) & j9;
        int i9 = zzepVar.zza.getInt(obj, j10);
        int i10 = ((~((int) j9)) & 3) << 3;
        zzepVar.zza.putInt(obj, j10, ((255 & b9) << i10) | (i9 & (~(255 << i10))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzE(Object obj, long j9, byte b9) {
        zzep zzepVar = zzf;
        long j10 = (-4) & j9;
        int i9 = (((int) j9) & 3) << 3;
        zzepVar.zza.putInt(obj, j10, ((255 & b9) << i9) | (zzepVar.zza.getInt(obj, j10) & (~(255 << i9))));
    }

    public static double zza(Object obj, long j9) {
        return zzf.zza(obj, j9);
    }

    public static float zzb(Object obj, long j9) {
        return zzf.zzb(obj, j9);
    }

    public static int zzc(Object obj, long j9) {
        return zzf.zza.getInt(obj, j9);
    }

    public static long zzd(Object obj, long j9) {
        return zzf.zza.getLong(obj, j9);
    }

    public static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static Object zzf(Object obj, long j9) {
        return zzf.zza.getObject(obj, j9);
    }

    public static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzem());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ void zzh(Throwable th) {
        Logger.getLogger(zzeq.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    public static void zzm(Object obj, long j9, boolean z8) {
        zzf.zzc(obj, j9, z8);
    }

    public static void zzn(byte[] bArr, long j9, byte b9) {
        zzf.zzd(bArr, zza + j9, b9);
    }

    public static void zzo(Object obj, long j9, double d9) {
        zzf.zze(obj, j9, d9);
    }

    public static void zzp(Object obj, long j9, float f9) {
        zzf.zzf(obj, j9, f9);
    }

    public static void zzq(Object obj, long j9, int i9) {
        zzf.zza.putInt(obj, j9, i9);
    }

    public static void zzr(Object obj, long j9, long j10) {
        zzf.zza.putLong(obj, j9, j10);
    }

    public static void zzs(Object obj, long j9, Object obj2) {
        zzf.zza.putObject(obj, j9, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j9) {
        return ((byte) ((zzf.zza.getInt(obj, (-4) & j9) >>> ((int) (((~j9) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j9) {
        return ((byte) ((zzf.zza.getInt(obj, (-4) & j9) >>> ((int) ((j9 & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean zzv(Class cls) {
        int i9 = zzam.zza;
        try {
            Class cls2 = zzd;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
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

    public static boolean zzw(Object obj, long j9) {
        return zzf.zzg(obj, j9);
    }

    public static boolean zzx() {
        return zzh;
    }

    public static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
