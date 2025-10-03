package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: classes2.dex */
final class zabf implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zaa zaa;

    public zabf(GoogleApiManager.zaa zaaVar) {
        this.zaa = zaaVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        GoogleApiManager.this.zaq.post(new zabh(this));
    }
}
