package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
final class zzip implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzin zzb;
    private final /* synthetic */ zzin zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzim zze;

    public zzip(zzim zzimVar, Bundle bundle, zzin zzinVar, zzin zzinVar2, long j9) {
        this.zze = zzimVar;
        this.zza = bundle;
        this.zzb = zzinVar;
        this.zzc = zzinVar2;
        this.zzd = j9;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
