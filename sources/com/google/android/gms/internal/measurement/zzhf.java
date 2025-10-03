package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public abstract class zzhf extends zzgn {
    private static final Logger zzb = Logger.getLogger(zzhf.class.getName());
    private static final boolean zzc = zzkt.zza();
    zzhh zza;

    public static class zza extends IOException {
        public zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        public zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public zza(String str, Throwable th) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "), th);
        }
    }

    public static class zzb extends zzhf {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        public zzb(byte[] bArr, int i9, int i10) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            if ((i10 | 0 | (bArr.length - i10)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i10)));
            }
            this.zzb = bArr;
            this.zzc = 0;
            this.zze = 0;
            this.zzd = i10;
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, int i10) {
            zzb((i9 << 3) | i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzb(int i9, int i10) {
            zza(i9, 0);
            zza(i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzc(int i9, int i10) {
            zza(i9, 0);
            zzb(i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzd(int i9) throws zza {
            try {
                byte[] bArr = this.zzb;
                int i10 = this.zze;
                int i11 = i10 + 1;
                bArr[i10] = (byte) i9;
                int i12 = i11 + 1;
                bArr[i11] = (byte) (i9 >> 8);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (i9 >> 16);
                this.zze = i13 + 1;
                bArr[i13] = (byte) (i9 >>> 24);
            } catch (IndexOutOfBoundsException e9) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zze(int i9, int i10) {
            zza(i9, 5);
            zzd(i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, long j9) {
            zza(i9, 0);
            zza(j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzb(byte[] bArr, int i9, int i10) throws zza {
            zzb(i10);
            zzc(bArr, 0, i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzc(int i9, long j9) {
            zza(i9, 1);
            zzc(j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, boolean z8) {
            zza(i9, 0);
            zza(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzb(int i9, zzgm zzgmVar) {
            zza(1, 3);
            zzc(2, i9);
            zza(3, zzgmVar);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzc(long j9) throws zza {
            try {
                byte[] bArr = this.zzb;
                int i9 = this.zze;
                int i10 = i9 + 1;
                bArr[i9] = (byte) j9;
                int i11 = i10 + 1;
                bArr[i10] = (byte) (j9 >> 8);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (j9 >> 16);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (j9 >> 24);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (j9 >> 32);
                int i15 = i14 + 1;
                bArr[i14] = (byte) (j9 >> 40);
                int i16 = i15 + 1;
                bArr[i15] = (byte) (j9 >> 48);
                this.zze = i16 + 1;
                bArr[i16] = (byte) (j9 >> 56);
            } catch (IndexOutOfBoundsException e9) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, String str) {
            zza(i9, 2);
            zza(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, zzgm zzgmVar) {
            zza(i9, 2);
            zza(zzgmVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zzb(int i9) throws zza {
            if (!zzhf.zzc || zzgj.zza() || zza() < 5) {
                while ((i9 & (-128)) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i10 = this.zze;
                        this.zze = i10 + 1;
                        bArr[i10] = (byte) ((i9 & 127) | 128);
                        i9 >>>= 7;
                    } catch (IndexOutOfBoundsException e9) {
                        throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i11 = this.zze;
                this.zze = i11 + 1;
                bArr2[i11] = (byte) i9;
                return;
            }
            if ((i9 & (-128)) == 0) {
                byte[] bArr3 = this.zzb;
                int i12 = this.zze;
                this.zze = i12 + 1;
                zzkt.zza(bArr3, i12, (byte) i9);
                return;
            }
            byte[] bArr4 = this.zzb;
            int i13 = this.zze;
            this.zze = i13 + 1;
            zzkt.zza(bArr4, i13, (byte) (i9 | 128));
            int i14 = i9 >>> 7;
            if ((i14 & (-128)) == 0) {
                byte[] bArr5 = this.zzb;
                int i15 = this.zze;
                this.zze = i15 + 1;
                zzkt.zza(bArr5, i15, (byte) i14);
                return;
            }
            byte[] bArr6 = this.zzb;
            int i16 = this.zze;
            this.zze = i16 + 1;
            zzkt.zza(bArr6, i16, (byte) (i14 | 128));
            int i17 = i14 >>> 7;
            if ((i17 & (-128)) == 0) {
                byte[] bArr7 = this.zzb;
                int i18 = this.zze;
                this.zze = i18 + 1;
                zzkt.zza(bArr7, i18, (byte) i17);
                return;
            }
            byte[] bArr8 = this.zzb;
            int i19 = this.zze;
            this.zze = i19 + 1;
            zzkt.zza(bArr8, i19, (byte) (i17 | 128));
            int i20 = i17 >>> 7;
            if ((i20 & (-128)) == 0) {
                byte[] bArr9 = this.zzb;
                int i21 = this.zze;
                this.zze = i21 + 1;
                zzkt.zza(bArr9, i21, (byte) i20);
                return;
            }
            byte[] bArr10 = this.zzb;
            int i22 = this.zze;
            this.zze = i22 + 1;
            zzkt.zza(bArr10, i22, (byte) (i20 | 128));
            byte[] bArr11 = this.zzb;
            int i23 = this.zze;
            this.zze = i23 + 1;
            zzkt.zza(bArr11, i23, (byte) (i20 >>> 7));
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(zzgm zzgmVar) {
            zzb(zzgmVar.zza());
            zzgmVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, zzjg zzjgVar, zzjv zzjvVar) {
            zza(i9, 2);
            zzgd zzgdVar = (zzgd) zzjgVar;
            int iZzbl = zzgdVar.zzbl();
            if (iZzbl == -1) {
                iZzbl = zzjvVar.zzb(zzgdVar);
                zzgdVar.zzc(iZzbl);
            }
            zzb(iZzbl);
            zzjvVar.zza((zzjv) zzjgVar, (zzlk) this.zza);
        }

        private final void zzc(byte[] bArr, int i9, int i10) throws zza {
            try {
                System.arraycopy(bArr, i9, this.zzb, this.zze, i10);
                this.zze += i10;
            } catch (IndexOutOfBoundsException e9) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i10)), e9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9, zzjg zzjgVar) {
            zza(1, 3);
            zzc(2, i9);
            zza(3, 2);
            zza(zzjgVar);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(zzjg zzjgVar) {
            zzb(zzjgVar.zzbp());
            zzjgVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(byte b9) throws zza {
            try {
                byte[] bArr = this.zzb;
                int i9 = this.zze;
                this.zze = i9 + 1;
                bArr[i9] = b9;
            } catch (IndexOutOfBoundsException e9) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(int i9) {
            if (i9 >= 0) {
                zzb(i9);
            } else {
                zza(i9);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(long j9) throws zza {
            if (zzhf.zzc && zza() >= 10) {
                while ((j9 & (-128)) != 0) {
                    byte[] bArr = this.zzb;
                    int i9 = this.zze;
                    this.zze = i9 + 1;
                    zzkt.zza(bArr, i9, (byte) ((((int) j9) & 127) | 128));
                    j9 >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i10 = this.zze;
                this.zze = i10 + 1;
                zzkt.zza(bArr2, i10, (byte) j9);
                return;
            }
            while ((j9 & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.zzb;
                    int i11 = this.zze;
                    this.zze = i11 + 1;
                    bArr3[i11] = (byte) ((((int) j9) & 127) | 128);
                    j9 >>>= 7;
                } catch (IndexOutOfBoundsException e9) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
                }
            }
            byte[] bArr4 = this.zzb;
            int i12 = this.zze;
            this.zze = i12 + 1;
            bArr4[i12] = (byte) j9;
        }

        @Override // com.google.android.gms.internal.measurement.zzgn
        public final void zza(byte[] bArr, int i9, int i10) throws zza {
            zzc(bArr, i9, i10);
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final void zza(String str) throws zza {
            int i9 = this.zze;
            try {
                int iZzg = zzhf.zzg(str.length() * 3);
                int iZzg2 = zzhf.zzg(str.length());
                if (iZzg2 == iZzg) {
                    int i10 = i9 + iZzg2;
                    this.zze = i10;
                    int iZza = zzkw.zza(str, this.zzb, i10, zza());
                    this.zze = i9;
                    zzb((iZza - i9) - iZzg2);
                    this.zze = iZza;
                    return;
                }
                zzb(zzkw.zza(str));
                this.zze = zzkw.zza(str, this.zzb, this.zze, zza());
            } catch (zzkz e9) {
                this.zze = i9;
                zza(str, e9);
            } catch (IndexOutOfBoundsException e10) {
                throw new zza(e10);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzhf
        public final int zza() {
            return this.zzd - this.zze;
        }
    }

    private zzhf() {
    }

    public static zzhf zza(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzb(double d9) {
        return 8;
    }

    public static int zzb(float f9) {
        return 4;
    }

    public static int zzb(boolean z8) {
        return 1;
    }

    public static int zze(int i9, long j9) {
        return zzg(i9 << 3) + zze(j9);
    }

    public static int zze(long j9) {
        int i9;
        if (((-128) & j9) == 0) {
            return 1;
        }
        if (j9 < 0) {
            return 10;
        }
        if (((-34359738368L) & j9) != 0) {
            j9 >>>= 28;
            i9 = 6;
        } else {
            i9 = 2;
        }
        if (((-2097152) & j9) != 0) {
            i9 += 2;
            j9 >>>= 14;
        }
        return (j9 & (-16384)) != 0 ? i9 + 1 : i9;
    }

    public static int zzf(int i9, int i10) {
        return zzg(i9 << 3) + zzf(i10);
    }

    public static int zzg(int i9) {
        if ((i9 & (-128)) == 0) {
            return 1;
        }
        if ((i9 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i9) == 0) {
            return 3;
        }
        return (i9 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzg(int i9, int i10) {
        return zzg(i9 << 3) + zzg(i10);
    }

    public static int zzg(long j9) {
        return 8;
    }

    public static int zzh(int i9, int i10) {
        return zzg(i9 << 3) + zzg(zzm(i10));
    }

    public static int zzh(long j9) {
        return 8;
    }

    public static int zzi(int i9) {
        return 4;
    }

    public static int zzi(int i9, int i10) {
        return zzg(i9 << 3) + 4;
    }

    private static long zzi(long j9) {
        return (j9 >> 63) ^ (j9 << 1);
    }

    public static int zzj(int i9) {
        return 4;
    }

    public static int zzj(int i9, int i10) {
        return zzg(i9 << 3) + 4;
    }

    public static int zzk(int i9, int i10) {
        return zzg(i9 << 3) + zzf(i10);
    }

    @Deprecated
    public static int zzl(int i9) {
        return zzg(i9);
    }

    private static int zzm(int i9) {
        return (i9 >> 31) ^ (i9 << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b9);

    public abstract void zza(int i9);

    public abstract void zza(int i9, int i10);

    public abstract void zza(int i9, long j9);

    public abstract void zza(int i9, zzgm zzgmVar);

    public abstract void zza(int i9, zzjg zzjgVar);

    public abstract void zza(int i9, zzjg zzjgVar, zzjv zzjvVar);

    public abstract void zza(int i9, String str);

    public abstract void zza(int i9, boolean z8);

    public abstract void zza(long j9);

    public abstract void zza(zzgm zzgmVar);

    public abstract void zza(zzjg zzjgVar);

    public abstract void zza(String str);

    public abstract void zzb(int i9);

    public abstract void zzb(int i9, int i10);

    public final void zzb(int i9, long j9) {
        zza(i9, zzi(j9));
    }

    public abstract void zzb(int i9, zzgm zzgmVar);

    public abstract void zzb(byte[] bArr, int i9, int i10);

    public final void zzc(int i9) {
        zzb(zzm(i9));
    }

    public abstract void zzc(int i9, int i10);

    public abstract void zzc(int i9, long j9);

    public abstract void zzc(long j9);

    public abstract void zzd(int i9);

    public final void zzd(int i9, int i10) {
        zzc(i9, zzm(i10));
    }

    public abstract void zze(int i9, int i10);

    public static int zzc(int i9, zzgm zzgmVar) {
        int iZzg = zzg(i9 << 3);
        int iZza = zzgmVar.zza();
        return iZzg + zzg(iZza) + iZza;
    }

    public static int zzd(int i9, long j9) {
        return zzg(i9 << 3) + zze(j9);
    }

    public final void zzb(long j9) {
        zza(zzi(j9));
    }

    public static int zzb(int i9, float f9) {
        return zzg(i9 << 3) + 4;
    }

    public static int zze(int i9) {
        return zzg(i9 << 3);
    }

    public static int zzf(int i9, long j9) {
        return zzg(i9 << 3) + zze(zzi(j9));
    }

    public static int zzg(int i9, long j9) {
        return zzg(i9 << 3) + 8;
    }

    public static int zzh(int i9, long j9) {
        return zzg(i9 << 3) + 8;
    }

    public static int zzk(int i9) {
        return zzf(i9);
    }

    public final void zza(int i9, float f9) {
        zze(i9, Float.floatToRawIntBits(f9));
    }

    public static int zzb(int i9, double d9) {
        return zzg(i9 << 3) + 8;
    }

    public static int zzd(int i9, zzgm zzgmVar) {
        return (zzg(8) << 1) + zzg(2, i9) + zzc(3, zzgmVar);
    }

    public static int zzh(int i9) {
        return zzg(zzm(i9));
    }

    public final void zza(int i9, double d9) {
        zzc(i9, Double.doubleToRawLongBits(d9));
    }

    public static int zzb(int i9, boolean z8) {
        return zzg(i9 << 3) + 1;
    }

    @Deprecated
    public static int zzc(int i9, zzjg zzjgVar, zzjv zzjvVar) {
        int iZzg = zzg(i9 << 3) << 1;
        zzgd zzgdVar = (zzgd) zzjgVar;
        int iZzbl = zzgdVar.zzbl();
        if (iZzbl == -1) {
            iZzbl = zzjvVar.zzb(zzgdVar);
            zzgdVar.zzc(iZzbl);
        }
        return iZzg + iZzbl;
    }

    public static int zzf(int i9) {
        if (i9 >= 0) {
            return zzg(i9);
        }
        return 10;
    }

    public final void zza(float f9) {
        zzd(Float.floatToRawIntBits(f9));
    }

    public static int zzb(int i9, String str) {
        return zzg(i9 << 3) + zzb(str);
    }

    public static int zzf(long j9) {
        return zze(zzi(j9));
    }

    public final void zza(double d9) {
        zzc(Double.doubleToRawLongBits(d9));
    }

    public static int zzd(long j9) {
        return zze(j9);
    }

    public final void zza(boolean z8) {
        zza(z8 ? (byte) 1 : (byte) 0);
    }

    public static int zza(int i9, zzil zzilVar) {
        int iZzg = zzg(i9 << 3);
        int iZzb = zzilVar.zzb();
        return iZzg + zzg(iZzb) + iZzb;
    }

    public static int zzb(int i9, zzjg zzjgVar, zzjv zzjvVar) {
        return zzg(i9 << 3) + zza(zzjgVar, zzjvVar);
    }

    public static int zzb(int i9, zzjg zzjgVar) {
        return (zzg(8) << 1) + zzg(2, i9) + zzg(24) + zzb(zzjgVar);
    }

    @Deprecated
    public static int zzc(zzjg zzjgVar) {
        return zzjgVar.zzbp();
    }

    public static int zza(zzil zzilVar) {
        int iZzb = zzilVar.zzb();
        return zzg(iZzb) + iZzb;
    }

    public static int zza(zzjg zzjgVar, zzjv zzjvVar) {
        zzgd zzgdVar = (zzgd) zzjgVar;
        int iZzbl = zzgdVar.zzbl();
        if (iZzbl == -1) {
            iZzbl = zzjvVar.zzb(zzgdVar);
            zzgdVar.zzc(iZzbl);
        }
        return zzg(iZzbl) + iZzbl;
    }

    public static int zzb(int i9, zzil zzilVar) {
        return (zzg(8) << 1) + zzg(2, i9) + zza(3, zzilVar);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzkw.zza(str);
        } catch (zzkz unused) {
            length = str.getBytes(zzhx.zza).length;
        }
        return zzg(length) + length;
    }

    public final void zza(String str, zzkz zzkzVar) throws zza {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzkzVar);
        byte[] bytes = str.getBytes(zzhx.zza);
        try {
            zzb(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zza e9) {
            throw e9;
        } catch (IndexOutOfBoundsException e10) {
            throw new zza(e10);
        }
    }

    public static int zzb(zzgm zzgmVar) {
        int iZza = zzgmVar.zza();
        return zzg(iZza) + iZza;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        return zzg(length) + length;
    }

    public static int zzb(zzjg zzjgVar) {
        int iZzbp = zzjgVar.zzbp();
        return zzg(iZzbp) + iZzbp;
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
}
