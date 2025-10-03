package com.google.android.gms.internal.measurement;

import java.util.AbstractMap;

/* loaded from: classes2.dex */
final class zzfm extends zzew {
    private final /* synthetic */ zzfn zza;

    public zzfm(zzfn zzfnVar) {
        this.zza = zzfnVar;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzdw.zza(i9, this.zza.zzd);
        int i10 = i9 * 2;
        return new AbstractMap.SimpleImmutableEntry(this.zza.zzb[i10], this.zza.zzb[i10 + 1]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return true;
    }
}
