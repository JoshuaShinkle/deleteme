package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.WeakReference;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class FinalizableWeakReference<T> extends WeakReference<T> implements FinalizableReference {
    public FinalizableWeakReference(T t8, FinalizableReferenceQueue finalizableReferenceQueue) {
        super(t8, finalizableReferenceQueue.queue);
        finalizableReferenceQueue.cleanUp();
    }
}
