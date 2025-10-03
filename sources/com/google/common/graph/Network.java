package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;

@Beta
/* loaded from: classes2.dex */
public interface Network<N, E> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<E> adjacentEdges(E e9);

    Set<N> adjacentNodes(N n8);

    boolean allowsParallelEdges();

    boolean allowsSelfLoops();

    Graph<N> asGraph();

    int degree(N n8);

    E edgeConnectingOrNull(N n8, N n9);

    ElementOrder<E> edgeOrder();

    Set<E> edges();

    Set<E> edgesConnecting(N n8, N n9);

    boolean equals(Object obj);

    boolean hasEdgeConnecting(N n8, N n9);

    int hashCode();

    int inDegree(N n8);

    Set<E> inEdges(N n8);

    Set<E> incidentEdges(N n8);

    EndpointPair<N> incidentNodes(E e9);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N n8);

    Set<E> outEdges(N n8);

    Set<N> predecessors(N n8);

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n8);
}
