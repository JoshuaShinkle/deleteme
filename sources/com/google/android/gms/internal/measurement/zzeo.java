package com.google.android.gms.internal.measurement;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
abstract class zzeo<T> implements Iterator<T> {
    private int zza;
    private int zzb;
    private int zzc;
    private final /* synthetic */ zzel zzd;

    private zzeo(zzel zzelVar) {
        this.zzd = zzelVar;
        this.zza = zzelVar.zzf;
        this.zzb = zzelVar.zzd();
        this.zzc = -1;
    }

    private final void zza() {
        if (this.zzd.zzf != this.zza) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zzb >= 0;
    }

    @Override // java.util.Iterator
    public T next() {
        zza();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i9 = this.zzb;
        this.zzc = i9;
        T tZza = zza(i9);
        this.zzb = this.zzd.zza(this.zzb);
        return tZza;
    }

    @Override // java.util.Iterator
    public void remove() {
        zza();
        zzdw.zzb(this.zzc >= 0, "no calls to next() since the last call to remove()");
        this.zza += 32;
        zzel zzelVar = this.zzd;
        zzelVar.remove(zzelVar.zzb[this.zzc]);
        this.zzb = zzel.zzb(this.zzb, this.zzc);
        this.zzc = -1;
    }

    public abstract T zza(int i9);

    public /* synthetic */ zzeo(zzel zzelVar, zzek zzekVar) {
        this(zzelVar);
    }
}
