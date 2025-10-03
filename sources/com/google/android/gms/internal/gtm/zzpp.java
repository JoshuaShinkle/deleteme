package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzpp {
    private static final Class<?> zzavt = zzcx("libcore.io.Memory");
    private static final boolean zzavu;

    static {
        zzavu = zzcx("org.robolectric.Robolectric") != null;
    }

    private static <T> Class<T> zzcx(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean zzna() {
        return (zzavt == null || zzavu) ? false : true;
    }

    public static Class<?> zznb() {
        return zzavt;
    }
}
