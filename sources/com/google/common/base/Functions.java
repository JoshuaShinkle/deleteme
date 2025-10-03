package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Functions {

    public static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private static final long serialVersionUID = 0;
        private final E value;

        public ConstantFunction(E e9) {
            this.value = e9;
        }

        @Override // com.google.common.base.Function
        public E apply(Object obj) {
            return this.value;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof ConstantFunction) {
                return Objects.equal(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        public int hashCode() {
            E e9 = this.value;
            if (e9 == null) {
                return 0;
            }
            return e9.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    public static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final V defaultValue;
        final Map<K, ? extends V> map;

        public ForMapWithDefault(Map<K, ? extends V> map, V v8) {
            this.map = (Map) Preconditions.checkNotNull(map);
            this.defaultValue = v8;
        }

        @Override // com.google.common.base.Function
        public V apply(K k9) {
            V v8 = this.map.get(k9);
            return (v8 != null || this.map.containsKey(k9)) ? v8 : this.defaultValue;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            return this.map.equals(forMapWithDefault.map) && Objects.equal(this.defaultValue, forMapWithDefault.defaultValue);
        }

        public int hashCode() {
            return Objects.hashCode(this.map, this.defaultValue);
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ", defaultValue=" + this.defaultValue + ")";
        }
    }

    public static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: f */
        private final Function<A, ? extends B> f15394f;

        /* renamed from: g */
        private final Function<B, C> f15395g;

        public FunctionComposition(Function<B, C> function, Function<A, ? extends B> function2) {
            this.f15395g = (Function) Preconditions.checkNotNull(function);
            this.f15394f = (Function) Preconditions.checkNotNull(function2);
        }

        @Override // com.google.common.base.Function
        public C apply(A a9) {
            return (C) this.f15395g.apply(this.f15394f.apply(a9));
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            return this.f15394f.equals(functionComposition.f15394f) && this.f15395g.equals(functionComposition.f15395g);
        }

        public int hashCode() {
            return this.f15394f.hashCode() ^ this.f15395g.hashCode();
        }

        public String toString() {
            return this.f15395g + "(" + this.f15394f + ")";
        }
    }

    public static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> map;

        public FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        @Override // com.google.common.base.Function
        public V apply(K k9) {
            V v8 = this.map.get(k9);
            Preconditions.checkArgument(v8 != null || this.map.containsKey(k9), "Key '%s' not present in map", k9);
            return v8;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof FunctionForMapNoDefault) {
                return this.map.equals(((FunctionForMapNoDefault) obj).map);
            }
            return false;
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ")";
        }
    }

    public enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override // com.google.common.base.Function
        public Object apply(Object obj) {
            return obj;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }
    }

    public static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final Predicate<T> predicate;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.base.Function
        public /* bridge */ /* synthetic */ Boolean apply(Object obj) {
            return apply((PredicateFunction<T>) obj);
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            return "Functions.forPredicate(" + this.predicate + ")";
        }

        private PredicateFunction(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Function
        public Boolean apply(T t8) {
            return Boolean.valueOf(this.predicate.apply(t8));
        }
    }

    public static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<T> supplier;

        @Override // com.google.common.base.Function
        public T apply(Object obj) {
            return this.supplier.get();
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            return "Functions.forSupplier(" + this.supplier + ")";
        }

        private SupplierFunction(Supplier<T> supplier) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }
    }

    public enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }

        @Override // com.google.common.base.Function
        public String apply(Object obj) {
            Preconditions.checkNotNull(obj);
            return obj.toString();
        }
    }

    private Functions() {
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> function, Function<A, ? extends B> function2) {
        return new FunctionComposition(function, function2);
    }

    public static <E> Function<Object, E> constant(E e9) {
        return new ConstantFunction(e9);
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, V v8) {
        return new ForMapWithDefault(map, v8);
    }
}
