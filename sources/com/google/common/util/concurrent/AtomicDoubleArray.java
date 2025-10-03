package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;

@GwtIncompatible
/* loaded from: classes2.dex */
public class AtomicDoubleArray implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int i9) {
        this.longs = new AtomicLongArray(i9);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i9 = objectInputStream.readInt();
        this.longs = new AtomicLongArray(i9);
        for (int i10 = 0; i10 < i9; i10++) {
            set(i10, objectInputStream.readDouble());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int i9 = 0; i9 < length; i9++) {
            objectOutputStream.writeDouble(get(i9));
        }
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i9, double d9) {
        long j9;
        double dLongBitsToDouble;
        do {
            j9 = this.longs.get(i9);
            dLongBitsToDouble = Double.longBitsToDouble(j9) + d9;
        } while (!this.longs.compareAndSet(i9, j9, Double.doubleToRawLongBits(dLongBitsToDouble)));
        return dLongBitsToDouble;
    }

    public final boolean compareAndSet(int i9, double d9, double d10) {
        return this.longs.compareAndSet(i9, Double.doubleToRawLongBits(d9), Double.doubleToRawLongBits(d10));
    }

    public final double get(int i9) {
        return Double.longBitsToDouble(this.longs.get(i9));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i9, double d9) {
        long j9;
        double dLongBitsToDouble;
        do {
            j9 = this.longs.get(i9);
            dLongBitsToDouble = Double.longBitsToDouble(j9);
        } while (!this.longs.compareAndSet(i9, j9, Double.doubleToRawLongBits(dLongBitsToDouble + d9)));
        return dLongBitsToDouble;
    }

    public final double getAndSet(int i9, double d9) {
        return Double.longBitsToDouble(this.longs.getAndSet(i9, Double.doubleToRawLongBits(d9)));
    }

    public final void lazySet(int i9, double d9) {
        set(i9, d9);
    }

    public final int length() {
        return this.longs.length();
    }

    public final void set(int i9, double d9) {
        this.longs.set(i9, Double.doubleToRawLongBits(d9));
    }

    public String toString() {
        int length = length() - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder((length + 1) * 19);
        sb.append('[');
        int i9 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i9)));
            if (i9 == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
            i9++;
        }
    }

    public final boolean weakCompareAndSet(int i9, double d9, double d10) {
        return this.longs.weakCompareAndSet(i9, Double.doubleToRawLongBits(d9), Double.doubleToRawLongBits(d10));
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int i9 = 0; i9 < length; i9++) {
            jArr[i9] = Double.doubleToRawLongBits(dArr[i9]);
        }
        this.longs = new AtomicLongArray(jArr);
    }
}
