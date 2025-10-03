package com.google.android.gms.internal.play_billing;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzbu extends zzal implements RandomAccess, zzdm {
    private static final zzbu zza = new zzbu(new float[0], 0, false);
    private float[] zzb;
    private int zzc;

    public zzbu() {
        this(new float[10], 0, true);
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
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        if (i9 < 0 || i9 > (i10 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i9));
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

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzcg.zzd;
        collection.getClass();
        if (!(collection instanceof zzbu)) {
            return super.addAll(collection);
        }
        zzbu zzbuVar = (zzbu) collection;
        int i9 = zzbuVar.zzc;
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
        System.arraycopy(zzbuVar.zzb, 0, this.zzb, this.zzc, zzbuVar.zzc);
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
        if (!(obj instanceof zzbu)) {
            return super.equals(obj);
        }
        zzbu zzbuVar = (zzbu) obj;
        if (this.zzc != zzbuVar.zzc) {
            return false;
        }
        float[] fArr = zzbuVar.zzb;
        for (int i9 = 0; i9 < this.zzc; i9++) {
            if (Float.floatToIntBits(this.zzb[i9]) != Float.floatToIntBits(fArr[i9])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        zzg(i9);
        return Float.valueOf(this.zzb[i9]);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.Collection, java.util.List
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
        int i9 = this.zzc;
        for (int i10 = 0; i10 < i9; i10++) {
            if (this.zzb[i10] == fFloatValue) {
                return i10;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i9) {
        zza();
        zzg(i9);
        float[] fArr = this.zzb;
        float f9 = fArr[i9];
        if (i9 < this.zzc - 1) {
            System.arraycopy(fArr, i9 + 1, fArr, i9, (r2 - i9) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f9);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i9, int i10) {
        zza();
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.zzb;
        System.arraycopy(fArr, i10, fArr, i9, this.zzc - i10);
        this.zzc -= i10 - i9;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i9, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        zzg(i9);
        float[] fArr = this.zzb;
        float f9 = fArr[i9];
        fArr[i9] = fFloatValue;
        return Float.valueOf(f9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcf
    public final /* bridge */ /* synthetic */ zzcf zzd(int i9) {
        if (i9 >= this.zzc) {
            return new zzbu(Arrays.copyOf(this.zzb, i9), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(float f9) {
        zza();
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

    private zzbu(float[] fArr, int i9, boolean z8) {
        super(z8);
        this.zzb = fArr;
        this.zzc = i9;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Float) obj).floatValue());
        return true;
    }
}
