package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzgu {
    private final zzhf zza;
    private final byte[] zzb;

    private zzgu(int i9) {
        byte[] bArr = new byte[i9];
        this.zzb = bArr;
        this.zza = zzhf.zza(bArr);
    }

    public final zzgm zza() {
        this.zza.zzb();
        return new zzgw(this.zzb);
    }

    public final zzhf zzb() {
        return this.zza;
    }

    public /* synthetic */ zzgu(int i9, zzgp zzgpVar) {
        this(i9);
    }
}
