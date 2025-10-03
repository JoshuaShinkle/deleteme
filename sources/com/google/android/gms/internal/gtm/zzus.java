package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public final class zzus implements Cloneable {
    private static final zzut zzbhe = new zzut();
    private int mSize;
    private boolean zzbhf;
    private int[] zzbhg;
    private zzut[] zzbhh;

    public zzus() {
        this(10);
    }

    private static int idealIntArraySize(int i9) {
        int i10 = i9 << 2;
        int i11 = 4;
        while (true) {
            if (i11 >= 32) {
                break;
            }
            int i12 = (1 << i11) - 12;
            if (i10 <= i12) {
                i10 = i12;
                break;
            }
            i11++;
        }
        return i10 / 4;
    }

    private final int zzcf(int i9) {
        int i10 = this.mSize - 1;
        int i11 = 0;
        while (i11 <= i10) {
            int i12 = (i11 + i10) >>> 1;
            int i13 = this.zzbhg[i12];
            if (i13 < i9) {
                i11 = i12 + 1;
            } else {
                if (i13 <= i9) {
                    return i12;
                }
                i10 = i12 - 1;
            }
        }
        return ~i11;
    }

    public final /* synthetic */ Object clone() {
        int i9 = this.mSize;
        zzus zzusVar = new zzus(i9);
        System.arraycopy(this.zzbhg, 0, zzusVar.zzbhg, 0, i9);
        for (int i10 = 0; i10 < i9; i10++) {
            zzut zzutVar = this.zzbhh[i10];
            if (zzutVar != null) {
                zzusVar.zzbhh[i10] = (zzut) zzutVar.clone();
            }
        }
        zzusVar.mSize = i9;
        return zzusVar;
    }

    public final boolean equals(Object obj) {
        boolean z8;
        boolean z9;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzus)) {
            return false;
        }
        zzus zzusVar = (zzus) obj;
        int i9 = this.mSize;
        if (i9 != zzusVar.mSize) {
            return false;
        }
        int[] iArr = this.zzbhg;
        int[] iArr2 = zzusVar.zzbhg;
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
            zzut[] zzutVarArr = this.zzbhh;
            zzut[] zzutVarArr2 = zzusVar.zzbhh;
            int i11 = this.mSize;
            int i12 = 0;
            while (true) {
                if (i12 >= i11) {
                    z9 = true;
                    break;
                }
                if (!zzutVarArr[i12].equals(zzutVarArr2[i12])) {
                    z9 = false;
                    break;
                }
                i12++;
            }
            if (z9) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 17;
        for (int i9 = 0; i9 < this.mSize; i9++) {
            iHashCode = (((iHashCode * 31) + this.zzbhg[i9]) * 31) + this.zzbhh[i9].hashCode();
        }
        return iHashCode;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    public final int size() {
        return this.mSize;
    }

    public final void zza(int i9, zzut zzutVar) {
        int iZzcf = zzcf(i9);
        if (iZzcf >= 0) {
            this.zzbhh[iZzcf] = zzutVar;
            return;
        }
        int i10 = ~iZzcf;
        int i11 = this.mSize;
        if (i10 < i11) {
            zzut[] zzutVarArr = this.zzbhh;
            if (zzutVarArr[i10] == zzbhe) {
                this.zzbhg[i10] = i9;
                zzutVarArr[i10] = zzutVar;
                return;
            }
        }
        if (i11 >= this.zzbhg.length) {
            int iIdealIntArraySize = idealIntArraySize(i11 + 1);
            int[] iArr = new int[iIdealIntArraySize];
            zzut[] zzutVarArr2 = new zzut[iIdealIntArraySize];
            int[] iArr2 = this.zzbhg;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zzut[] zzutVarArr3 = this.zzbhh;
            System.arraycopy(zzutVarArr3, 0, zzutVarArr2, 0, zzutVarArr3.length);
            this.zzbhg = iArr;
            this.zzbhh = zzutVarArr2;
        }
        int i12 = this.mSize;
        if (i12 - i10 != 0) {
            int[] iArr3 = this.zzbhg;
            int i13 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i13, i12 - i10);
            zzut[] zzutVarArr4 = this.zzbhh;
            System.arraycopy(zzutVarArr4, i10, zzutVarArr4, i13, this.mSize - i10);
        }
        this.zzbhg[i10] = i9;
        this.zzbhh[i10] = zzutVar;
        this.mSize++;
    }

    public final zzut zzcd(int i9) {
        zzut zzutVar;
        int iZzcf = zzcf(i9);
        if (iZzcf < 0 || (zzutVar = this.zzbhh[iZzcf]) == zzbhe) {
            return null;
        }
        return zzutVar;
    }

    public final zzut zzce(int i9) {
        return this.zzbhh[i9];
    }

    private zzus(int i9) {
        this.zzbhf = false;
        int iIdealIntArraySize = idealIntArraySize(i9);
        this.zzbhg = new int[iIdealIntArraySize];
        this.zzbhh = new zzut[iIdealIntArraySize];
        this.mSize = 0;
    }
}
