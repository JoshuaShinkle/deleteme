package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes2.dex */
final class zzae extends zzu {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzae(Object[] objArr, int i9, int i10) {
        this.zza = objArr;
        this.zzb = i9;
        this.zzc = i10;
    }

    @Override // java.util.List
    public final Object get(int i9) {
        zzm.zza(i9, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zza[i9 + i9 + this.zzb];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return true;
    }
}
