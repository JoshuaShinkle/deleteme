package com.google.android.exoplayer2.extractor.p037ts;

import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

/* loaded from: classes.dex */
final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int i9, int i10) {
        this.targetType = i9;
        byte[] bArr = new byte[i10 + 3];
        this.nalData = bArr;
        bArr[2] = 1;
    }

    public void appendToNalUnit(byte[] bArr, int i9, int i10) {
        if (this.isFilling) {
            int i11 = i10 - i9;
            byte[] bArr2 = this.nalData;
            int length = bArr2.length;
            int i12 = this.nalLength;
            if (length < i12 + i11) {
                this.nalData = Arrays.copyOf(bArr2, (i12 + i11) * 2);
            }
            System.arraycopy(bArr, i9, this.nalData, this.nalLength, i11);
            this.nalLength += i11;
        }
    }

    public boolean endNalUnit(int i9) {
        if (!this.isFilling) {
            return false;
        }
        this.nalLength -= i9;
        this.isFilling = false;
        this.isCompleted = true;
        return true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public void startNalUnit(int i9) {
        Assertions.checkState(!this.isFilling);
        boolean z8 = i9 == this.targetType;
        this.isFilling = z8;
        if (z8) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }
}
