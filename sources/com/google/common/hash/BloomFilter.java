package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;

@Beta
/* loaded from: classes2.dex */
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private final BloomFilterStrategies.LockFreeBitArray bits;
    private final Funnel<? super T> funnel;
    private final int numHashFunctions;
    private final Strategy strategy;

    public static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        public SerialForm(BloomFilter<T> bloomFilter) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(((BloomFilter) bloomFilter).bits.data);
            this.numHashFunctions = ((BloomFilter) bloomFilter).numHashFunctions;
            this.funnel = ((BloomFilter) bloomFilter).funnel;
            this.strategy = ((BloomFilter) bloomFilter).strategy;
        }

        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    public interface Strategy extends Serializable {
        <T> boolean mightContain(T t8, Funnel<? super T> funnel, int i9, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(T t8, Funnel<? super T> funnel, int i9, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i9, double d9) {
        return create(funnel, i9, d9);
    }

    @VisibleForTesting
    public static long optimalNumOfBits(long j9, double d9) {
        if (d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d9 = Double.MIN_VALUE;
        }
        return (long) (((-j9) * Math.log(d9)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    @VisibleForTesting
    public static int optimalNumOfHashFunctions(long j9, long j10) {
        return Math.max(1, (int) Math.round((j10 / j9) * Math.log(2.0d)));
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel) throws IOException {
        int i9;
        int i10;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        int i11 = -1;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte b9 = dataInputStream.readByte();
            try {
                i10 = UnsignedBytes.toInt(dataInputStream.readByte());
            } catch (RuntimeException e9) {
                e = e9;
                i10 = -1;
                i11 = b9;
                i9 = -1;
            }
            try {
                i11 = dataInputStream.readInt();
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b9];
                long[] jArr = new long[i11];
                for (int i12 = 0; i12 < i11; i12++) {
                    jArr[i12] = dataInputStream.readLong();
                }
                return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jArr), i10, funnel, bloomFilterStrategies);
            } catch (RuntimeException e10) {
                e = e10;
                int i13 = i11;
                i11 = b9;
                i9 = i13;
                throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + i11 + " numHashFunctions: " + i10 + " dataLength: " + i9, e);
            }
        } catch (RuntimeException e11) {
            e = e11;
            i9 = -1;
            i10 = -1;
        }
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(T t8) {
        return mightContain(t8);
    }

    public long approximateElementCount() {
        double dBitSize = this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(this.bits.bitCount() / dBitSize))) * dBitSize) / this.numHashFunctions, RoundingMode.HALF_UP);
    }

    @VisibleForTesting
    public long bitSize() {
        return this.bits.bitSize();
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
    }

    public double expectedFpp() {
        return Math.pow(this.bits.bitCount() / bitSize(), this.numHashFunctions);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public boolean mightContain(T t8) {
        return this.strategy.mightContain(t8, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(T t8) {
        return this.strategy.put(t8, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        int i9 = this.numHashFunctions;
        int i10 = bloomFilter.numHashFunctions;
        Preconditions.checkArgument(i9 == i10, "BloomFilters must have the same number of hash functions (%s != %s)", i9, i10);
        Preconditions.checkArgument(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", bitSize(), bloomFilter.bitSize());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, bloomFilter.funnel);
        this.bits.putAll(bloomFilter.bits);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast(this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast(this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i9 = 0; i9 < this.bits.data.length(); i9++) {
            dataOutputStream.writeLong(this.bits.data.get(i9));
        }
    }

    private BloomFilter(BloomFilterStrategies.LockFreeBitArray lockFreeBitArray, int i9, Funnel<? super T> funnel, Strategy strategy) {
        Preconditions.checkArgument(i9 > 0, "numHashFunctions (%s) must be > 0", i9);
        Preconditions.checkArgument(i9 <= 255, "numHashFunctions (%s) must be <= 255", i9);
        this.bits = (BloomFilterStrategies.LockFreeBitArray) Preconditions.checkNotNull(lockFreeBitArray);
        this.numHashFunctions = i9;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j9, double d9) {
        return create(funnel, j9, d9, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j9, double d9, Strategy strategy) {
        Preconditions.checkNotNull(funnel);
        Preconditions.checkArgument(j9 >= 0, "Expected insertions (%s) must be >= 0", j9);
        Preconditions.checkArgument(d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "False positive probability (%s) must be > 0.0", Double.valueOf(d9));
        Preconditions.checkArgument(d9 < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(d9));
        Preconditions.checkNotNull(strategy);
        if (j9 == 0) {
            j9 = 1;
        }
        long jOptimalNumOfBits = optimalNumOfBits(j9, d9);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jOptimalNumOfBits), optimalNumOfHashFunctions(j9, jOptimalNumOfBits), funnel, strategy);
        } catch (IllegalArgumentException e9) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + jOptimalNumOfBits + " bits", e9);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i9) {
        return create(funnel, i9);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j9) {
        return create(funnel, j9, 0.03d);
    }
}
