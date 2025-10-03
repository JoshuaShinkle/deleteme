package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzkp extends zzkn<zzkq, zzkq> {
    /* renamed from: zza, reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzkq zzkqVar) {
        ((zzhv) obj).zzb = zzkqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final boolean zza(zzjw zzjwVar) {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zzb(zzkq zzkqVar, zzlk zzlkVar) {
        zzkqVar.zza(zzlkVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ zzkq zzc(zzkq zzkqVar, zzkq zzkqVar2) {
        zzkq zzkqVar3 = zzkqVar;
        zzkq zzkqVar4 = zzkqVar2;
        return zzkqVar4.equals(zzkq.zza()) ? zzkqVar3 : zzkq.zza(zzkqVar3, zzkqVar4);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final void zzd(Object obj) {
        ((zzhv) obj).zzb.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ int zze(zzkq zzkqVar) {
        return zzkqVar.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ int zzf(zzkq zzkqVar) {
        return zzkqVar.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zza(zzkq zzkqVar, zzlk zzlkVar) {
        zzkqVar.zzb(zzlkVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zzb(Object obj, zzkq zzkqVar) {
        zza2(obj, zzkqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* bridge */ /* synthetic */ void zza(Object obj, zzkq zzkqVar) {
        zza2(obj, zzkqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ zzkq zzc(Object obj) {
        zzkq zzkqVar = ((zzhv) obj).zzb;
        if (zzkqVar != zzkq.zza()) {
            return zzkqVar;
        }
        zzkq zzkqVarZzb = zzkq.zzb();
        zza2(obj, zzkqVarZzb);
        return zzkqVarZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ zzkq zza(zzkq zzkqVar) {
        zzkq zzkqVar2 = zzkqVar;
        zzkqVar2.zzc();
        return zzkqVar2;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ zzkq zzb(Object obj) {
        return ((zzhv) obj).zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zzb(zzkq zzkqVar, int i9, long j9) {
        zzkqVar.zza((i9 << 3) | 1, Long.valueOf(j9));
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ zzkq zza() {
        return zzkq.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zza(zzkq zzkqVar, int i9, zzkq zzkqVar2) {
        zzkqVar.zza((i9 << 3) | 3, zzkqVar2);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zza(zzkq zzkqVar, int i9, zzgm zzgmVar) {
        zzkqVar.zza((i9 << 3) | 2, zzgmVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zza(zzkq zzkqVar, int i9, int i10) {
        zzkqVar.zza((i9 << 3) | 5, Integer.valueOf(i10));
    }

    @Override // com.google.android.gms.internal.measurement.zzkn
    public final /* synthetic */ void zza(zzkq zzkqVar, int i9, long j9) {
        zzkqVar.zza(i9 << 3, Long.valueOf(j9));
    }
}
