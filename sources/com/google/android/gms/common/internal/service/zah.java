package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
abstract class zah<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zaj> {
    public zah(GoogleApiClient googleApiClient) {
        super(Common.API, googleApiClient);
    }
}
