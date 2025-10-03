package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
/* loaded from: classes2.dex */
public interface MutableValueGraph<N, V> extends ValueGraph<N, V> {
    @CanIgnoreReturnValue
    boolean addNode(N n8);

    @CanIgnoreReturnValue
    V putEdgeValue(N n8, N n9, V v8);

    @CanIgnoreReturnValue
    V removeEdge(N n8, N n9);

    @CanIgnoreReturnValue
    boolean removeNode(N n8);
}
