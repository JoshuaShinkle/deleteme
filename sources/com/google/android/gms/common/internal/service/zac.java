package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public final class zac implements zad {
    @Override // com.google.android.gms.common.internal.service.zad
    public final PendingResult<Status> zaa(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zaf(this, googleApiClient));
    }
}
