package org.apache.commons.lang3.concurrent;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class TimedSemaphore {
    public static final int NO_LIMIT = 0;
    private static final int THREAD_POOL_SIZE = 1;
    private int acquireCount;
    private final ScheduledExecutorService executorService;
    private int lastCallsPerPeriod;
    private int limit;
    private final boolean ownExecutor;
    private final long period;
    private long periodCount;
    private boolean shutdown;
    private ScheduledFuture<?> task;
    private long totalAcquireCount;
    private final TimeUnit unit;

    public TimedSemaphore(long j9, TimeUnit timeUnit, int i9) {
        this(null, j9, timeUnit, i9);
    }

    public synchronized void acquire() {
        boolean z8;
        if (isShutdown()) {
            throw new IllegalStateException("TimedSemaphore is shut down!");
        }
        if (this.task == null) {
            this.task = startTimer();
        }
        do {
            z8 = getLimit() <= 0 || this.acquireCount < getLimit();
            if (z8) {
                this.acquireCount++;
            } else {
                wait();
            }
        } while (!z8);
    }

    public synchronized void endOfPeriod() {
        int i9 = this.acquireCount;
        this.lastCallsPerPeriod = i9;
        this.totalAcquireCount += i9;
        this.periodCount++;
        this.acquireCount = 0;
        notifyAll();
    }

    public synchronized int getAcquireCount() {
        return this.acquireCount;
    }

    public synchronized int getAvailablePermits() {
        return getLimit() - getAcquireCount();
    }

    public synchronized double getAverageCallsPerPeriod() {
        long j9;
        j9 = this.periodCount;
        return j9 == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : this.totalAcquireCount / j9;
    }

    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    public synchronized int getLastAcquiresPerPeriod() {
        return this.lastCallsPerPeriod;
    }

    public final synchronized int getLimit() {
        return this.limit;
    }

    public long getPeriod() {
        return this.period;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public final synchronized void setLimit(int i9) {
        this.limit = i9;
    }

    public synchronized void shutdown() {
        if (!this.shutdown) {
            if (this.ownExecutor) {
                getExecutorService().shutdownNow();
            }
            ScheduledFuture<?> scheduledFuture = this.task;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.shutdown = true;
        }
    }

    public ScheduledFuture<?> startTimer() {
        return getExecutorService().scheduleAtFixedRate(new Runnable() { // from class: org.apache.commons.lang3.concurrent.TimedSemaphore.1
            @Override // java.lang.Runnable
            public void run() {
                TimedSemaphore.this.endOfPeriod();
            }
        }, getPeriod(), getPeriod(), getUnit());
    }

    public TimedSemaphore(ScheduledExecutorService scheduledExecutorService, long j9, TimeUnit timeUnit, int i9) {
        if (j9 <= 0) {
            throw new IllegalArgumentException("Time period must be greater 0!");
        }
        this.period = j9;
        this.unit = timeUnit;
        if (scheduledExecutorService != null) {
            this.executorService = scheduledExecutorService;
            this.ownExecutor = false;
        } else {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            this.executorService = scheduledThreadPoolExecutor;
            this.ownExecutor = true;
        }
        setLimit(i9);
    }
}
