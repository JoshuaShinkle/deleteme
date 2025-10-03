package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
    public UndirectedNetworkConnections(Map<E, N> map) {
        super(map);
    }

    /* renamed from: of */
    public static <N, E> UndirectedNetworkConnections<N, E> m17676of() {
        return new UndirectedNetworkConnections<>(HashBiMap.create(2));
    }

    public static <N, E> UndirectedNetworkConnections<N, E> ofImmutable(Map<E, N> map) {
        return new UndirectedNetworkConnections<>(ImmutableBiMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(((BiMap) this.incidentEdgeMap).values());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(N n8) {
        return new EdgesConnecting(((BiMap) this.incidentEdgeMap).inverse(), n8);
    }
}
