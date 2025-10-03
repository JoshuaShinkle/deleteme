package com.google.android.gms.internal.gtm;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class zzqg extends zzqe {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzawk;
    private int zzawl;
    private int zzawm;
    private int zzawn;
    private int zzawo;

    private zzqg(byte[] bArr, int i9, int i10, boolean z8) {
        super();
        this.zzawo = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i10 + i9;
        this.pos = i9;
        this.zzawm = i9;
        this.zzawk = z8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
    
        if (r2[r3] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzoa() {
        int i9;
        int i10 = this.pos;
        int i11 = this.limit;
        if (i11 != i10) {
            byte[] bArr = this.buffer;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.pos = i12;
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
                this.pos = i13;
                return i9;
            }
        }
        return (int) zznx();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
    
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long zzob() {
        long j9;
        long j10;
        long j11;
        int i9;
        int i10 = this.pos;
        int i11 = this.limit;
        if (i11 != i10) {
            byte[] bArr = this.buffer;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.pos = i12;
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
                    this.pos = i13;
                    return j9;
                }
                i9 = i14 ^ (-128);
                j9 = i9;
                this.pos = i13;
                return j9;
            }
        }
        return zznx();
    }

    private final int zzoc() throws zzrk {
        int i9 = this.pos;
        if (this.limit - i9 < 4) {
            throw zzrk.zzpp();
        }
        byte[] bArr = this.buffer;
        this.pos = i9 + 4;
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    private final long zzod() throws zzrk {
        int i9 = this.pos;
        if (this.limit - i9 < 8) {
            throw zzrk.zzpp();
        }
        byte[] bArr = this.buffer;
        this.pos = i9 + 8;
        return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
    }

    private final void zzoe() {
        int i9 = this.limit + this.zzawl;
        this.limit = i9;
        int i10 = i9 - this.zzawm;
        int i11 = this.zzawo;
        if (i10 <= i11) {
            this.zzawl = 0;
            return;
        }
        int i12 = i10 - i11;
        this.zzawl = i12;
        this.limit = i9 - i12;
    }

    private final byte zzof() throws zzrk {
        int i9 = this.pos;
        if (i9 == this.limit) {
            throw zzrk.zzpp();
        }
        byte[] bArr = this.buffer;
        this.pos = i9 + 1;
        return bArr[i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final double readDouble() {
        return Double.longBitsToDouble(zzod());
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final float readFloat() {
        return Float.intBitsToFloat(zzoc());
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final String readString() throws zzrk {
        int iZzoa = zzoa();
        if (iZzoa > 0) {
            int i9 = this.limit;
            int i10 = this.pos;
            if (iZzoa <= i9 - i10) {
                String str = new String(this.buffer, i10, iZzoa, zzre.UTF_8);
                this.pos += iZzoa;
                return str;
            }
        }
        if (iZzoa == 0) {
            return "";
        }
        if (iZzoa < 0) {
            throw zzrk.zzpq();
        }
        throw zzrk.zzpp();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final <T extends zzsk> T zza(zzsu<T> zzsuVar, zzqp zzqpVar) throws zzrk {
        int iZzoa = zzoa();
        if (this.zzawf >= this.zzawg) {
            throw zzrk.zzpu();
        }
        int iZzaq = zzaq(iZzoa);
        this.zzawf++;
        T tZza = zzsuVar.zza(this, zzqpVar);
        zzan(0);
        this.zzawf--;
        zzar(iZzaq);
        return tZza;
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final void zzan(int i9) throws zzrk {
        if (this.zzawn != i9) {
            throw zzrk.zzps();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final boolean zzao(int i9) throws zzrk {
        int iZzni;
        int i10 = i9 & 7;
        int i11 = 0;
        if (i10 == 0) {
            if (this.limit - this.pos < 10) {
                while (i11 < 10) {
                    if (zzof() < 0) {
                        i11++;
                    }
                }
                throw zzrk.zzpr();
            }
            while (i11 < 10) {
                byte[] bArr = this.buffer;
                int i12 = this.pos;
                this.pos = i12 + 1;
                if (bArr[i12] < 0) {
                    i11++;
                }
            }
            throw zzrk.zzpr();
            return true;
        }
        if (i10 == 1) {
            zzas(8);
            return true;
        }
        if (i10 == 2) {
            zzas(zzoa());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw zzrk.zzpt();
            }
            zzas(4);
            return true;
        }
        do {
            iZzni = zzni();
            if (iZzni == 0) {
                break;
            }
        } while (zzao(iZzni));
        zzan(((i9 >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zzaq(int i9) throws zzrk {
        if (i9 < 0) {
            throw zzrk.zzpq();
        }
        int iZznz = i9 + zznz();
        int i10 = this.zzawo;
        if (iZznz > i10) {
            throw zzrk.zzpp();
        }
        this.zzawo = iZznz;
        zzoe();
        return i10;
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final void zzar(int i9) {
        this.zzawo = i9;
        zzoe();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final void zzas(int i9) throws zzrk {
        if (i9 >= 0) {
            int i10 = this.limit;
            int i11 = this.pos;
            if (i9 <= i10 - i11) {
                this.pos = i11 + i9;
                return;
            }
        }
        if (i9 >= 0) {
            throw zzrk.zzpp();
        }
        throw zzrk.zzpq();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zzni() throws zzrk {
        if (zzny()) {
            this.zzawn = 0;
            return 0;
        }
        int iZzoa = zzoa();
        this.zzawn = iZzoa;
        if ((iZzoa >>> 3) != 0) {
            return iZzoa;
        }
        throw new zzrk("Protocol message contained an invalid tag (zero).");
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznj() {
        return zzob();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznk() {
        return zzob();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznl() {
        return zzoa();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznm() {
        return zzod();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznn() {
        return zzoc();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final boolean zzno() {
        return zzob() != 0;
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final String zznp() throws zzrk {
        int iZzoa = zzoa();
        if (iZzoa > 0) {
            int i9 = this.limit;
            int i10 = this.pos;
            if (iZzoa <= i9 - i10) {
                String strZzh = zztz.zzh(this.buffer, i10, iZzoa);
                this.pos += iZzoa;
                return strZzh;
            }
        }
        if (iZzoa == 0) {
            return "";
        }
        if (iZzoa <= 0) {
            throw zzrk.zzpq();
        }
        throw zzrk.zzpp();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    @Override // com.google.android.gms.internal.gtm.zzqe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzps zznq() throws zzrk {
        byte[] bArrCopyOfRange;
        int iZzoa = zzoa();
        if (iZzoa > 0) {
            int i9 = this.limit;
            int i10 = this.pos;
            if (iZzoa <= i9 - i10) {
                zzps zzpsVarZzb = zzps.zzb(this.buffer, i10, iZzoa);
                this.pos += iZzoa;
                return zzpsVarZzb;
            }
        }
        if (iZzoa == 0) {
            return zzps.zzavx;
        }
        if (iZzoa > 0) {
            int i11 = this.limit;
            int i12 = this.pos;
            if (iZzoa <= i11 - i12) {
                int i13 = iZzoa + i12;
                this.pos = i13;
                bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i12, i13);
            } else {
                if (iZzoa > 0) {
                    throw zzrk.zzpp();
                }
                if (iZzoa != 0) {
                    throw zzrk.zzpq();
                }
                bArrCopyOfRange = zzre.zzbbh;
            }
        }
        return zzps.zzf(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznr() {
        return zzoa();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zzns() {
        return zzoa();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznt() {
        return zzoc();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznu() {
        return zzod();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznv() {
        int iZzoa = zzoa();
        return (-(iZzoa & 1)) ^ (iZzoa >>> 1);
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznw() {
        long jZzob = zzob();
        return (-(jZzob & 1)) ^ (jZzob >>> 1);
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final long zznx() throws zzrk {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((zzof() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw zzrk.zzpr();
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final boolean zzny() {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.gtm.zzqe
    public final int zznz() {
        return this.pos - this.zzawm;
    }
}
