package com.google.android.exoplayer2.extractor;

/* loaded from: classes.dex */
public interface ExtractorInput {
    void advancePeekPosition(int i9);

    boolean advancePeekPosition(int i9, boolean z8);

    long getLength();

    long getPeekPosition();

    long getPosition();

    void peekFully(byte[] bArr, int i9, int i10);

    boolean peekFully(byte[] bArr, int i9, int i10, boolean z8);

    int read(byte[] bArr, int i9, int i10);

    void readFully(byte[] bArr, int i9, int i10);

    boolean readFully(byte[] bArr, int i9, int i10, boolean z8);

    void resetPeekPosition();

    <E extends Throwable> void setRetryPosition(long j9, E e9);

    int skip(int i9);

    void skipFully(int i9);

    boolean skipFully(int i9, boolean z8);
}
