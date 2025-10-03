package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzrd extends zzpo<Integer> implements zzsv, RandomAccess {
    private static final zzrd zzbbf;
    private int size;
    private int[] zzbbg;

    static {
        zzrd zzrdVar = new zzrd(new int[0], 0);
        zzbbf = zzrdVar;
        zzrdVar.zzmi();
    }

    public zzrd() {
        this(new int[10], 0);
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

    public static zzrd zzpo() {
        return zzbbf;
    }

    private final void zzq(int i9, int i10) {
        int i11;
        zzmz();
        if (i9 < 0 || i9 > (i11 = this.size)) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
        int[] iArr = this.zzbbg;
        if (i11 < iArr.length) {
            System.arraycopy(iArr, i9, iArr, i9 + 1, i11 - i9);
        } else {
            int[] iArr2 = new int[((i11 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i9);
            System.arraycopy(this.zzbbg, i9, iArr2, i9 + 1, this.size - i9);
            this.zzbbg = iArr2;
        }
        this.zzbbg[i9] = i10;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zzq(i9, ((Integer) obj).intValue());
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzrd)) {
            return super.addAll(collection);
        }
        zzrd zzrdVar = (zzrd) collection;
        int i9 = zzrdVar.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        int[] iArr = this.zzbbg;
        if (i11 > iArr.length) {
            this.zzbbg = Arrays.copyOf(iArr, i11);
        }
        System.arraycopy(zzrdVar.zzbbg, 0, this.zzbbg, this.size, zzrdVar.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzrd)) {
            return super.equals(obj);
        }
        zzrd zzrdVar = (zzrd) obj;
        if (this.size != zzrdVar.size) {
            return false;
        }
        int[] iArr = zzrdVar.zzbbg;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.zzbbg[i9] != iArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        return Integer.valueOf(getInt(i9));
    }

    public final int getInt(int i9) {
        zzah(i9);
        return this.zzbbg[i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i9 = 1;
        for (int i10 = 0; i10 < this.size; i10++) {
            i9 = (i9 * 31) + this.zzbbg[i10];
        }
        return i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzmz();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Integer.valueOf(this.zzbbg[i9]))) {
                int[] iArr = this.zzbbg;
                System.arraycopy(iArr, i9 + 1, iArr, i9, (this.size - i9) - 1);
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
        int[] iArr = this.zzbbg;
        System.arraycopy(iArr, i10, iArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        zzmz();
        zzah(i9);
        int[] iArr = this.zzbbg;
        int i10 = iArr[i9];
        iArr[i9] = iIntValue;
        return Integer.valueOf(i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 >= this.size) {
            return new zzrd(Arrays.copyOf(this.zzbbg, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzbm(int i9) {
        zzq(this.size, i9);
    }

    private zzrd(int[] iArr, int i9) {
        this.zzbbg = iArr;
        this.size = i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        zzah(i9);
        int[] iArr = this.zzbbg;
        int i10 = iArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(iArr, i9 + 1, iArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i10);
    }
}
