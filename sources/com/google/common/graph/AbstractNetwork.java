package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class AbstractNetwork<N, E> implements Network<N, E> {

    /* renamed from: com.google.common.graph.AbstractNetwork$1 */
    public class C38381 extends AbstractGraph<N> {
        public C38381() {
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> adjacentNodes(N n8) {
            return AbstractNetwork.this.adjacentNodes(n8);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> edges() {
            return AbstractNetwork.this.allowsParallelEdges() ? super.edges() : new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.1.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean contains(Object obj) {
                    if (!(obj instanceof EndpointPair)) {
                        return false;
                    }
                    EndpointPair endpointPair = (EndpointPair) obj;
                    return C38381.this.isDirected() == endpointPair.isOrdered() && C38381.this.nodes().contains(endpointPair.nodeU()) && C38381.this.successors((C38381) endpointPair.nodeU()).contains(endpointPair.nodeV());
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<EndpointPair<N>> iterator() {
                    return Iterators.transform(AbstractNetwork.this.edges().iterator(), new Function<E, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.1.1.1
                        @Override // com.google.common.base.Function
                        public /* bridge */ /* synthetic */ Object apply(Object obj) {
                            return apply((C68641) obj);
                        }

                        @Override // com.google.common.base.Function
                        public EndpointPair<N> apply(E e9) {
                            return AbstractNetwork.this.incidentNodes(e9);
                        }
                    });
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return AbstractNetwork.this.edges().size();
                }
            };
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractNetwork.this.nodes();
        }

        @Override // com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((C38381) obj);
        }

        @Override // com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((C38381) obj);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n8) {
            return AbstractNetwork.this.predecessors((AbstractNetwork) n8);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n8) {
            return AbstractNetwork.this.successors((AbstractNetwork) n8);
        }
    }

    private Predicate<E> connectedPredicate(final N n8, final N n9) {
        return new Predicate<E>() { // from class: com.google.common.graph.AbstractNetwork.2
            @Override // com.google.common.base.Predicate
            public boolean apply(E e9) {
                return AbstractNetwork.this.incidentNodes(e9).adjacentNode(n8).equals(n9);
            }
        };
    }

    private static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(final Network<N, E> network) {
        return Maps.asMap(network.edges(), new Function<E, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.3
            @Override // com.google.common.base.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C38403) obj);
            }

            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(E e9) {
                return network.incidentNodes(e9);
            }
        });
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E e9) {
        EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e9);
        return Sets.difference(Sets.union(incidentEdges(endpointPairIncidentNodes.nodeU()), incidentEdges(endpointPairIncidentNodes.nodeV())), ImmutableSet.m17616of((Object) e9));
    }

    @Override // com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new C38381();
    }

    @Override // com.google.common.graph.Network
    public int degree(N n8) {
        return isDirected() ? IntMath.saturatedAdd(inEdges(n8).size(), outEdges(n8).size()) : IntMath.saturatedAdd(incidentEdges(n8).size(), edgesConnecting(n8, n8).size());
    }

    @Override // com.google.common.graph.Network
    public E edgeConnectingOrNull(N n8, N n9) {
        Set<E> setEdgesConnecting = edgesConnecting(n8, n9);
        int size = setEdgesConnecting.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return setEdgesConnecting.iterator().next();
        }
        throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", n8, n9));
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N n8, N n9) {
        Set<E> setOutEdges = outEdges(n8);
        Set<E> setInEdges = inEdges(n9);
        return setOutEdges.size() <= setInEdges.size() ? Collections.unmodifiableSet(Sets.filter(setOutEdges, connectedPredicate(n8, n9))) : Collections.unmodifiableSet(Sets.filter(setInEdges, connectedPredicate(n9, n8)));
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        return isDirected() == network.isDirected() && nodes().equals(network.nodes()) && edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(network));
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n8, N n9) {
        return !edgesConnecting(n8, n9).isEmpty();
    }

    @Override // com.google.common.graph.Network
    public final int hashCode() {
        return edgeIncidentNodesMap(this).hashCode();
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N n8) {
        return isDirected() ? inEdges(n8).size() : degree(n8);
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N n8) {
        return isDirected() ? outEdges(n8).size() : degree(n8);
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsParallelEdges: " + allowsParallelEdges() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeIncidentNodesMap(this);
    }
}
