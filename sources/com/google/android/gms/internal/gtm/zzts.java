package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzts {
    private static final zzts zzbem = new zzts(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzavs;
    private int zzbal;
    private Object[] zzbcz;
    private int[] zzben;

    private zzts() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzts zza(zzts zztsVar, zzts zztsVar2) {
        int i9 = zztsVar.count + zztsVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zztsVar.zzben, i9);
        System.arraycopy(zztsVar2.zzben, 0, iArrCopyOf, zztsVar.count, zztsVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zztsVar.zzbcz, i9);
        System.arraycopy(zztsVar2.zzbcz, 0, objArrCopyOf, zztsVar.count, zztsVar2.count);
        return new zzts(i9, iArrCopyOf, objArrCopyOf, true);
    }

    public static zzts zzrj() {
        return zzbem;
    }

    public static zzts zzrk() {
        return new zzts();
    }

    public final boolean equals(Object obj) {
        boolean z8;
        boolean z9;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzts)) {
            return false;
        }
        zzts zztsVar = (zzts) obj;
        int i9 = this.count;
        if (i9 == zztsVar.count) {
            int[] iArr = this.zzben;
            int[] iArr2 = zztsVar.zzben;
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
                Object[] objArr = this.zzbcz;
                Object[] objArr2 = zztsVar.zzbcz;
                int i11 = this.count;
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
        int i9 = this.count;
        int i10 = (i9 + 527) * 31;
        int[] iArr = this.zzben;
        int iHashCode = 17;
        int i11 = 17;
        for (int i12 = 0; i12 < i9; i12++) {
            i11 = (i11 * 31) + iArr[i12];
        }
        int i13 = (i10 + i11) * 31;
        Object[] objArr = this.zzbcz;
        int i14 = this.count;
        for (int i15 = 0; i15 < i14; i15++) {
            iHashCode = (iHashCode * 31) + objArr[i15].hashCode();
        }
        return i13 + iHashCode;
    }

    public final void zzb(zzum zzumVar) {
        if (this.count == 0) {
            return;
        }
        if (zzumVar.zzol() == zzrc.zze.zzbbc) {
            for (int i9 = 0; i9 < this.count; i9++) {
                zzb(this.zzben[i9], this.zzbcz[i9], zzumVar);
            }
            return;
        }
        for (int i10 = this.count - 1; i10 >= 0; i10--) {
            zzb(this.zzben[i10], this.zzbcz[i10], zzumVar);
        }
    }

    public final void zzmi() {
        this.zzavs = false;
    }

    public final int zzpe() {
        int iZze;
        int i9 = this.zzbal;
        if (i9 != -1) {
            return i9;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.count; i11++) {
            int i12 = this.zzben[i11];
            int i13 = i12 >>> 3;
            int i14 = i12 & 7;
            if (i14 == 0) {
                iZze = zzqj.zze(i13, ((Long) this.zzbcz[i11]).longValue());
            } else if (i14 == 1) {
                iZze = zzqj.zzg(i13, ((Long) this.zzbcz[i11]).longValue());
            } else if (i14 == 2) {
                iZze = zzqj.zzc(i13, (zzps) this.zzbcz[i11]);
            } else if (i14 == 3) {
                iZze = (zzqj.zzbb(i13) << 1) + ((zzts) this.zzbcz[i11]).zzpe();
            } else {
                if (i14 != 5) {
                    throw new IllegalStateException(zzrk.zzpt());
                }
                iZze = zzqj.zzl(i13, ((Integer) this.zzbcz[i11]).intValue());
            }
            i10 += iZze;
        }
        this.zzbal = i10;
        return i10;
    }

    public final int zzrl() {
        int i9 = this.zzbal;
        if (i9 != -1) {
            return i9;
        }
        int iZzd = 0;
        for (int i10 = 0; i10 < this.count; i10++) {
            iZzd += zzqj.zzd(this.zzben[i10] >>> 3, (zzps) this.zzbcz[i10]);
        }
        this.zzbal = iZzd;
        return iZzd;
    }

    private zzts(int i9, int[] iArr, Object[] objArr, boolean z8) {
        this.zzbal = -1;
        this.count = i9;
        this.zzben = iArr;
        this.zzbcz = objArr;
        this.zzavs = z8;
    }

    private static void zzb(int i9, Object obj, zzum zzumVar) {
        int i10 = i9 >>> 3;
        int i11 = i9 & 7;
        if (i11 == 0) {
            zzumVar.zzi(i10, ((Long) obj).longValue());
            return;
        }
        if (i11 == 1) {
            zzumVar.zzc(i10, ((Long) obj).longValue());
            return;
        }
        if (i11 == 2) {
            zzumVar.zza(i10, (zzps) obj);
            return;
        }
        if (i11 != 3) {
            if (i11 == 5) {
                zzumVar.zzh(i10, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzrk.zzpt());
        }
        if (zzumVar.zzol() == zzrc.zze.zzbbc) {
            zzumVar.zzbk(i10);
            ((zzts) obj).zzb(zzumVar);
            zzumVar.zzbl(i10);
        } else {
            zzumVar.zzbl(i10);
            ((zzts) obj).zzb(zzumVar);
            zzumVar.zzbk(i10);
        }
    }

    public final void zza(zzum zzumVar) {
        if (zzumVar.zzol() == zzrc.zze.zzbbd) {
            for (int i9 = this.count - 1; i9 >= 0; i9--) {
                zzumVar.zza(this.zzben[i9] >>> 3, this.zzbcz[i9]);
            }
            return;
        }
        for (int i10 = 0; i10 < this.count; i10++) {
            zzumVar.zza(this.zzben[i10] >>> 3, this.zzbcz[i10]);
        }
    }

    public final void zza(StringBuilder sb, int i9) {
        for (int i10 = 0; i10 < this.count; i10++) {
            zzsn.zza(sb, i9, String.valueOf(this.zzben[i10] >>> 3), this.zzbcz[i10]);
        }
    }

    public final void zzb(int i9, Object obj) {
        if (this.zzavs) {
            int i10 = this.count;
            int[] iArr = this.zzben;
            if (i10 == iArr.length) {
                int i11 = i10 + (i10 < 4 ? 8 : i10 >> 1);
                this.zzben = Arrays.copyOf(iArr, i11);
                this.zzbcz = Arrays.copyOf(this.zzbcz, i11);
            }
            int[] iArr2 = this.zzben;
            int i12 = this.count;
            iArr2[i12] = i9;
            this.zzbcz[i12] = obj;
            this.count = i12 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
