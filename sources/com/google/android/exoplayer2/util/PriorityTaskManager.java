package com.google.android.exoplayer2.util;

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

/* loaded from: classes.dex */
public final class PriorityTaskManager {
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
    private int highestPriority = Integer.MIN_VALUE;

    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i9, int i10) {
            super("Priority too low [priority=" + i9 + ", highest=" + i10 + "]");
        }
    }

    public void add(int i9) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i9));
            this.highestPriority = Math.max(this.highestPriority, i9);
        }
    }

    public void proceed(int i9) {
        synchronized (this.lock) {
            while (this.highestPriority != i9) {
                this.lock.wait();
            }
        }
    }

    public boolean proceedNonBlocking(int i9) {
        boolean z8;
        synchronized (this.lock) {
            z8 = this.highestPriority == i9;
        }
        return z8;
    }

    public void proceedOrThrow(int i9) {
        synchronized (this.lock) {
            if (this.highestPriority != i9) {
                throw new PriorityTooLowException(i9, this.highestPriority);
            }
        }
    }

    public void remove(int i9) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i9));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : this.queue.peek().intValue();
            this.lock.notifyAll();
        }
    }
}
