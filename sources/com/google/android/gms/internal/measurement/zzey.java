package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes2.dex */
final class zzey extends zzew {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzew zzc;

    public zzey(zzew zzewVar, int i9, int i10) {
        this.zzc = zzewVar;
        this.zza = i9;
        this.zzb = i10;
    }

    @Override // java.util.List
    public final Object get(int i9) {
        zzdw.zza(i9, this.zzb);
        return this.zzc.get(i9 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzew, java.util.List
    public final /* synthetic */ List subList(int i9, int i10) {
        return subList(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzew
    /* renamed from: zza */
    public final zzew subList(int i9, int i10) {
        zzdw.zza(i9, i10, this.zzb);
        zzew zzewVar = this.zzc;
        int i11 = this.zza;
        return (zzew) zzewVar.subList(i9 + i11, i10 + i11);
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final Object[] zzd() {
        return this.zzc.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zze() {
        return this.zzc.zze() + this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzf() {
        return this.zzc.zze() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return true;
    }
}
