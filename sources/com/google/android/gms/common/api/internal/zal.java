package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zal implements Runnable {
    final /* synthetic */ zak zaa;
    private final zam zab;

    public zal(zak zakVar, zam zamVar) {
        this.zaa = zakVar;
        this.zab = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zaa.zaa) {
            ConnectionResult connectionResultZab = this.zab.zab();
            if (connectionResultZab.hasResolution()) {
                zak zakVar = this.zaa;
                zakVar.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(zakVar.getActivity(), (PendingIntent) Preconditions.checkNotNull(connectionResultZab.getResolution()), this.zab.zaa(), false), 1);
                return;
            }
            zak zakVar2 = this.zaa;
            if (zakVar2.zac.getErrorResolutionIntent(zakVar2.getActivity(), connectionResultZab.getErrorCode(), null) != null) {
                zak zakVar3 = this.zaa;
                zakVar3.zac.zaa(zakVar3.getActivity(), this.zaa.mLifecycleFragment, connectionResultZab.getErrorCode(), 2, this.zaa);
            } else {
                if (connectionResultZab.getErrorCode() != 18) {
                    this.zaa.zaa(connectionResultZab, this.zab.zaa());
                    return;
                }
                Dialog dialogZaa = GoogleApiAvailability.zaa(this.zaa.getActivity(), this.zaa);
                zak zakVar4 = this.zaa;
                zakVar4.zac.zaa(zakVar4.getActivity().getApplicationContext(), new zan(this, dialogZaa));
            }
        }
    }
}
