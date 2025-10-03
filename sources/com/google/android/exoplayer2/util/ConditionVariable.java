package com.google.android.exoplayer2.util;

/* loaded from: classes.dex */
public final class ConditionVariable {
    private boolean isOpen;

    public synchronized void block() {
        while (!this.isOpen) {
            wait();
        }
    }

    public synchronized boolean close() {
        boolean z8;
        z8 = this.isOpen;
        this.isOpen = false;
        return z8;
    }

    public synchronized boolean open() {
        if (this.isOpen) {
            return false;
        }
        this.isOpen = true;
        notifyAll();
        return true;
    }

    public synchronized boolean block(long j9) {
        boolean z8;
        long jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
        long j10 = j9 + jElapsedRealtime;
        while (true) {
            z8 = this.isOpen;
            if (z8 || jElapsedRealtime >= j10) {
                break;
            }
            wait(j10 - jElapsedRealtime);
            jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
        }
        return z8;
    }
}
