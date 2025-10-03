package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class zzha extends zzgy {
    private final byte[] zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    private zzha(byte[] bArr, int i9, int i10, boolean z8) {
        super();
        this.zzk = Integer.MAX_VALUE;
        this.zzd = bArr;
        this.zzf = i10 + i9;
        this.zzh = i9;
        this.zzi = i9;
        this.zze = z8;
    }

    private final byte zzaa() throws zzig {
        int i9 = this.zzh;
        if (i9 == this.zzf) {
            throw zzig.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i9 + 1;
        return bArr[i9];
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
    
        if (r2[r3] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzv() {
        int i9;
        int i10 = this.zzh;
        int i11 = this.zzf;
        if (i11 != i10) {
            byte[] bArr = this.zzd;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.zzh = i12;
                return b9;
            }
            if (i11 - i12 >= 9) {
                int i13 = i12 + 1;
                int i14 = b9 ^ (bArr[i12] << 7);
                if (i14 < 0) {
                    i9 = i14 ^ (-128);
                } else {
                    int i15 = i13 + 1;
                    int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                    if (i16 >= 0) {
                        i9 = i16 ^ 16256;
                    } else {
                        i13 = i15 + 1;
                        int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                        if (i17 < 0) {
                            i9 = i17 ^ (-2080896);
                        } else {
                            i15 = i13 + 1;
                            byte b10 = bArr[i13];
                            i9 = (i17 ^ (b10 << Ascii.f15383FS)) ^ 266354560;
                            if (b10 < 0) {
                                i13 = i15 + 1;
                                if (bArr[i15] < 0) {
                                    i15 = i13 + 1;
                                    if (bArr[i13] < 0) {
                                        i13 = i15 + 1;
                                        if (bArr[i15] < 0) {
                                            i15 = i13 + 1;
                                            if (bArr[i13] < 0) {
                                                i13 = i15 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i13 = i15;
                }
                this.zzh = i13;
                return i9;
            }
        }
        return (int) zzs();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
    
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long zzw() {
        long j9;
        long j10;
        long j11;
        int i9;
        int i10 = this.zzh;
        int i11 = this.zzf;
        if (i11 != i10) {
            byte[] bArr = this.zzd;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.zzh = i12;
                return b9;
            }
            if (i11 - i12 >= 9) {
                int i13 = i12 + 1;
                int i14 = b9 ^ (bArr[i12] << 7);
                if (i14 >= 0) {
                    int i15 = i13 + 1;
                    int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                    if (i16 >= 0) {
                        i13 = i15;
                        j9 = i16 ^ 16256;
                    } else {
                        i13 = i15 + 1;
                        int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                        if (i17 < 0) {
                            i9 = i17 ^ (-2080896);
                        } else {
                            long j12 = i17;
                            int i18 = i13 + 1;
                            long j13 = j12 ^ (bArr[i13] << 28);
                            if (j13 >= 0) {
                                j11 = 266354560;
                            } else {
                                i13 = i18 + 1;
                                long j14 = j13 ^ (bArr[i18] << 35);
                                if (j14 < 0) {
                                    j10 = -34093383808L;
                                } else {
                                    i18 = i13 + 1;
                                    j13 = j14 ^ (bArr[i13] << 42);
                                    if (j13 >= 0) {
                                        j11 = 4363953127296L;
                                    } else {
                                        i13 = i18 + 1;
                                        j14 = j13 ^ (bArr[i18] << 49);
                                        if (j14 < 0) {
                                            j10 = -558586000294016L;
                                        } else {
                                            int i19 = i13 + 1;
                                            long j15 = (j14 ^ (bArr[i13] << 56)) ^ 71499008037633920L;
                                            i13 = j15 < 0 ? i19 + 1 : i19;
                                            j9 = j15;
                                        }
                                    }
                                }
                                j9 = j14 ^ j10;
                            }
                            j9 = j13 ^ j11;
                            i13 = i18;
                        }
                    }
                    this.zzh = i13;
                    return j9;
                }
                i9 = i14 ^ (-128);
                j9 = i9;
                this.zzh = i13;
                return j9;
            }
        }
        return zzs();
    }

    private final int zzx() throws zzig {
        int i9 = this.zzh;
        if (this.zzf - i9 < 4) {
            throw zzig.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i9 + 4;
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    private final long zzy() throws zzig {
        int i9 = this.zzh;
        if (this.zzf - i9 < 8) {
            throw zzig.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i9 + 8;
        return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
    }

    private final void zzz() {
        int i9 = this.zzf + this.zzg;
        this.zzf = i9;
        int i10 = i9 - this.zzi;
        int i11 = this.zzk;
        if (i10 <= i11) {
            this.zzg = 0;
            return;
        }
        int i12 = i10 - i11;
        this.zzg = i12;
        this.zzf = i9 - i12;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zza() throws zzig {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        int iZzv = zzv();
        this.zzj = iZzv;
        if ((iZzv >>> 3) != 0) {
            return iZzv;
        }
        throw zzig.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean zzb(int i9) throws zzig {
        int iZza;
        int i10 = i9 & 7;
        int i11 = 0;
        if (i10 == 0) {
            if (this.zzf - this.zzh < 10) {
                while (i11 < 10) {
                    if (zzaa() < 0) {
                        i11++;
                    }
                }
                throw zzig.zzc();
            }
            while (i11 < 10) {
                byte[] bArr = this.zzd;
                int i12 = this.zzh;
                this.zzh = i12 + 1;
                if (bArr[i12] < 0) {
                    i11++;
                }
            }
            throw zzig.zzc();
            return true;
        }
        if (i10 == 1) {
            zzf(8);
            return true;
        }
        if (i10 == 2) {
            zzf(zzv());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw zzig.zzf();
            }
            zzf(4);
            return true;
        }
        do {
            iZza = zza();
            if (iZza == 0) {
                break;
            }
        } while (zzb(iZza));
        zza(((i9 >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final float zzc() {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzd() {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zze() {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzf() {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzg() {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzh() {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean zzi() {
        return zzw() != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final String zzj() throws zzig {
        int iZzv = zzv();
        if (iZzv > 0) {
            int i9 = this.zzf;
            int i10 = this.zzh;
            if (iZzv <= i9 - i10) {
                String str = new String(this.zzd, i10, iZzv, zzhx.zza);
                this.zzh += iZzv;
                return str;
            }
        }
        if (iZzv == 0) {
            return "";
        }
        if (iZzv < 0) {
            throw zzig.zzb();
        }
        throw zzig.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final String zzk() throws zzig {
        int iZzv = zzv();
        if (iZzv > 0) {
            int i9 = this.zzf;
            int i10 = this.zzh;
            if (iZzv <= i9 - i10) {
                String strZzb = zzkw.zzb(this.zzd, i10, iZzv);
                this.zzh += iZzv;
                return strZzb;
            }
        }
        if (iZzv == 0) {
            return "";
        }
        if (iZzv <= 0) {
            throw zzig.zzb();
        }
        throw zzig.zza();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    @Override // com.google.android.gms.internal.measurement.zzgy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzgm zzl() throws zzig {
        byte[] bArrCopyOfRange;
        int iZzv = zzv();
        if (iZzv > 0) {
            int i9 = this.zzf;
            int i10 = this.zzh;
            if (iZzv <= i9 - i10) {
                zzgm zzgmVarZza = zzgm.zza(this.zzd, i10, iZzv);
                this.zzh += iZzv;
                return zzgmVarZza;
            }
        }
        if (iZzv == 0) {
            return zzgm.zza;
        }
        if (iZzv > 0) {
            int i11 = this.zzf;
            int i12 = this.zzh;
            if (iZzv <= i11 - i12) {
                int i13 = iZzv + i12;
                this.zzh = i13;
                bArrCopyOfRange = Arrays.copyOfRange(this.zzd, i12, i13);
            } else {
                if (iZzv > 0) {
                    throw zzig.zza();
                }
                if (iZzv != 0) {
                    throw zzig.zzb();
                }
                bArrCopyOfRange = zzhx.zzb;
            }
        }
        return zzgm.zza(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzm() {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzn() {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzo() {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzp() {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzq() {
        return zzgy.zze(zzv());
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzr() {
        return zzgy.zza(zzw());
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final long zzs() throws zzig {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((zzaa() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw zzig.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean zzt() {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzu() {
        return this.zzh - this.zzi;
    }

    private final void zzf(int i9) throws zzig {
        if (i9 >= 0) {
            int i10 = this.zzf;
            int i11 = this.zzh;
            if (i9 <= i10 - i11) {
                this.zzh = i11 + i9;
                return;
            }
        }
        if (i9 >= 0) {
            throw zzig.zza();
        }
        throw zzig.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final int zzc(int i9) throws zzig {
        if (i9 < 0) {
            throw zzig.zzb();
        }
        int iZzu = i9 + zzu();
        int i10 = this.zzk;
        if (iZzu > i10) {
            throw zzig.zza();
        }
        this.zzk = iZzu;
        zzz();
        return i10;
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zzd(int i9) {
        this.zzk = i9;
        zzz();
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final void zza(int i9) throws zzig {
        if (this.zzj != i9) {
            throw zzig.zze();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final double zzb() {
        return Double.longBitsToDouble(zzy());
    }
}
