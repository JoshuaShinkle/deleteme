package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

@GwtIncompatible
/* loaded from: classes2.dex */
public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private static final AtomicLongFieldUpdater<AtomicDouble> updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble.class, "value");
    private volatile transient long value;

    public AtomicDouble(double d9) {
        this.value = Double.doubleToRawLongBits(d9);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        set(objectInputStream.readDouble());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeDouble(get());
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double d9) {
        long j9;
        double dLongBitsToDouble;
        do {
            j9 = this.value;
            dLongBitsToDouble = Double.longBitsToDouble(j9) + d9;
        } while (!updater.compareAndSet(this, j9, Double.doubleToRawLongBits(dLongBitsToDouble)));
        return dLongBitsToDouble;
    }

    public final boolean compareAndSet(double d9, double d10) {
        return updater.compareAndSet(this, Double.doubleToRawLongBits(d9), Double.doubleToRawLongBits(d10));
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return get();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) get();
    }

    public final double get() {
        return Double.longBitsToDouble(this.value);
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double d9) {
        long j9;
        double dLongBitsToDouble;
        do {
            j9 = this.value;
            dLongBitsToDouble = Double.longBitsToDouble(j9);
        } while (!updater.compareAndSet(this, j9, Double.doubleToRawLongBits(dLongBitsToDouble + d9)));
        return dLongBitsToDouble;
    }

    public final double getAndSet(double d9) {
        return Double.longBitsToDouble(updater.getAndSet(this, Double.doubleToRawLongBits(d9)));
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) get();
    }

    public final void lazySet(double d9) {
        set(d9);
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) get();
    }

    public final void set(double d9) {
        this.value = Double.doubleToRawLongBits(d9);
    }

    public String toString() {
        return Double.toString(get());
    }

    public final boolean weakCompareAndSet(double d9, double d10) {
        return updater.weakCompareAndSet(this, Double.doubleToRawLongBits(d9), Double.doubleToRawLongBits(d10));
    }

    public AtomicDouble() {
    }
}
