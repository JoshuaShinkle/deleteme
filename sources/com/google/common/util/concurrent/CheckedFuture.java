package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.Exception;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible
@Deprecated
@CanIgnoreReturnValue
/* loaded from: classes2.dex */
public interface CheckedFuture<V, X extends Exception> extends ListenableFuture<V> {
    V checkedGet();

    V checkedGet(long j9, TimeUnit timeUnit);
}
