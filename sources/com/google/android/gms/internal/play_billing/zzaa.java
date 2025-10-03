package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes2.dex */
final class zzaa extends zzu {
    static final zzu zza = new zzaa(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    public zzaa(Object[] objArr, int i9) {
        this.zzb = objArr;
        this.zzc = i9;
    }

    @Override // java.util.List
    public final Object get(int i9) {
        zzm.zza(i9, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zzb[i9];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzu, com.google.android.gms.internal.play_billing.zzr
    public final int zza(Object[] objArr, int i9) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final Object[] zzg() {
        return this.zzb;
    }
}
