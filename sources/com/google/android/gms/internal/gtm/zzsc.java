package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public final class zzsc<K, V> {
    public static <K, V> void zza(zzqj zzqjVar, zzsd<K, V> zzsdVar, K k9, V v8) {
        zzqt.zza(zzqjVar, zzsdVar.zzbcp, 1, k9);
        zzqt.zza(zzqjVar, zzsdVar.zzbcr, 2, v8);
    }

    public static <K, V> int zza(zzsd<K, V> zzsdVar, K k9, V v8) {
        return zzqt.zza(zzsdVar.zzbcp, 1, k9) + zzqt.zza(zzsdVar.zzbcr, 2, v8);
    }
}
