package com.google.android.gms.internal.measurement;

import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbk extends zzag.zzb {
    private final /* synthetic */ com.google.android.gms.measurement.internal.zzhc zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbk(zzag zzagVar, com.google.android.gms.measurement.internal.zzhc zzhcVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        Pair pair;
        int i9 = 0;
        while (true) {
            if (i9 >= this.zzd.zzf.size()) {
                pair = null;
                break;
            } else {
                if (this.zzc.equals(((Pair) this.zzd.zzf.get(i9)).first)) {
                    pair = (Pair) this.zzd.zzf.get(i9);
                    break;
                }
                i9++;
            }
        }
        if (pair == null) {
            Log.w(this.zzd.zzc, "OnEventListener had not been registered.");
        } else {
            this.zzd.zzm.unregisterOnMeasurementEventListener((zzab) pair.second);
            this.zzd.zzf.remove(pair);
        }
    }
}
