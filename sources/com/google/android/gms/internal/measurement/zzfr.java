package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzfr<E> extends zzff<E> {
    static final zzfr<Object> zza = new zzfr<>(new Object[0], 0, null, 0, 0);
    private final transient Object[] zzb;
    private final transient Object[] zzc;
    private final transient int zzd;
    private final transient int zze;
    private final transient int zzf;

    public zzfr(Object[] objArr, int i9, Object[] objArr2, int i10, int i11) {
        this.zzb = objArr;
        this.zzc = objArr2;
        this.zzd = i10;
        this.zze = i9;
        this.zzf = i11;
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.zzc;
        if (obj == null || objArr == null) {
            return false;
        }
        int iZza = zzeu.zza(obj);
        while (true) {
            int i9 = iZza & this.zzd;
            Object obj2 = objArr[i9];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iZza = i9 + 1;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzff, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    /* renamed from: zzb */
    public final zzfs<E> iterator() {
        return (zzfs) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final Object[] zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzff
    public final zzew<E> zzh() {
        return zzew.zza(this.zzb, this.zzf);
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzb(Object[] objArr, int i9) {
        System.arraycopy(this.zzb, 0, objArr, i9, this.zzf);
        return i9 + this.zzf;
    }
}
