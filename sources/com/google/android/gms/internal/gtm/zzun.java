package com.google.android.gms.internal.gtm;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzun {
    private final byte[] buffer;
    private int zzawf;
    private int zzawl;
    private int zzawn;
    private final int zzbgu;
    private final int zzbgv;
    private int zzbgw;
    private int zzbgx;
    private zzqe zzbgy;
    private int zzawo = Integer.MAX_VALUE;
    private int zzawg = 64;
    private int zzawh = 67108864;

    private zzun(byte[] bArr, int i9, int i10) {
        this.buffer = bArr;
        this.zzbgu = i9;
        int i11 = i10 + i9;
        this.zzbgw = i11;
        this.zzbgv = i11;
        this.zzbgx = i9;
    }

    private final void zzas(int i9) throws zzuv {
        if (i9 < 0) {
            throw zzuv.zzsb();
        }
        int i10 = this.zzbgx;
        int i11 = i10 + i9;
        int i12 = this.zzawo;
        if (i11 > i12) {
            zzas(i12 - i10);
            throw zzuv.zzsa();
        }
        if (i9 > this.zzbgw - i10) {
            throw zzuv.zzsa();
        }
        this.zzbgx = i10 + i9;
    }

    public static zzun zzj(byte[] bArr, int i9, int i10) {
        return new zzun(bArr, 0, i10);
    }

    public static zzun zzk(byte[] bArr) {
        return zzj(bArr, 0, bArr.length);
    }

    private final void zzoe() {
        int i9 = this.zzbgw + this.zzawl;
        this.zzbgw = i9;
        int i10 = this.zzawo;
        if (i9 <= i10) {
            this.zzawl = 0;
            return;
        }
        int i11 = i9 - i10;
        this.zzawl = i11;
        this.zzbgw = i9 - i11;
    }

    private final byte zzof() throws zzuv {
        int i9 = this.zzbgx;
        if (i9 == this.zzbgw) {
            throw zzuv.zzsa();
        }
        byte[] bArr = this.buffer;
        this.zzbgx = i9 + 1;
        return bArr[i9];
    }

    private final zzqe zzru() throws IOException {
        if (this.zzbgy == null) {
            this.zzbgy = zzqe.zzd(this.buffer, this.zzbgu, this.zzbgv);
        }
        int iZznz = this.zzbgy.zznz();
        int i9 = this.zzbgx - this.zzbgu;
        if (iZznz > i9) {
            throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", Integer.valueOf(iZznz), Integer.valueOf(i9)));
        }
        this.zzbgy.zzas(i9 - iZznz);
        this.zzbgy.zzap(this.zzawg - this.zzawf);
        return this.zzbgy;
    }

    public final int getPosition() {
        return this.zzbgx - this.zzbgu;
    }

    public final String readString() throws zzuv {
        int iZzoa = zzoa();
        if (iZzoa < 0) {
            throw zzuv.zzsb();
        }
        int i9 = this.zzbgw;
        int i10 = this.zzbgx;
        if (iZzoa > i9 - i10) {
            throw zzuv.zzsa();
        }
        String str = new String(this.buffer, i10, iZzoa, zzuu.UTF_8);
        this.zzbgx += iZzoa;
        return str;
    }

    public final void zza(zzuw zzuwVar, int i9) throws zzuv {
        int i10 = this.zzawf;
        if (i10 >= this.zzawg) {
            throw zzuv.zzsd();
        }
        this.zzawf = i10 + 1;
        zzuwVar.zza(this);
        zzan((i9 << 3) | 4);
        this.zzawf--;
    }

    public final void zzan(int i9) throws zzuv {
        if (this.zzawn != i9) {
            throw new zzuv("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzao(int i9) throws zzuv {
        int iZzni;
        int i10 = i9 & 7;
        if (i10 == 0) {
            zzoa();
            return true;
        }
        if (i10 == 1) {
            zzof();
            zzof();
            zzof();
            zzof();
            zzof();
            zzof();
            zzof();
            zzof();
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
                throw new zzuv("Protocol message tag had invalid wire type.");
            }
            zzoc();
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

    public final int zzaq(int i9) throws zzuv {
        if (i9 < 0) {
            throw zzuv.zzsb();
        }
        int i10 = i9 + this.zzbgx;
        int i11 = this.zzawo;
        if (i10 > i11) {
            throw zzuv.zzsa();
        }
        this.zzawo = i10;
        zzoe();
        return i11;
    }

    public final void zzar(int i9) {
        this.zzawo = i9;
        zzoe();
    }

    public final void zzbz(int i9) {
        zzu(i9, this.zzawn);
    }

    public final int zzni() throws zzuv {
        if (this.zzbgx == this.zzbgw) {
            this.zzawn = 0;
            return 0;
        }
        int iZzoa = zzoa();
        this.zzawn = iZzoa;
        if (iZzoa != 0) {
            return iZzoa;
        }
        throw new zzuv("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzno() {
        return zzoa() != 0;
    }

    public final int zzoa() throws zzuv {
        int i9;
        byte bZzof = zzof();
        if (bZzof >= 0) {
            return bZzof;
        }
        int i10 = bZzof & Ascii.DEL;
        byte bZzof2 = zzof();
        if (bZzof2 >= 0) {
            i9 = bZzof2 << 7;
        } else {
            i10 |= (bZzof2 & Ascii.DEL) << 7;
            byte bZzof3 = zzof();
            if (bZzof3 >= 0) {
                i9 = bZzof3 << Ascii.f15390SO;
            } else {
                i10 |= (bZzof3 & Ascii.DEL) << 14;
                byte bZzof4 = zzof();
                if (bZzof4 < 0) {
                    int i11 = i10 | ((bZzof4 & Ascii.DEL) << 21);
                    byte bZzof5 = zzof();
                    int i12 = i11 | (bZzof5 << Ascii.f15383FS);
                    if (bZzof5 >= 0) {
                        return i12;
                    }
                    for (int i13 = 0; i13 < 5; i13++) {
                        if (zzof() >= 0) {
                            return i12;
                        }
                    }
                    throw zzuv.zzsc();
                }
                i9 = bZzof4 << Ascii.NAK;
            }
        }
        return i10 | i9;
    }

    public final long zzob() throws zzuv {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((zzof() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw zzuv.zzsc();
    }

    public final int zzoc() throws zzuv {
        return (zzof() & UnsignedBytes.MAX_VALUE) | ((zzof() & UnsignedBytes.MAX_VALUE) << 8) | ((zzof() & UnsignedBytes.MAX_VALUE) << 16) | ((zzof() & UnsignedBytes.MAX_VALUE) << 24);
    }

    public final int zzrv() {
        int i9 = this.zzawo;
        if (i9 == Integer.MAX_VALUE) {
            return -1;
        }
        return i9 - this.zzbgx;
    }

    public final byte[] zzt(int i9, int i10) {
        if (i10 == 0) {
            return zzuz.zzbhw;
        }
        byte[] bArr = new byte[i10];
        System.arraycopy(this.buffer, this.zzbgu + i9, bArr, 0, i10);
        return bArr;
    }

    public final void zzu(int i9, int i10) {
        int i11 = this.zzbgx;
        int i12 = this.zzbgu;
        if (i9 > i11 - i12) {
            int i13 = this.zzbgx - this.zzbgu;
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i9);
            sb.append(" is beyond current ");
            sb.append(i13);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i9 >= 0) {
            this.zzbgx = i12 + i9;
            this.zzawn = i10;
        } else {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i9);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final void zza(zzuw zzuwVar) throws zzuv {
        int iZzoa = zzoa();
        if (this.zzawf < this.zzawg) {
            int iZzaq = zzaq(iZzoa);
            this.zzawf++;
            zzuwVar.zza(this);
            zzan(0);
            this.zzawf--;
            zzar(iZzaq);
            return;
        }
        throw zzuv.zzsd();
    }

    public final <T extends zzrc<T, ?>> T zza(zzsu<T> zzsuVar) throws zzuv {
        try {
            T t8 = (T) zzru().zza(zzsuVar, zzqp.zzor());
            zzao(this.zzawn);
            return t8;
        } catch (zzrk e9) {
            throw new zzuv("", e9);
        }
    }
}
