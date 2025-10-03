package com.google.android.gms.internal.measurement;

import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbl extends zzag.zzb {
    private final /* synthetic */ com.google.android.gms.measurement.internal.zzhc zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbl(zzag zzagVar, com.google.android.gms.measurement.internal.zzhc zzhcVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        for (int i9 = 0; i9 < this.zzd.zzf.size(); i9++) {
            if (this.zzc.equals(((Pair) this.zzd.zzf.get(i9)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzag.zzd zzdVar = new zzag.zzd(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzdVar));
        this.zzd.zzm.registerOnMeasurementEventListener(zzdVar);
    }
}
