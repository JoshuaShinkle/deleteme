package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zziu extends zzgg<Long> implements zzie, zzjs, RandomAccess {
    private static final zziu zza;
    private long[] zzb;
    private int zzc;

    static {
        zziu zziuVar = new zziu(new long[0], 0);
        zza = zziuVar;
        zziuVar.mo17534i_();
    }

    public zziu() {
        this(new long[10], 0);
    }

    public static zziu zzd() {
        return zza;
    }

    private final String zze(int i9) {
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
        long jLongValue = ((Long) obj).longValue();
        zzc();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zze(i9));
        }
        long[] jArr = this.zzb;
        if (i10 < jArr.length) {
            System.arraycopy(jArr, i9, jArr, i9 + 1, i10 - i9);
        } else {
            long[] jArr2 = new long[((i10 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i9);
            System.arraycopy(this.zzb, i9, jArr2, i9 + 1, this.zzc - i9);
            this.zzb = jArr2;
        }
        this.zzb[i9] = jLongValue;
        this.zzc++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzc();
        zzhx.zza(collection);
        if (!(collection instanceof zziu)) {
            return super.addAll(collection);
        }
        zziu zziuVar = (zziu) collection;
        int i9 = zziuVar.zzc;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.zzc;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        long[] jArr = this.zzb;
        if (i11 > jArr.length) {
            this.zzb = Arrays.copyOf(jArr, i11);
        }
        System.arraycopy(zziuVar.zzb, 0, this.zzb, this.zzc, zziuVar.zzc);
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
        if (!(obj instanceof zziu)) {
            return super.equals(obj);
        }
        zziu zziuVar = (zziu) obj;
        if (this.zzc != zziuVar.zzc) {
            return false;
        }
        long[] jArr = zziuVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (this.zzb[i9] != jArr[i9]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        return Long.valueOf(zzb(i9));
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
        if (!(obj instanceof Long)) {
            return -1;
        }
        long jLongValue = ((Long) obj).longValue();
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.zzb[i9] == jLongValue) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzc();
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (obj.equals(Long.valueOf(this.zzb[i9]))) {
                long[] jArr = this.zzb;
                System.arraycopy(jArr, i9 + 1, jArr, i9, (this.zzc - i9) - 1);
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
        long[] jArr = this.zzb;
        System.arraycopy(jArr, i10, jArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        zzc();
        zzd(i9);
        long[] jArr = this.zzb;
        long j9 = jArr[i9];
        jArr[i9] = jLongValue;
        return Long.valueOf(j9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    public final void zza(long j9) {
        zzc();
        int i9 = this.zzc;
        long[] jArr = this.zzb;
        if (i9 == jArr.length) {
            long[] jArr2 = new long[((i9 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i9);
            this.zzb = jArr2;
        }
        long[] jArr3 = this.zzb;
        int i10 = this.zzc;
        this.zzc = i10 + 1;
        jArr3[i10] = j9;
    }

    @Override // com.google.android.gms.internal.measurement.zzie
    public final long zzb(int i9) {
        zzd(i9);
        return this.zzb[i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzie zza(int i9) {
        if (i9 >= this.zzc) {
            return new zziu(Arrays.copyOf(this.zzb, i9), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    private zziu(long[] jArr, int i9) {
        this.zzb = jArr;
        this.zzc = i9;
    }

    private final void zzd(int i9) {
        if (i9 < 0 || i9 >= this.zzc) {
            throw new IndexOutOfBoundsException(zze(i9));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzc();
        zzd(i9);
        long[] jArr = this.zzb;
        long j9 = jArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(jArr, i9 + 1, jArr, i9, (r3 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zza(((Long) obj).longValue());
        return true;
    }
}
