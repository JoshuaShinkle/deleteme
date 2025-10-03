package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public abstract class zzqj extends zzpr {
    private static final Logger logger = Logger.getLogger(zzqj.class.getName());
    private static final boolean zzawt = zztx.zzrm();
    zzql zzawu;

    public static final class zzb extends zza {
        private final ByteBuffer zzawv;
        private int zzaww;

        public zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzawv = byteBuffer;
            this.zzaww = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.gtm.zzqj.zza, com.google.android.gms.internal.gtm.zzqj
        public final void flush() {
            this.zzawv.position(this.zzaww + zzok());
        }
    }

    public static class zzc extends IOException {
        public zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public zzc(String str) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "));
        }

        public zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public zzc(String str, Throwable th) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "), th);
        }
    }

    private zzqj() {
    }

    public static zzqj zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
        return zztx.zzrn() ? new zze(byteBuffer) : new zzd(byteBuffer);
    }

    public static int zzb(float f9) {
        return 4;
    }

    public static int zzbb(int i9) {
        return zzbd(i9 << 3);
    }

    public static int zzbc(int i9) {
        if (i9 >= 0) {
            return zzbd(i9);
        }
        return 10;
    }

    public static int zzbd(int i9) {
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

    public static int zzbe(int i9) {
        return zzbd(zzbi(i9));
    }

    public static int zzbf(int i9) {
        return 4;
    }

    public static int zzbg(int i9) {
        return 4;
    }

    public static int zzbh(int i9) {
        return zzbc(i9);
    }

    private static int zzbi(int i9) {
        return (i9 >> 31) ^ (i9 << 1);
    }

    @Deprecated
    public static int zzbj(int i9) {
        return zzbd(i9);
    }

    public static int zzc(double d9) {
        return 8;
    }

    public static int zzc(int i9, boolean z8) {
        return zzbb(i9) + 1;
    }

    public static int zzd(int i9, long j9) {
        return zzbb(i9) + zzt(j9);
    }

    public static int zzda(String str) {
        int length;
        try {
            length = zztz.zza(str);
        } catch (zzud unused) {
            length = str.getBytes(zzre.UTF_8).length;
        }
        return zzbd(length) + length;
    }

    public static int zze(int i9, long j9) {
        return zzbb(i9) + zzt(j9);
    }

    public static int zzf(int i9, long j9) {
        return zzbb(i9) + zzt(zzx(j9));
    }

    public static zzqj zzg(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzh(int i9, long j9) {
        return zzbb(i9) + 8;
    }

    public static int zzj(int i9, int i10) {
        return zzbb(i9) + zzbd(i10);
    }

    public static int zzj(boolean z8) {
        return 1;
    }

    public static int zzk(int i9, int i10) {
        return zzbb(i9) + zzbd(zzbi(i10));
    }

    public static int zzl(int i9, int i10) {
        return zzbb(i9) + 4;
    }

    public static int zzm(int i9, int i10) {
        return zzbb(i9) + 4;
    }

    public static int zzn(int i9, int i10) {
        return zzbb(i9) + zzbc(i10);
    }

    public static int zzs(long j9) {
        return zzt(j9);
    }

    public static int zzt(long j9) {
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

    public static int zzu(long j9) {
        return zzt(zzx(j9));
    }

    public static int zzv(long j9) {
        return 8;
    }

    public static int zzw(long j9) {
        return 8;
    }

    private static long zzx(long j9) {
        return (j9 >> 63) ^ (j9 << 1);
    }

    public abstract void flush();

    public abstract void write(byte[] bArr, int i9, int i10);

    public abstract void zza(int i9, long j9);

    public abstract void zza(int i9, zzps zzpsVar);

    public abstract void zza(int i9, zzsk zzskVar);

    public abstract void zza(int i9, zzsk zzskVar, zzsz zzszVar);

    public abstract void zza(int i9, String str);

    public abstract void zza(zzps zzpsVar);

    public abstract void zza(zzsk zzskVar, zzsz zzszVar);

    public abstract void zzax(int i9);

    public abstract void zzay(int i9);

    public final void zzaz(int i9) {
        zzay(zzbi(i9));
    }

    public final void zzb(int i9, long j9) {
        zza(i9, zzx(j9));
    }

    public abstract void zzb(int i9, zzps zzpsVar);

    public abstract void zzb(int i9, zzsk zzskVar);

    public abstract void zzb(int i9, boolean z8);

    public abstract void zzb(zzsk zzskVar);

    public abstract void zzba(int i9);

    public abstract void zzc(byte b9);

    public abstract void zzc(int i9, long j9);

    public abstract void zzcz(String str);

    public abstract void zzd(int i9, int i10);

    public abstract void zze(int i9, int i10);

    public abstract void zze(byte[] bArr, int i9, int i10);

    public abstract void zzf(int i9, int i10);

    public abstract void zzh(int i9, int i10);

    public final void zzi(boolean z8) {
        zzc(z8 ? (byte) 1 : (byte) 0);
    }

    public abstract int zzoi();

    public abstract void zzp(long j9);

    public final void zzq(long j9) {
        zzp(zzx(j9));
    }

    public abstract void zzr(long j9);

    public static class zza extends zzqj {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        public zza(byte[] bArr, int i9, int i10) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i11 = i9 + i10;
            if ((i9 | i10 | (bArr.length - i11)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            this.buffer = bArr;
            this.offset = i9;
            this.position = i9;
            this.limit = i11;
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public void flush() {
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void write(byte[] bArr, int i9, int i10) throws zzc {
            try {
                System.arraycopy(bArr, i9, this.buffer, this.position, i10);
                this.position += i10;
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i10)), e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, long j9) {
            zzd(i9, 0);
            zzp(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzax(int i9) {
            if (i9 >= 0) {
                zzay(i9);
            } else {
                zzp(i9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzay(int i9) throws zzc {
            if (!zzqj.zzawt || zzpp.zzna() || zzoi() < 5) {
                while ((i9 & (-128)) != 0) {
                    try {
                        byte[] bArr = this.buffer;
                        int i10 = this.position;
                        this.position = i10 + 1;
                        bArr[i10] = (byte) ((i9 & 127) | 128);
                        i9 >>>= 7;
                    } catch (IndexOutOfBoundsException e9) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e9);
                    }
                }
                byte[] bArr2 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                bArr2[i11] = (byte) i9;
                return;
            }
            if ((i9 & (-128)) == 0) {
                byte[] bArr3 = this.buffer;
                int i12 = this.position;
                this.position = i12 + 1;
                zztx.zza(bArr3, i12, (byte) i9);
                return;
            }
            byte[] bArr4 = this.buffer;
            int i13 = this.position;
            this.position = i13 + 1;
            zztx.zza(bArr4, i13, (byte) (i9 | 128));
            int i14 = i9 >>> 7;
            if ((i14 & (-128)) == 0) {
                byte[] bArr5 = this.buffer;
                int i15 = this.position;
                this.position = i15 + 1;
                zztx.zza(bArr5, i15, (byte) i14);
                return;
            }
            byte[] bArr6 = this.buffer;
            int i16 = this.position;
            this.position = i16 + 1;
            zztx.zza(bArr6, i16, (byte) (i14 | 128));
            int i17 = i14 >>> 7;
            if ((i17 & (-128)) == 0) {
                byte[] bArr7 = this.buffer;
                int i18 = this.position;
                this.position = i18 + 1;
                zztx.zza(bArr7, i18, (byte) i17);
                return;
            }
            byte[] bArr8 = this.buffer;
            int i19 = this.position;
            this.position = i19 + 1;
            zztx.zza(bArr8, i19, (byte) (i17 | 128));
            int i20 = i17 >>> 7;
            if ((i20 & (-128)) == 0) {
                byte[] bArr9 = this.buffer;
                int i21 = this.position;
                this.position = i21 + 1;
                zztx.zza(bArr9, i21, (byte) i20);
                return;
            }
            byte[] bArr10 = this.buffer;
            int i22 = this.position;
            this.position = i22 + 1;
            zztx.zza(bArr10, i22, (byte) (i20 | 128));
            byte[] bArr11 = this.buffer;
            int i23 = this.position;
            this.position = i23 + 1;
            zztx.zza(bArr11, i23, (byte) (i20 >>> 7));
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, boolean z8) {
            zzd(i9, 0);
            zzc(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzba(int i9) throws zzc {
            try {
                byte[] bArr = this.buffer;
                int i10 = this.position;
                int i11 = i10 + 1;
                bArr[i10] = (byte) i9;
                int i12 = i11 + 1;
                bArr[i11] = (byte) (i9 >> 8);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (i9 >> 16);
                this.position = i13 + 1;
                bArr[i13] = (byte) (i9 >>> 24);
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(int i9, long j9) {
            zzd(i9, 1);
            zzr(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzcz(String str) throws zzc {
            int i9 = this.position;
            try {
                int iZzbd = zzqj.zzbd(str.length() * 3);
                int iZzbd2 = zzqj.zzbd(str.length());
                if (iZzbd2 != iZzbd) {
                    zzay(zztz.zza(str));
                    this.position = zztz.zza(str, this.buffer, this.position, zzoi());
                    return;
                }
                int i10 = i9 + iZzbd2;
                this.position = i10;
                int iZza = zztz.zza(str, this.buffer, i10, zzoi());
                this.position = i9;
                zzay((iZza - i9) - iZzbd2);
                this.position = iZza;
            } catch (zzud e9) {
                this.position = i9;
                zza(str, e9);
            } catch (IndexOutOfBoundsException e10) {
                throw new zzc(e10);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzd(int i9, int i10) {
            zzay((i9 << 3) | i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(int i9, int i10) {
            zzd(i9, 0);
            zzax(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzf(int i9, int i10) {
            zzd(i9, 0);
            zzay(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzh(int i9, int i10) {
            zzd(i9, 5);
            zzba(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final int zzoi() {
            return this.limit - this.position;
        }

        public final int zzok() {
            return this.position - this.offset;
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzp(long j9) throws zzc {
            if (zzqj.zzawt && zzoi() >= 10) {
                while ((j9 & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i9 = this.position;
                    this.position = i9 + 1;
                    zztx.zza(bArr, i9, (byte) ((((int) j9) & 127) | 128));
                    j9 >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i10 = this.position;
                this.position = i10 + 1;
                zztx.zza(bArr2, i10, (byte) j9);
                return;
            }
            while ((j9 & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i11 = this.position;
                    this.position = i11 + 1;
                    bArr3[i11] = (byte) ((((int) j9) & 127) | 128);
                    j9 >>>= 7;
                } catch (IndexOutOfBoundsException e9) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e9);
                }
            }
            byte[] bArr4 = this.buffer;
            int i12 = this.position;
            this.position = i12 + 1;
            bArr4[i12] = (byte) j9;
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzr(long j9) throws zzc {
            try {
                byte[] bArr = this.buffer;
                int i9 = this.position;
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
                this.position = i16 + 1;
                bArr[i16] = (byte) (j9 >> 56);
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, String str) {
            zzd(i9, 2);
            zzcz(str);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzsk zzskVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzskVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(byte b9) throws zzc {
            try {
                byte[] bArr = this.buffer;
                int i9 = this.position;
                this.position = i9 + 1;
                bArr[i9] = b9;
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(byte[] bArr, int i9, int i10) {
            zzay(i10);
            write(bArr, 0, i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzps zzpsVar) {
            zzd(i9, 2);
            zza(zzpsVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzps zzpsVar) {
            zzay(zzpsVar.size());
            zzpsVar.zza(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzps zzpsVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzpsVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar) {
            zzd(i9, 2);
            zzb(zzskVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar, zzsz zzszVar) {
            zzd(i9, 2);
            zzpl zzplVar = (zzpl) zzskVar;
            int iZzmw = zzplVar.zzmw();
            if (iZzmw == -1) {
                iZzmw = zzszVar.zzad(zzplVar);
                zzplVar.zzag(iZzmw);
            }
            zzay(iZzmw);
            zzszVar.zza(zzskVar, this.zzawu);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(zzsk zzskVar) {
            zzay(zzskVar.zzpe());
            zzskVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzsk zzskVar, zzsz zzszVar) {
            zzpl zzplVar = (zzpl) zzskVar;
            int iZzmw = zzplVar.zzmw();
            if (iZzmw == -1) {
                iZzmw = zzszVar.zzad(zzplVar);
                zzplVar.zzag(iZzmw);
            }
            zzay(iZzmw);
            zzszVar.zza(zzskVar, this.zzawu);
        }

        @Override // com.google.android.gms.internal.gtm.zzpr
        public final void zza(byte[] bArr, int i9, int i10) {
            write(bArr, i9, i10);
        }
    }

    public static final class zzd extends zzqj {
        private final int zzaww;
        private final ByteBuffer zzawx;
        private final ByteBuffer zzawy;

        public zzd(ByteBuffer byteBuffer) {
            super();
            this.zzawx = byteBuffer;
            this.zzawy = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzaww = byteBuffer.position();
        }

        private final void zzdb(String str) throws zzc {
            try {
                zztz.zza(str, this.zzawy);
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void flush() {
            this.zzawx.position(this.zzawy.position());
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void write(byte[] bArr, int i9, int i10) throws zzc {
            try {
                this.zzawy.put(bArr, i9, i10);
            } catch (IndexOutOfBoundsException e9) {
                throw new zzc(e9);
            } catch (BufferOverflowException e10) {
                throw new zzc(e10);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, long j9) {
            zzd(i9, 0);
            zzp(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzax(int i9) {
            if (i9 >= 0) {
                zzay(i9);
            } else {
                zzp(i9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzay(int i9) throws zzc {
            while ((i9 & (-128)) != 0) {
                try {
                    this.zzawy.put((byte) ((i9 & 127) | 128));
                    i9 >>>= 7;
                } catch (BufferOverflowException e9) {
                    throw new zzc(e9);
                }
            }
            this.zzawy.put((byte) i9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, boolean z8) {
            zzd(i9, 0);
            zzc(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzba(int i9) throws zzc {
            try {
                this.zzawy.putInt(i9);
            } catch (BufferOverflowException e9) {
                throw new zzc(e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(int i9, long j9) {
            zzd(i9, 1);
            zzr(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzcz(String str) throws zzc {
            int iPosition = this.zzawy.position();
            try {
                int iZzbd = zzqj.zzbd(str.length() * 3);
                int iZzbd2 = zzqj.zzbd(str.length());
                if (iZzbd2 != iZzbd) {
                    zzay(zztz.zza(str));
                    zzdb(str);
                    return;
                }
                int iPosition2 = this.zzawy.position() + iZzbd2;
                this.zzawy.position(iPosition2);
                zzdb(str);
                int iPosition3 = this.zzawy.position();
                this.zzawy.position(iPosition);
                zzay(iPosition3 - iPosition2);
                this.zzawy.position(iPosition3);
            } catch (zzud e9) {
                this.zzawy.position(iPosition);
                zza(str, e9);
            } catch (IllegalArgumentException e10) {
                throw new zzc(e10);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzd(int i9, int i10) {
            zzay((i9 << 3) | i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(int i9, int i10) {
            zzd(i9, 0);
            zzax(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzf(int i9, int i10) {
            zzd(i9, 0);
            zzay(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzh(int i9, int i10) {
            zzd(i9, 5);
            zzba(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final int zzoi() {
            return this.zzawy.remaining();
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzp(long j9) throws zzc {
            while (((-128) & j9) != 0) {
                try {
                    this.zzawy.put((byte) ((((int) j9) & 127) | 128));
                    j9 >>>= 7;
                } catch (BufferOverflowException e9) {
                    throw new zzc(e9);
                }
            }
            this.zzawy.put((byte) j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzr(long j9) throws zzc {
            try {
                this.zzawy.putLong(j9);
            } catch (BufferOverflowException e9) {
                throw new zzc(e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, String str) {
            zzd(i9, 2);
            zzcz(str);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzsk zzskVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzskVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(byte b9) throws zzc {
            try {
                this.zzawy.put(b9);
            } catch (BufferOverflowException e9) {
                throw new zzc(e9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(byte[] bArr, int i9, int i10) {
            zzay(i10);
            write(bArr, 0, i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzps zzpsVar) {
            zzd(i9, 2);
            zza(zzpsVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar) {
            zzd(i9, 2);
            zzb(zzskVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzps zzpsVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzpsVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar, zzsz zzszVar) {
            zzd(i9, 2);
            zza(zzskVar, zzszVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzsk zzskVar, zzsz zzszVar) {
            zzpl zzplVar = (zzpl) zzskVar;
            int iZzmw = zzplVar.zzmw();
            if (iZzmw == -1) {
                iZzmw = zzszVar.zzad(zzplVar);
                zzplVar.zzag(iZzmw);
            }
            zzay(iZzmw);
            zzszVar.zza(zzskVar, this.zzawu);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(zzsk zzskVar) {
            zzay(zzskVar.zzpe());
            zzskVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzps zzpsVar) {
            zzay(zzpsVar.size());
            zzpsVar.zza(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzpr
        public final void zza(byte[] bArr, int i9, int i10) {
            write(bArr, i9, i10);
        }
    }

    public static final class zze extends zzqj {
        private final ByteBuffer zzawx;
        private final ByteBuffer zzawy;
        private final long zzawz;
        private final long zzaxa;
        private final long zzaxb;
        private final long zzaxc;
        private long zzaxd;

        public zze(ByteBuffer byteBuffer) {
            super();
            this.zzawx = byteBuffer;
            this.zzawy = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            long jZzb = zztx.zzb(byteBuffer);
            this.zzawz = jZzb;
            long jPosition = byteBuffer.position() + jZzb;
            this.zzaxa = jPosition;
            long jLimit = jZzb + byteBuffer.limit();
            this.zzaxb = jLimit;
            this.zzaxc = jLimit - 10;
            this.zzaxd = jPosition;
        }

        private final void zzy(long j9) {
            this.zzawy.position((int) (j9 - this.zzawz));
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void flush() {
            this.zzawx.position((int) (this.zzaxd - this.zzawz));
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void write(byte[] bArr, int i9, int i10) throws zzc {
            if (bArr != null && i9 >= 0 && i10 >= 0 && bArr.length - i10 >= i9) {
                long j9 = i10;
                long j10 = this.zzaxb - j9;
                long j11 = this.zzaxd;
                if (j10 >= j11) {
                    zztx.zza(bArr, i9, j11, j9);
                    this.zzaxd += j9;
                    return;
                }
            }
            if (bArr != null) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzaxd), Long.valueOf(this.zzaxb), Integer.valueOf(i10)));
            }
            throw new NullPointerException("value");
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, long j9) {
            zzd(i9, 0);
            zzp(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzax(int i9) {
            if (i9 >= 0) {
                zzay(i9);
            } else {
                zzp(i9);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzay(int i9) throws zzc {
            if (this.zzaxd <= this.zzaxc) {
                while ((i9 & (-128)) != 0) {
                    long j9 = this.zzaxd;
                    this.zzaxd = j9 + 1;
                    zztx.zza(j9, (byte) ((i9 & 127) | 128));
                    i9 >>>= 7;
                }
                long j10 = this.zzaxd;
                this.zzaxd = 1 + j10;
                zztx.zza(j10, (byte) i9);
                return;
            }
            while (true) {
                long j11 = this.zzaxd;
                if (j11 >= this.zzaxb) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzaxd), Long.valueOf(this.zzaxb), 1));
                }
                if ((i9 & (-128)) == 0) {
                    this.zzaxd = 1 + j11;
                    zztx.zza(j11, (byte) i9);
                    return;
                } else {
                    this.zzaxd = j11 + 1;
                    zztx.zza(j11, (byte) ((i9 & 127) | 128));
                    i9 >>>= 7;
                }
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, boolean z8) {
            zzd(i9, 0);
            zzc(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzba(int i9) {
            this.zzawy.putInt((int) (this.zzaxd - this.zzawz), i9);
            this.zzaxd += 4;
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(int i9, long j9) {
            zzd(i9, 1);
            zzr(j9);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzcz(String str) throws zzc {
            long j9 = this.zzaxd;
            try {
                int iZzbd = zzqj.zzbd(str.length() * 3);
                int iZzbd2 = zzqj.zzbd(str.length());
                if (iZzbd2 != iZzbd) {
                    int iZza = zztz.zza(str);
                    zzay(iZza);
                    zzy(this.zzaxd);
                    zztz.zza(str, this.zzawy);
                    this.zzaxd += iZza;
                    return;
                }
                int i9 = ((int) (this.zzaxd - this.zzawz)) + iZzbd2;
                this.zzawy.position(i9);
                zztz.zza(str, this.zzawy);
                int iPosition = this.zzawy.position() - i9;
                zzay(iPosition);
                this.zzaxd += iPosition;
            } catch (zzud e9) {
                this.zzaxd = j9;
                zzy(j9);
                zza(str, e9);
            } catch (IllegalArgumentException e10) {
                throw new zzc(e10);
            } catch (IndexOutOfBoundsException e11) {
                throw new zzc(e11);
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzd(int i9, int i10) {
            zzay((i9 << 3) | i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(int i9, int i10) {
            zzd(i9, 0);
            zzax(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzf(int i9, int i10) {
            zzd(i9, 0);
            zzay(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzh(int i9, int i10) {
            zzd(i9, 5);
            zzba(i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final int zzoi() {
            return (int) (this.zzaxb - this.zzaxd);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzp(long j9) throws zzc {
            if (this.zzaxd <= this.zzaxc) {
                while ((j9 & (-128)) != 0) {
                    long j10 = this.zzaxd;
                    this.zzaxd = j10 + 1;
                    zztx.zza(j10, (byte) ((((int) j9) & 127) | 128));
                    j9 >>>= 7;
                }
                long j11 = this.zzaxd;
                this.zzaxd = 1 + j11;
                zztx.zza(j11, (byte) j9);
                return;
            }
            while (true) {
                long j12 = this.zzaxd;
                if (j12 >= this.zzaxb) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzaxd), Long.valueOf(this.zzaxb), 1));
                }
                if ((j9 & (-128)) == 0) {
                    this.zzaxd = 1 + j12;
                    zztx.zza(j12, (byte) j9);
                    return;
                } else {
                    this.zzaxd = j12 + 1;
                    zztx.zza(j12, (byte) ((((int) j9) & 127) | 128));
                    j9 >>>= 7;
                }
            }
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzr(long j9) {
            this.zzawy.putLong((int) (this.zzaxd - this.zzawz), j9);
            this.zzaxd += 8;
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, String str) {
            zzd(i9, 2);
            zzcz(str);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzsk zzskVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzskVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzc(byte b9) throws zzc {
            long j9 = this.zzaxd;
            if (j9 < this.zzaxb) {
                this.zzaxd = 1 + j9;
                zztx.zza(j9, b9);
                return;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzaxd), Long.valueOf(this.zzaxb), 1));
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zze(byte[] bArr, int i9, int i10) {
            zzay(i10);
            write(bArr, 0, i10);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzps zzpsVar) {
            zzd(i9, 2);
            zza(zzpsVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar) {
            zzd(i9, 2);
            zzb(zzskVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(int i9, zzps zzpsVar) {
            zzd(1, 3);
            zzf(2, i9);
            zza(3, zzpsVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(int i9, zzsk zzskVar, zzsz zzszVar) {
            zzd(i9, 2);
            zza(zzskVar, zzszVar);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzsk zzskVar, zzsz zzszVar) {
            zzpl zzplVar = (zzpl) zzskVar;
            int iZzmw = zzplVar.zzmw();
            if (iZzmw == -1) {
                iZzmw = zzszVar.zzad(zzplVar);
                zzplVar.zzag(iZzmw);
            }
            zzay(iZzmw);
            zzszVar.zza(zzskVar, this.zzawu);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zzb(zzsk zzskVar) {
            zzay(zzskVar.zzpe());
            zzskVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzqj
        public final void zza(zzps zzpsVar) {
            zzay(zzpsVar.size());
            zzpsVar.zza(this);
        }

        @Override // com.google.android.gms.internal.gtm.zzpr
        public final void zza(byte[] bArr, int i9, int i10) {
            write(bArr, i9, i10);
        }
    }

    public static int zzc(int i9, zzps zzpsVar) {
        int iZzbb = zzbb(i9);
        int size = zzpsVar.size();
        return iZzbb + zzbd(size) + size;
    }

    public static int zzh(byte[] bArr) {
        int length = bArr.length;
        return zzbd(length) + length;
    }

    public static int zzi(int i9, int i10) {
        return zzbb(i9) + zzbc(i10);
    }

    public final void zzb(double d9) {
        zzr(Double.doubleToRawLongBits(d9));
    }

    public static int zzb(int i9, float f9) {
        return zzbb(i9) + 4;
    }

    public static int zzd(int i9, zzsk zzskVar) {
        return (zzbb(1) << 1) + zzj(2, i9) + zzc(3, zzskVar);
    }

    public final void zzg(int i9, int i10) {
        zzf(i9, zzbi(i10));
    }

    public static int zzb(int i9, double d9) {
        return zzbb(i9) + 8;
    }

    public static int zzg(int i9, long j9) {
        return zzbb(i9) + 8;
    }

    public static int zzb(int i9, String str) {
        return zzbb(i9) + zzda(str);
    }

    public static int zzc(int i9, zzsk zzskVar) {
        return zzbb(i9) + zzc(zzskVar);
    }

    public static int zzb(int i9, zzsk zzskVar, zzsz zzszVar) {
        return zzbb(i9) + zzb(zzskVar, zzszVar);
    }

    public static int zzc(zzsk zzskVar) {
        int iZzpe = zzskVar.zzpe();
        return zzbd(iZzpe) + iZzpe;
    }

    public static int zzd(int i9, zzps zzpsVar) {
        return (zzbb(1) << 1) + zzj(2, i9) + zzc(3, zzpsVar);
    }

    public static int zzb(int i9, zzrr zzrrVar) {
        return (zzbb(1) << 1) + zzj(2, i9) + zza(3, zzrrVar);
    }

    @Deprecated
    public static int zzc(int i9, zzsk zzskVar, zzsz zzszVar) {
        int iZzbb = zzbb(i9) << 1;
        zzpl zzplVar = (zzpl) zzskVar;
        int iZzmw = zzplVar.zzmw();
        if (iZzmw == -1) {
            iZzmw = zzszVar.zzad(zzplVar);
            zzplVar.zzag(iZzmw);
        }
        return iZzbb + iZzmw;
    }

    public final void zza(int i9, float f9) {
        zzh(i9, Float.floatToRawIntBits(f9));
    }

    @Deprecated
    public static int zzd(zzsk zzskVar) {
        return zzskVar.zzpe();
    }

    public final void zza(int i9, double d9) {
        zzc(i9, Double.doubleToRawLongBits(d9));
    }

    public static int zzb(zzps zzpsVar) {
        int size = zzpsVar.size();
        return zzbd(size) + size;
    }

    public final void zza(float f9) {
        zzba(Float.floatToRawIntBits(f9));
    }

    public static int zza(int i9, zzrr zzrrVar) {
        int iZzbb = zzbb(i9);
        int iZzpe = zzrrVar.zzpe();
        return iZzbb + zzbd(iZzpe) + iZzpe;
    }

    public static int zzb(zzsk zzskVar, zzsz zzszVar) {
        zzpl zzplVar = (zzpl) zzskVar;
        int iZzmw = zzplVar.zzmw();
        if (iZzmw == -1) {
            iZzmw = zzszVar.zzad(zzplVar);
            zzplVar.zzag(iZzmw);
        }
        return zzbd(iZzmw) + iZzmw;
    }

    public static int zza(zzrr zzrrVar) {
        int iZzpe = zzrrVar.zzpe();
        return zzbd(iZzpe) + iZzpe;
    }

    public final void zza(String str, zzud zzudVar) throws zzc {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzudVar);
        byte[] bytes = str.getBytes(zzre.UTF_8);
        try {
            zzay(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zzc e9) {
            throw e9;
        } catch (IndexOutOfBoundsException e10) {
            throw new zzc(e10);
        }
    }
}
