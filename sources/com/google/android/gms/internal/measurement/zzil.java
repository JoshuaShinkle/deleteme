package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public class zzil {
    private static final zzhi zza = zzhi.zza();
    private zzgm zzb;
    private volatile zzjg zzc;
    private volatile zzgm zzd;

    private final zzjg zzb(zzjg zzjgVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzjgVar;
                        this.zzd = zzgm.zza;
                    } catch (zzig unused) {
                        this.zzc = zzjgVar;
                        this.zzd = zzgm.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzil)) {
            return false;
        }
        zzil zzilVar = (zzil) obj;
        zzjg zzjgVar = this.zzc;
        zzjg zzjgVar2 = zzilVar.zzc;
        return (zzjgVar == null && zzjgVar2 == null) ? zzc().equals(zzilVar.zzc()) : (zzjgVar == null || zzjgVar2 == null) ? zzjgVar != null ? zzjgVar.equals(zzilVar.zzb(zzjgVar.zzbv())) : zzb(zzjgVar2.zzbv()).equals(zzjgVar2) : zzjgVar.equals(zzjgVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzjg zza(zzjg zzjgVar) {
        zzjg zzjgVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzjgVar;
        return zzjgVar2;
    }

    public final zzgm zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzgm.zza;
            } else {
                this.zzd = this.zzc.zzbj();
            }
            return this.zzd;
        }
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzbp();
        }
        return 0;
    }
}
