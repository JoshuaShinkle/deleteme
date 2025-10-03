package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzkq {
    private static final zzkq zza = new zzkq(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzkq() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzkq zza() {
        return zza;
    }

    public static zzkq zzb() {
        return new zzkq();
    }

    public final boolean equals(Object obj) {
        boolean z8;
        boolean z9;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzkq)) {
            return false;
        }
        zzkq zzkqVar = (zzkq) obj;
        int i9 = this.zzb;
        if (i9 == zzkqVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzkqVar.zzc;
            int i10 = 0;
            while (true) {
                if (i10 >= i9) {
                    z8 = true;
                    break;
                }
                if (iArr[i10] != iArr2[i10]) {
                    z8 = false;
                    break;
                }
                i10++;
            }
            if (z8) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzkqVar.zzd;
                int i11 = this.zzb;
                int i12 = 0;
                while (true) {
                    if (i12 >= i11) {
                        z9 = true;
                        break;
                    }
                    if (!objArr[i12].equals(objArr2[i12])) {
                        z9 = false;
                        break;
                    }
                    i12++;
                }
                if (z9) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i9 = this.zzb;
        int i10 = (i9 + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i11 = 17;
        for (int i12 = 0; i12 < i9; i12++) {
            i11 = (i11 * 31) + iArr[i12];
        }
        int i13 = (i10 + i11) * 31;
        Object[] objArr = this.zzd;
        int i14 = this.zzb;
        for (int i15 = 0; i15 < i14; i15++) {
            iHashCode = (iHashCode * 31) + objArr[i15].hashCode();
        }
        return i13 + iHashCode;
    }

    public final void zzc() {
        this.zzf = false;
    }

    public final int zzd() {
        int i9 = this.zze;
        if (i9 != -1) {
            return i9;
        }
        int iZzd = 0;
        for (int i10 = 0; i10 < this.zzb; i10++) {
            iZzd += zzhf.zzd(this.zzc[i10] >>> 3, (zzgm) this.zzd[i10]);
        }
        this.zze = iZzd;
        return iZzd;
    }

    public final int zze() {
        int iZze;
        int i9 = this.zze;
        if (i9 != -1) {
            return i9;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.zzb; i11++) {
            int i12 = this.zzc[i11];
            int i13 = i12 >>> 3;
            int i14 = i12 & 7;
            if (i14 == 0) {
                iZze = zzhf.zze(i13, ((Long) this.zzd[i11]).longValue());
            } else if (i14 == 1) {
                iZze = zzhf.zzg(i13, ((Long) this.zzd[i11]).longValue());
            } else if (i14 == 2) {
                iZze = zzhf.zzc(i13, (zzgm) this.zzd[i11]);
            } else if (i14 == 3) {
                iZze = (zzhf.zze(i13) << 1) + ((zzkq) this.zzd[i11]).zze();
            } else {
                if (i14 != 5) {
                    throw new IllegalStateException(zzig.zzf());
                }
                iZze = zzhf.zzi(i13, ((Integer) this.zzd[i11]).intValue());
            }
            i10 += iZze;
        }
        this.zze = i10;
        return i10;
    }

    private zzkq(int i9, int[] iArr, Object[] objArr, boolean z8) {
        this.zze = -1;
        this.zzb = i9;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z8;
    }

    public static zzkq zza(zzkq zzkqVar, zzkq zzkqVar2) {
        int i9 = zzkqVar.zzb + zzkqVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzkqVar.zzc, i9);
        System.arraycopy(zzkqVar2.zzc, 0, iArrCopyOf, zzkqVar.zzb, zzkqVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzkqVar.zzd, i9);
        System.arraycopy(zzkqVar2.zzd, 0, objArrCopyOf, zzkqVar.zzb, zzkqVar2.zzb);
        return new zzkq(i9, iArrCopyOf, objArrCopyOf, true);
    }

    public final void zzb(zzlk zzlkVar) {
        if (this.zzb == 0) {
            return;
        }
        if (zzlkVar.zza() == zzhv.zze.zzj) {
            for (int i9 = 0; i9 < this.zzb; i9++) {
                zza(this.zzc[i9], this.zzd[i9], zzlkVar);
            }
            return;
        }
        for (int i10 = this.zzb - 1; i10 >= 0; i10--) {
            zza(this.zzc[i10], this.zzd[i10], zzlkVar);
        }
    }

    public final void zza(zzlk zzlkVar) {
        if (zzlkVar.zza() == zzhv.zze.zzk) {
            for (int i9 = this.zzb - 1; i9 >= 0; i9--) {
                zzlkVar.zza(this.zzc[i9] >>> 3, this.zzd[i9]);
            }
            return;
        }
        for (int i10 = 0; i10 < this.zzb; i10++) {
            zzlkVar.zza(this.zzc[i10] >>> 3, this.zzd[i10]);
        }
    }

    private static void zza(int i9, Object obj, zzlk zzlkVar) {
        int i10 = i9 >>> 3;
        int i11 = i9 & 7;
        if (i11 == 0) {
            zzlkVar.zza(i10, ((Long) obj).longValue());
            return;
        }
        if (i11 == 1) {
            zzlkVar.zzd(i10, ((Long) obj).longValue());
            return;
        }
        if (i11 == 2) {
            zzlkVar.zza(i10, (zzgm) obj);
            return;
        }
        if (i11 != 3) {
            if (i11 == 5) {
                zzlkVar.zzd(i10, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzig.zzf());
        }
        if (zzlkVar.zza() == zzhv.zze.zzj) {
            zzlkVar.zza(i10);
            ((zzkq) obj).zzb(zzlkVar);
            zzlkVar.zzb(i10);
        } else {
            zzlkVar.zzb(i10);
            ((zzkq) obj).zzb(zzlkVar);
            zzlkVar.zza(i10);
        }
    }

    public final void zza(StringBuilder sb, int i9) {
        for (int i10 = 0; i10 < this.zzb; i10++) {
            zzjh.zza(sb, i9, String.valueOf(this.zzc[i10] >>> 3), this.zzd[i10]);
        }
    }

    public final void zza(int i9, Object obj) {
        if (this.zzf) {
            int i10 = this.zzb;
            int[] iArr = this.zzc;
            if (i10 == iArr.length) {
                int i11 = i10 + (i10 < 4 ? 8 : i10 >> 1);
                this.zzc = Arrays.copyOf(iArr, i11);
                this.zzd = Arrays.copyOf(this.zzd, i11);
            }
            int[] iArr2 = this.zzc;
            int i12 = this.zzb;
            iArr2[i12] = i9;
            this.zzd[i12] = obj;
            this.zzb = i12 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
