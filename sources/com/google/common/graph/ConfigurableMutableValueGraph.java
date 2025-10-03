package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class ConfigurableMutableValueGraph<N, V> extends ConfigurableValueGraph<N, V> implements MutableValueGraph<N, V> {
    public ConfigurableMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
    }

    @CanIgnoreReturnValue
    private GraphConnections<N, V> addNodeInternal(N n8) {
        GraphConnections<N, V> graphConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(n8, graphConnectionsNewConnections) == null);
        return graphConnectionsNewConnections;
    }

    private GraphConnections<N, V> newConnections() {
        return isDirected() ? DirectedGraphConnections.m17668of() : UndirectedGraphConnections.m17674of();
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean addNode(N n8) {
        Preconditions.checkNotNull(n8, "node");
        if (containsNode(n8)) {
            return false;
        }
        addNodeInternal(n8);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V putEdgeValue(N n8, N n9, V v8) {
        Preconditions.checkNotNull(n8, "nodeU");
        Preconditions.checkNotNull(n9, "nodeV");
        Preconditions.checkNotNull(v8, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n8.equals(n9), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n8);
        }
        GraphConnections<N, V> graphConnectionsAddNodeInternal = this.nodeConnections.get(n8);
        if (graphConnectionsAddNodeInternal == null) {
            graphConnectionsAddNodeInternal = addNodeInternal(n8);
        }
        V vAddSuccessor = graphConnectionsAddNodeInternal.addSuccessor(n9, v8);
        GraphConnections<N, V> graphConnectionsAddNodeInternal2 = this.nodeConnections.get(n9);
        if (graphConnectionsAddNodeInternal2 == null) {
            graphConnectionsAddNodeInternal2 = addNodeInternal(n9);
        }
        graphConnectionsAddNodeInternal2.addPredecessor(n8, v8);
        if (vAddSuccessor == null) {
            long j9 = this.edgeCount + 1;
            this.edgeCount = j9;
            Graphs.checkPositive(j9);
        }
        return vAddSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public V removeEdge(N n8, N n9) {
        Preconditions.checkNotNull(n8, "nodeU");
        Preconditions.checkNotNull(n9, "nodeV");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n8);
        GraphConnections<N, V> graphConnections2 = this.nodeConnections.get(n9);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V vRemoveSuccessor = graphConnections.removeSuccessor(n9);
        if (vRemoveSuccessor != null) {
            graphConnections2.removePredecessor(n8);
            long j9 = this.edgeCount - 1;
            this.edgeCount = j9;
            Graphs.checkNonNegative(j9);
        }
        return vRemoveSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CanIgnoreReturnValue
    public boolean removeNode(N n8) {
        Preconditions.checkNotNull(n8, "node");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n8);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(n8) != null) {
            graphConnections.removePredecessor(n8);
            this.edgeCount--;
        }
        Iterator<N> it = graphConnections.successors().iterator();
        while (it.hasNext()) {
            this.nodeConnections.getWithoutCaching(it.next()).removePredecessor(n8);
            this.edgeCount--;
        }
        if (isDirected()) {
            Iterator<N> it2 = graphConnections.predecessors().iterator();
            while (it2.hasNext()) {
                Preconditions.checkState(this.nodeConnections.getWithoutCaching(it2.next()).removeSuccessor(n8) != null);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(n8);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }
}
