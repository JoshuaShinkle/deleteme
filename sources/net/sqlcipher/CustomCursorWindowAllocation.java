package net.sqlcipher;

/* loaded from: classes2.dex */
public class CustomCursorWindowAllocation implements CursorWindowAllocation {
    private long growthPaddingSize;
    private long initialAllocationSize;
    private long maxAllocationSize;

    public CustomCursorWindowAllocation(long j9, long j10, long j11) {
        this.initialAllocationSize = j9;
        this.growthPaddingSize = j10;
        this.maxAllocationSize = j11;
    }

    @Override // net.sqlcipher.CursorWindowAllocation
    public long getGrowthPaddingSize() {
        return this.growthPaddingSize;
    }

    @Override // net.sqlcipher.CursorWindowAllocation
    public long getInitialAllocationSize() {
        return this.initialAllocationSize;
    }

    @Override // net.sqlcipher.CursorWindowAllocation
    public long getMaxAllocationSize() {
        return this.maxAllocationSize;
    }
}
