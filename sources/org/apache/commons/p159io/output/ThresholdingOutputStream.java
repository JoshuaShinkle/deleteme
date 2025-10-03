package org.apache.commons.p159io.output;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public abstract class ThresholdingOutputStream extends OutputStream {
    private int threshold;
    private boolean thresholdExceeded;
    private long written;

    public ThresholdingOutputStream(int i9) {
        this.threshold = i9;
    }

    public void checkThreshold(int i9) {
        if (this.thresholdExceeded || this.written + i9 <= this.threshold) {
            return;
        }
        thresholdReached();
        this.thresholdExceeded = true;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
        } catch (IOException unused) {
        }
        getStream().close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        getStream().flush();
    }

    public long getByteCount() {
        return this.written;
    }

    public abstract OutputStream getStream();

    public int getThreshold() {
        return this.threshold;
    }

    public boolean isThresholdExceeded() {
        return this.written > ((long) this.threshold);
    }

    public abstract void thresholdReached();

    @Override // java.io.OutputStream
    public void write(int i9) throws IOException {
        checkThreshold(1);
        getStream().write(i9);
        this.written++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        checkThreshold(bArr.length);
        getStream().write(bArr);
        this.written += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        checkThreshold(i10);
        getStream().write(bArr, i9, i10);
        this.written += i10;
    }
}
