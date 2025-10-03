package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Uninterruptibles {
    private Uninterruptibles() {
    }

    @GwtIncompatible
    public static void awaitUninterruptibly(CountDownLatch countDownLatch) {
        boolean z8 = false;
        while (true) {
            try {
                countDownLatch.await();
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    @CanIgnoreReturnValue
    public static <V> V getUninterruptibly(Future<V> future) {
        V v8;
        boolean z8 = false;
        while (true) {
            try {
                v8 = future.get();
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
        return v8;
    }

    @GwtIncompatible
    public static void joinUninterruptibly(Thread thread) {
        boolean z8 = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    @GwtIncompatible
    public static <E> void putUninterruptibly(BlockingQueue<E> blockingQueue, E e9) {
        boolean z8 = false;
        while (true) {
            try {
                blockingQueue.put(e9);
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    @GwtIncompatible
    public static void sleepUninterruptibly(long j9, TimeUnit timeUnit) {
        boolean z8 = false;
        try {
            long nanos = timeUnit.toNanos(j9);
            long jNanoTime = System.nanoTime() + nanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.sleep(nanos);
                    break;
                } catch (InterruptedException unused) {
                    z8 = true;
                    nanos = jNanoTime - System.nanoTime();
                }
            }
        } finally {
            if (z8) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    public static <E> E takeUninterruptibly(BlockingQueue<E> blockingQueue) {
        E eTake;
        boolean z8 = false;
        while (true) {
            try {
                eTake = blockingQueue.take();
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
        return eTake;
    }

    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, long j9, TimeUnit timeUnit) {
        return tryAcquireUninterruptibly(semaphore, 1, j9, timeUnit);
    }

    @GwtIncompatible
    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int i9, long j9, TimeUnit timeUnit) {
        boolean z8 = false;
        try {
            long nanos = timeUnit.toNanos(j9);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z8 = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return semaphore.tryAcquire(i9, nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z8) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j9, TimeUnit timeUnit) {
        boolean z8 = false;
        try {
            long nanos = timeUnit.toNanos(j9);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z8 = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return countDownLatch.await(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z8) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static <V> V getUninterruptibly(Future<V> future, long j9, TimeUnit timeUnit) {
        boolean z8 = false;
        try {
            long nanos = timeUnit.toNanos(j9);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z8 = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return future.get(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z8) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @GwtIncompatible
    public static void joinUninterruptibly(Thread thread, long j9, TimeUnit timeUnit) {
        Preconditions.checkNotNull(thread);
        boolean z8 = false;
        try {
            long nanos = timeUnit.toNanos(j9);
            long jNanoTime = System.nanoTime() + nanos;
            while (true) {
                try {
                    TimeUnit.NANOSECONDS.timedJoin(thread, nanos);
                    break;
                } catch (InterruptedException unused) {
                    z8 = true;
                    nanos = jNanoTime - System.nanoTime();
                }
            }
        } finally {
            if (z8) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
