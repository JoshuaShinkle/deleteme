package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzju<E> extends zzgg<E> implements RandomAccess {
    private static final zzju<Object> zza;
    private E[] zzb;
    private int zzc;

    static {
        zzju<Object> zzjuVar = new zzju<>(new Object[0], 0);
        zza = zzjuVar;
        zzjuVar.mo17534i_();
    }

    public zzju() {
        this(new Object[10], 0);
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

    public static <E> zzju<E> zzd() {
        return (zzju<E>) zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e9) {
        zzc();
        int i9 = this.zzc;
        E[] eArr = this.zzb;
        if (i9 == eArr.length) {
            this.zzb = (E[]) Arrays.copyOf(eArr, ((i9 * 3) / 2) + 1);
        }
        E[] eArr2 = this.zzb;
        int i10 = this.zzc;
        this.zzc = i10 + 1;
        eArr2[i10] = e9;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i9) {
        zzb(i9);
        return this.zzb[i9];
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final E remove(int i9) {
        zzc();
        zzb(i9);
        E[] eArr = this.zzb;
        E e9 = eArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(eArr, i9 + 1, eArr, i9, (r2 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return e9;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final E set(int i9, E e9) {
        zzc();
        zzb(i9);
        E[] eArr = this.zzb;
        E e10 = eArr[i9];
        eArr[i9] = e9;
        ((AbstractList) this).modCount++;
        return e10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final /* synthetic */ zzid zza(int i9) {
        if (i9 >= this.zzc) {
            return new zzju(Arrays.copyOf(this.zzb, i9), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    private zzju(E[] eArr, int i9) {
        this.zzb = eArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final void add(int i9, E e9) {
        int i10;
        zzc();
        if (i9 >= 0 && i9 <= (i10 = this.zzc)) {
            E[] eArr = this.zzb;
            if (i10 < eArr.length) {
                System.arraycopy(eArr, i9, eArr, i9 + 1, i10 - i9);
            } else {
                E[] eArr2 = (E[]) new Object[((i10 * 3) / 2) + 1];
                System.arraycopy(eArr, 0, eArr2, 0, i9);
                System.arraycopy(this.zzb, i9, eArr2, i9 + 1, this.zzc - i9);
                this.zzb = eArr2;
            }
            this.zzb[i9] = e9;
            this.zzc++;
            ((AbstractList) this).modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(zzc(i9));
    }
}
