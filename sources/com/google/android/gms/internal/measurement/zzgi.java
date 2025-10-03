package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
final class zzgi {
    public static int zza(byte[] bArr, int i9, zzgl zzglVar) {
        int i10 = i9 + 1;
        byte b9 = bArr[i9];
        if (b9 < 0) {
            return zza(b9, bArr, i10, zzglVar);
        }
        zzglVar.zza = b9;
        return i10;
    }

    public static int zzb(byte[] bArr, int i9, zzgl zzglVar) {
        int i10 = i9 + 1;
        long j9 = bArr[i9];
        if (j9 >= 0) {
            zzglVar.zzb = j9;
            return i10;
        }
        int i11 = i10 + 1;
        byte b9 = bArr[i10];
        long j10 = (j9 & 127) | ((b9 & Ascii.DEL) << 7);
        int i12 = 7;
        while (b9 < 0) {
            int i13 = i11 + 1;
            i12 += 7;
            j10 |= (r10 & Ascii.DEL) << i12;
            b9 = bArr[i11];
            i11 = i13;
        }
        zzglVar.zzb = j10;
        return i11;
    }

    public static double zzc(byte[] bArr, int i9) {
        return Double.longBitsToDouble(zzb(bArr, i9));
    }

    public static float zzd(byte[] bArr, int i9) {
        return Float.intBitsToFloat(zza(bArr, i9));
    }

    public static int zze(byte[] bArr, int i9, zzgl zzglVar) {
        int iZza = zza(bArr, i9, zzglVar);
        int i10 = zzglVar.zza;
        if (i10 < 0) {
            throw zzig.zzb();
        }
        if (i10 > bArr.length - iZza) {
            throw zzig.zza();
        }
        if (i10 == 0) {
            zzglVar.zzc = zzgm.zza;
            return iZza;
        }
        zzglVar.zzc = zzgm.zza(bArr, iZza, i10);
        return iZza + i10;
    }

    public static int zzc(byte[] bArr, int i9, zzgl zzglVar) {
        int iZza = zza(bArr, i9, zzglVar);
        int i10 = zzglVar.zza;
        if (i10 < 0) {
            throw zzig.zzb();
        }
        if (i10 == 0) {
            zzglVar.zzc = "";
            return iZza;
        }
        zzglVar.zzc = new String(bArr, iZza, i10, zzhx.zza);
        return iZza + i10;
    }

    public static int zzd(byte[] bArr, int i9, zzgl zzglVar) {
        int iZza = zza(bArr, i9, zzglVar);
        int i10 = zzglVar.zza;
        if (i10 < 0) {
            throw zzig.zzb();
        }
        if (i10 == 0) {
            zzglVar.zzc = "";
            return iZza;
        }
        zzglVar.zzc = zzkw.zzb(bArr, iZza, i10);
        return iZza + i10;
    }

    public static int zza(int i9, byte[] bArr, int i10, zzgl zzglVar) {
        int i11 = i9 & 127;
        int i12 = i10 + 1;
        byte b9 = bArr[i10];
        if (b9 >= 0) {
            zzglVar.zza = i11 | (b9 << 7);
            return i12;
        }
        int i13 = i11 | ((b9 & Ascii.DEL) << 7);
        int i14 = i12 + 1;
        byte b10 = bArr[i12];
        if (b10 >= 0) {
            zzglVar.zza = i13 | (b10 << Ascii.f15390SO);
            return i14;
        }
        int i15 = i13 | ((b10 & Ascii.DEL) << 14);
        int i16 = i14 + 1;
        byte b11 = bArr[i14];
        if (b11 >= 0) {
            zzglVar.zza = i15 | (b11 << Ascii.NAK);
            return i16;
        }
        int i17 = i15 | ((b11 & Ascii.DEL) << 21);
        int i18 = i16 + 1;
        byte b12 = bArr[i16];
        if (b12 >= 0) {
            zzglVar.zza = i17 | (b12 << Ascii.f15383FS);
            return i18;
        }
        int i19 = i17 | ((b12 & Ascii.DEL) << 28);
        while (true) {
            int i20 = i18 + 1;
            if (bArr[i18] >= 0) {
                zzglVar.zza = i19;
                return i20;
            }
            i18 = i20;
        }
    }

    public static long zzb(byte[] bArr, int i9) {
        return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
    }

    public static int zza(byte[] bArr, int i9) {
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    public static int zza(zzjv zzjvVar, byte[] bArr, int i9, int i10, zzgl zzglVar) {
        int iZza = i9 + 1;
        int i11 = bArr[i9];
        if (i11 < 0) {
            iZza = zza(i11, bArr, iZza, zzglVar);
            i11 = zzglVar.zza;
        }
        int i12 = iZza;
        if (i11 >= 0 && i11 <= i10 - i12) {
            Object objZza = zzjvVar.zza();
            int i13 = i11 + i12;
            zzjvVar.zza(objZza, bArr, i12, i13, zzglVar);
            zzjvVar.zzc(objZza);
            zzglVar.zzc = objZza;
            return i13;
        }
        throw zzig.zza();
    }

    public static int zza(zzjv zzjvVar, byte[] bArr, int i9, int i10, int i11, zzgl zzglVar) {
        zzjk zzjkVar = (zzjk) zzjvVar;
        Object objZza = zzjkVar.zza();
        int iZza = zzjkVar.zza((zzjk) objZza, bArr, i9, i10, i11, zzglVar);
        zzjkVar.zzc((zzjk) objZza);
        zzglVar.zzc = objZza;
        return iZza;
    }

    public static int zza(int i9, byte[] bArr, int i10, int i11, zzid<?> zzidVar, zzgl zzglVar) {
        zzhy zzhyVar = (zzhy) zzidVar;
        int iZza = zza(bArr, i10, zzglVar);
        zzhyVar.zzd(zzglVar.zza);
        while (iZza < i11) {
            int iZza2 = zza(bArr, iZza, zzglVar);
            if (i9 != zzglVar.zza) {
                break;
            }
            iZza = zza(bArr, iZza2, zzglVar);
            zzhyVar.zzd(zzglVar.zza);
        }
        return iZza;
    }

    public static int zza(byte[] bArr, int i9, zzid<?> zzidVar, zzgl zzglVar) {
        zzhy zzhyVar = (zzhy) zzidVar;
        int iZza = zza(bArr, i9, zzglVar);
        int i10 = zzglVar.zza + iZza;
        while (iZza < i10) {
            iZza = zza(bArr, iZza, zzglVar);
            zzhyVar.zzd(zzglVar.zza);
        }
        if (iZza == i10) {
            return iZza;
        }
        throw zzig.zza();
    }

    public static int zza(zzjv<?> zzjvVar, int i9, byte[] bArr, int i10, int i11, zzid<?> zzidVar, zzgl zzglVar) {
        int iZza = zza(zzjvVar, bArr, i10, i11, zzglVar);
        zzidVar.add(zzglVar.zzc);
        while (iZza < i11) {
            int iZza2 = zza(bArr, iZza, zzglVar);
            if (i9 != zzglVar.zza) {
                break;
            }
            iZza = zza(zzjvVar, bArr, iZza2, i11, zzglVar);
            zzidVar.add(zzglVar.zzc);
        }
        return iZza;
    }

    public static int zza(int i9, byte[] bArr, int i10, int i11, zzkq zzkqVar, zzgl zzglVar) {
        if ((i9 >>> 3) == 0) {
            throw zzig.zzd();
        }
        int i12 = i9 & 7;
        if (i12 == 0) {
            int iZzb = zzb(bArr, i10, zzglVar);
            zzkqVar.zza(i9, Long.valueOf(zzglVar.zzb));
            return iZzb;
        }
        if (i12 == 1) {
            zzkqVar.zza(i9, Long.valueOf(zzb(bArr, i10)));
            return i10 + 8;
        }
        if (i12 == 2) {
            int iZza = zza(bArr, i10, zzglVar);
            int i13 = zzglVar.zza;
            if (i13 >= 0) {
                if (i13 > bArr.length - iZza) {
                    throw zzig.zza();
                }
                if (i13 == 0) {
                    zzkqVar.zza(i9, zzgm.zza);
                } else {
                    zzkqVar.zza(i9, zzgm.zza(bArr, iZza, i13));
                }
                return iZza + i13;
            }
            throw zzig.zzb();
        }
        if (i12 != 3) {
            if (i12 == 5) {
                zzkqVar.zza(i9, Integer.valueOf(zza(bArr, i10)));
                return i10 + 4;
            }
            throw zzig.zzd();
        }
        zzkq zzkqVarZzb = zzkq.zzb();
        int i14 = (i9 & (-8)) | 4;
        int i15 = 0;
        while (true) {
            if (i10 >= i11) {
                break;
            }
            int iZza2 = zza(bArr, i10, zzglVar);
            int i16 = zzglVar.zza;
            i15 = i16;
            if (i16 == i14) {
                i10 = iZza2;
                break;
            }
            int iZza3 = zza(i15, bArr, iZza2, i11, zzkqVarZzb, zzglVar);
            i15 = i16;
            i10 = iZza3;
        }
        if (i10 <= i11 && i15 == i14) {
            zzkqVar.zza(i9, zzkqVarZzb);
            return i10;
        }
        throw zzig.zzg();
    }

    public static int zza(int i9, byte[] bArr, int i10, int i11, zzgl zzglVar) {
        if ((i9 >>> 3) == 0) {
            throw zzig.zzd();
        }
        int i12 = i9 & 7;
        if (i12 == 0) {
            return zzb(bArr, i10, zzglVar);
        }
        if (i12 == 1) {
            return i10 + 8;
        }
        if (i12 == 2) {
            return zza(bArr, i10, zzglVar) + zzglVar.zza;
        }
        if (i12 != 3) {
            if (i12 == 5) {
                return i10 + 4;
            }
            throw zzig.zzd();
        }
        int i13 = (i9 & (-8)) | 4;
        int i14 = 0;
        while (i10 < i11) {
            i10 = zza(bArr, i10, zzglVar);
            i14 = zzglVar.zza;
            if (i14 == i13) {
                break;
            }
            i10 = zza(i14, bArr, i10, i11, zzglVar);
        }
        if (i10 > i11 || i14 != i13) {
            throw zzig.zzg();
        }
        return i10;
    }
}
