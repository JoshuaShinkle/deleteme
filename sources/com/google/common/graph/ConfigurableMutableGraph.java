package com.google.common.graph;

import com.google.common.graph.GraphConstants;

/* loaded from: classes2.dex */
final class ConfigurableMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
    private final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    public ConfigurableMutableGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this.backingValueGraph = new ConfigurableMutableValueGraph(abstractGraphBuilder);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean addNode(N n8) {
        return this.backingValueGraph.addNode(n8);
    }

    @Override // com.google.common.graph.ForwardingGraph
    public BaseGraph<N> delegate() {
        return this.backingValueGraph;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(N n8, N n9) {
        return this.backingValueGraph.putEdgeValue(n8, n9, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(N n8, N n9) {
        return this.backingValueGraph.removeEdge(n8, n9) != null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeNode(N n8) {
        return this.backingValueGraph.removeNode(n8);
    }
}
