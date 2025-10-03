package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/* loaded from: classes2.dex */
interface GraphConnections<N, V> {
    void addPredecessor(N n8, V v8);

    @CanIgnoreReturnValue
    V addSuccessor(N n8, V v8);

    Set<N> adjacentNodes();

    Set<N> predecessors();

    void removePredecessor(N n8);

    @CanIgnoreReturnValue
    V removeSuccessor(N n8);

    Set<N> successors();

    V value(N n8);
}
