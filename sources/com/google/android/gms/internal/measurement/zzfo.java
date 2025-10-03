package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzfo extends zzew<Object> {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzfo(Object[] objArr, int i9, int i10) {
        this.zza = objArr;
        this.zzb = i9;
        this.zzc = i10;
    }

    @Override // java.util.List
    public final Object get(int i9) {
        zzdw.zza(i9, this.zzc);
        return this.zza[(i9 * 2) + this.zzb];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return true;
    }
}
