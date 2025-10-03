package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes2.dex */
final class zaaz extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zaax zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaz(zaax zaaxVar, Looper looper) {
        super(looper);
        this.zaa = zaaxVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i9 = message.what;
        if (i9 == 1) {
            ((zaba) message.obj).zaa(this.zaa);
        } else {
            if (i9 == 2) {
                throw ((RuntimeException) message.obj);
            }
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i9);
            Log.w("GACStateManager", sb.toString());
        }
    }
}
