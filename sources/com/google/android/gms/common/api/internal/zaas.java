package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* loaded from: classes2.dex */
final class zaas implements com.google.android.gms.common.internal.zak {
    private final /* synthetic */ zaap zaa;

    public zaas(zaap zaapVar) {
        this.zaa = zaapVar;
    }

    @Override // com.google.android.gms.common.internal.zak
    public final Bundle getConnectionHint() {
        return null;
    }

    @Override // com.google.android.gms.common.internal.zak
    public final boolean isConnected() {
        return this.zaa.isConnected();
    }
}
