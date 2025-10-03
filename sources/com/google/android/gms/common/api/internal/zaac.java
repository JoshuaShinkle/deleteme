package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zaac implements zaay {
    private final zaax zaa;
    private boolean zab = false;

    public zaac(zaax zaaxVar) {
        this.zaa = zaaxVar;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t8) {
        return (T) zab(t8);
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa() {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z8) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t8) {
        try {
            this.zaa.zad.zae.zaa(t8);
            zaap zaapVar = this.zaa.zad;
            Api.Client client = zaapVar.zab.get(t8.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zaa.zab.containsKey(t8.getClientKey())) {
                t8.run(client);
            } else {
                t8.setFailedResult(new Status(17));
            }
        } catch (DeadObjectException unused) {
            this.zaa.zaa(new zaab(this, this));
        }
        return t8;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zac() {
        if (this.zab) {
            this.zab = false;
            this.zaa.zaa(new zaae(this, this));
        }
    }

    public final void zad() {
        if (this.zab) {
            this.zab = false;
            this.zaa.zad.zae.zaa();
            zab();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(int i9) {
        this.zaa.zaa((ConnectionResult) null);
        this.zaa.zae.zaa(i9, this.zab);
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final boolean zab() {
        if (this.zab) {
            return false;
        }
        Set<zack> set = this.zaa.zad.zad;
        if (set != null && !set.isEmpty()) {
            this.zab = true;
            Iterator<zack> it = set.iterator();
            while (it.hasNext()) {
                it.next().zaa();
            }
            return false;
        }
        this.zaa.zaa((ConnectionResult) null);
        return true;
    }
}
