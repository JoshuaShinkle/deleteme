package com.google.android.gms.measurement.internal;

import android.net.Uri;

/* loaded from: classes2.dex */
final class zzif implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ Uri zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzic zze;

    public zzif(zzic zzicVar, boolean z8, Uri uri, String str, String str2) {
        this.zze = zzicVar;
        this.zza = z8;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
