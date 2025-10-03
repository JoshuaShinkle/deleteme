package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> inEdgeMap;
    protected final Map<E, N> outEdgeMap;
    private int selfLoopCount;

    public AbstractDirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i9) {
        this.inEdgeMap = (Map) Preconditions.checkNotNull(map);
        this.outEdgeMap = (Map) Preconditions.checkNotNull(map2);
        this.selfLoopCount = Graphs.checkNonNegative(i9);
        Preconditions.checkState(i9 <= map.size() && i9 <= map2.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e9, N n8, boolean z8) {
        if (z8) {
            int i9 = this.selfLoopCount + 1;
            this.selfLoopCount = i9;
            Graphs.checkPositive(i9);
        }
        Preconditions.checkState(this.inEdgeMap.put(e9, n8) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e9, N n8) {
        Preconditions.checkState(this.outEdgeMap.put(e9, n8) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e9) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.get(e9));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() { // from class: com.google.common.graph.AbstractDirectedNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return Iterators.unmodifiableIterator((AbstractDirectedNetworkConnections.this.selfLoopCount == 0 ? Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()) : Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet())).iterator());
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e9, boolean z8) {
        if (z8) {
            int i9 = this.selfLoopCount - 1;
            this.selfLoopCount = i9;
            Graphs.checkNonNegative(i9);
        }
        return (N) Preconditions.checkNotNull(this.inEdgeMap.remove(e9));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e9) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.remove(e9));
    }
}
