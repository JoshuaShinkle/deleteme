package com.google.android.gms.analytics;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzch;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzd {
    private static String zza(String str, int i9) {
        if (i9 <= 0) {
            zzch.zzf("index out of range for prefix", str);
            return "";
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11);
        sb.append(str);
        sb.append(i9);
        return sb.toString();
    }

    public static String zzd(int i9) {
        return zza("&cd", i9);
    }

    public static String zze(int i9) {
        return zza("cd", i9);
    }

    public static String zzf(int i9) {
        return zza("&cm", i9);
    }

    public static String zzg(int i9) {
        return zza("cm", i9);
    }

    public static String zzh(int i9) {
        return zza("&pr", i9);
    }

    public static String zzi(int i9) {
        return zza("pr", i9);
    }

    public static String zzj(int i9) {
        return zza("&promo", i9);
    }

    public static String zzk(int i9) {
        return zza("promo", i9);
    }

    public static String zzl(int i9) {
        return zza("pi", i9);
    }

    public static String zzm(int i9) {
        return zza("&il", i9);
    }

    public static String zzn(int i9) {
        return zza("il", i9);
    }

    public static String zzo(int i9) {
        return zza("cd", i9);
    }

    public static String zzp(int i9) {
        return zza("cm", i9);
    }
}
