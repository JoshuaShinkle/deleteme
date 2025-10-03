package com.google.common.graph;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@GwtIncompatible
/* loaded from: classes2.dex */
class ConfigurableNetwork<N, E> extends AbstractNetwork<N, E> {
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;
    private final ElementOrder<E> edgeOrder;
    protected final MapIteratorCache<E, N> edgeToReferenceNode;
    private final boolean isDirected;
    protected final MapIteratorCache<N, NetworkConnections<N, E>> nodeConnections;
    private final ElementOrder<N> nodeOrder;

    public ConfigurableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        this(networkBuilder, networkBuilder.nodeOrder.createMap(networkBuilder.expectedNodeCount.mo17545or((Optional<Integer>) 10).intValue()), networkBuilder.edgeOrder.createMap(networkBuilder.expectedEdgeCount.mo17545or((Optional<Integer>) 20).intValue()));
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n8) {
        return checkedConnections(n8).adjacentNodes();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public final NetworkConnections<N, E> checkedConnections(N n8) {
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n8);
        if (networkConnections != null) {
            return networkConnections;
        }
        Preconditions.checkNotNull(n8);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", n8));
    }

    public final N checkedReferenceNode(E e9) {
        N n8 = this.edgeToReferenceNode.get(e9);
        if (n8 != null) {
            return n8;
        }
        Preconditions.checkNotNull(e9);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", e9));
    }

    public final boolean containsEdge(E e9) {
        return this.edgeToReferenceNode.containsKey(e9);
    }

    public final boolean containsNode(N n8) {
        return this.nodeConnections.containsKey(n8);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return this.edgeToReferenceNode.unmodifiableKeySet();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n8, N n9) {
        NetworkConnections<N, E> networkConnectionsCheckedConnections = checkedConnections(n8);
        if (!this.allowsSelfLoops && n8 == n9) {
            return ImmutableSet.m17615of();
        }
        Preconditions.checkArgument(containsNode(n9), "Node %s is not an element of this graph.", n9);
        return networkConnectionsCheckedConnections.edgesConnecting(n9);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n8) {
        return checkedConnections(n8).inEdges();
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n8) {
        return checkedConnections(n8).incidentEdges();
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e9) {
        N nCheckedReferenceNode = checkedReferenceNode(e9);
        return EndpointPair.m17672of(this, nCheckedReferenceNode, this.nodeConnections.get(nCheckedReferenceNode).adjacentNode(e9));
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n8) {
        return checkedConnections(n8).outEdges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ConfigurableNetwork<N, E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ConfigurableNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n8) {
        return checkedConnections(n8).predecessors();
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n8) {
        return checkedConnections(n8).successors();
    }

    public ConfigurableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder, Map<N, NetworkConnections<N, E>> map, Map<E, N> map2) {
        this.isDirected = networkBuilder.directed;
        this.allowsParallelEdges = networkBuilder.allowsParallelEdges;
        this.allowsSelfLoops = networkBuilder.allowsSelfLoops;
        this.nodeOrder = (ElementOrder<N>) networkBuilder.nodeOrder.cast();
        this.edgeOrder = (ElementOrder<E>) networkBuilder.edgeOrder.cast();
        this.nodeConnections = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        this.edgeToReferenceNode = new MapIteratorCache<>(map2);
    }
}
