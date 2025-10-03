package com.google.android.gms.common;

import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
/* loaded from: classes2.dex */
public class PackageVerificationResult {
    private final String zza;
    private final boolean zzb;
    private final String zzc;
    private final Throwable zzd;

    private PackageVerificationResult(String str, int i9, boolean z8, String str2, Throwable th) {
        this.zza = str;
        this.zzb = z8;
        this.zzc = str2;
        this.zzd = th;
    }

    public static PackageVerificationResult zza(String str, String str2, Throwable th) {
        return new PackageVerificationResult(str, 1, false, str2, th);
    }

    public static PackageVerificationResult zzd(String str, int i9) {
        return new PackageVerificationResult(str, i9, true, null, null);
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        String strConcat = "PackageVerificationRslt: ".concat(String.valueOf(this.zzc));
        Throwable th = this.zzd;
        if (th == null) {
            throw new SecurityException(strConcat);
        }
        throw new SecurityException(strConcat, th);
    }

    public final boolean zzc() {
        return this.zzb;
    }
}
