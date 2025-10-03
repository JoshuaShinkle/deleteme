package org.apache.commons.p159io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class NullInputStream extends InputStream {
    private boolean eof;
    private long mark;
    private boolean markSupported;
    private long position;
    private long readlimit;
    private long size;
    private boolean throwEofException;

    public NullInputStream(long j9) {
        this(j9, true, false);
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (this.throwEofException) {
            throw new EOFException();
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int available() {
        long j9 = this.size - this.position;
        if (j9 <= 0) {
            return 0;
        }
        if (j9 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j9;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.eof = false;
        this.position = 0L;
        this.mark = -1L;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i9) {
        if (!this.markSupported) {
            throw new UnsupportedOperationException("Mark not supported");
        }
        this.mark = this.position;
        this.readlimit = i9;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.markSupported;
    }

    public int processByte() {
        return 0;
    }

    public void processBytes(byte[] bArr, int i9, int i10) {
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        long j9 = this.position;
        if (j9 == this.size) {
            return doEndOfFile();
        }
        this.position = j9 + 1;
        return processByte();
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        if (!this.markSupported) {
            throw new UnsupportedOperationException("Mark not supported");
        }
        long j9 = this.mark;
        if (j9 < 0) {
            throw new IOException("No position has been marked");
        }
        if (this.position > this.readlimit + j9) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Marked position [");
            stringBuffer.append(this.mark);
            stringBuffer.append("] is no longer valid - passed the read limit [");
            stringBuffer.append(this.readlimit);
            stringBuffer.append("]");
            throw new IOException(stringBuffer.toString());
        }
        this.position = j9;
        this.eof = false;
    }

    @Override // java.io.InputStream
    public long skip(long j9) throws IOException {
        if (this.eof) {
            throw new IOException("Skip after end of file");
        }
        long j10 = this.position;
        long j11 = this.size;
        if (j10 == j11) {
            return doEndOfFile();
        }
        long j12 = j10 + j9;
        this.position = j12;
        if (j12 <= j11) {
            return j9;
        }
        long j13 = j9 - (j12 - j11);
        this.position = j11;
        return j13;
    }

    public NullInputStream(long j9, boolean z8, boolean z9) {
        this.mark = -1L;
        this.size = j9;
        this.markSupported = z8;
        this.throwEofException = z9;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        if (!this.eof) {
            long j9 = this.position;
            long j10 = this.size;
            if (j9 == j10) {
                return doEndOfFile();
            }
            long j11 = j9 + i10;
            this.position = j11;
            if (j11 > j10) {
                i10 -= (int) (j11 - j10);
                this.position = j10;
            }
            processBytes(bArr, i9, i10);
            return i10;
        }
        throw new IOException("Read after end of file");
    }
}
