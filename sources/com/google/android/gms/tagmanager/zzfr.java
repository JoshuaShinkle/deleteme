package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes2.dex */
final class zzfr implements zzfq {
    private Handler handler;
    final /* synthetic */ zzfn zzakz;

    private zzfr(zzfn zzfnVar) {
        this.zzakz = zzfnVar;
        this.handler = new com.google.android.gms.internal.gtm.zzdj(zzfnVar.zzako.getMainLooper(), new zzfs(this));
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzfn.zzakn);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void cancel() {
        this.handler.removeMessages(1, zzfn.zzakn);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void zzh(long j9) {
        this.handler.removeMessages(1, zzfn.zzakn);
        this.handler.sendMessageDelayed(obtainMessage(), j9);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void zzjt() {
        this.handler.removeMessages(1, zzfn.zzakn);
        this.handler.sendMessage(obtainMessage());
    }

    public /* synthetic */ zzfr(zzfn zzfnVar, zzfo zzfoVar) {
        this(zzfnVar);
    }
}
