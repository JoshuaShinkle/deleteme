package com.google.android.gms.internal.gtm;

import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzua {
    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b9, char[] cArr, int i9) {
        cArr[i9] = (char) b9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzd(byte b9) {
        return b9 >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zze(byte b9) {
        return b9 < -32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzf(byte b9) {
        return b9 < -16;
    }

    private static boolean zzg(byte b9) {
        return b9 > -65;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b9, byte b10, char[] cArr, int i9) throws zzrk {
        if (b9 < -62 || zzg(b10)) {
            throw zzrk.zzpw();
        }
        cArr[i9] = (char) (((b9 & Ascii.f15392US) << 6) | (b10 & 63));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b9, byte b10, byte b11, char[] cArr, int i9) throws zzrk {
        if (!zzg(b10) && ((b9 != -32 || b10 >= -96) && ((b9 != -19 || b10 < -96) && !zzg(b11)))) {
            cArr[i9] = (char) (((b9 & Ascii.f15389SI) << 12) | ((b10 & 63) << 6) | (b11 & 63));
            return;
        }
        throw zzrk.zzpw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b9, byte b10, byte b11, byte b12, char[] cArr, int i9) throws zzrk {
        if (!zzg(b10) && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0 && !zzg(b11) && !zzg(b12)) {
            int i10 = ((b9 & 7) << 18) | ((b10 & 63) << 12) | ((b11 & 63) << 6) | (b12 & 63);
            cArr[i9] = (char) ((i10 >>> 10) + 55232);
            cArr[i9 + 1] = (char) ((i10 & 1023) + 56320);
            return;
        }
        throw zzrk.zzpw();
    }
}
