package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
final class zzhn implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ Bundle zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ String zzh;
    private final /* synthetic */ zzhe zzi;

    public zzhn(zzhe zzheVar, String str, String str2, long j9, Bundle bundle, boolean z8, boolean z9, boolean z10, String str3) {
        this.zzi = zzheVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = j9;
        this.zzd = bundle;
        this.zze = z8;
        this.zzf = z9;
        this.zzg = z10;
        this.zzh = str3;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        this.zzi.zza(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }
}
