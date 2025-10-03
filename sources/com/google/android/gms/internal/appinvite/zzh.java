package com.google.android.gms.internal.appinvite;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
abstract class zzh<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzm> {
    public zzh(GoogleApiClient googleApiClient) {
        super(AppInvite.API, googleApiClient);
    }
}
