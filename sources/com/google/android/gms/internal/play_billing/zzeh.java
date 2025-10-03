package com.google.android.gms.internal.play_billing;

import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzeh {
    private static final zzeh zza = new zzeh(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzeh() {
        this(0, new int[8], new Object[8], true);
    }

    private zzeh(int i9, int[] iArr, Object[] objArr, boolean z8) {
        this.zze = -1;
        this.zzb = i9;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z8;
    }

    public static zzeh zzc() {
        return zza;
    }

    public static zzeh zze(zzeh zzehVar, zzeh zzehVar2) {
        int i9 = zzehVar.zzb + zzehVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzehVar.zzc, i9);
        System.arraycopy(zzehVar2.zzc, 0, iArrCopyOf, zzehVar.zzb, zzehVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzehVar.zzd, i9);
        System.arraycopy(zzehVar2.zzd, 0, objArrCopyOf, zzehVar.zzb, zzehVar2.zzb);
        return new zzeh(i9, iArrCopyOf, objArrCopyOf, true);
    }

    public static zzeh zzf() {
        return new zzeh(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i9) {
        int[] iArr = this.zzc;
        if (i9 > iArr.length) {
            int i10 = this.zzb;
            int i11 = i10 + (i10 / 2);
            if (i11 >= i9) {
                i9 = i11;
            }
            if (i9 < 8) {
                i9 = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i9);
            this.zzd = Arrays.copyOf(this.zzd, i9);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzeh)) {
            return false;
        }
        zzeh zzehVar = (zzeh) obj;
        int i9 = this.zzb;
        if (i9 == zzehVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzehVar.zzc;
            int i10 = 0;
            while (true) {
                if (i10 >= i9) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzehVar.zzd;
                    int i11 = this.zzb;
                    for (int i12 = 0; i12 < i11; i12++) {
                        if (objArr[i12].equals(objArr2[i12])) {
                        }
                    }
                    return true;
                }
                if (iArr[i10] != iArr2[i10]) {
                    break;
                }
                i10++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i9 = this.zzb;
        int i10 = i9 + 527;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i11 = 17;
        for (int i12 = 0; i12 < i9; i12++) {
            i11 = (i11 * 31) + iArr[i12];
        }
        int i13 = (i10 * 31) + i11;
        Object[] objArr = this.zzd;
        int i14 = this.zzb;
        for (int i15 = 0; i15 < i14; i15++) {
            iHashCode = (iHashCode * 31) + objArr[i15].hashCode();
        }
        return (i13 * 31) + iHashCode;
    }

    public final int zza() {
        int iZzy;
        int iZzx;
        int iZzx2;
        int i9 = this.zze;
        if (i9 != -1) {
            return i9;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.zzb; i11++) {
            int i12 = this.zzc[i11];
            int i13 = i12 >>> 3;
            int i14 = i12 & 7;
            if (i14 != 0) {
                if (i14 == 1) {
                    ((Long) this.zzd[i11]).longValue();
                    iZzx2 = zzbi.zzx(i13 << 3) + 8;
                } else if (i14 == 2) {
                    zzba zzbaVar = (zzba) this.zzd[i11];
                    int i15 = zzbi.zzb;
                    int iZzd = zzbaVar.zzd();
                    iZzx2 = zzbi.zzx(i13 << 3) + zzbi.zzx(iZzd) + iZzd;
                } else if (i14 == 3) {
                    int i16 = i13 << 3;
                    int i17 = zzbi.zzb;
                    iZzy = ((zzeh) this.zzd[i11]).zza();
                    int iZzx3 = zzbi.zzx(i16);
                    iZzx = iZzx3 + iZzx3;
                } else {
                    if (i14 != 5) {
                        throw new IllegalStateException(zzci.zza());
                    }
                    ((Integer) this.zzd[i11]).intValue();
                    iZzx2 = zzbi.zzx(i13 << 3) + 4;
                }
                i10 += iZzx2;
            } else {
                int i18 = i13 << 3;
                iZzy = zzbi.zzy(((Long) this.zzd[i11]).longValue());
                iZzx = zzbi.zzx(i18);
            }
            iZzx2 = iZzx + iZzy;
            i10 += iZzx2;
        }
        this.zze = i10;
        return i10;
    }

    public final int zzb() {
        int i9 = this.zze;
        if (i9 != -1) {
            return i9;
        }
        int iZzx = 0;
        for (int i10 = 0; i10 < this.zzb; i10++) {
            int i11 = this.zzc[i10] >>> 3;
            zzba zzbaVar = (zzba) this.zzd[i10];
            int i12 = zzbi.zzb;
            int iZzd = zzbaVar.zzd();
            int iZzx2 = zzbi.zzx(iZzd) + iZzd;
            int iZzx3 = zzbi.zzx(16);
            int iZzx4 = zzbi.zzx(i11);
            int iZzx5 = zzbi.zzx(8);
            iZzx += iZzx5 + iZzx5 + iZzx3 + iZzx4 + zzbi.zzx(24) + iZzx2;
        }
        this.zze = iZzx;
        return iZzx;
    }

    public final zzeh zzd(zzeh zzehVar) {
        if (zzehVar.equals(zza)) {
            return this;
        }
        zzg();
        int i9 = this.zzb + zzehVar.zzb;
        zzl(i9);
        System.arraycopy(zzehVar.zzc, 0, this.zzc, this.zzb, zzehVar.zzb);
        System.arraycopy(zzehVar.zzd, 0, this.zzd, this.zzb, zzehVar.zzb);
        this.zzb = i9;
        return this;
    }

    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    public final void zzi(StringBuilder sb, int i9) {
        for (int i10 = 0; i10 < this.zzb; i10++) {
            zzdh.zzb(sb, i9, String.valueOf(this.zzc[i10] >>> 3), this.zzd[i10]);
        }
    }

    public final void zzj(int i9, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i10 = this.zzb;
        iArr[i10] = i9;
        this.zzd[i10] = obj;
        this.zzb = i10 + 1;
    }

    public final void zzk(zzey zzeyVar) {
        if (this.zzb != 0) {
            for (int i9 = 0; i9 < this.zzb; i9++) {
                int i10 = this.zzc[i9];
                Object obj = this.zzd[i9];
                int i11 = i10 & 7;
                int i12 = i10 >>> 3;
                if (i11 == 0) {
                    zzeyVar.zzt(i12, ((Long) obj).longValue());
                } else if (i11 == 1) {
                    zzeyVar.zzm(i12, ((Long) obj).longValue());
                } else if (i11 == 2) {
                    zzeyVar.zzd(i12, (zzba) obj);
                } else if (i11 == 3) {
                    zzeyVar.zzE(i12);
                    ((zzeh) obj).zzk(zzeyVar);
                    zzeyVar.zzh(i12);
                } else {
                    if (i11 != 5) {
                        throw new RuntimeException(zzci.zza());
                    }
                    zzeyVar.zzk(i12, ((Integer) obj).intValue());
                }
            }
        }
    }
}
