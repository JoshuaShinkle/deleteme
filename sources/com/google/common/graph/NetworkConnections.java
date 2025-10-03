package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/* loaded from: classes2.dex */
interface NetworkConnections<N, E> {
    void addInEdge(E e9, N n8, boolean z8);

    void addOutEdge(E e9, N n8);

    N adjacentNode(E e9);

    Set<N> adjacentNodes();

    Set<E> edgesConnecting(N n8);

    Set<E> inEdges();

    Set<E> incidentEdges();

    Set<E> outEdges();

    Set<N> predecessors();

    @CanIgnoreReturnValue
    N removeInEdge(E e9, boolean z8);

    @CanIgnoreReturnValue
    N removeOutEdge(E e9);

    Set<N> successors();
}
