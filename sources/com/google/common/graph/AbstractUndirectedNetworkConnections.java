package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> incidentEdgeMap;

    public AbstractUndirectedNetworkConnections(Map<E, N> map) {
        this.incidentEdgeMap = (Map) Preconditions.checkNotNull(map);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e9, N n8, boolean z8) {
        if (z8) {
            return;
        }
        addOutEdge(e9, n8);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e9, N n8) {
        Preconditions.checkState(this.incidentEdgeMap.put(e9, n8) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e9) {
        return (N) Preconditions.checkNotNull(this.incidentEdgeMap.get(e9));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return Collections.unmodifiableSet(this.incidentEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e9, boolean z8) {
        if (z8) {
            return null;
        }
        return removeOutEdge(e9);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e9) {
        return (N) Preconditions.checkNotNull(this.incidentEdgeMap.remove(e9));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return adjacentNodes();
    }
}
