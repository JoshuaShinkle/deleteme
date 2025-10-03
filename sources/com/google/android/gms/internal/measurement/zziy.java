package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zziy<K, V> {
    public static <K, V> void zza(zzhf zzhfVar, zzix<K, V> zzixVar, K k9, V v8) {
        zzho.zza(zzhfVar, zzixVar.zza, 1, k9);
        zzho.zza(zzhfVar, zzixVar.zzc, 2, v8);
    }

    public static <K, V> int zza(zzix<K, V> zzixVar, K k9, V v8) {
        return zzho.zza(zzixVar.zza, 1, k9) + zzho.zza(zzixVar.zzc, 2, v8);
    }
}
