package org.apache.commons.p159io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public abstract class ProxyInputStream extends FilterInputStream {
    public ProxyInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return ((FilterInputStream) this).in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterInputStream) this).in.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i9) {
        ((FilterInputStream) this).in.mark(i9);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return ((FilterInputStream) this).in.markSupported();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        return ((FilterInputStream) this).in.read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        ((FilterInputStream) this).in.reset();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j9) {
        return ((FilterInputStream) this).in.skip(j9);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return ((FilterInputStream) this).in.read(bArr);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) {
        return ((FilterInputStream) this).in.read(bArr, i9, i10);
    }
}
