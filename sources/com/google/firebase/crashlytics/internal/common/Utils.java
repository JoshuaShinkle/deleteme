package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes2.dex */
public final class Utils {
    private static final ExecutorService TASK_CONTINUATION_EXECUTOR_SERVICE = ExecutorUtils.buildSingleThreadExecutorService("awaitEvenIfOnMainThread task continuation executor");
    private static final int TIMEOUT_SEC = 4;

    private Utils() {
    }

    public static <T> T awaitEvenIfOnMainThread(Task<T> task) throws InterruptedException, TimeoutException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        task.continueWith(TASK_CONTINUATION_EXECUTOR_SERVICE, new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.q
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return Utils.lambda$awaitEvenIfOnMainThread$4(countDownLatch, task2);
            }
        });
        if (Looper.getMainLooper() == Looper.myLooper()) {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } else {
            countDownLatch.await(4L, TimeUnit.SECONDS);
        }
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        if (task.isComplete()) {
            throw new IllegalStateException(task.getException());
        }
        throw new TimeoutException();
    }

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

    public static <T> Task<T> callTask(final Executor executor, final Callable<Task<T>> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.s
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$callTask$3(callable, executor, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$awaitEvenIfOnMainThread$4(CountDownLatch countDownLatch, Task task) {
        countDownLatch.countDown();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$callTask$2(TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(task.getResult());
            return null;
        }
        if (task.getException() == null) {
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$callTask$3(Callable callable, Executor executor, final TaskCompletionSource taskCompletionSource) {
        try {
            ((Task) callable.call()).continueWith(executor, new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.o
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return Utils.lambda$callTask$2(taskCompletionSource, task);
                }
            });
        } catch (Exception e9) {
            taskCompletionSource.setException(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void lambda$race$0(TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
            return null;
        }
        if (task.getException() == null) {
            return null;
        }
        taskCompletionSource.trySetException(task.getException());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void lambda$race$1(TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
            return null;
        }
        if (task.getException() == null) {
            return null;
        }
        taskCompletionSource.trySetException(task.getException());
        return null;
    }

    @SuppressLint({"TaskMainThread"})
    public static <T> Task<T> race(Task<T> task, Task<T> task2) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Continuation<T, TContinuationResult> continuation = new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.p
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task3) {
                return Utils.lambda$race$0(taskCompletionSource, task3);
            }
        };
        task.continueWith(continuation);
        task2.continueWith(continuation);
        return taskCompletionSource.getTask();
    }

    public static <T> Task<T> race(Executor executor, Task<T> task, Task<T> task2) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Continuation<T, TContinuationResult> continuation = new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.r
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task3) {
                return Utils.lambda$race$1(taskCompletionSource, task3);
            }
        };
        task.continueWith(executor, continuation);
        task2.continueWith(executor, continuation);
        return taskCompletionSource.getTask();
    }
}
