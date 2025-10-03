package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
class DelegatingScheduledExecutorService implements ScheduledExecutorService {
    private final ExecutorService delegate;
    private final ScheduledExecutorService scheduler;

    public DelegatingScheduledExecutorService(ExecutorService executorService, ScheduledExecutorService scheduledExecutorService) {
        this.delegate = executorService;
        this.scheduler = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$schedule$0(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        try {
            runnable.run();
            completer.set(null);
        } catch (Exception e9) {
            completer.setException(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$schedule$1(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.m
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$schedule$0(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScheduledFuture lambda$schedule$2(final Runnable runnable, long j9, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.schedule(new Runnable() { // from class: com.google.firebase.concurrent.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f15522b.lambda$schedule$1(runnable, completer);
            }
        }, j9, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$schedule$3(Callable callable, DelegatingScheduledFuture.Completer completer) {
        try {
            completer.set(callable.call());
        } catch (Exception e9) {
            completer.setException(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Future lambda$schedule$4(final Callable callable, final DelegatingScheduledFuture.Completer completer) {
        return this.delegate.submit(new Runnable() { // from class: com.google.firebase.concurrent.e
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$schedule$3(callable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScheduledFuture lambda$schedule$5(final Callable callable, long j9, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.schedule(new Callable() { // from class: com.google.firebase.concurrent.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f15495b.lambda$schedule$4(callable, completer);
            }
        }, j9, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scheduleAtFixedRate$6(Runnable runnable, DelegatingScheduledFuture.Completer completer) throws Exception {
        try {
            runnable.run();
        } catch (Exception e9) {
            completer.setException(e9);
            throw e9;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleAtFixedRate$7(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.f
            @Override // java.lang.Runnable
            public final void run() throws Exception {
                DelegatingScheduledExecutorService.lambda$scheduleAtFixedRate$6(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScheduledFuture lambda$scheduleAtFixedRate$8(final Runnable runnable, long j9, long j10, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.scheduleAtFixedRate(new Runnable() { // from class: com.google.firebase.concurrent.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f15502b.lambda$scheduleAtFixedRate$7(runnable, completer);
            }
        }, j9, j10, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleWithFixedDelay$10(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.c
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$scheduleWithFixedDelay$9(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScheduledFuture lambda$scheduleWithFixedDelay$11(final Runnable runnable, long j9, long j10, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.google.firebase.concurrent.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f15514b.lambda$scheduleWithFixedDelay$10(runnable, completer);
            }
        }, j9, j10, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scheduleWithFixedDelay$9(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        try {
            runnable.run();
        } catch (Exception e9) {
            completer.setException(e9);
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j9, TimeUnit timeUnit) {
        return this.delegate.awaitTermination(j9, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.delegate.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.delegate.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.delegate.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(final Runnable runnable, final long j9, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.h
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return this.f15505a.lambda$schedule$2(runnable, j9, timeUnit, completer);
            }
        });
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(final Runnable runnable, final long j9, final long j10, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.k
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return this.f15517a.lambda$scheduleAtFixedRate$8(runnable, j9, j10, timeUnit, completer);
            }
        });
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable runnable, final long j9, final long j10, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.i
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return this.f15509a.lambda$scheduleWithFixedDelay$11(runnable, j9, j10, timeUnit, completer);
            }
        });
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
        return this.delegate.invokeAll(collection, j9, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j9, TimeUnit timeUnit) {
        return (T) this.delegate.invokeAny(collection, j9, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long j9, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.b
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return this.f15489a.lambda$schedule$5(callable, j9, timeUnit, completer);
            }
        });
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t8) {
        return this.delegate.submit(runnable, t8);
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.delegate.submit(runnable);
    }
}
