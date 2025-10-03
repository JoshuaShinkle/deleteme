package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Beta
/* loaded from: classes2.dex */
public final class ValueGraphBuilder<N, V> extends AbstractGraphBuilder<N> {
    private ValueGraphBuilder(boolean z8) {
        super(z8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N, V1 extends V> ValueGraphBuilder<N1, V1> cast() {
        return this;
    }

    public static ValueGraphBuilder<Object, Object> directed() {
        return new ValueGraphBuilder<>(true);
    }

    public static <N, V> ValueGraphBuilder<N, V> from(ValueGraph<N, V> valueGraph) {
        return (ValueGraphBuilder<N, V>) new ValueGraphBuilder(valueGraph.isDirected()).allowsSelfLoops(valueGraph.allowsSelfLoops()).nodeOrder(valueGraph.nodeOrder());
    }

    public static ValueGraphBuilder<Object, Object> undirected() {
        return new ValueGraphBuilder<>(false);
    }

    public ValueGraphBuilder<N, V> allowsSelfLoops(boolean z8) {
        this.allowsSelfLoops = z8;
        return this;
    }

    public <N1 extends N, V1 extends V> MutableValueGraph<N1, V1> build() {
        return new ConfigurableMutableValueGraph(this);
    }

    public ValueGraphBuilder<N, V> expectedNodeCount(int i9) {
        this.expectedNodeCount = Optional.m17551of(Integer.valueOf(Graphs.checkNonNegative(i9)));
        return this;
    }

    public <N1 extends N> ValueGraphBuilder<N1, V> nodeOrder(ElementOrder<N1> elementOrder) {
        ValueGraphBuilder<N1, V> valueGraphBuilder = (ValueGraphBuilder<N1, V>) cast();
        valueGraphBuilder.nodeOrder = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return valueGraphBuilder;
    }
}
