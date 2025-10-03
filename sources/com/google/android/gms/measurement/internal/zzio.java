package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
final class zzio implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ zzin zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzim zze;

    public zzio(zzim zzimVar, zzin zzinVar, zzin zzinVar2, long j9, boolean z8) {
        this.zze = zzimVar;
        this.zza = zzinVar;
        this.zzb = zzinVar2;
        this.zzc = j9;
        this.zzd = z8;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
