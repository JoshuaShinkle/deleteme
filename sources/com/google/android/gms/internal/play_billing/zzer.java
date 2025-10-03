package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzer {
    public static /* bridge */ /* synthetic */ void zza(byte b9, byte b10, byte b11, byte b12, char[] cArr, int i9) throws zzci {
        if (zze(b10) || (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) != 0 || zze(b11) || zze(b12)) {
            throw zzci.zzc();
        }
        int i10 = ((b9 & 7) << 18) | ((b10 & 63) << 12) | ((b11 & 63) << 6) | (b12 & 63);
        cArr[i9] = (char) ((i10 >>> 10) + 55232);
        cArr[i9 + 1] = (char) ((i10 & 1023) + 56320);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0016 A[PHI: r2
      0x0016: PHI (r2v3 byte) = (r2v2 byte), (r2v9 byte) binds: [B:9:0x0011, B:11:0x0015] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* bridge */ /* synthetic */ void zzb(byte b9, byte b10, byte b11, char[] cArr, int i9) throws zzci {
        if (!zze(b10)) {
            if (b9 != -32) {
                if (b9 != -19) {
                    if (!zze(b11)) {
                        cArr[i9] = (char) (((b9 & Ascii.f15389SI) << 12) | ((b10 & 63) << 6) | (b11 & 63));
                        return;
                    }
                } else if (b10 < -96) {
                    b9 = -19;
                    if (!zze(b11)) {
                    }
                }
            } else if (b10 >= -96) {
                b9 = -32;
                if (b9 != -19) {
                }
            }
        }
        throw zzci.zzc();
    }

    public static /* bridge */ /* synthetic */ void zzc(byte b9, byte b10, char[] cArr, int i9) throws zzci {
        if (b9 < -62 || zze(b10)) {
            throw zzci.zzc();
        }
        cArr[i9] = (char) (((b9 & Ascii.f15392US) << 6) | (b10 & 63));
    }

    public static /* bridge */ /* synthetic */ boolean zzd(byte b9) {
        return b9 >= 0;
    }

    private static boolean zze(byte b9) {
        return b9 > -65;
    }
}
