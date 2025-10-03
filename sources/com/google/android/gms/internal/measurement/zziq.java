package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes2.dex */
abstract class zziq {
    private static final zziq zza;
    private static final zziq zzb;

    static {
        zzip zzipVar = null;
        zza = new zzis();
        zzb = new zzir();
    }

    private zziq() {
    }

    public static zziq zza() {
        return zza;
    }

    public static zziq zzb() {
        return zzb;
    }

    public abstract <L> List<L> zza(Object obj, long j9);

    public abstract <L> void zza(Object obj, Object obj2, long j9);

    public abstract void zzb(Object obj, long j9);
}
