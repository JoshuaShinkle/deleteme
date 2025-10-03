package com.google.android.gms.internal.play_billing;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzbk extends zzal implements RandomAccess, zzdm {
    private static final zzbk zza = new zzbk(new double[0], 0, false);
    private double[] zzb;
    private int zzc;

    public zzbk() {
        this(new double[10], 0, true);
    }

    private final String zzf(int i9) {
        return "Index:" + i9 + ", Size:" + this.zzc;
    }

    private final void zzg(int i9) {
        if (i9 < 0 || i9 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i9));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        int i10;
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i9));
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

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzcg.zzd;
        collection.getClass();
        if (!(collection instanceof zzbk)) {
            return super.addAll(collection);
        }
        zzbk zzbkVar = (zzbk) collection;
        int i9 = zzbkVar.zzc;
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
        System.arraycopy(zzbkVar.zzb, 0, this.zzb, this.zzc, zzbkVar.zzc);
        this.zzc = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbk)) {
            return super.equals(obj);
        }
        zzbk zzbkVar = (zzbk) obj;
        if (this.zzc != zzbkVar.zzc) {
            return false;
        }
        double[] dArr = zzbkVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (Double.doubleToLongBits(this.zzb[i9]) != Double.doubleToLongBits(dArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzg(i9);
        return Double.valueOf(this.zzb[i9]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i9 = 1;
        for (int i10 = 0; i10 < this.zzc; i10++) {
            long jDoubleToLongBits = Double.doubleToLongBits(this.zzb[i10]);
            byte[] bArr = zzcg.zzd;
            i9 = (i9 * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        }
        return i9;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int i9 = this.zzc;
        for (int i10 = 0; i10 < i9; i10++) {
            if (this.zzb[i10] == dDoubleValue) {
                return i10;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i9) {
        zza();
        zzg(i9);
        double[] dArr = this.zzb;
        double d9 = dArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(dArr, i9 + 1, dArr, i9, (r3 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d9);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i9, int i10) {
        zza();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzb;
        System.arraycopy(dArr, i10, dArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i9, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        zzg(i9);
        double[] dArr = this.zzb;
        double d9 = dArr[i9];
        dArr[i9] = dDoubleValue;
        return Double.valueOf(d9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcf
    public final /* bridge */ /* synthetic */ zzcf zzd(int i9) {
        if (i9 >= this.zzc) {
            return new zzbk(Arrays.copyOf(this.zzb, i9), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(double d9) {
        zza();
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

    private zzbk(double[] dArr, int i9, boolean z8) {
        super(z8);
        this.zzb = dArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Double) obj).doubleValue());
        return true;
    }
}
