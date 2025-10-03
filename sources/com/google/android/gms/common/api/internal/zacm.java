package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zacm extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zack zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacm(zack zackVar, Looper looper) {
        super(looper);
        this.zaa = zackVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i9 = message.what;
        if (i9 == 0) {
            PendingResult<?> pendingResult = (PendingResult) message.obj;
            synchronized (this.zaa.zae) {
                zack zackVar = (zack) Preconditions.checkNotNull(this.zaa.zab);
                if (pendingResult == null) {
                    zackVar.zaa(new Status(13, "Transform returned null"));
                } else if (pendingResult instanceof zabz) {
                    zackVar.zaa(((zabz) pendingResult).zaa());
                } else {
                    zackVar.zaa(pendingResult);
                }
            }
            return;
        }
        if (i9 == 1) {
            RuntimeException runtimeException = (RuntimeException) message.obj;
            String strValueOf = String.valueOf(runtimeException.getMessage());
            Log.e("TransformedResultImpl", strValueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(strValueOf) : new String("Runtime exception on the transformation worker thread: "));
            throw runtimeException;
        }
        StringBuilder sb = new StringBuilder(70);
        sb.append("TransformationResultHandler received unknown message type: ");
        sb.append(i9);
        Log.e("TransformedResultImpl", sb.toString());
    }
}
