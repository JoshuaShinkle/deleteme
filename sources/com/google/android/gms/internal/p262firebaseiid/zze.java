package com.google.android.gms.internal.p262firebaseiid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes2.dex */
public class zze extends Handler {
    private static zzg propagator;

    public zze() {
    }

    private void prepare(Message message, long j9) {
        zzg zzgVar = propagator;
        if (zzgVar != null) {
            zzgVar.zza(this, message, j9);
        }
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        zzg zzgVar = propagator;
        if (zzgVar == null) {
            dispatchMessageTraced(message);
            return;
        }
        Object objZza = zzgVar.zza(this, message);
        try {
            dispatchMessageTraced(message);
        } finally {
        }
    }

    public void dispatchMessageTraced(Message message) {
        super.dispatchMessage(message);
    }

    @Override // android.os.Handler
    public boolean sendMessageAtTime(Message message, long j9) {
        prepare(message, j9);
        return super.sendMessageAtTime(message, j9);
    }

    public zze(Looper looper) {
        super(looper);
    }

    public zze(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
