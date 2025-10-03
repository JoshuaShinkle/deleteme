package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;

/* loaded from: classes2.dex */
class zzgw extends zzgx {
    protected final byte[] zzb;

    public zzgw(byte[] bArr) {
        bArr.getClass();
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgm) || zza() != ((zzgm) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (!(obj instanceof zzgw)) {
            return obj.equals(this);
        }
        zzgw zzgwVar = (zzgw) obj;
        int iZzd = zzd();
        int iZzd2 = zzgwVar.zzd();
        if (iZzd == 0 || iZzd2 == 0 || iZzd == iZzd2) {
            return zza(zzgwVar, 0, zza());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public byte zza(int i9) {
        return this.zzb[i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public byte zzb(int i9) {
        return this.zzb[i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final boolean zzc() {
        int iZze = zze();
        return zzkw.zza(this.zzb, iZze, zza() + iZze);
    }

    public int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final zzgm zza(int i9, int i10) {
        int iZzb = zzgm.zzb(0, i10, zza());
        if (iZzb == 0) {
            return zzgm.zza;
        }
        return new zzgt(this.zzb, zze(), iZzb);
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final void zza(zzgn zzgnVar) {
        zzgnVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean zza(zzgm zzgmVar, int i9, int i10) {
        if (i10 <= zzgmVar.zza()) {
            if (i10 <= zzgmVar.zza()) {
                if (zzgmVar instanceof zzgw) {
                    zzgw zzgwVar = (zzgw) zzgmVar;
                    byte[] bArr = this.zzb;
                    byte[] bArr2 = zzgwVar.zzb;
                    int iZze = zze() + i10;
                    int iZze2 = zze();
                    int iZze3 = zzgwVar.zze();
                    while (iZze2 < iZze) {
                        if (bArr[iZze2] != bArr2[iZze3]) {
                            return false;
                        }
                        iZze2++;
                        iZze3++;
                    }
                    return true;
                }
                return zzgmVar.zza(0, i10).equals(zza(0, i10));
            }
            int iZza = zzgmVar.zza();
            StringBuilder sb = new StringBuilder(59);
            sb.append("Ran off end of other: 0, ");
            sb.append(i10);
            sb.append(", ");
            sb.append(iZza);
            throw new IllegalArgumentException(sb.toString());
        }
        int iZza2 = zza();
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Length too large: ");
        sb2.append(i10);
        sb2.append(iZza2);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final int zza(int i9, int i10, int i11) {
        return zzhx.zza(i9, this.zzb, zze(), i11);
    }
}
