package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes2.dex */
final class zzt extends zzw {
    final /* synthetic */ zzu zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzt(zzu zzuVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.zza = zzuVar;
    }

    @Override // com.google.android.gms.internal.common.zzw
    public final int zzc(int i9) {
        return i9 + 1;
    }

    @Override // com.google.android.gms.internal.common.zzw
    public final int zzd(int i9) {
        zzo zzoVar = this.zza.zza;
        CharSequence charSequence = ((zzw) this).zzb;
        int length = charSequence.length();
        zzs.zzb(i9, length, FirebaseAnalytics.Param.INDEX);
        while (i9 < length) {
            if (zzoVar.zza(charSequence.charAt(i9))) {
                return i9;
            }
            i9++;
        }
        return -1;
    }
}
