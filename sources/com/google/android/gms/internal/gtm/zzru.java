package com.google.android.gms.internal.gtm;

import java.util.List;

/* loaded from: classes2.dex */
abstract class zzru {
    private static final zzru zzbch;
    private static final zzru zzbci;

    static {
        zzrv zzrvVar = null;
        zzbch = new zzrw();
        zzbci = new zzrx();
    }

    private zzru() {
    }

    public static zzru zzqc() {
        return zzbch;
    }

    public static zzru zzqd() {
        return zzbci;
    }

    public abstract <L> List<L> zza(Object obj, long j9);

    public abstract <L> void zza(Object obj, Object obj2, long j9);

    public abstract void zzb(Object obj, long j9);
}
