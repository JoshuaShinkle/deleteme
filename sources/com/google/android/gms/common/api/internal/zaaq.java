package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;
import java.util.Iterator;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* loaded from: classes2.dex */
public final class zaaq implements zaay {

    @NotOnlyInitialized
    private final zaax zaa;

    public zaaq(zaax zaaxVar) {
        this.zaa = zaaxVar;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa() {
        Iterator<Api.Client> it = this.zaa.zaa.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect();
        }
        this.zaa.zad.zac = Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(int i9) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z8) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final boolean zab() {
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zac() {
        this.zaa.zah();
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8) {
        this.zaa.zad.zaa.add(t8);
        return t8;
    }
}
