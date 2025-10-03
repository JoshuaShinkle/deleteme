package com.google.android.exoplayer2.util;

import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public interface HandlerWrapper {
    Looper getLooper();

    Message obtainMessage(int i9);

    Message obtainMessage(int i9, int i10, int i11);

    Message obtainMessage(int i9, int i10, int i11, Object obj);

    Message obtainMessage(int i9, Object obj);

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j9);

    void removeCallbacksAndMessages(Object obj);

    void removeMessages(int i9);

    boolean sendEmptyMessage(int i9);

    boolean sendEmptyMessageAtTime(int i9, long j9);
}
