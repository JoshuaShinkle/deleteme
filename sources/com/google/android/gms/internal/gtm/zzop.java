package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public final class zzop extends zzuq<zzop> {
    public long zzaux = 0;
    public zzi zzqk = null;
    public zzk zzauy = null;

    public zzop() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzop)) {
            return false;
        }
        zzop zzopVar = (zzop) obj;
        if (this.zzaux != zzopVar.zzaux) {
            return false;
        }
        zzi zziVar = this.zzqk;
        if (zziVar == null) {
            if (zzopVar.zzqk != null) {
                return false;
            }
        } else if (!zziVar.equals(zzopVar.zzqk)) {
            return false;
        }
        zzk zzkVar = this.zzauy;
        if (zzkVar == null) {
            if (zzopVar.zzauy != null) {
                return false;
            }
        } else if (!zzkVar.equals(zzopVar.zzauy)) {
            return false;
        }
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            return this.zzbhb.equals(zzopVar.zzbhb);
        }
        zzus zzusVar2 = zzopVar.zzbhb;
        return zzusVar2 == null || zzusVar2.isEmpty();
    }

    public final int hashCode() {
        int iHashCode = (zzop.class.getName().hashCode() + 527) * 31;
        long j9 = this.zzaux;
        int i9 = iHashCode + ((int) (j9 ^ (j9 >>> 32)));
        zzi zziVar = this.zzqk;
        int iHashCode2 = 0;
        int iHashCode3 = (i9 * 31) + (zziVar == null ? 0 : zziVar.hashCode());
        zzk zzkVar = this.zzauy;
        int iHashCode4 = ((iHashCode3 * 31) + (zzkVar == null ? 0 : zzkVar.hashCode())) * 31;
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            iHashCode2 = this.zzbhb.hashCode();
        }
        return iHashCode4 + iHashCode2;
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final void zza(zzuo zzuoVar) throws zzup {
        zzuoVar.zzi(1, this.zzaux);
        zzi zziVar = this.zzqk;
        if (zziVar != null) {
            zzuoVar.zza(2, zziVar);
        }
        zzk zzkVar = this.zzauy;
        if (zzkVar != null) {
            zzuoVar.zza(3, zzkVar);
        }
        super.zza(zzuoVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final int zzy() {
        int iZzy = super.zzy() + zzuo.zzd(1, this.zzaux);
        zzi zziVar = this.zzqk;
        if (zziVar != null) {
            iZzy += zzuo.zzb(2, zziVar);
        }
        zzk zzkVar = this.zzauy;
        return zzkVar != null ? iZzy + zzuo.zzb(3, zzkVar) : iZzy;
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public final /* synthetic */ zzuw zza(zzun zzunVar) throws zzuv {
        while (true) {
            int iZzni = zzunVar.zzni();
            if (iZzni == 0) {
                return this;
            }
            if (iZzni == 8) {
                this.zzaux = zzunVar.zzob();
            } else if (iZzni == 18) {
                if (this.zzqk == null) {
                    this.zzqk = new zzi();
                }
                zzunVar.zza(this.zzqk);
            } else if (iZzni != 26) {
                if (!super.zza(zzunVar, iZzni)) {
                    return this;
                }
            } else {
                if (this.zzauy == null) {
                    this.zzauy = new zzk();
                }
                zzunVar.zza(this.zzauy);
            }
        }
    }
}
