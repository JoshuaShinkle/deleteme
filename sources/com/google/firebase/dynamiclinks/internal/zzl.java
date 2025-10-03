package com.google.firebase.dynamiclinks.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

/* loaded from: classes2.dex */
final class zzl extends TaskApiCall<zzd, PendingDynamicLinkData> {
    private final String zzj;
    private final AnalyticsConnector zzr;

    public zzl(AnalyticsConnector analyticsConnector, String str) {
        this.zzj = str;
        this.zzr = analyticsConnector;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<PendingDynamicLinkData> taskCompletionSource) {
        zzd zzdVar = (zzd) anyClient;
        try {
            ((zzm) zzdVar.getService()).zza(new zzi(this.zzr, taskCompletionSource), this.zzj);
        } catch (RemoteException unused) {
        }
    }
}
