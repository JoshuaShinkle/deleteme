package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
final class zzau extends zzax {
    private final int zzc;

    public zzau(byte[] bArr, int i9, int i10) {
        super(bArr);
        zzba.zzj(0, i10, bArr.length);
        this.zzc = i10;
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    public final byte zza(int i9) {
        int i10 = this.zzc;
        if (((i10 - (i9 + 1)) | i9) >= 0) {
            return this.zza[i9];
        }
        if (i9 < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i9);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i9 + ", " + i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    public final byte zzb(int i9) {
        return this.zza[i9];
    }

    @Override // com.google.android.gms.internal.play_billing.zzax
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzax, com.google.android.gms.internal.play_billing.zzba
    public final int zzd() {
        return this.zzc;
    }
}
