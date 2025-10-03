package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
final class zzao {
    public static int zza(byte[] bArr, int i9, zzan zzanVar) {
        int iZzj = zzj(bArr, i9, zzanVar);
        int i10 = zzanVar.zza;
        if (i10 < 0) {
            throw zzci.zzd();
        }
        if (i10 > bArr.length - iZzj) {
            throw zzci.zzg();
        }
        if (i10 == 0) {
            zzanVar.zzc = zzba.zzb;
            return iZzj;
        }
        zzanVar.zzc = zzba.zzl(bArr, iZzj, i10);
        return iZzj + i10;
    }

    public static int zzb(byte[] bArr, int i9) {
        int i10 = bArr[i9] & UnsignedBytes.MAX_VALUE;
        int i11 = bArr[i9 + 1] & UnsignedBytes.MAX_VALUE;
        int i12 = bArr[i9 + 2] & UnsignedBytes.MAX_VALUE;
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (i11 << 8) | i10 | (i12 << 16);
    }

    public static int zzc(zzdp zzdpVar, byte[] bArr, int i9, int i10, int i11, zzan zzanVar) {
        Object objZze = zzdpVar.zze();
        int iZzn = zzn(objZze, zzdpVar, bArr, i9, i10, i11, zzanVar);
        zzdpVar.zzf(objZze);
        zzanVar.zzc = objZze;
        return iZzn;
    }

    public static int zzd(zzdp zzdpVar, byte[] bArr, int i9, int i10, zzan zzanVar) {
        Object objZze = zzdpVar.zze();
        int iZzo = zzo(objZze, zzdpVar, bArr, i9, i10, zzanVar);
        zzdpVar.zzf(objZze);
        zzanVar.zzc = objZze;
        return iZzo;
    }

    public static int zze(zzdp zzdpVar, int i9, byte[] bArr, int i10, int i11, zzcf zzcfVar, zzan zzanVar) {
        int iZzd = zzd(zzdpVar, bArr, i10, i11, zzanVar);
        zzcfVar.add(zzanVar.zzc);
        while (iZzd < i11) {
            int iZzj = zzj(bArr, iZzd, zzanVar);
            if (i9 != zzanVar.zza) {
                break;
            }
            iZzd = zzd(zzdpVar, bArr, iZzj, i11, zzanVar);
            zzcfVar.add(zzanVar.zzc);
        }
        return iZzd;
    }

    public static int zzf(byte[] bArr, int i9, zzcf zzcfVar, zzan zzanVar) {
        zzcc zzccVar = (zzcc) zzcfVar;
        int iZzj = zzj(bArr, i9, zzanVar);
        int i10 = zzanVar.zza + iZzj;
        while (iZzj < i10) {
            iZzj = zzj(bArr, iZzj, zzanVar);
            zzccVar.zzf(zzanVar.zza);
        }
        if (iZzj == i10) {
            return iZzj;
        }
        throw zzci.zzg();
    }

    public static int zzg(byte[] bArr, int i9, zzan zzanVar) throws zzci {
        int iZzj = zzj(bArr, i9, zzanVar);
        int i10 = zzanVar.zza;
        if (i10 < 0) {
            throw zzci.zzd();
        }
        if (i10 == 0) {
            zzanVar.zzc = "";
            return iZzj;
        }
        zzanVar.zzc = new String(bArr, iZzj, i10, zzcg.zzb);
        return iZzj + i10;
    }

    public static int zzh(byte[] bArr, int i9, zzan zzanVar) throws zzci {
        int iZzj = zzj(bArr, i9, zzanVar);
        int i10 = zzanVar.zza;
        if (i10 < 0) {
            throw zzci.zzd();
        }
        if (i10 == 0) {
            zzanVar.zzc = "";
            return iZzj;
        }
        int i11 = zzev.zza;
        int length = bArr.length;
        if ((((length - iZzj) - i10) | iZzj | i10) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(iZzj), Integer.valueOf(i10)));
        }
        int i12 = iZzj + i10;
        char[] cArr = new char[i10];
        int i13 = 0;
        while (iZzj < i12) {
            byte b9 = bArr[iZzj];
            if (!zzer.zzd(b9)) {
                break;
            }
            iZzj++;
            cArr[i13] = (char) b9;
            i13++;
        }
        while (iZzj < i12) {
            int i14 = iZzj + 1;
            byte b10 = bArr[iZzj];
            if (zzer.zzd(b10)) {
                int i15 = i13 + 1;
                cArr[i13] = (char) b10;
                iZzj = i14;
                while (true) {
                    i13 = i15;
                    if (iZzj < i12) {
                        byte b11 = bArr[iZzj];
                        if (zzer.zzd(b11)) {
                            iZzj++;
                            i15 = i13 + 1;
                            cArr[i13] = (char) b11;
                        }
                    }
                }
            } else if (b10 < -32) {
                if (i14 >= i12) {
                    throw zzci.zzc();
                }
                zzer.zzc(b10, bArr[i14], cArr, i13);
                iZzj = i14 + 1;
                i13++;
            } else if (b10 < -16) {
                if (i14 >= i12 - 1) {
                    throw zzci.zzc();
                }
                int i16 = i14 + 1;
                zzer.zzb(b10, bArr[i14], bArr[i16], cArr, i13);
                iZzj = i16 + 1;
                i13++;
            } else {
                if (i14 >= i12 - 2) {
                    throw zzci.zzc();
                }
                int i17 = i14 + 1;
                byte b12 = bArr[i14];
                int i18 = i17 + 1;
                zzer.zza(b10, b12, bArr[i17], bArr[i18], cArr, i13);
                i13 += 2;
                iZzj = i18 + 1;
            }
        }
        zzanVar.zzc = new String(cArr, 0, i13);
        return i12;
    }

    public static int zzi(int i9, byte[] bArr, int i10, int i11, zzeh zzehVar, zzan zzanVar) {
        if ((i9 >>> 3) == 0) {
            throw zzci.zzb();
        }
        int i12 = i9 & 7;
        if (i12 == 0) {
            int iZzm = zzm(bArr, i10, zzanVar);
            zzehVar.zzj(i9, Long.valueOf(zzanVar.zzb));
            return iZzm;
        }
        if (i12 == 1) {
            zzehVar.zzj(i9, Long.valueOf(zzp(bArr, i10)));
            return i10 + 8;
        }
        if (i12 == 2) {
            int iZzj = zzj(bArr, i10, zzanVar);
            int i13 = zzanVar.zza;
            if (i13 < 0) {
                throw zzci.zzd();
            }
            if (i13 > bArr.length - iZzj) {
                throw zzci.zzg();
            }
            if (i13 == 0) {
                zzehVar.zzj(i9, zzba.zzb);
            } else {
                zzehVar.zzj(i9, zzba.zzl(bArr, iZzj, i13));
            }
            return iZzj + i13;
        }
        if (i12 != 3) {
            if (i12 != 5) {
                throw zzci.zzb();
            }
            zzehVar.zzj(i9, Integer.valueOf(zzb(bArr, i10)));
            return i10 + 4;
        }
        int i14 = (i9 & (-8)) | 4;
        zzeh zzehVarZzf = zzeh.zzf();
        int i15 = 0;
        while (true) {
            if (i10 >= i11) {
                break;
            }
            int iZzj2 = zzj(bArr, i10, zzanVar);
            int i16 = zzanVar.zza;
            i15 = i16;
            if (i16 == i14) {
                i10 = iZzj2;
                break;
            }
            int iZzi = zzi(i15, bArr, iZzj2, i11, zzehVarZzf, zzanVar);
            i15 = i16;
            i10 = iZzi;
        }
        if (i10 > i11 || i15 != i14) {
            throw zzci.zze();
        }
        zzehVar.zzj(i9, zzehVarZzf);
        return i10;
    }

    public static int zzj(byte[] bArr, int i9, zzan zzanVar) {
        int i10 = i9 + 1;
        byte b9 = bArr[i9];
        if (b9 < 0) {
            return zzk(b9, bArr, i10, zzanVar);
        }
        zzanVar.zza = b9;
        return i10;
    }

    public static int zzk(int i9, byte[] bArr, int i10, zzan zzanVar) {
        byte b9 = bArr[i10];
        int i11 = i10 + 1;
        int i12 = i9 & 127;
        if (b9 >= 0) {
            zzanVar.zza = i12 | (b9 << 7);
            return i11;
        }
        int i13 = i12 | ((b9 & Ascii.DEL) << 7);
        int i14 = i11 + 1;
        byte b10 = bArr[i11];
        if (b10 >= 0) {
            zzanVar.zza = i13 | (b10 << Ascii.f15390SO);
            return i14;
        }
        int i15 = i13 | ((b10 & Ascii.DEL) << 14);
        int i16 = i14 + 1;
        byte b11 = bArr[i14];
        if (b11 >= 0) {
            zzanVar.zza = i15 | (b11 << Ascii.NAK);
            return i16;
        }
        int i17 = i15 | ((b11 & Ascii.DEL) << 21);
        int i18 = i16 + 1;
        byte b12 = bArr[i16];
        if (b12 >= 0) {
            zzanVar.zza = i17 | (b12 << Ascii.f15383FS);
            return i18;
        }
        int i19 = i17 | ((b12 & Ascii.DEL) << 28);
        while (true) {
            int i20 = i18 + 1;
            if (bArr[i18] >= 0) {
                zzanVar.zza = i19;
                return i20;
            }
            i18 = i20;
        }
    }

    public static int zzl(int i9, byte[] bArr, int i10, int i11, zzcf zzcfVar, zzan zzanVar) {
        zzcc zzccVar = (zzcc) zzcfVar;
        int iZzj = zzj(bArr, i10, zzanVar);
        zzccVar.zzf(zzanVar.zza);
        while (iZzj < i11) {
            int iZzj2 = zzj(bArr, iZzj, zzanVar);
            if (i9 != zzanVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzanVar);
            zzccVar.zzf(zzanVar.zza);
        }
        return iZzj;
    }

    public static int zzm(byte[] bArr, int i9, zzan zzanVar) {
        long j9 = bArr[i9];
        int i10 = i9 + 1;
        if (j9 >= 0) {
            zzanVar.zzb = j9;
            return i10;
        }
        int i11 = i10 + 1;
        byte b9 = bArr[i10];
        long j10 = (j9 & 127) | ((b9 & Ascii.DEL) << 7);
        int i12 = 7;
        while (b9 < 0) {
            int i13 = i11 + 1;
            byte b10 = bArr[i11];
            i12 += 7;
            j10 |= (b10 & Ascii.DEL) << i12;
            i11 = i13;
            b9 = b10;
        }
        zzanVar.zzb = j10;
        return i11;
    }

    public static int zzn(Object obj, zzdp zzdpVar, byte[] bArr, int i9, int i10, int i11, zzan zzanVar) {
        int iZzc = ((zzdi) zzdpVar).zzc(obj, bArr, i9, i10, i11, zzanVar);
        zzanVar.zzc = obj;
        return iZzc;
    }

    public static int zzo(Object obj, zzdp zzdpVar, byte[] bArr, int i9, int i10, zzan zzanVar) {
        int iZzk = i9 + 1;
        int i11 = bArr[i9];
        if (i11 < 0) {
            iZzk = zzk(i11, bArr, iZzk, zzanVar);
            i11 = zzanVar.zza;
        }
        int i12 = iZzk;
        if (i11 < 0 || i11 > i10 - i12) {
            throw zzci.zzg();
        }
        int i13 = i11 + i12;
        zzdpVar.zzh(obj, bArr, i12, i13, zzanVar);
        zzanVar.zzc = obj;
        return i13;
    }

    public static long zzp(byte[] bArr, int i9) {
        return (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48) | ((bArr[i9 + 7] & 255) << 56);
    }
}
