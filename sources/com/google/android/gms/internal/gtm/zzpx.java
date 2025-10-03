package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzpx extends zzqc {
    private final int zzawb;
    private final int zzawc;

    public zzpx(byte[] bArr, int i9, int i10) {
        super(bArr);
        zzps.zzb(i9, i9 + i10, bArr.length);
        this.zzawb = i9;
        this.zzawc = i10;
    }

    @Override // com.google.android.gms.internal.gtm.zzqc, com.google.android.gms.internal.gtm.zzps
    public final int size() {
        return this.zzawc;
    }

    @Override // com.google.android.gms.internal.gtm.zzqc, com.google.android.gms.internal.gtm.zzps
    public final byte zzak(int i9) {
        int size = size();
        if (((size - (i9 + 1)) | i9) >= 0) {
            return this.zzawe[this.zzawb + i9];
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
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.gtm.zzqc, com.google.android.gms.internal.gtm.zzps
    public final byte zzal(int i9) {
        return this.zzawe[this.zzawb + i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzqc
    public final int zznf() {
        return this.zzawb;
    }
}
