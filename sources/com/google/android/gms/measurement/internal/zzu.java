package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzms;

/* loaded from: classes2.dex */
final class zzu extends zzv {
    private zzbv.zze zzg;
    private final /* synthetic */ zzo zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzu(zzo zzoVar, String str, int i9, zzbv.zze zzeVar) {
        super(str, i9);
        this.zzh = zzoVar;
        this.zzg = zzeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final int zza() {
        return this.zzg.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final boolean zzb() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final boolean zzc() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zza(Long l9, Long l10, zzcd.zzk zzkVar, boolean z8) {
        Object[] objArr = zzms.zzb() && this.zzh.zzs().zzd(this.zza, zzat.zzba);
        boolean zZze = this.zzg.zze();
        boolean zZzf = this.zzg.zzf();
        boolean zZzh = this.zzg.zzh();
        Object[] objArr2 = zZze || zZzf || zZzh;
        Boolean boolZza = null;
        boolZza = null;
        boolZza = null;
        boolZza = null;
        boolZza = null;
        if (z8 && objArr2 != true) {
            this.zzh.zzq().zzw().zza("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzb), this.zzg.zza() ? Integer.valueOf(this.zzg.zzb()) : null);
            return true;
        }
        zzbv.zzc zzcVarZzd = this.zzg.zzd();
        boolean zZzf2 = zzcVarZzd.zzf();
        if (zzkVar.zzf()) {
            if (zzcVarZzd.zzc()) {
                boolZza = zzv.zza(zzv.zza(zzkVar.zzg(), zzcVarZzd.zzd()), zZzf2);
            } else {
                this.zzh.zzq().zzh().zza("No number filter for long property. property", this.zzh.zzn().zzc(zzkVar.zzc()));
            }
        } else if (zzkVar.zzh()) {
            if (zzcVarZzd.zzc()) {
                boolZza = zzv.zza(zzv.zza(zzkVar.zzi(), zzcVarZzd.zzd()), zZzf2);
            } else {
                this.zzh.zzq().zzh().zza("No number filter for double property. property", this.zzh.zzn().zzc(zzkVar.zzc()));
            }
        } else if (!zzkVar.zzd()) {
            this.zzh.zzq().zzh().zza("User property has no value, property", this.zzh.zzn().zzc(zzkVar.zzc()));
        } else if (zzcVarZzd.zza()) {
            boolZza = zzv.zza(zzv.zza(zzkVar.zze(), zzcVarZzd.zzb(), this.zzh.zzq()), zZzf2);
        } else if (!zzcVarZzd.zzc()) {
            this.zzh.zzq().zzh().zza("No string or number filter defined. property", this.zzh.zzn().zzc(zzkVar.zzc()));
        } else if (zzkt.zza(zzkVar.zze())) {
            boolZza = zzv.zza(zzv.zza(zzkVar.zze(), zzcVarZzd.zzd()), zZzf2);
        } else {
            this.zzh.zzq().zzh().zza("Invalid user property value for Numeric number filter. property, value", this.zzh.zzn().zzc(zzkVar.zzc()), zzkVar.zze());
        }
        this.zzh.zzq().zzw().zza("Property filter result", boolZza == null ? "null" : boolZza);
        if (boolZza == null) {
            return false;
        }
        this.zzc = Boolean.TRUE;
        if (zZzh && !boolZza.booleanValue()) {
            return true;
        }
        if (!z8 || this.zzg.zze()) {
            this.zzd = boolZza;
        }
        if (boolZza.booleanValue() && objArr2 != false && zzkVar.zza()) {
            long jZzb = zzkVar.zzb();
            if (l9 != null) {
                jZzb = l9.longValue();
            }
            if (objArr != false && this.zzg.zze() && !this.zzg.zzf() && l10 != null) {
                jZzb = l10.longValue();
            }
            if (this.zzg.zzf()) {
                this.zzf = Long.valueOf(jZzb);
            } else {
                this.zze = Long.valueOf(jZzb);
            }
        }
        return true;
    }
}
