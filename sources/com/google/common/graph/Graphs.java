package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Beta
/* loaded from: classes2.dex */
public final class Graphs {

    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    public static class TransposedGraph<N> extends ForwardingGraph<N> {
        private final Graph<N> graph;

        public TransposedGraph(Graph<N> graph) {
            this.graph = graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n8, N n9) {
            return delegate().hasEdgeConnecting(n9, n8);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n8) {
            return delegate().outDegree(n8);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n8) {
            return delegate().inDegree(n8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedGraph<N>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedGraph<N>) obj);
        }

        @Override // com.google.common.graph.ForwardingGraph
        public Graph<N> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n8) {
            return delegate().successors((Graph<N>) n8);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n8) {
            return delegate().predecessors((Graph<N>) n8);
        }
    }

    @GwtIncompatible
    public static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        private final Network<N, E> network;

        public TransposedNetwork(Network<N, E> network) {
            this.network = network;
        }

        @Override // com.google.common.graph.ForwardingNetwork
        public Network<N, E> delegate() {
            return this.network;
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N n8, N n9) {
            return delegate().edgeConnectingOrNull(n9, n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N n8, N n9) {
            return delegate().edgesConnecting(n9, n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N n8, N n9) {
            return delegate().hasEdgeConnecting(n9, n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N n8) {
            return delegate().outDegree(n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N n8) {
            return delegate().outEdges(n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E e9) {
            EndpointPair<N> endpointPairIncidentNodes = delegate().incidentNodes(e9);
            return EndpointPair.m17672of((Network<?, ?>) this.network, (Object) endpointPairIncidentNodes.nodeV(), (Object) endpointPairIncidentNodes.nodeU());
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N n8) {
            return delegate().inDegree(n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N n8) {
            return delegate().inEdges(n8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedNetwork<N, E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedNetwork<N, E>) obj);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n8) {
            return delegate().successors((Network<N, E>) n8);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n8) {
            return delegate().predecessors((Network<N, E>) n8);
        }
    }

    public static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        public TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        public V edgeValueOrDefault(N n8, N n9, V v8) {
            return delegate().edgeValueOrDefault(n9, n8, v8);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n8, N n9) {
            return delegate().hasEdgeConnecting(n9, n8);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n8) {
            return delegate().outDegree(n8);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n8) {
            return delegate().inDegree(n8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedValueGraph<N, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedValueGraph<N, V>) obj);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n8) {
            return delegate().successors((ValueGraph<N, V>) n8);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n8) {
            return delegate().predecessors((ValueGraph<N, V>) n8);
        }
    }

    private Graphs() {
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(int i9) {
        Preconditions.checkArgument(i9 >= 0, "Not true that %s is non-negative.", i9);
        return i9;
    }

    @CanIgnoreReturnValue
    public static int checkPositive(int i9) {
        Preconditions.checkArgument(i9 > 0, "Not true that %s is positive.", i9);
        return i9;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N> mutableGraph = (MutableGraph<N>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            mutableGraph.addNode(it.next());
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            mutableGraph.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return mutableGraph;
    }

    @Deprecated
    public static boolean equivalent(Graph<?> graph, Graph<?> graph2) {
        return Objects.equal(graph, graph2);
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            if (subgraphHasCycle(graph, mapNewHashMapWithExpectedSize, it.next(), null)) {
                return true;
            }
        }
        return false;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        ConfigurableMutableGraph configurableMutableGraph = iterable instanceof Collection ? (MutableGraph<N>) GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build() : (MutableGraph<N>) GraphBuilder.from(graph).build();
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            configurableMutableGraph.addNode(it.next());
        }
        for (N n8 : configurableMutableGraph.nodes()) {
            for (N n9 : graph.successors((Graph<N>) n8)) {
                if (configurableMutableGraph.nodes().contains(n9)) {
                    configurableMutableGraph.putEdge(n8, n9);
                }
            }
        }
        return configurableMutableGraph;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N> Set<N> reachableNodes(Graph<N> graph, N n8) {
        Preconditions.checkArgument(graph.nodes().contains(n8), "Node %s is not an element of this graph.", n8);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        linkedHashSet.add(n8);
        arrayDeque.add(n8);
        while (!arrayDeque.isEmpty()) {
            for (Object obj : graph.successors((Graph<N>) arrayDeque.remove())) {
                if (linkedHashSet.add(obj)) {
                    arrayDeque.add(obj);
                }
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n8, N n9) {
        NodeVisitState nodeVisitState = map.get(n8);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        NodeVisitState nodeVisitState2 = NodeVisitState.PENDING;
        if (nodeVisitState == nodeVisitState2) {
            return true;
        }
        map.put(n8, nodeVisitState2);
        for (N n10 : graph.successors((Graph<N>) n8)) {
            if (canTraverseWithoutReusingEdge(graph, n10, n9) && subgraphHasCycle(graph, map, n10, n8)) {
                return true;
            }
        }
        map.put(n8, NodeVisitState.COMPLETE);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        ConfigurableMutableGraph configurableMutableGraphBuild = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N n8 : graph.nodes()) {
                Iterator it = reachableNodes(graph, n8).iterator();
                while (it.hasNext()) {
                    configurableMutableGraphBuild.putEdge(n8, it.next());
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n9 : graph.nodes()) {
                if (!hashSet.contains(n9)) {
                    Set setReachableNodes = reachableNodes(graph, n9);
                    hashSet.addAll(setReachableNodes);
                    int i9 = 1;
                    for (Object obj : setReachableNodes) {
                        int i10 = i9 + 1;
                        Iterator it2 = Iterables.limit(setReachableNodes, i9).iterator();
                        while (it2.hasNext()) {
                            configurableMutableGraphBuild.putEdge(obj, it2.next());
                        }
                        i9 = i10;
                    }
                }
            }
        }
        return configurableMutableGraphBuild;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        return !graph.isDirected() ? graph : graph instanceof TransposedGraph ? ((TransposedGraph) graph).graph : new TransposedGraph(graph);
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(long j9) {
        Preconditions.checkArgument(j9 >= 0, "Not true that %s is non-negative.", j9);
        return j9;
    }

    @CanIgnoreReturnValue
    public static long checkPositive(long j9) {
        Preconditions.checkArgument(j9 > 0, "Not true that %s is positive.", j9);
        return j9;
    }

    @Deprecated
    public static boolean equivalent(ValueGraph<?, ?> valueGraph, ValueGraph<?, ?> valueGraph2) {
        return Objects.equal(valueGraph, valueGraph2);
    }

    @Deprecated
    public static boolean equivalent(Network<?, ?> network, Network<?, ?> network2) {
        return Objects.equal(network, network2);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (!valueGraph.isDirected()) {
            return valueGraph;
        }
        if (valueGraph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) valueGraph).graph;
        }
        return new TransposedValueGraph(valueGraph);
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N, V> mutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        Iterator<N> it = valueGraph.nodes().iterator();
        while (it.hasNext()) {
            mutableValueGraph.addNode(it.next());
        }
        for (EndpointPair<N> endpointPair : valueGraph.edges()) {
            mutableValueGraph.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return mutableValueGraph;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    @GwtIncompatible
    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        ConfigurableMutableValueGraph configurableMutableValueGraph;
        if (iterable instanceof Collection) {
            configurableMutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            configurableMutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).build();
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            configurableMutableValueGraph.addNode(it.next());
        }
        for (N n8 : configurableMutableValueGraph.nodes()) {
            for (N n9 : valueGraph.successors((ValueGraph<N, V>) n8)) {
                if (configurableMutableValueGraph.nodes().contains(n9)) {
                    configurableMutableValueGraph.putEdgeValue(n8, n9, valueGraph.edgeValueOrDefault(n8, n9, null));
                }
            }
        }
        return configurableMutableValueGraph;
    }

    @GwtIncompatible
    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N, E> mutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        Iterator<N> it = network.nodes().iterator();
        while (it.hasNext()) {
            mutableNetwork.addNode(it.next());
        }
        for (E e9 : network.edges()) {
            EndpointPair<N> endpointPairIncidentNodes = network.incidentNodes(e9);
            mutableNetwork.addEdge(endpointPairIncidentNodes.nodeU(), endpointPairIncidentNodes.nodeV(), e9);
        }
        return mutableNetwork;
    }

    @GwtIncompatible
    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        ConfigurableMutableNetwork configurableMutableNetwork;
        if (iterable instanceof Collection) {
            configurableMutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            configurableMutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).build();
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            configurableMutableNetwork.addNode(it.next());
        }
        for (E e9 : configurableMutableNetwork.nodes()) {
            for (E e10 : network.outEdges(e9)) {
                N nAdjacentNode = network.incidentNodes(e10).adjacentNode(e9);
                if (configurableMutableNetwork.nodes().contains(nAdjacentNode)) {
                    configurableMutableNetwork.addEdge(e9, nAdjacentNode, e10);
                }
            }
        }
        return configurableMutableNetwork;
    }
}
