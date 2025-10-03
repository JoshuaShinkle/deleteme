package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public abstract class zzgy {
    int zza;
    int zzb;
    zzhd zzc;
    private int zzd;
    private boolean zze;

    private zzgy() {
        this.zzb = 100;
        this.zzd = Integer.MAX_VALUE;
        this.zze = false;
    }

    public static long zza(long j9) {
        return (-(j9 & 1)) ^ (j9 >>> 1);
    }

    public static zzgy zza(byte[] bArr, int i9, int i10, boolean z8) {
        zzha zzhaVar = new zzha(bArr, i10);
        try {
            zzhaVar.zzc(i10);
            return zzhaVar;
        } catch (zzig e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public static int zze(int i9) {
        return (-(i9 & 1)) ^ (i9 >>> 1);
    }

    public abstract int zza();

    public abstract void zza(int i9);

    public abstract double zzb();

    public abstract boolean zzb(int i9);

    public abstract float zzc();

    public abstract int zzc(int i9);

    public abstract long zzd();

    public abstract void zzd(int i9);

    public abstract long zze();

    public abstract int zzf();

    public abstract long zzg();

    public abstract int zzh();

    public abstract boolean zzi();

    public abstract String zzj();

    public abstract String zzk();

    public abstract zzgm zzl();

    public abstract int zzm();

    public abstract int zzn();

    public abstract int zzo();

    public abstract long zzp();

    public abstract int zzq();

    public abstract long zzr();

    public abstract long zzs();

    public abstract boolean zzt();

    public abstract int zzu();
}
