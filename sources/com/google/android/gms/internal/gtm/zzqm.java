package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzqm extends zzpo<Double> implements zzsv, RandomAccess {
    private static final zzqm zzaxe;
    private int size;
    private double[] zzaxf;

    static {
        zzqm zzqmVar = new zzqm(new double[0], 0);
        zzaxe = zzqmVar;
        zzqmVar.zzmi();
    }

    public zzqm() {
        this(new double[10], 0);
    }

    private final void zzah(int i9) {
        if (i9 < 0 || i9 >= this.size) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
    }

    private final String zzai(int i9) {
        int i10 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i9);
        sb.append(", Size:");
        sb.append(i10);
        return sb.toString();
    }

    private final void zzc(int i9, double d9) {
        int i10;
        zzmz();
        if (i9 < 0 || i9 > (i10 = this.size)) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
        double[] dArr = this.zzaxf;
        if (i10 < dArr.length) {
            System.arraycopy(dArr, i9, dArr, i9 + 1, i10 - i9);
        } else {
            double[] dArr2 = new double[((i10 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i9);
            System.arraycopy(this.zzaxf, i9, dArr2, i9 + 1, this.size - i9);
            this.zzaxf = dArr2;
        }
        this.zzaxf[i9] = d9;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zzc(i9, ((Double) obj).doubleValue());
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzqm)) {
            return super.addAll(collection);
        }
        zzqm zzqmVar = (zzqm) collection;
        int i9 = zzqmVar.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        double[] dArr = this.zzaxf;
        if (i11 > dArr.length) {
            this.zzaxf = Arrays.copyOf(dArr, i11);
        }
        System.arraycopy(zzqmVar.zzaxf, 0, this.zzaxf, this.size, zzqmVar.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqm)) {
            return super.equals(obj);
        }
        zzqm zzqmVar = (zzqm) obj;
        if (this.size != zzqmVar.size) {
            return false;
        }
        double[] dArr = zzqmVar.zzaxf;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (Double.doubleToLongBits(this.zzaxf[i9]) != Double.doubleToLongBits(dArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzah(i9);
        return Double.valueOf(this.zzaxf[i9]);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzz = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iZzz = (iZzz * 31) + zzre.zzz(Double.doubleToLongBits(this.zzaxf[i9]));
        }
        return iZzz;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzmz();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Double.valueOf(this.zzaxf[i9]))) {
                double[] dArr = this.zzaxf;
                System.arraycopy(dArr, i9 + 1, dArr, i9, (this.size - i9) - 1);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i9, int i10) {
        zzmz();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzaxf;
        System.arraycopy(dArr, i10, dArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zzmz();
        zzah(i9);
        double[] dArr = this.zzaxf;
        double d9 = dArr[i9];
        dArr[i9] = dDoubleValue;
        return Double.valueOf(d9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 >= this.size) {
            return new zzqm(Arrays.copyOf(this.zzaxf, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzd(double d9) {
        zzc(this.size, d9);
    }

    private zzqm(double[] dArr, int i9) {
        this.zzaxf = dArr;
        this.size = i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        zzah(i9);
        double[] dArr = this.zzaxf;
        double d9 = dArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(dArr, i9 + 1, dArr, i9, (r3 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d9);
    }
}
