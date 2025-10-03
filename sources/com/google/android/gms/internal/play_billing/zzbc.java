package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
final class zzbc extends zzbe {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze;

    public /* synthetic */ zzbc(byte[] bArr, int i9, int i10, boolean z8, zzbb zzbbVar) {
        super(null);
        this.zze = Integer.MAX_VALUE;
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i9) {
        int i10 = this.zze;
        this.zze = 0;
        int i11 = this.zzc + this.zzd;
        this.zzc = i11;
        if (i11 > 0) {
            this.zzd = i11;
            this.zzc = i11 - i11;
        } else {
            this.zzd = 0;
        }
        return i10;
    }
}
