package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataSource dataSource;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;
    private byte[] peekBuffer = new byte[65536];
    private final byte[] scratchSpace = new byte[4096];

    public DefaultExtractorInput(DataSource dataSource, long j9, long j10) {
        this.dataSource = dataSource;
        this.position = j9;
        this.streamLength = j10;
    }

    private void commitBytesRead(int i9) {
        if (i9 != -1) {
            this.position += i9;
        }
    }

    private void ensureSpaceForPeek(int i9) {
        int i10 = this.peekBufferPosition + i9;
        byte[] bArr = this.peekBuffer;
        if (i10 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + i10, i10 + PEEK_MAX_FREE_SPACE));
        }
    }

    private int readFromDataSource(byte[] bArr, int i9, int i10, int i11, boolean z8) throws InterruptedException, EOFException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i12 = this.dataSource.read(bArr, i9 + i11, i10 - i11);
        if (i12 != -1) {
            return i11 + i12;
        }
        if (i11 == 0 && z8) {
            return -1;
        }
        throw new EOFException();
    }

    private int readFromPeekBuffer(byte[] bArr, int i9, int i10) {
        int i11 = this.peekBufferLength;
        if (i11 == 0) {
            return 0;
        }
        int iMin = Math.min(i11, i10);
        System.arraycopy(this.peekBuffer, 0, bArr, i9, iMin);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private int skipFromPeekBuffer(int i9) {
        int iMin = Math.min(this.peekBufferLength, i9);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private void updatePeekBuffer(int i9) {
        int i10 = this.peekBufferLength - i9;
        this.peekBufferLength = i10;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        byte[] bArr2 = i10 < bArr.length - PEEK_MAX_FREE_SPACE ? new byte[65536 + i10] : bArr;
        System.arraycopy(bArr, i9, bArr2, 0, i10);
        this.peekBuffer = bArr2;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean advancePeekPosition(int i9, boolean z8) throws InterruptedException, EOFException {
        ensureSpaceForPeek(i9);
        int iMin = Math.min(this.peekBufferLength - this.peekBufferPosition, i9);
        while (iMin < i9) {
            iMin = readFromDataSource(this.peekBuffer, this.peekBufferPosition, i9, iMin, z8);
            if (iMin == -1) {
                return false;
            }
        }
        int i10 = this.peekBufferPosition + i9;
        this.peekBufferPosition = i10;
        this.peekBufferLength = Math.max(this.peekBufferLength, i10);
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + this.peekBufferPosition;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int i9, int i10, boolean z8) {
        if (!advancePeekPosition(i10, z8)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i10, bArr, i9, i10);
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int read(byte[] bArr, int i9, int i10) throws InterruptedException, EOFException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i9, i10);
        if (fromPeekBuffer == 0) {
            fromPeekBuffer = readFromDataSource(bArr, i9, i10, 0, true);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int i9, int i10, boolean z8) throws InterruptedException, EOFException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i9, i10);
        while (fromPeekBuffer < i10 && fromPeekBuffer != -1) {
            fromPeekBuffer = readFromDataSource(bArr, i9, i10, fromPeekBuffer, z8);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Throwable */
    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j9, E e9) throws E {
        Assertions.checkArgument(j9 >= 0);
        this.position = j9;
        throw e9;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int skip(int i9) throws InterruptedException, EOFException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i9);
        if (iSkipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            iSkipFromPeekBuffer = readFromDataSource(bArr, 0, Math.min(i9, bArr.length), 0, true);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean skipFully(int i9, boolean z8) throws InterruptedException, EOFException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i9);
        while (iSkipFromPeekBuffer < i9 && iSkipFromPeekBuffer != -1) {
            iSkipFromPeekBuffer = readFromDataSource(this.scratchSpace, -iSkipFromPeekBuffer, Math.min(i9, this.scratchSpace.length + iSkipFromPeekBuffer), iSkipFromPeekBuffer, z8);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int i9, int i10) {
        peekFully(bArr, i9, i10, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void readFully(byte[] bArr, int i9, int i10) throws InterruptedException, EOFException {
        readFully(bArr, i9, i10, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void advancePeekPosition(int i9) throws InterruptedException, EOFException {
        advancePeekPosition(i9, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void skipFully(int i9) throws InterruptedException, EOFException {
        skipFully(i9, false);
    }
}
