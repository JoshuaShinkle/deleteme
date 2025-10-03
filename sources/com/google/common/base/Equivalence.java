package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.ForOverride;
import java.io.Serializable;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class Equivalence<T> {

    public static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object obj) {
            return obj.hashCode();
        }
    }

    public static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<T> equivalence;
        private final T target;

        public EquivalentToPredicate(Equivalence<T> equivalence, T t8) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
            this.target = t8;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T t8) {
            return this.equivalence.equivalent(t8, this.target);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EquivalentToPredicate)) {
                return false;
            }
            EquivalentToPredicate equivalentToPredicate = (EquivalentToPredicate) obj;
            return this.equivalence.equals(equivalentToPredicate.equivalence) && Objects.equal(this.target, equivalentToPredicate.target);
        }

        public int hashCode() {
            return Objects.hashCode(this.equivalence, this.target);
        }

        public String toString() {
            return this.equivalence + ".equivalentTo(" + this.target + ")";
        }
    }

    public static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object obj, Object obj2) {
            return false;
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object obj) {
            return System.identityHashCode(obj);
        }
    }

    public static final class Wrapper<T> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<? super T> equivalence;
        private final T reference;

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Wrapper)) {
                return false;
            }
            Wrapper wrapper = (Wrapper) obj;
            if (this.equivalence.equals(wrapper.equivalence)) {
                return this.equivalence.equivalent(this.reference, wrapper.reference);
            }
            return false;
        }

        public T get() {
            return this.reference;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            return this.equivalence + ".wrap(" + this.reference + ")";
        }

        private Wrapper(Equivalence<? super T> equivalence, T t8) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
            this.reference = t8;
        }
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    @ForOverride
    public abstract boolean doEquivalent(T t8, T t9);

    @ForOverride
    public abstract int doHash(T t8);

    public final boolean equivalent(T t8, T t9) {
        if (t8 == t9) {
            return true;
        }
        if (t8 == null || t9 == null) {
            return false;
        }
        return doEquivalent(t8, t9);
    }

    public final Predicate<T> equivalentTo(T t8) {
        return new EquivalentToPredicate(this, t8);
    }

    public final int hash(T t8) {
        if (t8 == null) {
            return 0;
        }
        return doHash(t8);
    }

    public final <F> Equivalence<F> onResultOf(Function<F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public final <S extends T> Wrapper<S> wrap(S s8) {
        return new Wrapper<>(s8);
    }
}
