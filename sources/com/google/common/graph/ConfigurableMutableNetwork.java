package com.google.common.graph;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;

@GwtIncompatible
/* loaded from: classes2.dex */
final class ConfigurableMutableNetwork<N, E> extends ConfigurableNetwork<N, E> implements MutableNetwork<N, E> {
    public ConfigurableMutableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        super(networkBuilder);
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N n8) {
        NetworkConnections<N, E> networkConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(n8, networkConnectionsNewConnections) == null);
        return networkConnectionsNewConnections;
    }

    private NetworkConnections<N, E> newConnections() {
        return isDirected() ? allowsParallelEdges() ? DirectedMultiNetworkConnections.m17669of() : DirectedNetworkConnections.m17670of() : allowsParallelEdges() ? UndirectedMultiNetworkConnections.m17675of() : UndirectedNetworkConnections.m17676of();
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(N n8, N n9, E e9) {
        Preconditions.checkNotNull(n8, "nodeU");
        Preconditions.checkNotNull(n9, "nodeV");
        Preconditions.checkNotNull(e9, "edge");
        if (containsEdge(e9)) {
            EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e9);
            EndpointPair endpointPairM17672of = EndpointPair.m17672of(this, n8, n9);
            Preconditions.checkArgument(endpointPairIncidentNodes.equals(endpointPairM17672of), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", e9, endpointPairIncidentNodes, endpointPairM17672of);
            return false;
        }
        NetworkConnections<N, E> networkConnectionsAddNodeInternal = this.nodeConnections.get(n8);
        if (!allowsParallelEdges()) {
            Preconditions.checkArgument(networkConnectionsAddNodeInternal == null || !networkConnectionsAddNodeInternal.successors().contains(n9), "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", n8, n9);
        }
        boolean zEquals = n8.equals(n9);
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!zEquals, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n8);
        }
        if (networkConnectionsAddNodeInternal == null) {
            networkConnectionsAddNodeInternal = addNodeInternal(n8);
        }
        networkConnectionsAddNodeInternal.addOutEdge(e9, n9);
        NetworkConnections<N, E> networkConnectionsAddNodeInternal2 = this.nodeConnections.get(n9);
        if (networkConnectionsAddNodeInternal2 == null) {
            networkConnectionsAddNodeInternal2 = addNodeInternal(n9);
        }
        networkConnectionsAddNodeInternal2.addInEdge(e9, n8, zEquals);
        this.edgeToReferenceNode.put(e9, n8);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addNode(N n8) {
        Preconditions.checkNotNull(n8, "node");
        if (containsNode(n8)) {
            return false;
        }
        addNodeInternal(n8);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeEdge(E e9) {
        Preconditions.checkNotNull(e9, "edge");
        N n8 = this.edgeToReferenceNode.get(e9);
        boolean z8 = false;
        if (n8 == null) {
            return false;
        }
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n8);
        N nAdjacentNode = networkConnections.adjacentNode(e9);
        NetworkConnections<N, E> networkConnections2 = this.nodeConnections.get(nAdjacentNode);
        networkConnections.removeOutEdge(e9);
        if (allowsSelfLoops() && n8.equals(nAdjacentNode)) {
            z8 = true;
        }
        networkConnections2.removeInEdge(e9, z8);
        this.edgeToReferenceNode.remove(e9);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeNode(N n8) {
        Preconditions.checkNotNull(n8, "node");
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n8);
        if (networkConnections == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf((Collection) networkConnections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.nodeConnections.remove(n8);
        return true;
    }
}
