package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzgk extends zzgg<Boolean> implements zzjs, RandomAccess {
    private static final zzgk zza;
    private boolean[] zzb;
    private int zzc;

    static {
        zzgk zzgkVar = new zzgk(new boolean[0], 0);
        zza = zzgkVar;
        zzgkVar.mo17534i_();
    }

    public zzgk() {
        this(new boolean[10], 0);
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
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        zzc();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzc(i9));
        }
        boolean[] zArr = this.zzb;
        if (i10 < zArr.length) {
            System.arraycopy(zArr, i9, zArr, i9 + 1, i10 - i9);
        } else {
            boolean[] zArr2 = new boolean[((i10 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i9);
            System.arraycopy(this.zzb, i9, zArr2, i9 + 1, this.zzc - i9);
            this.zzb = zArr2;
        }
        this.zzb[i9] = zBooleanValue;
        this.zzc++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzc();
        zzhx.zza(collection);
        if (!(collection instanceof zzgk)) {
            return super.addAll(collection);
        }
        zzgk zzgkVar = (zzgk) collection;
        int i9 = zzgkVar.zzc;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.zzc;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        boolean[] zArr = this.zzb;
        if (i11 > zArr.length) {
            this.zzb = Arrays.copyOf(zArr, i11);
        }
        System.arraycopy(zzgkVar.zzb, 0, this.zzb, this.zzc, zzgkVar.zzc);
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
        if (!(obj instanceof zzgk)) {
            return super.equals(obj);
        }
        zzgk zzgkVar = (zzgk) obj;
        if (this.zzc != zzgkVar.zzc) {
            return false;
        }
        boolean[] zArr = zzgkVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (this.zzb[i9] != zArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzb(i9);
        return Boolean.valueOf(this.zzb[i9]);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZza = 1;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            iZza = (iZza * 31) + zzhx.zza(this.zzb[i9]);
        }
        return iZza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.zzb[i9] == zBooleanValue) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzc();
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (obj.equals(Boolean.valueOf(this.zzb[i9]))) {
                boolean[] zArr = this.zzb;
                System.arraycopy(zArr, i9 + 1, zArr, i9, (this.zzc - i9) - 1);
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
        boolean[] zArr = this.zzb;
        System.arraycopy(zArr, i10, zArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        zzc();
        zzb(i9);
        boolean[] zArr = this.zzb;
        boolean z8 = zArr[i9];
        zArr[i9] = zBooleanValue;
        return Boolean.valueOf(z8);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    public final void zza(boolean z8) {
        zzc();
        int i9 = this.zzc;
        boolean[] zArr = this.zzb;
        if (i9 == zArr.length) {
            boolean[] zArr2 = new boolean[((i9 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i9);
            this.zzb = zArr2;
        }
        boolean[] zArr3 = this.zzb;
        int i10 = this.zzc;
        this.zzc = i10 + 1;
        zArr3[i10] = z8;
    }

    private zzgk(boolean[] zArr, int i9) {
        this.zzb = zArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzc();
        zzb(i9);
        boolean[] zArr = this.zzb;
        boolean z8 = zArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(zArr, i9 + 1, zArr, i9, (r2 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z8);
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final /* synthetic */ zzid zza(int i9) {
        if (i9 >= this.zzc) {
            return new zzgk(Arrays.copyOf(this.zzb, i9), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zza(((Boolean) obj).booleanValue());
        return true;
    }
}
