package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzhf {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    com.google.android.gms.internal.measurement.zzae zzg;
    boolean zzh;
    Long zzi;

    @VisibleForTesting
    public zzhf(Context context, com.google.android.gms.internal.measurement.zzae zzaeVar, Long l9) {
        this.zzh = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l9;
        if (zzaeVar != null) {
            this.zzg = zzaeVar;
            this.zzb = zzaeVar.zzf;
            this.zzc = zzaeVar.zze;
            this.zzd = zzaeVar.zzd;
            this.zzh = zzaeVar.zzc;
            this.zzf = zzaeVar.zzb;
            Bundle bundle = zzaeVar.zzg;
            if (bundle != null) {
                this.zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
