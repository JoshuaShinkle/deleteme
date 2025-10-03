package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzft<E> extends zzff<E> {
    private final transient E zza;
    private transient int zzb;

    public zzft(E e9) {
        this.zza = (E) zzdw.zza(e9);
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.zza.equals(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzff, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i9 = this.zzb;
        if (i9 != 0) {
            return i9;
        }
        int iHashCode = this.zza.hashCode();
        this.zzb = iHashCode;
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String string = this.zza.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 2);
        sb.append('[');
        sb.append(string);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final boolean zza() {
        return this.zzb != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    /* renamed from: zzb */
    public final zzfs<E> iterator() {
        return new zzfg(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final zzew<E> zzh() {
        return zzew.zza(this.zza);
    }

    public zzft(E e9, int i9) {
        this.zza = e9;
        this.zzb = i9;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzb(Object[] objArr, int i9) {
        objArr[i9] = this.zza;
        return i9 + 1;
    }
}
