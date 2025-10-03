package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
/* loaded from: classes2.dex */
public interface MutableNetwork<N, E> extends Network<N, E> {
    @CanIgnoreReturnValue
    boolean addEdge(N n8, N n9, E e9);

    @CanIgnoreReturnValue
    boolean addNode(N n8);

    @CanIgnoreReturnValue
    boolean removeEdge(E e9);

    @CanIgnoreReturnValue
    boolean removeNode(N n8);
}
