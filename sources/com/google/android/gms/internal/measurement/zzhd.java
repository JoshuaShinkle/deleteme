package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzhd implements zzjw {
    private final zzgy zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzhd(zzgy zzgyVar) {
        zzgy zzgyVar2 = (zzgy) zzhx.zza(zzgyVar, "input");
        this.zza = zzgyVar2;
        zzgyVar2.zzc = this;
    }

    public static zzhd zza(zzgy zzgyVar) {
        zzhd zzhdVar = zzgyVar.zzc;
        return zzhdVar != null ? zzhdVar : new zzhd(zzgyVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final boolean zzc() {
        int i9;
        if (this.zza.zzt() || (i9 = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzb(i9);
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final double zzd() throws zzif {
        zza(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final float zze() throws zzif {
        zza(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final long zzf() throws zzif {
        zza(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final long zzg() throws zzif {
        zza(0);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzh() throws zzif {
        zza(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final long zzi() throws zzif {
        zza(1);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzj() throws zzif {
        zza(5);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final boolean zzk() throws zzif {
        zza(0);
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final String zzl() throws zzif {
        zza(2);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final String zzm() throws zzif {
        zza(2);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final zzgm zzn() throws zzif {
        zza(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzo() throws zzif {
        zza(0);
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzp() throws zzif {
        zza(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzq() throws zzif {
        zza(5);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final long zzr() throws zzif {
        zza(1);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zzs() throws zzif {
        zza(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final long zzt() throws zzif {
        zza(0);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final <T> T zzb(zzjv<T> zzjvVar, zzhi zzhiVar) throws zzif {
        zza(3);
        return (T) zzd(zzjvVar, zzhiVar);
    }

    private final <T> T zzc(zzjv<T> zzjvVar, zzhi zzhiVar) throws zzig {
        int iZzm = this.zza.zzm();
        zzgy zzgyVar = this.zza;
        if (zzgyVar.zza < zzgyVar.zzb) {
            int iZzc = zzgyVar.zzc(iZzm);
            T tZza = zzjvVar.zza();
            this.zza.zza++;
            zzjvVar.zza(tZza, this, zzhiVar);
            zzjvVar.zzc(tZza);
            this.zza.zza(0);
            r5.zza--;
            this.zza.zzd(iZzc);
            return tZza;
        }
        throw new zzig("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzjv<T> zzjvVar, zzhi zzhiVar) {
        int i9 = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T tZza = zzjvVar.zza();
            zzjvVar.zza(tZza, this, zzhiVar);
            zzjvVar.zzc(tZza);
            if (this.zzb == this.zzc) {
                return tZza;
            }
            throw zzig.zzg();
        } finally {
            this.zzc = i9;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final int zza() {
        int i9 = this.zzd;
        if (i9 != 0) {
            this.zzb = i9;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zza();
        }
        int i10 = this.zzb;
        if (i10 == 0 || i10 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i10 >>> 3;
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zze(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zzhyVar.zzd(this.zza.zzf());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhyVar.zzd(this.zza.zzf());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzf(List<Long> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i9 = this.zzb & 7;
            if (i9 == 1) {
                do {
                    zziuVar.zza(this.zza.zzg());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zziuVar.zza(this.zza.zzg());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzg()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzg()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzg(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhyVar.zzd(this.zza.zzh());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i9 == 5) {
                do {
                    zzhyVar.zzd(this.zza.zzh());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i10 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzh(List<Boolean> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzgk) {
            zzgk zzgkVar = (zzgk) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zzgkVar.zza(this.zza.zzi());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgkVar.zza(this.zza.zzi());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzi(List<String> list) throws zzif {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzj(List<String> list) throws zzif {
        zza(list, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzk(List<zzgm> list) throws zzif {
        int iZza;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzn());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzl(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zzhyVar.zzd(this.zza.zzm());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhyVar.zzd(this.zza.zzm());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzm(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zzhyVar.zzd(this.zza.zzn());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhyVar.zzd(this.zza.zzn());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzn(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhyVar.zzd(this.zza.zzo());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i9 == 5) {
                do {
                    zzhyVar.zzd(this.zza.zzo());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i10 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzo(List<Long> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i9 = this.zzb & 7;
            if (i9 == 1) {
                do {
                    zziuVar.zza(this.zza.zzp());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zziuVar.zza(this.zza.zzp());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzp()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzp()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzp(List<Integer> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zzhyVar.zzd(this.zza.zzq());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhyVar.zzd(this.zza.zzq());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzq(List<Long> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zziuVar.zza(this.zza.zzr());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zzr());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzr()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzr()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzb(List<Float> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhu) {
            zzhu zzhuVar = (zzhu) list;
            int i9 = this.zzb & 7;
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhuVar.zza(this.zza.zzc());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i9 == 5) {
                do {
                    zzhuVar.zza(this.zza.zzc());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Float.valueOf(this.zza.zzc()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i10 == 5) {
            do {
                list.add(Float.valueOf(this.zza.zzc()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    private final void zza(int i9) throws zzif {
        if ((this.zzb & 7) != i9) {
            throw zzig.zzf();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final <T> T zza(zzjv<T> zzjvVar, zzhi zzhiVar) throws zzif {
        zza(2);
        return (T) zzc(zzjvVar, zzhiVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zza(List<Double> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            int i9 = this.zzb & 7;
            if (i9 == 1) {
                do {
                    zzhgVar.zza(this.zza.zzb());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhgVar.zza(this.zza.zzb());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 1) {
            do {
                list.add(Double.valueOf(this.zza.zzb()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Double.valueOf(this.zza.zzb()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzd(List<Long> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zziuVar.zza(this.zza.zze());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zze());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zze()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zze()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjw
    public final void zzc(List<Long> list) throws zzig {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i9 = this.zzb & 7;
            if (i9 == 0) {
                do {
                    zziuVar.zza(this.zza.zzd());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i9 == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zzd());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i10 = this.zzb & 7;
        if (i10 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzd()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i10 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzd()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzjw
    public final <T> void zzb(List<T> list, zzjv<T> zzjvVar, zzhi zzhiVar) throws zzif {
        int iZza;
        int i9 = this.zzb;
        if ((i9 & 7) == 3) {
            do {
                list.add(zzd(zzjvVar, zzhiVar));
                if (this.zza.zzt() || this.zzd != 0) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == i9);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    private static void zzb(int i9) throws zzig {
        if ((i9 & 7) != 0) {
            throw zzig.zzg();
        }
    }

    private final void zza(List<String> list, boolean z8) throws zzif {
        int iZza;
        int iZza2;
        if ((this.zzb & 7) == 2) {
            if ((list instanceof zzin) && !z8) {
                zzin zzinVar = (zzin) list;
                do {
                    zzinVar.zza(zzn());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            do {
                list.add(z8 ? zzm() : zzl());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    private final void zzd(int i9) throws zzig {
        if (this.zza.zzu() != i9) {
            throw zzig.zza();
        }
    }

    private static void zzc(int i9) throws zzig {
        if ((i9 & 3) != 0) {
            throw zzig.zzg();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzjw
    public final <T> void zza(List<T> list, zzjv<T> zzjvVar, zzhi zzhiVar) throws zzif {
        int iZza;
        int i9 = this.zzb;
        if ((i9 & 7) == 2) {
            do {
                list.add(zzc(zzjvVar, zzhiVar));
                if (this.zza.zzt() || this.zzd != 0) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == i9);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0063, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzjw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final <K, V> void zza(Map<K, V> map, zzix<K, V> zzixVar, zzhi zzhiVar) throws zzif {
        zza(2);
        int iZzc = this.zza.zzc(this.zza.zzm());
        Object objZza = zzixVar.zzb;
        Object objZza2 = zzixVar.zzd;
        while (true) {
            try {
                int iZza = zza();
                if (iZza == Integer.MAX_VALUE || this.zza.zzt()) {
                    break;
                }
                if (iZza == 1) {
                    objZza = zza(zzixVar.zza, (Class<?>) null, (zzhi) null);
                } else if (iZza != 2) {
                    try {
                        if (!zzc()) {
                            throw new zzig("Unable to parse map entry.");
                        }
                    } catch (zzif unused) {
                        if (!zzc()) {
                            throw new zzig("Unable to parse map entry.");
                        }
                    }
                } else {
                    objZza2 = zza(zzixVar.zzc, zzixVar.zzd.getClass(), zzhiVar);
                }
            } finally {
                this.zza.zzd(iZzc);
            }
        }
    }

    private final Object zza(zzle zzleVar, Class<?> cls, zzhi zzhiVar) throws zzif {
        switch (zzhc.zza[zzleVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzk());
            case 2:
                return zzn();
            case 3:
                return Double.valueOf(zzd());
            case 4:
                return Integer.valueOf(zzp());
            case 5:
                return Integer.valueOf(zzj());
            case 6:
                return Long.valueOf(zzi());
            case 7:
                return Float.valueOf(zze());
            case 8:
                return Integer.valueOf(zzh());
            case 9:
                return Long.valueOf(zzg());
            case 10:
                zza(2);
                return zzc(zzjr.zza().zza((Class) cls), zzhiVar);
            case 11:
                return Integer.valueOf(zzq());
            case 12:
                return Long.valueOf(zzr());
            case 13:
                return Integer.valueOf(zzs());
            case 14:
                return Long.valueOf(zzt());
            case 15:
                return zzm();
            case 16:
                return Integer.valueOf(zzo());
            case 17:
                return Long.valueOf(zzf());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }
}
