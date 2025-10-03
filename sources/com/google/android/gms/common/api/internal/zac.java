package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: classes2.dex */
public abstract class zac {
    public final int zaa;

    public zac(int i9) {
        this.zaa = i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zab(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }

    public abstract void zaa(Status status);

    public abstract void zaa(zaw zawVar, boolean z8);

    public abstract void zaa(Exception exc);

    public abstract void zac(GoogleApiManager.zaa<?> zaaVar);
}
