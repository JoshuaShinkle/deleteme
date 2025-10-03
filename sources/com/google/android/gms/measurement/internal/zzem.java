package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzem<V> {
    private static final Object zzf = new Object();
    private final String zza;
    private final zzek<V> zzb;
    private final V zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzg;
    private volatile V zzh;

    private zzem(String str, V v8, V v9, zzek<V> zzekVar) {
        this.zze = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zza = str;
        this.zzc = v8;
        this.zzd = v9;
        this.zzb = zzekVar;
    }

    public final String zza() {
        return this.zza;
    }

    public final V zza(V v8) {
        synchronized (this.zze) {
        }
        if (v8 != null) {
            return v8;
        }
        if (zzen.zza == null) {
            return this.zzc;
        }
        synchronized (zzf) {
            if (zzx.zza()) {
                return this.zzh == null ? this.zzc : this.zzh;
            }
            try {
                for (zzem zzemVar : zzat.zzcu) {
                    if (zzx.zza()) {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                    V vZza = null;
                    try {
                        zzek<V> zzekVar = zzemVar.zzb;
                        if (zzekVar != null) {
                            vZza = zzekVar.zza();
                        }
                    } catch (IllegalStateException unused) {
                    }
                    synchronized (zzf) {
                        zzemVar.zzh = vZza;
                    }
                }
            } catch (SecurityException unused2) {
            }
            zzek<V> zzekVar2 = this.zzb;
            if (zzekVar2 == null) {
                return this.zzc;
            }
            try {
                return zzekVar2.zza();
            } catch (IllegalStateException unused3) {
                return this.zzc;
            } catch (SecurityException unused4) {
                return this.zzc;
            }
        }
    }
}
