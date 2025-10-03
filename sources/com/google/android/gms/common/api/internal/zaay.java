package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* loaded from: classes2.dex */
public interface zaay {
    <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8);

    void zaa();

    void zaa(int i9);

    void zaa(Bundle bundle);

    void zaa(ConnectionResult connectionResult, Api<?> api, boolean z8);

    <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8);

    boolean zab();

    void zac();
}
