package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzhg extends zzgg<Double> implements zzjs, RandomAccess {
    private static final zzhg zza;
    private double[] zzb;
    private int zzc;

    static {
        zzhg zzhgVar = new zzhg(new double[0], 0);
        zza = zzhgVar;
        zzhgVar.mo17534i_();
    }

    public zzhg() {
        this(new double[10], 0);
    }

    private final void zzb(int i9) {
        if (i9 < 0 || i9 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i9));
        }
    }

    private final String zzc(int i9) {
        int i10 = this.zzc;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i9);
        sb.append(", Size:");
        sb.append(i10);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        int i10;
        double dDoubleValue = ((Double) obj).doubleValue();
        zzc();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzc(i9));
        }
        double[] dArr = this.zzb;
        if (i10 < dArr.length) {
            System.arraycopy(dArr, i9, dArr, i9 + 1, i10 - i9);
        } else {
            double[] dArr2 = new double[((i10 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i9);
            System.arraycopy(this.zzb, i9, dArr2, i9 + 1, this.zzc - i9);
            this.zzb = dArr2;
        }
        this.zzb[i9] = dDoubleValue;
        this.zzc++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzc();
        zzhx.zza(collection);
        if (!(collection instanceof zzhg)) {
            return super.addAll(collection);
        }
        zzhg zzhgVar = (zzhg) collection;
        int i9 = zzhgVar.zzc;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.zzc;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        double[] dArr = this.zzb;
        if (i11 > dArr.length) {
            this.zzb = Arrays.copyOf(dArr, i11);
        }
        System.arraycopy(zzhgVar.zzb, 0, this.zzb, this.zzc, zzhgVar.zzc);
        this.zzc = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhg)) {
            return super.equals(obj);
        }
        zzhg zzhgVar = (zzhg) obj;
        if (this.zzc != zzhgVar.zzc) {
            return false;
        }
        double[] dArr = zzhgVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (Double.doubleToLongBits(this.zzb[i9]) != Double.doubleToLongBits(dArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzb(i9);
        return Double.valueOf(this.zzb[i9]);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZza = 1;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            iZza = (iZza * 31) + zzhx.zza(Double.doubleToLongBits(this.zzb[i9]));
        }
        return iZza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.zzb[i9] == dDoubleValue) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzc();
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (obj.equals(Double.valueOf(this.zzb[i9]))) {
                double[] dArr = this.zzb;
                System.arraycopy(dArr, i9 + 1, dArr, i9, (this.zzc - i9) - 1);
                this.zzc--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i9, int i10) {
        zzc();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzb;
        System.arraycopy(dArr, i10, dArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zzc();
        zzb(i9);
        double[] dArr = this.zzb;
        double d9 = dArr[i9];
        dArr[i9] = dDoubleValue;
        return Double.valueOf(d9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    public final void zza(double d9) {
        zzc();
        int i9 = this.zzc;
        double[] dArr = this.zzb;
        if (i9 == dArr.length) {
            double[] dArr2 = new double[((i9 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i9);
            this.zzb = dArr2;
        }
        double[] dArr3 = this.zzb;
        int i10 = this.zzc;
        this.zzc = i10 + 1;
        dArr3[i10] = d9;
    }

    private zzhg(double[] dArr, int i9) {
        this.zzb = dArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzc();
        zzb(i9);
        double[] dArr = this.zzb;
        double d9 = dArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(dArr, i9 + 1, dArr, i9, (r3 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d9);
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final /* synthetic */ zzid zza(int i9) {
        if (i9 >= this.zzc) {
            return new zzhg(Arrays.copyOf(this.zzb, i9), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zza(((Double) obj).doubleValue());
        return true;
    }
}
