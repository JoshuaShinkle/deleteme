package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ForwardingObject {
    public abstract Object delegate();

    public String toString() {
        return delegate().toString();
    }
}
