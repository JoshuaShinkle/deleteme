package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {

    public abstract class CollectionFutureRunningState extends AggregateFuture<V, C>.RunningState {
        private List<Optional<V>> values;

        public CollectionFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z8) {
            super(immutableCollection, z8, true);
            this.values = immutableCollection.isEmpty() ? ImmutableList.m17573of() : Lists.newArrayListWithCapacity(immutableCollection.size());
            for (int i9 = 0; i9 < immutableCollection.size(); i9++) {
                this.values.add(null);
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public final void collectOneValue(boolean z8, int i9, V v8) {
            List<Optional<V>> list = this.values;
            if (list != null) {
                list.set(i9, Optional.fromNullable(v8));
            } else {
                Preconditions.checkState(z8 || CollectionFuture.this.isCancelled(), "Future was done before all dependencies completed");
            }
        }

        public abstract C combine(List<Optional<V>> list);

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public final void handleAllCompleted() {
            List<Optional<V>> list = this.values;
            if (list != null) {
                CollectionFuture.this.set(combine(list));
            } else {
                Preconditions.checkState(CollectionFuture.this.isDone());
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.values = null;
        }
    }

    public static final class ListFuture<V> extends CollectionFuture<V, List<V>> {

        public final class ListFutureRunningState extends CollectionFuture<V, List<V>>.CollectionFutureRunningState {
            public ListFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z8) {
                super(immutableCollection, z8);
            }

            @Override // com.google.common.util.concurrent.CollectionFuture.CollectionFutureRunningState
            public List<V> combine(List<Optional<V>> list) {
                ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
                Iterator<Optional<V>> it = list.iterator();
                while (it.hasNext()) {
                    Optional<V> next = it.next();
                    arrayListNewArrayListWithCapacity.add(next != null ? next.orNull() : null);
                }
                return Collections.unmodifiableList(arrayListNewArrayListWithCapacity);
            }
        }

        public ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z8) {
            init(new ListFutureRunningState(immutableCollection, z8));
        }
    }
}
