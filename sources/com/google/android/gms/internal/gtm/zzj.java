package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public final class zzj extends zzuq<zzj> {
    private static volatile zzj[] zzqg;
    public String name = "";
    private zzl zzqh = null;
    public zzh zzqi = null;

    public zzj() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public static zzj[] zzz() {
        if (zzqg == null) {
            synchronized (zzuu.zzbhk) {
                if (zzqg == null) {
                    zzqg = new zzj[0];
                }
            }
        }
        return zzqg;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzjVar = (zzj) obj;
        String str = this.name;
        if (str == null) {
            if (zzjVar.name != null) {
                return false;
            }
        } else if (!str.equals(zzjVar.name)) {
            return false;
        }
        zzl zzlVar = this.zzqh;
        if (zzlVar == null) {
            if (zzjVar.zzqh != null) {
                return false;
            }
        } else if (!zzlVar.equals(zzjVar.zzqh)) {
            return false;
        }
        zzh zzhVar = this.zzqi;
        if (zzhVar == null) {
            if (zzjVar.zzqi != null) {
                return false;
            }
        } else if (!zzhVar.equals(zzjVar.zzqi)) {
            return false;
        }
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            return this.zzbhb.equals(zzjVar.zzbhb);
        }
        zzus zzusVar2 = zzjVar.zzbhb;
        return zzusVar2 == null || zzusVar2.isEmpty();
    }

    public final int hashCode() {
        int iHashCode = (zzj.class.getName().hashCode() + 527) * 31;
        String str = this.name;
        int iHashCode2 = 0;
        int iHashCode3 = iHashCode + (str == null ? 0 : str.hashCode());
        zzl zzlVar = this.zzqh;
        int iHashCode4 = (iHashCode3 * 31) + (zzlVar == null ? 0 : zzlVar.hashCode());
        zzh zzhVar = this.zzqi;
        int iHashCode5 = ((iHashCode4 * 31) + (zzhVar == null ? 0 : zzhVar.hashCode())) * 31;
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            iHashCode2 = this.zzbhb.hashCode();
        }
        return iHashCode5 + iHashCode2;
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final void zza(zzuo zzuoVar) throws zzup {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzuoVar.zza(1, this.name);
        }
        zzl zzlVar = this.zzqh;
        if (zzlVar != null) {
            zzuoVar.zza(2, zzlVar);
        }
        zzh zzhVar = this.zzqi;
        if (zzhVar != null) {
            zzuoVar.zza(3, zzhVar);
        }
        super.zza(zzuoVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final int zzy() {
        int iZzy = super.zzy();
        String str = this.name;
        if (str != null && !str.equals("")) {
            iZzy += zzuo.zzb(1, this.name);
        }
        zzl zzlVar = this.zzqh;
        if (zzlVar != null) {
            iZzy += zzuo.zzb(2, zzlVar);
        }
        zzh zzhVar = this.zzqi;
        return zzhVar != null ? iZzy + zzuo.zzb(3, zzhVar) : iZzy;
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public final /* synthetic */ zzuw zza(zzun zzunVar) throws zzuv {
        while (true) {
            int iZzni = zzunVar.zzni();
            if (iZzni == 0) {
                return this;
            }
            if (iZzni == 10) {
                this.name = zzunVar.readString();
            } else if (iZzni == 18) {
                if (this.zzqh == null) {
                    this.zzqh = new zzl();
                }
                zzunVar.zza(this.zzqh);
            } else if (iZzni != 26) {
                if (!super.zza(zzunVar, iZzni)) {
                    return this;
                }
            } else {
                if (this.zzqi == null) {
                    this.zzqi = new zzh();
                }
                zzunVar.zza(this.zzqi);
            }
        }
    }
}
