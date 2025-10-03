package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzfu extends zzfx {
    public static int zza(int i9, int i10, int i11) {
        if (i10 <= 1073741823) {
            return Math.min(Math.max(i9, i10), 1073741823);
        }
        throw new IllegalArgumentException(zzdy.zza("min (%s) must be less than or equal to max (%s)", Integer.valueOf(i10), 1073741823));
    }
}
