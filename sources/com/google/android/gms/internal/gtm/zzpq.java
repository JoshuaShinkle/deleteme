package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzpq extends zzpo<Boolean> implements zzsv, RandomAccess {
    private static final zzpq zzavv;
    private int size;
    private boolean[] zzavw;

    static {
        zzpq zzpqVar = new zzpq(new boolean[0], 0);
        zzavv = zzpqVar;
        zzpqVar.zzmi();
    }

    public zzpq() {
        this(new boolean[10], 0);
    }

    private final void zza(int i9, boolean z8) {
        int i10;
        zzmz();
        if (i9 < 0 || i9 > (i10 = this.size)) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
        boolean[] zArr = this.zzavw;
        if (i10 < zArr.length) {
            System.arraycopy(zArr, i9, zArr, i9 + 1, i10 - i9);
        } else {
            boolean[] zArr2 = new boolean[((i10 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i9);
            System.arraycopy(this.zzavw, i9, zArr2, i9 + 1, this.size - i9);
            this.zzavw = zArr2;
        }
        this.zzavw[i9] = z8;
        this.size++;
        ((AbstractList) this).modCount++;
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

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zza(i9, ((Boolean) obj).booleanValue());
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzpq)) {
            return super.addAll(collection);
        }
        zzpq zzpqVar = (zzpq) collection;
        int i9 = zzpqVar.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        boolean[] zArr = this.zzavw;
        if (i11 > zArr.length) {
            this.zzavw = Arrays.copyOf(zArr, i11);
        }
        System.arraycopy(zzpqVar.zzavw, 0, this.zzavw, this.size, zzpqVar.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void addBoolean(boolean z8) {
        zza(this.size, z8);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzpq)) {
            return super.equals(obj);
        }
        zzpq zzpqVar = (zzpq) obj;
        if (this.size != zzpqVar.size) {
            return false;
        }
        boolean[] zArr = zzpqVar.zzavw;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.zzavw[i9] != zArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzah(i9);
        return Boolean.valueOf(this.zzavw[i9]);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzk = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iZzk = (iZzk * 31) + zzre.zzk(this.zzavw[i9]);
        }
        return iZzk;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzmz();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Boolean.valueOf(this.zzavw[i9]))) {
                boolean[] zArr = this.zzavw;
                System.arraycopy(zArr, i9 + 1, zArr, i9, (this.size - i9) - 1);
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
        boolean[] zArr = this.zzavw;
        System.arraycopy(zArr, i10, zArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        zzmz();
        zzah(i9);
        boolean[] zArr = this.zzavw;
        boolean z8 = zArr[i9];
        zArr[i9] = zBooleanValue;
        return Boolean.valueOf(z8);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 >= this.size) {
            return new zzpq(Arrays.copyOf(this.zzavw, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    private zzpq(boolean[] zArr, int i9) {
        this.zzavw = zArr;
        this.size = i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        zzah(i9);
        boolean[] zArr = this.zzavw;
        boolean z8 = zArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(zArr, i9 + 1, zArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z8);
    }
}
