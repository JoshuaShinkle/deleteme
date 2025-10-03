package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* loaded from: classes2.dex */
final class zzab extends zzu {
    final /* synthetic */ zzac zza;

    public zzab(zzac zzacVar) {
        this.zza = zzacVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i9) {
        zzm.zza(i9, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        zzac zzacVar = this.zza;
        int i10 = i9 + i9;
        Object obj = zzacVar.zzb[i10];
        obj.getClass();
        Object obj2 = zzacVar.zzb[i10 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return true;
    }
}
