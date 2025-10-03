package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzjm<T> implements zzjv<T> {
    private final zzjg zza;
    private final zzkn<?, ?> zzb;
    private final boolean zzc;
    private final zzhk<?> zzd;

    private zzjm(zzkn<?, ?> zzknVar, zzhk<?> zzhkVar, zzjg zzjgVar) {
        this.zzb = zzknVar;
        this.zzc = zzhkVar.zza(zzjgVar);
        this.zzd = zzhkVar;
        this.zza = zzjgVar;
    }

    public static <T> zzjm<T> zza(zzkn<?, ?> zzknVar, zzhk<?> zzhkVar, zzjg zzjgVar) {
        return new zzjm<>(zzknVar, zzhkVar, zzjgVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final void zzb(T t8, T t9) {
        zzjx.zza(this.zzb, t8, t9);
        if (this.zzc) {
            zzjx.zza(this.zzd, t8, t9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final void zzc(T t8) {
        this.zzb.zzd(t8);
        this.zzd.zzc(t8);
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final boolean zzd(T t8) {
        return this.zzd.zza(t8).zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final T zza() {
        return (T) this.zza.zzbu().zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final boolean zza(T t8, T t9) {
        if (!this.zzb.zzb(t8).equals(this.zzb.zzb(t9))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t8).equals(this.zzd.zza(t9));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final int zzb(T t8) {
        zzkn<?, ?> zzknVar = this.zzb;
        int iZze = zzknVar.zze(zzknVar.zzb(t8)) + 0;
        return this.zzc ? iZze + this.zzd.zza(t8).zzg() : iZze;
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final int zza(T t8) {
        int iHashCode = this.zzb.zzb(t8).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zza(t8).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final void zza(T t8, zzlk zzlkVar) {
        Iterator itZzd = this.zzd.zza(t8).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzhq zzhqVar = (zzhq) entry.getKey();
            if (zzhqVar.zzc() == zzlh.MESSAGE && !zzhqVar.zzd() && !zzhqVar.zze()) {
                if (entry instanceof zzij) {
                    zzlkVar.zza(zzhqVar.zza(), (Object) ((zzij) entry).zza().zzc());
                } else {
                    zzlkVar.zza(zzhqVar.zza(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        zzkn<?, ?> zzknVar = this.zzb;
        zzknVar.zzb((zzkn<?, ?>) zzknVar.zzb(t8), zzlkVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0099 A[EDGE_INSN: B:56:0x0099->B:34:0x0099 BREAK  A[LOOP:1: B:18:0x0053->B:61:0x0053], SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, byte[] bArr, int i9, int i10, zzgl zzglVar) throws zzig {
        zzhv zzhvVar = (zzhv) t8;
        zzkq zzkqVarZzb = zzhvVar.zzb;
        if (zzkqVarZzb == zzkq.zza()) {
            zzkqVarZzb = zzkq.zzb();
            zzhvVar.zzb = zzkqVarZzb;
        }
        ((zzhv.zzd) t8).zza();
        zzhv.zzf zzfVar = null;
        while (i9 < i10) {
            int iZza = zzgi.zza(bArr, i9, zzglVar);
            int i11 = zzglVar.zza;
            if (i11 == 11) {
                int i12 = 0;
                zzgm zzgmVar = null;
                while (iZza < i10) {
                    iZza = zzgi.zza(bArr, iZza, zzglVar);
                    int i13 = zzglVar.zza;
                    int i14 = i13 >>> 3;
                    int i15 = i13 & 7;
                    if (i14 != 2) {
                        if (i14 == 3) {
                            if (zzfVar != null) {
                                zzjr.zza();
                                throw new NoSuchMethodError();
                            }
                            if (i15 == 2) {
                                iZza = zzgi.zze(bArr, iZza, zzglVar);
                                zzgmVar = (zzgm) zzglVar.zzc;
                            }
                        }
                        if (i13 != 12) {
                            break;
                        } else {
                            iZza = zzgi.zza(i13, bArr, iZza, i10, zzglVar);
                        }
                    } else if (i15 == 0) {
                        iZza = zzgi.zza(bArr, iZza, zzglVar);
                        i12 = zzglVar.zza;
                        zzfVar = (zzhv.zzf) this.zzd.zza(zzglVar.zzd, this.zza, i12);
                    } else if (i13 != 12) {
                    }
                }
                if (zzgmVar != null) {
                    zzkqVarZzb.zza((i12 << 3) | 2, zzgmVar);
                }
                i9 = iZza;
            } else if ((i11 & 7) == 2) {
                zzfVar = (zzhv.zzf) this.zzd.zza(zzglVar.zzd, this.zza, i11 >>> 3);
                if (zzfVar == null) {
                    i9 = zzgi.zza(i11, bArr, iZza, i10, zzkqVarZzb, zzglVar);
                } else {
                    zzjr.zza();
                    throw new NoSuchMethodError();
                }
            } else {
                i9 = zzgi.zza(i11, bArr, iZza, i10, zzglVar);
            }
        }
        if (i9 != i10) {
            throw zzig.zzg();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:0: B:45:0x000c->B:53:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzjw zzjwVar, zzhi zzhiVar) {
        boolean zZzc;
        zzkn<?, ?> zzknVar = this.zzb;
        zzhk<?> zzhkVar = this.zzd;
        Object objZzc = zzknVar.zzc(t8);
        zzho<T> zzhoVarZzb = zzhkVar.zzb(t8);
        while (zzjwVar.zza() != Integer.MAX_VALUE) {
            try {
                int iZzb = zzjwVar.zzb();
                if (iZzb != 11) {
                    if ((iZzb & 7) == 2) {
                        Object objZza = zzhkVar.zza(zzhiVar, this.zza, iZzb >>> 3);
                        if (objZza != null) {
                            zzhkVar.zza(zzjwVar, objZza, zzhiVar, zzhoVarZzb);
                        } else {
                            zZzc = zzknVar.zza((zzkn<?, ?>) objZzc, zzjwVar);
                        }
                    } else {
                        zZzc = zzjwVar.zzc();
                    }
                    if (zZzc) {
                        return;
                    }
                } else {
                    Object objZza2 = null;
                    int iZzo = 0;
                    zzgm zzgmVarZzn = null;
                    while (zzjwVar.zza() != Integer.MAX_VALUE) {
                        int iZzb2 = zzjwVar.zzb();
                        if (iZzb2 == 16) {
                            iZzo = zzjwVar.zzo();
                            objZza2 = zzhkVar.zza(zzhiVar, this.zza, iZzo);
                        } else if (iZzb2 == 26) {
                            if (objZza2 != null) {
                                zzhkVar.zza(zzjwVar, objZza2, zzhiVar, zzhoVarZzb);
                            } else {
                                zzgmVarZzn = zzjwVar.zzn();
                            }
                        } else if (!zzjwVar.zzc()) {
                            break;
                        }
                    }
                    if (zzjwVar.zzb() != 12) {
                        throw zzig.zze();
                    }
                    if (zzgmVarZzn != null) {
                        if (objZza2 != null) {
                            zzhkVar.zza(zzgmVarZzn, objZza2, zzhiVar, zzhoVarZzb);
                        } else {
                            zzknVar.zza((zzkn<?, ?>) objZzc, iZzo, zzgmVarZzn);
                        }
                    }
                }
                zZzc = true;
                if (zZzc) {
                }
            } finally {
                zzknVar.zzb((Object) t8, (T) objZzc);
            }
        }
    }
}
