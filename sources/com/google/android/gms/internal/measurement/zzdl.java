package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;

/* loaded from: classes2.dex */
public final class zzdl {
    final Uri zza;
    final String zzb;
    final String zzc;
    private final String zzd;
    private final boolean zze;
    private final boolean zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final zzdv<Context, Boolean> zzi;

    public zzdl(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    public final zzdc<Long> zza(String str, long j9) {
        return zzdc.zzb(this, str, j9, true);
    }

    private zzdl(String str, Uri uri, String str2, String str3, boolean z8, boolean z9, boolean z10, boolean z11, zzdv<Context, Boolean> zzdvVar) {
        this.zzd = null;
        this.zza = uri;
        this.zzb = str2;
        this.zzc = str3;
        this.zze = false;
        this.zzf = false;
        this.zzg = false;
        this.zzh = false;
        this.zzi = null;
    }

    public final zzdc<Boolean> zza(String str, boolean z8) {
        return zzdc.zzb(this, str, z8, true);
    }

    public final zzdc<Double> zza(String str, double d9) {
        return zzdc.zzb(this, str, -3.0d, true);
    }

    public final zzdc<String> zza(String str, String str2) {
        return zzdc.zzb(this, str, str2, true);
    }
}
