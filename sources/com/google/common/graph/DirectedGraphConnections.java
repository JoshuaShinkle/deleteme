package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    private int predecessorCount;
    private int successorCount;

    public static final class PredAndSucc {
        private final Object successorValue;

        public PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, int i9, int i10) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.predecessorCount = Graphs.checkNonNegative(i9);
        this.successorCount = Graphs.checkNonNegative(i10);
        Preconditions.checkState(i9 <= map.size() && i10 <= map.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPredecessor(Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSuccessor(Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    /* renamed from: of */
    public static <N, V> DirectedGraphConnections<N, V> m17668of() {
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(Set<N> set, Map<N, V> map) {
        HashMap map2 = new HashMap();
        map2.putAll(map);
        for (N n8 : set) {
            Object objPut = map2.put(n8, PRED);
            if (objPut != null) {
                map2.put(n8, new PredAndSucc(objPut));
            }
        }
        return new DirectedGraphConnections<>(ImmutableMap.copyOf((Map) map2), set.size(), map.size());
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n8, V v8) {
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object objPut = map.put(n8, obj);
        if (objPut == null) {
            int i9 = this.predecessorCount + 1;
            this.predecessorCount = i9;
            Graphs.checkPositive(i9);
        } else if (objPut instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n8, objPut);
        } else if (objPut != obj) {
            this.adjacentNodeValues.put(n8, new PredAndSucc(objPut));
            int i10 = this.predecessorCount + 1;
            this.predecessorCount = i10;
            Graphs.checkPositive(i10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n8, V v8) {
        V v9 = (V) this.adjacentNodeValues.put(n8, v8);
        if (v9 == 0) {
            int i9 = this.successorCount + 1;
            this.successorCount = i9;
            Graphs.checkPositive(i9);
            return null;
        }
        if (v9 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n8, new PredAndSucc(v8));
            return (V) ((PredAndSucc) v9).successorValue;
        }
        if (v9 != PRED) {
            return v9;
        }
        this.adjacentNodeValues.put(n8, new PredAndSucc(v8));
        int i10 = this.successorCount + 1;
        this.successorCount = i10;
        Graphs.checkPositive(i10);
        return null;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n8) {
        Object obj = this.adjacentNodeValues.get(n8);
        if (obj == PRED) {
            this.adjacentNodeValues.remove(n8);
            int i9 = this.predecessorCount - 1;
            this.predecessorCount = i9;
            Graphs.checkNonNegative(i9);
            return;
        }
        if (obj instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n8, ((PredAndSucc) obj).successorValue);
            int i10 = this.predecessorCount - 1;
            this.predecessorCount = i10;
            Graphs.checkNonNegative(i10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        V v8 = (V) this.adjacentNodeValues.get(obj);
        if (v8 == 0 || v8 == (obj2 = PRED)) {
            return null;
        }
        if (v8 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            int i9 = this.successorCount - 1;
            this.successorCount = i9;
            Graphs.checkNonNegative(i9);
            return (V) ((PredAndSucc) v8).successorValue;
        }
        this.adjacentNodeValues.remove(obj);
        int i10 = this.successorCount - 1;
        this.successorCount = i10;
        Graphs.checkNonNegative(i10);
        return v8;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    public V value(N n8) {
        V v8 = (V) this.adjacentNodeValues.get(n8);
        if (v8 == PRED) {
            return null;
        }
        return v8 instanceof PredAndSucc ? (V) ((PredAndSucc) v8).successorValue : v8;
    }
}
