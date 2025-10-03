package com.google.android.gms.internal.play_billing;

import java.nio.charset.Charset;

/* loaded from: classes2.dex */
class zzax extends zzaw {
    protected final byte[] zza;

    public zzax(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzba) || zzd() != ((zzba) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzax)) {
            return obj.equals(this);
        }
        zzax zzaxVar = (zzax) obj;
        int iZzk = zzk();
        int iZzk2 = zzaxVar.zzk();
        if (iZzk != 0 && iZzk2 != 0 && iZzk != iZzk2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzaxVar.zzd()) {
            throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
        }
        if (iZzd > zzaxVar.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zzaxVar.zzd());
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzaxVar.zza;
        zzaxVar.zzc();
        int i9 = 0;
        int i10 = 0;
        while (i9 < iZzd) {
            if (bArr[i9] != bArr2[i10]) {
                return false;
            }
            i9++;
            i10++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public byte zza(int i9) {
        return this.zza[i9];
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public byte zzb(int i9) {
        return this.zza[i9];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final int zze(int i9, int i10, int i11) {
        return zzcg.zzb(i9, this.zza, 0, i11);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final zzba zzf(int i9, int i10) {
        int iZzj = zzba.zzj(0, i10, zzd());
        return iZzj == 0 ? zzba.zzb : new zzau(this.zza, 0, iZzj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final void zzh(zzaq zzaqVar) {
        ((zzbf) zzaqVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final boolean zzi() {
        return zzev.zze(this.zza, 0, zzd());
    }
}
