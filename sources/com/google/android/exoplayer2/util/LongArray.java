package com.google.android.exoplayer2.util;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class LongArray {
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public void add(long j9) {
        int i9 = this.size;
        long[] jArr = this.values;
        if (i9 == jArr.length) {
            this.values = Arrays.copyOf(jArr, i9 * 2);
        }
        long[] jArr2 = this.values;
        int i10 = this.size;
        this.size = i10 + 1;
        jArr2[i10] = j9;
    }

    public long get(int i9) {
        if (i9 >= 0 && i9 < this.size) {
            return this.values[i9];
        }
        throw new IndexOutOfBoundsException("Invalid index " + i9 + ", size is " + this.size);
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }

    public LongArray(int i9) {
        this.values = new long[i9];
    }
}
