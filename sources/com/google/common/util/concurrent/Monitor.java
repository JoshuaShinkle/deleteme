package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Monitor {
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;

        @Weak
        final Monitor monitor;
        Guard next;
        int waiterCount = 0;

        public Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }

        public abstract boolean isSatisfied();
    }

    public Monitor() {
        this(false);
    }

    private void await(Guard guard, boolean z8) {
        if (z8) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private boolean awaitNanos(Guard guard, long j9, boolean z8) {
        boolean z9 = true;
        while (j9 > 0) {
            if (z9) {
                if (z8) {
                    try {
                        signalNextWaiter();
                    } finally {
                        if (!z9) {
                            endWaitingFor(guard);
                        }
                    }
                }
                beginWaitingFor(guard);
                z9 = false;
            }
            j9 = guard.condition.awaitNanos(j9);
            if (guard.isSatisfied()) {
                if (!z9) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        return false;
    }

    private void awaitUninterruptibly(Guard guard, boolean z8) {
        if (z8) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private void beginWaitingFor(Guard guard) {
        int i9 = guard.waiterCount;
        guard.waiterCount = i9 + 1;
        if (i9 == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int i9 = guard.waiterCount - 1;
        guard.waiterCount = i9;
        if (i9 == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private static long initNanoTime(long j9) {
        if (j9 <= 0) {
            return 0L;
        }
        long jNanoTime = System.nanoTime();
        if (jNanoTime == 0) {
            return 1L;
        }
        return jNanoTime;
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw Throwables.propagate(th);
        }
    }

    private static long remainingNanos(long j9, long j10) {
        if (j10 <= 0) {
            return 0L;
        }
        return j10 - (System.nanoTime() - j9);
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private static long toSafeNanos(long j9, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j9);
        long j10 = 0;
        if (nanos > 0) {
            j10 = 6917529027641081853L;
            if (nanos <= 6917529027641081853L) {
                return nanos;
            }
        }
        return j10;
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean zIsSatisfied = guard.isSatisfied();
            if (!zIsSatisfied) {
            }
            return zIsSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        try {
            boolean zIsSatisfied = guard.isSatisfied();
            if (!zIsSatisfied) {
            }
            return zIsSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean zIsHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lockInterruptibly();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            await(guard, zIsHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean zIsHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lock();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            awaitUninterruptibly(guard, zIsHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            return guard.waiterCount;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock()) {
            return false;
        }
        try {
            boolean zIsSatisfied = guard.isSatisfied();
            if (!zIsSatisfied) {
            }
            return zIsSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void waitFor(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        await(guard, true);
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        awaitUninterruptibly(guard, true);
    }

    public Monitor(boolean z8) {
        this.activeGuards = null;
        this.fair = z8;
        this.lock = new ReentrantLock(z8);
    }

    public boolean enter(long j9, TimeUnit timeUnit) throws Throwable {
        boolean zTryLock;
        long safeNanos = toSafeNanos(j9, timeUnit);
        ReentrantLock reentrantLock = this.lock;
        boolean z8 = true;
        if (!this.fair && reentrantLock.tryLock()) {
            return true;
        }
        boolean zInterrupted = Thread.interrupted();
        try {
            long jNanoTime = System.nanoTime();
            long jRemainingNanos = safeNanos;
            while (true) {
                try {
                    try {
                        zTryLock = reentrantLock.tryLock(jRemainingNanos, TimeUnit.NANOSECONDS);
                        break;
                    } catch (Throwable th) {
                        th = th;
                        if (z8) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    jRemainingNanos = remainingNanos(jNanoTime, safeNanos);
                    zInterrupted = true;
                }
            }
            if (zInterrupted) {
                Thread.currentThread().interrupt();
            }
            return zTryLock;
        } catch (Throwable th2) {
            th = th2;
            z8 = zInterrupted;
        }
    }

    public boolean enterInterruptibly(long j9, TimeUnit timeUnit) {
        return this.lock.tryLock(j9, timeUnit);
    }

    public boolean waitFor(Guard guard, long j9, TimeUnit timeUnit) throws InterruptedException {
        long safeNanos = toSafeNanos(j9, timeUnit);
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            if (!Thread.interrupted()) {
                return awaitNanos(guard, safeNanos, true);
            }
            throw new InterruptedException();
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean waitForUninterruptibly(Guard guard, long j9, TimeUnit timeUnit) throws Throwable {
        long safeNanos = toSafeNanos(j9, timeUnit);
        boolean z8 = true;
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            long jInitNanoTime = initNanoTime(safeNanos);
            boolean zInterrupted = Thread.interrupted();
            long jRemainingNanos = safeNanos;
            boolean z9 = true;
            while (true) {
                try {
                    try {
                        boolean zAwaitNanos = awaitNanos(guard, jRemainingNanos, z9);
                        if (zInterrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return zAwaitNanos;
                    } catch (Throwable th) {
                        th = th;
                        if (z8) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    if (guard.isSatisfied()) {
                        Thread.currentThread().interrupt();
                        return true;
                    }
                    jRemainingNanos = remainingNanos(jInitNanoTime, safeNanos);
                    z9 = false;
                    zInterrupted = true;
                } catch (Throwable th2) {
                    th = th2;
                    z8 = zInterrupted;
                    if (z8) {
                    }
                    throw th;
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long j9, TimeUnit timeUnit) {
        if (guard.monitor == this) {
            if (!enter(j9, timeUnit)) {
                return false;
            }
            try {
                boolean zIsSatisfied = guard.isSatisfied();
                if (!zIsSatisfied) {
                }
                return zIsSatisfied;
            } finally {
                this.lock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard, long j9, TimeUnit timeUnit) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock(j9, timeUnit)) {
                return false;
            }
            try {
                boolean zIsSatisfied = guard.isSatisfied();
                if (!zIsSatisfied) {
                }
                return zIsSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhen(Guard guard, long j9, TimeUnit timeUnit) throws InterruptedException {
        long jInitNanoTime;
        long safeNanos = toSafeNanos(j9, timeUnit);
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean zIsHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            boolean z8 = false;
            if (!this.fair) {
                if (!Thread.interrupted()) {
                    if (reentrantLock.tryLock()) {
                        jInitNanoTime = 0;
                    }
                } else {
                    throw new InterruptedException();
                }
            } else {
                jInitNanoTime = initNanoTime(safeNanos);
                if (!reentrantLock.tryLock(j9, timeUnit)) {
                    return false;
                }
            }
            try {
                if (guard.isSatisfied()) {
                    z8 = true;
                } else {
                    if (jInitNanoTime != 0) {
                        safeNanos = remainingNanos(jInitNanoTime, safeNanos);
                    }
                    if (awaitNanos(guard, safeNanos, zIsHeldByCurrentThread)) {
                    }
                }
                if (!z8) {
                }
                return z8;
            } catch (Throwable th) {
                if (!zIsHeldByCurrentThread) {
                    try {
                        signalNextWaiter();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
                throw th;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterWhenUninterruptibly(Guard guard, long j9, TimeUnit timeUnit) throws Throwable {
        long jInitNanoTime;
        long jRemainingNanos;
        long safeNanos = toSafeNanos(j9, timeUnit);
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean zIsHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            boolean zInterrupted = Thread.interrupted();
            try {
                boolean zAwaitNanos = true;
                if (this.fair || !reentrantLock.tryLock()) {
                    jInitNanoTime = initNanoTime(safeNanos);
                    long jRemainingNanos2 = safeNanos;
                    while (true) {
                        try {
                            try {
                                break;
                            } catch (Throwable th) {
                                th = th;
                                zInterrupted = true;
                                if (zInterrupted) {
                                    Thread.currentThread().interrupt();
                                }
                                throw th;
                            }
                        } catch (InterruptedException unused) {
                            jRemainingNanos2 = remainingNanos(jInitNanoTime, safeNanos);
                            zInterrupted = true;
                        }
                    }
                    if (!reentrantLock.tryLock(jRemainingNanos2, TimeUnit.NANOSECONDS)) {
                        if (zInterrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return false;
                    }
                } else {
                    jInitNanoTime = 0;
                }
                while (!guard.isSatisfied()) {
                    try {
                        if (jInitNanoTime == 0) {
                            jInitNanoTime = initNanoTime(safeNanos);
                            jRemainingNanos = safeNanos;
                        } else {
                            jRemainingNanos = remainingNanos(jInitNanoTime, safeNanos);
                        }
                        zAwaitNanos = awaitNanos(guard, jRemainingNanos, zIsHeldByCurrentThread);
                    } catch (InterruptedException unused2) {
                        zIsHeldByCurrentThread = false;
                        zInterrupted = zAwaitNanos;
                    } catch (Throwable th2) {
                        reentrantLock.unlock();
                        throw th2;
                    }
                }
                if (!zAwaitNanos) {
                    reentrantLock.unlock();
                }
                if (zInterrupted) {
                    Thread.currentThread().interrupt();
                }
                return zAwaitNanos;
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }
}
