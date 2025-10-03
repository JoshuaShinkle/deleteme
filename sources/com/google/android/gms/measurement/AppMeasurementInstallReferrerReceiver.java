package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.measurement.zzag;

@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver {
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public final void doStartService(Context context, Intent intent) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        zzag.zza(context).zza(5, "Install Referrer Broadcast deprecated", (Object) null, (Object) null, (Object) null);
    }
}
