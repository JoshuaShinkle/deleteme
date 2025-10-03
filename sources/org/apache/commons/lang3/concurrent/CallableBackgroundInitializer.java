package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class CallableBackgroundInitializer<T> extends BackgroundInitializer<T> {
    private final Callable<T> callable;

    public CallableBackgroundInitializer(Callable<T> callable) {
        checkCallable(callable);
        this.callable = callable;
    }

    private void checkCallable(Callable<T> callable) {
        if (callable == null) {
            throw new IllegalArgumentException("Callable must not be null!");
        }
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public T initialize() {
        return this.callable.call();
    }

    public CallableBackgroundInitializer(Callable<T> callable, ExecutorService executorService) {
        super(executorService);
        checkCallable(callable);
        this.callable = callable;
    }
}
