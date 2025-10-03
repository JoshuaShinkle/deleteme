package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzc;

/* loaded from: classes2.dex */
public final class zzh extends zzuq<zzh> {
    public zzl[] zzpe = zzl.zzaa();
    public zzl[] zzpf = zzl.zzaa();
    public zzc.C6855zzc[] zzpg = new zzc.C6855zzc[0];

    public zzh() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzhVar = (zzh) obj;
        if (!zzuu.equals(this.zzpe, zzhVar.zzpe) || !zzuu.equals(this.zzpf, zzhVar.zzpf) || !zzuu.equals(this.zzpg, zzhVar.zzpg)) {
            return false;
        }
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            return this.zzbhb.equals(zzhVar.zzbhb);
        }
        zzus zzusVar2 = zzhVar.zzbhb;
        return zzusVar2 == null || zzusVar2.isEmpty();
    }

    public final int hashCode() {
        int iHashCode = (((((((zzh.class.getName().hashCode() + 527) * 31) + zzuu.hashCode(this.zzpe)) * 31) + zzuu.hashCode(this.zzpf)) * 31) + zzuu.hashCode(this.zzpg)) * 31;
        zzus zzusVar = this.zzbhb;
        return iHashCode + ((zzusVar == null || zzusVar.isEmpty()) ? 0 : this.zzbhb.hashCode());
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final void zza(zzuo zzuoVar) throws zzup {
        zzl[] zzlVarArr = this.zzpe;
        int i9 = 0;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i10 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzpe;
                if (i10 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i10];
                if (zzlVar != null) {
                    zzuoVar.zza(1, zzlVar);
                }
                i10++;
            }
        }
        zzl[] zzlVarArr3 = this.zzpf;
        if (zzlVarArr3 != null && zzlVarArr3.length > 0) {
            int i11 = 0;
            while (true) {
                zzl[] zzlVarArr4 = this.zzpf;
                if (i11 >= zzlVarArr4.length) {
                    break;
                }
                zzl zzlVar2 = zzlVarArr4[i11];
                if (zzlVar2 != null) {
                    zzuoVar.zza(2, zzlVar2);
                }
                i11++;
            }
        }
        zzc.C6855zzc[] c6855zzcArr = this.zzpg;
        if (c6855zzcArr != null && c6855zzcArr.length > 0) {
            while (true) {
                zzc.C6855zzc[] c6855zzcArr2 = this.zzpg;
                if (i9 >= c6855zzcArr2.length) {
                    break;
                }
                zzc.C6855zzc c6855zzc = c6855zzcArr2[i9];
                if (c6855zzc != null) {
                    zzuoVar.zze(3, c6855zzc);
                }
                i9++;
            }
        }
        super.zza(zzuoVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final int zzy() {
        int iZzy = super.zzy();
        zzl[] zzlVarArr = this.zzpe;
        int i9 = 0;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i10 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzpe;
                if (i10 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i10];
                if (zzlVar != null) {
                    iZzy += zzuo.zzb(1, zzlVar);
                }
                i10++;
            }
        }
        zzl[] zzlVarArr3 = this.zzpf;
        if (zzlVarArr3 != null && zzlVarArr3.length > 0) {
            int i11 = 0;
            while (true) {
                zzl[] zzlVarArr4 = this.zzpf;
                if (i11 >= zzlVarArr4.length) {
                    break;
                }
                zzl zzlVar2 = zzlVarArr4[i11];
                if (zzlVar2 != null) {
                    iZzy += zzuo.zzb(2, zzlVar2);
                }
                i11++;
            }
        }
        zzc.C6855zzc[] c6855zzcArr = this.zzpg;
        if (c6855zzcArr != null && c6855zzcArr.length > 0) {
            while (true) {
                zzc.C6855zzc[] c6855zzcArr2 = this.zzpg;
                if (i9 >= c6855zzcArr2.length) {
                    break;
                }
                zzc.C6855zzc c6855zzc = c6855zzcArr2[i9];
                if (c6855zzc != null) {
                    iZzy += zzqj.zzc(3, c6855zzc);
                }
                i9++;
            }
        }
        return iZzy;
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public final /* synthetic */ zzuw zza(zzun zzunVar) throws zzuv {
        while (true) {
            int iZzni = zzunVar.zzni();
            if (iZzni == 0) {
                return this;
            }
            if (iZzni == 10) {
                int iZzb = zzuz.zzb(zzunVar, 10);
                zzl[] zzlVarArr = this.zzpe;
                int length = zzlVarArr == null ? 0 : zzlVarArr.length;
                int i9 = iZzb + length;
                zzl[] zzlVarArr2 = new zzl[i9];
                if (length != 0) {
                    System.arraycopy(zzlVarArr, 0, zzlVarArr2, 0, length);
                }
                while (length < i9 - 1) {
                    zzl zzlVar = new zzl();
                    zzlVarArr2[length] = zzlVar;
                    zzunVar.zza(zzlVar);
                    zzunVar.zzni();
                    length++;
                }
                zzl zzlVar2 = new zzl();
                zzlVarArr2[length] = zzlVar2;
                zzunVar.zza(zzlVar2);
                this.zzpe = zzlVarArr2;
            } else if (iZzni == 18) {
                int iZzb2 = zzuz.zzb(zzunVar, 18);
                zzl[] zzlVarArr3 = this.zzpf;
                int length2 = zzlVarArr3 == null ? 0 : zzlVarArr3.length;
                int i10 = iZzb2 + length2;
                zzl[] zzlVarArr4 = new zzl[i10];
                if (length2 != 0) {
                    System.arraycopy(zzlVarArr3, 0, zzlVarArr4, 0, length2);
                }
                while (length2 < i10 - 1) {
                    zzl zzlVar3 = new zzl();
                    zzlVarArr4[length2] = zzlVar3;
                    zzunVar.zza(zzlVar3);
                    zzunVar.zzni();
                    length2++;
                }
                zzl zzlVar4 = new zzl();
                zzlVarArr4[length2] = zzlVar4;
                zzunVar.zza(zzlVar4);
                this.zzpf = zzlVarArr4;
            } else if (iZzni != 26) {
                if (!super.zza(zzunVar, iZzni)) {
                    return this;
                }
            } else {
                int iZzb3 = zzuz.zzb(zzunVar, 26);
                zzc.C6855zzc[] c6855zzcArr = this.zzpg;
                int length3 = c6855zzcArr == null ? 0 : c6855zzcArr.length;
                int i11 = iZzb3 + length3;
                zzc.C6855zzc[] c6855zzcArr2 = new zzc.C6855zzc[i11];
                if (length3 != 0) {
                    System.arraycopy(c6855zzcArr, 0, c6855zzcArr2, 0, length3);
                }
                while (length3 < i11 - 1) {
                    c6855zzcArr2[length3] = (zzc.C6855zzc) zzunVar.zza(zzc.C6855zzc.zza());
                    zzunVar.zzni();
                    length3++;
                }
                c6855zzcArr2[length3] = (zzc.C6855zzc) zzunVar.zza(zzc.C6855zzc.zza());
                this.zzpg = c6855zzcArr2;
            }
        }
    }
}
