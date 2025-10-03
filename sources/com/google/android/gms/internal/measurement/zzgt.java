package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzgt extends zzgw {
    private final int zzc;
    private final int zzd;

    public zzgt(byte[] bArr, int i9, int i10) {
        super(bArr);
        zzgm.zzb(i9, i9 + i10, bArr.length);
        this.zzc = i9;
        this.zzd = i10;
    }

    @Override // com.google.android.gms.internal.measurement.zzgw, com.google.android.gms.internal.measurement.zzgm
    public final byte zza(int i9) {
        int iZza = zza();
        if (((iZza - (i9 + 1)) | i9) >= 0) {
            return this.zzb[this.zzc + i9];
        }
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i9);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i9);
        sb2.append(", ");
        sb2.append(iZza);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzgw, com.google.android.gms.internal.measurement.zzgm
    public final byte zzb(int i9) {
        return this.zzb[this.zzc + i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzgw
    public final int zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzgw, com.google.android.gms.internal.measurement.zzgm
    public final int zza() {
        return this.zzd;
    }
}
