package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzen extends zzep {
    public zzen(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final double zza(Object obj, long j9) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final float zzb(Object obj, long j9) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzc(Object obj, long j9, boolean z8) {
        if (zzeq.zzb) {
            zzeq.zzD(obj, j9, z8 ? (byte) 1 : (byte) 0);
        } else {
            zzeq.zzE(obj, j9, z8 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzd(Object obj, long j9, byte b9) {
        if (zzeq.zzb) {
            zzeq.zzD(obj, j9, b9);
        } else {
            zzeq.zzE(obj, j9, b9);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zze(Object obj, long j9, double d9) {
        this.zza.putLong(obj, j9, Double.doubleToLongBits(d9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzf(Object obj, long j9, float f9) {
        this.zza.putInt(obj, j9, Float.floatToIntBits(f9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final boolean zzg(Object obj, long j9) {
        return zzeq.zzb ? zzeq.zzt(obj, j9) : zzeq.zzu(obj, j9);
    }
}
