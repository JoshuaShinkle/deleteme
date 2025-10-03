package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Suppliers {

    @VisibleForTesting
    public static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        final long durationNanos;
        volatile transient long expirationNanos;
        volatile transient T value;

        public ExpiringMemoizingSupplier(Supplier<T> supplier, long j9, TimeUnit timeUnit) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
            this.durationNanos = timeUnit.toNanos(j9);
            Preconditions.checkArgument(j9 > 0);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            long j9 = this.expirationNanos;
            long jSystemNanoTime = Platform.systemNanoTime();
            if (j9 == 0 || jSystemNanoTime - j9 >= 0) {
                synchronized (this) {
                    if (j9 == this.expirationNanos) {
                        T t8 = this.delegate.get();
                        this.value = t8;
                        long j10 = jSystemNanoTime + this.durationNanos;
                        if (j10 == 0) {
                            j10 = 1;
                        }
                        this.expirationNanos = j10;
                        return t8;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            return "Suppliers.memoizeWithExpiration(" + this.delegate + ", " + this.durationNanos + ", NANOS)";
        }
    }

    @VisibleForTesting
    public static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        transient T value;

        public MemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t8 = this.delegate.get();
                        this.value = t8;
                        this.initialized = true;
                        return t8;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            return "Suppliers.memoize(" + this.delegate + ")";
        }
    }

    @VisibleForTesting
    public static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        volatile Supplier<T> delegate;
        volatile boolean initialized;
        T value;

        public NonSerializableMemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t8 = this.delegate.get();
                        this.value = t8;
                        this.initialized = true;
                        this.delegate = null;
                        return t8;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            return "Suppliers.memoize(" + this.delegate + ")";
        }
    }

    public static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Function<? super F, T> function;
        final Supplier<F> supplier;

        public SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            this.function = function;
            this.supplier = supplier;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierComposition)) {
                return false;
            }
            SupplierComposition supplierComposition = (SupplierComposition) obj;
            return this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public int hashCode() {
            return Objects.hashCode(this.function, this.supplier);
        }

        public String toString() {
            return "Suppliers.compose(" + this.function + ", " + this.supplier + ")";
        }
    }

    public interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    public enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Suppliers.supplierFunction()";
        }

        @Override // com.google.common.base.Function
        public Object apply(Supplier<Object> supplier) {
            return supplier.get();
        }
    }

    public static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final T instance;

        public SupplierOfInstance(T t8) {
            this.instance = t8;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.instance;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.instance + ")";
        }
    }

    public static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;

        public ThreadSafeSupplier(Supplier<T> supplier) {
            this.delegate = supplier;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            T t8;
            synchronized (this.delegate) {
                t8 = this.delegate.get();
            }
            return t8;
        }

        public String toString() {
            return "Suppliers.synchronizedSupplier(" + this.delegate + ")";
        }
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(supplier);
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        return ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) ? supplier : supplier instanceof Serializable ? new MemoizingSupplier(supplier) : new NonSerializableMemoizingSupplier(supplier);
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j9, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j9, timeUnit);
    }

    public static <T> Supplier<T> ofInstance(T t8) {
        return new SupplierOfInstance(t8);
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier((Supplier) Preconditions.checkNotNull(supplier));
    }
}
