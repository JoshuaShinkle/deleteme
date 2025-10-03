package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes2.dex */
public final class zzm {
    public static int zza(int i9, int i10, String str) {
        String strZza;
        if (i9 >= 0 && i9 < i10) {
            return i9;
        }
        if (i9 < 0) {
            strZza = zzn.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i9));
        } else {
            if (i10 < 0) {
                throw new IllegalArgumentException("negative size: " + i10);
            }
            strZza = zzn.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        throw new IndexOutOfBoundsException(strZza);
    }

    public static int zzb(int i9, int i10, String str) {
        if (i9 < 0 || i9 > i10) {
            throw new IndexOutOfBoundsException(zze(i9, i10, FirebaseAnalytics.Param.INDEX));
        }
        return i9;
    }

    public static Object zzc(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzd(int i9, int i10, int i11) {
        if (i9 < 0 || i10 < i9 || i10 > i11) {
            throw new IndexOutOfBoundsException((i9 < 0 || i9 > i11) ? zze(i9, i11, "start index") : (i10 < 0 || i10 > i11) ? zze(i10, i11, "end index") : zzn.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i10), Integer.valueOf(i9)));
        }
    }

    private static String zze(int i9, int i10, String str) {
        if (i9 < 0) {
            return zzn.zza("%s (%s) must not be negative", str, Integer.valueOf(i9));
        }
        if (i10 >= 0) {
            return zzn.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        throw new IllegalArgumentException("negative size: " + i10);
    }
}
