package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

@GwtIncompatible
/* loaded from: classes2.dex */
final class CharSequenceReader extends Reader {
    private int mark;
    private int pos;
    private CharSequence seq;

    public CharSequenceReader(CharSequence charSequence) {
        this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        return this.seq.length() - this.pos;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.seq = null;
    }

    @Override // java.io.Reader
    public synchronized void mark(int i9) {
        Preconditions.checkArgument(i9 >= 0, "readAheadLimit (%s) may not be negative", i9);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer charBuffer) {
        Preconditions.checkNotNull(charBuffer);
        checkOpen();
        if (!hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(charBuffer.remaining(), remaining());
        for (int i9 = 0; i9 < iMin; i9++) {
            CharSequence charSequence = this.seq;
            int i10 = this.pos;
            this.pos = i10 + 1;
            charBuffer.put(charSequence.charAt(i10));
        }
        return iMin;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() {
        checkOpen();
        return true;
    }

    @Override // java.io.Reader
    public synchronized void reset() {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Reader
    public synchronized long skip(long j9) {
        int iMin;
        Preconditions.checkArgument(j9 >= 0, "n (%s) may not be negative", j9);
        checkOpen();
        iMin = (int) Math.min(remaining(), j9);
        this.pos += iMin;
        return iMin;
    }

    @Override // java.io.Reader
    public synchronized int read() {
        char cCharAt;
        checkOpen();
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i9 = this.pos;
            this.pos = i9 + 1;
            cCharAt = charSequence.charAt(i9);
        } else {
            cCharAt = 65535;
        }
        return cCharAt;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, cArr.length);
        checkOpen();
        if (!hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(i10, remaining());
        for (int i11 = 0; i11 < iMin; i11++) {
            CharSequence charSequence = this.seq;
            int i12 = this.pos;
            this.pos = i12 + 1;
            cArr[i9 + i11] = charSequence.charAt(i12);
        }
        return iMin;
    }
}
