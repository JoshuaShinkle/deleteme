package com.google.common.graph;

import com.google.common.annotations.GwtIncompatible;
import java.util.Set;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class ForwardingNetwork<N, E> extends AbstractNetwork<N, E> {
    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> adjacentEdges(E e9) {
        return delegate().adjacentEdges(e9);
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n8) {
        return delegate().adjacentNodes(n8);
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return delegate().allowsParallelEdges();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int degree(N n8) {
        return delegate().degree(n8);
    }

    public abstract Network<N, E> delegate();

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public E edgeConnectingOrNull(N n8, N n9) {
        return delegate().edgeConnectingOrNull(n8, n9);
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return delegate().edgeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return delegate().edges();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n8, N n9) {
        return delegate().edgesConnecting(n8, n9);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n8, N n9) {
        return delegate().hasEdgeConnecting(n8, n9);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int inDegree(N n8) {
        return delegate().inDegree(n8);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n8) {
        return delegate().inEdges(n8);
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n8) {
        return delegate().incidentEdges(n8);
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e9) {
        return delegate().incidentNodes(e9);
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return delegate().nodes();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int outDegree(N n8) {
        return delegate().outDegree(n8);
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n8) {
        return delegate().outEdges(n8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.PredecessorsFunction
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors((ForwardingNetwork<N, E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction
    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors((ForwardingNetwork<N, E>) obj);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n8) {
        return delegate().predecessors((Network<N, E>) n8);
    }

    @Override // com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n8) {
        return delegate().successors((Network<N, E>) n8);
    }
}
