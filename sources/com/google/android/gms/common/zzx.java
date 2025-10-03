package com.google.android.gms.common;

import android.util.Log;
import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
/* loaded from: classes2.dex */
class zzx {
    private static final zzx zze = new zzx(true, 3, 1, null, null);
    final boolean zza;
    final String zzb;
    final Throwable zzc;
    final int zzd;

    private zzx(boolean z8, int i9, int i10, String str, Throwable th) {
        this.zza = z8;
        this.zzd = i9;
        this.zzb = str;
        this.zzc = th;
    }

    @Deprecated
    public static zzx zzb() {
        return zze;
    }

    public static zzx zzc(String str) {
        return new zzx(false, 1, 5, str, null);
    }

    public static zzx zzd(String str, Throwable th) {
        return new zzx(false, 1, 5, str, th);
    }

    public static zzx zzf(int i9) {
        return new zzx(true, i9, 1, null, null);
    }

    public static zzx zzg(int i9, int i10, String str, Throwable th) {
        return new zzx(false, i9, i10, str, th);
    }

    public String zza() {
        return this.zzb;
    }

    public final void zze() {
        if (this.zza || !Log.isLoggable("GoogleCertificatesRslt", 3)) {
            return;
        }
        if (this.zzc != null) {
            Log.d("GoogleCertificatesRslt", zza(), this.zzc);
        } else {
            Log.d("GoogleCertificatesRslt", zza());
        }
    }
}
