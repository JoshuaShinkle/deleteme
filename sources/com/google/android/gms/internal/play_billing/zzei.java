package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
final class zzei extends zzeg {
    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* synthetic */ int zza(Object obj) {
        return ((zzeh) obj).zza();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzeh) obj).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzcb zzcbVar = (zzcb) obj;
        zzeh zzehVar = zzcbVar.zzc;
        if (zzehVar != zzeh.zzc()) {
            return zzehVar;
        }
        zzeh zzehVarZzf = zzeh.zzf();
        zzcbVar.zzc = zzehVarZzf;
        return zzehVarZzf;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzcb) obj).zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzeh.zzc().equals(obj2)) {
            return obj;
        }
        if (zzeh.zzc().equals(obj)) {
            return zzeh.zze((zzeh) obj, (zzeh) obj2);
        }
        ((zzeh) obj).zzd((zzeh) obj2);
        return obj;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* bridge */ /* synthetic */ void zzf(Object obj, int i9, long j9) {
        ((zzeh) obj).zzj(i9 << 3, Long.valueOf(j9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final void zzg(Object obj) {
        ((zzcb) obj).zzc.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzcb) obj).zzc = (zzeh) obj2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzeg
    public final /* synthetic */ void zzi(Object obj, zzey zzeyVar) {
        ((zzeh) obj).zzk(zzeyVar);
    }
}
