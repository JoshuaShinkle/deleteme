package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
/* loaded from: classes2.dex */
public interface MutableGraph<N> extends Graph<N> {
    @CanIgnoreReturnValue
    boolean addNode(N n8);

    @CanIgnoreReturnValue
    boolean putEdge(N n8, N n9);

    @CanIgnoreReturnValue
    boolean removeEdge(N n8, N n9);

    @CanIgnoreReturnValue
    boolean removeNode(N n8);
}
