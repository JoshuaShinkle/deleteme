package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes2.dex */
public class zap extends Handler {
    private static zar zaa;

    public zap() {
    }

    public zap(Looper looper) {
        super(looper);
    }

    public zap(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
