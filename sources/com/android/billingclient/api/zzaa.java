package com.android.billingclient.api;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.google.android.gms.internal.play_billing.zzb;

/* loaded from: classes.dex */
final class zzaa extends ResultReceiver {
    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i9, Bundle bundle) {
        zzb.zze(bundle, "BillingClient");
        throw null;
    }
}
