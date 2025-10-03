package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
abstract class zztr<T, B> {
    public abstract void zza(B b9, int i9, long j9);

    public abstract void zza(B b9, int i9, zzps zzpsVar);

    public abstract void zza(B b9, int i9, T t8);

    public abstract void zza(T t8, zzum zzumVar);

    public abstract boolean zza(zzsy zzsyVar);

    public final boolean zza(B b9, zzsy zzsyVar) throws zzrk {
        int tag = zzsyVar.getTag();
        int i9 = tag >>> 3;
        int i10 = tag & 7;
        if (i10 == 0) {
            zza((zztr<T, B>) b9, i9, zzsyVar.zznk());
            return true;
        }
        if (i10 == 1) {
            zzb(b9, i9, zzsyVar.zznm());
            return true;
        }
        if (i10 == 2) {
            zza((zztr<T, B>) b9, i9, zzsyVar.zznq());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw zzrk.zzpt();
            }
            zzc(b9, i9, zzsyVar.zznn());
            return true;
        }
        B bZzri = zzri();
        int i11 = 4 | (i9 << 3);
        while (zzsyVar.zzog() != Integer.MAX_VALUE && zza((zztr<T, B>) bZzri, zzsyVar)) {
        }
        if (i11 != zzsyVar.getTag()) {
            throw zzrk.zzps();
        }
        zza((zztr<T, B>) b9, i9, (int) zzaa(bZzri));
        return true;
    }

    public abstract T zzaa(B b9);

    public abstract int zzad(T t8);

    public abstract T zzag(Object obj);

    public abstract B zzah(Object obj);

    public abstract int zzai(T t8);

    public abstract void zzb(B b9, int i9, long j9);

    public abstract void zzc(B b9, int i9, int i10);

    public abstract void zzc(T t8, zzum zzumVar);

    public abstract void zzf(Object obj, T t8);

    public abstract void zzg(Object obj, B b9);

    public abstract T zzh(T t8, T t9);

    public abstract B zzri();

    public abstract void zzt(Object obj);
}
