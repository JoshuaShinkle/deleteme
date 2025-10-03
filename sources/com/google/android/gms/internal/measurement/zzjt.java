package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;

/* loaded from: classes2.dex */
final class zzjt implements zzje {
    private final zzjg zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public zzjt(zzjg zzjgVar, String str, Object[] objArr) {
        this.zza = zzjgVar;
        this.zzb = str;
        this.zzc = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.zzd = cCharAt;
            return;
        }
        int i9 = cCharAt & 8191;
        int i10 = 13;
        int i11 = 1;
        while (true) {
            int i12 = i11 + 1;
            char cCharAt2 = str.charAt(i11);
            if (cCharAt2 < 55296) {
                this.zzd = i9 | (cCharAt2 << i10);
                return;
            } else {
                i9 |= (cCharAt2 & 8191) << i10;
                i10 += 13;
                i11 = i12;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final int zza() {
        return (this.zzd & 1) == 1 ? zzhv.zze.zzh : zzhv.zze.zzi;
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.gms.internal.measurement.zzje
    public final zzjg zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
