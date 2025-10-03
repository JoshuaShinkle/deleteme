package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;

@Beta
/* loaded from: classes2.dex */
public interface Graph<N> extends BaseGraph<N> {
    Set<N> adjacentNodes(N n8);

    boolean allowsSelfLoops();

    @Override // com.google.common.graph.BaseGraph
    int degree(N n8);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(Object obj);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n8, N n9);

    int hashCode();

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N n8);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n8);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N n8);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n8);
}
