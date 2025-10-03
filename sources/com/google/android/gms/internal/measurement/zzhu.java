package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzhu extends zzgg<Float> implements zzjs, RandomAccess {
    private static final zzhu zza;
    private float[] zzb;
    private int zzc;

    static {
        zzhu zzhuVar = new zzhu(new float[0], 0);
        zza = zzhuVar;
        zzhuVar.mo17534i_();
    }

    public zzhu() {
        this(new float[10], 0);
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
        float fFloatValue = ((Float) obj).floatValue();
        zzc();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzc(i9));
        }
        float[] fArr = this.zzb;
        if (i10 < fArr.length) {
            System.arraycopy(fArr, i9, fArr, i9 + 1, i10 - i9);
        } else {
            float[] fArr2 = new float[((i10 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i9);
            System.arraycopy(this.zzb, i9, fArr2, i9 + 1, this.zzc - i9);
            this.zzb = fArr2;
        }
        this.zzb[i9] = fFloatValue;
        this.zzc++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzc();
        zzhx.zza(collection);
        if (!(collection instanceof zzhu)) {
            return super.addAll(collection);
        }
        zzhu zzhuVar = (zzhu) collection;
        int i9 = zzhuVar.zzc;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.zzc;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        float[] fArr = this.zzb;
        if (i11 > fArr.length) {
            this.zzb = Arrays.copyOf(fArr, i11);
        }
        System.arraycopy(zzhuVar.zzb, 0, this.zzb, this.zzc, zzhuVar.zzc);
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
        if (!(obj instanceof zzhu)) {
            return super.equals(obj);
        }
        zzhu zzhuVar = (zzhu) obj;
        if (this.zzc != zzhuVar.zzc) {
            return false;
        }
        float[] fArr = zzhuVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (Float.floatToIntBits(this.zzb[i9]) != Float.floatToIntBits(fArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzb(i9);
        return Float.valueOf(this.zzb[i9]);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.zzb[i9]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.zzb[i9] == fFloatValue) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzc();
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (obj.equals(Float.valueOf(this.zzb[i9]))) {
                float[] fArr = this.zzb;
                System.arraycopy(fArr, i9 + 1, fArr, i9, (this.zzc - i9) - 1);
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
        float[] fArr = this.zzb;
        System.arraycopy(fArr, i10, fArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zzc();
        zzb(i9);
        float[] fArr = this.zzb;
        float f9 = fArr[i9];
        fArr[i9] = fFloatValue;
        return Float.valueOf(f9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    public final void zza(float f9) {
        zzc();
        int i9 = this.zzc;
        float[] fArr = this.zzb;
        if (i9 == fArr.length) {
            float[] fArr2 = new float[((i9 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i9);
            this.zzb = fArr2;
        }
        float[] fArr3 = this.zzb;
        int i10 = this.zzc;
        this.zzc = i10 + 1;
        fArr3[i10] = f9;
    }

    private zzhu(float[] fArr, int i9) {
        this.zzb = fArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzc();
        zzb(i9);
        float[] fArr = this.zzb;
        float f9 = fArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(fArr, i9 + 1, fArr, i9, (r2 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f9);
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final /* synthetic */ zzid zza(int i9) {
        if (i9 >= this.zzc) {
            return new zzhu(Arrays.copyOf(this.zzb, i9), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zza(((Float) obj).floatValue());
        return true;
    }
}
