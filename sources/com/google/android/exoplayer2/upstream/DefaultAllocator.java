package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class DefaultAllocator implements Allocator {
    private static final int AVAILABLE_EXTRA_CAPACITY = 100;
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    private final Allocation[] singleAllocationReleaseHolder;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean z8, int i9) {
        this(z8, i9, 0);
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized Allocation allocate() {
        Allocation allocation;
        this.allocatedCount++;
        int i9 = this.availableCount;
        if (i9 > 0) {
            Allocation[] allocationArr = this.availableAllocations;
            int i10 = i9 - 1;
            this.availableCount = i10;
            allocation = allocationArr[i10];
            allocationArr[i10] = null;
        } else {
            allocation = new Allocation(new byte[this.individualAllocationSize], 0);
        }
        return allocation;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void release(Allocation allocation) {
        Allocation[] allocationArr = this.singleAllocationReleaseHolder;
        allocationArr[0] = allocation;
        release(allocationArr);
    }

    public synchronized void reset() {
        if (this.trimOnReset) {
            setTargetBufferSize(0);
        }
    }

    public synchronized void setTargetBufferSize(int i9) {
        boolean z8 = i9 < this.targetBufferSize;
        this.targetBufferSize = i9;
        if (z8) {
            trim();
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void trim() {
        int i9 = 0;
        int iMax = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
        int i10 = this.availableCount;
        if (iMax >= i10) {
            return;
        }
        if (this.initialAllocationBlock != null) {
            int i11 = i10 - 1;
            while (i9 <= i11) {
                Allocation[] allocationArr = this.availableAllocations;
                Allocation allocation = allocationArr[i9];
                byte[] bArr = allocation.data;
                byte[] bArr2 = this.initialAllocationBlock;
                if (bArr == bArr2) {
                    i9++;
                } else {
                    Allocation allocation2 = allocationArr[i11];
                    if (allocation2.data != bArr2) {
                        i11--;
                    } else {
                        allocationArr[i9] = allocation2;
                        allocationArr[i11] = allocation;
                        i11--;
                        i9++;
                    }
                }
            }
            iMax = Math.max(iMax, i9);
            if (iMax >= this.availableCount) {
                return;
            }
        }
        Arrays.fill(this.availableAllocations, iMax, this.availableCount, (Object) null);
        this.availableCount = iMax;
    }

    public DefaultAllocator(boolean z8, int i9, int i10) {
        Assertions.checkArgument(i9 > 0);
        Assertions.checkArgument(i10 >= 0);
        this.trimOnReset = z8;
        this.individualAllocationSize = i9;
        this.availableCount = i10;
        this.availableAllocations = new Allocation[i10 + 100];
        if (i10 > 0) {
            this.initialAllocationBlock = new byte[i10 * i9];
            for (int i11 = 0; i11 < i10; i11++) {
                this.availableAllocations[i11] = new Allocation(this.initialAllocationBlock, i11 * i9);
            }
        } else {
            this.initialAllocationBlock = null;
        }
        this.singleAllocationReleaseHolder = new Allocation[1];
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void release(Allocation[] allocationArr) {
        int i9 = this.availableCount;
        int length = allocationArr.length + i9;
        Allocation[] allocationArr2 = this.availableAllocations;
        if (length >= allocationArr2.length) {
            this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, Math.max(allocationArr2.length * 2, i9 + allocationArr.length));
        }
        for (Allocation allocation : allocationArr) {
            byte[] bArr = allocation.data;
            Assertions.checkArgument(bArr == this.initialAllocationBlock || bArr.length == this.individualAllocationSize);
            Allocation[] allocationArr3 = this.availableAllocations;
            int i10 = this.availableCount;
            this.availableCount = i10 + 1;
            allocationArr3[i10] = allocation;
        }
        this.allocatedCount -= allocationArr.length;
        notifyAll();
    }
}
