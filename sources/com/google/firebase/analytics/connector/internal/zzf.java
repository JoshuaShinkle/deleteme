package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* loaded from: classes2.dex */
final class zzf implements AppMeasurementSdk.OnEventListener {
    private final /* synthetic */ zzc zza;

    public zzf(zzc zzcVar) {
        this.zza = zzcVar;
    }

    @Override // com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener, com.google.android.gms.measurement.internal.zzhc
    public final void onEvent(String str, String str2, Bundle bundle, long j9) {
        if (this.zza.zza.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("events", zzd.zze(str2));
            this.zza.zzb.onMessageTriggered(2, bundle2);
        }
    }
}
