package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.atomic.AtomicReference;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class InterruptibleTask extends AtomicReference<Thread> implements Runnable {
    private volatile boolean doneInterrupting;

    public final void interruptTask() {
        Thread thread = get();
        if (thread != null) {
            thread.interrupt();
        }
        this.doneInterrupting = true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (compareAndSet(null, Thread.currentThread())) {
            try {
                runInterruptibly();
            } finally {
                if (wasInterrupted()) {
                    while (!this.doneInterrupting) {
                        Thread.yield();
                    }
                }
            }
        }
    }

    public abstract void runInterruptibly();

    @Override // java.util.concurrent.atomic.AtomicReference
    public abstract String toString();

    public abstract boolean wasInterrupted();
}
