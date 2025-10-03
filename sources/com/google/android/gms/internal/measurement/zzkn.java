package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
abstract class zzkn<T, B> {
    public abstract B zza();

    public abstract T zza(B b9);

    public abstract void zza(B b9, int i9, int i10);

    public abstract void zza(B b9, int i9, long j9);

    public abstract void zza(B b9, int i9, zzgm zzgmVar);

    public abstract void zza(B b9, int i9, T t8);

    public abstract void zza(T t8, zzlk zzlkVar);

    public abstract void zza(Object obj, T t8);

    public abstract boolean zza(zzjw zzjwVar);

    public final boolean zza(B b9, zzjw zzjwVar) throws zzig {
        int iZzb = zzjwVar.zzb();
        int i9 = iZzb >>> 3;
        int i10 = iZzb & 7;
        if (i10 == 0) {
            zza((zzkn<T, B>) b9, i9, zzjwVar.zzg());
            return true;
        }
        if (i10 == 1) {
            zzb(b9, i9, zzjwVar.zzi());
            return true;
        }
        if (i10 == 2) {
            zza((zzkn<T, B>) b9, i9, zzjwVar.zzn());
            return true;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 5) {
                throw zzig.zzf();
            }
            zza((zzkn<T, B>) b9, i9, zzjwVar.zzj());
            return true;
        }
        B bZza = zza();
        int i11 = 4 | (i9 << 3);
        while (zzjwVar.zza() != Integer.MAX_VALUE && zza((zzkn<T, B>) bZza, zzjwVar)) {
        }
        if (i11 != zzjwVar.zzb()) {
            throw zzig.zze();
        }
        zza((zzkn<T, B>) b9, i9, (int) zza((zzkn<T, B>) bZza));
        return true;
    }

    public abstract T zzb(Object obj);

    public abstract void zzb(B b9, int i9, long j9);

    public abstract void zzb(T t8, zzlk zzlkVar);

    public abstract void zzb(Object obj, B b9);

    public abstract B zzc(Object obj);

    public abstract T zzc(T t8, T t9);

    public abstract void zzd(Object obj);

    public abstract int zze(T t8);

    public abstract int zzf(T t8);
}
