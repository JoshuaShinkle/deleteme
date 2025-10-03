package com.google.common.graph;

import java.util.Set;

/* loaded from: classes2.dex */
interface BaseGraph<N> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<N> adjacentNodes(N n8);

    boolean allowsSelfLoops();

    int degree(N n8);

    Set<EndpointPair<N>> edges();

    boolean hasEdgeConnecting(N n8, N n9);

    int inDegree(N n8);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N n8);

    Set<N> predecessors(N n8);

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n8);
}
