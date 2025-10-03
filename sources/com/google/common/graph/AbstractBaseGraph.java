package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Set;

/* loaded from: classes2.dex */
abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
    @Override // com.google.common.graph.BaseGraph
    public int degree(N n8) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((AbstractBaseGraph<N>) n8).size(), successors((AbstractBaseGraph<N>) n8).size());
        }
        Set<N> setAdjacentNodes = adjacentNodes(n8);
        return IntMath.saturatedAdd(setAdjacentNodes.size(), (allowsSelfLoops() && setAdjacentNodes.contains(n8)) ? 1 : 0);
    }

    public long edgeCount() {
        long jDegree = 0;
        while (nodes().iterator().hasNext()) {
            jDegree += degree(r0.next());
        }
        Preconditions.checkState((1 & jDegree) == 0);
        return jDegree >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair endpointPair = (EndpointPair) obj;
                return AbstractBaseGraph.this.isDirected() == endpointPair.isOrdered() && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors((AbstractBaseGraph) endpointPair.nodeU()).contains(endpointPair.nodeV());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return EndpointPairIterator.m17673of(AbstractBaseGraph.this);
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n8, N n9) {
        Preconditions.checkNotNull(n8);
        Preconditions.checkNotNull(n9);
        return nodes().contains(n8) && successors((AbstractBaseGraph<N>) n8).contains(n9);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N n8) {
        return isDirected() ? predecessors((AbstractBaseGraph<N>) n8).size() : degree(n8);
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N n8) {
        return isDirected() ? successors((AbstractBaseGraph<N>) n8).size() : degree(n8);
    }
}
