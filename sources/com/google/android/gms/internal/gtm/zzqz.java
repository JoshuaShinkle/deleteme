package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzqz extends zzpo<Float> implements zzsv, RandomAccess {
    private static final zzqz zzbag;
    private int size;
    private float[] zzbah;

    static {
        zzqz zzqzVar = new zzqz(new float[0], 0);
        zzbag = zzqzVar;
        zzqzVar.zzmi();
    }

    public zzqz() {
        this(new float[10], 0);
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
        zzc(i9, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzmz();
        zzre.checkNotNull(collection);
        if (!(collection instanceof zzqz)) {
            return super.addAll(collection);
        }
        zzqz zzqzVar = (zzqz) collection;
        int i9 = zzqzVar.size;
        if (i9 == 0) {
            return false;
        }
        int i10 = this.size;
        if (Integer.MAX_VALUE - i10 < i9) {
            throw new OutOfMemoryError();
        }
        int i11 = i10 + i9;
        float[] fArr = this.zzbah;
        if (i11 > fArr.length) {
            this.zzbah = Arrays.copyOf(fArr, i11);
        }
        System.arraycopy(zzqzVar.zzbah, 0, this.zzbah, this.size, zzqzVar.size);
        this.size = i11;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqz)) {
            return super.equals(obj);
        }
        zzqz zzqzVar = (zzqz) obj;
        if (this.size != zzqzVar.size) {
            return false;
        }
        float[] fArr = zzqzVar.zzbah;
        for (int i9 = 0; i9 < this.size; i9++) {
            if (Float.floatToIntBits(this.zzbah[i9]) != Float.floatToIntBits(fArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzah(i9);
        return Float.valueOf(this.zzbah[i9]);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i9 = 0; i9 < this.size; i9++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.zzbah[i9]);
        }
        return iFloatToIntBits;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzmz();
        for (int i9 = 0; i9 < this.size; i9++) {
            if (obj.equals(Float.valueOf(this.zzbah[i9]))) {
                float[] fArr = this.zzbah;
                System.arraycopy(fArr, i9 + 1, fArr, i9, (this.size - i9) - 1);
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
        float[] fArr = this.zzbah;
        System.arraycopy(fArr, i10, fArr, i9, this.size - i10);
        this.size -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zzmz();
        zzah(i9);
        float[] fArr = this.zzbah;
        float f9 = fArr[i9];
        fArr[i9] = fFloatValue;
        return Float.valueOf(f9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 >= this.size) {
            return new zzqz(Arrays.copyOf(this.zzbah, i9), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzc(float f9) {
        zzc(this.size, f9);
    }

    private zzqz(float[] fArr, int i9) {
        this.zzbah = fArr;
        this.size = i9;
    }

    private final void zzc(int i9, float f9) {
        int i10;
        zzmz();
        if (i9 < 0 || i9 > (i10 = this.size)) {
            throw new IndexOutOfBoundsException(zzai(i9));
        }
        float[] fArr = this.zzbah;
        if (i10 < fArr.length) {
            System.arraycopy(fArr, i9, fArr, i9 + 1, i10 - i9);
        } else {
            float[] fArr2 = new float[((i10 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i9);
            System.arraycopy(this.zzbah, i9, fArr2, i9 + 1, this.size - i9);
            this.zzbah = fArr2;
        }
        this.zzbah[i9] = f9;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        zzah(i9);
        float[] fArr = this.zzbah;
        float f9 = fArr[i9];
        if (i9 < this.size - 1) {
            System.arraycopy(fArr, i9 + 1, fArr, i9, (r2 - i9) - 1);
        }
        this.size--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f9);
    }
}
