package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
final class FunctionalEquivalence<F, T> extends Equivalence<F> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Function<F, ? extends T> function;
    private final Equivalence<T> resultEquivalence;

    public FunctionalEquivalence(Function<F, ? extends T> function, Equivalence<T> equivalence) {
        this.function = (Function) Preconditions.checkNotNull(function);
        this.resultEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
    }

    @Override // com.google.common.base.Equivalence
    public boolean doEquivalent(F f9, F f10) {
        return this.resultEquivalence.equivalent(this.function.apply(f9), this.function.apply(f10));
    }

    @Override // com.google.common.base.Equivalence
    public int doHash(F f9) {
        return this.resultEquivalence.hash(this.function.apply(f9));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FunctionalEquivalence)) {
            return false;
        }
        FunctionalEquivalence functionalEquivalence = (FunctionalEquivalence) obj;
        return this.function.equals(functionalEquivalence.function) && this.resultEquivalence.equals(functionalEquivalence.resultEquivalence);
    }

    public int hashCode() {
        return Objects.hashCode(this.function, this.resultEquivalence);
    }

    public String toString() {
        return this.resultEquivalence + ".onResultOf(" + this.function + ")";
    }
}
