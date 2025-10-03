package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zacj implements Runnable {
    private final /* synthetic */ Result zaa;
    private final /* synthetic */ zack zab;

    public zacj(zack zackVar, Result result) {
        this.zab = zackVar;
        this.zaa = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            try {
                ThreadLocal<Boolean> threadLocal = BasePendingResult.zaa;
                threadLocal.set(Boolean.TRUE);
                this.zab.zah.sendMessage(this.zab.zah.obtainMessage(0, ((ResultTransform) Preconditions.checkNotNull(this.zab.zaa)).onSuccess(this.zaa)));
                threadLocal.set(Boolean.FALSE);
                zack zackVar = this.zab;
                zack.zaa(this.zaa);
                GoogleApiClient googleApiClient = (GoogleApiClient) this.zab.zag.get();
                if (googleApiClient != null) {
                    googleApiClient.zab(this.zab);
                }
            } catch (RuntimeException e9) {
                this.zab.zah.sendMessage(this.zab.zah.obtainMessage(1, e9));
                BasePendingResult.zaa.set(Boolean.FALSE);
                zack zackVar2 = this.zab;
                zack.zaa(this.zaa);
                GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zab.zag.get();
                if (googleApiClient2 != null) {
                    googleApiClient2.zab(this.zab);
                }
            }
        } catch (Throwable th) {
            BasePendingResult.zaa.set(Boolean.FALSE);
            zack zackVar3 = this.zab;
            zack.zaa(this.zaa);
            GoogleApiClient googleApiClient3 = (GoogleApiClient) this.zab.zag.get();
            if (googleApiClient3 != null) {
                googleApiClient3.zab(this.zab);
            }
            throw th;
        }
    }
}
