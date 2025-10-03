package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class SystemHandlerWrapper implements HandlerWrapper {
    private final Handler handler;

    public SystemHandlerWrapper(Handler handler) {
        this.handler = handler;
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Looper getLooper() {
        return this.handler.getLooper();
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Message obtainMessage(int i9) {
        return this.handler.obtainMessage(i9);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean postDelayed(Runnable runnable, long j9) {
        return this.handler.postDelayed(runnable, j9);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public void removeCallbacksAndMessages(Object obj) {
        this.handler.removeCallbacksAndMessages(obj);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public void removeMessages(int i9) {
        this.handler.removeMessages(i9);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendEmptyMessage(int i9) {
        return this.handler.sendEmptyMessage(i9);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendEmptyMessageAtTime(int i9, long j9) {
        return this.handler.sendEmptyMessageAtTime(i9, j9);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Message obtainMessage(int i9, Object obj) {
        return this.handler.obtainMessage(i9, obj);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Message obtainMessage(int i9, int i10, int i11) {
        return this.handler.obtainMessage(i9, i10, i11);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Message obtainMessage(int i9, int i10, int i11, Object obj) {
        return this.handler.obtainMessage(i9, i10, i11, obj);
    }
}
