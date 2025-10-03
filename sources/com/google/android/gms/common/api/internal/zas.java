package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes2.dex */
final class zas implements zabm {
    private final /* synthetic */ zaq zaa;

    private zas(zaq zaqVar) {
        this.zaa = zaqVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaa(bundle);
            this.zaa.zaj = ConnectionResult.RESULT_SUCCESS;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public /* synthetic */ zas(zaq zaqVar, zat zatVar) {
        this(zaqVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaj = connectionResult;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabm
    public final void zaa(int i9, boolean z8) {
        this.zaa.zam.lock();
        try {
            if (!this.zaa.zal && this.zaa.zak != null && this.zaa.zak.isSuccess()) {
                this.zaa.zal = true;
                this.zaa.zae.onConnectionSuspended(i9);
                return;
            }
            this.zaa.zal = false;
            this.zaa.zaa(i9, z8);
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
