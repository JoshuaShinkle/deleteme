package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class zzdw {
    public static void zza(boolean z8, @NullableDecl Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void zzb(boolean z8, @NullableDecl Object obj) {
        if (!z8) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    @NonNullDecl
    public static <T> T zza(@NonNullDecl T t8) {
        t8.getClass();
        return t8;
    }

    public static int zzb(int i9, int i10) {
        if (i9 < 0 || i9 > i10) {
            throw new IndexOutOfBoundsException(zza(i9, i10, FirebaseAnalytics.Param.INDEX));
        }
        return i9;
    }

    @NonNullDecl
    public static <T> T zza(@NonNullDecl T t8, @NullableDecl Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static int zza(int i9, int i10) {
        String strZza;
        if (i9 >= 0 && i9 < i10) {
            return i9;
        }
        if (i9 < 0) {
            strZza = zzdy.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i9));
        } else {
            if (i10 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(i10);
                throw new IllegalArgumentException(sb.toString());
            }
            strZza = zzdy.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        throw new IndexOutOfBoundsException(strZza);
    }

    private static String zza(int i9, int i10, @NullableDecl String str) {
        if (i9 < 0) {
            return zzdy.zza("%s (%s) must not be negative", str, Integer.valueOf(i9));
        }
        if (i10 >= 0) {
            return zzdy.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(i10);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void zza(int i9, int i10, int i11) {
        String strZza;
        if (i9 < 0 || i10 < i9 || i10 > i11) {
            if (i9 >= 0 && i9 <= i11) {
                strZza = (i10 < 0 || i10 > i11) ? zza(i10, i11, "end index") : zzdy.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i10), Integer.valueOf(i9));
            } else {
                strZza = zza(i9, i11, "start index");
            }
            throw new IndexOutOfBoundsException(strZza);
        }
    }
}
