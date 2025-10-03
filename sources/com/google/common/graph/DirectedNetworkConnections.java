package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class DirectedNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {
    public DirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i9) {
        super(map, map2, i9);
    }

    /* renamed from: of */
    public static <N, E> DirectedNetworkConnections<N, E> m17670of() {
        return new DirectedNetworkConnections<>(HashBiMap.create(2), HashBiMap.create(2), 0);
    }

    public static <N, E> DirectedNetworkConnections<N, E> ofImmutable(Map<E, N> map, Map<E, N> map2, int i9) {
        return new DirectedNetworkConnections<>(ImmutableBiMap.copyOf((Map) map), ImmutableBiMap.copyOf((Map) map2), i9);
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(N n8) {
        return new EdgesConnecting(((BiMap) this.outEdgeMap).inverse(), n8);
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return Collections.unmodifiableSet(((BiMap) this.inEdgeMap).values());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return Collections.unmodifiableSet(((BiMap) this.outEdgeMap).values());
    }
}
