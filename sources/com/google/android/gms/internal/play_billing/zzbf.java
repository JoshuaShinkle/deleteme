package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
final class zzbf extends zzbi {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    public zzbf(byte[] bArr, int i9, int i10) {
        super(null);
        int length = bArr.length;
        if (((length - i10) | i10) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i10)));
        }
        this.zzc = bArr;
        this.zze = 0;
        this.zzd = i10;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final int zza() {
        return this.zzd - this.zze;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzb(byte b9) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i9 = this.zze;
            this.zze = i9 + 1;
            bArr[i9] = b9;
        } catch (IndexOutOfBoundsException e9) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
        }
    }

    public final void zzc(byte[] bArr, int i9, int i10) {
        try {
            System.arraycopy(bArr, 0, this.zzc, this.zze, i10);
            this.zze += i10;
        } catch (IndexOutOfBoundsException e9) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i10)), e9);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzd(int i9, boolean z8) throws zzbg {
        zzq(i9 << 3);
        zzb(z8 ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zze(int i9, zzba zzbaVar) {
        zzq((i9 << 3) | 2);
        zzq(zzbaVar.zzd());
        zzbaVar.zzh(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzf(int i9, int i10) throws zzbg {
        zzq((i9 << 3) | 5);
        zzg(i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzg(int i9) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i10 = this.zze;
            int i11 = i10 + 1;
            bArr[i10] = (byte) (i9 & 255);
            int i12 = i11 + 1;
            bArr[i11] = (byte) ((i9 >> 8) & 255);
            int i13 = i12 + 1;
            bArr[i12] = (byte) ((i9 >> 16) & 255);
            this.zze = i13 + 1;
            bArr[i13] = (byte) ((i9 >> 24) & 255);
        } catch (IndexOutOfBoundsException e9) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzh(int i9, long j9) throws zzbg {
        zzq((i9 << 3) | 1);
        zzi(j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzi(long j9) throws zzbg {
        try {
            byte[] bArr = this.zzc;
            int i9 = this.zze;
            int i10 = i9 + 1;
            bArr[i9] = (byte) (((int) j9) & 255);
            int i11 = i10 + 1;
            bArr[i10] = (byte) (((int) (j9 >> 8)) & 255);
            int i12 = i11 + 1;
            bArr[i11] = (byte) (((int) (j9 >> 16)) & 255);
            int i13 = i12 + 1;
            bArr[i12] = (byte) (((int) (j9 >> 24)) & 255);
            int i14 = i13 + 1;
            bArr[i13] = (byte) (((int) (j9 >> 32)) & 255);
            int i15 = i14 + 1;
            bArr[i14] = (byte) (((int) (j9 >> 40)) & 255);
            int i16 = i15 + 1;
            bArr[i15] = (byte) (((int) (j9 >> 48)) & 255);
            this.zze = i16 + 1;
            bArr[i16] = (byte) (((int) (j9 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e9) {
            throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzj(int i9, int i10) throws zzbg {
        zzq(i9 << 3);
        zzk(i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzk(int i9) throws zzbg {
        if (i9 >= 0) {
            zzq(i9);
        } else {
            zzs(i9);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzl(byte[] bArr, int i9, int i10) {
        zzc(bArr, 0, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzm(int i9, String str) throws zzbg {
        zzq((i9 << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) throws zzbg {
        int i9 = this.zze;
        try {
            int iZzx = zzbi.zzx(str.length() * 3);
            int iZzx2 = zzbi.zzx(str.length());
            if (iZzx2 != iZzx) {
                zzq(zzev.zzc(str));
                byte[] bArr = this.zzc;
                int i10 = this.zze;
                this.zze = zzev.zzb(str, bArr, i10, this.zzd - i10);
                return;
            }
            int i11 = i9 + iZzx2;
            this.zze = i11;
            int iZzb = zzev.zzb(str, this.zzc, i11, this.zzd - i11);
            this.zze = i9;
            zzq((iZzb - i9) - iZzx2);
            this.zze = iZzb;
        } catch (zzeu e9) {
            this.zze = i9;
            zzB(str, e9);
        } catch (IndexOutOfBoundsException e10) {
            throw new zzbg(e10);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzo(int i9, int i10) {
        zzq((i9 << 3) | i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzp(int i9, int i10) {
        zzq(i9 << 3);
        zzq(i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzq(int i9) {
        while ((i9 & (-128)) != 0) {
            try {
                byte[] bArr = this.zzc;
                int i10 = this.zze;
                this.zze = i10 + 1;
                bArr[i10] = (byte) ((i9 & 127) | 128);
                i9 >>>= 7;
            } catch (IndexOutOfBoundsException e9) {
                throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
            }
        }
        byte[] bArr2 = this.zzc;
        int i11 = this.zze;
        this.zze = i11 + 1;
        bArr2[i11] = (byte) i9;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzr(int i9, long j9) throws zzbg {
        zzq(i9 << 3);
        zzs(j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbi
    public final void zzs(long j9) throws zzbg {
        if (zzbi.zzd && this.zzd - this.zze >= 10) {
            while ((j9 & (-128)) != 0) {
                byte[] bArr = this.zzc;
                int i9 = this.zze;
                this.zze = i9 + 1;
                zzeq.zzn(bArr, i9, (byte) ((((int) j9) & 127) | 128));
                j9 >>>= 7;
            }
            byte[] bArr2 = this.zzc;
            int i10 = this.zze;
            this.zze = i10 + 1;
            zzeq.zzn(bArr2, i10, (byte) j9);
            return;
        }
        while ((j9 & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zzc;
                int i11 = this.zze;
                this.zze = i11 + 1;
                bArr3[i11] = (byte) ((((int) j9) & 127) | 128);
                j9 >>>= 7;
            } catch (IndexOutOfBoundsException e9) {
                throw new zzbg(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e9);
            }
        }
        byte[] bArr4 = this.zzc;
        int i12 = this.zze;
        this.zze = i12 + 1;
        bArr4[i12] = (byte) j9;
    }
}
