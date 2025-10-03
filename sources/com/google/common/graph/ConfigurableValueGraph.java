package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes2.dex */
class ConfigurableValueGraph<N, V> extends AbstractValueGraph<N, V> {
    private final boolean allowsSelfLoops;
    protected long edgeCount;
    private final boolean isDirected;
    protected final MapIteratorCache<N, GraphConnections<N, V>> nodeConnections;
    private final ElementOrder<N> nodeOrder;

    public ConfigurableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this(abstractGraphBuilder, abstractGraphBuilder.nodeOrder.createMap(abstractGraphBuilder.expectedNodeCount.mo17545or((Optional<Integer>) 10).intValue()), 0L);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n8) {
        return checkedConnections(n8).adjacentNodes();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public final GraphConnections<N, V> checkedConnections(N n8) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n8);
        if (graphConnections != null) {
            return graphConnections;
        }
        Preconditions.checkNotNull(n8);
        throw new IllegalArgumentException("Node " + n8 + " is not an element of this graph.");
    }

    public final boolean containsNode(N n8) {
        return this.nodeConnections.containsKey(n8);
    }

    @Override // com.google.common.graph.AbstractBaseGraph
    public long edgeCount() {
        return this.edgeCount;
    }

    @Override // com.google.common.graph.ValueGraph
    public V edgeValueOrDefault(N n8, N n9, V v8) {
        Preconditions.checkNotNull(n8);
        Preconditions.checkNotNull(n9);
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n8);
        return graphConnections == null ? v8 : graphConnections.value(n9);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n8, N n9) {
        Preconditions.checkNotNull(n8);
        Preconditions.checkNotNull(n9);
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n8);
        return graphConnections != null && graphConnections.successors().contains(n9);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ConfigurableValueGraph<N, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ConfigurableValueGraph<N, V>) obj);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n8) {
        return checkedConnections(n8).predecessors();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n8) {
        return checkedConnections(n8).successors();
    }

    public ConfigurableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder, Map<N, GraphConnections<N, V>> map, long j9) {
        this.isDirected = abstractGraphBuilder.directed;
        this.allowsSelfLoops = abstractGraphBuilder.allowsSelfLoops;
        this.nodeOrder = (ElementOrder<N>) abstractGraphBuilder.nodeOrder.cast();
        this.nodeConnections = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        this.edgeCount = Graphs.checkNonNegative(j9);
    }
}
