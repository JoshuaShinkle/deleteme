package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes2.dex */
final class zaaw extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zaap zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaw(zaap zaapVar, Looper looper) {
        super(looper);
        this.zaa = zaapVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i9 = message.what;
        if (i9 == 1) {
            this.zaa.zaf();
            return;
        }
        if (i9 == 2) {
            this.zaa.zae();
            return;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("Unknown message id: ");
        sb.append(i9);
        Log.w("GoogleApiClientImpl", sb.toString());
    }
}
