package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
/* loaded from: classes2.dex */
final class SerializingExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    private final Deque<Runnable> queue = new ArrayDeque();
    private boolean isWorkerRunning = false;
    private int suspensions = 0;
    private final QueueWorker worker = new QueueWorker();

    public final class QueueWorker implements Runnable {
        private QueueWorker() {
        }

        private void workOnQueue() {
            Runnable runnable;
            while (true) {
                synchronized (SerializingExecutor.this.queue) {
                    runnable = SerializingExecutor.this.suspensions == 0 ? (Runnable) SerializingExecutor.this.queue.pollFirst() : null;
                    if (runnable == null) {
                        SerializingExecutor.this.isWorkerRunning = false;
                        return;
                    }
                }
                try {
                    runnable.run();
                } catch (RuntimeException e9) {
                    SerializingExecutor.log.log(Level.SEVERE, "Exception while executing runnable " + runnable, (Throwable) e9);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e9) {
                synchronized (SerializingExecutor.this.queue) {
                    SerializingExecutor.this.isWorkerRunning = false;
                    throw e9;
                }
            }
        }
    }

    public SerializingExecutor(Executor executor) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    private void startQueueWorker() {
        try {
            this.executor.execute(this.worker);
        } catch (Throwable th) {
            synchronized (this.queue) {
                this.isWorkerRunning = false;
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        synchronized (this.queue) {
            this.queue.addLast(runnable);
            if (!this.isWorkerRunning && this.suspensions <= 0) {
                this.isWorkerRunning = true;
                startQueueWorker();
            }
        }
    }

    public void executeFirst(Runnable runnable) {
        synchronized (this.queue) {
            this.queue.addFirst(runnable);
            if (!this.isWorkerRunning && this.suspensions <= 0) {
                this.isWorkerRunning = true;
                startQueueWorker();
            }
        }
    }

    public void resume() {
        synchronized (this.queue) {
            Preconditions.checkState(this.suspensions > 0);
            int i9 = this.suspensions - 1;
            this.suspensions = i9;
            if (!this.isWorkerRunning && i9 <= 0 && !this.queue.isEmpty()) {
                this.isWorkerRunning = true;
                startQueueWorker();
            }
        }
    }

    public void suspend() {
        synchronized (this.queue) {
            this.suspensions++;
        }
    }
}
