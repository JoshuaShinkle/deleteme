package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzry extends zzpo<Long> implements zzsv, RandomAccess {
    private static final zzry zzbck;
    private int size;
    private long[] zzbcl;

    static {
        zzry zzryVar = new zzry(new long[0], 0);
        zzbck = zzryVar;
        zzryVar.zzmi();
    }

    public zzry() {
        this(new long[10], 0);
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

    private final void zzk(int i9, long j9) {
        int i10;
        zzmz();
        if (i9 < 0 || i9 > (i10 = this.size)) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
        long[] jArr = this.zzbcl;
        if (i10 < jArr.length) {
            System.arraycopy(jArr, i9, jArr, i9 + 1, i10 - i9);
        } else {
            long[] jArr2 = new long[((i10 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i9);
            System.arraycopy(this.zzbcl, i9, jArr2, i9 + 1, this.size - i9);
            this.zzbcl = jArr2;
        }
        this.zzbcl[i9] = j9;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zzk(i9, ((Long) obj).longValue());
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzry)) {
            return super.addAll(collection);
        }
        zzry zzryVar = (zzry) collection;
        int i9 = zzryVar.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        long[] jArr = this.zzbcl;
        if (i11 > jArr.length) {
            this.zzbcl = Arrays.copyOf(jArr, i11);
        }
        System.arraycopy(zzryVar.zzbcl, 0, this.zzbcl, this.size, zzryVar.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzry)) {
            return super.equals(obj);
        }
        zzry zzryVar = (zzry) obj;
        if (this.size != zzryVar.size) {
            return false;
        }
        long[] jArr = zzryVar.zzbcl;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (this.zzbcl[i9] != jArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        return Long.valueOf(getLong(i9));
    }

    public final long getLong(int i9) {
        zzah(i9);
        return this.zzbcl[i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzz = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iZzz = (iZzz * 31) + zzre.zzz(this.zzbcl[i9]);
        }
        return iZzz;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzmz();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Long.valueOf(this.zzbcl[i9]))) {
                long[] jArr = this.zzbcl;
                System.arraycopy(jArr, i9 + 1, jArr, i9, (this.size - i9) - 1);
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
        long[] jArr = this.zzbcl;
        System.arraycopy(jArr, i10, jArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        zzmz();
        zzah(i9);
        long[] jArr = this.zzbcl;
        long j9 = jArr[i9];
        jArr[i9] = jLongValue;
        return Long.valueOf(j9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzaa(long j9) {
        zzk(this.size, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 >= this.size) {
            return new zzry(Arrays.copyOf(this.zzbcl, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    private zzry(long[] jArr, int i9) {
        this.zzbcl = jArr;
        this.size = i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        zzah(i9);
        long[] jArr = this.zzbcl;
        long j9 = jArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(jArr, i9 + 1, jArr, i9, (r3 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j9);
    }
}
