package com.google.android.datatransport.runtime.retries;

/* loaded from: classes.dex */
public final class Retries {
    private Retries() {
    }

    public static <TInput, TResult, TException extends Throwable> TResult retry(int i9, TInput tinput, Function<TInput, TResult, TException> function, RetryStrategy<TInput, TResult> retryStrategy) {
        TResult tresultApply;
        if (i9 < 1) {
            return function.apply(tinput);
        }
        do {
            tresultApply = function.apply(tinput);
            tinput = retryStrategy.shouldRetry(tinput, tresultApply);
            if (tinput == null) {
                break;
            }
            i9--;
        } while (i9 >= 1);
        return tresultApply;
    }
}
