package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class AggregateFuture<InputT, OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    private AggregateFuture<InputT, OutputT>.RunningState runningState;

    public abstract class RunningState extends AggregateFutureState implements Runnable {
        private final boolean allMustSucceed;
        private final boolean collectsValues;
        private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

        public RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z8, boolean z9) {
            super(immutableCollection.size());
            this.futures = (ImmutableCollection) Preconditions.checkNotNull(immutableCollection);
            this.allMustSucceed = z8;
            this.collectsValues = z9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void decrementCountAndMaybeComplete() {
            int iDecrementRemainingAndGet = decrementRemainingAndGet();
            Preconditions.checkState(iDecrementRemainingAndGet >= 0, "Less than 0 remaining futures");
            if (iDecrementRemainingAndGet == 0) {
                processCompleted();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
        /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void handleException(Throwable th) {
            boolean exception;
            boolean zAddCausalChain;
            boolean z8;
            Preconditions.checkNotNull(th);
            if (this.allMustSucceed) {
                exception = AggregateFuture.this.setException(th);
                if (!exception) {
                    zAddCausalChain = AggregateFuture.addCausalChain(getOrInitSeenExceptions(), th);
                    z8 = th instanceof Error;
                    if (!((!exception) & this.allMustSucceed & zAddCausalChain) && !z8) {
                        AggregateFuture.logger.log(Level.SEVERE, z8 ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
                        return;
                    }
                    return;
                }
                releaseResourcesAfterFailure();
            } else {
                exception = false;
            }
            zAddCausalChain = true;
            z8 = th instanceof Error;
            if (!(((!exception) & this.allMustSucceed & zAddCausalChain) | z8)) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public void handleOneInputDone(int i9, Future<? extends InputT> future) {
            Preconditions.checkState(this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                    } else {
                        Object done = Futures.getDone(future);
                        if (this.collectsValues) {
                            collectOneValue(this.allMustSucceed, i9, done);
                        }
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    collectOneValue(this.allMustSucceed, i9, Futures.getDone(future));
                }
            } catch (ExecutionException e9) {
                handleException(e9.getCause());
            } catch (Throwable th) {
                handleException(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void init() {
            if (this.futures.isEmpty()) {
                handleAllCompleted();
                return;
            }
            if (!this.allMustSucceed) {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    it.next().addListener(this, MoreExecutors.directExecutor());
                }
                return;
            }
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
            final int i9 = 0;
            while (it2.hasNext()) {
                final ListenableFuture<? extends InputT> next = it2.next();
                next.addListener(new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture.RunningState.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            RunningState.this.handleOneInputDone(i9, next);
                        } finally {
                            RunningState.this.decrementCountAndMaybeComplete();
                        }
                    }
                }, MoreExecutors.directExecutor());
                i9++;
            }
        }

        private void processCompleted() {
            if (this.collectsValues & (!this.allMustSucceed)) {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                int i9 = 0;
                while (it.hasNext()) {
                    handleOneInputDone(i9, it.next());
                    i9++;
                }
            }
            handleAllCompleted();
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState
        public final void addInitialException(Set<Throwable> set) {
            if (AggregateFuture.this.isCancelled()) {
                return;
            }
            AggregateFuture.addCausalChain(set, AggregateFuture.this.trustedGetException());
        }

        public abstract void collectOneValue(boolean z8, int i9, InputT inputt);

        public abstract void handleAllCompleted();

        public void interruptTask() {
        }

        public void releaseResourcesAfterFailure() {
            this.futures = null;
        }

        @Override // java.lang.Runnable
        public final void run() {
            decrementCountAndMaybeComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean addCausalChain(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        super.afterDone();
        AggregateFuture<InputT, OutputT>.RunningState runningState = this.runningState;
        if (runningState != null) {
            this.runningState = null;
            ImmutableCollection immutableCollection = ((RunningState) runningState).futures;
            boolean zWasInterrupted = wasInterrupted();
            if (wasInterrupted()) {
                runningState.interruptTask();
            }
            if (isCancelled() && (immutableCollection != null)) {
                UnmodifiableIterator it = immutableCollection.iterator();
                while (it.hasNext()) {
                    ((ListenableFuture) it.next()).cancel(zWasInterrupted);
                }
            }
        }
    }

    public final void init(AggregateFuture<InputT, OutputT>.RunningState runningState) {
        this.runningState = runningState;
        runningState.init();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ImmutableCollection immutableCollection;
        AggregateFuture<InputT, OutputT>.RunningState runningState = this.runningState;
        if (runningState == null || (immutableCollection = ((RunningState) runningState).futures) == null) {
            return null;
        }
        return "futures=[" + immutableCollection + "]";
    }
}
