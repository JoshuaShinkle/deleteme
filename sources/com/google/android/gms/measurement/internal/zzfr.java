package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: classes2.dex */
public final class zzfr implements ServiceConnection {
    final /* synthetic */ zzfo zza;
    private final String zzb;

    public zzfr(zzfo zzfoVar, String str) {
        this.zza = zzfoVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzq().zzh().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            com.google.android.gms.internal.measurement.zzd zzdVarZza = com.google.android.gms.internal.measurement.zzg.zza(iBinder);
            if (zzdVarZza == null) {
                this.zza.zza.zzq().zzh().zza("Install Referrer Service implementation was not found");
            } else {
                this.zza.zza.zzq().zzw().zza("Install Referrer Service connected");
                this.zza.zza.zzp().zza(new zzfq(this, zzdVarZza, this));
            }
        } catch (Exception e9) {
            this.zza.zza.zzq().zzh().zza("Exception occurred while calling Install Referrer API", e9);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzq().zzw().zza("Install Referrer Service disconnected");
    }
}
