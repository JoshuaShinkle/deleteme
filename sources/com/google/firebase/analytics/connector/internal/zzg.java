package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* loaded from: classes2.dex */
final class zzg implements AppMeasurementSdk.OnEventListener {
    private final /* synthetic */ zze zza;

    public zzg(zze zzeVar) {
        this.zza = zzeVar;
    }

    @Override // com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener, com.google.android.gms.measurement.internal.zzhc
    public final void onEvent(String str, String str2, Bundle bundle, long j9) {
        if (str == null || str.equals("crash") || !zzd.zzb(str2)) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
        bundle2.putLong("timestampInMillis", j9);
        bundle2.putBundle("params", bundle);
        this.zza.zza.onMessageTriggered(3, bundle2);
    }
}
