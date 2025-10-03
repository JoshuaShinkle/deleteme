package com.google.firebase.inject;

import com.google.firebase.annotations.DeferredApi;

/* loaded from: classes2.dex */
public interface Deferred<T> {

    public interface DeferredHandler<T> {
        @DeferredApi
        void handle(Provider<T> provider);
    }

    void whenAvailable(DeferredHandler<T> deferredHandler);
}
