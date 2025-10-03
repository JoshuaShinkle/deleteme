package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class CrashlyticsBackgroundWorker {
    private final Executor executor;
    private Task<Void> tail = Tasks.forResult(null);
    private final Object tailLock = new Object();
    private final ThreadLocal<Boolean> isExecutorThread = new ThreadLocal<>();

    public CrashlyticsBackgroundWorker(Executor executor) {
        this.executor = executor;
        executor.execute(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.1
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsBackgroundWorker.this.isExecutorThread.set(Boolean.TRUE);
            }
        });
    }

    private <T> Task<Void> ignoreResult(Task<T> task) {
        return task.continueWith(this.executor, new Continuation<T, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.4
            @Override // com.google.android.gms.tasks.Continuation
            public Void then(Task<T> task2) {
                return null;
            }
        });
    }

    private boolean isRunningOnThread() {
        return Boolean.TRUE.equals(this.isExecutorThread.get());
    }

    private <T> Continuation<Void, T> newContinuation(final Callable<T> callable) {
        return new Continuation<Void, T>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.3
            @Override // com.google.android.gms.tasks.Continuation
            public T then(Task<Void> task) {
                return (T) callable.call();
            }
        };
    }

    public void checkRunningOnThread() {
        if (!isRunningOnThread()) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public Task<Void> submit(final Runnable runnable) {
        return submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.2
            @Override // java.util.concurrent.Callable
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }

    public <T> Task<T> submitTask(Callable<Task<T>> callable) {
        Task<T> taskContinueWithTask;
        synchronized (this.tailLock) {
            taskContinueWithTask = this.tail.continueWithTask(this.executor, newContinuation(callable));
            this.tail = ignoreResult(taskContinueWithTask);
        }
        return taskContinueWithTask;
    }

    public <T> Task<T> submit(Callable<T> callable) {
        Task<T> taskContinueWith;
        synchronized (this.tailLock) {
            taskContinueWith = this.tail.continueWith(this.executor, newContinuation(callable));
            this.tail = ignoreResult(taskContinueWith);
        }
        return taskContinueWith;
    }
}
