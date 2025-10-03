package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzfl<E> extends zzew<E> {
    static final zzew<Object> zza = new zzfl(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzfl(Object[] objArr, int i9) {
        this.zzb = objArr;
        this.zzc = i9;
    }

    @Override // java.util.List
    public final E get(int i9) {
        zzdw.zza(i9, this.zzc);
        return (E) this.zzb[i9];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzew, com.google.android.gms.internal.measurement.zzex
    public final int zzb(Object[] objArr, int i9) {
        System.arraycopy(this.zzb, 0, objArr, i9, this.zzc);
        return i9 + this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final Object[] zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return false;
    }
}
